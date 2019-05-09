package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {
    /* renamed from: a */
    private static AtomicReference f16a = new AtomicReference();

    private GoogleVrApi() {
    }

    /* renamed from: a */
    static void m30a() {
        f16a.set(null);
    }

    /* renamed from: a */
    static void m31a(C0230f c0230f) {
        f16a.compareAndSet(null, new GoogleVrProxy(c0230f));
    }

    /* renamed from: b */
    static GoogleVrProxy m32b() {
        return (GoogleVrProxy) f16a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) f16a.get();
    }
}
