package com.razorpay;

import android.util.Base64;
import com.bumptech.glide.load.Key;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class CryptLib {
    private Cipher a = Cipher.getInstance("AES/GCM/NoPadding");
    private byte[] b = new byte[32];
    private byte[] c = new byte[16];

    enum EncryptMode {
        ENCRYPT,
        DECRYPT
    }

    CryptLib() {
    }

    private String a(String str, String str2, EncryptMode encryptMode, String str3) {
        String str4;
        int length = str2.getBytes(Key.STRING_CHARSET_NAME).length;
        int length2 = str2.getBytes(Key.STRING_CHARSET_NAME).length;
        byte[] bArr = this.b;
        if (length2 > bArr.length) {
            length = bArr.length;
        }
        int length3 = str3.getBytes(Key.STRING_CHARSET_NAME).length;
        int length4 = str3.getBytes(Key.STRING_CHARSET_NAME).length;
        byte[] bArr2 = this.c;
        if (length4 > bArr2.length) {
            length3 = bArr2.length;
        }
        System.arraycopy(str2.getBytes(Key.STRING_CHARSET_NAME), 0, this.b, 0, length);
        System.arraycopy(str3.getBytes(Key.STRING_CHARSET_NAME), 0, this.c, 0, length3);
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.b, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(this.c);
        if (encryptMode.equals(EncryptMode.ENCRYPT)) {
            this.a.init(1, secretKeySpec, ivParameterSpec);
            str4 = Base64.encodeToString(this.a.doFinal(str.getBytes(Key.STRING_CHARSET_NAME)), 2);
        } else {
            str4 = "";
        }
        if (!encryptMode.equals(EncryptMode.DECRYPT)) {
            return str4;
        }
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            this.a = instance;
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(this.a.doFinal(Base64.decode(str, 0)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return str4;
        }
    }

    /* access modifiers changed from: package-private */
    public final String a(String str, String str2, String str3) {
        return a(str, str2, EncryptMode.ENCRYPT, str3);
    }

    /* access modifiers changed from: package-private */
    public final String b(String str, String str2, String str3) {
        return a(str, str2, EncryptMode.DECRYPT, str3);
    }
}
