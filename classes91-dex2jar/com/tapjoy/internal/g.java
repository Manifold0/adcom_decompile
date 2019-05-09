// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class g
{
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public long g;
    
    public g(final String s) {
        final bs b = bs.b(s);
        b.h();
        while (b.j()) {
            final String l = b.l();
            if ("productId".equals(l)) {
                this.a = b.m();
            }
            else if ("type".equals(l)) {
                this.b = b.m();
            }
            else if ("price".equals(l)) {
                this.c = b.m();
            }
            else if ("title".equals(l)) {
                this.d = b.m();
            }
            else if ("description".equals(l)) {
                this.e = b.m();
            }
            else if ("price_currency_code".equals(l)) {
                this.f = b.m();
            }
            else if ("price_amount_micros".equals(l)) {
                this.g = b.q();
            }
            else {
                b.s();
            }
        }
        b.i();
    }
}
