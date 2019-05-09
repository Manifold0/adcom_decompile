package com.google.firebase.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class zzz {
    private final KeyPair zzbr;
    private final long zzbs;

    @VisibleForTesting
    zzz(KeyPair keyPair, long j) {
        this.zzbr = keyPair;
        this.zzbs = j;
    }

    final KeyPair getKeyPair() {
        return this.zzbr;
    }

    final long getCreationTime() {
        return this.zzbs;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzz)) {
            return false;
        }
        zzz zzz = (zzz) obj;
        if (this.zzbs == zzz.zzbs && this.zzbr.getPublic().equals(zzz.zzbr.getPublic()) && this.zzbr.getPrivate().equals(zzz.zzbr.getPrivate())) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzbr.getPublic(), this.zzbr.getPrivate(), Long.valueOf(this.zzbs)});
    }

    private final String zzv() {
        return Base64.encodeToString(this.zzbr.getPublic().getEncoded(), 11);
    }

    private final String zzw() {
        return Base64.encodeToString(this.zzbr.getPrivate().getEncoded(), 11);
    }
}
