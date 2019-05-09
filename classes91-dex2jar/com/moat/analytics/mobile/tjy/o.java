// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class o implements Runnable
{
    final /* synthetic */ n a;
    
    o(final n a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.a.e();
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
        }
    }
}
