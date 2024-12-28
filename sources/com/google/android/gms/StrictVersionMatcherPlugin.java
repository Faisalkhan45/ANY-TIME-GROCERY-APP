package com.google.android.gms;

import com.google.android.gms.dependencies.DependencyAnalyzer;
import com.google.android.gms.dependencies.DependencyInspector;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ResolvableDependencies;

public class StrictVersionMatcherPlugin implements Plugin<Project> {
    private static DependencyAnalyzer globalDependencies = new DependencyAnalyzer();

    static /* synthetic */ void lambda$apply$0(ResolvableDependencies x) {
        throw new GradleException("test");
    }

    public void apply(@Nonnull Project project) {
        new StrictVersionMatcherPlugin$$ExternalSyntheticLambda1
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: CONSTRUCTOR  (r0v0 ? I:com.google.android.gms.StrictVersionMatcherPlugin$$ExternalSyntheticLambda1) =  call: com.google.android.gms.StrictVersionMatcherPlugin$$ExternalSyntheticLambda1.<init>():void type: CONSTRUCTOR in method: com.google.android.gms.StrictVersionMatcherPlugin.apply(org.gradle.api.Project):void, dex: classes.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.util.ArrayList.forEach(ArrayList.java:1259)
            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
            	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
            	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
            	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
            	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
            	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v0 ?
            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
            	... 29 more
            */
        /*
            this = this;
            com.google.android.gms.StrictVersionMatcherPlugin$$ExternalSyntheticLambda1 r0 = new com.google.android.gms.StrictVersionMatcherPlugin$$ExternalSyntheticLambda1
            r0.<init>()
            com.google.android.gms.dependencies.DependencyInspector r1 = new com.google.android.gms.dependencies.DependencyInspector
            com.google.android.gms.dependencies.DependencyAnalyzer r2 = globalDependencies
            java.lang.String r3 = r6.getName()
            java.lang.String r4 = "This error message came from the strict-version-matcher-plugin Gradle plugin, report issues at https://github.com/google/play-services-plugins and disable by removing the reference to the plugin (\"apply 'strict-version-matcher-plugin'\") from build.gradle."
            r1.<init>(r2, r3, r4)
            org.gradle.api.artifacts.ConfigurationContainer r2 = r6.getConfigurations()
            com.google.android.gms.StrictVersionMatcherPlugin$$ExternalSyntheticLambda2 r3 = new com.google.android.gms.StrictVersionMatcherPlugin$$ExternalSyntheticLambda2
            r3.<init>(r1)
            r2.all(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.StrictVersionMatcherPlugin.apply(org.gradle.api.Project):void");
    }

    static /* synthetic */ void lambda$apply$1(DependencyInspector strictVersionDepInspector, Configuration config) {
        if (config.getName().contains("ompile")) {
            ResolvableDependencies incoming = config.getIncoming();
            Objects.requireNonNull(strictVersionDepInspector);
            incoming.afterResolve(new StrictVersionMatcherPlugin$$ExternalSyntheticLambda0(strictVersionDepInspector));
        }
    }
}
