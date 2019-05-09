// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public interface Continuation<TResult, TContinuationResult>
{
    TContinuationResult then(@NonNull final Task<TResult> p0) throws Exception;
}
