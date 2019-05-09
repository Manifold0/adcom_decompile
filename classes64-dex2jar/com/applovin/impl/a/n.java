// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import java.util.Date;
import java.util.TimeZone;
import java.util.Map;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;
import com.applovin.impl.sdk.gf;
import java.util.concurrent.TimeUnit;
import android.webkit.URLUtil;
import com.applovin.sdk.AppLovinSdk;
import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.text.DateFormat;

public class n
{
    private static DateFormat a;
    private static Random b;
    
    static {
        n.a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        n.b = new Random(System.currentTimeMillis());
    }
    
    public static Uri a(final String s, final long n, Uri parse, final h h, final AppLovinSdk appLovinSdk) {
        if (URLUtil.isValidUrl(s)) {
            try {
                String s3;
                final String s2 = s3 = s.replace("[ERRORCODE]", Integer.toString(h.a()));
                if (n >= 0L) {
                    s3 = s2.replace("[CONTENTPLAYHEAD]", a(n));
                }
                String replace = s3;
                if (parse != null) {
                    replace = s3.replace("[ASSETURI]", parse.toString());
                }
                parse = Uri.parse(replace.replace("[CACHEBUSTING]", a()).replace("[TIMESTAMP]", b()));
                return parse;
            }
            catch (Throwable t) {
                appLovinSdk.getLogger().e("VastUtils", "Unable to replace macros in URL string " + s, t);
                return null;
            }
        }
        appLovinSdk.getLogger().e("VastUtils", "Unable to replace macros in invalid URL string.");
        return null;
    }
    
    public static h a(final a a) {
        h c = null;
        if (!b(a)) {
            c = c;
            if (!c(a)) {
                c = h.c;
            }
        }
        return c;
    }
    
    private static String a() {
        return Integer.toString(10000000 + n.b.nextInt(89999999));
    }
    
    private static String a(final long n) {
        if (n > 0L) {
            return String.format("%02d:%02d:%02d.000", TimeUnit.SECONDS.toHours(n), TimeUnit.SECONDS.toMinutes(n) % TimeUnit.MINUTES.toSeconds(1L), n % TimeUnit.MINUTES.toSeconds(1L));
        }
        return "00:00:00.000";
    }
    
    public static String a(final g g) {
        if (g == null) {
            throw new IllegalArgumentException("Unable to get resolution uri string for fetching the next wrapper or inline response in the chain");
        }
        final List<gf> b = g.b();
        final int size = g.b().size();
        if (size > 0) {
            final gf c = b.get(size - 1).c("VASTAdTagURI");
            if (c != null) {
                return c.c();
            }
        }
        return null;
    }
    
    public static String a(final gf gf, String c, final String s) {
        final gf b = gf.b(c);
        String s2 = s;
        if (b != null) {
            c = b.c();
            s2 = s;
            if (AppLovinSdkUtils.isValidString(c)) {
                s2 = c;
            }
        }
        return s2;
    }
    
    private static Set<l> a(final g g, final AppLovinSdk appLovinSdk) {
        if (g != null) {
            final List<gf> b = g.b();
            Set<l> set = new HashSet<l>(b.size());
            for (final gf gf : b) {
                gf gf2 = gf.c("Wrapper");
                if (gf2 == null) {
                    gf2 = gf.c("InLine");
                }
                if (gf2 != null) {
                    set = a(set, gf2.a("Error"), g, appLovinSdk);
                }
                else {
                    set = a(set, gf.a("Error"), g, appLovinSdk);
                }
            }
            appLovinSdk.getLogger().d("VastUtils", "Retrieved " + set.size() + " top level error trackers: " + set);
            return set;
        }
        return null;
    }
    
    private static Set<l> a(final Set<l> set, final List<gf> list, final g g, final AppLovinSdk appLovinSdk) {
        if (list != null) {
            final Iterator<gf> iterator = list.iterator();
            while (iterator.hasNext()) {
                final l a = l.a(iterator.next(), g, appLovinSdk);
                if (a != null) {
                    set.add(a);
                }
            }
        }
        return set;
    }
    
