package com.tapjoy.internal;

import android.os.Handler;
import android.os.Looper;

public class gd implements fr {
    /* renamed from: a */
    private static final gd f7869a = new C29381();
    /* renamed from: b */
    private final fr f7870b;
    /* renamed from: c */
    private final bf f7871c;

    /* renamed from: com.tapjoy.internal.gd$1 */
    static class C29381 extends gd {
        C29381() {
            super();
        }

        /* renamed from: a */
        public final void mo6136a(String str) {
        }

        /* renamed from: b */
        public final void mo6139b(String str) {
        }

        /* renamed from: c */
        public final void mo6140c(String str) {
        }

        /* renamed from: d */
        public final void mo6141d(String str) {
        }

        /* renamed from: a */
        public final void mo6137a(String str, fo foVar) {
        }

        /* renamed from: a */
        public final void mo6138a(String str, String str2, fo foVar) {
        }
    }

    /* renamed from: a */
    public static gd m7853a(fr frVar) {
        Object obj;
        if (frVar instanceof gd) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            throw new IllegalArgumentException();
        } else if (frVar != null) {
            return new gd(frVar);
        } else {
            return f7869a;
        }
    }

    private gd() {
        this.f7870b = null;
        this.f7871c = null;
    }

    private gd(fr frVar) {
        Handler a;
        this.f7870b = frVar;
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            cs.m7336a(myLooper);
            a = myLooper == Looper.getMainLooper() ? C2998x.m8228a() : new Handler(myLooper);
        } else {
            a = null;
        }
        if (a != null) {
            this.f7871c = C2998x.m8229a(a);
            new Object[1][0] = a.getLooper();
        } else if (Thread.currentThread() == fu.m7790b()) {
            this.f7871c = fu.f7795a;
        } else {
            this.f7871c = C2998x.m8229a(C2998x.m8228a());
        }
    }

    /* renamed from: a */
    public void mo6136a(final String str) {
        this.f7871c.mo6287a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ gd f7873b;

            public final void run() {
                this.f7873b.f7870b.mo6136a(str);
            }
        });
    }

    /* renamed from: b */
    public void mo6139b(final String str) {
        this.f7871c.mo6287a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ gd f7875b;

            public final void run() {
                this.f7875b.f7870b.mo6139b(str);
            }
        });
    }

    /* renamed from: c */
    public void mo6140c(final String str) {
        this.f7871c.mo6287a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ gd f7877b;

            public final void run() {
                this.f7877b.f7870b.mo6140c(str);
            }
        });
    }

    /* renamed from: d */
    public void mo6141d(final String str) {
        this.f7871c.mo6287a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ gd f7879b;

            public final void run() {
                this.f7879b.f7870b.mo6141d(str);
            }
        });
    }

    /* renamed from: a */
    public void mo6137a(final String str, final fo foVar) {
        this.f7871c.mo6287a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ gd f7882c;

            public final void run() {
                this.f7882c.f7870b.mo6137a(str, foVar);
            }
        });
    }

    /* renamed from: a */
    public void mo6138a(final String str, final String str2, final fo foVar) {
        this.f7871c.mo6287a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ gd f7886d;

            public final void run() {
                this.f7886d.f7870b.mo6138a(str, str2, foVar);
            }
        });
    }
}
