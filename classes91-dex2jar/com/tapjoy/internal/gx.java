// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import android.os.SystemClock;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import android.graphics.Point;

public final class gx extends gt
{
    public static final bn n;
    public ha a;
    public ha b;
    public ha c;
    public Point d;
    public ha e;
    public ha f;
    public String g;
    public fo h;
    public ArrayList i;
    public ArrayList j;
    public Map k;
    public long l;
    public gy m;
    
    static {
        n = new bn() {};
    }
    
    public gx() {
        this.i = new ArrayList();
        this.j = new ArrayList();
    }
    
    gx(final bs bs) {
        this.i = new ArrayList();
        this.j = new ArrayList();
        bs.h();
        String b = null;
        String b2 = null;
        while (bs.j()) {
            final String l = bs.l();
            if ("frame".equals(l)) {
                bs.h();
                while (bs.j()) {
                    final String i = bs.l();
                    if ("portrait".equals(i)) {
                        this.a = (ha)ha.e.a(bs);
                    }
                    else if ("landscape".equals(i)) {
                        this.b = (ha)ha.e.a(bs);
                    }
                    else if ("close_button".equals(i)) {
                        this.c = (ha)ha.e.a(bs);
                    }
                    else if ("close_button_offset".equals(i)) {
                        this.d = (Point)bo.a.a(bs);
                    }
                    else {
                        bs.s();
                    }
                }
                bs.i();
            }
            else if ("creative".equals(l)) {
                bs.h();
                while (bs.j()) {
                    final String j = bs.l();
                    if ("portrait".equals(j)) {
                        this.e = (ha)ha.e.a(bs);
                    }
                    else if ("landscape".equals(j)) {
                        this.f = (ha)ha.e.a(bs);
                    }
                    else {
                        bs.s();
                    }
                }
                bs.i();
            }
            else if ("url".equals(l)) {
                this.g = bs.b();
            }
            else if (gr.a(l)) {
                this.h = gr.a(l, bs);
            }
            else if ("mappings".equals(l)) {
                bs.h();
                while (bs.j()) {
                    final String k = bs.l();
                    if ("portrait".equals(k)) {
                        bs.a(this.i, gv.h);
                    }
                    else if ("landscape".equals(k)) {
                        bs.a(this.j, gv.h);
                    }
                    else {
                        bs.s();
                    }
                }
                bs.i();
            }
            else if ("meta".equals(l)) {
                this.k = bs.d();
            }
            else if ("ttl".equals(l)) {
                this.l = (long)(bs.p() * 1000.0) + SystemClock.elapsedRealtime();
            }
            else if ("no_more_today".equals(l)) {
                this.m = (gy)gy.d.a(bs);
            }
            else if ("ad_content".equals(l)) {
                b = bs.b();
            }
            else if ("redirect_url".equals(l)) {
                b2 = bs.b();
            }
            else {
                bs.s();
            }
        }
        bs.i();
        if (this.g == null) {
            this.g = "";
        }
        if (this.i != null) {
            for (final gv gv : this.i) {
                if (gv.f == null) {
                    gv.f = b;
                }
                if (gv.e == null) {
                    gv.e = b2;
                }
            }
        }
        if (this.j != null) {
            for (final gv gv2 : this.j) {
                if (gv2.f == null) {
                    gv2.f = b;
                }
                if (gv2.e == null) {
                    gv2.e = b2;
                }
            }
        }
    }
    
    public final boolean a() {
        return this.c != null && this.a != null && this.e != null;
    }
    
    public final boolean b() {
        return this.c != null && this.b != null && this.f != null;
    }
}
