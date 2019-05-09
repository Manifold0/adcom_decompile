// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Model;

import java.io.File;
import com.chartboost.sdk.Libraries.e;
import org.json.JSONArray;
import java.util.Iterator;
import org.json.JSONException;
import com.chartboost.sdk.Tracking.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class b
{
    public final String a;
    public final String b;
    public final String c;
    
    public b(final String a, final String b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public static Map<String, b> a(JSONObject jsonArray) {
        final HashMap<String, b> hashMap = new HashMap<String, b>();
        try {
            jsonArray = (JSONObject)jsonArray.getJSONArray("videos");
            final int length = ((JSONArray)jsonArray).length();
            int i = 0;
            while (i < length) {
                try {
                    final JSONObject jsonObject = ((JSONArray)jsonArray).getJSONObject(i);
                    final String string = jsonObject.getString("id");
                    hashMap.put(string, new b("videos", string, jsonObject.getString("video")));
                    ++i;
                }
                catch (JSONException ex) {
                    a.a(b.class, "deserializeNativeVideos (file)", (Exception)ex);
                }
            }
        }
        catch (JSONException ex2) {
            a.a(b.class, "deserializeNativeVideos (videos array)", (Exception)ex2);
        }
        return hashMap;
    }
    
    public static Map<String, b> a(JSONObject jsonObject, final int n) {
        HashMap<String, b> hashMap = null;
    Label_0022_Outer:
        while (true) {
            hashMap = new HashMap<String, b>();
            while (true) {
                String s = null;
                Label_0180: {
                    while (true) {
                        int n2 = 0;
                        Label_0173: {
                            try {
                                jsonObject = jsonObject.getJSONObject("cache_assets");
                                final Iterator keys = jsonObject.keys();
                                JSONArray optJSONArray = null;
                                Block_7: {
                                    while (keys.hasNext()) {
                                        s = keys.next();
                                        if (!s.equals("templates")) {
                                            break Label_0180;
                                        }
                                        optJSONArray = jsonObject.optJSONArray("templates");
                                        if (optJSONArray == null) {
                                            continue Label_0022_Outer;
                                        }
                                        final int min = Math.min(n, optJSONArray.length());
                                        n2 = 0;
                                        if (n2 < min) {
                                            break Block_7;
                                        }
                                    }
                                    break;
                                }
                                final Iterator<Map.Entry<String, b>> iterator = b(a(optJSONArray.getJSONObject(n2).getJSONArray("elements"))).entrySet().iterator();
                                while (iterator.hasNext()) {
                                    final b b = iterator.next().getValue();
                                    hashMap.put(b.b, b);
                                }
                                break Label_0173;
                            }
                            catch (JSONException ex) {
                                a.a(b.class, "v2PrefetchToAssets", (Exception)ex);
                            }
                            break;
                        }
                        ++n2;
                        continue;
                    }
                }
                final JSONArray jsonArray = jsonObject.getJSONArray(s);
                for (int i = 0; i < jsonArray.length(); ++i) {
                    final JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    final String string = jsonObject2.getString("name");
                    hashMap.put(string, new b(s, string, jsonObject2.getString("value")));
                }
                continue;
            }
        }
        return hashMap;
    }
    
    private static JSONObject a(final JSONArray jsonArray) throws JSONException {
        final JSONObject a = e.a(new e.a[0]);
        for (int i = 0; i < jsonArray.length(); ++i) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            final String optString = jsonObject.optString("name");
            final String optString2 = jsonObject.optString("type");
            final String optString3 = jsonObject.optString("value");
            final String optString4 = jsonObject.optString("param");
            if (!optString2.equals("param") && optString4.isEmpty()) {
                JSONObject jsonObject2;
                if ((jsonObject2 = a.optJSONObject(optString2)) == null) {
                    jsonObject2 = e.a(new e.a[0]);
                    a.put(optString2, (Object)jsonObject2);
                }
                String s;
                if (optString2.equals("html")) {
                    s = "body";
                }
                else {
                    s = optString;
                }
                jsonObject2.put(s, (Object)e.a(e.a("filename", optString), e.a("url", optString3)));
            }
        }
        return a;
    }
    
    private static Map<String, b> b(final JSONObject jsonObject) throws JSONException {
        final HashMap<String, b> hashMap = new HashMap<String, b>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final JSONObject jsonObject2 = jsonObject.getJSONObject(s);
            final Iterator keys2 = jsonObject2.keys();
            while (keys2.hasNext()) {
                final String s2 = keys2.next();
                final JSONObject jsonObject3 = jsonObject2.getJSONObject(s2);
                hashMap.put(s2, new b(s, jsonObject3.getString("filename"), jsonObject3.getString("url")));
            }
        }
        return hashMap;
    }
    
    public File a(final File file) {
        return new File(file, this.a + "/" + this.b);
    }
}
