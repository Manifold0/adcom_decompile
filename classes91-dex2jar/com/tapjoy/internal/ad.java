// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ad
{
    public static Object a(final bg bg) {
        int n = 1;
        try {
            return bg.call();
        }
        catch (OutOfMemoryError outOfMemoryError) {
            if (n >= 10) {
                throw outOfMemoryError;
            }
            System.gc();
            ++n;
            return bg.call();
        }
    }
}
