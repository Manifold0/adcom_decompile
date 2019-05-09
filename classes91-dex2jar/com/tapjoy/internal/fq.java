// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Locale;
import java.io.IOException;
import android.app.Activity;

public final class fq
{
    public static void a() {
        final gc a = gc.a();
        if (a.b("startSession") && a.e()) {
            fw.b(null);
        }
    }
    
    public static void a(final Activity activity) {
        final gc a = gc.a();
        if (fz.a(activity, "onActivityStart: The given activity was null")) {
            fz.c("onActivityStart");
            d.a(activity.getApplication());
            d.b(activity);
            if (a.b("onActivityStart") && a.e()) {
                fw.b(activity);
            }
        }
    }
    
    public static void a(String s, String b, String b2, String b3) {
        final gc a = gc.a();
        if (!a.b("trackPurchase")) {
            return;
        }
        String b4;
        String s2;
        try {
            s = (String)new g(s);
            b4 = fx.b(((g)s).a);
            s2 = fx.b(((g)s).f);
            if (b4 == null || s2 == null) {
                fz.a("trackPurchase", "skuDetails", "insufficient fields");
                return;
            }
        }
        catch (IOException ex) {
            fz.a("trackPurchase", "skuDetails", "invalid SkuDetails JSON");
            return;
        }
        if (s2.length() != 3) {
            fz.a("trackPurchase", "skuDetails", "invalid currency code");
            return;
        }
        b = fx.b(b);
        b2 = fx.b(b2);
        while (true) {
            Label_0239: {
                if (b != null) {
                    if (b2 == null) {
                        break Label_0239;
                    }
                }
                else {
                    if (b2 != null) {
                        fz.a("trackPurchase", "purchaseData", "is null. skipping purchase validation");
                        break Label_0172;
                    }
                    break Label_0172;
                }
                Label_0267: {
                    try {
                        final h h = new h(b);
                        if (ct.c(h.a) || ct.c(h.b) || ct.c(h.c) || h.d == 0L) {
                            fz.a("trackPurchase", "purchaseData", "insufficient fields");
                        }
                        s2 = s2.toUpperCase(Locale.US);
                        b3 = fx.b(b3);
                        a.g.a(b4, s2, ((g)s).g / 1000000.0, b, b2, b3);
                        if (b != null && b2 != null) {
                            fz.a("trackPurchase with purchaseData called");
                            return;
                        }
                        break Label_0267;
                    }
                    catch (IOException ex2) {
                        fz.a("trackPurchase", "purchaseData", "invalid PurchaseData JSON");
                        continue;
                    }
                    break Label_0239;
                }
                fz.a("trackPurchase without purchaseData called");
                return;
            }
            fz.a("trackPurchase", "dataSignature", "is null, skipping purchase validation");
            continue;
        }
    }
    
    public static void a(final String s, final String s2, final String s3, final String s4, final long n) {
        final gc a = gc.a();
        if (a.c("trackEvent") && fz.a((Object)s2, "trackEvent: name was null")) {
            Map<String, Long> b = null;
            if (n != 0L) {
                b = (Map<String, Long>)cx.b();
                b.put("value", n);
            }
            a.g.a(s, s2, s3, s4, b);
            fz.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", s, s2, s3, s4, b);
        }
    }
    
    public static void a(final String s, final String s2, final String s3, final String s4, final String s5, final long n, final String s6, final long n2, final String s7, final long n3) {
        final gc a = gc.a();
        if (a.c("trackEvent") && fz.a((Object)s2, "trackEvent: name was null")) {
            final LinkedHashMap b = cx.b();
            if (s5 != null && n != 0L) {
                b.put(s5, n);
            }
            if (s6 != null && n2 != 0L) {
                b.put(s6, n2);
            }
            if (s7 != null && n3 != 0L) {
                b.put(s7, n3);
            }
            LinkedHashMap<String, Long> linkedHashMap = (LinkedHashMap<String, Long>)b;
            if (b.isEmpty()) {
                linkedHashMap = null;
            }
            a.g.a(s, s2, s3, s4, linkedHashMap);
            fz.a("trackEvent category:{}, name:{}, p1:{}, p2:{}, values:{} called", s, s2, s3, s4, linkedHashMap);
        }
    }
    
    public static void b() {
        final gc a = gc.a();
        if (a.b("endSession")) {
            a.h.a();
        }
    }
    
    public static void b(final Activity activity) {
        final gc a = gc.a();
        if (fz.a(activity, "onActivityStop: The given activity was null")) {
            fz.c("onActivityStop");
            d.c(activity);
            if (a.b("onActivityStop") && !d.b()) {
                a.h.a();
            }
        }
    }
}
