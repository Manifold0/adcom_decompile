// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.util.Log;

final class g
{
    protected static boolean a;
    
    static {
        g.a = false;
    }
    
    protected static void Log(final int n, final String s) {
        if (!g.a) {
            if (n == 6) {
                Log.e("Unity", s);
            }
            if (n == 5) {
                Log.w("Unity", s);
            }
        }
    }
}
