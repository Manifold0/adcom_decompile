// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.net.URI;
import java.io.InputStream;
import java.util.Iterator;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;

public final class cj implements ci
{
    private final String a;
    private final URL b;
    
    public cj(final String a, final URL b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final Object a(cf a) {
        final URL url = new URL(this.b, a.c());
        final String b = a.b();
        URL url2 = null;
        Label_0101: {
            if (!"GET".equals(b)) {
                url2 = url;
                if (!"DELETE".equals(b)) {
                    break Label_0101;
                }
            }
            final Map e = a.e();
            url2 = url;
            if (!e.isEmpty()) {
                url2 = new URL(url, url.getPath() + "?" + en.a(e));
            }
        }
        final HttpURLConnection httpURLConnection = (HttpURLConnection)em.a(url2);
        httpURLConnection.setRequestMethod(b);
        httpURLConnection.setRequestProperty("User-Agent", this.a);
        for (final Map.Entry<String, V> entry : a.a().entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue().toString());
        }
        if (!"GET".equals(b) && !"DELETE".equals(b)) {
            if (!"POST".equals(b) && !"PUT".equals(b)) {
                throw new IllegalArgumentException("Unknown method: " + b);
            }
            final String d = a.d();
            if (d == null) {
                en.a(httpURLConnection, "application/x-www-form-urlencoded", en.a(a.e()), cp.c);
            }
            else {
                if (!"application/json".equals(d)) {
                    throw new IllegalArgumentException("Unknown content type: " + d);
                }
                en.a(httpURLConnection, "application/json; charset=utf-8", bm.a((Object)a.e()), cp.c);
            }
        }
        httpURLConnection.connect();
        switch (httpURLConnection.getResponseCode()) {
            default: {
                throw new IOException("Unexpected status code: " + httpURLConnection.getResponseCode());
            }
            case 200:
            case 201:
            case 409: {
                final InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    final URI uri = httpURLConnection.getURL().toURI();
                    a = (cf)a.a(uri, inputStream);
                    return a;
                }
                catch (URISyntaxException ex) {
                    final URI uri = null;
                }
                finally {
                    inputStream.close();
                }
                break;
            }
        }
    }
}
