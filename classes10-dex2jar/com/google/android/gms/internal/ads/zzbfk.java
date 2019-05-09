// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzbfk
{
    final int tag;
    final byte[] zzdpw;
    
    zzbfk(final int tag, final byte[] zzdpw) {
        this.tag = tag;
        this.zzdpw = zzdpw;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzbfk)) {
                return false;
            }
            final zzbfk zzbfk = (zzbfk)o;
            if (this.tag != zzbfk.tag || !Arrays.equals(this.zzdpw, zzbfk.zzdpw)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzdpw);
    }
}
