package com.kongregate.p000o.p002g;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.google.gson.Gson;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0568p;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p007d.C0635b;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* renamed from: com.kongregate.o.g.a */
public class C0640a {
    /* renamed from: a */
    public static final String f1003a = "application/json";
    /* renamed from: b */
    public static final String f1004b = "HttpClient/4.0";
    /* renamed from: c */
    private static volatile C0640a f1005c = null;
    /* renamed from: d */
    private final C0650f f1006d;
    /* renamed from: e */
    private final AtomicReference<String> f1007e = new AtomicReference(f1004b);

    /* renamed from: com.kongregate.o.g.a$1 */
    class C06391 implements HostnameVerifier {
        /* renamed from: a */
        final /* synthetic */ C0640a f1002a;

        C06391(C0640a c0640a) {
            this.f1002a = c0640a;
        }

        public boolean verify(String str, SSLSession sSLSession) {
            C0562j.m759c("Allowing insecure cerfiticate!");
            return true;
        }
    }

    /* renamed from: a */
    public static synchronized C0640a m1051a() {
        C0640a c0640a;
        synchronized (C0640a.class) {
            if (f1005c == null) {
                f1005c = new C0640a();
            }
            c0640a = f1005c;
        }
        return c0640a;
    }

    /* renamed from: b */
    public static synchronized C0640a m1055b() {
        C0640a c0640a;
        synchronized (C0640a.class) {
            c0640a = f1005c;
        }
        return c0640a;
    }

    /* renamed from: j */
    private static String m1057j() {
        return C0635b.m1001a().m1014c();
    }

    /* renamed from: k */
    private static String m1058k() {
        return "http://" + C0640a.m1057j();
    }

    /* renamed from: l */
    private static String m1059l() {
        if (C0635b.m1001a().m1022j()) {
            return "https://" + C0640a.m1057j();
        }
        return C0640a.m1058k();
    }

