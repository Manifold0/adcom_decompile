package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinLogger;
import com.ironsource.sdk.constants.Constants.RequestParameters;

/* renamed from: com.applovin.impl.sdk.x */
class C1296x implements AppLovinLogger {
    /* renamed from: a */
    private final AppLovinSdkImpl f2634a;
    /* renamed from: b */
    private C1297y f2635b;

    C1296x(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2634a = appLovinSdkImpl;
    }

    /* renamed from: a */
    void m3081a(C1297y c1297y) {
        this.f2635b = c1297y;
    }

    /* renamed from: a */
    boolean m3082a() {
        return this.f2634a.getSettings().isVerboseLoggingEnabled() || (this.f2634a.getSettingsManager() != null && ((Boolean) this.f2634a.getSettingsManager().m2666a(ea.f2415l)).booleanValue());
    }

    /* renamed from: d */
    public void mo4172d(String str, String str2) {
        if (m3082a()) {
            Log.d(AppLovinLogger.SDK_TAG, RequestParameters.LEFT_BRACKETS + str + "] " + str2);
        }
        if (this.f2635b != null) {
            this.f2635b.m3089a("DEBUG  [" + str + "] " + str2);
        }
    }

    /* renamed from: e */
    public void mo4173e(String str, String str2) {
        mo4174e(str, str2, null);
    }

    /* renamed from: e */
    public void mo4174e(String str, String str2, Throwable th) {
        if (m3082a()) {
            Log.e(AppLovinLogger.SDK_TAG, RequestParameters.LEFT_BRACKETS + str + "] " + str2, th);
        }
        if (this.f2635b != null) {
            this.f2635b.m3089a("ERROR  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : ""));
        }
    }

    /* renamed from: i */
    public void mo4175i(String str, String str2) {
        if (m3082a()) {
            Log.i(AppLovinLogger.SDK_TAG, RequestParameters.LEFT_BRACKETS + str + "] " + str2);
        }
        if (this.f2635b != null) {
            this.f2635b.m3089a("INFO  [" + str + "] " + str2);
        }
    }

    public void userError(String str, String str2) {
        userError(str, str2, null);
    }

    public void userError(String str, String str2, Throwable th) {
        Log.e(AppLovinLogger.SDK_TAG, RequestParameters.LEFT_BRACKETS + str + "] " + str2, th);
        if (this.f2635b != null) {
            this.f2635b.m3089a("USER  [" + str + "] " + str2 + (th != null ? ": " + th.getMessage() : ""));
        }
    }

    /* renamed from: w */
    public void mo4178w(String str, String str2) {
        mo4179w(str, str2, null);
    }

    /* renamed from: w */
    public void mo4179w(String str, String str2, Throwable th) {
        if (m3082a()) {
            Log.w(AppLovinLogger.SDK_TAG, RequestParameters.LEFT_BRACKETS + str + "] " + str2, th);
        }
        if (this.f2635b != null) {
            this.f2635b.m3089a("WARN  [" + str + "] " + str2);
        }
    }
}
