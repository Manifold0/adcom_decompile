package com.applovin.sdk;

import com.applovin.impl.sdk.aa;
import com.google.android.gms.nearby.messages.Strategy;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class AppLovinAdSize {
    public static final AppLovinAdSize BANNER = new AppLovinAdSize(-1, 50, "BANNER");
    public static final AppLovinAdSize INTERSTITIAL = new AppLovinAdSize(-1, -1, "INTER");
    public static final AppLovinAdSize LEADER = new AppLovinAdSize(-1, 75, "LEADER");
    public static final AppLovinAdSize MREC = new AppLovinAdSize(Strategy.TTL_SECONDS_DEFAULT, 250, "MREC");
    public static final AppLovinAdSize NATIVE = new AppLovinAdSize("NATIVE");
    public static final int SPAN = -1;
    /* renamed from: a */
    private final int f2650a;
    /* renamed from: b */
    private final int f2651b;
    /* renamed from: c */
    private final String f2652c;

    protected AppLovinAdSize(int i, int i2, String str) {
        if (i < 0 && i != -1) {
            throw new IllegalArgumentException("Ad width must be a positive number. Number provided: " + i);
        } else if (i > 9999) {
            throw new IllegalArgumentException("Ad width must be less then 9999. Number provided: " + i);
        } else if (i2 < 0 && i2 != -1) {
            throw new IllegalArgumentException("Ad height must be a positive number. Number provided: " + i2);
        } else if (i2 > 9999) {
            throw new IllegalArgumentException("Ad height must be less then 9999. Number provided: " + i2);
        } else if (str == null) {
            throw new IllegalArgumentException("No label specified");
        } else if (str.length() > 9) {
            throw new IllegalArgumentException("Provided label is too long. Label provided: " + str);
        } else {
            this.f2650a = i;
            this.f2651b = i2;
            this.f2652c = str;
        }
    }

    public AppLovinAdSize(String str) {
        this(0, 0, str);
    }

    /* renamed from: a */
    private static int m3094a(String str) {
        if ("span".equalsIgnoreCase(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Set<AppLovinAdSize> allSizes() {
        Set<AppLovinAdSize> hashSet = new HashSet(4);
        hashSet.add(BANNER);
        hashSet.add(MREC);
        hashSet.add(INTERSTITIAL);
        hashSet.add(LEADER);
        hashSet.add(NATIVE);
        return hashSet;
    }

    public static AppLovinAdSize fromString(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        String toLowerCase = str.toLowerCase(Locale.ENGLISH);
        if (toLowerCase.equals("banner")) {
            return BANNER;
        }
        if (toLowerCase.equals("interstitial") || toLowerCase.equals("inter")) {
            return INTERSTITIAL;
        }
        if (toLowerCase.equals("mrec")) {
            return MREC;
        }
        if (toLowerCase.equals("leader")) {
            return LEADER;
        }
        List a = aa.m2194a(str, "x");
        return a.size() == 2 ? new AppLovinAdSize(m3094a((String) a.get(0)), m3094a((String) a.get(1)), str) : new AppLovinAdSize(0, 0, str);
    }

    public int getHeight() {
        return this.f2651b;
    }

    public String getLabel() {
        return this.f2652c.toUpperCase(Locale.ENGLISH);
    }

    public int getWidth() {
        return this.f2650a;
    }

    public String toString() {
        return getLabel();
    }
}
