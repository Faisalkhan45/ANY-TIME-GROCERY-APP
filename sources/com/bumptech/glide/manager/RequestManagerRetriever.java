package com.bumptech.glide.manager;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.GlideExperiments;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Handler.Callback {
    private static final RequestManagerFactory DEFAULT_FACTORY = new RequestManagerFactory() {
        public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
            return new RequestManager(glide, lifecycle, requestManagerTreeNode, context);
        }
    };
    private static final String FRAGMENT_INDEX_KEY = "key";
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";
    private static final int HAS_ATTEMPTED_TO_ADD_FRAGMENT_TWICE = 1;
    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;
    private static final String TAG = "RMRetriever";
    private volatile RequestManager applicationManager;
    private final GlideExperiments experiments;
    private final RequestManagerFactory factory;
    private final FrameWaiter frameWaiter;
    private final Handler handler;
    private final LifecycleRequestManagerRetriever lifecycleRequestManagerRetriever;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap();
    final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments = new HashMap();
    private final Bundle tempBundle = new Bundle();
    private final ArrayMap<View, Fragment> tempViewToFragment = new ArrayMap<>();
    private final ArrayMap<View, androidx.fragment.app.Fragment> tempViewToSupportFragment = new ArrayMap<>();

    public interface RequestManagerFactory {
        RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context);
    }

    public RequestManagerRetriever(RequestManagerFactory factory2, GlideExperiments experiments2) {
        RequestManagerFactory requestManagerFactory = factory2 != null ? factory2 : DEFAULT_FACTORY;
        this.factory = requestManagerFactory;
        this.experiments = experiments2;
        this.handler = new Handler(Looper.getMainLooper(), this);
        this.lifecycleRequestManagerRetriever = new LifecycleRequestManagerRetriever(requestManagerFactory);
        this.frameWaiter = buildFrameWaiter(experiments2);
    }

    private static FrameWaiter buildFrameWaiter(GlideExperiments experiments2) {
        if (!HardwareConfigState.HARDWARE_BITMAPS_SUPPORTED || !HardwareConfigState.BLOCK_HARDWARE_BITMAPS_WHEN_GL_CONTEXT_MIGHT_NOT_BE_INITIALIZED) {
            return new DoNothingFirstFrameWaiter();
        }
        if (experiments2.isEnabled(GlideBuilder.WaitForFramesAfterTrimMemory.class)) {
            return new FirstFrameAndAfterTrimMemoryWaiter();
        }
        return new FirstFrameWaiter();
    }

    private RequestManager getApplicationManager(Context context) {
        if (this.applicationManager == null) {
            synchronized (this) {
                if (this.applicationManager == null) {
                    this.applicationManager = this.factory.build(Glide.get(context.getApplicationContext()), new ApplicationLifecycle(), new EmptyRequestManagerTreeNode(), context.getApplicationContext());
                }
            }
        }
        return this.applicationManager;
    }

    public RequestManager get(Context context) {
        if (context != null) {
            if (Util.isOnMainThread() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return get((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return get((Activity) context);
                }
                if ((context instanceof ContextWrapper) && ((ContextWrapper) context).getBaseContext().getApplicationContext() != null) {
                    return get(((ContextWrapper) context).getBaseContext());
                }
            }
            return getApplicationManager(context);
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    public RequestManager get(FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        assertNotDestroyed(activity);
        this.frameWaiter.registerSelf(activity);
        androidx.fragment.app.FragmentManager fm = activity.getSupportFragmentManager();
        boolean isActivityVisible = isActivityVisible(activity);
        if (!useLifecycleInsteadOfInjectingFragments()) {
            return supportFragmentGet(activity, fm, (androidx.fragment.app.Fragment) null, isActivityVisible);
        }
        Context context = activity.getApplicationContext();
        return this.lifecycleRequestManagerRetriever.getOrCreate(context, Glide.get(context), activity.getLifecycle(), activity.getSupportFragmentManager(), isActivityVisible);
    }

    private boolean useLifecycleInsteadOfInjectingFragments() {
        return this.experiments.isEnabled(GlideBuilder.UseLifecycleInsteadOfInjectingFragments.class);
    }

    public RequestManager get(androidx.fragment.app.Fragment fragment) {
        Preconditions.checkNotNull(fragment.getContext(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getContext().getApplicationContext());
        }
        if (fragment.getActivity() != null) {
            this.frameWaiter.registerSelf(fragment.getActivity());
        }
        androidx.fragment.app.FragmentManager fm = fragment.getChildFragmentManager();
        Context context = fragment.getContext();
        if (!useLifecycleInsteadOfInjectingFragments()) {
            return supportFragmentGet(context, fm, fragment, fragment.isVisible());
        }
        return this.lifecycleRequestManagerRetriever.getOrCreate(context, Glide.get(context.getApplicationContext()), fragment.getLifecycle(), fm, fragment.isVisible());
    }

    @Deprecated
    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        }
        if (activity instanceof FragmentActivity) {
            return get((FragmentActivity) activity);
        }
        assertNotDestroyed(activity);
        this.frameWaiter.registerSelf(activity);
        return fragmentGet(activity, activity.getFragmentManager(), (Fragment) null, isActivityVisible(activity));
    }

    public RequestManager get(View view) {
        if (Util.isOnBackgroundThread()) {
            return get(view.getContext().getApplicationContext());
        }
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activity = findActivity(view.getContext());
        if (activity == null) {
            return get(view.getContext().getApplicationContext());
        }
        if (activity instanceof FragmentActivity) {
            androidx.fragment.app.Fragment fragment = findSupportFragment(view, (FragmentActivity) activity);
            return fragment != null ? get(fragment) : get((FragmentActivity) activity);
        }
        Fragment fragment2 = findFragment(view, activity);
        if (fragment2 == null) {
            return get(activity);
        }
        return get(fragment2);
    }

    private static void findAllSupportFragmentsWithViews(Collection<androidx.fragment.app.Fragment> topLevelFragments, Map<View, androidx.fragment.app.Fragment> result) {
        if (topLevelFragments != null) {
            for (androidx.fragment.app.Fragment fragment : topLevelFragments) {
                if (!(fragment == null || fragment.getView() == null)) {
                    result.put(fragment.getView(), fragment);
                    findAllSupportFragmentsWithViews(fragment.getChildFragmentManager().getFragments(), result);
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v6, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.fragment.app.Fragment findSupportFragment(android.view.View r5, androidx.fragment.app.FragmentActivity r6) {
        /*
            r4 = this;
            androidx.collection.ArrayMap<android.view.View, androidx.fragment.app.Fragment> r0 = r4.tempViewToSupportFragment
            r0.clear()
            androidx.fragment.app.FragmentManager r0 = r6.getSupportFragmentManager()
            java.util.List r0 = r0.getFragments()
            androidx.collection.ArrayMap<android.view.View, androidx.fragment.app.Fragment> r1 = r4.tempViewToSupportFragment
            findAllSupportFragmentsWithViews(r0, r1)
            r0 = 0
            r1 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r6.findViewById(r1)
            r2 = r5
        L_0x001c:
            boolean r3 = r2.equals(r1)
            if (r3 != 0) goto L_0x003e
            androidx.collection.ArrayMap<android.view.View, androidx.fragment.app.Fragment> r3 = r4.tempViewToSupportFragment
            java.lang.Object r3 = r3.get(r2)
            r0 = r3
            androidx.fragment.app.Fragment r0 = (androidx.fragment.app.Fragment) r0
            if (r0 == 0) goto L_0x002e
            goto L_0x003e
        L_0x002e:
            android.view.ViewParent r3 = r2.getParent()
            boolean r3 = r3 instanceof android.view.View
            if (r3 == 0) goto L_0x003e
            android.view.ViewParent r3 = r2.getParent()
            r2 = r3
            android.view.View r2 = (android.view.View) r2
            goto L_0x001c
        L_0x003e:
            androidx.collection.ArrayMap<android.view.View, androidx.fragment.app.Fragment> r3 = r4.tempViewToSupportFragment
            r3.clear()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerRetriever.findSupportFragment(android.view.View, androidx.fragment.app.FragmentActivity):androidx.fragment.app.Fragment");
    }

    /* JADX WARNING: type inference failed for: r3v6, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.app.Fragment findFragment(android.view.View r5, android.app.Activity r6) {
        /*
            r4 = this;
            androidx.collection.ArrayMap<android.view.View, android.app.Fragment> r0 = r4.tempViewToFragment
            r0.clear()
            android.app.FragmentManager r0 = r6.getFragmentManager()
            androidx.collection.ArrayMap<android.view.View, android.app.Fragment> r1 = r4.tempViewToFragment
            r4.findAllFragmentsWithViews(r0, r1)
            r0 = 0
            r1 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r1 = r6.findViewById(r1)
            r2 = r5
        L_0x0017:
            boolean r3 = r2.equals(r1)
            if (r3 != 0) goto L_0x0039
            androidx.collection.ArrayMap<android.view.View, android.app.Fragment> r3 = r4.tempViewToFragment
            java.lang.Object r3 = r3.get(r2)
            r0 = r3
            android.app.Fragment r0 = (android.app.Fragment) r0
            if (r0 == 0) goto L_0x0029
            goto L_0x0039
        L_0x0029:
            android.view.ViewParent r3 = r2.getParent()
            boolean r3 = r3 instanceof android.view.View
            if (r3 == 0) goto L_0x0039
            android.view.ViewParent r3 = r2.getParent()
            r2 = r3
            android.view.View r2 = (android.view.View) r2
            goto L_0x0017
        L_0x0039:
            androidx.collection.ArrayMap<android.view.View, android.app.Fragment> r3 = r4.tempViewToFragment
            r3.clear()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerRetriever.findFragment(android.view.View, android.app.Activity):android.app.Fragment");
    }

    @Deprecated
    private void findAllFragmentsWithViews(FragmentManager fragmentManager, ArrayMap<View, Fragment> result) {
        if (Build.VERSION.SDK_INT >= 26) {
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    result.put(fragment.getView(), fragment);
                    findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                }
            }
            return;
        }
        findAllFragmentsWithViewsPreO(fragmentManager, result);
    }

    @Deprecated
    private void findAllFragmentsWithViewsPreO(FragmentManager fragmentManager, ArrayMap<View, Fragment> result) {
        int index = 0;
        while (true) {
            int index2 = index + 1;
            this.tempBundle.putInt(FRAGMENT_INDEX_KEY, index);
            Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.tempBundle, FRAGMENT_INDEX_KEY);
            } catch (Exception e) {
            }
            if (fragment != null) {
                if (fragment.getView() != null) {
                    result.put(fragment.getView(), fragment);
                    if (Build.VERSION.SDK_INT >= 17) {
                        findAllFragmentsWithViews(fragment.getChildFragmentManager(), result);
                    }
                }
                index = index2;
            } else {
                return;
            }
        }
    }

    private static Activity findActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private static void assertNotDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @Deprecated
    public RequestManager get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < 17) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            if (fragment.getActivity() != null) {
                this.frameWaiter.registerSelf(fragment.getActivity());
            }
            return fragmentGet(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
        }
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public RequestManagerFragment getRequestManagerFragment(Activity activity) {
        return getRequestManagerFragment(activity.getFragmentManager(), (Fragment) null);
    }

    private RequestManagerFragment getRequestManagerFragment(FragmentManager fm, Fragment parentHint) {
        RequestManagerFragment current = this.pendingRequestManagerFragments.get(fm);
        if (current != null) {
            return current;
        }
        RequestManagerFragment current2 = (RequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current2 != null) {
            return current2;
        }
        RequestManagerFragment current3 = new RequestManagerFragment();
        current3.setParentFragmentHint(parentHint);
        this.pendingRequestManagerFragments.put(fm, current3);
        fm.beginTransaction().add(current3, FRAGMENT_TAG).commitAllowingStateLoss();
        this.handler.obtainMessage(1, fm).sendToTarget();
        return current3;
    }

    @Deprecated
    private RequestManager fragmentGet(Context context, FragmentManager fm, Fragment parentHint, boolean isParentVisible) {
        RequestManagerFragment current = getRequestManagerFragment(fm, parentHint);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            requestManager = this.factory.build(Glide.get(context), current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
            if (isParentVisible) {
                requestManager.onStart();
            }
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }

    /* access modifiers changed from: package-private */
    public SupportRequestManagerFragment getSupportRequestManagerFragment(androidx.fragment.app.FragmentManager fragmentManager) {
        return getSupportRequestManagerFragment(fragmentManager, (androidx.fragment.app.Fragment) null);
    }

    private static boolean isActivityVisible(Context context) {
        Activity activity = findActivity(context);
        return activity == null || !activity.isFinishing();
    }

    private SupportRequestManagerFragment getSupportRequestManagerFragment(androidx.fragment.app.FragmentManager fm, androidx.fragment.app.Fragment parentHint) {
        SupportRequestManagerFragment current = this.pendingSupportRequestManagerFragments.get(fm);
        if (current != null) {
            return current;
        }
        SupportRequestManagerFragment current2 = (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current2 != null) {
            return current2;
        }
        SupportRequestManagerFragment current3 = new SupportRequestManagerFragment();
        current3.setParentFragmentHint(parentHint);
        this.pendingSupportRequestManagerFragments.put(fm, current3);
        fm.beginTransaction().add((androidx.fragment.app.Fragment) current3, FRAGMENT_TAG).commitAllowingStateLoss();
        this.handler.obtainMessage(2, fm).sendToTarget();
        return current3;
    }

    private RequestManager supportFragmentGet(Context context, androidx.fragment.app.FragmentManager fm, androidx.fragment.app.Fragment parentHint, boolean isParentVisible) {
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm, parentHint);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            requestManager = this.factory.build(Glide.get(context), current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
            if (isParentVisible) {
                requestManager.onStart();
            }
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }

    private boolean verifyOurFragmentWasAddedOrCantBeAdded(FragmentManager fm, boolean hasAttemptedToAddFragmentTwice) {
        RequestManagerFragment newlyAddedRequestManagerFragment = this.pendingRequestManagerFragments.get(fm);
        RequestManagerFragment actualFragment = (RequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (actualFragment == newlyAddedRequestManagerFragment) {
            return true;
        }
        if (actualFragment != null && actualFragment.getRequestManager() != null) {
            throw new IllegalStateException("We've added two fragments with requests! Old: " + actualFragment + " New: " + newlyAddedRequestManagerFragment);
        } else if (hasAttemptedToAddFragmentTwice || fm.isDestroyed()) {
            if (Log.isLoggable(TAG, 5)) {
                if (fm.isDestroyed()) {
                    Log.w(TAG, "Parent was destroyed before our Fragment could be added");
                } else {
                    Log.w(TAG, "Tried adding Fragment twice and failed twice, giving up!");
                }
            }
            newlyAddedRequestManagerFragment.getGlideLifecycle().onDestroy();
            return true;
        } else {
            FragmentTransaction transaction = fm.beginTransaction().add(newlyAddedRequestManagerFragment, FRAGMENT_TAG);
            if (actualFragment != null) {
                transaction.remove(actualFragment);
            }
            transaction.commitAllowingStateLoss();
            this.handler.obtainMessage(1, 1, 0, fm).sendToTarget();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "We failed to add our Fragment the first time around, trying again...");
            }
            return false;
        }
    }

    private boolean verifyOurSupportFragmentWasAddedOrCantBeAdded(androidx.fragment.app.FragmentManager supportFm, boolean hasAttemptedToAddFragmentTwice) {
        SupportRequestManagerFragment newlyAddedSupportRequestManagerFragment = this.pendingSupportRequestManagerFragments.get(supportFm);
        SupportRequestManagerFragment actualFragment = (SupportRequestManagerFragment) supportFm.findFragmentByTag(FRAGMENT_TAG);
        if (actualFragment == newlyAddedSupportRequestManagerFragment) {
            return true;
        }
        if (actualFragment != null && actualFragment.getRequestManager() != null) {
            throw new IllegalStateException("We've added two fragments with requests! Old: " + actualFragment + " New: " + newlyAddedSupportRequestManagerFragment);
        } else if (hasAttemptedToAddFragmentTwice || supportFm.isDestroyed()) {
            if (supportFm.isDestroyed()) {
                if (Log.isLoggable(TAG, 5)) {
                    Log.w(TAG, "Parent was destroyed before our Fragment could be added, all requests for the destroyed parent are cancelled");
                }
            } else if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "ERROR: Tried adding Fragment twice and failed twice, giving up and cancelling all associated requests! This probably means you're starting loads in a unit test with an Activity that you haven't created and never create. If you're using Robolectric, create the Activity as part of your test setup");
            }
            newlyAddedSupportRequestManagerFragment.getGlideLifecycle().onDestroy();
            return true;
        } else {
            androidx.fragment.app.FragmentTransaction transaction = supportFm.beginTransaction().add((androidx.fragment.app.Fragment) newlyAddedSupportRequestManagerFragment, FRAGMENT_TAG);
            if (actualFragment != null) {
                transaction.remove(actualFragment);
            }
            transaction.commitNowAllowingStateLoss();
            this.handler.obtainMessage(2, 1, 0, supportFm).sendToTarget();
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "We failed to add our Fragment the first time around, trying again...");
            }
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: android.app.FragmentManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.app.FragmentManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.app.FragmentManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: androidx.fragment.app.FragmentManager} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: android.app.FragmentManager} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleMessage(android.os.Message r9) {
        /*
            r8 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            r3 = 0
            int r4 = r9.arg1
            r5 = 1
            if (r4 != r5) goto L_0x000a
            goto L_0x000b
        L_0x000a:
            r5 = 0
        L_0x000b:
            r4 = r5
            int r5 = r9.what
            switch(r5) {
                case 1: goto L_0x0026;
                case 2: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            r0 = 0
            goto L_0x0038
        L_0x0013:
            java.lang.Object r5 = r9.obj
            androidx.fragment.app.FragmentManager r5 = (androidx.fragment.app.FragmentManager) r5
            boolean r6 = r8.verifyOurSupportFragmentWasAddedOrCantBeAdded(r5, r4)
            if (r6 == 0) goto L_0x0038
            r1 = 1
            r3 = r5
            java.util.Map<androidx.fragment.app.FragmentManager, com.bumptech.glide.manager.SupportRequestManagerFragment> r6 = r8.pendingSupportRequestManagerFragments
            java.lang.Object r2 = r6.remove(r5)
            goto L_0x0038
        L_0x0026:
            java.lang.Object r5 = r9.obj
            android.app.FragmentManager r5 = (android.app.FragmentManager) r5
            boolean r6 = r8.verifyOurFragmentWasAddedOrCantBeAdded(r5, r4)
            if (r6 == 0) goto L_0x0038
            r1 = 1
            r3 = r5
            java.util.Map<android.app.FragmentManager, com.bumptech.glide.manager.RequestManagerFragment> r6 = r8.pendingRequestManagerFragments
            java.lang.Object r2 = r6.remove(r5)
        L_0x0038:
            r5 = 5
            java.lang.String r6 = "RMRetriever"
            boolean r5 = android.util.Log.isLoggable(r6, r5)
            if (r5 == 0) goto L_0x005b
            if (r1 == 0) goto L_0x005b
            if (r2 != 0) goto L_0x005b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Failed to remove expected request manager fragment, manager: "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r5 = r5.toString()
            android.util.Log.w(r6, r5)
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.manager.RequestManagerRetriever.handleMessage(android.os.Message):boolean");
    }
}
