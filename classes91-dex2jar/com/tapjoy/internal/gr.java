// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Arrays;

abstract class gr implements fo
{
    private static final String[] a;
    
    static {
        Arrays.sort(a = new String[] { "reward", "purchase", "custom_action" });
    }
    
    public static gr a(final String s, final bs bs) {
        if ("reward".equals(s)) {
            return (gr)bs.a(hb.a);
        }
        if ("purchase".equals(s)) {
            return (gr)bs.a(gz.a);
        }
        return null;
    }
    
    public static boolean a(final String s) {
        return Arrays.binarySearch(gr.a, s) >= 0;
    }
    
    @Override
    public final void a(final fp fp) {
        if (this instanceof fs) {
            final fs fs = (fs)this;
            fp.a(fs.a(), fs.b());
        }
        else if (this instanceof ft) {
            final ft ft = (ft)this;
            fp.a(ft.a(), ft.b(), ft.c(), ft.d());
        }
    }
}
