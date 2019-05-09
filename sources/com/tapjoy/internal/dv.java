package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build.VERSION;
import com.tapjoy.FiveRocksIntegration;
import com.tapjoy.TJAdUnit;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TJAwardCurrencyListener;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJCurrency;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJEventOptimizer;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TJSetUserIDListener;
import com.tapjoy.TJSpendCurrencyListener;
import com.tapjoy.TJVideoListener;
import com.tapjoy.TapjoyAppSettings;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyException;
import com.tapjoy.TapjoyIntegrationException;
import com.tapjoy.TapjoyLog;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class dv extends du {
    /* renamed from: b */
    private boolean f7387b = false;
    /* renamed from: c */
    private String f7388c = "";
    /* renamed from: d */
    private TJCurrency f7389d = null;
    /* renamed from: e */
    private TapjoyCache f7390e = null;

    dv() {
    }

    /* renamed from: b */
    public final String mo6245b() {
        return TapjoyConstants.TJC_LIBRARY_VERSION_NUMBER;
    }

    /* renamed from: a */
    public final void mo6242a(boolean z) {
        TapjoyLog.setDebugEnabled(z);
    }

    /* renamed from: a */
    public final boolean mo6243a(Context context, String str) {
        return mo6244a(context, str, null, null);
    }

    /* renamed from: a */
    public synchronized boolean mo6244a(final Context context, String str, Hashtable hashtable, final TJConnectListener tJConnectListener) {
        boolean z = false;
        synchronized (this) {
            if (hashtable != null) {
                Object obj = hashtable.get(TapjoyConnectFlag.ENABLE_LOGGING);
                if (obj != null) {
                    TapjoyLog.setDebugEnabled("true".equals(obj.toString()));
                }
            }
            TapjoyConnectCore.setSDKType("event");
            if (context == null) {
                TapjoyLog.m7127e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "The application context is NULL"));
                if (tJConnectListener != null) {
                    tJConnectListener.onConnectFailure();
                }
            } else {
                FiveRocksIntegration.m6984a();
                try {
                    TapjoyAppSettings.init(context);
                    TapjoyConnectCore.requestTapjoyConnect(context, str, hashtable, new TJConnectListener(this) {
                        /* renamed from: c */
                        final /* synthetic */ dv f7386c;

                        public final void onConnectSuccess() {
                            this.f7386c.f7389d = new TJCurrency(context);
                            this.f7386c.f7390e = new TapjoyCache(context);
                            try {
                                TJEventOptimizer.init(context);
                                this.f7386c.a = true;
                                if (tJConnectListener != null) {
                                    tJConnectListener.onConnectSuccess();
                                }
                            } catch (InterruptedException e) {
                                onConnectFailure();
                            } catch (RuntimeException e2) {
                                TapjoyLog.m7131w("TapjoyAPI", e2.getMessage());
                                onConnectFailure();
                            }
                        }

                        public final void onConnectFailure() {
                            if (tJConnectListener != null) {
                                tJConnectListener.onConnectFailure();
                            }
                        }
                    });
                    this.f7387b = true;
                    if (VERSION.SDK_INT < 14) {
                        TapjoyLog.m7129i("TapjoyAPI", "Automatic session tracking is not available on this device.");
                    } else {
                        if (hashtable != null) {
                            String valueOf = String.valueOf(hashtable.get(TapjoyConnectFlag.DISABLE_AUTOMATIC_SESSION_TRACKING));
                            if (valueOf != null && valueOf.equalsIgnoreCase("true")) {
                                z = true;
                            }
                        }
                        if (z) {
                            TapjoyLog.m7129i("TapjoyAPI", "Automatic session tracking is disabled.");
                        } else {
                            eo.m7658a(context);
                        }
                    }
                    z = true;
                } catch (TapjoyIntegrationException e) {
                    TapjoyLog.m7127e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, e.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                } catch (TapjoyException e2) {
                    TapjoyLog.m7127e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.SDK_ERROR, e2.getMessage()));
                    if (tJConnectListener != null) {
                        tJConnectListener.onConnectFailure();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public final TJPlacement mo6218a(String str, TJPlacementListener tJPlacementListener) {
        return TJPlacementManager.m7081a(str, "", "", tJPlacementListener);
    }

    /* renamed from: a */
    public final void mo6224a(Activity activity) {
        if (activity != null) {
            C2854d.m7348a(activity);
        } else {
            TapjoyLog.m7127e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Cannot set activity to NULL"));
        }
    }

    /* renamed from: a */
    public final void mo6219a(float f) {
        if (m7531j("setCurrencyMultiplier")) {
            TapjoyConnectCore.getInstance().setCurrencyMultiplier(f);
        }
    }

    /* renamed from: c */
    public final float mo6251c() {
        if (m7531j("getCurrencyMultiplier")) {
            return TapjoyConnectCore.getInstance().getCurrencyMultiplier();
        }
        return 1.0f;
    }

    /* renamed from: e */
    public final void mo6258e(String str) {
        if (m7530i("actionComplete")) {
            TapjoyConnectCore.getInstance().actionComplete(str);
        }
    }

    /* renamed from: a */
    public final void mo6227a(TJGetCurrencyBalanceListener tJGetCurrencyBalanceListener) {
        if (this.f7389d != null && m7530i("getCurrencyBalance")) {
            this.f7389d.getCurrencyBalance(tJGetCurrencyBalanceListener);
        }
    }

    /* renamed from: a */
    public final void mo6222a(int i, TJSpendCurrencyListener tJSpendCurrencyListener) {
        if (this.f7389d != null && m7530i("spendCurrency")) {
            this.f7389d.spendCurrency(i, tJSpendCurrencyListener);
        }
    }

    /* renamed from: a */
    public final void mo6221a(int i, TJAwardCurrencyListener tJAwardCurrencyListener) {
        if (this.f7389d != null && m7530i("awardCurrency")) {
            this.f7389d.awardCurrency(i, tJAwardCurrencyListener);
        }
    }

    /* renamed from: a */
    public final void mo6226a(TJEarnedCurrencyListener tJEarnedCurrencyListener) {
        if (this.f7389d != null && m7530i("setEarnedCurrencyListener")) {
            this.f7389d.setEarnedCurrencyListener(tJEarnedCurrencyListener);
        }
    }

    /* renamed from: a */
    public final void mo6228a(TJVideoListener tJVideoListener) {
        if (m7531j("setVideoListener")) {
            TJAdUnit.publisherVideoListener = tJVideoListener;
        }
    }

    /* renamed from: a */
    public final void mo6233a(String str, String str2, double d, String str3) {
        gc a = gc.m7831a();
        if (a.m7847b("trackPurchase")) {
            String a2 = fx.m7794a(str, "trackPurchase", "productId");
            if (a2 != null) {
                String a3 = fx.m7794a(str2, "trackPurchase", "currencyCode");
                if (a3 == null) {
                    return;
                }
                if (a3.length() != 3) {
                    fz.m7812a("trackPurchase", "currencyCode", "invalid currency code");
                    return;
                }
                a.f7855g.m7825a(a2, a3.toUpperCase(Locale.US), d, null, null, fx.m7795b(str3));
                fz.m7811a("trackPurchase called");
            }
        }
    }

    /* renamed from: a */
    public final void mo6235a(String str, String str2, String str3, String str4) {
        fq.m7776a(str, str2, str3, str4);
    }

    /* renamed from: a */
    public final void mo6232a(String str, String str2) {
        fq.m7776a(str, null, null, str2);
    }

    /* renamed from: a */
    public final void mo6229a(String str) {
        fq.m7777a(null, str, null, null, 0);
    }

    /* renamed from: a */
    public final void mo6230a(String str, long j) {
        fq.m7777a(null, str, null, null, j);
    }

    /* renamed from: a */
    public final void mo6234a(String str, String str2, long j) {
        fq.m7777a(str, str2, null, null, j);
    }

    /* renamed from: b */
    public final void mo6249b(String str, String str2, String str3, String str4) {
        fq.m7777a(str, str2, str3, str4, 0);
    }

    /* renamed from: a */
    public final void mo6236a(String str, String str2, String str3, String str4, long j) {
        fq.m7777a(str, str2, str3, str4, j);
    }

    /* renamed from: a */
    public final void mo6237a(String str, String str2, String str3, String str4, String str5, long j) {
        fq.m7778a(str, str2, str3, str4, str5, j, null, 0, null, 0);
    }

    /* renamed from: a */
    public final void mo6238a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2) {
        fq.m7778a(str, str2, str3, str4, str5, j, str6, j2, null, 0);
    }

    /* renamed from: a */
    public final void mo6239a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        fq.m7778a(str, str2, str3, str4, str5, j, str6, j2, str7, j3);
    }

    /* renamed from: d */
    public final void mo6255d() {
        if (m7531j("startSession")) {
            if (VERSION.SDK_INT >= 14) {
                eo.m7657a();
            }
            TapjoyConnectCore.getInstance().appResume();
            fq.m7774a();
        }
    }

    /* renamed from: e */
    public final void mo6257e() {
        if (m7531j("endSession")) {
            if (VERSION.SDK_INT >= 14) {
                eo.m7657a();
            }
            gc.m7831a().f7862n = false;
            TapjoyConnectCore.getInstance().appPause();
            fq.m7779b();
        }
    }

    /* renamed from: b */
    public final void mo6247b(Activity activity) {
        if (VERSION.SDK_INT >= 14) {
            eo.m7657a();
        }
        gc.m7831a().f7862n = true;
        fq.m7775a(activity);
    }

    /* renamed from: c */
    public final void mo6252c(Activity activity) {
        if (VERSION.SDK_INT >= 14) {
            eo.m7657a();
        }
        fq.m7780b(activity);
    }

    /* renamed from: a */
    public final void mo6231a(String str, TJSetUserIDListener tJSetUserIDListener) {
        if (m7531j("setUserID")) {
            TapjoyConnectCore.setUserID(str, tJSetUserIDListener);
            gc a = gc.m7831a();
            if (a.m7849c("setUserId")) {
                a.f7854f.m7934b(fx.m7793a(str));
            }
        } else if (tJSetUserIDListener != null) {
            tJSetUserIDListener.onSetUserIDFailure(this.f7388c);
        }
    }

    /* renamed from: a */
    public final void mo6223a(int i, String str) {
        gc a = gc.m7831a();
        if (a.m7849c("setUserCohortVariable")) {
            int i2;
            if (i <= 0 || i > 5) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            String str2 = "setCohortVariable: variableIndex is out of range";
            if (fz.f7823a && i2 == 0) {
                fz.m7816b(str2);
            }
            if (i2 != 0) {
                fz.m7813a("setUserCohortVariable({}, {}) called", Integer.valueOf(i), str);
                a.f7854f.m7927a(i, fx.m7793a(str));
            }
        }
    }

    /* renamed from: f */
    public final Set mo6259f() {
        return gc.m7831a().m7848c();
    }

    /* renamed from: a */
    public final void mo6241a(Set set) {
        gc.m7831a().m7844a(set);
    }

    /* renamed from: g */
    public final void mo6262g() {
        gc.m7831a().m7844a(null);
    }

    /* renamed from: c */
    public final void mo6253c(String str) {
        if (!ct.m7339c(str)) {
            gc a = gc.m7831a();
            Set c = a.m7848c();
            if (c.add(str)) {
                a.m7844a(c);
            }
        }
    }

    /* renamed from: d */
    public final void mo6256d(String str) {
        if (!ct.m7339c(str)) {
            gc a = gc.m7831a();
            Set c = a.m7848c();
            if (c.remove(str)) {
                a.m7844a(c);
            }
        }
    }

    /* renamed from: h */
    public final boolean mo6264h() {
        gc a = gc.m7831a();
        if (!a.m7849c("isPushNotificationDisabled")) {
            return false;
        }
        fz.m7813a("isPushNotificationDisabled = {}", Boolean.valueOf(a.f7854f.m7939f()));
        return a.f7854f.m7939f();
    }

    /* renamed from: b */
    public final void mo6250b(boolean z) {
        int i = 0;
        gc a = gc.m7831a();
        if (a.m7849c("setPushNotificationDisabled")) {
            Object[] objArr;
            Boolean valueOf;
            String str;
            Object[] objArr2;
            boolean a2 = a.f7854f.m7931a(z);
            if (a2) {
                objArr = new Object[1];
                valueOf = Boolean.valueOf(z);
                str = "setPushNotificationDisabled({}) called";
                objArr2 = objArr;
            } else {
                str = "setPushNotificationDisabled({}) called, but it is already {}";
                objArr2 = new Object[2];
                objArr2[0] = Boolean.valueOf(z);
                if (z) {
                    valueOf = "disabled";
                    i = 1;
                    objArr = objArr2;
                } else {
                    valueOf = String.ENABLED;
                    i = 1;
                    objArr = objArr2;
                }
            }
            objArr[i] = valueOf;
            fz.m7813a(str, objArr2);
            if (a2 && a.f7859k && !ct.m7339c(a.f7852d)) {
                String str2;
                if (a.f7863o != null) {
                    str2 = null;
                } else {
                    C2948r b = ge.m7914b(a.f7853e);
                    str2 = ct.m7338b(b.f7888b.mo6301b(b.f7887a));
                }
                a.m7840a(str2);
            }
        }
    }

    /* renamed from: i */
    public final boolean mo6265i() {
        return this.a;
    }

    /* renamed from: g */
    public final String mo6261g(String str) {
        if (m7530i("getSupportURL")) {
            return TapjoyConnectCore.getSupportURL(str);
        }
        return null;
    }

    /* renamed from: i */
    private boolean m7530i(String str) {
        if (this.a) {
            return true;
        }
        TapjoyLog.m7131w("TapjoyAPI", "Can not call " + str + " because Tapjoy SDK has not successfully connected.");
        return false;
    }

    /* renamed from: j */
    private boolean m7531j(String str) {
        if (this.f7387b) {
            return true;
        }
        this.f7388c = "Can not call " + str + " because Tapjoy SDK is not initialized.";
        TapjoyLog.m7127e("TapjoyAPI", new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, this.f7388c));
        return false;
    }

    /* renamed from: c */
    public final void mo6254c(boolean z) {
        fh a = fh.m7730a();
        a.f7758a = Boolean.valueOf(z);
        if (!a.m7732b()) {
            a.f7760c = true;
        }
    }

    /* renamed from: h */
    public final void mo6263h(String str) {
        fh a = fh.m7730a();
        if (!aq.m7169a(str)) {
            a.f7759b = str;
            if (!a.m7733c()) {
                a.f7760c = true;
            }
        }
    }

    /* renamed from: a */
    public final void mo6240a(String str, String str2, String str3, String str4, Map map) {
        gc a = gc.m7831a();
        if (a.m7847b("trackEvent") && !ct.m7339c(str2)) {
            Map b = cx.m7345b();
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    Object key = entry.getKey();
                    String str5;
                    if (key == null) {
                        String str6 = "trackEvent";
                        str5 = "key in values map";
                        if (fz.f7823a) {
                            ac.m7145a("Tapjoy", "{}: {} must not be null", str6, str5);
                            return;
                        }
                        return;
                    } else if (key instanceof String) {
                        str5 = fx.m7794a((String) key, "trackEvent", "key in values map");
                        if (str5 != null) {
                            Object value = entry.getValue();
                            if (value instanceof Number) {
                                b.put(str5, Long.valueOf(((Number) value).longValue()));
                            } else {
                                fz.m7812a("trackEvent", "value in values map", "must be a long");
                                return;
                            }
                        }
                        return;
                    }
                }
            }
            a.f7855g.m7827a(str, str2, str3, str4, b);
            fz.m7813a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, b);
        }
    }

    /* renamed from: a */
    public final void mo6220a(int i) {
        gc a = gc.m7831a();
        if (a.m7849c("setUserLevel")) {
            Integer valueOf;
            fz.m7813a("setUserLevel({}) called", Integer.valueOf(i));
            gf gfVar = a.f7854f;
            if (i >= 0) {
                valueOf = Integer.valueOf(i);
            } else {
                valueOf = null;
            }
            gfVar.m7928a(valueOf);
        }
    }

    /* renamed from: b */
    public final void mo6246b(int i) {
        gc a = gc.m7831a();
        if (a.m7849c("setUserFriendCount")) {
            Integer valueOf;
            fz.m7813a("setUserFriendCount({}) called", Integer.valueOf(i));
            gf gfVar = a.f7854f;
            if (i >= 0) {
                valueOf = Integer.valueOf(i);
            } else {
                valueOf = null;
            }
            gfVar.m7933b(valueOf);
        }
    }

    /* renamed from: b */
    public final void mo6248b(String str) {
        gc a = gc.m7831a();
        if (a.m7849c("setAppDataVersion")) {
            a.f7854f.m7929a(fx.m7793a(str));
        }
    }

    /* renamed from: f */
    public final void mo6260f(String str) {
        gc a = gc.m7831a();
        fz.m7813a("setGcmSender({}) called", str);
        a.f7852d = ct.m7337a(str);
        a.m7845b();
    }

    /* renamed from: a */
    public final void mo6225a(GLSurfaceView gLSurfaceView) {
        gc.m7831a();
        gc.m7834a(gLSurfaceView);
    }
}
