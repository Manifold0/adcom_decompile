// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgp
{
    private final float zzais;
    private final float zzait;
    private final float zzaiu;
    private final float zzaiv;
    private final int zzaiw;
    
    @VisibleForTesting
    public zzgp(final float zzais, final float zzait, final float n, final float n2, final int zzaiw) {
        this.zzais = zzais;
        this.zzait = zzait;
        this.zzaiu = zzais + n;
        this.zzaiv = zzait + n2;
        this.zzaiw = zzaiw;
    }
    
    final float zzhb() {
        return this.zzais;
    }
    
    final float zzhc() {
        return this.zzait;
    }
    
    final float zzhd() {
        return this.zzaiu;
    }
    
    final float zzhe() {
        return this.zzaiv;
    }
    
    final int zzhf() {
        return this.zzaiw;
    }
}
