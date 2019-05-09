package com.kongregate.android.internal.browser;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import com.google.android.gms.common.util.GmsVersion;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0566n;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p001j.C0666a;
import com.kongregate.p000o.p006c.C0626d;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.kongregate.android.internal.browser.b */
public class C0462b {
    /* renamed from: a */
    public static final int f362a = 120;
    /* renamed from: b */
    private static AtomicReference<C0462b> f363b = new AtomicReference(null);
    /* renamed from: c */
    private static final AtomicReference<String> f364c = new AtomicReference(null);
    /* renamed from: d */
    private static final AtomicInteger f365d = new AtomicInteger(GmsVersion.VERSION_PARMESAN);
    /* renamed from: e */
    private MobileApiWebView f366e;
    /* renamed from: f */
    private final Context f367f;
    /* renamed from: g */
    private final String f368g;
    /* renamed from: h */
    private final boolean f369h;
    /* renamed from: i */
    private final boolean f370i;
    /* renamed from: j */
    private final Set<String> f371j;
    /* renamed from: k */
    private long f372k = 0;
    /* renamed from: l */
    private int f373l = 0;
    /* renamed from: m */
    private String f374m = null;
    /* renamed from: n */
    private final AtomicReference<ScheduledFuture<?>> f375n = new AtomicReference(null);

    /* renamed from: com.kongregate.android.internal.browser.b$3 */
    class C04603 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0462b f360a;

        C04603(C0462b c0462b) {
            this.f360a = c0462b;
        }

