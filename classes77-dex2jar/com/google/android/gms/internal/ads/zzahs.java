// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public final class zzahs
{
    private int mErrorCode;
    private String zzbth;
    private String zzcly;
    private long zzclz;
    
    public final zzahs zzad(final int mErrorCode) {
        this.mErrorCode = mErrorCode;
        return this;
    }
    
    public final zzahs zzcc(final String zzbth) {
        this.zzbth = zzbth;
        return this;
    }
    
    public final zzahs zzcd(final String zzcly) {
        this.zzcly = zzcly;
        return this;
    }
    
    public final zzahs zzg(final long zzclz) {
        this.zzclz = zzclz;
        return this;
    }
    
    public final zzahq zzpd() {
        return new zzahq(this, null);
    }
}
