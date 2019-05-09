package com.applovin.impl.p016a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.gf;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.kongregate.android.internal.sdk.C0525o;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* renamed from: com.applovin.impl.a.n */
public class C1241n {
    /* renamed from: a */
    private static DateFormat f1651a = new SimpleDateFormat(C0525o.f631q);
    /* renamed from: b */
    private static Random f1652b = new Random(System.currentTimeMillis());

    /* renamed from: a */
    public static Uri m1902a(String str, long j, Uri uri, C1235h c1235h, AppLovinSdk appLovinSdk) {
        Uri uri2 = null;
        if (URLUtil.isValidUrl(str)) {
            try {
                String replace = str.replace("[ERRORCODE]", Integer.toString(c1235h.m1888a()));
                if (j >= 0) {
                    replace = replace.replace("[CONTENTPLAYHEAD]", C1241n.m1905a(j));
                }
                if (uri != null) {
                    replace = replace.replace("[ASSETURI]", uri.toString());
                }
                uri2 = Uri.parse(replace.replace("[CACHEBUSTING]", C1241n.m1904a()).replace("[TIMESTAMP]", C1241n.m1917b()));
            } catch (Throwable th) {
                appLovinSdk.getLogger().mo4174e("VastUtils", "Unable to replace macros in URL string " + str, th);
            }
        } else {
            appLovinSdk.getLogger().mo4173e("VastUtils", "Unable to replace macros in invalid URL string.");
        }
        return uri2;
    }

    /* renamed from: a */
    public static C1235h m1903a(C1228a c1228a) {
        return (C1241n.m1918b(c1228a) || C1241n.m1920c(c1228a)) ? null : C1235h.GENERAL_WRAPPER_ERROR;
    }

    /* renamed from: a */
    private static String m1904a() {
        return Integer.toString(10000000 + f1652b.nextInt(89999999));
    }

    /* renamed from: a */
    private static String m1905a(long j) {
        if (j <= 0) {
            return "00:00:00.000";
        }
        long toHours = TimeUnit.SECONDS.toHours(j);
        long toMinutes = TimeUnit.SECONDS.toMinutes(j) % TimeUnit.MINUTES.toSeconds(1);
        long toSeconds = j % TimeUnit.MINUTES.toSeconds(1);
        return String.format("%02d:%02d:%02d.000", new Object[]{Long.valueOf(toHours), Long.valueOf(toMinutes), Long.valueOf(toSeconds)});
    }

