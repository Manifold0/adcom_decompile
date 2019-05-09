package com.kongregate.p000o.p002g;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/* renamed from: com.kongregate.o.g.f */
public class C0650f {
    /* renamed from: a */
    public static final String f1032a = "_kongregate_session";
    /* renamed from: b */
    public static final String f1033b = "kong_sec";
    /* renamed from: c */
    public static final String f1034c = "kongregateMobileApi";
    /* renamed from: d */
    public static final String f1035d = "m_pass";
    /* renamed from: e */
    public static final String f1036e = "kong_svid";
    /* renamed from: f */
    public static final List<String> f1037f = Arrays.asList(new String[]{f1032a, f1033b, "kong_flash_messages"});
    /* renamed from: g */
    private final List<String> f1038g = Arrays.asList(new String[]{f1032a, f1033b});
    /* renamed from: h */
    private final List<String> f1039h = Arrays.asList(new String[]{f1036e});
    /* renamed from: i */
    private final String f1040i = "; ";
    /* renamed from: j */
    private final CookieSyncManager f1041j = CookieSyncManager.getInstance();
    /* renamed from: k */
    private final CookieManager f1042k = CookieManager.getInstance();
    /* renamed from: l */
    private final String f1043l;
    /* renamed from: m */
    private final Set<HttpCookie> f1044m = new HashSet();

    public C0650f(String str) {
        this.f1043l = str;
        m1108a();
    }

    /* renamed from: a */
    public synchronized void m1108a() {
        C0562j.m753a("Reloading cookies from webview: " + this.f1043l);
        this.f1044m.clear();
        for (HttpCookie add : m1105g().values()) {
            this.f1044m.add(add);
        }
        C0562j.m753a("cookies loaded");
    }

    /* renamed from: a */
    public synchronized void m1110a(HttpCookie httpCookie) {
        m1111a(httpCookie, false);
    }

    /* renamed from: a */
    public synchronized void m1111a(HttpCookie httpCookie, boolean z) {
        if (httpCookie == null) {
            C0562j.m753a("Can't add a null cookie.");
        } else {
            C0562j.m753a("Cookie added: " + httpCookie.getName());
            this.f1044m.add(httpCookie);
            if (z) {
                this.f1042k.setCookie("https://" + this.f1043l, m1103c(httpCookie));
                this.f1041j.sync();
            }
        }
    }

    /* renamed from: a */
    public synchronized void m1109a(String str) {
        try {
            HttpCookie e = m1104e(str);
            if (e != null) {
                m1110a(e);
            }
        } catch (Throwable e2) {
            C0562j.m760c("Illegal cookie", e2);
        } catch (Throwable e22) {
            C0562j.m760c("NPE parsing cookies", e22);
        }
    }

    /* renamed from: e */
    private HttpCookie m1104e(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        String nextToken = stringTokenizer.nextToken();
        int indexOf = nextToken.indexOf(61);
        if (indexOf != -1) {
            HttpCookie httpCookie = new HttpCookie(nextToken.substring(0, indexOf).trim(), nextToken.substring(indexOf + 1));
            httpCookie.setDomain(this.f1043l);
            httpCookie.setPath("/");
            while (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
                int indexOf2 = nextToken.indexOf(61);
                if ((indexOf2 == -1 ? nextToken.trim() : nextToken.substring(0, indexOf2).trim()).equalsIgnoreCase("secure")) {
                    httpCookie.setSecure(true);
                    break;
                }
            }
            return httpCookie;
        }
        throw new IllegalArgumentException("Malformed cookie");
    }

    /* renamed from: b */
    public synchronized void m1115b(HttpCookie httpCookie) {
        if (!this.f1038g.contains(httpCookie.getName())) {
            this.f1044m.add(httpCookie);
        }
    }

