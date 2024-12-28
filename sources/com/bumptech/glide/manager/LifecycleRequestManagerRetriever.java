package com.bumptech.glide.manager;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class LifecycleRequestManagerRetriever {
    private final RequestManagerRetriever.RequestManagerFactory factory;
    final Map<Lifecycle, RequestManager> lifecycleToRequestManager = new HashMap();

    LifecycleRequestManagerRetriever(RequestManagerRetriever.RequestManagerFactory factory2) {
        this.factory = factory2;
    }

    /* access modifiers changed from: package-private */
    public RequestManager getOnly(Lifecycle lifecycle) {
        Util.assertMainThread();
        return this.lifecycleToRequestManager.get(lifecycle);
    }

    /* access modifiers changed from: package-private */
    public RequestManager getOrCreate(Context context, Glide glide, final Lifecycle lifecycle, FragmentManager childFragmentManager, boolean isParentVisible) {
        Util.assertMainThread();
        RequestManager result = getOnly(lifecycle);
        if (result == null) {
            LifecycleLifecycle glideLifecycle = new LifecycleLifecycle(lifecycle);
            result = this.factory.build(glide, glideLifecycle, new SupportRequestManagerTreeNode(childFragmentManager), context);
            this.lifecycleToRequestManager.put(lifecycle, result);
            glideLifecycle.addListener(new LifecycleListener() {
                public void onStart() {
                }

                public void onStop() {
                }

                public void onDestroy() {
                    LifecycleRequestManagerRetriever.this.lifecycleToRequestManager.remove(lifecycle);
                }
            });
            if (isParentVisible) {
                result.onStart();
            }
        }
        return result;
    }

    private final class SupportRequestManagerTreeNode implements RequestManagerTreeNode {
        private final FragmentManager childFragmentManager;

        SupportRequestManagerTreeNode(FragmentManager childFragmentManager2) {
            this.childFragmentManager = childFragmentManager2;
        }

        public Set<RequestManager> getDescendants() {
            Set<RequestManager> result = new HashSet<>();
            getChildFragmentsRecursive(this.childFragmentManager, result);
            return result;
        }

        private void getChildFragmentsRecursive(FragmentManager fragmentManager, Set<RequestManager> requestManagers) {
            List<Fragment> children = fragmentManager.getFragments();
            int size = children.size();
            for (int i = 0; i < size; i++) {
                Fragment child = children.get(i);
                getChildFragmentsRecursive(child.getChildFragmentManager(), requestManagers);
                RequestManager fromChild = LifecycleRequestManagerRetriever.this.getOnly(child.getLifecycle());
                if (fromChild != null) {
                    requestManagers.add(fromChild);
                }
            }
        }
    }
}
