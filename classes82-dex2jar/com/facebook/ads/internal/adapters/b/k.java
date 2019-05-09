// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import com.facebook.ads.internal.w.h.a;
import java.util.ArrayList;
import android.content.Context;
import org.json.JSONObject;
import java.util.List;
import java.io.Serializable;

public class k implements Serializable
{
    private static final long serialVersionUID = 8751287062553772011L;
    private final m a;
    private final b b;
    private final List<l> c;
    private final int d;
    private final int e;
    private final String f;
    private final String g;
    private int h;
    private String i;
    
    private k(final m a, final b b, final List<l> c, final String f, final String g, final int d, final int e) {
        this.h = 200;
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;
        this.g = g;
        this.d = d;
        this.e = e;
    }
    
    public static k a(final JSONObject jsonObject, final Context context) {
        String optString = null;
        Object o = new m.a().a(jsonObject.optString("title"));
        Object o2 = null;
        Object o3 = null;
        int optInt = 0;
        int optInt2 = 0;
        ArrayList<l> list = null;
        Label_0255: {
            if (jsonObject.optJSONObject("icon") == null) {
                break Label_0255;
            }
            o2 = jsonObject.optJSONObject("icon").optString("url");
        Label_0078_Outer:
            while (true) {
                o = ((m.a)o).b((String)o2).c(jsonObject.optString("ad_choices_link_url"));
                o2 = jsonObject.optJSONObject("generic_text");
                Label_0262: {
                    if (o2 != null) {
                        break Label_0262;
                    }
                    o2 = "Sponsored";
                Label_0112_Outer:
                    while (true) {
                        o = ((m.a)o).d((String)o2).a();
                        o3 = jsonObject.optJSONObject("layout");
                        Label_0276: {
                            if (o3 == null) {
                                break Label_0276;
                            }
                            o2 = ((JSONObject)o3).optJSONObject("portrait");
                        Label_0222_Outer:
                            while (true) {
                                final h a = h.a((JSONObject)o2);
                                Object optJSONObject = optString;
                                if (o3 != null) {
                                    optJSONObject = ((JSONObject)o3).optJSONObject("landscape");
                                }
                                o2 = new b(a, h.a((JSONObject)optJSONObject));
                                optInt = jsonObject.optInt("viewability_check_initial_delay", 0);
                                optInt2 = jsonObject.optInt("viewability_check_interval", 1000);
                                optString = jsonObject.optString("ct");
                                o3 = jsonObject.optString("request_id", "");
                                final JSONArray optJSONArray = jsonObject.optJSONArray("carousel");
                                list = new ArrayList<l>();
                                if (optJSONArray == null || optJSONArray.length() <= 0) {
                                    break Label_0255;
                                }
                                int n = 0;
                            Label_0248_Outer:
                                while (true) {
                                    if (n >= optJSONArray.length()) {
                                        return new k((m)o, (b)o2, list, optString, (String)o3, optInt, optInt2);
                                    }
                                    while (true) {
                                        try {
                                            list.add(l.a(optJSONArray.getJSONObject(n)));
                                            ++n;
                                            continue Label_0248_Outer;
                                            o2 = null;
                                            continue Label_0222_Outer;
                                            o2 = "";
                                            continue Label_0078_Outer;
                                            o2 = ((JSONObject)o2).optString("sponsored", "Sponsored");
                                            continue Label_0112_Outer;
                                        }
                                        catch (JSONException ex) {
                                            com.facebook.ads.internal.w.h.a.b(context, "parsing", com.facebook.ads.internal.w.h.b.K, (Exception)ex);
                                            ex.printStackTrace();
                                            continue;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        list.add(l.a(jsonObject));
        return new k((m)o, (b)o2, list, optString, (String)o3, optInt, optInt2);
    }
    
    public m a() {
        return this.a;
    }
    
    public void a(final int h) {
        this.h = h;
    }
    
    public void a(final String i) {
        this.i = i;
    }
    
    public b b() {
        return this.b;
    }
    
    public String c() {
        return this.f;
    }
    
    public List<l> d() {
        return Collections.unmodifiableList((List<? extends l>)this.c);
    }
    
    public String e() {
        return this.g;
    }
    
    public int f() {
        return this.d;
    }
    
    public int g() {
        return this.e;
    }
    
    public int h() {
        return this.h;
    }
    
    public String i() {
        return this.i;
    }
}
