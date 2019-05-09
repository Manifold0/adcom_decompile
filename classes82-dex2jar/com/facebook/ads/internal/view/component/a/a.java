// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.b.l;
import com.facebook.ads.internal.w.b.x;

public class a
{
    private static final int a;
    private static final int b;
    
    static {
        a = x.a.heightPixels;
        b = x.a.widthPixels;
    }
    
    public static float a(final l l) {
        final int h = l.c().h();
        final int i = l.c().i();
        if (i > 0) {
            return h / (float)i;
        }
        return -1.0f;
    }
    
    public static boolean a(final double n) {
        return n < 0.9;
    }
    
    public static boolean a(int a, final int n, final double n2) {
        boolean b = false;
        if (a != 2) {
            a = x.a(16);
            if (a.a - n - (a + a.a * 2 + f.a * 2) < (int)((a.b - f.a * 2) / n2)) {
                a = 1;
            }
            else {
                a = 0;
            }
            if (a == 0) {
                return b;
            }
        }
        b = true;
        return b;
    }
}
