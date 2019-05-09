package com.facebook.ads.internal.p025w.p069c;

import android.graphics.Bitmap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.facebook.ads.internal.w.c.f */
public class C2608f {
    /* renamed from: a */
    static final int f6462a = Runtime.getRuntime().availableProcessors();
    /* renamed from: b */
    static final ExecutorService f6463b = Executors.newFixedThreadPool(f6462a);
    /* renamed from: c */
    private static volatile boolean f6464c = true;
    /* renamed from: d */
    private final Bitmap f6465d;
    /* renamed from: e */
    private Bitmap f6466e;
    /* renamed from: f */
    private final C2602a f6467f = new C2606d();

    public C2608f(Bitmap bitmap) {
        this.f6465d = bitmap;
    }

    /* renamed from: a */
    public Bitmap m6704a() {
        return this.f6466e;
    }

    /* renamed from: a */
    public Bitmap m6705a(int i) {
        this.f6466e = this.f6467f.mo5644a(this.f6465d, (float) i);
        return this.f6466e;
    }
}
