package com.chartboost.sdk.impl;

import android.content.SharedPreferences;
import android.os.Handler;
import com.chartboost.sdk.C1397c;
import com.chartboost.sdk.C1397c.C1396c;
import com.chartboost.sdk.C1399d;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.C1382i;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1386a;
import com.chartboost.sdk.Model.C1387b;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.C1385a;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1434c.C1433a;
import com.chartboost.sdk.impl.aj.C1406a;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.impl.e */
public class C1440e {
    /* renamed from: A */
    private final long f3231A = TimeUnit.SECONDS.toNanos(1);
    /* renamed from: B */
    private final String[] f3232B = new String[]{"ASKED_TO_CACHE", "ASKED_TO_SHOW", "REQUESTING_TO_CACHE", "REQUESTING_TO_SHOW", "DOWNLOADING_TO_CACHE", "DOWNLOADING_TO_SHOW", "READY", "ASKING_UI_TO_SHOW_AD", "DONE"};
    /* renamed from: a */
    final ScheduledExecutorService f3233a;
    /* renamed from: b */
    public final C1378f f3234b;
    /* renamed from: c */
    final C1382i f3235c;
    /* renamed from: d */
    final Handler f3236d;
    /* renamed from: e */
    final C1397c f3237e;
    /* renamed from: f */
    final C1434c f3238f;
    /* renamed from: g */
    int f3239g = 0;
    /* renamed from: h */
    final Map<String, C1441f> f3240h;
    /* renamed from: i */
    final SortedSet<C1441f> f3241i;
    /* renamed from: j */
    final SortedSet<C1441f> f3242j;
    /* renamed from: k */
    ScheduledFuture<?> f3243k;
    /* renamed from: l */
    private final C1447l f3244l;
    /* renamed from: m */
    private final ah f3245m;
    /* renamed from: n */
    private final ai f3246n;
    /* renamed from: o */
    private final ap f3247o;
    /* renamed from: p */
    private final AtomicReference<C1390e> f3248p;
    /* renamed from: q */
    private final SharedPreferences f3249q;
    /* renamed from: r */
    private final C1391a f3250r;
    /* renamed from: s */
    private final ak f3251s;
    /* renamed from: t */
    private final C1399d f3252t;
    /* renamed from: u */
    private final al f3253u;
    /* renamed from: v */
    private int f3254v;
    /* renamed from: w */
    private boolean f3255w;
    /* renamed from: x */
    private final Map<String, Long> f3256x;
    /* renamed from: y */
    private final Map<String, Integer> f3257y;
    /* renamed from: z */
    private final long f3258z = TimeUnit.SECONDS.toNanos(5);

    /* renamed from: com.chartboost.sdk.impl.e$a */
    public class C1439a implements Runnable {
        /* renamed from: a */
        final int f3226a;
        /* renamed from: b */
        final String f3227b;
        /* renamed from: c */
        final C1441f f3228c;
        /* renamed from: d */
        final CBImpressionError f3229d;
        /* renamed from: e */
        final /* synthetic */ C1440e f3230e;

        public C1439a(C1440e c1440e, int i, String str, C1441f c1441f, CBImpressionError cBImpressionError) {
            this.f3230e = c1440e;
            this.f3226a = i;
            this.f3227b = str;
            this.f3228c = c1441f;
            this.f3229d = cBImpressionError;
        }

        public void run() {
            try {
                synchronized (this.f3230e) {
                    switch (this.f3226a) {
                        case 0:
                            this.f3230e.m3575a();
                            break;
                        case 2:
                            this.f3230e.f3243k = null;
                            this.f3230e.m3581b();
                            break;
                        case 3:
                            this.f3230e.m3583b(this.f3227b);
                            break;
                        case 4:
                            this.f3230e.m3584c(this.f3227b);
                            break;
                        case 5:
                            this.f3230e.m3582b(this.f3228c);
                            break;
                        case 6:
                            this.f3230e.m3577a(this.f3228c, this.f3229d);
                            break;
                        case 7:
                            this.f3230e.m3576a(this.f3228c);
                            break;
                        case 8:
                            this.f3230e.m3585d(this.f3227b);
                            break;
                    }
                }
            } catch (Exception e) {
                C1391a.m3206a(getClass(), "run", e);
            }
        }
    }

