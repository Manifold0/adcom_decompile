// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class hc
{
    public static final bn n;
    public he a;
    public he b;
    public he c;
    public he d;
    public int e;
    public int f;
    public String g;
    public String h;
    public String i;
    public boolean j;
    public String k;
    public ha l;
    public ha m;
    
    static {
        n = new bn() {};
    }
    
    public hc(final bs bs) {
        this.e = 9;
        this.f = 10;
        this.j = false;
        bs.h();
        while (bs.j()) {
            final String l = bs.l();
            if ("x".equals(l)) {
                this.a = he.a(bs.m());
            }
            else if ("y".equals(l)) {
                this.b = he.a(bs.m());
            }
            else if ("width".equals(l)) {
                this.c = he.a(bs.m());
            }
            else if ("height".equals(l)) {
                this.d = he.a(bs.m());
            }
            else if ("url".equals(l)) {
                this.g = bs.m();
            }
            else if ("redirect_url".equals(l)) {
                this.h = bs.m();
            }
            else if ("ad_content".equals(l)) {
                this.i = bs.m();
            }
            else if ("dismiss".equals(l)) {
                this.j = bs.n();
            }
            else if ("value".equals(l)) {
                this.k = bs.m();
            }
            else if ("image".equals(l)) {
                this.l = (ha)ha.e.a(bs);
            }
            else if ("image_clicked".equals(l)) {
                this.m = (ha)ha.e.a(bs);
            }
            else if ("align".equals(l)) {
                final String m = bs.m();
                if ("left".equals(m)) {
                    this.e = 9;
                }
                else if ("right".equals(m)) {
                    this.e = 11;
                }
                else if ("center".equals(m)) {
                    this.e = 14;
                }
                else {
                    bs.s();
                }
            }
            else if ("valign".equals(l)) {
                final String i = bs.m();
                if ("top".equals(i)) {
                    this.f = 10;
                }
                else if ("middle".equals(i)) {
                    this.f = 15;
                }
                else if ("bottom".equals(i)) {
                    this.f = 12;
                }
                else {
                    bs.s();
                }
            }
            else {
                bs.s();
            }
        }
        bs.i();
    }
}
