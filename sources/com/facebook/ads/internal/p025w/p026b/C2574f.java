package com.facebook.ads.internal.p025w.p026b;

import android.content.Context;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;

/* renamed from: com.facebook.ads.internal.w.b.f */
public class C2574f {
    /* renamed from: a */
    private static final String f6340a = C2574f.class.getSimpleName();

    /* renamed from: com.facebook.ads.internal.w.b.f$a */
    public enum C2573a {
        UNKNOWN(0),
        UNROOTED(1),
        ROOTED(2);
        
        /* renamed from: d */
        public final int f6339d;

        private C2573a(int i) {
            this.f6339d = i;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static com.facebook.ads.internal.p025w.p026b.C2574f.C2573a m6634a() {
        /*
        r1 = 1;
        r0 = 0;
        r2 = new java.io.File;	 Catch:{ Throwable -> 0x0031 }
        r3 = "/system/app/Superuser.apk";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0031 }
        r2 = r2.exists();	 Catch:{ Throwable -> 0x0031 }
        if (r2 != 0) goto L_0x0026;
    L_0x000f:
        r2 = android.os.Build.TAGS;	 Catch:{ Throwable -> 0x0031 }
        if (r2 == 0) goto L_0x002c;
    L_0x0013:
        r3 = "test-keys";
        r2 = r2.contains(r3);	 Catch:{ Throwable -> 0x0031 }
        if (r2 == 0) goto L_0x002c;
    L_0x001b:
        r2 = r1;
    L_0x001c:
        if (r2 != 0) goto L_0x0026;
    L_0x001e:
        r2 = "su";
        r2 = com.facebook.ads.internal.p025w.p026b.C2574f.m6636a(r2);	 Catch:{ Throwable -> 0x0031 }
        if (r2 == 0) goto L_0x0027;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        if (r0 == 0) goto L_0x002e;
    L_0x0029:
        r0 = com.facebook.ads.internal.p025w.p026b.C2574f.C2573a.ROOTED;	 Catch:{ Throwable -> 0x0031 }
    L_0x002b:
        return r0;
    L_0x002c:
        r2 = r0;
        goto L_0x001c;
    L_0x002e:
        r0 = com.facebook.ads.internal.p025w.p026b.C2574f.C2573a.UNROOTED;	 Catch:{ Throwable -> 0x0031 }
        goto L_0x002b;
    L_0x0031:
        r0 = move-exception;
        r0 = com.facebook.ads.internal.p025w.p026b.C2574f.C2573a.UNKNOWN;
        goto L_0x002b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.w.b.f.a():com.facebook.ads.internal.w.b.f$a");
    }

    @Nullable
    /* renamed from: a */
    public static String m6635a(Context context) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (Signature toByteArray : context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures) {
                stringBuilder.append(C2579i.m6641a(MessageDigest.getInstance("SHA1").digest(CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(toByteArray.toByteArray())).getPublicKey().getEncoded())));
                stringBuilder.append(";");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m6636a(String str) {
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File name : listFiles) {
                        if (name.getName().equals(str)) {
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        return false;
    }
}
