// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzavg implements zzauf
{
    private final /* synthetic */ zzaum zzdhy;
    
    zzavg(final zzaum zzdhy) {
        this.zzdhy = zzdhy;
    }
    
    @Override
    public final byte[] zzc(final byte[] array, final byte[] array2) throws GeneralSecurityException {
        return zzayk.zza(new byte[][] { this.zzdhy.zzwh().zzwj(), this.zzdhy.zzwh().zzwi().zzc(array, array2) });
    }
}
