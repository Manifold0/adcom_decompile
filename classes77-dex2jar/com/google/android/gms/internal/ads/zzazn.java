// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.nio.IntBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.nio.ByteBuffer;

abstract class zzazn implements zzazi
{
    static final int[] zzdoo;
    final zzazh zzdop;
    private final int zzdoq;
    
    static {
        zzdoo = zza(ByteBuffer.wrap(new byte[] { 101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107 }));
    }
    
    zzazn(final byte[] array, final int zzdoq) throws InvalidKeyException {
        if (array.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.zzdop = zzazh.zzm(array);
        this.zzdoq = zzdoq;
    }
    
    static int rotateLeft(final int n, final int n2) {
        return n << n2 | n >>> -n2;
    }
    
    static int[] zza(final ByteBuffer byteBuffer) {
        final IntBuffer intBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        final int[] array = new int[intBuffer.remaining()];
        intBuffer.get(array);
        return array;
    }
    
    final void zza(final ByteBuffer byteBuffer, final byte[] array) throws GeneralSecurityException {
        if (byteBuffer.remaining() - this.zzaao() < array.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        final byte[] zzbh = zzazl.zzbh(this.zzaao());
        byteBuffer.put(zzbh);
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        final int remaining = wrap.remaining();
        for (int n = remaining / 64 + 1, i = 0; i < n; ++i) {
            final ByteBuffer zzb = this.zzb(zzbh, this.zzdoq + i);
            if (i == n - 1) {
                zzayk.zza(byteBuffer, wrap, zzb, remaining % 64);
            }
            else {
                zzayk.zza(byteBuffer, wrap, zzb, 64);
            }
        }
    }
    
    abstract int zzaao();
    
    abstract ByteBuffer zzb(final byte[] p0, final int p1);
    
    @Override
    public final byte[] zzk(final byte[] array) throws GeneralSecurityException {
        final int length = array.length;
        this.zzaao();
        if (length > 2147483635) {
            throw new GeneralSecurityException("plaintext too long");
        }
        final ByteBuffer allocate = ByteBuffer.allocate(this.zzaao() + array.length);
        this.zza(allocate, array);
        return allocate.array();
    }
}
