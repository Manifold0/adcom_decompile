// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.a;

class j implements Runnable
{
    final /* synthetic */ i a;
    
    j(final i a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            if (this.a.f.get() != null && !this.a.e()) {
                if ((boolean)this.a.i()) {
                    this.a.d.postDelayed((Runnable)this, 200L);
                    return;
                }
                this.a.c();
                return;
            }
        }
        catch (Exception ex) {
            this.a.c();
            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
            return;
        }
        this.a.c();
    }
}
