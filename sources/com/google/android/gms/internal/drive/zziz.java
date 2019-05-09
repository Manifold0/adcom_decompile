package com.google.android.gms.internal.drive;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Arrays;

final class zziz {
    final int tag;
    final byte[] zzng;

    zziz(int i, byte[] bArr) {
        this.tag = i;
        this.zzng = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zziz)) {
            return false;
        }
        zziz zziz = (zziz) obj;
        return this.tag == zziz.tag && Arrays.equals(this.zzng, zziz.zzng);
    }

    public final int hashCode() {
        return ((this.tag + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + Arrays.hashCode(this.zzng);
    }
}
