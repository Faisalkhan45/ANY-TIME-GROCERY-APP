package com.razorpay;

import android.app.Activity;
import kotlin.jvm.internal.Ref;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OpinionatedSoln$$ExternalSyntheticLambda0 implements Callback {
    public final /* synthetic */ Ref.BooleanRef f$0;
    public final /* synthetic */ Activity f$1;

    public /* synthetic */ OpinionatedSoln$$ExternalSyntheticLambda0(Ref.BooleanRef booleanRef, Activity activity) {
        this.f$0 = booleanRef;
        this.f$1 = activity;
    }

    public final void run(ResponseObject responseObject) {
        OpinionatedSoln.m435checkIfVersionUpdateExists$lambda0(this.f$0, this.f$1, responseObject);
    }
}
