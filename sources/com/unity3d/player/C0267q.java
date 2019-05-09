package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.C0260p.C0258a;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.unity3d.player.q */
final class C0267q {
    /* renamed from: a */
    private UnityPlayer f264a = null;
    /* renamed from: b */
    private Context f265b = null;
    /* renamed from: c */
    private C0213a f266c;
    /* renamed from: d */
    private final Semaphore f267d = new Semaphore(0);
    /* renamed from: e */
    private final Lock f268e = new ReentrantLock();
    /* renamed from: f */
    private C0260p f269f = null;
    /* renamed from: g */
    private int f270g = 2;
    /* renamed from: h */
    private boolean f271h = false;
    /* renamed from: i */
    private boolean f272i = false;

    /* renamed from: com.unity3d.player.q$a */
    public interface C0213a {
        /* renamed from: a */
        void mo688a();
    }

    /* renamed from: com.unity3d.player.q$2 */
    class C02642 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0267q f261a;

        C02642(C0267q c0267q) {
            this.f261a = c0267q;
        }

        public final void run() {
            this.f261a.f264a.pause();
        }
    }

    /* renamed from: com.unity3d.player.q$3 */
    class C02653 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0267q f262a;

        C02653(C0267q c0267q) {
            this.f262a = c0267q;
        }

        public final void run() {
            if (this.f262a.f269f != null) {
                this.f262a.f264a.addViewToPlayer(this.f262a.f269f, true);
                this.f262a.f272i = true;
                this.f262a.f269f.requestFocus();
            }
        }
    }

    /* renamed from: com.unity3d.player.q$4 */
    class C02664 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0267q f263a;

        C02664(C0267q c0267q) {
            this.f263a = c0267q;
        }

        public final void run() {
            this.f263a.m195d();
            this.f263a.f264a.resume();
        }
    }

    C0267q(UnityPlayer unityPlayer) {
        this.f264a = unityPlayer;
    }

    /* renamed from: d */
    private void m195d() {
        if (this.f269f != null) {
            this.f264a.removeViewFromPlayer(this.f269f);
            this.f272i = false;
            this.f269f.destroyPlayer();
            this.f269f = null;
            if (this.f266c != null) {
                this.f266c.mo688a();
            }
        }
    }

    /* renamed from: a */
    public final void m200a() {
        this.f268e.lock();
        if (this.f269f != null) {
            if (this.f270g == 0) {
                this.f269f.CancelOnPrepare();
            } else if (this.f272i) {
                this.f271h = this.f269f.m187a();
                if (!this.f271h) {
                    this.f269f.pause();
                }
            }
        }
        this.f268e.unlock();
    }

    /* renamed from: a */
    public final boolean m201a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0213a c0213a) {
        this.f268e.lock();
        this.f266c = c0213a;
        this.f265b = context;
        this.f267d.drainPermits();
        this.f270g = 2;
        final String str2 = str;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final boolean z2 = z;
        final long j3 = j;
        final long j4 = j2;
        runOnUiThread(new Runnable(this) {
            /* renamed from: h */
            final /* synthetic */ C0267q f260h;

            /* renamed from: com.unity3d.player.q$1$1 */
            class C02621 implements C0258a {
                /* renamed from: a */
                final /* synthetic */ C02631 f252a;

                /* renamed from: com.unity3d.player.q$1$1$1 */
                class C02611 implements Runnable {
                    /* renamed from: a */
                    final /* synthetic */ C02621 f251a;

                    C02611(C02621 c02621) {
                        this.f251a = c02621;
                    }

                    public final void run() {
                        this.f251a.f252a.f260h.m195d();
                        this.f251a.f252a.f260h.f264a.resume();
                    }
                }

                C02621(C02631 c02631) {
                    this.f252a = c02631;
                }

                /* renamed from: a */
                public final void mo695a(int i) {
                    this.f252a.f260h.f268e.lock();
                    this.f252a.f260h.f270g = i;
                    if (i == 3 && this.f252a.f260h.f272i) {
                        this.f252a.f260h.runOnUiThread(new C02611(this));
                    }
                    if (i != 0) {
                        this.f252a.f260h.f267d.release();
                    }
                    this.f252a.f260h.f268e.unlock();
                }
            }

            public final void run() {
                if (this.f260h.f269f != null) {
                    C0243g.Log(5, "Video already playing");
                    this.f260h.f270g = 2;
                    this.f260h.f267d.release();
                    return;
                }
                this.f260h.f269f = new C0260p(this.f260h.f265b, str2, i4, i5, i6, z2, j3, j4, new C02621(this));
                if (this.f260h.f269f != null) {
                    this.f260h.f264a.addView(this.f260h.f269f);
                }
            }
        });
        boolean z3 = false;
        try {
            this.f268e.unlock();
            this.f267d.acquire();
            this.f268e.lock();
            z3 = this.f270g != 2;
        } catch (InterruptedException e) {
        }
        runOnUiThread(new C02642(this));
        if (!z3 || this.f270g == 3) {
            runOnUiThread(new C02664(this));
        } else {
            runOnUiThread(new C02653(this));
        }
        this.f268e.unlock();
        return z3;
    }

    /* renamed from: b */
    public final void m202b() {
        this.f268e.lock();
        if (!(this.f269f == null || !this.f272i || this.f271h)) {
            this.f269f.start();
        }
        this.f268e.unlock();
    }

    /* renamed from: c */
    public final void m203c() {
        this.f268e.lock();
        if (this.f269f != null) {
            this.f269f.updateVideoLayout();
        }
        this.f268e.unlock();
    }

    protected final void runOnUiThread(Runnable runnable) {
        if (this.f265b instanceof Activity) {
            ((Activity) this.f265b).runOnUiThread(runnable);
        } else {
            C0243g.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
