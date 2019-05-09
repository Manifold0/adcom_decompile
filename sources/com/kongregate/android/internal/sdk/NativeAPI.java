package com.kongregate.android.internal.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.WebView;
import android.widget.Toast;
import com.adjust.sdk.AdjustConfig;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.LocationConst;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.api.KongregateEventBundleListener;
import com.kongregate.android.api.KongregateEventListener;
import com.kongregate.android.api.KongregateServices;
import com.kongregate.android.api.MicrotransactionServices;
import com.kongregate.android.api.MobileServices;
import com.kongregate.android.api.StatServices;
import com.kongregate.android.api.activities.KongregatePanelActivity;
import com.kongregate.android.api.providers.KongregateSharedSecretProvider;
import com.kongregate.android.internal.browser.C0451a;
import com.kongregate.android.internal.browser.C0462b;
import com.kongregate.android.internal.sdk.C0485c.C0482c;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0543b;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0563k;
import com.kongregate.android.internal.util.C0564l;
import com.kongregate.android.internal.util.SharedSecretProvider;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p001j.C0666a;
import com.kongregate.p000o.p001j.C0666a.C0463a;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p002g.C0645b;
import com.kongregate.p000o.p002g.C0645b.C0468a;
import com.kongregate.p000o.p002g.C0646c;
import com.kongregate.p000o.p003a.C0578a;
import com.kongregate.p000o.p003a.C0582d;
import com.kongregate.p000o.p004e.C0526c;
import com.kongregate.p000o.p004e.C0637b;
import com.kongregate.p000o.p005b.C0603c;
import com.kongregate.p000o.p005b.C0607d;
import com.kongregate.p000o.p005b.C0613e;
import com.kongregate.p000o.p005b.C0613e.C0612a;
import com.kongregate.p000o.p006c.C0617a;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p007d.C0629a;
import com.kongregate.p000o.p007d.C0635b;
import com.tapjoy.TJAdUnitConstants.String;
import com.tonyodev.fetch.FetchConst;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONObject;

public class NativeAPI extends C0485c {
    /* renamed from: H */
    private static final C0629a f427H = new C0629a();
    /* renamed from: I */
    private static final AtomicLong f428I = new AtomicLong(0);
    /* renamed from: J */
    private static final AtomicInteger f429J = new AtomicInteger(0);
    /* renamed from: k */
    protected static final String f430k = "kongregate_remote_configuraiton";
    /* renamed from: l */
    protected static final String f431l = "gdpr_policy_version";
    /* renamed from: m */
    protected static final String f432m = "gdpr_target_registered";
    /* renamed from: n */
    protected static final String f433n = "gdpr_target_guests";
    /* renamed from: o */
    private static final List<Long> f434o = Arrays.asList(new Long[]{Long.valueOf(185301), Long.valueOf(180045), Long.valueOf(192193), Long.valueOf(184565)});
    /* renamed from: p */
    private static final List<Long> f435p = Arrays.asList(new Long[]{Long.valueOf(179960), Long.valueOf(180048), Long.valueOf(186939)});
    /* renamed from: A */
    private volatile boolean f436A = false;
    /* renamed from: B */
    private int f437B = 0;
    /* renamed from: C */
    private boolean f438C = false;
    /* renamed from: D */
    private volatile Uri f439D = null;
    /* renamed from: E */
    private volatile ScheduledFuture<?> f440E = null;
    /* renamed from: F */
    private final AtomicReference<ScheduledFuture<?>> f441F = new AtomicReference(null);
    /* renamed from: G */
    private final C0479a f442G = new C0479a(this);
    /* renamed from: q */
    private volatile C0526c f443q;
    /* renamed from: r */
    private final Map<String, Object> f444r;
    /* renamed from: s */
    private volatile C0462b f445s;
    /* renamed from: t */
    private final AtomicBoolean f446t = new AtomicBoolean(false);
    /* renamed from: u */
    private final List<String> f447u = new LinkedList();
    /* renamed from: v */
    private volatile SharedPreferences f448v;
    /* renamed from: w */
    private C0480b f449w;
    /* renamed from: x */
    private final AtomicLong f450x = new AtomicLong(0);
    /* renamed from: y */
    private final AtomicBoolean f451y = new AtomicBoolean(false);
    /* renamed from: z */
    private volatile CountDownLatch f452z = null;

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$6 */
    class C04746 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f399a;

        C04746(NativeAPI nativeAPI) {
            this.f399a = nativeAPI;
        }

        public void run() {
            this.f399a.f445s = C0462b.m267a(this.f399a.c, this.f399a.m393f(), this.f399a.m402m(), this.f399a.m404o(), this.f399a.m403n());
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$7 */
    class C04767 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f401a;

        /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$7$1 */
        class C04751 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C04767 f400a;

            C04751(C04767 c04767) {
                this.f400a = c04767;
            }

            public void run() {
            }
        }

        C04767(NativeAPI nativeAPI) {
            this.f401a = nativeAPI;
        }

