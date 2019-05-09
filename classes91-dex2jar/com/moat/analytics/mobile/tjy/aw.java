// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class aw implements Runnable
{
    final /* synthetic */ ar a;
    final /* synthetic */ av b;
    
    aw(final av b, final ar a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.b.d.a(this.a);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
