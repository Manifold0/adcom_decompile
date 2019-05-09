package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.List;

public class AppLovinSdkImpl extends AppLovinSdk {
    /* renamed from: a */
    private static Context f1946a;
    /* renamed from: A */
    private boolean f1947A = false;
    /* renamed from: B */
    private boolean f1948B = false;
    /* renamed from: C */
    private boolean f1949C = false;
    /* renamed from: D */
    private boolean f1950D = false;
    /* renamed from: E */
    private String f1951E;
    /* renamed from: b */
    private String f1952b;
    /* renamed from: c */
    private AppLovinSdkSettings f1953c;
    /* renamed from: d */
    private WeakReference<Activity> f1954d;
    /* renamed from: e */
    private long f1955e;
    /* renamed from: f */
    private AppLovinLogger f1956f;
    /* renamed from: g */
    private fd f1957g;
    /* renamed from: h */
    private ed f1958h;
    /* renamed from: i */
    private ad f1959i;
    /* renamed from: j */
    private aw f1960j;
    /* renamed from: k */
    private C1275c f1961k;
    /* renamed from: l */
    private av f1962l;
    /* renamed from: m */
    private C1285l f1963m;
    /* renamed from: n */
    private dd f1964n;
    /* renamed from: o */
    private ah f1965o;
    /* renamed from: p */
    private eg f1966p;
    /* renamed from: q */
    private C1289p f1967q;
    /* renamed from: r */
    private AppLovinAdServiceImpl f1968r;
    /* renamed from: s */
    private df f1969s;
    /* renamed from: t */
    private PostbackServiceImpl f1970t;
    /* renamed from: u */
    private EventServiceImpl f1971u;
    /* renamed from: v */
    private MediationServiceImpl f1972v;
    /* renamed from: w */
    private C1277do f1973w;
    /* renamed from: x */
    private dy f1974x;
    /* renamed from: y */
    private final Object f1975y = new Object();
    /* renamed from: z */
    private boolean f1976z = false;

    public static Context getStaticApplicationContext() {
        return f1946a;
    }

    /* renamed from: k */
    private static boolean m2137k() {
        return (VERSION.RELEASE.startsWith("1.") || VERSION.RELEASE.startsWith("2.0") || VERSION.RELEASE.startsWith("2.1")) ? false : true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: l */
    private void m2138l() {
        /*
        r5 = this;
        r4 = 801; // 0x321 float:1.122E-42 double:3.957E-321;
        r0 = com.applovin.impl.sdk.ef.f2442c;	 Catch:{ Exception -> 0x003c }
        r1 = 0;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x003c }
        r0 = r5.get(r0, r1);	 Catch:{ Exception -> 0x003c }
        r0 = (java.lang.Integer) r0;	 Catch:{ Exception -> 0x003c }
        r0 = r0.intValue();	 Catch:{ Exception -> 0x003c }
        if (r0 >= r4) goto L_0x0034;
    L_0x0015:
        r0 = "AppLovinSdk";
        r1 = "SDK has been updated since last run. Continuing...";
        android.util.Log.i(r0, r1);	 Catch:{ Exception -> 0x003c }
        r0 = r5.getSettingsManager();	 Catch:{ Exception -> 0x003c }
        r0.m2673c();	 Catch:{ Exception -> 0x003c }
        r0 = r5.getSettingsManager();	 Catch:{ Exception -> 0x003c }
        r0.m2667a();	 Catch:{ Exception -> 0x003c }
    L_0x002a:
        r0 = com.applovin.impl.sdk.ef.f2442c;
        r1 = java.lang.Integer.valueOf(r4);
        r5.put(r0, r1);
    L_0x0033:
        return;
    L_0x0034:
        r0 = "AppLovinSdk";
        r1 = "SDK has not been updated since last run. Continuing...";
        android.util.Log.d(r0, r1);	 Catch:{ Exception -> 0x003c }
        goto L_0x002a;
    L_0x003c:
        r0 = move-exception;
        r1 = r5.getLogger();	 Catch:{ all -> 0x0052 }
        r2 = "AppLovinSdkImpl";
        r3 = "Unable to check for SDK update";
        r1.mo4174e(r2, r3, r0);	 Catch:{ all -> 0x0052 }
        r0 = com.applovin.impl.sdk.ef.f2442c;
        r1 = java.lang.Integer.valueOf(r4);
        r5.put(r0, r1);
        goto L_0x0033;
    L_0x0052:
        r0 = move-exception;
        r1 = com.applovin.impl.sdk.ef.f2442c;
        r2 = java.lang.Integer.valueOf(r4);
        r5.put(r1, r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.AppLovinSdkImpl.l():void");
    }

    public static void reinitializeAll() {
        synchronized (sdkInstancesLock) {
            for (AppLovinSdkImpl g : sdkInstances) {
                g.m2146g();
            }
        }
    }

    /* renamed from: a */
    aw m2139a() {
        return this.f1960j;
    }

    /* renamed from: a */
    void m2140a(boolean z) {
        synchronized (this.f1975y) {
            this.f1976z = false;
            this.f1947A = z;
        }
        getTaskManager().m2857b();
    }

    /* renamed from: b */
    C1275c m2141b() {
        return this.f1961k;
    }

    /* renamed from: c */
    C1285l m2142c() {
        return this.f1963m;
    }

    public boolean checkCorrectInitialization(Context context) {
        try {
            getLogger().mo4172d(AppLovinLogger.SDK_TAG, "Checking if SDK is initialized in main activity or application context...");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setPackage(context.getPackageName());
            String stackTraceString = Log.getStackTraceString(new Throwable());
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null) {
                getLogger().mo4172d(AppLovinLogger.SDK_TAG, "Found " + queryIntentActivities.size() + " main activities for this application");
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    if (stackTraceString.contains(resolveInfo.activityInfo.name)) {
                        return true;
                    }
                }
            }
            if (stackTraceString.contains(getApplicationContext().getClass().getName())) {
                getLogger().mo4172d(AppLovinLogger.SDK_TAG, "SDK initialized in application context");
                return true;
            }
            getLogger().mo4178w(AppLovinLogger.SDK_TAG, "AppLovin SDK was initialized too late in session; SDK should always be initialized within main activity, application context, and/or any relevant entry points");
            getLogger().mo4178w(AppLovinLogger.SDK_TAG, "Initialization instead happened from: " + stackTraceString);
            return false;
        } catch (Throwable th) {
            getLogger().mo4174e(AppLovinLogger.SDK_TAG, "Error checking if SDK is initialized in main activity or application context...", th);
        }
    }

