// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.util.Pair;
import android.os.Message;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.api.ResultTransform;
import java.util.concurrent.TimeUnit;
import android.util.Log;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import android.os.Looper;
import com.google.android.gms.common.internal.ICancelToken;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.api.ResultCallback;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import com.google.android.gms.common.api.GoogleApiClient;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

@KeepForSdk
@KeepName
public abstract class BasePendingResult<R extends Result> extends PendingResult<R>
{
    static final ThreadLocal<Boolean> zadn;
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
    
    static {
        zadn = new zap();
    }
    
    @Deprecated
    BasePendingResult() {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList<StatusListener>();
        this.zadu = new AtomicReference<zacs>();
        this.zaea = false;
        this.zadp = new CallbackHandler<R>(Looper.getMainLooper());
        this.zadq = new WeakReference<GoogleApiClient>(null);
    }
    
    @Deprecated
    @KeepForSdk
    protected BasePendingResult(final Looper looper) {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList<StatusListener>();
        this.zadu = new AtomicReference<zacs>();
        this.zaea = false;
        this.zadp = new CallbackHandler<R>(looper);
        this.zadq = new WeakReference<GoogleApiClient>(null);
    }
    
    @KeepForSdk
    protected BasePendingResult(final GoogleApiClient googleApiClient) {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList<StatusListener>();
        this.zadu = new AtomicReference<zacs>();
        this.zaea = false;
        Looper looper;
        if (googleApiClient != null) {
            looper = googleApiClient.getLooper();
        }
        else {
            looper = Looper.getMainLooper();
        }
        this.zadp = new CallbackHandler<R>(looper);
        this.zadq = new WeakReference<GoogleApiClient>(googleApiClient);
    }
    
    @KeepForSdk
    @VisibleForTesting
    protected BasePendingResult(@NonNull final CallbackHandler<R> callbackHandler) {
        this.zado = new Object();
        this.zadr = new CountDownLatch(1);
        this.zads = new ArrayList<StatusListener>();
        this.zadu = new AtomicReference<zacs>();
        this.zaea = false;
        this.zadp = (CallbackHandler<R>)Preconditions.checkNotNull((Object)callbackHandler, (Object)"CallbackHandler must not be null");
        this.zadq = new WeakReference<GoogleApiClient>(null);
    }
    
    private final R get() {
        boolean b = true;
        Object zado = this.zado;
        synchronized (zado) {
            if (this.zadv) {
                b = false;
            }
            Preconditions.checkState(b, (Object)"Result has already been consumed.");
            Preconditions.checkState(this.isReady(), (Object)"Result is not ready.");
            final Result zacj = this.zacj;
            this.zacj = null;
            this.zadt = null;
            this.zadv = true;
            // monitorexit(zado)
            zado = this.zadu.getAndSet(null);
            if (zado != null) {
                ((zacs)zado).zac(this);
            }
            return (R)zacj;
        }
    }
    
    private final void zaa(final R zacj) {
        this.zacj = zacj;
        this.zady = null;
        this.zadr.countDown();
        this.mStatus = this.zacj.getStatus();
        if (this.zadw) {
            this.zadt = null;
        }
        else if (this.zadt == null) {
            if (this.zacj instanceof Releasable) {
                this.mResultGuardian = new zaa((zap)null);
            }
        }
        else {
            this.zadp.removeMessages(2);
            this.zadp.zaa(this.zadt, this.get());
        }
        final ArrayList<StatusListener> list = this.zads;
        final int size = list.size();
        int i = 0;
        while (i < size) {
            final StatusListener value = list.get(i);
            ++i;
            value.onComplete(this.mStatus);
        }
        this.zads.clear();
    }
    
    public static void zab(final Result result) {
        if (!(result instanceof Releasable)) {
            return;
        }
        try {
            ((Releasable)result).release();
        }
        catch (RuntimeException ex) {
            final String value = String.valueOf(result);
            Log.w("BasePendingResult", new StringBuilder(String.valueOf(value).length() + 18).append("Unable to release ").append(value).toString(), (Throwable)ex);
        }
    }
    
