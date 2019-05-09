// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import java.util.List;
import java.util.concurrent.TimeUnit;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.gf;

public class l
{
    private String a;
    private String b;
    private String c;
    private long d;
    private int e;
    
    private l() {
        this.d = -1L;
        this.e = -1;
    }
    
    private static int a(final String s, final g g) {
        if ("start".equalsIgnoreCase(s)) {
            return 0;
        }
        if ("firstQuartile".equalsIgnoreCase(s)) {
            return 25;
        }
        if ("midpoint".equalsIgnoreCase(s)) {
            return 50;
        }
        if ("thirdQuartile".equalsIgnoreCase(s)) {
            return 75;
        }
        if (!"complete".equalsIgnoreCase(s)) {
            return -1;
        }
        if (g != null) {
            return g.h();
        }
        return 95;
    }
    
    public static l a(final gf gf, final g g, final AppLovinSdk appLovinSdk) {
        if (gf == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        while (true) {
            Label_0368: {
                l l = null;
                String trim = null;
                Label_0335: {
                    while (true) {
                        int n = 0;
                        Label_0384: {
                            try {
                                final String c = gf.c();
                                if (!AppLovinSdkUtils.isValidString(c)) {
                                    break Label_0368;
                                }
                                l = new l();
                                l.c = c;
                                l.a = gf.b().get("id");
                                l.b = gf.b().get("event");
                                l.e = a(l.a(), g);
                                final String s = gf.b().get("offset");
                                if (!AppLovinSdkUtils.isValidString(s)) {
                                    return l;
                                }
                                trim = s.trim();
                                if (trim.contains("%")) {
                                    l.e = gd.e(trim.substring(0, trim.length() - 1));
                                    return l;
                                }
                                if (!trim.contains(":")) {
                                    break Label_0335;
                                }
                                final List<String> a = aa.a(trim, ":");
                                final int size = a.size();
                                if (size <= 0) {
                                    return l;
                                }
                                long d = 0L;
                                n = size - 1;
                                if (n < 0) {
                                    l.d = d;
                                    l.e = -1;
                                    return l;
                                }
                                final String s2 = a.get(n);
                                if (!gd.d(s2)) {
                                    break Label_0384;
                                }
                                final int int1 = Integer.parseInt(s2);
                                if (n == size - 1) {
                                    d += int1;
                                    break Label_0384;
                                }
                                if (n == size - 2) {
                                    d += TimeUnit.MINUTES.toSeconds(int1);
                                    break Label_0384;
                                }
                                if (n == size - 3) {
                                    d += TimeUnit.HOURS.toSeconds(int1);
                                }
                                break Label_0384;
                            }
                            catch (Throwable t) {
                                appLovinSdk.getLogger().e("VastTracker", "Error occurred while initializing", t);
                            }
                            break;
                        }
                        --n;
                        continue;
                    }
                }
                appLovinSdk.getLogger().e("VastTracker", "Unable to parse time offset from rawOffsetString = " + trim);
                return l;
            }
            appLovinSdk.getLogger().e("VastTracker", "Unable to create tracker. Could not find URL.");
            break;
        }
        return null;
    }
    
    public String a() {
        return this.b;
    }
    
    public boolean a(final long n, int n2) {
        boolean b;
        if (this.d >= 0L) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (n >= this.d) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (this.e >= 0) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        if (n2 >= this.e) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        return (b && b2) || (b3 && n2 != 0);
    }
    
    public String b() {
        return this.c;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = false;
        boolean b2;
        if (this == o) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o instanceof l) {
                final l l = (l)o;
                b2 = b;
                if (this.d == l.d) {
                    b2 = b;
                    if (this.e == l.e) {
                        if (this.a != null) {
                            b2 = b;
                            if (!this.a.equals(l.a)) {
                                return b2;
                            }
                        }
                        else if (l.a != null) {
                            return false;
                        }
                        if (this.b != null) {
                            b2 = b;
                            if (!this.b.equals(l.b)) {
                                return b2;
                            }
                        }
                        else if (l.b != null) {
                            return false;
                        }
                        return this.c.equals(l.c);
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.a != null) {
            hashCode2 = this.a.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        if (this.b != null) {
            hashCode = this.b.hashCode();
        }
        return (((hashCode2 * 31 + hashCode) * 31 + this.c.hashCode()) * 31 + (int)(this.d ^ this.d >>> 32)) * 31 + this.e;
    }
    
    @Override
    public String toString() {
        return "VastTracker{identifier='" + this.a + '\'' + ", event='" + this.b + '\'' + ", uriString='" + this.c + '\'' + ", offsetSeconds=" + this.d + ", offsetPercent=" + this.e + '}';
    }
}
