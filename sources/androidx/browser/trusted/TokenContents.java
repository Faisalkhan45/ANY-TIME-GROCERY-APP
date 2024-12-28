package androidx.browser.trusted;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class TokenContents {
    private final byte[] mContents;
    private List<byte[]> mFingerprints;
    private String mPackageName;

    static TokenContents deserialize(byte[] serialized) {
        return new TokenContents(serialized);
    }

    private TokenContents(byte[] contents) {
        this.mContents = contents;
    }

    static TokenContents create(String packageName, List<byte[]> fingerprints) throws IOException {
        return new TokenContents(createToken(packageName, fingerprints), packageName, fingerprints);
    }

    private TokenContents(byte[] contents, String packageName, List<byte[]> fingerprints) {
        this.mContents = contents;
        this.mPackageName = packageName;
        this.mFingerprints = new ArrayList(fingerprints.size());
        for (byte[] fingerprint : fingerprints) {
            this.mFingerprints.add(Arrays.copyOf(fingerprint, fingerprint.length));
        }
    }

    public String getPackageName() throws IOException {
        parseIfNeeded();
        String str = this.mPackageName;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException();
    }

    public int getFingerprintCount() throws IOException {
        parseIfNeeded();
        List<byte[]> list = this.mFingerprints;
        if (list != null) {
            return list.size();
        }
        throw new IllegalStateException();
    }

    public byte[] getFingerprint(int i) throws IOException {
        parseIfNeeded();
        List<byte[]> list = this.mFingerprints;
        if (list != null) {
            return Arrays.copyOf(list.get(i), this.mFingerprints.get(i).length);
        }
        throw new IllegalStateException();
    }

    public byte[] serialize() {
        byte[] bArr = this.mContents;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Arrays.equals(this.mContents, ((TokenContents) o).mContents);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mContents);
    }

    private static byte[] createToken(String packageName, List<byte[]> fingerprints) throws IOException {
        Collections.sort(fingerprints, new TokenContents$$ExternalSyntheticLambda0());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream writer = new DataOutputStream(baos);
        writer.writeUTF(packageName);
        writer.writeInt(fingerprints.size());
        for (byte[] fingerprint : fingerprints) {
            writer.writeInt(fingerprint.length);
            writer.write(fingerprint);
        }
        writer.flush();
        return baos.toByteArray();
    }

    /* access modifiers changed from: private */
    public static int compareByteArrays(byte[] a, byte[] b) {
        if (a == b) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        if (a.length != b.length) {
            return a.length - b.length;
        }
        return 0;
    }

    private void parseIfNeeded() throws IOException {
        if (this.mPackageName == null) {
            DataInputStream reader = new DataInputStream(new ByteArrayInputStream(this.mContents));
            this.mPackageName = reader.readUTF();
            int numFingerprints = reader.readInt();
            this.mFingerprints = new ArrayList(numFingerprints);
            int i = 0;
            while (i < numFingerprints) {
                int size = reader.readInt();
                byte[] fingerprint = new byte[size];
                if (reader.read(fingerprint) == size) {
                    this.mFingerprints.add(fingerprint);
                    i++;
                } else {
                    throw new IllegalStateException("Could not read fingerprint");
                }
            }
        }
    }
}