    /* renamed from: a */
    public static String m1906a(C1234g c1234g) {
        if (c1234g == null) {
            throw new IllegalArgumentException("Unable to get resolution uri string for fetching the next wrapper or inline response in the chain");
        }
        List b = c1234g.m1881b();
        int size = c1234g.m1881b().size();
        if (size > 0) {
            gf c = ((gf) b.get(size - 1)).m2976c("VASTAdTagURI");
            if (c != null) {
                return c.m2977c();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m1907a(gf gfVar, String str, String str2) {
        gf b = gfVar.m2974b(str);
        if (b == null) {
            return str2;
        }
        String c = b.m2977c();
        return AppLovinSdkUtils.isValidString(c) ? c : str2;
    }

    /* renamed from: a */
    private static Set<C1239l> m1908a(C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (c1234g == null) {
            return null;
        }
        List<gf> b = c1234g.m1881b();
        HashSet hashSet = new HashSet(b.size());
        Set set = hashSet;
        for (gf gfVar : b) {
            gf c = gfVar.m2976c("Wrapper");
            if (c == null) {
                c = gfVar.m2976c("InLine");
            }
            Set<C1239l> a = c != null ? C1241n.m1909a(set, c.m2973a("Error"), c1234g, appLovinSdk) : C1241n.m1909a(set, gfVar.m2973a("Error"), c1234g, appLovinSdk);
        }
        appLovinSdk.getLogger().mo4172d("VastUtils", "Retrieved " + set.size() + " top level error trackers: " + set);
        return set;
    }

    /* renamed from: a */
    private static Set<C1239l> m1909a(Set<C1239l> set, List<gf> list, C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (list != null) {
            for (gf a : list) {
                C1239l a2 = C1239l.m1898a(a, c1234g, appLovinSdk);
                if (a2 != null) {
                    set.add(a2);
                }
            }
        }
        return set;
    }

    /* renamed from: a */
    public static void m1910a(C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, C1235h c1235h, int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to handle failure. No sdk specified.");
        }
        gd.m2943a(appLovinAdLoadListener, c1234g.m1885f(), i, appLovinSdkImpl);
        C1241n.m1914a(C1241n.m1908a(c1234g, (AppLovinSdk) appLovinSdkImpl), c1235h, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static void m1911a(gf gfVar, Map<String, Set<C1239l>> map, C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to render event trackers. No sdk specified.");
        } else if (gfVar == null) {
            appLovinSdk.getLogger().mo4173e("VastUtils", "Unable to render event trackers; null node provided");
        } else if (map == null) {
            appLovinSdk.getLogger().mo4173e("VastUtils", "Unable to render event trackers; null event trackers provided");
        } else {
            gf b = gfVar.m2974b("TrackingEvents");
            if (b != null) {
                List<gf> a = b.m2973a("Tracking");
                if (a != null) {
                    for (gf b2 : a) {
                        String str = (String) b2.m2975b().get("event");
                        if (AppLovinSdkUtils.isValidString(str)) {
                            C1239l a2 = C1239l.m1898a(b2, c1234g, appLovinSdk);
                            if (a2 != null) {
                                Set set = (Set) map.get(str);
                                if (set != null) {
                                    set.add(a2);
                                } else {
                                    set = new HashSet();
                                    set.add(a2);
                                    map.put(str, set);
                                }
                            }
                        } else {
                            appLovinSdk.getLogger().mo4173e("VastUtils", "Could not find event for tracking node = " + b2);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1912a(List<gf> list, Set<C1239l> set, C1234g c1234g, AppLovinSdk appLovinSdk) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to render trackers. No sdk specified.");
        } else if (list == null) {
            appLovinSdk.getLogger().mo4173e("VastUtils", "Unable to render trackers; null nodes provided");
        } else if (set == null) {
            appLovinSdk.getLogger().mo4173e("VastUtils", "Unable to render trackers; null trackers provided");
        } else {
            for (gf a : list) {
                C1239l a2 = C1239l.m1898a(a, c1234g, appLovinSdk);
                if (a2 != null) {
                    set.add(a2);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1913a(Set<C1239l> set, long j, Uri uri, C1235h c1235h, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("Unable to fire trackers. No sdk specified.");
        } else if (set != null && !set.isEmpty()) {
            for (C1239l b : set) {
                Uri a = C1241n.m1902a(b.m1901b(), j, uri, c1235h, (AppLovinSdk) appLovinSdkImpl);
                if (a != null) {
                    appLovinSdkImpl.getPersistentPostbackManager().m2611a(a.toString(), null, false);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1914a(Set<C1239l> set, C1235h c1235h, AppLovinSdkImpl appLovinSdkImpl) {
        C1241n.m1913a((Set) set, -1, null, c1235h, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static void m1915a(Set<C1239l> set, AppLovinSdkImpl appLovinSdkImpl) {
        C1241n.m1913a((Set) set, -1, null, C1235h.UNSPECIFIED, appLovinSdkImpl);
    }

    /* renamed from: a */
    public static boolean m1916a(gf gfVar) {
        if (gfVar != null) {
            return gfVar.m2976c("Wrapper") != null;
        } else {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains a wrapper response");
        }
    }

    /* renamed from: b */
    private static String m1917b() {
        f1651a.setTimeZone(TimeZone.getDefault());
        return f1651a.format(new Date());
    }

    /* renamed from: b */
    public static boolean m1918b(C1228a c1228a) {
        if (c1228a == null) {
            return false;
        }
        C1242o a = c1228a.mo4000a();
        if (a == null) {
            return false;
        }
        List a2 = a.m1925a();
        return (a2 == null || a2.isEmpty()) ? false : true;
    }

    /* renamed from: b */
    public static boolean m1919b(gf gfVar) {
        if (gfVar != null) {
            return gfVar.m2976c("InLine") != null;
        } else {
            throw new IllegalArgumentException("Unable to check if a given XmlNode contains an inline response");
        }
    }

    /* renamed from: c */
    public static boolean m1920c(C1228a c1228a) {
        if (c1228a == null) {
            return false;
        }
        C1233f e = c1228a.m1844e();
        if (e == null) {
            return false;
        }
        C1236i b = e.m1877b();
        return b != null ? b.m1894b() != null || AppLovinSdkUtils.isValidString(b.m1895c()) : false;
    }
}
