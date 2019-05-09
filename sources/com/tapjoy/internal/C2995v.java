package com.tapjoy.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.tapjoy.internal.v */
public final class C2995v implements bi {
    /* renamed from: a */
    public static final C2995v f8235a = new C2995v();

    /* renamed from: a */
    public final /* synthetic */ void mo6321a(OutputStream outputStream, Object obj) {
        if (!((Bitmap) obj).compress(CompressFormat.PNG, 100, outputStream)) {
            throw new RuntimeException();
        }
    }

    /* renamed from: b */
    public final /* synthetic */ Object mo6322b(InputStream inputStream) {
        return m8223a(inputStream);
    }

    private C2995v() {
    }

    /* renamed from: a */
    public final Bitmap m8223a(final InputStream inputStream) {
        try {
            return (Bitmap) ad.m7146a(new bg(this) {
                /* renamed from: b */
                final /* synthetic */ C2995v f8234b;

                public final /* synthetic */ Object call() {
                    if (inputStream instanceof bh) {
                        return BitmapFactory.decodeStream(inputStream);
                    }
                    return BitmapFactory.decodeStream(new bh(inputStream));
                }
            });
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
