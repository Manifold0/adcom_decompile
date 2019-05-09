package com.kongregate.p000o.p007d;

import android.os.Environment;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0545c;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.d.b */
public class C0635b {
    /* renamed from: A */
    private static final AtomicReference<String> f969A = new AtomicReference("");
    /* renamed from: a */
    public static final String f970a = "analytics.kongregate.io";
    /* renamed from: b */
    public static final C0634d f971b = new C0634d("app.domain", "m.kongregate.com");
    /* renamed from: c */
    public static final C0634d f972c = new C0634d("app.fb_id", "218282488189771");
    /* renamed from: d */
    private static final AtomicReference<C0635b> f973d = new AtomicReference(null);
    /* renamed from: e */
    private static final C0632a f974e = new C0632a("app.flash_required", Boolean.valueOf(true));
    /* renamed from: f */
    private static final C0632a f975f = new C0632a("app.use_mopub", Boolean.valueOf(false));
    /* renamed from: g */
    private static final C0633b f976g = new C0633b("app.rate_games_nag_rate", Integer.valueOf(5));
    /* renamed from: h */
    private static final C0632a f977h = new C0632a("app.ssl.httpclient", Boolean.valueOf(false));
    /* renamed from: i */
    private static final C0632a f978i = new C0632a("app.crash", Boolean.valueOf(false));
    /* renamed from: j */
    private static final C0632a f979j = new C0632a("app.prerolls", Boolean.valueOf(false));
    /* renamed from: k */
    private static final C0633b f980k = new C0633b("app.prerolls.chance", Integer.valueOf(10));
    /* renamed from: l */
    private static final C0632a f981l = new C0632a("app.prerolls.test", Boolean.valueOf(false));
    /* renamed from: m */
    private static final C0632a f982m = new C0632a("app.prerolls.vdopia", Boolean.valueOf(false));
    /* renamed from: n */
    private static final C0632a f983n = new C0632a("app.prerolls.brightroll", Boolean.valueOf(false));
    /* renamed from: o */
    private static final C0632a f984o = new C0632a("app.prerolls.greystripe", Boolean.valueOf(false));
    /* renamed from: p */
    private static final C0632a f985p = new C0632a("app.prerolls.admob", Boolean.valueOf(false));
    /* renamed from: q */
    private static final C0632a f986q = new C0632a("app.prerolls.admob.dfp", Boolean.valueOf(false));
    /* renamed from: r */
    private static final C0632a f987r = new C0632a("app.prerolls.mobclix", Boolean.valueOf(false));
    /* renamed from: s */
    private static final C0633b f988s = new C0633b("app.share_challenge_nag_interval", Integer.valueOf(20));
    /* renamed from: t */
    private static final C0632a f989t = new C0632a("app.power_up_rewards", Boolean.valueOf(true));
    /* renamed from: u */
    private static final C0632a f990u = new C0632a("app.use_fixtures", Boolean.valueOf(true));
    /* renamed from: v */
    private static final C0631c[] f991v = new C0631c[]{f971b, f974e, f975f, f976g, f977h, f978i, f979j, f980k, f981l, f982m, f983n, f984o, f985p, f986q, f987r, f988s, f989t, f990u};
    /* renamed from: w */
    private static final C0632a f992w = new C0632a("delta_isinclusive", Boolean.valueOf(false));
    /* renamed from: x */
    private static final C0634d f993x = new C0634d("delta_filterlist", "{}");
    /* renamed from: y */
    private static final List<String> f994y = Arrays.asList(new String[]{f974e.m987a(), f971b.m987a(), f978i.m987a(), f990u.m987a()});
    /* renamed from: z */
    private static final List<String> f995z = Arrays.asList(new String[]{f974e.m987a(), f971b.m987a(), f978i.m987a(), f976g.m987a(), f977h.m987a()});
    /* renamed from: B */
    private final String f996B;
    /* renamed from: C */
    private final Properties f997C;
    /* renamed from: D */
    private final String f998D = C0558g.m681b("admin_preferences").getString("amin.host.override", "");
    /* renamed from: E */
    private final int f999E = C0558g.m681b("admin_preferences").getInt("admin.manifest.override", 0);
    /* renamed from: F */
    private final String f1000F;

