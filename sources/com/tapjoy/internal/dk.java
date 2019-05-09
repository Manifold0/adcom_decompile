package com.tapjoy.internal;

public enum dk {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);
    
    /* renamed from: e */
    final int f7360e;

    private dk(int i) {
        this.f7360e = i;
    }

    /* renamed from: a */
    public final dn m7402a() {
        switch (this) {
            case VARINT:
                return dn.f7343j;
            case FIXED32:
                return dn.f7340g;
            case FIXED64:
                return dn.f7345l;
            case LENGTH_DELIMITED:
                return dn.f7350q;
            default:
                throw new AssertionError();
        }
    }
}
