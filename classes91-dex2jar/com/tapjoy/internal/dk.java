// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public enum dk
{
    a("VARINT", 0, 0), 
    b("FIXED64", 1, 1), 
    c("LENGTH_DELIMITED", 2, 2), 
    d("FIXED32", 3, 5);
    
    final int e;
    
    private dk(final String s, final int n, final int e) {
        this.e = e;
    }
    
    public final dn a() {
        switch (dk$1.a[this.ordinal()]) {
            default: {
                throw new AssertionError();
            }
            case 1: {
                return dn.j;
            }
            case 2: {
                return dn.g;
            }
            case 3: {
                return dn.l;
            }
            case 4: {
                return dn.q;
            }
        }
    }
}
