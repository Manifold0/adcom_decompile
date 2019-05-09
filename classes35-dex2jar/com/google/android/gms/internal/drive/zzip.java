// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;

public final class zzip
{
    private final ByteBuffer zzmv;
    
    private zzip(final ByteBuffer zzmv) {
        (this.zzmv = zzmv).order(ByteOrder.LITTLE_ENDIAN);
    }
    
    private zzip(final byte[] array, final int n, final int n2) {
        this(ByteBuffer.wrap(array, n, n2));
    }
    
    private static int zza(final CharSequence charSequence) {
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
                    while (i < charSequence.length()) {
                        final char char2 = charSequence.charAt(i);
                        int n3;
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
                                        throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(i).toString());
                                    }
                                    n3 = i + 1;
                                    n = n4;
                                }
                            }
                        }
                        i = n3 + 1;
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
    
    private final void zza(long n) throws IOException {
        while ((0xFFFFFFFFFFFFFF80L & n) != 0x0L) {
            this.zzn(((int)n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.zzn((int)n);
    }
    
    private static void zza(final CharSequence charSequence, final ByteBuffer byteBuffer) {
        final int n = 0;
        int n2 = 0;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (byteBuffer.hasArray()) {
            int n15;
            while (true) {
            Label_0121_Outer:
                while (true) {
                    int n7 = 0;
                    int n8 = 0;
                    Label_0943: {
                        int n3 = 0;
                        while (true) {
                            int length = 0;
                            Label_0915: {
                                byte[] array;
                                int n4;
                                char char2;
                                final int n5;
                                try {
                                    array = byteBuffer.array();
                                    n3 = byteBuffer.arrayOffset() + byteBuffer.position();
                                    final int remaining = byteBuffer.remaining();
                                    for (length = charSequence.length(), n4 = n3 + remaining; n2 < length && n2 + n3 < n4; ++n2) {
                                        final char char1 = charSequence.charAt(n2);
                                        if (char1 >= '\u0080') {
                                            break;
                                        }
                                        array[n3 + n2] = (byte)char1;
                                    }
                                    break Label_0915;
                                    // iftrue(Label_0604:, n2 >= length)
                                    char2 = charSequence.charAt(n2);
                                    // iftrue(Label_0188:, char2 >= '\u0080' || n5 >= n4)
                                    final int n6 = n5 + 1;
                                    array[n5] = (byte)char2;
                                    n7 = n2;
                                    n8 = n6;
                                    break Label_0943;
                                    // iftrue(Label_0272:, char2 >= '\u0800' || n5 > n4 - 2)
                                    Block_23: {
                                        break Block_23;
                                        final int n9;
                                        byteBuffer.position(n9 - byteBuffer.arrayOffset());
                                        return;
                                    }
                                    final int n10 = n5 + 1;
                                    array[n5] = (byte)(char2 >>> 6 | 0x3C0);
                                    final int n11 = n10 + 1;
                                    array[n10] = (byte)((char2 & '?') | 0x80);
                                    n7 = n2;
                                    n8 = n11;
                                    break Label_0943;
                                }
                                catch (ArrayIndexOutOfBoundsException ex2) {
                                    final BufferOverflowException ex = new BufferOverflowException();
                                    ex.initCause(ex2);
                                    throw ex;
                                }
                                Label_0272: {
                                    if ((char2 < '\ud800' || '\udfff' < char2) && n5 <= n4 - 3) {
                                        final int n12 = n5 + 1;
                                        array[n5] = (byte)(char2 >>> 12 | 0x1E0);
                                        final int n13 = n12 + 1;
                                        array[n12] = (byte)((char2 >>> 6 & 0x3F) | 0x80);
                                        final int n14 = n13 + 1;
                                        array[n13] = (byte)((char2 & '?') | 0x80);
                                        n7 = n2;
                                        n8 = n14;
                                        break Label_0943;
                                    }
                                }
                                if (n5 > n4 - 4) {
                                    throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(char2).append(" at index ").append(n5).toString());
                                }
                                n15 = n2;
                                if (n2 + 1 == charSequence.length()) {
                                    break;
                                }
                                final int n16 = n2 + 1;
                                final char char3 = charSequence.charAt(n16);
                                if (!Character.isSurrogatePair(char2, char3)) {
                                    n15 = n16;
                                    break;
                                }
                                final int codePoint = Character.toCodePoint(char2, char3);
                                final int n17 = n5 + 1;
                                array[n5] = (byte)(codePoint >>> 18 | 0xF0);
                                final int n18 = n17 + 1;
                                array[n17] = (byte)((codePoint >>> 12 & 0x3F) | 0x80);
                                final int n19 = n18 + 1;
                                array[n18] = (byte)((codePoint >>> 6 & 0x3F) | 0x80);
                                final int n20 = n19 + 1;
                                array[n19] = (byte)((codePoint & 0x3F) | 0x80);
                                n7 = n16;
                                n8 = n20;
                                break Label_0943;
                                Label_0604:
                                final int n9 = n5;
                                continue;
                            }
                            if (n2 == length) {
                                final int n9 = n3 + length;
                                continue;
                            }
                            break;
                        }
                        final int n5 = n3 + n2;
                        continue Label_0121_Outer;
                    }
                    final int n21 = n7 + 1;
                    final int n5 = n8;
                    n2 = n21;
                    continue;
                }
            }
            throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(n15 - 1).toString());
        }
        for (int length2 = charSequence.length(), i = n; i < length2; ++i) {
            final char char4 = charSequence.charAt(i);
            if (char4 < '\u0080') {
                byteBuffer.put((byte)char4);
            }
            else if (char4 < '\u0800') {
                byteBuffer.put((byte)(char4 >>> 6 | 0x3C0));
                byteBuffer.put((byte)((char4 & '?') | 0x80));
            }
            else {
                if (char4 >= '\ud800' && '\udfff' >= char4) {
                    int n22 = i;
                    if (i + 1 != charSequence.length()) {
                        ++i;
                        final char char5 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(char4, char5)) {
                            final int codePoint2 = Character.toCodePoint(char4, char5);
                            byteBuffer.put((byte)(codePoint2 >>> 18 | 0xF0));
                            byteBuffer.put((byte)((codePoint2 >>> 12 & 0x3F) | 0x80));
                            byteBuffer.put((byte)((codePoint2 >>> 6 & 0x3F) | 0x80));
                            byteBuffer.put((byte)((codePoint2 & 0x3F) | 0x80));
                            continue;
                        }
                        n22 = i;
                    }
                    throw new IllegalArgumentException(new StringBuilder(39).append("Unpaired surrogate at index ").append(n22 - 1).toString());
                }
                byteBuffer.put((byte)(char4 >>> 12 | 0x1E0));
                byteBuffer.put((byte)((char4 >>> 6 & 0x3F) | 0x80));
                byteBuffer.put((byte)((char4 & '?') | 0x80));
            }
        }
    }
    
    public static int zzb(int n, long zzb) {
        final int zzo = zzo(n);
        zzb = zzb(zzb);
        if ((0xFFFFFFFFFFFFFF80L & zzb) == 0x0L) {
            n = 1;
        }
        else if ((0xFFFFFFFFFFFFC000L & zzb) == 0x0L) {
            n = 2;
        }
        else if ((0xFFFFFFFFFFE00000L & zzb) == 0x0L) {
            n = 3;
        }
        else if ((0xFFFFFFFFF0000000L & zzb) == 0x0L) {
            n = 4;
        }
        else if ((0xFFFFFFF800000000L & zzb) == 0x0L) {
            n = 5;
        }
        else if ((0xFFFFFC0000000000L & zzb) == 0x0L) {
            n = 6;
        }
        else if ((0xFFFE000000000000L & zzb) == 0x0L) {
            n = 7;
        }
        else if ((0xFF00000000000000L & zzb) == 0x0L) {
            n = 8;
        }
        else if ((zzb & Long.MIN_VALUE) == 0x0L) {
            n = 9;
        }
        else {
            n = 10;
        }
        return n + zzo;
    }
    
    private static long zzb(final long n) {
        return n << 1 ^ n >> 63;
    }
    
    public static zzip zzb(final byte[] array) {
        return zzb(array, 0, array.length);
    }
    
    public static zzip zzb(final byte[] array, final int n, final int n2) {
        return new zzip(array, 0, n2);
    }
    
    public static int zzc(final int n, final int n2) {
        return zzo(n) + zzm(n2);
    }
    
    public static int zzi(final String s) {
        final int zza = zza(s);
        return zza + zzq(zza);
    }
    
    public static int zzm(final int n) {
        if (n >= 0) {
            return zzq(n);
        }
        return 10;
    }
    
    private final void zzn(final int n) throws IOException {
        final byte b = (byte)n;
        if (!this.zzmv.hasRemaining()) {
            throw new zziq(this.zzmv.position(), this.zzmv.limit());
        }
        this.zzmv.put(b);
    }
    
    public static int zzo(final int n) {
        return zzq(n << 3);
    }
    
    public static int zzq(final int n) {
        if ((n & 0xFFFFFF80) == 0x0) {
            return 1;
        }
        if ((n & 0xFFFFC000) == 0x0) {
            return 2;
        }
        if ((0xFFE00000 & n) == 0x0) {
            return 3;
        }
        if ((0xF0000000 & n) == 0x0) {
            return 4;
        }
        return 5;
    }
    
    public final void zza(final int n, final long n2) throws IOException {
        this.zzd(n, 0);
        this.zza(zzb(n2));
    }
    
    public final void zzb(final int n, final int n2) throws IOException {
        this.zzd(n, 0);
        if (n2 >= 0) {
            this.zzp(n2);
            return;
        }
        this.zza(n2);
    }
    
    public final void zzbh() {
        if (this.zzmv.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", this.zzmv.remaining()));
        }
    }
    
    public final void zzc(final byte[] array) throws IOException {
        final int length = array.length;
        if (this.zzmv.remaining() >= length) {
            this.zzmv.put(array, 0, length);
            return;
        }
        throw new zziq(this.zzmv.position(), this.zzmv.limit());
    }
    
    public final void zzd(final int n, final int n2) throws IOException {
        this.zzp(n << 3 | n2);
    }
    
    public final void zzh(final String s) throws IOException {
        Label_0150: {
            int zzq;
            int position;
            try {
                zzq = zzq(s.length());
                if (zzq != zzq(s.length() * 3)) {
                    break Label_0150;
                }
                position = this.zzmv.position();
                if (this.zzmv.remaining() < zzq) {
                    throw new zziq(zzq + position, this.zzmv.limit());
                }
            }
            catch (BufferOverflowException ex) {
                final zziq zziq = new zziq(this.zzmv.position(), this.zzmv.limit());
                zziq.initCause(ex);
                throw zziq;
            }
            this.zzmv.position(position + zzq);
            zza(s, this.zzmv);
            final int position2 = this.zzmv.position();
            this.zzmv.position(position);
            this.zzp(position2 - position - zzq);
            this.zzmv.position(position2);
            return;
        }
        this.zzp(zza(s));
        zza(s, this.zzmv);
    }
    
    public final void zzp(int n) throws IOException {
        while ((n & 0xFFFFFF80) != 0x0) {
            this.zzn((n & 0x7F) | 0x80);
            n >>>= 7;
        }
        this.zzn(n);
    }
}
