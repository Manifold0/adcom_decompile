// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.h;

import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.k.e;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.util.Log;
import android.content.Context;
import java.util.concurrent.Executors;
import java.util.HashSet;
import java.util.Set;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Executor;
import android.support.annotation.Nullable;
import android.support.annotation.AnyThread;

@AnyThread
public class a
{
    @Nullable
    public static a a;
    @VisibleForTesting
    static Executor b;
    public static boolean c;
    private static final Set<Integer> d;
    
    static {
        d = new HashSet<Integer>();
        com.facebook.ads.internal.w.h.a.b = Executors.newSingleThreadExecutor();
        com.facebook.ads.internal.w.h.a.c = false;
    }
    
    public static void a(final Context context, final String s, final int n, final Exception ex) {
        boolean b = false;
        try {
            synchronized (com.facebook.ads.internal.w.h.a.d) {
                if (!com.facebook.ads.internal.w.h.a.d.contains(n)) {
                    com.facebook.ads.internal.w.h.a.d.add(n);
                    b = true;
                }
                // monitorexit(a.d)
                if (b && a(context, s, n, Math.random())) {
                    c(context, s, n, ex);
                }
            }
        }
        catch (Throwable t) {
            a(t);
        }
    }
    
    public static void a(final Throwable t) {
        Log.e("FBAudienceNetwork", "Exception during logging debug event.", t);
        if (com.facebook.ads.internal.w.h.a.c) {
            throw new RuntimeException(t);
        }
    }
    
    @VisibleForTesting
    static boolean a(final Context context, final String s, final int n, final double n2) {
        double n3;
        if (com.facebook.ads.internal.r.a.s(context).contains(s + ":" + n)) {
            n3 = com.facebook.ads.internal.r.a.u(context) * com.facebook.ads.internal.r.a.t(context) / 10000.0;
        }
        else {
            n3 = com.facebook.ads.internal.r.a.u(context) / 100.0;
        }
        return n2 >= 1.0 - n3;
    }
    
    public static void b(final Context context, final String s, final int n, final Exception ex) {
        while (true) {
            if (context == null) {
                try {
                    a(new RuntimeException("Can't log Debug Event. Context is null."));
                    return;
                    com.facebook.ads.internal.i.a.a(context);
                    // iftrue(Label_0087:, !a.c)
                    final String string = "Debug crash because of event with subtype = " + s + ", subtypeCode = " + n;
                    // iftrue(Label_0087:, AdInternalSettings.d && n == b.aa)
                    throw new RuntimeException(string, ex);
                }
                catch (Throwable t) {
                    a(t);
                    return;
                }
                Label_0087: {
                    if (a(context, s, n, Math.random())) {
                        c(context, s, n, ex);
                    }
                }
                return;
            }
            continue;
        }
    }
    
    private static void c(final Context context, final String s, final int n, final Exception ex) {
        int n2;
        if (com.facebook.ads.internal.w.h.a.a != null && com.facebook.ads.internal.w.h.a.a.b()) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (n2 != 0) {
            Log.e("FBAudienceNetwork", "Debug Event with subtype = " + s + ", subtypeCode = " + n, (Throwable)ex);
        }
        com.facebook.ads.internal.w.h.a.b.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (true) {
                        try {
                            if (com.facebook.ads.internal.w.h.a.a != null) {
                                final Map<String, String> a = com.facebook.ads.internal.w.h.a.a.a();
                                a.put("subtype", s);
                                a.put("subtype_code", String.valueOf(n));
                                e.a(ex, context, a);
                                return;
                            }
                            if (com.facebook.ads.internal.w.h.a.c) {
                                throw new RuntimeException("Debug crash because sEnvironmentDataProvider not injected", ex);
                            }
                        }
                        catch (Throwable t) {
                            com.facebook.ads.internal.w.h.a.a(t);
                            return;
                        }
                        final Map<String, String> a = new HashMap<String, String>();
                        continue;
                    }
                }
            }
        });
    }
    
    public interface a
    {
        Map<String, String> a();
        
        boolean b();
    }
}
