// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import android.text.TextUtils;
import android.support.annotation.Nullable;
import org.json.JSONObject;
import java.io.Serializable;

public class g implements Serializable
{
    private static final long serialVersionUID = 4559450202335985006L;
    private final String a;
    private final String b;
    
    private g() {
        this.a = "";
        this.b = "";
    }
    
    private g(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    static g a(@Nullable final JSONObject jsonObject) {
        if (jsonObject == null) {
            return new g();
        }
        return new g(jsonObject.optString("timer_text"), jsonObject.optString("title_text"));
    }
    
    public String a() {
        return this.b;
    }
    
    public String a(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            return this.a;
        }
        return this.a.replace("[fb_sec]", s);
    }
}
