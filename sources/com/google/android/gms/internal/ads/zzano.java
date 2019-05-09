package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzano {
    public static <V> zzanz<V> zza(zzanz<V> zzanz, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzaoj zzaoj = new zzaoj();
        zza((zzanz) zzaoj, (Future) zzanz);
        Future schedule = scheduledExecutorService.schedule(new zzans(zzaoj), j, timeUnit);
        zza((zzanz) zzanz, zzaoj);
        zzaoj.zza(new zzant(schedule), zzaoe.zzcvz);
        return zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> zzanz, zzanj<? super A, ? extends B> zzanj, Executor executor) {
        zzanz zzaoj = new zzaoj();
        zzanz.zza(new zzanr(zzaoj, zzanj, zzanz), executor);
        zza(zzaoj, (Future) zzanz);
        return zzaoj;
    }

    public static <A, B> zzanz<B> zza(zzanz<A> zzanz, zzank<A, B> zzank, Executor executor) {
        zzanz zzaoj = new zzaoj();
        zzanz.zza(new zzanq(zzaoj, zzank, zzanz), executor);
        zza(zzaoj, (Future) zzanz);
        return zzaoj;
    }

    public static <V, X extends Throwable> zzanz<V> zza(zzanz<? extends V> zzanz, Class<X> cls, zzanj<? super X, ? extends V> zzanj, Executor executor) {
        zzanz zzaoj = new zzaoj();
        zza(zzaoj, (Future) zzanz);
        zzanz.zza(new zzanu(zzaoj, zzanz, cls, zzanj, executor), zzaoe.zzcvz);
        return zzaoj;
    }

    public static <T> T zza(Future<T> future, T t) {
        try {
            t = future.get(((Long) zzkb.zzik().zzd(zznk.zzbam)).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zzb(e, "Futures.resolveFuture");
        } catch (Throwable e2) {
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", e2);
            zzbv.zzeo().zzb(e2, "Futures.resolveFuture");
        }
        return t;
    }

    public static <T> T zza(Future<T> future, T t, long j, TimeUnit timeUnit) {
        try {
            t = future.get(j, timeUnit);
        } catch (Throwable e) {
            future.cancel(true);
            zzakb.zzc("InterruptedException caught while resolving future.", e);
            Thread.currentThread().interrupt();
            zzbv.zzeo().zza(e, "Futures.resolveFuture");
        } catch (Throwable e2) {
            future.cancel(true);
            zzakb.zzb("Error waiting for future.", e2);
            zzbv.zzeo().zza(e2, "Futures.resolveFuture");
        }
        return t;
    }

    public static <V> void zza(zzanz<V> zzanz, zzanl<V> zzanl, Executor executor) {
        zzanz.zza(new zzanp(zzanl, zzanz), executor);
    }

    private static <V> void zza(zzanz<? extends V> zzanz, zzaoj<V> zzaoj) {
        zza((zzanz) zzaoj, (Future) zzanz);
        zzanz.zza(new zzanv(zzaoj, zzanz), zzaoe.zzcvz);
    }

    private static <A, B> void zza(zzanz<A> zzanz, Future<B> future) {
        zzanz.zza(new zzanw(zzanz, future), zzaoe.zzcvz);
    }

    static final /* synthetic */ void zza(zzaoj zzaoj, zzanj zzanj, zzanz zzanz) {
        if (!zzaoj.isCancelled()) {
            try {
                zza(zzanj.zzc(zzanz.get()), zzaoj);
            } catch (CancellationException e) {
                zzaoj.cancel(true);
            } catch (ExecutionException e2) {
                zzaoj.setException(e2.getCause());
            } catch (Throwable e3) {
                Thread.currentThread().interrupt();
                zzaoj.setException(e3);
            } catch (Throwable e32) {
                zzaoj.setException(e32);
            }
        }
    }

    static final /* synthetic */ void zza(zzaoj zzaoj, zzanz zzanz, Class cls, zzanj zzanj, Executor executor) {
        Throwable cause;
        try {
            zzaoj.set(zzanz.get());
            return;
        } catch (ExecutionException e) {
            cause = e.getCause();
        } catch (InterruptedException e2) {
            cause = e2;
            Thread.currentThread().interrupt();
        } catch (Exception e3) {
            cause = e3;
        }
        if (cls.isInstance(cause)) {
            zza(zza(zzi(cause), zzanj, executor), zzaoj);
        } else {
            zzaoj.setException(cause);
        }
    }

    public static <T> zzanx<T> zzd(Throwable th) {
        return new zzanx(th);
    }

    public static <T> zzany<T> zzi(T t) {
        return new zzany(t);
    }
}
