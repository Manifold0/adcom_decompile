package com.applovin.mediation;

import java.util.Map;

public class AppLovinMediatedAdInfo {
    /* renamed from: a */
    private final Map<String, Object> f2638a;

    public AppLovinMediatedAdInfo(Map<String, Object> map) {
        this.f2638a = map;
    }

    public boolean containsKey(String str) {
        return this.f2638a != null ? this.f2638a.containsKey(str) : false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AppLovinMediatedAdInfo)) {
            return false;
        }
        AppLovinMediatedAdInfo appLovinMediatedAdInfo = (AppLovinMediatedAdInfo) obj;
        return this.f2638a != null ? this.f2638a.equals(appLovinMediatedAdInfo.f2638a) : appLovinMediatedAdInfo.f2638a == null;
    }

    public Object get(String str) {
        return this.f2638a != null ? this.f2638a.get(str) : null;
    }

    public int hashCode() {
        return this.f2638a != null ? this.f2638a.hashCode() : 0;
    }

    public String toString() {
        return "AppLovinMediatedAdInfo{adInfo=" + this.f2638a + "}";
    }
}
