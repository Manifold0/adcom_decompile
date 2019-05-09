// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;
import java.util.ArrayList;
import android.graphics.PointF;

public final class hd
{
    public static final bn d;
    public af a;
    public PointF b;
    public ArrayList c;
    
    static {
        d = new bn() {};
    }
    
    public hd(final bs bs) {
        this.a = af.a;
        this.c = new ArrayList();
        bs.h();
        while (bs.j()) {
            final String l = bs.l();
            if ("buttons".equals(l)) {
                int n;
                if (bs.k() == bx.a) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n != 0) {
                    bs.a(this.c, hc.n);
                }
                else {
                    bs.s();
                }
            }
            else if ("window_aspect_ratio".equals(l)) {
                if (bs.a()) {
                    final PointF b = new PointF();
                    bs.h();
                    while (bs.j()) {
                        final String i = bs.l();
                        if ("width".equals(i)) {
                            b.x = (float)bs.p();
                        }
                        else if ("height".equals(i)) {
                            b.y = (float)bs.p();
                        }
                        else {
                            bs.s();
                        }
                    }
                    bs.i();
                    if (b.x == 0.0f || b.y == 0.0f) {
                        continue;
                    }
                    this.b = b;
                }
                else {
                    bs.s();
                }
            }
            else if ("orientation".equals(l)) {
                final String m = bs.m();
                if ("landscape".equals(m)) {
                    this.a = af.c;
                }
                else {
                    if (!"portrait".equals(m)) {
                        continue;
                    }
                    this.a = af.b;
                }
            }
            else {
                bs.s();
            }
        }
        bs.i();
    }
}
