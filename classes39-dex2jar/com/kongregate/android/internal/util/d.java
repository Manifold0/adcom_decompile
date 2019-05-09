// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import java.security.PublicKey;
import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.Cipher;
import java.security.spec.KeySpec;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import android.util.Base64;

public class d
{
    public static boolean a(String substring, String b) {
        final byte[] decode = Base64.decode(b, 0);
        if (decode != null && decode.length != 0) {
            final int n = decode.length * 8;
            final int min = Math.min(20, n / 8 - 11);
            switch (n) {
                default: {
                    return false;
                }
                case 2048: {
                    substring = StringUtils.c(substring).toLowerCase().substring(0, min * 2);
                    final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmcYRydg2slRHWF5FLS7h\ngrYADRev22NbrKqay3cffaWQySvN4NL57SXI5GA4XCoEUtbOfp1VWkmXuW5BM5JN\nl95wyb+u7+Ns/Bn0C0OiHK5ULe9fvhseE1WHtpsTYhYF/DmhoS+M5lNWJLJdcsxa\nj6QiVI08vGZB3bsQ7BgOo1cIJoxmZ6g4IHRr/TUOy8mS5CkGGNAUMUGkR7uwRyLz\ndmPOJP6OKZXij3wYq2Pv0PdQZcYw1MgTP82LSXNy0KgUEiea8tF0YMUJI6TxSb2B\nyS7LrOekrMdjT6sKhiG/tymCrXXk4DTNNJbZ0dE248IHO4JxTgK2LgIrUGQw4DSx\nYwIDAQAB".getBytes(), 0));
                    try {
                        final PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
                        final Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                        instance.init(2, generatePublic);
                        b = h.b(instance.doFinal(decode));
                        if (b != null && substring.equalsIgnoreCase(b)) {
                            return true;
                        }
                        break;
                    }
                    catch (GeneralSecurityException ex) {
                        j.d("Security exception", ex);
                        return false;
                    }
                    break;
                }
            }
        }
        return false;
    }
}
