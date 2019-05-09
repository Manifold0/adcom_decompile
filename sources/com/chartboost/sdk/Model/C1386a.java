package com.chartboost.sdk.Model;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.kongregate.android.internal.util.C0558g;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Model.a */
public class C1386a {
    /* renamed from: a */
    public final JSONObject f2729a;
    /* renamed from: b */
    public final int f2730b;
    /* renamed from: c */
    public final Map<String, C1387b> f2731c = new HashMap();
    /* renamed from: d */
    public final Map<String, String> f2732d = new HashMap();
    /* renamed from: e */
    public final String f2733e;
    /* renamed from: f */
    public final String f2734f;
    /* renamed from: g */
    public final String f2735g;
    /* renamed from: h */
    public final String f2736h;
    /* renamed from: i */
    public final String f2737i;
    /* renamed from: j */
    public final String f2738j;
    /* renamed from: k */
    public final int f2739k;
    /* renamed from: l */
    public final String f2740l;
    /* renamed from: m */
    public final String f2741m;
    /* renamed from: n */
    public final Map<String, List<String>> f2742n = new HashMap();
    /* renamed from: o */
    public final int f2743o;
    /* renamed from: p */
    public final String f2744p;
    /* renamed from: q */
    public final String f2745q;
    /* renamed from: r */
    public final C1387b f2746r;
    /* renamed from: s */
    public final HashSet<String> f2747s = new HashSet();

    public C1386a(int i, JSONObject jSONObject, boolean z) throws JSONException {
        int i2 = 0;
        this.f2730b = i;
        this.f2729a = jSONObject;
        this.f2734f = jSONObject.getString("ad_id");
        this.f2735g = jSONObject.getString("cgn");
        this.f2736h = jSONObject.getString("creative");
        this.f2737i = jSONObject.optString("deep-link");
        this.f2738j = jSONObject.getString("link");
        this.f2741m = jSONObject.getString("to");
        this.f2743o = jSONObject.optInt("animation");
        this.f2744p = jSONObject.optString("media-type");
        this.f2745q = jSONObject.optString("name");
        String str;
        String string;
        if (i == 1) {
            int intValue;
            str = "";
            JSONObject jSONObject2 = jSONObject.getJSONObject(ParametersKeys.WEB_VIEW);
            JSONArray jSONArray = jSONObject2.getJSONArray(MessengerShareContentUtility.ELEMENTS);
            int i3 = 0;
            int i4 = 0;
            while (i3 < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i3);
                String string2 = jSONObject3.getString("name");
                Object optString = jSONObject3.optString("param");
                String string3 = jSONObject3.getString("type");
                string = jSONObject3.getString("value");
                if (string3.equals("param")) {
                    this.f2732d.put(optString, string);
                    if (string2.equals("reward_amount")) {
                        intValue = Integer.valueOf(string).intValue();
                    } else {
                        if (string2.equals("reward_currency")) {
                            str = string;
                            intValue = i4;
                        }
                        intValue = i4;
                    }
                } else {
                    if (string3.equals(String.HTML) && optString.isEmpty()) {
                        optString = "body";
                    } else if (optString.isEmpty()) {
                        String str2 = string2;
                    }
                    this.f2731c.put(optString, new C1387b(string3, string2, string));
                    intValue = i4;
                }
                i3++;
                i4 = intValue;
            }
            this.f2739k = i4;
            this.f2740l = str;
            this.f2746r = (C1387b) this.f2731c.get("body");
            if (this.f2746r == null) {
                throw new RuntimeException("WebView AdUnit does not have a template html body asset");
            }
            this.f2733e = jSONObject2.getString("template");
            JSONObject optJSONObject = jSONObject.optJSONObject(EventEntry.TABLE_NAME);
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    str = (String) keys.next();
                    JSONArray jSONArray2 = optJSONObject.getJSONArray(str);
                    List arrayList = new ArrayList();
                    for (intValue = 0; intValue < jSONArray2.length(); intValue++) {
                        arrayList.add(jSONArray2.getString(intValue));
                    }
                    this.f2742n.put(str, arrayList);
                }
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("certification_providers");
            if (optJSONArray != null) {
                while (i2 < optJSONArray.length()) {
                    this.f2747s.add(optJSONArray.getString(i2));
                    i2++;
                }
                return;
            }
            return;
        }
        if (z) {
            str = jSONObject.getJSONObject(C0558g.f746a).getString("lg");
            this.f2731c.put("lg", new C1387b("inPlayIcons", str.substring(str.lastIndexOf("/") + 1), str));
            this.f2739k = 0;
            this.f2740l = "";
        } else {
            JSONObject jSONObject4 = jSONObject.getJSONObject("assets");
            Iterator keys2 = jSONObject4.keys();
            while (keys2.hasNext()) {
                str = (String) keys2.next();
                JSONObject jSONObject5 = jSONObject4.getJSONObject(str);
                string = (str.equals("video-portrait") || str.equals("video-landscape")) ? "videos" : "images";
                String optString2 = jSONObject5.optString("id", null);
                if (optString2 == null) {
                    optString2 = jSONObject5.getString("checksum") + ".png";
                }
                this.f2731c.put(str, new C1387b(string, optString2, jSONObject5.getString("url")));
            }
            this.f2739k = jSONObject.optInt("reward");
            this.f2740l = jSONObject.optString("currency-name");
        }
        this.f2746r = null;
        this.f2733e = "";
    }
}
