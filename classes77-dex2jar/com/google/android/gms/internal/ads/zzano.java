// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzano
{
    public static <V> zzanz<V> zza(final zzanz<V> zzanz, final long n, final TimeUnit timeUnit, final ScheduledExecutorService scheduledExecutorService) {
        final zzaoj<Object> zzaoj = new zzaoj<Object>();
        zza(zzaoj, (Future<Object>)zzanz);
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new zzans(zzaoj), n, timeUnit);
        zza((zzanz<? extends A>)zzanz, (zzaoj<A>)zzaoj);
        zzaoj.zza(new zzant(schedule), zzaoe.zzcvz);
        return (zzanz<V>)zzaoj;
    }
    
    public static <A, B> zzanz<B> zza(final zzanz<A> zzanz, final zzanj<? super A, ? extends B> zzanj, final Executor executor) {
        final zzaoj<Object> zzaoj = (zzaoj<Object>)new zzaoj<B>();
        zzanz.zza(new zzanr(zzaoj, zzanj, zzanz), executor);
        zza(zzaoj, zzanz);
        return (zzanz<B>)zzaoj;
    }
    
    public static <A, B> zzanz<B> zza(final zzanz<A> zzanz, final zzank<A, B> zzank, final Executor executor) {
        final zzaoj<Object> zzaoj = (zzaoj<Object>)new zzaoj<B>();
        zzanz.zza(new zzanq(zzaoj, zzank, zzanz), executor);
        zza(zzaoj, zzanz);
        return (zzanz<B>)zzaoj;
    }
    
    public static <V, X extends Throwable> zzanz<V> zza(final zzanz<? extends V> zzanz, final Class<X> clazz, final zzanj<? super X, ? extends V> zzanj, final Executor executor) {
        final zzaoj<Object> zzaoj = (zzaoj<Object>)new zzaoj<V>();
        zza((zzanz<V>)zzaoj, (Future<Object>)zzanz);
        zzanz.zza(new zzanu(zzaoj, zzanz, clazz, zzanj, executor), zzaoe.zzcvz);
        return (zzanz<V>)zzaoj;
    }
    
    public static <T> T zza(final Future<T> future, final T t) {
        try {
            return future.get((long)zzkb.zzik().zzd(zznk.zzbam), TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException ex) {
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", (Throwable)ex);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zzb(ex, "Futures.resolveFuture");
            return t;
        }
        catch (Exception ex2) {
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", (Throwable)ex2);
            zzbv.zzeo().zzb(ex2, "Futures.resolveFuture");
            return t;
        }
    }
    
    public static <T> T zza(final Future<T> future, final T t, final long n, final TimeUnit timeUnit) {
        try {
            return future.get(n, timeUnit);
        }
        catch (InterruptedException ex) {
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", (Throwable)ex);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zza(ex, "Futures.resolveFuture");
            return t;
        }
        catch (Exception ex2) {
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", (Throwable)ex2);
            zzbv.zzeo().zza(ex2, "Futures.resolveFuture");
            return t;
        }
    }
    
    public static <V> void zza(final zzanz<V> zzanz, final zzanl<V> zzanl, final Executor executor) {
        zzanz.zza(new zzanp(zzanl, zzanz), executor);
    }
    
    private static <V> void zza(final zzanz<? extends V> zzanz, final zzaoj<V> zzaoj) {
        zza(zzaoj, (Future<Object>)zzanz);
        zzanz.zza(new zzanv(zzaoj, zzanz), zzaoe.zzcvz);
    }
    
    private static <A, B> void zza(final zzanz<A> zzanz, final Future<B> future) {
        zzanz.zza(new zzanw(zzanz, future), zzaoe.zzcvz);
    }
    
    public static <T> zzanx<T> zzd(final Throwable t) {
        return new zzanx<T>(t);
    }
    
    public static <T> zzany<T> zzi(final T t) {
        return new zzany<T>(t);
    }
}
