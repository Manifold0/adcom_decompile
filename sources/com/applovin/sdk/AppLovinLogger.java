package com.applovin.sdk;

public interface AppLovinLogger {
    public static final String SDK_TAG = "AppLovinSdk";

    /* renamed from: d */
    void mo4172d(String str, String str2);

    /* renamed from: e */
    void mo4173e(String str, String str2);

    /* renamed from: e */
    void mo4174e(String str, String str2, Throwable th);

    /* renamed from: i */
    void mo4175i(String str, String str2);

    void userError(String str, String str2);

    void userError(String str, String str2, Throwable th);

    /* renamed from: w */
    void mo4178w(String str, String str2);

    /* renamed from: w */
    void mo4179w(String str, String str2, Throwable th);
}
