package com.tapjoy.internal;

import android.graphics.Bitmap;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.URLConnection;

/* renamed from: com.tapjoy.internal.u */
public final class C2993u extends ContentHandler {
    /* renamed from: a */
    public static final C2993u f8232a = new C2993u();

    public final /* synthetic */ Object getContent(URLConnection uRLConnection) {
        return C2993u.m8222a(uRLConnection);
    }

    private C2993u() {
    }

    /* renamed from: a */
    private static Bitmap m8222a(URLConnection uRLConnection) {
        InputStream inputStream = uRLConnection.getInputStream();
        try {
            Bitmap a = C2995v.f8235a.m8223a(inputStream);
            return a;
        } finally {
            inputStream.close();
        }
    }

    /* renamed from: a */
    public static Bitmap m8221a(InputStream inputStream) {
        try {
            Bitmap a = C2995v.f8235a.m8223a(inputStream);
            return a;
        } finally {
            inputStream.close();
        }
    }
}
