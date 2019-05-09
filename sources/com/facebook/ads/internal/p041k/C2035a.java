package com.facebook.ads.internal.p041k;

import com.facebook.ads.internal.p025w.p026b.C2585o;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.k.a */
public class C2035a extends C2034d {
    public C2035a(String str, String str2) {
        double b = C2585o.m6652b();
        String c = C2585o.m6653c();
        Map hashMap = new HashMap();
        hashMap.put(ParametersKeys.KEY, str);
        hashMap.put("value", str2);
        super(b, c, hashMap);
    }

    /* renamed from: a */
    public String mo5463a() {
        return "client_response";
    }
}
