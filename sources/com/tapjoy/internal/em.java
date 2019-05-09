package com.tapjoy.internal;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public final class em {
    /* renamed from: a */
    private static em f7665a = new em();

    /* renamed from: a */
    public static InputStream m7651a(String str) {
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.connect();
        return openConnection.getInputStream();
    }

    /* renamed from: a */
    public static URLConnection m7652a(URL url) {
        return url.openConnection();
    }
}
