// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.util.Map;
import android.opengl.GLSurfaceView;
import android.app.Activity;
import java.util.Set;
import java.util.Hashtable;
import android.content.Context;
import com.tapjoy.internal.du;

public final class Tapjoy
{
    public static final String INTENT_EXTRA_PUSH_PAYLOAD = "com.tapjoy.PUSH_PAYLOAD";
    
    public static void actionComplete(final String s) {
        du.a().e(s);
    }
    
    public static void addUserTag(final String s) {
        du.a().c(s);
    }
    
    public static void awardCurrency(final int n, final TJAwardCurrencyListener tjAwardCurrencyListener) {
        du.a().a(n, tjAwardCurrencyListener);
    }
    
    public static void clearUserTags() {
        du.a().g();
    }
    
    public static boolean connect(final Context context, final String s) {
        return du.a().a(context, s);
    }
    
    public static boolean connect(final Context context, final String s, final Hashtable hashtable) {
        return du.a().a(context, s, hashtable, null);
    }
    
    public static boolean connect(final Context context, final String s, final Hashtable hashtable, final TJConnectListener tjConnectListener) {
        synchronized (Tapjoy.class) {
            return du.a().a(context, s, hashtable, tjConnectListener);
        }
    }
    
    public static void endSession() {
        du.a().e();
    }
    
    public static void getCurrencyBalance(final TJGetCurrencyBalanceListener tjGetCurrencyBalanceListener) {
        du.a().a(tjGetCurrencyBalanceListener);
    }
    
    @Deprecated
    public static float getCurrencyMultiplier() {
        return du.a().c();
    }
    
    public static TJPlacement getPlacement(final String s, final TJPlacementListener tjPlacementListener) {
        return du.a().a(s, tjPlacementListener);
    }
    
    public static String getSupportURL() {
        return du.a().g(null);
    }
    
    public static String getSupportURL(final String s) {
        return du.a().g(s);
    }
    
    public static Set getUserTags() {
        return du.a().f();
    }
    
    public static String getVersion() {
        return du.a().b();
    }
    
    public static boolean isConnected() {
        return du.a().i();
    }
    
    public static boolean isPushNotificationDisabled() {
        return du.a().h();
    }
    
    public static void loadSharedLibrary() {
        try {
            System.loadLibrary("tapjoy");
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {}
    }
    
    public static void onActivityStart(final Activity activity) {
        du.a().b(activity);
    }
    
    public static void onActivityStop(final Activity activity) {
        du.a().c(activity);
    }
    
    public static void removeUserTag(final String s) {
        du.a().d(s);
    }
    
    public static void setActivity(final Activity activity) {
        du.a().a(activity);
    }
    
    public static void setAppDataVersion(final String s) {
        du.a().b(s);
    }
    
    @Deprecated
    public static void setCurrencyMultiplier(final float n) {
        du.a().a(n);
    }
    
    public static void setDebugEnabled(final boolean b) {
        du.a().a(b);
    }
    
    public static void setEarnedCurrencyListener(final TJEarnedCurrencyListener tjEarnedCurrencyListener) {
        du.a().a(tjEarnedCurrencyListener);
    }
    
    public static void setGLSurfaceView(final GLSurfaceView glSurfaceView) {
        du.a().a(glSurfaceView);
    }
    
    public static void setGcmSender(final String s) {
        du.a().f(s);
    }
    
    public static void setPushNotificationDisabled(final boolean b) {
        du.a().b(b);
    }
    
    public static void setUserCohortVariable(final int n, final String s) {
        du.a().a(n, s);
    }
    
    public static void setUserConsent(final String s) {
        du.a().h(s);
    }
    
    public static void setUserFriendCount(final int n) {
        du.a().b(n);
    }
    
    public static void setUserID(final String s) {
        setUserID(s, null);
    }
    
    public static void setUserID(final String s, final TJSetUserIDListener tjSetUserIDListener) {
        du.a().a(s, tjSetUserIDListener);
    }
    
    public static void setUserLevel(final int n) {
        du.a().a(n);
    }
    
    public static void setUserTags(final Set set) {
        du.a().a(set);
    }
    
    public static void setVideoListener(final TJVideoListener tjVideoListener) {
        du.a().a(tjVideoListener);
    }
    
    public static void spendCurrency(final int n, final TJSpendCurrencyListener tjSpendCurrencyListener) {
        du.a().a(n, tjSpendCurrencyListener);
    }
    
    public static void startSession() {
        du.a().d();
    }
    
    public static void subjectToGDPR(final boolean b) {
        du.a().c(b);
    }
    
    public static void trackEvent(final String s) {
        du.a().a(s);
    }
    
    public static void trackEvent(final String s, final long n) {
        du.a().a(s, n);
    }
    
    public static void trackEvent(final String s, final String s2, final long n) {
        du.a().a(s, s2, n);
    }
    
    public static void trackEvent(final String s, final String s2, final String s3, final String s4) {
        du.a().b(s, s2, s3, s4);
    }
    
    public static void trackEvent(final String s, final String s2, final String s3, final String s4, final long n) {
        du.a().a(s, s2, s3, s4, n);
    }
    
    public static void trackEvent(final String s, final String s2, final String s3, final String s4, final String s5, final long n) {
        du.a().a(s, s2, s3, s4, s5, n);
    }
    
    public static void trackEvent(final String s, final String s2, final String s3, final String s4, final String s5, final long n, final String s6, final long n2) {
        du.a().a(s, s2, s3, s4, s5, n, s6, n2);
    }
    
    public static void trackEvent(final String s, final String s2, final String s3, final String s4, final String s5, final long n, final String s6, final long n2, final String s7, final long n3) {
        du.a().a(s, s2, s3, s4, s5, n, s6, n2, s7, n3);
    }
    
    public static void trackEvent(final String s, final String s2, final String s3, final String s4, final Map map) {
        du.a().a(s, s2, s3, s4, map);
    }
    
    @Deprecated
    public static void trackPurchase(final String s, final String s2) {
        du.a().a(s, s2);
    }
    
    public static void trackPurchase(final String s, final String s2, final double n, final String s3) {
        du.a().a(s, s2, n, s3);
    }
    
    public static void trackPurchase(final String s, final String s2, final String s3, final String s4) {
        du.a().a(s, s2, s3, s4);
    }
}
