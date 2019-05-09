// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzbbq
{
    private static final Charset ISO_8859_1;
    static final Charset UTF_8;
    public static final byte[] zzduq;
    private static final ByteBuffer zzdur;
    private static final zzbaq zzdus;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
        zzdur = ByteBuffer.wrap(zzduq = new byte[0]);
        final byte[] zzduq2 = zzbbq.zzduq;
        zzdus = zzbaq.zza(zzduq2, 0, zzduq2.length, false);
    }
    
    static <T> T checkNotNull(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
    
    public static int hashCode(final byte[] array) {
        final int length = array.length;
        int zza;
        if ((zza = zza(length, array, 0, length)) == 0) {
            zza = 1;
        }
        return zza;
    }
    
    static int zza(int n, final byte[] array, final int n2, final int n3) {
        for (int i = n2; i < n2 + n3; ++i) {
            n = n * 31 + array[i];
        }
        return n;
    }
    
    static Object zza(final Object o, final Object o2) {
        return ((zzbcu)o).zzade().zzd((zzbcu)o2).zzadj();
    }
    
    static <T> T zza(final T t, final String s) {
        if (t == null) {
            throw new NullPointerException(s);
        }
        return t;
    }
    
    public static int zzar(final boolean b) {
        if (b) {
            return 1231;
        }
        return 1237;
    }
    
    static boolean zzi(final zzbcu zzbcu) {
        return false;
    }
    
    public static boolean zzs(final byte[] array) {
        return zzbem.zzs(array);
    }
    
    public static String zzt(final byte[] array) {
        return new String(array, zzbbq.UTF_8);
    }
    
    public static int zzv(final long n) {
        return (int)(n >>> 32 ^ n);
    }
}
