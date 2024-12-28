package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;
    private final int c;
    private final int d;
    private final long k0;
    private final long k1;

    SipHashFunction(int c2, int d2, long k02, long k12) {
        boolean z = true;
        Preconditions.checkArgument(c2 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", c2);
        Preconditions.checkArgument(d2 <= 0 ? false : z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", d2);
        this.c = c2;
        this.d = d2;
        this.k0 = k02;
        this.k1 = k12;
    }

    public int bits() {
        return 64;
    }

    public Hasher newHasher() {
        return new SipHasher(this.c, this.d, this.k0, this.k1);
    }

    public String toString() {
        int i = this.c;
        int i2 = this.d;
        long j = this.k0;
        return new StringBuilder(81).append("Hashing.sipHash").append(i).append(i2).append("(").append(j).append(", ").append(this.k1).append(")").toString();
    }

    public boolean equals(@CheckForNull Object object) {
        if (!(object instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction other = (SipHashFunction) object;
        if (this.c == other.c && this.d == other.d && this.k0 == other.k0 && this.k1 == other.k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.c) ^ this.d)) ^ this.k0) ^ this.k1);
    }

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;
        private long b = 0;
        private final int c;
        private final int d;
        private long finalM = 0;
        private long v0 = 8317987319222330741L;
        private long v1 = 7237128888997146477L;
        private long v2 = 7816392313619706465L;
        private long v3 = 8387220255154660723L;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SipHasher(int c2, int d2, long k0, long k1) {
            super(8);
            this.c = c2;
            this.d = d2;
            this.v0 = 8317987319222330741L ^ k0;
            this.v1 = 7237128888997146477L ^ k1;
            this.v2 = 7816392313619706465L ^ k0;
            this.v3 = 8387220255154660723L ^ k1;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer buffer) {
            this.b += 8;
            processM(buffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer buffer) {
            this.b += (long) buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= (((long) buffer.get()) & 255) << i;
                i += 8;
            }
        }

        /* access modifiers changed from: protected */
        public HashCode makeHash() {
            long j = this.finalM ^ (this.b << 56);
            this.finalM = j;
            processM(j);
            this.v2 ^= 255;
            sipRound(this.d);
            return HashCode.fromLong(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        private void processM(long m) {
            this.v3 ^= m;
            sipRound(this.c);
            this.v0 ^= m;
        }

        private void sipRound(int iterations) {
            for (int i = 0; i < iterations; i++) {
                long j = this.v0;
                long j2 = this.v1;
                this.v0 = j + j2;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(j2, 13);
                long rotateLeft = Long.rotateLeft(this.v3, 16);
                this.v3 = rotateLeft;
                long j3 = this.v1;
                long j4 = this.v0;
                this.v1 = j3 ^ j4;
                this.v3 = rotateLeft ^ this.v2;
                long rotateLeft2 = Long.rotateLeft(j4, 32);
                this.v0 = rotateLeft2;
                long j5 = this.v2;
                long j6 = this.v1;
                this.v2 = j5 + j6;
                this.v0 = rotateLeft2 + this.v3;
                this.v1 = Long.rotateLeft(j6, 17);
                long rotateLeft3 = Long.rotateLeft(this.v3, 21);
                this.v3 = rotateLeft3;
                long j7 = this.v1;
                long j8 = this.v2;
                this.v1 = j7 ^ j8;
                this.v3 = rotateLeft3 ^ this.v0;
                this.v2 = Long.rotateLeft(j8, 32);
            }
        }
    }
}
