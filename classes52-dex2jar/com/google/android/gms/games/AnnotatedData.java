// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.support.annotation.Nullable;

public class AnnotatedData<T>
{
    private final T data;
    private final boolean zzi;
    
    public AnnotatedData(@Nullable final T data, final boolean zzi) {
        this.data = data;
        this.zzi = zzi;
    }
    
    @Nullable
    public T get() {
        return this.data;
    }
    
    public boolean isStale() {
        return this.zzi;
    }
}
