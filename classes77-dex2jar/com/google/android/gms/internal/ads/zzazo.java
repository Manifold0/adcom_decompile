// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class zzazo implements zzatz
{
    private final byte[] key;
    private final zzazn zzdor;
    private final zzazn zzdos;
    
    zzazo(final byte[] array) throws InvalidKeyException {
        this.key = array.clone();
        this.zzdor = this.zzc(array, 1);
        this.zzdos = this.zzc(array, 0);
    }
    
    abstract zzazn zzc(final byte[] p0, final int p1) throws InvalidKeyException;
    
    @Override
    public byte[] zzc(byte[] zze, byte[] array) throws GeneralSecurityException {
        final int length = zze.length;
        this.zzdor.zzaao();
        if (length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        final ByteBuffer allocate = ByteBuffer.allocate(zze.length + this.zzdor.zzaao() + 16);
        if (allocate.remaining() < zze.length + this.zzdor.zzaao() + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        final int position = allocate.position();
        this.zzdor.zza(allocate, zze);
        allocate.position(position);
        final byte[] array2 = new byte[this.zzdor.zzaao()];
        allocate.get(array2);
        allocate.limit(allocate.limit() - 16);
        if ((zze = array) == null) {
            zze = new byte[0];
        }
        final ByteBuffer zzb = this.zzdos.zzb(array2, 0);
        array = new byte[32];
        zzb.get(array);
        int length2;
        if (zze.length % 16 == 0) {
            length2 = zze.length;
        }
        else {
            length2 = zze.length + 16 - zze.length % 16;
        }
        final int remaining = allocate.remaining();
        int n;
        if (remaining % 16 == 0) {
            n = remaining;
        }
        else {
            n = remaining + 16 - remaining % 16;
        }
        final ByteBuffer order = ByteBuffer.allocate(length2 + n + 16).order(ByteOrder.LITTLE_ENDIAN);
        order.put(zze);
        order.position(length2);
        order.put(allocate);
        order.position(length2 + n);
        order.putLong(zze.length);
        order.putLong(remaining);
        zze = zzazk.zze(array, order.array());
        allocate.limit(allocate.limit() + 16);
        allocate.put(zze);
        return allocate.array();
    }
}
