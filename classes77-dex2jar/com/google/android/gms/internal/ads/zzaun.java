// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;

public final class zzaun<P>
{
    private final P zzdhm;
    private final byte[] zzdhn;
    private final zzaxl zzdho;
    private final zzayd zzdhp;
    
    public zzaun(final P zzdhm, final byte[] array, final zzaxl zzdho, final zzayd zzdhp) {
        this.zzdhm = zzdhm;
        this.zzdhn = Arrays.copyOf(array, array.length);
        this.zzdho = zzdho;
        this.zzdhp = zzdhp;
    }
    
    public final P zzwi() {
        return this.zzdhm;
    }
    
    public final byte[] zzwj() {
        if (this.zzdhn == null) {
            return null;
        }
        return Arrays.copyOf(this.zzdhn, this.zzdhn.length);
    }
}
