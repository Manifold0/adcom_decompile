// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class g implements r
{
    private void a(final Map<String, List<String>> map) {
        if (map != null) {
            for (final String s : map.keySet()) {
                final Iterator<String> iterator2 = map.get(s).iterator();
                while (iterator2.hasNext()) {
                    this.a(s + ":" + iterator2.next());
                }
            }
        }
    }
    
    @Override
    public void a(final n n) {
        if (n != null) {
            this.a("=== HTTP Response ===");
            this.a("Receive url: " + n.b());
            this.a("Status: " + n.a());
            this.a(n.c());
            this.a("Content:\n" + n.e());
        }
    }
    
    @Override
    public void a(final String s) {
        System.out.println(s);
    }
    
    @Override
    public void a(final HttpURLConnection httpURLConnection, final Object o) {
        this.a("=== HTTP Request ===");
        this.a(httpURLConnection.getRequestMethod() + " " + httpURLConnection.getURL().toString());
        if (o instanceof String) {
            this.a("Content: " + (String)o);
        }
        this.a(httpURLConnection.getRequestProperties());
    }
    
    @Override
    public boolean a() {
        return AdInternalSettings.isDebugBuild();
    }
}
