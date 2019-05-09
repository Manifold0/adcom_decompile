// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.os.Build$VERSION;

public final class j
{
    static final boolean a;
    static final boolean b;
    static final boolean c;
    static final e d;
    
    static {
        final boolean b2 = true;
        a = (Build$VERSION.SDK_INT >= 19);
        b = (Build$VERSION.SDK_INT >= 21);
        e d2;
        if (c = (Build$VERSION.SDK_INT >= 23 && b2)) {
            d2 = new h();
        }
        else {
            d2 = null;
        }
        d = d2;
    }
}
