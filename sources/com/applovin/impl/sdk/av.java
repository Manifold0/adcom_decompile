package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.nearby.messages.Strategy;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class av {
    /* renamed from: a */
    private final String f2076a = "FileManager";
    /* renamed from: b */
    private final AppLovinSdkImpl f2077b;
    /* renamed from: c */
    private final AppLovinLogger f2078c;
    /* renamed from: d */
    private final Object f2079d;

    av(AppLovinSdk appLovinSdk) {
        this.f2077b = (AppLovinSdkImpl) appLovinSdk;
        this.f2078c = appLovinSdk.getLogger();
        this.f2079d = new Object();
    }

    /* renamed from: a */
    private long m2285a(long j) {
        return j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    /* renamed from: a */
    private void m2286a(long j, Context context) {
        long c = (long) m2291c();
        if (c == -1) {
            this.f2078c.mo4172d("FileManager", "Cache has no maximum size set; skipping drop...");
        } else if (m2285a(j) > c) {
            this.f2078c.mo4172d("FileManager", "Cache has exceeded maximum size; dropping...");
            m2294g(context);
            this.f2077b.m2139a().m2310a("cache_drop_count");
        } else {
            this.f2078c.mo4172d("FileManager", "Cache is present but under size limit; not dropping...");
        }
    }

    /* renamed from: a */
    private boolean m2287a() {
        return ((Boolean) this.f2077b.get(ea.aY)).booleanValue();
    }

    /* renamed from: b */
    private long m2288b() {
        long longValue = ((Long) this.f2077b.get(ea.aZ)).longValue();
        return (longValue < 0 || !m2287a()) ? -1 : longValue;
    }

    /* renamed from: b */
    private boolean m2289b(ByteArrayOutputStream byteArrayOutputStream, File file) {
        boolean z;
        Throwable e;
        this.f2078c.mo4172d("FileManager", "Writing resource to filesystem: " + file.getName());
        FileOutputStream fileOutputStream = null;
        synchronized (this.f2079d) {
            FileOutputStream fileOutputStream2;
            try {
                fileOutputStream2 = new FileOutputStream(file);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream2);
                    z = true;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    try {
                        this.f2078c.mo4174e("FileManager", "Unable to write data to file.", e);
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Exception e4) {
                                z = false;
                            }
                        }
                        z = false;
                        return z;
                    } catch (Throwable th) {
                        e = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    fileOutputStream = fileOutputStream2;
                    try {
                        this.f2078c.mo4174e("FileManager", "Unknown failure to write file.", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e6) {
                                z = false;
                            }
                        }
                        z = false;
                        return z;
                    } catch (Throwable th3) {
                        e = th3;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw e;
                    }
                }
            } catch (IOException e7) {
                e = e7;
                fileOutputStream2 = null;
                this.f2078c.mo4174e("FileManager", "Unable to write data to file.", e);
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                z = false;
                return z;
            } catch (Throwable th4) {
                e = th4;
                this.f2078c.mo4174e("FileManager", "Unknown failure to write file.", e);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                z = false;
                return z;
            }
        }
        return z;
    }

    /* renamed from: b */
    private boolean m2290b(File file) {
        boolean delete;
        this.f2078c.mo4172d("FileManager", "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.f2079d) {
            try {
                delete = file.delete();
            } catch (Throwable e) {
                this.f2078c.mo4174e("FileManager", "Failed to remove file " + file.getName() + " from filesystem!", e);
                delete = false;
            }
        }
        return delete;
    }

    /* renamed from: c */
    private int m2291c() {
        int intValue = ((Integer) this.f2077b.get(ea.ba)).intValue();
        return (intValue < 0 || !m2287a()) ? -1 : intValue;
    }

    /* renamed from: e */
    private File m2292e(Context context) {
        return m2300a(context) ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
    }

    /* renamed from: f */
    private long m2293f(Context context) {
        long j = 0;
        long b = m2288b();
        Object obj = b != -1 ? 1 : null;
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (this.f2079d) {
            for (File file : m2305b(context)) {
                Object obj2 = null;
                if (obj != null && toSeconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) > b) {
                    this.f2078c.mo4172d("FileManager", "File " + file.getName() + " has expired, removing...");
                    m2290b(file);
                    obj2 = 1;
                }
                if (obj2 != null) {
                    this.f2077b.m2139a().m2310a("cached_files_expired");
                } else {
                    j += file.length();
                }
            }
        }
        return j;
    }

    /* renamed from: g */
    private void m2294g(Context context) {
        synchronized (this.f2079d) {
            for (File b : m2305b(context)) {
                m2290b(b);
            }
        }
    }

    /* renamed from: a */
    ByteArrayOutputStream m2295a(File file) {
        FileInputStream fileInputStream;
        Object e;
        Throwable th;
        Throwable th2;
        if (file == null) {
            return null;
        }
        this.f2078c.mo4172d("FileManager", "Reading resource from filesystem: " + file.getName());
        synchronized (this.f2079d) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr, 0, bArr.length);
                        if (read < 0) {
                            break;
                        }
                        try {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } catch (Exception e2) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e3) {
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                }
                            }
                            return null;
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    return byteArrayOutputStream;
                } catch (FileNotFoundException e6) {
                    e = e6;
                    try {
                        this.f2078c.mo4175i("FileManager", "File not found. " + e);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e7) {
                            }
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e8) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e9) {
                    e = e9;
                    this.f2078c.mo4172d("FileManager", "Failed to read file: " + file.getName() + e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e10) {
                        }
                    }
                    return null;
                } catch (Throwable th4) {
                    th2 = th4;
                    this.f2078c.mo4174e("FileManager", "Unknown failure to read file.", th2);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e11) {
                        }
                    }
                    return null;
                }
            } catch (FileNotFoundException e12) {
                e = e12;
                fileInputStream = null;
                this.f2078c.mo4175i("FileManager", "File not found. " + e);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (IOException e13) {
                e = e13;
                fileInputStream = null;
                this.f2078c.mo4172d("FileManager", "Failed to read file: " + file.getName() + e);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return null;
            } catch (Throwable th22) {
                fileInputStream = null;
                th = th22;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
    }

    /* renamed from: a */
    ByteArrayOutputStream m2296a(String str, List<String> list, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        HttpURLConnection httpURLConnection;
        Throwable e;
        HttpURLConnection httpURLConnection2;
        ByteArrayOutputStream byteArrayOutputStream2;
        InputStream inputStream;
        Throwable th;
        HttpURLConnection httpURLConnection3;
        InputStream inputStream2 = null;
        if (!z || gd.m2948a(str, (List) list)) {
            if (((Boolean) this.f2077b.get(ea.bS)).booleanValue() && !str.contains("https://")) {
                this.f2077b.getLogger().mo4178w("FileManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
                str = str.replace("http://", "https://");
            }
            this.f2078c.mo4172d("FileManager", "Loading " + str + "...");
            InputStream inputStream3 = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                } catch (IOException e2) {
                    e = e2;
                    httpURLConnection2 = null;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    inputStream = null;
                    try {
                        this.f2078c.mo4174e("FileManager", "Error loading " + str, e);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception e3) {
                            }
                        }
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (Exception e5) {
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        e = th2;
                        inputStream2 = inputStream;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Exception e6) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e7) {
                            }
                        }
                        if (httpURLConnection2 != null) {
                            try {
                                httpURLConnection2.disconnect();
                            } catch (Exception e8) {
                            }
                        }
                        throw e;
                    }
                } catch (Throwable th3) {
                    e = th3;
                    httpURLConnection2 = null;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw e;
                }
                try {
                    httpURLConnection.setConnectTimeout(((Integer) this.f2077b.get(ea.f2426w)).intValue());
                    httpURLConnection.setReadTimeout(((Integer) this.f2077b.get(ea.f2428y)).intValue());
                    httpURLConnection.setDefaultUseCaches(true);
                    httpURLConnection.setUseCaches(true);
                    httpURLConnection.setAllowUserInteraction(false);
                    httpURLConnection.setInstanceFollowRedirects(true);
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode < 200 || responseCode >= Strategy.TTL_SECONDS_DEFAULT) {
                        if (null != null) {
                            try {
                                inputStream3.close();
                            } catch (Exception e9) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e10) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e11) {
                            }
                        }
                        return null;
                    }
                    inputStream3 = httpURLConnection.getInputStream();
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = inputStream3.read(bArr, 0, bArr.length);
                            if (read < 0) {
                                break;
                            }
                            try {
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (Exception e12) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Exception e13) {
                                }
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (Exception e14) {
                                    }
                                }
                                if (byteArrayOutputStream != null) {
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception e15) {
                                    }
                                }
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Exception e16) {
                                    }
                                }
                                return null;
                            }
                        }
                        this.f2078c.mo4172d("FileManager", "Loaded resource at " + str);
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (Exception e17) {
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e18) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception e19) {
                            }
                        }
                        return byteArrayOutputStream;
                    } catch (Throwable e20) {
                        th = e20;
                        httpURLConnection2 = httpURLConnection;
                        e = th;
                        InputStream inputStream4 = inputStream3;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        inputStream = inputStream4;
                        this.f2078c.mo4174e("FileManager", "Error loading " + str, e);
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return null;
                    } catch (Throwable e202) {
                        inputStream2 = inputStream3;
                        httpURLConnection3 = httpURLConnection;
                        e = e202;
                        httpURLConnection2 = httpURLConnection3;
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw e;
                    }
                } catch (Throwable e2022) {
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    inputStream = null;
                    httpURLConnection3 = httpURLConnection;
                    e = e2022;
                    httpURLConnection2 = httpURLConnection3;
                    this.f2078c.mo4174e("FileManager", "Error loading " + str, e);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return null;
                } catch (Throwable e20222) {
                    th = e20222;
                    httpURLConnection2 = httpURLConnection;
                    e = th;
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw e;
                }
            } catch (IOException e21) {
                e = e21;
                httpURLConnection2 = null;
                inputStream = null;
                byteArrayOutputStream2 = null;
                this.f2078c.mo4174e("FileManager", "Error loading " + str, e);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return null;
            } catch (Throwable th4) {
                e = th4;
                httpURLConnection2 = null;
                byteArrayOutputStream = null;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw e;
            }
        }
        this.f2078c.mo4172d("FileManager", "Domain is not whitelisted, skipping precache for url: " + str);
        return null;
    }

    /* renamed from: a */
    File m2297a(String str, Context context, boolean z) {
        if (AppLovinSdkUtils.isValidString(str)) {
            this.f2078c.mo4172d("FileManager", "Looking up cached resource: " + str);
            if (!m2300a(context) && !z) {
                return null;
            }
            File file;
            if (str.contains("icon")) {
                str = str.replace("/", "_").replace(".", "_");
            }
            synchronized (this.f2079d) {
                File e = m2292e(context);
                file = new File(e, str);
                try {
                    e.mkdirs();
                } catch (Exception e2) {
                    return null;
                }
            }
            return file;
        }
        this.f2077b.getLogger().mo4172d("FileManager", "Nothing to look up, skipping...");
        return null;
    }

    /* renamed from: a */
    String m2298a(Context context, String str, String str2, List<String> list, boolean z, C1298z c1298z) throws MalformedURLException {
        return m2299a(context, str, str2, list, z, false, c1298z);
    }

    /* renamed from: a */
    String m2299a(Context context, String str, String str2, List<String> list, boolean z, boolean z2, C1298z c1298z) throws MalformedURLException {
        if (AppLovinSdkUtils.isValidString(str)) {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            String str3 = (AppLovinSdkUtils.isValidString(lastPathSegment) && AppLovinSdkUtils.isValidString(str2)) ? str2 + lastPathSegment : lastPathSegment;
            File a = m2297a(str3, context, false);
            if (!m2303a(a, str, list, z, c1298z)) {
                return null;
            }
            this.f2078c.mo4172d("FileManager", "Caching succeeded for file " + str3);
            return z2 ? Uri.fromFile(a).toString() : str3;
        } else {
            this.f2077b.getLogger().mo4172d("FileManager", "Nothing to cache, skipping...");
            return null;
        }
    }

    /* renamed from: a */
    protected boolean m2300a(Context context) {
        if (ab.m2199a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (ab.m2206f() && !((Boolean) this.f2077b.get(ea.cc)).booleanValue()) {
            return true;
        }
        this.f2077b.getLogger().mo4178w("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }

    /* renamed from: a */
    boolean m2301a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        if (file == null) {
            return false;
        }
        this.f2078c.mo4172d("FileManager", "Caching " + file.getAbsolutePath() + "...");
        if (byteArrayOutputStream == null || byteArrayOutputStream.size() <= 0) {
            this.f2078c.mo4178w("FileManager", "No data for " + file.getAbsolutePath());
            return false;
        } else if (m2289b(byteArrayOutputStream, file)) {
            this.f2078c.mo4172d("FileManager", "Caching completed for " + file);
            return true;
        } else {
            this.f2078c.mo4173e("FileManager", "Unable to cache " + file.getAbsolutePath());
            return false;
        }
    }

    /* renamed from: a */
    boolean m2302a(File file, String str, List<String> list, C1298z c1298z) {
        return m2303a(file, str, list, true, c1298z);
    }

    /* renamed from: a */
    boolean m2303a(File file, String str, List<String> list, boolean z, C1298z c1298z) {
        if (file == null || !file.exists() || file.isDirectory()) {
            ByteArrayOutputStream a = m2296a(str, (List) list, z);
            if (c1298z != null) {
                c1298z.m3091a((long) a.size());
            }
            return m2301a(a, file);
        }
        this.f2077b.getLogger().mo4172d("FileManager", "File exists for " + str);
        if (c1298z != null) {
            c1298z.m3093b(file.length());
        }
        return true;
    }

    /* renamed from: a */
    public boolean m2304a(String str, Context context) {
        boolean b;
        synchronized (this.f2079d) {
            b = m2306b(str, context, false);
        }
        return b;
    }

    /* renamed from: b */
    public List<File> m2305b(Context context) {
        File e = m2292e(context);
        if (!e.isDirectory()) {
            return new ArrayList(0);
        }
        List<File> asList;
        synchronized (this.f2079d) {
            asList = Arrays.asList(e.listFiles());
        }
        return asList;
    }

    /* renamed from: b */
    boolean m2306b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.f2079d) {
            File a = m2297a(str, context, z);
            z2 = (a == null || !a.exists() || a.isDirectory()) ? false : true;
        }
        return z2;
    }

    /* renamed from: c */
    boolean m2307c(Context context) {
        if (((Boolean) this.f2077b.get(ea.cd)).booleanValue()) {
            try {
                m2297a(".nomedia", context, false);
                File file = new File(m2292e(context), ".nomedia");
                if (file != null) {
                    if (file.exists()) {
                        return true;
                    }
                    this.f2077b.getLogger().mo4172d("FileManager", "Dropping .nomedia file at " + file.getAbsolutePath());
                    return file.createNewFile();
                }
            } catch (Throwable e) {
                this.f2077b.getLogger().mo4179w("FileManager", "Failed to create nomedia file", e);
            }
        }
        return false;
    }

    /* renamed from: d */
    void m2308d(Context context) {
        try {
            if (!m2287a()) {
                return;
            }
            if (this.f2077b.m2144e()) {
                this.f2078c.mo4172d("FileManager", "Compacting cache...");
                synchronized (this.f2079d) {
                    m2286a(m2293f(context), context);
                }
                return;
            }
            this.f2078c.mo4173e("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
        } catch (Throwable e) {
            this.f2078c.mo4174e("FileManager", "Caught exception while compacting cache!", e);
            this.f2077b.getSettingsManager().m2668a(ea.aY, Boolean.valueOf(false));
            this.f2077b.getSettingsManager().m2667a();
        }
    }
}
