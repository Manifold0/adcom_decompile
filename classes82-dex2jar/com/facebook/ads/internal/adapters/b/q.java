// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import com.facebook.ads.internal.o.c;
import org.json.JSONObject;

public class q extends a
{
    private final String a;
    private final m b;
    private final e c;
    private final i d;
    private final b e;
    private final d f;
    private final j g;
    private final String h;
    private boolean i;
    
    private q(final String a, final m b, final e c, final i d, final b e, final d f, final j g, final String h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public static q a(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = null;
        final m.a a = new m.a().a(jsonObject.optString("advertiser_name"));
        String optString;
        if (jsonObject.optJSONObject("icon") != null) {
            optString = jsonObject.optJSONObject("icon").optString("url");
        }
        else {
            optString = "";
        }
        final m.a c = a.b(optString).c(jsonObject.optString("ad_choices_link_url"));
        final JSONObject optJSONObject = jsonObject.optJSONObject("generic_text");
        String optString2;
        if (optJSONObject == null) {
            optString2 = "Sponsored";
        }
        else {
            optString2 = optJSONObject.optString("sponsored", "Sponsored");
        }
        final m a2 = c.d(optString2).a();
        final e a3 = new e.b().a(jsonObject.optString("title")).b(jsonObject.optString("subtitle")).c(jsonObject.optString("body")).d(jsonObject.optString("rating_value")).e(jsonObject.optString("category")).f(jsonObject.optString("destination_title")).g(jsonObject.optString("ad_creative_type")).a();
        final i i = new i(jsonObject.optString("fbad_command"), jsonObject.optString("call_to_action"));
        final JSONObject optJSONObject2 = jsonObject.optJSONObject("layout");
        JSONObject optJSONObject3;
        if (optJSONObject2 != null) {
            optJSONObject3 = optJSONObject2.optJSONObject("portrait");
        }
        else {
            optJSONObject3 = null;
        }
        final h a4 = h.a(optJSONObject3);
        JSONObject optJSONObject4 = jsonObject2;
        if (optJSONObject2 != null) {
            optJSONObject4 = optJSONObject2.optJSONObject("landscape");
        }
        final b b = new b(a4, h.a(optJSONObject4));
        final d.a a5 = new d.a().a(jsonObject.optString("video_url"));
        String optString3;
        if (jsonObject.optJSONObject("image") != null) {
            optString3 = jsonObject.optJSONObject("image").optString("url");
        }
        else {
            optString3 = "";
        }
        return new q(jsonObject.optString("request_id"), a2, a3, i, b, a5.b(optString3).a(jsonObject.optInt("skippable_seconds")).b(jsonObject.optInt("video_duration_sec")).a(n.a(jsonObject)).a(), new j(com.facebook.ads.internal.o.c.a(jsonObject.optString("end_card_markup")), jsonObject.optString("activation_command"), a(jsonObject.optJSONArray("end_card_images"))), jsonObject.optString("ct"));
    }
    
    private static List<String> a(final JSONArray jsonArray) {
        if (jsonArray == null) {
            return new ArrayList<String>();
        }
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final String optString = jsonArray.optString(i);
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                list.add(optString);
            }
        }
        return list;
    }
    
    @Override
    public String a() {
        return this.h;
    }
    
    @Override
    public void a(final String s) {
        super.a(s);
        this.g.a(s);
    }
    
    public void a(final boolean i) {
        this.i = i;
    }
    
    public void c(final String s) {
        this.f.a(s);
    }
    
    public String e() {
        return this.a;
    }
    
    public m f() {
        return this.b;
    }
    
    public e g() {
        return this.c;
    }
    
    public i h() {
        return this.d;
    }
    
    public b i() {
        return this.e;
    }
    
    public d j() {
        return this.f;
    }
    
    public j k() {
        return this.g;
    }
    
    public boolean l() {
        return this.i;
    }
}
