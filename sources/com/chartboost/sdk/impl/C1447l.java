package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.C1382i;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1387b;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Tracking.C1391a;
import com.google.android.gms.games.request.Requests;
import com.kongregate.p000o.p003a.C0578a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.chartboost.sdk.impl.l */
public class C1447l {
    /* renamed from: a */
    int f3296a = 1;
    /* renamed from: b */
    private final Executor f3297b;
    /* renamed from: c */
    private final ah f3298c;
    /* renamed from: d */
    private final ai f3299d;
    /* renamed from: e */
    private final AtomicReference<C1390e> f3300e;
    /* renamed from: f */
    private final C1382i f3301f;
    /* renamed from: g */
    private final C1391a f3302g;
    /* renamed from: h */
    private final C1378f f3303h;
    /* renamed from: i */
    private C1445k f3304i = null;
    /* renamed from: j */
    private final PriorityQueue<C1444j> f3305j;

    /* renamed from: com.chartboost.sdk.impl.l$1 */
    class C14461 implements Comparator<File> {
        /* renamed from: a */
        final /* synthetic */ C1447l f3295a;

        C14461(C1447l c1447l) {
            this.f3295a = c1447l;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m3595a((File) obj, (File) obj2);
        }

        /* renamed from: a */
        public int m3595a(File file, File file2) {
            return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
        }
    }

    public C1447l(Executor executor, C1378f c1378f, ah ahVar, ai aiVar, AtomicReference<C1390e> atomicReference, C1382i c1382i, C1391a c1391a) {
        this.f3297b = executor;
        this.f3303h = c1378f;
        this.f3298c = ahVar;
        this.f3299d = aiVar;
        this.f3300e = atomicReference;
        this.f3301f = c1382i;
        this.f3302g = c1391a;
        this.f3305j = new PriorityQueue();
    }

    /* renamed from: a */
    public synchronized void m3598a(int i, Map<String, C1387b> map, AtomicInteger atomicInteger, C1437h c1437h) {
        long b = this.f3301f.m3159b();
        AtomicInteger atomicInteger2 = new AtomicInteger();
        AtomicReference atomicReference = new AtomicReference(c1437h);
        for (C1387b c1387b : map.values()) {
            this.f3305j.add(new C1444j(this.f3301f, i, c1387b.f2749b, c1387b.f2750c, c1387b.f2748a, atomicInteger, atomicReference, b, atomicInteger2));
        }
        if (this.f3296a == 1 || this.f3296a == 2) {
            m3596d();
        }
    }

    /* renamed from: a */
    public synchronized void m3600a(AtomicInteger atomicInteger) {
        atomicInteger.set(-10000);
        switch (this.f3296a) {
            case 2:
                if ((this.f3304i.f3292a.f3287e == atomicInteger ? 1 : null) != null && this.f3304i.m3368b()) {
                    this.f3304i = null;
                    m3596d();
                    break;
                }
        }
    }

    /* renamed from: a */
    public synchronized void m3597a() {
        switch (this.f3296a) {
            case 1:
                CBLogging.m3097a("Downloader", "Change state to PAUSED");
                this.f3296a = 4;
                break;
            case 2:
                if (!this.f3304i.m3368b()) {
                    CBLogging.m3097a("Downloader", "Change state to PAUSING");
                    this.f3296a = 3;
                    break;
                }
                this.f3305j.add(this.f3304i.f3292a);
                this.f3304i = null;
                CBLogging.m3097a("Downloader", "Change state to PAUSED");
                this.f3296a = 4;
                break;
        }
    }

    /* renamed from: b */
    public synchronized void m3601b() {
        switch (this.f3296a) {
            case 3:
                CBLogging.m3097a("Downloader", "Change state to DOWNLOADING");
                this.f3296a = 2;
                break;
            case 4:
                CBLogging.m3097a("Downloader", "Change state to IDLE");
                this.f3296a = 1;
                m3596d();
                break;
        }
    }

    /* renamed from: a */
    synchronized void m3599a(C1445k c1445k, CBError cBError, ag agVar) {
        switch (this.f3296a) {
            case 2:
            case 3:
                if (c1445k == this.f3304i) {
                    C1444j c1444j = c1445k.f3292a;
                    this.f3304i = null;
                    long toMillis = TimeUnit.NANOSECONDS.toMillis(c1445k.g);
                    c1444j.f3288f.addAndGet((int) toMillis);
                    c1444j.m3590a(this.f3297b, cBError == null);
                    long toMillis2 = TimeUnit.NANOSECONDS.toMillis(c1445k.h);
                    long toMillis3 = TimeUnit.NANOSECONDS.toMillis(c1445k.i);
                    if (cBError == null) {
                        this.f3302g.m3212a(c1444j.f3285c, toMillis, toMillis2, toMillis3);
                        CBLogging.m3097a("Downloader", "Downloaded " + c1444j.f3285c);
                    } else {
                        String b = cBError.m3162b();
                        this.f3302g.m3214a(c1444j.f3285c, b, toMillis, toMillis2, toMillis3);
                        CBLogging.m3097a("Downloader", "Failed to download " + c1444j.f3285c + (agVar != null ? " Status code=" + agVar.f2995a : "") + (b != null ? " Error message=" + b : ""));
                    }
                    if (this.f3296a != 3) {
                        m3596d();
                        break;
                    }
                    CBLogging.m3097a("Downloader", "Change state to PAUSED");
                    this.f3296a = 4;
                    break;
                }
                break;
        }
    }

