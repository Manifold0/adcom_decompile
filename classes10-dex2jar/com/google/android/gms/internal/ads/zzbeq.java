// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbeq extends zzben
{
    private static int zza(final byte[] array, final int n, final long n2, final int n3) {
        switch (n3) {
            default: {
                throw new AssertionError();
            }
            case 0: {
                return zzda(n);
            }
            case 1: {
                return zzz(n, zzbek.zza(array, n2));
            }
            case 2: {
                return zzf(n, zzbek.zza(array, n2), zzbek.zza(array, 1L + n2));
            }
        }
    }
    
    @Override
    final int zzb(int n, final byte[] array, int i, int n2) {
        if ((i | n2 | array.length - n2) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", array.length, i, n2));
        }
        final long n3 = i;
        n2 -= (int)n3;
        Label_0075: {
            if (n2 < 16) {
                n = 0;
            }
            else {
                i = 0;
                for (long n4 = n3; i < n2; ++i, ++n4) {
                    n = i;
                    if (zzbek.zza(array, n4) < 0) {
                        break Label_0075;
                    }
                }
                n = n2;
            }
        }
        long n5 = n + n3;
        n = n2 - n;
        while (true) {
            i = 0;
            long n6;
            while (true) {
                n6 = n5;
                if (n <= 0) {
                    break;
                }
                n6 = 1L + n5;
                i = zzbek.zza(array, n5);
                if (i < 0) {
                    break;
                }
                --n;
                n5 = n6;
            }
            if (n == 0) {
                return 0;
            }
            --n;
            if (i < -32) {
                if (n == 0) {
                    return i;
                }
                --n;
                if (i < -62) {
                    break;
                }
                n5 = 1L + n6;
                if (zzbek.zza(array, n6) > -65) {
                    break;
                }
                continue;
            }
            else if (i < -16) {
                if (n < 2) {
                    return zza(array, i, n6, n);
                }
                n -= 2;
                final long n7 = n6 + 1L;
                n2 = zzbek.zza(array, n6);
                if (n2 > -65 || (i == -32 && n2 < -96) || (i == -19 && n2 >= -96)) {
                    return -1;
                }
                n5 = 1L + n7;
                if (zzbek.zza(array, n7) > -65) {
                    return -1;
                }
                continue;
            }
            else {
                if (n < 3) {
                    return zza(array, i, n6, n);
                }
                n -= 3;
                final long n8 = 1L + n6;
                n2 = zzbek.zza(array, n6);
                if (n2 > -65 || (i << 28) + (n2 + 112) >> 30 != 0) {
                    return -1;
                }
                final long n9 = 1L + n8;
                if (zzbek.zza(array, n8) > -65) {
                    return -1;
                }
                n5 = 1L + n9;
                if (zzbek.zza(array, n9) > -65) {
                    return -1;
                }
                continue;
            }
        }
        return -1;
    }
    
    @Override
    final int zzb(final CharSequence charSequence, final byte[] array, int i, int n) {
        long n2 = i;
        final long n3 = n2 + n;
        final int length = charSequence.length();
        if (length > n || array.length - n < i) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(length - 1)).append(" at index ").append(i + n).toString());
        }
        for (i = 0; i < length; ++i, ++n2) {
            n = charSequence.charAt(i);
            if (n >= 128) {
                break;
            }
            zzbek.zza(array, n2, (byte)n);
        }
        if (i == length) {
            return (int)n2;
        }
        while (i < length) {
            final char char1 = charSequence.charAt(i);
            Label_0197: {
                if (char1 < '\u0080' && n2 < n3) {
                    final long n4 = 1L + n2;
                    zzbek.zza(array, n2, (byte)char1);
                    n2 = n4;
                }
                else if (char1 < '\u0800' && n2 <= n3 - 2L) {
                    final long n5 = n2 + 1L;
                    zzbek.zza(array, n2, (byte)(char1 >>> 6 | 0x3C0));
                    n2 = 1L + n5;
                    zzbek.zza(array, n5, (byte)((char1 & '?') | 0x80));
                }
                else if ((char1 < '\ud800' || '\udfff' < char1) && n2 <= n3 - 3L) {
                    final long n6 = 1L + n2;
                    zzbek.zza(array, n2, (byte)(char1 >>> 12 | 0x1E0));
                    final long n7 = 1L + n6;
                    zzbek.zza(array, n6, (byte)((char1 >>> 6 & 0x3F) | 0x80));
                    n2 = 1L + n7;
                    zzbek.zza(array, n7, (byte)((char1 & '?') | 0x80));
                }
                else {
                    if (n2 <= n3 - 4L) {
                        n = i;
                        if (i + 1 != length) {
                            ++i;
                            final char char2 = charSequence.charAt(i);
                            if (Character.isSurrogatePair(char1, char2)) {
                                n = Character.toCodePoint(char1, char2);
                                final long n8 = 1L + n2;
                                zzbek.zza(array, n2, (byte)(n >>> 18 | 0xF0));
                                final long n9 = 1L + n8;
                                zzbek.zza(array, n8, (byte)((n >>> 12 & 0x3F) | 0x80));
                                final long n10 = n9 + 1L;
                                zzbek.zza(array, n9, (byte)((n >>> 6 & 0x3F) | 0x80));
                                n2 = 1L + n10;
                                zzbek.zza(array, n10, (byte)((n & 0x3F) | 0x80));
                                break Label_0197;
                            }
                            n = i;
                        }
                        throw new zzbep(n - 1, length);
                    }
                    if ('\ud800' <= char1 && char1 <= '\udfff' && (i + 1 == length || !Character.isSurrogatePair(char1, charSequence.charAt(i + 1)))) {
                        throw new zzbep(i, length);
                    }
                    throw new ArrayIndexOutOfBoundsException(new StringBuilder(46).append("Failed writing ").append(char1).append(" at index ").append(n2).toString());
                }
            }
            ++i;
        }
        return (int)n2;
    }
}
