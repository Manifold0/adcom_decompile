// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import java.util.List;
import com.applovin.impl.sdk.aa;
import java.util.Locale;
import java.util.HashSet;
import java.util.Set;

public class AppLovinAdSize
{
    public static final AppLovinAdSize BANNER;
    public static final AppLovinAdSize INTERSTITIAL;
    public static final AppLovinAdSize LEADER;
    public static final AppLovinAdSize MREC;
    public static final AppLovinAdSize NATIVE;
    public static final int SPAN = -1;
    private final int a;
    private final int b;
    private final String c;
    
    static {
        BANNER = new AppLovinAdSize(-1, 50, "BANNER");
        LEADER = new AppLovinAdSize(-1, 75, "LEADER");
        INTERSTITIAL = new AppLovinAdSize(-1, -1, "INTER");
        MREC = new AppLovinAdSize(300, 250, "MREC");
        NATIVE = new AppLovinAdSize("NATIVE");
    }
    
    protected AppLovinAdSize(final int a, final int b, final String c) {
        if (a < 0 && a != -1) {
            throw new IllegalArgumentException("Ad width must be a positive number. Number provided: " + a);
        }
        if (a > 9999) {
            throw new IllegalArgumentException("Ad width must be less then 9999. Number provided: " + a);
        }
        if (b < 0 && b != -1) {
            throw new IllegalArgumentException("Ad height must be a positive number. Number provided: " + b);
        }
        if (b > 9999) {
            throw new IllegalArgumentException("Ad height must be less then 9999. Number provided: " + b);
        }
        if (c == null) {
            throw new IllegalArgumentException("No label specified");
        }
        if (c.length() > 9) {
            throw new IllegalArgumentException("Provided label is too long. Label provided: " + c);
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public AppLovinAdSize(final String s) {
        this(0, 0, s);
    }
    
    private static int a(final String s) {
        if ("span".equalsIgnoreCase(s)) {
            return -1;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    public static Set<AppLovinAdSize> allSizes() {
        final HashSet<AppLovinAdSize> set = new HashSet<AppLovinAdSize>(4);
        set.add(AppLovinAdSize.BANNER);
        set.add(AppLovinAdSize.MREC);
        set.add(AppLovinAdSize.INTERSTITIAL);
        set.add(AppLovinAdSize.LEADER);
        set.add(AppLovinAdSize.NATIVE);
        return set;
    }
    
    public static AppLovinAdSize fromString(final String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        final String lowerCase = s.toLowerCase(Locale.ENGLISH);
        if (lowerCase.equals("banner")) {
            return AppLovinAdSize.BANNER;
        }
        if (lowerCase.equals("interstitial") || lowerCase.equals("inter")) {
            return AppLovinAdSize.INTERSTITIAL;
        }
        if (lowerCase.equals("mrec")) {
            return AppLovinAdSize.MREC;
        }
        if (lowerCase.equals("leader")) {
            return AppLovinAdSize.LEADER;
        }
        final List<String> a = aa.a(s, "x");
        if (a.size() == 2) {
            return new AppLovinAdSize(a(a.get(0)), a(a.get(1)), s);
        }
        return new AppLovinAdSize(0, 0, s);
    }
    
    public int getHeight() {
        return this.b;
    }
    
    public String getLabel() {
        return this.c.toUpperCase(Locale.ENGLISH);
    }
    
    public int getWidth() {
        return this.a;
    }
    
    @Override
    public String toString() {
        return this.getLabel();
    }
}
