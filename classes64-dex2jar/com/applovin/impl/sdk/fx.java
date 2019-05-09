// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class fx extends fs<gf>
{
    final /* synthetic */ fw a;
    
    fx(final fw a, final String s, final gf gf, final String s2, final AppLovinSdkImpl appLovinSdkImpl) {
        this.a = a;
        super(s, gf, s2, appLovinSdkImpl);
    }
    
    @Override
    public void a(final int n) {
        this.e.e(this.c, "Unable to resolve VAST wrapper. Server returned " + n);
        this.a.a(n);
    }
    
    @Override
    public void a(final gf gf, final int n) {
        this.d.getTaskManager().a(fl.a(gf, this.a.a, this.a.b, this.d));
    }
}
