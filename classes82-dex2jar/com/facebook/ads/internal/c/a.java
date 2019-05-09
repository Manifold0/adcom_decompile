// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import com.facebook.ads.AdSettings;
import android.util.Log;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.Locale;
import com.facebook.ads.internal.b.e;
import android.content.Context;
import java.util.HashMap;
import android.support.annotation.UiThread;

@UiThread
public final class a
{
    private static final HashMap<a, a> d;
    a a;
    private final b b;
    private final Context c;
    
    static {
        (d = new HashMap<a, a>()).put(a.a, a.b);
        a.d.put(a.b, a.c);
        a.d.put(a.c, a.d);
        a.d.put(a.d, a.e);
        a.d.put(a.e, a.b);
        a.d.put(a.f, a.b);
        a.d.put(a.g, a.b);
    }
    
    a(final Context c, final b b) {
        this.a = com.facebook.ads.internal.c.a.a.a;
        this.c = c;
        this.b = b;
    }
    
    public void a(final a a) {
        if (!a.ab(this.c)) {
            this.a = a;
            return;
        }
        if (a.equals(a.f) || a.equals(a.g)) {
            this.a = a;
            return;
        }
        if (!a.equals(a.d.get(this.a))) {
            com.facebook.ads.internal.w.h.a.b(this.c, "api", com.facebook.ads.internal.w.h.b.k, new Exception("Wrong internal transition form " + this.a + " to " + a));
        }
        this.a = a;
    }
    
    public boolean a(final a a, String format) {
        if (a.equals(a.d.get(this.a))) {
            this.a = a;
            return false;
        }
        if (!a.ab(this.c)) {
            return false;
        }
        final AdSettings.IntegrationErrorMode a2 = e.a(this.c);
        format = String.format(Locale.US, AdErrorType.INCORRECT_STATE_ERROR.getDefaultErrorMessage(), format, this.a);
        switch (a$1.a[a2.ordinal()]) {
            default: {
                Log.e("FBAudienceNetwork", format);
                return true;
            }
            case 1: {
                throw new IllegalStateException(format + ". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
            }
            case 2: {
                this.b.d();
                this.b.a(10, AdErrorType.INCORRECT_STATE_ERROR, format);
                Log.e("FBAudienceNetwork", format);
                com.facebook.ads.internal.w.h.a.b(this.c, "api", com.facebook.ads.internal.w.h.b.l, new Exception(format));
                return true;
            }
        }
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d, 
        e, 
        f, 
        g;
    }
}
