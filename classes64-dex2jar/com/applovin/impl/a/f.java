// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.gf;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import android.net.Uri;

public class f
{
    private int a;
    private int b;
    private Uri c;
    private i d;
    private Set<l> e;
    private Map<String, Set<l>> f;
    
    private f() {
        this.e = new HashSet<l>();
        this.f = new HashMap<String, Set<l>>();
    }
    
    public static f a(final gf gf, f f, final g g, final AppLovinSdk appLovinSdk) {
        if (gf == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        Label_0190: {
            if (f == null) {
                break Label_0190;
            }
            try {
                while (true) {
                    if (f.a == 0 && f.b == 0) {
                        final int e = gd.e(gf.b().get("width"));
                        final int e2 = gd.e(gf.b().get("height"));
                        if (e > 0 && e2 > 0) {
                            f.a = e;
                            f.b = e2;
                        }
                    }
                    f.d = i.a(gf, f.d, appLovinSdk);
                    if (f.c == null) {
                        final gf b = gf.b("CompanionClickThrough");
                        if (b != null) {
                            final String c = b.c();
                            if (AppLovinSdkUtils.isValidString(c)) {
                                f.c = Uri.parse(c);
                            }
                        }
                    }
                    n.a(gf.a("CompanionClickTracking"), f.e, g, appLovinSdk);
                    n.a(gf, f.f, g, appLovinSdk);
                    return f;
                    f = new f();
                    continue;
                }
            }
            catch (Throwable t) {
                appLovinSdk.getLogger().e("VastCompanionAd", "Error occurred while initializing", t);
                return null;
            }
        }
    }
    
    public Uri a() {
        return this.c;
    }
    
    public i b() {
        return this.d;
    }
    
    public Set<l> c() {
        return this.e;
    }
    
    public Map<String, Set<l>> d() {
        return this.f;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o instanceof f) {
                final f f = (f)o;
                b3 = b2;
                if (this.a == f.a) {
                    b3 = b2;
                    if (this.b == f.b) {
                        if (this.c != null) {
                            b3 = b2;
                            if (!this.c.equals((Object)f.c)) {
                                return b3;
                            }
                        }
                        else if (f.c != null) {
                            return false;
                        }
                        if (this.d != null) {
                            b3 = b2;
                            if (!this.d.equals(f.d)) {
                                return b3;
                            }
                        }
                        else if (f.d != null) {
                            return false;
                        }
                        if (this.e != null) {
                            b3 = b2;
                            if (!this.e.equals(f.e)) {
                                return b3;
                            }
                        }
                        else if (f.e != null) {
                            return false;
                        }
                        boolean equals;
                        if (this.f != null) {
                            equals = this.f.equals(f.f);
                        }
                        else {
                            equals = b;
                            if (f.f != null) {
                                equals = false;
                            }
                        }
                        return equals;
                    }
                }
            }
        }
        return b3;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int a = this.a;
        final int b = this.b;
        int hashCode2;
        if (this.c != null) {
            hashCode2 = this.c.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.d != null) {
            hashCode3 = this.d.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        int hashCode4;
        if (this.e != null) {
            hashCode4 = this.e.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        if (this.f != null) {
            hashCode = this.f.hashCode();
        }
        return (hashCode4 + (hashCode3 + (hashCode2 + (a * 31 + b) * 31) * 31) * 31) * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "VastCompanionAd{width=" + this.a + ", height=" + this.b + ", destinationUri=" + this.c + ", nonVideoResource=" + this.d + ", clickTrackers=" + this.e + ", eventTrackers=" + this.f + '}';
    }
}
