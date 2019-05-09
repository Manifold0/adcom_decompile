// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.util.Arrays;

final class zziz
{
    final int tag;
    final byte[] zzng;
    
    zziz(final int tag, final byte[] zzng) {
        this.tag = tag;
        this.zzng = zzng;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zziz)) {
                return false;
            }
            final zziz zziz = (zziz)o;
            if (this.tag != zziz.tag || !Arrays.equals(this.zzng, zziz.zzng)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzng);
    }
}
