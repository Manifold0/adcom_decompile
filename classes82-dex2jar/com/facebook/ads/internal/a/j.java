// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.w.e.g;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import java.util.Map;
import android.net.Uri;

public class j extends h
{
    private static final String e;
    private final Uri f;
    private final Map<String, String> g;
    
    static {
        e = j.class.getSimpleName();
    }
    
    j(final Context context, final c c, final String s, final Uri f, final Map<String, String> g, final m m) {
        super(context, c, s, m);
        this.f = f;
        this.g = g;
    }
    
    @Nullable
    @Override
    public a b() {
        try {
            com.facebook.ads.internal.w.e.g.a(new g(), this.a, Uri.parse(this.f.getQueryParameter("link")), this.c);
            return null;
        }
        catch (Exception ex) {
            Log.d(j.e, "Failed to open link url: " + this.f.toString(), (Throwable)ex);
            return com.facebook.ads.internal.a.a.a;
        }
    }
    
    @Override
    void e() {
        this.a(this.g, this.b());
    }
}
