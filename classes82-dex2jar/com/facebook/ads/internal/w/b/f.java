// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.support.annotation.Nullable;
import android.content.pm.Signature;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.MessageDigest;
import android.content.Context;
import android.os.Build;
import java.io.File;

public class f
{
    private static final String a;
    
    static {
        a = f.class.getSimpleName();
    }
    
    public static a a() {
        while (true) {
            final int n = 0;
            while (true) {
                Label_0077: {
                    while (true) {
                        try {
                            if (!new File("/system/app/Superuser.apk").exists()) {
                                final String tags = Build.TAGS;
                                if (tags == null || !tags.contains("test-keys")) {
                                    break Label_0077;
                                }
                                final int n2 = 1;
                                if (n2 == 0) {
                                    final int n3 = n;
                                    if (!a("su")) {
                                        if (n3 != 0) {
                                            return f.a.c;
                                        }
                                        return f.a.b;
                                    }
                                }
                            }
                        }
                        catch (Throwable t) {
                            return f.a.a;
                        }
                        final int n3 = 1;
                        continue;
                    }
                }
                final int n2 = 0;
                continue;
            }
        }
    }
    
    @Nullable
    public static String a(final Context context) {
        try {
            final StringBuilder sb = new StringBuilder();
            final Signature[] signatures = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            for (int length = signatures.length, i = 0; i < length; ++i) {
                sb.append(i.a(MessageDigest.getInstance("SHA1").digest(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatures[i].toByteArray())).getPublicKey().getEncoded())));
                sb.append(";");
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static boolean a(final String s) {
        final boolean b = false;
        final String[] split = System.getenv("PATH").split(":");
        final int length = split.length;
        int n = 0;
        boolean b2 = false;
    Label_0108:
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            final File file = new File(split[n]);
            if (file.exists() && file.isDirectory()) {
                final File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (int length2 = listFiles.length, i = 0; i < length2; ++i) {
                        if (listFiles[i].getName().equals(s)) {
                            b2 = true;
                            break Label_0108;
                        }
                    }
                }
            }
            ++n;
        }
        return b2;
    }
    
    public enum a
    {
        a(0), 
        b(1), 
        c(2);
        
        public final int d;
        
        private a(final int d) {
            this.d = d;
        }
    }
}
