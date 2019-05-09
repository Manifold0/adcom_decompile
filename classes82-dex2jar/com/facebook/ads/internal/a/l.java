// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import com.facebook.ads.internal.w.e.g;
import android.util.Log;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import android.net.Uri;

public class l extends b
{
    private static final String d;
    private final Uri e;
    
    static {
        d = l.class.getSimpleName();
    }
    
    public l(final Context context, final c c, final String s, final Uri e) {
        super(context, c, s);
        this.e = e;
    }
    
    @Override
    public void a() {
        try {
            Log.w("REDIRECTACTION: ", this.e.toString());
            g.a(new g(), this.a, this.e, this.c);
        }
        catch (Exception ex) {
            Log.d(l.d, "Failed to open link url: " + this.e.toString(), (Throwable)ex);
        }
    }
}