    /* renamed from: com.kongregate.o.d.b$1 */
    class C06301 extends TypeToken<List<String>> {
        /* renamed from: a */
        final /* synthetic */ C0635b f965a;

        C06301(C0635b c0635b) {
            this.f965a = c0635b;
        }
    }

    /* renamed from: com.kongregate.o.d.b$c */
    public static abstract class C0631c<T> {
        /* renamed from: a */
        protected boolean f966a;
        /* renamed from: b */
        private final String f967b;
        /* renamed from: c */
        private final T f968c;

        /* renamed from: b */
        public abstract T mo1255b(Properties properties);

        public C0631c(String str, T t) {
            if (str == null) {
                throw new IllegalArgumentException("Name must not be null");
            }
            this.f967b = str;
            this.f968c = t;
            this.f966a = false;
        }

        /* renamed from: a */
        public String m987a() {
            return this.f967b;
        }

        /* renamed from: b */
        protected T m988b() {
            return this.f968c;
        }

        /* renamed from: c */
        public boolean m991c() {
            return this.f966a;
        }

        /* renamed from: c */
        protected String m990c(Properties properties) {
            String a = m987a();
            String str = a + "." + C0635b.m999C();
            if (!properties.containsKey(str)) {
                return properties.getProperty(a);
            }
            C0562j.m753a("GlobalConfig: returning build specific value: " + str);
            return properties.getProperty(str);
        }
    }

    /* renamed from: com.kongregate.o.d.b$a */
    public static class C0632a extends C0631c<Boolean> {
        /* renamed from: b */
        public /* synthetic */ Object mo1255b(Properties properties) {
            return m992a(properties);
        }

        public C0632a(String str, Boolean bool) {
            super(str, bool);
        }

        /* renamed from: a */
        public Boolean m992a(Properties properties) {
            boolean z = true;
            String c = m990c(properties);
            this.a = false;
            if (c != null) {
                c = c.toLowerCase();
                if ("false".equals(c) || "true".equals(c)) {
                    return Boolean.valueOf(c);
                }
                try {
                    if (C0545c.m625e() != null) {
                        int parseInt = Integer.parseInt(c);
                        this.a = true;
                        if (((long) (Math.abs(m987a().hashCode()) + C0635b.m998B())) % 100 >= ((long) parseInt)) {
                            z = false;
                        }
                        return Boolean.valueOf(z);
                    }
                } catch (NumberFormatException e) {
                    C0562j.m759c("Invalid value for option " + m987a() + ": " + c);
                }
            }
            return (Boolean) m988b();
        }
    }

    /* renamed from: com.kongregate.o.d.b$b */
    public static class C0633b extends C0631c<Integer> {
        /* renamed from: b */
        public /* synthetic */ Object mo1255b(Properties properties) {
            return m994a(properties);
        }

        public C0633b(String str, Integer num) {
            super(str, num);
        }

        /* renamed from: a */
        public Integer m994a(Properties properties) {
            String c = m990c(properties);
            if (c != null) {
                try {
                    return Integer.valueOf(Integer.parseInt(c));
                } catch (NumberFormatException e) {
                    C0562j.m759c("Invalid value for option " + m987a() + ": " + c);
                }
            }
            return (Integer) m988b();
        }
    }

    /* renamed from: com.kongregate.o.d.b$d */
    public static class C0634d extends C0631c<String> {
        /* renamed from: b */
        public /* synthetic */ Object mo1255b(Properties properties) {
            return m996a(properties);
        }

        public C0634d(String str, String str2) {
            super(str, str2);
        }

        /* renamed from: a */
        public String m996a(Properties properties) {
            String c = m990c(properties);
            return c != null ? c : (String) m988b();
        }
    }

