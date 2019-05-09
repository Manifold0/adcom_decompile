// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface SuccessContinuation<TResult, TContinuationResult>
{
    @NonNull
    Task<TContinuationResult> then(@Nullable final TResult p0) throws Exception;
}
