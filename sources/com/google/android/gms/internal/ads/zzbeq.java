package com.google.android.gms.internal.ads;

import com.tonyodev.fetch.FetchService;

final class zzbeq extends zzben {
    zzbeq() {
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzbem.zzda(i);
            case 1:
                return zzbem.zzz(i, zzbek.zza(bArr, j));
            case 2:
                return zzbem.zzf(i, zzbek.zza(bArr, j), zzbek.zza(bArr, 1 + j));
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final int zzb(int r9, byte[] r10, int r11, int r12) {
        /*
        r8 = this;
        r0 = r11 | r12;
        r1 = r10.length;
        r1 = r1 - r12;
        r0 = r0 | r1;
        if (r0 >= 0) goto L_0x002c;
    L_0x0007:
        r0 = new java.lang.ArrayIndexOutOfBoundsException;
        r1 = "Array length=%d, index=%d, limit=%d";
        r2 = 3;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = r10.length;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = java.lang.Integer.valueOf(r11);
        r2[r3] = r4;
        r3 = 2;
        r4 = java.lang.Integer.valueOf(r12);
        r2[r3] = r4;
        r1 = java.lang.String.format(r1, r2);
        r0.<init>(r1);
        throw r0;
    L_0x002c:
        r4 = (long) r11;
        r0 = (long) r12;
        r0 = r0 - r4;
        r1 = (int) r0;
        r0 = 16;
        if (r1 >= r0) goto L_0x004a;
    L_0x0034:
        r0 = 0;
    L_0x0035:
        r1 = r1 - r0;
        r2 = (long) r0;
        r2 = r2 + r4;
        r0 = r1;
    L_0x0039:
        r1 = 0;
        r4 = r2;
    L_0x003b:
        if (r0 <= 0) goto L_0x005e;
    L_0x003d:
        r2 = 1;
        r2 = r2 + r4;
        r1 = com.google.android.gms.internal.ads.zzbek.zza(r10, r4);
        if (r1 < 0) goto L_0x005d;
    L_0x0046:
        r0 = r0 + -1;
        r4 = r2;
        goto L_0x003b;
    L_0x004a:
        r0 = 0;
        r2 = r4;
    L_0x004c:
        if (r0 >= r1) goto L_0x005b;
    L_0x004e:
        r6 = 1;
        r6 = r6 + r2;
        r2 = com.google.android.gms.internal.ads.zzbek.zza(r10, r2);
        if (r2 < 0) goto L_0x0035;
    L_0x0057:
        r0 = r0 + 1;
        r2 = r6;
        goto L_0x004c;
    L_0x005b:
        r0 = r1;
        goto L_0x0035;
    L_0x005d:
        r4 = r2;
    L_0x005e:
        if (r0 != 0) goto L_0x0062;
    L_0x0060:
        r0 = 0;
    L_0x0061:
        return r0;
    L_0x0062:
        r0 = r0 + -1;
        r2 = -32;
        if (r1 >= r2) goto L_0x007f;
    L_0x0068:
        if (r0 != 0) goto L_0x006c;
    L_0x006a:
        r0 = r1;
        goto L_0x0061;
    L_0x006c:
        r0 = r0 + -1;
        r2 = -62;
        if (r1 < r2) goto L_0x007d;
    L_0x0072:
        r2 = 1;
        r2 = r2 + r4;
        r1 = com.google.android.gms.internal.ads.zzbek.zza(r10, r4);
        r4 = -65;
        if (r1 <= r4) goto L_0x0039;
    L_0x007d:
        r0 = -1;
        goto L_0x0061;
    L_0x007f:
        r2 = -16;
        if (r1 >= r2) goto L_0x00b6;
    L_0x0083:
        r2 = 2;
        if (r0 >= r2) goto L_0x008b;
    L_0x0086:
        r0 = zza(r10, r1, r4, r0);
        goto L_0x0061;
    L_0x008b:
        r0 = r0 + -2;
        r2 = 1;
        r6 = r4 + r2;
        r2 = com.google.android.gms.internal.ads.zzbek.zza(r10, r4);
        r3 = -65;
        if (r2 > r3) goto L_0x00b4;
    L_0x0099:
        r3 = -32;
        if (r1 != r3) goto L_0x00a1;
    L_0x009d:
        r3 = -96;
        if (r2 < r3) goto L_0x00b4;
    L_0x00a1:
        r3 = -19;
        if (r1 != r3) goto L_0x00a9;
    L_0x00a5:
        r1 = -96;
        if (r2 >= r1) goto L_0x00b4;
    L_0x00a9:
        r2 = 1;
        r2 = r2 + r6;
        r1 = com.google.android.gms.internal.ads.zzbek.zza(r10, r6);
        r4 = -65;
        if (r1 <= r4) goto L_0x0039;
    L_0x00b4:
        r0 = -1;
        goto L_0x0061;
    L_0x00b6:
        r2 = 3;
        if (r0 >= r2) goto L_0x00be;
    L_0x00b9:
        r0 = zza(r10, r1, r4, r0);
        goto L_0x0061;
    L_0x00be:
        r0 = r0 + -3;
        r2 = 1;
        r2 = r2 + r4;
        r4 = com.google.android.gms.internal.ads.zzbek.zza(r10, r4);
        r5 = -65;
        if (r4 > r5) goto L_0x00ea;
    L_0x00cb:
        r1 = r1 << 28;
        r4 = r4 + 112;
        r1 = r1 + r4;
        r1 = r1 >> 30;
        if (r1 != 0) goto L_0x00ea;
    L_0x00d4:
        r4 = 1;
        r4 = r4 + r2;
        r1 = com.google.android.gms.internal.ads.zzbek.zza(r10, r2);
        r2 = -65;
        if (r1 > r2) goto L_0x00ea;
    L_0x00df:
        r2 = 1;
        r2 = r2 + r4;
        r1 = com.google.android.gms.internal.ads.zzbek.zza(r10, r4);
        r4 = -65;
        if (r1 <= r4) goto L_0x0039;
    L_0x00ea:
        r0 = -1;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbeq.zzb(int, byte[], int, int):int");
    }

    final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j = (long) i;
        long j2 = j + ((long) i2);
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length - 1) + " at index " + (i + i2));
        }
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt >= '') {
                break;
            }
            long j3 = 1 + j;
            zzbek.zza(bArr, j, (byte) charAt);
            i3++;
            j = j3;
        }
        if (i3 == length) {
            return (int) j;
        }
        j3 = j;
        while (i3 < length) {
            charAt = charSequence.charAt(i3);
            if (charAt < '' && j3 < j2) {
                j = 1 + j3;
                zzbek.zza(bArr, j3, (byte) charAt);
            } else if (charAt < 'ࠀ' && j3 <= j2 - 2) {
                r12 = j3 + 1;
                zzbek.zza(bArr, j3, (byte) ((charAt >>> 6) | 960));
                j = 1 + r12;
                zzbek.zza(bArr, r12, (byte) ((charAt & 63) | 128));
            } else if ((charAt < '?' || '?' < charAt) && j3 <= j2 - 3) {
                j = 1 + j3;
                zzbek.zza(bArr, j3, (byte) ((charAt >>> 12) | FetchService.QUERY_SINGLE));
                j3 = 1 + j;
                zzbek.zza(bArr, j, (byte) (((charAt >>> 6) & 63) | 128));
                j = 1 + j3;
                zzbek.zza(bArr, j3, (byte) ((charAt & 63) | 128));
            } else if (j3 <= j2 - 4) {
                if (i3 + 1 != length) {
                    i3++;
                    char charAt2 = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int toCodePoint = Character.toCodePoint(charAt, charAt2);
                        j = 1 + j3;
                        zzbek.zza(bArr, j3, (byte) ((toCodePoint >>> 18) | 240));
                        j3 = 1 + j;
                        zzbek.zza(bArr, j, (byte) (((toCodePoint >>> 12) & 63) | 128));
                        r12 = j3 + 1;
                        zzbek.zza(bArr, j3, (byte) (((toCodePoint >>> 6) & 63) | 128));
                        j = 1 + r12;
                        zzbek.zza(bArr, r12, (byte) ((toCodePoint & 63) | 128));
                    }
                }
                throw new zzbep(i3 - 1, length);
            } else if ('?' > charAt || charAt > '?' || (i3 + 1 != length && Character.isSurrogatePair(charAt, charSequence.charAt(i3 + 1)))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt + " at index " + j3);
            } else {
                throw new zzbep(i3, length);
            }
            i3++;
            j3 = j;
        }
        return (int) j3;
    }
}
