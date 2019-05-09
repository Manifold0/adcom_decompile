// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.n;

import java.util.Map;
import com.facebook.ads.internal.k.c;
import com.facebook.ads.internal.w.h.b;
import android.util.Log;
import android.content.Context;
import android.support.annotation.UiThread;

@UiThread
public final class a
{
    private static boolean a;
    
    public static void a(Context applicationContext) {
        synchronized (a.class) {
            if (com.facebook.ads.internal.i.a.a() == null) {
                applicationContext = applicationContext.getApplicationContext();
                com.facebook.ads.internal.i.a.a(applicationContext);
                c(applicationContext);
            }
        }
    }
    
    public static void b(final Context context) {
        synchronized (a.class) {
            Context context2;
            if ((context2 = com.facebook.ads.internal.i.a.a()) == null) {
                context2 = context.getApplicationContext();
                com.facebook.ads.internal.i.a.a(context2);
                c(context2);
                Log.e("FBAudienceNetwork", "You don't call AudienceNetworkAds.initialize(). Some functionality may not work properly.");
                com.facebook.ads.internal.w.h.a.a(context2, "api", b.p, new Exception("initialize() not called."));
            }
            if (com.facebook.ads.internal.n.a.a) {
                return;
            }
            Label_0085: {
                if (!com.facebook.ads.internal.r.a.p(context2)) {
                    break Label_0085;
                }
                try {
                    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new c(Thread.getDefaultUncaughtExceptionHandler(), context2, d.b(context2)));
                    com.facebook.ads.internal.n.a.a = true;
                }
                catch (SecurityException ex) {
                    Log.e("FBAudienceNetwork", "No permissions to set the default uncaught exception handler", (Throwable)ex);
                }
            }
        }
    }
    
    private static void c(final Context context) {
        synchronized (a.class) {
            if (com.facebook.ads.internal.w.h.a.a == null) {
                com.facebook.ads.internal.w.h.a.a = (com.facebook.ads.internal.w.h.a.a)new com.facebook.ads.internal.w.h.a.a() {
                    @Override
                    public Map<String, String> a() {
                        return com.facebook.ads.internal.n.d.b(context);
                    }
                    
                    @Override
                    public boolean b() {
                        return com.facebook.ads.internal.w.b.a.a();
                    }
                };
            }
            com.facebook.ads.internal.w.a.b.a(context);
        }
    }
}
