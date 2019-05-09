// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.nio.ByteBuffer;

public final class zzayx implements zzatz
{
    private final zzazi zzdnv;
    private final zzauk zzdnw;
    private final int zzdnx;
    
    public zzayx(final zzazi zzdnv, final zzauk zzdnw, final int zzdnx) {
        this.zzdnv = zzdnv;
        this.zzdnw = zzdnw;
        this.zzdnx = zzdnx;
    }
    
    @Override
    public final byte[] zzc(byte[] array, byte[] copy) throws GeneralSecurityException {
        final byte[] zzk = this.zzdnv.zzk(array);
        array = copy;
        if (copy == null) {
            array = new byte[0];
        }
        copy = Arrays.copyOf(ByteBuffer.allocate(8).putLong(8L * array.length).array(), 8);
        return zzayk.zza(new byte[][] { zzk, this.zzdnw.zzg(zzayk.zza(new byte[][] { array, zzk, copy })) });
    }
}
