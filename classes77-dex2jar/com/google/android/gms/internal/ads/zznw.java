// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznw
{
    private final Map<String, zznv> zzbgm;
    @Nullable
    private final zznx zzvr;
    
    public zznw(@Nullable final zznx zzvr) {
        this.zzvr = zzvr;
        this.zzbgm = new HashMap<String, zznv>();
    }
    
    public final void zza(final String s, final zznv zznv) {
        this.zzbgm.put(s, zznv);
    }
    
    public final void zza(final String s, final String s2, final long n) {
        final zznx zzvr = this.zzvr;
        final zznv zznv = this.zzbgm.get(s2);
        if (zzvr != null && zznv != null) {
            zzvr.zza(zznv, n, s);
        }
        final Map<String, zznv> zzbgm = this.zzbgm;
        final zznx zzvr2 = this.zzvr;
        zznv zzd;
        if (zzvr2 == null) {
            zzd = null;
        }
        else {
            zzd = zzvr2.zzd(n);
        }
        zzbgm.put(s, zzd);
    }
    
    @Nullable
    public final zznx zzji() {
        return this.zzvr;
    }
}