    public static void a(final g g, final AppLovinAdLoadListener appLovinAdLoadListener, final h h, final int n, final AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to handle failure. No sdk specified.");
        }
        gd.a(appLovinAdLoadListener, g.f(), n, appLovinSdkImpl);
        a(a(g, appLovinSdkImpl), h, appLovinSdkImpl);
    }
    
    public static void a(gf b, final Map<String, Set<l>> map, final g g, final AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to render event trackers. No sdk specified.");
        }
        if (b == null) {
            appLovinSdk.getLogger().e("VastUtils", "Unable to render event trackers; null node provided");
        }
        else {
            if (map == null) {
                appLovinSdk.getLogger().e("VastUtils", "Unable to render event trackers; null event trackers provided");
                return;
            }
            b = b.b("TrackingEvents");
            if (b != null) {
                final List<gf> a = b.a("Tracking");
                if (a != null) {
                    for (final gf gf : a) {
                        final String s = gf.b().get("event");
                        if (AppLovinSdkUtils.isValidString(s)) {
                            final l a2 = l.a(gf, g, appLovinSdk);
                            if (a2 == null) {
                                continue;
                            }
                            final HashSet<l> set = map.get(s);
                            if (set != null) {
                                set.add(a2);
                            }
                            else {
                                final HashSet<l> set2 = new HashSet<l>();
                                set2.add(a2);
                                map.put(s, set2);
                            }
                        }
                        else {
                            appLovinSdk.getLogger().e("VastUtils", "Could not find event for tracking node = " + gf);
                        }
                    }
                }
            }
        }
    }
    
    public static void a(final List<gf> list, final Set<l> set, final g g, final AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to render trackers. No sdk specified.");
        }
        if (list == null) {
            appLovinSdk.getLogger().e("VastUtils", "Unable to render trackers; null nodes provided");
        }
        else {
            if (set == null) {
                appLovinSdk.getLogger().e("VastUtils", "Unable to render trackers; null trackers provided");
                return;
            }
            final Iterator<gf> iterator = list.iterator();
            while (iterator.hasNext()) {
                final l a = l.a(iterator.next(), g, appLovinSdk);
                if (a != null) {
                    set.add(a);
                }
            }
        }
    }
    
    public static void a(final Set<l> set, final long n, final Uri uri, final h h, final AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to fire trackers. No sdk specified.");
        }
        if (set != null && !set.isEmpty()) {
            final Iterator<l> iterator = set.iterator();
            while (iterator.hasNext()) {
                final Uri a = a(iterator.next().b(), n, uri, h, appLovinSdkImpl);
                if (a != null) {
                    appLovinSdkImpl.getPersistentPostbackManager().a(a.toString(), null, false);
                }
            }
        }
    }
    
    public static void a(final Set<l> set, final h h, final AppLovinSdkImpl appLovinSdkImpl) {
        a(set, -1L, null, h, appLovinSdkImpl);
    }
    
    public static void a(final Set<l> set, final AppLovinSdkImpl appLovinSdkImpl) {
        a(set, -1L, null, h.a, appLovinSdkImpl);
    }
    
    public static boolean a(final gf gf) {
        if (gf == null) {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains a wrapper response");
        }
        return gf.c("Wrapper") != null;
    }
    
    private static String b() {
        n.a.setTimeZone(TimeZone.getDefault());
        return n.a.format(new Date());
    }
    
    public static boolean b(final a a) {
        if (a != null) {
            final o a2 = a.a();
            if (a2 != null) {
                final List<r> a3 = a2.a();
                if (a3 != null && !a3.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean b(final gf gf) {
        if (gf == null) {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains an inline response");
        }
        return gf.c("InLine") != null;
    }
    
    public static boolean c(final a a) {
        if (a != null) {
            final f e = a.e();
            if (e != null) {
                final i b = e.b();
                if (b != null && (b.b() != null || AppLovinSdkUtils.isValidString(b.c()))) {
                    return true;
                }
            }
        }
        return false;
    }
}
