// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.MainThread;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import java.lang.ref.WeakReference;
import java.util.List;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;
import android.support.annotation.NonNull;
import android.app.Activity;
import java.util.concurrent.CancellationException;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.concurrent.GuardedBy;

final class zzu<TResult> extends Task<TResult>
{
    private final Object mLock;
    @GuardedBy("mLock")
    private TResult zzaa;
    @GuardedBy("mLock")
    private Exception zzab;
    private final zzr<TResult> zzx;
    @GuardedBy("mLock")
    private boolean zzy;
    private volatile boolean zzz;
    
    zzu() {
        this.mLock = new Object();
        this.zzx = new zzr<TResult>();
    }
    
    @GuardedBy("mLock")
    private final void zzb() {
        Preconditions.checkState(this.zzy, (Object)"Task is not yet complete");
    }
    
    @GuardedBy("mLock")
    private final void zzc() {
        Preconditions.checkState(!this.zzy, (Object)"Task is already complete");
    }
    
    @GuardedBy("mLock")
    private final void zzd() {
        if (this.zzz) {
            throw new CancellationException("Task is already canceled.");
        }
    }
    
    private final void zze() {
        synchronized (this.mLock) {
            if (!this.zzy) {
                return;
            }
            // monitorexit(this.mLock)
            this.zzx.zza(this);
        }
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnCanceledListener(@NonNull final Activity activity, @NonNull final OnCanceledListener onCanceledListener) {
        final zzg<TResult> zzg = new zzg<TResult>(TaskExecutors.MAIN_THREAD, onCanceledListener);
        this.zzx.zza(zzg);
        zza.zza(activity).zzb(zzg);
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnCanceledListener(@NonNull final OnCanceledListener onCanceledListener) {
        return this.addOnCanceledListener(TaskExecutors.MAIN_THREAD, onCanceledListener);
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnCanceledListener(@NonNull final Executor executor, @NonNull final OnCanceledListener onCanceledListener) {
        this.zzx.zza(new zzg<TResult>(executor, onCanceledListener));
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnCompleteListener(@NonNull final Activity activity, @NonNull final OnCompleteListener<TResult> onCompleteListener) {
        final zzi<TResult> zzi = new zzi<TResult>(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzx.zza(zzi);
        zza.zza(activity).zzb(zzi);
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnCompleteListener(@NonNull final OnCompleteListener<TResult> onCompleteListener) {
        return this.addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnCompleteListener(@NonNull final Executor executor, @NonNull final OnCompleteListener<TResult> onCompleteListener) {
        this.zzx.zza(new zzi<TResult>(executor, onCompleteListener));
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnFailureListener(@NonNull final Activity activity, @NonNull final OnFailureListener onFailureListener) {
        final zzk<TResult> zzk = new zzk<TResult>(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzx.zza(zzk);
        zza.zza(activity).zzb(zzk);
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnFailureListener(@NonNull final OnFailureListener onFailureListener) {
        return this.addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnFailureListener(@NonNull final Executor executor, @NonNull final OnFailureListener onFailureListener) {
        this.zzx.zza(new zzk<TResult>(executor, onFailureListener));
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnSuccessListener(@NonNull final Activity activity, @NonNull final OnSuccessListener<? super TResult> onSuccessListener) {
        final zzm<TResult> zzm = new zzm<TResult>(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzx.zza(zzm);
        zza.zza(activity).zzb(zzm);
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnSuccessListener(@NonNull final OnSuccessListener<? super TResult> onSuccessListener) {
        return this.addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }
    
    @NonNull
    @Override
    public final Task<TResult> addOnSuccessListener(@NonNull final Executor executor, @NonNull final OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzx.zza(new zzm<TResult>(executor, onSuccessListener));
        this.zze();
        return this;
    }
    
    @NonNull
    @Override
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull final Continuation<TResult, TContinuationResult> continuation) {
        return this.continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }
    
    @NonNull
    @Override
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull final Executor executor, @NonNull final Continuation<TResult, TContinuationResult> continuation) {
        final zzu<Object> zzu = new zzu<Object>();
        this.zzx.zza(new zzc<TResult, Object>(executor, (Continuation<Object, Object>)continuation, zzu));
        this.zze();
        return (Task<TContinuationResult>)zzu;
    }
    
    @NonNull
    @Override
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull final Continuation<TResult, Task<TContinuationResult>> continuation) {
        return this.continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }
    
    @NonNull
    @Override
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull final Executor executor, @NonNull final Continuation<TResult, Task<TContinuationResult>> continuation) {
        final zzu<Object> zzu = new zzu<Object>();
        this.zzx.zza(new zze<TResult, Object>(executor, (Continuation<Object, Task<Object>>)continuation, zzu));
        this.zze();
        return (Task<TContinuationResult>)zzu;
    }
    
    @Nullable
    @Override
    public final Exception getException() {
        synchronized (this.mLock) {
            return this.zzab;
        }
    }
    
    @Override
    public final TResult getResult() {
        synchronized (this.mLock) {
            this.zzb();
            this.zzd();
            if (this.zzab != null) {
                throw new RuntimeExecutionException(this.zzab);
            }
        }
        // monitorexit(o)
        return this.zzaa;
    }
    
    @Override
    public final <X extends Throwable> TResult getResult(@NonNull final Class<X> clazz) throws X, Throwable {
        synchronized (this.mLock) {
            this.zzb();
            this.zzd();
            if (clazz.isInstance(this.zzab)) {
                throw clazz.cast(this.zzab);
            }
        }
        if (this.zzab != null) {
            throw new RuntimeExecutionException(this.zzab);
        }
        // monitorexit(o)
        return this.zzaa;
    }
    
    @Override
    public final boolean isCanceled() {
        return this.zzz;
    }
    
    @Override
    public final boolean isComplete() {
        synchronized (this.mLock) {
            return this.zzy;
        }
    }
    
    @Override
    public final boolean isSuccessful() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzy && !this.zzz && this.zzab == null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @NonNull
    @Override
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull final SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        return this.onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation);
    }
    
    @NonNull
    @Override
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Executor executor, final SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        final zzu<Object> zzu = new zzu<Object>();
        this.zzx.zza(new zzo<TResult, Object>(executor, (SuccessContinuation<Object, Object>)successContinuation, zzu));
        this.zze();
        return (Task<TContinuationResult>)zzu;
    }
    
    public final void setException(@NonNull final Exception zzab) {
        Preconditions.checkNotNull((Object)zzab, (Object)"Exception must not be null");
        synchronized (this.mLock) {
            this.zzc();
            this.zzy = true;
            this.zzab = zzab;
            // monitorexit(this.mLock)
            this.zzx.zza(this);
        }
    }
    
    public final void setResult(final TResult zzaa) {
        synchronized (this.mLock) {
            this.zzc();
            this.zzy = true;
            this.zzaa = zzaa;
            // monitorexit(this.mLock)
            this.zzx.zza(this);
        }
    }
    
    public final boolean trySetException(@NonNull final Exception zzab) {
        Preconditions.checkNotNull((Object)zzab, (Object)"Exception must not be null");
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzab = zzab;
            // monitorexit(this.mLock)
            this.zzx.zza(this);
            return true;
        }
    }
    
    public final boolean trySetResult(final TResult zzaa) {
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzaa = zzaa;
            // monitorexit(this.mLock)
            this.zzx.zza(this);
            return true;
        }
    }
    
    public final boolean zza() {
        synchronized (this.mLock) {
            if (this.zzy) {
                return false;
            }
            this.zzy = true;
            this.zzz = true;
            // monitorexit(this.mLock)
            this.zzx.zza(this);
            return true;
        }
    }
    
    private static class zza extends LifecycleCallback
    {
        private final List<WeakReference<zzq<?>>> zzac;
        
        private zza(final LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.zzac = new ArrayList<WeakReference<zzq<?>>>();
            this.mLifecycleFragment.addCallback("TaskOnStopCallback", (LifecycleCallback)this);
        }
        
        public static zza zza(final Activity activity) {
            final LifecycleFragment fragment = getFragment(activity);
            zza zza;
            if ((zza = (zza)fragment.getCallbackOrNull("TaskOnStopCallback", (Class)zza.class)) == null) {
                zza = new zza(fragment);
            }
            return zza;
        }
        
        @MainThread
        public void onStop() {
            synchronized (this.zzac) {
                final Iterator<WeakReference<zzq<?>>> iterator = this.zzac.iterator();
                while (iterator.hasNext()) {
                    final zzq zzq = iterator.next().get();
                    if (zzq != null) {
                        zzq.cancel();
                    }
                }
            }
            this.zzac.clear();
        }
        // monitorexit(list)
        
        public final <T> void zzb(final zzq<T> zzq) {
            synchronized (this.zzac) {
                this.zzac.add(new WeakReference<zzq<?>>(zzq));
            }
        }
    }
}
