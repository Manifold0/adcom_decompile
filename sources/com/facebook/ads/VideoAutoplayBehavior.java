package com.facebook.ads;

import com.facebook.ads.internal.p017t.C2119l;

public enum VideoAutoplayBehavior {
    DEFAULT,
    ON,
    OFF;

    public static VideoAutoplayBehavior fromInternalAutoplayBehavior(C2119l c2119l) {
        if (c2119l == null) {
            return DEFAULT;
        }
        switch (c2119l) {
            case f4896a:
                return DEFAULT;
            case ON:
                return ON;
            case OFF:
                return OFF;
            default:
                return DEFAULT;
        }
    }
}
