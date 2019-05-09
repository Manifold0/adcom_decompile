// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

public final class zzazq
{
    private static final Pattern zzdot;
    private static final Pattern zzdou;
    
    static {
        zzdot = Pattern.compile(String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s$", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+"), 2);
        zzdou = Pattern.compile(String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s/cryptoKeyVersions/%s$", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+", "([0-9a-zA-Z\\-\\.\\_~])+"), 2);
    }
    
    public static void zzbi(final int n) throws GeneralSecurityException {
        if (n != 16 && n != 24 && n != 32) {
            throw new GeneralSecurityException("invalid AES key size");
        }
    }
    
    public static void zzj(final int n, final int n2) throws GeneralSecurityException {
        if (n < 0 || n > 0) {
            throw new GeneralSecurityException(String.format("key has version %d; only keys with version in range [0..%d] are supported", n, 0));
        }
    }
}
