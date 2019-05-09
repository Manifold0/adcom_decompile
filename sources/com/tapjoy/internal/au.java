package com.tapjoy.internal;

public final class au {

    /* renamed from: com.tapjoy.internal.au$a */
    public static final class C2839a implements ax {
        /* renamed from: a */
        private final av f7211a;

        public C2839a(av avVar) {
            this.f7211a = avVar;
        }

        /* renamed from: a */
        public final Object mo6179a(Object obj) {
            synchronized (this.f7211a) {
                at a = this.f7211a.mo6181a(obj, false);
            }
            if (a == null) {
                return null;
            }
            Object a2;
            synchronized (a) {
                a2 = a.mo6177a();
            }
            return a2;
        }

        /* renamed from: a */
        public final void mo6180a(Object obj, Object obj2) {
            synchronized (this.f7211a) {
                at a = this.f7211a.mo6181a(obj, true);
            }
            synchronized (a) {
                a.mo6178a(obj2);
            }
        }
    }
}
