// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.util.Log;
import com.moat.analytics.mobile.tjy.base.functional.a;

class z implements ba
{
    final /* synthetic */ ap a;
    final /* synthetic */ String b;
    final /* synthetic */ v c;
    
    z(final v c, final ap a, final String b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public a a() {
        if (this.a.b()) {
            Log.d("MoatFactory", "Creating NativeVideo tracker.");
        }
        return com.moat.analytics.mobile.tjy.base.functional.a.a(new ah(this.b, this.c.b, this.a));
    }
}