    /* renamed from: g */
    private Map<String, HttpCookie> m1105g() {
        String cookie = this.f1042k.getCookie("https://" + this.f1043l);
        Map hashMap = new HashMap();
        if (cookie != null && cookie.length() > 0) {
            for (String str : cookie.split("; ")) {
                if (str != null) {
                    String[] split = str.split(RequestParameters.EQUAL);
                    if (split != null && split.length >= 2) {
                        HttpCookie a = m1106a(split[0], split[1]);
                        if (f1033b.equals(a.getName())) {
                            a.setSecure(true);
                        }
                        hashMap.put(a.getName(), a);
                    }
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public HttpCookie m1106a(String str, String str2) {
        return m1107a(str, str2, false);
    }

    /* renamed from: a */
    public HttpCookie m1107a(String str, String str2, boolean z) {
        HttpCookie httpCookie;
        Throwable e;
        try {
            httpCookie = new HttpCookie(str, str2);
            try {
                httpCookie.setDomain(this.f1043l);
                httpCookie.setPath("/");
                httpCookie.setSecure(z);
            } catch (IllegalArgumentException e2) {
                e = e2;
                C0562j.m760c("unable to build cookie. malformed name: " + str, e);
                return httpCookie;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            httpCookie = null;
            e = th;
            C0562j.m760c("unable to build cookie. malformed name: " + str, e);
            return httpCookie;
        }
        return httpCookie;
    }

    /* renamed from: b */
    public synchronized String m1113b(String str) {
        String value;
        for (HttpCookie httpCookie : m1121f()) {
            if (httpCookie.getName().equals(str)) {
                value = httpCookie.getValue();
                break;
            }
        }
        value = null;
        return value;
    }

    /* renamed from: a */
    public synchronized void m1112a(boolean z) {
        Map g = m1105g();
        for (HttpCookie httpCookie : m1121f()) {
            if (z || !g.containsKey(httpCookie.getName()) || this.f1039h.contains(httpCookie.getName())) {
                this.f1042k.setCookie("https://" + this.f1043l, m1103c(httpCookie));
            }
        }
        this.f1041j.sync();
    }

    /* renamed from: b */
    public synchronized void m1114b() {
        for (String str : f1037f) {
            this.f1042k.setCookie("https://" + this.f1043l, str + RequestParameters.EQUAL);
        }
        this.f1041j.sync();
        m1108a();
    }

    /* renamed from: c */
    public synchronized void m1117c(String str) {
        C0562j.m753a("setting shared cookies from shared storage: ");
        if (!StringUtils.m580a((CharSequence) str)) {
            for (String str2 : str.split("; ")) {
                String[] split = str2.split(RequestParameters.EQUAL);
                if (split.length == 2) {
                    try {
                        HttpCookie httpCookie = new HttpCookie(split[0], split[1]);
                        httpCookie.setDomain(this.f1043l);
                        httpCookie.setPath("/");
                        if (f1033b.equals(split[0])) {
                            httpCookie.setSecure(true);
                        }
                        this.f1042k.setCookie("https://" + this.f1043l, m1103c(httpCookie));
                    } catch (Throwable e) {
                        C0562j.m760c("malformed cookie: " + str2, e);
                    }
                } else if (split.length == 1) {
                    m1119d(split[0]);
                }
            }
            for (String str3 : f1037f) {
                if (!str.contains(str3)) {
                    m1119d(str3);
                }
            }
        }
        this.f1041j.sync();
        m1108a();
    }

    /* renamed from: c */
    public synchronized String m1116c() {
        StringBuffer stringBuffer;
        C0562j.m753a("getSharedKongregateCookies from webview cookie store");
        m1108a();
        stringBuffer = new StringBuffer();
        for (String str : f1037f) {
            String str2;
            String b = m1113b(str2);
            if (stringBuffer.length() > 0) {
                stringBuffer.append("; ");
            }
            StringBuilder append = new StringBuilder().append(str2).append(RequestParameters.EQUAL);
            if (b != null) {
                str2 = b;
            } else {
                str2 = "";
            }
            stringBuffer.append(append.append(str2).toString());
        }
        CharSequence b2 = m1113b(f1036e);
        if (!StringUtils.m580a(b2)) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append("; ");
            }
            stringBuffer.append("kong_svid=" + b2);
        }
        return stringBuffer.toString();
    }

    /* renamed from: d */
    public synchronized void m1119d(String str) {
        HttpCookie httpCookie = new HttpCookie(str, "");
        httpCookie.setDomain(this.f1043l);
        httpCookie.setPath("/");
        if (f1033b.equals(str)) {
            httpCookie.setSecure(true);
        }
        this.f1042k.setCookie("https://" + this.f1043l, m1103c(httpCookie));
        this.f1044m.remove(httpCookie);
        this.f1041j.sync();
    }

    /* renamed from: d */
    public synchronized void m1118d() {
        C0562j.m753a("Clearing all cookies");
        this.f1044m.clear();
        this.f1042k.removeAllCookie();
        this.f1042k.setCookie("https://" + this.f1043l, "");
        this.f1041j.sync();
    }

    /* renamed from: e */
    public synchronized String m1120e() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder(1024);
        for (HttpCookie httpCookie : m1121f()) {
            stringBuilder.append(httpCookie.getName()).append(RequestParameters.EQUAL).append(httpCookie.getValue()).append("; ");
        }
        return stringBuilder.toString();
    }

    /* renamed from: c */
    private String m1103c(HttpCookie httpCookie) {
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append(httpCookie.getName()).append(RequestParameters.EQUAL).append(httpCookie.getValue());
        stringBuilder.append("; path=").append(httpCookie.getPath());
        if (httpCookie.getSecure()) {
            stringBuilder.append("; secure");
        }
        if (this.f1038g.contains(httpCookie.getName())) {
            stringBuilder.append("; HttpOnly");
        }
        if (httpCookie.getMaxAge() > 0) {
            stringBuilder.append("; max-age=" + httpCookie.getMaxAge());
        }
        return stringBuilder.toString();
    }

    /* renamed from: f */
    protected Set<HttpCookie> m1121f() {
        return this.f1044m;
    }
}
