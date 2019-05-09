// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import rx.subjects.UnicastSubject;
import java.util.LinkedList;
import rx.observers.SerializedSubscriber;
import java.util.List;
import rx.observers.SerializedObserver;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorWindowWithStartEndObservable<T, U, V> implements Operator<Observable<T>, T>
{
    final Func1<? super U, ? extends Observable<? extends V>> windowClosingSelector;
    final Observable<? extends U> windowOpenings;
    
    public OperatorWindowWithStartEndObservable(final Observable<? extends U> windowOpenings, final Func1<? super U, ? extends Observable<? extends V>> windowClosingSelector) {
        this.windowOpenings = windowOpenings;
        this.windowClosingSelector = windowClosingSelector;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Observable<T>> subscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        subscriber.add(compositeSubscription);
        final SourceSubscriber sourceSubscriber = new SourceSubscriber(subscriber, compositeSubscription);
        final Subscriber<U> subscriber2 = new Subscriber<U>() {
            @Override
            public void onCompleted() {
                sourceSubscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                sourceSubscriber.onError(t);
            }
            
            @Override
            public void onNext(final U u) {
                sourceSubscriber.beginWindow(u);
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
        compositeSubscription.add(sourceSubscriber);
        compositeSubscription.add(subscriber2);
        this.windowOpenings.unsafeSubscribe(subscriber2);
        return sourceSubscriber;
    }
    
    static final class SerializedSubject<T>
    {
        final Observer<T> consumer;
        final Observable<T> producer;
        
        public SerializedSubject(final Observer<T> observer, final Observable<T> producer) {
            this.consumer = new SerializedObserver<T>(observer);
            this.producer = producer;
        }
    }
    
    final class SourceSubscriber extends Subscriber<T>
    {
        final Subscriber<? super Observable<T>> child;
        final List<SerializedSubject<T>> chunks;
        final CompositeSubscription composite;
        boolean done;
        final Object guard;
        final /* synthetic */ OperatorWindowWithStartEndObservable this$0;
        
        public SourceSubscriber(final Subscriber<? super Observable<T>> subscriber, final CompositeSubscription composite) {
            this.child = new SerializedSubscriber<Object>(subscriber);
            this.guard = new Object();
            this.chunks = new LinkedList<SerializedSubject<T>>();
            this.composite = composite;
        }
        
        void beginWindow(final U u) {
            final SerializedSubject<T> serializedSubject = this.createSerializedSubject();
            final Object guard = this.guard;
            synchronized (guard) {
                if (this.done) {
                    return;
                }
                this.chunks.add(serializedSubject);
                // monitorexit(guard)
                this.child.onNext(serializedSubject.producer);
                final SourceSubscriber sourceSubscriber = this;
                final OperatorWindowWithStartEndObservable operatorWindowWithStartEndObservable = sourceSubscriber.this$0;
                final Func1<? super U, ? extends Observable<? extends V>> func1 = operatorWindowWithStartEndObservable.windowClosingSelector;
                final U u2 = u;
                final Observable<? extends V> observable = (Observable<? extends V>)func1.call(u2);
                final Observable<? extends V> observable2 = observable;
                final SourceSubscriber sourceSubscriber2 = this;
                final SerializedSubject<T> serializedSubject2 = serializedSubject;
                final Subscriber<V> subscriber = new Subscriber<V>() {
                    boolean once;
                    final /* synthetic */ SerializedSubject val$s;
                    
                    {
                        this.once = true;
                    }
                    
                    @Override
                    public void onCompleted() {
                        if (this.once) {
                            this.once = false;
                            SourceSubscriber.this.endWindow(this.val$s);
                            SourceSubscriber.this.composite.remove(this);
                        }
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        SourceSubscriber.this.onError(t);
                    }
                    
                    @Override
                    public void onNext(final V v) {
                        this.onCompleted();
                    }
                };
                final SourceSubscriber sourceSubscriber3 = this;
                final CompositeSubscription compositeSubscription = sourceSubscriber3.composite;
                final Subscriber<V> subscriber2 = subscriber;
                compositeSubscription.add(subscriber2);
                final Observable<? extends V> observable3 = observable2;
                final Subscriber<V> subscriber3 = subscriber;
                observable3.unsafeSubscribe(subscriber3);
                return;
            }
            try {
                final SourceSubscriber sourceSubscriber = this;
                final OperatorWindowWithStartEndObservable operatorWindowWithStartEndObservable = sourceSubscriber.this$0;
                final Func1<? super U, ? extends Observable<? extends V>> func1 = operatorWindowWithStartEndObservable.windowClosingSelector;
                final U u2 = u;
                final Observable<? extends V> observable = (Observable<? extends V>)func1.call(u2);
                final Observable<? extends V> observable2 = observable;
                final SourceSubscriber sourceSubscriber2 = this;
                final SerializedSubject<T> serializedSubject2 = serializedSubject;
                final Subscriber<V> subscriber = new Subscriber<V>() {
                    boolean once = true;
                    final /* synthetic */ SerializedSubject val$s;
                    
                    @Override
                    public void onCompleted() {
                        if (this.once) {
                            this.once = false;
                            sourceSubscriber2.endWindow(serializedSubject2);
                            sourceSubscriber2.composite.remove(this);
                        }
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        sourceSubscriber2.onError(t);
                    }
                    
                    @Override
                    public void onNext(final V v) {
                        this.onCompleted();
                    }
                };
                final SourceSubscriber sourceSubscriber3 = this;
                final CompositeSubscription compositeSubscription = sourceSubscriber3.composite;
                final Subscriber<V> subscriber2 = subscriber;
                compositeSubscription.add(subscriber2);
                final Observable<? extends V> observable3 = observable2;
                final Subscriber<V> subscriber3 = subscriber;
                observable3.unsafeSubscribe(subscriber3);
            }
            catch (Throwable t) {
                this.onError(t);
            }
        }
        
        SerializedSubject<T> createSerializedSubject() {
            final UnicastSubject<Object> create = UnicastSubject.create();
            return (SerializedSubject<T>)new SerializedSubject((Observer<Object>)create, (Observable<Object>)create);
        }
        
        void endWindow(final SerializedSubject<T> serializedSubject) {
            final int n = 0;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                final Iterator<SerializedSubject<T>> iterator = this.chunks.iterator();
                while (true) {
                    do {
                        final int n2 = n;
                        if (iterator.hasNext()) {
                            continue;
                        }
                        // monitorexit(this.guard)
                        if (n2 != 0) {
                            serializedSubject.consumer.onCompleted();
                        }
                        return;
                    } while (iterator.next() != serializedSubject);
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
            //     1: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.guard:Ljava/lang/Object;
            //     4: astore_1       
            //     5: aload_1        
            //     6: monitorenter   
            //     7: aload_0        
            //     8: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.done:Z
            //    11: ifeq            24
            //    14: aload_1        
            //    15: monitorexit    
            //    16: aload_0        
            //    17: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.composite:Lrx/subscriptions/CompositeSubscription;
            //    20: invokevirtual   rx/subscriptions/CompositeSubscription.unsubscribe:()V
            //    23: return         
            //    24: aload_0        
            //    25: iconst_1       
            //    26: putfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.done:Z
            //    29: new             Ljava/util/ArrayList;
            //    32: dup            
            //    33: aload_0        
            //    34: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.chunks:Ljava/util/List;
            //    37: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
            //    40: astore_2       
            //    41: aload_0        
            //    42: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.chunks:Ljava/util/List;
            //    45: invokeinterface java/util/List.clear:()V
            //    50: aload_1        
            //    51: monitorexit    
            //    52: aload_2        
            //    53: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    58: astore_1       
            //    59: aload_1        
            //    60: invokeinterface java/util/Iterator.hasNext:()Z
            //    65: ifeq            103
            //    68: aload_1        
            //    69: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    74: checkcast       Lrx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject;
            //    77: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject.consumer:Lrx/Observer;
            //    80: invokeinterface rx/Observer.onCompleted:()V
            //    85: goto            59
            //    88: astore_1       
            //    89: aload_0        
            //    90: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.composite:Lrx/subscriptions/CompositeSubscription;
            //    93: invokevirtual   rx/subscriptions/CompositeSubscription.unsubscribe:()V
            //    96: aload_1        
            //    97: athrow         
            //    98: astore_2       
            //    99: aload_1        
            //   100: monitorexit    
            //   101: aload_2        
            //   102: athrow         
            //   103: aload_0        
            //   104: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.child:Lrx/Subscriber;
            //   107: invokevirtual   rx/Subscriber.onCompleted:()V
            //   110: aload_0        
            //   111: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.composite:Lrx/subscriptions/CompositeSubscription;
            //   114: invokevirtual   rx/subscriptions/CompositeSubscription.unsubscribe:()V
            //   117: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  0      7      88     98     Any
            //  7      16     98     103    Any
            //  24     52     98     103    Any
            //  52     59     88     98     Any
            //  59     85     88     98     Any
            //  99     101    98     103    Any
            //  101    103    88     98     Any
            //  103    110    88     98     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
        public void onError(final Throwable p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.guard:Ljava/lang/Object;
            //     4: astore_2       
            //     5: aload_2        
            //     6: monitorenter   
            //     7: aload_0        
            //     8: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.done:Z
            //    11: ifeq            24
            //    14: aload_2        
            //    15: monitorexit    
            //    16: aload_0        
            //    17: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.composite:Lrx/subscriptions/CompositeSubscription;
            //    20: invokevirtual   rx/subscriptions/CompositeSubscription.unsubscribe:()V
            //    23: return         
            //    24: aload_0        
            //    25: iconst_1       
            //    26: putfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.done:Z
            //    29: new             Ljava/util/ArrayList;
            //    32: dup            
            //    33: aload_0        
            //    34: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.chunks:Ljava/util/List;
            //    37: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
            //    40: astore_3       
            //    41: aload_0        
            //    42: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.chunks:Ljava/util/List;
            //    45: invokeinterface java/util/List.clear:()V
            //    50: aload_2        
            //    51: monitorexit    
            //    52: aload_3        
            //    53: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    58: astore_2       
            //    59: aload_2        
            //    60: invokeinterface java/util/Iterator.hasNext:()Z
            //    65: ifeq            104
            //    68: aload_2        
            //    69: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    74: checkcast       Lrx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject;
            //    77: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject.consumer:Lrx/Observer;
            //    80: aload_1        
            //    81: invokeinterface rx/Observer.onError:(Ljava/lang/Throwable;)V
            //    86: goto            59
            //    89: astore_1       
            //    90: aload_0        
            //    91: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.composite:Lrx/subscriptions/CompositeSubscription;
            //    94: invokevirtual   rx/subscriptions/CompositeSubscription.unsubscribe:()V
            //    97: aload_1        
            //    98: athrow         
            //    99: astore_1       
            //   100: aload_2        
            //   101: monitorexit    
            //   102: aload_1        
            //   103: athrow         
            //   104: aload_0        
            //   105: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.child:Lrx/Subscriber;
            //   108: aload_1        
            //   109: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   112: aload_0        
            //   113: getfield        rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber.composite:Lrx/subscriptions/CompositeSubscription;
            //   116: invokevirtual   rx/subscriptions/CompositeSubscription.unsubscribe:()V
            //   119: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  0      7      89     99     Any
            //  7      16     99     104    Any
            //  24     52     99     104    Any
            //  52     59     89     99     Any
            //  59     86     89     99     Any
            //  100    102    99     104    Any
            //  102    104    89     99     Any
            //  104    112    89     99     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
        public void onNext(final T t) {
            Object o = this.guard;
            synchronized (o) {
                if (this.done) {
                    return;
                }
                final ArrayList<SerializedSubject> list = new ArrayList<SerializedSubject>((Collection<? extends SerializedSubject>)this.chunks);
                // monitorexit(o)
                o = list.iterator();
                while (((Iterator)o).hasNext()) {
                    ((SerializedSubject)((Iterator<SerializedSubject>)o).next()).consumer.onNext((T)t);
                }
            }
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
    }
}
