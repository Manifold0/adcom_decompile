package com.google.android.gms.games;

import android.support.annotation.Nullable;

public class AnnotatedData<T> {
    private final T data;
    private final boolean zzi;

    public AnnotatedData(@Nullable T t, boolean z) {
        this.data = t;
        this.zzi = z;
    }

    @Nullable
    public T get() {
        return this.data;
    }

    public boolean isStale() {
        return this.zzi;
    }
}
