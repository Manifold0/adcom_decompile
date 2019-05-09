// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.nio.ByteBuffer;

public final class zzayk
{
    public static final void zza(final ByteBuffer byteBuffer, final ByteBuffer byteBuffer2, final ByteBuffer byteBuffer3, final int n) {
        if (n < 0 || byteBuffer2.remaining() < n || byteBuffer3.remaining() < n || byteBuffer.remaining() < n) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int i = 0; i < n; ++i) {
            byteBuffer.put((byte)(byteBuffer2.get() ^ byteBuffer3.get()));
        }
    }
    
    public static byte[] zza(final byte[]... array) throws GeneralSecurityException {
        final int length = array.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            final byte[] array2 = array[i];
            if (n > Integer.MAX_VALUE - array2.length) {
                throw new GeneralSecurityException("exceeded size limit");
            }
            n += array2.length;
            ++i;
        }
        final byte[] array3 = new byte[n];
        final int length2 = array.length;
        int j = 0;
        int n2 = 0;
        while (j < length2) {
            final byte[] array4 = array[j];
            System.arraycopy(array4, 0, array3, n2, array4.length);
            n2 += array4.length;
            ++j;
        }
        return array3;
    }
}
