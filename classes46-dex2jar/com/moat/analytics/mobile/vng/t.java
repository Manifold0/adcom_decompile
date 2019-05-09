// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import org.json.JSONException;
import android.graphics.Rect;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.HashSet;
import android.view.View;
import java.util.Set;
import java.util.Map;

class t extends b implements NativeDisplayTracker
{
    private final Map<String, String> f;
    private final Set<MoatUserInteractionType> g;
    
    t(final View view, final Map<String, String> f) {
        super(view, true, false);
        this.g = new HashSet<MoatUserInteractionType>();
        p.a(3, "NativeDisplayTracker", this, "Initializing.");
        this.f = f;
        final g d = ((k)MoatAnalytics.getInstance()).d;
        if (d != null) {
            super.a(d.b);
            super.a(d.a);
        }
        this.g();
        p.a("[SUCCESS] ", this.a() + " created for " + this.e() + ", with adIds:" + f.toString());
    }
    
    private static String a(final Map<String, String> map) {
        final int n = 0;
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        int n2 = 0;
        int i;
        while (true) {
            i = n;
            if (n2 >= 8) {
                break;
            }
            final String string = "moatClientLevel" + n2;
            if (map.containsKey(string)) {
                linkedHashMap.put(string, map.get(string));
            }
            ++n2;
        }
        while (i < 8) {
            final String string2 = "moatClientSlicer" + i;
            if (map.containsKey(string2)) {
                linkedHashMap.put(string2, map.get(string2));
            }
            ++i;
        }
        for (final String s : map.keySet()) {
            if (!linkedHashMap.containsKey(s)) {
                linkedHashMap.put(s, map.get(s));
            }
        }
        return new JSONObject((Map)linkedHashMap).toString();
    }
    
    private void g() {
        if (this.a != null) {
            this.a.a(this.h());
        }
    }
    
    private String h() {
        try {
            final String a = a(this.f);
            p.a(3, "NativeDisplayTracker", this, "Parsed ad ids = " + a);
            return "{\"adIds\":" + a + ", \"adKey\":\"" + this.b + "\", \"adSize\":" + this.i() + "}";
        }
        catch (Exception ex) {
            m.a(ex);
            return "";
        }
    }
    
    private String i() {
        try {
            final Rect a = z.a(super.d());
            final int width = a.width();
            final int height = a.height();
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject((Map)hashMap).toString();
        }
        catch (Exception ex) {
            m.a(ex);
            return null;
        }
    }
    
    @Override
    String a() {
        return "NativeDisplayTracker";
    }
    
    @Override
    public void reportUserInteractionEvent(final MoatUserInteractionType moatUserInteractionType) {
        try {
            p.a(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.g.contains(moatUserInteractionType)) {
                this.g.add(moatUserInteractionType);
                final JSONObject jsonObject = new JSONObject();
                jsonObject.accumulate("adKey", (Object)this.b);
                jsonObject.accumulate("event", (Object)moatUserInteractionType.name().toLowerCase());
                if (this.a != null) {
                    this.a.b(jsonObject.toString());
                }
            }
        }
        catch (JSONException ex) {
            p.b(2, "NativeDisplayTracker", this, "Got JSON exception");
            m.a((Exception)ex);
        }
        catch (Exception ex2) {
            m.a(ex2);
        }
    }
}
