package com.facebook.ads.internal.p041k;

import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.k.b */
public class C2036b {
    /* renamed from: a */
    private final String f4540a;
    /* renamed from: b */
    private final Map<String, String> f4541b;
    /* renamed from: c */
    private final String f4542c;

    public C2036b(String str, Map<String, String> map) {
        this(str, map, false);
    }

    public C2036b(String str, Map<String, String> map, boolean z) {
        this.f4540a = str;
        this.f4541b = map;
        this.f4542c = z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    /* renamed from: a */
    public Map<String, String> m4931a() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("stacktrace", this.f4540a);
        hashMap.put("caught_exception", this.f4542c);
        hashMap.putAll(this.f4541b);
        return hashMap;
    }
}
