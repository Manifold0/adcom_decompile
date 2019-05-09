package com.tapjoy.internal;

import com.kongregate.p000o.p002g.C0640a;
import com.tonyodev.fetch.FetchConst;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

public final class cj implements ci {
    /* renamed from: a */
    private final String f7286a;
    /* renamed from: b */
    private final URL f7287b;

    public cj(String str, URL url) {
        this.f7286a = str;
        this.f7287b = url;
    }

    /* renamed from: a */
    public final Object mo6207a(cf cfVar) {
        URL url = new URL(this.f7287b, cfVar.mo6338c());
        String b = cfVar.mo6334b();
        if ("GET".equals(b) || "DELETE".equals(b)) {
            Map e = cfVar.mo6336e();
            if (!e.isEmpty()) {
                url = new URL(url, url.getPath() + "?" + en.m7653a(e));
            }
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) em.m7652a(url);
        httpURLConnection.setRequestMethod(b);
        httpURLConnection.setRequestProperty("User-Agent", this.f7286a);
        for (Entry entry : cfVar.mo6206a().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), entry.getValue().toString());
        }
        if (!("GET".equals(b) || "DELETE".equals(b))) {
            if ("POST".equals(b) || "PUT".equals(b)) {
                String d = cfVar.mo6335d();
                if (d == null) {
                    en.m7654a(httpURLConnection, "application/x-www-form-urlencoded", en.m7653a(cfVar.mo6336e()), cp.f7292c);
                } else if (C0640a.f1003a.equals(d)) {
                    en.m7654a(httpURLConnection, "application/json; charset=utf-8", bm.m7204a(cfVar.mo6336e()), cp.f7292c);
                } else {
                    throw new IllegalArgumentException("Unknown content type: " + d);
                }
            }
            throw new IllegalArgumentException("Unknown method: " + b);
        }
        httpURLConnection.connect();
        switch (httpURLConnection.getResponseCode()) {
            case 200:
            case FetchConst.NETWORK_WIFI /*201*/:
            case 409:
                URI toURI;
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    toURI = httpURLConnection.getURL().toURI();
                } catch (URISyntaxException e2) {
                    toURI = null;
                }
                try {
                    Object a = cfVar.mo6205a(toURI, inputStream);
                    return a;
                } finally {
                    inputStream.close();
                }
            default:
                throw new IOException("Unexpected status code: " + httpURLConnection.getResponseCode());
        }
    }
}
