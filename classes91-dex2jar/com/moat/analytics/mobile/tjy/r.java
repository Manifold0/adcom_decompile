// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class r implements Runnable
{
    final /* synthetic */ q a;
    
    r(final q a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.a.a.b();
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
