package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;

public class af<T> {
    /* renamed from: a */
    public final T f2993a;
    /* renamed from: b */
    public final CBError f2994b;

    /* renamed from: a */
    public static <T> af<T> m3370a(T t) {
        return new af(t, null);
    }

    /* renamed from: a */
    public static <T> af<T> m3369a(CBError cBError) {
        return new af(null, cBError);
    }

    private af(T t, CBError cBError) {
        this.f2993a = t;
        this.f2994b = cBError;
    }
}
