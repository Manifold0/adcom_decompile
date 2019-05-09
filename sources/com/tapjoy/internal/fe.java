package com.tapjoy.internal;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tapjoy.TJConnectListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class fe {
    /* renamed from: a */
    private final ReentrantLock f7391a = new ReentrantLock();
    /* renamed from: b */
    volatile int f7392b = C2924c.f7748a;
    /* renamed from: c */
    C2923b f7393c;
    /* renamed from: d */
    long f7394d = 1000;
    /* renamed from: e */
    C2920a f7395e;
    /* renamed from: f */
    private final Condition f7396f = this.f7391a.newCondition();
    /* renamed from: g */
    private final LinkedList f7397g = new LinkedList();
    /* renamed from: h */
    private C2920a f7398h;

    /* renamed from: com.tapjoy.internal.fe$1 */
    class C29171 implements Observer {
        /* renamed from: a */
        final /* synthetic */ fe f7733a;

        C29171(fe feVar) {
            this.f7733a = feVar;
        }

        public final void update(Observable observable, Object data) {
            ev.f7701b.deleteObserver(this);
            if (!Boolean.valueOf(Boolean.TRUE.equals(data)).booleanValue() && this.f7733a.f7395e != null && this.f7733a.f7395e.f7736a != null) {
                this.f7733a.f7393c = new C2923b();
                this.f7733a.f7393c.mo6208e();
            }
        }
    }

    /* renamed from: com.tapjoy.internal.fe$2 */
    class C29182 implements TJConnectListener {
        /* renamed from: a */
        final /* synthetic */ fe f7734a;

        C29182(fe feVar) {
            this.f7734a = feVar;
        }

        public final void onConnectSuccess() {
            fe feVar = this.f7734a;
            int i = C2924c.f7752e;
            int i2 = C2924c.f7749b;
            feVar.m7582a(i);
            this.f7734a.m7583a(true);
        }

        public final void onConnectFailure() {
            this.f7734a.m7583a(false);
        }
    }

    /* renamed from: com.tapjoy.internal.fe$3 */
    static /* synthetic */ class C29193 {
        /* renamed from: a */
        static final /* synthetic */ int[] f7735a = new int[C2924c.m7726a().length];

        static {
            try {
                f7735a[C2924c.f7752e - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7735a[C2924c.f7748a - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7735a[C2924c.f7749b - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7735a[C2924c.f7750c - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7735a[C2924c.f7751d - 1] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* renamed from: com.tapjoy.internal.fe$a */
    class C2920a {
        /* renamed from: a */
        public final Context f7736a;
        /* renamed from: b */
        public final String f7737b;
        /* renamed from: c */
        public final Hashtable f7738c;
        /* renamed from: d */
        final /* synthetic */ fe f7739d;

        public C2920a(fe feVar, Context context, String str, Hashtable hashtable) {
            this.f7739d = feVar;
            Context context2 = null;
            if (context != null) {
                if (context instanceof Application) {
                    context2 = context;
                } else {
                    context2 = context.getApplicationContext();
                }
            }
            if (context2 != null) {
                context = context2;
            }
            this.f7736a = context;
            this.f7737b = str;
            this.f7738c = hashtable;
        }
    }

    /* renamed from: com.tapjoy.internal.fe$b */
    class C2923b extends dd {
        /* renamed from: a */
        final /* synthetic */ fe f7743a;
        /* renamed from: b */
        private boolean f7744b;
        /* renamed from: c */
        private boolean f7745c;
        /* renamed from: d */
        private Context f7746d;
        /* renamed from: e */
        private BroadcastReceiver f7747e;

        /* renamed from: com.tapjoy.internal.fe$b$2 */
        class C29222 extends BroadcastReceiver {
            /* renamed from: a */
            final /* synthetic */ C2923b f7742a;

            C29222(C2923b c2923b) {
                this.f7742a = c2923b;
            }

            public final void onReceive(Context context, Intent intent) {
                this.f7742a.f7743a.m7586b();
            }
        }

        private C2923b(fe feVar) {
            this.f7743a = feVar;
            this.f7747e = new C29222(this);
        }

        /* renamed from: d */
        protected final void mo6281d() {
            this.f7744b = true;
            this.f7743a.m7586b();
        }

        /* renamed from: a */
        protected final void mo6278a() {
            fe feVar = this.f7743a;
            int i = C2924c.f7750c;
            int i2 = C2924c.f7749b;
            feVar.m7582a(i);
        }

        /* renamed from: c */
        protected final void mo6280c() {
            if (this.f7743a.f7393c == this) {
                this.f7743a.f7393c = null;
            }
            if (this.f7743a.f7392b == C2924c.f7750c) {
                fe feVar = this.f7743a;
                int i = C2924c.f7748a;
                int i2 = C2924c.f7750c;
                feVar.m7582a(i);
            }
        }

        /* renamed from: b */
        protected final void mo6279b() {
            this.f7746d = this.f7743a.m7581a().f7736a;
            this.f7746d.registerReceiver(this.f7747e, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            while (!this.f7744b) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                ev.f7701b.addObserver(new Observer(this) {
                    /* renamed from: b */
                    final /* synthetic */ C2923b f7741b;

                    public final void update(Observable observable, Object data) {
                        ev.f7701b.deleteObserver(this);
                        this.f7741b.f7745c = Boolean.TRUE.equals(data);
                        countDownLatch.countDown();
                    }
                });
                C2920a a = this.f7743a.m7581a();
                if (this.f7743a.mo6266a(a.f7736a, a.f7737b, a.f7738c, null)) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                    }
                    if (this.f7745c) {
                        fe feVar = this.f7743a;
                        int i = C2924c.f7752e;
                        int i2 = C2924c.f7750c;
                        feVar.m7582a(i);
                        this.f7743a.m7583a(true);
                        return;
                    }
                    try {
                        this.f7743a.m7583a(false);
                        long max = Math.max(this.f7743a.f7394d, 1000);
                        this.f7743a.f7394d = Math.min(max << 2, 3600000);
                        this.f7743a.m7584a(max);
                    } finally {
                        m7721h();
                    }
                } else {
                    this.f7743a.m7583a(false);
                    m7721h();
                    return;
                }
            }
            m7721h();
        }

        /* renamed from: h */
        private void m7721h() {
            this.f7746d.unregisterReceiver(this.f7747e);
        }
    }

    /* renamed from: com.tapjoy.internal.fe$c */
    enum C2924c {
        ;

        /* renamed from: a */
        public static int[] m7726a() {
            return (int[]) f7753f.clone();
        }

        static {
            f7748a = 1;
            f7749b = 2;
            f7750c = 3;
            f7751d = 4;
            f7752e = 5;
            f7753f = new int[]{f7748a, f7749b, f7750c, f7751d, f7752e};
        }
    }

    /* renamed from: a */
    public abstract boolean mo6266a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener);

    /* renamed from: b */
    public final boolean m7587b(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        this.f7391a.lock();
        if (tJConnectListener != null) {
            try {
                this.f7397g.addLast(eq.m7671a(tJConnectListener, TJConnectListener.class));
            } catch (Throwable th) {
                this.f7391a.unlock();
            }
        }
        C2920a c2920a = new C2920a(this, context, str, hashtable);
        switch (C29193.f7735a[this.f7392b - 1]) {
            case 1:
                m7583a(true);
                this.f7391a.unlock();
                return true;
            case 2:
                this.f7395e = c2920a;
                ev.f7701b.addObserver(new C29171(this));
                if (mo6266a(c2920a.f7736a, c2920a.f7737b, c2920a.f7738c, new C29182(this))) {
                    int i = C2924c.f7749b;
                    int i2 = C2924c.f7748a;
                    m7582a(i);
                    this.f7391a.unlock();
                    return true;
                }
                this.f7397g.clear();
                this.f7391a.unlock();
                return false;
            case 3:
            case 4:
                this.f7398h = c2920a;
                this.f7391a.unlock();
                return true;
            case 5:
                this.f7398h = c2920a;
                m7586b();
                this.f7391a.unlock();
                return true;
            default:
                m7582a(C2924c.f7748a);
                this.f7391a.unlock();
                return false;
        }
        this.f7391a.unlock();
    }

    /* renamed from: a */
    final void m7582a(int i) {
        this.f7391a.lock();
        try {
            int i2 = this.f7392b;
            this.f7392b = i;
        } finally {
            this.f7391a.unlock();
        }
    }

    /* renamed from: a */
    final C2920a m7581a() {
        this.f7391a.lock();
        try {
            if (this.f7398h != null) {
                this.f7395e = this.f7398h;
                this.f7398h = null;
            }
            C2920a c2920a = this.f7395e;
            return c2920a;
        } finally {
            this.f7391a.unlock();
        }
    }

    /* renamed from: a */
    final void m7583a(boolean z) {
        this.f7391a.lock();
        try {
            if (this.f7397g.size() != 0) {
                ArrayList arrayList = new ArrayList(this.f7397g);
                this.f7397g.clear();
                this.f7391a.unlock();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    TJConnectListener tJConnectListener = (TJConnectListener) it.next();
                    if (z) {
                        tJConnectListener.onConnectSuccess();
                    } else {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        } finally {
            this.f7391a.unlock();
        }
    }

    /* renamed from: b */
    final void m7586b() {
        this.f7391a.lock();
        try {
            this.f7394d = 1000;
            this.f7396f.signal();
        } finally {
            this.f7391a.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    final boolean m7584a(long r4) {
        /*
        r3 = this;
        r2 = 0;
        r0 = r3.f7391a;
        r0.lock();
        r0 = com.tapjoy.internal.fe.C2924c.f7751d;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r1 = com.tapjoy.internal.fe.C2924c.f7750c;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r3.m7582a(r0);	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r0 = r3.f7396f;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        r0 = r0.await(r4, r1);	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
        if (r0 == 0) goto L_0x001b;
    L_0x0017:
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3.f7394d = r0;	 Catch:{ InterruptedException -> 0x0028, all -> 0x0036 }
    L_0x001b:
        r0 = com.tapjoy.internal.fe.C2924c.f7750c;
        r1 = com.tapjoy.internal.fe.C2924c.f7751d;
        r3.m7582a(r0);
        r0 = r3.f7391a;
        r0.unlock();
    L_0x0027:
        return r2;
    L_0x0028:
        r0 = move-exception;
        r0 = com.tapjoy.internal.fe.C2924c.f7750c;
        r1 = com.tapjoy.internal.fe.C2924c.f7751d;
        r3.m7582a(r0);
        r0 = r3.f7391a;
        r0.unlock();
        goto L_0x0027;
    L_0x0036:
        r0 = move-exception;
        r1 = com.tapjoy.internal.fe.C2924c.f7750c;
        r2 = com.tapjoy.internal.fe.C2924c.f7751d;
        r3.m7582a(r1);
        r1 = r3.f7391a;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.fe.a(long):boolean");
    }
}
