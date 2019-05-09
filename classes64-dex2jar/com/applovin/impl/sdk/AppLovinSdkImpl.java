// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.os.Build;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinPostbackService;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinMediationService;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinAdService;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.util.Log;
import android.os.Build$VERSION;
import com.applovin.sdk.AppLovinLogger;
import android.app.Activity;
import java.lang.ref.WeakReference;
import com.applovin.sdk.AppLovinSdkSettings;
import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public class AppLovinSdkImpl extends AppLovinSdk
{
    private static Context a;
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private String E;
    private String b;
    private AppLovinSdkSettings c;
    private WeakReference<Activity> d;
    private long e;
    private AppLovinLogger f;
    private fd g;
    private ed h;
    private ad i;
    private aw j;
    private c k;
    private av l;
    private l m;
    private dd n;
    private ah o;
    private eg p;
    private p q;
    private AppLovinAdServiceImpl r;
    private df s;
    private PostbackServiceImpl t;
    private EventServiceImpl u;
    private MediationServiceImpl v;
    private do w;
    private dy x;
    private final Object y;
    private boolean z;
    
    public AppLovinSdkImpl() {
        this.y = new Object();
        this.z = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
    }
    
    public static Context getStaticApplicationContext() {
        return AppLovinSdkImpl.a;
    }
    
    private static boolean k() {
        return !Build$VERSION.RELEASE.startsWith("1.") && !Build$VERSION.RELEASE.startsWith("2.0") && !Build$VERSION.RELEASE.startsWith("2.1");
    }
    
    private void l() {
        try {
            if (this.get(ef.c, 0) < 801) {
                Log.i("AppLovinSdk", "SDK has been updated since last run. Continuing...");
                this.getSettingsManager().c();
                this.getSettingsManager().a();
            }
            else {
                Log.d("AppLovinSdk", "SDK has not been updated since last run. Continuing...");
            }
        }
        catch (Exception ex) {
            this.getLogger().e("AppLovinSdkImpl", "Unable to check for SDK update", ex);
        }
        finally {
            this.put(ef.c, 801);
        }
    }
    
    public static void reinitializeAll() {
        synchronized (AppLovinSdkImpl.sdkInstancesLock) {
            final AppLovinSdkImpl[] sdkInstances = AppLovinSdkImpl.sdkInstances;
            for (int length = sdkInstances.length, i = 0; i < length; ++i) {
                sdkInstances[i].g();
            }
        }
    }
    
    aw a() {
        return this.j;
    }
    
    void a(final boolean a) {
        synchronized (this.y) {
            this.z = false;
            this.A = a;
            // monitorexit(this.y)
            this.getTaskManager().b();
        }
    }
    
    c b() {
        return this.k;
    }
    
    l c() {
        return this.m;
    }
    
    public boolean checkCorrectInitialization(final Context context) {
        try {
            this.getLogger().d("AppLovinSdk", "Checking if SDK is initialized in main activity or application context...");
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.setPackage(context.getPackageName());
            final String stackTraceString = Log.getStackTraceString(new Throwable());
            final List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null) {
                this.getLogger().d("AppLovinSdk", "Found " + queryIntentActivities.size() + " main activities for this application");
                final Iterator<ResolveInfo> iterator = queryIntentActivities.iterator();
                while (iterator.hasNext()) {
                    if (stackTraceString.contains(iterator.next().activityInfo.name)) {
                        return true;
                    }
                }
            }
            if (stackTraceString.contains(this.getApplicationContext().getClass().getName())) {
                this.getLogger().d("AppLovinSdk", "SDK initialized in application context");
                return true;
            }
            this.getLogger().w("AppLovinSdk", "AppLovin SDK was initialized too late in session; SDK should always be initialized within main activity, application context, and/or any relevant entry points");
            this.getLogger().w("AppLovinSdk", "Initialization instead happened from: " + stackTraceString);
            return false;
        }
        catch (Throwable t) {
            this.getLogger().e("AppLovinSdk", "Error checking if SDK is initialized in main activity or application context...", t);
            return false;
        }
    }
    
    public void clear(final SharedPreferences sharedPreferences) {
        this.p.a(sharedPreferences);
    }
    
    dd d() {
        return this.n;
    }
    
    boolean e() {
        synchronized (this.y) {
            return this.z;
        }
    }
    
    void f() {
        synchronized (this.y) {
            if (!this.z && !this.A) {
                this.g();
            }
        }
    }
    
    void g() {
        synchronized (this.y) {
            this.z = true;
            this.getTaskManager().a();
            this.getTaskManager().a(new eu(this), fe.a);
        }
    }
    
    public <T> T get(final ec<T> ec) {
        return this.h.a(ec);
    }
    
    public <T> T get(final ef<T> ef) {
        return this.get(ef, (T)null);
    }
    
    public <T> T get(final ef<T> ef, final T t) {
        return this.p.b(ef, t);
    }
    
    public <T> T get(final ef<T> ef, final T t, final SharedPreferences sharedPreferences) {
        return this.p.b(ef, t, sharedPreferences);
    }
    
    public <T> T get(final String s, final T t, final Class clazz, final SharedPreferences sharedPreferences) {
        return this.p.a(s, t, clazz, sharedPreferences);
    }
    
    @Override
    public AppLovinAdServiceImpl getAdService() {
        return this.r;
    }
    
    @Override
    public Context getApplicationContext() {
        return AppLovinSdkImpl.a;
    }
    
    public List<String> getAsList(final ec ec) {
        return this.h.b(ec);
    }
    
    public ad getConnectionManager() {
        return this.i;
    }
    
    public ah getDataCollector() {
        return this.o;
    }
    
    @Override
    public AppLovinEventService getEventService() {
        return this.u;
    }
    
    public av getFileManager() {
        return this.l;
    }
    
    public Activity getInitializationActivity() {
        if (this.d != null) {
            return this.d.get();
        }
        return null;
    }
    
    public long getInitializedTimeMillis() {
        return this.e;
    }
    
    @Override
    public AppLovinLogger getLogger() {
        return this.f;
    }
    
    @Override
    public String getMediationProvider() {
        return this.E;
    }
    
    @Override
    public MediationServiceImpl getMediationService() {
        return this.v;
    }
    
    @Override
    public df getNativeAdService() {
        return this.s;
    }
    
    public do getPersistentPostbackManager() {
        return this.w;
    }
    
    @Override
    public PostbackServiceImpl getPostbackService() {
        return this.t;
    }
    
    @Override
    public String getSdkKey() {
        return this.b;
    }
    
    public dy getSessionTracker() {
        return this.x;
    }
    
    @Override
    public AppLovinSdkSettings getSettings() {
        return this.c;
    }
    
    public ed getSettingsManager() {
        return this.h;
    }
    
    public fd getTaskManager() {
        return this.g;
    }
    
    @Override
    public String getUserIdentifier() {
        return gc.a();
    }
    
    public p getZoneManager() {
        return this.q;
    }
    
    void h() {
        this.h.c();
        this.h.a();
        this.j.a();
        this.k.b();
        this.g();
    }
    
    @Override
    public boolean hasCriticalErrors() {
        return this.B || this.C;
    }
    
    void i() {
        this.x.a(AppLovinSdkImpl.a);
    }
    
    @Override
    public void initialize(String string, final AppLovinSdkSettings c, final Context context) {
        this.b = string;
        this.c = c;
        this.e = System.currentTimeMillis();
        AppLovinSdkImpl.a = context.getApplicationContext();
        if (context instanceof Activity) {
            this.d = new WeakReference<Activity>((Activity)context);
        }
        try {
            final x f = new x(this);
            this.f = f;
            this.p = new eg(this);
            (this.h = new ed(this)).b();
            this.l();
            this.g = new fd(this);
            this.i = new ad(this);
            this.j = new aw(this);
            this.k = new c(this);
            this.l = new av(this);
            this.o = new ah(this);
            this.q = new p(this);
            this.r = new AppLovinAdServiceImpl(this);
            this.s = new df(this);
            this.t = new PostbackServiceImpl(this);
            this.u = new EventServiceImpl(this);
            this.v = new MediationServiceImpl(this);
            this.w = new do(this);
            this.m = new l(this);
            this.n = new dd(this);
            this.x = new dy(this);
            if (!k()) {
                this.B = true;
                Log.e("AppLovinSdk", "Unable to initialize AppLovin SDK: Android SDK version has to be at least level 8");
            }
            if (!AppLovinSdkUtils.isValidString(string)) {
                this.C = true;
                Log.e("AppLovinSdk", "Unable to find AppLovin SDK key. Please add     meta-data android:name=\"applovin.sdk.key\" android:value=\"YOUR_SDK_KEY_HERE\" into AndroidManifest.xml.");
                final StringWriter stringWriter = new StringWriter();
                new Throwable("").printStackTrace(new PrintWriter(stringWriter));
                string = stringWriter.toString();
                Log.e("AppLovinSdk", "Called with an invalid SDK key from: " + string);
            }
            if (!this.hasCriticalErrors()) {
                if (c instanceof bt) {
                    f.a(((bt)c).a());
                }
                if (c instanceof AppLovinInternalSdkSettings) {
                    this.h.a(ea.l, c.isVerboseLoggingEnabled());
                    this.h.a();
                }
                else if (this.h.a(ea.b)) {
                    c.setTestAdsEnabled(gd.b(context));
                    c.setVerboseLogging(gd.c(context));
                    this.h.a(c);
                    this.h.a();
                }
                this.g();
                return;
            }
        }
        catch (Throwable t) {
            Log.e("AppLovinSdk", "Failed to load AppLovin SDK, ad serving will be disabled", t);
            this.a(false);
            return;
        }
        this.a(false);
    }
    
    @Override
    public void initializeSdk() {
    }
    
    @Override
    public boolean isEnabled() {
        synchronized (this.y) {
            return this.A;
        }
    }
    
    public boolean isFireOS() {
        final Iterator<String> iterator = aa.a(this.get(ea.cg)).iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase(Build.MANUFACTURER)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isInitializedInMainActivity() {
        return this.D;
    }
    
    public boolean isSessionTrackingEnabled() {
        return this.x.c();
    }
    
    boolean j() {
        return this.x.d();
    }
    
    public <T> void put(final ef<T> ef, final T t) {
        this.p.a(ef, t);
    }
    
    public <T> void put(final ef<T> ef, final T t, final SharedPreferences sharedPreferences) {
        this.p.a(ef, t, sharedPreferences);
    }
    
    public <T> void put(final String s, final T t, final SharedPreferences sharedPreferences) {
        this.p.a(s, t, sharedPreferences);
    }
    
    public <T> void remove(final ef<T> ef) {
        this.p.a(ef);
    }
    
    public <T> ec retrieveSetting(final String s, final ec<T> ec) {
        return this.h.a(s, ec);
    }
    
    public void setInitializedInMainActivity(final boolean d) {
        this.D = d;
    }
    
    @Override
    public void setMediationProvider(final String e) {
        this.E = e;
    }
    
    @Override
    public void setPluginVersion(final String s) {
        this.h.a(ea.I, s);
        this.h.a();
    }
    
    @Override
    public void setUserIdentifier(final String s) {
        gc.a(s);
    }
}
