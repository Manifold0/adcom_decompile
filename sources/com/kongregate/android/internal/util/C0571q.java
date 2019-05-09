package com.kongregate.android.internal.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import com.kongregate.android.internal.sdk.NativeAPI;
import com.kongregate.p000o.p006c.C0626d;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import net.hockeyapp.android.LoginActivity;
import org.json.JSONObject;

/* renamed from: com.kongregate.android.internal.util.q */
public class C0571q {
    /* renamed from: a */
    private final SecretKey f774a;
    /* renamed from: b */
    private final Context f775b;
    /* renamed from: c */
    private final String f776c;
    /* renamed from: d */
    private final Map<String, String> f777d;
    /* renamed from: e */
    private final Object f778e;

    /* renamed from: a */
    public static C0571q m795a(final Context context, final String str) {
        try {
            return (C0571q) C0626d.m963a(new Callable<C0571q>() {
                public /* synthetic */ Object call() throws Exception {
                    return m792a();
                }

                /* renamed from: a */
                public C0571q m792a() {
                    return new C0571q(context, str);
                }
            }).get();
        } catch (Throwable e) {
            C0562j.m762d("failed to get shared data store: ", e);
            return null;
        } catch (Throwable e2) {
            C0562j.m762d("failed to get shared data store: ", e2);
            return null;
        }
    }