    public C1440e(C1434c c1434c, ScheduledExecutorService scheduledExecutorService, C1447l c1447l, C1378f c1378f, ah ahVar, ai aiVar, ap apVar, AtomicReference<C1390e> atomicReference, SharedPreferences sharedPreferences, C1382i c1382i, C1391a c1391a, Handler handler, C1397c c1397c, ak akVar, C1399d c1399d, al alVar) {
        this.f3233a = scheduledExecutorService;
        this.f3244l = c1447l;
        this.f3234b = c1378f;
        this.f3245m = ahVar;
        this.f3246n = aiVar;
        this.f3247o = apVar;
        this.f3248p = atomicReference;
        this.f3249q = sharedPreferences;
        this.f3235c = c1382i;
        this.f3250r = c1391a;
        this.f3236d = handler;
        this.f3237e = c1397c;
        this.f3251s = akVar;
        this.f3252t = c1399d;
        this.f3253u = alVar;
        this.f3238f = c1434c;
        this.f3254v = 1;
        this.f3240h = new HashMap();
        this.f3242j = new TreeSet();
        this.f3241i = new TreeSet();
        this.f3256x = new HashMap();
        this.f3257y = new HashMap();
        this.f3255w = false;
    }

    /* renamed from: a */
    void m3575a() {
        if (this.f3239g == 0) {
            this.f3239g = 1;
            m3581b();
        }
    }

    /* renamed from: b */
    void m3581b() {
        if (!this.f3255w) {
            try {
                this.f3255w = true;
                m3564d();
                if (this.f3239g == 1 && !m3559a(this.f3242j, 1, 3, 1, "show")) {
                    m3559a(this.f3241i, 0, 2, 2, "cache");
                }
                m3562c();
            } finally {
                this.f3255w = false;
            }
        }
    }

    /* renamed from: c */
    private void m3562c() {
        Long l;
        if (this.f3239g == 1) {
            long b = this.f3235c.m3159b();
            Long l2 = null;
            for (Entry entry : this.f3256x.entrySet()) {
                if (((C1441f) this.f3240h.get((String) entry.getKey())) != null) {
                    Long valueOf;
                    long max = Math.max(this.f3258z, ((Long) entry.getValue()).longValue() - b);
                    if (l2 == null || max < l2.longValue()) {
                        valueOf = Long.valueOf(max);
                    } else {
                        valueOf = l2;
                    }
                    l2 = valueOf;
                }
            }
            l = l2;
        } else {
            l = null;
        }
        if (!(l == null || this.f3243k == null)) {
            if (Math.abs(l.longValue() - this.f3243k.getDelay(TimeUnit.NANOSECONDS)) <= TimeUnit.SECONDS.toNanos(5)) {
                return;
            }
        }
        if (this.f3243k != null) {
            this.f3243k.cancel(false);
            this.f3243k = null;
        }
        if (l != null) {
            this.f3243k = this.f3233a.schedule(new C1439a(this, 2, null, null, null), l.longValue(), TimeUnit.NANOSECONDS);
        }
    }

    /* renamed from: a */
    private boolean m3559a(SortedSet<C1441f> sortedSet, int i, int i2, int i3, String str) {
        Iterator it = sortedSet.iterator();
        while (it.hasNext()) {
            C1441f c1441f = (C1441f) it.next();
            if (c1441f.f3261c != i || c1441f.f3262d != null) {
                it.remove();
            } else if (m3568e(c1441f.f3260b)) {
                continue;
            } else if (this.f3238f.m3546g(c1441f.f3260b)) {
                c1441f.f3261c = i2;
                it.remove();
                m3557a(c1441f, i3, str);
                return true;
            } else {
                c1441f.f3261c = 8;
                this.f3240h.remove(c1441f.f3260b);
                it.remove();
            }
        }
        return false;
    }

    /* renamed from: a */
    public synchronized C1386a m3574a(String str) {
        C1386a c1386a;
        C1441f c1441f = (C1441f) this.f3240h.get(str);
        if (c1441f == null || !(c1441f.f3261c == 6 || c1441f.f3261c == 7)) {
            c1386a = null;
        } else {
            c1386a = c1441f.f3262d;
        }
        return c1386a;
    }

