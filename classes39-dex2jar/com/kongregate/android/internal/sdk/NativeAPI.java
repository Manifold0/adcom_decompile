// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import java.util.Hashtable;
import android.database.sqlite.SQLiteException;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.kongregate.o.b.e;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.kongregate.android.api.MicrotransactionServices;
import com.kongregate.android.api.MobileServices;
import android.widget.Toast;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.android.api.StatServices;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.KongregateEventListener;
import android.content.SharedPreferences$Editor;
import org.json.JSONArray;
import com.kongregate.android.internal.util.i;
import org.json.JSONException;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import java.io.Serializable;
import com.kongregate.android.api.activities.KongregatePanelActivity;
import android.content.Intent;
import com.kongregate.android.api.KongregateServices;
import org.json.JSONObject;
import java.lang.ref.WeakReference;
import android.os.Bundle;
import android.app.Application;
import android.app.Application$ActivityLifecycleCallbacks;
import android.annotation.TargetApi;
import android.webkit.WebView;
import java.util.Iterator;
import com.kongregate.android.api.KongregateEventBundleListener;
import com.kongregate.android.internal.util.l;
import java.util.concurrent.TimeUnit;
import com.kongregate.o.c.d;
import java.util.Properties;
import com.kongregate.android.internal.util.g;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.kongregate.android.internal.util.j;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedList;
import android.content.Context;
import android.app.Activity;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import android.content.SharedPreferences;
import java.util.concurrent.atomic.AtomicBoolean;
import com.kongregate.android.internal.browser.b;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.ScheduledFuture;
import android.net.Uri;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import com.kongregate.o.d.a;

public class NativeAPI extends com.kongregate.android.internal.sdk.c
{
    private static final com.kongregate.o.d.a H;
    private static final AtomicLong I;
    private static final AtomicInteger J;
    protected static final String k = "kongregate_remote_configuraiton";
    protected static final String l = "gdpr_policy_version";
    protected static final String m = "gdpr_target_registered";
    protected static final String n = "gdpr_target_guests";
    private static final List<Long> o;
    private static final List<Long> p;
    private volatile boolean A;
    private int B;
    private boolean C;
    private volatile Uri D;
    private volatile ScheduledFuture<?> E;
    private final AtomicReference<ScheduledFuture<?>> F;
    private final a G;
    private volatile com.kongregate.o.e.c q;
    private final Map<String, Object> r;
    private volatile com.kongregate.android.internal.browser.b s;
    private final AtomicBoolean t;
    private final List<String> u;
    private volatile SharedPreferences v;
    private b w;
    private final AtomicLong x;
    private final AtomicBoolean y;
    private volatile CountDownLatch z;
    
    static {
        o = Arrays.asList(185301L, 180045L, 192193L, 184565L);
        p = Arrays.asList(179960L, 180048L, 186939L);
        H = new com.kongregate.o.d.a();
        I = new AtomicLong(0L);
        J = new AtomicInteger(0);
    }
    
