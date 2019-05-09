package com.facebook.ads.internal.p025w.p026b;

import android.os.Handler;
import android.support.annotation.VisibleForTesting;

/* renamed from: com.facebook.ads.internal.w.b.e */
public class C2572e {
    /* renamed from: a */
    private final Handler f6330a;
    /* renamed from: b */
    private final C2338a f6331b;
    /* renamed from: c */
    private int f6332c;
    /* renamed from: d */
    private boolean f6333d;
    /* renamed from: e */
    private boolean f6334e;

    /* renamed from: com.facebook.ads.internal.w.b.e$a */
    public interface C2338a {
        /* renamed from: a */
        void mo5586a();

        /* renamed from: a */
        void mo5587a(int i);
    }

    /* renamed from: com.facebook.ads.internal.w.b.e$1 */
    class C25711 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2572e f6329a;

        C25711(C2572e c2572e) {
            this.f6329a = c2572e;
        }

        public void run() {
            if (this.f6329a.m6631c()) {
                C2572e.m6627a(this.f6329a);
                this.f6329a.f6330a.postDelayed(this, 1000);
            }
        }
    }

    public C2572e(int i, C2338a c2338a) {
        this(i, c2338a, new Handler());
    }

    @VisibleForTesting
    C2572e(int i, C2338a c2338a, Handler handler) {
        this.f6333d = false;
        this.f6332c = i;
        this.f6331b = c2338a;
        this.f6330a = handler;
    }

    /* renamed from: a */
    static /* synthetic */ void m6627a(C2572e c2572e) {
        c2572e.f6332c--;
        c2572e.f6331b.mo5587a(c2572e.f6332c);
        if (c2572e.f6332c == 0 && !c2572e.f6334e) {
            c2572e.f6334e = true;
            c2572e.f6331b.mo5586a();
            c2572e.f6333d = false;
        }
    }

    /* renamed from: a */
    public boolean m6629a() {
        if (m6632d() && !this.f6334e) {
            this.f6331b.mo5586a();
        }
        if (m6632d() || m6631c()) {
            return false;
        }
        this.f6333d = true;
        this.f6331b.mo5587a(this.f6332c);
        this.f6330a.postDelayed(new C25711(this), 1000);
        return true;
    }

    /* renamed from: b */
    public boolean m6630b() {
        if (!m6631c()) {
            return false;
        }
        this.f6333d = false;
        return true;
    }

    /* renamed from: c */
    public boolean m6631c() {
        return this.f6333d;
    }

    /* renamed from: d */
    public boolean m6632d() {
        return this.f6332c <= 0;
    }

    /* renamed from: e */
    public int m6633e() {
        return this.f6332c;
    }
}
