package com.chartboost.sdk.Libraries;

import android.content.Context;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.bh;
import com.kongregate.p000o.p003a.C0578a;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.f */
public class C1378f {
    /* renamed from: a */
    public final File f2690a;
    /* renamed from: b */
    public final File f2691b;
    /* renamed from: c */
    private final AtomicReference<C1390e> f2692c;
    /* renamed from: d */
    private final C1379g f2693d;
    /* renamed from: e */
    private final AtomicReference<C1379g> f2694e = new AtomicReference();
    /* renamed from: f */
    private C1454s f2695f;

    public C1378f(C1454s c1454s, Context context, AtomicReference<C1390e> atomicReference) {
        File b;
        this.f2695f = c1454s;
        this.f2693d = new C1379g(context.getCacheDir());
        this.f2692c = atomicReference;
        try {
            b = c1454s.m3632b();
            if (b != null) {
                this.f2694e.set(new C1379g(b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f2691b = new File(this.f2693d.f2696a, "track");
        this.f2690a = new File(this.f2693d.f2696a, C0578a.f789b);
        for (C1379g c1379g : new C1379g[]{this.f2693d, (C1379g) this.f2694e.get()}) {
            try {
                Object obj;
                if (c1379g == this.f2693d) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (c1379g != null && (obj != null || m3133a())) {
                    long currentTimeMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis((long) ((C1390e) atomicReference.get()).f2815w);
                    b = new File(c1379g.f2696a, "templates");
                    if (b.exists()) {
                        File[] listFiles = b.listFiles();
                        if (listFiles != null) {
                            for (File file : listFiles) {
                                if (file.isDirectory()) {
                                    File[] listFiles2 = file.listFiles();
                                    if (listFiles2 != null) {
                                        for (File file2 : listFiles2) {
                                            if ((obj != null || file2.lastModified() < currentTimeMillis) && !file2.delete()) {
                                                CBLogging.m3099b("FileCache", "Unable to delete " + file2.getPath());
                                            }
                                        }
                                    }
                                    File[] listFiles3 = file.listFiles();
                                    if (!(listFiles3 == null || listFiles3.length != 0 || file.delete())) {
                                        CBLogging.m3099b("FileCache", "Unable to delete " + file.getPath());
                                    }
                                }
                            }
                        }
                    }
                    b = new File(c1379g.f2696a, ".adId");
                    if (b.exists() && ((obj != null || b.lastModified() < currentTimeMillis) && !b.delete())) {
                        CBLogging.m3099b("FileCache", "Unable to delete " + b.getPath());
                    }
                }
            } catch (Throwable e2) {
                CBLogging.m3098a("FileCache", "Exception while cleaning up templates directory at " + c1379g.f2701f.getPath(), e2);
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public synchronized byte[] m3134a(File file) {
        byte[] bArr = null;
        synchronized (this) {
            if (file != null) {
                try {
                    bArr = bh.m3524b(file);
                } catch (Exception e) {
                    CBLogging.m3098a("FileCache", "Error loading cache from disk", e);
                    C1391a.m3206a(getClass(), "readByteArrayFromDisk", e);
                }
            }
        }
        return bArr;
    }

    /* renamed from: a */
    public String m3132a(String str) {
        File file = new File(m3140d().f2702g, str);
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    /* renamed from: a */
    public boolean m3133a() {
        try {
            String c = this.f2695f.m3633c();
            if (!(c == null || !c.equals("mounted") || C1410i.f2937n)) {
                return true;
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "isExternalStorageAvailable", e);
        }
        CBLogging.m3104e("FileCache", "External Storage unavailable");
        return false;
    }

    /* renamed from: b */
    public boolean m3137b(String str) {
        if (m3140d().f2699d == null || str == null) {
            return false;
        }
        return new File(m3140d().f2699d, str).exists();
    }

    /* renamed from: b */
    public JSONArray m3136b() {
        JSONArray jSONArray = new JSONArray();
        String[] list = m3140d().f2702g.list();
        if (list != null) {
            for (String str : list) {
                if (!(str.equals(".nomedia") || str.endsWith(".tmp"))) {
                    jSONArray.put(str);
                }
            }
        }
        return jSONArray;
    }

    /* renamed from: c */
    public JSONObject m3138c() {
        JSONObject jSONObject = new JSONObject();
        try {
            File file = m3140d().f2696a;
            for (String str : ((C1390e) this.f2692c.get()).f2816x) {
                if (!str.equals("templates")) {
                    File file2 = new File(file, str);
                    JSONArray jSONArray = new JSONArray();
                    if (file2.exists()) {
                        String[] list = file2.list();
                        if (list != null) {
                            for (String str2 : list) {
                                if (!(str2.equals(".nomedia") || str2.endsWith(".tmp"))) {
                                    jSONArray.put(str2);
                                }
                            }
                        }
                    }
                    C1377e.m3131a(jSONObject, str, jSONArray);
                }
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "getWebViewCacheAssets", e);
        }
        return jSONObject;
    }

    /* renamed from: d */
    public C1379g m3140d() {
        if (m3133a()) {
            C1379g c1379g = (C1379g) this.f2694e.get();
            if (c1379g == null) {
                try {
                    File b = this.f2695f.m3632b();
                    if (b != null) {
                        this.f2694e.compareAndSet(null, new C1379g(b));
                        c1379g = (C1379g) this.f2694e.get();
                    }
                } catch (Exception e) {
                    C1391a.m3206a(getClass(), "currentLocations", e);
                }
            }
            if (c1379g != null) {
                return c1379g;
            }
        }
        return this.f2693d;
    }

    /* renamed from: b */
    public long m3135b(File file) {
        long j = 0;
        if (file != null) {
            try {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        while (i < listFiles.length) {
                            long b = m3135b(listFiles[i]) + j;
                            i++;
                            j = b;
                        }
                    }
                    return j;
                }
            } catch (Exception e) {
                C1391a.m3206a(C1378f.class, "getFolderSize", e);
            }
        }
        if (file != null) {
            j = file.length();
        }
        return j;
    }

    /* renamed from: e */
    public JSONObject m3141e() {
        JSONObject jSONObject = new JSONObject();
        C1379g c1379g = (C1379g) this.f2694e.get();
        if (c1379g != null) {
            C1377e.m3131a(jSONObject, ".chartboost-external-folder-size", Long.valueOf(m3135b(c1379g.f2696a)));
        }
        C1377e.m3131a(jSONObject, ".chartboost-internal-folder-size", Long.valueOf(m3135b(this.f2693d.f2696a)));
        File file = m3140d().f2696a;
        String[] list = file.list();
        if (list != null && list.length > 0) {
            for (String file2 : list) {
                File file3 = new File(file, file2);
                JSONObject jSONObject2 = new JSONObject();
                C1377e.m3131a(jSONObject2, file3.getName() + "-size", Long.valueOf(m3135b(file3)));
                String[] list2 = file3.list();
                if (list2 != null) {
                    C1377e.m3131a(jSONObject2, NewHtcHomeBadger.COUNT, Integer.valueOf(list2.length));
                }
                C1377e.m3131a(jSONObject, file3.getName(), jSONObject2);
            }
        }
        return jSONObject;
    }

    /* renamed from: c */
    public void m3139c(File file) {
        RandomAccessFile randomAccessFile;
        Throwable e;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile.seek(0);
                int read = randomAccessFile.read();
                randomAccessFile.seek(0);
                randomAccessFile.write(read);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    CBLogging.m3098a("FileCache", "File not found when attempting to touch", e);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable th) {
                    e = th;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw e;
                }
            } catch (IOException e6) {
                e = e6;
                CBLogging.m3098a("FileCache", "IOException when attempting to touch file", e);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e7) {
                    }
                }
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            randomAccessFile = null;
            CBLogging.m3098a("FileCache", "File not found when attempting to touch", e);
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (IOException e9) {
            e = e9;
            randomAccessFile = null;
            CBLogging.m3098a("FileCache", "IOException when attempting to touch file", e);
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
        } catch (Throwable th2) {
            e = th2;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw e;
        }
    }
}
