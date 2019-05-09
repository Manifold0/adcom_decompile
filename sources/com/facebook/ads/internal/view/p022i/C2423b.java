package com.facebook.ads.internal.view.p022i;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p032d.C1907b;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1820x;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p023b.C2414q;
import com.facebook.ads.internal.view.p022i.p023b.C2415s;
import com.facebook.ads.internal.view.p022i.p023b.C2416t;
import com.facebook.ads.internal.view.p022i.p023b.C2419w;
import com.facebook.ads.internal.view.p022i.p023b.C2420y;
import com.facebook.ads.internal.view.p022i.p023b.C2421z;
import com.tapjoy.TJAdUnitConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.i.b */
public class C2423b extends C2422c {
    /* renamed from: a */
    public int f5843a;
    /* renamed from: b */
    private final C1820x f5844b;
    /* renamed from: c */
    private final C1809f<C2415s> f5845c;
    /* renamed from: d */
    private final C1809f<C2410i> f5846d;
    /* renamed from: e */
    private final C1809f<C2411k> f5847e;
    /* renamed from: f */
    private final C1809f<C2413o> f5848f;
    /* renamed from: g */
    private final C1809f<C2406c> f5849g;
    /* renamed from: h */
    private final C1809f<C2414q> f5850h;
    /* renamed from: i */
    private final C1809f<C2420y> f5851i;
    /* renamed from: j */
    private final C1809f<C2421z> f5852j;
    /* renamed from: k */
    private final C1809f<C2416t> f5853k;
    /* renamed from: l */
    private final C1810n f5854l;
    /* renamed from: m */
    private final C2394a f5855m;
    /* renamed from: n */
    private boolean f5856n;

    /* renamed from: com.facebook.ads.internal.view.i.b$1 */
    class C23951 extends C1820x {
        /* renamed from: a */
        final /* synthetic */ C2423b f5802a;

        C23951(C2423b c2423b) {
            this.f5802a = c2423b;
        }