        public void run() {
            C0617a.m955a(new C04751(this), false);
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$8 */
    class C04778 extends C0468a {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f402a;

        C04778(NativeAPI nativeAPI) {
            this.f402a = nativeAPI;
        }

        /* renamed from: a */
        public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject("user_info");
            C0562j.m753a("Native API - User and game info received: " + C0561i.m748c(optJSONObject, "username"));
            this.f402a.m363E().m1166a(optJSONObject, jSONObject.optString("auth_token"));
            ((C0525o) this.f402a.analytics()).m521a(jSONObject.optString(LocationConst.TIME, null), jSONObject.optString("ip", null));
            ((C0525o) this.f402a.analytics()).m529b(new HashMap());
            this.f402a.m378a(jSONObject);
            C0462b.m271a(jSONObject.optString("panel_fingerprint", null));
            this.f402a.m320O();
            LocalBroadcastManager.getInstance(this.f402a.c).sendBroadcast(new Intent(C0507l.f561k));
            this.f402a.m415z();
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$9 */
    class C04789 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f403a;

        C04789(NativeAPI nativeAPI) {
            this.f403a = nativeAPI;
        }

        public void run() {
            this.f403a.m359A();
        }
    }

    protected class LocalBroadcastReciever extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f404a;

        protected LocalBroadcastReciever(NativeAPI nativeAPI) {
            this.f404a = nativeAPI;
        }

        public void onReceive(Context context, Intent intent) {
            if (C0507l.f551a.equals(intent.getAction())) {
                CharSequence stringExtra = intent.getStringExtra(C0507l.f552b);
                String stringExtra2 = intent.getStringExtra(C0507l.f553c);
                if (StringUtils.m580a(stringExtra)) {
                    C0562j.m759c("BroadcastEvent, unable to broadcast empty event");
                    return;
                }
                if (KongregateEvent.CLOSED_KONGREGATE.equals(stringExtra)) {
                    C0562j.m753a("Kong Panel closed, clearing active flag");
                    this.f404a.f451y.set(false);
                    this.f404a.m392e("panel-closed");
                }
                this.f404a.m310a(stringExtra, stringExtra2);
            } else if (C0507l.f554d.equals(intent.getAction())) {
                this.f404a.m396h(intent.getStringExtra(C0507l.f555e));
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$a */
    class C0479a extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f405a;
        /* renamed from: b */
        private final AtomicBoolean f406b = new AtomicBoolean(false);

        C0479a(NativeAPI nativeAPI) {
            this.f405a = nativeAPI;
        }

        /* renamed from: a */
        void m301a() {
            synchronized (this.f406b) {
                if (this.f406b.compareAndSet(false, true)) {
                    C0562j.m753a("register for conn changes");
                    this.f405a.getApplicationContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                }
            }
        }

        /* renamed from: b */
        void m302b() {
            synchronized (this.f406b) {
                if (this.f406b.compareAndSet(true, false)) {
                    C0562j.m753a("unregister for conn changes");
                    this.f405a.getApplicationContext().unregisterReceiver(this);
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            C0562j.m756b("Connectivity changed: " + this.f405a.m362D().m1093b());
            if (this.f405a.m362D().m1093b()) {
                m302b();
                this.f405a.m414y();
            }
        }
    }

    @TargetApi(14)
    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$b */
    class C0480b implements ActivityLifecycleCallbacks {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f407a;

        C0480b(NativeAPI nativeAPI) {
            this.f407a = nativeAPI;
        }

        public void onActivityResumed(Activity activity) {
            this.f407a.m350f(activity);
        }

        public void onActivityPaused(Activity activity) {
            this.f407a.m340b((Context) activity);
        }

        public void onActivityDestroyed(Activity activity) {
            this.f407a.m348e(activity);
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            this.f407a.m334a(activity, bundle);
        }

        public void onActivityStarted(Activity activity) {
            C0562j.m756b(String.VIDEO_START);
        }

        public void onActivityStopped(Activity activity) {
            C0562j.m756b("stop");
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$c */
    protected class C0483c extends C0482c {
        /* renamed from: b */
        final /* synthetic */ NativeAPI f413b;

        protected C0483c(NativeAPI nativeAPI) {
            this.f413b = nativeAPI;
            super(nativeAPI);
        }

        public void submit(String str, long j) {
            if (NativeAPI.f427H.mo1252b()) {
                final long g = C0666a.m1145a().m1177g();
                final String str2 = str;
                final long j2 = j;
                C0626d.m962a(new Runnable(this) {
                    /* renamed from: d */
                    final /* synthetic */ C0483c f411d;

                    public void run() {
                        if (this.f411d.f413b.f443q != null) {
                            C0613e.m939a(this.f411d.f413b.f443q.getWritableDatabase(), g, this.f411d.f413b.a, str2, j2);
                        }
                    }
                });
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$d */
    protected class C0484d implements Runnable {
        /* renamed from: a */
        final /* synthetic */ NativeAPI f414a;

        protected C0484d(NativeAPI nativeAPI) {
            this.f414a = nativeAPI;
        }

        /* renamed from: a */
        private void m303a(SQLiteDatabase sQLiteDatabase) {
            if (DatabaseUtils.longForQuery(sQLiteDatabase, "SELECT COUNT(_id) FROM statistic_records WHERE statistic_id IS NULL", null) <= 0) {
                NativeAPI.f428I.set(0);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long j = (long) NativeAPI.f429J.get();
            long j2 = currentTimeMillis - NativeAPI.f428I.get();
            long j3 = 120000 * j;
            if (j >= 10 || j2 < j3) {
                C0562j.m753a("Not requesting statistic definitions, too soon since last request. cooldownPeriod=" + j3 + ", timePassed=" + j2 + ", counter=" + j);
                return;
            }
            NativeAPI.f428I.set(currentTimeMillis);
            C0562j.m756b("Found unknown statistic records, hitting server for definitions, counter=" + ((long) NativeAPI.f429J.incrementAndGet()));
            this.f414a.m365G();
        }

        public void run() {
            try {
                SQLiteDatabase a = C0637b.m1044a();
                m303a(a);
                new C0612a(this.f414a.c, a, this.f414a.m363E().m1177g(), this.f414a.a, C0640a.m1054a("/statistic_records/mobile_submit.json", true).toString()).run();
            } catch (Throwable e) {
                C0562j.m760c("SQLiteException in StatSubmissionTask", e);
            }
        }
    }

    public /* bridge */ /* synthetic */ void addEventBundleListener(KongregateEventBundleListener kongregateEventBundleListener) {
        super.addEventBundleListener(kongregateEventBundleListener);
    }

    public /* bridge */ /* synthetic */ void addEventListener(KongregateEventListener kongregateEventListener) {
        super.addEventListener(kongregateEventListener);
    }

    public /* bridge */ /* synthetic */ AnalyticsServices analytics() {
        return super.analytics();
    }

    public /* bridge */ /* synthetic */ String getApiKey() {
        return super.getApiKey();
    }

    public /* bridge */ /* synthetic */ Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public /* bridge */ /* synthetic */ long getApplicationId() {
        return super.getApplicationId();
    }

    public /* bridge */ /* synthetic */ MobileServices mobile() {
        return super.mobile();
    }

    public /* bridge */ /* synthetic */ MicrotransactionServices mtx() {
        return super.mtx();
    }

    public /* bridge */ /* synthetic */ List pollEventBundles() {
        return super.pollEventBundles();
    }

    public /* bridge */ /* synthetic */ List pollEvents() {
        return super.pollEvents();
    }

    public /* bridge */ /* synthetic */ void removeEventBundleListener(KongregateEventBundleListener kongregateEventBundleListener) {
        super.removeEventBundleListener(kongregateEventBundleListener);
    }

    public /* bridge */ /* synthetic */ void removeEventListener(KongregateEventListener kongregateEventListener) {
        super.removeEventListener(kongregateEventListener);
    }

    public /* bridge */ /* synthetic */ KongregateServices services() {
        return super.services();
    }

    public /* bridge */ /* synthetic */ StatServices stats() {
        return super.stats();
    }

    /* renamed from: f */
    public String m393f() {
        return m380b(null);
    }

    /* renamed from: b */
    public String m380b(String str) {
        return m381b(str, null);
    }

    /* renamed from: b */
    public String m381b(String str, String str2) {
        String str3 = "https://" + C0635b.m1001a().m1014c() + "/mobile_api?api=" + KongregateAPI.KONGREGATE_API_VERSION;
        if (m367I()) {
            str3 = str3 + "&unsigned=true";
        }
        if (!C0542a.m606a(19) && Boolean.TRUE.equals(this.f444r.get(KongregateAPI.KONGREGATE_OPTION_DEBUG_WEBVIEW))) {
            str3 = str3 + "&debug=true";
        }
        str3 = str3 + "#/";
        if (StringUtils.m587c((CharSequence) str)) {
            return str3;
        }
        str3 = str3 + "redirect?target=" + str;
        if (StringUtils.m584b((CharSequence) str2)) {
            return str3 + "&target_id=" + str2;
        }
        return str3;
    }

    public NativeAPI(Activity activity, long j, String str, Map<String, Object> map) {
        String str2;
        super(activity, j, str);
        m322Q();
        if (map != null) {
            this.f444r = Collections.unmodifiableMap(C0506k.m455a(map));
            Object obj = this.f444r.get("domain");
            str2 = obj instanceof String ? (String) obj : "m.kongregate.com";
        } else {
            this.f444r = Collections.unmodifiableMap(new HashMap());
            str2 = "m.kongregate.com";
        }
        if (C0487b.m425a(this.f444r, KongregateAPI.KONGREGATE_OPTION_CRASHLYTICS_LOGGING, false)) {
            C0562j.m758b(true);
        }
        if (m397h()) {
            C0543b.m614a("kong_sdk_version", KongregateAPI.KONGREGATE_API_VERSION);
        }
        C0562j.m756b("Kongregate NativeAPI initialized, domain: " + str2 + ", version: " + KongregateAPI.KONGREGATE_API_VERSION + ", feature_set: " + f427H.mo1251a());
        BroadcastReceiver localBroadcastReciever = new LocalBroadcastReciever(this);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(localBroadcastReciever, new IntentFilter(C0507l.f551a));
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(localBroadcastReciever, new IntentFilter(C0507l.f554d));
        m323R();
        m325T();
        m373a(activity);
        final long j2 = j;
        final Activity activity2 = activity;
        C0626d.m962a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ NativeAPI f388d;

            /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$1$1 */
            class C04641 implements C0463a {
                /* renamed from: a */
                final /* synthetic */ C04691 f376a;

                C04641(C04691 c04691) {
                    this.f376a = c04691;
                }

                /* renamed from: a */
                public void mo1131a(String str) {
                    synchronized (this.f376a.f388d.f447u) {
                        if (this.f376a.f388d.f446t.get()) {
                            this.f376a.f388d.m309a(str);
                            if (KongregateEvent.USER_CHANGED.equals(str)) {
                                C0626d.m962a(new C0484d(this.f376a.f388d));
                            } else if (KongregateEvent.LOGIN_COMPLETE.equals(str)) {
                                this.f376a.f388d.m414y();
                            }
                        } else {
                            C0562j.m756b("Queueing pre-ready event: " + str);
                            this.f376a.f388d.f447u.add(str);
                        }
                    }
                }

                /* renamed from: a */
                public void mo1130a() {
                    this.f376a.f388d.m410u().m529b(new HashMap());
                }
            }

            /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$1$2 */
            class C04652 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C04691 f377a;

                C04652(C04691 c04691) {
                    this.f377a = c04691;
                }

                public void run() {
                    this.f377a.f388d.m320O();
                }
            }

            /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$1$3 */
            class C04663 extends KongregateEventBundleListener {
                /* renamed from: a */
                final /* synthetic */ C04691 f378a;

                C04663(C04691 c04691) {
                    this.f378a = c04691;
                }

                public void onKongregateEventBundle(String str, String str2) {
                    if (KongregateEvent.USER_CHANGED.equals(str) && this.f378a.f388d.m397h()) {
                        C0543b.m614a("kong_username", this.f378a.f388d.m363E().m1178h());
                    }
                }
            }

            /* renamed from: com.kongregate.android.internal.sdk.NativeAPI$1$4 */
            class C04674 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C04691 f379a;

                C04674(C04691 c04691) {
                    this.f379a = c04691;
                }

                public void run() {
                    this.f379a.f388d.m413x();
                    synchronized (this.f379a.f388d.f447u) {
                        this.f379a.f388d.f446t.set(true);
                        C0562j.m756b("READY: true");
                        this.f379a.f388d.m309a(KongregateEvent.READY);
                        for (String a : this.f379a.f388d.f447u) {
                            this.f379a.f388d.m309a(a);
                        }
                    }
                }
            }

            public void run() {
                C0558g.m673a(this.f388d.c);
                Properties properties = new Properties();
                properties.put(C0635b.f971b.m987a(), str2);
                Object obj = this.f388d.f444r.get(C0635b.f972c.m987a());
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.length() > 0) {
                        properties.put(C0635b.f972c.m987a(), str);
                        C0562j.m756b("Using FB App ID override: " + str);
                    }
                }
                C0635b a = C0635b.m1003a("kong-api-" + j2, properties);
                this.f388d.f448v = this.f388d.c.getSharedPreferences(NativeAPI.f430k, 0);
                this.f388d.f443q = new C0527p(this.f388d.c, a.m1014c());
                C0637b.m1046a(this.f388d.f443q.getWritableDatabase());
                this.f388d.m366H();
                C0462b.m272a(this.f388d.c);
                C0645b.m1086a(this.f388d.getApplicationContext(), "HttpClient/4.0 KongregateMobileApi/3.0.5", j2, this.f388d.m321P());
                this.f388d.m363E().m1163a(this.f388d.c, new C04641(this));
                this.f388d.m363E().m1171b();
                this.f388d.m346d(activity2);
                this.f388d.f440E = C0626d.m964a(5, 15, TimeUnit.SECONDS, new C0484d(this.f388d));
                if (NativeAPI.f427H.mo1253c()) {
                    C0462b.m268a(C0564l.m772a(this.f388d.f448v, "panel_reload_delay_minutes", (int) C0462b.f362a));
                    C0626d.m965a(15, TimeUnit.SECONDS, new C04652(this));
                }
                this.f388d.addEventBundleListener(new C04663(this));
                C0626d.m968b(new C04674(this));
                if (this.f388d.m397h()) {
                    C0543b.m614a("kong_username", this.f388d.m363E().m1178h());
                }
            }
        });
        m343c((Context) activity);
    }

    /* renamed from: g */
    public static C0629a m351g() {
        return f427H;
    }

    /* renamed from: O */
    private void m320O() {
        if (f427H.mo1253c()) {
            C0626d.m968b(new C04746(this));
        }
    }

    /* renamed from: c */
    public void m388c(String str) {
        synchronized (this.f447u) {
            if (this.f446t.get()) {
                m309a(str);
            } else {
                C0562j.m756b("Queueing pre-ready event: " + str);
                this.f447u.add(str);
            }
        }
    }

    /* renamed from: a */
    void m373a(Activity activity) {
        Uri uri = null;
        if (m371a(KongregateAPI.KONGREGATE_OPTION_AUTO_PROCESS_DEEP_LINKS, Boolean.valueOf(false)).booleanValue()) {
            Intent intent = activity.getIntent();
            if (intent != null && "android.intent.action.VIEW".equals(intent.getAction())) {
                String uri2;
                String action = intent.getAction();
                Uri data = intent.getData();
                if (data != null) {
                    uri2 = data.toString();
                }
                C0562j.m753a("launch action=" + action + " data=" + uri2);
                uri = data;
            }
            willOpenUrl(uri);
        }
    }

    /* renamed from: h */
    boolean m397h() {
        return C0487b.m425a(this.f444r, KongregateAPI.KONGREGATE_OPTION_CRASHLYTICS_USER_KEYS, false);
    }

    /* renamed from: i */
    boolean m398i() {
        return C0564l.m774a(this.f448v, "keen", true);
    }

    /* renamed from: j */
    boolean m399j() {
        return C0564l.m774a(this.f448v, "swrve", true);
    }

    /* renamed from: k */
    boolean m400k() {
        return C0564l.m774a(this.f448v, "delta", true);
    }

    /* renamed from: l */
    boolean m401l() {
        return C0564l.m774a(this.f448v, "kong_analytics", true);
    }

    /* renamed from: m */
    boolean m402m() {
        boolean z;
        if (Boolean.FALSE.equals(this.f444r.get(KongregateAPI.KONGREGATE_OPTION_PERSISTENT_WEBVIEW))) {
            z = false;
        } else {
            z = true;
        }
        return z && C0564l.m774a(this.f448v, KongregateAPI.KONGREGATE_OPTION_PERSISTENT_WEBVIEW, true);
    }

    /* renamed from: n */
    Set<String> m403n() {
        return new HashSet(Arrays.asList(C0487b.m428d(this.f444r, KongregateAPI.KONGREGATE_OPTION_SUPPORTED_PANEL_EVENTS)));
    }

    /* renamed from: o */
    boolean m404o() {
        return C0487b.m425a(this.f444r, "guild_chat", false);
    }

    /* renamed from: p */
    boolean m405p() {
        return C0564l.m774a(this.f448v, "adjust", true);
    }

    /* renamed from: q */
    boolean m406q() {
        return C0564l.m774a(this.f448v, "adx", true);
    }

    /* renamed from: r */
    int m407r() {
        return C0564l.m772a(this.f448v, f431l, 0);
    }

    /* renamed from: s */
    boolean m408s() {
        return C0564l.m774a(this.f448v, f432m, false);
    }

    /* renamed from: t */
    boolean m409t() {
        return C0564l.m774a(this.f448v, f433n, false);
    }

    /* renamed from: u */
    protected C0525o m410u() {
        return (C0525o) this.g;
    }

    /* renamed from: P */
    private String m321P() {
        return m402m() ? "pw" : "";
    }

    /* renamed from: v */
    protected Context m411v() {
        return this.c;
    }

    /* renamed from: d */
    private void m346d(Activity activity) {
        Map hashMap = new HashMap(this.f444r);
        hashMap.put("_keen_enabled_internal", Boolean.valueOf(m398i()));
        hashMap.put("_swrve_enabled_internal", Boolean.valueOf(m399j()));
        hashMap.put("_adjust_enabled_internal", Boolean.valueOf(m405p()));
        hashMap.put("_adx_enabled_internal", Boolean.valueOf(m406q()));
        hashMap.put("_delta_enabled_internal", Boolean.valueOf(m400k()));
        hashMap.put("_kong_analytics_enabled_internal", Boolean.valueOf(m401l()));
        m410u().m519a(activity, Collections.unmodifiableMap(hashMap));
    }

    /* renamed from: Q */
    private void m322Q() {
        C0626d.m968b(new C04767(this));
    }

    @TargetApi(19)
    /* renamed from: R */
    private void m323R() {
        if (Boolean.TRUE.equals(this.f444r.get(KongregateAPI.KONGREGATE_OPTION_DEBUG_WEBVIEW)) && C0542a.m606a(19)) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public boolean isReady() {
        return this.f446t.get();
    }

    /* renamed from: w */
    public Uri m412w() {
        return this.f439D;
    }

    /* renamed from: d */
    protected void m390d(String str) {
        if (StringUtils.m581a(str, MobileServices.GDPR_ALERT_TRIGGER)) {
            m361C();
        }
    }

    /* renamed from: x */
    protected void m413x() {
        if (m362D().m1093b()) {
            m364F();
        } else {
            this.f442G.m301a();
        }
    }

    /* renamed from: y */
    protected void m414y() {
        long currentTimeMillis = System.currentTimeMillis();
        if (m363E().m1174d() || currentTimeMillis - this.f450x.get() >= 60000) {
            this.f450x.set(currentTimeMillis);
            C0562j.m753a("Native API - download user and game info: " + m363E().m1178h());
            m362D().m1090a(C0640a.m1054a("/games/play_mobile.json?game_id=" + this.a + (m363E().m1174d() ? "&register_gameplay=true" : null), true).toString(), new C04778(this));
            return;
        }
        C0562j.m753a("Native API - too soon, not registering play mobile");
    }

    /* renamed from: z */
    protected void m415z() {
        C0626d.m962a(new C04789(this));
    }

    /* renamed from: A */
    protected void m359A() {
        C0666a E = m363E();
        if (m371a(KongregateAPI.KONGREGATE_OPTION_TEST_GDPR_ALERT, Boolean.valueOf(false)).booleanValue()) {
            C0562j.m753a("GDPR Check: testing alert");
            if (m371a(KongregateAPI.KONGREGATE_OPTION_DEFER_GDPR_ALERT, Boolean.valueOf(false)).booleanValue()) {
                E.m1170b(true);
                return;
            } else {
                m360B();
                return;
            }
        }
        E.m1170b(false);
        if (E.m1176f() && !m409t()) {
            C0562j.m753a("GDPR Check: Active user is guest and guests are not targeted for gdpr. skippping check");
        } else if (!E.m1176f() && !m408s()) {
            C0562j.m753a("GDPR Check: Active user is registered and not targetting registered users for gdpr. skipping check");
        } else if (!E.m1176f() && m408s() && E.m1184n()) {
            C0562j.m753a("GDPR Check: Active user accepted online privacy policy. skipping local check");
        } else {
            int r = m407r();
            long o = (long) E.m1185o();
            C0562j.m753a("GDPR Check: checking local GDPR: accepted_version=" + o + ", current_policy_version=" + r);
            if (o >= ((long) r)) {
                return;
            }
            if (m371a(KongregateAPI.KONGREGATE_OPTION_DEFER_GDPR_ALERT, Boolean.valueOf(false)).booleanValue()) {
                E.m1170b(true);
            } else {
                m360B();
            }
        }
    }

    /* renamed from: B */
    protected void m360B() {
        C0666a E = m363E();
        if (E.m1184n()) {
            C0562j.m753a("GDPR show alert: user already accepted");
        } else if (E.m1187q()) {
            m394f(KongregatePanelActivity.ALERT_TYPE_GDPR_UNDERAGE);
        } else {
            m394f(KongregatePanelActivity.ALERT_TYPE_GDPR_ADULT);
        }
    }

    /* renamed from: C */
    protected void m361C() {
        C0666a E = m363E();
        if (E.m1191u()) {
            E.m1170b(false);
            m360B();
        }
    }

    /* renamed from: D */
    protected C0645b m362D() {
        return C0645b.m1085a();
    }

    /* renamed from: E */
    protected C0666a m363E() {
        return C0666a.m1145a();
    }

    /* renamed from: F */
    protected void m364F() {
        m414y();
        if (this.f445s != null) {
            this.f445s.m290d();
        }
    }

    /* renamed from: G */
    protected void m365G() {
        m362D().m1090a(C0640a.m1054a("/games/full_manifest.json?game_id=" + this.a, true).toString(), new C0468a(this) {
            /* renamed from: a */
            final /* synthetic */ NativeAPI f380a;

            {
                this.f380a = r1;
            }

            /* renamed from: a */
            public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
                C0562j.m756b("Game manifest received");
                this.f380a.m378a(jSONObject.optJSONObject("manifest"));
            }
        });
    }

    /* renamed from: a */
    protected KongregateServices mo1150a() {
        return new C0508m();
    }

    /* renamed from: b */
    protected StatServices mo1151b() {
        return new C0483c(this);
    }

    /* renamed from: e */
    protected MicrotransactionServices mo1154e() {
        return new C0512n(this);
    }

    /* renamed from: c */
    protected MobileServices mo1152c() {
        return new C0528q(this.c);
    }

    public void onPause(Activity activity) {
        if (!m324S()) {
            m340b((Context) activity);
        }
    }

    public void onPause(Activity activity, String str) {
        m410u().setCommonProperties(str);
        onPause(activity);
    }

    public void onPause(Activity activity, Map<String, Object> map) {
        m410u().setCommonProperties((Map) map);
        onPause(activity);
    }

    public void onResume(Activity activity) {
        if (!m324S()) {
            m350f(activity);
        }
    }

    public void onResume(Activity activity, String str) {
        m410u().setCommonProperties(str);
        onResume(activity);
    }

    public void onResume(Activity activity, Map<String, Object> map) {
        m410u().setCommonProperties((Map) map);
        onResume(activity);
    }

    public void onCreate(Activity activity, Bundle bundle) {
        if (!m324S()) {
            m334a(activity, bundle);
        }
    }

    public void onDestroy(Activity activity) {
        if (!m324S()) {
            m348e(activity);
        }
    }

    public void onLowMemory() {
        m410u().m538k();
    }

    /* renamed from: a */
    private void m334a(Activity activity, Bundle bundle) {
        m373a(activity);
        m410u().m518a(activity, bundle, this.f439D);
        activity.getIntent();
    }

    public void willOpenUrl(Uri uri) {
        this.f439D = uri;
        if (uri != null && StringUtils.m584b(uri.toString())) {
            m388c(KongregateEvent.OPEN_DEEP_LINK);
            m410u().m539l();
        }
    }

    /* renamed from: e */
    private void m348e(Activity activity) {
        m410u().m528b(activity);
    }

    /* renamed from: b */
    private void m340b(Context context) {
        this.f437B--;
        if (this.f437B > 0 && this.f438C) {
            C0562j.m753a("Native API - onPause: other activities are still active. do not pause.");
        } else if (this.f446t.get()) {
            C0562j.m753a("Native API - onPause: ready");
            this.f452z = new CountDownLatch(1);
            C0626d.m962a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ NativeAPI f381a;

                {
                    this.f381a = r1;
                }

                public void run() {
                    this.f381a.f436A = false;
                    try {
                        C0562j.m753a("Native API - onPause: waiting to pause");
                        this.f381a.f436A = !this.f381a.f452z.await(1, TimeUnit.SECONDS);
                    } catch (Throwable e) {
                        C0562j.m760c("Native API - onPause: pause latch interrupted: ", e);
                        this.f381a.f436A = true;
                    }
                    C0562j.m753a("Native API - onPause: pausing: " + this.f381a.f436A);
                    if (this.f381a.f436A) {
                        this.f381a.f442G.m302b();
                        this.f381a.m327V();
                        if (this.f381a.m363E().m1176f()) {
                            this.f381a.m363E().m1167a(false);
                        }
                        this.f381a.m392e("app-background");
                    }
                }
            });
            m410u().m535h();
        }
    }

    /* renamed from: f */
    private void m350f(Activity activity) {
        this.f437B++;
        if (this.f446t.get()) {
            C0562j.m753a("Native API - onResume: ready");
            if (this.f452z != null) {
                this.f452z.countDown();
            }
            m343c((Context) activity);
            C0626d.m962a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ NativeAPI f382a;

                {
                    this.f382a = r1;
                }

                public void run() {
                    if (this.f382a.f436A) {
                        C0562j.m753a("Native API - onResume: is paused. Resuming...");
                        this.f382a.m328W();
                        boolean b = this.f382a.m363E().m1171b();
                        this.f382a.m413x();
                        this.f382a.f436A = false;
                        this.f382a.m377a("app-foreground", b);
                        return;
                    }
                    C0562j.m753a("Native API - onResume: not paused. don't schedule new tasks");
                }
            });
            m410u().m517a(activity);
        }
    }

