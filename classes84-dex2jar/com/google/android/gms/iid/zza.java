// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import java.security.NoSuchAlgorithmException;
import java.security.KeyPairGenerator;
import java.security.KeyPair;

public final class zza
{
    public static KeyPair zzauq() {
        try {
            final KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(2048);
            return instance.generateKeyPair();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new AssertionError((Object)ex);
        }
    }
}
