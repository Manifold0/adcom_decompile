package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.google.android.gms.nearby.messages.Strategy;
import com.tonyodev.fetch.FetchConst;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

class ad {
    /* renamed from: a */
    private static final Object f2011a = new JSONObject();
    /* renamed from: b */
    private final AppLovinSdkImpl f2012b;
    /* renamed from: c */
    private final AppLovinLogger f2013c;

    ad(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2012b = appLovinSdkImpl;
        this.f2013c = appLovinSdkImpl.getLogger();
    }

    /* renamed from: a */
    private int m2215a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return AppLovinErrorCodes.NO_NETWORK;
        }
        if (th instanceof SocketTimeoutException) {
            return -102;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? FetchConst.ERROR_CONNECTION_TIMEOUT : -1;
        } else {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : 401;
        }
    }

    /* renamed from: a */
    private HttpURLConnection m2216a(String str, String str2, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.f2012b.get(ea.f2426w)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.f2012b.get(ea.f2428y)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    /* renamed from: a */
    private static void m2217a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    private <T> void m2218a(String str, int i, String str2, String str3, T t, af<T> afVar) throws JSONException {
        this.f2013c.mo4172d("ConnectionManager", i + " received from from \"" + str3 + "\": " + str);
        if (i < 200 || i >= Strategy.TTL_SECONDS_DEFAULT) {
            this.f2013c.mo4173e("ConnectionManager", i + " error received from \"" + str3 + "\"");
            afVar.mo4128a(i);
            return;
        }
        Object obj;
        Object obj2 = (str == null || str.length() <= 2) ? null : 1;
        if (!(i == AppLovinErrorCodes.NO_FILL || obj2 == null)) {
            try {
                if (!(t instanceof String)) {
                    if (t instanceof gf) {
                        str = gg.m2980a(str, this.f2012b);
                    } else if (t instanceof JSONObject) {
                        Object jSONObject = new JSONObject(str);
                    } else {
                        this.f2013c.mo4173e("ConnectionManager", "Unable to handle '" + t.getClass().getName() + "'");
                        T t2 = t;
                    }
                }
                obj = str;
            } catch (Throwable e) {
                this.f2013c.mo4174e("ConnectionManager", "Invalid JSON returned from \"" + str3 + "\"", e);
            } catch (Throwable e2) {
                this.f2013c.mo4174e("ConnectionManager", "Invalid XML returned from \"" + str3 + "\"", e2);
            }
        }
        afVar.mo4129a(obj, i);
    }

    /* renamed from: a */
    private void m2219a(String str, String str2, int i, long j) {
        this.f2013c.mo4175i("ConnectionManager", "Successful " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s over " + ag.m2234a(this.f2012b) + " to \"" + str2 + "\"");
    }

    /* renamed from: a */
    private void m2220a(String str, String str2, int i, long j, Throwable th) {
        this.f2013c.mo4174e("ConnectionManager", "Failed " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s over " + ag.m2234a(this.f2012b) + " to \"" + str2 + "\"", th);
    }

    /* renamed from: a */
    private static void m2221a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    void m2222a(String str, af<String> afVar) {
        m2224a(str, "GET", -1, null, "", true, afVar);
    }

    /* renamed from: a */
    <T> void m2223a(String str, String str2, int i, JSONObject jSONObject, T t, boolean z, ae aeVar, af<T> afVar) {
        InputStream inputStream;
        HttpURLConnection a;
        Throwable th;
        HttpURLConnection httpURLConnection;
        int a2;
        Throwable th2;
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No method specified");
        } else if (afVar == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (str.toLowerCase().startsWith("http")) {
            String replace;
            long currentTimeMillis;
            String jSONObject2;
            PrintWriter printWriter;
            String contentType;
            InputStream inputStream2;
            String a3;
            if (((Boolean) this.f2012b.get(ea.bS)).booleanValue()) {
                if (!str.contains("https://")) {
                    this.f2012b.getLogger().mo4178w("ConnectionManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
                    replace = str.replace("http://", "https://");
                    currentTimeMillis = System.currentTimeMillis();
                    inputStream = null;
                    this.f2013c.mo4175i("ConnectionManager", "Sending " + str2 + " request to \"" + replace + "\"...");
                    a = m2216a(replace, str2, i);
                    if (jSONObject != null) {
                        try {
                            jSONObject2 = jSONObject.toString();
                            this.f2013c.mo4172d("ConnectionManager", "Request to \"" + replace + "\" is " + jSONObject2);
                            a.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                            a.setDoOutput(true);
                            a.setFixedLengthStreamingMode(jSONObject2.getBytes(Charset.forName("UTF-8")).length);
                            printWriter = new PrintWriter(new OutputStreamWriter(a.getOutputStream(), "UTF8"));
                            printWriter.print(jSONObject2);
                            printWriter.close();
                        } catch (Throwable th3) {
                            th2 = th3;
                            m2217a(inputStream);
                            m2221a(a);
                            throw th2;
                        }
                    }
                    try {
                        a2 = a.getResponseCode();
                        contentType = a.getContentType();
                        if (a2 <= 0) {
                            m2219a(str2, replace, a2, currentTimeMillis);
                            inputStream2 = a.getInputStream();
                            if (z) {
                                if (aeVar != null) {
                                    aeVar.m2225a(System.currentTimeMillis() - currentTimeMillis);
                                }
                                afVar.mo4129a(t, a2);
                            } else {
                                try {
                                    a3 = ag.m2235a(inputStream2, this.f2012b);
                                    if (aeVar != null) {
                                        if (a3 != null) {
                                            aeVar.m2227b((long) a3.length());
                                        }
                                        aeVar.m2225a(System.currentTimeMillis() - currentTimeMillis);
                                    }
                                    m2218a(a3, a.getResponseCode(), contentType, replace, t, afVar);
                                } catch (MalformedURLException e) {
                                    if (z) {
                                        try {
                                            afVar.mo4128a(-901);
                                            m2217a(inputStream2);
                                            m2221a(a);
                                        } catch (Throwable th4) {
                                            inputStream = inputStream2;
                                            th2 = th4;
                                            m2217a(inputStream);
                                            m2221a(a);
                                            throw th2;
                                        }
                                    }
                                    afVar.mo4129a(t, -901);
                                    m2217a(inputStream2);
                                    m2221a(a);
                                }
                            }
                        }
                        m2220a(str2, replace, a2, currentTimeMillis, null);
                        afVar.mo4128a(a2);
                        inputStream2 = null;
                    } catch (MalformedURLException e2) {
                        inputStream2 = null;
                        if (z) {
                            afVar.mo4128a(-901);
                            m2217a(inputStream2);
                            m2221a(a);
                        }
                        afVar.mo4129a(t, -901);
                        m2217a(inputStream2);
                        m2221a(a);
                    }
                    m2217a(inputStream2);
                    m2221a(a);
                }
            }
            replace = str;
            currentTimeMillis = System.currentTimeMillis();
            inputStream = null;
            try {
                this.f2013c.mo4175i("ConnectionManager", "Sending " + str2 + " request to \"" + replace + "\"...");
                a = m2216a(replace, str2, i);
                if (jSONObject != null) {
                    jSONObject2 = jSONObject.toString();
                    this.f2013c.mo4172d("ConnectionManager", "Request to \"" + replace + "\" is " + jSONObject2);
                    a.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    a.setDoOutput(true);
                    a.setFixedLengthStreamingMode(jSONObject2.getBytes(Charset.forName("UTF-8")).length);
                    printWriter = new PrintWriter(new OutputStreamWriter(a.getOutputStream(), "UTF8"));
                    printWriter.print(jSONObject2);
                    printWriter.close();
                }
                a2 = a.getResponseCode();
                contentType = a.getContentType();
                if (a2 <= 0) {
                    m2220a(str2, replace, a2, currentTimeMillis, null);
                    afVar.mo4128a(a2);
                    inputStream2 = null;
                } else {
                    m2219a(str2, replace, a2, currentTimeMillis);
                    inputStream2 = a.getInputStream();
                    if (z) {
                        if (aeVar != null) {
                            aeVar.m2225a(System.currentTimeMillis() - currentTimeMillis);
                        }
                        afVar.mo4129a(t, a2);
                    } else {
                        a3 = ag.m2235a(inputStream2, this.f2012b);
                        if (aeVar != null) {
                            if (a3 != null) {
                                aeVar.m2227b((long) a3.length());
                            }
                            aeVar.m2225a(System.currentTimeMillis() - currentTimeMillis);
                        }
                        m2218a(a3, a.getResponseCode(), contentType, replace, t, afVar);
                    }
                }
                m2217a(inputStream2);
                m2221a(a);
            } catch (Throwable th42) {
                a = null;
                th2 = th42;
                m2217a(inputStream);
                m2221a(a);
                throw th2;
            }
        } else {
            this.f2013c.userError("ConnectionManager", "Requested postback submission to non HTTP endpoint " + str + "; skipping...");
            afVar.mo4128a(-900);
        }
    }

    /* renamed from: a */
    <T> void m2224a(String str, String str2, int i, JSONObject jSONObject, T t, boolean z, af<T> afVar) {
        m2223a(str, str2, i, jSONObject, t, z, null, afVar);
    }
}
