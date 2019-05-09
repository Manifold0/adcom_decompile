package com.chartboost.sdk.impl;

import android.app.Application;
import android.webkit.WebView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.chartboost.sdk.impl.o */
public class C1450o {
    /* renamed from: a */
    static List<String> f3317a = new ArrayList();
    /* renamed from: b */
    static HashMap<String, C1451p> f3318b = new HashMap();

    /* renamed from: a */
    public static void m3610a() {
        f3317a.clear();
        if (C1450o.m3614b()) {
            f3317a.add("moat");
        }
    }

    /* renamed from: b */
    static boolean m3614b() {
        return true;
    }

    /* renamed from: a */
    public static void m3613a(List<String> list) {
        for (String str : list) {
            if (f3317a.contains(str) && !f3318b.containsKey(str)) {
                f3318b.put(str, null);
            }
        }
    }

    /* renamed from: a */
    public static void m3611a(Application application, boolean z, boolean z2, boolean z3) {
        for (String str : f3318b.keySet()) {
            if (str.contains("moat")) {
                if (f3318b.get(str) != null) {
                    ((C1451p) f3318b.get(str)).mo4315b();
                }
                C1452q c1453r = new C1453r();
                c1453r.mo4313a(application, z, z2, z3);
                f3318b.put("moat", c1453r);
            }
        }
    }

    /* renamed from: a */
    public static void m3612a(WebView webView, HashSet<String> hashSet) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            C1451p c1451p = (C1451p) f3318b.get((String) it.next());
            if (c1451p != null) {
                c1451p.mo4314a(webView);
            }
        }
    }

    /* renamed from: c */
    public static void m3615c() {
        for (C1451p c1451p : f3318b.values()) {
            if (c1451p != null) {
                c1451p.mo4312a();
            }
        }
    }

    /* renamed from: d */
    public static void m3616d() {
        for (String str : f3318b.keySet()) {
            C1451p c1451p = (C1451p) f3318b.get(str);
            if (c1451p != null) {
                c1451p.mo4315b();
            }
        }
    }

    /* renamed from: e */
    public static JSONArray m3617e() {
        JSONArray jSONArray = new JSONArray();
        if (f3317a != null) {
            for (String put : f3317a) {
                jSONArray.put(put);
            }
        }
        return jSONArray;
    }

    /* renamed from: f */
    public static JSONArray m3618f() {
        JSONArray jSONArray = new JSONArray();
        if (f3318b != null) {
            for (String put : f3318b.keySet()) {
                jSONArray.put(put);
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static String m3609a(HashSet<String> hashSet) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (((C1451p) f3318b.get(str)) != null) {
                jSONArray.put(str);
            }
        }
        return jSONArray.toString();
    }
}
