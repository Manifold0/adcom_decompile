// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import java.math.BigInteger;
import java.util.Locale;
import java.security.NoSuchAlgorithmException;
import com.chartboost.sdk.Tracking.a;
import java.security.MessageDigest;

public final class c
{
    private c() {
    }
    
    public static byte[] a(final byte[] array) {
        final byte[] array2 = null;
        // monitorenter(c.class)
        byte[] digest = array2;
        if (array == null) {
            return digest;
        }
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(array);
            digest = instance.digest();
            return digest;
        }
        catch (NoSuchAlgorithmException ex) {
            a.a(c.class, "sha1", ex);
            digest = array2;
            return digest;
        }
        catch (Exception ex2) {
            a.a(c.class, "sha1", ex2);
            digest = array2;
            return digest;
        }
        finally {
        }
        // monitorexit(c.class)
    }
    
    public static String b(final byte[] array) {
        if (array == null) {
            return null;
        }
        return String.format(Locale.US, "%0" + (array.length << 1) + "x", new BigInteger(1, array));
    }
}