    /* renamed from: e */
    public void m392e(String str) {
        m377a(str, false);
    }

    /* renamed from: a */
    public void m377a(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("foreground", !this.f436A);
            jSONObject.put("panel-open", this.f451y.get());
            jSONObject.put("user-changed", z);
            if (this.f445s != null) {
                this.f445s.m287a(str, jSONObject);
            }
        } catch (Throwable e) {
            C0562j.m760c("NativeAPI - exception building app-state message.", e);
        }
    }

    /* renamed from: S */
    private boolean m324S() {
        return C0542a.m606a(14) && !Boolean.FALSE.equals(this.f444r.get(KongregateAPI.KONGREGATE_OPTION_MANAGE_LIFECYCLE));
    }

    @TargetApi(14)
    /* renamed from: T */
    private void m325T() {
        this.f438C = C0487b.m425a(this.f444r, KongregateAPI.KONGREGATE_OPTION_STRICT_LIFECYCLE_MODE, false);
        if (m324S()) {
            Application U = m326U();
            if (U != null) {
                C0562j.m756b("Registering lifecycle callbacks");
                this.f449w = new C0480b(this);
                U.registerActivityLifecycleCallbacks(this.f449w);
                return;
            }
            C0562j.m759c("KONGREGATE CONFIGURATION WARNING: Could not get application instance. Try passing an Activity into the SDK initialize function");
            return;
        }
        C0562j.m756b("Not managing application lifecycle");
    }

    /* renamed from: U */
    private Application m326U() {
        if (this.c instanceof Activity) {
            return ((Activity) this.c).getApplication();
        }
        Context applicationContext = this.c.getApplicationContext();
        if (applicationContext instanceof Application) {
            return (Application) applicationContext;
        }
        return null;
    }

    /* renamed from: c */
    private void m343c(Context context) {
        if ((context instanceof Activity) && m371a(KongregateAPI.KONGREGATE_OPTION_ALLOW_IMMERSIVE_MODE, Boolean.valueOf(true)).booleanValue()) {
            final WeakReference weakReference = new WeakReference((Activity) context);
            C0626d.m968b(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ NativeAPI f384b;

                public void run() {
                    C0542a.m602a((Activity) weakReference.get(), true);
                }
            });
        }
    }

    /* renamed from: V */
    private void m327V() {
        ((C0525o) this.g).m536i();
        if (!(this.f440E == null || this.f440E.isCancelled())) {
            this.f440E.cancel(false);
        }
        if (this.f445s != null) {
            this.f445s.m289c();
        }
    }

    /* renamed from: W */
    private void m328W() {
        m410u().m537j();
        if (this.f440E != null && this.f440E.isCancelled()) {
            this.f440E = C0626d.m964a(5, 15, TimeUnit.SECONDS, new C0484d(this));
        }
        if (this.f445s != null) {
            this.f445s.m290d();
        }
    }

    /* renamed from: d */
    protected AnalyticsServices mo1153d() {
        return new C0525o(this.c);
    }

    /* renamed from: a */
    protected void m378a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(C0607d.f906a);
        if (optJSONArray != null) {
            C0607d.m925a(this.a, optJSONArray);
        }
        optJSONArray = jSONObject.optJSONArray(C0603c.f884a);
        if (optJSONArray != null) {
            C0603c.m919a(this.a, optJSONArray);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("config");
        if (optJSONObject != null) {
            Editor edit = this.f448v.edit();
            edit.putBoolean("keen", optJSONObject.optBoolean("keen", true));
            edit.putBoolean("swrve", optJSONObject.optBoolean("swrve", true));
            edit.putBoolean("adjust", optJSONObject.optBoolean("adjust", true));
            edit.putBoolean("adx", optJSONObject.optBoolean("adx", true));
            edit.putBoolean(KongregateAPI.KONGREGATE_OPTION_PERSISTENT_WEBVIEW, optJSONObject.optBoolean(KongregateAPI.KONGREGATE_OPTION_PERSISTENT_WEBVIEW, true));
            edit.putInt(f431l, optJSONObject.optInt(f431l, 0));
            edit.putBoolean(f432m, optJSONObject.optBoolean(f432m, false));
            edit.putBoolean(f433n, optJSONObject.optBoolean(f433n, false));
            int a = C0561i.m732a(optJSONObject, "panel_reload_delay_minutes", (int) C0462b.f362a);
            edit.putInt("panel_reload_delay_minutes", a);
            C0462b.m268a(a);
            edit.apply();
            C0640a b = C0640a.m1055b();
            if (b != null) {
                b.m1064a(this.a, m321P());
            }
        }
    }

    /* renamed from: f */
    void m394f(String str) {
        Intent intent = new Intent(this.c, KongregatePanelActivity.class);
        intent.setFlags(DriveFile.MODE_WRITE_ONLY);
        if (!(this.c instanceof Activity)) {
            intent.setFlags(DriveFile.MODE_READ_ONLY);
        }
        intent.putExtra(KongregatePanelActivity.INTENT_EXTRA_ALERT, str);
        m411v().startActivity(intent);
    }

    /* renamed from: a */
    void m375a(final Context context, final String str, final String str2) {
        C0562j.m753a("openning kong panel with target: " + str);
        if (context != null) {
            C0626d.m968b(new Runnable(this) {
                /* renamed from: d */
                final /* synthetic */ NativeAPI f392d;

                public void run() {
                    this.f392d.m320O();
                    if (this.f392d.f451y.compareAndSet(false, true)) {
                        C0562j.m753a("Opening Kong Panel with target: " + str);
                        Intent intent = new Intent(context, KongregatePanelActivity.class);
                        intent.putExtra(KongregatePanelActivity.INTENT_EXTRA_TARGET, str);
                        intent.putExtra(KongregatePanelActivity.INTENT_EXTRA_TARGET_ID, str2);
                        intent.putExtra(C0451a.f312b, this.f392d.m371a(KongregateAPI.KONGREGATE_OPTION_SHOW_SYSTEM_UI, Boolean.FALSE));
                        intent.putExtra(C0451a.f313c, this.f392d.m371a(KongregateAPI.KONGREGATE_OPTION_ALLOW_IMMERSIVE_MODE, Boolean.TRUE));
                        intent.putExtra(C0451a.f317g, C0487b.m422a(this.f392d.f444r, KongregateAPI.KONGREGATE_OPTION_PANEL_ORIENTATION_OVERRIDE, null));
                        String a = C0487b.m422a(this.f392d.f444r, KongregateAPI.KONGREGATE_OPTION_DEFAULT_PANEL_TRANSITION, null);
                        intent.putExtra(C0451a.f318h, a);
                        intent.setFlags(DriveFile.MODE_WRITE_ONLY);
                        if (!(context instanceof Activity)) {
                            intent.setFlags(DriveFile.MODE_READ_ONLY);
                        }
                        context.startActivity(intent);
                        if ((context instanceof Activity) && KongregatePanelActivity.PANEL_TRANSITION_MAP.containsKey(a)) {
                            String[] strArr = (String[]) KongregatePanelActivity.PANEL_TRANSITION_MAP.get(a);
                            ((Activity) context).overridePendingTransition(context.getResources().getIdentifier(strArr[0], "anim", context.getPackageName()), context.getResources().getIdentifier(strArr[1], "anim", context.getPackageName()));
                        }
                        this.f392d.m309a(KongregateEvent.OPENING_KONGREGATE);
                        this.f392d.m392e("panel-opened");
                        return;
                    }
                    C0562j.m759c("Kong Panel is already showing, ignoring showPanel call");
                }
            });
        }
    }

    /* renamed from: a */
    void m374a(Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(C0507l.f560j));
    }

    /* renamed from: H */
    protected void m366H() {
        try {
            boolean z;
            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 4239);
            boolean J = m368J();
            String str = packageInfo.packageName != null ? packageInfo.packageName : "";
            String substring = str.substring(str.lastIndexOf("."));
            if (J && !((".google".equals(substring) || ".amazon".equals(substring)) && str.indexOf("com.kongregate.mobile.") == 0)) {
                C0562j.m759c("KONGREGATE CONFIGURATION WARNING: package name does not follow naming convention. Should be: \"com.kongregate.mobile.<yourgamename>.(google|amazon)\" not: \"" + str + "\"");
            }
            if (J && packageInfo.providers != null) {
                for (ProviderInfo providerInfo : packageInfo.providers) {
                    if ("com.kongregate.permission.ReadSharedData2".equals(providerInfo.readPermission)) {
                        Class cls;
                        try {
                            cls = Class.forName(providerInfo.name);
                        } catch (ClassNotFoundException e) {
                            C0562j.m759c("KONGREGATE CONFIGURATION WARNING: Provider found with com.kongregate.permission.ReadSharedData2 permission, but the class " + providerInfo.name + " is not found");
                            cls = null;
                        }
                        if (cls != null && KongregateSharedSecretProvider.class.equals(cls.getSuperclass())) {
                            break;
                        }
                    }
                }
            }
            ProviderInfo providerInfo2 = null;
            if (packageInfo.permissions != null) {
                for (PermissionInfo permissionInfo : packageInfo.permissions) {
                    if ("com.kongregate.permission.ReadSharedData2".equals(permissionInfo.name) && !J) {
                        if (m369K()) {
                            m384b("Allowing READ_PERMISSION_2 with SSO disabled since this is TPT", false);
                        } else {
                            m395g("Games that do not support shared sign-on should not use new read permission: com.kongregate.permission.ReadSharedData2");
                        }
                    }
                }
            }
            if (providerInfo2 == null && J) {
                m384b("KONGREGATE CONFIGURATION WARNING: Must specify a " + KongregateSharedSecretProvider.class.toString() + "  subclass that requires the " + "com.kongregate.permission.ReadSharedData2" + " read permission.", true);
            } else if (J) {
                String str2 = providerInfo2.name;
                if (!(providerInfo2.authority == null || providerInfo2.authority.equals(str2) || f434o.contains(Long.valueOf(this.a)))) {
                    m395g("Authority for provider " + providerInfo2.name + " should be " + providerInfo2.name + " to prevent conflicts with other apps");
                }
                if (!providerInfo2.exported) {
                    m395g("The provider: " + providerInfo2.name + " should be exported");
                }
                if (".amazon".equals(substring) && providerInfo2.name.substring(providerInfo2.name.lastIndexOf(".")).indexOf(".Amazon") != 0) {
                    m395g("The provider for the amazon store should be named com.kongregate.android.api.providers.Amazon<YourGameName>SharedSecretProvider");
                }
            }
            if (packageInfo.permissions != null) {
                z = false;
                for (PermissionInfo permissionInfo2 : packageInfo.permissions) {
                    if (permissionInfo2.name.equals("com.kongregate.permission.ReadSharedData2")) {
                        if (permissionInfo2.protectionLevel != 2) {
                            m395g("Custom permission: com.kongregate.permission.ReadSharedData2 must have 'signature' protection level");
                        }
                        if (!J) {
                            if (m369K()) {
                                m384b("Allowing READ_PERMISSION_2 with SSO disabled since this is TPT", false);
                            } else {
                                m395g("Games that do not support shared sign-on should not use new read permission: com.kongregate.permission.ReadSharedData2");
                                z = true;
                            }
                        }
                        z = true;
                    }
                    if (SharedSecretProvider.READ_PERMISSION_DEPRECATED.equals(permissionInfo2.name) && m368J()) {
                        m395g("com.kongregate.permission.ReadSharedData has been deprecated. In it's place use: com.kongregate.permission.ReadSharedData2");
                    }
                }
            } else {
                z = false;
            }
            if (!z && J) {
                m384b("Manifest must include custom permission 'com.kongregate.permission.ReadSharedData2' with 'signature' protection level for shared sign-on to work", true);
            }
            if (z && m367I()) {
                m384b("APK not signed by Kongregate but SSO permission detected. Kongregate must resign before uploading to Google Play.", true);
            }
            List asList = Arrays.asList(packageInfo.requestedPermissions);
            if (asList.indexOf(SharedSecretProvider.READ_PERMISSION_DEPRECATED) > -1) {
                C0562j.m759c("KONGREGATE CONFIGURATION WARNING: permission has been deprecated: com.kongregate.permission.ReadSharedData");
            }
            if (asList.indexOf("com.kongregate.permission.ReadSharedData2") == -1 && J) {
                C0562j.m759c("KONGREGATE CONFIGURATION WARNING: must use permission: com.kongregate.permission.ReadSharedData2");
            }
            if (asList.indexOf("android.permission.ACCESS_NETWORK_STATE") == -1) {
                C0562j.m759c("KONGREGATE CONFIGURATION WARNING: must use permission: android.permission.ACCESS_NETWORK_STATE");
            }
            if (asList.indexOf("android.permission.INTERNET") == -1) {
                C0562j.m759c("KONGREGATE CONFIGURATION WARNING: must use permission: android.permission.INTERNET");
            }
            m376a(packageInfo);
            m383b(packageInfo);
            m384b(C0582d.m846a(packageInfo), true);
            if (C0487b.m425a(this.f444r, KongregateAPI.KONGREGATE_OPTION_TEST_GDPR_ALERT, false)) {
                m384b("Test option [KONGREGATE_OPTION_TEST_GDPR_ALERT] is set to true. This build should be for testing only", true);
            }
        } catch (Throwable e2) {
            C0562j.m762d("Package Not Found, unable to check configuration", e2);
        }
    }

    /* renamed from: g */
    void m395g(final String str) {
        C0562j.m761d("KONGREGATE CONFIGURATION ERROR: " + str);
        C0626d.m968b(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ NativeAPI f394b;

            public void run() {
                Toast.makeText(this.f394b.c, "KONG CONFIGURATION ERROR: " + str, 1).show();
            }
        });
        C0562j.m759c("APP WILL BE KILLED DUE TO KONGREGATE CONFIGURATION ERROR");
        try {
            Thread.sleep(FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
        } catch (InterruptedException e) {
        }
        C0626d.m968b(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ NativeAPI f396b;

            public void run() {
                throw new RuntimeException("KONGREGATE CONFIGURATION ERROR: " + str);
            }
        });
    }

    /* renamed from: b */
    void m384b(final String str, boolean z) {
        if (!StringUtils.m580a((CharSequence) str)) {
            C0562j.m759c("KONGREGATE CONFIGURATION WARNING: " + str);
            if (!z) {
                return;
            }
            if (!m367I() || !m371a("KONG_OPTION_SUPPRESS_TOAST_WARNINGS", Boolean.valueOf(false)).booleanValue()) {
                C0626d.m968b(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ NativeAPI f398b;

                    public void run() {
                        Toast.makeText(this.f398b.c, "KONG CONFIGURATION WARNING: " + str, 1).show();
                    }
                });
            }
        }
    }

    /* renamed from: I */
    boolean m367I() {
        return !C0563k.m767a(getApplicationContext(), -192598629) && m368J();
    }

    /* renamed from: J */
    protected boolean m368J() {
        boolean z = true;
        if (!f427H.mo1254d()) {
            return false;
        }
        if (m369K()) {
            return true;
        }
        if (C0563k.m768a(getApplicationContext(), "com.kongregate.mobile.SELF_SIGNED", false)) {
            return false;
        }
        if (f435p.contains(Long.valueOf(this.a))) {
            z = false;
        }
        return z;
    }

    /* renamed from: K */
    protected boolean m369K() {
        return "com.kongregate.android.test.thirdpartytest".equals(getApplicationContext().getPackageName());
    }

    /* renamed from: a */
    protected void m376a(PackageInfo packageInfo) {
        Object obj = null;
        if (f427H.mo1253c()) {
            ActivityInfo[] activityInfoArr = packageInfo.activities;
            int length = activityInfoArr.length;
            int i = 0;
            while (i < length) {
                ActivityInfo activityInfo = activityInfoArr[i];
                if (activityInfo.name.equals(KongregatePanelActivity.class.getName())) {
                    obj = 1;
                    if (activityInfo.theme != C0558g.m664a("KongregateDialogTheme", "style", this.c)) {
                        C0562j.m759c("KONGREGATE CONFIGURATION WARNING: KongregatePanelActivity should use android:theme=\"@style/KongregateDialogTheme\" in AndroidManifest.xml if possible.");
                    }
                }
                i = activityInfo.name.equals("com.facebook.LoginActivity") ? i + 1 : i + 1;
            }
            if (obj == null) {
                C0562j.m759c("KONGREGATE CONFIGURATION WARNING: must include actitiy: " + KongregatePanelActivity.class.getName());
            }
        }
    }

    /* renamed from: b */
    protected void m383b(PackageInfo packageInfo) {
        if (StringUtils.m580a(C0487b.m421a(this.f444r, KongregateAPI.KONGREGATE_OPTION_ADJUST_APP_TOKEN))) {
            C0562j.m759c("KONGREGATE CONFIGURATION WARNING: adjust not configured");
            return;
        }
        if (!AdjustConfig.ENVIRONMENT_PRODUCTION.equals(C0487b.m421a(this.f444r, KongregateAPI.KONGREGATE_OPTION_ADJUST_ENVIRONMENT))) {
            m384b("Be sure to change AdjustEnvironment meta-data tag to 'production' prior to release", true);
        }
        if (!C0578a.m827a(this.c, packageInfo)) {
            m384b("InstallReceiver misconfigured. You must specify 'com.adjust.sdk.AdjustReferrerReceiver' in the forwards of the InstallReceiver and the adjust.jar must be included.", true);
        }
    }

    /* renamed from: b */
    public void m382b(Activity activity) {
        if (!m324S()) {
            m340b((Context) activity);
        }
    }

    /* renamed from: c */
    public void m387c(Activity activity) {
        if (!m324S()) {
            m350f(activity);
        }
    }

    /* renamed from: h */
    protected void m396h(String str) {
        if (C0507l.f556f.equals(str)) {
            C0666a E = m363E();
            int r = m407r();
            C0562j.m759c("GDPR Accepted: user ID: " + E.m1177g() + " policy version: " + r);
            E.m1161a(r);
        } else if (C0507l.f557g.equals(str)) {
            C0562j.m759c("GDPR: View Policy");
            m375a(this.c, null, null);
        }
    }

    /* renamed from: c */
    public String m386c(String str, String str2) {
        Object obj = this.f444r.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return str2;
    }

    /* renamed from: a */
    public Integer m372a(String str, Integer num) {
        Object obj = this.f444r.get(str);
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        return num;
    }

    /* renamed from: a */
    public Boolean m371a(String str, Boolean bool) {
        Object obj = this.f444r.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return bool;
    }
}
