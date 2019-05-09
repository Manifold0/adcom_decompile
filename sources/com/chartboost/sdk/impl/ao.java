package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ao {
    /* renamed from: a */
    HttpURLConnection m3406a(ad<?> adVar) throws IOException {
        return (HttpURLConnection) new URL(adVar.f2982c).openConnection();
    }
}
