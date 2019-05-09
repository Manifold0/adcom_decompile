// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import com.google.android.gms.ads.internal.zzp;

@zzadh
final class zztx
{
    private final Object[] mParams;
    
    zztx(final zzjj zzjj, final String s, final int n) {
        this.mParams = zzp.zza((String)zzkb.zzik().zzd(zznk.zzaza), zzjj, s, n, null);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o instanceof zztx && Arrays.equals(this.mParams, ((zztx)o).mParams);
    }
    
    @Override
    public final int hashCode() {
        return Arrays.hashCode(this.mParams);
    }
    
    @Override
    public final String toString() {
        final String string = Arrays.toString(this.mParams);
        return new StringBuilder(String.valueOf(string).length() + 24).append("[InterstitialAdPoolKey ").append(string).append("]").toString();
    }
}
