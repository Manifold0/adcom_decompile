// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class bk implements bc
{
    private static final a a;
    
    static {
        a a2 = com.moat.analytics.mobile.tjy.base.functional.a.a();
        while (true) {
            try {
                a2 = com.moat.analytics.mobile.tjy.base.functional.a.a(WebAdTracker.class.getMethod("track", (Class<?>[])new Class[0]));
                a = a2;
            }
            catch (NoSuchMethodException ex) {
                com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
                continue;
            }
            break;
        }
    }
    
    @Override
    public Class a() {
        return WebAdTracker.class;
    }
}