    private C0571q(Context context, String str) {
        this.f778e = new Object();
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        } else if (StringUtils.m580a((CharSequence) str)) {
            throw new IllegalArgumentException("filename can't be empty");
        } else {
            this.f775b = context;
            SharedPreferences a = m793a(context);
            CharSequence string = a.getString(LoginActivity.EXTRA_SECRET, null);
            CharSequence string2 = a.getString("file_postfix", null);
            this.f776c = str + string2;
            if (StringUtils.m580a(string) || StringUtils.m580a(string2)) {
                this.f774a = null;
                this.f777d = new HashMap(0);
                C0562j.m761d("unable to obtain a secret key or shared file location");
                return;
            }
            this.f774a = new SecretKeySpec(Base64.decode(string, 0), "AES");
            C0562j.m756b("shared filename is: " + this.f776c);
            this.f777d = m812e();
        }
    }

    /* renamed from: a */
    public String m815a(String str) {
        String str2;
        synchronized (this.f778e) {
            str2 = (String) this.f777d.get(str);
        }
        return str2;
    }

    /* renamed from: a */
    public void m817a(String str, String str2) {
        synchronized (this.f778e) {
            this.f777d.put(str, str2);
        }
    }

    /* renamed from: b */
    public void m819b(String str) {
        synchronized (this.f778e) {
            this.f777d.remove(str);
        }
    }

    /* renamed from: a */
    public void m816a() {
        synchronized (this.f778e) {
            this.f777d.clear();
        }
    }

    /* renamed from: b */
    public Map<String, Object> m818b() {
        Map hashMap;
        synchronized (this.f778e) {
            hashMap = new HashMap(this.f777d);
        }
        return hashMap;
    }

    /* renamed from: c */
    public void m820c() {
        if (this.f774a == null) {
            C0562j.m761d("no secret key, unable to persist");
        } else if (this.f775b.getApplicationInfo() == null) {
            C0562j.m759c("SharedDataStore unable to commit. no app info");
        } else if (this.f775b.getPackageManager() == null) {
            C0562j.m759c("SharedDataStore unable to commit. no package manager");
        } else {
            JSONObject jSONObject;
            synchronized (this.f778e) {
                jSONObject = new JSONObject(this.f777d);
            }
            final String jSONObject2 = jSONObject.toString();
            final Cipher f = m813f();
            C0626d.m962a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ C0571q f773c;

                public void run() {
                    byte[] a = this.f773c.m805a(jSONObject2, f);
                    byte[] iv = f.getIV();
                    for (ApplicationInfo a2 : this.f773c.m814g()) {
                        this.f773c.m803a(this.f773c.m796a(a2), a, iv);
                    }
                    if (C0558g.m686b(this.f773c.f775b)) {
                        this.f773c.m803a(this.f773c.m811d(), a, iv);
                    }
                }
            });
        }
    }

    /* renamed from: d */
    private File m811d() {
        File file = new File(Environment.getExternalStorageDirectory() + "/.kongregate/data");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    @TargetApi(9)
    /* renamed from: a */
    private File m796a(ApplicationInfo applicationInfo) {
        File file = new File(applicationInfo.dataDir + "/" + "kongregate_shared_datastore");
        if (!file.exists()) {
            file.mkdirs();
            if (C0542a.m606a(9)) {
                file.setWritable(true, false);
                file.setReadable(true, false);
                file.setExecutable(true, false);
            } else {
                C0562j.m759c("app dir may not be shareable");
            }
        }
        return file;
    }

    /* renamed from: a */
    private File m798a(File file) {
        return new File(file + "/" + this.f776c);
    }

    /* renamed from: b */
    private File m807b(ApplicationInfo applicationInfo) {
        return m798a(m796a(applicationInfo));
    }

    /* renamed from: e */
    private Map<String, String> m812e() {
        Map<String, String> hashMap = new HashMap(0);
        ApplicationInfo applicationInfo = this.f775b.getApplicationInfo();
        if (applicationInfo == null) {
            C0562j.m759c("SharedDataStore - unable to load shared data. no app info");
            return hashMap;
        }
        File a;
        Map<String, String> b;
        if (C0558g.m686b(this.f775b)) {
            a = m798a(m811d());
        } else {
            a = m807b(applicationInfo);
        }
        File file = a;
        for (ApplicationInfo applicationInfo2 : m814g()) {
            a = m807b(applicationInfo2);
            if (!a.exists() || a.lastModified() <= file.lastModified()) {
                a = file;
            }
            file = a;
        }
        if (file.exists()) {
            b = m808b(file);
        } else {
            C0562j.m756b("no data store found");
            b = hashMap;
        }
        return b;
    }

    /* renamed from: a */
    private byte[] m805a(String str, Cipher cipher) {
        CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(str.getBytes()), cipher);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = cipherInputStream.read();
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(read);
            } catch (Throwable e) {
                C0562j.m760c("unable to encrypt", e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private Cipher m801a(byte[] bArr) {
        return m800a(2, bArr);
    }

    /* renamed from: f */
    private Cipher m813f() {
        return m800a(1, SecureRandom.getSeed(16));
    }

    /* renamed from: a */
    private Cipher m800a(int i, byte[] bArr) {
        if (this.f774a == null) {
            throw new IllegalStateException("no secret key.");
        }
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(i, this.f774a, ivParameterSpec);
            return instance;
        } catch (Throwable e) {
            C0562j.m760c("unable to encrypt json", e);
            return null;
        }
    }

    @TargetApi(9)
    /* renamed from: a */
    private void m803a(File file, byte[] bArr, byte[] bArr2) {
        C0562j.m756b("commiting to: " + file);
        try {
            File createTempFile = File.createTempFile(this.f776c, "tmp", file);
            if (m809b(createTempFile, bArr, bArr2)) {
                if (C0542a.m606a(9)) {
                    createTempFile.setWritable(true, false);
                    createTempFile.setReadable(true, false);
                }
                createTempFile.renameTo(m798a(file));
            }
        } catch (IOException e) {
            C0562j.m759c("SharedData Store - error creating temporary file in: " + file + " : " + e);
        }
    }

    /* renamed from: b */
    private boolean m809b(File file, byte[] bArr, byte[] bArr2) {
        Throwable e;
        Throwable th;
        Closeable bufferedOutputStream;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            try {
                bufferedOutputStream.write(bArr2);
                bufferedOutputStream.write(bArr);
                bufferedOutputStream.flush();
                C0558g.m674a(bufferedOutputStream);
                return true;
            } catch (IOException e2) {
                e = e2;
                try {
                    C0562j.m760c("error saving shared data store", e);
                    C0558g.m674a(bufferedOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    C0558g.m674a(bufferedOutputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedOutputStream = null;
            C0562j.m760c("error saving shared data store", e);
            C0558g.m674a(bufferedOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
            C0558g.m674a(bufferedOutputStream);
            throw th;
        }
    }

    /* renamed from: b */
    private Map<String, String> m808b(File file) {
        Closeable bufferedInputStream;
        Throwable e;
        Closeable closeable = null;
        C0562j.m756b("read and decrypt data store: " + file);
        HashMap hashMap = new HashMap();
        Closeable byteArrayOutputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                byte[] bArr = new byte[16];
                if (bufferedInputStream.read(bArr, 0, 16) != 16) {
                    C0562j.m759c("error unable to read initialization vector");
                    C0558g.m674a(null);
                    C0558g.m674a(bufferedInputStream);
                    return hashMap;
                }
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    CipherInputStream cipherInputStream = new CipherInputStream(bufferedInputStream, m801a(bArr));
                    while (true) {
                        int read = cipherInputStream.read();
                        if (read < 0) {
                            break;
                        }
                        byteArrayOutputStream.write(read);
                    }
                    C0558g.m674a(byteArrayOutputStream);
                    C0558g.m674a(bufferedInputStream);
                    JSONObject c = C0561i.m749c(byteArrayOutputStream.toString());
                    if (c != null) {
                        Iterator keys = c.keys();
                        while (keys.hasNext()) {
                            String str = (String) keys.next();
                            hashMap.put(str, C0561i.m748c(c, str));
                        }
                    }
                    return hashMap;
                } catch (IOException e2) {
                    e = e2;
                    closeable = byteArrayOutputStream;
                    byteArrayOutputStream = bufferedInputStream;
                    try {
                        C0562j.m760c("error reading datastore", e);
                        C0558g.m674a(closeable);
                        C0558g.m674a(byteArrayOutputStream);
                        return hashMap;
                    } catch (Throwable th) {
                        e = th;
                        bufferedInputStream = byteArrayOutputStream;
                        C0558g.m674a(closeable);
                        C0558g.m674a(bufferedInputStream);
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    closeable = byteArrayOutputStream;
                    C0558g.m674a(closeable);
                    C0558g.m674a(bufferedInputStream);
                    throw e;
                }
            } catch (IOException e3) {
                e = e3;
                byteArrayOutputStream = bufferedInputStream;
                C0562j.m760c("error reading datastore", e);
                C0558g.m674a(closeable);
                C0558g.m674a(byteArrayOutputStream);
                return hashMap;
            } catch (Throwable th3) {
                e = th3;
                C0558g.m674a(closeable);
                C0558g.m674a(bufferedInputStream);
                throw e;
            }
        } catch (IOException e4) {
            e = e4;
            byteArrayOutputStream = null;
            C0562j.m760c("error reading datastore", e);
            C0558g.m674a(closeable);
            C0558g.m674a(byteArrayOutputStream);
            return hashMap;
        } catch (Throwable th4) {
            e = th4;
            bufferedInputStream = null;
            C0558g.m674a(closeable);
            C0558g.m674a(bufferedInputStream);
            throw e;
        }
    }

    /* renamed from: a */
    private SharedPreferences m793a(Context context) {
        Cursor query;
        SharedPreferences sharedPreferences = context.getSharedPreferences("kongregate_shared_secret", 0);
        String string = sharedPreferences.getString(LoginActivity.EXTRA_SECRET, null);
        String string2 = sharedPreferences.getString("file_postfix", null);
        if (StringUtils.m580a((CharSequence) string) || StringUtils.m580a((CharSequence) string2)) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && NativeAPI.m351g().mo1254d()) {
                C0562j.m756b("check providers for secret");
                CharSequence charSequence = string;
                CharSequence charSequence2 = string2;
                for (PackageInfo packageInfo : packageManager.getInstalledPackages(8)) {
                    CharSequence charSequence3;
                    ProviderInfo[] providerInfoArr = packageInfo.providers;
                    if (providerInfoArr != null) {
                        int length = providerInfoArr.length;
                        int i = 0;
                        CharSequence charSequence4 = charSequence2;
                        CharSequence charSequence5 = charSequence;
                        while (i < length) {
                            Object obj;
                            ProviderInfo providerInfo = providerInfoArr[i];
                            if ("com.kongregate.permission.ReadSharedData2".equals(providerInfo.readPermission)) {
                                C0562j.m756b("checking authority: " + providerInfo.authority);
                                try {
                                    query = context.getContentResolver().query(Uri.parse("content://" + providerInfo.authority + "/" + "SharedSecret"), null, null, null, null);
                                } catch (SecurityException e) {
                                    C0562j.m759c("Permission denied");
                                    query = null;
                                }
                                if (query != null) {
                                    try {
                                        if (query.moveToFirst()) {
                                            charSequence2 = query.getString(query.getColumnIndex(LoginActivity.EXTRA_SECRET));
                                            string2 = query.getString(query.getColumnIndex("file_postfix"));
                                        } else {
                                            charSequence3 = charSequence4;
                                            charSequence2 = charSequence5;
                                        }
                                        if (!query.isClosed()) {
                                            query.close();
                                        }
                                        if (!(StringUtils.m580a(charSequence2) || StringUtils.m580a(charSequence2))) {
                                            return m794a(sharedPreferences, (String) charSequence2, string2);
                                        }
                                        i++;
                                        obj = string2;
                                        charSequence5 = charSequence2;
                                    } catch (Throwable th) {
                                        if (!query.isClosed()) {
                                            query.close();
                                        }
                                    }
                                }
                            }
                            string2 = charSequence4;
                            charSequence2 = charSequence5;
                            i++;
                            obj = string2;
                            charSequence5 = charSequence2;
                        }
                        charSequence3 = charSequence4;
                        charSequence2 = charSequence5;
                    } else {
                        charSequence3 = charSequence2;
                        charSequence2 = charSequence;
                    }
                    charSequence = charSequence2;
                    charSequence2 = charSequence3;
                }
                string2 = charSequence2;
                string = charSequence;
            } else if (packageManager == null) {
                C0562j.m761d("PackageManager not present");
            }
            if (StringUtils.m580a((CharSequence) string) || StringUtils.m580a((CharSequence) r0)) {
                try {
                    KeyGenerator instance = KeyGenerator.getInstance("AES");
                    instance.init(128);
                    string = Base64.encodeToString(instance.generateKey().getEncoded(), 0);
                    string2 = Integer.toString(new Random().nextInt(Integer.MAX_VALUE)) + ".shared";
                    C0562j.m756b("generated a secret");
                } catch (NoSuchAlgorithmException e2) {
                    C0562j.m761d("unable to find or generate key");
                    return sharedPreferences;
                }
            }
            return m794a(sharedPreferences, string, string2);
        }
        C0562j.m756b("found secret in prefs");
        return sharedPreferences;
    }

    /* renamed from: a */
    private SharedPreferences m794a(SharedPreferences sharedPreferences, String str, String str2) {
        sharedPreferences.edit().putString(LoginActivity.EXTRA_SECRET, str).putString("file_postfix", str2).apply();
        return sharedPreferences;
    }

    /* renamed from: g */
    private List<ApplicationInfo> m814g() {
        PackageManager packageManager = this.f775b.getPackageManager();
        List<ApplicationInfo> linkedList = new LinkedList();
        if (!NativeAPI.m351g().mo1254d()) {
            linkedList.add(this.f775b.getApplicationInfo());
            return linkedList;
        } else if (packageManager == null) {
            C0562j.m759c("package manager not found. unable to search for kongregate apps");
            return linkedList;
        } else {
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(8)) {
                ProviderInfo[] providerInfoArr = packageInfo.providers;
                if (providerInfoArr != null) {
                    for (ProviderInfo providerInfo : providerInfoArr) {
                        if ("com.kongregate.permission.ReadSharedData2".equals(providerInfo.readPermission)) {
                            linkedList.add(packageInfo.applicationInfo);
                        }
                    }
                }
            }
            return linkedList;
        }
    }
}
