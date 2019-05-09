// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import android.os.Build$VERSION;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import android.util.Base64;
import java.security.MessageDigest;

public class o
{
    private static String a(final byte[] array, final String s) {
        final MessageDigest instance = MessageDigest.getInstance(s);
        instance.reset();
        return Base64.encodeToString(instance.digest(array), 0);
    }
    
    public static void a(final HttpsURLConnection httpsURLConnection, final Set<String> set, final Set<String> set2) {
        if (Build$VERSION.SDK_INT != 15 || !"4.0.3".equals(Build$VERSION.RELEASE)) {
            while (true) {
                while (true) {
                    int n;
                    try {
                        final Certificate[] serverCertificates = httpsURLConnection.getServerCertificates();
                        final int length = serverCertificates.length;
                        n = 0;
                        if (n >= length) {
                            throw new CertificateException("Unable to find valid certificate or public key.");
                        }
                        final X509Certificate x509Certificate = (X509Certificate)serverCertificates[n];
                        final String a = a(x509Certificate.getEncoded(), "SHA-1");
                        if (set != null && set.contains(a)) {
                            break;
                        }
                        final String a2 = a(x509Certificate.getPublicKey().getEncoded(), "SHA-1");
                        if (set2 != null) {
                            if (set2.contains(a2)) {
                                break;
                            }
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                    ++n;
                    continue;
                }
            }
        }
    }
}
