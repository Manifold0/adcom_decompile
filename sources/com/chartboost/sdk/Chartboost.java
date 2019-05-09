package com.chartboost.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.chartboost.sdk.C1397c.C1396c;
import com.chartboost.sdk.C1409h.C1408a;
import com.chartboost.sdk.Libraries.C1372b;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBLogging.Level;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.C1434c;
import com.chartboost.sdk.impl.C1434c.C1433a;
import com.chartboost.sdk.impl.C1440e;
import com.chartboost.sdk.impl.C1440e.C1439a;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.aq;
import java.util.HashMap;
import java.util.Map;

public class Chartboost {

    public enum CBFramework {
        CBFrameworkUnity("Unity"),
        CBFrameworkCorona("Corona"),
        CBFrameworkAir("AIR"),
        CBFrameworkGameSalad("GameSalad"),
        CBFrameworkCordova("Cordova"),
        CBFrameworkCocoonJS("CocoonJS"),
        CBFrameworkCocos2dx("Cocos2dx"),
        CBFrameworkPrime31Unreal("Prime31Unreal"),
        CBFrameworkWeeby("Weeby"),
        CBFrameworkOther("Other");
        
        /* renamed from: a */
        private final String f2661a;

        private CBFramework(String s) {
            this.f2661a = s;
        }

        public String toString() {
            return this.f2661a;
        }

        public boolean doesWrapperUseCustomShouldDisplayBehavior() {
            return this == CBFrameworkAir || this == CBFrameworkCocos2dx;
        }

        public boolean doesWrapperUseCustomBackgroundingBehavior() {
            return this == CBFrameworkAir;
        }
    }

    public enum CBMediation {
        CBMediationAdMarvel("AdMarvel"),
        CBMediationAdMob("AdMob"),
        CBMediationFuse("Fuse"),
        CBMediationFyber("Fyber"),
        CBMediationHeyZap("HeyZap"),
        CBMediationMoPub("MoPub"),
        CBMediationironSource("ironSource"),
        CBMediationHyprMX("HyprMX"),
        CBMediationAerServ("AerServ"),
        CBMediationOther("Other");
        
        /* renamed from: a */
        private final String f2663a;

        private CBMediation(String s) {
            this.f2663a = s;
        }

        public String toString() {
            return this.f2663a;
        }
    }

    public enum CBPIDataUseConsent {
        UNKNOWN(-1),
        NO_BEHAVIORAL(0),
        YES_BEHAVIORAL(1);
        
        /* renamed from: b */
        private static Map<Integer, CBPIDataUseConsent> f2664b;
        /* renamed from: a */
        private int f2666a;

        static {
            f2664b = new HashMap();
            CBPIDataUseConsent[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CBPIDataUseConsent cBPIDataUseConsent = values[i];
                f2664b.put(Integer.valueOf(cBPIDataUseConsent.f2666a), cBPIDataUseConsent);
                i++;
            }
        }

        private CBPIDataUseConsent(int value) {
            this.f2666a = value;
        }

        public static CBPIDataUseConsent valueOf(int pageType) {
            CBPIDataUseConsent cBPIDataUseConsent = (CBPIDataUseConsent) f2664b.get(Integer.valueOf(pageType));
            return cBPIDataUseConsent == null ? UNKNOWN : cBPIDataUseConsent;
        }

        public int getValue() {
            return this.f2666a;
        }
    }

    private Chartboost() {
    }

    public static void startWithAppId(Activity activity, String appId, String appSignature) {
        aq.m3408a("Chartboost.startWithAppId", (Object) activity);
        Runnable c1404f = new C1404f(0);
        c1404f.f2889h = activity;
        c1404f.f2890i = appId;
        c1404f.f2891j = appSignature;
        C1409h.m3328b(c1404f);
    }

    public static void setPIDataUseConsent(Context context, CBPIDataUseConsent consent) {
        C1409h.m3326a(context, consent);
    }

    public static CBPIDataUseConsent getPIDataUseConsent() {
        return C1410i.f2947x;
    }

    @Deprecated
    public static void restrictDataCollection(Context context, boolean restrict) {
        setPIDataUseConsent(context, restrict ? CBPIDataUseConsent.NO_BEHAVIORAL : CBPIDataUseConsent.UNKNOWN);
    }

    public static void onCreate(Activity activity) {
        aq.m3408a("Chartboost.onCreate", (Object) activity);
        C1409h a = C1409h.m3324a();
        if (a != null && !C1410i.f2942s) {
            a.f2918q.m3256b(activity);
        }
    }

