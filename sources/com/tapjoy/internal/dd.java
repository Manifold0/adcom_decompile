package com.tapjoy.internal;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.internal.di.C2861a;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public abstract class dd implements di {
    /* renamed from: a */
    private static final Logger f7317a = Logger.getLogger(dd.class.getName());
    /* renamed from: b */
    private final di f7318b = new C28561(this);

    /* renamed from: com.tapjoy.internal.dd$1 */
    class C28561 extends df {
        /* renamed from: a */
        final /* synthetic */ dd f7315a;

        /* renamed from: com.tapjoy.internal.dd$1$1 */
        class C28551 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C28561 f7309a;

            C28551(C28561 c28561) {
                this.f7309a = c28561;
            }

            public final void run() {
                try {
                    Object obj;
                    this.f7309a.f7315a.mo6278a();
                    this.f7309a.m7364c();
                    if (this.f7309a.mo6209f() == C2861a.RUNNING) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        this.f7309a.f7315a.mo6279b();
                    }
                    this.f7309a.f7315a.mo6280c();
                    this.f7309a.m7365d();
                } catch (Throwable th) {
                    this.f7309a.m7362a(th);
                    RuntimeException a = cu.m7340a(th);
                }
            }
        }

        C28561(dd ddVar) {
            this.f7315a = ddVar;
        }

        /* renamed from: a */
        protected final void mo6210a() {
            new C28572(this.f7315a).execute(new C28551(this));
        }

        /* renamed from: b */
        protected final void mo6211b() {
            this.f7315a.mo6281d();
        }
    }

    /* renamed from: com.tapjoy.internal.dd$2 */
    class C28572 implements Executor {
        /* renamed from: a */
        final /* synthetic */ dd f7316a;

        C28572(dd ddVar) {
            this.f7316a = ddVar;
        }

        public final void execute(Runnable command) {
            new Thread(command, this.f7316a.getClass().getSimpleName()).start();
        }
    }

    /* renamed from: b */
    public abstract void mo6279b();

    /* renamed from: a */
    public void mo6278a() {
    }

    /* renamed from: c */
    public void mo6280c() {
    }

    /* renamed from: d */
    public void mo6281d() {
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + mo6209f() + RequestParameters.RIGHT_BRACKETS;
    }

    /* renamed from: e */
    public final dh mo6208e() {
        return this.f7318b.mo6208e();
    }

    /* renamed from: f */
    public final C2861a mo6209f() {
        return this.f7318b.mo6209f();
    }
}
