// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorDebounceWithTime<T> implements Operator<T, T>
{
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;
    
    public OperatorDebounceWithTime(final long timeout, final TimeUnit unit, final Scheduler scheduler) {
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber((Subscriber<? super T>)subscriber);
        final SerialSubscription serialSubscription = new SerialSubscription();
        serializedSubscriber.add(worker);
        serializedSubscriber.add(serialSubscription);
        return new Subscriber<T>(subscriber) {
            final Subscriber<?> self = this;
            final DebounceState<T> state = new DebounceState();
            
            @Override
            public void onCompleted() {
                this.state.emitAndComplete(serializedSubscriber, this);
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                this.unsubscribe();
                this.state.clear();
            }
            
            @Override
            public void onNext(final T t) {
                serialSubscription.set(worker.schedule(new Action0() {
                    final /* synthetic */ int val$index = OperatorDebounceWithTime$1.this.state.next(t);
                    
                    @Override
                    public void call() {
                        Subscriber.this.state.emit(this.val$index, serializedSubscriber, Subscriber.this.self);
                    }
                }, OperatorDebounceWithTime.this.timeout, OperatorDebounceWithTime.this.unit));
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
    }
    
    static final class DebounceState<T>
    {
        boolean emitting;
        boolean hasValue;
        int index;
        boolean terminate;
        T value;
        
        public void clear() {
            synchronized (this) {
                ++this.index;
                this.value = null;
                this.hasValue = false;
            }
        }
        
        public void emit(final int n, final Subscriber<T> subscriber, final Subscriber<?> subscriber2) {
            Observer observer = null;
            Label_0092: {
                final T value;
                synchronized (this) {
                    if (this.emitting || !this.hasValue || n != this.index) {
                        return;
                    }
                    value = this.value;
                    this.value = null;
                    this.hasValue = false;
                    this.emitting = true;
                    // monitorexit(this)
                    final Subscriber<T> subscriber3 = subscriber;
                    final T t = value;
                    subscriber3.onNext(t);
                    final DebounceState debounceState = this;
                    // monitorenter(debounceState)
                    try {
                        final DebounceState debounceState2 = this;
                        final boolean b = debounceState2.terminate;
                        if (!b) {
                            final DebounceState debounceState3 = this;
                            final boolean b2 = false;
                            debounceState3.emitting = b2;
                            return;
                        }
                        break Label_0092;
                    }
                    finally {
                        final Object o;
                        observer = (Observer)o;
                    }
                    // monitorexit(this)
                }
                try {
                    final Subscriber<T> subscriber3 = subscriber;
                    final T t = value;
                    subscriber3.onNext(t);
                    final DebounceState debounceState = this;
                    // monitorenter(debounceState)
                    final DebounceState debounceState2 = this;
                    final boolean b = debounceState2.terminate;
                    if (!b) {
                        final DebounceState debounceState3 = this;
                        final boolean b2 = false;
                        debounceState3.emitting = b2;
                        return;
                    }
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, subscriber2, value);
                    return;
                }
            }
            // monitorexit(this)
            observer.onCompleted();
        }
        
        public void emitAndComplete(final Subscriber<T> p0, final Subscriber<?> p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.emitting:Z
            //     6: ifeq            17
            //     9: aload_0        
            //    10: iconst_1       
            //    11: putfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.terminate:Z
            //    14: aload_0        
            //    15: monitorexit    
            //    16: return         
            //    17: aload_0        
            //    18: getfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.value:Ljava/lang/Object;
            //    21: astore          4
            //    23: aload_0        
            //    24: getfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.hasValue:Z
            //    27: istore_3       
            //    28: aload_0        
            //    29: aconst_null    
            //    30: putfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.value:Ljava/lang/Object;
            //    33: aload_0        
            //    34: iconst_0       
            //    35: putfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.hasValue:Z
            //    38: aload_0        
            //    39: iconst_1       
            //    40: putfield        rx/internal/operators/OperatorDebounceWithTime$DebounceState.emitting:Z
            //    43: aload_0        
            //    44: monitorexit    
            //    45: iload_3        
            //    46: ifeq            55
            //    49: aload_1        
            //    50: aload           4
            //    52: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //    55: aload_1        
            //    56: invokevirtual   rx/Subscriber.onCompleted:()V
            //    59: return         
            //    60: astore_1       
            //    61: aload_0        
            //    62: monitorexit    
            //    63: aload_1        
            //    64: athrow         
            //    65: astore_1       
            //    66: aload_1        
            //    67: aload_2        
            //    68: aload           4
            //    70: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;Ljava/lang/Object;)V
            //    73: return         
            //    Signature:
            //  (Lrx/Subscriber<TT;>;Lrx/Subscriber<*>;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  2      16     60     65     Any
            //  17     45     60     65     Any
            //  49     55     65     74     Ljava/lang/Throwable;
            //  61     63     60     65     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        public int next(final T value) {
            synchronized (this) {
                this.value = value;
                this.hasValue = true;
                return ++this.index;
            }
        }
    }
}