    private C0640a() {
        System.setProperty("http.keepAlive", "false");
        URI create = URI.create(C0640a.m1058k());
        boolean k = C0635b.m1001a().m1023k();
        this.f1006d = new C0650f(create.getHost());
        if (!k) {
            HttpsURLConnection.setDefaultHostnameVerifier(new C06391(this));
        }
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(C0649e.m1101a().getSocketFactory());
        } catch (Throwable e) {
            C0562j.m760c("IOException setting socket factory", e);
        } catch (Throwable e2) {
            C0562j.m760c("Exception setting socket factory", e2);
        }
    }

    /* renamed from: a */
    public static URI m1054a(String str, boolean z) {
        try {
            if (str.indexOf("http") == 0) {
                return URI.create(str);
            }
            return URI.create((z ? C0640a.m1059l() : C0640a.m1058k()) + str);
        } catch (Throwable e) {
            C0562j.m760c("Illegal URI: " + str, e);
            return null;
        }
    }

    /* renamed from: a */
    public String m1063a(String str) {
        return this.f1006d.m1113b(str);
    }

    /* renamed from: c */
    public String m1070c() {
        return m1063a(C0650f.f1036e);
    }

    /* renamed from: a */
    public void m1064a(long j, String str) {
        HttpCookie httpCookie = new HttpCookie(C0650f.f1034c, "api:3.0.5:" + String.valueOf(j) + ":android:" + VERSION.RELEASE + ":" + str);
        httpCookie.setDomain(C0635b.m1001a().m1014c());
        httpCookie.setPath("/");
        this.f1006d.m1111a(httpCookie, true);
        C0562j.m756b("API cookie set to: " + this.f1006d.m1113b(C0650f.f1034c));
    }

    /* renamed from: b */
    public void m1069b(String str) {
        this.f1007e.set(str);
        C0562j.m753a("User agent set to: " + str);
    }

    /* renamed from: a */
    public void m1065a(String str, String str2) {
        this.f1006d.m1110a(this.f1006d.m1106a(C0650f.f1032a, str));
        this.f1006d.m1110a(this.f1006d.m1107a(C0650f.f1033b, str2, true));
    }

    /* renamed from: c */
    public void m1071c(String str) {
        this.f1006d.m1117c(str);
    }

    /* renamed from: d */
    public void m1072d() {
        this.f1006d.m1108a();
    }

    /* renamed from: e */
    public void m1075e() {
        Editor edit = C0558g.m698e().edit();
        edit.remove("session_backup");
        edit.remove("secure_backup");
        edit.commit();
        this.f1006d.m1114b();
    }

    /* renamed from: f */
    public String m1077f() {
        return this.f1006d.m1116c();
    }

    /* renamed from: g */
    public void m1078g() {
        Editor edit = C0558g.m698e().edit();
        edit.remove("session_backup");
        edit.remove("secure_backup");
        edit.commit();
        this.f1006d.m1118d();
    }

    /* renamed from: d */
    public void m1073d(String str) {
        this.f1006d.m1119d(str);
    }

    /* renamed from: h */
    public boolean m1079h() {
        return StringUtils.m584b(this.f1006d.m1113b(C0650f.f1033b));
    }

    /* renamed from: a */
    public void m1066a(boolean z) {
        CharSequence b = this.f1006d.m1113b(C0650f.f1032a);
        CharSequence b2 = this.f1006d.m1113b(C0650f.f1033b);
        if (StringUtils.m584b(b)) {
            C0562j.m753a("Session cookie found in WebView");
            Editor edit = C0558g.m698e().edit();
            if (z) {
                C0562j.m759c("User is a guest but we have a session cookie, removing all cookies");
                edit.remove("session_backup");
                edit.remove("secure_backup");
                m1078g();
            } else {
                C0562j.m753a("Backing up session cookie");
                edit.putString("session_backup", b);
                if (StringUtils.m584b(b2)) {
                    edit.putString("secure_backup", b2);
                }
            }
            edit.commit();
        } else if (!z) {
            String string = C0558g.m698e().getString("session_backup", null);
            if (StringUtils.m584b((CharSequence) string)) {
                C0562j.m759c("Session cookie not found, restoring from backup");
                this.f1006d.m1110a(this.f1006d.m1106a(C0650f.f1032a, string));
                b = C0558g.m698e().getString("secure_backup", null);
                if (StringUtils.m580a(b2) && StringUtils.m584b(b)) {
                    C0562j.m753a("Restoring secure cookie from backup");
                    this.f1006d.m1110a(this.f1006d.m1107a(C0650f.f1032a, b, true));
                }
            }
        }
    }

    /* renamed from: i */
    public void m1080i() {
        this.f1006d.m1112a(true);
    }

    /* renamed from: e */
    public C0646c m1074e(String str) {
        return m1056b(str, false);
    }

    /* renamed from: f */
    public C0646c m1076f(String str) {
        return m1056b(str, true);
    }

    /* renamed from: b */
    private C0646c m1056b(String str, boolean z) {
        if (C0640a.m1054a(str, z) == null) {
            return new C0646c(C0568p.ERROR_HTTP, null, 404);
        }
        return m1052a(C0640a.m1054a(str, z).toString(), "GET", null);
    }

    /* renamed from: a */
    public C0646c m1061a(String str, Map<String, Object> map) {
        return m1052a(C0640a.m1054a(str, true).toString(), "POST", map);
    }

    /* renamed from: a */
    public C0646c m1062a(String str, Map<String, Object> map, String str2, Map<String, String> map2) {
        return m1053a(C0640a.m1054a(str, true).toString(), "POST", map, str2, map2);
    }

    /* renamed from: b */
    public C0646c m1067b(String str, Map<String, Object> map) {
        return m1052a(C0640a.m1054a(str, false).toString(), "POST", map);
    }

    /* renamed from: b */
    public C0646c m1068b(String str, Map<String, Object> map, String str2, Map<String, String> map2) {
        return m1053a(C0640a.m1054a(str, false).toString(), "POST", map, str2, map2);
    }

    /* renamed from: m */
    private void m1060m() {
        CookieHandler cookieHandler = CookieManager.getDefault();
        if (cookieHandler instanceof CookieManager) {
            CookieStore cookieStore = ((CookieManager) cookieHandler).getCookieStore();
            if (cookieStore != null) {
                List<HttpCookie> linkedList = new LinkedList();
                for (HttpCookie httpCookie : cookieStore.getCookies()) {
                    if (StringUtils.m585b(C0640a.m1057j(), httpCookie.getDomain())) {
                        linkedList.add(httpCookie);
                    }
                }
                for (HttpCookie httpCookie2 : linkedList) {
                    URI create = URI.create((httpCookie2.getSecure() ? "https://" : "http://") + httpCookie2.getDomain() + httpCookie2.getPath());
                    if (cookieStore.remove(create, httpCookie2)) {
                        C0562j.m753a("cleanDefaultCookieStore: cookie removed: " + httpCookie2.getName() + " from domain " + create.toString());
                    } else {
                        C0562j.m753a("cleanDefaultCookieStore: cookie not removed: " + httpCookie2.getName() + " from domain " + create.toString());
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private C0646c m1052a(String str, String str2, Map<String, Object> map) {
        return m1053a(str, str2, map, "application/x-www-form-urlencoded", null);
    }

    /* renamed from: a */
    private C0646c m1053a(String str, String str2, Map<String, Object> map, String str3, Map<String, String> map2) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        C0646c c0646c;
        HttpURLConnection httpURLConnection2 = null;
        m1060m();
        try {
            String toJson;
            HttpURLConnection httpURLConnection3;
            Closeable dataOutputStream;
            InputStream inputStream;
            Closeable bufferedReader;
            StringBuilder stringBuilder;
            String readLine;
            int responseCode;
            byte[] bytes;
            Map headerFields;
            LinkedList linkedList;
            List list;
            Iterator it;
            C0646c c0646c2;
            String str4 = "";
            if (map != null) {
                if (str3 == f1003a) {
                    toJson = new Gson().toJson(new HashMap(map));
                    httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection3.setConnectTimeout(15000);
                    httpURLConnection3.setReadTimeout(15000);
                    httpURLConnection3.setRequestMethod(str2);
                    httpURLConnection3.setRequestProperty("Content-Type", str3);
                    httpURLConnection3.setRequestProperty("Content-Language", "en-US");
                    httpURLConnection3.setRequestProperty("User-Agent", (String) this.f1007e.get());
                    httpURLConnection3.setRequestProperty("Cookie", this.f1006d.m1120e());
                    if (map2 != null) {
                        for (String str5 : map2.keySet()) {
                            httpURLConnection3.setRequestProperty(str5, (String) map2.get(str5));
                        }
                    }
                    httpURLConnection3.setUseCaches(false);
                    httpURLConnection3.setDoInput(true);
                    httpURLConnection3.setDoOutput("POST".equals(str2));
                    httpURLConnection3.setRequestProperty("Connection", String.CLOSE);
                    if (httpURLConnection3.getDoOutput()) {
                        dataOutputStream = new DataOutputStream(httpURLConnection3.getOutputStream());
                        dataOutputStream.writeBytes(toJson);
                        dataOutputStream.flush();
                        C0558g.m674a(dataOutputStream);
                    }
                    inputStream = httpURLConnection3.getInputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
                    stringBuilder = new StringBuilder(1024);
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                        stringBuilder.append('\n');
                    }
                    C0558g.m674a(bufferedReader);
                    responseCode = httpURLConnection3.getResponseCode();
                    bytes = stringBuilder.toString().getBytes("UTF-8");
                    if (responseCode != 200) {
                        headerFields = httpURLConnection3.getHeaderFields();
                        if (headerFields != null) {
                            linkedList = new LinkedList();
                            for (String str52 : headerFields.keySet()) {
                                if ("Set-Cookie".equalsIgnoreCase(str52)) {
                                    list = (List) headerFields.get(str52);
                                    if (list != null) {
                                        linkedList.addAll(list);
                                    }
                                }
                            }
                            if (!linkedList.isEmpty()) {
                                it = linkedList.iterator();
                                while (it.hasNext()) {
                                    this.f1006d.m1109a((String) it.next());
                                }
                            }
                        }
                        c0646c2 = new C0646c(C0568p.SUCCESS, bytes, responseCode);
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        m1060m();
                        return c0646c2;
                    }
                    C0562j.m759c("HttpUrlConnection failed: " + responseCode);
                    C0562j.m753a("Connection parameters: " + toJson);
                    c0646c2 = new C0646c(C0568p.ERROR_HTTP, bytes, responseCode);
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    m1060m();
                    return c0646c2;
                }
                for (Entry entry : map.entrySet()) {
                    String str6;
                    if (StringUtils.m584b((CharSequence) entry.getKey()) && entry.getValue() != null && StringUtils.m584b(entry.getValue().toString())) {
                        str6 = str4 + ((String) entry.getKey()) + RequestParameters.EQUAL + URLEncoder.encode(entry.getValue().toString(), "UTF-8") + RequestParameters.AMPERSAND;
                    } else {
                        str6 = str4;
                    }
                    str4 = str6;
                }
            }
            toJson = str4;
            httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(15000);
                httpURLConnection3.setReadTimeout(15000);
                httpURLConnection3.setRequestMethod(str2);
                httpURLConnection3.setRequestProperty("Content-Type", str3);
                httpURLConnection3.setRequestProperty("Content-Language", "en-US");
                httpURLConnection3.setRequestProperty("User-Agent", (String) this.f1007e.get());
                httpURLConnection3.setRequestProperty("Cookie", this.f1006d.m1120e());
                if (map2 != null) {
                    for (String str522 : map2.keySet()) {
                        httpURLConnection3.setRequestProperty(str522, (String) map2.get(str522));
                    }
                }
                httpURLConnection3.setUseCaches(false);
                httpURLConnection3.setDoInput(true);
                httpURLConnection3.setDoOutput("POST".equals(str2));
                httpURLConnection3.setRequestProperty("Connection", String.CLOSE);
                if (httpURLConnection3.getDoOutput()) {
                    dataOutputStream = new DataOutputStream(httpURLConnection3.getOutputStream());
                    dataOutputStream.writeBytes(toJson);
                    dataOutputStream.flush();
                    C0558g.m674a(dataOutputStream);
                }
                inputStream = httpURLConnection3.getInputStream();
            } catch (Throwable e) {
                C0562j.m760c("HTTP Post error", e);
                inputStream = httpURLConnection3.getErrorStream();
                if (inputStream == null) {
                    throw e;
                }
            } catch (Throwable e2) {
                Throwable th2 = e2;
                httpURLConnection = httpURLConnection3;
                th = th2;
                try {
                    C0562j.m762d("IOException in executeUrlRequest", th);
                    c0646c = new C0646c(C0568p.ERROR_NETWORK);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    m1060m();
                    return c0646c;
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    m1060m();
                    throw th;
                }
            } catch (Throwable e22) {
                httpURLConnection2 = httpURLConnection3;
                th = e22;
                try {
                    C0562j.m762d("NumberFormatException in executeUrlRequest", th);
                    c0646c = new C0646c(C0568p.ERROR_HTTP);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    m1060m();
                    return c0646c;
                } catch (Throwable th4) {
                    th = th4;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    m1060m();
                    throw th;
                }
            } catch (Throwable e222) {
                httpURLConnection2 = httpURLConnection3;
                th = e222;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                m1060m();
                throw th;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
            stringBuilder = new StringBuilder(1024);
            while (true) {
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                    break;
                }
                stringBuilder.append(readLine);
                stringBuilder.append('\n');
            }
            C0558g.m674a(bufferedReader);
            responseCode = httpURLConnection3.getResponseCode();
            bytes = stringBuilder.toString().getBytes("UTF-8");
            if (responseCode != 200) {
                C0562j.m759c("HttpUrlConnection failed: " + responseCode);
                C0562j.m753a("Connection parameters: " + toJson);
                c0646c2 = new C0646c(C0568p.ERROR_HTTP, bytes, responseCode);
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                m1060m();
                return c0646c2;
            }
            headerFields = httpURLConnection3.getHeaderFields();
            if (headerFields != null) {
                linkedList = new LinkedList();
                for (String str5222 : headerFields.keySet()) {
                    if ("Set-Cookie".equalsIgnoreCase(str5222)) {
                        list = (List) headerFields.get(str5222);
                        if (list != null) {
                            linkedList.addAll(list);
                        }
                    }
                }
                if (linkedList.isEmpty()) {
                    it = linkedList.iterator();
                    while (it.hasNext()) {
                        this.f1006d.m1109a((String) it.next());
                    }
                }
            }
            c0646c2 = new C0646c(C0568p.SUCCESS, bytes, responseCode);
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
            m1060m();
            return c0646c2;
        } catch (IOException e3) {
            th = e3;
            httpURLConnection = null;
            C0562j.m762d("IOException in executeUrlRequest", th);
            c0646c = new C0646c(C0568p.ERROR_NETWORK);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            m1060m();
            return c0646c;
        } catch (NumberFormatException e4) {
            th = e4;
            C0562j.m762d("NumberFormatException in executeUrlRequest", th);
            c0646c = new C0646c(C0568p.ERROR_HTTP);
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            m1060m();
            return c0646c;
        }
    }
}
