// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import java.util.Set;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Collection;
import java.util.Locale;
import android.text.TextUtils;
import java.util.HashMap;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinLogger;
import java.util.Map;

public final class n
{
    private static final Map<String, n> a;
    private static final Object b;
    private AppLovinSdkImpl c;
    private AppLovinLogger d;
    private JSONObject e;
    private final String f;
    private String g;
    private AppLovinAdSize h;
    private AppLovinAdType i;
    private o j;
    
    static {
        a = new HashMap<String, n>();
        b = new Object();
    }
    
    private n(final AppLovinAdSize h, final AppLovinAdType i, final o j, final String s, final AppLovinSdkImpl c) {
        if (TextUtils.isEmpty((CharSequence)s) && (i == null || h == null || j == o.a)) {
            throw new IllegalArgumentException("No zone identifier or type/size/mediation type specified");
        }
        AppLovinLogger logger;
        if ((this.c = c) != null) {
            logger = c.getLogger();
        }
        else {
            logger = null;
        }
        this.d = logger;
        this.h = h;
        this.i = i;
        this.j = j;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.f = s.toLowerCase(Locale.ENGLISH);
            this.g = s.toLowerCase(Locale.ENGLISH);
            return;
        }
        this.f = (h.getLabel() + "_" + i.getLabel() + "_" + j.toString()).toLowerCase(Locale.ENGLISH);
    }
    
    private ec a(String string, final ec ec) {
        string += this.f;
        return this.c.retrieveSetting(string, (ec<Object>)ec);
    }
    
    public static n a(final AppLovinAdSize appLovinAdSize, final AppLovinAdType appLovinAdType, final o o, final AppLovinSdkImpl appLovinSdkImpl) {
        return a(appLovinAdSize, appLovinAdType, o, null, appLovinSdkImpl);
    }
    
    public static n a(final AppLovinAdSize appLovinAdSize, final AppLovinAdType appLovinAdType, final o o, final String s, final AppLovinSdkImpl appLovinSdkImpl) {
        n n = new n(appLovinAdSize, appLovinAdType, o, s, appLovinSdkImpl);
        synchronized (com.applovin.impl.sdk.n.b) {
            final String f = n.f;
            if (com.applovin.impl.sdk.n.a.containsKey(f)) {
                n = com.applovin.impl.sdk.n.a.get(f);
            }
            else {
                com.applovin.impl.sdk.n.a.put(f, n);
            }
            return n;
        }
    }
    
    public static n a(final String s, final AppLovinSdkImpl appLovinSdkImpl) {
        return a(null, null, o.a, s, appLovinSdkImpl);
    }
    
    public static n a(final String s, final JSONObject e, final AppLovinSdkImpl appLovinSdkImpl) {
        final n a = a(s, appLovinSdkImpl);
        a.e = e;
        return a;
    }
    
    private boolean a(final ec<String> ec, final AppLovinAdSize appLovinAdSize) {
        return this.c.get(ec).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.getLabel());
    }
    
    public static n b(final String s, final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, o.b, s, appLovinSdkImpl);
    }
    
    public static Collection<n> b(final AppLovinSdkImpl appLovinSdkImpl) {
        final LinkedHashSet<Object> set = new LinkedHashSet<Object>(8);
        Collections.addAll(set, c(appLovinSdkImpl), d(appLovinSdkImpl), e(appLovinSdkImpl), f(appLovinSdkImpl), g(appLovinSdkImpl), h(appLovinSdkImpl), i(appLovinSdkImpl), j(appLovinSdkImpl));
        return (Collection<n>)Collections.unmodifiableSet((Set<?>)set);
    }
    
    public static n c(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.BANNER, AppLovinAdType.REGULAR, o.b, appLovinSdkImpl);
    }
    
    public static n d(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.MREC, AppLovinAdType.REGULAR, o.b, appLovinSdkImpl);
    }
    
    public static n e(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.LEADER, AppLovinAdType.REGULAR, o.b, appLovinSdkImpl);
    }
    
    public static n f(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, o.b, appLovinSdkImpl);
    }
    
    public static n g(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, o.c, appLovinSdkImpl);
    }
    
    public static n h(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, o.b, appLovinSdkImpl);
    }
    
    public static n i(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, o.c, appLovinSdkImpl);
    }
    
    public static n j(final AppLovinSdkImpl appLovinSdkImpl) {
        return a(AppLovinAdSize.NATIVE, AppLovinAdType.NATIVE, o.b, appLovinSdkImpl);
    }
    
    private boolean n() {
        try {
            if (!TextUtils.isEmpty((CharSequence)this.g)) {
                return true;
            }
            if (this.d() == o.b) {
                if (AppLovinAdType.INCENTIVIZED.equals(this.c())) {
                    return this.c.get(ea.P);
                }
                return this.a(ea.N, this.b());
            }
            else {
                if (this.d() != o.c) {
                    return false;
                }
                if (AppLovinAdType.INCENTIVIZED.equals(this.c())) {
                    return this.c.get(ea.Q);
                }
                return this.a(ea.O, this.b());
            }
        }
        catch (Throwable t) {
            this.d.e("AdZone", "Unable to safely test preload merge capability", t);
            return false;
        }
    }
    
    String a() {
        return this.f;
    }
    
    void a(final AppLovinSdkImpl c) {
        this.c = c;
        this.d = c.getLogger();
    }
    
    AppLovinAdSize b() {
        if (this.h == null && bu.a(this.e, "ad_size")) {
            this.h = new AppLovinAdSize(bu.a(this.e, "ad_size", (String)null, this.c));
        }
        return this.h;
    }
    
    AppLovinAdType c() {
        if (this.i == null && bu.a(this.e, "ad_type")) {
            this.i = new AppLovinAdType(bu.a(this.e, "ad_type", (String)null, this.c));
        }
        return this.i;
    }
    
    o d() {
        if (this.j == o.a && bu.a(this.e, "type")) {
            this.j = o.a(bu.a(this.e, "type", (String)null, this.c));
        }
        return this.j;
    }
    
    public boolean e() {
        return AppLovinAdSize.NATIVE.equals(this.b()) && AppLovinAdType.NATIVE.equals(this.c());
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.f.equalsIgnoreCase(((n)o).f));
    }
    
    public int f() {
        if (bu.a(this.e, "capacity")) {
            return bu.a(this.e, "capacity", 0, this.c);
        }
        if (TextUtils.isEmpty((CharSequence)this.g)) {
            return this.c.get((ec<Integer>)this.a("preload_capacity_", ea.ar));
        }
        if (this.e()) {
            return this.c.get(ea.aM);
        }
        return this.c.get(ea.aL);
    }
    
    public int g() {
        int a = 0;
        if (bu.a(this.e, "extended_capacity")) {
            a = bu.a(this.e, "extended_capacity", 0, this.c);
        }
        else {
            if (TextUtils.isEmpty((CharSequence)this.g)) {
                return this.c.get((ec<Integer>)this.a("extended_preload_capacity_", ea.aB));
            }
            if (!this.e()) {
                return this.c.get(ea.aN);
            }
        }
        return a;
    }
    
    public int h() {
        return bu.a(this.e, "preload_count", 0, this.c);
    }
    
    @Override
    public int hashCode() {
        return this.f.hashCode();
    }
    
    public boolean i() {
        boolean booleanValue = false;
        if (bu.a(this.e, "refresh_enabled")) {
            booleanValue = bu.a(this.e, "refresh_enabled", false, this.c);
        }
        else {
            if (AppLovinAdSize.BANNER.equals(this.b())) {
                return this.c.get(ea.C);
            }
            if (AppLovinAdSize.MREC.equals(this.b())) {
                return this.c.get(ea.E);
            }
            if (AppLovinAdSize.LEADER.equals(this.b())) {
                return this.c.get(ea.G);
            }
        }
        return booleanValue;
    }
    
    public long j() {
        if (bu.a(this.e, "refresh_seconds")) {
            return bu.a(this.e, "refresh_seconds", 0, this.c);
        }
        if (AppLovinAdSize.BANNER.equals(this.b())) {
            return this.c.get(ea.D);
        }
        if (AppLovinAdSize.MREC.equals(this.b())) {
            return this.c.get(ea.F);
        }
        if (AppLovinAdSize.LEADER.equals(this.b())) {
            return this.c.get(ea.H);
        }
        return -1L;
    }
    
    public boolean k() {
        if (this.c.get(ea.J) && this.n()) {
            if (!TextUtils.isEmpty((CharSequence)this.g)) {
                if (this.e == null || this.h() != 0) {
                    final String upperCase = this.c.get(ea.N).toUpperCase(Locale.ENGLISH);
                    if (upperCase.contains(AppLovinAdSize.INTERSTITIAL.getLabel()) || upperCase.contains(AppLovinAdSize.BANNER.getLabel()) || upperCase.contains(AppLovinAdSize.MREC.getLabel()) || upperCase.contains(AppLovinAdSize.LEADER.getLabel())) {
                        return this.c.get(ea.bl);
                    }
                    if (this.c.getZoneManager().a(this) && this.h() > 0 && this.c.get(ea.cX)) {
                        return true;
                    }
                }
            }
            else {
                final ec a = this.a("preload_merge_init_tasks_", (ec)null);
                if (a != null && this.c.get((ec<Boolean>)a)) {
                    return this.f() > 0;
                }
            }
        }
        return false;
    }
    
    public boolean l() {
        if (bu.a(this.e, "wrapped_ads_enabled")) {
            return bu.a(this.e, "wrapped_ads_enabled", false, this.c);
        }
        if (this.b() != null) {
            return this.c.getAsList(ea.cU).contains(this.b().getLabel());
        }
        return this.c.get(ea.cT);
    }
    
    public boolean m() {
        return b(this.c).contains(this);
    }
    
    @Override
    public String toString() {
        return "AdZone{identifier=" + this.f + ", zoneObject=" + this.e + '}';
    }
}
