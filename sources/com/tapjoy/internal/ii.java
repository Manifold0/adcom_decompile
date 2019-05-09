package com.tapjoy.internal;

import com.ironsource.sdk.constants.Constants.RequestParameters;

public final class ii {
    /* renamed from: a */
    private static final char[] f8218a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: b */
    private static final char[] f8219b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /* renamed from: c */
    private final String f8220c;

    /* renamed from: a */
    public static char[] m8189a(byte[] bArr) {
        int i = 0;
        char[] cArr = f8218a;
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public final String toString() {
        return super.toString() + "[charsetName=" + this.f8220c + RequestParameters.RIGHT_BRACKETS;
    }
}
