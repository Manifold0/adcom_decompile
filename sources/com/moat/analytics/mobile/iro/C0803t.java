package com.moat.analytics.mobile.iro;

import android.os.Handler;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.moat.analytics.mobile.iro.t */
final class C0803t {
    /* renamed from: ʻ */
    private static final Queue<C0799b> f1293 = new ConcurrentLinkedQueue();
    /* renamed from: ʽ */
    private static C0803t f1294;
    /* renamed from: ʼ */
    private long f1295 = 60000;
    /* renamed from: ˊ */
    volatile int f1296 = 10;
    /* renamed from: ˊॱ */
    private long f1297 = TapjoyConstants.SESSION_ID_INACTIVITY_TIME;
    /* renamed from: ˋ */
    volatile int f1298 = C0800c.f1285;
    /* renamed from: ˋॱ */
    private final AtomicInteger f1299 = new AtomicInteger(0);
    /* renamed from: ˎ */
    volatile boolean f1300 = false;
    /* renamed from: ˏ */
    volatile boolean f1301 = false;
    /* renamed from: ˏॱ */
    private final AtomicBoolean f1302 = new AtomicBoolean(false);
    /* renamed from: ॱ */
    volatile int f1303 = 200;
    /* renamed from: ॱˊ */
    private final AtomicBoolean f1304 = new AtomicBoolean(false);
    /* renamed from: ॱˋ */
    private volatile long f1305 = 0;
    /* renamed from: ᐝ */
    private Handler f1306;

    /* renamed from: com.moat.analytics.mobile.iro.t$a */
    interface C0773a {
        /* renamed from: ॱ */
        void mo1645() throws C0785o;
    }

    /* renamed from: com.moat.analytics.mobile.iro.t$2 */
    class C07952 implements Runnable {
        /* renamed from: ˋ */
        private /* synthetic */ C0803t f1278;

        C07952(C0803t c0803t) {
            this.f1278 = c0803t;
        }

