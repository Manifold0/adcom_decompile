package com.facebook.ads.internal.p046v.p047a;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.v.a.g */
public class C2143g implements C2142r {
    /* renamed from: a */
    private void m5485a(Map<String, List<String>> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                for (String str2 : (List) map.get(str)) {
                    mo5516a(str + ":" + str2);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo5515a(C2150n c2150n) {
        if (c2150n != null) {
            mo5516a("=== HTTP Response ===");
            mo5516a("Receive url: " + c2150n.m5502b());
            mo5516a("Status: " + c2150n.m5501a());
            m5485a(c2150n.m5503c());
            mo5516a("Content:\n" + c2150n.m5505e());
        }
    }

    /* renamed from: a */
    public void mo5516a(String str) {
        System.out.println(str);
    }

    /* renamed from: a */
    public void mo5517a(HttpURLConnection httpURLConnection, Object obj) {
        mo5516a("=== HTTP Request ===");
        mo5516a(httpURLConnection.getRequestMethod() + " " + httpURLConnection.getURL().toString());
        if (obj instanceof String) {
            mo5516a("Content: " + ((String) obj));
        }
        m5485a(httpURLConnection.getRequestProperties());
    }

    /* renamed from: a */
    public boolean mo5518a() {
        return AdInternalSettings.isDebugBuild();
    }
}
