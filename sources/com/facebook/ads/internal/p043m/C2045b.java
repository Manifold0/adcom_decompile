package com.facebook.ads.internal.p043m;

import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.m.b */
public class C2045b {
    /* renamed from: a */
    public String f4567a;
    /* renamed from: b */
    public String f4568b;
    /* renamed from: c */
    public String f4569c;
    /* renamed from: d */
    public Date f4570d;
    /* renamed from: e */
    public boolean f4571e;

    public C2045b(String str, String str2, String str3, Date date) {
        this.f4567a = str;
        this.f4568b = str2;
        this.f4569c = str3;
        this.f4570d = date;
        boolean z = (str2 == null || str3 == null) ? false : true;
        this.f4571e = z;
    }

    public C2045b(JSONObject jSONObject) {
        this(jSONObject.optString("url"), jSONObject.optString(ParametersKeys.KEY), jSONObject.optString("value"), new Date(jSONObject.optLong("expiration") * 1000));
    }

    /* renamed from: a */
    public static List<C2045b> m4967a(String str) {
        if (str == null) {
            return null;
        }
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(str);
        } catch (JSONException e) {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        List<C2045b> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject;
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e2) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                C2045b c2045b = new C2045b(jSONObject);
                if (c2045b != null) {
                    arrayList.add(c2045b);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public String m4968a() {
        Date date = this.f4570d;
        if (date == null) {
            date = new Date();
            date.setTime(date.getTime() + 3600000);
        }
        DateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(date);
    }

    /* renamed from: b */
    public boolean m4969b() {
        return (this.f4568b == null || this.f4569c == null || this.f4567a == null) ? false : true;
    }
}
