// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbem
{
    private static final zzben zzdzz;
    
    static {
        int n;
        if (zzbek.zzagf() && zzbek.zzagg()) {
            n = 1;
        }
        else {
            n = 0;
        }
        zzben zzdzz2;
        if (n != 0) {
            zzdzz2 = new zzbeq();
        }
        else {
            zzdzz2 = new zzbeo();
        }
        zzdzz = zzdzz2;
    }
    
    static int zza(final CharSequence charSequence) {
        int n = 0;
        int length;
        int i;
        for (length = charSequence.length(), i = 0; i < length && charSequence.charAt(i) < '\u0080'; ++i) {}
        int n2 = length;
        while (true) {
            while (i < length) {
                final char char1 = charSequence.charAt(i);
                if (char1 < '\u0800') {
                    n2 += '\u007f' - char1 >>> 31;
                    ++i;
                }
                else {
                    int n3;
                    for (int length2 = charSequence.length(); i < length2; i = n3 + 1) {
                        final char char2 = charSequence.charAt(i);
                        if (char2 < '\u0800') {
                            n += '\u007f' - char2 >>> 31;
                            n3 = i;
                        }
                        else {
                            final int n4 = n + 2;
                            n3 = i;
                            n = n4;
                            if ('\ud800' <= char2) {
                                n3 = i;
                                n = n4;
                                if (char2 <= '\udfff') {
                                    if (Character.codePointAt(charSequence, i) < 65536) {
                                        throw new zzbep(i, length2);
                                    }
                                    n3 = i + 1;
                                    n = n4;
                                }
                            }
                        }
                    }
                    n2 += n;
                    if (n2 < length) {
                        throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(n2 + 4294967296L).toString());
                    }
                    return n2;
                }
            }
            continue;
        }
    }
    
    static int zza(final CharSequence charSequence, final byte[] array, final int n, final int n2) {
        return zzbem.zzdzz.zzb(charSequence, array, n, n2);
    }
    
    private static int zzda(final int n) {
        int n2 = n;
        if (n > -12) {
            n2 = -1;
        }
        return n2;
    }
    
    private static int zzf(final int n, final int n2, final int n3) {
        if (n > -12 || n2 > -65 || n3 > -65) {
            return -1;
        }
        return n2 << 8 ^ n ^ n3 << 16;
    }
    
    public static boolean zzf(final byte[] array, final int n, final int n2) {
        return zzbem.zzdzz.zzf(array, n, n2);
    }
    
    private static int zzg(final byte[] array, final int n, final int n2) {
        final byte b = array[n - 1];
        switch (n2 - n) {
            default: {
                throw new AssertionError();
            }
            case 0: {
                return zzda(b);
            }
            case 1: {
                return zzz(b, array[n]);
            }
            case 2: {
                return zzf(b, array[n], array[n + 1]);
            }
        }
    }
    
    public static boolean zzs(final byte[] array) {
        return zzbem.zzdzz.zzf(array, 0, array.length);
    }
    
    private static int zzz(final int n, final int n2) {
        if (n > -12 || n2 > -65) {
            return -1;
        }
        return n2 << 8 ^ n;
    }
}
