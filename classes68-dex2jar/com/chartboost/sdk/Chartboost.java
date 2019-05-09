// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import java.util.HashMap;
import java.util.Map;
import android.app.Application$ActivityLifecycleCallbacks;
import android.annotation.TargetApi;
import android.view.WindowManager$LayoutParams;
import android.view.Window;
import android.os.Build$VERSION;
import android.content.Context;
import android.app.Activity;
import android.os.Handler;
import com.chartboost.sdk.Model.e;
import com.chartboost.sdk.impl.c;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.aq;

public class Chartboost
{
    private Chartboost() {
    }
    
    public static void cacheInterstitial(final String s) {
        aq.a("Chartboost.cacheInterstitial", s);
        final h a = h.a();
        if (a == null || !b.a() || !h.f()) {
            return;
        }
        if (s.a().a(s)) {
            CBLogging.b("Chartboost", "cacheInterstitial location cannot be empty");
            final Handler p = a.p;
            final c g = a.g;
            g.getClass();
            p.post((Runnable)g.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
            return;
        }
        final e e = a.m.get();
        if ((e.y && e.A) || (e.e && e.g)) {
            final com.chartboost.sdk.impl.e f = a.f;
            f.getClass();
            a.a.execute(f.new a(3, s, null, null));
            return;
        }
        final Handler p2 = a.p;
        final c g2 = a.g;
        g2.getClass();
        p2.post((Runnable)g2.new a(4, s, CBError.CBImpressionError.END_POINT_DISABLED));
    }
    
    public static void cacheMoreApps(final String b) {
        final h a = h.a();
        if (a != null && b.a() && h.f()) {
            a.getClass();
            final h.a a2 = a.new a(5);
            a2.b = b;
            a.p.postDelayed((Runnable)a2, com.chartboost.sdk.Libraries.b.c);
        }
    }
    
    public static void cacheRewardedVideo(final String s) {
        aq.a("Chartboost.cacheRewardedVideo", s);
        final h a = h.a();
        if (a == null || !b.a() || !h.f()) {
            return;
        }
        if (s.a().a(s)) {
            CBLogging.b("Chartboost", "cacheRewardedVideo location cannot be empty");
            final Handler p = a.p;
            final c l = a.l;
            l.getClass();
            p.post((Runnable)l.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
            return;
        }
        final e e = a.m.get();
        if ((e.y && e.E) || (e.e && e.j)) {
            final com.chartboost.sdk.impl.e k = a.k;
            k.getClass();
            a.a.execute(k.new a(3, s, null, null));
            return;
        }
        final Handler p2 = a.p;
        final c i = a.l;
        i.getClass();
        p2.post((Runnable)i.new a(4, s, CBError.CBImpressionError.END_POINT_DISABLED));
    }
    
    @Deprecated
    public static void closeImpression() {
    }
    
    private static void forwardTouchEventsAIR(final boolean c) {
        final h a = h.a();
        if (a == null) {
            return;
        }
        final com.chartboost.sdk.c q = a.q;
        q.getClass();
        final com.chartboost.sdk.c.c c2 = q.new c(6);
        c2.c = c;
        h.b(c2);
    }
    
    public static boolean getAutoCacheAds() {
        return i.t;
    }
    
    public static String getCustomId() {
        if (!b.b()) {
            return "";
        }
        return i.a;
    }
    
    public static a getDelegate() {
        return i.c;
    }
    
    public static CBLogging.Level getLoggingLevel() {
        b.b();
        return CBLogging.a;
    }
    
    public static CBPIDataUseConsent getPIDataUseConsent() {
        return i.x;
    }
    
    public static String getSDKVersion() {
        return "7.3.0";
    }
    
    public static boolean hasInterstitial(final String s) {
        aq.a("Chartboost.hasInterstitial", s);
        final h a = h.a();
        return a != null && b.a() && a.f.a(s) != null;
    }
    
    public static boolean hasMoreApps(final String s) {
        return false;
    }
    
    public static boolean hasRewardedVideo(final String s) {
        aq.a("Chartboost.hasRewardedVideo", s);
        final h a = h.a();
        return a != null && b.a() && a.k.a(s) != null;
    }
    
    public static boolean isAnyViewVisible() {
        aq.a("Chartboost.isAnyViewVisible");
        final h a = h.a();
        return a != null && a.q.e();
    }
    
    public static boolean isWebViewEnabled() {
        final h a = h.a();
        return a == null || a.m.get().y;
    }
    
    public static boolean onBackPressed() {
        aq.a("Chartboost.onBackPressed");
        final h a = h.a();
        return a != null && a.q.j();
    }
    
    public static void onCreate(final Activity activity) {
        aq.a("Chartboost.onCreate", activity);
        final h a = h.a();
        if (a != null && !i.s) {
            a.q.b(activity);
        }
    }
    
    public static void onDestroy(final Activity activity) {
        aq.a("Chartboost.onDestroy", activity);
        final h a = h.a();
        if (a != null && !i.s) {
            a.q.j(activity);
        }
    }
    
    public static void onPause(final Activity activity) {
        aq.a("Chartboost.onPause", activity);
        final h a = h.a();
        if (a != null && !i.s) {
            a.q.g(activity);
        }
    }
    
    public static void onResume(final Activity activity) {
        aq.a("Chartboost.onResume", activity);
        final h a = h.a();
        if (a != null && !i.s) {
            a.q.f(activity);
        }
    }
    
    public static void onStart(final Activity activity) {
        aq.a("Chartboost.onStart", activity);
        final h a = h.a();
        if (a != null && !i.s) {
            a.q.d(activity);
        }
    }
    
    public static void onStop(final Activity activity) {
        aq.a("Chartboost.onStop", activity);
        final h a = h.a();
        if (a != null && !i.s) {
            a.q.h(activity);
        }
    }
    
    @Deprecated
    public static void restrictDataCollection(final Context context, final boolean b) {
        CBPIDataUseConsent cbpiDataUseConsent;
        if (b) {
            cbpiDataUseConsent = CBPIDataUseConsent.NO_BEHAVIORAL;
        }
        else {
            cbpiDataUseConsent = CBPIDataUseConsent.UNKNOWN;
        }
        setPIDataUseConsent(context, cbpiDataUseConsent);
    }
    
    @TargetApi(28)
    public static void setActivityAttrs(final Activity activity) {
        if (activity == null || !i.g) {
            if ((activity.getWindow().getAttributes().flags & 0x400) != 0x0) {
                CBLogging.d("Chartboost", "Attempting to show Status and Navigation bars on a fullscreen activity. Please change your Chartboost activity theme to: \"@android:style/Theme.Translucent\"` in your Manifest file");
            }
            return;
        }
        final Window window = activity.getWindow();
        int systemUiVisibility = 1798;
        if (Build$VERSION.SDK_INT >= 19) {
            systemUiVisibility = 5894;
        }
        if (Build$VERSION.SDK_INT >= 28) {
            final WindowManager$LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }
    
    @TargetApi(14)
    public static void setActivityCallbacks(final boolean b) {
        aq.a("Chartboost.setActivityCallbacks", b);
        final h a = h.a();
        if (a != null) {
            final Activity a2 = a.q.a();
            if (a2 != null) {
                final Application$ActivityLifecycleCallbacks h = a.q.h;
                if (h != null) {
                    if (!i.s && b) {
                        a2.getApplication().registerActivityLifecycleCallbacks(h);
                        i.s = true;
                        return;
                    }
                    if (i.s && !b) {
                        a2.getApplication().unregisterActivityLifecycleCallbacks(h);
                        i.s = false;
                    }
                }
            }
        }
    }
    
    public static void setAutoCacheAds(final boolean c) {
        aq.a("Chartboost.setAutoCacheAds", c);
        final h a = h.a();
        if (a != null) {
            a.getClass();
            final h.a a2 = a.new a(1);
            a2.c = c;
            h.b(a2);
        }
    }
    
    public static void setChartboostWrapperVersion(final String d) {
        aq.a("Chartboost.setChartboostWrapperVersion", d);
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(5);
        f.d = d;
        h.b(f);
    }
    
    public static void setCustomId(final String e) {
        aq.a("Chartboost.setCustomId", e);
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(6);
        f.e = e;
        h.b(f);
    }
    
    public static void setDelegate(final ChartboostDelegate g) {
        aq.a("Chartboost.setDelegate", g);
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(8);
        f.g = g;
        h.b(f);
    }
    
    public static void setFramework(final CBFramework b, final String d) {
        aq.a("Chartboost.setFramework");
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(4);
        f.b = b;
        f.d = d;
        h.b(f);
    }
    
    @Deprecated
    public static void setFrameworkVersion(final String d) {
        aq.a("Chartboost.setFrameworkVersion", d);
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(5);
        f.d = d;
        h.b(f);
    }
    
    public static void setLoggingLevel(final CBLogging.Level f) {
        aq.a("Chartboost.setLoggingLevel", f.toString());
        final com.chartboost.sdk.f f2 = new com.chartboost.sdk.f(7);
        f2.f = f;
        h.b(f2);
    }
    
    public static void setMediation(final CBMediation c, final String d) {
        aq.a("Chartboost.setMediation");
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(3);
        f.c = c;
        f.d = d;
        h.b(f);
    }
    
    public static void setPIDataUseConsent(final Context context, final CBPIDataUseConsent cbpiDataUseConsent) {
        h.a(context, cbpiDataUseConsent);
    }
    
    public static void setShouldDisplayLoadingViewForMoreApps(final boolean b) {
    }
    
    public static void setShouldHideSystemUI(final Boolean b) {
        aq.a("Chartboost.setHideSystemUI", b);
        i.g = b;
    }
    
    public static void setShouldPrefetchVideoContent(final boolean d) {
        aq.a("Chartboost.setShouldPrefetchVideoContent", d);
        final h a = h.a();
        if (a == null || !b.a()) {
            return;
        }
        a.getClass();
        final h.a a2 = a.new a(2);
        a2.d = d;
        h.b(a2);
    }
    
    public static void setShouldRequestInterstitialsInFirstSession(final boolean a) {
        aq.a("Chartboost.setShouldRequestInterstitialsInFirstSession", a);
        if (b.b()) {
            final com.chartboost.sdk.f f = new com.chartboost.sdk.f(1);
            f.a = a;
            h.b(f);
        }
    }
    
    public static void showInterstitial(final String s) {
        aq.a("Chartboost.showInterstitial", s);
        final h a = h.a();
        if (a == null || !b.a() || !h.f()) {
            return;
        }
        if (s.a().a(s)) {
            CBLogging.b("Chartboost", "showInterstitial location cannot be empty");
            final Handler p = a.p;
            final c g = a.g;
            g.getClass();
            p.post((Runnable)g.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
            return;
        }
        final e e = a.m.get();
        if ((e.y && e.A) || (e.e && e.g)) {
            final com.chartboost.sdk.impl.e f = a.f;
            f.getClass();
            a.a.execute(f.new a(4, s, null, null));
            return;
        }
        final Handler p2 = a.p;
        final c g2 = a.g;
        g2.getClass();
        p2.post((Runnable)g2.new a(4, s, CBError.CBImpressionError.END_POINT_DISABLED));
    }
    
    private static void showInterstitialAIR(final String s, final boolean b) {
        final h a = h.a();
        if (a == null || !b.a() || !h.f()) {
            return;
        }
        final e e = a.m.get();
        if ((e.y && e.A) || (e.e && e.g)) {
            final Handler p2 = a.p;
            final c g = a.g;
            g.getClass();
            p2.post((Runnable)g.new a(4, s, CBError.CBImpressionError.INTERNAL));
            return;
        }
        i.c.didFailToLoadInterstitial(s, CBError.CBImpressionError.END_POINT_DISABLED);
    }
    
    public static void showMoreApps(final String s) {
        cacheMoreApps(s);
    }
    
    private static void showMoreAppsAIR(final String s, final boolean b) {
        cacheMoreApps(s);
    }
    
    public static void showRewardedVideo(final String s) {
        aq.a("Chartboost.showRewardedVideo", s);
        final h a = h.a();
        if (a == null || !b.a() || !h.f()) {
            return;
        }
        if (s.a().a(s)) {
            CBLogging.b("Chartboost", "showRewardedVideo location cannot be empty");
            final Handler p = a.p;
            final c l = a.l;
            l.getClass();
            p.post((Runnable)l.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
            return;
        }
        final e e = a.m.get();
        if ((e.y && e.E) || (e.e && e.j)) {
            final com.chartboost.sdk.impl.e k = a.k;
            k.getClass();
            a.a.execute(k.new a(4, s, null, null));
            return;
        }
        final Handler p2 = a.p;
        final c i = a.l;
        i.getClass();
        p2.post((Runnable)i.new a(4, s, CBError.CBImpressionError.END_POINT_DISABLED));
    }
    
    private static void showRewardedVideoAIR(final String s, final boolean b) {
        final h a = h.a();
        if (a == null || !b.a() || !h.f()) {
            return;
        }
        final e e = a.m.get();
        if ((e.y && e.E) || (e.e && e.j)) {
            final Handler p2 = a.p;
            final c g = a.g;
            g.getClass();
            p2.post((Runnable)g.new a(4, s, CBError.CBImpressionError.INTERNAL));
            return;
        }
        i.c.didFailToLoadRewardedVideo(s, CBError.CBImpressionError.END_POINT_DISABLED);
    }
    
    public static void startWithAppId(final Activity h, final String i, final String j) {
        aq.a("Chartboost.startWithAppId", h);
        final com.chartboost.sdk.f f = new com.chartboost.sdk.f(0);
        f.h = h;
        f.i = i;
        f.j = j;
        h.b(f);
    }
    
    public enum CBFramework
    {
        CBFrameworkAir("AIR"), 
        CBFrameworkCocoonJS("CocoonJS"), 
        CBFrameworkCocos2dx("Cocos2dx"), 
        CBFrameworkCordova("Cordova"), 
        CBFrameworkCorona("Corona"), 
        CBFrameworkGameSalad("GameSalad"), 
        CBFrameworkOther("Other"), 
        CBFrameworkPrime31Unreal("Prime31Unreal"), 
        CBFrameworkUnity("Unity"), 
        CBFrameworkWeeby("Weeby");
        
        private final String a;
        
        private CBFramework(final String a) {
            this.a = a;
        }
        
        public boolean doesWrapperUseCustomBackgroundingBehavior() {
            return this == CBFramework.CBFrameworkAir;
        }
        
        public boolean doesWrapperUseCustomShouldDisplayBehavior() {
            return this == CBFramework.CBFrameworkAir || this == CBFramework.CBFrameworkCocos2dx;
        }
        
        @Override
        public String toString() {
            return this.a;
        }
    }
    
    public enum CBMediation
    {
        CBMediationAdMarvel("AdMarvel"), 
        CBMediationAdMob("AdMob"), 
        CBMediationAerServ("AerServ"), 
        CBMediationFuse("Fuse"), 
        CBMediationFyber("Fyber"), 
        CBMediationHeyZap("HeyZap"), 
        CBMediationHyprMX("HyprMX"), 
        CBMediationMoPub("MoPub"), 
        CBMediationOther("Other"), 
        CBMediationironSource("ironSource");
        
        private final String a;
        
        private CBMediation(final String a) {
            this.a = a;
        }
        
        @Override
        public String toString() {
            return this.a;
        }
    }
    
    public enum CBPIDataUseConsent
    {
        NO_BEHAVIORAL(0), 
        UNKNOWN(-1), 
        YES_BEHAVIORAL(1);
        
        private static Map<Integer, CBPIDataUseConsent> b;
        private int a;
        
        static {
            int i = 0;
            CBPIDataUseConsent.b = new HashMap<Integer, CBPIDataUseConsent>();
            for (CBPIDataUseConsent[] values = values(); i < values.length; ++i) {
                final CBPIDataUseConsent cbpiDataUseConsent = values[i];
                CBPIDataUseConsent.b.put(cbpiDataUseConsent.a, cbpiDataUseConsent);
            }
        }
        
        private CBPIDataUseConsent(final int a) {
            this.a = a;
        }
        
        public static CBPIDataUseConsent valueOf(final int n) {
            CBPIDataUseConsent unknown;
            if ((unknown = CBPIDataUseConsent.b.get(n)) == null) {
                unknown = CBPIDataUseConsent.UNKNOWN;
            }
            return unknown;
        }
        
        public int getValue() {
            return this.a;
        }
    }
}
