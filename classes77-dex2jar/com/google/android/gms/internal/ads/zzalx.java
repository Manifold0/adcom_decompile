// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzalx implements zzy
{
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ zzama zzctj;
    
    zzalx(final zzalt zzalt, final String zzcce, final zzama zzctj) {
        this.zzcce = zzcce;
        this.zzctj = zzctj;
    }
    
    @Override
    public final void zzd(final zzae zzae) {
        final String zzcce = this.zzcce;
        final String string = zzae.toString();
        zzakb.zzdk(new StringBuilder(String.valueOf(zzcce).length() + 21 + String.valueOf(string).length()).append("Failed to load URL: ").append(zzcce).append("\n").append(string).toString());
        this.zzctj.zzb(null);
    }
}
