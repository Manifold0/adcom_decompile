// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import org.json.JSONArray;
import android.os.Build$VERSION;
import org.json.JSONObject;

public class u
{
    private boolean a;
    private boolean b;
    private int c;
    
    public u(final String s) {
        this.a = false;
        this.b = false;
        this.c = 200;
        this.a(s);
    }
    
    private boolean a(final JSONObject jsonObject) {
        try {
            if (jsonObject.has("ob")) {
                final JSONArray jsonArray = jsonObject.getJSONArray("ob");
                for (int length = jsonArray.length(), i = 0; i < length; ++i) {
                    if (jsonArray.getInt(i) == Build$VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public void a(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final String string = jsonObject.getString("sa");
            final boolean equals = string.equals("8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
            if ((string.equals("on") || equals) && !this.a(jsonObject)) {
                this.a = true;
                this.b = equals;
            }
            if (jsonObject.has("in")) {
                final int int1 = jsonObject.getInt("in");
                if (int1 >= 100 && int1 <= 1000) {
                    this.c = int1;
                }
            }
        }
        catch (Exception ex) {
            this.a = false;
            this.b = false;
            this.c = 200;
        }
    }
    
    public boolean a() {
        return this.b;
    }
    
    public boolean b() {
        return this.a;
    }
    
    public int c() {
        return this.c;
    }
}
