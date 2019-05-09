package com.tapjoy.internal;

import android.app.Activity;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public final class fq {
    /* renamed from: a */
    public static void m7775a(Activity activity) {
        gc a = gc.m7831a();
        if (fz.m7814a((Object) activity, "onActivityStart: The given activity was null")) {
            fz.m7818c("onActivityStart");
            C2854d.m7349a(activity.getApplication());
            C2854d.m7350b(activity);
            if (a.m7847b("onActivityStart") && a.m7851e()) {
                fw.m7682b(activity);
            }
        }
    }

    /* renamed from: b */
    public static void m7780b(Activity activity) {
        gc a = gc.m7831a();
        if (fz.m7814a((Object) activity, "onActivityStop: The given activity was null")) {
            fz.m7818c("onActivityStop");
            C2854d.m7353c(activity);
            if (a.m7847b("onActivityStop") && !C2854d.m7351b()) {
                a.f7856h.m7982a();
            }
        }
    }

    /* renamed from: a */
    public static void m7774a() {
        gc a = gc.m7831a();
        if (a.m7847b("startSession") && a.m7851e()) {
            fw.m7682b(null);
        }
    }

    /* renamed from: b */
    public static void m7779b() {
        gc a = gc.m7831a();
        if (a.m7847b("endSession")) {
            a.f7856h.m7982a();
        }
    }

    /* renamed from: a */
    public static void m7777a(String str, String str2, String str3, String str4, long j) {
        gc a = gc.m7831a();
        if (a.m7849c("trackEvent") && fz.m7814a((Object) str2, "trackEvent: name was null")) {
            Map map = null;
            if (j != 0) {
                map = cx.m7345b();
                map.put("value", Long.valueOf(j));
            }
            a.f7855g.m7827a(str, str2, str3, str4, map);
            fz.m7813a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, map);
        }
    }

    /* renamed from: a */
    public static void m7778a(String str, String str2, String str3, String str4, String str5, long j, String str6, long j2, String str7, long j3) {
        gc a = gc.m7831a();
        if (a.m7849c("trackEvent") && fz.m7814a((Object) str2, "trackEvent: name was null")) {
            Map b = cx.m7345b();
            if (!(str5 == null || j == 0)) {
                b.put(str5, Long.valueOf(j));
            }
            if (!(str6 == null || j2 == 0)) {
                b.put(str6, Long.valueOf(j2));
            }
            if (!(str7 == null || j3 == 0)) {
                b.put(str7, Long.valueOf(j3));
            }
            if (b.isEmpty()) {
                b = null;
            }
            a.f7855g.m7827a(str, str2, str3, str4, b);
            fz.m7813a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", str, str2, str3, str4, b);
        }
    }

    /* renamed from: a */
    public static void m7776a(String str, String str2, String str3, String str4) {
        gc a = gc.m7831a();
        if (a.m7847b("trackPurchase")) {
            try {
                C2935g c2935g = new C2935g(str);
                String b = fx.m7795b(c2935g.f7824a);
                String b2 = fx.m7795b(c2935g.f7829f);
                if (b == null || b2 == null) {
                    fz.m7812a("trackPurchase", "skuDetails", "insufficient fields");
                } else if (b2.length() != 3) {
                    fz.m7812a("trackPurchase", "skuDetails", "invalid currency code");
                } else {
                    String b3 = fx.m7795b(str2);
                    String b4 = fx.m7795b(str3);
                    if (b3 != null) {
                        if (b4 != null) {
                            try {
                                C2968h c2968h = new C2968h(b3);
                                if (ct.m7339c(c2968h.f8025a) || ct.m7339c(c2968h.f8026b) || ct.m7339c(c2968h.f8027c) || c2968h.f8028d == 0) {
                                    fz.m7812a("trackPurchase", "purchaseData", "insufficient fields");
                                }
                            } catch (IOException e) {
                                fz.m7812a("trackPurchase", "purchaseData", "invalid PurchaseData JSON");
                            }
                        } else {
                            fz.m7812a("trackPurchase", "dataSignature", "is null, skipping purchase validation");
                        }
                    } else if (b4 != null) {
                        fz.m7812a("trackPurchase", "purchaseData", "is null. skipping purchase validation");
                    }
                    a.f7855g.m7825a(b, b2.toUpperCase(Locale.US), ((double) c2935g.f7830g) / 1000000.0d, b3, b4, fx.m7795b(str4));
                    if (b3 == null || b4 == null) {
                        fz.m7811a("trackPurchase without purchaseData called");
                    } else {
                        fz.m7811a("trackPurchase with purchaseData called");
                    }
                }
            } catch (IOException e2) {
                fz.m7812a("trackPurchase", "skuDetails", "invalid SkuDetails JSON");
            }
        }
    }
}
