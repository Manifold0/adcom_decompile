// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJPlacementListener;
import android.content.Context;

abstract class ff
{
    private static final ff a;
    private static ff b;
    
    static {
        ff.b = (a = new fg());
    }
    
    static ff a() {
        return ff.b;
    }
    
    public abstract Object a(final Context p0, final String p1, final TJPlacementListener p2);
}
