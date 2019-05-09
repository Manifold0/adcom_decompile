package com.moat.analytics.mobile.vng;

import android.os.Handler;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.moat.analytics.mobile.vng.w */
class C0879w {
    /* renamed from: g */
    private static C0879w f1476g;
    /* renamed from: h */
    private static final Queue<C0877c> f1477h = new ConcurrentLinkedQueue();
    /* renamed from: a */
    volatile C0878d f1478a = C0878d.OFF;
    /* renamed from: b */
    volatile boolean f1479b = false;
    /* renamed from: c */
    volatile boolean f1480c = false;
    /* renamed from: d */
    volatile int f1481d = 200;
    /* renamed from: e */
    private long f1482e = TapjoyConstants.SESSION_ID_INACTIVITY_TIME;
    /* renamed from: f */
    private long f1483f = 60000;
    /* renamed from: i */
    private Handler f1484i;
    /* renamed from: j */
    private final AtomicBoolean f1485j = new AtomicBoolean(false);
    /* renamed from: k */
    private volatile long f1486k = 0;
    /* renamed from: l */
    private final AtomicInteger f1487l = new AtomicInteger(0);
    /* renamed from: m */
    private final AtomicBoolean f1488m = new AtomicBoolean(false);

    /* renamed from: com.moat.analytics.mobile.vng.w$b */
    interface C0846b {
        /* renamed from: b */
        void mo1669b();

        /* renamed from: c */
        void mo1670c();
    }

    /* renamed from: com.moat.analytics.mobile.vng.w$e */
    interface C0871e {
        /* renamed from: a */
        void mo1694a(C0848l c0848l);
    }

    /* renamed from: com.moat.analytics.mobile.vng.w$2 */
    class C08742 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0879w f1463a;

        C08742(C0879w c0879w) {
            this.f1463a = c0879w;
        }

        public void run() {
            try {
                if (C0879w.f1477h.size() > 0) {
                    this.f1463a.m1618d();
                    this.f1463a.f1484i.postDelayed(this, 60000);
                    return;
                }
                this.f1463a.f1485j.compareAndSet(true, false);
                this.f1463a.f1484i.removeCallbacks(this);
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.w$a */
    private class C0876a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0879w f1466a;
        /* renamed from: b */
        private final Handler f1467b;
        /* renamed from: c */
        private final String f1468c;
        /* renamed from: d */
        private final C0871e f1469d;

        private C0876a(C0879w c0879w, String str, Handler handler, C0871e c0871e) {
            this.f1466a = c0879w;
            this.f1469d = c0871e;
            this.f1467b = handler;
            this.f1468c = "https://z.moatads.com/" + str + "/android/" + "3f2ae9c1894282b5e0222f0d06bbf457191f816f".substring(0, 7) + "/status.json";
        }

        /* renamed from: a */
        private void m1607a() {
            String b = m1608b();
            final C0848l c0848l = new C0848l(b);
            this.f1466a.f1479b = c0848l.m1539a();
            this.f1466a.f1480c = c0848l.m1540b();
            this.f1466a.f1481d = c0848l.m1541c();
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0876a f1465b;

                public void run() {
                    try {
                        this.f1465b.f1469d.mo1694a(c0848l);
                    } catch (Exception e) {
                        C0849m.m1543a(e);
                    }
                }
            });
            this.f1466a.f1486k = System.currentTimeMillis();
            this.f1466a.f1488m.compareAndSet(true, false);
            if (b != null) {
                this.f1466a.f1487l.set(0);
            } else if (this.f1466a.f1487l.incrementAndGet() < 10) {
                this.f1466a.m1611a(this.f1466a.f1483f);
            }
        }

        /* renamed from: b */
        private String m1608b() {
            try {
                return (String) C0859q.m1581a(this.f1468c + "?ts=" + System.currentTimeMillis() + "&v=" + "2.2.0").m1435b();
            } catch (Exception e) {
                return null;
            }
        }

        public void run() {
            try {
                m1607a();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
            this.f1467b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.w$c */
    private class C0877c {
        /* renamed from: a */
        final Long f1470a;
        /* renamed from: b */
        final C0846b f1471b;
        /* renamed from: c */
        final /* synthetic */ C0879w f1472c;

        C0877c(C0879w c0879w, Long l, C0846b c0846b) {
            this.f1472c = c0879w;
            this.f1470a = l;
            this.f1471b = c0846b;
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.w$d */
    enum C0878d {
        OFF,
        ON
    }

    private C0879w() {
        try {
            this.f1484i = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: a */
    static synchronized C0879w m1610a() {
        C0879w c0879w;
        synchronized (C0879w.class) {
            if (f1476g == null) {
                f1476g = new C0879w();
            }
            c0879w = f1476g;
        }
        return c0879w;
    }

    /* renamed from: a */
    private void m1611a(final long j) {
        if (this.f1488m.compareAndSet(false, true)) {
            C0858p.m1577a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread(this) {
                /* renamed from: b */
                final /* synthetic */ C0879w f1462b;

                /* renamed from: com.moat.analytics.mobile.vng.w$1$1 */
                class C08721 implements C0871e {
                    /* renamed from: a */
                    final /* synthetic */ C08731 f1460a;

                    C08721(C08731 c08731) {
                        this.f1460a = c08731;
                    }

                    /* renamed from: a */
                    public void mo1694a(C0848l c0848l) {
                        synchronized (C0879w.f1477h) {
                            boolean z = ((C0847k) MoatAnalytics.getInstance()).f1423a;
                            if (this.f1460a.f1462b.f1478a != c0848l.m1542d() || (this.f1460a.f1462b.f1478a == C0878d.OFF && z)) {
                                this.f1460a.f1462b.f1478a = c0848l.m1542d();
                                if (this.f1460a.f1462b.f1478a == C0878d.OFF && z) {
                                    this.f1460a.f1462b.f1478a = C0878d.ON;
                                }
                                if (this.f1460a.f1462b.f1478a == C0878d.ON) {
                                    C0858p.m1577a(3, "OnOff", (Object) this, "Moat enabled - Version 2.2.0");
                                }
                                for (C0877c c0877c : C0879w.f1477h) {
                                    if (this.f1460a.f1462b.f1478a == C0878d.ON) {
                                        c0877c.f1471b.mo1669b();
                                    } else {
                                        c0877c.f1471b.mo1670c();
                                    }
                                }
                            }
                            while (!C0879w.f1477h.isEmpty()) {
                                C0879w.f1477h.remove();
                            }
                        }
                    }
                }

                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    String str = "VNG";
                    handler.postDelayed(new C0876a("VNG", handler, new C08721(this)), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* renamed from: d */
    private void m1618d() {
        synchronized (f1477h) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f1477h.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((C0877c) it.next()).f1470a.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (f1477h.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f1477h.remove();
                }
            }
        }
    }

    /* renamed from: e */
    private void m1620e() {
        if (this.f1485j.compareAndSet(false, true)) {
            this.f1484i.postDelayed(new C08742(this), 60000);
        }
    }

    /* renamed from: a */
    void m1622a(C0846b c0846b) {
        if (this.f1478a == C0878d.ON) {
            c0846b.mo1669b();
            return;
        }
        m1618d();
        f1477h.add(new C0877c(this, Long.valueOf(System.currentTimeMillis()), c0846b));
        m1620e();
    }

    /* renamed from: b */
    void m1623b() {
        if (System.currentTimeMillis() - this.f1486k > this.f1482e) {
            m1611a(0);
        }
    }
}
