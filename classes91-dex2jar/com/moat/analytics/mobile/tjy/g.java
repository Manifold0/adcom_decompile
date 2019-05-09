// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class g implements Runnable
{
    final /* synthetic */ f a;
    
    g(final f a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.a.a("Shutting down.");
            this.a.l.b();
            this.a.l = null;
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
