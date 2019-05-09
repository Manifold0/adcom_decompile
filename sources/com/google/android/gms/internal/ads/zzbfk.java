package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Arrays;

final class zzbfk {
    final int tag;
    final byte[] zzdpw;

    zzbfk(int i, byte[] bArr) {
        this.tag = i;
        this.zzdpw = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbfk)) {
            return false;
        }
        zzbfk zzbfk = (zzbfk) obj;
        return this.tag == zzbfk.tag && Arrays.equals(this.zzdpw, zzbfk.zzdpw);
    }

    public final int hashCode() {
        return ((this.tag + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + Arrays.hashCode(this.zzdpw);
    }
}
