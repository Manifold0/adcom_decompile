// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.impl.adview.al;
import com.applovin.sdk.AppLovinSdkUtils;
import android.net.Uri;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;

public final class m extends an
{
    public m(final JSONObject jsonObject, final JSONObject jsonObject2, final AppLovinSdkImpl appLovinSdkImpl) {
        super(jsonObject, jsonObject2, appLovinSdkImpl);
    }
    
    private String an() {
        return bu.a(this.a, "stream_url", "", this.c);
    }
    
    public String a() {
        return bu.a(this.a, "html", (String)null, this.c);
    }
    
    public void a(final Uri uri) {
        try {
            synchronized (this.d) {
                this.a.put("video", (Object)uri.toString());
            }
        }
        catch (Throwable t) {}
    }
    
    public void a(final String s) {
        try {
            synchronized (this.d) {
                this.a.put("html", (Object)s);
            }
        }
        catch (Throwable t) {}
    }
    
    @Override
    public boolean b() {
        return this.a.has("stream_url");
    }
    
    public void c() {
        synchronized (this.d) {
            this.a.remove("stream_url");
        }
    }
    
    @Override
    public Uri d() {
        final String an = this.an();
        if (AppLovinSdkUtils.isValidString(an)) {
            return Uri.parse(an);
        }
        final String e = this.e();
        if (AppLovinSdkUtils.isValidString(e)) {
            return Uri.parse(e);
        }
        return null;
    }
    
    public String e() {
        return bu.a(this.a, "video", "", this.c);
    }
    
    @Override
    public boolean f() {
        return this.d() != null;
    }
    
    @Override
    public Uri g() {
        final String a = bu.a(this.a, "click_url", "", this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            return Uri.parse(a);
        }
        return null;
    }
    
    public float h() {
        return bu.a(this.a, "mraid_close_delay_graphic", 0.0f, this.c);
    }
    
    public boolean i() {
        return bu.a(this.a, "close_button_graphic_hidden", false, this.c);
    }
    
    public boolean j() {
        return !this.a.has("close_button_expandable_hidden") || bu.a(this.a, "close_button_expandable_hidden", false, this.c);
    }
    
    public al k() {
        return this.a(bu.a(this.a, "expandable_style", al.c.ordinal(), this.c));
    }
}
