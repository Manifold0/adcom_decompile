package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.fm.C2928a;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public final class fb extends fm {
    /* renamed from: a */
    static final Map f7717a = Collections.unmodifiableMap(new HashMap());
    /* renamed from: c */
    private final C2928a f7718c = m7707a("BuildConfig");
    /* renamed from: d */
    private final C2928a f7719d = m7707a("ServerFinal");
    /* renamed from: e */
    private final C2928a f7720e = m7707a("AppRuntime");
    /* renamed from: f */
    private final C2928a f7721f = m7707a("ConnectFlags");
    /* renamed from: g */
    private final C2928a f7722g = m7707a("ServerDefault");

    fb() {
        C2928a a = m7707a("SDKDefault");
        if (!"".isEmpty()) {
            try {
                this.f7718c.f7790b = bs.m7244b("").m7257d();
            } catch (Throwable e) {
                throw new Error("BuildConfig.TJC_CONFIGURATION malformed", e);
            }
        }
        Map hashMap = new HashMap();
        hashMap.put("placement_request_content_retry_timeout", Integer.valueOf(-1));
        hashMap.put("placement_request_content_retry_backoff", Arrays.asList(new Number[]{Long.valueOf(0), Long.valueOf(500), Long.valueOf(TapjoyConstants.TIMER_INCREMENT), Double.valueOf(2.0d)}));
        a.f7790b = hashMap;
    }

    /* renamed from: a */
    public final void m7713a(Map map) {
        Map map2;
        Map map3 = null;
        if (map != null) {
            map2 = (Map) map.get("final");
            map3 = (Map) map.get("default");
        } else {
            map2 = null;
        }
        this.f7719d.f7790b = map2;
        this.f7722g.f7790b = map3;
        setChanged();
    }

    /* renamed from: a */
    public final void m7712a(Hashtable hashtable) {
        Map hashMap = new HashMap();
        for (Entry entry : hashtable.entrySet()) {
            Object obj = (String) f7717a.get(entry.getKey());
            if (obj == null) {
                obj = (String) entry.getKey();
            }
            this.f7720e.f7790b.remove(obj);
            hashMap.put(obj, entry.getValue());
        }
        this.f7721f.f7790b = hashMap;
        setChanged();
    }
}
