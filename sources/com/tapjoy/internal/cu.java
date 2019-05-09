package com.tapjoy.internal;

public final class cu {
    /* renamed from: a */
    private static void m7341a(Throwable th, Class cls) {
        if (th != null && cls.isInstance(th)) {
            throw ((Throwable) cls.cast(th));
        }
    }

    /* renamed from: a */
    public static RuntimeException m7340a(Throwable th) {
        Throwable th2 = (Throwable) cs.m7336a(th);
        m7341a(th2, Error.class);
        m7341a(th2, RuntimeException.class);
        throw new RuntimeException(th);
    }
}
