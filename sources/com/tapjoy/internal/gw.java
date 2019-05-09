package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class gw {
    /* renamed from: a */
    public static final gw f7998a = new gw();
    /* renamed from: b */
    public Context f7999b;
    /* renamed from: c */
    public SharedPreferences f8000c = null;
    /* renamed from: d */
    public SharedPreferences f8001d = null;
    /* renamed from: e */
    ExecutorService f8002e = new ThreadPoolExecutor(0, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* renamed from: f */
    private File f8003f;

    /* renamed from: com.tapjoy.internal.gw$1 */
    class C29621 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ gw f7992a;

        C29621(gw gwVar) {
            this.f7992a = gwVar;
        }

        public final void run() {
            if (this.f7992a.f7999b != null) {
                this.f7992a.m8003c();
            }
        }
    }

    /* renamed from: com.tapjoy.internal.gw$2 */
    class C29632 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ URL f7993a;
        /* renamed from: b */
        final /* synthetic */ InputStream f7994b;
        /* renamed from: c */
        final /* synthetic */ long f7995c;
        /* renamed from: d */
        final /* synthetic */ gw f7996d;

        C29632(gw gwVar, URL url, InputStream inputStream, long j) {
            this.f7996d = gwVar;
            this.f7993a = url;
            this.f7994b = inputStream;
            this.f7995c = j;
        }

        public final void run() {
            long j = 604800;
            try {
                File createTempFile = File.createTempFile("tj_", null, this.f7996d.m8007b());
                if (createTempFile == null) {
                    new Object[1][0] = this.f7993a;
                    return;
                }
                OutputStream fileOutputStream = new FileOutputStream(createTempFile);
                try {
                    da.m7354a(this.f7994b, fileOutputStream);
                    fileOutputStream.close();
                    long j2 = this.f7995c;
                    if (j2 <= 604800) {
                        j = j2;
                    }
                    j = (j * 1000) + C2999y.m8233b();
                    synchronized (this.f7996d) {
                        String b = this.f7996d.m8008b(this.f7993a);
                        if (createTempFile.renameTo(this.f7996d.m8004a(b))) {
                            this.f7996d.f8000c.edit().putLong(b, j).commit();
                            Object[] objArr = new Object[]{createTempFile, b, this.f7993a};
                        }
                    }
                } catch (IOException e) {
                    new Object[1][0] = this.f7993a;
                }
            } catch (FileNotFoundException e2) {
                new Object[1][0] = this.f7993a;
            } catch (IOException e3) {
                new Object[1][0] = this.f7993a;
            }
        }
    }

    /* renamed from: com.tapjoy.internal.gw$3 */
    class C29643 implements Comparator {
        /* renamed from: a */
        final /* synthetic */ gw f7997a;

        C29643(gw gwVar) {
            this.f7997a = gwVar;
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return ((Long) ((Entry) obj).getValue()).compareTo((Long) ((Entry) obj2).getValue());
        }
    }

    private gw() {
    }

    /* renamed from: a */
    public static boolean m8000a(long j) {
        return j >= 3600;
    }

    /* renamed from: a */
    public final void m8006a() {
        this.f8002e.submit(new C29621(this));
    }

    /* renamed from: a */
    public final File m8005a(URL url) {
        if (this.f7999b == null) {
            return null;
        }
        synchronized (this) {
            String b = m8008b(url);
            File a = m8004a(b);
            if (a.exists()) {
                long b2 = C2999y.m8233b();
                long j = this.f8000c.getLong(b, 0);
                if (j >= b2) {
                    Object[] objArr = new Object[]{b, url};
                    return a;
                }
                Object[] objArr2 = new Object[]{b, Long.valueOf(b2), Long.valueOf(j)};
                if (j != 0) {
                    this.f8000c.edit().remove(b).commit();
                    this.f8001d.edit().remove(b).commit();
                }
                a.delete();
                return null;
            }
            return null;
        }
    }

    /* renamed from: b */
    final synchronized String m8008b(URL url) {
        String str;
        String url2 = url.toString();
        str = new String(ii.m8189a(cm.m7332a(url2.getBytes())));
        String string = this.f8001d.getString(str, null);
        if (string == null) {
            this.f8001d.edit().putString(str, url2).commit();
        } else if (!string.equals(url2)) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                string = str + "_" + i2;
                String string2 = this.f8001d.getString(string, null);
                if (string2 == null || string2.equals(url2)) {
                    this.f8001d.edit().putString(string, url2).commit();
                } else {
                    i = i2;
                }
            }
            this.f8001d.edit().putString(string, url2).commit();
            str = string;
        }
        return str;
    }

    /* renamed from: b */
    final File m8007b() {
        File file = this.f8003f;
        if (file == null) {
            file = new File(this.f7999b.getCacheDir(), "tapjoy_mm_cache");
            this.f8003f = file;
        }
        if (!file.isDirectory()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /* renamed from: a */
    final File m8004a(String str) {
        return new File(m8007b(), str);
    }

    /* renamed from: c */
    private synchronized void m8003c() {
        Object obj = 1;
        synchronized (this) {
            int i;
            long b = C2999y.m8233b();
            File[] listFiles = m8007b().listFiles();
            HashMap hashMap = new HashMap();
            if (listFiles != null) {
                for (File file : listFiles) {
                    hashMap.put(file.getName(), file);
                }
            }
            Editor edit = this.f8000c.edit();
            Editor edit2 = this.f8001d.edit();
            Map hashMap2 = new HashMap(this.f8000c.getAll());
            Map hashMap3 = new HashMap(this.f8001d.getAll());
            Iterator it = hashMap2.entrySet().iterator();
            Object obj2 = null;
            while (it.hasNext()) {
                Object obj3;
                String str = (String) ((Entry) it.next()).getKey();
                if (hashMap3.containsKey(str)) {
                    obj3 = obj2;
                } else {
                    it.remove();
                    edit.remove(str);
                    new Object[1][0] = str;
                    obj3 = 1;
                }
                obj2 = obj3;
            }
            Iterator it2 = hashMap3.entrySet().iterator();
            while (it2.hasNext()) {
                str = (String) ((Entry) it2.next()).getKey();
                if (hashMap2.containsKey(str)) {
                    obj3 = obj2;
                } else {
                    it2.remove();
                    edit2.remove(str);
                    new Object[1][0] = str;
                    obj3 = 1;
                }
                obj2 = obj3;
            }
            it = hashMap2.entrySet().iterator();
            Object obj4 = obj2;
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                String str2 = (String) entry.getKey();
                if (((Long) entry.getValue()).longValue() < b) {
                    it.remove();
                    edit.remove(str2);
                    edit2.remove(str2);
                    obj4 = 1;
                } else {
                    hashMap.remove(str2);
                }
            }
            for (Entry entry2 : hashMap.entrySet()) {
                File file2 = (File) entry2.getValue();
                file2.delete();
                new Object[1][0] = file2;
            }
            if (hashMap2.size() > 30) {
                int size = hashMap2.size() - 30;
                List linkedList = new LinkedList(hashMap2.entrySet());
                Collections.sort(linkedList, new C29643(this));
                Iterator it3 = linkedList.iterator();
                for (i = 0; i < size && it3.hasNext(); i++) {
                    entry2 = (Entry) it3.next();
                    str2 = (String) entry2.getKey();
                    Long l = (Long) entry2.getValue();
                    edit.remove(str2);
                    edit2.remove(str2);
                    m8004a(str2).delete();
                    Object[] objArr = new Object[]{r1, l};
                }
            } else {
                obj = obj4;
            }
            if (obj != null) {
                edit.commit();
                edit2.commit();
            }
        }
    }
}
