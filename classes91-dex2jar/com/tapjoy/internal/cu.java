// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class cu
{
    public static RuntimeException a(final Throwable t) {
        final Throwable t2 = (Throwable)cs.a(t);
        a(t2, Error.class);
        a(t2, RuntimeException.class);
        throw new RuntimeException(t);
    }
    
    private static void a(final Throwable t, final Class clazz) {
        if (t != null && clazz.isInstance(t)) {
            throw (Throwable)clazz.cast(t);
        }
    }
}
