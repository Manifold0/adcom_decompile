// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

enum bv
{
    a("EMPTY_ARRAY", 0), 
    b("NONEMPTY_ARRAY", 1), 
    c("EMPTY_OBJECT", 2), 
    d("DANGLING_NAME", 3), 
    e("NONEMPTY_OBJECT", 4), 
    f("EMPTY_DOCUMENT", 5), 
    g("NONEMPTY_DOCUMENT", 6), 
    h("CLOSED", 7);
    
    private bv(final String s, final int n) {
    }
}
