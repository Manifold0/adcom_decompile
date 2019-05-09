package com.google.android.gms.internal.drive;

import com.tonyodev.fetch.FetchService;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzip {
    private final ByteBuffer zzmv;

    private zzip(ByteBuffer byteBuffer) {
        this.zzmv = byteBuffer;
        this.zzmv.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzip(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < '') {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 'ࠀ') {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 'ࠀ') {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if ('?' <= charAt2 && charAt2 <= '?') {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + i2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i2 = i3 + i;
                if (i2 < length) {
                    return i2;
                }
                throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
            }
        }
        i2 = i3;
        if (i2 < length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    private final void zza(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzn((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzn((int) j);
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i = 0;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byte[] array = byteBuffer.array();
                r1 = byteBuffer.arrayOffset() + byteBuffer.position();
                r2 = byteBuffer.remaining();
                int length = charSequence.length();
                int i2 = r1 + r2;
                while (i < length && i + r1 < i2) {
                    r2 = charSequence.charAt(i);
                    if (r2 >= '') {
                        break;
                    }
                    array[r1 + i] = (byte) r2;
                    i++;
                }
                if (i == length) {
                    i = r1 + length;
                } else {
                    r2 = r1 + i;
                    while (i < length) {
                        char charAt = charSequence.charAt(i);
                        if (charAt < '' && r2 < i2) {
                            r1 = r2 + 1;
                            array[r2] = (byte) charAt;
                        } else if (charAt < 'ࠀ' && r2 <= i2 - 2) {
                            r7 = r2 + 1;
                            array[r2] = (byte) ((charAt >>> 6) | 960);
                            r1 = r7 + 1;
                            array[r7] = (byte) ((charAt & 63) | 128);
                        } else if ((charAt < '?' || '?' < charAt) && r2 <= i2 - 3) {
                            r1 = r2 + 1;
                            array[r2] = (byte) ((charAt >>> 12) | FetchService.QUERY_SINGLE);
                            r2 = r1 + 1;
                            array[r1] = (byte) (((charAt >>> 6) & 63) | 128);
                            r1 = r2 + 1;
                            array[r2] = (byte) ((charAt & 63) | 128);
                        } else if (r2 <= i2 - 4) {
                            if (i + 1 != charSequence.length()) {
                                i++;
                                char charAt2 = charSequence.charAt(i);
                                if (Character.isSurrogatePair(charAt, charAt2)) {
                                    int toCodePoint = Character.toCodePoint(charAt, charAt2);
                                    r1 = r2 + 1;
                                    array[r2] = (byte) ((toCodePoint >>> 18) | 240);
                                    r2 = r1 + 1;
                                    array[r1] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                                    r7 = r2 + 1;
                                    array[r2] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                                    r1 = r7 + 1;
                                    array[r7] = (byte) ((toCodePoint & 63) | 128);
                                }
                            }
                            throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                        } else {
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt + " at index " + r2);
                        }
                        i++;
                        r2 = r1;
                    }
                    i = r2;
                }
                byteBuffer.position(i - byteBuffer.arrayOffset());
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            r1 = charSequence.length();
            while (i < r1) {
                r2 = charSequence.charAt(i);
                if (r2 < '') {
                    byteBuffer.put((byte) r2);
                } else if (r2 < 'ࠀ') {
                    byteBuffer.put((byte) ((r2 >>> 6) | 960));
                    byteBuffer.put((byte) ((r2 & 63) | 128));
                } else if (r2 < '?' || '?' < r2) {
                    byteBuffer.put((byte) ((r2 >>> 12) | FetchService.QUERY_SINGLE));
                    byteBuffer.put((byte) (((r2 >>> 6) & 63) | 128));
                    byteBuffer.put((byte) ((r2 & 63) | 128));
                } else {
                    if (i + 1 != charSequence.length()) {
                        i++;
                        char charAt3 = charSequence.charAt(i);
                        if (Character.isSurrogatePair(r2, charAt3)) {
                            r2 = Character.toCodePoint(r2, charAt3);
                            byteBuffer.put((byte) ((r2 >>> 18) | 240));
                            byteBuffer.put((byte) (((r2 >>> 12) & 63) | 128));
                            byteBuffer.put((byte) (((r2 >>> 6) & 63) | 128));
                            byteBuffer.put((byte) ((r2 & 63) | 128));
                        }
                    }
                    throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
                }
                i++;
            }
        }
    }

    public static int zzb(int i, long j) {
        int zzo = zzo(i);
        long zzb = zzb(j);
        int i2 = (-128 & zzb) == 0 ? 1 : (-16384 & zzb) == 0 ? 2 : (-2097152 & zzb) == 0 ? 3 : (-268435456 & zzb) == 0 ? 4 : (-34359738368L & zzb) == 0 ? 5 : (-4398046511104L & zzb) == 0 ? 6 : (-562949953421312L & zzb) == 0 ? 7 : (-72057594037927936L & zzb) == 0 ? 8 : (zzb & Long.MIN_VALUE) == 0 ? 9 : 10;
        return i2 + zzo;
    }

    private static long zzb(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static zzip zzb(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzip zzb(byte[] bArr, int i, int i2) {
        return new zzip(bArr, 0, i2);
    }

    public static int zzc(int i, int i2) {
        return zzo(i) + zzm(i2);
    }

    public static int zzi(String str) {
        int zza = zza((CharSequence) str);
        return zza + zzq(zza);
    }

    public static int zzm(int i) {
        return i >= 0 ? zzq(i) : 10;
    }

    private final void zzn(int i) throws IOException {
        byte b = (byte) i;
        if (this.zzmv.hasRemaining()) {
            this.zzmv.put(b);
            return;
        }
        throw new zziq(this.zzmv.position(), this.zzmv.limit());
    }

    public static int zzo(int i) {
        return zzq(i << 3);
    }

    public static int zzq(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public final void zza(int i, long j) throws IOException {
        zzd(i, 0);
        zza(zzb(j));
    }

    public final void zzb(int i, int i2) throws IOException {
        zzd(i, 0);
        if (i2 >= 0) {
            zzp(i2);
        } else {
            zza((long) i2);
        }
    }

    public final void zzbh() {
        if (this.zzmv.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzmv.remaining())}));
        }
    }

    public final void zzc(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzmv.remaining() >= length) {
            this.zzmv.put(bArr, 0, length);
            return;
        }
        throw new zziq(this.zzmv.position(), this.zzmv.limit());
    }

    public final void zzd(int i, int i2) throws IOException {
        zzp((i << 3) | i2);
    }

    public final void zzh(String str) throws IOException {
        try {
            int zzq = zzq(str.length());
            if (zzq == zzq(str.length() * 3)) {
                int position = this.zzmv.position();
                if (this.zzmv.remaining() < zzq) {
                    throw new zziq(zzq + position, this.zzmv.limit());
                }
                this.zzmv.position(position + zzq);
                zza((CharSequence) str, this.zzmv);
                int position2 = this.zzmv.position();
                this.zzmv.position(position);
                zzp((position2 - position) - zzq);
                this.zzmv.position(position2);
                return;
            }
            zzp(zza((CharSequence) str));
            zza((CharSequence) str, this.zzmv);
        } catch (Throwable e) {
            zziq zziq = new zziq(this.zzmv.position(), this.zzmv.limit());
            zziq.initCause(e);
            throw zziq;
        }
    }

    public final void zzp(int i) throws IOException {
        while ((i & -128) != 0) {
            zzn((i & 127) | 128);
            i >>>= 7;
        }
        zzn(i);
    }
}
