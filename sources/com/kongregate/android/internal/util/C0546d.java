package com.kongregate.android.internal.util;

import android.util.Base64;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.kongregate.android.internal.util.d */
public class C0546d {
    /* renamed from: a */
    public static boolean m633a(String str, String str2) {
        byte[] decode = Base64.decode(str2, 0);
        if (decode == null || decode.length == 0) {
            return false;
        }
        int length = decode.length * 8;
        int min = Math.min(20, (length / 8) - 11);
        switch (length) {
            case 2048:
                String substring = StringUtils.m586c(str).toLowerCase().substring(0, min * 2);
                try {
                    Key generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmcYRydg2slRHWF5FLS7h\ngrYADRev22NbrKqay3cffaWQySvN4NL57SXI5GA4XCoEUtbOfp1VWkmXuW5BM5JN\nl95wyb+u7+Ns/Bn0C0OiHK5ULe9fvhseE1WHtpsTYhYF/DmhoS+M5lNWJLJdcsxa\nj6QiVI08vGZB3bsQ7BgOo1cIJoxmZ6g4IHRr/TUOy8mS5CkGGNAUMUGkR7uwRyLz\ndmPOJP6OKZXij3wYq2Pv0PdQZcYw1MgTP82LSXNy0KgUEiea8tF0YMUJI6TxSb2B\nyS7LrOekrMdjT6sKhiG/tymCrXXk4DTNNJbZ0dE248IHO4JxTgK2LgIrUGQw4DSx\nYwIDAQAB".getBytes(), 0)));
                    Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    instance.init(2, generatePublic);
                    String b = C0559h.m730b(instance.doFinal(decode));
                    if (b == null || !substring.equalsIgnoreCase(b)) {
                        return false;
                    }
                    return true;
                } catch (Throwable e) {
                    C0562j.m762d("Security exception", e);
                    return false;
                }
            default:
                return false;
        }
    }
}
