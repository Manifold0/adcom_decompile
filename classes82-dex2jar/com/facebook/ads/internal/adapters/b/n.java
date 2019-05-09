// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import android.support.annotation.Nullable;
import org.json.JSONObject;
import com.facebook.ads.internal.view.c.f;
import java.io.Serializable;

public class n implements Serializable
{
    private static final f a;
    private static final p b;
    private static final long serialVersionUID = -5352540123250859603L;
    private final String c;
    private final String d;
    private final int e;
    private final String f;
    private final String g;
    private final int h;
    private final boolean i;
    private final boolean j;
    private final f k;
    private final p l;
    private String m;
    
    static {
        a = f.b;
        b = p.c;
    }
    
    private n(final String c, final String d, final int e, final String f, final String g, final f k, final int h, final boolean i, final boolean j, final p l) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.k = k;
        this.h = h;
        this.i = i;
        this.j = j;
        this.l = l;
    }
    
    @Nullable
    public static n a(JSONObject optJSONObject) {
        final JSONObject optJSONObject2 = optJSONObject.optJSONObject("playable_data");
        if (optJSONObject2 == null) {
            return null;
        }
        final p a = p.a(optJSONObject2.optString("precaching_method", n.b.name()));
        final String optString = optJSONObject2.optString("uri");
        final String optString2 = optJSONObject2.optString("intro_card_icon_url");
        int n;
        if (optJSONObject.has("skippable_seconds")) {
            n = optJSONObject.optInt("skippable_seconds");
        }
        else {
            n = optJSONObject.optInt("unskippable_seconds", 0);
        }
        optJSONObject = optJSONObject2.optJSONObject("generic_text");
        String optString3;
        if (optJSONObject == null) {
            optString3 = "Rewarded Play";
        }
        else {
            optString3 = optJSONObject.optString("rewarded_play_text", "Rewarded Play");
        }
        final JSONObject optJSONObject3 = optJSONObject2.optJSONObject("generic_text");
        String optString4;
        if (optJSONObject3 == null) {
            optString4 = "Play Store will automatically open in [secs]s";
        }
        else {
            optString4 = optJSONObject3.optString("delay_click_text", "Play Store will automatically open in [secs]s");
        }
        return new n(optString, optString2, n, optString3, optString4, f.a(optJSONObject2.optInt("orientation", com.facebook.ads.internal.adapters.b.n.a.a())), optJSONObject2.optInt("web_view_timeout_in_milliseconds", 5000), optJSONObject2.optBoolean("enable_intro_card", true), optJSONObject2.optBoolean("enable_end_card"), a);
    }
    
    public String a() {
        return this.c;
    }
    
    public void a(final String m) {
        this.m = m;
    }
    
    public String b() {
        return this.d;
    }
    
    public int c() {
        return this.e;
    }
    
    public String d() {
        return this.f;
    }
    
    public String e() {
        return this.g;
    }
    
    public f f() {
        return this.k;
    }
    
    public int g() {
        return this.h;
    }
    
    public boolean h() {
        return this.i;
    }
    
    public boolean i() {
        return this.j;
    }
    
    public String j() {
        return this.m;
    }
    
    public p k() {
        return this.l;
    }
}
