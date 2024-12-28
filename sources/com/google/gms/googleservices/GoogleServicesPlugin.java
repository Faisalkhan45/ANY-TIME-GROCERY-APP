package com.google.gms.googleservices;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.dependencies.DependencyAnalyzer;
import com.google.android.gms.dependencies.DependencyInspector;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import groovy.lang.Reference;
import groovy.transform.Generated;
import groovy.transform.Internal;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.Iterator;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.GeneratedClosure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.typehandling.ShortTypeHandling;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/* compiled from: GoogleServicesPlugin.groovy */
public class GoogleServicesPlugin implements Plugin<Project>, GroovyObject {
    private static /* synthetic */ SoftReference $callSiteArray = null;
    private static /* synthetic */ ClassInfo $staticClassInfo = null;
    private static /* synthetic */ ClassInfo $staticClassInfo$ = null;
    public static final String MINIMUM_VERSION = "9.0.0";
    public static final String MODULE_CORE = "firebase-core";
    public static final String MODULE_GROUP = "com.google.android.gms";
    public static final String MODULE_GROUP_FIREBASE = "com.google.firebase";
    public static final String MODULE_VERSION = "11.4.2";
    public static transient /* synthetic */ boolean __$stMC;
    private transient /* synthetic */ MetaClass metaClass = $getStaticMetaClass();

    /* compiled from: GoogleServicesPlugin.groovy */
    public static class GoogleServicesPluginConfig implements GroovyObject {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        private static /* synthetic */ ClassInfo $staticClassInfo$;
        public static transient /* synthetic */ boolean __$stMC;
        private boolean disableVersionCheck = false;
        private transient /* synthetic */ MetaClass metaClass = $getStaticMetaClass();

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            return new CallSiteArray(GoogleServicesPluginConfig.class, new String[0]);
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        public static /* synthetic */ Object $static_methodMissing(String str, Object obj) {
            $getCallSiteArray();
            String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
            String str2 = castToString;
            return ScriptBytecodeAdapter.invokeMethodN(GoogleServicesPluginConfig.class, GoogleServicesPlugin.class, castToString, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{obj}, new int[]{0}));
        }

        public static /* synthetic */ Object $static_propertyMissing(String str) {
            $getCallSiteArray();
            String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
            String str2 = castToString;
            return ScriptBytecodeAdapter.getProperty(GoogleServicesPluginConfig.class, GoogleServicesPlugin.class, castToString);
        }

        public static /* synthetic */ void $static_propertyMissing(String str, Object obj) {
            $getCallSiteArray();
            String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
            String str2 = castToString;
            ScriptBytecodeAdapter.setProperty(obj, (Class) null, GoogleServicesPlugin.class, castToString);
        }

        @Generated
        public GoogleServicesPluginConfig() {
            $getCallSiteArray();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != GoogleServicesPluginConfig.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public boolean getDisableVersionCheck() {
            return this.disableVersionCheck;
        }

        @Generated
        @Internal
        public /* synthetic */ MetaClass getMetaClass() {
            MetaClass metaClass2 = this.metaClass;
            if (metaClass2 != null) {
                return metaClass2;
            }
            MetaClass $getStaticMetaClass = $getStaticMetaClass();
            this.metaClass = $getStaticMetaClass;
            return $getStaticMetaClass;
        }

        @Generated
        @Internal
        public /* synthetic */ Object getProperty(String str) {
            return getMetaClass().getProperty(this, str);
        }

        @Generated
        @Internal
        public /* synthetic */ Object invokeMethod(String str, Object obj) {
            return getMetaClass().invokeMethod(this, str, obj);
        }

        @Generated
        public boolean isDisableVersionCheck() {
            return this.disableVersionCheck;
        }

        public /* synthetic */ Object methodMissing(String str, Object obj) {
            $getCallSiteArray();
            String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
            String str2 = castToString;
            return ScriptBytecodeAdapter.invokeMethodN(GoogleServicesPluginConfig.class, GoogleServicesPlugin.class, castToString, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{obj}, new int[]{0}));
        }

        public /* synthetic */ Object propertyMissing(String str) {
            $getCallSiteArray();
            String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
            String str2 = castToString;
            return ScriptBytecodeAdapter.getProperty(GoogleServicesPluginConfig.class, GoogleServicesPlugin.class, castToString);
        }

