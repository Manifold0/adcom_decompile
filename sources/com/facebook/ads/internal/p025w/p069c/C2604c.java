package com.facebook.ads.internal.p025w.p069c;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import java.io.InputStream;

/* renamed from: com.facebook.ads.internal.w.c.c */
public class C2604c {
    /* renamed from: a */
    private static int m6696a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 >= i2 && i4 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    /* renamed from: a */
    public static Bitmap m6697a(C2603b c2603b) {
        byte[] decode = Base64.decode(c2603b.m6695a(C2600x.f6420b), 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    @WorkerThread
    /* renamed from: a */
    public static Bitmap m6698a(InputStream inputStream, int i, int i2) {
        if (VERSION.SDK_INT < 19) {
            return BitmapFactory.decodeStream(inputStream);
        }
        InputStream c2607e = new C2607e(inputStream);
        c2607e.mark(8192);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c2607e, null, options);
        c2607e.reset();
        if (c2607e.m6703a()) {
            return BitmapFactory.decodeStream(c2607e);
        }
        options.inSampleSize = C2604c.m6696a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(c2607e, null, options);
    }

    @WorkerThread
    /* renamed from: a */
    public static Bitmap m6699a(String str, int i, int i2) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = C2604c.m6696a(options, i2, i);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }
}
