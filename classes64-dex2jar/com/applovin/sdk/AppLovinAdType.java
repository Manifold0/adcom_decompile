// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import java.util.Locale;
import java.util.HashSet;
import java.util.Set;

public class AppLovinAdType
{
    public static final AppLovinAdType INCENTIVIZED;
    public static final AppLovinAdType NATIVE;
    public static final AppLovinAdType REGULAR;
    private final String a;
    
    static {
        REGULAR = new AppLovinAdType("REGULAR");
        INCENTIVIZED = new AppLovinAdType("VIDEOA");
        NATIVE = new AppLovinAdType("NATIVE");
    }
    
    public AppLovinAdType(final String a) {
        this.a = a;
    }
    
    public static Set<AppLovinAdType> allTypes() {
        final HashSet<AppLovinAdType> set = new HashSet<AppLovinAdType>(2);
        set.add(AppLovinAdType.REGULAR);
        set.add(AppLovinAdType.INCENTIVIZED);
        return set;
    }
    
    public static AppLovinAdType fromString(final String s) {
        if (s.toUpperCase(Locale.ENGLISH).equals(AppLovinAdType.INCENTIVIZED.getLabel())) {
            return AppLovinAdType.INCENTIVIZED;
        }
        return AppLovinAdType.REGULAR;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final AppLovinAdType appLovinAdType = (AppLovinAdType)o;
            if (this.a != null) {
                if (this.a.equals(appLovinAdType.a)) {
                    return true;
                }
            }
            else if (appLovinAdType.a == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public String getLabel() {
        return this.a.toUpperCase(Locale.ENGLISH);
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
        return this.getLabel();
    }
}
