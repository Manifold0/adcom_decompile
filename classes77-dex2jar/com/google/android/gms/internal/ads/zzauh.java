// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzauh
{
    private zzaxr zzdhi;
    
    private zzauh(final zzaxr zzdhi) {
        this.zzdhi = zzdhi;
    }
    
    static final zzauh zza(final zzaxr zzaxr) throws GeneralSecurityException {
        if (zzaxr == null || zzaxr.zzzm() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return new zzauh(zzaxr);
    }
    
    @Override
    public final String toString() {
        return zzaup.zzb(this.zzdhi).toString();
    }
    
    final zzaxr zzwg() {
        return this.zzdhi;
    }
}