    public NativeAPI(final Activity activity, final long n, String s, final Map<String, Object> map) {
        super((Context)activity, n, s);
        this.t = new AtomicBoolean(false);
        this.u = new LinkedList<String>();
        this.x = new AtomicLong(0L);
        this.y = new AtomicBoolean(false);
        this.z = null;
        this.A = false;
        this.B = 0;
        this.C = false;
        this.D = null;
        this.E = null;
        this.F = new AtomicReference<ScheduledFuture<?>>(null);
        this.G = new a();
        this.Q();
        if (map != null) {
            this.r = Collections.unmodifiableMap((Map<? extends String, ?>)com.kongregate.android.internal.sdk.k.a(map));
            final String value = this.r.get("domain");
            if (value instanceof String) {
                s = value;
            }
            else {
                s = "m.kongregate.com";
            }
        }
        else {
            this.r = Collections.unmodifiableMap((Map<? extends String, ?>)new HashMap<String, Object>());
            s = "m.kongregate.com";
        }
        if (com.kongregate.android.internal.sdk.b.a(this.r, "crashlytics_logging", false)) {
            com.kongregate.android.internal.util.j.b(true);
        }
        if (this.h()) {
            com.kongregate.android.internal.util.b.a("kong_sdk_version", "3.0.5");
        }
        com.kongregate.android.internal.util.j.b("Kongregate NativeAPI initialized, domain: " + s + ", version: " + "3.0.5" + ", feature_set: " + NativeAPI.H.a());
        final LocalBroadcastReciever localBroadcastReciever = new LocalBroadcastReciever();
        LocalBroadcastManager.getInstance(this.getApplicationContext()).registerReceiver((BroadcastReceiver)localBroadcastReciever, new IntentFilter("com.kongregate.android.internal.sdk.BroadcastAPIEvent"));
        LocalBroadcastManager.getInstance(this.getApplicationContext()).registerReceiver((BroadcastReceiver)localBroadcastReciever, new IntentFilter("com.kongregate.android.internal.sdk.AlertDismissEvent"));
        this.R();
        this.T();
        this.a(activity);
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.util.g.a(NativeAPI.this.c);
                final Properties properties = new Properties();
                ((Hashtable<String, String>)properties).put(((com.kongregate.o.d.b.c)com.kongregate.o.d.b.b).a(), s);
                final String value = NativeAPI.this.r.get(((com.kongregate.o.d.b.c)com.kongregate.o.d.b.c).a());
                if (value instanceof String) {
                    final String s = value;
                    if (s.length() > 0) {
                        ((Hashtable<String, String>)properties).put(((com.kongregate.o.d.b.c)com.kongregate.o.d.b.c).a(), s);
                        com.kongregate.android.internal.util.j.b("Using FB App ID override: " + s);
                    }
                }
                final com.kongregate.o.d.b a = com.kongregate.o.d.b.a("kong-api-" + n, properties);
                NativeAPI.this.v = NativeAPI.this.c.getSharedPreferences("kongregate_remote_configuraiton", 0);
                NativeAPI.this.q = new p(NativeAPI.this.c, a.c());
                com.kongregate.o.e.b.a(NativeAPI.this.q.getWritableDatabase());
                NativeAPI.this.H();
                com.kongregate.android.internal.browser.b.a(NativeAPI.this.c);
                com.kongregate.o.g.b.a(NativeAPI.this.getApplicationContext(), "HttpClient/4.0 KongregateMobileApi/3.0.5", n, NativeAPI.this.P());
                NativeAPI.this.E().a(NativeAPI.this.c, (com.kongregate.o.j.a.a)new com.kongregate.o.j.a.a() {
                    @Override
                    public void a() {
                        NativeAPI.this.u().b(new HashMap<String, Object>());
                    }
                    
                    @Override
                    public void a(final String s) {
                        while (true) {
                            synchronized (NativeAPI.this.u) {
                                if (NativeAPI.this.t.get()) {
                                    NativeAPI.this.a(s);
                                    if ("KONG_API_EVENT_USER_CHANGED".equals(s)) {
                                        d.a(new d());
                                    }
                                    else if ("KONG_API_EVENT_LOGIN_COMPLETE".equals(s)) {
                                        NativeAPI.this.y();
                                    }
                                    return;
                                }
                            }
                            final String s2;
                            j.b("Queueing pre-ready event: " + s2);
                            NativeAPI.this.u.add(s2);
                        }
                    }
                });
                NativeAPI.this.E().b();
                NativeAPI.this.d(activity);
                NativeAPI.this.E = com.kongregate.o.c.d.a(5L, 15L, TimeUnit.SECONDS, new d());
                if (NativeAPI.H.c()) {
                    com.kongregate.android.internal.browser.b.a(com.kongregate.android.internal.util.l.a(NativeAPI.this.v, "panel_reload_delay_minutes", 120));
                    com.kongregate.o.c.d.a(15L, TimeUnit.SECONDS, new Runnable() {
                        @Override
                        public void run() {
                            NativeAPI.this.O();
                        }
                    });
                }
                NativeAPI.this.addEventBundleListener(new KongregateEventBundleListener() {
                    @Override
                    public void onKongregateEventBundle(final String s, final String s2) {
                        if ("KONG_API_EVENT_USER_CHANGED".equals(s) && NativeAPI.this.h()) {
                            com.kongregate.android.internal.util.b.a("kong_username", NativeAPI.this.E().h());
                        }
                    }
                });
                com.kongregate.o.c.d.b(new Runnable() {
                    @Override
                    public void run() {
                        NativeAPI.this.x();
                        synchronized (NativeAPI.this.u) {
                            NativeAPI.this.t.set(true);
                            com.kongregate.android.internal.util.j.b("READY: true");
                            NativeAPI.this.a("KONG_API_EVENT_READY");
                            final Iterator<String> iterator = NativeAPI.this.u.iterator();
                            while (iterator.hasNext()) {
                                NativeAPI.this.a(iterator.next());
                            }
                        }
                    }
                    // monitorexit(list)
                });
                if (NativeAPI.this.h()) {
                    com.kongregate.android.internal.util.b.a("kong_username", NativeAPI.this.E().h());
                }
            }
        });
        this.c((Context)activity);
    }
    
    private void O() {
        if (!NativeAPI.H.c()) {
            return;
        }
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                NativeAPI.this.s = com.kongregate.android.internal.browser.b.a(NativeAPI.this.c, NativeAPI.this.f(), NativeAPI.this.m(), NativeAPI.this.o(), NativeAPI.this.n());
            }
        });
    }
    
    private String P() {
        if (this.m()) {
            return "pw";
        }
        return "";
    }
    
    private void Q() {
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                com.kongregate.o.c.a.a(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, false);
            }
        });
    }
    
    @TargetApi(19)
    private void R() {
        if (Boolean.TRUE.equals(this.r.get("debug_webview")) && com.kongregate.android.internal.util.a.a(19)) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }
    
    private boolean S() {
        return com.kongregate.android.internal.util.a.a(14) && !Boolean.FALSE.equals(this.r.get("manage_lifecycle"));
    }
    
    @TargetApi(14)
    private void T() {
        this.C = com.kongregate.android.internal.sdk.b.a(this.r, "strict_lifecycle", false);
        if (!this.S()) {
            com.kongregate.android.internal.util.j.b("Not managing application lifecycle");
            return;
        }
        final Application u = this.U();
        if (u != null) {
            com.kongregate.android.internal.util.j.b("Registering lifecycle callbacks");
            u.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)(this.w = new b()));
            return;
        }
        com.kongregate.android.internal.util.j.c("KONGREGATE CONFIGURATION WARNING: Could not get application instance. Try passing an Activity into the SDK initialize function");
    }
    
    private Application U() {
        if (this.c instanceof Activity) {
            return ((Activity)this.c).getApplication();
        }
        final Context applicationContext = this.c.getApplicationContext();
        if (applicationContext instanceof Application) {
            return (Application)applicationContext;
        }
        return null;
    }
    
    private void V() {
        ((o)this.g).i();
        if (this.E != null && !this.E.isCancelled()) {
            this.E.cancel(false);
        }
        if (this.s != null) {
            this.s.c();
        }
    }
    
    private void W() {
        this.u().j();
        if (this.E != null && this.E.isCancelled()) {
            this.E = com.kongregate.o.c.d.a(5L, 15L, TimeUnit.SECONDS, new d());
        }
        if (this.s != null) {
            this.s.d();
        }
    }
    
    private void a(final Activity activity, final Bundle bundle) {
        this.a(activity);
        this.u().a(activity, bundle, this.D);
        activity.getIntent();
    }
    
    private void b(final Context context) {
        --this.B;
        if (this.B > 0 && this.C) {
            com.kongregate.android.internal.util.j.a("Native API - onPause: other activities are still active. do not pause.");
        }
        else if (this.t.get()) {
            com.kongregate.android.internal.util.j.a("Native API - onPause: ready");
            this.z = new CountDownLatch(1);
            com.kongregate.o.c.d.a(new Runnable() {
                @Override
                public void run() {
                    NativeAPI.this.A = false;
                    while (true) {
                        try {
                            com.kongregate.android.internal.util.j.a("Native API - onPause: waiting to pause");
                            NativeAPI.this.A = !NativeAPI.this.z.await(1L, TimeUnit.SECONDS);
                            com.kongregate.android.internal.util.j.a("Native API - onPause: pausing: " + NativeAPI.this.A);
                            if (NativeAPI.this.A) {
                                NativeAPI.this.G.b();
                                NativeAPI.this.V();
                                if (NativeAPI.this.E().f()) {
                                    NativeAPI.this.E().a(false);
                                }
                                NativeAPI.this.e("app-background");
                            }
                        }
                        catch (InterruptedException ex) {
                            com.kongregate.android.internal.util.j.c("Native API - onPause: pause latch interrupted: ", ex);
                            NativeAPI.this.A = true;
                            continue;
                        }
                        break;
                    }
                }
            });
            this.u().h();
        }
    }
    
    private void c(final Context context) {
        if (context instanceof Activity && this.a("allow_immersive_mode", Boolean.valueOf(true))) {
            com.kongregate.o.c.d.b(new Runnable() {
                final /* synthetic */ WeakReference a = new WeakReference((T)context);
                
                @Override
                public void run() {
                    com.kongregate.android.internal.util.a.a((Activity)this.a.get(), true);
                }
            });
        }
    }
    
    private void d(final Activity activity) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>(this.r);
        hashMap.put("_keen_enabled_internal", this.i());
        hashMap.put("_swrve_enabled_internal", this.j());
        hashMap.put("_adjust_enabled_internal", this.p());
        hashMap.put("_adx_enabled_internal", this.q());
        hashMap.put("_delta_enabled_internal", this.k());
        hashMap.put("_kong_analytics_enabled_internal", this.l());
        this.u().a(activity, (Map<String, Object>)Collections.unmodifiableMap((Map<?, ?>)hashMap));
    }
    
    private void e(final Activity activity) {
        this.u().b(activity);
    }
    
    private void f(final Activity activity) {
        ++this.B;
        if (this.t.get()) {
            com.kongregate.android.internal.util.j.a("Native API - onResume: ready");
            if (this.z != null) {
                this.z.countDown();
            }
            this.c((Context)activity);
            com.kongregate.o.c.d.a(new Runnable() {
                @Override
                public void run() {
                    if (!NativeAPI.this.A) {
                        com.kongregate.android.internal.util.j.a("Native API - onResume: not paused. don't schedule new tasks");
                        return;
                    }
                    com.kongregate.android.internal.util.j.a("Native API - onResume: is paused. Resuming...");
                    NativeAPI.this.W();
                    final boolean b = NativeAPI.this.E().b();
                    NativeAPI.this.x();
                    NativeAPI.this.A = false;
                    NativeAPI.this.a("app-foreground", b);
                }
            });
            this.u().a(activity);
        }
    }
    
    public static com.kongregate.o.d.a g() {
        return NativeAPI.H;
    }
    
    protected void A() {
        final com.kongregate.o.j.a e = this.E();
        if (this.a("test.gdpr_alert", Boolean.valueOf(false))) {
            com.kongregate.android.internal.util.j.a("GDPR Check: testing alert");
            if (!this.a("defer_gdpr_check", Boolean.valueOf(false))) {
                this.B();
                return;
            }
            e.b(true);
        }
        else {
            e.b(false);
            if (e.f() && !this.t()) {
                com.kongregate.android.internal.util.j.a("GDPR Check: Active user is guest and guests are not targeted for gdpr. skippping check");
                return;
            }
            if (!e.f() && !this.s()) {
                com.kongregate.android.internal.util.j.a("GDPR Check: Active user is registered and not targetting registered users for gdpr. skipping check");
                return;
            }
            if (!e.f() && this.s() && e.n()) {
                com.kongregate.android.internal.util.j.a("GDPR Check: Active user accepted online privacy policy. skipping local check");
                return;
            }
            final int r = this.r();
            final long n = e.o();
            com.kongregate.android.internal.util.j.a("GDPR Check: checking local GDPR: accepted_version=" + n + ", current_policy_version=" + r);
            if (n < r) {
                if (this.a("defer_gdpr_check", Boolean.valueOf(false))) {
                    e.b(true);
                    return;
                }
                this.B();
            }
        }
    }
    
    protected void B() {
        final com.kongregate.o.j.a e = this.E();
        if (e.n()) {
            com.kongregate.android.internal.util.j.a("GDPR show alert: user already accepted");
            return;
        }
        if (e.q()) {
            this.f("gdpr_underage");
            return;
        }
        this.f("gdpr_adult");
    }
    
    protected void C() {
        final com.kongregate.o.j.a e = this.E();
        if (e.u()) {
            e.b(false);
            this.B();
        }
    }
    
    protected com.kongregate.o.g.b D() {
        return com.kongregate.o.g.b.a();
    }
    
    protected com.kongregate.o.j.a E() {
        return com.kongregate.o.j.a.a();
    }
    
    protected void F() {
        this.y();
        if (this.s != null) {
            this.s.d();
        }
    }
    
    protected void G() {
        this.D().a(com.kongregate.o.g.a.a("/games/full_manifest.json?game_id=" + this.a, true).toString(), (com.kongregate.o.g.b.a)new com.kongregate.o.g.b.a() {
            @Override
            public void a(final com.kongregate.o.g.c c, final JSONObject jsonObject) {
                com.kongregate.android.internal.util.j.b("Game manifest received");
                NativeAPI.this.a(jsonObject.optJSONObject("manifest"));
            }
        });
    }
    
    protected void H() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.getApplicationContext:()Landroid/content/Context;
        //     4: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //     7: astore          6
        //     9: aload           6
        //    11: aload_0        
        //    12: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.getApplicationContext:()Landroid/content/Context;
        //    15: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    18: sipush          4239
        //    21: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    24: astore          8
        //    26: aload_0        
        //    27: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.J:()Z
        //    30: istore          5
        //    32: aload           8
        //    34: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //    37: ifnull          293
        //    40: aload           8
        //    42: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //    45: astore          6
        //    47: aload           6
        //    49: aload           6
        //    51: ldc_w           "."
        //    54: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //    57: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    60: astore          9
        //    62: iload           5
        //    64: ifeq            130
        //    67: ldc_w           ".google"
        //    70: aload           9
        //    72: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    75: ifne            89
        //    78: ldc_w           ".amazon"
        //    81: aload           9
        //    83: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    86: ifeq            100
        //    89: aload           6
        //    91: ldc_w           "com.kongregate.mobile."
        //    94: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    97: ifeq            130
        //   100: new             Ljava/lang/StringBuilder;
        //   103: dup            
        //   104: invokespecial   java/lang/StringBuilder.<init>:()V
        //   107: ldc_w           "KONGREGATE CONFIGURATION WARNING: package name does not follow naming convention. Should be: \"com.kongregate.mobile.<yourgamename>.(google|amazon)\" not: \""
        //   110: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   113: aload           6
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: ldc_w           "\""
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   130: iload           5
        //   132: ifeq            923
        //   135: aload           8
        //   137: getfield        android/content/pm/PackageInfo.providers:[Landroid/content/pm/ProviderInfo;
        //   140: ifnull          923
        //   143: aload           8
        //   145: getfield        android/content/pm/PackageInfo.providers:[Landroid/content/pm/ProviderInfo;
        //   148: astore          10
        //   150: aload           10
        //   152: arraylength    
        //   153: istore_2       
        //   154: iconst_0       
        //   155: istore_1       
        //   156: iload_1        
        //   157: iload_2        
        //   158: if_icmpge       923
        //   161: aload           10
        //   163: iload_1        
        //   164: aaload         
        //   165: astore          7
        //   167: ldc_w           "com.kongregate.permission.ReadSharedData2"
        //   170: aload           7
        //   172: getfield        android/content/pm/ProviderInfo.readPermission:Ljava/lang/String;
        //   175: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   178: ifeq            342
        //   181: aload           7
        //   183: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   186: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //   189: astore          6
        //   191: aload           6
        //   193: ifnull          342
        //   196: ldc_w           Lcom/kongregate/android/api/providers/KongregateSharedSecretProvider;.class
        //   199: aload           6
        //   201: invokevirtual   java/lang/Class.getSuperclass:()Ljava/lang/Class;
        //   204: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   207: ifeq            342
        //   210: aload           7
        //   212: astore          6
        //   214: aload           8
        //   216: getfield        android/content/pm/PackageInfo.permissions:[Landroid/content/pm/PermissionInfo;
        //   219: ifnull          360
        //   222: aload           8
        //   224: getfield        android/content/pm/PackageInfo.permissions:[Landroid/content/pm/PermissionInfo;
        //   227: astore          7
        //   229: aload           7
        //   231: arraylength    
        //   232: istore_2       
        //   233: iconst_0       
        //   234: istore_1       
        //   235: iload_1        
        //   236: iload_2        
        //   237: if_icmpge       360
        //   240: ldc_w           "com.kongregate.permission.ReadSharedData2"
        //   243: aload           7
        //   245: iload_1        
        //   246: aaload         
        //   247: getfield        android/content/pm/PermissionInfo.name:Ljava/lang/String;
        //   250: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   253: ifeq            275
        //   256: iload           5
        //   258: ifne            275
        //   261: aload_0        
        //   262: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.K:()Z
        //   265: ifne            349
        //   268: aload_0        
        //   269: ldc_w           "Games that do not support shared sign-on should not use new read permission: com.kongregate.permission.ReadSharedData2"
        //   272: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   275: iload_1        
        //   276: iconst_1       
        //   277: iadd           
        //   278: istore_1       
        //   279: goto            235
        //   282: astore          6
        //   284: ldc_w           "Package Not Found, unable to check configuration"
        //   287: aload           6
        //   289: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   292: return         
        //   293: ldc_w           ""
        //   296: astore          6
        //   298: goto            47
        //   301: astore          6
        //   303: new             Ljava/lang/StringBuilder;
        //   306: dup            
        //   307: invokespecial   java/lang/StringBuilder.<init>:()V
        //   310: ldc_w           "KONGREGATE CONFIGURATION WARNING: Provider found with com.kongregate.permission.ReadSharedData2 permission, but the class "
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   316: aload           7
        //   318: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: ldc_w           " is not found"
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   333: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   336: aconst_null    
        //   337: astore          6
        //   339: goto            191
        //   342: iload_1        
        //   343: iconst_1       
        //   344: iadd           
        //   345: istore_1       
        //   346: goto            156
        //   349: aload_0        
        //   350: ldc_w           "Allowing READ_PERMISSION_2 with SSO disabled since this is TPT"
        //   353: iconst_0       
        //   354: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   357: goto            275
        //   360: aload           6
        //   362: ifnonnull       542
        //   365: iload           5
        //   367: ifeq            542
        //   370: aload_0        
        //   371: new             Ljava/lang/StringBuilder;
        //   374: dup            
        //   375: invokespecial   java/lang/StringBuilder.<init>:()V
        //   378: ldc_w           "KONGREGATE CONFIGURATION WARNING: Must specify a "
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: ldc_w           Lcom/kongregate/android/api/providers/KongregateSharedSecretProvider;.class
        //   387: invokevirtual   java/lang/Class.toString:()Ljava/lang/String;
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: ldc_w           "  subclass that requires the "
        //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   399: ldc_w           "com.kongregate.permission.ReadSharedData2"
        //   402: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   405: ldc_w           " read permission."
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   414: iconst_1       
        //   415: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   418: aload           8
        //   420: getfield        android/content/pm/PackageInfo.permissions:[Landroid/content/pm/PermissionInfo;
        //   423: ifnull          745
        //   426: aload           8
        //   428: getfield        android/content/pm/PackageInfo.permissions:[Landroid/content/pm/PermissionInfo;
        //   431: astore          6
        //   433: aload           6
        //   435: arraylength    
        //   436: istore          4
        //   438: iconst_0       
        //   439: istore_2       
        //   440: iconst_0       
        //   441: istore_1       
        //   442: iload_1        
        //   443: istore_3       
        //   444: iload_2        
        //   445: iload           4
        //   447: if_icmpge       747
        //   450: aload           6
        //   452: iload_2        
        //   453: aaload         
        //   454: astore          7
        //   456: aload           7
        //   458: getfield        android/content/pm/PermissionInfo.name:Ljava/lang/String;
        //   461: ldc_w           "com.kongregate.permission.ReadSharedData2"
        //   464: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   467: ifeq            507
        //   470: aload           7
        //   472: getfield        android/content/pm/PermissionInfo.protectionLevel:I
        //   475: iconst_2       
        //   476: if_icmpeq       486
        //   479: aload_0        
        //   480: ldc_w           "Custom permission: com.kongregate.permission.ReadSharedData2 must have 'signature' protection level"
        //   483: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   486: iload           5
        //   488: ifne            740
        //   491: aload_0        
        //   492: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.K:()Z
        //   495: ifne            732
        //   498: aload_0        
        //   499: ldc_w           "Games that do not support shared sign-on should not use new read permission: com.kongregate.permission.ReadSharedData2"
        //   502: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   505: iconst_1       
        //   506: istore_1       
        //   507: ldc_w           "com.kongregate.permission.ReadSharedData"
        //   510: aload           7
        //   512: getfield        android/content/pm/PermissionInfo.name:Ljava/lang/String;
        //   515: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   518: ifeq            535
        //   521: aload_0        
        //   522: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.J:()Z
        //   525: ifeq            535
        //   528: aload_0        
        //   529: ldc_w           "com.kongregate.permission.ReadSharedData has been deprecated. In it's place use: com.kongregate.permission.ReadSharedData2"
        //   532: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   535: iload_2        
        //   536: iconst_1       
        //   537: iadd           
        //   538: istore_2       
        //   539: goto            442
        //   542: iload           5
        //   544: ifeq            418
        //   547: aload           6
        //   549: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   552: astore          7
        //   554: aload           6
        //   556: getfield        android/content/pm/ProviderInfo.authority:Ljava/lang/String;
        //   559: ifnull          641
        //   562: aload           6
        //   564: getfield        android/content/pm/ProviderInfo.authority:Ljava/lang/String;
        //   567: aload           7
        //   569: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   572: ifne            641
        //   575: getstatic       com/kongregate/android/internal/sdk/NativeAPI.o:Ljava/util/List;
        //   578: aload_0        
        //   579: getfield        com/kongregate/android/internal/sdk/NativeAPI.a:J
        //   582: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   585: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   590: ifne            641
        //   593: aload_0        
        //   594: new             Ljava/lang/StringBuilder;
        //   597: dup            
        //   598: invokespecial   java/lang/StringBuilder.<init>:()V
        //   601: ldc_w           "Authority for provider "
        //   604: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   607: aload           6
        //   609: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   612: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   615: ldc_w           " should be "
        //   618: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   621: aload           6
        //   623: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   626: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   629: ldc_w           " to prevent conflicts with other apps"
        //   632: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   635: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   638: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   641: aload           6
        //   643: getfield        android/content/pm/ProviderInfo.exported:Z
        //   646: ifne            683
        //   649: aload_0        
        //   650: new             Ljava/lang/StringBuilder;
        //   653: dup            
        //   654: invokespecial   java/lang/StringBuilder.<init>:()V
        //   657: ldc_w           "The provider: "
        //   660: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   663: aload           6
        //   665: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   668: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   671: ldc_w           " should be exported"
        //   674: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   677: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   680: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   683: ldc_w           ".amazon"
        //   686: aload           9
        //   688: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   691: ifeq            418
        //   694: aload           6
        //   696: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   699: aload           6
        //   701: getfield        android/content/pm/ProviderInfo.name:Ljava/lang/String;
        //   704: ldc_w           "."
        //   707: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   710: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   713: ldc_w           ".Amazon"
        //   716: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   719: ifeq            418
        //   722: aload_0        
        //   723: ldc_w           "The provider for the amazon store should be named com.kongregate.android.api.providers.Amazon<YourGameName>SharedSecretProvider"
        //   726: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.g:(Ljava/lang/String;)V
        //   729: goto            418
        //   732: aload_0        
        //   733: ldc_w           "Allowing READ_PERMISSION_2 with SSO disabled since this is TPT"
        //   736: iconst_0       
        //   737: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   740: iconst_1       
        //   741: istore_1       
        //   742: goto            507
        //   745: iconst_0       
        //   746: istore_3       
        //   747: iload_3        
        //   748: ifne            764
        //   751: iload           5
        //   753: ifeq            764
        //   756: aload_0        
        //   757: ldc_w           "Manifest must include custom permission 'com.kongregate.permission.ReadSharedData2' with 'signature' protection level for shared sign-on to work"
        //   760: iconst_1       
        //   761: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   764: iload_3        
        //   765: ifeq            783
        //   768: aload_0        
        //   769: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.I:()Z
        //   772: ifeq            783
        //   775: aload_0        
        //   776: ldc_w           "APK not signed by Kongregate but SSO permission detected. Kongregate must resign before uploading to Google Play."
        //   779: iconst_1       
        //   780: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   783: aload           8
        //   785: getfield        android/content/pm/PackageInfo.requestedPermissions:[Ljava/lang/String;
        //   788: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   791: astore          6
        //   793: aload           6
        //   795: ldc_w           "com.kongregate.permission.ReadSharedData"
        //   798: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   803: iconst_m1      
        //   804: if_icmple       813
        //   807: ldc_w           "KONGREGATE CONFIGURATION WARNING: permission has been deprecated: com.kongregate.permission.ReadSharedData"
        //   810: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   813: aload           6
        //   815: ldc_w           "com.kongregate.permission.ReadSharedData2"
        //   818: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   823: iconst_m1      
        //   824: if_icmpne       838
        //   827: iload           5
        //   829: ifeq            838
        //   832: ldc_w           "KONGREGATE CONFIGURATION WARNING: must use permission: com.kongregate.permission.ReadSharedData2"
        //   835: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   838: aload           6
        //   840: ldc_w           "android.permission.ACCESS_NETWORK_STATE"
        //   843: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   848: iconst_m1      
        //   849: if_icmpne       858
        //   852: ldc_w           "KONGREGATE CONFIGURATION WARNING: must use permission: android.permission.ACCESS_NETWORK_STATE"
        //   855: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   858: aload           6
        //   860: ldc_w           "android.permission.INTERNET"
        //   863: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   868: iconst_m1      
        //   869: if_icmpne       878
        //   872: ldc_w           "KONGREGATE CONFIGURATION WARNING: must use permission: android.permission.INTERNET"
        //   875: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   878: aload_0        
        //   879: aload           8
        //   881: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.a:(Landroid/content/pm/PackageInfo;)V
        //   884: aload_0        
        //   885: aload           8
        //   887: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Landroid/content/pm/PackageInfo;)V
        //   890: aload_0        
        //   891: aload           8
        //   893: invokestatic    com/kongregate/o/a/d.a:(Landroid/content/pm/PackageInfo;)Ljava/lang/String;
        //   896: iconst_1       
        //   897: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   900: aload_0        
        //   901: getfield        com/kongregate/android/internal/sdk/NativeAPI.r:Ljava/util/Map;
        //   904: ldc_w           "test.gdpr_alert"
        //   907: iconst_0       
        //   908: invokestatic    com/kongregate/android/internal/sdk/b.a:(Ljava/util/Map;Ljava/lang/String;Z)Z
        //   911: ifeq            292
        //   914: aload_0        
        //   915: ldc_w           "Test option [KONGREGATE_OPTION_TEST_GDPR_ALERT] is set to true. This build should be for testing only"
        //   918: iconst_1       
        //   919: invokevirtual   com/kongregate/android/internal/sdk/NativeAPI.b:(Ljava/lang/String;Z)V
        //   922: return         
        //   923: aconst_null    
        //   924: astore          6
        //   926: goto            214
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  9      26     282    292    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  181    191    301    342    Ljava/lang/ClassNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0191:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    boolean I() {
        return !com.kongregate.android.internal.util.k.a(this.getApplicationContext(), -192598629) && this.J();
    }
    
    protected boolean J() {
        boolean b = true;
        if (NativeAPI.H.d()) {
            if (this.K()) {
                return true;
            }
            if (!com.kongregate.android.internal.util.k.a(this.getApplicationContext(), "com.kongregate.mobile.SELF_SIGNED", false)) {
                if (NativeAPI.p.contains(this.a)) {
                    b = false;
                }
                return b;
            }
        }
        return false;
    }
    
    protected boolean K() {
        return "com.kongregate.android.test.thirdpartytest".equals(this.getApplicationContext().getPackageName());
    }
    
    @Override
    protected KongregateServices a() {
        return new m();
    }
    
    public Boolean a(final String s, final Boolean b) {
        final Boolean value = this.r.get(s);
        if (value instanceof Boolean) {
            return value;
        }
        return b;
    }
    
    public Integer a(final String s, final Integer n) {
        final Integer value = this.r.get(s);
        if (value instanceof Integer) {
            return value;
        }
        return n;
    }
    
    void a(final Activity activity) {
        final Uri uri = null;
        final String s = null;
        if (!this.a("KONG_OPTIONS_AUTO_PROCESS_DEEPLINKS", Boolean.valueOf(false))) {
            return;
        }
        final Intent intent = activity.getIntent();
        Uri uri2 = uri;
        if (intent != null) {
            uri2 = uri;
            if ("android.intent.action.VIEW".equals(intent.getAction())) {
                final String action = intent.getAction();
                final Uri data = intent.getData();
                String string = s;
                if (data != null) {
                    string = data.toString();
                }
                com.kongregate.android.internal.util.j.a("launch action=" + action + " data=" + string);
                uri2 = data;
            }
        }
        this.willOpenUrl(uri2);
    }
    
    void a(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.kongregate.android.internal.sdk.ClosePanel"));
    }
    
    void a(final Context context, final String s, final String s2) {
        com.kongregate.android.internal.util.j.a("openning kong panel with target: " + s);
        if (context != null) {
            com.kongregate.o.c.d.b(new Runnable() {
                @Override
                public void run() {
                    NativeAPI.this.O();
                    if (NativeAPI.this.y.compareAndSet(false, true)) {
                        com.kongregate.android.internal.util.j.a("Opening Kong Panel with target: " + s);
                        final Intent intent = new Intent(context, (Class)KongregatePanelActivity.class);
                        intent.putExtra("target", s);
                        intent.putExtra("target_id", s2);
                        intent.putExtra("showSystemUi", (Serializable)NativeAPI.this.a("show_system_ui", Boolean.FALSE));
                        intent.putExtra("allowImmersiveMode", (Serializable)NativeAPI.this.a("allow_immersive_mode", Boolean.TRUE));
                        intent.putExtra("orientationOverride", com.kongregate.android.internal.sdk.b.a(NativeAPI.this.r, "panel_orientation_override", null));
                        final String a = com.kongregate.android.internal.sdk.b.a(NativeAPI.this.r, "default_panel_transition", null);
                        intent.putExtra("overrideTransition", a);
                        intent.setFlags(536870912);
                        if (!(context instanceof Activity)) {
                            intent.setFlags(268435456);
                        }
                        context.startActivity(intent);
                        if (context instanceof Activity && KongregatePanelActivity.PANEL_TRANSITION_MAP.containsKey(a)) {
                            final String[] array = KongregatePanelActivity.PANEL_TRANSITION_MAP.get(a);
                            ((Activity)context).overridePendingTransition(context.getResources().getIdentifier(array[0], "anim", context.getPackageName()), context.getResources().getIdentifier(array[1], "anim", context.getPackageName()));
                        }
                        NativeAPI.this.a("KONG_API_EVENT_OPENING_KONGREGATE");
                        NativeAPI.this.e("panel-opened");
                        return;
                    }
                    com.kongregate.android.internal.util.j.c("Kong Panel is already showing, ignoring showPanel call");
                }
            });
        }
    }
    
    protected void a(final PackageInfo packageInfo) {
        int n = 0;
        if (NativeAPI.H.c()) {
            final ActivityInfo[] activities = packageInfo.activities;
            for (int length = activities.length, i = 0; i < length; ++i) {
                final ActivityInfo activityInfo = activities[i];
                if (activityInfo.name.equals(KongregatePanelActivity.class.getName())) {
                    final boolean b = true;
                    final int a = com.kongregate.android.internal.util.g.a("KongregateDialogTheme", "style", this.c);
                    n = (b ? 1 : 0);
                    if (activityInfo.theme != a) {
                        com.kongregate.android.internal.util.j.c("KONGREGATE CONFIGURATION WARNING: KongregatePanelActivity should use android:theme=\"@style/KongregateDialogTheme\" in AndroidManifest.xml if possible.");
                        n = (b ? 1 : 0);
                    }
                }
                if (activityInfo.name.equals("com.facebook.LoginActivity")) {}
            }
            if (n == 0) {
                com.kongregate.android.internal.util.j.c("KONGREGATE CONFIGURATION WARNING: must include actitiy: " + KongregatePanelActivity.class.getName());
            }
        }
    }
    
    public void a(final String s, final boolean b) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("foreground", !this.A);
            jsonObject.put("panel-open", this.y.get());
            jsonObject.put("user-changed", b);
            if (this.s != null) {
                this.s.a(s, jsonObject);
            }
        }
        catch (JSONException ex) {
            com.kongregate.android.internal.util.j.c("NativeAPI - exception building app-state message.", (Throwable)ex);
        }
    }
    
    protected void a(JSONObject optJSONObject) {
        final JSONArray optJSONArray = optJSONObject.optJSONArray("statistics");
        if (optJSONArray != null) {
            com.kongregate.o.b.d.a(this.a, optJSONArray);
        }
        final JSONArray optJSONArray2 = optJSONObject.optJSONArray("badges");
        if (optJSONArray2 != null) {
            com.kongregate.o.b.c.a(this.a, optJSONArray2);
        }
        optJSONObject = optJSONObject.optJSONObject("config");
        if (optJSONObject != null) {
            final SharedPreferences$Editor edit = this.v.edit();
            edit.putBoolean("keen", optJSONObject.optBoolean("keen", true));
            edit.putBoolean("swrve", optJSONObject.optBoolean("swrve", true));
            edit.putBoolean("adjust", optJSONObject.optBoolean("adjust", true));
            edit.putBoolean("adx", optJSONObject.optBoolean("adx", true));
            edit.putBoolean("persistent_webview", optJSONObject.optBoolean("persistent_webview", true));
            edit.putInt("gdpr_policy_version", optJSONObject.optInt("gdpr_policy_version", 0));
            edit.putBoolean("gdpr_target_registered", optJSONObject.optBoolean("gdpr_target_registered", false));
            edit.putBoolean("gdpr_target_guests", optJSONObject.optBoolean("gdpr_target_guests", false));
            final int a = com.kongregate.android.internal.util.i.a(optJSONObject, "panel_reload_delay_minutes", 120);
            edit.putInt("panel_reload_delay_minutes", a);
            com.kongregate.android.internal.browser.b.a(a);
            edit.apply();
            final com.kongregate.o.g.a b = com.kongregate.o.g.a.b();
            if (b != null) {
                b.a(this.a, this.P());
            }
        }
    }
    
    @Override
    protected StatServices b() {
        return new c();
    }
    
    public String b(final String s) {
        return this.b(s, null);
    }
    
    public String b(String s, final String s2) {
        String s4;
        final String s3 = s4 = "https://" + com.kongregate.o.d.b.a().c() + "/mobile_api?api=" + "3.0.5";
        if (this.I()) {
            s4 = s3 + "&unsigned=true";
        }
        String string = s4;
        if (!com.kongregate.android.internal.util.a.a(19)) {
            string = s4;
            if (Boolean.TRUE.equals(this.r.get("debug_webview"))) {
                string = s4 + "&debug=true";
            }
        }
        String s5 = string + "#/";
        if (!StringUtils.c((CharSequence)s)) {
            s = (s5 = s5 + "redirect?target=" + s);
            if (StringUtils.b((CharSequence)s2)) {
                s5 = s + "&target_id=" + s2;
            }
        }
        return s5;
    }
    
    public void b(final Activity activity) {
        if (this.S()) {
            return;
        }
        this.b((Context)activity);
    }
    
    protected void b(final PackageInfo packageInfo) {
        if (StringUtils.a((CharSequence)com.kongregate.android.internal.sdk.b.a(this.r, "adjust.app_token"))) {
            com.kongregate.android.internal.util.j.c("KONGREGATE CONFIGURATION WARNING: adjust not configured");
        }
        else {
            if (!"production".equals(com.kongregate.android.internal.sdk.b.a(this.r, "adjust.environment"))) {
                this.b("Be sure to change AdjustEnvironment meta-data tag to 'production' prior to release", true);
            }
            if (!com.kongregate.o.a.a.a(this.c, packageInfo)) {
                this.b("InstallReceiver misconfigured. You must specify 'com.adjust.sdk.AdjustReferrerReceiver' in the forwards of the InstallReceiver and the adjust.jar must be included.", true);
            }
        }
    }
    
    void b(final String s, final boolean b) {
        if (!StringUtils.a((CharSequence)s)) {
            com.kongregate.android.internal.util.j.c("KONGREGATE CONFIGURATION WARNING: " + s);
            if (b && (!this.I() || !this.a("KONG_OPTION_SUPPRESS_TOAST_WARNINGS", Boolean.valueOf(false)))) {
                com.kongregate.o.c.d.b(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NativeAPI.this.c, (CharSequence)("KONG CONFIGURATION WARNING: " + s), 1).show();
                    }
                });
            }
        }
    }
    
    @Override
    protected MobileServices c() {
        return new q(this.c);
    }
    
    public String c(final String s, final String s2) {
        final String value = this.r.get(s);
        if (value instanceof String) {
            return value;
        }
        return s2;
    }
    
    public void c(final Activity activity) {
        if (this.S()) {
            return;
        }
        this.f(activity);
    }
    
    public void c(final String s) {
        synchronized (this.u) {
            if (this.t.get()) {
                this.a(s);
            }
            else {
                com.kongregate.android.internal.util.j.b("Queueing pre-ready event: " + s);
                this.u.add(s);
            }
        }
    }
    
    @Override
    protected AnalyticsServices d() {
        return new o(this.c);
    }
    
    protected void d(final String s) {
        if (StringUtils.a(s, "gdpr_alert")) {
            this.C();
        }
    }
    
    @Override
    protected MicrotransactionServices e() {
        return new n(this);
    }
    
    public void e(final String s) {
        this.a(s, false);
    }
    
    public String f() {
        return this.b((String)null);
    }
    
    void f(final String s) {
        final Intent intent = new Intent(this.c, (Class)KongregatePanelActivity.class);
        intent.setFlags(536870912);
        if (!(this.c instanceof Activity)) {
            intent.setFlags(268435456);
        }
        intent.putExtra("alert", s);
        this.v().startActivity(intent);
    }
    
    void g(final String s) {
        com.kongregate.android.internal.util.j.d("KONGREGATE CONFIGURATION ERROR: " + s);
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(NativeAPI.this.c, (CharSequence)("KONG CONFIGURATION ERROR: " + s), 1).show();
            }
        });
        com.kongregate.android.internal.util.j.c("APP WILL BE KILLED DUE TO KONGREGATE CONFIGURATION ERROR");
        while (true) {
            try {
                Thread.sleep(2000L);
                com.kongregate.o.c.d.b(new Runnable() {
                    @Override
                    public void run() {
                        throw new RuntimeException("KONGREGATE CONFIGURATION ERROR: " + s);
                    }
                });
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
    }
    
    protected void h(final String s) {
        if ("gdpr_policy_accepted".equals(s)) {
            final com.kongregate.o.j.a e = this.E();
            final int r = this.r();
            com.kongregate.android.internal.util.j.c("GDPR Accepted: user ID: " + e.g() + " policy version: " + r);
            e.a(r);
        }
        else if ("gdpr_view_policy".equals(s)) {
            com.kongregate.android.internal.util.j.c("GDPR: View Policy");
            this.a(this.c, null, null);
        }
    }
    
    boolean h() {
        return com.kongregate.android.internal.sdk.b.a(this.r, "crashlytics_user_keys", false);
    }
    
    boolean i() {
        return com.kongregate.android.internal.util.l.a(this.v, "keen", true);
    }
    
    @Override
    public boolean isReady() {
        return this.t.get();
    }
    
    boolean j() {
        return com.kongregate.android.internal.util.l.a(this.v, "swrve", true);
    }
    
    boolean k() {
        return com.kongregate.android.internal.util.l.a(this.v, "delta", true);
    }
    
    boolean l() {
        return com.kongregate.android.internal.util.l.a(this.v, "kong_analytics", true);
    }
    
    boolean m() {
        boolean b;
        if (!Boolean.FALSE.equals(this.r.get("persistent_webview"))) {
            b = true;
        }
        else {
            b = false;
        }
        return b && com.kongregate.android.internal.util.l.a(this.v, "persistent_webview", true);
    }
    
    Set<String> n() {
        return new HashSet<String>(Arrays.asList(com.kongregate.android.internal.sdk.b.d(this.r, "supported_panel_events")));
    }
    
    boolean o() {
        return com.kongregate.android.internal.sdk.b.a(this.r, "guild_chat", false);
    }
    
    @Override
    public void onCreate(final Activity activity, final Bundle bundle) {
        if (!this.S()) {
            this.a(activity, bundle);
        }
    }
    
    @Override
    public void onDestroy(final Activity activity) {
        if (!this.S()) {
            this.e(activity);
        }
    }
    
    @Override
    public void onLowMemory() {
        this.u().k();
    }
    
    @Override
    public void onPause(final Activity activity) {
        if (!this.S()) {
            this.b((Context)activity);
        }
    }
    
    @Override
    public void onPause(final Activity activity, final String commonProperties) {
        this.u().setCommonProperties(commonProperties);
        this.onPause(activity);
    }
    
    @Override
    public void onPause(final Activity activity, final Map<String, Object> commonProperties) {
        this.u().setCommonProperties(commonProperties);
        this.onPause(activity);
    }
    
    @Override
    public void onResume(final Activity activity) {
        if (!this.S()) {
            this.f(activity);
        }
    }
    
    @Override
    public void onResume(final Activity activity, final String commonProperties) {
        this.u().setCommonProperties(commonProperties);
        this.onResume(activity);
    }
    
    @Override
    public void onResume(final Activity activity, final Map<String, Object> commonProperties) {
        this.u().setCommonProperties(commonProperties);
        this.onResume(activity);
    }
    
    boolean p() {
        return com.kongregate.android.internal.util.l.a(this.v, "adjust", true);
    }
    
    boolean q() {
        return com.kongregate.android.internal.util.l.a(this.v, "adx", true);
    }
    
    int r() {
        return com.kongregate.android.internal.util.l.a(this.v, "gdpr_policy_version", 0);
    }
    
    boolean s() {
        return com.kongregate.android.internal.util.l.a(this.v, "gdpr_target_registered", false);
    }
    
    boolean t() {
        return com.kongregate.android.internal.util.l.a(this.v, "gdpr_target_guests", false);
    }
    
    protected o u() {
        return (o)this.g;
    }
    
    protected Context v() {
        return this.c;
    }
    
    public Uri w() {
        return this.D;
    }
    
    @Override
    public void willOpenUrl(final Uri d) {
        this.D = d;
        if (d != null && StringUtils.b((CharSequence)d.toString())) {
            this.c("KONG_API_EVENT_OPEN_DEEP_LINK");
            this.u().l();
        }
    }
    
    protected void x() {
        if (this.D().b()) {
            this.F();
            return;
        }
        this.G.a();
    }
    
    protected void y() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (!this.E().d() && currentTimeMillis - this.x.get() < 60000L) {
            com.kongregate.android.internal.util.j.a("Native API - too soon, not registering play mobile");
            return;
        }
        this.x.set(currentTimeMillis);
        com.kongregate.android.internal.util.j.a("Native API - download user and game info: " + this.E().h());
        String s;
        if (this.E().d()) {
            s = "&register_gameplay=true";
        }
        else {
            s = null;
        }
        this.D().a(com.kongregate.o.g.a.a("/games/play_mobile.json?game_id=" + this.a + s, true).toString(), (com.kongregate.o.g.b.a)new com.kongregate.o.g.b.a() {
            @Override
            public void a(final com.kongregate.o.g.c c, final JSONObject jsonObject) {
                final JSONObject optJSONObject = jsonObject.optJSONObject("user_info");
                com.kongregate.android.internal.util.j.a("Native API - User and game info received: " + com.kongregate.android.internal.util.i.c(optJSONObject, "username"));
                NativeAPI.this.E().a(optJSONObject, jsonObject.optString("auth_token"));
                ((o)NativeAPI.this.analytics()).a(jsonObject.optString("time", (String)null), jsonObject.optString("ip", (String)null));
                ((o)NativeAPI.this.analytics()).b(new HashMap<String, Object>());
                NativeAPI.this.a(jsonObject);
                b.a(jsonObject.optString("panel_fingerprint", (String)null));
                NativeAPI.this.O();
                LocalBroadcastManager.getInstance(NativeAPI.this.c).sendBroadcast(new Intent("com.kongregate.android.internal.sdk.LoadUser"));
                NativeAPI.this.z();
            }
        });
    }
    
    protected void z() {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                NativeAPI.this.A();
            }
        });
    }
    
    protected class LocalBroadcastReciever extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("com.kongregate.android.internal.sdk.BroadcastAPIEvent".equals(intent.getAction())) {
                final String stringExtra = intent.getStringExtra("com.kongregate.android.internal.sdk.event");
                final String stringExtra2 = intent.getStringExtra("com.kongregate.android.internal.sdk.data");
                if (StringUtils.a((CharSequence)stringExtra)) {
                    com.kongregate.android.internal.util.j.c("BroadcastEvent, unable to broadcast empty event");
                    return;
                }
                if ("KONG_API_EVENT_CLOSED_KONGREGATE".equals(stringExtra)) {
                    com.kongregate.android.internal.util.j.a("Kong Panel closed, clearing active flag");
                    NativeAPI.this.y.set(false);
                    NativeAPI.this.e("panel-closed");
                }
                NativeAPI.this.a(stringExtra, stringExtra2);
            }
            else if ("com.kongregate.android.internal.sdk.AlertDismissEvent".equals(intent.getAction())) {
                NativeAPI.this.h(intent.getStringExtra("com.kongregate.android.internal.sdk.AlertDismissButton"));
            }
        }
    }
    
    class a extends BroadcastReceiver
    {
        private final AtomicBoolean b;
        
        a() {
            this.b = new AtomicBoolean(false);
        }
        
        void a() {
            synchronized (this.b) {
                if (this.b.compareAndSet(false, true)) {
                    com.kongregate.android.internal.util.j.a("register for conn changes");
                    NativeAPI.this.getApplicationContext().registerReceiver((BroadcastReceiver)this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        }
        
        void b() {
            synchronized (this.b) {
                if (this.b.compareAndSet(true, false)) {
                    com.kongregate.android.internal.util.j.a("unregister for conn changes");
                    NativeAPI.this.getApplicationContext().unregisterReceiver((BroadcastReceiver)this);
                }
            }
        }
        
        public void onReceive(final Context context, final Intent intent) {
            com.kongregate.android.internal.util.j.b("Connectivity changed: " + NativeAPI.this.D().b());
            if (NativeAPI.this.D().b()) {
                this.b();
                NativeAPI.this.y();
            }
        }
    }
    
    @TargetApi(14)
    class b implements Application$ActivityLifecycleCallbacks
    {
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
            NativeAPI.this.a(activity, bundle);
        }
        
        public void onActivityDestroyed(final Activity activity) {
            NativeAPI.this.e(activity);
        }
        
        public void onActivityPaused(final Activity activity) {
            NativeAPI.this.b((Context)activity);
        }
        
        public void onActivityResumed(final Activity activity) {
            NativeAPI.this.f(activity);
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            com.kongregate.android.internal.util.j.b("start");
        }
        
        public void onActivityStopped(final Activity activity) {
            com.kongregate.android.internal.util.j.b("stop");
        }
    }
    
    protected class c extends com.kongregate.android.internal.sdk.c.c
    {
        @Override
        public void submit(final String s, final long n) {
            if (!NativeAPI.H.b()) {
                return;
            }
            d.a(new Runnable() {
                final /* synthetic */ long a = com.kongregate.o.j.a.a().g();
                
                @Override
                public void run() {
                    if (NativeAPI.this.q != null) {
                        com.kongregate.o.b.e.a(NativeAPI.this.q.getWritableDatabase(), this.a, NativeAPI.this.a, s, n);
                    }
                }
            });
        }
    }
    
    protected class d implements Runnable
    {
        private void a(final SQLiteDatabase sqLiteDatabase) {
            if (DatabaseUtils.longForQuery(sqLiteDatabase, "SELECT COUNT(_id) FROM statistic_records WHERE statistic_id IS NULL", (String[])null) <= 0L) {
                NativeAPI.I.set(0L);
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final long n = NativeAPI.J.get();
            final long n2 = currentTimeMillis - NativeAPI.I.get();
            final long n3 = 120000L * n;
            if (n >= 10L || n2 < n3) {
                com.kongregate.android.internal.util.j.a("Not requesting statistic definitions, too soon since last request. cooldownPeriod=" + n3 + ", timePassed=" + n2 + ", counter=" + n);
                return;
            }
            NativeAPI.I.set(currentTimeMillis);
            com.kongregate.android.internal.util.j.b("Found unknown statistic records, hitting server for definitions, counter=" + (long)NativeAPI.J.incrementAndGet());
            NativeAPI.this.G();
        }
        
        @Override
        public void run() {
            try {
                final SQLiteDatabase a = com.kongregate.o.e.b.a();
                this.a(a);
                new e.a(NativeAPI.this.c, a, NativeAPI.this.E().g(), NativeAPI.this.a, com.kongregate.o.g.a.a("/statistic_records/mobile_submit.json", true).toString()).run();
            }
            catch (SQLiteException ex) {
                com.kongregate.android.internal.util.j.c("SQLiteException in StatSubmissionTask", (Throwable)ex);
            }
        }
    }
}
