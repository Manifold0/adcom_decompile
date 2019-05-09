// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public abstract class av implements as
{
    protected abstract at a(final Object p0, final boolean p1);
    
    @Override
    public final Object a(final Object o) {
        final at a = this.a(o, false);
        if (a != null) {
            return a.a();
        }
        return null;
    }
    
    @Override
    public void a(final Object o, final Object o2) {
        this.a(o, true).a(o2);
    }
}
