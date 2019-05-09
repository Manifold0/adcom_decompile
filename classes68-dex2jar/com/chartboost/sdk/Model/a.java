// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Model;

import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class a
{
    public final JSONObject a;
    public final int b;
    public final Map<String, b> c;
    public final Map<String, String> d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final int k;
    public final String l;
    public final String m;
    public final Map<String, List<String>> n;
    public final int o;
    public final String p;
    public final String q;
    public final b r;
    public final HashSet<String> s;
    
    public a(int i, final JSONObject a, final boolean b) throws JSONException {
        final int n = 0;
        this.b = i;
        this.a = a;
        this.f = a.getString("ad_id");
        this.g = a.getString("cgn");
        this.h = a.getString("creative");
        this.i = a.optString("deep-link");
        this.j = a.getString("link");
        this.m = a.getString("to");
        this.o = a.optInt("animation");
        this.p = a.optString("media-type");
        this.q = a.optString("name");
        this.c = new HashMap<String, b>();
        this.d = new HashMap<String, String>();
        this.n = new HashMap<String, List<String>>();
        this.s = new HashSet<String>();
        if (i == 1) {
            String l = "";
            final JSONObject jsonObject = a.getJSONObject("webview");
            final JSONArray jsonArray = jsonObject.getJSONArray("elements");
            int j = 0;
            i = 0;
        Label_0280_Outer:
            while (j < jsonArray.length()) {
                final JSONObject jsonObject2 = jsonArray.getJSONObject(j);
                final String string = jsonObject2.getString("name");
                final String optString = jsonObject2.optString("param");
                final String string2 = jsonObject2.getString("type");
                final String string3 = jsonObject2.getString("value");
                if (string2.equals("param")) {
                    this.d.put(optString, string3);
                    if (string.equals("reward_amount")) {
                        i = Integer.valueOf(string3);
                    }
                    else if (string.equals("reward_currency")) {
                        l = string3;
                    }
                }
                else {
                    String s;
                    if (string2.equals("html") && optString.isEmpty()) {
                        s = "body";
                    }
                    else {
                        s = optString;
                        if (optString.isEmpty()) {
                            s = string;
                        }
                    }
                    this.c.put(s, new b(string2, string, string3));
                }
                while (true) {
                    ++j;
                    continue Label_0280_Outer;
                    continue;
                }
            }
            this.k = i;
            this.l = l;
            this.r = this.c.get("body");
            if (this.r == null) {
                throw new RuntimeException("WebView AdUnit does not have a template html body asset");
            }
            this.e = jsonObject.getString("template");
            final JSONObject optJSONObject = a.optJSONObject("events");
            if (optJSONObject != null) {
                final Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    final JSONArray jsonArray2 = optJSONObject.getJSONArray(s2);
                    final ArrayList<String> list = new ArrayList<String>();
                    for (i = 0; i < jsonArray2.length(); ++i) {
                        list.add(jsonArray2.getString(i));
                    }
                    this.n.put(s2, list);
                }
            }
            final JSONArray optJSONArray = a.optJSONArray("certification_providers");
            if (optJSONArray != null) {
                for (i = n; i < optJSONArray.length(); ++i) {
                    this.s.add(optJSONArray.getString(i));
                }
            }
        }
        else {
            if (b) {
                final String string4 = a.getJSONObject("icons").getString("lg");
                this.c.put("lg", new b("inPlayIcons", string4.substring(string4.lastIndexOf("/") + 1), string4));
                this.k = 0;
                this.l = "";
            }
            else {
                final JSONObject jsonObject3 = a.getJSONObject("assets");
                final Iterator keys2 = jsonObject3.keys();
                while (keys2.hasNext()) {
                    final String s3 = keys2.next();
                    final JSONObject jsonObject4 = jsonObject3.getJSONObject(s3);
                    String s4;
                    if (s3.equals("video-portrait") || s3.equals("video-landscape")) {
                        s4 = "videos";
                    }
                    else {
                        s4 = "images";
                    }
                    String s5;
                    if ((s5 = jsonObject4.optString("id", (String)null)) == null) {
                        s5 = jsonObject4.getString("checksum") + ".png";
                    }
                    this.c.put(s3, new b(s4, s5, jsonObject4.getString("url")));
                }
                this.k = a.optInt("reward");
                this.l = a.optString("currency-name");
            }
            this.r = null;
            this.e = "";
        }
    }
}
