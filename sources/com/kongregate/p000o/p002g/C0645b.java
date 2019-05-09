package com.kongregate.p000o.p002g;

import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import com.adjust.sdk.Constants;
import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.internal.sdk.C0507l;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0568p;
import com.kongregate.p000o.p001j.C0666a;
import com.kongregate.p000o.p003a.C0578a;
import com.kongregate.p000o.p006c.C0618b;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p006c.C0627e;
import com.kongregate.p000o.p010i.C0657a;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.g.b */
public class C0645b {
    /* renamed from: b */
    private static C0645b f1022b = null;
    /* renamed from: a */
    public final C0468a f1023a = new C06444(this);
    /* renamed from: c */
    private final ScheduledExecutorService f1024c;
    /* renamed from: d */
    private final Context f1025d;
    /* renamed from: e */
    private boolean f1026e = false;
    /* renamed from: f */
    private final C0657a f1027f;

    /* renamed from: com.kongregate.o.g.b$a */
    public static abstract class C0468a {
        /* renamed from: a */
        public abstract void mo1133a(C0646c c0646c, JSONObject jSONObject);

        /* renamed from: a */
        public void mo1185a(C0646c c0646c) {
            C0562j.m761d("HTTP error: " + c0646c.m1098e());
        }

        /* renamed from: b */
        public void mo1186b(C0646c c0646c, JSONObject jSONObject) {
            C0562j.m761d("Response error: " + c0646c.m1095b());
        }
    }

    /* renamed from: com.kongregate.o.g.b$4 */
    class C06444 extends C0468a {
        /* renamed from: a */
        final /* synthetic */ C0645b f1021a;

        C06444(C0645b c0645b) {
            this.f1021a = c0645b;
        }

        /* renamed from: a */
        public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
        }
    }

    /* renamed from: a */
    public static synchronized C0645b m1086a(Context context, String str, long j, String str2) {
        C0645b c0645b;
        synchronized (C0645b.class) {
            C0640a.m1051a();
            C0640a.m1055b().m1069b(str);
            C0640a.m1055b().m1064a(j, str2);
            if (f1022b == null) {
                f1022b = new C0645b(context);
            }
            c0645b = f1022b;
        }
        return c0645b;
    }

    /* renamed from: a */
    public static synchronized C0645b m1085a() {
        C0645b c0645b;
        synchronized (C0645b.class) {
            if (f1022b == null) {
                throw new IllegalStateException("must initialize before first use");
            }
            c0645b = f1022b;
        }
        return c0645b;
    }

    private C0645b(Context context) {
        String str = "KongregateAPI-httpRequestQueue-" + new Random().nextInt() + "-";
        this.f1025d = context;
        this.f1027f = new C0657a(this.f1025d);
        this.f1024c = new C0627e(1, new C0618b(str), new DiscardPolicy(), C0626d.m975g());
    }

    /* renamed from: a */
    public Future<C0646c> m1090a(final String str, final C0468a c0468a) {
        return this.f1024c.submit(new Callable<C0646c>(this) {
            /* renamed from: c */
            final /* synthetic */ C0645b f1010c;

            public /* synthetic */ Object call() throws Exception {
                return m1081a();
            }

            /* renamed from: a */
            public C0646c m1081a() throws Exception {
                if (!this.f1010c.f1027f.m1144b()) {
                    return this.f1010c.m1088a(new C0646c(C0568p.ERROR_NETWORK), c0468a);
                }
                if (str.startsWith(Constants.SCHEME)) {
                    return this.f1010c.m1088a(C0640a.m1055b().m1076f(str), c0468a);
                }
                return this.f1010c.m1088a(C0640a.m1055b().m1074e(str), c0468a);
            }
        });
    }

    /* renamed from: a */
    public Future<C0646c> m1091a(final String str, final Map<String, Object> map, final C0468a c0468a) {
        return this.f1024c.submit(new Callable<C0646c>(this) {
            /* renamed from: d */
            final /* synthetic */ C0645b f1014d;

            public /* synthetic */ Object call() throws Exception {
                return m1082a();
            }

            /* renamed from: a */
            public C0646c m1082a() throws Exception {
                if (!this.f1014d.f1027f.m1144b()) {
                    return this.f1014d.m1088a(new C0646c(C0568p.ERROR_NETWORK), c0468a);
                }
                if (str.startsWith(Constants.SCHEME)) {
                    return this.f1014d.m1088a(C0640a.m1055b().m1061a(str, map), c0468a);
                }
                return this.f1014d.m1088a(C0640a.m1055b().m1067b(str, map), c0468a);
            }
        });
    }

    /* renamed from: a */
    public Future<C0646c> m1092a(String str, Map<String, Object> map, String str2, Map<String, String> map2, C0468a c0468a) {
        final C0468a c0468a2 = c0468a;
        final String str3 = str;
        final Map<String, Object> map3 = map;
        final String str4 = str2;
        final Map<String, String> map4 = map2;
        return this.f1024c.submit(new Callable<C0646c>(this) {
            /* renamed from: f */
            final /* synthetic */ C0645b f1020f;

            public /* synthetic */ Object call() throws Exception {
                return m1083a();
            }

            /* renamed from: a */
            public C0646c m1083a() throws Exception {
                if (!this.f1020f.f1027f.m1144b()) {
                    return this.f1020f.m1088a(new C0646c(C0568p.ERROR_NETWORK), c0468a2);
                }
                if (str3.startsWith(Constants.SCHEME)) {
                    return this.f1020f.m1088a(C0640a.m1055b().m1062a(str3, map3, str4, map4), c0468a2);
                }
                return this.f1020f.m1088a(C0640a.m1055b().m1068b(str3, map3, str4, map4), c0468a2);
            }
        });
    }

    /* renamed from: a */
    private C0646c m1088a(C0646c c0646c, C0468a c0468a) {
        if (c0468a == null) {
            throw new IllegalArgumentException("No handler specified. You may use NullResultHandler");
        }
        if (c0646c.m787f()) {
            this.f1026e = false;
            JSONObject c = c0646c.m1096c();
            if (c == null || !c.optBoolean("success", Boolean.TRUE.booleanValue())) {
                if (C0578a.f789b.equals(c.optString("error", ""))) {
                    C0562j.m759c("session error. clear active user");
                    C0666a.m1145a().m1172c();
                }
                c0468a.mo1186b(c0646c, c);
            } else {
                c0468a.mo1133a(c0646c, c);
            }
        } else {
            C0562j.m759c("HTTP request error: " + c0646c.m1098e());
            if (!this.f1026e) {
                this.f1026e = true;
                LocalBroadcastManager.getInstance(this.f1025d).sendBroadcast(C0507l.m456a(KongregateEvent.SERVICE_UNAVAILABLE));
            }
            c0468a.mo1185a(c0646c);
        }
        return c0646c;
    }

    /* renamed from: b */
    public boolean m1093b() {
        return this.f1027f.m1144b();
    }
}
