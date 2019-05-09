// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.a;

import com.applovin.impl.sdk.gd;
import com.applovin.impl.sdk.aa;
import com.applovin.impl.sdk.o;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.sdk.bu;
import com.applovin.impl.sdk.n;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.gf;
import java.util.List;

public class g
{
    private static final List<String> c;
    protected List<gf> a;
    private final AppLovinSdkImpl b;
    private final JSONObject d;
    private final JSONObject e;
    private final long f;
    
    static {
        c = Arrays.asList("video/mp4", "video/webm", "video/3gpp", "video/x-matroska");
    }
    
    public g(final JSONObject d, final JSONObject e, final AppLovinSdkImpl b) {
        this.b = b;
        this.d = d;
        this.e = e;
        this.a = new ArrayList<gf>();
        this.f = System.currentTimeMillis();
    }
    
    public int a() {
        return this.a.size();
    }
    
    public List<gf> b() {
        return this.a;
    }
    
    public JSONObject c() {
        return this.d;
    }
    
    public JSONObject d() {
        return this.e;
    }
    
    public long e() {
        return this.f;
    }
    
    public n f() {
        return n.a(AppLovinAdSize.fromString(bu.a(this.e, "ad_size", (String)null, this.b)), AppLovinAdType.fromString(bu.a(this.e, "ad_type", (String)null, this.b)), o.b, bu.a(this.e, "zone_id", (String)null, this.b), this.b);
    }
    
    public List<String> g() {
        final List<String> a = aa.a(bu.a(this.d, "vast_preferred_video_types", (String)null, null));
        if (!a.isEmpty()) {
            return a;
        }
        return g.c;
    }
    
    public int h() {
        return gd.a(this.d);
    }
}
