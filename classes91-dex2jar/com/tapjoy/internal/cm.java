// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public final class cm
{
    public static byte[] a(final byte[] array) {
        try {
            return MessageDigest.getInstance("SHA-1").digest(array);
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
