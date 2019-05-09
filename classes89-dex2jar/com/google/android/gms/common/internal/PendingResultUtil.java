// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PendingResultUtil
{
    private static final zaa zaou;
    
    static {
        zaou = (zaa)new zai();
    }
    
    @KeepForSdk
    public static <R extends Result, T extends Response<R>> Task<T> toResponseTask(final PendingResult<R> pendingResult, final T t) {
        return toTask(pendingResult, (ResultConverter<R, T>)new zak(t));
    }
    
    @KeepForSdk
    public static <R extends Result, T> Task<T> toTask(final PendingResult<R> pendingResult, final ResultConverter<R, T> resultConverter) {
        final zaa zaou = PendingResultUtil.zaou;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener((PendingResult.StatusListener)new zaj(pendingResult, taskCompletionSource, (ResultConverter)resultConverter, zaou));
        return (Task<T>)taskCompletionSource.getTask();
    }
    
    @KeepForSdk
    public static <R extends Result> Task<Void> toVoidTask(final PendingResult<R> pendingResult) {
        return toTask(pendingResult, (ResultConverter<R, Void>)new zal());
    }
    
    @KeepForSdk
    public interface ResultConverter<R extends Result, T>
    {
        @KeepForSdk
        T convert(final R p0);
    }
    
    public interface zaa
    {
        ApiException zaf(final Status p0);
    }
}
