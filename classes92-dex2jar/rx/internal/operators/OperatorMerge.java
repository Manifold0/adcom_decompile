// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.OnErrorThrowable;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.ScalarSynchronousObservable;
import rx.exceptions.CompositeException;
import java.util.Collection;
import java.util.ArrayList;
import rx.subscriptions.CompositeSubscription;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.internal.util.RxRingBuffer;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.Observable;

public final class OperatorMerge<T> implements Operator<T, Observable<? extends T>>
{
    final boolean delayErrors;
    final int maxConcurrent;
    
    OperatorMerge(final boolean delayErrors, final int maxConcurrent) {
        this.delayErrors = delayErrors;
        this.maxConcurrent = maxConcurrent;
    }
    
    public static <T> OperatorMerge<T> instance(final boolean b) {
        if (b) {
            return (OperatorMerge<T>)HolderDelayErrors.INSTANCE;
        }
        return (OperatorMerge<T>)HolderNoDelay.INSTANCE;
    }
    
    public static <T> OperatorMerge<T> instance(final boolean b, final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + n);
        }
        if (n == Integer.MAX_VALUE) {
            return instance(b);
        }
        return new OperatorMerge<T>(b, n);
    }
    
    @Override
    public Subscriber<Observable<? extends T>> call(final Subscriber<? super T> subscriber) {
        final MergeSubscriber mergeSubscriber = new MergeSubscriber((Subscriber<? super Object>)subscriber, this.delayErrors, this.maxConcurrent);
        final MergeProducer mergeProducer = new MergeProducer((MergeSubscriber<Object>)mergeSubscriber);
        mergeSubscriber.producer = (MergeProducer<T>)mergeProducer;
        subscriber.add(mergeSubscriber);
        subscriber.setProducer(mergeProducer);
        return (Subscriber<Observable<? extends T>>)mergeSubscriber;
    }
    
    static final class HolderDelayErrors
    {
        static final OperatorMerge<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorMerge<Object>(true, Integer.MAX_VALUE);
        }
    }
    
    static final class HolderNoDelay
    {
        static final OperatorMerge<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorMerge<Object>(false, Integer.MAX_VALUE);
        }
    }
    
    static final class InnerSubscriber<T> extends Subscriber<T>
    {
        static final int LIMIT;
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;
        
        static {
            LIMIT = RxRingBuffer.SIZE / 4;
        }
        
        public InnerSubscriber(final MergeSubscriber<T> parent, final long id) {
            this.parent = parent;
            this.id = id;
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(t);
            this.parent.emit();
        }
        
        @Override
        public void onNext(final T t) {
            this.parent.tryEmit(this, t);
        }
        
        @Override
        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            this.request(RxRingBuffer.SIZE);
        }
        
        public void requestMore(final long n) {
            final int outstanding = this.outstanding - (int)n;
            if (outstanding > InnerSubscriber.LIMIT) {
                this.outstanding = outstanding;
            }
            else {
                this.outstanding = RxRingBuffer.SIZE;
                final int n2 = RxRingBuffer.SIZE - outstanding;
                if (n2 > 0) {
                    this.request(n2);
                }
            }
        }
    }
    
    static final class MergeProducer<T> extends AtomicLong implements Producer
    {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;
        
        public MergeProducer(final MergeSubscriber<T> subscriber) {
            this.subscriber = subscriber;
        }
        
        public long produced(final int n) {
            return this.addAndGet(-n);
        }
        
        @Override
        public void request(final long n) {
            if (n > 0L) {
                if (this.get() != Long.MAX_VALUE) {
                    BackpressureUtils.getAndAddRequest(this, n);
                    this.subscriber.emit();
                }
            }
            else if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }
    }
    
    static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>>
    {
        static final InnerSubscriber<?>[] EMPTY;
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard;
        volatile InnerSubscriber<?>[] innerSubscribers;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        final NotificationLite<T> nl;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;
        
        static {
            EMPTY = new InnerSubscriber[0];
        }
        
        public MergeSubscriber(final Subscriber<? super T> child, final boolean delayErrors, final int maxConcurrent) {
            this.child = child;
            this.delayErrors = delayErrors;
            this.maxConcurrent = maxConcurrent;
            this.nl = NotificationLite.instance();
            this.innerGuard = new Object();
            this.innerSubscribers = MergeSubscriber.EMPTY;
            if (maxConcurrent == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                this.request(Long.MAX_VALUE);
                return;
            }
            this.scalarEmissionLimit = Math.max(1, maxConcurrent >> 1);
            this.request(maxConcurrent);
        }
        
        private void reportError() {
            final ArrayList<Throwable> list = new ArrayList<Throwable>(this.errors);
            if (list.size() == 1) {
                this.child.onError((Throwable)list.get(0));
                return;
            }
            this.child.onError(new CompositeException(list));
        }
        
        void addInner(final InnerSubscriber<T> innerSubscriber) {
            this.getOrCreateComposite().add(innerSubscriber);
            synchronized (this.innerGuard) {
                final InnerSubscriber<?>[] innerSubscribers = this.innerSubscribers;
                final int length = innerSubscribers.length;
                final InnerSubscriber[] innerSubscribers2 = new InnerSubscriber[length + 1];
                System.arraycopy(innerSubscribers, 0, innerSubscribers2, 0, length);
                innerSubscribers2[length] = innerSubscriber;
                this.innerSubscribers = (InnerSubscriber<?>[])innerSubscribers2;
            }
        }
        
        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            final ConcurrentLinkedQueue<Throwable> errors = this.errors;
            if (!this.delayErrors && errors != null && !errors.isEmpty()) {
                try {
                    this.reportError();
                    return true;
                }
                finally {
                    this.unsubscribe();
                }
            }
            return false;
        }
        
        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                // monitorexit(this)
                this.emitLoop();
            }
        }
        
        void emitEmpty() {
            final int scalarEmissionCount = this.scalarEmissionCount + 1;
            if (scalarEmissionCount == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                this.requestMore(scalarEmissionCount);
                return;
            }
            this.scalarEmissionCount = scalarEmissionCount;
        }
        
        void emitLoop() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: iconst_0       
            //     4: istore          9
            //     6: iload           9
            //     8: istore_2       
            //     9: aload_0        
            //    10: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.child:Lrx/Subscriber;
            //    13: astore          20
            //    15: iload           9
            //    17: istore_2       
            //    18: aload_0        
            //    19: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.checkTerminate:()Z
            //    22: istore          13
            //    24: iload           13
            //    26: ifeq            50
            //    29: iconst_1       
            //    30: ifne            1145
            //    33: aload_0        
            //    34: monitorenter   
            //    35: aload_0        
            //    36: iconst_0       
            //    37: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //    40: aload_0        
            //    41: monitorexit    
            //    42: return         
            //    43: astore          18
            //    45: aload_0        
            //    46: monitorexit    
            //    47: aload           18
            //    49: athrow         
            //    50: iload           9
            //    52: istore_2       
            //    53: aload_0        
            //    54: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.queue:Ljava/util/Queue;
            //    57: astore          19
            //    59: iload           9
            //    61: istore_2       
            //    62: aload_0        
            //    63: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.producer:Lrx/internal/operators/OperatorMerge$MergeProducer;
            //    66: invokevirtual   rx/internal/operators/OperatorMerge$MergeProducer.get:()J
            //    69: lstore          14
            //    71: lload           14
            //    73: ldc2_w          9223372036854775807
            //    76: lcmp           
            //    77: ifne            140
            //    80: iconst_1       
            //    81: istore          6
            //    83: goto            1146
            //    86: lload           14
            //    88: lconst_0       
            //    89: lcmp           
            //    90: ifle            151
            //    93: iload           9
            //    95: istore_2       
            //    96: aload           19
            //    98: invokeinterface java/util/Queue.poll:()Ljava/lang/Object;
            //   103: astore          18
            //   105: iload           9
            //   107: istore_2       
            //   108: aload_0        
            //   109: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.checkTerminate:()Z
            //   112: istore          13
            //   114: iload           13
            //   116: ifeq            146
            //   119: iconst_1       
            //   120: ifne            1145
            //   123: aload_0        
            //   124: monitorenter   
            //   125: aload_0        
            //   126: iconst_0       
            //   127: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   130: aload_0        
            //   131: monitorexit    
            //   132: return         
            //   133: astore          18
            //   135: aload_0        
            //   136: monitorexit    
            //   137: aload           18
            //   139: athrow         
            //   140: iconst_0       
            //   141: istore          6
            //   143: goto            1146
            //   146: aload           18
            //   148: ifnonnull       314
            //   151: iload_1        
            //   152: ifle            165
            //   155: iload           6
            //   157: ifeq            449
            //   160: ldc2_w          9223372036854775807
            //   163: lstore          14
            //   165: iload_3        
            //   166: istore_1       
            //   167: lload           14
            //   169: lstore          16
            //   171: lload           14
            //   173: lconst_0       
            //   174: lcmp           
            //   175: ifeq            195
            //   178: iload_3        
            //   179: istore_1       
            //   180: lload           14
            //   182: lstore          16
            //   184: aload           18
            //   186: ifnonnull       1165
            //   189: lload           14
            //   191: lstore          16
            //   193: iload_3        
            //   194: istore_1       
            //   195: iload           9
            //   197: istore_2       
            //   198: aload_0        
            //   199: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.done:Z
            //   202: istore          13
            //   204: iload           9
            //   206: istore_2       
            //   207: aload_0        
            //   208: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.queue:Ljava/util/Queue;
            //   211: astore          18
            //   213: iload           9
            //   215: istore_2       
            //   216: aload_0        
            //   217: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.innerSubscribers:[Lrx/internal/operators/OperatorMerge$InnerSubscriber;
            //   220: astore          21
            //   222: iload           9
            //   224: istore_2       
            //   225: aload           21
            //   227: arraylength    
            //   228: istore          12
            //   230: iload           13
            //   232: ifeq            475
            //   235: aload           18
            //   237: ifnull          253
            //   240: iload           9
            //   242: istore_2       
            //   243: aload           18
            //   245: invokeinterface java/util/Queue.isEmpty:()Z
            //   250: ifeq            475
            //   253: iload           12
            //   255: ifne            475
            //   258: iload           9
            //   260: istore_2       
            //   261: aload_0        
            //   262: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.errors:Ljava/util/concurrent/ConcurrentLinkedQueue;
            //   265: astore          18
            //   267: aload           18
            //   269: ifnull          285
            //   272: iload           9
            //   274: istore_2       
            //   275: aload           18
            //   277: invokeinterface java/util/Queue.isEmpty:()Z
            //   282: ifeq            465
            //   285: iload           9
            //   287: istore_2       
            //   288: aload           20
            //   290: invokevirtual   rx/Subscriber.onCompleted:()V
            //   293: iconst_1       
            //   294: ifne            1145
            //   297: aload_0        
            //   298: monitorenter   
            //   299: aload_0        
            //   300: iconst_0       
            //   301: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   304: aload_0        
            //   305: monitorexit    
            //   306: return         
            //   307: astore          18
            //   309: aload_0        
            //   310: monitorexit    
            //   311: aload           18
            //   313: athrow         
            //   314: iload           9
            //   316: istore_2       
            //   317: aload_0        
            //   318: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.nl:Lrx/internal/operators/NotificationLite;
            //   321: aload           18
            //   323: invokevirtual   rx/internal/operators/NotificationLite.getValue:(Ljava/lang/Object;)Ljava/lang/Object;
            //   326: astore          21
            //   328: iload           9
            //   330: istore_2       
            //   331: aload           20
            //   333: aload           21
            //   335: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //   338: iload_3        
            //   339: iconst_1       
            //   340: iadd           
            //   341: istore_3       
            //   342: iload_1        
            //   343: iconst_1       
            //   344: iadd           
            //   345: istore_1       
            //   346: lload           14
            //   348: lconst_1       
            //   349: lsub           
            //   350: lstore          14
            //   352: goto            86
            //   355: astore          21
            //   357: iload           9
            //   359: istore_2       
            //   360: aload_0        
            //   361: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.delayErrors:Z
            //   364: ifne            413
            //   367: iload           9
            //   369: istore_2       
            //   370: aload           21
            //   372: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   375: iconst_1       
            //   376: istore_1       
            //   377: iload_1        
            //   378: istore_2       
            //   379: aload_0        
            //   380: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.unsubscribe:()V
            //   383: iload_1        
            //   384: istore_2       
            //   385: aload           20
            //   387: aload           21
            //   389: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   392: iconst_1       
            //   393: ifne            1145
            //   396: aload_0        
            //   397: monitorenter   
            //   398: aload_0        
            //   399: iconst_0       
            //   400: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   403: aload_0        
            //   404: monitorexit    
            //   405: return         
            //   406: astore          18
            //   408: aload_0        
            //   409: monitorexit    
            //   410: aload           18
            //   412: athrow         
            //   413: iload           9
            //   415: istore_2       
            //   416: aload_0        
            //   417: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.getOrCreateErrorQueue:()Ljava/util/Queue;
            //   420: aload           21
            //   422: invokeinterface java/util/Queue.offer:(Ljava/lang/Object;)Z
            //   427: pop            
            //   428: goto            338
            //   431: astore          18
            //   433: iload_2        
            //   434: ifne            446
            //   437: aload_0        
            //   438: monitorenter   
            //   439: aload_0        
            //   440: iconst_0       
            //   441: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   444: aload_0        
            //   445: monitorexit    
            //   446: aload           18
            //   448: athrow         
            //   449: iload           9
            //   451: istore_2       
            //   452: aload_0        
            //   453: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.producer:Lrx/internal/operators/OperatorMerge$MergeProducer;
            //   456: iload_1        
            //   457: invokevirtual   rx/internal/operators/OperatorMerge$MergeProducer.produced:(I)J
            //   460: lstore          14
            //   462: goto            165
            //   465: iload           9
            //   467: istore_2       
            //   468: aload_0        
            //   469: invokespecial   rx/internal/operators/OperatorMerge$MergeSubscriber.reportError:()V
            //   472: goto            293
            //   475: iconst_0       
            //   476: istore          5
            //   478: iconst_0       
            //   479: istore          11
            //   481: iload_1        
            //   482: istore_3       
            //   483: iload           12
            //   485: ifle            1015
            //   488: iload           9
            //   490: istore_2       
            //   491: aload_0        
            //   492: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.lastId:J
            //   495: lstore          14
            //   497: iload           9
            //   499: istore_2       
            //   500: aload_0        
            //   501: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.lastIndex:I
            //   504: istore          4
            //   506: iload           12
            //   508: iload           4
            //   510: if_icmple       1181
            //   513: iload           9
            //   515: istore_2       
            //   516: iload           4
            //   518: istore_3       
            //   519: aload           21
            //   521: iload           4
            //   523: aaload         
            //   524: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.id:J
            //   527: lload           14
            //   529: lcmp           
            //   530: ifeq            1201
            //   533: goto            1181
            //   536: iload           4
            //   538: iload           12
            //   540: if_icmpge       559
            //   543: iload           9
            //   545: istore_2       
            //   546: aload           21
            //   548: iload_3        
            //   549: aaload         
            //   550: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.id:J
            //   553: lload           14
            //   555: lcmp           
            //   556: ifne            638
            //   559: iload_3        
            //   560: istore          4
            //   562: iload           9
            //   564: istore_2       
            //   565: aload_0        
            //   566: iload_3        
            //   567: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.lastIndex:I
            //   570: iload           9
            //   572: istore_2       
            //   573: aload_0        
            //   574: aload           21
            //   576: iload_3        
            //   577: aaload         
            //   578: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.id:J
            //   581: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.lastId:J
            //   584: iload           4
            //   586: istore_3       
            //   587: goto            1201
            //   590: iload_3        
            //   591: istore          5
            //   593: iload           4
            //   595: istore_1       
            //   596: iload           7
            //   598: iload           12
            //   600: if_icmpge       989
            //   603: iload           9
            //   605: istore_2       
            //   606: aload_0        
            //   607: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.checkTerminate:()Z
            //   610: istore          13
            //   612: iload           13
            //   614: ifeq            661
            //   617: iconst_1       
            //   618: ifne            1145
            //   621: aload_0        
            //   622: monitorenter   
            //   623: aload_0        
            //   624: iconst_0       
            //   625: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   628: aload_0        
            //   629: monitorexit    
            //   630: return         
            //   631: astore          18
            //   633: aload_0        
            //   634: monitorexit    
            //   635: aload           18
            //   637: athrow         
            //   638: iload_3        
            //   639: iconst_1       
            //   640: iadd           
            //   641: istore_2       
            //   642: iload_2        
            //   643: istore_3       
            //   644: iload_2        
            //   645: iload           12
            //   647: if_icmpne       652
            //   650: iconst_0       
            //   651: istore_3       
            //   652: iload           4
            //   654: iconst_1       
            //   655: iadd           
            //   656: istore          4
            //   658: goto            536
            //   661: aload           21
            //   663: iload           8
            //   665: aaload         
            //   666: astore          22
            //   668: aconst_null    
            //   669: astore          19
            //   671: lload           14
            //   673: lstore          16
            //   675: goto            1220
            //   678: aload           19
            //   680: astore          18
            //   682: lload           14
            //   684: lconst_0       
            //   685: lcmp           
            //   686: ifle            743
            //   689: iload           9
            //   691: istore_2       
            //   692: aload_0        
            //   693: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.checkTerminate:()Z
            //   696: istore          13
            //   698: iload           13
            //   700: ifeq            724
            //   703: iconst_1       
            //   704: ifne            1145
            //   707: aload_0        
            //   708: monitorenter   
            //   709: aload_0        
            //   710: iconst_0       
            //   711: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   714: aload_0        
            //   715: monitorexit    
            //   716: return         
            //   717: astore          18
            //   719: aload_0        
            //   720: monitorexit    
            //   721: aload           18
            //   723: athrow         
            //   724: iload           9
            //   726: istore_2       
            //   727: aload           22
            //   729: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.queue:Lrx/internal/util/RxRingBuffer;
            //   732: astore          18
            //   734: aload           18
            //   736: ifnonnull       875
            //   739: aload           19
            //   741: astore          18
            //   743: iload_1        
            //   744: ifle            1229
            //   747: iload           6
            //   749: ifne            1252
            //   752: iload           9
            //   754: istore_2       
            //   755: aload_0        
            //   756: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.producer:Lrx/internal/operators/OperatorMerge$MergeProducer;
            //   759: iload_1        
            //   760: invokevirtual   rx/internal/operators/OperatorMerge$MergeProducer.produced:(I)J
            //   763: lstore          14
            //   765: iload           9
            //   767: istore_2       
            //   768: aload           22
            //   770: iload_1        
            //   771: i2l            
            //   772: invokevirtual   rx/internal/operators/OperatorMerge$InnerSubscriber.requestMore:(J)V
            //   775: goto            1229
            //   778: iload           9
            //   780: istore_2       
            //   781: aload           22
            //   783: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.done:Z
            //   786: istore          13
            //   788: iload           9
            //   790: istore_2       
            //   791: aload           22
            //   793: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.queue:Lrx/internal/util/RxRingBuffer;
            //   796: astore          18
            //   798: iload_3        
            //   799: istore          5
            //   801: iload           4
            //   803: istore_1       
            //   804: iload           13
            //   806: ifeq            1268
            //   809: aload           18
            //   811: ifnull          831
            //   814: iload           9
            //   816: istore_2       
            //   817: iload_3        
            //   818: istore          5
            //   820: iload           4
            //   822: istore_1       
            //   823: aload           18
            //   825: invokevirtual   rx/internal/util/RxRingBuffer.isEmpty:()Z
            //   828: ifeq            1268
            //   831: iload           9
            //   833: istore_2       
            //   834: aload_0        
            //   835: aload           22
            //   837: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.removeInner:(Lrx/internal/operators/OperatorMerge$InnerSubscriber;)V
            //   840: iload           9
            //   842: istore_2       
            //   843: aload_0        
            //   844: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.checkTerminate:()Z
            //   847: istore          13
            //   849: iload           13
            //   851: ifeq            1260
            //   854: iconst_1       
            //   855: ifne            1145
            //   858: aload_0        
            //   859: monitorenter   
            //   860: aload_0        
            //   861: iconst_0       
            //   862: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   865: aload_0        
            //   866: monitorexit    
            //   867: return         
            //   868: astore          18
            //   870: aload_0        
            //   871: monitorexit    
            //   872: aload           18
            //   874: athrow         
            //   875: iload           9
            //   877: istore_2       
            //   878: aload           18
            //   880: invokevirtual   rx/internal/util/RxRingBuffer.poll:()Ljava/lang/Object;
            //   883: astore          19
            //   885: aload           19
            //   887: astore          18
            //   889: aload           19
            //   891: ifnull          743
            //   894: iload           9
            //   896: istore_2       
            //   897: aload_0        
            //   898: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.nl:Lrx/internal/operators/NotificationLite;
            //   901: aload           19
            //   903: invokevirtual   rx/internal/operators/NotificationLite.getValue:(Ljava/lang/Object;)Ljava/lang/Object;
            //   906: astore          18
            //   908: iload           9
            //   910: istore_2       
            //   911: aload           20
            //   913: aload           18
            //   915: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //   918: lload           14
            //   920: lconst_1       
            //   921: lsub           
            //   922: lstore          14
            //   924: iload_1        
            //   925: iconst_1       
            //   926: iadd           
            //   927: istore_1       
            //   928: goto            678
            //   931: astore          18
            //   933: iconst_1       
            //   934: istore_1       
            //   935: iload_1        
            //   936: istore_2       
            //   937: aload           18
            //   939: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   942: aload           20
            //   944: aload           18
            //   946: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   949: iload_1        
            //   950: istore_2       
            //   951: aload_0        
            //   952: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.unsubscribe:()V
            //   955: iconst_1       
            //   956: ifne            1145
            //   959: aload_0        
            //   960: monitorenter   
            //   961: aload_0        
            //   962: iconst_0       
            //   963: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   966: aload_0        
            //   967: monitorexit    
            //   968: return         
            //   969: astore          18
            //   971: aload_0        
            //   972: monitorexit    
            //   973: aload           18
            //   975: athrow         
            //   976: astore          18
            //   978: iload_1        
            //   979: istore_2       
            //   980: aload_0        
            //   981: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.unsubscribe:()V
            //   984: iload_1        
            //   985: istore_2       
            //   986: aload           18
            //   988: athrow         
            //   989: iload           9
            //   991: istore_2       
            //   992: aload_0        
            //   993: iload           8
            //   995: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.lastIndex:I
            //   998: iload           9
            //  1000: istore_2       
            //  1001: aload_0        
            //  1002: aload           21
            //  1004: iload           8
            //  1006: aaload         
            //  1007: getfield        rx/internal/operators/OperatorMerge$InnerSubscriber.id:J
            //  1010: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.lastId:J
            //  1013: iload_1        
            //  1014: istore_3       
            //  1015: iload_3        
            //  1016: ifle            1028
            //  1019: iload           9
            //  1021: istore_2       
            //  1022: aload_0        
            //  1023: iload_3        
            //  1024: i2l            
            //  1025: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.request:(J)V
            //  1028: iload           5
            //  1030: ifne            15
            //  1033: iload           9
            //  1035: istore_2       
            //  1036: aload_0        
            //  1037: monitorenter   
            //  1038: iload           10
            //  1040: istore_2       
            //  1041: aload_0        
            //  1042: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.missed:Z
            //  1045: ifne            1115
            //  1048: iconst_1       
            //  1049: istore_1       
            //  1050: iload_1        
            //  1051: istore_2       
            //  1052: aload_0        
            //  1053: iconst_0       
            //  1054: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //  1057: iload_1        
            //  1058: istore_2       
            //  1059: aload_0        
            //  1060: monitorexit    
            //  1061: iconst_1       
            //  1062: ifne            1145
            //  1065: aload_0        
            //  1066: monitorenter   
            //  1067: aload_0        
            //  1068: iconst_0       
            //  1069: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //  1072: aload_0        
            //  1073: monitorexit    
            //  1074: return         
            //  1075: astore          18
            //  1077: aload_0        
            //  1078: monitorexit    
            //  1079: aload           18
            //  1081: athrow         
            //  1082: iload           8
            //  1084: iconst_1       
            //  1085: iadd           
            //  1086: istore_3       
            //  1087: iload_3        
            //  1088: istore_2       
            //  1089: iload_3        
            //  1090: iload           12
            //  1092: if_icmpne       1097
            //  1095: iconst_0       
            //  1096: istore_2       
            //  1097: iload           7
            //  1099: iconst_1       
            //  1100: iadd           
            //  1101: istore          7
            //  1103: iload           5
            //  1105: istore_3       
            //  1106: iload_2        
            //  1107: istore          8
            //  1109: iload_1        
            //  1110: istore          4
            //  1112: goto            590
            //  1115: iload           10
            //  1117: istore_2       
            //  1118: aload_0        
            //  1119: iconst_0       
            //  1120: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.missed:Z
            //  1123: iload           10
            //  1125: istore_2       
            //  1126: aload_0        
            //  1127: monitorexit    
            //  1128: goto            15
            //  1131: astore          18
            //  1133: aload_0        
            //  1134: monitorexit    
            //  1135: aload           18
            //  1137: athrow         
            //  1138: astore          18
            //  1140: aload_0        
            //  1141: monitorexit    
            //  1142: aload           18
            //  1144: athrow         
            //  1145: return         
            //  1146: iconst_0       
            //  1147: istore_2       
            //  1148: iconst_0       
            //  1149: istore_1       
            //  1150: lload           14
            //  1152: lstore          16
            //  1154: aload           19
            //  1156: ifnull          195
            //  1159: lload           14
            //  1161: lstore          16
            //  1163: iload_2        
            //  1164: istore_1       
            //  1165: iconst_0       
            //  1166: istore_2       
            //  1167: aconst_null    
            //  1168: astore          18
            //  1170: iload_1        
            //  1171: istore_3       
            //  1172: lload           16
            //  1174: lstore          14
            //  1176: iload_2        
            //  1177: istore_1       
            //  1178: goto            86
            //  1181: iload           4
            //  1183: istore_2       
            //  1184: iload           12
            //  1186: iload           4
            //  1188: if_icmpgt       1193
            //  1191: iconst_0       
            //  1192: istore_2       
            //  1193: iconst_0       
            //  1194: istore          4
            //  1196: iload_2        
            //  1197: istore_3       
            //  1198: goto            536
            //  1201: iload_3        
            //  1202: istore          8
            //  1204: iconst_0       
            //  1205: istore          7
            //  1207: iload           11
            //  1209: istore_3       
            //  1210: iload_1        
            //  1211: istore          4
            //  1213: lload           16
            //  1215: lstore          14
            //  1217: goto            590
            //  1220: iconst_0       
            //  1221: istore_1       
            //  1222: lload           16
            //  1224: lstore          14
            //  1226: goto            678
            //  1229: lload           14
            //  1231: lconst_0       
            //  1232: lcmp           
            //  1233: ifeq            778
            //  1236: aload           18
            //  1238: astore          19
            //  1240: lload           14
            //  1242: lstore          16
            //  1244: aload           18
            //  1246: ifnonnull       1220
            //  1249: goto            778
            //  1252: ldc2_w          9223372036854775807
            //  1255: lstore          14
            //  1257: goto            765
            //  1260: iload           4
            //  1262: iconst_1       
            //  1263: iadd           
            //  1264: istore_1       
            //  1265: iconst_1       
            //  1266: istore          5
            //  1268: lload           14
            //  1270: lconst_0       
            //  1271: lcmp           
            //  1272: ifne            1082
            //  1275: goto            989
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  9      15     431    1145   Any
            //  18     24     431    1145   Any
            //  35     42     43     50     Any
            //  45     47     43     50     Any
            //  53     59     431    1145   Any
            //  62     71     431    1145   Any
            //  96     105    431    1145   Any
            //  108    114    431    1145   Any
            //  125    132    133    140    Any
            //  135    137    133    140    Any
            //  198    204    431    1145   Any
            //  207    213    431    1145   Any
            //  216    222    431    1145   Any
            //  225    230    431    1145   Any
            //  243    253    431    1145   Any
            //  261    267    431    1145   Any
            //  275    285    431    1145   Any
            //  288    293    431    1145   Any
            //  299    306    307    314    Any
            //  309    311    307    314    Any
            //  317    328    431    1145   Any
            //  331    338    355    431    Ljava/lang/Throwable;
            //  331    338    431    1145   Any
            //  360    367    431    1145   Any
            //  370    375    431    1145   Any
            //  379    383    431    1145   Any
            //  385    392    431    1145   Any
            //  398    405    406    413    Any
            //  408    410    406    413    Any
            //  416    428    431    1145   Any
            //  439    446    1138   1145   Any
            //  452    462    431    1145   Any
            //  468    472    431    1145   Any
            //  491    497    431    1145   Any
            //  500    506    431    1145   Any
            //  519    533    431    1145   Any
            //  546    559    431    1145   Any
            //  565    570    431    1145   Any
            //  573    584    431    1145   Any
            //  606    612    431    1145   Any
            //  623    630    631    638    Any
            //  633    635    631    638    Any
            //  692    698    431    1145   Any
            //  709    716    717    724    Any
            //  719    721    717    724    Any
            //  727    734    431    1145   Any
            //  755    765    431    1145   Any
            //  768    775    431    1145   Any
            //  781    788    431    1145   Any
            //  791    798    431    1145   Any
            //  823    831    431    1145   Any
            //  834    840    431    1145   Any
            //  843    849    431    1145   Any
            //  860    867    868    875    Any
            //  870    872    868    875    Any
            //  878    885    431    1145   Any
            //  897    908    431    1145   Any
            //  911    918    931    989    Ljava/lang/Throwable;
            //  911    918    431    1145   Any
            //  937    942    431    1145   Any
            //  942    949    976    989    Any
            //  951    955    431    1145   Any
            //  961    968    969    976    Any
            //  971    973    969    976    Any
            //  980    984    431    1145   Any
            //  986    989    431    1145   Any
            //  992    998    431    1145   Any
            //  1001   1013   431    1145   Any
            //  1022   1028   431    1145   Any
            //  1036   1038   431    1145   Any
            //  1041   1048   1131   1138   Any
            //  1052   1057   1131   1138   Any
            //  1059   1061   1131   1138   Any
            //  1067   1074   1075   1082   Any
            //  1077   1079   1075   1082   Any
            //  1118   1123   1131   1138   Any
            //  1126   1128   1131   1138   Any
            //  1133   1135   1131   1138   Any
            //  1135   1138   431    1145   Any
            //  1140   1142   1138   1145   Any
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
            //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
            //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
        
        protected void emitScalar(final T p0, final long p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: istore          5
            //     3: iload           5
            //     5: istore          4
            //     7: aload_0        
            //     8: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.child:Lrx/Subscriber;
            //    11: aload_1        
            //    12: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //    15: lload_2        
            //    16: ldc2_w          9223372036854775807
            //    19: lcmp           
            //    20: ifeq            36
            //    23: iload           5
            //    25: istore          4
            //    27: aload_0        
            //    28: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.producer:Lrx/internal/operators/OperatorMerge$MergeProducer;
            //    31: iconst_1       
            //    32: invokevirtual   rx/internal/operators/OperatorMerge$MergeProducer.produced:(I)J
            //    35: pop2           
            //    36: iload           5
            //    38: istore          4
            //    40: aload_0        
            //    41: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.scalarEmissionCount:I
            //    44: iconst_1       
            //    45: iadd           
            //    46: istore          6
            //    48: iload           5
            //    50: istore          4
            //    52: iload           6
            //    54: aload_0        
            //    55: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.scalarEmissionLimit:I
            //    58: if_icmpne       212
            //    61: iload           5
            //    63: istore          4
            //    65: aload_0        
            //    66: iconst_0       
            //    67: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.scalarEmissionCount:I
            //    70: iload           5
            //    72: istore          4
            //    74: aload_0        
            //    75: iload           6
            //    77: i2l            
            //    78: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.requestMore:(J)V
            //    81: iload           5
            //    83: istore          4
            //    85: aload_0        
            //    86: monitorenter   
            //    87: iconst_1       
            //    88: istore          4
            //    90: aload_0        
            //    91: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.missed:Z
            //    94: ifne            230
            //    97: aload_0        
            //    98: iconst_0       
            //    99: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   102: aload_0        
            //   103: monitorexit    
            //   104: iconst_1       
            //   105: ifne            117
            //   108: aload_0        
            //   109: monitorenter   
            //   110: aload_0        
            //   111: iconst_0       
            //   112: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   115: aload_0        
            //   116: monitorexit    
            //   117: return         
            //   118: astore_1       
            //   119: iload           5
            //   121: istore          4
            //   123: aload_0        
            //   124: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.delayErrors:Z
            //   127: ifne            177
            //   130: iload           5
            //   132: istore          4
            //   134: aload_1        
            //   135: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   138: iconst_1       
            //   139: istore          5
            //   141: iload           5
            //   143: istore          4
            //   145: aload_0        
            //   146: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.unsubscribe:()V
            //   149: iload           5
            //   151: istore          4
            //   153: aload_0        
            //   154: aload_1        
            //   155: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.onError:(Ljava/lang/Throwable;)V
            //   158: iconst_1       
            //   159: ifne            117
            //   162: aload_0        
            //   163: monitorenter   
            //   164: aload_0        
            //   165: iconst_0       
            //   166: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   169: aload_0        
            //   170: monitorexit    
            //   171: return         
            //   172: astore_1       
            //   173: aload_0        
            //   174: monitorexit    
            //   175: aload_1        
            //   176: athrow         
            //   177: iload           5
            //   179: istore          4
            //   181: aload_0        
            //   182: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.getOrCreateErrorQueue:()Ljava/util/Queue;
            //   185: aload_1        
            //   186: invokeinterface java/util/Queue.offer:(Ljava/lang/Object;)Z
            //   191: pop            
            //   192: goto            15
            //   195: astore_1       
            //   196: iload           4
            //   198: ifne            210
            //   201: aload_0        
            //   202: monitorenter   
            //   203: aload_0        
            //   204: iconst_0       
            //   205: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   208: aload_0        
            //   209: monitorexit    
            //   210: aload_1        
            //   211: athrow         
            //   212: iload           5
            //   214: istore          4
            //   216: aload_0        
            //   217: iload           6
            //   219: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.scalarEmissionCount:I
            //   222: goto            81
            //   225: astore_1       
            //   226: aload_0        
            //   227: monitorexit    
            //   228: aload_1        
            //   229: athrow         
            //   230: aload_0        
            //   231: iconst_0       
            //   232: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.missed:Z
            //   235: aload_0        
            //   236: monitorexit    
            //   237: iconst_1       
            //   238: ifne            250
            //   241: aload_0        
            //   242: monitorenter   
            //   243: aload_0        
            //   244: iconst_0       
            //   245: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   248: aload_0        
            //   249: monitorexit    
            //   250: aload_0        
            //   251: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.emitLoop:()V
            //   254: return         
            //   255: astore_1       
            //   256: aload_0        
            //   257: monitorexit    
            //   258: aload_1        
            //   259: athrow         
            //   260: astore_1       
            //   261: aload_0        
            //   262: monitorexit    
            //   263: aload_1        
            //   264: athrow         
            //   265: astore_1       
            //   266: aload_0        
            //   267: monitorexit    
            //   268: aload_1        
            //   269: athrow         
            //    Signature:
            //  (TT;J)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  7      15     118    195    Ljava/lang/Throwable;
            //  7      15     195    270    Any
            //  27     36     195    270    Any
            //  40     48     195    270    Any
            //  52     61     195    270    Any
            //  65     70     195    270    Any
            //  74     81     195    270    Any
            //  85     87     195    270    Any
            //  90     104    255    260    Any
            //  110    117    225    230    Any
            //  123    130    195    270    Any
            //  134    138    195    270    Any
            //  145    149    195    270    Any
            //  153    158    195    270    Any
            //  164    171    172    177    Any
            //  173    175    172    177    Any
            //  181    192    195    270    Any
            //  203    210    265    270    Any
            //  216    222    195    270    Any
            //  226    228    225    230    Any
            //  230    237    255    260    Any
            //  243    250    260    265    Any
            //  256    258    255    260    Any
            //  258    260    195    270    Any
            //  261    263    260    265    Any
            //  266    268    265    270    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
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
        
        protected void emitScalar(final InnerSubscriber<T> p0, final T p1, final long p2) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: istore          6
            //     3: iload           6
            //     5: istore          5
            //     7: aload_0        
            //     8: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.child:Lrx/Subscriber;
            //    11: aload_2        
            //    12: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //    15: lload_3        
            //    16: ldc2_w          9223372036854775807
            //    19: lcmp           
            //    20: ifeq            36
            //    23: iload           6
            //    25: istore          5
            //    27: aload_0        
            //    28: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.producer:Lrx/internal/operators/OperatorMerge$MergeProducer;
            //    31: iconst_1       
            //    32: invokevirtual   rx/internal/operators/OperatorMerge$MergeProducer.produced:(I)J
            //    35: pop2           
            //    36: iload           6
            //    38: istore          5
            //    40: aload_1        
            //    41: lconst_1       
            //    42: invokevirtual   rx/internal/operators/OperatorMerge$InnerSubscriber.requestMore:(J)V
            //    45: iload           6
            //    47: istore          5
            //    49: aload_0        
            //    50: monitorenter   
            //    51: iconst_1       
            //    52: istore          5
            //    54: aload_0        
            //    55: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.missed:Z
            //    58: ifne            181
            //    61: aload_0        
            //    62: iconst_0       
            //    63: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //    66: aload_0        
            //    67: monitorexit    
            //    68: iconst_1       
            //    69: ifne            81
            //    72: aload_0        
            //    73: monitorenter   
            //    74: aload_0        
            //    75: iconst_0       
            //    76: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //    79: aload_0        
            //    80: monitorexit    
            //    81: return         
            //    82: astore_2       
            //    83: iload           6
            //    85: istore          5
            //    87: aload_0        
            //    88: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.delayErrors:Z
            //    91: ifne            141
            //    94: iload           6
            //    96: istore          5
            //    98: aload_2        
            //    99: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   102: iconst_1       
            //   103: istore          6
            //   105: iload           6
            //   107: istore          5
            //   109: aload_1        
            //   110: invokevirtual   rx/internal/operators/OperatorMerge$InnerSubscriber.unsubscribe:()V
            //   113: iload           6
            //   115: istore          5
            //   117: aload_1        
            //   118: aload_2        
            //   119: invokevirtual   rx/internal/operators/OperatorMerge$InnerSubscriber.onError:(Ljava/lang/Throwable;)V
            //   122: iconst_1       
            //   123: ifne            81
            //   126: aload_0        
            //   127: monitorenter   
            //   128: aload_0        
            //   129: iconst_0       
            //   130: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   133: aload_0        
            //   134: monitorexit    
            //   135: return         
            //   136: astore_1       
            //   137: aload_0        
            //   138: monitorexit    
            //   139: aload_1        
            //   140: athrow         
            //   141: iload           6
            //   143: istore          5
            //   145: aload_0        
            //   146: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.getOrCreateErrorQueue:()Ljava/util/Queue;
            //   149: aload_2        
            //   150: invokeinterface java/util/Queue.offer:(Ljava/lang/Object;)Z
            //   155: pop            
            //   156: goto            15
            //   159: astore_1       
            //   160: iload           5
            //   162: ifne            174
            //   165: aload_0        
            //   166: monitorenter   
            //   167: aload_0        
            //   168: iconst_0       
            //   169: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   172: aload_0        
            //   173: monitorexit    
            //   174: aload_1        
            //   175: athrow         
            //   176: astore_1       
            //   177: aload_0        
            //   178: monitorexit    
            //   179: aload_1        
            //   180: athrow         
            //   181: aload_0        
            //   182: iconst_0       
            //   183: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.missed:Z
            //   186: aload_0        
            //   187: monitorexit    
            //   188: iconst_1       
            //   189: ifne            201
            //   192: aload_0        
            //   193: monitorenter   
            //   194: aload_0        
            //   195: iconst_0       
            //   196: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.emitting:Z
            //   199: aload_0        
            //   200: monitorexit    
            //   201: aload_0        
            //   202: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.emitLoop:()V
            //   205: return         
            //   206: astore_1       
            //   207: aload_0        
            //   208: monitorexit    
            //   209: aload_1        
            //   210: athrow         
            //   211: astore_1       
            //   212: aload_0        
            //   213: monitorexit    
            //   214: aload_1        
            //   215: athrow         
            //   216: astore_1       
            //   217: aload_0        
            //   218: monitorexit    
            //   219: aload_1        
            //   220: athrow         
            //    Signature:
            //  (Lrx/internal/operators/OperatorMerge$InnerSubscriber<TT;>;TT;J)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  7      15     82     159    Ljava/lang/Throwable;
            //  7      15     159    221    Any
            //  27     36     159    221    Any
            //  40     45     159    221    Any
            //  49     51     159    221    Any
            //  54     68     206    211    Any
            //  74     81     176    181    Any
            //  87     94     159    221    Any
            //  98     102    159    221    Any
            //  109    113    159    221    Any
            //  117    122    159    221    Any
            //  128    135    136    141    Any
            //  137    139    136    141    Any
            //  145    156    159    221    Any
            //  167    174    216    221    Any
            //  177    179    176    181    Any
            //  181    188    206    211    Any
            //  194    201    211    216    Any
            //  207    209    206    211    Any
            //  209    211    159    221    Any
            //  212    214    211    216    Any
            //  217    219    216    221    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0081:
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
        
        CompositeSubscription getOrCreateComposite() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     4: astore_2       
            //     5: aload_2        
            //     6: astore_3       
            //     7: aload_2        
            //     8: ifnonnull       56
            //    11: iconst_0       
            //    12: istore_1       
            //    13: aload_0        
            //    14: monitorenter   
            //    15: aload_0        
            //    16: getfield        rx/internal/operators/OperatorMerge$MergeSubscriber.subscriptions:Lrx/subscriptions/CompositeSubscription;
            //    19: astore_3       
            //    20: aload_3        
            //    21: astore_2       
            //    22: aload_3        
            //    23: ifnonnull       41
            //    26: new             Lrx/subscriptions/CompositeSubscription;
            //    29: dup            
            //    30: invokespecial   rx/subscriptions/CompositeSubscription.<init>:()V
            //    33: astore_2       
            //    34: aload_0        
            //    35: aload_2        
            //    36: putfield        rx/internal/operators/OperatorMerge$MergeSubscriber.subscriptions:Lrx/subscriptions/CompositeSubscription;
            //    39: iconst_1       
            //    40: istore_1       
            //    41: aload_0        
            //    42: monitorexit    
            //    43: aload_2        
            //    44: astore_3       
            //    45: iload_1        
            //    46: ifeq            56
            //    49: aload_0        
            //    50: aload_2        
            //    51: invokevirtual   rx/internal/operators/OperatorMerge$MergeSubscriber.add:(Lrx/Subscription;)V
            //    54: aload_2        
            //    55: astore_3       
            //    56: aload_3        
            //    57: areturn        
            //    58: astore_2       
            //    59: aload_0        
            //    60: monitorexit    
            //    61: aload_2        
            //    62: athrow         
            //    63: astore_2       
            //    64: goto            59
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  15     20     58     59     Any
            //  26     34     58     59     Any
            //  34     39     63     67     Any
            //  41     43     58     59     Any
            //  59     61     58     59     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
        
        Queue<Throwable> getOrCreateErrorQueue() {
            final ConcurrentLinkedQueue<Throwable> errors = this.errors;
            if (errors != null) {
                return errors;
            }
            // monitorenter(this)
        Label_0039:
            while (true) {
                try {
                    while (true) {
                        ConcurrentLinkedQueue<Throwable> errors2;
                        if ((errors2 = this.errors) == null) {
                            errors2 = new ConcurrentLinkedQueue<Throwable>();
                            try {
                                this.errors = errors2;
                                // monitorexit(this)
                                return errors2;
                                // monitorexit(this)
                                throw;
                            }
                            finally {
                                continue Label_0039;
                            }
                            return errors;
                        }
                        continue;
                    }
                }
                finally {
                    continue Label_0039;
                }
                break;
            }
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.emit();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.getOrCreateErrorQueue().offer(t);
            this.done = true;
            this.emit();
        }
        
        @Override
        public void onNext(final Observable<? extends T> observable) {
            if (observable == null) {
                return;
            }
            if (observable == Observable.empty()) {
                this.emitEmpty();
                return;
            }
            if (observable instanceof ScalarSynchronousObservable) {
                this.tryEmit(((ScalarSynchronousObservable<T>)observable).get());
                return;
            }
            final long uniqueId = this.uniqueId;
            this.uniqueId = 1L + uniqueId;
            final InnerSubscriber innerSubscriber = new InnerSubscriber<Object>((MergeSubscriber<Object>)this, uniqueId);
            this.addInner((InnerSubscriber<T>)innerSubscriber);
            observable.unsafeSubscribe((Subscriber<? super Object>)innerSubscriber);
            this.emit();
        }
        
        protected void queueScalar(final T t) {
            Queue<Object> queue;
            if ((queue = this.queue) == null) {
                final int maxConcurrent = this.maxConcurrent;
                if (maxConcurrent == Integer.MAX_VALUE) {
                    queue = new SpscUnboundedAtomicArrayQueue<Object>(RxRingBuffer.SIZE);
                }
                else if (Pow2.isPowerOfTwo(maxConcurrent)) {
                    if (UnsafeAccess.isUnsafeAvailable()) {
                        queue = new SpscArrayQueue<Object>(maxConcurrent);
                    }
                    else {
                        queue = new SpscAtomicArrayQueue<Object>(maxConcurrent);
                    }
                }
                else {
                    queue = new SpscExactAtomicArrayQueue<Object>(maxConcurrent);
                }
                this.queue = queue;
            }
            if (!queue.offer(this.nl.next(t))) {
                this.unsubscribe();
                this.onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
            }
        }
        
        protected void queueScalar(final InnerSubscriber<T> innerSubscriber, final T t) {
            RxRingBuffer queue;
            if ((queue = innerSubscriber.queue) == null) {
                queue = RxRingBuffer.getSpscInstance();
                innerSubscriber.add(queue);
                innerSubscriber.queue = queue;
            }
            try {
                queue.onNext(this.nl.next(t));
            }
            catch (MissingBackpressureException ex) {
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(ex);
            }
            catch (IllegalStateException ex2) {
                if (!innerSubscriber.isUnsubscribed()) {
                    innerSubscriber.unsubscribe();
                    innerSubscriber.onError(ex2);
                }
            }
        }
        
        void removeInner(final InnerSubscriber<T> innerSubscriber) {
            final RxRingBuffer queue = innerSubscriber.queue;
            if (queue != null) {
                queue.release();
            }
            InnerSubscriber<?>[] innerSubscribers;
            int length;
            int n3;
            while (true) {
                this.subscriptions.remove(innerSubscriber);
                while (true) {
                    int n2 = 0;
                    Label_0144: {
                        synchronized (this.innerGuard) {
                            innerSubscribers = this.innerSubscribers;
                            length = innerSubscribers.length;
                            final int n = -1;
                            n2 = 0;
                            n3 = n;
                            if (n2 < length) {
                                if (!innerSubscriber.equals(innerSubscribers[n2])) {
                                    break Label_0144;
                                }
                                n3 = n2;
                            }
                            if (n3 < 0) {
                                return;
                            }
                            if (length == 1) {
                                this.innerSubscribers = MergeSubscriber.EMPTY;
                                return;
                            }
                        }
                        break;
                    }
                    ++n2;
                    continue;
                }
            }
            final InnerSubscriber[] innerSubscribers2 = new InnerSubscriber[length - 1];
            System.arraycopy(innerSubscribers, 0, innerSubscribers2, 0, n3);
            System.arraycopy(innerSubscribers, n3 + 1, innerSubscribers2, n3, length - n3 - 1);
            this.innerSubscribers = (InnerSubscriber<?>[])innerSubscribers2;
        }
        // monitorexit(o)
        
        public void requestMore(final long n) {
            this.request(n);
        }
        
        void tryEmit(final T t) {
            int n = 0;
            final boolean b = false;
            while (true) {
                long n2;
                if ((n2 = this.producer.get()) != 0L) {
                    final T t2;
                    Label_0110: {
                        synchronized (this) {
                            n2 = this.producer.get();
                            n = (b ? 1 : 0);
                            if (!this.emitting) {
                                n = (b ? 1 : 0);
                                if (n2 != 0L) {
                                    this.emitting = true;
                                    n = 1;
                                }
                            }
                            // monitorexit(this)
                            if (n == 0) {
                                break Label_0110;
                            }
                            final Queue<Object> queue = this.queue;
                            if (queue == null || queue.isEmpty()) {
                                this.emitScalar(t, n2);
                                return;
                            }
                        }
                        this.queueScalar(t2);
                        this.emitLoop();
                        return;
                    }
                    this.queueScalar(t2);
                    this.emit();
                    return;
                }
                continue;
            }
        }
        
        void tryEmit(final InnerSubscriber<T> innerSubscriber, final T t) {
            int n = 0;
            final boolean b = false;
            while (true) {
                long n2;
                if ((n2 = this.producer.get()) != 0L) {
                    final InnerSubscriber<T> innerSubscriber2;
                    Label_0113: {
                        synchronized (this) {
                            n2 = this.producer.get();
                            n = (b ? 1 : 0);
                            if (!this.emitting) {
                                n = (b ? 1 : 0);
                                if (n2 != 0L) {
                                    this.emitting = true;
                                    n = 1;
                                }
                            }
                            // monitorexit(this)
                            if (n == 0) {
                                break Label_0113;
                            }
                            final RxRingBuffer queue = innerSubscriber.queue;
                            if (queue == null || queue.isEmpty()) {
                                this.emitScalar(innerSubscriber, t, n2);
                                return;
                            }
                        }
                        this.queueScalar(innerSubscriber2, t);
                        this.emitLoop();
                        return;
                    }
                    this.queueScalar(innerSubscriber2, t);
                    this.emit();
                    return;
                }
                continue;
            }
        }
    }
}
