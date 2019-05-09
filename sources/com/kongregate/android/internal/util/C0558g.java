package com.kongregate.android.internal.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.kongregate.android.internal.sdk.C0973R;
import com.kongregate.p000o.p005b.C0603c;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.kongregate.android.internal.util.g */
public class C0558g {
    /* renamed from: a */
    public static final String f746a = "icons";
    /* renamed from: b */
    public static final int f747b = 8192;
    /* renamed from: c */
    private static final AtomicReference<Context> f748c = new AtomicReference(null);
    /* renamed from: d */
    private static PackageInfo f749d;

    /* renamed from: com.kongregate.android.internal.util.g$a */
    public interface C0556a {
        /* renamed from: a */
        void m660a(int i, int i2, double d);

        /* renamed from: a */
        void m661a(C0568p c0568p);
    }

    /* renamed from: com.kongregate.android.internal.util.g$b */
    private static class C0557b extends GZIPOutputStream {
        public C0557b(OutputStream outputStream) throws IOException {
            super(outputStream);
            m662a(9);
        }

        /* renamed from: a */
        public void m662a(int i) {
            this.def.setLevel(i);
        }
    }

    /* renamed from: a */
    public static void m673a(Context context) {
        if (context != null) {
            f748c.set(context.getApplicationContext());
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    f749d = packageManager.getPackageInfo(context.getPackageName(), 0);
                    if (f749d != null && f749d.applicationInfo != null) {
                        C0562j.m756b("SourceDir: " + f749d.applicationInfo.sourceDir);
                        return;
                    }
                    return;
                } catch (Throwable e) {
                    C0562j.m762d("Package " + context.getPackageName() + " not found!", e);
                    return;
                } catch (Throwable e2) {
                    C0562j.m762d("RuntimeException getting package info", e2);
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException("Context is required");
    }

    /* renamed from: a */
    public static Resources m665a() {
        return ((Context) f748c.get()).getResources();
    }

    /* renamed from: b */
    public static boolean m685b() {
        return (f749d == null || f749d.applicationInfo == null || (f749d.applicationInfo.flags & 262144) == 0) ? false : true;
    }

    /* renamed from: c */
    public static boolean m692c() {
        try {
            return StringUtils.m593e(VERSION.RELEASE, "2.2.1");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public static String m671a(String str) {
        C0558g.m726t();
        if (!C0558g.m692c()) {
            return null;
        }
        File externalFilesDir = ((Context) f748c.get()).getExternalFilesDir(null);
        if (externalFilesDir == null) {
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/" + ((Context) f748c.get()).getPackageName() + "/files");
            C0558g.m708i(file.getAbsolutePath() + "/");
            externalFilesDir = file;
        }
        return externalFilesDir.getAbsolutePath() + "/arcade/" + str;
    }

    /* renamed from: b */
    public static boolean m686b(Context context) {
        return C0558g.m697d() && context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    /* renamed from: d */
    public static boolean m697d() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* renamed from: e */
    public static SharedPreferences m698e() {
        return ((Context) f748c.get()).getSharedPreferences("shared_preferences", 0);
    }

    /* renamed from: b */
    public static SharedPreferences m681b(String str) {
        return ((Context) f748c.get()).getSharedPreferences(str, 0);
    }

    /* renamed from: f */
    public static long m701f() {
        if (C0558g.m697d()) {
            return C0558g.m719o(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        return 0;
    }

    /* renamed from: g */
    public static long m703g() {
        if (C0558g.m697d()) {
            return C0558g.m721p(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        return 0;
    }

    /* renamed from: h */
    public static long m705h() {
        return C0558g.m719o(C0558g.m691c(""));
    }

    /* renamed from: i */
    public static long m707i() {
        return C0558g.m721p(C0558g.m691c(""));
    }

    /* renamed from: o */
    private static long m719o(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    /* renamed from: p */
    private static long m721p(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    /* renamed from: a */
    public static float m663a(int i) {
        try {
            return ((Context) f748c.get()).getResources().getDimension(i);
        } catch (Throwable e) {
            C0562j.m760c("Dimension id " + i + " not found", e);
            return 0.0f;
        }
    }

    /* renamed from: a */
    public static int m664a(String str, String str2, Context context) {
        Resources resources = context.getResources();
        try {
            return resources.getIdentifier(str, str2, resources.getResourcePackageName(C0973R.id.resources_package_name_lookup));
        } catch (Throwable e) {
            C0562j.m760c("token resource not found (R.id.resources_package_name_lookup) unable to lookup: " + str, e);
            return 0;
        }
    }

    /* renamed from: j */
    public static String m710j() {
        return C0558g.m695d(f746a);
    }

    /* renamed from: k */
    public static String m711k() {
        return C0558g.m695d(C0603c.f884a);
    }

    /* renamed from: l */
    public static String m714l() {
        return C0558g.m695d("avatar");
    }

    /* renamed from: m */
    public static String m716m() {
        return C0558g.m691c("games");
    }

    /* renamed from: n */
    public static String m717n() {
        return C0558g.m691c("avatars");
    }

    /* renamed from: o */
    public static String m720o() {
        return C0558g.m691c("app_images");
    }

    /* renamed from: p */
    public static String m722p() {
        return C0558g.m691c("promotion_images");
    }

    /* renamed from: q */
    public static String m723q() {
        return C0558g.m691c("badge_images");
    }

    @TargetApi(9)
    /* renamed from: r */
    public static String m724r() {
        File file = new File(C0558g.m691c("downloads"));
        if (!file.exists()) {
            file.mkdirs();
        }
        if (C0542a.m606a(9)) {
            file.setReadable(true, false);
        }
        return file.getPath();
    }

    /* renamed from: c */
    public static String m691c(String str) {
        C0558g.m726t();
        return ((Context) f748c.get()).getFilesDir().getPath() + "/" + str;
    }

    /* renamed from: a */
    public static boolean m680a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return str.equals(str2);
        }
        try {
            return new File(str).getCanonicalPath().equals(new File(str2).getCanonicalPath());
        } catch (IOException e) {
            C0562j.m759c("exception comparing files. they must not be the same");
            return false;
        }
    }

    /* renamed from: d */
    public static String m695d(String str) {
        C0558g.m726t();
        File cacheDir = ((Context) f748c.get()).getCacheDir();
        if (cacheDir == null) {
            File file = new File("/data/data/" + ((Context) f748c.get()).getPackageName() + "/cache");
            if (!file.exists()) {
                file.mkdirs();
            }
            cacheDir = file;
        }
        return cacheDir.toString() + "/" + str;
    }

    /* renamed from: s */
    public static String m725s() {
        return C0558g.m691c("userdata");
    }

    /* renamed from: a */
    public static String m669a(int i, String str) {
        return C0558g.m691c("userdata/" + i + "/" + str);
    }

    /* renamed from: e */
    public static boolean m700e(String str) {
        return new File(str).exists();
    }

    /* renamed from: f */
    public static String m702f(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\\.");
        if (split.length >= 2) {
            return split[split.length - 1];
        }
        return "";
    }

    /* renamed from: a */
    public static String m672a(String str, long j, long j2, String str2) {
        int indexOf = str2.indexOf("?");
        if (indexOf > 0) {
            str2 = str2.substring(0, indexOf);
        }
        StringBuilder stringBuilder = new StringBuilder(256);
        String f = C0558g.m702f(str2);
        stringBuilder.append(str).append("/").append(j).append("_").append(j2);
        stringBuilder.append(".").append(f);
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m670a(long j, long j2, String str) {
        return C0558g.m672a(C0558g.m720o(), j, j2, str);
    }

    /* renamed from: g */
    public static boolean m704g(String str) {
        return C0558g.m678a(new File(str));
    }

    /* renamed from: h */
    public static boolean m706h(String str) {
        if (StringUtils.m580a((CharSequence) str)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        return file.delete();
    }

    /* renamed from: a */
    public static boolean m678a(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        C0558g.m678a(file2);
                    } else if (!file2.delete()) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }

    /* renamed from: b */
    public static void m684b(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }

    /* renamed from: c */
    public static boolean m693c(File file) {
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length != 0) {
            return false;
        }
        return true;
    }

    /* renamed from: i */
    public static boolean m708i(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf != str.length() - 1) {
            str = str.substring(0, lastIndexOf + 1);
        }
        if (new File(str).exists()) {
            return true;
        }
        return new File(str).mkdirs();
    }

    /* renamed from: b */
    public static boolean m687b(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    /* renamed from: a */
    private static C0568p m666a(C0556a c0556a, C0568p c0568p) {
        if (c0556a != null) {
            c0556a.m661a(c0568p);
        }
        return c0568p;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static com.kongregate.android.internal.util.C0568p m668a(java.lang.String r12, java.lang.String r13, com.kongregate.android.internal.util.C0558g.C0556a r14) {
        /*
        r3 = 0;
        r0 = 1;
        r2 = 0;
        r1 = ".gz";
        r1 = r13.lastIndexOf(r1);
        r4 = r13.length();
        r4 = r4 + -3;
        if (r1 != r4) goto L_0x0091;
    L_0x0011:
        r1 = r0;
    L_0x0012:
        if (r1 == 0) goto L_0x0093;
    L_0x0014:
        r1 = ".gz";
        r1 = r12.lastIndexOf(r1);
        r4 = r12.length();
        r4 = r4 + -3;
        if (r1 == r4) goto L_0x0093;
    L_0x0022:
        r1 = r0;
    L_0x0023:
        r0 = "MD5";
        r0 = java.security.MessageDigest.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x0095 }
        r0.reset();	 Catch:{ NoSuchAlgorithmException -> 0x0212 }
        r4 = r0;
    L_0x002d:
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0.<init>(r12);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.openConnection();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = "GET";
        r0.setRequestMethod(r5);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = "Accept-Encoding";
        r6 = "gzip, deflate";
        r0.setRequestProperty(r5, r6);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = "Connection";
        r6 = "Keep-Alive";
        r0.setRequestProperty(r5, r6);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r0.setConnectTimeout(r5);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        r0.setReadTimeout(r5);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0.connect();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = r0.getResponseCode();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r5 == r6) goto L_0x009e;
    L_0x0060:
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1.<init>();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = "Connection to ";
        r1 = r1.append(r2);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1 = r1.append(r12);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = " yielded response code ";
        r1 = r1.append(r2);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.getResponseCode();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r1.append(r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1 = ", aborting";
        r0 = r0.append(r1);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        com.kongregate.android.internal.util.C0562j.m759c(r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_HTTP;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
    L_0x0090:
        return r0;
    L_0x0091:
        r1 = r2;
        goto L_0x0012;
    L_0x0093:
        r1 = r2;
        goto L_0x0023;
    L_0x0095:
        r0 = move-exception;
        r0 = r3;
    L_0x0097:
        r4 = "MD5 algorithm not found, checksum verification disabled";
        com.kongregate.android.internal.util.C0562j.m759c(r4);
        r4 = r0;
        goto L_0x002d;
    L_0x009e:
        r5 = "ETag";
        r5 = r0.getHeaderField(r5);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        if (r5 == 0) goto L_0x00ae;
    L_0x00a6:
        r6 = r5.length();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r7 = 34;
        if (r6 == r7) goto L_0x0128;
    L_0x00ae:
        r5 = r3;
        r6 = r3;
    L_0x00b0:
        r4 = r0.getInputStream();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r3 = r0.getContentEncoding();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r7 = "gzip";
        r7 = r7.equalsIgnoreCase(r3);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        if (r7 == 0) goto L_0x0134;
    L_0x00c0:
        r3 = new java.util.zip.GZIPInputStream;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r3.<init>(r4);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
    L_0x00c5:
        r4 = com.kongregate.android.internal.util.C0558g.m690c(r3);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        com.kongregate.android.internal.util.C0558g.m708i(r13);	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r7 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r3 = new java.io.File;	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r3.<init>(r13);	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r7.<init>(r3);	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r3 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r8 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r3.<init>(r7, r8);	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        if (r1 == 0) goto L_0x0215;
    L_0x00df:
        r1 = new com.kongregate.android.internal.util.g$b;	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r1.<init>(r3);	 Catch:{ IOException -> 0x0161, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
    L_0x00e4:
        r0 = r0.getContentLength();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r3 = new byte[r3];	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
    L_0x00ec:
        r8 = r4.read(r3);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r9 = -1;
        if (r8 == r9) goto L_0x0178;
    L_0x00f3:
        if (r6 == 0) goto L_0x00f9;
    L_0x00f5:
        r9 = 0;
        r6.update(r3, r9, r8);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
    L_0x00f9:
        r9 = 0;
        r1.write(r3, r9, r8);	 Catch:{ IOException -> 0x016f, MalformedURLException -> 0x0109, NumberFormatException -> 0x01f3 }
        r2 = r2 + r8;
        if (r14 == 0) goto L_0x00ec;
    L_0x0100:
        if (r0 <= 0) goto L_0x00ec;
    L_0x0102:
        r8 = (double) r2;
        r10 = (double) r0;
        r8 = r8 / r10;
        r14.m660a(r2, r0, r8);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        goto L_0x00ec;
    L_0x0109:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "FileUtils - Incorrect url: ";
        r1 = r1.append(r2);
        r1 = r1.append(r12);
        r1 = r1.toString();
        com.kongregate.android.internal.util.C0562j.m762d(r1, r0);
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_HTTP;
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);
        goto L_0x0090;
    L_0x0128:
        r3 = "\"";
        r6 = "";
        r3 = r5.replace(r3, r6);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r5 = r3;
        r6 = r4;
        goto L_0x00b0;
    L_0x0134:
        r7 = "deflate";
        r3 = r7.equalsIgnoreCase(r3);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        if (r3 == 0) goto L_0x0218;
    L_0x013c:
        r3 = new java.util.zip.InflaterInputStream;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r3.<init>(r4);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        goto L_0x00c5;
    L_0x0142:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "FileUtils - IOException downloading: ";
        r1 = r1.append(r2);
        r1 = r1.append(r12);
        r1 = r1.toString();
        com.kongregate.android.internal.util.C0562j.m762d(r1, r0);
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_NETWORK;
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);
        goto L_0x0090;
    L_0x0161:
        r0 = move-exception;
        r1 = "IOException while opening output file";
        com.kongregate.android.internal.util.C0562j.m762d(r1, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_FILESYSTEM;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        goto L_0x0090;
    L_0x016f:
        r0 = move-exception;
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_FILESYSTEM;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        goto L_0x0090;
    L_0x0178:
        com.kongregate.android.internal.util.C0558g.m675a(r7);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        com.kongregate.android.internal.util.C0558g.m676a(r4);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        com.kongregate.android.internal.util.C0558g.m677a(r1);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        if (r6 == 0) goto L_0x01eb;
    L_0x0183:
        r0 = r6.digest();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1 = new java.math.BigInteger;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = 1;
        r1.<init>(r2, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2.<init>();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r3 = "%0";
        r2 = r2.append(r3);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.length;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0 << 1;
        r0 = r2.append(r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = "x";
        r0 = r0.append(r2);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r3 = 0;
        r2[r3] = r1;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = java.lang.String.format(r0, r2);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1 = r0.equalsIgnoreCase(r5);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        if (r1 != 0) goto L_0x01eb;
    L_0x01b9:
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1.<init>();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = "Download failed due to checksum mismatch! MD5(";
        r1 = r1.append(r2);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1 = r1.append(r12);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r2 = "): ";
        r1 = r1.append(r2);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r1.append(r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r1 = ", expected: ";
        r0 = r0.append(r1);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.append(r5);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        com.kongregate.android.internal.util.C0562j.m761d(r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_HTTP;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        goto L_0x0090;
    L_0x01eb:
        r0 = com.kongregate.android.internal.util.C0568p.SUCCESS;	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);	 Catch:{ MalformedURLException -> 0x0109, IOException -> 0x0142, NumberFormatException -> 0x01f3 }
        goto L_0x0090;
    L_0x01f3:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "NumberFormatException while downloading: ";
        r1 = r1.append(r2);
        r1 = r1.append(r12);
        r1 = r1.toString();
        com.kongregate.android.internal.util.C0562j.m762d(r1, r0);
        r0 = com.kongregate.android.internal.util.C0568p.ERROR_HTTP;
        r0 = com.kongregate.android.internal.util.C0558g.m666a(r14, r0);
        goto L_0x0090;
    L_0x0212:
        r4 = move-exception;
        goto L_0x0097;
    L_0x0215:
        r1 = r3;
        goto L_0x00e4;
    L_0x0218:
        r3 = r4;
        goto L_0x00c5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kongregate.android.internal.util.g.a(java.lang.String, java.lang.String, com.kongregate.android.internal.util.g$a):com.kongregate.android.internal.util.p");
    }

    /* renamed from: c */
    public static C0568p m689c(String str, String str2) {
        return C0558g.m668a(str, str2, null);
    }

    /* renamed from: a */
    public static C0568p m667a(String str, String str2, int i, long j) {
        C0568p c0568p = C0568p.FAILURE;
        for (int i2 = 0; i2 <= i && !c0568p.m790a(); i2++) {
            c0568p = C0558g.m689c(str, str2);
            if (!c0568p.m790a()) {
                try {
                    Thread.sleep(j);
                } catch (InterruptedException e) {
                }
            }
        }
        return c0568p;
    }

    /* renamed from: a */
    public static void m676a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public static void m677a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                if (outputStream instanceof FileOutputStream) {
                    C0558g.m675a((FileOutputStream) outputStream);
                }
                outputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public static void m674a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    public static void m675a(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.getFD().sync();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: b */
    public static Bitmap m682b(int i) {
        Drawable c = C0558g.m688c(i);
        if (c instanceof BitmapDrawable) {
            return ((BitmapDrawable) c).getBitmap();
        }
        C0562j.m759c("Resource id " + i + " is not a BitmapDrawable");
        return null;
    }

    /* renamed from: c */
    public static Drawable m688c(int i) {
        try {
            return ((Context) f748c.get()).getResources().getDrawable(i);
        } catch (Throwable e) {
            C0562j.m760c("Resource id " + i + " not found", e);
            return null;
        }
    }

    /* renamed from: d */
    public static InputStream m694d(int i) {
        try {
            return ((Context) f748c.get()).getResources().openRawResource(i);
        } catch (NotFoundException e) {
            return null;
        }
    }

    /* renamed from: e */
    public static String m699e(int i) {
        InputStream d = C0558g.m694d(i);
        if (d != null) {
            return C0558g.m683b(d);
        }
        return null;
    }

    /* renamed from: b */
    public static String m683b(InputStream inputStream) {
        try {
            char[] cArr = new char[8192];
            StringWriter stringWriter = new StringWriter(8192);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read != -1) {
                    stringWriter.write(cArr, 0, read);
                } else {
                    inputStreamReader.close();
                    C0558g.m676a(inputStream);
                    return stringWriter.toString();
                }
            }
        } catch (Throwable e) {
            C0562j.m762d("IOException", e);
            return null;
        }
    }

    /* renamed from: j */
    public static InputStream m709j(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            Object obj = str.lastIndexOf(".gz") == str.length() + -3 ? 1 : null;
            InputStream c = C0558g.m690c(new FileInputStream(file));
            if (obj != null) {
                return new GZIPInputStream(c);
            }
            return c;
        } catch (Throwable e) {
            C0562j.m762d("FileUtils - File not found: " + str, e);
            return null;
        } catch (Throwable e2) {
            C0562j.m762d("FileUtils - IOException in openTextFile", e2);
            return null;
        }
    }

    /* renamed from: k */
    public static String m712k(String str) {
        try {
            InputStream l = C0558g.m713l(str);
            if (l == null) {
                return null;
            }
            String b = C0558g.m683b(l);
            l.close();
            return b;
        } catch (Throwable e) {
            C0562j.m762d("FileUtils - Couldn't close asset: " + str, e);
            return null;
        }
    }

    /* renamed from: l */
    public static InputStream m713l(String str) {
        try {
            return C0558g.m690c(((Context) f748c.get()).getAssets().open(str));
        } catch (FileNotFoundException e) {
            C0562j.m753a("Asset not found: " + str);
            return null;
        } catch (Throwable e2) {
            C0562j.m762d("FileUtils - Couldn't open asset: " + str, e2);
            return null;
        }
    }

    /* renamed from: m */
    public static long m715m(String str) {
        Throwable e;
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            AssetFileDescriptor openFd = ((Context) f748c.get()).getAssets().openFd(str);
            try {
                long length = openFd.getLength();
                if (openFd == null) {
                    return length;
                }
                try {
                    openFd.close();
                    return length;
                } catch (IOException e2) {
                    return length;
                }
            } catch (IOException e3) {
                e = e3;
                assetFileDescriptor = openFd;
                try {
                    C0562j.m762d("Failed to get asset size: " + str, e);
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException e4) {
                        }
                    }
                    return -1;
                } catch (Throwable th) {
                    e = th;
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                assetFileDescriptor = openFd;
                if (assetFileDescriptor != null) {
                    assetFileDescriptor.close();
                }
                throw e;
            }
        } catch (IOException e6) {
            e = e6;
            C0562j.m762d("Failed to get asset size: " + str, e);
            if (assetFileDescriptor != null) {
                assetFileDescriptor.close();
            }
            return -1;
        }
    }

    /* renamed from: t */
    private static synchronized void m726t() {
        synchronized (C0558g.class) {
            if (f748c.get() == null) {
                throw new IllegalStateException("You must call FileUtils.initialize first!");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static boolean m679a(java.io.InputStream r7, java.lang.String r8, boolean r9) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = new java.io.File;
        r2.<init>(r8);
        r3 = r2.exists();
        if (r3 == 0) goto L_0x0010;
    L_0x000d:
        if (r9 != 0) goto L_0x0010;
    L_0x000f:
        return r0;
    L_0x0010:
        r3 = 0;
        com.kongregate.android.internal.util.C0558g.m708i(r8);	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r4 = r2.exists();	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        if (r4 != 0) goto L_0x001d;
    L_0x001a:
        r2.createNewFile();	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
    L_0x001d:
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r2 = new java.io.File;	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r2.<init>(r8);	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r4.<init>(r2);	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r2 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r5 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r2.<init>(r4, r5);	 Catch:{ IOException -> 0x004c, all -> 0x0087 }
        r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r3 = new byte[r3];	 Catch:{ IOException -> 0x0092 }
    L_0x0032:
        r5 = r7.read(r3);	 Catch:{ IOException -> 0x0092 }
        r6 = -1;
        if (r5 == r6) goto L_0x005a;
    L_0x0039:
        r6 = 0;
        r2.write(r3, r6, r5);	 Catch:{ IOException -> 0x003e }
        goto L_0x0032;
    L_0x003e:
        r0 = move-exception;
        r3 = "IOException while writing";
        com.kongregate.android.internal.util.C0562j.m762d(r3, r0);	 Catch:{ IOException -> 0x0092 }
        com.kongregate.android.internal.util.C0558g.m676a(r7);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        r0 = r1;
        goto L_0x000f;
    L_0x004c:
        r0 = move-exception;
        r2 = "IOException while opening output file";
        com.kongregate.android.internal.util.C0562j.m762d(r2, r0);	 Catch:{ IOException -> 0x0067, all -> 0x0087 }
        com.kongregate.android.internal.util.C0558g.m676a(r7);
        com.kongregate.android.internal.util.C0558g.m677a(r3);
        r0 = r1;
        goto L_0x000f;
    L_0x005a:
        r2.flush();	 Catch:{ IOException -> 0x0092 }
        com.kongregate.android.internal.util.C0558g.m675a(r4);	 Catch:{ IOException -> 0x0092 }
        com.kongregate.android.internal.util.C0558g.m676a(r7);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        goto L_0x000f;
    L_0x0067:
        r0 = move-exception;
        r2 = r3;
    L_0x0069:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0090 }
        r3.<init>();	 Catch:{ all -> 0x0090 }
        r4 = "Error while copying file to ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0090 }
        r3 = r3.append(r8);	 Catch:{ all -> 0x0090 }
        r3 = r3.toString();	 Catch:{ all -> 0x0090 }
        com.kongregate.android.internal.util.C0562j.m762d(r3, r0);	 Catch:{ all -> 0x0090 }
        com.kongregate.android.internal.util.C0558g.m676a(r7);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        r0 = r1;
        goto L_0x000f;
    L_0x0087:
        r0 = move-exception;
        r2 = r3;
    L_0x0089:
        com.kongregate.android.internal.util.C0558g.m676a(r7);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        throw r0;
    L_0x0090:
        r0 = move-exception;
        goto L_0x0089;
    L_0x0092:
        r0 = move-exception;
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kongregate.android.internal.util.g.a(java.io.InputStream, java.lang.String, boolean):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    public static void m696d(java.lang.String r7, java.lang.String r8) {
        /*
        r0 = 0;
        r1 = ".gz";
        r1 = r8.endsWith(r1);
        if (r1 == 0) goto L_0x0012;
    L_0x0009:
        r1 = ".gz";
        r1 = r7.endsWith(r1);
        if (r1 != 0) goto L_0x0012;
    L_0x0011:
        r0 = 1;
    L_0x0012:
        com.kongregate.android.internal.util.C0558g.m704g(r8);
        r3 = new java.io.File;
        r3.<init>(r8);
        r1 = r3.exists();
        if (r1 != 0) goto L_0x0023;
    L_0x0020:
        r3.createNewFile();	 Catch:{ IOException -> 0x002f }
    L_0x0023:
        r2 = 0;
        r1 = com.kongregate.android.internal.util.C0558g.m713l(r7);
        r4 = com.kongregate.android.internal.util.C0558g.m690c(r1);
        if (r4 != 0) goto L_0x0036;
    L_0x002e:
        return;
    L_0x002f:
        r1 = move-exception;
        r2 = "IOException while copying file";
        com.kongregate.android.internal.util.C0562j.m762d(r2, r1);
        goto L_0x0023;
    L_0x0036:
        r1 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r5 = new byte[r1];	 Catch:{ FileNotFoundException -> 0x0068, IOException -> 0x0076 }
        r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0068, IOException -> 0x0076 }
        r6 = 0;
        r1.<init>(r3, r6);	 Catch:{ FileNotFoundException -> 0x0068, IOException -> 0x0076 }
        if (r0 == 0) goto L_0x0099;
    L_0x0042:
        r2 = new com.kongregate.android.internal.util.g$b;	 Catch:{ FileNotFoundException -> 0x0094, IOException -> 0x0091, all -> 0x008b }
        r2.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0094, IOException -> 0x0091, all -> 0x008b }
    L_0x0047:
        r0 = r4.read(r5);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0076 }
        r3 = -1;
        if (r0 == r3) goto L_0x005b;
    L_0x004e:
        r3 = 0;
        r2.write(r5, r3, r0);	 Catch:{ IOException -> 0x0053, FileNotFoundException -> 0x0096 }
        goto L_0x0047;
    L_0x0053:
        r0 = move-exception;
        com.kongregate.android.internal.util.C0558g.m676a(r4);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        goto L_0x002e;
    L_0x005b:
        r2.flush();	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0076 }
        com.kongregate.android.internal.util.C0558g.m675a(r1);	 Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0076 }
        com.kongregate.android.internal.util.C0558g.m676a(r4);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        goto L_0x002e;
    L_0x0068:
        r0 = move-exception;
        r1 = r2;
    L_0x006a:
        r2 = "FileNotFound";
        com.kongregate.android.internal.util.C0562j.m760c(r2, r0);	 Catch:{ all -> 0x008e }
        com.kongregate.android.internal.util.C0558g.m676a(r4);
        com.kongregate.android.internal.util.C0558g.m677a(r1);
        goto L_0x002e;
    L_0x0076:
        r0 = move-exception;
    L_0x0077:
        r1 = "IOException";
        com.kongregate.android.internal.util.C0562j.m760c(r1, r0);	 Catch:{ all -> 0x0083 }
        com.kongregate.android.internal.util.C0558g.m676a(r4);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        goto L_0x002e;
    L_0x0083:
        r0 = move-exception;
    L_0x0084:
        com.kongregate.android.internal.util.C0558g.m676a(r4);
        com.kongregate.android.internal.util.C0558g.m677a(r2);
        throw r0;
    L_0x008b:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0084;
    L_0x008e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0084;
    L_0x0091:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0077;
    L_0x0094:
        r0 = move-exception;
        goto L_0x006a;
    L_0x0096:
        r0 = move-exception;
        r1 = r2;
        goto L_0x006a;
    L_0x0099:
        r2 = r1;
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kongregate.android.internal.util.g.d(java.lang.String, java.lang.String):void");
    }

    /* renamed from: c */
    public static InputStream m690c(InputStream inputStream) {
        return ((inputStream instanceof BufferedInputStream) || inputStream == null) ? inputStream : new BufferedInputStream(inputStream, 8192);
    }

    /* renamed from: n */
    public static boolean m718n(String str) {
        C0558g.m726t();
        if (StringUtils.m587c((CharSequence) str)) {
            return false;
        }
        PackageManager packageManager = ((Context) f748c.get()).getPackageManager();
        if (packageManager != null) {
            try {
                packageManager.getPackageInfo(str, 1);
                return true;
            } catch (NameNotFoundException e) {
            }
        }
        return false;
    }
}
