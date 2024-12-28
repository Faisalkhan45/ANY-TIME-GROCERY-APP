package com.razorpay;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class AppSignatureHelper extends ContextWrapper {
    private static final String HASH_TYPE = "SHA-256";
    public static final int NUM_BASE64_CHAR = 11;
    public static final int NUM_HASHED_BYTES = 9;
    public static final String TAG = AppSignatureHelper.class.getSimpleName();

    public AppSignatureHelper(Context context) {
        super(context);
    }

    public ArrayList<String> getAppSignatures() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String packageName = getPackageName();
            for (Signature charsString : getPackageManager().getPackageInfo(packageName, 64).signatures) {
                String hash = hash(packageName, charsString.toCharsString());
                if (hash != null) {
                    arrayList.add(String.format("%s", new Object[]{hash}));
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to find package to obtain hash.", e);
        }
        return arrayList;
    }

    private static String hash(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest instance = MessageDigest.getInstance(HASH_TYPE);
            instance.update(str3.getBytes(StandardCharsets.UTF_8));
            String substring = Base64.encodeToString(Arrays.copyOfRange(instance.digest(), 0, 9), 3).substring(0, 11);
            Log.d(TAG, String.format("pkg: %s -- hash: %s", new Object[]{str, substring}));
            return substring;
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "hash:NoSuchAlgorithm", e);
            return null;
        }
    }
}
