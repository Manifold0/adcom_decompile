// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.graphics.Bitmap$CompressFormat;
import java.io.OutputStream;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import java.io.InputStream;

public final class v implements bi
{
    public static final v a;
    
    static {
        a = new v();
    }
    
    private v() {
    }
    
    public final Bitmap a(final InputStream inputStream) {
        try {
            return (Bitmap)ad.a(new bg() {});
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return null;
        }
    }
}