    /* renamed from: d */
    private void m3596d() {
        C1444j c1444j;
        if (this.f3304i != null) {
            c1444j = (C1444j) this.f3305j.peek();
            if (c1444j != null && this.f3304i.f3292a.f3283a > c1444j.f3283a && this.f3304i.m3368b()) {
                this.f3305j.add(this.f3304i.f3292a);
                this.f3304i = null;
            }
        }
        while (this.f3304i == null) {
            c1444j = (C1444j) this.f3305j.poll();
            if (c1444j == null) {
                break;
            } else if (c1444j.f3287e.get() > 0) {
                File file = new File(this.f3303h.m3140d().f2696a, c1444j.f3286d);
                if (file.exists() || file.mkdirs() || file.isDirectory()) {
                    File file2 = new File(file, c1444j.f3284b);
                    if (file2.exists()) {
                        this.f3303h.m3139c(file2);
                        c1444j.m3590a(this.f3297b, true);
                    } else {
                        this.f3304i = new C1445k(this, this.f3299d, c1444j, file2);
                        this.f3298c.m3371a(this.f3304i);
                        this.f3302g.m3213a(c1444j.f3285c, c1444j.f3284b);
                    }
                } else {
                    CBLogging.m3099b("Downloader", "Unable to create directory " + file.getPath());
                    c1444j.m3590a(this.f3297b, false);
                }
            }
        }
        if (this.f3304i != null) {
            if (this.f3296a != 2) {
                CBLogging.m3097a("Downloader", "Change state to DOWNLOADING");
                this.f3296a = 2;
            }
        } else if (this.f3296a != 1) {
            CBLogging.m3097a("Downloader", "Change state to IDLE");
            this.f3296a = 1;
        }
    }

    /* renamed from: c */
    public synchronized void m3602c() {
        if (this.f3296a == 1) {
            try {
                CBLogging.m3097a("Downloader", "########### Trimming the disk cache");
                File file = this.f3303h.m3140d().f2696a;
                List arrayList = new ArrayList();
                String[] list = file.list();
                if (list != null && list.length > 0) {
                    for (String str : list) {
                        if (!(str.equalsIgnoreCase(Requests.EXTRA_REQUESTS) || str.equalsIgnoreCase("track") || str.equalsIgnoreCase(C0578a.f789b) || str.equalsIgnoreCase("videoCompletionEvents") || str.contains("."))) {
                            arrayList.addAll(CBUtility.m3109a(new File(file, str), true));
                        }
                    }
                }
                File[] fileArr = new File[arrayList.size()];
                arrayList.toArray(fileArr);
                if (fileArr.length > 1) {
                    Arrays.sort(fileArr, new C14461(this));
                }
                if (fileArr.length > 0) {
                    C1390e c1390e = (C1390e) this.f3300e.get();
                    long j = (long) c1390e.f2813u;
                    long b = this.f3303h.m3135b(this.f3303h.m3140d().f2702g);
                    long a = this.f3301f.m3158a();
                    List list2 = c1390e.f2796d;
                    CBLogging.m3097a("Downloader", "Total local file count:" + fileArr.length);
                    CBLogging.m3097a("Downloader", "Video Folder Size in bytes :" + b);
                    CBLogging.m3097a("Downloader", "Max Bytes allowed:" + j);
                    for (File file2 : fileArr) {
                        Object obj = TimeUnit.MILLISECONDS.toDays(a - file2.lastModified()) >= ((long) c1390e.f2815w) ? 1 : null;
                        boolean endsWith = file2.getName().endsWith(".tmp");
                        File parentFile = file2.getParentFile();
                        boolean contains = parentFile.getAbsolutePath().contains("/videos");
                        Object obj2 = (b <= j || !contains) ? null : 1;
                        obj2 = (file2.length() == 0 || endsWith || obj != null || list2.contains(parentFile.getName()) || obj2 != null) ? 1 : null;
                        if (obj2 != null) {
                            if (contains) {
                                b -= file2.length();
                            }
                            CBLogging.m3097a("Downloader", "Deleting file at path:" + file2.getPath());
                            if (!file2.delete()) {
                                CBLogging.m3099b("Downloader", "Unable to delete " + file2.getPath());
                            }
                        }
                    }
                }
                this.f3302g.m3221a(this.f3303h.m3141e());
            } catch (Exception e) {
                C1391a.m3206a(getClass(), "reduceCacheSize", e);
            }
        }
    }
}
