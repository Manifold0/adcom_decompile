// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.Serializable;
import com.tapjoy.TapjoyException;
import com.tapjoy.TapjoyIntegrationException;
import android.os.Build$VERSION;
import com.tapjoy.TJEventOptimizer;
import com.tapjoy.TapjoyAppSettings;
import com.tapjoy.FiveRocksIntegration;
import com.tapjoy.TJConnectListener;
import java.util.Hashtable;
import android.content.Context;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Locale;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJAdUnit;
import com.tapjoy.TJVideoListener;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJEarnedCurrencyListener;
import android.opengl.GLSurfaceView;
import android.app.Activity;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TJCurrency;

class dv extends du
{
    private boolean b;
    private String c;
    private TJCurrency d;
    private TapjoyCache e;
    
    dv() {
        this.b = false;
        this.c = "";
        this.d = null;
        this.e = null;
    }
    
    private boolean i(final String s) {
        if (!this.a) {
            TapjoyLog.w("TapjoyAPI", "Can not call " + s + " because Tapjoy SDK has not successfully connected.");
            return false;
        }
        return true;
    }
    
    private boolean j(final String s) {
        if (!this.b) {
            this.c = "Can not call " + s + " because Tapjoy SDK is not initialized.";
            TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, this.c));
            return false;
        }
        return true;
    }
    
    @Override
    public final TJPlacement a(final String s, final TJPlacementListener tjPlacementListener) {
        return TJPlacementManager.a(s, "", "", tjPlacementListener);
    }
    
    @Override
    public final void a(final float currencyMultiplier) {
        if (this.j("setCurrencyMultiplier")) {
            TapjoyConnectCore.getInstance().setCurrencyMultiplier(currencyMultiplier);
        }
    }
    
    @Override
    public final void a(final int n) {
        final gc a = gc.a();
        if (a.c("setUserLevel")) {
            fz.a("setUserLevel({}) called", n);
            final gf f = a.f;
            Integer value;
            if (n >= 0) {
                value = n;
            }
            else {
                value = null;
            }
            f.a(value);
        }
    }
    
    @Override
    public final void a(final int n, final TJAwardCurrencyListener tjAwardCurrencyListener) {
        if (this.d != null && this.i("awardCurrency")) {
            this.d.awardCurrency(n, tjAwardCurrencyListener);
        }
    }
    
    @Override
    public final void a(final int n, final TJSpendCurrencyListener tjSpendCurrencyListener) {
        if (this.d != null && this.i("spendCurrency")) {
            this.d.spendCurrency(n, tjSpendCurrencyListener);
        }
    }
    
    @Override
    public final void a(final int n, String a) {
        final gc a2 = gc.a();
        if (a2.c("setUserCohortVariable")) {
            boolean b;
            if (n > 0 && n <= 5) {
                b = true;
            }
            else {
                b = false;
            }
            if (fz.a && !b) {
                fz.b("setCohortVariable: variableIndex is out of range");
            }
            if (b) {
                fz.a("setUserCohortVariable({}, {}) called", n, a);
                a = fx.a(a);
                a2.f.a(n, a);
            }
        }
    }
    
    @Override
    public final void a(final Activity activity) {
        if (activity != null) {
            com.tapjoy.internal.d.a(activity);
            return;
        }
        TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Cannot set activity to NULL"));
    }
    
    @Override
    public final void a(final GLSurfaceView glSurfaceView) {
        gc.a();
        gc.a(glSurfaceView);
    }
    
    @Override
    public final void a(final TJEarnedCurrencyListener earnedCurrencyListener) {
        if (this.d != null && this.i("setEarnedCurrencyListener")) {
            this.d.setEarnedCurrencyListener(earnedCurrencyListener);
        }
    }
    
    @Override
    public final void a(final TJGetCurrencyBalanceListener tjGetCurrencyBalanceListener) {
        if (this.d != null && this.i("getCurrencyBalance")) {
            this.d.getCurrencyBalance(tjGetCurrencyBalanceListener);
        }
    }
    
    @Override
    public final void a(final TJVideoListener publisherVideoListener) {
        if (this.j("setVideoListener")) {
            TJAdUnit.publisherVideoListener = publisherVideoListener;
        }
    }
    
    @Override
    public final void a(final String s) {
        fq.a(null, s, null, null, 0L);
    }
    
    @Override
    public final void a(final String s, final long n) {
        fq.a(null, s, null, null, n);
    }
    
    @Override
    public final void a(String a, final TJSetUserIDListener tjSetUserIDListener) {
        if (this.j("setUserID")) {
            TapjoyConnectCore.setUserID(a, tjSetUserIDListener);
            final gc a2 = gc.a();
            if (a2.c("setUserId")) {
                a = fx.a(a);
                a2.f.b(a);
            }
        }
        else if (tjSetUserIDListener != null) {
            tjSetUserIDListener.onSetUserIDFailure(this.c);
        }
    }
    
    @Override
    public final void a(final String s, final String s2) {
        fq.a(s, null, null, s2);
    }
    
    @Override
    public final void a(String a, String s, final double n, String b) {
        final gc a2 = gc.a();
        if (a2.b("trackPurchase")) {
            a = fx.a(a, "trackPurchase", "productId");
            if (a != null) {
                s = fx.a(s, "trackPurchase", "currencyCode");
                if (s != null) {
                    if (s.length() == 3) {
                        s = s.toUpperCase(Locale.US);
                        b = fx.b(b);
                        a2.g.a(a, s, n, null, null, b);
                        fz.a("trackPurchase called");
                        return;
                    }
                    fz.a("trackPurchase", "currencyCode", "invalid currency code");
                }
            }
        }
    }
    
    @Override
    public final void a(final String s, final String s2, final long n) {
        fq.a(s, s2, null, null, n);
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3, final String s4) {
        fq.a(s, s2, s3, s4);
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3, final String s4, final long n) {
        fq.a(s, s2, s3, s4, n);
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3, final String s4, final String s5, final long n) {
        fq.a(s, s2, s3, s4, s5, n, null, 0L, null, 0L);
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3, final String s4, final String s5, final long n, final String s6, final long n2) {
        fq.a(s, s2, s3, s4, s5, n, s6, n2, null, 0L);
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3, final String s4, final String s5, final long n, final String s6, final long n2, final String s7, final long n3) {
        fq.a(s, s2, s3, s4, s5, n, s6, n2, s7, n3);
    }
    
    @Override
    public final void a(final String s, final String s2, final String s3, final String s4, final Map map) {
        final gc a = gc.a();
        if (a.b("trackEvent") && !ct.c(s2)) {
            final LinkedHashMap b = cx.b();
            if (map != null && map.size() > 0) {
                for (final Map.Entry<Object, V> entry : map.entrySet()) {
                    final String key = entry.getKey();
                    if (key == null) {
                        if (fz.a) {
                            ac.a("Tapjoy", "{}: {} must not be null", "trackEvent", "key in values map");
                        }
                        return;
                    }
                    else {
                        if (!(key instanceof String)) {
                            continue;
                        }
                        final String a2 = fx.a(key, "trackEvent", "key in values map");
                        if (a2 == null) {
                            return;
                        }
                        final V value = entry.getValue();
                        if (!(value instanceof Number)) {
                            fz.a("trackEvent", "value in values map", "must be a long");
                            return;
                        }
                        b.put(a2, ((Number)value).longValue());
                    }
                }
            }
            a.g.a(s, s2, s3, s4, b);
            fz.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", s, s2, s3, s4, b);
        }
    }
    
    @Override
    public final void a(final Set set) {
        gc.a().a(set);
    }
    
    @Override
    public final void a(final boolean debugEnabled) {
        TapjoyLog.setDebugEnabled(debugEnabled);
    }
    
    @Override
    public final boolean a(final Context context, final String s) {
        return this.a(context, s, null, null);
    }
    
    @Override
    public boolean a(final Context context, String value, final Hashtable hashtable, final TJConnectListener tjConnectListener) {
        final boolean b = false;
        final boolean b2 = false;
        // monitorenter(this)
        Label_0040: {
            if (hashtable == null) {
                break Label_0040;
            }
            while (true) {
                try {
                    final Object value2 = hashtable.get("TJC_OPTION_ENABLE_LOGGING");
                    if (value2 != null) {
                        TapjoyLog.setDebugEnabled("true".equals(value2.toString()));
                    }
                    TapjoyConnectCore.setSDKType("event");
                    boolean b3 = false;
                    if (context == null) {
                        TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "The application context is NULL"));
                        b3 = b2;
                        if (tjConnectListener != null) {
                            tjConnectListener.onConnectFailure();
                            b3 = b2;
                        }
                    }
                    else {
                        FiveRocksIntegration.a();
                        try {
                            TapjoyAppSettings.init(context);
                            TapjoyConnectCore.requestTapjoyConnect(context, value, hashtable, new TJConnectListener() {
                                @Override
                                public final void onConnectFailure() {
                                    if (tjConnectListener != null) {
                                        tjConnectListener.onConnectFailure();
                                    }
                                }
                                
                                @Override
                                public final void onConnectSuccess() {
                                    dv.this.d = new TJCurrency(context);
                                    dv.this.e = new TapjoyCache(context);
                                    try {
                                        TJEventOptimizer.init(context);
                                        dv.this.a = true;
                                        if (tjConnectListener != null) {
                                            tjConnectListener.onConnectSuccess();
                                        }
                                    }
                                    catch (InterruptedException ex2) {
                                        this.onConnectFailure();
                                    }
                                    catch (RuntimeException ex) {
                                        TapjoyLog.w("TapjoyAPI", ex.getMessage());
                                        this.onConnectFailure();
                                    }
                                }
                            });
                            this.b = true;
                            if (Build$VERSION.SDK_INT < 14) {
                                TapjoyLog.i("TapjoyAPI", "Automatic session tracking is not available on this device.");
                                return true;
                            }
                        }
                        catch (TapjoyIntegrationException ex) {
                            TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, ex.getMessage()));
                            b3 = b2;
                            if (tjConnectListener != null) {
                                tjConnectListener.onConnectFailure();
                                b3 = b2;
                                return b3;
                            }
                            return b3;
                        }
                        catch (TapjoyException ex2) {
                            TapjoyLog.e("TapjoyAPI", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, ex2.getMessage()));
                            b3 = b2;
                            if (tjConnectListener != null) {
                                tjConnectListener.onConnectFailure();
                                b3 = b2;
                                return b3;
                            }
                            return b3;
                        }
                    }
                    return b3;
                }
                finally {}
                int n = b ? 1 : 0;
                if (hashtable != null) {
                    value = String.valueOf(hashtable.get("TJC_OPTION_DISABLE_AUTOMATIC_SESSION_TRACKING"));
                    n = (b ? 1 : 0);
                    if (value != null) {
                        n = (b ? 1 : 0);
                        if (value.equalsIgnoreCase("true")) {
                            n = 1;
                        }
                    }
                }
                if (n == 0) {
                    final Context context2;
                    eo.a(context2);
                }
                else {
                    TapjoyLog.i("TapjoyAPI", "Automatic session tracking is disabled.");
                }
                return true;
            }
        }
    }
    
    @Override
    public final String b() {
        return "11.12.2";
    }
    
    @Override
    public final void b(final int n) {
        final gc a = gc.a();
        if (a.c("setUserFriendCount")) {
            fz.a("setUserFriendCount({}) called", n);
            final gf f = a.f;
            Integer value;
            if (n >= 0) {
                value = n;
            }
            else {
                value = null;
            }
            f.b(value);
        }
    }
    
    @Override
    public final void b(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 14) {
            eo.a();
        }
        gc.a().n = true;
        fq.a(activity);
    }
    
    @Override
    public final void b(String a) {
        final gc a2 = gc.a();
        if (a2.c("setAppDataVersion")) {
            a = fx.a(a);
            a2.f.a(a);
        }
    }
    
    @Override
    public final void b(final String s, final String s2, final String s3, final String s4) {
        fq.a(s, s2, s3, s4, 0L);
    }
    
    @Override
    public final void b(final boolean b) {
        int n = 0;
        final gc a = gc.a();
        if (a.c("setPushNotificationDisabled")) {
            final boolean a2 = a.f.a(b);
            Object[] array;
            Serializable value;
            String s;
            Object[] array2;
            if (a2) {
                array = new Object[] { null };
                value = b;
                s = "setPushNotificationDisabled({}) called";
                array2 = array;
            }
            else {
                s = "setPushNotificationDisabled({}) called, but it is already {}";
                array2 = new Object[] { b, null };
                if (b) {
                    value = "disabled";
                    n = 1;
                    array = array2;
                }
                else {
                    value = "enabled";
                    n = 1;
                    array = array2;
                }
            }
            array[n] = value;
            fz.a(s, array2);
            if (a2 && a.k && !ct.c(a.d)) {
                String b2;
                if (a.o != null) {
                    b2 = null;
                }
                else {
                    final ge b3 = ge.b(a.e);
                    b2 = ct.b(b3.b.b(b3.a));
                }
                a.a(b2);
            }
        }
    }
    
    @Override
    public final float c() {
        if (this.j("getCurrencyMultiplier")) {
            return TapjoyConnectCore.getInstance().getCurrencyMultiplier();
        }
        return 1.0f;
    }
    
    @Override
    public final void c(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 14) {
            eo.a();
        }
        fq.b(activity);
    }
    
    @Override
    public final void c(final String s) {
        if (!ct.c(s)) {
            final gc a = gc.a();
            final Set c = a.c();
            if (c.add(s)) {
                a.a(c);
            }
        }
    }
    
    @Override
    public final void c(final boolean b) {
        final fh a = fh.a();
        a.a = b;
        if (!a.b()) {
            a.c = true;
        }
    }
    
    @Override
    public final void d() {
        if (this.j("startSession")) {
            if (Build$VERSION.SDK_INT >= 14) {
                eo.a();
            }
            TapjoyConnectCore.getInstance().appResume();
            fq.a();
        }
    }
    
    @Override
    public final void d(final String s) {
        if (!ct.c(s)) {
            final gc a = gc.a();
            final Set c = a.c();
            if (c.remove(s)) {
                a.a(c);
            }
        }
    }
    
    @Override
    public final void e() {
        if (this.j("endSession")) {
            if (Build$VERSION.SDK_INT >= 14) {
                eo.a();
            }
            gc.a().n = false;
            TapjoyConnectCore.getInstance().appPause();
            fq.b();
        }
    }
    
    @Override
    public final void e(final String s) {
        if (this.i("actionComplete")) {
            TapjoyConnectCore.getInstance().actionComplete(s);
        }
    }
    
    @Override
    public final Set f() {
        return gc.a().c();
    }
    
    @Override
    public final void f(final String s) {
        final gc a = gc.a();
        fz.a("setGcmSender({}) called", new Object[] { s });
        a.d = ct.a(s);
        a.b();
    }
    
    @Override
    public final String g(final String s) {
        if (this.i("getSupportURL")) {
            return TapjoyConnectCore.getSupportURL(s);
        }
        return null;
    }
    
    @Override
    public final void g() {
        gc.a().a((Set)null);
    }
    
    @Override
    public final void h(final String b) {
        final fh a = fh.a();
        if (!aq.a(b)) {
            a.b = b;
            if (!a.c()) {
                a.c = true;
            }
        }
    }
    
    @Override
    public final boolean h() {
        final gc a = gc.a();
        if (!a.c("isPushNotificationDisabled")) {
            return false;
        }
        final boolean f = a.f.f();
        fz.a("isPushNotificationDisabled = {}", f);
        return f;
    }
    
    @Override
    public final boolean i() {
        return this.a;
    }
}
