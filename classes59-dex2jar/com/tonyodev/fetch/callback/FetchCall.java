// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch.callback;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import com.tonyodev.fetch.request.Request;

public interface FetchCall<T>
{
    void onError(final int p0, @NonNull final Request p1);
    
    void onSuccess(@Nullable final T p0, @NonNull final Request p1);
}
