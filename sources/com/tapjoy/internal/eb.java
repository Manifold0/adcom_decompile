package com.tapjoy.internal;

public enum eb implements dq {
    APP(0),
    CAMPAIGN(1),
    CUSTOM(2),
    USAGES(3);
    
    public static final dn ADAPTER = null;
    /* renamed from: a */
    private final int f7487a;

    /* renamed from: com.tapjoy.internal.eb$a */
    static final class C2886a extends dj {
        C2886a() {
            super(eb.class);
        }

        /* renamed from: a */
        protected final /* bridge */ /* synthetic */ dq mo6268a(int i) {
            return eb.m7610a(i);
        }
    }

    static {
        ADAPTER = new C2886a();
    }

    private eb(int i) {
        this.f7487a = i;
    }

    /* renamed from: a */
    public static eb m7610a(int i) {
        switch (i) {
            case 0:
                return APP;
            case 1:
                return CAMPAIGN;
            case 2:
                return CUSTOM;
            case 3:
                return USAGES;
            default:
                return null;
        }
    }

    /* renamed from: a */
    public final int mo6269a() {
        return this.f7487a;
    }
}
