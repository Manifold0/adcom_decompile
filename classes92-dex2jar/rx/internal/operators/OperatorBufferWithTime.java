// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Iterator;
import java.util.LinkedList;
import rx.functions.Action0;
import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import java.util.List;
import rx.Observable;

public final class OperatorBufferWithTime<T> implements Operator<List<T>, T>
{
    final int count;
    final Scheduler scheduler;
    final long timeshift;
    final long timespan;
    final TimeUnit unit;
    
    public OperatorBufferWithTime(final long timespan, final long timeshift, final TimeUnit unit, final int count, final Scheduler scheduler) {
        this.timespan = timespan;
        this.timeshift = timeshift;
        this.unit = unit;
        this.count = count;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        final SerializedSubscriber<Object> serializedSubscriber = new SerializedSubscriber<Object>((Subscriber<? super Object>)subscriber);
        if (this.timespan == this.timeshift) {
            final ExactSubscriber exactSubscriber = new ExactSubscriber(serializedSubscriber, worker);
            exactSubscriber.add(worker);
            subscriber.add(exactSubscriber);
            exactSubscriber.scheduleExact();
            return exactSubscriber;
        }
        final InexactSubscriber inexactSubscriber = new InexactSubscriber(serializedSubscriber, worker);
        inexactSubscriber.add(worker);
        subscriber.add(inexactSubscriber);
        inexactSubscriber.startNewChunk();
        inexactSubscriber.scheduleChunk();
        return inexactSubscriber;
    }
    
    final class ExactSubscriber extends Subscriber<T>
    {
        final Subscriber<? super List<T>> child;
        List<T> chunk;
        boolean done;
        final Scheduler.Worker inner;
        
        public ExactSubscriber(final Subscriber<? super List<T>> child, final Scheduler.Worker inner) {
            this.child = child;
            this.inner = inner;
            this.chunk = new ArrayList<T>();
        }
        
        void emit() {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                final List<T> chunk = this.chunk;
                this.chunk = new ArrayList<T>();
                // monitorexit(this)
                try {
                    this.child.onNext(chunk);
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }
        }
        
