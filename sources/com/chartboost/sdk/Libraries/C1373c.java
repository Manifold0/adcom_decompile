package com.chartboost.sdk.Libraries;

import com.adjust.sdk.Constants;
import com.chartboost.sdk.Tracking.C1391a;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* renamed from: com.chartboost.sdk.Libraries.c */
public final class C1373c {
    private C1373c() {
    }

    /* renamed from: a */
    public static synchronized byte[] m3122a(byte[] bArr) {
        byte[] bArr2 = null;
        synchronized (C1373c.class) {
            if (bArr != null) {
                try {
                    MessageDigest instance = MessageDigest.getInstance(Constants.SHA1);
                    instance.update(bArr);
                    bArr2 = instance.digest();
                } catch (Exception e) {
                    C1391a.m3206a(C1373c.class, "sha1", e);
                } catch (Exception e2) {
                    C1391a.m3206a(C1373c.class, "sha1", e2);
                }
            }
        }
        return bArr2;
    }

    /* renamed from: b */
    public static String m3123b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        return String.format(Locale.US, "%0" + (bArr.length << 1) + "x", new Object[]{bigInteger});
    }
}
