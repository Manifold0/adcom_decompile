package com.applovin.impl.p016a;

import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

/* renamed from: com.applovin.impl.a.k */
public class C1238k {
    /* renamed from: a */
    private String f1643a;
    /* renamed from: b */
    private String f1644b;

    private C1238k() {
    }

    /* renamed from: a */
    public static C1238k m1896a(gf gfVar, C1238k c1238k, AppLovinSdk appLovinSdk) {
        if (gfVar == null) {
            throw new IllegalArgumentException("No node specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        } else {
            C1238k c1238k2 = c1238k != null ? c1238k : new C1238k();
            try {
                String c;
                if (!AppLovinSdkUtils.isValidString(c1238k2.f1643a)) {
                    c = gfVar.m2977c();
                    if (AppLovinSdkUtils.isValidString(c)) {
                        c1238k2.f1643a = c;
                    }
                }
                if (!AppLovinSdkUtils.isValidString(c1238k2.f1644b)) {
                    c = (String) gfVar.m2975b().get("version");
                    if (AppLovinSdkUtils.isValidString(c)) {
                        c1238k2.f1644b = c;
                    }
                }
                return c1238k2;
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastSystemInfo", "Error occurred while initializing", th);
                return null;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1238k)) {
            return false;
        }
        C1238k c1238k = (C1238k) obj;
        return (this.f1643a == null ? c1238k.f1643a != null : !this.f1643a.equals(c1238k.f1643a)) ? false : this.f1644b != null ? this.f1644b.equals(c1238k.f1644b) : c1238k.f1644b == null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f1643a != null ? this.f1643a.hashCode() : 0) * 31;
        if (this.f1644b != null) {
            i = this.f1644b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "VastSystemInfo{name='" + this.f1643a + '\'' + ", version='" + this.f1644b + '\'' + '}';
    }
}
