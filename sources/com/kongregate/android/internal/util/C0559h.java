package com.kongregate.android.internal.util;

/* renamed from: com.kongregate.android.internal.util.h */
public class C0559h {
    /* renamed from: a */
    private static final char[] f750a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: b */
    private static final char[] f751b = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static char[] m727a(byte[] bArr) {
        return C0559h.m728a(bArr, true);
    }

    /* renamed from: a */
    public static char[] m728a(byte[] bArr, boolean z) {
        return C0559h.m729a(bArr, z ? f750a : f751b);
    }

    /* renamed from: a */
    protected static char[] m729a(byte[] bArr, char[] cArr) {
        int i = 0;
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

    /* renamed from: b */
    public static String m730b(byte[] bArr) {
        return new String(C0559h.m727a(bArr));
    }
}
