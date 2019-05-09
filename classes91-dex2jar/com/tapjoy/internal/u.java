// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.net.URLConnection;
import android.graphics.Bitmap;
import java.io.InputStream;
import java.net.ContentHandler;

public final class u extends ContentHandler
{
    public static final u a;
    
    static {
        a = new u();
    }
    
    private u() {
    }
    
    public static Bitmap a(final InputStream inputStream) {
        try {
            return v.a.a(inputStream);
        }
        finally {
            inputStream.close();
        }
    }
    
    private static Bitmap a(URLConnection inputStream) {
        inputStream = (URLConnection)inputStream.getInputStream();
        try {
            return v.a.a((InputStream)inputStream);
        }
        finally {
            ((InputStream)inputStream).close();
        }
    }
}
