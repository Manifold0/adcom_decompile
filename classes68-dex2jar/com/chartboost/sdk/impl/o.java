// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.webkit.WebView;
import android.app.Application;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class o
{
    static List<String> a;
    static HashMap<String, p> b;
    
    static {
        o.a = new ArrayList<String>();
        o.b = new HashMap<String, p>();
    }
    
    public static String a(final HashSet<String> set) {
        final JSONArray jsonArray = new JSONArray();
        for (final String s : set) {
            if (o.b.get(s) != null) {
                jsonArray.put((Object)s);
            }
        }
        return jsonArray.toString();
    }
    
    public static void a() {
        o.a.clear();
        if (b()) {
            o.a.add("moat");
        }
    }
    
    public static void a(final Application application, final boolean b, final boolean b2, final boolean b3) {
        for (final String s : o.b.keySet()) {
            if (s.contains("moat")) {
                if (o.b.get(s) != null) {
                    o.b.get(s).b();
                }
                final r r = new r();
                r.a(application, b, b2, b3);
                o.b.put("moat", r);
            }
        }
    }
    
    public static void a(final WebView webView, final HashSet<String> set) {
        final Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            final p p2 = o.b.get(iterator.next());
            if (p2 != null) {
                p2.a(webView);
            }
        }
    }
    
    public static void a(final List<String> list) {
        for (final String s : list) {
            if (o.a.contains(s) && !o.b.containsKey(s)) {
                o.b.put(s, null);
            }
        }
    }
    
    static boolean b() {
        return true;
    }
    
    public static void c() {
        for (final p p : o.b.values()) {
            if (p != null) {
                p.a();
            }
        }
    }
    
    public static void d() {
        final Iterator<String> iterator = o.b.keySet().iterator();
        while (iterator.hasNext()) {
            final p p = o.b.get(iterator.next());
            if (p != null) {
                p.b();
            }
        }
    }
    
    public static JSONArray e() {
        final JSONArray jsonArray = new JSONArray();
        if (o.a != null) {
            final Iterator<String> iterator = o.a.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next());
            }
        }
        return jsonArray;
    }
    
    public static JSONArray f() {
        final JSONArray jsonArray = new JSONArray();
        if (o.b != null) {
            final Iterator<String> iterator = o.b.keySet().iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next());
            }
        }
        return jsonArray;
    }
}
