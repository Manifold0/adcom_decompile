// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.d;

import java.util.Hashtable;
import com.kongregate.android.internal.util.c;
import com.kongregate.android.internal.util.a;
import java.util.HashMap;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
import com.kongregate.android.internal.util.i;
import java.io.IOException;
import java.io.Closeable;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import android.os.Environment;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Random;
import com.kongregate.android.internal.util.g;
import com.kongregate.android.internal.util.j;
import java.util.Arrays;
import java.util.Properties;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class b
{
    private static final AtomicReference<String> A;
    public static final String a = "analytics.kongregate.io";
    public static final d b;
    public static final d c;
    private static final AtomicReference<b> d;
    private static final a e;
    private static final a f;
    private static final b g;
    private static final a h;
    private static final a i;
    private static final a j;
    private static final b k;
    private static final a l;
    private static final a m;
    private static final a n;
    private static final a o;
    private static final a p;
    private static final a q;
    private static final a r;
    private static final b s;
    private static final a t;
    private static final a u;
    private static final c[] v;
    private static final a w;
    private static final d x;
    private static final List<String> y;
    private static final List<String> z;
    private final String B;
    private final Properties C;
    private final String D;
    private final int E;
    private final String F;
    
    static {
        d = new AtomicReference<b>(null);
        b = new d("app.domain", "m.kongregate.com");
        c = new d("app.fb_id", "218282488189771");
        e = new a("app.flash_required", true);
        f = new a("app.use_mopub", false);
        g = new b("app.rate_games_nag_rate", 5);
        h = new a("app.ssl.httpclient", false);
        i = new a("app.crash", false);
        j = new a("app.prerolls", false);
        k = new b("app.prerolls.chance", 10);
        l = new a("app.prerolls.test", false);
        m = new a("app.prerolls.vdopia", false);
        n = new a("app.prerolls.brightroll", false);
        o = new a("app.prerolls.greystripe", false);
        p = new a("app.prerolls.admob", false);
        q = new a("app.prerolls.admob.dfp", false);
        r = new a("app.prerolls.mobclix", false);
        s = new b("app.share_challenge_nag_interval", 20);
        t = new a("app.power_up_rewards", true);
        u = new a("app.use_fixtures", true);
        v = new c[] { (c)com.kongregate.o.d.b.b, (c)com.kongregate.o.d.b.e, (c)com.kongregate.o.d.b.f, (c)com.kongregate.o.d.b.g, (c)com.kongregate.o.d.b.h, (c)com.kongregate.o.d.b.i, (c)com.kongregate.o.d.b.j, (c)com.kongregate.o.d.b.k, (c)com.kongregate.o.d.b.l, (c)com.kongregate.o.d.b.m, (c)com.kongregate.o.d.b.n, (c)com.kongregate.o.d.b.o, (c)com.kongregate.o.d.b.p, (c)com.kongregate.o.d.b.q, (c)com.kongregate.o.d.b.r, (c)com.kongregate.o.d.b.s, (c)com.kongregate.o.d.b.t, (c)com.kongregate.o.d.b.u };
        w = new a("delta_isinclusive", false);
        x = new d("delta_filterlist", "{}");
        y = Arrays.asList(((c)com.kongregate.o.d.b.e).a(), ((c)com.kongregate.o.d.b.b).a(), ((c)com.kongregate.o.d.b.i).a(), ((c)com.kongregate.o.d.b.u).a());
        z = Arrays.asList(((c)com.kongregate.o.d.b.e).a(), ((c)com.kongregate.o.d.b.b).a(), ((c)com.kongregate.o.d.b.i).a(), ((c)com.kongregate.o.d.b.g).a(), ((c)com.kongregate.o.d.b.h).a());
        A = new AtomicReference<String>("");
    }
    
    private b(String c, final Properties properties) {
        com.kongregate.android.internal.util.j.b("GlobalConfiguration initialized!");
        this.C = new Properties(properties);
        this.B = c;
        this.D = com.kongregate.android.internal.util.g.b("admin_preferences").getString("amin.host.override", "");
        this.E = com.kongregate.android.internal.util.g.b("admin_preferences").getInt("admin.manifest.override", 0);
        this.H();
        c = this.c();
        if (c.indexOf("m.") == 0) {
            this.F = c.replaceFirst("m\\.", "chat\\.");
            return;
        }
        this.F = c;
    }
    
    public static int B() {
        int n;
        if ((n = com.kongregate.android.internal.util.g.e().getInt("split_test_seed", -1)) < 0) {
            n = Math.abs(new Random(System.currentTimeMillis()).nextInt());
            com.kongregate.android.internal.util.g.e().edit().putInt("split_test_seed", n).commit();
        }
        return n;
    }
    
    public static String C() {
        return StringUtils.f(com.kongregate.o.d.b.A.get());
    }
    
    private void H() {
        final String string = Environment.getExternalStorageDirectory().toString() + "/kongregate/" + this.B + "/" + "config.properties";
        this.C.clear();
        while (true) {
            if (com.kongregate.android.internal.util.g.e(string)) {
                try {
                    final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(string), 1024);
                    this.C.load(bufferedInputStream);
                    com.kongregate.android.internal.util.g.a((Closeable)bufferedInputStream);
                    com.kongregate.android.internal.util.j.b("Loaded configuration file at: " + string);
                    if (com.kongregate.o.d.b.i.a(this.C)) {
                        throw new RuntimeException("Forced a crash!");
                    }
                }
                catch (IOException ex) {
                    com.kongregate.android.internal.util.j.d("IOException while loading global configuration", ex);
                    continue;
                }
                this.a(com.kongregate.android.internal.util.i.c(com.kongregate.android.internal.util.g.e().getString("remote.configuration", "{}")), false);
                return;
            }
            continue;
        }
    }
    
    public static b a() {
        synchronized (b.class) {
            return com.kongregate.o.d.b.d.get();
        }
    }
    
    public static b a(final String s) {
        synchronized (b.class) {
            return a(s, new Properties());
        }
    }
    
    public static b a(final String s, final Properties properties) {
        synchronized (b.class) {
            com.kongregate.o.d.b.d.set(new b(s, properties));
            return a();
        }
    }
    
    public static void a(final JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        com.kongregate.android.internal.util.g.e().edit().putString("remote.configuration", jsonObject2.toString()).commit();
        final b a = a();
        if (a != null) {
            com.kongregate.android.internal.util.j.a("Updating dynamic remote properties");
            a.a(jsonObject2, true);
        }
    }
    
    private void a(final JSONObject jsonObject, final boolean b) {
        if (jsonObject != null) {
            com.kongregate.android.internal.util.j.a("Updating remote properties");
            final Iterator keys = jsonObject.keys();
            while (keys != null && keys.hasNext()) {
                final String s = keys.next();
                if (!this.C.containsKey(s) && !b.y.contains(s) && (!b || !b.z.contains(s))) {
                    final String optString = jsonObject.optString(s, (String)null);
                    if (optString == null) {
                        continue;
                    }
                    ((Hashtable<String, String>)this.C).put(s, optString);
                }
            }
            final Iterator<Map.Entry<String, String>> iterator = this.h().entrySet().iterator();
            while (iterator.hasNext()) {
                com.kongregate.android.internal.util.j.a("Adding split test: " + iterator.next().getKey());
            }
        }
    }
    
    public static void b(final String s) {
        com.kongregate.o.d.b.A.set(s);
    }
    
    public boolean A() {
        return com.kongregate.o.d.b.t.a(this.C);
    }
    
    public String D() {
        return this.D;
    }
    
    public int E() {
        return this.E;
    }
    
    public Boolean F() {
        return com.kongregate.o.d.b.w.a(this.C);
    }
    
    public List<String> G() {
        final String a = com.kongregate.o.d.b.x.a(this.C);
        final ArrayList<String> list = new ArrayList<String>();
        try {
            return (List<String>)new Gson().fromJson(a, new TypeToken<List<String>>() {}.getType());
        }
        catch (JsonSyntaxException ex) {
            com.kongregate.android.internal.util.j.c("Failed to parse Delta filter list");
            return list;
        }
    }
    
    public void a(final int n) {
        com.kongregate.android.internal.util.j.c("Setting admin manifest override:");
        if (n > 0) {
            com.kongregate.android.internal.util.g.b("admin_preferences").edit().putInt("admin.manifest.override", n).commit();
            return;
        }
        com.kongregate.android.internal.util.g.b("admin_preferences").edit().remove("admin.manifest.override").commit();
    }
    
    public void b() {
        com.kongregate.android.internal.util.g.e().edit().putString("remote.configuration", "{}").commit();
        this.H();
    }
    
    public String c() {
        final String d = this.D();
        if (!StringUtils.a((CharSequence)d)) {
            return d;
        }
        return com.kongregate.o.d.b.b.a(this.C);
    }
    
    public void c(final String s) {
        com.kongregate.android.internal.util.j.c("Setting admin host overide: " + s);
        com.kongregate.android.internal.util.g.b("admin_preferences").edit().putString("amin.host.override", s).commit();
    }
    
    public String d() {
        return com.kongregate.o.d.b.c.a(this.C);
    }
    
    public String e() {
        return this.F;
    }
    
    public boolean f() {
        return "m.kongregate.com".equals(this.c());
    }
    
    public boolean g() {
        return com.kongregate.o.d.b.u.a(this.C);
    }
    
    public Map<String, String> h() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final c[] v = com.kongregate.o.d.b.v;
        for (int length = v.length, i = 0; i < length; ++i) {
            final c c = v[i];
            if (c instanceof a) {
                final a a = (a)c;
                final boolean booleanValue = a.a(this.C);
                if (((c)a).c()) {
                    hashMap.put("split." + ((c)a).a(), String.valueOf(booleanValue));
                }
            }
        }
        return hashMap;
    }
    
    public String i() {
        final Iterator<Map.Entry<String, String>> iterator = this.h().entrySet().iterator();
        String string = "";
        while (iterator.hasNext()) {
            final Map.Entry<String, String> entry = iterator.next();
            final StringBuilder append = new StringBuilder().append(string);
            String string2;
            if (string.length() == 0) {
                string2 = "";
            }
            else {
                string2 = "&" + StringUtils.h(entry.getKey()) + "=" + StringUtils.h(entry.getValue());
            }
            string = append.append(string2).toString();
        }
        return string;
    }
    
    public boolean j() {
        final String c = this.c();
        return "m.kongshred.com".equals(c) || "m.kongregatetrunk.com".equals(c) || "m.kongregatestage.com".equals(c) || "m.kongregate.com".equals(c) || "m.kongbus.com".equals(c);
    }
    
    public boolean k() {
        return this.f();
    }
    
    public int l() {
        return com.kongregate.o.d.b.g.a(this.C);
    }
    
    public boolean m() {
        return com.kongregate.o.d.b.e.a(this.C);
    }
    
    public boolean n() {
        return false;
    }
    
    public boolean o() {
        return com.kongregate.o.d.b.f.a(this.C);
    }
    
    @Deprecated
    public boolean p() {
        return com.kongregate.o.d.b.h.a(this.C);
    }
    
    public boolean q() {
        return com.kongregate.o.d.b.j.a(this.C);
    }
    
    public boolean r() {
        return !com.kongregate.android.internal.util.a.a() && com.kongregate.o.d.b.m.a(this.C);
    }
    
    public boolean s() {
        return !com.kongregate.android.internal.util.a.a() && com.kongregate.o.d.b.n.a(this.C);
    }
    
    public boolean t() {
        return !com.kongregate.android.internal.util.a.b() && com.kongregate.o.d.b.o.a(this.C);
    }
    
    public boolean u() {
        return com.kongregate.o.d.b.r.a(this.C);
    }
    
    public boolean v() {
        return com.kongregate.o.d.b.p.a(this.C);
    }
    
    public boolean w() {
        return com.kongregate.o.d.b.q.a(this.C);
    }
    
    public double x() {
        if (!this.q()) {
            return 0.0;
        }
        return com.kongregate.o.d.b.k.a(this.C) / 100.0;
    }
    
    public boolean y() {
        return com.kongregate.o.d.b.l.a(this.C);
    }
    
    public int z() {
        return com.kongregate.o.d.b.s.a(this.C);
    }
    
    public static class a extends c<Boolean>
    {
        public a(final String s, final Boolean b) {
            super(s, b);
        }
        
        public Boolean a(Properties lowerCase) {
            boolean b = true;
            final String c = ((c)this).c(lowerCase);
            this.a = false;
            if (c != null) {
                lowerCase = (Properties)c.toLowerCase();
                if ("false".equals(lowerCase) || "true".equals(lowerCase)) {
                    return Boolean.valueOf((String)lowerCase);
                }
                try {
                    if (com.kongregate.android.internal.util.c.e() != null) {
                        final int int1 = Integer.parseInt((String)lowerCase);
                        this.a = true;
                        if ((Math.abs(((c)this).a().hashCode()) + com.kongregate.o.d.b.B()) % 100L >= int1) {
                            b = false;
                        }
                        return b;
                    }
                }
                catch (NumberFormatException ex) {
                    com.kongregate.android.internal.util.j.c("Invalid value for option " + ((c)this).a() + ": " + (String)lowerCase);
                }
            }
            return ((c<Boolean>)this).b();
        }
    }
    
    public static class b extends c<Integer>
    {
        public b(final String s, final Integer n) {
            super(s, n);
        }
        
        public Integer a(Properties c) {
            c = (Properties)((c)this).c(c);
            if (c != null) {
                try {
                    return Integer.parseInt((String)c);
                }
                catch (NumberFormatException ex) {
                    com.kongregate.android.internal.util.j.c("Invalid value for option " + ((c)this).a() + ": " + (String)c);
                }
            }
            return ((c<Integer>)this).b();
        }
    }
    
    public abstract static class c<T>
    {
        protected boolean a;
        private final String b;
        private final T c;
        
        public c(final String b, final T c) {
            if (b == null) {
                throw new IllegalArgumentException("Name must not be null");
            }
            this.b = b;
            this.c = c;
            this.a = false;
        }
        
        public String a() {
            return this.b;
        }
        
        protected T b() {
            return this.c;
        }
        
        public abstract T b(final Properties p0);
        
        protected String c(final Properties properties) {
            final String a = this.a();
            final String string = a + "." + com.kongregate.o.d.b.C();
            if (properties.containsKey(string)) {
                com.kongregate.android.internal.util.j.a("GlobalConfig: returning build specific value: " + string);
                return properties.getProperty(string);
            }
            return properties.getProperty(a);
        }
        
        public boolean c() {
            return this.a;
        }
    }
    
    public static class d extends c<String>
    {
        public d(final String s, final String s2) {
            super(s, s2);
        }
        
        public String a(final Properties properties) {
            final String c = ((c)this).c(properties);
            if (c != null) {
                return c;
            }
            return ((c<String>)this).b();
        }
    }
}
