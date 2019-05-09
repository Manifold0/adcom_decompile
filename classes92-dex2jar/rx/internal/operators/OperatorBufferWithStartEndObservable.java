// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import rx.subscriptions.CompositeSubscription;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func1;
import java.util.List;
import rx.Observable;

public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing> implements Operator<List<T>, T>
{
    final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing;
    final Observable<? extends TOpening> bufferOpening;
    
    public OperatorBufferWithStartEndObservable(final Observable<? extends TOpening> bufferOpening, final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing) {
        this.bufferOpening = bufferOpening;
        this.bufferClosing = bufferClosing;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final BufferingSubscriber bufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber<Object>(subscriber));
        final Subscriber<TOpening> subscriber2 = new Subscriber<TOpening>() {
            @Override
            public void onCompleted() {
                bufferingSubscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                bufferingSubscriber.onError(t);
            }
            
            @Override
            public void onNext(final TOpening tOpening) {
                bufferingSubscriber.startBuffer(tOpening);
            }
        };
        subscriber.add(subscriber2);
        subscriber.add(bufferingSubscriber);
        this.bufferOpening.unsafeSubscribe(subscriber2);
        return bufferingSubscriber;
    }
    
    final class BufferingSubscriber extends Subscriber<T>
    {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks;
        final CompositeSubscription closingSubscriptions;
        boolean done;
        final /* synthetic */ OperatorBufferWithStartEndObservable this$0;
        
        public BufferingSubscriber(final Subscriber<? super List<T>> child) {
            this.child = child;
            this.chunks = new LinkedList<List<T>>();
            this.add(this.closingSubscriptions = new CompositeSubscription());
        }
        
        void endBuffer(final List<T> list) {
            final int n = 0;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                final Iterator<List<T>> iterator = this.chunks.iterator();
                while (true) {
                    do {
                        final int n2 = n;
                        if (iterator.hasNext()) {
                            continue;
                        }
                        // monitorexit(this)
                        if (n2 != 0) {
                            this.child.onNext(list);
                        }
                        return;
                    } while (iterator.next() != list);
                    final int n2 = 1;
                    iterator.remove();
                    continue;
                }
            }
        }
        
        @Override
        public void onCompleted() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.done:Z
            //     6: ifeq            12
            //     9: aload_0        
            //    10: monitorexit    
            //    11: return         
            //    12: aload_0        
            //    13: iconst_1       
            //    14: putfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.done:Z
            //    17: new             Ljava/util/LinkedList;
            //    20: dup            
            //    21: aload_0        
            //    22: getfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.chunks:Ljava/util/List;
            //    25: invokespecial   java/util/LinkedList.<init>:(Ljava/util/Collection;)V
            //    28: astore_1       
            //    29: aload_0        
            //    30: getfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.chunks:Ljava/util/List;
            //    33: invokeinterface java/util/List.clear:()V
            //    38: aload_0        
            //    39: monitorexit    
            //    40: aload_1        
            //    41: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    46: astore_1       
            //    47: aload_1        
            //    48: invokeinterface java/util/Iterator.hasNext:()Z
            //    53: ifeq            92
            //    56: aload_1        
            //    57: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    62: checkcast       Ljava/util/List;
            //    65: astore_2       
            //    66: aload_0        
            //    67: getfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.child:Lrx/Subscriber;
            //    70: aload_2        
            //    71: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //    74: goto            47
            //    77: astore_1       
            //    78: aload_1        
            //    79: aload_0        
            //    80: getfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.child:Lrx/Subscriber;
            //    83: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;)V
            //    86: return         
            //    87: astore_1       
            //    88: aload_0        
            //    89: monitorexit    
            //    90: aload_1        
            //    91: athrow         
            //    92: aload_0        
            //    93: getfield        rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.child:Lrx/Subscriber;
            //    96: invokevirtual   rx/Subscriber.onCompleted:()V
            //    99: aload_0        
            //   100: invokevirtual   rx/internal/operators/OperatorBufferWithStartEndObservable$BufferingSubscriber.unsubscribe:()V
            //   103: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  0      2      77     87     Ljava/lang/Throwable;
            //  2      11     87     92     Any
            //  12     40     87     92     Any
            //  40     47     77     87     Ljava/lang/Throwable;
            //  47     74     77     87     Ljava/lang/Throwable;
            //  88     90     87     92     Any
            //  90     92     77     87     Ljava/lang/Throwable;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0012:
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
        
        @Override
        public void onError(final Throwable t) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunks.clear();
                // monitorexit(this)
                this.child.onError(t);
                this.unsubscribe();
            }
        }
        
        @Override
        public void onNext(final T t) {
            synchronized (this) {
                final Iterator<List<T>> iterator = this.chunks.iterator();
                while (iterator.hasNext()) {
                    iterator.next().add(t);
                }
            }
        }
        // monitorexit(this)
        
        void startBuffer(final TOpening tOpening) {
            final ArrayList<T> list = new ArrayList<T>();
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunks.add(list);
                // monitorexit(this)
                final BufferingSubscriber bufferingSubscriber = this;
                final OperatorBufferWithStartEndObservable operatorBufferWithStartEndObservable = bufferingSubscriber.this$0;
                final Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1 = operatorBufferWithStartEndObservable.bufferClosing;
                final TOpening tOpening2 = tOpening;
                final Observable<? extends TClosing> observable = (Observable<? extends TClosing>)func1.call(tOpening2);
                final Observable<? extends TClosing> observable2 = observable;
                final BufferingSubscriber bufferingSubscriber2 = this;
                final ArrayList<T> list2 = list;
                final Subscriber<TClosing> subscriber = new Subscriber<TClosing>() {
                    final /* synthetic */ List val$chunk;
                    
                    @Override
                    public void onCompleted() {
                        BufferingSubscriber.this.closingSubscriptions.remove(this);
                        BufferingSubscriber.this.endBuffer(this.val$chunk);
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        BufferingSubscriber.this.onError(t);
                    }
                    
                    @Override
                    public void onNext(final TClosing tClosing) {
                        BufferingSubscriber.this.closingSubscriptions.remove(this);
                        BufferingSubscriber.this.endBuffer(this.val$chunk);
                    }
                };
                final BufferingSubscriber bufferingSubscriber3 = this;
                final CompositeSubscription compositeSubscription = bufferingSubscriber3.closingSubscriptions;
                final Subscriber<TClosing> subscriber2 = subscriber;
                compositeSubscription.add(subscriber2);
                final Observable<? extends TClosing> observable3 = observable2;
                final Subscriber<TClosing> subscriber3 = subscriber;
                observable3.unsafeSubscribe(subscriber3);
                return;
            }
            try {
                final BufferingSubscriber bufferingSubscriber = this;
                final OperatorBufferWithStartEndObservable operatorBufferWithStartEndObservable = bufferingSubscriber.this$0;
                final Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1 = operatorBufferWithStartEndObservable.bufferClosing;
                final TOpening tOpening2 = tOpening;
                final Observable<? extends TClosing> observable = (Observable<? extends TClosing>)func1.call(tOpening2);
                final Observable<? extends TClosing> observable2 = observable;
                final BufferingSubscriber bufferingSubscriber2 = this;
                final ArrayList<T> list2 = list;
                final Subscriber<TClosing> subscriber = new Subscriber<TClosing>() {
                    final /* synthetic */ List val$chunk;
                    
                    @Override
                    public void onCompleted() {
                        bufferingSubscriber2.closingSubscriptions.remove(this);
                        bufferingSubscriber2.endBuffer(list2);
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        bufferingSubscriber2.onError(t);
                    }
                    
                    @Override
                    public void onNext(final TClosing tClosing) {
                        bufferingSubscriber2.closingSubscriptions.remove(this);
                        bufferingSubscriber2.endBuffer(list2);
                    }
                };
                final BufferingSubscriber bufferingSubscriber3 = this;
                final CompositeSubscription compositeSubscription = bufferingSubscriber3.closingSubscriptions;
                final Subscriber<TClosing> subscriber2 = subscriber;
                compositeSubscription.add(subscriber2);
                final Observable<? extends TClosing> observable3 = observable2;
                final Subscriber<TClosing> subscriber3 = subscriber;
                observable3.unsafeSubscribe(subscriber3);
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, this);
            }
        }
    }
}
