package com.tapjoy.internal;

public final class ad {
    /* renamed from: a */
    public static Object m7146a(bg bgVar) {
        int i = 1;
        while (true) {
            try {
                break;
            } catch (OutOfMemoryError e) {
                if (i >= 10) {
                    throw e;
                }
                System.gc();
                i++;
            }
        }
        return bgVar.call();
    }
}
