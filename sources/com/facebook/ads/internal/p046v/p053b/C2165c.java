package com.facebook.ads.internal.p046v.p053b;

import com.facebook.ads.internal.p046v.p053b.p054a.C2153a;
import com.facebook.ads.internal.p046v.p053b.p054a.C2156c;
import java.io.File;

/* renamed from: com.facebook.ads.internal.v.b.c */
class C2165c {
    /* renamed from: a */
    public final File f4989a;
    /* renamed from: b */
    public final C2156c f4990b;
    /* renamed from: c */
    public final C2153a f4991c;

    C2165c(File file, C2156c c2156c, C2153a c2153a) {
        this.f4989a = file;
        this.f4990b = c2156c;
        this.f4991c = c2153a;
    }

    /* renamed from: a */
    File m5539a(String str) {
        return new File(this.f4989a, this.f4990b.mo5527a(str));
    }
}