    public void clear(SharedPreferences sharedPreferences) {
        this.f1966p.m2735a(sharedPreferences);
    }

    /* renamed from: d */
    dd m2143d() {
        return this.f1964n;
    }

    /* renamed from: e */
    boolean m2144e() {
        boolean z;
        synchronized (this.f1975y) {
            z = this.f1976z;
        }
        return z;
    }

    /* renamed from: f */
    void m2145f() {
        synchronized (this.f1975y) {
            if (!(this.f1976z || this.f1947A)) {
                m2146g();
            }
        }
    }

    /* renamed from: g */
    void m2146g() {
        synchronized (this.f1975y) {
            this.f1976z = true;
            getTaskManager().m2853a();
            getTaskManager().m2855a(new eu(this), fe.MAIN);
        }
    }

    public <T> T get(ec<T> ecVar) {
        return this.f1958h.m2666a((ec) ecVar);
    }

    public <T> T get(ef<T> efVar) {
        return get(efVar, null);
    }

    public <T> T get(ef<T> efVar, T t) {
        return this.f1966p.m2740b(efVar, t);
    }

    public <T> T get(ef<T> efVar, T t, SharedPreferences sharedPreferences) {
        return this.f1966p.m2741b((ef) efVar, (Object) t, sharedPreferences);
    }

    public <T> T get(String str, T t, Class cls, SharedPreferences sharedPreferences) {
        return this.f1966p.m2734a(str, (Object) t, cls, sharedPreferences);
    }

    public AppLovinAdServiceImpl getAdService() {
        return this.f1968r;
    }

    public Context getApplicationContext() {
        return f1946a;
    }

    public List<String> getAsList(ec ecVar) {
        return this.f1958h.m2671b(ecVar);
    }

    public ad getConnectionManager() {
        return this.f1959i;
    }

    public ah getDataCollector() {
        return this.f1965o;
    }

    public AppLovinEventService getEventService() {
        return this.f1971u;
    }

    public av getFileManager() {
        return this.f1962l;
    }

    public Activity getInitializationActivity() {
        return this.f1954d != null ? (Activity) this.f1954d.get() : null;
    }

    public long getInitializedTimeMillis() {
        return this.f1955e;
    }

    public AppLovinLogger getLogger() {
        return this.f1956f;
    }

    public String getMediationProvider() {
        return this.f1951E;
    }

    public MediationServiceImpl getMediationService() {
        return this.f1972v;
    }

    public df getNativeAdService() {
        return this.f1969s;
    }

    public C1277do getPersistentPostbackManager() {
        return this.f1973w;
    }

    public PostbackServiceImpl getPostbackService() {
        return this.f1970t;
    }

    public String getSdkKey() {
        return this.f1952b;
    }

    public dy getSessionTracker() {
        return this.f1974x;
    }

    public AppLovinSdkSettings getSettings() {
        return this.f1953c;
    }

    public ed getSettingsManager() {
        return this.f1958h;
    }

    public fd getTaskManager() {
        return this.f1957g;
    }

    public String getUserIdentifier() {
        return gc.m2926a();
    }

    public C1289p getZoneManager() {
        return this.f1967q;
    }