        public void run() {
            synchronized (this.f360a) {
                MobileApiWebView a = this.f360a.m285a(true);
                if (!(a == null || a.m262n())) {
                    a.m253a(false);
                    this.f360a.f372k = System.currentTimeMillis();
                }
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.b$a */
    public class C0461a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0462b f361a;

        public C0461a(C0462b c0462b) {
            this.f361a = c0462b;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0046 }
            r2 = r6.f361a;	 Catch:{ Throwable -> 0x0046 }
            monitor-enter(r2);	 Catch:{ Throwable -> 0x0046 }
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f366e;	 Catch:{ all -> 0x0043 }
            if (r3 != 0) goto L_0x0011;
        L_0x000f:
            monitor-exit(r2);	 Catch:{ all -> 0x0043 }
        L_0x0010:
            return;
        L_0x0011:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f366e;	 Catch:{ all -> 0x0043 }
            r3 = r3.m259k();	 Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x0030;
        L_0x001d:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f373l;	 Catch:{ all -> 0x0043 }
            if (r3 <= 0) goto L_0x0030;
        L_0x0025:
            r3 = "Panel is loaded, resetting reload attempt count";
            com.kongregate.android.internal.util.C0562j.m753a(r3);	 Catch:{ all -> 0x0043 }
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r4 = 0;
            r3.f373l = r4;	 Catch:{ all -> 0x0043 }
        L_0x0030:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f366e;	 Catch:{ all -> 0x0043 }
            r3 = r3.m262n();	 Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x004d;
        L_0x003c:
            r0 = "Ignoring cache sync request, WebView is currently attached";
            com.kongregate.android.internal.util.C0562j.m753a(r0);	 Catch:{ all -> 0x0043 }
            monitor-exit(r2);	 Catch:{ all -> 0x0043 }
            goto L_0x0010;
        L_0x0043:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0043 }
            throw r0;	 Catch:{ Throwable -> 0x0046 }
        L_0x0046:
            r0 = move-exception;
            r1 = "Exception while warming cache";
            com.kongregate.android.internal.util.C0562j.m760c(r1, r0);
            goto L_0x0010;
        L_0x004d:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f369h;	 Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x0093;
        L_0x0055:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f373l;	 Catch:{ all -> 0x0043 }
            r4 = 5;
            if (r3 > r4) goto L_0x0093;
        L_0x005e:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.f366e;	 Catch:{ all -> 0x0043 }
            r3 = r3.m259k();	 Catch:{ all -> 0x0043 }
            if (r3 != 0) goto L_0x0093;
        L_0x006a:
            r0 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r0.f373l = r0.f373l + 1;	 Catch:{ all -> 0x0043 }
            r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0043 }
            r0.<init>();	 Catch:{ all -> 0x0043 }
            r1 = "Panel has not loaded, attempting a reload, reloadAttempt=";
            r0 = r0.append(r1);	 Catch:{ all -> 0x0043 }
            r1 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r1 = r1.f373l;	 Catch:{ all -> 0x0043 }
            r0 = r0.append(r1);	 Catch:{ all -> 0x0043 }
            r0 = r0.toString();	 Catch:{ all -> 0x0043 }
            com.kongregate.android.internal.util.C0562j.m753a(r0);	 Catch:{ all -> 0x0043 }
            r0 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r0.m284i();	 Catch:{ all -> 0x0043 }
            monitor-exit(r2);	 Catch:{ all -> 0x0043 }
            goto L_0x0010;
        L_0x0093:
            r3 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r3 = r3.m282g();	 Catch:{ all -> 0x0043 }
            if (r3 != 0) goto L_0x00af;
        L_0x009b:
            r4 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r4 = r4.f372k;	 Catch:{ all -> 0x0043 }
            r0 = r0 - r4;
            r4 = com.kongregate.android.internal.browser.C0462b.f365d;	 Catch:{ all -> 0x0043 }
            r4 = r4.get();	 Catch:{ all -> 0x0043 }
            r4 = (long) r4;	 Catch:{ all -> 0x0043 }
            r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r0 < 0) goto L_0x00d3;
        L_0x00af:
            r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0043 }
            r0.<init>();	 Catch:{ all -> 0x0043 }
            r1 = "Reloading WebView, needsReload=";
            r0 = r0.append(r1);	 Catch:{ all -> 0x0043 }
            r0 = r0.append(r3);	 Catch:{ all -> 0x0043 }
            r0 = r0.toString();	 Catch:{ all -> 0x0043 }
            com.kongregate.android.internal.util.C0562j.m753a(r0);	 Catch:{ all -> 0x0043 }
            r0 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r1 = 0;
            r0.f373l = r1;	 Catch:{ all -> 0x0043 }
            r0 = r6.f361a;	 Catch:{ all -> 0x0043 }
            r0.m284i();	 Catch:{ all -> 0x0043 }
        L_0x00d0:
            monitor-exit(r2);	 Catch:{ all -> 0x0043 }
            goto L_0x0010;
        L_0x00d3:
            r0 = "Ignoring cache sync request, too soon";
            com.kongregate.android.internal.util.C0562j.m753a(r0);	 Catch:{ all -> 0x0043 }
            goto L_0x00d0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kongregate.android.internal.browser.b.a.run():void");
        }
    }

    /* renamed from: a */
    public static synchronized C0462b m267a(Context context, String str, boolean z, boolean z2, Set<String> set) {
        C0462b a;
        synchronized (C0462b.class) {
            if (C0626d.m967a()) {
                if (f363b.get() == null) {
                    f363b.set(new C0462b(context, str, z, z2, set));
                }
                a = C0462b.m266a();
            } else {
                throw new IllegalStateException("WebViewManager must be initialized from the main thread");
            }
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized C0462b m266a() {
        C0462b c0462b;
        synchronized (C0462b.class) {
            c0462b = (C0462b) f363b.get();
        }
        return c0462b;
    }

    private C0462b(Context context, String str, boolean z, boolean z2, Set<String> set) {
        this.f369h = z;
        this.f370i = z2;
        this.f371j = set;
        this.f368g = str;
        this.f367f = context.getApplicationContext();
        m275b(true);
        m290d();
    }

    /* renamed from: a */
    public synchronized MobileApiWebView m285a(boolean z) {
        if (C0626d.m967a()) {
            if (!(this.f369h || z) || this.f366e == null) {
                m275b(z);
            }
        } else {
            throw new IllegalStateException("WebViewManager.getWebView must be called from the main thread");
        }
        return this.f366e;
    }

    /* renamed from: b */
    private synchronized void m275b(boolean z) {
        m288b();
        boolean z2 = z && !this.f369h;
        this.f366e = new MobileApiWebView(new MutableContextWrapper(this.f367f), this, this.f368g, this.f369h, z2, this.f370i, this.f371j);
        if (z2) {
            C0462b.m269a(this.f366e);
        }
    }

    /* renamed from: b */
    public synchronized void m288b() {
        if (this.f366e != null) {
            final MobileApiWebView mobileApiWebView = this.f366e;
            this.f366e = null;
            C0626d.m968b(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0462b f356b;

                public void run() {
                    mobileApiWebView.stopLoading();
                    mobileApiWebView.removeAllViews();
                    mobileApiWebView.destroy();
                }
            });
        }
    }

    /* renamed from: a */
    public void m287a(final String str, final JSONObject jSONObject) {
        C0626d.m968b(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C0462b f359c;

            public void run() {
                this.f359c.m274b(str, jSONObject);
            }
        });
    }

    /* renamed from: b */
    private synchronized void m274b(String str, JSONObject jSONObject) {
        if (this.f366e == null) {
            C0562j.m759c("WebViewManager - no webview, not sending api message: " + str);
        } else {
            this.f366e.m252a(str, jSONObject);
        }
    }

    /* renamed from: a */
    public static void m268a(int i) {
        C0562j.m753a("Setting WebView reload delay to " + i + " minutes");
        f365d.set(60000 * i);
    }

    /* renamed from: a */
    public static void m271a(String str) {
        C0562j.m753a("Latest panel fingerprint received: " + str);
        f364c.set(str);
    }

    /* renamed from: a */
    public synchronized void m286a(String str, String str2) {
        C0562j.m753a("Fingerprint info received, current=" + str + ", latest=" + str2);
        f364c.set(str2);
        this.f374m = str;
    }

    /* renamed from: g */
    private synchronized boolean m282g() {
        boolean z;
        String str = (String) f364c.get();
        z = (StringUtils.m580a(this.f374m) || StringUtils.m580a((CharSequence) str)) ? false : !StringUtils.m581a(this.f374m, str);
        return z;
    }

    /* renamed from: c */
    public void m289c() {
        synchronized (this.f375n) {
            ScheduledFuture scheduledFuture = (ScheduledFuture) this.f375n.get();
            if (!(scheduledFuture == null || scheduledFuture.isCancelled())) {
                scheduledFuture.cancel(false);
            }
        }
    }

    /* renamed from: d */
    public void m290d() {
        synchronized (this.f375n) {
            ScheduledFuture scheduledFuture = (ScheduledFuture) this.f375n.get();
            if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                this.f375n.set(m283h());
            }
        }
    }

    /* renamed from: h */
    private ScheduledFuture<?> m283h() {
        return C0626d.m964a(0, 300000, TimeUnit.MILLISECONDS, new C0461a(this));
    }

    /* renamed from: i */
    private synchronized void m284i() {
        new Handler(Looper.getMainLooper()).post(new C04603(this));
    }

    /* renamed from: a */
    private static void m269a(MobileApiWebView mobileApiWebView) {
        mobileApiWebView.getSettings().setJavaScriptEnabled(true);
        mobileApiWebView.getSettings().setDomStorageEnabled(false);
        mobileApiWebView.getSettings().setUserAgentString(MobileApiWebView.m221a((WebView) mobileApiWebView) + " KongregateCacheWarmer/1.0");
    }

    /* renamed from: a */
    public static boolean m272a(Context context) {
        boolean z = true;
        File databasePath = context.getDatabasePath("webview.db");
        File databasePath2 = context.getDatabasePath("webviewCache.db");
        if (databasePath != null) {
            C0558g.m708i(databasePath.getAbsolutePath());
        }
        try {
            CookieSyncManager.createInstance(context);
            if (!C0542a.m605a()) {
                if (C0566n.m784a(WebViewDatabase.class, "mDatabase") && C0566n.m785b(WebViewDatabase.class, "mDatabase") == null) {
                    throw new RuntimeException("Failed to open WebViewDatabase, mDatabase is null");
                } else if (C0566n.m784a(WebViewDatabase.class, "mCacheDatabase") && C0566n.m785b(WebViewDatabase.class, "mCacheDatabase") == null) {
                    throw new RuntimeException("Failed to open WebViewDatabase, mCacheDatabase is null");
                }
            }
        } catch (Throwable e) {
            C0562j.m760c("Error opening CookieSyncManager database, retrying", e);
            if (!C0542a.m605a()) {
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) C0566n.m785b(WebViewDatabase.class, "mDatabase");
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                    C0566n.m782a(WebViewDatabase.class, "mDatabase", null);
                }
                if (databasePath != null) {
                    databasePath.delete();
                }
                sQLiteDatabase = (SQLiteDatabase) C0566n.m785b(WebViewDatabase.class, "mCacheDatabase");
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                    C0566n.m782a(WebViewDatabase.class, "mCacheDatabase", null);
                }
                if (databasePath2 != null) {
                    databasePath2.delete();
                }
                C0566n.m782a(WebViewDatabase.class, "mInstance", null);
                z = false;
            }
            try {
                CookieSyncManager.createInstance(context);
            } catch (RuntimeException e2) {
                C0562j.m762d("Error during CookieSyncManager for the 2nd time", e);
                throw new RuntimeException("Unable to initialize CookieSyncManager", e);
            }
        }
        return z;
    }

    /* renamed from: e */
    public synchronized void m291e() {
        C0562j.m753a("Broadcasting character token");
        Map hashMap = new HashMap();
        hashMap.put("value", C0666a.m1145a().m1193w());
        m287a("character-token", new JSONObject(hashMap));
    }
}
