// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzabz implements zzv<Object>
{
    private final /* synthetic */ zzabv zzcal;
    private final /* synthetic */ zzos zzcam;
    
    zzabz(final zzabv zzcal, final zzos zzcam) {
        this.zzcal = zzcal;
        this.zzcam = zzcam;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        this.zzcal.zzc((zzqs)this.zzcam, map.get("asset"));
    }
}
