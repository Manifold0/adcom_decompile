// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class h
{
    public String a;
    public String b;
    public String c;
    public long d;
    public int e;
    public String f;
    public String g;
    
    public h(final String s) {
        final bs b = bs.b(s);
        b.h();
        while (b.j()) {
            final String l = b.l();
            if ("orderId".equals(l)) {
                this.a = b.m();
            }
            else if ("packageName".equals(l)) {
                this.b = b.m();
            }
            else if ("productId".equals(l)) {
                this.c = b.m();
            }
            else if ("purchaseTime".equals(l)) {
                this.d = b.q();
            }
            else if ("purchaseState".equals(l)) {
                this.e = b.r();
            }
            else if ("developerPayload".equals(l)) {
                this.f = b.m();
            }
            else if ("purchaseToken".equals(l)) {
                this.g = b.m();
            }
            else {
                b.s();
            }
        }
        b.i();
    }
}
