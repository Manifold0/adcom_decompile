// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

public final class zzazl
{
    private static final ThreadLocal<SecureRandom> zzdon;
    
    static {
        zzdon = new zzazm();
    }
    
    private static SecureRandom zzaar() {
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }
    
    public static byte[] zzbh(final int n) {
        final byte[] array = new byte[n];
        zzazl.zzdon.get().nextBytes(array);
        return array;
    }
}
