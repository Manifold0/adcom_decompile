// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import android.text.TextUtils;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;

class e
{
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final Map<String, Long> e;
    
    private e(final String a, final String b, final String c) {
        this.e = new HashMap<String, Long>();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = System.currentTimeMillis();
    }
    
    private JSONObject a() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("pk", (Object)this.a);
        jsonObject.put("ts", this.d);
        if (!TextUtils.isEmpty((CharSequence)this.b)) {
            jsonObject.put("sk1", (Object)this.b);
        }
        if (!TextUtils.isEmpty((CharSequence)this.c)) {
            jsonObject.put("sk2", (Object)this.c);
        }
        for (final Map.Entry<String, Long> entry : this.e.entrySet()) {
            jsonObject.put((String)entry.getKey(), (Object)entry.getValue());
        }
        return jsonObject;
    }
    
    private String b() throws JSONException {
        return this.a().toString();
    }
    
    void a(final String s, final long n) {
        this.e.put(s, n);
    }
    
    @Override
    public String toString() {
        return "[AdEventStats pk: " + this.a + ", size: " + this.e.size() + "]";
    }
}
