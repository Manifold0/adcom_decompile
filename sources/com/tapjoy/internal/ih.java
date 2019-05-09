package com.tapjoy.internal;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import java.nio.charset.Charset;

final class ih {
    /* renamed from: a */
    public static final Charset f8217a = Charset.forName("UTF-8");

    /* renamed from: a */
    public static void m8186a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    /* renamed from: a */
    public static int m8184a(int i) {
        return ((((ViewCompat.MEASURED_STATE_MASK & i) >>> 24) | ((16711680 & i) >>> 8)) | ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) << 8)) | ((i & 255) << 24);
    }

    /* renamed from: a */
    public static long m8185a(long j) {
        return ((((((((-72057594037927936L & j) >>> 56) | ((71776119061217280L & j) >>> 40)) | ((280375465082880L & j) >>> 24)) | ((1095216660480L & j) >>> 8)) | ((4278190080L & j) << 8)) | ((16711680 & j) << 24)) | ((65280 & j) << 40)) | ((255 & j) << 56);
    }

    /* renamed from: a */
    public static boolean m8188a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static void m8187a(Throwable th) {
        throw th;
    }
}
