// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.c;

import android.support.annotation.WorkerThread;
import android.graphics.Rect;
import android.os.Build$VERSION;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.facebook.ads.internal.w.b.x;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;

public class c
{
    private static int a(final BitmapFactory$Options bitmapFactory$Options, final int n, final int n2) {
        final int outHeight = bitmapFactory$Options.outHeight;
        final int outWidth = bitmapFactory$Options.outWidth;
        int n3 = 1;
        int n4 = 1;
        if (outHeight > n2 || outWidth > n) {
            final int n5 = outHeight / 2;
            final int n6 = outWidth / 2;
            while (true) {
                n3 = n4;
                if (n5 / n4 < n2) {
                    break;
                }
                n3 = n4;
                if (n6 / n4 < n) {
                    break;
                }
                n4 *= 2;
            }
        }
        return n3;
    }
    
    public static Bitmap a(final b b) {
        final byte[] decode = Base64.decode(b.a(x.b), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }
    
    @WorkerThread
    public static Bitmap a(final InputStream inputStream, final int n, final int n2) {
        if (Build$VERSION.SDK_INT < 19) {
            return BitmapFactory.decodeStream(inputStream);
        }
        final e e = new e(inputStream);
        e.mark(8192);
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream((InputStream)e, (Rect)null, bitmapFactory$Options);
        e.reset();
        if (!e.a()) {
            bitmapFactory$Options.inSampleSize = a(bitmapFactory$Options, n2, n);
            bitmapFactory$Options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream((InputStream)e, (Rect)null, bitmapFactory$Options);
        }
        return BitmapFactory.decodeStream((InputStream)e);
    }
    
    @WorkerThread
    public static Bitmap a(final String s, final int n, final int n2) {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(s, bitmapFactory$Options);
        bitmapFactory$Options.inSampleSize = a(bitmapFactory$Options, n2, n);
        bitmapFactory$Options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(s, bitmapFactory$Options);
    }
}
