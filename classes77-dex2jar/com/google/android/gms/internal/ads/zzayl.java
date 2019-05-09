// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;

final class zzayl extends zzazn
{
    private static final byte[] zzdnb;
    
    static {
        zzdnb = new byte[16];
    }
    
    zzayl(final byte[] array, final int n) throws InvalidKeyException {
        super(array, n);
    }
    
    private static void zza(final int[] array, final int n, final int n2, final int n3, final int n4) {
        array[n] += array[n2];
        array[n4] = zzazn.rotateLeft(array[n4] ^ array[n], 16);
        array[n3] += array[n4];
        array[n2] = zzazn.rotateLeft(array[n2] ^ array[n3], 12);
        array[n] += array[n2];
        array[n4] = zzazn.rotateLeft(array[n4] ^ array[n], 8);
        array[n3] += array[n4];
        array[n2] = zzazn.rotateLeft(array[n2] ^ array[n3], 7);
    }
    
    @Override
    final int zzaao() {
        return 12;
    }
    
    @Override
    final ByteBuffer zzb(final byte[] array, int i) {
        final int[] array2 = new int[16];
        System.arraycopy(zzazn.zzdoo, 0, array2, 0, zzayl.zzdoo.length);
        final int[] zza = zzazn.zza(ByteBuffer.wrap(this.zzdop.getBytes()));
        System.arraycopy(zza, 0, array2, 4, zza.length);
        array2[12] = i;
        System.arraycopy(zzazn.zza(ByteBuffer.wrap(array)), 0, array2, 13, 3);
        final int[] array3 = array2.clone();
        for (i = 0; i < 10; ++i) {
            zza(array3, 0, 4, 8, 12);
            zza(array3, 1, 5, 9, 13);
            zza(array3, 2, 6, 10, 14);
            zza(array3, 3, 7, 11, 15);
            zza(array3, 0, 5, 10, 15);
            zza(array3, 1, 6, 11, 12);
            zza(array3, 2, 7, 8, 13);
            zza(array3, 3, 4, 9, 14);
        }
        for (i = 0; i < 16; ++i) {
            array2[i] += array3[i];
        }
        final ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(array2, 0, 16);
        return order;
    }
}
