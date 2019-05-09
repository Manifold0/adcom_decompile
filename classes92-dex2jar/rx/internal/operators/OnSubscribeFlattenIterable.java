// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.plugins.RxJavaHooks;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.internal.util.RxRingBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Iterator;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.internal.util.ScalarSynchronousObservable;
import rx.functions.Func1;
import rx.Observable;

public final class OnSubscribeFlattenIterable<T, R> implements OnSubscribe<R>
{
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;
    
    protected OnSubscribeFlattenIterable(final Observable<? extends T> source, final Func1<? super T, ? extends Iterable<? extends R>> mapper, final int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
    }
    
    public static <T, R> Observable<R> createFrom(final Observable<? extends T> observable, final Func1<? super T, ? extends Iterable<? extends R>> func1, final int n) {
        if (observable instanceof ScalarSynchronousObservable) {
            return Observable.create((Observable.OnSubscribe<R>)new OnSubscribeScalarFlattenIterable(((ScalarSynchronousObservable<Object>)observable).get(), (Func1<? super Object, ? extends Iterable<?>>)func1));
        }
        return Observable.create((Observable.OnSubscribe<R>)new OnSubscribeFlattenIterable(observable, (Func1<? super Object, ? extends Iterable<?>>)func1, n));
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        final FlattenIterableSubscriber flattenIterableSubscriber = new FlattenIterableSubscriber((Subscriber<? super R>)subscriber, (Func1<? super T, ? extends Iterable<? extends R>>)this.mapper, this.prefetch);
        subscriber.add(flattenIterableSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                flattenIterableSubscriber.requestMore(n);
            }
        });
        this.source.unsafeSubscribe(flattenIterableSubscriber);
    }
    
    static final class FlattenIterableSubscriber<T, R> extends Subscriber<T>
    {
        Iterator<? extends R> active;
        final Subscriber<? super R> actual;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final long limit;
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final NotificationLite<T> nl;
        long produced;
        final Queue<Object> queue;
        final AtomicLong requested;
        final AtomicInteger wip;
        
        public FlattenIterableSubscriber(final Subscriber<? super R> actual, final Func1<? super T, ? extends Iterable<? extends R>> mapper, final int n) {
            this.actual = actual;
            this.mapper = mapper;
            this.error = new AtomicReference<Throwable>();
            this.wip = new AtomicInteger();
            this.requested = new AtomicLong();
            this.nl = NotificationLite.instance();
            if (n == Integer.MAX_VALUE) {
                this.limit = Long.MAX_VALUE;
                this.queue = new SpscLinkedArrayQueue<Object>(RxRingBuffer.SIZE);
            }
            else {
                this.limit = n - (n >> 2);
                if (UnsafeAccess.isUnsafeAvailable()) {
                    this.queue = new SpscArrayQueue<Object>(n);
                }
                else {
                    this.queue = new SpscAtomicArrayQueue<Object>(n);
                }
            }
            this.request(n);
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<?> subscriber, final Queue<?> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                this.active = null;
                return true;
            }
            if (b) {
                if (this.error.get() != null) {
                    final Throwable terminate = ExceptionsUtils.terminate(this.error);
                    this.unsubscribe();
                    queue.clear();
                    this.active = null;
                    subscriber.onError(terminate);
                    return true;
                }
                if (b2) {
                    subscriber.onCompleted();
                    return true;
                }
            }
            return false;
        }
        
        void drain() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     4: invokevirtual   java/util/concurrent/atomic/AtomicInteger.getAndIncrement:()I
            //     7: ifeq            11
            //    10: return         
            //    11: aload_0        
            //    12: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.actual:Lrx/Subscriber;
            //    15: astore          13
            //    17: aload_0        
            //    18: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.queue:Ljava/util/Queue;
            //    21: astore          14
            //    23: iconst_1       
            //    24: istore_1       
            //    25: aload_0        
            //    26: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.active:Ljava/util/Iterator;
            //    29: astore          11
            //    31: aload           11
            //    33: astore          12
            //    35: aload           11
            //    37: ifnonnull       159
            //    40: aload_0        
            //    41: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.done:Z
            //    44: istore          4
            //    46: aload           14
            //    48: invokeinterface java/util/Queue.poll:()Ljava/lang/Object;
            //    53: astore          15
            //    55: aload           15
            //    57: ifnonnull       354
            //    60: iconst_1       
            //    61: istore_3       
            //    62: aload_0        
            //    63: iload           4
            //    65: iload_3        
            //    66: aload           13
            //    68: aload           14
            //    70: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.checkTerminated:(ZZLrx/Subscriber;Ljava/util/Queue;)Z
            //    73: ifne            10
            //    76: aload           11
            //    78: astore          12
            //    80: iload_3        
            //    81: ifne            159
            //    84: aload_0        
            //    85: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.produced:J
            //    88: lconst_1       
            //    89: ladd           
            //    90: lstore          5
            //    92: lload           5
            //    94: aload_0        
            //    95: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.limit:J
            //    98: lcmp           
            //    99: ifne            359
            //   102: aload_0        
            //   103: lconst_0       
            //   104: putfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.produced:J
            //   107: aload_0        
            //   108: lload           5
            //   110: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.request:(J)V
            //   113: aload_0        
            //   114: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.mapper:Lrx/functions/Func1;
            //   117: aload_0        
            //   118: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.nl:Lrx/internal/operators/NotificationLite;
            //   121: aload           15
            //   123: invokevirtual   rx/internal/operators/NotificationLite.getValue:(Ljava/lang/Object;)Ljava/lang/Object;
            //   126: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
            //   131: checkcast       Ljava/lang/Iterable;
            //   134: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
            //   139: astore          12
            //   141: aload           12
            //   143: invokeinterface java/util/Iterator.hasNext:()Z
            //   148: istore_3       
            //   149: iload_3        
            //   150: ifeq            25
            //   153: aload_0        
            //   154: aload           12
            //   156: putfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.active:Ljava/util/Iterator;
            //   159: aload           12
            //   161: ifnull          337
            //   164: aload_0        
            //   165: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.requested:Ljava/util/concurrent/atomic/AtomicLong;
            //   168: invokevirtual   java/util/concurrent/atomic/AtomicLong.get:()J
            //   171: lstore          9
            //   173: lconst_0       
            //   174: lstore          5
            //   176: lload           5
            //   178: lstore          7
            //   180: aload           12
            //   182: astore          11
            //   184: lload           5
            //   186: lload           9
            //   188: lcmp           
            //   189: ifeq            270
            //   192: aload_0        
            //   193: aload_0        
            //   194: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.done:Z
            //   197: iconst_0       
            //   198: aload           13
            //   200: aload           14
            //   202: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.checkTerminated:(ZZLrx/Subscriber;Ljava/util/Queue;)Z
            //   205: ifne            10
            //   208: aload           12
            //   210: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   215: astore          11
            //   217: aload           13
            //   219: aload           11
            //   221: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //   224: aload_0        
            //   225: aload_0        
            //   226: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.done:Z
            //   229: iconst_0       
            //   230: aload           13
            //   232: aload           14
            //   234: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.checkTerminated:(ZZLrx/Subscriber;Ljava/util/Queue;)Z
            //   237: ifne            10
            //   240: lload           5
            //   242: lconst_1       
            //   243: ladd           
            //   244: lstore          7
            //   246: aload           12
            //   248: invokeinterface java/util/Iterator.hasNext:()Z
            //   253: istore_3       
            //   254: lload           7
            //   256: lstore          5
            //   258: iload_3        
            //   259: ifne            176
            //   262: aconst_null    
            //   263: astore          11
            //   265: aload_0        
            //   266: aconst_null    
            //   267: putfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.active:Ljava/util/Iterator;
            //   270: lload           7
            //   272: lload           9
            //   274: lcmp           
            //   275: ifne            315
            //   278: aload_0        
            //   279: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.done:Z
            //   282: istore          4
            //   284: aload           14
            //   286: invokeinterface java/util/Queue.isEmpty:()Z
            //   291: ifeq            436
            //   294: aload           11
            //   296: ifnonnull       436
            //   299: iconst_1       
            //   300: istore_3       
            //   301: aload_0        
            //   302: iload           4
            //   304: iload_3        
            //   305: aload           13
            //   307: aload           14
            //   309: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.checkTerminated:(ZZLrx/Subscriber;Ljava/util/Queue;)Z
            //   312: ifne            10
            //   315: lload           7
            //   317: lconst_0       
            //   318: lcmp           
            //   319: ifeq            332
            //   322: aload_0        
            //   323: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.requested:Ljava/util/concurrent/atomic/AtomicLong;
            //   326: lload           7
            //   328: invokestatic    rx/internal/operators/BackpressureUtils.produced:(Ljava/util/concurrent/atomic/AtomicLong;J)J
            //   331: pop2           
            //   332: aload           11
            //   334: ifnull          25
            //   337: aload_0        
            //   338: getfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.wip:Ljava/util/concurrent/atomic/AtomicInteger;
            //   341: iload_1        
            //   342: ineg           
            //   343: invokevirtual   java/util/concurrent/atomic/AtomicInteger.addAndGet:(I)I
            //   346: istore_2       
            //   347: iload_2        
            //   348: istore_1       
            //   349: iload_2        
            //   350: ifne            25
            //   353: return         
            //   354: iconst_0       
            //   355: istore_3       
            //   356: goto            62
            //   359: aload_0        
            //   360: lload           5
            //   362: putfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.produced:J
            //   365: goto            113
            //   368: astore          11
            //   370: aload           11
            //   372: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   375: aload_0        
            //   376: aload           11
            //   378: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.onError:(Ljava/lang/Throwable;)V
            //   381: goto            25
            //   384: astore          12
            //   386: aload           12
            //   388: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   391: aconst_null    
            //   392: astore          11
            //   394: aload_0        
            //   395: aconst_null    
            //   396: putfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.active:Ljava/util/Iterator;
            //   399: aload_0        
            //   400: aload           12
            //   402: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.onError:(Ljava/lang/Throwable;)V
            //   405: lload           5
            //   407: lstore          7
            //   409: goto            270
            //   412: astore          12
            //   414: aload           12
            //   416: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   419: aconst_null    
            //   420: astore          11
            //   422: aload_0        
            //   423: aconst_null    
            //   424: putfield        rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.active:Ljava/util/Iterator;
            //   427: aload_0        
            //   428: aload           12
            //   430: invokevirtual   rx/internal/operators/OnSubscribeFlattenIterable$FlattenIterableSubscriber.onError:(Ljava/lang/Throwable;)V
            //   433: goto            270
            //   436: iconst_0       
            //   437: istore_3       
            //   438: goto            301
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  113    149    368    384    Ljava/lang/Throwable;
            //  208    217    384    412    Ljava/lang/Throwable;
            //  246    254    412    436    Ljava/lang/Throwable;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 215, Size: 215
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
        public void onCompleted() {
            this.done = true;
            this.drain();
        }
        
        @Override
        public void onError(final Throwable t) {
            if (ExceptionsUtils.addThrowable(this.error, t)) {
                this.done = true;
                this.drain();
                return;
            }
            RxJavaHooks.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.queue.offer(this.nl.next(t))) {
                this.unsubscribe();
                this.onError(new MissingBackpressureException());
                return;
            }
            this.drain();
        }
        
        void requestMore(final long n) {
            if (n > 0L) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.drain();
            }
            else if (n < 0L) {
                throw new IllegalStateException("n >= 0 required but it was " + n);
            }
        }
    }
    
    static final class OnSubscribeScalarFlattenIterable<T, R> implements OnSubscribe<R>
    {
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final T value;
        
        public OnSubscribeScalarFlattenIterable(final T value, final Func1<? super T, ? extends Iterable<? extends R>> mapper) {
            this.value = value;
            this.mapper = mapper;
        }
        
        @Override
        public void call(final Subscriber<? super R> subscriber) {
            Iterator<?> iterator;
            try {
                iterator = (Iterator<?>)((Iterable)this.mapper.call((Object)this.value)).iterator();
                if (!iterator.hasNext()) {
                    subscriber.onCompleted();
                    return;
                }
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, subscriber, this.value);
                return;
            }
            subscriber.setProducer(new OnSubscribeFromIterable.IterableProducer<Object>((Subscriber<? super Object>)subscriber, iterator));
        }
    }
}