        /* renamed from: a */
        public void m6183a(C2419w c2419w) {
            this.f5802a.m6224e();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$2 */
    class C23962 extends C1809f<C2416t> {
        /* renamed from: a */
        final /* synthetic */ C2423b f5803a;

        C23962(C2423b c2423b) {
            this.f5803a = c2423b;
        }

        /* renamed from: a */
        public Class<C2416t> mo5359a() {
            return C2416t.class;
        }

        /* renamed from: a */
        public void m6186a(C2416t c2416t) {
            this.f5803a.m6218a(this.f5803a.m6229j(), this.f5803a.m6229j());
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$3 */
    class C23973 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ C2423b f5804a;

        C23973(C2423b c2423b) {
            this.f5804a = c2423b;
        }

        /* renamed from: a */
        public void m6188a(C2412m c2412m) {
            this.f5804a.f5843a = this.f5804a.f5855m.getDuration();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$4 */
    class C23984 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2423b f5805a;

        C23984(C2423b c2423b) {
            this.f5805a = c2423b;
        }

        public void run() {
            this.f5805a.f5855m.getEventBus().m5031b(this.f5805a.f5844b, this.f5805a.f5848f, this.f5805a.f5845c, this.f5805a.f5847e, this.f5805a.f5846d, this.f5805a.f5849g, this.f5805a.f5850h, this.f5805a.f5851i, this.f5805a.f5852j, this.f5805a.f5854l, this.f5805a.f5853k);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$5 */
    class C23995 extends C1809f<C2415s> {
        /* renamed from: a */
        final /* synthetic */ C2423b f5806a;

        C23995(C2423b c2423b) {
            this.f5806a = c2423b;
        }

        /* renamed from: a */
        public Class<C2415s> mo5359a() {
            return C2415s.class;
        }

        /* renamed from: a */
        public void m6191a(C2415s c2415s) {
            this.f5806a.m6225f();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$6 */
    class C24006 extends C1809f<C2410i> {
        /* renamed from: a */
        final /* synthetic */ C2423b f5807a;

        C24006(C2423b c2423b) {
            this.f5807a = c2423b;
        }

        /* renamed from: a */
        public Class<C2410i> mo5359a() {
            return C2410i.class;
        }

        /* renamed from: a */
        public void m6194a(C2410i c2410i) {
            this.f5807a.m6227h();
            this.f5807a.m6219a(c2410i.m6204a(), false, ((double) c2410i.m6204a()) < 2000.0d);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$7 */
    class C24017 extends C1809f<C2411k> {
        /* renamed from: a */
        final /* synthetic */ C2423b f5808a;

        C24017(C2423b c2423b) {
            this.f5808a = c2423b;
        }

        /* renamed from: a */
        public Class<C2411k> mo5359a() {
            return C2411k.class;
        }

        /* renamed from: a */
        public void m6197a(C2411k c2411k) {
            if (this.f5808a.f5856n) {
                this.f5808a.m6228i();
            } else {
                this.f5808a.f5856n = true;
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$8 */
    class C24028 extends C1809f<C2413o> {
        /* renamed from: a */
        final /* synthetic */ C2423b f5809a;

        C24028(C2423b c2423b) {
            this.f5809a = c2423b;
        }

        /* renamed from: a */
        public Class<C2413o> mo5359a() {
            return C2413o.class;
        }

        /* renamed from: a */
        public void m6200a(C2413o c2413o) {
            int a = c2413o.m6204a();
            if (this.f5809a.f5843a <= 0 || a != this.f5809a.f5855m.getDuration() || this.f5809a.f5855m.getDuration() <= this.f5809a.f5843a) {
                this.f5809a.m6217a(a);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.b$9 */
    class C24039 extends C1809f<C2406c> {
        /* renamed from: a */
        final /* synthetic */ C2423b f5810a;

        C24039(C2423b c2423b) {
            this.f5810a = c2423b;
        }

        /* renamed from: a */
        public Class<C2406c> mo5359a() {
            return C2406c.class;
        }

        /* renamed from: a */
        public void m6203a(C2406c c2406c) {
            int a = c2406c.m6204a();
            int b = c2406c.m6207b();
            if (this.f5810a.f5843a > 0 && a == b && b > this.f5810a.f5843a) {
                return;
            }
            if (b >= a + TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL) {
                this.f5810a.m6221b(a);
            } else if (b == 0) {
                this.f5810a.m6221b(this.f5810a.f5843a);
            } else {
                this.f5810a.m6221b(b);
            }
        }
    }

    public C2423b(Context context, C2085c c2085c, C2394a c2394a, String str) {
        this(context, c2085c, c2394a, new ArrayList(), str);
    }

    public C2423b(Context context, C2085c c2085c, C2394a c2394a, String str, @Nullable Bundle bundle) {
        this(context, c2085c, c2394a, new ArrayList(), str, bundle, null);
    }

    public C2423b(Context context, C2085c c2085c, C2394a c2394a, String str, @Nullable Map<String, String> map) {
        this(context, c2085c, c2394a, new ArrayList(), str, null, map);
    }

    public C2423b(Context context, C2085c c2085c, C2394a c2394a, List<C1907b> list, String str) {
        super(context, c2085c, c2394a, list, str);
        this.f5844b = new C23951(this);
        this.f5845c = new C23995(this);
        this.f5846d = new C24006(this);
        this.f5847e = new C24017(this);
        this.f5848f = new C24028(this);
        this.f5849g = new C24039(this);
        this.f5850h = new C1809f<C2414q>(this) {
            /* renamed from: a */
            final /* synthetic */ C2423b f5799a;

            {
                this.f5799a = r1;
            }

            /* renamed from: a */
            public Class<C2414q> mo5359a() {
                return C2414q.class;
            }

            /* renamed from: a */
            public void m6175a(C2414q c2414q) {
                this.f5799a.m6218a(c2414q.m6208a(), c2414q.m6209b());
            }
        };
        this.f5851i = new C1809f<C2420y>(this) {
            /* renamed from: a */
            final /* synthetic */ C2423b f5800a;

            {
                this.f5800a = r1;
            }

            /* renamed from: a */
            public Class<C2420y> mo5359a() {
                return C2420y.class;
            }

            /* renamed from: a */
            public void m6178a(C2420y c2420y) {
                this.f5800a.m6220b();
            }
        };
        this.f5852j = new C1809f<C2421z>(this) {
            /* renamed from: a */
            final /* synthetic */ C2423b f5801a;

            {
                this.f5801a = r1;
            }

            /* renamed from: a */
            public Class<C2421z> mo5359a() {
                return C2421z.class;
            }

            /* renamed from: a */
            public void m6181a(C2421z c2421z) {
                this.f5801a.m6222c();
            }
        };
        this.f5853k = new C23962(this);
        this.f5854l = new C23973(this);
        this.f5856n = false;
        this.f5855m = c2394a;
        this.f5855m.getEventBus().m5029a(this.f5844b, this.f5848f, this.f5845c, this.f5847e, this.f5846d, this.f5849g, this.f5850h, this.f5851i, this.f5852j, this.f5854l, this.f5853k);
    }

    public C2423b(Context context, C2085c c2085c, C2394a c2394a, List<C1907b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        super(context, c2085c, c2394a, list, str, bundle, map);
        this.f5844b = new C23951(this);
        this.f5845c = new C23995(this);
        this.f5846d = new C24006(this);
        this.f5847e = new C24017(this);
        this.f5848f = new C24028(this);
        this.f5849g = new C24039(this);
        this.f5850h = /* anonymous class already generated */;
        this.f5851i = /* anonymous class already generated */;
        this.f5852j = /* anonymous class already generated */;
        this.f5853k = new C23962(this);
        this.f5854l = new C23973(this);
        this.f5856n = false;
        this.f5855m = c2394a;
        this.f5855m.getEventBus().m5029a(this.f5844b, this.f5848f, this.f5845c, this.f5847e, this.f5846d, this.f5849g, this.f5850h, this.f5851i, this.f5852j, this.f5853k);
    }

    /* renamed from: a */
    public void m6244a() {
        this.f5855m.getStateHandler().post(new C23984(this));
    }
}