    @Override
    public final void addStatusListener(final StatusListener statusListener) {
        while (true) {
            Label_0056: {
                if (statusListener == null) {
                    break Label_0056;
                }
                final boolean b = true;
                Preconditions.checkArgument(b, (Object)"Callback cannot be null.");
                synchronized (this.zado) {
                    if (this.isReady()) {
                        statusListener.onComplete(this.mStatus);
                    }
                    else {
                        this.zads.add(statusListener);
                    }
                    return;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    @Override
    public final R await() {
        final boolean b = true;
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        while (true) {
            while (true) {
                boolean b2 = false;
                Label_0017: {
                    if (!this.zadv) {
                        b2 = true;
                        break Label_0017;
                    }
                    Label_0061: {
                        break Label_0061;
                    Label_0047_Outer:
                        while (true) {
                            boolean b3 = false;
                            Preconditions.checkState(b3, (Object)"Cannot await if then() has been called.");
                            while (true) {
                                try {
                                    this.zadr.await();
                                    Preconditions.checkState(this.isReady(), (Object)"Result is not ready.");
                                    return this.get();
                                    b3 = false;
                                    continue Label_0047_Outer;
                                    b2 = false;
                                    break;
                                }
                                catch (InterruptedException ex) {
                                    this.zab(Status.RESULT_INTERRUPTED);
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
                Preconditions.checkState(b2, (Object)"Result has already been consumed");
                if (this.zadz == null) {
                    final boolean b3 = b;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public final R await(final long n, final TimeUnit timeUnit) {
        final boolean b = true;
        if (n > 0L) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        while (true) {
            while (true) {
                boolean b2 = false;
                Label_0025: {
                    if (!this.zadv) {
                        b2 = true;
                        break Label_0025;
                    }
                    Label_0084: {
                        break Label_0084;
                        while (true) {
                            boolean b3 = false;
                            Preconditions.checkState(b3, (Object)"Cannot await if then() has been called.");
                            while (true) {
                                try {
                                    if (!this.zadr.await(n, timeUnit)) {
                                        this.zab(Status.RESULT_TIMEOUT);
                                    }
                                    Preconditions.checkState(this.isReady(), (Object)"Result is not ready.");
                                    return this.get();
                                    b2 = false;
                                    break;
                                    b3 = false;
                                }
                                catch (InterruptedException ex) {
                                    this.zab(Status.RESULT_INTERRUPTED);
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
                Preconditions.checkState(b2, (Object)"Result has already been consumed.");
                if (this.zadz == null) {
                    final boolean b3 = b;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @KeepForSdk
    @Override
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/common/api/internal/BasePendingResult.zado:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/api/internal/BasePendingResult.zadw:Z
        //    11: ifne            21
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/common/api/internal/BasePendingResult.zadv:Z
        //    18: ifeq            24
        //    21: aload_1        
        //    22: monitorexit    
        //    23: return         
        //    24: aload_0        
        //    25: getfield        com/google/android/gms/common/api/internal/BasePendingResult.zady:Lcom/google/android/gms/common/internal/ICancelToken;
        //    28: astore_2       
        //    29: aload_2        
        //    30: ifnull          42
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/common/api/internal/BasePendingResult.zady:Lcom/google/android/gms/common/internal/ICancelToken;
        //    37: invokeinterface com/google/android/gms/common/internal/ICancelToken.cancel:()V
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/common/api/internal/BasePendingResult.zacj:Lcom/google/android/gms/common/api/Result;
        //    46: invokestatic    com/google/android/gms/common/api/internal/BasePendingResult.zab:(Lcom/google/android/gms/common/api/Result;)V
        //    49: aload_0        
        //    50: iconst_1       
        //    51: putfield        com/google/android/gms/common/api/internal/BasePendingResult.zadw:Z
        //    54: aload_0        
        //    55: aload_0        
        //    56: getstatic       com/google/android/gms/common/api/Status.RESULT_CANCELED:Lcom/google/android/gms/common/api/Status;
        //    59: invokevirtual   com/google/android/gms/common/api/internal/BasePendingResult.createFailedResult:(Lcom/google/android/gms/common/api/Status;)Lcom/google/android/gms/common/api/Result;
        //    62: invokespecial   com/google/android/gms/common/api/internal/BasePendingResult.zaa:(Lcom/google/android/gms/common/api/Result;)V
        //    65: aload_1        
        //    66: monitorexit    
        //    67: return         
        //    68: astore_2       
        //    69: aload_1        
        //    70: monitorexit    
        //    71: aload_2        
        //    72: athrow         
        //    73: astore_2       
        //    74: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      21     68     73     Any
        //  21     23     68     73     Any
        //  24     29     68     73     Any
        //  33     42     73     77     Landroid/os/RemoteException;
        //  33     42     68     73     Any
        //  42     67     68     73     Any
        //  69     71     68     73     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @NonNull
    @KeepForSdk
    protected abstract R createFailedResult(final Status p0);
    
    @Override
    public boolean isCanceled() {
        synchronized (this.zado) {
            return this.zadw;
        }
    }
    
    @KeepForSdk
    public final boolean isReady() {
        return this.zadr.getCount() == 0L;
    }
    
    @KeepForSdk
    protected final void setCancelToken(final ICancelToken zady) {
        synchronized (this.zado) {
            this.zady = zady;
        }
    }
    
    @KeepForSdk
    public final void setResult(final R r) {
    Label_0057_Outer:
        while (true) {
            final boolean b = true;
            while (true) {
            Label_0092:
                while (true) {
                    synchronized (this.zado) {
                        if (this.zadx || this.zadw) {
                            zab(r);
                            return;
                        }
                        if (this.isReady()) {}
                        if (!this.isReady()) {
                            final boolean b2 = true;
                            Preconditions.checkState(b2, (Object)"Results have already been set");
                            if (!this.zadv) {
                                final boolean b3 = b;
                                Preconditions.checkState(b3, (Object)"Result has already been consumed");
                                this.zaa(r);
                                return;
                            }
                            break Label_0092;
                        }
                    }
                    final boolean b2 = false;
                    continue Label_0057_Outer;
                }
                final boolean b3 = false;
                continue;
            }
        }
    }
    
    @KeepForSdk
    @Override
    public final void setResultCallback(final ResultCallback<? super R> resultCallback) {
        final boolean b = true;
        final Object zado = this.zado;
        // monitorenter(zado)
        while (true) {
            if (resultCallback == null) {
            Label_0033_Outer:
                while (true) {
                Label_0048:
                    while (true) {
                    Label_0033:
                        while (true) {
                            try {
                                this.zadt = null;
                                return;
                                // iftrue(Label_0072:, this.zadv)
                                boolean b2;
                                while (true) {
                                    b2 = true;
                                    break Label_0033;
                                    continue Label_0033_Outer;
                                }
                                Preconditions.checkState(b2, (Object)"Result has already been consumed.");
                                // iftrue(Label_0077:, this.zadz != null)
                                // iftrue(Label_0082:, !this.isCanceled())
                                Block_5: {
                                    break Block_5;
                                    final boolean b3;
                                    Preconditions.checkState(b3, (Object)"Cannot set callbacks if then() has been called.");
                                    return;
                                }
                                final boolean b3 = b;
                                continue Label_0048;
                            }
                            finally {
                            }
                            // monitorexit(zado)
                            Label_0072: {
                                final boolean b2 = false;
                            }
                            continue Label_0033;
                        }
                        Label_0077: {
                            final boolean b3 = false;
                        }
                        continue Label_0048;
                    }
                }
                Label_0082: {
                    if (this.isReady()) {
                        final com.google.android.gms.common.api.ResultCallback<? super R> zadt;
                        this.zadp.zaa(zadt, this.get());
                    }
                    else {
                        final com.google.android.gms.common.api.ResultCallback<? super R> zadt;
                        this.zadt = zadt;
                    }
                }
                // monitorexit(zado)
                return;
            }
            continue;
        }
    }
    
    @KeepForSdk
    @Override
    public final void setResultCallback(final ResultCallback<? super R> resultCallback, long millis, final TimeUnit timeUnit) {
        final boolean b = true;
        final Object zado = this.zado;
        // monitorenter(zado)
        while (true) {
            if (resultCallback == null) {
            Label_0053_Outer:
                while (true) {
                Label_0053:
                    while (true) {
                        while (true) {
                            try {
                                this.zadt = null;
                                return;
                                // iftrue(Label_0078:, this.zadv)
                                // iftrue(Label_0084:, this.zadz != null)
                            Block_4:
                                while (true) {
                                    final boolean b2 = b;
                                    break Label_0053;
                                    break Block_4;
                                    final boolean b3;
                                    Preconditions.checkState(b3, (Object)"Result has already been consumed.");
                                    continue Label_0053_Outer;
                                }
                                final boolean b3 = true;
                                continue;
                                boolean b2 = false;
                                Preconditions.checkState(b2, (Object)"Cannot set callbacks if then() has been called.");
                                // iftrue(Label_0090:, !this.isCanceled())
                                return;
                            }
                            finally {
                            }
                            // monitorexit(zado)
                            Label_0078: {
                                final boolean b3 = false;
                            }
                            continue;
                        }
                        Label_0084: {
                            final boolean b2 = false;
                        }
                        continue Label_0053;
                    }
                }
                Label_0090: {
                    if (this.isReady()) {
                        final com.google.android.gms.common.api.ResultCallback<? super R> zadt;
                        this.zadp.zaa(zadt, this.get());
                    }
                    else {
                        final com.google.android.gms.common.api.ResultCallback<? super R> zadt;
                        this.zadt = zadt;
                        final CallbackHandler<R> zadp = this.zadp;
                        millis = timeUnit.toMillis(millis);
                        zadp.sendMessageDelayed(zadp.obtainMessage(2, (Object)this), millis);
                    }
                }
                // monitorexit(zado)
                return;
            }
            continue;
        }
    }
    
    @Override
    public <S extends Result> TransformedResult<S> then(final ResultTransform<? super R, ? extends S> resultTransform) {
        final boolean b = true;
        while (true) {
            Label_0147: {
                if (this.zadv) {
                    break Label_0147;
                }
                final boolean b2 = true;
                boolean b3;
                boolean b4;
                boolean b5;
                TransformedResult<S> then;
                Label_0051_Outer:Label_0067_Outer:
                while (true) {
                    Preconditions.checkState(b2, (Object)"Result has already been consumed.");
                    while (true) {
                    Label_0162:
                        while (true) {
                        Label_0157:
                            while (true) {
                                Label_0152: {
                                    synchronized (this.zado) {
                                        if (this.zadz != null) {
                                            break Label_0152;
                                        }
                                        b3 = true;
                                        Preconditions.checkState(b3, (Object)"Cannot call then() twice.");
                                        if (this.zadt != null) {
                                            break Label_0157;
                                        }
                                        b4 = true;
                                        Preconditions.checkState(b4, (Object)"Cannot call then() if callbacks are set.");
                                        if (!this.zadw) {
                                            b5 = b;
                                            Preconditions.checkState(b5, (Object)"Cannot call then() if result was canceled.");
                                            this.zaea = true;
                                            this.zadz = new zacm<R>(this.zadq);
                                            then = this.zadz.then(resultTransform);
                                            if (this.isReady()) {
                                                this.zadp.zaa((com.google.android.gms.common.api.ResultCallback<? super R>)this.zadz, this.get());
                                            }
                                            else {
                                                this.zadt = (ResultCallback<? super R>)this.zadz;
                                            }
                                            return then;
                                        }
                                        break Label_0162;
                                    }
                                    break;
                                }
                                b3 = false;
                                continue Label_0051_Outer;
                            }
                            b4 = false;
                            continue Label_0067_Outer;
                        }
                        b5 = false;
                        continue;
                    }
                }
            }
            final boolean b2 = false;
            continue;
        }
    }
    
    public final void zaa(final zacs zacs) {
        this.zadu.set(zacs);
    }
    
    public final void zab(final Status status) {
        synchronized (this.zado) {
            if (!this.isReady()) {
                this.setResult(this.createFailedResult(status));
                this.zadx = true;
            }
        }
    }
    
    @Override
    public final Integer zam() {
        return null;
    }
    
    public final boolean zat() {
        synchronized (this.zado) {
            if (this.zadq.get() == null || !this.zaea) {
                this.cancel();
            }
            return this.isCanceled();
        }
    }
    
    public final void zau() {
        this.zaea = (this.zaea || BasePendingResult.zadn.get());
    }
    
    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends zap
    {
        public CallbackHandler() {
            this(Looper.getMainLooper());
        }
        
        public CallbackHandler(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.wtf("BasePendingResult", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), (Throwable)new Exception());
                }
                case 1: {
                    final Pair pair = (Pair)message.obj;
                    final ResultCallback resultCallback = (ResultCallback)pair.first;
                    final Result result = (Result)pair.second;
                    try {
                        resultCallback.onResult(result);
                    }
                    catch (RuntimeException ex) {
                        BasePendingResult.zab(result);
                        throw ex;
                    }
                }
                case 2: {
                    ((BasePendingResult)message.obj).zab(Status.RESULT_TIMEOUT);
                }
            }
        }
        
        public final void zaa(final ResultCallback<? super R> resultCallback, final R r) {
            this.sendMessage(this.obtainMessage(1, (Object)new Pair((Object)resultCallback, (Object)r)));
        }
    }
    
    private final class zaa
    {
        @Override
        protected final void finalize() throws Throwable {
            BasePendingResult.zab(BasePendingResult.this.zacj);
            super.finalize();
        }
    }
}
