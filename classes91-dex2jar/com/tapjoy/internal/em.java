// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;

public final class em
{
    private static em a;
    
    static {
        em.a = new em();
    }
    
    public static InputStream a(final String s) {
        final URLConnection openConnection = new URL(s).openConnection();
        openConnection.connect();
        return openConnection.getInputStream();
    }
    
    public static URLConnection a(final URL url) {
        return url.openConnection();
    }
}
