package com.tapjoy.internal;

import com.adjust.sdk.Constants;
import java.security.MessageDigest;

public final class cm {
    /* renamed from: a */
    public static byte[] m7332a(byte[] bArr) {
        try {
            return MessageDigest.getInstance(Constants.SHA1).digest(bArr);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
