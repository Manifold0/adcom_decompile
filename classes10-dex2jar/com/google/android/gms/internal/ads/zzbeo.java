// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbeo extends zzben
{
    @Override
    final int zzb(int i, final byte[] array, int n, final int n2) {
        while (n < n2 && array[n] >= 0) {
            ++n;
        }
        if ((i = n) < n2) {
            while (i < n2) {
                n = i + 1;
                final int n3 = array[i];
                if (n3 < 0) {
                    if (n3 < -32) {
                        i = n3;
                        if (n < n2) {
                            if (n3 >= -62) {
                                i = n + 1;
                                if (array[n] <= -65) {
                                    continue;
                                }
                            }
                            return -1;
                        }
                        return i;
                    }
                    else if (n3 < -16) {
                        if (n >= n2 - 1) {
                            return zzg(array, n, n2);
                        }
                        final int n4 = n + 1;
                        i = array[n];
                        if (i <= -65 && (n3 != -32 || i >= -96) && (n3 != -19 || i < -96)) {
                            i = n4 + 1;
                            if (array[n4] <= -65) {
                                continue;
                            }
                        }
                        return -1;
                    }
                    else {
                        if (n >= n2 - 2) {
                            return zzg(array, n, n2);
                        }
                        i = n + 1;
                        n = array[n];
                        if (n <= -65 && (n3 << 28) + (n + 112) >> 30 == 0) {
                            n = i + 1;
                            if (array[i] <= -65) {
                                i = n + 1;
                                if (array[n] <= -65) {
                                    continue;
                                }
                            }
                        }
                        return -1;
                    }
                }
                else {
                    i = n;
                }
            }
            return 0;
        }
        i = 0;
        return i;
    }
    
    @Override
    final int zzb(final CharSequence charSequence, final byte[] array, int n, int i) {
        final int length = charSequence.length();
        final int n2 = 0;
        int n3;
        char char1;
        for (n3 = n + i, i = n2; i < length && i + n < n3; ++i) {
            char1 = charSequence.charAt(i);
            if (char1 >= '\u0080') {
                break;
            }
            array[n + i] = (byte)char1;
        }
        if (i == length) {
            return n + length;
        }
        n += i;
        while (i < length) {
            final char char2 = charSequence.charAt(i);
            Label_0135: {
                if (char2 < '\u0080' && n < n3) {
                    final int n4 = n + 1;
                    array[n] = (byte)char2;
                    n = n4;
                }
                else if (char2 < '\u0800' && n <= n3 - 2) {
                    final int n5 = n + 1;
                    array[n] = (byte)(char2 >>> 6 | 0x3C0);
                    n = n5 + 1;
                    array[n5] = (byte)((char2 & '?') | 0x80);
                }
                else if ((char2 < '\ud800' || '\udfff' < char2) && n <= n3 - 3) {
                    final int n6 = n + 1;
                    array[n] = (byte)(char2 >>> 12 | 0x1E0);
                    final int n7 = n6 + 1;
                    array[n6] = (byte)((char2 >>> 6 & 0x3F) | 0x80);
                    n = n7 + 1;
                    array[n7] = (byte)((char2 & '?') | 0x80);
                }
                else {
                    if (n <= n3 - 4) {
                        int n8 = i;
                        if (i + 1 != charSequence.length()) {
                            ++i;
                            final char char3 = charSequence.charAt(i);
                            if (Character.isSurrogatePair(char2, char3)) {
                                final int codePoint = Character.toCodePoint(char2, char3);
                                final int n9 = n + 1;
                                array[n] = (byte)(codePoint >>> 18 | 0xF0);
                                n = n9 + 1;
                                array[n9] = (byte)((codePoint >>> 12 & 0x3F) | 0x80);
                                final int n10 = n + 1;
                                array[n] = (byte)((codePoint >>> 6 & 0x3F) | 0x80);
                                n = n10 + 1;
                                array[n10] = (byte)((codePoint & 0x3F) | 0x80);
                                break Label_0135;
                            }
                            n8 = i;
                        }
                        throw new zzbep(n8 - 1, length);
                    }
                    if ('\ud800' <= char2 && char2 <= '\udfff' && (i + 1 == charSequence.length() || !Character.isSurrogatePair(char2, charSequence.charAt(i + 1)))) {
                        throw new zzbep(i, length);
                    }
                    throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(char2).append(" at index ").append(n).toString());
                }
            }
            ++i;
        }
        return n;
    }
}