    /* renamed from: h */
    void m2147h() {
        this.f1958h.m2673c();
        this.f1958h.m2667a();
        this.f1960j.m2312a();
        this.f1961k.m2421b();
        m2146g();
    }

    public boolean hasCriticalErrors() {
        return this.f1948B || this.f1949C;
    }

    /* renamed from: i */
    void m2148i() {
        this.f1974x.m2648a(f1946a);
    }

    public void initialize(String str, AppLovinSdkSettings appLovinSdkSettings, Context context) {
        this.f1952b = str;
        this.f1953c = appLovinSdkSettings;
        this.f1955e = System.currentTimeMillis();
        f1946a = context.getApplicationContext();
        if (context instanceof Activity) {
            this.f1954d = new WeakReference((Activity) context);
        }
        try {
            Object c1296x = new C1296x(this);
            this.f1956f = c1296x;
            this.f1966p = new eg(this);
            this.f1958h = new ed(this);
            this.f1958h.m2672b();
            m2138l();
            this.f1957g = new fd(this);
            this.f1959i = new ad(this);
            this.f1960j = new aw(this);
            this.f1961k = new C1275c(this);
            this.f1962l = new av(this);
            this.f1965o = new ah(this);
            this.f1967q = new C1289p(this);
            this.f1968r = new AppLovinAdServiceImpl(this);
            this.f1969s = new df(this);
            this.f1970t = new PostbackServiceImpl(this);
            this.f1971u = new EventServiceImpl(this);
            this.f1972v = new MediationServiceImpl(this);
            this.f1973w = new C1277do(this);
            this.f1963m = new C1285l(this);
            this.f1964n = new dd(this);
            this.f1974x = new dy(this);
            if (!m2137k()) {
                this.f1948B = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to initialize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (!AppLovinSdkUtils.isValidString(str)) {
                this.f1949C = true;
                Log.e(AppLovinLogger.SDK_TAG, "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                Writer stringWriter = new StringWriter();
                new Throwable("").printStackTrace(new PrintWriter(stringWriter));
                Log.e(AppLovinLogger.SDK_TAG, "Called with an invalid SDK key from: " + stringWriter.toString());
            }
            if (hasCriticalErrors()) {
                m2140a(false);
                return;
            }
            if (appLovinSdkSettings instanceof bt) {
                c1296x.m3081a(((bt) appLovinSdkSettings).m2382a());
            }
            if (appLovinSdkSettings instanceof AppLovinInternalSdkSettings) {
                this.f1958h.m2668a(ea.f2415l, Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled()));
                this.f1958h.m2667a();
            } else if (((Boolean) this.f1958h.m2666a(ea.f2404b)).booleanValue()) {
                appLovinSdkSettings.setTestAdsEnabled(gd.m2951b(context));
                appLovinSdkSettings.setVerboseLogging(gd.m2954c(context));
                this.f1958h.m2669a(appLovinSdkSettings);
                this.f1958h.m2667a();
            }
            m2146g();
        } catch (Throwable th) {
            Log.e(AppLovinLogger.SDK_TAG, "Failed to load AppLovin SDK, ad serving will be disabled", th);
            m2140a(false);
        }
    }

    public void initializeSdk() {
    }

    public boolean isEnabled() {
        boolean z;
        synchronized (this.f1975y) {
            z = this.f1947A;
        }
        return z;
    }

    public boolean isFireOS() {
        for (String equalsIgnoreCase : aa.m2193a((String) get(ea.cg))) {
            if (equalsIgnoreCase.equalsIgnoreCase(Build.MANUFACTURER)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInitializedInMainActivity() {
        return this.f1950D;
    }

    public boolean isSessionTrackingEnabled() {
        return this.f1974x.m2650c();
    }

    /* renamed from: j */
    boolean m2149j() {
        return this.f1974x.m2651d();
    }

    public <T> void put(ef<T> efVar, T t) {
        this.f1966p.m2737a((ef) efVar, (Object) t);
    }

    public <T> void put(ef<T> efVar, T t, SharedPreferences sharedPreferences) {
        this.f1966p.m2738a((ef) efVar, (Object) t, sharedPreferences);
    }

    public <T> void put(String str, T t, SharedPreferences sharedPreferences) {
        this.f1966p.m2739a(str, (Object) t, sharedPreferences);
    }

    public <T> void remove(ef<T> efVar) {
        this.f1966p.m2736a((ef) efVar);
    }

    public <T> ec retrieveSetting(String str, ec<T> ecVar) {
        return this.f1958h.m2665a(str, (ec) ecVar);
    }

    public void setInitializedInMainActivity(boolean z) {
        this.f1950D = z;
    }

    public void setMediationProvider(String str) {
        this.f1951E = str;
    }

    public void setPluginVersion(String str) {
        this.f1958h.m2668a(ea.f2385I, (Object) str);
        this.f1958h.m2667a();
    }

    public void setUserIdentifier(String str) {
        gc.m2927a(str);
    }
}