    public static void onStart(Activity activity) {
        aq.m3408a("Chartboost.onStart", (Object) activity);
        C1409h a = C1409h.m3324a();
        if (a != null && !C1410i.f2942s) {
            a.f2918q.m3263d(activity);
        }
    }

    public static void onResume(Activity activity) {
        aq.m3408a("Chartboost.onResume", (Object) activity);
        C1409h a = C1409h.m3324a();
        if (a != null && !C1410i.f2942s) {
            a.f2918q.m3267f(activity);
        }
    }

    public static void onPause(Activity activity) {
        aq.m3408a("Chartboost.onPause", (Object) activity);
        C1409h a = C1409h.m3324a();
        if (a != null && !C1410i.f2942s) {
            a.f2918q.m3268g(activity);
        }
    }

    public static void onStop(Activity activity) {
        aq.m3408a("Chartboost.onStop", (Object) activity);
        C1409h a = C1409h.m3324a();
        if (a != null && !C1410i.f2942s) {
            a.f2918q.m3271h(activity);
        }
    }

    public static boolean onBackPressed() {
        aq.m3407a("Chartboost.onBackPressed");
        C1409h a = C1409h.m3324a();
        if (a == null) {
            return false;
        }
        return a.f2918q.m3275j();
    }

    public static void onDestroy(Activity activity) {
        aq.m3408a("Chartboost.onDestroy", (Object) activity);
        C1409h a = C1409h.m3324a();
        if (a != null && !C1410i.f2942s) {
            a.f2918q.m3274j(activity);
        }
    }

