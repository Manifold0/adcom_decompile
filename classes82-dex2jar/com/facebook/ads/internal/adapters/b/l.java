// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import org.json.JSONObject;
import java.io.Serializable;

public class l implements Serializable
{
    private static final long serialVersionUID = 85021702336014823L;
    private final e a;
    private final i b;
    private final d c;
    private final boolean d;
    
    private l(final e a, final i b, final d c, final boolean d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    static l a(final JSONObject jsonObject) {
        int optInt = 0;
        final e a = new e.b().a(jsonObject.optString("title")).b(jsonObject.optString("subtitle")).c(jsonObject.optString("body")).a();
        final i i = new i(jsonObject.optString("fbad_command"), jsonObject.optString("call_to_action"));
        final boolean optBoolean = jsonObject.optBoolean("video_autoplay_enabled");
        final boolean optBoolean2 = jsonObject.optBoolean("is_watch_and_browse");
        final d.a b = new d.a().a(jsonObject.optString("video_url")).a(optBoolean).b(jsonObject.optBoolean("is_audio_muted", true));
        if (optBoolean) {
            optInt = jsonObject.optInt("unskippable_seconds", 0);
        }
        final d.a a2 = b.a(optInt);
        final JSONObject optJSONObject = jsonObject.optJSONObject("image");
        if (optJSONObject != null) {
            a2.b(optJSONObject.optString("url")).c(optJSONObject.optInt("width")).d(optJSONObject.optInt("height"));
        }
        a2.a(n.a(jsonObject));
        return new l(a, i, a2.a(), optBoolean2);
    }
    
    public e a() {
        return this.a;
    }
    
    public i b() {
        return this.b;
    }
    
    public d c() {
        return this.c;
    }
    
    public boolean d() {
        return this.d;
    }
}