    /* renamed from: a */
    public static synchronized C0635b m1002a(String str) {
        C0635b a;
        synchronized (C0635b.class) {
            a = C0635b.m1003a(str, new Properties());
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized C0635b m1003a(String str, Properties properties) {
        C0635b a;
        synchronized (C0635b.class) {
            f973d.set(new C0635b(str, properties));
            a = C0635b.m1001a();
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized C0635b m1001a() {
        C0635b c0635b;
        synchronized (C0635b.class) {
            c0635b = (C0635b) f973d.get();
        }
        return c0635b;
    }

    /* renamed from: a */
    public static void m1004a(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        C0558g.m698e().edit().putString("remote.configuration", jSONObject.toString()).commit();
        C0635b a = C0635b.m1001a();
        if (a != null) {
            C0562j.m753a("Updating dynamic remote properties");
            a.m1005a(jSONObject, true);
        }
    }

    private C0635b(String str, Properties properties) {
        C0562j.m756b("GlobalConfiguration initialized!");
        this.f997C = new Properties(properties);
        this.f996B = str;
        m1000H();
        String c = m1014c();
        if (c.indexOf("m.") == 0) {
            this.f1000F = c.replaceFirst("m\\.", "chat\\.");
        } else {
            this.f1000F = c;
        }
    }

    /* renamed from: b */
    public static void m1006b(String str) {
        f969A.set(str);
    }

    /* renamed from: a */
    private void m1005a(JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            C0562j.m753a("Updating remote properties");
            Iterator keys = jSONObject.keys();
            while (keys != null && keys.hasNext()) {
                String str = (String) keys.next();
                if (!(this.f997C.containsKey(str) || f994y.contains(str))) {
                    if (!z || !f995z.contains(str)) {
                        String optString = jSONObject.optString(str, null);
                        if (optString != null) {
                            this.f997C.put(str, optString);
                        }
                    }
                }
            }
            for (Entry key : m1020h().entrySet()) {
                C0562j.m753a("Adding split test: " + ((String) key.getKey()));
            }
        }
    }

    /* renamed from: b */
    public void m1013b() {
        C0558g.m698e().edit().putString("remote.configuration", "{}").commit();
        m1000H();
    }

    /* renamed from: H */
    private void m1000H() {
        String str = Environment.getExternalStorageDirectory().toString() + "/kongregate/" + this.f996B + "/" + "config.properties";
        this.f997C.clear();
        if (C0558g.m700e(str)) {
            try {
                Closeable bufferedInputStream = new BufferedInputStream(new FileInputStream(str), 1024);
                this.f997C.load(bufferedInputStream);
                C0558g.m674a(bufferedInputStream);
                C0562j.m756b("Loaded configuration file at: " + str);
            } catch (Throwable e) {
                C0562j.m762d("IOException while loading global configuration", e);
            }
        }
        if (f978i.m992a(this.f997C).booleanValue()) {
            throw new RuntimeException("Forced a crash!");
        }
        m1005a(C0561i.m749c(C0558g.m698e().getString("remote.configuration", "{}")), false);
    }

    /* renamed from: c */
    public String m1014c() {
        CharSequence D = m1008D();
        return !StringUtils.m580a(D) ? D : f971b.m996a(this.f997C);
    }

    /* renamed from: d */
    public String m1016d() {
        return f972c.m996a(this.f997C);
    }

    /* renamed from: e */
    public String m1017e() {
        return this.f1000F;
    }

    /* renamed from: f */
    public boolean m1018f() {
        return "m.kongregate.com".equals(m1014c());
    }

    /* renamed from: g */
    public boolean m1019g() {
        return f990u.m992a(this.f997C).booleanValue();
    }

    /* renamed from: h */
    public Map<String, String> m1020h() {
        Map<String, String> hashMap = new HashMap();
        for (C0631c c0631c : f991v) {
            if (c0631c instanceof C0632a) {
                C0632a c0632a = (C0632a) c0631c;
                boolean booleanValue = c0632a.m992a(this.f997C).booleanValue();
                if (c0632a.m991c()) {
                    hashMap.put("split." + c0632a.m987a(), String.valueOf(booleanValue));
                }
            }
        }
        return hashMap;
    }

    /* renamed from: i */
    public String m1021i() {
        String str = "";
        for (Entry entry : m1020h().entrySet()) {
            str = str + (str.length() == 0 ? "" : RequestParameters.AMPERSAND + StringUtils.m596h((String) entry.getKey()) + RequestParameters.EQUAL + StringUtils.m596h((String) entry.getValue()));
        }
        return str;
    }

    /* renamed from: j */
    public boolean m1022j() {
        String c = m1014c();
        return "m.kongshred.com".equals(c) || "m.kongregatetrunk.com".equals(c) || "m.kongregatestage.com".equals(c) || "m.kongregate.com".equals(c) || "m.kongbus.com".equals(c);
    }

    /* renamed from: k */
    public boolean m1023k() {
        return m1018f();
    }

    /* renamed from: l */
    public int m1024l() {
        return f976g.m994a(this.f997C).intValue();
    }

    /* renamed from: m */
    public boolean m1025m() {
        return f974e.m992a(this.f997C).booleanValue();
    }

    /* renamed from: n */
    public boolean m1026n() {
        return false;
    }

    /* renamed from: o */
    public boolean m1027o() {
        return f975f.m992a(this.f997C).booleanValue();
    }

    @Deprecated
    /* renamed from: p */
    public boolean m1028p() {
        return f977h.m992a(this.f997C).booleanValue();
    }

    /* renamed from: q */
    public boolean m1029q() {
        return f979j.m992a(this.f997C).booleanValue();
    }

    /* renamed from: r */
    public boolean m1030r() {
        return !C0542a.m605a() && f982m.m992a(this.f997C).booleanValue();
    }

    /* renamed from: s */
    public boolean m1031s() {
        return !C0542a.m605a() && f983n.m992a(this.f997C).booleanValue();
    }

    /* renamed from: t */
    public boolean m1032t() {
        return !C0542a.m608b() && f984o.m992a(this.f997C).booleanValue();
    }

    /* renamed from: u */
    public boolean m1033u() {
        return f987r.m992a(this.f997C).booleanValue();
    }

    /* renamed from: v */
    public boolean m1034v() {
        return f985p.m992a(this.f997C).booleanValue();
    }

    /* renamed from: w */
    public boolean m1035w() {
        return f986q.m992a(this.f997C).booleanValue();
    }

    /* renamed from: x */
    public double m1036x() {
        if (m1029q()) {
            return ((double) f980k.m994a(this.f997C).intValue()) / 100.0d;
        }
        return 0.0d;
    }

    /* renamed from: y */
    public boolean m1037y() {
        return f981l.m992a(this.f997C).booleanValue();
    }

    /* renamed from: z */
    public int m1038z() {
        return f988s.m994a(this.f997C).intValue();
    }

    /* renamed from: A */
    public boolean m1007A() {
        return f989t.m992a(this.f997C).booleanValue();
    }

    /* renamed from: B */
    public static int m998B() {
        int i = C0558g.m698e().getInt("split_test_seed", -1);
        if (i >= 0) {
            return i;
        }
        i = Math.abs(new Random(System.currentTimeMillis()).nextInt());
        C0558g.m698e().edit().putInt("split_test_seed", i).commit();
        return i;
    }

    /* renamed from: C */
    public static String m999C() {
        return StringUtils.m594f((String) f969A.get());
    }

    /* renamed from: c */
    public void m1015c(String str) {
        C0562j.m759c("Setting admin host overide: " + str);
        C0558g.m681b("admin_preferences").edit().putString("amin.host.override", str).commit();
    }

    /* renamed from: a */
    public void m1012a(int i) {
        C0562j.m759c("Setting admin manifest override:");
        if (i > 0) {
            C0558g.m681b("admin_preferences").edit().putInt("admin.manifest.override", i).commit();
        } else {
            C0558g.m681b("admin_preferences").edit().remove("admin.manifest.override").commit();
        }
    }

    /* renamed from: D */
    public String m1008D() {
        return this.f998D;
    }

    /* renamed from: E */
    public int m1009E() {
        return this.f999E;
    }

    /* renamed from: F */
    public Boolean m1010F() {
        return f992w.m992a(this.f997C);
    }

    /* renamed from: G */
    public List<String> m1011G() {
        String a = f993x.m996a(this.f997C);
        ArrayList arrayList = new ArrayList();
        try {
            return (List) new Gson().fromJson(a, new C06301(this).getType());
        } catch (JsonSyntaxException e) {
            C0562j.m759c("Failed to parse Delta filter list");
            return arrayList;
        }
    }
}
