// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import com.applovin.impl.sdk.aa;
import java.util.List;
import android.net.Uri;
import com.applovin.impl.sdk.gd;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.bu;
import com.applovin.impl.sdk.ee;
import java.util.Map;
import java.util.Collections;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.applovin.impl.sdk.an;

public class a extends an
{
    private final String e;
    private final String f;
    private final k g;
    private final long h;
    private final o i;
    private final f j;
    private final Set<l> k;
    private final Set<l> l;
    
    private a(final c c) {
        super(c.a, c.b, c.c);
        this.e = c.e;
        this.g = c.g;
        this.f = c.f;
        this.i = c.h;
        this.j = c.i;
        this.k = c.j;
        this.l = c.k;
        this.h = c.d;
    }
    
    private Set<l> a(final d d, final String[] array) {
        if (array != null && array.length > 0) {
            Map<String, Set<l>> map;
            if (d == d.a && this.i != null) {
                map = this.i.e();
            }
            else if (d == d.b && this.j != null) {
                map = this.j.d();
            }
            else {
                map = null;
            }
            final HashSet<l> set = new HashSet<l>();
            if (map != null && !map.isEmpty()) {
                for (int length = array.length, i = 0; i < length; ++i) {
                    final String s = array[i];
                    if (map.containsKey(s)) {
                        set.addAll((Collection<?>)map.get(s));
                    }
                }
            }
            return (Set<l>)Collections.unmodifiableSet((Set<?>)set);
        }
        return Collections.emptySet();
    }
    
    public static c an() {
        return new c(null);
    }
    
    private com.applovin.impl.a.q ao() {
        final com.applovin.impl.a.q[] a = com.applovin.impl.a.q.a();
        final int y = new ee(this.c).Y();
        if (y >= 0 && y < a.length) {
            return a[y];
        }
        return com.applovin.impl.a.q.a;
    }
    
    private Set<l> ap() {
        if (this.i != null) {
            return this.i.d();
        }
        return Collections.emptySet();
    }
    
    private Set<l> aq() {
        if (this.j != null) {
            return this.j.c();
        }
        return Collections.emptySet();
    }
    
    @Override
    public boolean E() {
        return this.g() != null;
    }
    
    public o a() {
        return this.i;
    }
    
    public String a(String s) {
        try {
            final String a = bu.a(this.a, "vimp_url", "", this.c);
            if (AppLovinSdkUtils.isValidString(a)) {
                final String replace = a.replace("{CLCODE}", gd.c(this.n()));
                if (AppLovinSdkUtils.isValidString(s)) {
                    s = replace.replace("{PLACEMENT}", gd.c(s));
                }
                else {
                    s = replace.replace("{PLACEMENT}", "");
                }
                return s.toString();
            }
        }
        catch (Throwable t) {
            this.c.getLogger().e("VastAd", "Unable to create VAST impression URL", t);
        }
        return "";
    }
    
    public Set<l> a(final e e, final String s) {
        return this.a(e, new String[] { s });
    }
    
    public Set<l> a(final e e, final String[] array) {
        this.c.getLogger().d("VastAd", "Retrieving trackers of type '" + e + "' and events '" + array + "'...");
        if (e == e.a) {
            return this.k;
        }
        if (e == e.b) {
            return this.ap();
        }
        if (e == e.c) {
            return this.aq();
        }
        if (e == e.d) {
            return this.a(com.applovin.impl.a.d.a, array);
        }
        if (e == e.e) {
            return this.a(com.applovin.impl.a.d.b, array);
        }
        if (e == e.f) {
            return this.l;
        }
        this.c.getLogger().e("VastAd", "Failed to retrieve trackers of invalid type '" + e + "' and events '" + array + "'");
        return Collections.emptySet();
    }
    
    public boolean a(final AppLovinSdk appLovinSdk) {
        return bu.a(this.a, "cache_companion_ad", true, appLovinSdk);
    }
    
    @Override
    public boolean b() {
        final r c = this.c();
        return c != null && c.c();
    }
    
    public boolean b(final AppLovinSdk appLovinSdk) {
        return bu.a(this.a, "cache_video", true, appLovinSdk);
    }
    