        @Override
        public void onCompleted() {
            try {
                this.inner.unsubscribe();
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    final List<T> chunk = this.chunk;
                    this.chunk = null;
                    // monitorexit(this)
                    this.child.onNext(chunk);
                    this.child.onCompleted();
                    this.unsubscribe();
                }
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, this.child);
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunk = null;
                // monitorexit(this)
                this.child.onError(t);
                this.unsubscribe();
            }
        }
        
        @Override
        public void onNext(final T t) {
            final List<T> list = null;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunk.add(t);
                List<T> chunk = list;
                if (this.chunk.size() == OperatorBufferWithTime.this.count) {
                    chunk = this.chunk;
                    this.chunk = new ArrayList<T>();
                }
                // monitorexit(this)
                if (chunk != null) {
                    this.child.onNext(chunk);
                }
            }
        }
        
        void scheduleExact() {
            this.inner.schedulePeriodically(new Action0() {
                @Override
                public void call() {
                    ExactSubscriber.this.emit();
                }
            }, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
        }
    }
    
    final class InexactSubscriber extends Subscriber<T>
    {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks;
        boolean done;
        final Scheduler.Worker inner;
        
        public InexactSubscriber(final Subscriber<? super List<T>> child, final Scheduler.Worker inner) {
            this.child = child;
            this.inner = inner;
            this.chunks = new LinkedList<List<T>>();
        }
        
        void emitChunk(final List<T> list) {
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
                        if (n2 == 0) {
                            return;
                        }
                        try {
                            this.child.onNext(list);
                            return;
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, this);
                            return;
                        }
                    } while (iterator.next() != list);
                    iterator.remove();
                    final int n2 = 1;
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
            //     3: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.done:Z
            //     6: ifeq            12
            //     9: aload_0        
            //    10: monitorexit    
            //    11: return         
            //    12: aload_0        
            //    13: iconst_1       
            //    14: putfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.done:Z
            //    17: new             Ljava/util/LinkedList;
            //    20: dup            
            //    21: aload_0        
            //    22: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.chunks:Ljava/util/List;
            //    25: invokespecial   java/util/LinkedList.<init>:(Ljava/util/Collection;)V
            //    28: astore_1       
            //    29: aload_0        
            //    30: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.chunks:Ljava/util/List;
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
            //    67: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.child:Lrx/Subscriber;
            //    70: aload_2        
            //    71: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //    74: goto            47
            //    77: astore_1       
            //    78: aload_1        
            //    79: aload_0        
            //    80: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.child:Lrx/Subscriber;
            //    83: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;)V
            //    86: return         
            //    87: astore_1       
            //    88: aload_0        
            //    89: monitorexit    
            //    90: aload_1        
            //    91: athrow         
            //    92: aload_0        
            //    93: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.child:Lrx/Subscriber;
            //    96: invokevirtual   rx/Subscriber.onCompleted:()V
            //    99: aload_0        
            //   100: invokevirtual   rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.unsubscribe:()V
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
        public void onNext(final T p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.done:Z
            //     6: ifeq            12
            //     9: aload_0        
            //    10: monitorexit    
            //    11: return         
            //    12: aload_0        
            //    13: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.chunks:Ljava/util/List;
            //    16: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    21: astore_3       
            //    22: aconst_null    
            //    23: astore_2       
            //    24: aload_3        
            //    25: invokeinterface java/util/Iterator.hasNext:()Z
            //    30: ifeq            100
            //    33: aload_3        
            //    34: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    39: checkcast       Ljava/util/List;
            //    42: astore          4
            //    44: aload           4
            //    46: aload_1        
            //    47: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //    52: pop            
            //    53: aload           4
            //    55: invokeinterface java/util/List.size:()I
            //    60: aload_0        
            //    61: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.this$0:Lrx/internal/operators/OperatorBufferWithTime;
            //    64: getfield        rx/internal/operators/OperatorBufferWithTime.count:I
            //    67: if_icmpne       156
            //    70: aload_3        
            //    71: invokeinterface java/util/Iterator.remove:()V
            //    76: aload_2        
            //    77: ifnonnull       153
            //    80: new             Ljava/util/LinkedList;
            //    83: dup            
            //    84: invokespecial   java/util/LinkedList.<init>:()V
            //    87: astore_2       
            //    88: aload_2        
            //    89: aload           4
            //    91: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //    96: pop            
            //    97: goto            24
            //   100: aload_0        
            //   101: monitorexit    
            //   102: aload_2        
            //   103: ifnull          148
            //   106: aload_2        
            //   107: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   112: astore_1       
            //   113: aload_1        
            //   114: invokeinterface java/util/Iterator.hasNext:()Z
            //   119: ifeq            148
            //   122: aload_1        
            //   123: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   128: checkcast       Ljava/util/List;
            //   131: astore_2       
            //   132: aload_0        
            //   133: getfield        rx/internal/operators/OperatorBufferWithTime$InexactSubscriber.child:Lrx/Subscriber;
            //   136: aload_2        
            //   137: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //   140: goto            113
            //   143: astore_1       
            //   144: aload_0        
            //   145: monitorexit    
            //   146: aload_1        
            //   147: athrow         
            //   148: return         
            //   149: astore_1       
            //   150: goto            144
            //   153: goto            88
            //   156: goto            97
            //    Signature:
            //  (TT;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  2      11     143    144    Any
            //  12     22     143    144    Any
            //  24     76     149    153    Any
            //  80     88     149    153    Any
            //  88     97     143    144    Any
            //  100    102    149    153    Any
            //  144    146    143    144    Any
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
        
        void scheduleChunk() {
            this.inner.schedulePeriodically(new Action0() {
                @Override
                public void call() {
                    InexactSubscriber.this.startNewChunk();
                }
            }, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.unit);
        }
        
        void startNewChunk() {
            final ArrayList<T> list = new ArrayList<T>();
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunks.add(list);
                // monitorexit(this)
                this.inner.schedule(new Action0() {
                    @Override
                    public void call() {
                        InexactSubscriber.this.emitChunk(list);
                    }
                }, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
            }
        }
    }
}
