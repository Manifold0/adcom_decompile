// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;

final class hk implements a
{
    @Override
    public final Bitmap a(final int n, final int n2, final Bitmap$Config bitmap$Config) {
        return Bitmap.createBitmap(n, n2, bitmap$Config);
    }
    
    @Override
    public final byte[] a(final int n) {
        return new byte[n];
    }
    
    @Override
    public final int[] b(final int n) {
        return new int[n];
    }
}
