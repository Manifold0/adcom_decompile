// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public final class gu extends gt
{
    public static final bn d;
    public ArrayList a;
    public Map b;
    public float c;
    
    static {
        d = new bn() {};
    }
    
    public gu(final bs bs) {
        this.a = new ArrayList();
        bs.h();
        String b = null;
        String b2 = null;
        while (bs.j()) {
            final String l = bs.l();
            if ("layouts".equals(l)) {
                bs.a(this.a, hd.d);
            }
            else if ("meta".equals(l)) {
                this.b = bs.d();
            }
            else if ("max_show_time".equals(l)) {
                this.c = (float)bs.p();
            }
            else if ("ad_content".equals(l)) {
                b2 = bs.b();
            }
            else if ("redirect_url".equals(l)) {
                b = bs.b();
            }
            else {
                bs.s();
            }
        }
        bs.i();
        if (this.a != null) {
            for (final hd hd : this.a) {
                if (hd.c != null) {
                    for (final hc hc : hd.c) {
                        if (hc.i == null) {
                            hc.i = b2;
                        }
                        if (hc.h == null) {
                            hc.h = b;
                        }
                    }
                }
            }
        }
    }
}
