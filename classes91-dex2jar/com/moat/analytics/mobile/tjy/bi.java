// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.os.Build$VERSION;
import java.util.HashMap;
import android.util.DisplayMetrics;
import android.content.Context;
import java.util.Iterator;
import java.util.Map;
import android.graphics.Rect;
import android.util.Log;
import android.webkit.WebView;
import android.view.View;

class bi implements bh, m
{
    private View a;
    private final WebView b;
    private boolean c;
    private final l d;
    private final a e;
    private final ap f;
    private com.moat.analytics.mobile.tjy.base.functional.a g;
    
    bi(final View view, final WebView webView, final boolean b, final a a, final ap ap) {
        this(view, webView, b, new n(webView.getContext(), ap), a, ap);
    }
    
    bi(final View a, final WebView b, final boolean c, final l d, final a e, final ap f) {
        com.moat.analytics.mobile.tjy.base.asserts.a.a(a);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(b);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(e);
        com.moat.analytics.mobile.tjy.base.asserts.a.a(d);
        if (f.b()) {
            Log.d("MoatViewTracker", "In initialization method.");
        }
        this.e = e;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
        this.g = com.moat.analytics.mobile.tjy.base.functional.a.a();
    }
    
    private static String a(final Rect rect) {
        return String.valueOf(new StringBuilder("{\"x\":").append(rect.left).append(',').append('\"').append("y\":").append(rect.top).append(',').append('\"').append("w\":").append(rect.right - rect.left).append(',').append('\"').append("h\":").append(rect.bottom - rect.top).append('}'));
    }
    
    private static String a(final Map map, final boolean b) {
        final StringBuilder sb = new StringBuilder("{");
        for (final Map.Entry<String, V> entry : map.entrySet()) {
            final String s = entry.getKey();
            final String s2 = (String)entry.getValue();
            if (sb.length() > 1) {
                sb.append(',');
            }
            sb.append('\"').append(s).append('\"').append(':');
            if (!b) {
                sb.append(s2);
            }
            else {
                sb.append('\"').append(s2).append('\"');
            }
        }
        sb.append("}");
        return String.valueOf(sb);
    }
    
    private void a(final Map map, final String s, final Rect rect) {
        map.put(s, a(this.b(rect)));
    }
    
    private Rect b(final Rect rect) {
        final float density = this.j().density;
        if (density == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(rect.left / density), Math.round(rect.top / density), Math.round(rect.right / density), Math.round(rect.bottom / density));
    }
    
    private Rect c(final Rect rect) {
        Rect rect2;
        if (!this.a.getGlobalVisibleRect(rect2 = this.k())) {
            rect2 = this.k();
        }
        rect2.left = Math.min(Math.max(0, rect2.left), rect.right);
        rect2.right = Math.min(Math.max(0, rect2.right), rect.right);
        rect2.top = Math.min(Math.max(0, rect2.top), rect.bottom);
        rect2.bottom = Math.min(Math.max(0, rect2.bottom), rect.bottom);
        return rect2;
    }
    
    private String g() {
        if (this.g.c()) {
            return (String)this.g.b();
        }
        while (true) {
            try {
                final Context context = this.b.getContext();
                final String string = context.getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
                try {
                    this.g = com.moat.analytics.mobile.tjy.base.functional.a.a(string);
                    return string;
                }
                catch (Exception ex2) {}
                final Exception ex;
                com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
                return string;
            }
            catch (Exception ex) {
                final String string = "_unknown_";
                continue;
            }
            break;
        }
    }
    
    private boolean h() {
        return this.a.isShown() && !this.e.a();
    }
    
    private Rect i() {
        final DisplayMetrics j = this.j();
        return new Rect(0, 0, j.widthPixels, j.heightPixels);
    }
    
    private DisplayMetrics j() {
        return this.a.getContext().getResources().getDisplayMetrics();
    }
    
    private Rect k() {
        return new Rect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }
    
    @Override
    public String a() {
        int n = 0;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            final Rect i = this.i();
            final Rect c = this.c(i);
            final Rect e = this.e();
            this.a(hashMap, "screen", i);
            this.a(hashMap, "visible", c);
            this.a(hashMap, "maybe", c);
            this.a(hashMap, "view", e);
            if (this.h()) {
                n = 1;
            }
            hashMap.put("inFocus", String.valueOf(n));
            hashMap.put("dr", new StringBuilder().append(this.j().density).toString());
            return a(hashMap, false);
        }
        catch (Exception ex) {
            return "{}";
        }
    }
    
    @Override
    public void a(final View a) {
        if (this.f.b()) {
            final StringBuilder sb = new StringBuilder("changing view to ");
            String string;
            if (a != null) {
                string = a.getClass().getSimpleName() + "@" + a.hashCode();
            }
            else {
                string = "null";
            }
            Log.d("MoatViewTracker", sb.append(string).toString());
        }
        this.a = a;
    }
    
    @Override
    public String b() {
        try {
            return a(this.f(), true);
        }
        catch (Exception ex) {
            return "{}";
        }
    }
    
    @Override
    public boolean c() {
        if (this.f.b()) {
            Log.d("MoatViewTracker", "Attempting bridge installation.");
        }
        final boolean a = this.d.a(this.b, this);
        if (this.f.b()) {
            final StringBuilder sb = new StringBuilder("Bridge ");
            String s;
            if (a) {
                s = "";
            }
            else {
                s = "not ";
            }
            Log.d("MoatViewTracker", sb.append(s).append("installed.").toString());
        }
        return a;
    }
    
    @Override
    public void d() {
        this.d.a();
    }
    
    @Override
    public Rect e() {
        final int[] array2;
        final int[] array = array2 = new int[2];
        array2[1] = (array2[0] = Integer.MAX_VALUE);
        this.a.getLocationInWindow(array);
        final int n = array[0];
        final int n2 = array[1];
        return new Rect(n, n2, this.a.getWidth() + n, this.a.getHeight() + n2);
    }
    
    public Map f() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final String g = this.g();
        final String string = Integer.toString(Build$VERSION.SDK_INT);
        String s;
        if (this.c) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("versionHash", "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
        hashMap.put("appName", g);
        hashMap.put("namespace", "TJY");
        hashMap.put("version", "1.7.10");
        hashMap.put("deviceOS", string);
        hashMap.put("isNative", s);
        return hashMap;
    }
}
