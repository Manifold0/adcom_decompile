// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class ao
{
    HttpURLConnection a(final ad<?> ad) throws IOException {
        return (HttpURLConnection)new URL(ad.c).openConnection();
    }
}
