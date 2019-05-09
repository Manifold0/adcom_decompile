// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.b;
import com.moat.analytics.mobile.tjy.base.exception.a;

class t implements Runnable
{
    final /* synthetic */ s a;
    
    t(final s a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            this.a.a.c();
        }
        catch (b b) {
            com.moat.analytics.mobile.tjy.base.exception.a.a(b);
        }
    }
}
