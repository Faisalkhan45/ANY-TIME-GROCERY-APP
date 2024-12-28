package androidx.browser.trusted.sharing;

import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShareTarget {
    public static final String ENCODING_TYPE_MULTIPART = "multipart/form-data";
    public static final String ENCODING_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
    public static final String KEY_ACTION = "androidx.browser.trusted.sharing.KEY_ACTION";
    public static final String KEY_ENCTYPE = "androidx.browser.trusted.sharing.KEY_ENCTYPE";
    public static final String KEY_METHOD = "androidx.browser.trusted.sharing.KEY_METHOD";
    public static final String KEY_PARAMS = "androidx.browser.trusted.sharing.KEY_PARAMS";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public final String action;
    public final String encodingType;
    public final String method;
    public final Params params;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EncodingType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestMethod {
    }

    public ShareTarget(String action2, String method2, String encodingType2, Params params2) {
        this.action = action2;
        this.method = method2;
        this.encodingType = encodingType2;
        this.params = params2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ACTION, this.action);
        bundle.putString(KEY_METHOD, this.method);
        bundle.putString(KEY_ENCTYPE, this.encodingType);
        bundle.putBundle(KEY_PARAMS, this.params.toBundle());
        return bundle;
    }

    public static ShareTarget fromBundle(Bundle bundle) {
        String action2 = bundle.getString(KEY_ACTION);
        String method2 = bundle.getString(KEY_METHOD);
        String encType = bundle.getString(KEY_ENCTYPE);
        Params params2 = Params.fromBundle(bundle.getBundle(KEY_PARAMS));
        if (action2 == null || params2 == null) {
            return null;
        }
        return new ShareTarget(action2, method2, encType, params2);
    }

    public static class Params {
        public static final String KEY_FILES = "androidx.browser.trusted.sharing.KEY_FILES";
        public static final String KEY_TEXT = "androidx.browser.trusted.sharing.KEY_TEXT";
        public static final String KEY_TITLE = "androidx.browser.trusted.sharing.KEY_TITLE";
        public final List<FileFormField> files;
        public final String text;
        public final String title;

        public Params(String title2, String text2, List<FileFormField> files2) {
            this.title = title2;
            this.text = text2;
            this.files = files2;
        }

        /* access modifiers changed from: package-private */
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("androidx.browser.trusted.sharing.KEY_TITLE", this.title);
            bundle.putString("androidx.browser.trusted.sharing.KEY_TEXT", this.text);
            if (this.files != null) {
                ArrayList<Bundle> fileBundles = new ArrayList<>();
                for (FileFormField file : this.files) {
                    fileBundles.add(file.toBundle());
                }
                bundle.putParcelableArrayList(KEY_FILES, fileBundles);
            }
            return bundle;
        }

        static Params fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            List<FileFormField> files2 = null;
            List<Bundle> fileBundles = bundle.getParcelableArrayList(KEY_FILES);
            if (fileBundles != null) {
                files2 = new ArrayList<>();
                for (Bundle fileBundle : fileBundles) {
                    files2.add(FileFormField.fromBundle(fileBundle));
                }
            }
            return new Params(bundle.getString("androidx.browser.trusted.sharing.KEY_TITLE"), bundle.getString("androidx.browser.trusted.sharing.KEY_TEXT"), files2);
        }
    }

    public static final class FileFormField {
        public static final String KEY_ACCEPTED_TYPES = "androidx.browser.trusted.sharing.KEY_ACCEPTED_TYPES";
        public static final String KEY_NAME = "androidx.browser.trusted.sharing.KEY_FILE_NAME";
        public final List<String> acceptedTypes;
        public final String name;

        public FileFormField(String name2, List<String> acceptedTypes2) {
            this.name = name2;
            this.acceptedTypes = Collections.unmodifiableList(acceptedTypes2);
        }

        /* access modifiers changed from: package-private */
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_NAME, this.name);
            bundle.putStringArrayList(KEY_ACCEPTED_TYPES, new ArrayList(this.acceptedTypes));
            return bundle;
        }

        static FileFormField fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            String name2 = bundle.getString(KEY_NAME);
            ArrayList<String> acceptedTypes2 = bundle.getStringArrayList(KEY_ACCEPTED_TYPES);
            if (name2 == null || acceptedTypes2 == null) {
                return null;
            }
            return new FileFormField(name2, acceptedTypes2);
        }
    }
}
