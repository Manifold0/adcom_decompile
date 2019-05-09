// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.Observer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.internal.util.BackpressureDrainManager;
import rx.Subscription;
import rx.Subscriber;
import rx.BackpressureOverflow;
import rx.functions.Action0;
import rx.Observable;

public class OperatorOnBackpressureBuffer<T> implements Operator<T, T>
{
    private final Long capacity;
    private final Action0 onOverflow;
    private final BackpressureOverflow.Strategy overflowStrategy;
    
    OperatorOnBackpressureBuffer() {
        this.capacity = null;
        this.onOverflow = null;
        this.overflowStrategy = BackpressureOverflow.ON_OVERFLOW_DEFAULT;
    }
    
    public OperatorOnBackpressureBuffer(final long n) {
        this(n, null, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }
    
    public OperatorOnBackpressureBuffer(final long n, final Action0 action0) {
        this(n, action0, BackpressureOverflow.ON_OVERFLOW_DEFAULT);
    }
    
    public OperatorOnBackpressureBuffer(final long n, final Action0 onOverflow, final BackpressureOverflow.Strategy overflowStrategy) {
        if (n <= 0L) {
            throw new IllegalArgumentException("Buffer capacity must be > 0");
        }
        if (overflowStrategy == null) {
            throw new NullPointerException("The BackpressureOverflow strategy must not be null");
        }
        this.capacity = n;
        this.onOverflow = onOverflow;
        this.overflowStrategy = overflowStrategy;
    }
    
    public static <T> OperatorOnBackpressureBuffer<T> instance() {
        return (OperatorOnBackpressureBuffer<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final BufferSubscriber<Object> bufferSubscriber = new BufferSubscriber<Object>((Subscriber<? super Object>)subscriber, this.capacity, this.onOverflow, this.overflowStrategy);
        subscriber.add(bufferSubscriber);
        subscriber.setProducer(bufferSubscriber.manager());
        return bufferSubscriber;
    }
    
    static final class BufferSubscriber<T> extends Subscriber<T> implements BackpressureQueueCallback
    {
        private final AtomicLong capacity;
        private final Subscriber<? super T> child;
        private final BackpressureDrainManager manager;
        private final NotificationLite<T> on;
        private final Action0 onOverflow;
        private final BackpressureOverflow.Strategy overflowStrategy;
        private final ConcurrentLinkedQueue<Object> queue;
        private final AtomicBoolean saturated;
        
        public BufferSubscriber(final Subscriber<? super T> child, final Long n, final Action0 onOverflow, final BackpressureOverflow.Strategy overflowStrategy) {
            this.queue = new ConcurrentLinkedQueue<Object>();
            this.saturated = new AtomicBoolean(false);
            this.on = NotificationLite.instance();
            this.child = child;
            AtomicLong capacity;
            if (n != null) {
                capacity = new AtomicLong(n);
            }
            else {
                capacity = null;
            }
            this.capacity = capacity;
            this.onOverflow = onOverflow;
            this.manager = new BackpressureDrainManager((BackpressureDrainManager.BackpressureQueueCallback)this);
            this.overflowStrategy = overflowStrategy;
        }
        
        private boolean assertCapacity() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.capacity:Ljava/util/concurrent/atomic/AtomicLong;
            //     4: ifnonnull       9
            //     7: iconst_1       
            //     8: ireturn        
            //     9: aload_0        
            //    10: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.capacity:Ljava/util/concurrent/atomic/AtomicLong;
            //    13: invokevirtual   java/util/concurrent/atomic/AtomicLong.get:()J
            //    16: lstore_3       
            //    17: lload_3        
            //    18: lconst_0       
            //    19: lcmp           
            //    20: ifgt            129
            //    23: iconst_0       
            //    24: istore_2       
            //    25: aload_0        
            //    26: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.overflowStrategy:Lrx/BackpressureOverflow$Strategy;
            //    29: invokeinterface rx/BackpressureOverflow$Strategy.mayAttemptDrop:()Z
            //    34: ifeq            72
            //    37: aload_0        
            //    38: invokevirtual   rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.poll:()Ljava/lang/Object;
            //    41: astore          5
            //    43: aload           5
            //    45: ifnull          72
            //    48: iconst_1       
            //    49: istore_1       
            //    50: aload_0        
            //    51: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.onOverflow:Lrx/functions/Action0;
            //    54: ifnull          66
            //    57: aload_0        
            //    58: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.onOverflow:Lrx/functions/Action0;
            //    61: invokeinterface rx/functions/Action0.call:()V
            //    66: iload_1        
            //    67: ifne            129
            //    70: iconst_0       
            //    71: ireturn        
            //    72: iconst_0       
            //    73: istore_1       
            //    74: goto            50
            //    77: astore          5
            //    79: iload_2        
            //    80: istore_1       
            //    81: aload_0        
            //    82: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.saturated:Ljava/util/concurrent/atomic/AtomicBoolean;
            //    85: iconst_0       
            //    86: iconst_1       
            //    87: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.compareAndSet:(ZZ)Z
            //    90: ifeq            50
            //    93: aload_0        
            //    94: invokevirtual   rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.unsubscribe:()V
            //    97: aload_0        
            //    98: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.child:Lrx/Subscriber;
            //   101: aload           5
            //   103: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   106: iload_2        
            //   107: istore_1       
            //   108: goto            50
            //   111: astore          5
            //   113: aload           5
            //   115: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   118: aload_0        
            //   119: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.manager:Lrx/internal/util/BackpressureDrainManager;
            //   122: aload           5
            //   124: invokevirtual   rx/internal/util/BackpressureDrainManager.terminateAndDrain:(Ljava/lang/Throwable;)V
            //   127: iconst_0       
            //   128: ireturn        
            //   129: aload_0        
            //   130: getfield        rx/internal/operators/OperatorOnBackpressureBuffer$BufferSubscriber.capacity:Ljava/util/concurrent/atomic/AtomicLong;
            //   133: lload_3        
            //   134: lload_3        
            //   135: lconst_1       
            //   136: lsub           
            //   137: invokevirtual   java/util/concurrent/atomic/AtomicLong.compareAndSet:(JJ)Z
            //   140: ifeq            9
            //   143: iconst_1       
            //   144: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                        
            //  -----  -----  -----  -----  --------------------------------------------
            //  25     43     77     111    Lrx/exceptions/MissingBackpressureException;
            //  57     66     111    129    Ljava/lang/Throwable;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
        public boolean accept(final Object o) {
            return this.on.accept(this.child, o);
        }
        
        @Override
        public void complete(final Throwable t) {
            if (t != null) {
                this.child.onError(t);
                return;
            }
            this.child.onCompleted();
        }
        
        protected Producer manager() {
            return this.manager;
        }
        
        @Override
        public void onCompleted() {
            if (!this.saturated.get()) {
                this.manager.terminateAndDrain();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (!this.saturated.get()) {
                this.manager.terminateAndDrain(t);
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.assertCapacity()) {
                return;
            }
            this.queue.offer(this.on.next(t));
            this.manager.drain();
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
        
        @Override
        public Object peek() {
            return this.queue.peek();
        }
        
        @Override
        public Object poll() {
            final Object poll = this.queue.poll();
            if (this.capacity != null && poll != null) {
                this.capacity.incrementAndGet();
            }
            return poll;
        }
    }
    
    static final class Holder
    {
        static final OperatorOnBackpressureBuffer<?> INSTANCE;
        
        static {
            INSTANCE = new OperatorOnBackpressureBuffer<Object>();
        }
    }
}
