// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import com.facebook.ads.internal.s.c;
import android.content.Context;
import java.util.Map;
import android.net.Uri;

public class i extends h
{
    private final Uri e;
    private Map<String, String> f;
    
    i(final Context context, final c c, final String s, final Uri e, final Map<String, String> f) {
        super(context, c, s, null);
        this.e = e;
        this.f = f;
    }
    
    public void a(final Map<String, String> map) {
        this.f.putAll(map);
    }
    
    public Uri c() {
        return Uri.parse(this.e.getQueryParameter("link"));
    }
    
    @Override
    void e() {
        this.a(this.f, null);
    }
}
