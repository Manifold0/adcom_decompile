package com.unity3d.player;

/* renamed from: com.unity3d.player.n */
final class C0255n {
    /* renamed from: a */
    private static boolean f211a = false;
    /* renamed from: b */
    private boolean f212b;
    /* renamed from: c */
    private boolean f213c;
    /* renamed from: d */
    private boolean f214d;
    /* renamed from: e */
    private boolean f215e;

    C0255n() {
        this.f212b = !C0246j.f190c;
        this.f213c = false;
        this.f214d = false;
        this.f215e = true;
    }

    /* renamed from: a */
    static void m166a() {
        f211a = true;
    }

    /* renamed from: b */
    static void m167b() {
        f211a = false;
    }

    /* renamed from: c */
    static boolean m168c() {
        return f211a;
    }

    /* renamed from: a */
    final void m169a(boolean z) {
        this.f213c = z;
    }

    /* renamed from: b */
    final void m170b(boolean z) {
        this.f215e = z;
    }

    /* renamed from: c */
    final void m171c(boolean z) {
        this.f214d = z;
    }

    /* renamed from: d */
    final void m172d() {
        this.f212b = true;
    }

    /* renamed from: e */
    final boolean m173e() {
        return this.f215e;
    }

    /* renamed from: f */
    final boolean m174f() {
        return f211a && this.f213c && this.f212b && !this.f215e && !this.f214d;
    }

    /* renamed from: g */
    final boolean m175g() {
        return this.f214d;
    }

    public final String toString() {
        return super.toString();
    }
}
