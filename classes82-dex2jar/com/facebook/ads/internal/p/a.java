// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.p;

import android.util.Log;
import com.facebook.ads.internal.w.e.d;
import java.util.Map;
import com.facebook.ads.internal.v.a.p;
import android.annotation.SuppressLint;
import java.util.UUID;
import android.app.Application;
import android.content.Context;
import java.util.LinkedHashMap;
import com.facebook.ads.internal.v.a.b;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a
{
    private static final String a;
    private static final AtomicBoolean b;
    private static b c;
    private static String d;
    private static LinkedHashMap<String, String> e;
    private static String f;
    
    static {
        a = a.class.getSimpleName();
        b = new AtomicBoolean();
    }
    
    public static String a(final int n) {
        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n / "bbbbbbbbbb".length(); ++i) {
            sb.append("bbbbbbbbbb");
        }
        return sb.toString();
    }
    
    @SuppressLint({ "CatchGeneralException" })
    public static void a(final Context context, final b c) {
        if (com.facebook.ads.internal.p.a.b.get() || !(context instanceof Application)) {
            return;
        }
        com.facebook.ads.internal.p.a.f = context.getPackageName();
        com.facebook.ads.internal.p.a.c = c;
        com.facebook.ads.internal.p.a.d = UUID.randomUUID().toString();
        com.facebook.ads.internal.p.a.b.compareAndSet(false, true);
    }
    
    @SuppressLint({ "CatchGeneralException" })
    public static void a(final Context context, String a, String a2) {
        final Object o = null;
        final Object o2 = null;
        if (!a.b.get() || !a.z(context) || Math.random() > a.B(context)) {
            return;
        }
        (a.e = new LinkedHashMap<String, String>()).put(com.facebook.ads.internal.p.b.a.toString(), (String)a2);
        a.e.put(com.facebook.ads.internal.p.b.b.toString(), "AN_ANDROID");
        a.e.put(com.facebook.ads.internal.p.b.c.toString(), (String)a);
        a.e.put(com.facebook.ads.internal.p.b.d.toString(), a.f);
        a.e.put(com.facebook.ads.internal.p.b.e.toString(), a.d);
        Object o3;
        a = (o3 = new p().a(a.e).a());
        if (a.C(context)) {
            o3 = a;
            if (Math.random() <= a.E(context)) {
                a2 = a(a.D(context));
                o3 = (String)a + "&" + com.facebook.ads.internal.p.b.f.toString() + "=" + (String)a2;
            }
        }
        a = o2;
        a2 = o;
        try {
            final Object o4 = a2 = (a = com.facebook.ads.internal.w.e.d.a(context));
            final String string = a.A(context) + "&" + (String)o3;
            if (string != null) {
                a = o4;
                a2 = o4;
                if (!string.isEmpty()) {
                    a = o4;
                    a2 = o4;
                    ((com.facebook.ads.internal.v.a.a)o4).a(string, null, a.c);
                }
            }
        }
        catch (Exception ex) {
            a2 = a;
            Log.e(a.a, "Bot Detection Network Signal Error", (Throwable)ex);
        }
        finally {
            ((com.facebook.ads.internal.v.a.a)a2).b();
        }
    }
}
