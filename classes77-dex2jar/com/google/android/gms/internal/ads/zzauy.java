// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.nio.ByteBuffer;

public final class zzauy implements zzatz
{
    private static final byte[] zzdhv;
    private final zzaxn zzdhw;
    private final zzatz zzdhx;
    
    static {
        zzdhv = new byte[0];
    }
    
    public zzauy(final zzaxn zzdhw, final zzatz zzdhx) {
        this.zzdhw = zzdhw;
        this.zzdhx = zzdhx;
    }
    
    @Override
    public final byte[] zzc(byte[] zzc, final byte[] array) throws GeneralSecurityException {
        final byte[] byteArray = zzauo.zzb(this.zzdhw).toByteArray();
        final byte[] zzc2 = this.zzdhx.zzc(byteArray, zzauy.zzdhv);
        zzc = ((zzatz)zzauo.zza(this.zzdhw.zzyw(), byteArray)).zzc(zzc, array);
        return ByteBuffer.allocate(zzc2.length + 4 + zzc.length).putInt(zzc2.length).put(zzc2).put(zzc).array();
    }
}
