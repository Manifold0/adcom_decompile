// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.net.URL;
import android.graphics.Point;

public final class gy
{
    public static final bn d;
    public final ha a;
    public final Point b;
    public final Point c;
    
    static {
        d = new bn() {
            private static Point b(final bs bs) {
                Point point = null;
                bs.h();
                while (bs.j()) {
                    if ("offset".equals(bs.l())) {
                        bs.h();
                        int r = 0;
                        int r2 = 0;
                        while (bs.j()) {
                            final String l = bs.l();
                            if ("x".equals(l)) {
                                r2 = bs.r();
                            }
                            else if ("y".equals(l)) {
                                r = bs.r();
                            }
                            else {
                                bs.s();
                            }
                        }
                        bs.i();
                        point = new Point(r2, r);
                    }
                    else {
                        bs.s();
                    }
                }
                bs.i();
                return point;
            }
        };
    }
    
    public gy(final ha a, final Point b, final Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
