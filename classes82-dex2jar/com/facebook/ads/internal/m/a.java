// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.m;

import android.text.TextUtils;
import java.util.Locale;
import java.util.LinkedList;
import java.util.HashMap;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class a
{
    private final String a;
    private final String b;
    private final JSONObject c;
    private final Map<e, List<String>> d;
    
    public a(String string, String string2, final JSONObject c, @Nullable final JSONArray jsonArray) {
        this.d = new HashMap<e, List<String>>();
        this.a = string;
        this.b = string2;
        this.c = c;
        if (jsonArray != null && jsonArray.length() != 0) {
            final e[] values = e.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                this.d.put(values[i], new LinkedList<String>());
            }
            int j = 0;
            while (j < jsonArray.length()) {
                while (true) {
                    try {
                        final JSONObject jsonObject = jsonArray.getJSONObject(j);
                        string = jsonObject.getString("type");
                        string2 = jsonObject.getString("url");
                        final e value = e.valueOf(string.toUpperCase(Locale.US));
                        if (value != null && !TextUtils.isEmpty((CharSequence)string2)) {
                            this.d.get(value).add(string2);
                        }
                        ++j;
                    }
                    catch (Exception ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public String a() {
        return this.a;
    }
    
    public List<String> a(final e e) {
        return this.d.get(e);
    }
    
    public String b() {
        return this.b;
    }
    
    public JSONObject c() {
        return this.c;
    }
}