    public r c() {
        if (this.i != null) {
            return this.i.a(this.ao());
        }
        return null;
    }
    
    public void c(final String s) {
        try {
            synchronized (this.d) {
                this.a.put("html_template", (Object)s);
            }
        }
        catch (Throwable t) {}
    }
    
    @Override
    public Uri d() {
        final r c = this.c();
        if (c != null) {
            return c.b();
        }
        return null;
    }
    
    public f e() {
        return this.j;
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
            if (o instanceof a) {
                b3 = b2;
                if (super.equals(o)) {
                    final a a = (a)o;
                    if (this.e != null) {
                        b3 = b2;
                        if (!this.e.equals(a.e)) {
                            return b3;
                        }
                    }
                    else if (a.e != null) {
                        return false;
                    }
                    if (this.f != null) {
                        b3 = b2;
                        if (!this.f.equals(a.f)) {
                            return b3;
                        }
                    }
                    else if (a.f != null) {
                        return false;
                    }
                    if (this.g != null) {
                        b3 = b2;
                        if (!this.g.equals(a.g)) {
                            return b3;
                        }
                    }
                    else if (a.g != null) {
                        return false;
                    }
                    if (this.i != null) {
                        b3 = b2;
                        if (!this.i.equals(a.i)) {
                            return b3;
                        }
                    }
                    else if (a.i != null) {
                        return false;
                    }
                    if (this.j != null) {
                        b3 = b2;
                        if (!this.j.equals(a.j)) {
                            return b3;
                        }
                    }
                    else if (a.j != null) {
                        return false;
                    }
                    if (this.k != null) {
                        b3 = b2;
                        if (!this.k.equals(a.k)) {
                            return b3;
                        }
                    }
                    else if (a.k != null) {
                        return false;
                    }
                    boolean equals;
                    if (this.l != null) {
                        equals = this.l.equals(a.l);
                    }
                    else {
                        equals = b;
                        if (a.l != null) {
                            equals = false;
                        }
                    }
                    return equals;
                }
            }
        }
        return b3;
    }
    
    @Override
    public boolean f() {
        boolean b2;
        final boolean b = b2 = false;
        if (this.i != null) {
            final List<r> a = this.i.a();
            b2 = b;
            if (a != null) {
                b2 = b;
                if (a.size() > 0) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Override
    public Uri g() {
        if (this.i != null) {
            return this.i.c();
        }
        return null;
    }
    
    public List<String> h() {
        return aa.a(bu.a(this.a, "vast_resource_cache_prefix", (String)null, this.c));
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = super.hashCode();
        int hashCode3;
        if (this.e != null) {
            hashCode3 = this.e.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        int hashCode4;
        if (this.f != null) {
            hashCode4 = this.f.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        int hashCode5;
        if (this.g != null) {
            hashCode5 = this.g.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        int hashCode6;
        if (this.i != null) {
            hashCode6 = this.i.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        int hashCode7;
        if (this.j != null) {
            hashCode7 = this.j.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        int hashCode8;
        if (this.k != null) {
            hashCode8 = this.k.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        if (this.l != null) {
            hashCode = this.l.hashCode();
        }
        return (hashCode8 + (hashCode7 + (hashCode6 + (hashCode5 + (hashCode4 + (hashCode3 + hashCode2 * 31) * 31) * 31) * 31) * 31) * 31) * 31 + hashCode;
    }
    
    public boolean i() {
        return bu.a(this.a, "vast_fire_click_trackers_on_html_clicks", false, this.c);
    }
    
    public String j() {
        return bu.a(this.a, "html_template", "", this.c);
    }
    
    public Uri k() {
        Uri parse = null;
        final String a = bu.a(this.a, "html_template_url", (String)null, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            parse = Uri.parse(a);
        }
        return parse;
    }
    
    @Override
    public long l() {
        return this.h;
    }
    
    @Override
    public String toString() {
        return "VastAd{title='" + this.e + '\'' + ", adDescription='" + this.f + '\'' + ", systemInfo=" + this.g + ", videoCreative=" + this.i + ", companionAd=" + this.j + ", impressionTrackers=" + this.k + ", errorTrackers=" + this.l + '}';
    }
}
