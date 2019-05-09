package com.facebook.ads.internal.p021o;

import android.support.annotation.Nullable;
import com.ironsource.sdk.constants.LocationConst;
import com.unity.purchasing.googleplay.Consts;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.o.a */
public class C2058a {
    /* renamed from: a */
    public static String f4614a = null;
    /* renamed from: b */
    private String f4615b;
    /* renamed from: c */
    private Map<String, Object> f4616c;
    /* renamed from: d */
    private int f4617d;
    /* renamed from: e */
    private String f4618e;

    public C2058a(String str, Map<String, Object> map, int i, String str2) {
        this.f4615b = str;
        this.f4616c = map;
        this.f4617d = i;
        this.f4618e = str2;
    }

    /* renamed from: a */
    public static C2058a m5020a(@Nullable Throwable th, @Nullable String str) {
        Map hashMap = new HashMap();
        if (th != null) {
            hashMap.put("ex", th.getClass().getSimpleName());
            hashMap.put("ex_msg", th.getMessage());
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String str2 = "error";
        if (str == null) {
            str = f4614a;
        }
        return new C2058a(str2, hashMap, currentTimeMillis, str);
    }

    /* renamed from: a */
    public JSONObject m5021a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f4615b);
            jSONObject.put("data", new JSONObject(this.f4616c));
            jSONObject.put(LocationConst.TIME, this.f4617d);
            jSONObject.put(Consts.INAPP_REQUEST_ID, this.f4618e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
