// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import org.json.JSONObject;

abstract class dv extends dx
{
    protected dv(final String s, final AppLovinSdkImpl appLovinSdkImpl) {
        super(s, appLovinSdkImpl);
    }
    
    protected void a(final String s, final JSONObject jsonObject, final af af) {
        final dw dw = new dw(this, "POST", new JSONObject(), "Repeat" + this.c, this.d, af);
        dw.a(ag.a(s, null, this.d));
        dw.b(ag.c(s, null, this.d));
        dw.a(jsonObject);
        dw.c(this.d.get(ea.j));
        dw.a(ea.m);
        dw.b(ea.q);
        dw.run();
    }
}
