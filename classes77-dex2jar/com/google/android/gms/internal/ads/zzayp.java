// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class zzayp implements zzauf
{
    private static final byte[] zzdhv;
    private final String zzdne;
    private final byte[] zzdnf;
    private final zzayw zzdng;
    private final zzayn zzdnh;
    private final zzayr zzdni;
    
    static {
        zzdhv = new byte[0];
    }
    
    public zzayp(final ECPublicKey ecPublicKey, final byte[] zzdnf, final String zzdne, final zzayw zzdng, final zzayn zzdnh) throws GeneralSecurityException {
        zzayt.zza(ecPublicKey.getW(), ecPublicKey.getParams().getCurve());
        this.zzdni = new zzayr(ecPublicKey);
        this.zzdnf = zzdnf;
        this.zzdne = zzdne;
        this.zzdng = zzdng;
        this.zzdnh = zzdnh;
    }
    
    @Override
    public final byte[] zzc(byte[] zzc, byte[] zzaap) throws GeneralSecurityException {
        final zzays zza = this.zzdni.zza(this.zzdne, this.zzdnf, zzaap, this.zzdnh.zzwm(), this.zzdng);
        zzc = this.zzdnh.zzi(zza.zzaaq()).zzc(zzc, zzayp.zzdhv);
        zzaap = zza.zzaap();
        return ByteBuffer.allocate(zzaap.length + zzc.length).put(zzaap).put(zzc).array();
    }
}