        public /* synthetic */ void propertyMissing(String str, Object obj) {
            $getCallSiteArray();
            String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
            String str2 = castToString;
            ScriptBytecodeAdapter.setProperty(obj, (Class) null, GoogleServicesPlugin.class, castToString);
        }

        @Generated
        public void setDisableVersionCheck(boolean z) {
            this.disableVersionCheck = z;
        }

        @Generated
        @Internal
        public /* synthetic */ void setMetaClass(MetaClass metaClass2) {
            this.metaClass = metaClass2;
        }

        @Generated
        @Internal
        public /* synthetic */ void setProperty(String str, Object obj) {
            getMetaClass().setProperty(this, str, obj);
        }
    }

    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        String[] strArr = new String[63];
        $createCallSiteArray_1(strArr);
        return new CallSiteArray(GoogleServicesPlugin.class, strArr);
    }

    private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
        strArr[0] = "create";
        strArr[1] = "extensions";
        strArr[2] = "afterEvaluate";
        strArr[3] = "iterator";
        strArr[4] = "values";
        strArr[5] = "iterator";
        strArr[6] = "plugins";
        strArr[7] = "hasPlugin";
        strArr[8] = "plugins";
        strArr[9] = "setupPlugin";
        strArr[10] = "showWarningForPluginLocation";
        strArr[11] = "withId";
        strArr[12] = "plugins";
        strArr[13] = "withId";
        strArr[14] = "plugins";
        strArr[15] = "withId";
        strArr[16] = "plugins";
        strArr[17] = "warn";
        strArr[18] = "getLogger";
        strArr[19] = "APPLICATION";
        strArr[20] = "all";
        strArr[21] = "applicationVariants";
        strArr[22] = "android";
        strArr[23] = "LIBRARY";
        strArr[24] = "all";
        strArr[25] = "libraryVariants";
        strArr[26] = "android";
        strArr[27] = "FEATURE";
        strArr[28] = "all";
        strArr[29] = "featureVariants";
        strArr[30] = "android";
        strArr[31] = "MODEL_APPLICATION";
        strArr[32] = "all";
        strArr[33] = "applicationVariants";
        strArr[34] = "android";
        strArr[35] = "model";
        strArr[36] = "MODEL_LIBRARY";
        strArr[37] = "all";
        strArr[38] = "libraryVariants";
        strArr[39] = "android";
        strArr[40] = "model";
        strArr[41] = "file";
        strArr[42] = "buildDir";
        strArr[43] = "dirName";
        strArr[44] = "getJsonLocations";
        strArr[45] = "name";
        strArr[46] = "buildType";
        strArr[47] = "collect";
        strArr[48] = "productFlavors";
        strArr[49] = "register";
        strArr[50] = "tasks";
        strArr[51] = "capitalize";
        strArr[52] = "name";
        strArr[53] = "respondsTo";
        strArr[54] = "registerGeneratedResFolders";
        strArr[55] = "files";
        strArr[56] = "flatMap";
        strArr[57] = "registerResGeneratingTask";
        strArr[58] = "get";
        strArr[59] = "asFile";
        strArr[60] = "get";
        strArr[61] = "outputDirectory";
        strArr[62] = "get";
    }

    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray callSiteArray;
        SoftReference softReference = $callSiteArray;
        if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
            callSiteArray = $createCallSiteArray();
            $callSiteArray = new SoftReference(callSiteArray);
        }
        return callSiteArray.array;
    }

    @Generated
    public GoogleServicesPlugin() {
        $getCallSiteArray();
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ MetaClass $getStaticMetaClass() {
        if (getClass() != GoogleServicesPlugin.class) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo classInfo = $staticClassInfo;
        if (classInfo == null) {
            classInfo = ClassInfo.getClassInfo(getClass());
            $staticClassInfo = classInfo;
        }
        return classInfo.getMetaClass();
    }

    @Generated
    @Internal
    public /* synthetic */ MetaClass getMetaClass() {
        MetaClass metaClass2 = this.metaClass;
        if (metaClass2 != null) {
            return metaClass2;
        }
        MetaClass $getStaticMetaClass = $getStaticMetaClass();
        this.metaClass = $getStaticMetaClass;
        return $getStaticMetaClass;
    }

    @Generated
    @Internal
    public /* synthetic */ Object getProperty(String str) {
        return getMetaClass().getProperty(this, str);
    }

    @Generated
    @Internal
    public /* synthetic */ Object invokeMethod(String str, Object obj) {
        return getMetaClass().invokeMethod(this, str, obj);
    }

    @Generated
    @Internal
    public /* synthetic */ void setMetaClass(MetaClass metaClass2) {
        this.metaClass = metaClass2;
    }

    @Generated
    @Internal
    public /* synthetic */ void setProperty(String str, Object obj) {
        getMetaClass().setProperty(this, str, obj);
    }

    public /* synthetic */ Object this$dist$get$1(String str) {
        $getCallSiteArray();
        String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
        String str2 = castToString;
        return ScriptBytecodeAdapter.getGroovyObjectProperty(GoogleServicesPlugin.class, this, castToString);
    }

    public /* synthetic */ Object this$dist$invoke$1(String str, Object obj) {
        $getCallSiteArray();
        String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
        String str2 = castToString;
        return ScriptBytecodeAdapter.invokeMethodOnCurrentN(GoogleServicesPlugin.class, this, castToString, ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{obj}, new int[]{0}));
    }

    public /* synthetic */ void this$dist$set$1(String str, Object obj) {
        $getCallSiteArray();
        String castToString = ShortTypeHandling.castToString(new GStringImpl(new Object[]{str}, new String[]{"", ""}));
        String str2 = castToString;
        ScriptBytecodeAdapter.setGroovyObjectProperty(obj, GoogleServicesPlugin.class, this, castToString);
    }

    public void apply(Project project) {
        Reference project2 = new Reference(project);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[2].call((Project) project2.get(), new _apply_closure1(this, this, new Reference((GoogleServicesPluginConfig) ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty((Project) project2.get()), "googleServices", GoogleServicesPluginConfig.class), GoogleServicesPluginConfig.class)), project2));
        Iterator it = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].call(PluginType.class)), Iterator.class);
        while (it.hasNext()) {
            PluginType pluginType = (PluginType) ShortTypeHandling.castToEnum(it.next(), PluginType.class);
            Iterator it2 = (Iterator) ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call($getCallSiteArray[6].call(pluginType)), Iterator.class);
            while (true) {
                if (it2.hasNext()) {
                    if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty((Project) project2.get()), ShortTypeHandling.castToString(it2.next())))) {
                        $getCallSiteArray[9].callCurrent(this, (Project) project2.get(), pluginType);
                        return;
                    }
                }
            }
        }
        $getCallSiteArray[10].callCurrent(this, (Project) project2.get());
        $getCallSiteArray[11].call($getCallSiteArray[12].callGetProperty((Project) project2.get()), "android", new _apply_closure2(this, this, project2));
        $getCallSiteArray[13].call($getCallSiteArray[14].callGetProperty((Project) project2.get()), "android-library", new _apply_closure3(this, this, project2));
        $getCallSiteArray[15].call($getCallSiteArray[16].callGetProperty((Project) project2.get()), "android-feature", new _apply_closure4(this, this, project2));
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _apply_closure1 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference config;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[8];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure1.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "disableVersionCheck";
            strArr[1] = "<$constructor$>";
            strArr[2] = "<$constructor$>";
            strArr[3] = "getName";
            strArr[4] = "plus";
            strArr[5] = "plus";
            strArr[6] = "all";
            strArr[7] = "getConfigurations";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure1(Object obj, Object obj2, Reference reference, Reference reference2) {
            super(obj, obj2);
            $getCallSiteArray();
            this.config = reference;
            this.project = reference2;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure1.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        @Generated
        public GoogleServicesPluginConfig getConfig() {
            $getCallSiteArray();
            return (GoogleServicesPluginConfig) ScriptBytecodeAdapter.castToType(this.config.get(), GoogleServicesPluginConfig.class);
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGroovyObjectGetProperty(this.config.get()))) {
                return null;
            }
            return $getCallSiteArray[6].call($getCallSiteArray[7].call(this.project.get()), new _closure13(this, getThisObject(), new Reference((DependencyInspector) ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor(DependencyInspector.class, (DependencyAnalyzer) ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor(DependencyAnalyzer.class), DependencyAnalyzer.class), $getCallSiteArray[3].call(this.project.get()), $getCallSiteArray[4].call($getCallSiteArray[5].call("This error message came from the google-services Gradle plugin, report", " issues at https://github.com/google/play-services-plugins and disable by "), "adding \"googleServices { disableVersionCheck = true }\" to your build.gradle file.")), DependencyInspector.class))));
        }

        /* compiled from: GoogleServicesPlugin.groovy */
        public final class _closure13 extends Closure implements GeneratedClosure {
            private static /* synthetic */ SoftReference $callSiteArray;
            private static /* synthetic */ ClassInfo $staticClassInfo;
            public static transient /* synthetic */ boolean __$stMC;
            private /* synthetic */ Reference strictVersionDepInspector;

            private static /* synthetic */ CallSiteArray $createCallSiteArray() {
                String[] strArr = new String[4];
                $createCallSiteArray_1(strArr);
                return new CallSiteArray(_closure13.class, strArr);
            }

            private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
                strArr[0] = "contains";
                strArr[1] = "getName";
                strArr[2] = "afterResolve";
                strArr[3] = "getIncoming";
            }

            private static /* synthetic */ CallSite[] $getCallSiteArray() {
                CallSiteArray callSiteArray;
                SoftReference softReference = $callSiteArray;
                if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                    callSiteArray = $createCallSiteArray();
                    $callSiteArray = new SoftReference(callSiteArray);
                }
                return callSiteArray.array;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public _closure13(Object obj, Object obj2, Reference reference) {
                super(obj, obj2);
                $getCallSiteArray();
                this.strictVersionDepInspector = reference;
            }

            /* access modifiers changed from: protected */
            public /* synthetic */ MetaClass $getStaticMetaClass() {
                if (getClass() != _closure13.class) {
                    return ScriptBytecodeAdapter.initMetaClass(this);
                }
                ClassInfo classInfo = $staticClassInfo;
                if (classInfo == null) {
                    classInfo = ClassInfo.getClassInfo(getClass());
                    $staticClassInfo = classInfo;
                }
                return classInfo.getMetaClass();
            }

            @Generated
            public DependencyInspector getStrictVersionDepInspector() {
                $getCallSiteArray();
                return (DependencyInspector) ScriptBytecodeAdapter.castToType(this.strictVersionDepInspector.get(), DependencyInspector.class);
            }

            public Object doCall(Object projectConfig) {
                CallSite[] $getCallSiteArray = $getCallSiteArray();
                if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].call(projectConfig), "ompile"))) {
                    return $getCallSiteArray[2].call($getCallSiteArray[3].call(projectConfig), ScriptBytecodeAdapter.getMethodPointer(this.strictVersionDepInspector.get(), "afterResolve"));
                }
                return null;
            }
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _apply_closure2 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure2.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "APPLICATION";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure2(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure2.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _apply_closure3 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure3.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "LIBRARY";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure3(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure3.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _apply_closure4 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[2];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_apply_closure4.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "setupPlugin";
            strArr[1] = "FEATURE";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _apply_closure4(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _apply_closure4.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            return $getCallSiteArray[0].callCurrent(this, this.project.get(), $getCallSiteArray[1].callGetProperty(PluginType.class));
        }
    }

    private void showWarningForPluginLocation(Project project) {
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[17].call($getCallSiteArray[18].call(project), "Warning: Please apply google-services plugin at the bottom of the build file.");
    }

    private void setupPlugin(Project project, PluginType pluginType) {
        Reference project2 = new Reference(project);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[19].callGetProperty(PluginType.class))) {
            $getCallSiteArray[20].call($getCallSiteArray[21].callGetProperty($getCallSiteArray[22].callGetProperty((Project) project2.get())), new _setupPlugin_closure5(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[23].callGetProperty(PluginType.class))) {
            $getCallSiteArray[24].call($getCallSiteArray[25].callGetProperty($getCallSiteArray[26].callGetProperty((Project) project2.get())), new _setupPlugin_closure6(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[27].callGetProperty(PluginType.class))) {
            $getCallSiteArray[28].call($getCallSiteArray[29].callGetProperty($getCallSiteArray[30].callGetProperty((Project) project2.get())), new _setupPlugin_closure7(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[31].callGetProperty(PluginType.class))) {
            $getCallSiteArray[32].call($getCallSiteArray[33].callGetProperty($getCallSiteArray[34].callGetProperty($getCallSiteArray[35].callGetProperty((Project) project2.get()))), new _setupPlugin_closure8(this, this, project2));
        } else if (ScriptBytecodeAdapter.isCase(pluginType, $getCallSiteArray[36].callGetProperty(PluginType.class))) {
            $getCallSiteArray[37].call($getCallSiteArray[38].callGetProperty($getCallSiteArray[39].callGetProperty($getCallSiteArray[40].callGetProperty((Project) project2.get()))), new _setupPlugin_closure9(this, this, project2));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _setupPlugin_closure5 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure5.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure5(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure5.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callStatic(GoogleServicesPlugin.class, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _setupPlugin_closure6 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure6.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure6(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure6.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callStatic(GoogleServicesPlugin.class, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _setupPlugin_closure7 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure7.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure7(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure7.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callStatic(GoogleServicesPlugin.class, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _setupPlugin_closure8 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure8.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure8(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure8.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callStatic(GoogleServicesPlugin.class, this.project.get(), variant);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _setupPlugin_closure9 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference project;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_setupPlugin_closure9.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "handleVariant";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _setupPlugin_closure9(Object obj, Object obj2, Reference reference) {
            super(obj, obj2);
            $getCallSiteArray();
            this.project = reference;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _setupPlugin_closure9.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Project getProject() {
            $getCallSiteArray();
            return (Project) ScriptBytecodeAdapter.castToType(this.project.get(), Project.class);
        }

        public Object doCall(Object variant) {
            return $getCallSiteArray()[0].callStatic(GoogleServicesPlugin.class, this.project.get(), variant);
        }
    }

    private static void handleVariant(Project project, Object variant) {
        Class<GoogleServicesPlugin> cls = GoogleServicesPlugin.class;
        Reference variant2 = new Reference(variant);
        CallSite[] $getCallSiteArray = $getCallSiteArray();
        Reference outputDir = new Reference((File) ScriptBytecodeAdapter.castToType($getCallSiteArray[41].call(project, new GStringImpl(new Object[]{$getCallSiteArray[42].callGetProperty(project), $getCallSiteArray[43].callGetProperty(variant2.get())}, new String[]{"", "/generated/res/google-services/", ""})), File.class));
        Reference googleServicesFiles = new Reference($getCallSiteArray[44].call(GoogleServicesTask.class, $getCallSiteArray[45].callGetProperty($getCallSiteArray[46].callGetProperty(variant2.get())), $getCallSiteArray[47].call($getCallSiteArray[48].callGetProperty(variant2.get()), new _handleVariant_closure10(cls, cls))));
        Object processTask = $getCallSiteArray[49].call($getCallSiteArray[50].callGetProperty(project), new GStringImpl(new Object[]{$getCallSiteArray[51].call($getCallSiteArray[52].callGetProperty(variant2.get()))}, new String[]{"process", "GoogleServices"}), GoogleServicesTask.class, new _handleVariant_closure11(GoogleServicesPlugin.class, GoogleServicesPlugin.class, outputDir, variant2, googleServicesFiles));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[53].call(variant2.get(), "registerGeneratedResFolders"))) {
            $getCallSiteArray[54].call(variant2.get(), $getCallSiteArray[55].call(project, $getCallSiteArray[56].call(processTask, new _handleVariant_closure12(cls, cls))));
        } else {
            $getCallSiteArray[57].call(variant2.get(), $getCallSiteArray[58].call(processTask), $getCallSiteArray[59].callGetProperty($getCallSiteArray[60].call($getCallSiteArray[61].callGetProperty($getCallSiteArray[62].call(processTask)))));
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _handleVariant_closure10 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_handleVariant_closure10.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "name";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _handleVariant_closure10(Object obj, Object obj2) {
            super(obj, obj2);
            $getCallSiteArray();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _handleVariant_closure10.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        public Object doCall(Object it) {
            return $getCallSiteArray()[0].callGetProperty(it);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _handleVariant_closure11 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;
        private /* synthetic */ Reference googleServicesFiles;
        private /* synthetic */ Reference outputDir;
        private /* synthetic */ Reference variant;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[7];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_handleVariant_closure11.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "set";
            strArr[1] = "outputDirectory";
            strArr[2] = "set";
            strArr[3] = "applicationId";
            strArr[4] = "applicationId";
            strArr[5] = TypedValues.TransitionType.S_FROM;
            strArr[6] = "googleServicesJsonFiles";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _handleVariant_closure11(Object obj, Object obj2, Reference reference, Reference reference2, Reference reference3) {
            super(obj, obj2);
            $getCallSiteArray();
            this.outputDir = reference;
            this.variant = reference2;
            this.googleServicesFiles = reference3;
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _handleVariant_closure11.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        @Generated
        public Object doCall() {
            $getCallSiteArray();
            return doCall((Object) null);
        }

        @Generated
        public Object getGoogleServicesFiles() {
            $getCallSiteArray();
            return this.googleServicesFiles.get();
        }

        @Generated
        public File getOutputDir() {
            $getCallSiteArray();
            return (File) ScriptBytecodeAdapter.castToType(this.outputDir.get(), File.class);
        }

        @Generated
        public Object getVariant() {
            $getCallSiteArray();
            return this.variant.get();
        }

        public Object doCall(Object it) {
            CallSite[] $getCallSiteArray = $getCallSiteArray();
            $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), this.outputDir.get());
            $getCallSiteArray[2].call($getCallSiteArray[3].callGroovyObjectGetProperty(this), $getCallSiteArray[4].callGetProperty(this.variant.get()));
            return $getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), this.googleServicesFiles.get());
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    public final class _handleVariant_closure12 extends Closure implements GeneratedClosure {
        private static /* synthetic */ SoftReference $callSiteArray;
        private static /* synthetic */ ClassInfo $staticClassInfo;
        public static transient /* synthetic */ boolean __$stMC;

        private static /* synthetic */ CallSiteArray $createCallSiteArray() {
            String[] strArr = new String[1];
            $createCallSiteArray_1(strArr);
            return new CallSiteArray(_handleVariant_closure12.class, strArr);
        }

        private static /* synthetic */ void $createCallSiteArray_1(String[] strArr) {
            strArr[0] = "outputDirectory";
        }

        private static /* synthetic */ CallSite[] $getCallSiteArray() {
            CallSiteArray callSiteArray;
            SoftReference softReference = $callSiteArray;
            if (softReference == null || (callSiteArray = (CallSiteArray) softReference.get()) == null) {
                callSiteArray = $createCallSiteArray();
                $callSiteArray = new SoftReference(callSiteArray);
            }
            return callSiteArray.array;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public _handleVariant_closure12(Object obj, Object obj2) {
            super(obj, obj2);
            $getCallSiteArray();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ MetaClass $getStaticMetaClass() {
            if (getClass() != _handleVariant_closure12.class) {
                return ScriptBytecodeAdapter.initMetaClass(this);
            }
            ClassInfo classInfo = $staticClassInfo;
            if (classInfo == null) {
                classInfo = ClassInfo.getClassInfo(getClass());
                $staticClassInfo = classInfo;
            }
            return classInfo.getMetaClass();
        }

        public Object doCall(Object task) {
            return $getCallSiteArray()[0].callGetProperty(task);
        }
    }

    /* compiled from: GoogleServicesPlugin.groovy */
    enum PluginType implements GroovyObject {
        ;
        
        public static final PluginType MAX_VALUE = null;
        public static final PluginType MIN_VALUE = null;
        private final Collection plugins;

        /* access modifiers changed from: public */
        PluginType(Collection plugins2) {
            $getCallSiteArray();
            this.metaClass = $getStaticMetaClass();
            this.plugins = (Collection) ScriptBytecodeAdapter.castToType(plugins2, Collection.class);
        }

        public Collection plugins() {
            $getCallSiteArray();
            return this.plugins;
        }
    }
}
