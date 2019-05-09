// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

import java.util.Map;

public class AppLovinMediatedAdInfo
{
    private final Map<String, Object> a;
    
    public AppLovinMediatedAdInfo(final Map<String, Object> a) {
        this.a = a;
    }
    
    public boolean containsKey(final String s) {
        return this.a != null && this.a.containsKey(s);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof AppLovinMediatedAdInfo)) {
                return false;
            }
            final AppLovinMediatedAdInfo appLovinMediatedAdInfo = (AppLovinMediatedAdInfo)o;
            if (this.a != null) {
                return this.a.equals(appLovinMediatedAdInfo.a);
            }
            if (appLovinMediatedAdInfo.a != null) {
                return false;
            }
        }
        return true;
    }
    
    public Object get(final String s) {
        if (this.a != null) {
            return this.a.get(s);
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        if (this.a != null) {
            return this.a.hashCode();
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "AppLovinMediatedAdInfo{adInfo=" + this.a + "}";
    }
}
