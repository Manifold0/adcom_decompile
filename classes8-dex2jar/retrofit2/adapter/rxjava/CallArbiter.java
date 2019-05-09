// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import rx.exceptions.CompositeException;
import rx.plugins.RxJavaPlugins;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import retrofit2.Response;
import retrofit2.Call;
import rx.Producer;
import rx.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

final class CallArbiter<T> extends AtomicInteger implements Subscription, Producer
{
    private static final int STATE_HAS_RESPONSE = 2;
    private static final int STATE_REQUESTED = 1;
    private static final int STATE_TERMINATED = 3;
    private static final int STATE_WAITING = 0;
    private final Call<T> call;
    private volatile Response<T> response;
    private final Subscriber<? super Response<T>> subscriber;
    
    CallArbiter(final Call<T> call, final Subscriber<? super Response<T>> subscriber) {
        super(0);
        this.call = call;
        this.subscriber = subscriber;
    }
    
    private void deliverResponse(final Response<T> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   retrofit2/adapter/rxjava/CallArbiter.isUnsubscribed:()Z
        //     4: ifne            15
        //     7: aload_0        
        //     8: getfield        retrofit2/adapter/rxjava/CallArbiter.subscriber:Lrx/Subscriber;
        //    11: aload_1        
        //    12: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
        //    15: aload_0        
        //    16: getfield        retrofit2/adapter/rxjava/CallArbiter.subscriber:Lrx/Subscriber;
        //    19: invokevirtual   rx/Subscriber.onCompleted:()V
        //    22: return         
        //    23: astore_1       
        //    24: aload_1        
        //    25: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    28: aload_0        
        //    29: getfield        retrofit2/adapter/rxjava/CallArbiter.subscriber:Lrx/Subscriber;
        //    32: aload_1        
        //    33: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
        //    36: return         
        //    37: astore_2       
        //    38: aload_2        
        //    39: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    42: new             Lrx/exceptions/CompositeException;
        //    45: dup            
        //    46: iconst_2       
        //    47: anewarray       Ljava/lang/Throwable;
        //    50: dup            
        //    51: iconst_0       
        //    52: aload_1        
        //    53: aastore        
        //    54: dup            
        //    55: iconst_1       
        //    56: aload_2        
        //    57: aastore        
        //    58: invokespecial   rx/exceptions/CompositeException.<init>:([Ljava/lang/Throwable;)V
        //    61: astore_1       
        //    62: invokestatic    rx/plugins/RxJavaPlugins.getInstance:()Lrx/plugins/RxJavaPlugins;
        //    65: invokevirtual   rx/plugins/RxJavaPlugins.getErrorHandler:()Lrx/plugins/RxJavaErrorHandler;
        //    68: aload_1        
        //    69: invokevirtual   rx/plugins/RxJavaErrorHandler.handleError:(Ljava/lang/Throwable;)V
        //    72: return         
        //    73: astore_1       
        //    74: aload_1        
        //    75: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    78: invokestatic    rx/plugins/RxJavaPlugins.getInstance:()Lrx/plugins/RxJavaPlugins;
        //    81: invokevirtual   rx/plugins/RxJavaPlugins.getErrorHandler:()Lrx/plugins/RxJavaErrorHandler;
        //    84: aload_1        
        //    85: invokevirtual   rx/plugins/RxJavaErrorHandler.handleError:(Ljava/lang/Throwable;)V
        //    88: return         
        //    Signature:
        //  (Lretrofit2/Response<TT;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      15     23     73     Ljava/lang/Throwable;
        //  15     22     73     89     Ljava/lang/Throwable;
        //  28     36     37     73     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    
    void emitError(final Throwable t) {
        this.set(3);
        if (this.isUnsubscribed()) {
            return;
        }
        try {
            this.subscriber.onError(t);
        }
        catch (Throwable t2) {
            Exceptions.throwIfFatal(t2);
            RxJavaPlugins.getInstance().getErrorHandler().handleError((Throwable)new CompositeException(new Throwable[] { t, t2 }));
        }
    }
    
    void emitResponse(final Response<T> response) {
        while (true) {
            final int value = this.get();
            switch (value) {
                default: {
                    throw new IllegalStateException("Unknown state: " + value);
                }
                case 0: {
                    this.response = response;
                    if (this.compareAndSet(0, 2)) {
                        return;
                    }
                    continue;
                }
                case 1: {
                    if (this.compareAndSet(1, 3)) {
                        this.deliverResponse(response);
                        return;
                    }
                    continue;
                }
                case 2:
                case 3: {
                    throw new AssertionError();
                }
            }
        }
    }
    
    public boolean isUnsubscribed() {
        return this.call.isCanceled();
    }
    
    public void request(final long n) {
        if (n != 0L) {
            Block_2: {
                int value = 0;
            Label_0044:
                while (true) {
                    value = this.get();
                    switch (value) {
                        case 1:
                        case 3: {
                            return;
                        }
                        default: {
                            break Label_0044;
                        }
                        case 0: {
                            if (this.compareAndSet(0, 1)) {
                                return;
                            }
                            continue;
                        }
                        case 2: {
                            if (this.compareAndSet(2, 3)) {
                                break Block_2;
                            }
                            continue;
                        }
                    }
                }
                throw new IllegalStateException("Unknown state: " + value);
            }
            this.deliverResponse(this.response);
        }
    }
    
    public void unsubscribe() {
        this.call.cancel();
    }
}
