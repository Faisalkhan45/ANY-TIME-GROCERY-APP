package com.google.firebase.auth;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzv;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class FirebaseAuthRegistrar implements ComponentRegistrar {
    static /* synthetic */ FirebaseAuth lambda$getComponents$0(ComponentContainer container) {
        return new zzv((FirebaseApp) container.get(FirebaseApp.class), container.getProvider(HeartBeatController.class));
    }

    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseAuth.class, InternalAuthProvider.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.requiredProvider(HeartBeatController.class)).factory(zzx.zza).eagerInDefaultApp().build(), HeartBeatConsumerComponent.create(), LibraryVersionComponent.create("fire-auth", "21.1.0")});
    }
}
