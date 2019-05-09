package com.moat.analytics.mobile.cha;

import android.os.Handler;
import android.os.Looper;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.moat.analytics.mobile.cha.t */
final class C1536t {
    /* renamed from: ʻ */
    private static C1536t f3603;
    /* renamed from: ʽ */
    private static final Queue<C1535e> f3604 = new ConcurrentLinkedQueue();
    /* renamed from: ʼ */
    private long f3605 = 60000;
    /* renamed from: ˊ */
    volatile int f3606 = 200;
    /* renamed from: ˊॱ */
    private long f3607 = TapjoyConstants.SESSION_ID_INACTIVITY_TIME;
    /* renamed from: ˋ */
    volatile boolean f3608 = false;
    /* renamed from: ˋॱ */
    private final AtomicBoolean f3609 = new AtomicBoolean(false);
    /* renamed from: ˎ */
    volatile int f3610 = C1532a.f3593;
    /* renamed from: ˏ */
    volatile boolean f3611 = false;
    /* renamed from: ˏॱ */
    private final AtomicBoolean f3612 = new AtomicBoolean(false);
    /* renamed from: ॱ */
    volatile int f3613 = 10;
    /* renamed from: ॱˊ */
    private final AtomicInteger f3614 = new AtomicInteger(0);
    /* renamed from: ॱˋ */
    private volatile long f3615 = 0;
    /* renamed from: ᐝ */
    private Handler f3616;

    /* renamed from: com.moat.analytics.mobile.cha.t$b */
    interface C1494b {
        /* renamed from: ˎ */
        void mo4370() throws C1518o;
    }

    /* renamed from: com.moat.analytics.mobile.cha.t$c */
    interface C1528c {
        /* renamed from: ˏ */
        void mo4380(C1496g c1496g) throws C1518o;
    }

    /* renamed from: com.moat.analytics.mobile.cha.t$4 */
    class C15314 implements Runnable {
        /* renamed from: ॱ */
        private /* synthetic */ C1536t f3591;

        C15314(C1536t c1536t) {
            this.f3591 = c1536t;
        }

