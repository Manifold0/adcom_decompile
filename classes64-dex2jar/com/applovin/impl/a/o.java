// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import java.util.Comparator;
import java.util.Iterator;
import android.webkit.MimeTypeMap;
import com.applovin.impl.sdk.ee;
import java.util.ArrayList;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Collection;
import com.applovin.impl.sdk.gf;
import java.util.concurrent.TimeUnit;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.aa;
import com.applovin.sdk.AppLovinSdk;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import android.net.Uri;
import java.util.List;

public class o
{
    private List<r> a;
    private List<String> b;
    private int c;
    private Uri d;
    private final Set<l> e;
    private final Map<String, Set<l>> f;
    
    private o() {
        this.a = (List<r>)Collections.EMPTY_LIST;
        this.b = (List<String>)Collections.EMPTY_LIST;
        this.e = new HashSet<l>();
        this.f = new HashMap<String, Set<l>>();
    }
    
    private o(final g g) {
        this.a = (List<r>)Collections.EMPTY_LIST;
        this.b = (List<String>)Collections.EMPTY_LIST;
        this.e = new HashSet<l>();
        this.f = new HashMap<String, Set<l>>();
        this.b = g.g();
    }
    
    private static int a(final String s, final AppLovinSdk appLovinSdk) {
        try {
            final List<String> a = aa.a(s, ":");
            if (a.size() == 3) {
                return (int)(gd.e(a.get(2)) + (TimeUnit.HOURS.toSeconds(gd.e(a.get(0))) + TimeUnit.MINUTES.toSeconds(gd.e(a.get(1)))));
            }
        }
        catch (Throwable t) {
            appLovinSdk.getLogger().e("VastVideoCreative", "Unable to parse duration from \"" + s + "\"");
        }
        return 0;
    }
    
    public static o a(final gf gf, o o, final g g, final AppLovinSdk appLovinSdk) {
        if (gf == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (g == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        Label_0235: {
            if (o == null) {
                break Label_0235;
            }
            try {
                while (true) {
                    if (o.c == 0) {
                        final gf b = gf.b("Duration");
                        if (b != null) {
                            final int a = a(b.c(), appLovinSdk);
                            if (a > 0) {
                                o.c = a;
                            }
                        }
                    }
                    final gf b2 = gf.b("MediaFiles");
                    if (b2 != null) {
                        final List<r> a2 = a(b2, appLovinSdk);
                        if (a2 != null && a2.size() > 0) {
                            if (o.a != null) {
                                a2.addAll(o.a);
                            }
                            o.a = a2;
                        }
                    }
                    final gf b3 = gf.b("VideoClicks");
                    if (b3 != null) {
                        if (o.d == null) {
                            final gf b4 = b3.b("ClickThrough");
                            if (b4 != null) {
                                final String c = b4.c();
                                if (AppLovinSdkUtils.isValidString(c)) {
                                    o.d = Uri.parse(c);
                                }
                            }
                        }
                        n.a(b3.a("ClickTracking"), o.e, g, appLovinSdk);
                    }
                    n.a(gf, o.f, g, appLovinSdk);
                    return o;
                    o = new o(g);
                    continue;
                }
            }
            catch (Throwable t) {
                appLovinSdk.getLogger().e("VastVideoCreative", "Error occurred while initializing", t);
                return null;
            }
        }
    }
    
    private static List<r> a(gf gf, final AppLovinSdk appLovinSdk) {
        final List<gf> a = gf.a("MediaFile");
        gf = (gf)new ArrayList(a.size());
        final ee ee = new ee(appLovinSdk);
        final List<String> a2 = aa.a(ee.aa());
        final List<String> a3 = aa.a(ee.ab());
        final Iterator<gf> iterator = a.iterator();
        while (iterator.hasNext()) {
            final r a4 = r.a(iterator.next(), appLovinSdk);
            if (a4 != null) {
                Label_0165: {
                    try {
                        final String d = a4.d();
                        if (!AppLovinSdkUtils.isValidString(d) || a2.contains(d)) {
                            break Label_0165;
                        }
                        ((List<r>)gf).add(a4);
                    }
                    catch (Throwable t) {
                        appLovinSdk.getLogger().e("VastVideoCreative", "Failed to validate vidoe file: " + a4, t);
                    }
                    continue;
                }
                if (ee.ac()) {
                    final String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(a4.b().toString());
                    if (AppLovinSdkUtils.isValidString(fileExtensionFromUrl) && !a3.contains(fileExtensionFromUrl)) {
                        ((List<r>)gf).add(a4);
                        continue;
                    }
                }
                appLovinSdk.getLogger().w("VastVideoCreative", "Video file not supported: " + a4);
            }
        }
        return (List<r>)gf;
    }
    
    public r a(final q q) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        Object a = new ArrayList<Object>(3);
        for (final String s : this.b) {
            for (final r r : this.a) {
                final String d = r.d();
                if (AppLovinSdkUtils.isValidString(d) && s.equalsIgnoreCase(d)) {
                    ((List<r>)a).add(r);
                }
            }
            if (!((List)a).isEmpty()) {
                break;
            }
        }
        if (((List)a).isEmpty()) {
            a = this.a;
        }
        Collections.sort((List<Object>)a, (Comparator<? super Object>)new p(this));
        if (q == q.b) {
            return ((List<r>)a).get(0);
        }
        if (q == q.c) {
            return ((List<r>)a).get(((List)a).size() / 2);
        }
        return ((List<r>)a).get(((List)a).size() - 1);
    }
    
    public List<r> a() {
        return this.a;
    }
    
    public int b() {
        return this.c;
    }
    
    public Uri c() {
        return this.d;
    }
    
    public Set<l> d() {
        return this.e;
    }
    
    public Map<String, Set<l>> e() {
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
            if (o instanceof o) {
                final o o2 = (o)o;
                b3 = b2;
                if (this.c == o2.c) {
                    if (this.a != null) {
                        b3 = b2;
                        if (!this.a.equals(o2.a)) {
                            return b3;
                        }
                    }
                    else if (o2.a != null) {
                        return false;
                    }
                    if (this.d != null) {
                        b3 = b2;
                        if (!this.d.equals((Object)o2.d)) {
                            return b3;
                        }
                    }
                    else if (o2.d != null) {
                        return false;
                    }
                    if (this.e != null) {
                        b3 = b2;
                        if (!this.e.equals(o2.e)) {
                            return b3;
                        }
                    }
                    else if (o2.e != null) {
                        return false;
                    }
                    boolean equals;
                    if (this.f != null) {
                        equals = this.f.equals(o2.f);
                    }
                    else {
                        equals = b;
                        if (o2.f != null) {
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
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.a != null) {
            hashCode2 = this.a.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final int c = this.c;
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
        return (hashCode4 + (hashCode3 + (hashCode2 * 31 + c) * 31) * 31) * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "VastVideoCreative{videoFiles=" + this.a + ", durationSeconds=" + this.c + ", destinationUri=" + this.d + ", clickTrackers=" + this.e + ", eventTrackers=" + this.f + '}';
    }
}
