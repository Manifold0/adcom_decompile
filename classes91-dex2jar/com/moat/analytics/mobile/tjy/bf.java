// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

class bf implements ba
{
    final /* synthetic */ a a;
    final /* synthetic */ ap b;
    final /* synthetic */ ReactiveVideoTrackerPlugin c;
    
    bf(final ReactiveVideoTrackerPlugin c, final a a, final ap b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public com.moat.analytics.mobile.tjy.base.functional.a a() {
        return com.moat.analytics.mobile.tjy.base.functional.a.a(new bd(this.c.a, this.a, this.b));
    }
}