        public final void run() {
            try {
                if (C1536t.f3604.size() > 0) {
                    C1536t.m3881();
                    this.f3591.f3616.postDelayed(this, 60000);
                    return;
                }
                this.f3591.f3609.compareAndSet(true, false);
                this.f3591.f3616.removeCallbacks(this);
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.t$a */
    enum C1532a {
        ;
        
        /* renamed from: ˎ */
        public static final int f3592 = 0;
        /* renamed from: ॱ */
        public static final int f3593 = 0;

        static {
            f3593 = 1;
            f3592 = 2;
            int[] iArr = new int[]{1, 2};
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.t$d */
    class C1534d implements Runnable {
        /* renamed from: ˋ */
        private /* synthetic */ C1536t f3596;
        /* renamed from: ˎ */
        private final String f3597;
        /* renamed from: ˏ */
        private final C15292 f3598;
        /* renamed from: ॱ */
        private final Handler f3599;

        private C1534d(C1536t c1536t, String str, Handler handler, C15292 c15292) {
            this.f3596 = c1536t;
            this.f3598 = c15292;
            this.f3599 = handler;
            this.f3597 = "https://z.moatads.com/" + str + "/android/" + "35d482907bc2811c2e46b96f16eb5f9fe00185f3".substring(0, 7) + "/status.json";
        }

        /* renamed from: ˎ */
        private String m3878() {
            try {
                return (String) C1514m.m3818(this.f3597 + "?ts=" + System.currentTimeMillis() + "&v=2.4.1").get();
            } catch (Exception e) {
                return null;
            }
        }

        public final void run() {
            try {
                String ˎ = m3878();
                final C1496g c1496g = new C1496g(ˎ);
                this.f3596.f3611 = c1496g.m3763();
                this.f3596.f3608 = c1496g.m3767();
                this.f3596.f3606 = c1496g.m3766();
                this.f3596.f3613 = c1496g.m3765();
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    /* renamed from: ˊ */
                    private /* synthetic */ C1534d f3594;

                    public final void run() {
                        try {
                            this.f3594.f3598.mo4380(c1496g);
                        } catch (Exception e) {
                            C1518o.m3840(e);
                        }
                    }
                });
                this.f3596.f3615 = System.currentTimeMillis();
                this.f3596.f3612.compareAndSet(true, false);
                if (ˎ != null) {
                    this.f3596.f3614.set(0);
                } else if (this.f3596.f3614.incrementAndGet() < 10) {
                    this.f3596.m3888(this.f3596.f3605);
                }
            } catch (Exception e) {
                C1518o.m3840(e);
            }
            this.f3599.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.t$e */
    class C1535e {
        /* renamed from: ˎ */
        final Long f3600;
        /* renamed from: ˏ */
        private /* synthetic */ C1536t f3601;
        /* renamed from: ॱ */
        final C1494b f3602;

        C1535e(C1536t c1536t, Long l, C1494b c1494b) {
            this.f3601 = c1536t;
            this.f3600 = l;
            this.f3602 = c1494b;
        }
    }

    /* renamed from: ˏ */
    static synchronized C1536t m3887() {
        C1536t c1536t;
        synchronized (C1536t.class) {
            if (f3603 == null) {
                f3603 = new C1536t();
            }
            c1536t = f3603;
        }
        return c1536t;
    }

    private C1536t() {
        try {
            this.f3616 = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ˎ */
    final void m3893() {
        if (System.currentTimeMillis() - this.f3615 > this.f3607) {
            m3888(0);
        }
    }

    /* renamed from: ˏ */
    private void m3888(final long j) {
        if (this.f3612.compareAndSet(false, true)) {
            C1487a.m3715(3, "OnOff", this, "Performing status check.");
            new Thread(this) {
                /* renamed from: ˎ */
                final /* synthetic */ C1536t f3589;

                /* renamed from: com.moat.analytics.mobile.cha.t$2$2 */
                class C15292 implements C1528c {
                    /* renamed from: ॱ */
                    private /* synthetic */ C15302 f3588;

                    C15292(C15302 c15302) {
                        this.f3588 = c15302;
                    }

                    /* renamed from: ˏ */
                    public final void mo4380(C1496g c1496g) throws C1518o {
                        synchronized (C1536t.f3604) {
                            boolean z = ((C1495f) MoatAnalytics.getInstance()).f3472;
                            if (this.f3588.f3589.f3610 != c1496g.m3764() || (this.f3588.f3589.f3610 == C1532a.f3593 && z)) {
                                this.f3588.f3589.f3610 = c1496g.m3764();
                                if (this.f3588.f3589.f3610 == C1532a.f3593 && z) {
                                    this.f3588.f3589.f3610 = C1532a.f3592;
                                }
                                if (this.f3588.f3589.f3610 == C1532a.f3592) {
                                    C1487a.m3715(3, "OnOff", this, "Moat enabled - Version 2.4.1");
                                }
                                for (C1535e c1535e : C1536t.f3604) {
                                    if (this.f3588.f3589.f3610 == C1532a.f3592) {
                                        c1535e.f3602.mo4370();
                                    }
                                }
                            }
                            while (!C1536t.f3604.isEmpty()) {
                                C1536t.f3604.remove();
                            }
                        }
                    }
                }

                public final void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new C1534d("CHA", handler, new C15292(this)), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* renamed from: ˊ */
    final void m3892(C1494b c1494b) throws C1518o {
        if (this.f3610 == C1532a.f3592) {
            c1494b.mo4370();
            return;
        }
        C1536t.m3881();
        f3604.add(new C1535e(this, Long.valueOf(System.currentTimeMillis()), c1494b));
        if (this.f3609.compareAndSet(false, true)) {
            this.f3616.postDelayed(new C15314(this), 60000);
        }
    }

    /* renamed from: ˊ */
    private static void m3881() {
        synchronized (f3604) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f3604.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((C1535e) it.next()).f3600.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (f3604.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f3604.remove();
                }
            }
        }
    }
}
