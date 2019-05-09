// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.k;

import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.w.b.o;
import com.facebook.ads.internal.w.b.s;
import android.os.Process;
import android.support.annotation.Nullable;
import java.util.Map;
import android.content.Context;

public class c implements UncaughtExceptionHandler
{
    private final UncaughtExceptionHandler a;
    private final Context b;
    private final Map<String, String> c;
    
    public c(@Nullable final UncaughtExceptionHandler a, final Context context, final Map<String, String> c) {
        this.a = a;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.b = context.getApplicationContext();
        this.c = c;
    }
    
    private static void a() {
        while (true) {
            try {
                Process.killProcess(Process.myPid());
                try {
                    System.exit(10);
                }
                catch (Throwable t) {}
            }
            catch (Throwable t2) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void uncaughtException(final Thread t, final Throwable t2) {
        while (true) {
            try {
                final String a = s.a(t2);
                if (a != null && a.contains("com.facebook.ads")) {
                    final Map<String, String> a2 = new b(a, this.c).a();
                    a2.put("subtype", "crash");
                    a2.put("subtype_code", "0");
                    e.a(new d(o.b(), o.c(), a2), this.b);
                }
                if (!com.facebook.ads.internal.r.a.X(this.b) || !AdInternalSettings.d) {
                    if (this.a != null) {
                        this.a.uncaughtException((Thread)t, t2);
                        return;
                    }
                    a();
                }
                else {
                    try {
                        Log.e("FBAudienceNetwork", "Facebook Audience Network process crashed with exception: ", t2);
                        a();
                    }
                    catch (Throwable t) {}
                }
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
}
