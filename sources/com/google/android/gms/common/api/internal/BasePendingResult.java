package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepName
@KeepForSdk
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zadn = new zap();
    @KeepName
    private zaa mResultGuardian;
    private Status mStatus;
    private R zacj;
    private final Object zado;
    private final CallbackHandler<R> zadp;
    private final WeakReference<GoogleApiClient> zadq;
    private final CountDownLatch zadr;
    private final ArrayList<StatusListener> zads;
    private ResultCallback<? super R> zadt;
    private final AtomicReference<zacs> zadu;
    private volatile boolean zadv;
    private boolean zadw;
    private boolean zadx;
    private ICancelToken zady;
    private volatile zacm<R> zadz;
    private boolean zaea;

    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends zap {
        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public final void zaa(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    ResultCallback resultCallback = (ResultCallback) pair.first;
                    Result result = (Result) pair.second;
                    try {
                        resultCallback.onResult(result);
                        return;
                    } catch (RuntimeException e) {
                        BasePendingResult.zab(result);
                        throw e;
                    }
                case 2:
                    ((BasePendingResult) message.obj).zab(Status.RESULT_TIMEOUT);
                    return;
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
            }
        }
    }

    private final class zaa {
        private final /* synthetic */ BasePendingResult zaeb;

        private zaa(BasePendingResult basePendingResult) {
            this.zaeb = basePendingResult;
        }

        protected final void finalize() throws Throwable {
            BasePendingResult.zab(this.zaeb.zacj);
            super.finalize();
        }
    }

    @Deprecated
    BasePendingResult() {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList();
        this.zadu = new AtomicReference();
        this.zaea = false;
        this.zadp = new CallbackHandler(Looper.getMainLooper());
        this.zadq = new WeakReference(null);
    }

    @KeepForSdk
    @NonNull
    protected abstract R createFailedResult(Status status);

    @KeepForSdk
    protected BasePendingResult(GoogleApiClient googleApiClient) {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList();
        this.zadu = new AtomicReference();
        this.zaea = false;
        this.zadp = new CallbackHandler(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zadq = new WeakReference(googleApiClient);
    }

    @KeepForSdk
    @Deprecated
    protected BasePendingResult(Looper looper) {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList();
        this.zadu = new AtomicReference();
        this.zaea = false;
        this.zadp = new CallbackHandler(looper);
        this.zadq = new WeakReference(null);
    }

    @KeepForSdk
    @VisibleForTesting
    protected BasePendingResult(@NonNull CallbackHandler<R> callbackHandler) {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList();
        this.zadu = new AtomicReference();
        this.zaea = false;
        this.zadp = (CallbackHandler) Preconditions.checkNotNull(callbackHandler, "CallbackHandler must not be null");
        this.zadq = new WeakReference(null);
    }

    @KeepForSdk
    public final boolean isReady() {
        return this.zadr.getCount() == 0;
    }

    public final R await() {
        boolean z = true;
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        Preconditions.checkState(!this.zadv, "Result has already been consumed");
        if (this.zadz != null) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot await if then() has been called.");
        try {
            this.zadr.await();
        } catch (InterruptedException e) {
            zab(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        if (j > 0) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        Preconditions.checkState(!this.zadv, "Result has already been consumed.");
        if (this.zadz != null) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot await if then() has been called.");
        try {
            if (!this.zadr.await(j, timeUnit)) {
                zab(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException e) {
            zab(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.annotation.KeepForSdk
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r3 = r5.zado;
        monitor-enter(r3);
        if (r6 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r5.zadt = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r5.zadv;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.Preconditions.checkState(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r5.zadz;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.Preconditions.checkState(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r5.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r5.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r5.zadp;	 Catch:{ all -> 0x0027 }
        r1 = r5.get();	 Catch:{ all -> 0x0027 }
        r0.zaa(r6, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r5.zadt = r6;	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.annotation.KeepForSdk
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r3 = r6.zado;
        monitor-enter(r3);
        if (r7 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r6.zadt = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r6.zadv;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.Preconditions.checkState(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r6.zadz;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.Preconditions.checkState(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r6.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r6.zadp;	 Catch:{ all -> 0x0027 }
        r1 = r6.get();	 Catch:{ all -> 0x0027 }
        r0.zaa(r7, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r6.zadt = r7;	 Catch:{ all -> 0x0027 }
        r0 = r6.zadp;	 Catch:{ all -> 0x0027 }
        r4 = r10.toMillis(r8);	 Catch:{ all -> 0x0027 }
        r1 = 2;
        r1 = r0.obtainMessage(r1, r6);	 Catch:{ all -> 0x0027 }
        r0.sendMessageDelayed(r1, r4);	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public final void addStatusListener(StatusListener statusListener) {
        Preconditions.checkArgument(statusListener != null, "Callback cannot be null.");
        synchronized (this.zado) {
            if (isReady()) {
                statusListener.onComplete(this.mStatus);
            } else {
                this.zads.add(statusListener);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.annotation.KeepForSdk
    public void cancel() {
        /*
        r2 = this;
        r1 = r2.zado;
        monitor-enter(r1);
        r0 = r2.zadw;	 Catch:{ all -> 0x0029 }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.zadv;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.zady;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r2.zady;	 Catch:{ RemoteException -> 0x002c }
        r0.cancel();	 Catch:{ RemoteException -> 0x002c }
    L_0x0016:
        r0 = r2.zacj;	 Catch:{ all -> 0x0029 }
        zab(r0);	 Catch:{ all -> 0x0029 }
        r0 = 1;
        r2.zadw = r0;	 Catch:{ all -> 0x0029 }
        r0 = com.google.android.gms.common.api.Status.RESULT_CANCELED;	 Catch:{ all -> 0x0029 }
        r0 = r2.createFailedResult(r0);	 Catch:{ all -> 0x0029 }
        r2.zaa(r0);	 Catch:{ all -> 0x0029 }
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        goto L_0x000c;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.cancel():void");
    }

    public final boolean zat() {
        boolean isCanceled;
        synchronized (this.zado) {
            if (((GoogleApiClient) this.zadq.get()) == null || !this.zaea) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zado) {
            z = this.zadw;
        }
        return z;
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        Preconditions.checkState(!this.zadv, "Result has already been consumed.");
        synchronized (this.zado) {
            boolean z2;
            if (this.zadz == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkState(z2, "Cannot call then() twice.");
            if (this.zadt == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkState(z2, "Cannot call then() if callbacks are set.");
            if (this.zadw) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() if result was canceled.");
            this.zaea = true;
            this.zadz = new zacm(this.zadq);
            then = this.zadz.then(resultTransform);
            if (isReady()) {
                this.zadp.zaa(this.zadz, get());
            } else {
                this.zadt = this.zadz;
            }
        }
        return then;
    }

    @KeepForSdk
    public final void setResult(R r) {
        boolean z = true;
        synchronized (this.zado) {
            if (this.zadx || this.zadw) {
                zab((Result) r);
                return;
            }
            boolean z2;
            if (isReady()) {
            }
            if (isReady()) {
                z2 = false;
            } else {
                z2 = true;
            }
            Preconditions.checkState(z2, "Results have already been set");
            if (this.zadv) {
                z = false;
            }
            Preconditions.checkState(z, "Result has already been consumed");
            zaa((Result) r);
        }
    }

    public final void zab(Status status) {
        synchronized (this.zado) {
            if (!isReady()) {
                setResult(createFailedResult(status));
                this.zadx = true;
            }
        }
    }

    public final void zaa(zacs zacs) {
        this.zadu.set(zacs);
    }

    public final Integer zam() {
        return null;
    }

    @KeepForSdk
    protected final void setCancelToken(ICancelToken iCancelToken) {
        synchronized (this.zado) {
            this.zady = iCancelToken;
        }
    }

    public final void zau() {
        boolean z = this.zaea || ((Boolean) zadn.get()).booleanValue();
        this.zaea = z;
    }

    private final R get() {
        R r;
        boolean z = true;
        synchronized (this.zado) {
            if (this.zadv) {
                z = false;
            }
            Preconditions.checkState(z, "Result has already been consumed.");
            Preconditions.checkState(isReady(), "Result is not ready.");
            r = this.zacj;
            this.zacj = null;
            this.zadt = null;
            this.zadv = true;
        }
        zacs zacs = (zacs) this.zadu.getAndSet(null);
        if (zacs != null) {
            zacs.zac(this);
        }
        return r;
    }

    private final void zaa(R r) {
        this.zacj = r;
        this.zady = null;
        this.zadr.countDown();
        this.mStatus = this.zacj.getStatus();
        if (this.zadw) {
            this.zadt = null;
        } else if (this.zadt != null) {
            this.zadp.removeMessages(2);
            this.zadp.zaa(this.zadt, get());
        } else if (this.zacj instanceof Releasable) {
            this.mResultGuardian = new zaa();
        }
        ArrayList arrayList = this.zads;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((StatusListener) obj).onComplete(this.mStatus);
        }
        this.zads.clear();
    }

    public static void zab(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }
}
