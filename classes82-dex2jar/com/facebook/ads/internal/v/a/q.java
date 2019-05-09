// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public interface q
{
    OutputStream a(final HttpURLConnection p0);
    
    HttpURLConnection a(final String p0);
    
    void a(final OutputStream p0, final byte[] p1);
    
    void a(final HttpURLConnection p0, final j p1, final String p2);
    
    boolean a(final m p0);
    
    byte[] a(final InputStream p0);
    
    InputStream b(final HttpURLConnection p0);
}
