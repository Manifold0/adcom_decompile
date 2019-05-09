package com.chartboost.sdk.impl;

/* renamed from: com.chartboost.sdk.impl.b */
public class C1426b {
    /* renamed from: a */
    public static int[] m3474a(char[] cArr, int[] iArr, boolean z) {
        int i = (cArr[0] << 16) + cArr[1];
        int i2 = cArr[3] + (cArr[2] << 16);
        if (!z) {
            C1426b.m3473a(iArr);
        }
        int i3 = 0;
        while (i3 < 16) {
            i ^= iArr[i3];
            C1411a c1411a = C1411a.f2948b;
            int i4 = (c1411a.f2949a[0][i >>> 24] + c1411a.f2949a[1][(i >>> 16) & 255]) ^ c1411a.f2949a[2][(i >>> 8) & 255];
            i3++;
            int i5 = i;
            i = i2 ^ (c1411a.f2949a[3][i & 255] + i4);
            i2 = i5;
        }
        i3 = iArr[16] ^ i;
        i2 ^= iArr[17];
        int[] iArr2 = new int[]{i2, i3};
        cArr[0] = i2 >>> 16;
        cArr[1] = (char) i2;
        cArr[2] = i3 >>> 16;
        cArr[3] = (char) i3;
        if (!z) {
            C1426b.m3473a(iArr);
        }
        return iArr2;
    }

    /* renamed from: a */
    private static void m3473a(int[] iArr) {
        for (int i = 0; i < iArr.length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(iArr.length - i) - 1];
            iArr[(iArr.length - i) - 1] = i2;
        }
    }
}