    public static boolean hasRewardedVideo(String location) {
        aq.m3409a("Chartboost.hasRewardedVideo", location);
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a() || a.f2912k.m3574a(location) == null) {
            return false;
        }
        return true;
    }

    public static void cacheRewardedVideo(String location) {
        aq.m3409a("Chartboost.cacheRewardedVideo", location);
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a() || !C1409h.m3329f()) {
            return;
        }
        if (C1454s.m3627a().m3631a((CharSequence) location)) {
            CBLogging.m3099b("Chartboost", "cacheRewardedVideo location cannot be empty");
            Handler handler = a.f2917p;
            C1434c c1434c = a.f2913l;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
            return;
        }
        C1390e c1390e = (C1390e) a.f2914m.get();
        if ((c1390e.f2817y && c1390e.f2785E) || (c1390e.f2797e && c1390e.f2802j)) {
            C1440e c1440e = a.f2912k;
            c1440e.getClass();
            a.f2902a.execute(new C1439a(c1440e, 3, location, null, null));
            return;
        }
        handler = a.f2917p;
        c1434c = a.f2913l;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 4, location, CBImpressionError.END_POINT_DISABLED));
    }

    public static void showRewardedVideo(String location) {
        aq.m3409a("Chartboost.showRewardedVideo", location);
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a() || !C1409h.m3329f()) {
            return;
        }
        if (C1454s.m3627a().m3631a((CharSequence) location)) {
            CBLogging.m3099b("Chartboost", "showRewardedVideo location cannot be empty");
            Handler handler = a.f2917p;
            C1434c c1434c = a.f2913l;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
            return;
        }
        C1390e c1390e = (C1390e) a.f2914m.get();
        if ((c1390e.f2817y && c1390e.f2785E) || (c1390e.f2797e && c1390e.f2802j)) {
            C1440e c1440e = a.f2912k;
            c1440e.getClass();
            a.f2902a.execute(new C1439a(c1440e, 4, location, null, null));
            return;
        }
        handler = a.f2917p;
        c1434c = a.f2913l;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 4, location, CBImpressionError.END_POINT_DISABLED));
    }

    public static boolean hasInterstitial(String location) {
        aq.m3409a("Chartboost.hasInterstitial", location);
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a() || a.f2907f.m3574a(location) == null) {
            return false;
        }
        return true;
    }

    public static void cacheInterstitial(String location) {
        aq.m3409a("Chartboost.cacheInterstitial", location);
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a() || !C1409h.m3329f()) {
            return;
        }
        if (C1454s.m3627a().m3631a((CharSequence) location)) {
            CBLogging.m3099b("Chartboost", "cacheInterstitial location cannot be empty");
            Handler handler = a.f2917p;
            C1434c c1434c = a.f2908g;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
            return;
        }
        C1390e c1390e = (C1390e) a.f2914m.get();
        if ((c1390e.f2817y && c1390e.f2781A) || (c1390e.f2797e && c1390e.f2799g)) {
            C1440e c1440e = a.f2907f;
            c1440e.getClass();
            a.f2902a.execute(new C1439a(c1440e, 3, location, null, null));
            return;
        }
        handler = a.f2917p;
        c1434c = a.f2908g;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 4, location, CBImpressionError.END_POINT_DISABLED));
    }

    public static void showInterstitial(String location) {
        aq.m3409a("Chartboost.showInterstitial", location);
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a() || !C1409h.m3329f()) {
            return;
        }
        if (C1454s.m3627a().m3631a((CharSequence) location)) {
            CBLogging.m3099b("Chartboost", "showInterstitial location cannot be empty");
            Handler handler = a.f2917p;
            C1434c c1434c = a.f2908g;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
            return;
        }
        C1390e c1390e = (C1390e) a.f2914m.get();
        if ((c1390e.f2817y && c1390e.f2781A) || (c1390e.f2797e && c1390e.f2799g)) {
            C1440e c1440e = a.f2907f;
            c1440e.getClass();
            a.f2902a.execute(new C1439a(c1440e, 4, location, null, null));
            return;
        }
        handler = a.f2917p;
        c1434c = a.f2908g;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 4, location, CBImpressionError.END_POINT_DISABLED));
    }

    @Deprecated
    public static void closeImpression() {
    }

    public static boolean hasMoreApps(String location) {
        return false;
    }

    public static void cacheMoreApps(String location) {
        C1409h a = C1409h.m3324a();
        if (a != null && C1392b.m3234a() && C1409h.m3329f()) {
            a.getClass();
            Runnable c1408a = new C1408a(a, 5);
            c1408a.f2897b = location;
            a.f2917p.postDelayed(c1408a, C1372b.f2680c);
        }
    }

    public static void showMoreApps(String location) {
        cacheMoreApps(location);
    }

    public static boolean isAnyViewVisible() {
        aq.m3407a("Chartboost.isAnyViewVisible");
        C1409h a = C1409h.m3324a();
        return a != null && a.f2918q.m3265e();
    }

    public static void setMediation(CBMediation mediation, String libraryVersion) {
        aq.m3407a("Chartboost.setMediation");
        Runnable c1404f = new C1404f(3);
        c1404f.f2884c = mediation;
        c1404f.f2885d = libraryVersion;
        C1409h.m3328b(c1404f);
    }

    public static void setFramework(CBFramework framework, String version) {
        aq.m3407a("Chartboost.setFramework");
        Runnable c1404f = new C1404f(4);
        c1404f.f2883b = framework;
        c1404f.f2885d = version;
        C1409h.m3328b(c1404f);
    }

    @Deprecated
    public static void setFrameworkVersion(String version) {
        aq.m3409a("Chartboost.setFrameworkVersion", version);
        Runnable c1404f = new C1404f(5);
        c1404f.f2885d = version;
        C1409h.m3328b(c1404f);
    }

    public static void setChartboostWrapperVersion(String version) {
        aq.m3409a("Chartboost.setChartboostWrapperVersion", version);
        Runnable c1404f = new C1404f(5);
        c1404f.f2885d = version;
        C1409h.m3328b(c1404f);
    }

    public static String getCustomId() {
        if (C1392b.m3238b()) {
            return C1410i.f2924a;
        }
        return "";
    }

    public static void setCustomId(String customID) {
        aq.m3409a("Chartboost.setCustomId", customID);
        Runnable c1404f = new C1404f(6);
        c1404f.f2886e = customID;
        C1409h.m3328b(c1404f);
    }

    public static void setLoggingLevel(Level lvl) {
        aq.m3409a("Chartboost.setLoggingLevel", lvl.toString());
        Runnable c1404f = new C1404f(7);
        c1404f.f2887f = lvl;
        C1409h.m3328b(c1404f);
    }

    public static Level getLoggingLevel() {
        C1392b.m3238b();
        return CBLogging.f2675a;
    }

    public static C1370a getDelegate() {
        return C1410i.f2926c;
    }

    public static void setDelegate(ChartboostDelegate delegate) {
        aq.m3408a("Chartboost.setDelegate", (Object) delegate);
        Runnable c1404f = new C1404f(8);
        c1404f.f2888g = delegate;
        C1409h.m3328b(c1404f);
    }

    public static boolean getAutoCacheAds() {
        return C1410i.f2943t;
    }

    public static void setAutoCacheAds(boolean autoCacheAds) {
        aq.m3410a("Chartboost.setAutoCacheAds", autoCacheAds);
        C1409h a = C1409h.m3324a();
        if (a != null) {
            a.getClass();
            Runnable c1408a = new C1408a(a, 1);
            c1408a.f2898c = autoCacheAds;
            C1409h.m3328b(c1408a);
        }
    }

    public static void setShouldRequestInterstitialsInFirstSession(boolean shouldRequest) {
        aq.m3410a("Chartboost.setShouldRequestInterstitialsInFirstSession", shouldRequest);
        if (C1392b.m3238b()) {
            Runnable c1404f = new C1404f(1);
            c1404f.f2882a = shouldRequest;
            C1409h.m3328b(c1404f);
        }
    }

    public static void setShouldDisplayLoadingViewForMoreApps(boolean shouldDisplay) {
    }

    public static void setShouldPrefetchVideoContent(boolean shouldPrefetch) {
        aq.m3410a("Chartboost.setShouldPrefetchVideoContent", shouldPrefetch);
        C1409h a = C1409h.m3324a();
        if (a != null && C1392b.m3234a()) {
            a.getClass();
            Runnable c1408a = new C1408a(a, 2);
            c1408a.f2899d = shouldPrefetch;
            C1409h.m3328b(c1408a);
        }
    }

    public static String getSDKVersion() {
        return "7.3.0";
    }

    public static void setShouldHideSystemUI(Boolean hide) {
        aq.m3408a("Chartboost.setHideSystemUI", (Object) hide);
        C1410i.f2930g = hide.booleanValue();
    }

    public static boolean isWebViewEnabled() {
        C1409h a = C1409h.m3324a();
        return a == null || ((C1390e) a.f2914m.get()).f2817y;
    }

    @TargetApi(14)
    public static void setActivityCallbacks(boolean enabled) {
        aq.m3410a("Chartboost.setActivityCallbacks", enabled);
        C1409h a = C1409h.m3324a();
        if (a != null) {
            Activity a2 = a.f2918q.m3247a();
            if (a2 != null) {
                ActivityLifecycleCallbacks activityLifecycleCallbacks = a.f2918q.f2845h;
                if (activityLifecycleCallbacks == null) {
                    return;
                }
                if (!C1410i.f2942s && enabled) {
                    a2.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
                    C1410i.f2942s = true;
                } else if (C1410i.f2942s && !enabled) {
                    a2.getApplication().unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
                    C1410i.f2942s = false;
                }
            }
        }
    }

    private static void showInterstitialAIR(String location, boolean show) {
        C1409h a = C1409h.m3324a();
        if (a != null && C1392b.m3234a() && C1409h.m3329f()) {
            C1390e c1390e = (C1390e) a.f2914m.get();
            if ((c1390e.f2817y && c1390e.f2781A) || (c1390e.f2797e && c1390e.f2799g)) {
                Handler handler = a.f2917p;
                C1434c c1434c = a.f2908g;
                c1434c.getClass();
                handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INTERNAL));
                return;
            }
            C1410i.f2926c.didFailToLoadInterstitial(location, CBImpressionError.END_POINT_DISABLED);
        }
    }

    private static void showMoreAppsAIR(String location, boolean show) {
        cacheMoreApps(location);
    }

    private static void showRewardedVideoAIR(String location, boolean show) {
        C1409h a = C1409h.m3324a();
        if (a != null && C1392b.m3234a() && C1409h.m3329f()) {
            C1390e c1390e = (C1390e) a.f2914m.get();
            if ((c1390e.f2817y && c1390e.f2785E) || (c1390e.f2797e && c1390e.f2802j)) {
                Handler handler = a.f2917p;
                C1434c c1434c = a.f2908g;
                c1434c.getClass();
                handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INTERNAL));
                return;
            }
            C1410i.f2926c.didFailToLoadRewardedVideo(location, CBImpressionError.END_POINT_DISABLED);
        }
    }

    private static void forwardTouchEventsAIR(boolean forward) {
        C1409h a = C1409h.m3324a();
        if (a != null) {
            C1397c c1397c = a.f2918q;
            c1397c.getClass();
            Runnable c1396c = new C1396c(c1397c, 6);
            c1396c.f2835c = forward;
            C1409h.m3328b(c1396c);
        }
    }

    @TargetApi(28)
    public static void setActivityAttrs(Activity activity) {
        if (activity != null && C1410i.f2930g) {
            Window window = activity.getWindow();
            int i = 1798;
            if (VERSION.SDK_INT >= 19) {
                i = 5894;
            }
            if (VERSION.SDK_INT >= 28) {
                LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
            }
            window.getDecorView().setSystemUiVisibility(i);
        } else if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
            CBLogging.m3103d("Chartboost", "Attempting to show Status and Navigation bars on a fullscreen activity. Please change your Chartboost activity theme to: \"@android:style/Theme.Translucent\"` in your Manifest file");
        }
    }
}
