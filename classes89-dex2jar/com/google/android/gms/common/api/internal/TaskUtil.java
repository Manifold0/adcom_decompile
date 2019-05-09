// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class TaskUtil
{
    @KeepForSdk
    public static void setResultOrApiException(final Status status, final TaskCompletionSource<Void> taskCompletionSource) {
        setResultOrApiException(status, null, (com.google.android.gms.tasks.TaskCompletionSource<Object>)taskCompletionSource);
    }
    
    @KeepForSdk
    public static <TResult> void setResultOrApiException(final Status status, final TResult result, final TaskCompletionSource<TResult> taskCompletionSource) {
        if (status.isSuccess()) {
            taskCompletionSource.setResult((Object)result);
            return;
        }
        taskCompletionSource.setException((Exception)new ApiException(status));
    }
    
    @Deprecated
    @KeepForSdk
    public static Task<Void> toVoidTaskThatFailsOnFalse(final Task<Boolean> task) {
        return (Task<Void>)task.continueWith((Continuation)new zacl());
    }
}