        public final void run() {
            try {
                if (C0803t.f1293.size() > 0) {
                    C0803t.m1391();
                    this.f1278.f1306.postDelayed(this, 60000);
                    return;
                }
                this.f1278.f1304.compareAndSet(true, false);
                this.f1278.f1306.removeCallbacks(this);
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.t$e */
    interface C0796e {
        /* renamed from: ˋ */
        void mo1652(C0772i c0772i) throws C0785o;
    }

    /* renamed from: com.moat.analytics.mobile.iro.t$b */
    class C0799b {
        /* renamed from: ˋ */
        final C0773a f1282;
        /* renamed from: ˏ */
        private /* synthetic */ C0803t f1283;
        /* renamed from: ॱ */
        final Long f1284;

        C0799b(C0803t c0803t, Long l, C0773a c0773a) {
            this.f1283 = c0803t;
            this.f1284 = l;
            this.f1282 = c0773a;
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.t$c */
    enum C0800c {
        ;
        
        /* renamed from: ˊ */
        public static final int f1285 = 0;
        /* renamed from: ॱ */
        public static final int f1286 = 0;

        static {
            f1285 = 1;
            f1286 = 2;
            int[] iArr = new int[]{1, 2};
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.t$d */
    class C0802d implements Runnable {
        /* renamed from: ˋ */
        private final Handler f1289;
        /* renamed from: ˎ */
        private final C07972 f1290;
        /* renamed from: ˏ */
        private final String f1291;
        /* renamed from: ॱ */
        private /* synthetic */ C0803t f1292;

        private C0802d(C0803t c0803t, String str, Handler handler, C07972 c07972) {
            this.f1292 = c0803t;
            this.f1290 = c07972;
            this.f1289 = handler;
            this.f1291 = "https://z.moatads.com/" + str + "/android/" + "2bc3418b93f01686fcbd1ebebcc04694651821b2".substring(0, 7) + "/status.json";
        }

        /* renamed from: ॱ */
        private String m1389() {
            try {
                return (String) C0779l.m1343(this.f1291 + "?ts=" + System.currentTimeMillis() + "&v=2.4.0").get();
            } catch (Exception e) {
                return null;
            }
        }

        public final void run() {
            try {
                String ॱ = m1389();
                final C0772i c0772i = new C0772i(ॱ);
                this.f1292.f1300 = c0772i.m1313();
                this.f1292.f1301 = c0772i.m1315();
                this.f1292.f1303 = c0772i.m1314();
                this.f1292.f1296 = c0772i.m1317();
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    /* renamed from: ॱ */
                    private /* synthetic */ C0802d f1288;

                    public final void run() {
                        try {
                            this.f1288.f1290.mo1652(c0772i);
                        } catch (Exception e) {
                            C0785o.m1351(e);
                        }
                    }
                });
                this.f1292.f1305 = System.currentTimeMillis();
                this.f1292.f1302.compareAndSet(true, false);
                if (ॱ != null) {
                    this.f1292.f1299.set(0);
                } else if (this.f1292.f1299.incrementAndGet() < 10) {
                    this.f1292.m1395(this.f1292.f1295);
                }
            } catch (Exception e) {
                C0785o.m1351(e);
            }
            this.f1289.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    /* renamed from: ˋ */
    static synchronized C0803t m1393() {
        C0803t c0803t;
        synchronized (C0803t.class) {
            if (f1294 == null) {
                f1294 = new C0803t();
            }
            c0803t = f1294;
        }
        return c0803t;
    }

    private C0803t() {
        try {
            this.f1306 = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ˏ */
    final void m1402() {
        if (System.currentTimeMillis() - this.f1305 > this.f1297) {
            m1395(0);
        }
    }

    /* renamed from: ˋ */
    private void m1395(final long j) {
        if (this.f1302.compareAndSet(false, true)) {
            C0756b.m1234(3, "OnOff", this, "Performing status check.");
            new Thread(this) {
                /* renamed from: ॱ */
                final /* synthetic */ C0803t f1281;

                /* renamed from: com.moat.analytics.mobile.iro.t$5$2 */
                class C07972 implements C0796e {
                    /* renamed from: ˎ */
                    private /* synthetic */ C07985 f1279;

                    C07972(C07985 c07985) {
                        this.f1279 = c07985;
                    }

                    /* renamed from: ˋ */
                    public final void mo1652(C0772i c0772i) throws C0785o {
                        synchronized (C0803t.f1293) {
                            boolean z = ((C0774j) MoatAnalytics.getInstance()).f1224;
                            if (this.f1279.f1281.f1298 != c0772i.m1316() || (this.f1279.f1281.f1298 == C0800c.f1285 && z)) {
                                this.f1279.f1281.f1298 = c0772i.m1316();
                                if (this.f1279.f1281.f1298 == C0800c.f1285 && z) {
                                    this.f1279.f1281.f1298 = C0800c.f1286;
                                }
                                if (this.f1279.f1281.f1298 == C0800c.f1286) {
                                    C0756b.m1234(3, "OnOff", this, "Moat enabled - Version 2.4.0");
                                }
                                for (C0799b c0799b : C0803t.f1293) {
                                    if (this.f1279.f1281.f1298 == C0800c.f1286) {
                                        c0799b.f1282.mo1645();
                                    }
                                }
                            }
                            while (!C0803t.f1293.isEmpty()) {
                                C0803t.f1293.remove();
                            }
                        }
                    }
                }

                public final void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new C0802d("IRO", handler, new C07972(this)), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* renamed from: ॱ */
    final void m1403(C0773a c0773a) throws C0785o {
        if (this.f1298 == C0800c.f1286) {
            c0773a.mo1645();
            return;
        }
        C0803t.m1391();
        f1293.add(new C0799b(this, Long.valueOf(System.currentTimeMillis()), c0773a));
        if (this.f1304.compareAndSet(false, true)) {
            this.f1306.postDelayed(new C07952(this), 60000);
        }
    }

    /* renamed from: ˊ */
    private static void m1391() {
        synchronized (f1293) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f1293.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((C0799b) it.next()).f1284.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (f1293.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f1293.remove();
                }
            }
        }
    }
}
