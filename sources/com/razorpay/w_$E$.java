package com.razorpay;

/* compiled from: MagicData */
final class w_$E$ implements Callback {
    private /* synthetic */ String a;
    private /* synthetic */ p$$q_ b;

    w_$E$(p$$q_ p__q_, String str) {
        this.b = p__q_;
        this.a = str;
    }

    public final void run(ResponseObject responseObject) {
        String decryptFile;
        if (responseObject.getResponseResult() != null && (decryptFile = BaseUtils.decryptFile(responseObject.getResponseResult())) != null) {
            if (BaseUtils.storeFileInInternal(this.b.a, BaseUtils.getVersionedAssetName(this.a, g$_H$.a().getMagicJsFileName()), responseObject.getResponseResult())) {
                String unused = this.b.c = decryptFile;
                BaseUtils.updateLocalVersion(this.b.a, p$$q_.b, this.a);
            }
        }
    }
}