    /* renamed from: b */
    void m3583b(String str) {
        if (m3567e()) {
            C1434c c1434c = this.f3238f;
            c1434c.getClass();
            this.f3236d.postDelayed(new C1433a(c1434c, 4, str, CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED), this.f3231A);
            return;
        }
        C1441f c1441f = (C1441f) this.f3240h.get(str);
        if (!(c1441f == null || c1441f.f3261c != 6 || m3558a(c1441f.f3262d))) {
            this.f3240h.remove(str);
            c1441f = null;
        }
        if (c1441f == null) {
            int i = this.f3254v;
            this.f3254v = i + 1;
            c1441f = new C1441f(i, str, 0);
            this.f3240h.put(str, c1441f);
            this.f3241i.add(c1441f);
        }
        c1441f.f3264f = true;
        if (c1441f.f3266h == null) {
            c1441f.f3266h = Long.valueOf(this.f3235c.m3159b());
        }
        switch (c1441f.f3261c) {
            case 6:
            case 7:
                Handler handler = this.f3236d;
                C1434c c1434c2 = this.f3238f;
                c1434c2.getClass();
                handler.post(new C1433a(c1434c2, 0, str, null));
                break;
        }
        m3581b();
    }

    /* renamed from: a */
    private boolean m3558a(C1386a c1386a) {
        File file = this.f3234b.m3140d().f2696a;
        for (C1387b c1387b : c1386a.f2731c.values()) {
            if (!c1387b.m3168a(file).exists()) {
                CBLogging.m3099b("AdUnitManager", "Asset does not exist: " + c1387b.f2749b);
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    private void m3564d() {
        long b = this.f3235c.m3159b();
        Iterator it = this.f3256x.values().iterator();
        while (it.hasNext()) {
            if (b - ((Long) it.next()).longValue() >= 0) {
                it.remove();
            }
        }
    }

    /* renamed from: e */
    private boolean m3568e(String str) {
        return this.f3256x.containsKey(str);
    }

    /* renamed from: a */
    private void m3557a(C1441f c1441f, int i, String str) {
        boolean z = true;
        try {
            boolean z2;
            ad ajVar;
            C1390e c1390e = (C1390e) this.f3248p.get();
            final boolean z3 = this.f3238f.f3209a == 2;
            if (!c1390e.f2817y || z3) {
                z2 = false;
            } else {
                z2 = true;
            }
            final long b = this.f3235c.m3159b();
            final C1441f c1441f2 = c1441f;
            C14361 c14361 = new C1406a(this) {
                /* renamed from: e */
                final /* synthetic */ C1440e f3223e;

                /* renamed from: a */
                public void mo4278a(aj ajVar, JSONObject jSONObject) {
                    try {
                        C1386a c1386a;
                        c1441f2.f3274p = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(this.f3223e.f3235c.m3159b() - b));
                        c1441f2.f3275q = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(ajVar.h));
                        c1441f2.f3276r = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(ajVar.i));
                        if (z3) {
                            c1386a = new C1386a(0, jSONObject, true);
                        } else if (z2) {
                            c1386a = new C1386a(1, jSONObject, false);
                        } else {
                            c1386a = new C1386a(0, jSONObject, false);
                        }
                        this.f3223e.m3579a(c1441f2, c1386a);
                    } catch (Exception e) {
                        C1391a.m3206a(C1440e.class, "sendAdGetRequest.onSuccess", e);
                        this.f3223e.m3578a(c1441f2, new CBError(C1385a.UNEXPECTED_RESPONSE, "Response conversion failure"));
                    }
                }

                /* renamed from: a */
                public void mo4277a(aj ajVar, CBError cBError) {
                    this.f3223e.m3578a(c1441f2, cBError);
                }
            };
            if (c1441f.f3261c != 2) {
                z = false;
            }
            if (z3) {
                ajVar = new aj(this.f3238f.f3212d, this.f3247o, this.f3250r, i, c14361);
                ajVar.f3010l = true;
                ajVar.m3386a(PlaceFields.LOCATION, c1441f.f3260b);
                ajVar.m3386a("cache", Boolean.valueOf(z));
                ajVar.m3386a("raw", Boolean.valueOf(true));
                c1441f.f3263e = Integer.valueOf(0);
            } else if (z2) {
                ajVar = new am(String.format(this.f3238f.f3213e, new Object[]{c1390e.f2786F}), this.f3247o, this.f3250r, i, c14361);
                ajVar.m3400a("cache_assets", this.f3234b.m3138c(), 0);
                ajVar.m3400a(PlaceFields.LOCATION, c1441f.f3260b, 0);
                ajVar.m3400a("cache", Boolean.valueOf(z), 0);
                ajVar.l = true;
                c1441f.f3263e = Integer.valueOf(1);
            } else {
                ajVar = new aj(this.f3238f.f3212d, this.f3247o, this.f3250r, i, c14361);
                ajVar.m3386a("local-videos", this.f3234b.m3136b());
                ajVar.f3010l = true;
                ajVar.m3386a(PlaceFields.LOCATION, c1441f.f3260b);
                ajVar.m3386a("cache", Boolean.valueOf(z));
                c1441f.f3263e = Integer.valueOf(0);
            }
            ajVar.j = 1;
            this.f3239g = 2;
            this.f3245m.m3371a(ajVar);
            this.f3250r.m3215a(this.f3238f.m3538a(c1441f.f3263e.intValue()), str, c1441f.f3260b);
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "sendAdGetRequest", e);
            m3578a(c1441f, new CBError(C1385a.MISCELLANEOUS, "error sending ad-get request"));
        }
    }

    /* renamed from: a */
    synchronized void m3579a(C1441f c1441f, C1386a c1386a) {
        this.f3239g = 1;
        c1441f.f3261c = c1441f.f3261c == 2 ? 4 : 5;
        c1441f.f3262d = c1386a;
        m3563c(c1441f);
        m3581b();
    }

    /* renamed from: c */
    private void m3563c(final C1441f c1441f) {
        if (c1441f.f3262d == null) {
            return;
        }
        if (c1441f.f3261c == 5 || c1441f.f3261c == 4) {
            int i = c1441f.f3261c == 5 ? 1 : 2;
            if (c1441f.f3265g > i) {
                C14382 c14382 = new C1437h(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1440e f3225b;

                    /* renamed from: a */
                    public void mo4311a(boolean z, int i, int i2) {
                        this.f3225b.m3580a(c1441f, z, i, i2);
                    }
                };
                c1441f.f3265g = i;
                this.f3244l.m3598a(i, c1441f.f3262d.f2731c, new AtomicInteger(), (C1437h) C1405g.m3317a().m3318a(c14382));
            }
        }
    }

    /* renamed from: a */
    synchronized void m3580a(C1441f c1441f, boolean z, int i, int i2) {
        if (c1441f.f3261c == 4 || c1441f.f3261c == 5) {
            c1441f.f3272n = Integer.valueOf(i);
            c1441f.f3273o = Integer.valueOf(i2);
            if (z) {
                m3565d(c1441f);
            } else {
                m3566e(c1441f);
            }
        }
        m3581b();
    }

    /* renamed from: d */
    private void m3565d(C1441f c1441f) {
        int i = c1441f.f3261c;
        long b = this.f3235c.m3159b();
        if (c1441f.f3266h != null) {
            c1441f.f3269k = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(b - c1441f.f3266h.longValue()));
        }
        if (c1441f.f3267i != null) {
            c1441f.f3270l = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(b - c1441f.f3267i.longValue()));
        }
        m3561b(c1441f, "ad-unit-cached");
        c1441f.f3261c = 6;
        if (c1441f.f3264f) {
            Handler handler = this.f3236d;
            C1434c c1434c = this.f3238f;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 0, c1441f.f3260b, null));
        }
        if (i == 5) {
            m3571h(c1441f);
        }
    }

    /* renamed from: e */
    private void m3566e(C1441f c1441f) {
        m3560b(c1441f, CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        m3569f(c1441f);
        m3570g(c1441f);
    }

    /* renamed from: f */
    private void m3569f(C1441f c1441f) {
        this.f3240h.remove(c1441f.f3260b);
        c1441f.f3261c = 8;
        c1441f.f3262d = null;
    }

    /* renamed from: a */
    synchronized void m3578a(C1441f c1441f, CBError cBError) {
        if (this.f3239g != 0) {
            this.f3239g = 1;
            m3560b(c1441f, cBError.m3163c());
            m3569f(c1441f);
            m3570g(c1441f);
            m3581b();
        }
    }

    /* renamed from: g */
    private void m3570g(C1441f c1441f) {
        C1390e c1390e = (C1390e) this.f3248p.get();
        long j = c1390e.f2811s;
        int i = c1390e.f2812t;
        Integer num = (Integer) this.f3257y.get(c1441f.f3260b);
        if (num == null) {
            num = Integer.valueOf(0);
        }
        num = Integer.valueOf(Math.min(num.intValue(), i));
        this.f3257y.put(c1441f.f3260b, Integer.valueOf(num.intValue() + 1));
        this.f3256x.put(c1441f.f3260b, Long.valueOf(TimeUnit.MILLISECONDS.toNanos(j << num.intValue()) + this.f3235c.m3159b()));
    }

    /* renamed from: c */
    void m3584c(String str) {
        if (m3567e()) {
            C1434c c1434c = this.f3238f;
            c1434c.getClass();
            this.f3236d.postDelayed(new C1433a(c1434c, 4, str, CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED), this.f3231A);
            return;
        }
        C1441f c1441f = (C1441f) this.f3240h.get(str);
        if (c1441f == null) {
            int i = this.f3254v;
            this.f3254v = i + 1;
            c1441f = new C1441f(i, str, 1);
            this.f3240h.put(str, c1441f);
            this.f3242j.add(c1441f);
        }
        if (c1441f.f3267i == null) {
            c1441f.f3267i = Long.valueOf(this.f3235c.m3159b());
        }
        switch (c1441f.f3261c) {
            case 0:
                this.f3241i.remove(c1441f);
                this.f3242j.add(c1441f);
                c1441f.f3261c = 1;
                break;
            case 2:
                c1441f.f3261c = 3;
                break;
            case 4:
                c1441f.f3261c = 5;
                m3563c(c1441f);
                break;
            case 6:
                m3571h(c1441f);
                break;
        }
        m3581b();
    }

    /* renamed from: h */
    private void m3571h(C1441f c1441f) {
        if (this.f3246n.m3378b()) {
            CBImpressionError cBImpressionError;
            C1388c a;
            try {
                CBImpressionError a2;
                C1386a c1386a = c1441f.f3262d;
                File file = this.f3234b.m3140d().f2696a;
                if (c1386a.f2730b == 0 && (this.f3238f.f3215g || c1386a.f2744p.equals("video"))) {
                    a2 = m3573a(c1386a.f2729a);
                    if (a2 != null) {
                        CBLogging.m3099b("AdUnitManager", "Video media unavailable for the impression");
                    }
                } else {
                    a2 = null;
                }
                if (a2 == null) {
                    cBImpressionError = a2;
                    for (C1387b c1387b : c1386a.f2731c.values()) {
                        if (c1387b.m3168a(file).exists()) {
                            a2 = cBImpressionError;
                        } else {
                            CBLogging.m3099b("AdUnitManager", "Asset does not exist: " + c1387b.f2749b);
                            a2 = CBImpressionError.ASSET_MISSING;
                        }
                        cBImpressionError = a2;
                    }
                    a2 = cBImpressionError;
                }
                if (a2 == null) {
                    String str;
                    if (c1386a.f2730b == 1) {
                        String a3 = m3556a(c1386a, file);
                        String str2;
                        if (a3 == null) {
                            str2 = a3;
                            cBImpressionError = CBImpressionError.ERROR_LOADING_WEB_VIEW;
                            str = str2;
                        } else {
                            str2 = a3;
                            cBImpressionError = a2;
                            str = str2;
                        }
                    } else {
                        cBImpressionError = a2;
                        str = null;
                    }
                    a = cBImpressionError == null ? m3555a(c1441f, str) : null;
                } else {
                    cBImpressionError = a2;
                    a = null;
                }
            } catch (Exception e) {
                C1391a.m3206a(getClass(), "showReady", e);
                cBImpressionError = CBImpressionError.INTERNAL;
                a = null;
            }
            if (cBImpressionError == null) {
                c1441f.f3261c = 7;
                C1397c c1397c = this.f3237e;
                c1397c.getClass();
                Runnable c1396c = new C1396c(c1397c, 10);
                c1396c.f2836d = a;
                c1441f.f3268j = Long.valueOf(this.f3235c.m3159b());
                this.f3236d.post(c1396c);
                return;
            }
            m3560b(c1441f, cBImpressionError);
            m3569f(c1441f);
            return;
        }
        Handler handler = this.f3236d;
        C1434c c1434c = this.f3238f;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 4, c1441f.f3260b, CBImpressionError.INTERNET_UNAVAILABLE_AT_SHOW));
    }

    /* renamed from: a */
    private String m3556a(C1386a c1386a, File file) {
        if (c1386a.f2746r == null) {
            CBLogging.m3099b("AdUnitManager", "AdUnit does not have a template body");
            return null;
        }
        File a = c1386a.f2746r.m3168a(file);
        Map hashMap = new HashMap();
        hashMap.putAll(c1386a.f2732d);
        hashMap.put("{% certification_providers %}", C1450o.m3609a(c1386a.f2747s));
        for (Entry entry : c1386a.f2731c.entrySet()) {
            hashMap.put(entry.getKey(), ((C1387b) entry.getValue()).f2749b);
        }
        try {
            return C1449n.m3608a(a, hashMap);
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "loadTemplateHtml", e);
            return null;
        }
    }

    /* renamed from: a */
    private C1388c m3555a(C1441f c1441f, String str) {
        return new C1388c(c1441f.f3262d, new C1435d(this, c1441f), this.f3234b, this.f3245m, this.f3247o, this.f3249q, this.f3250r, this.f3236d, this.f3237e, this.f3251s, this.f3252t, this.f3253u, this.f3238f, c1441f.f3260b, str);
    }

    /* renamed from: a */
    void m3577a(C1441f c1441f, CBImpressionError cBImpressionError) {
        m3560b(c1441f, cBImpressionError);
        if (c1441f.f3261c != 7) {
            return;
        }
        if (cBImpressionError == CBImpressionError.IMPRESSION_ALREADY_VISIBLE) {
            c1441f.f3261c = 6;
            c1441f.f3268j = null;
            c1441f.f3267i = null;
            c1441f.f3271m = null;
            return;
        }
        m3570g(c1441f);
        m3569f(c1441f);
        m3581b();
    }

    /* renamed from: b */
    private void m3560b(C1441f c1441f, CBImpressionError cBImpressionError) {
        Handler handler = this.f3236d;
        C1434c c1434c = this.f3238f;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 4, c1441f.f3260b, cBImpressionError));
        if (cBImpressionError != CBImpressionError.NO_AD_FOUND) {
            String str = c1441f.f3262d != null ? c1441f.f3262d.f2734f : null;
            String str2 = (c1441f.f3261c == 0 || c1441f.f3261c == 2 || c1441f.f3261c == 4) ? "cache" : "show";
            Integer valueOf = Integer.valueOf(c1441f.f3262d != null ? c1441f.f3262d.f2730b : c1441f.f3263e.intValue());
            String str3 = valueOf != null ? valueOf.intValue() == 0 ? "native" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : null;
            String str4 = (c1441f.f3261c < 0 || c1441f.f3261c >= this.f3232B.length) ? "Unknown state: " + c1441f.f3261c : this.f3232B[c1441f.f3261c];
            this.f3250r.m3217a(this.f3238f.f3210b, str2, str3, cBImpressionError.toString(), str, c1441f.f3260b, str4);
        }
    }

    /* renamed from: b */
    private void m3561b(C1441f c1441f, String str) {
        if (((C1390e) this.f3248p.get()).f2808p) {
            Integer valueOf;
            Object obj = c1441f.f3262d != null ? c1441f.f3262d.f2734f : null;
            String str2 = (c1441f.f3261c == 0 || c1441f.f3261c == 2 || c1441f.f3261c == 4) ? "cache" : "show";
            if (c1441f.f3262d != null) {
                valueOf = Integer.valueOf(c1441f.f3262d.f2730b);
            } else {
                valueOf = c1441f.f3263e;
            }
            String str3 = valueOf != null ? valueOf.intValue() == 0 ? "native" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB : null;
            Object obj2 = (c1441f.f3261c < 0 || c1441f.f3261c >= this.f3232B.length) ? "Unknown state: " + c1441f.f3261c : this.f3232B[c1441f.f3261c];
            this.f3250r.m3219a(str, this.f3238f.f3210b, str2, str3, null, null, C1377e.m3130a(C1377e.m3128a("adGetRequestSubmitToCallbackMs", c1441f.f3274p), C1377e.m3128a("downloadRequestToCompletionMs", c1441f.f3272n), C1377e.m3128a("downloadAccumulatedProcessingMs", c1441f.f3273o), C1377e.m3128a("adGetRequestGetResponseCodeMs", c1441f.f3275q), C1377e.m3128a("adGetRequestReadDataMs", c1441f.f3276r), C1377e.m3128a("cacheRequestToReadyMs", c1441f.f3269k), C1377e.m3128a("showRequestToReadyMs", c1441f.f3270l), C1377e.m3128a("showRequestToShownMs", c1441f.f3271m), C1377e.m3128a("adId", obj), C1377e.m3128a(PlaceFields.LOCATION, c1441f.f3260b), C1377e.m3128a("state", obj2)), false);
        }
    }

    /* renamed from: a */
    void m3576a(C1441f c1441f) {
        if (c1441f.f3261c == 7) {
            c1441f.f3261c = 6;
            c1441f.f3268j = null;
            c1441f.f3267i = null;
            c1441f.f3271m = null;
        }
    }

    /* renamed from: b */
    void m3582b(C1441f c1441f) {
        if (c1441f.f3261c == 7) {
            if (c1441f.f3267i != null && c1441f.f3271m == null) {
                c1441f.f3271m = Integer.valueOf((int) TimeUnit.NANOSECONDS.toMillis(this.f3235c.m3159b() - c1441f.f3267i.longValue()));
            }
            m3561b(c1441f, "ad-unit-shown");
            this.f3257y.remove(c1441f.f3260b);
            Handler handler = this.f3236d;
            C1434c c1434c = this.f3238f;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 5, c1441f.f3260b, null));
            m3572i(c1441f);
            m3569f(c1441f);
            m3581b();
        }
    }

    /* renamed from: d */
    void m3585d(String str) {
        C1441f c1441f = (C1441f) this.f3240h.get(str);
        if (c1441f != null && c1441f.f3261c == 6) {
            m3569f(c1441f);
            m3581b();
        }
    }

    /* renamed from: i */
    private void m3572i(C1441f c1441f) {
        ad ajVar = new aj(this.f3238f.f3214f, this.f3247o, this.f3250r, 2, new C1442g(this, c1441f.f3260b));
        ajVar.j = 1;
        ajVar.m3386a("cached", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        String str = c1441f.f3262d.f2734f;
        if (!str.isEmpty()) {
            ajVar.m3386a("ad_id", (Object) str);
        }
        ajVar.m3386a(PlaceFields.LOCATION, c1441f.f3260b);
        this.f3245m.m3371a(ajVar);
        this.f3250r.m3224b(this.f3238f.m3538a(c1441f.f3262d.f2730b), c1441f.f3260b, str);
    }

    /* renamed from: a */
    CBImpressionError m3573a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return CBImpressionError.INVALID_RESPONSE;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("assets");
        if (optJSONObject == null) {
            return CBImpressionError.INVALID_RESPONSE;
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject(CBUtility.m3111a(CBUtility.m3107a()) ? "video-portrait" : "video-landscape");
        if (optJSONObject2 == null) {
            return CBImpressionError.VIDEO_UNAVAILABLE_FOR_CURRENT_ORIENTATION;
        }
        String optString = optJSONObject2.optString("id");
        if (optString.isEmpty()) {
            return CBImpressionError.VIDEO_ID_MISSING;
        }
        return new File(this.f3234b.m3140d().f2702g, optString).exists() ? null : CBImpressionError.VIDEO_UNAVAILABLE;
    }

    /* renamed from: e */
    private boolean m3567e() {
        boolean z = true;
        if (this.f3238f.f3209a != 0 || C1410i.f2944u) {
            return false;
        }
        if (this.f3249q.getInt("cbPrefSessionCount", 0) != 1) {
            z = false;
        }
        return z;
    }
}
