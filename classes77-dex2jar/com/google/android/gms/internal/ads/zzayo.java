// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

public final class zzayo implements zzaue
{
    private static final byte[] zzdhv;
    private final ECPrivateKey zzdnc;
    private final zzayq zzdnd;
    private final String zzdne;
    private final byte[] zzdnf;
    private final zzayw zzdng;
    private final zzayn zzdnh;
    
    static {
        zzdhv = new byte[0];
    }
    
    public zzayo(final ECPrivateKey zzdnc, final byte[] zzdnf, final String zzdne, final zzayw zzdng, final zzayn zzdnh) throws GeneralSecurityException {
        this.zzdnc = zzdnc;
        this.zzdnd = new zzayq(zzdnc);
        this.zzdnf = zzdnf;
        this.zzdne = zzdne;
        this.zzdng = zzdng;
        this.zzdnh = zzdnh;
    }
}
