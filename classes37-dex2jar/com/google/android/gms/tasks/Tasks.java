// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CancellationException;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;

public final class Tasks
{
    private Tasks() {
    }
    
    public static <TResult> TResult await(@NonNull final Task<TResult> task) throws ExecutionException, InterruptedException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull((Object)task, (Object)"Task must not be null");
        if (task.isComplete()) {
            return (TResult)zzb((Task<Object>)task);
        }
        final zza zza = new zza(null);
        zza(task, (zzb)zza);
        zza.await();
        return (TResult)zzb((Task<Object>)task);
    }
    
    public static <TResult> TResult await(@NonNull final Task<TResult> task, final long n, @NonNull final TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotNull((Object)task, (Object)"Task must not be null");
        Preconditions.checkNotNull((Object)timeUnit, (Object)"TimeUnit must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        final zza zza = new zza(null);
        zza(task, (zzb)zza);
        if (!zza.await(n, timeUnit)) {
            throw new TimeoutException("Timed out waiting for Task");
        }
        return zzb(task);
    }
    
    public static <TResult> Task<TResult> call(@NonNull final Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }
    
    public static <TResult> Task<TResult> call(@NonNull final Executor executor, @NonNull final Callable<TResult> callable) {
        Preconditions.checkNotNull((Object)executor, (Object)"Executor must not be null");
        Preconditions.checkNotNull((Object)callable, (Object)"Callback must not be null");
        final zzu<TResult> zzu = new zzu<TResult>();
        executor.execute(new zzv(zzu, callable));
        return zzu;
    }
    
    public static <TResult> Task<TResult> forCanceled() {
        final zzu<TResult> zzu = new zzu<TResult>();
        zzu.zza();
        return zzu;
    }
    
    public static <TResult> Task<TResult> forException(@NonNull final Exception exception) {
        final zzu<TResult> zzu = new zzu<TResult>();
        zzu.setException(exception);
        return zzu;
    }
    
    public static <TResult> Task<TResult> forResult(final TResult result) {
        final zzu<TResult> zzu = new zzu<TResult>();
        zzu.setResult(result);
        return zzu;
    }
    
    public static Task<Void> whenAll(final Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult((Void)null);
        }
        final Iterator<? extends Task<?>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        final zzu<Void> zzu = new zzu<Void>();
        final zzc zzc = new zzc(collection.size(), zzu);
        final Iterator<? extends Task<?>> iterator2 = collection.iterator();
        while (iterator2.hasNext()) {
            zza((Task<?>)iterator2.next(), (zzb)zzc);
        }
        return zzu;
    }
    
    public static Task<Void> whenAll(final Task<?>... array) {
        if (array.length == 0) {
            return forResult((Void)null);
        }
        return whenAll(Arrays.asList(array));
    }
    
    public static Task<List<Task<?>>> whenAllComplete(final Collection<? extends Task<?>> collection) {
        return whenAll(collection).continueWithTask((Continuation<Void, Task<List<Task<?>>>>)new zzx(collection));
    }
    
    public static Task<List<Task<?>>> whenAllComplete(final Task<?>... array) {
        return whenAllComplete(Arrays.asList(array));
    }
    
    public static <TResult> Task<List<TResult>> whenAllSuccess(final Collection<? extends Task<?>> collection) {
        return whenAll(collection).continueWith((Continuation<Void, List<TResult>>)new zzw(collection));
    }
    
    public static <TResult> Task<List<TResult>> whenAllSuccess(final Task<?>... array) {
        return whenAllSuccess(Arrays.asList(array));
    }
    
    private static void zza(final Task<?> task, final zzb zzb) {
        task.addOnSuccessListener(TaskExecutors.zzw, zzb);
        task.addOnFailureListener(TaskExecutors.zzw, zzb);
        task.addOnCanceledListener(TaskExecutors.zzw, zzb);
    }
    
    private static <TResult> TResult zzb(final Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (task.isCanceled()) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(task.getException());
    }
    
    private static final class zza implements zzb
    {
        private final CountDownLatch zzaf;
        
        private zza() {
            this.zzaf = new CountDownLatch(1);
        }
        
        public final void await() throws InterruptedException {
            this.zzaf.await();
        }
        
        public final boolean await(final long n, final TimeUnit timeUnit) throws InterruptedException {
            return this.zzaf.await(n, timeUnit);
        }
        
        @Override
        public final void onCanceled() {
            this.zzaf.countDown();
        }
        
        @Override
        public final void onFailure(@NonNull final Exception ex) {
            this.zzaf.countDown();
        }
        
        @Override
        public final void onSuccess(final Object o) {
            this.zzaf.countDown();
        }
    }
    
    interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object>
    {
    }
    
    private static final class zzc implements zzb
    {
        private final Object mLock;
        private final zzu<Void> zza;
        @GuardedBy("mLock")
        private Exception zzab;
        private final int zzag;
        @GuardedBy("mLock")
        private int zzah;
        @GuardedBy("mLock")
        private int zzai;
        @GuardedBy("mLock")
        private int zzaj;
        @GuardedBy("mLock")
        private boolean zzak;
        
        public zzc(final int zzag, final zzu<Void> zza) {
            this.mLock = new Object();
            this.zzag = zzag;
            this.zza = zza;
        }
        
        @GuardedBy("mLock")
        private final void zzf() {
            if (this.zzah + this.zzai + this.zzaj == this.zzag) {
                if (this.zzab != null) {
                    this.zza.setException(new ExecutionException(new StringBuilder(54).append(this.zzai).append(" out of ").append(this.zzag).append(" underlying tasks failed").toString(), this.zzab));
                }
                else {
                    if (this.zzak) {
                        this.zza.zza();
                        return;
                    }
                    this.zza.setResult(null);
                }
            }
        }
        
        @Override
        public final void onCanceled() {
            synchronized (this.mLock) {
                ++this.zzaj;
                this.zzak = true;
                this.zzf();
            }
        }
        
        @Override
        public final void onFailure(@NonNull final Exception zzab) {
            synchronized (this.mLock) {
                ++this.zzai;
                this.zzab = zzab;
                this.zzf();
            }
        }
        
        @Override
        public final void onSuccess(final Object o) {
            synchronized (this.mLock) {
                ++this.zzah;
                this.zzf();
            }
        }
    }
}
