// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.Nullable;
import java.util.concurrent.Executor;
import android.support.annotation.NonNull;
import android.app.Activity;

public abstract class Task<TResult>
{
    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull final Activity activity, @NonNull final OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }
    
    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull final OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }
    
    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull final Executor executor, @NonNull final OnCanceledListener onCanceledListener) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }
    
    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull final Activity activity, @NonNull final OnCompleteListener<TResult> onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }
    
    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull final OnCompleteListener<TResult> onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }
    
    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull final Executor executor, @NonNull final OnCompleteListener<TResult> onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }
    
    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull final Activity p0, @NonNull final OnFailureListener p1);
    
    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull final OnFailureListener p0);
    
    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull final Executor p0, @NonNull final OnFailureListener p1);
    
    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull final Activity p0, @NonNull final OnSuccessListener<? super TResult> p1);
    
    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull final OnSuccessListener<? super TResult> p0);
    
    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull final Executor p0, @NonNull final OnSuccessListener<? super TResult> p1);
    
    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull final Continuation<TResult, TContinuationResult> continuation) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }
    
    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull final Executor executor, @NonNull final Continuation<TResult, TContinuationResult> continuation) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }
    
    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull final Continuation<TResult, Task<TContinuationResult>> continuation) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }
    
    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull final Executor executor, @NonNull final Continuation<TResult, Task<TContinuationResult>> continuation) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }
    
    @Nullable
    public abstract Exception getException();
    
    @Nullable
    public abstract TResult getResult();
    
    @Nullable
    public abstract <X extends Throwable> TResult getResult(@NonNull final Class<X> p0) throws X, Throwable;
    
    public abstract boolean isCanceled();
    
    public abstract boolean isComplete();
    
    public abstract boolean isSuccessful();
    
    @NonNull
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull final SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
    
    @NonNull
    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull final Executor executor, @NonNull final SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
}
