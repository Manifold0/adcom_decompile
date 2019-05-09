// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

public class o
{
    protected final p a;
    
    public o(final p a) {
        if (a == null) {
            throw new IllegalArgumentException("resultCode cannot be null");
        }
        this.a = a;
    }
    
    public boolean f() {
        return this.a.a();
    }
    
    public boolean g() {
        return this.a.b();
    }
    
    public p h() {
        return this.a;
    }
}
