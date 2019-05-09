// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.MissingBackpressureException;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.functions.Action1;
import rx.Subscription;
import rx.Producer;
import rx.internal.util.RxRingBuffer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;
import java.util.concurrent.atomic.AtomicReference;
import rx.observables.ConnectableObservable;

public final class OperatorPublish<T> extends ConnectableObservable<T>
{
    final AtomicReference<PublishSubscriber<T>> current;
    final Observable<? extends T> source;
    
    private OperatorPublish(final OnSubscribe<T> onSubscribe, final Observable<? extends T> source, final AtomicReference<PublishSubscriber<T>> current) {
        super(onSubscribe);
        this.source = source;
        this.current = current;
    }
    
    public static <T, R> Observable<R> create(final Observable<? extends T> observable, final Func1<? super Observable<T>, ? extends Observable<R>> func1) {
        return create((Observable<?>)observable, (Func1<? super Observable<Object>, ? extends Observable<R>>)func1, false);
    }
    
    public static <T, R> Observable<R> create(final Observable<? extends T> observable, final Func1<? super Observable<T>, ? extends Observable<R>> func1, final boolean b) {
        return Observable.create((OnSubscribe<R>)new OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                final OnSubscribePublishMulticast onSubscribePublishMulticast = new OnSubscribePublishMulticast<Object>(RxRingBuffer.SIZE, b);
                final Subscriber<R> subscriber2 = new Subscriber<R>() {
                    @Override
                    public void onCompleted() {
                        onSubscribePublishMulticast.unsubscribe();
                        subscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        onSubscribePublishMulticast.unsubscribe();
                        subscriber.onError(t);
                    }
                    
                    @Override
                    public void onNext(final R r) {
                        subscriber.onNext(r);
                    }
                    
                    @Override
                    public void setProducer(final Producer producer) {
                        subscriber.setProducer(producer);
                    }
                };
                subscriber.add(onSubscribePublishMulticast);
                subscriber.add(subscriber2);
                func1.call(Observable.create((OnSubscribe<Object>)onSubscribePublishMulticast)).unsafeSubscribe(subscriber2);
                observable.unsafeSubscribe(onSubscribePublishMulticast.subscriber());
            }
        });
    }
    
    public static <T> ConnectableObservable<T> create(final Observable<? extends T> observable) {
        final AtomicReference<PublishSubscriber<T>> atomicReference = (AtomicReference<PublishSubscriber<T>>)new AtomicReference<PublishSubscriber<Object>>();
        return new OperatorPublish<T>((OnSubscribe<Object>)new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                InnerProducer producer;
                while (true) {
                    final PublishSubscriber publishSubscriber = atomicReference.get();
                    PublishSubscriber publishSubscriber2 = null;
                    Label_0052: {
                        if (publishSubscriber != null) {
                            publishSubscriber2 = publishSubscriber;
                            if (!publishSubscriber.isUnsubscribed()) {
                                break Label_0052;
                            }
                        }
                        publishSubscriber2 = new PublishSubscriber(atomicReference);
                        publishSubscriber2.init();
                        if (!atomicReference.compareAndSet(publishSubscriber, publishSubscriber2)) {
                            continue;
                        }
                    }
                    producer = new InnerProducer(publishSubscriber2, (Subscriber<? super Object>)subscriber);
                    if (publishSubscriber2.add((InnerProducer<Object>)producer)) {
                        break;
                    }
                }
                subscriber.add(producer);
                subscriber.setProducer(producer);
            }
        }, observable, (AtomicReference<PublishSubscriber<Object>>)atomicReference);
    }
    
    @Override
    public void connect(final Action1<? super Subscription> action1) {
        int n = 1;
        PublishSubscriber<T> publishSubscriber;
        PublishSubscriber<T> publishSubscriber2;
        do {
            publishSubscriber = this.current.get();
            if (publishSubscriber != null) {
                publishSubscriber2 = publishSubscriber;
                if (!publishSubscriber.isUnsubscribed()) {
                    break;
                }
            }
            publishSubscriber2 = new PublishSubscriber<T>(this.current);
            publishSubscriber2.init();
        } while (!this.current.compareAndSet(publishSubscriber, publishSubscriber2));
        if (publishSubscriber2.shouldConnect.get() || !publishSubscriber2.shouldConnect.compareAndSet(false, true)) {
            n = 0;
        }
        action1.call(publishSubscriber2);
        if (n != 0) {
            this.source.unsafeSubscribe(publishSubscriber2);
        }
    }
    
    static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription
    {
        static final long NOT_REQUESTED = -4611686018427387904L;
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        final PublishSubscriber<T> parent;
        
        public InnerProducer(final PublishSubscriber<T> parent, final Subscriber<? super T> child) {
            this.parent = parent;
            this.child = child;
            this.lazySet(-4611686018427387904L);
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() == Long.MIN_VALUE;
        }
        
        public long produced(final long n) {
            if (n <= 0L) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            long value;
            long n2;
            do {
                value = this.get();
                if (value == -4611686018427387904L) {
                    throw new IllegalStateException("Produced without request");
                }
                if (value == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                n2 = value - n;
                if (n2 < 0L) {
                    throw new IllegalStateException("More produced (" + n + ") than requested (" + value + ")");
                }
            } while (!this.compareAndSet(value, n2));
            return n2;
        }
        
        @Override
        public void request(final long n) {
            if (n >= 0L) {
                long value;
                long n2;
                do {
                    value = this.get();
                    if (value == Long.MIN_VALUE || (value >= 0L && n == 0L)) {
                        return;
                    }
                    if (value == -4611686018427387904L) {
                        n2 = n;
                    }
                    else if ((n2 = value + n) < 0L) {
                        n2 = Long.MAX_VALUE;
                    }
                } while (!this.compareAndSet(value, n2));
                this.parent.dispatch();
            }
        }
        
        @Override
        public void unsubscribe() {
            if (this.get() != Long.MIN_VALUE && this.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.dispatch();
            }
        }
    }
    
    static final class PublishSubscriber<T> extends Subscriber<T> implements Subscription
    {
        static final InnerProducer[] EMPTY;
        static final InnerProducer[] TERMINATED;
        final AtomicReference<PublishSubscriber<T>> current;
        boolean emitting;
        boolean missed;
        final NotificationLite<T> nl;
        final AtomicReference<InnerProducer[]> producers;
        final Queue<Object> queue;
        final AtomicBoolean shouldConnect;
        volatile Object terminalEvent;
        
        static {
            EMPTY = new InnerProducer[0];
            TERMINATED = new InnerProducer[0];
        }
        
        public PublishSubscriber(final AtomicReference<PublishSubscriber<T>> current) {
            Object queue;
            if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new SpscArrayQueue<Object>(RxRingBuffer.SIZE);
            }
            else {
                queue = new SynchronizedQueue<Object>(RxRingBuffer.SIZE);
            }
            this.queue = (Queue<Object>)queue;
            this.nl = NotificationLite.instance();
            this.producers = new AtomicReference<InnerProducer[]>(PublishSubscriber.EMPTY);
            this.current = current;
            this.shouldConnect = new AtomicBoolean();
        }
        
        boolean add(final InnerProducer<T> innerProducer) {
            if (innerProducer == null) {
                throw new NullPointerException();
            }
            InnerProducer[] array;
            InnerProducer[] array2;
            do {
                array = this.producers.get();
                if (array == PublishSubscriber.TERMINATED) {
                    return false;
                }
                final int length = array.length;
                array2 = new InnerProducer[length + 1];
                System.arraycopy(array, 0, array2, 0, length);
                array2[length] = innerProducer;
            } while (!this.producers.compareAndSet(array, array2));
            return true;
        }
        
        boolean checkTerminated(final Object o, final boolean b) {
            if (o != null) {
                if (this.nl.isCompleted(o)) {
                    if (!b) {
                        return false;
                    }
                    this.current.compareAndSet(this, null);
                    try {
                        final InnerProducer[] array = this.producers.getAndSet(PublishSubscriber.TERMINATED);
                        for (int length = array.length, i = 0; i < length; ++i) {
                            array[i].child.onCompleted();
                        }
                        return true;
                    }
                    finally {
                        this.unsubscribe();
                    }
                }
                final Throwable error = this.nl.getError(o);
                this.current.compareAndSet(this, null);
                try {
                    final InnerProducer[] array2 = this.producers.getAndSet(PublishSubscriber.TERMINATED);
                    for (int length2 = array2.length, j = 0; j < length2; ++j) {
                        array2[j].child.onError(error);
                    }
                    return true;
                }
                finally {
                    this.unsubscribe();
                }
            }
            return false;
        }
        
        void dispatch() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: aload_0        
            //     3: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //     6: ifeq            17
            //     9: aload_0        
            //    10: iconst_1       
            //    11: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.missed:Z
            //    14: aload_0        
            //    15: monitorexit    
            //    16: return         
            //    17: aload_0        
            //    18: iconst_1       
            //    19: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //    22: aload_0        
            //    23: iconst_0       
            //    24: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.missed:Z
            //    27: aload_0        
            //    28: monitorexit    
            //    29: iconst_0       
            //    30: istore          5
            //    32: iconst_0       
            //    33: istore          4
            //    35: iload           4
            //    37: istore_1       
            //    38: aload_0        
            //    39: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.terminalEvent:Ljava/lang/Object;
            //    42: astore          16
            //    44: iload           4
            //    46: istore_1       
            //    47: aload_0        
            //    48: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.queue:Ljava/util/Queue;
            //    51: invokeinterface java/util/Queue.isEmpty:()Z
            //    56: istore          8
            //    58: iload           4
            //    60: istore_1       
            //    61: aload_0        
            //    62: aload           16
            //    64: iload           8
            //    66: invokevirtual   rx/internal/operators/OperatorPublish$PublishSubscriber.checkTerminated:(Ljava/lang/Object;Z)Z
            //    69: istore          9
            //    71: iload           9
            //    73: ifeq            104
            //    76: iconst_1       
            //    77: ifne            601
            //    80: aload_0        
            //    81: monitorenter   
            //    82: aload_0        
            //    83: iconst_0       
            //    84: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //    87: aload_0        
            //    88: monitorexit    
            //    89: return         
            //    90: astore          16
            //    92: aload_0        
            //    93: monitorexit    
            //    94: aload           16
            //    96: athrow         
            //    97: astore          16
            //    99: aload_0        
            //   100: monitorexit    
            //   101: aload           16
            //   103: athrow         
            //   104: iload           8
            //   106: ifne            409
            //   109: iload           4
            //   111: istore_1       
            //   112: aload_0        
            //   113: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.producers:Ljava/util/concurrent/atomic/AtomicReference;
            //   116: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
            //   119: checkcast       [Lrx/internal/operators/OperatorPublish$InnerProducer;
            //   122: astore          16
            //   124: iload           4
            //   126: istore_1       
            //   127: aload           16
            //   129: arraylength    
            //   130: istore          6
            //   132: ldc2_w          9223372036854775807
            //   135: lstore          10
            //   137: iconst_0       
            //   138: istore_3       
            //   139: iload           4
            //   141: istore_1       
            //   142: aload           16
            //   144: arraylength    
            //   145: istore          7
            //   147: iconst_0       
            //   148: istore_2       
            //   149: iload_2        
            //   150: iload           7
            //   152: if_icmpge       191
            //   155: iload           4
            //   157: istore_1       
            //   158: aload           16
            //   160: iload_2        
            //   161: aaload         
            //   162: invokevirtual   rx/internal/operators/OperatorPublish$InnerProducer.get:()J
            //   165: lstore          14
            //   167: lload           14
            //   169: lconst_0       
            //   170: lcmp           
            //   171: iflt            615
            //   174: iload           4
            //   176: istore_1       
            //   177: lload           10
            //   179: lload           14
            //   181: invokestatic    java/lang/Math.min:(JJ)J
            //   184: lstore          12
            //   186: iload_3        
            //   187: istore_1       
            //   188: goto            602
            //   191: iload           6
            //   193: iload_3        
            //   194: if_icmpne       298
            //   197: iload           4
            //   199: istore_1       
            //   200: aload_0        
            //   201: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.terminalEvent:Ljava/lang/Object;
            //   204: astore          16
            //   206: iload           4
            //   208: istore_1       
            //   209: aload_0        
            //   210: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.queue:Ljava/util/Queue;
            //   213: invokeinterface java/util/Queue.poll:()Ljava/lang/Object;
            //   218: ifnonnull       263
            //   221: iconst_1       
            //   222: istore          8
            //   224: iload           4
            //   226: istore_1       
            //   227: aload_0        
            //   228: aload           16
            //   230: iload           8
            //   232: invokevirtual   rx/internal/operators/OperatorPublish$PublishSubscriber.checkTerminated:(Ljava/lang/Object;Z)Z
            //   235: istore          8
            //   237: iload           8
            //   239: ifeq            269
            //   242: iconst_1       
            //   243: ifne            601
            //   246: aload_0        
            //   247: monitorenter   
            //   248: aload_0        
            //   249: iconst_0       
            //   250: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //   253: aload_0        
            //   254: monitorexit    
            //   255: return         
            //   256: astore          16
            //   258: aload_0        
            //   259: monitorexit    
            //   260: aload           16
            //   262: athrow         
            //   263: iconst_0       
            //   264: istore          8
            //   266: goto            224
            //   269: iload           4
            //   271: istore_1       
            //   272: aload_0        
            //   273: lconst_1       
            //   274: invokevirtual   rx/internal/operators/OperatorPublish$PublishSubscriber.request:(J)V
            //   277: goto            35
            //   280: astore          16
            //   282: iload_1        
            //   283: ifne            295
            //   286: aload_0        
            //   287: monitorenter   
            //   288: aload_0        
            //   289: iconst_0       
            //   290: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //   293: aload_0        
            //   294: monitorexit    
            //   295: aload           16
            //   297: athrow         
            //   298: iconst_0       
            //   299: istore_2       
            //   300: iload_2        
            //   301: i2l            
            //   302: lload           10
            //   304: lcmp           
            //   305: ifge            389
            //   308: iload           4
            //   310: istore_1       
            //   311: aload_0        
            //   312: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.terminalEvent:Ljava/lang/Object;
            //   315: astore          17
            //   317: iload           4
            //   319: istore_1       
            //   320: aload_0        
            //   321: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.queue:Ljava/util/Queue;
            //   324: invokeinterface java/util/Queue.poll:()Ljava/lang/Object;
            //   329: astore          18
            //   331: aload           18
            //   333: ifnonnull       378
            //   336: iconst_1       
            //   337: istore          8
            //   339: iload           4
            //   341: istore_1       
            //   342: aload_0        
            //   343: aload           17
            //   345: iload           8
            //   347: invokevirtual   rx/internal/operators/OperatorPublish$PublishSubscriber.checkTerminated:(Ljava/lang/Object;Z)Z
            //   350: istore          9
            //   352: iload           9
            //   354: ifeq            384
            //   357: iconst_1       
            //   358: ifne            601
            //   361: aload_0        
            //   362: monitorenter   
            //   363: aload_0        
            //   364: iconst_0       
            //   365: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //   368: aload_0        
            //   369: monitorexit    
            //   370: return         
            //   371: astore          16
            //   373: aload_0        
            //   374: monitorexit    
            //   375: aload           16
            //   377: athrow         
            //   378: iconst_0       
            //   379: istore          8
            //   381: goto            339
            //   384: iload           8
            //   386: ifeq            457
            //   389: iload_2        
            //   390: ifle            641
            //   393: iload_2        
            //   394: i2l            
            //   395: lstore          12
            //   397: iload           4
            //   399: istore_1       
            //   400: aload_0        
            //   401: lload           12
            //   403: invokevirtual   rx/internal/operators/OperatorPublish$PublishSubscriber.request:(J)V
            //   406: goto            641
            //   409: iload           4
            //   411: istore_1       
            //   412: aload_0        
            //   413: monitorenter   
            //   414: iload           5
            //   416: istore_1       
            //   417: aload_0        
            //   418: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.missed:Z
            //   421: ifne            571
            //   424: iload           5
            //   426: istore_1       
            //   427: aload_0        
            //   428: iconst_0       
            //   429: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //   432: iconst_1       
            //   433: istore_1       
            //   434: aload_0        
            //   435: monitorexit    
            //   436: iconst_1       
            //   437: ifne            601
            //   440: aload_0        
            //   441: monitorenter   
            //   442: aload_0        
            //   443: iconst_0       
            //   444: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.emitting:Z
            //   447: aload_0        
            //   448: monitorexit    
            //   449: return         
            //   450: astore          16
            //   452: aload_0        
            //   453: monitorexit    
            //   454: aload           16
            //   456: athrow         
            //   457: iload           4
            //   459: istore_1       
            //   460: aload_0        
            //   461: getfield        rx/internal/operators/OperatorPublish$PublishSubscriber.nl:Lrx/internal/operators/NotificationLite;
            //   464: aload           18
            //   466: invokevirtual   rx/internal/operators/NotificationLite.getValue:(Ljava/lang/Object;)Ljava/lang/Object;
            //   469: astore          17
            //   471: iload           4
            //   473: istore_1       
            //   474: aload           16
            //   476: arraylength    
            //   477: istore          6
            //   479: iconst_0       
            //   480: istore_3       
            //   481: iload_3        
            //   482: iload           6
            //   484: if_icmpge       564
            //   487: aload           16
            //   489: iload_3        
            //   490: aaload         
            //   491: astore          18
            //   493: iload           4
            //   495: istore_1       
            //   496: aload           18
            //   498: invokevirtual   rx/internal/operators/OperatorPublish$InnerProducer.get:()J
            //   501: lstore          12
            //   503: lload           12
            //   505: lconst_0       
            //   506: lcmp           
            //   507: ifle            656
            //   510: iload           4
            //   512: istore_1       
            //   513: aload           18
            //   515: getfield        rx/internal/operators/OperatorPublish$InnerProducer.child:Lrx/Subscriber;
            //   518: aload           17
            //   520: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //   523: iload           4
            //   525: istore_1       
            //   526: aload           18
            //   528: lconst_1       
            //   529: invokevirtual   rx/internal/operators/OperatorPublish$InnerProducer.produced:(J)J
            //   532: pop2           
            //   533: goto            656
            //   536: astore          19
            //   538: iload           4
            //   540: istore_1       
            //   541: aload           18
            //   543: invokevirtual   rx/internal/operators/OperatorPublish$InnerProducer.unsubscribe:()V
            //   546: iload           4
            //   548: istore_1       
            //   549: aload           19
            //   551: aload           18
            //   553: getfield        rx/internal/operators/OperatorPublish$InnerProducer.child:Lrx/Subscriber;
            //   556: aload           17
            //   558: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;Ljava/lang/Object;)V
            //   561: goto            656
            //   564: iload_2        
            //   565: iconst_1       
            //   566: iadd           
            //   567: istore_2       
            //   568: goto            300
            //   571: iload           5
            //   573: istore_1       
            //   574: aload_0        
            //   575: iconst_0       
            //   576: putfield        rx/internal/operators/OperatorPublish$PublishSubscriber.missed:Z
            //   579: iload           5
            //   581: istore_1       
            //   582: aload_0        
            //   583: monitorexit    
            //   584: goto            35
            //   587: astore          16
            //   589: aload_0        
            //   590: monitorexit    
            //   591: aload           16
            //   593: athrow         
            //   594: astore          16
            //   596: aload_0        
            //   597: monitorexit    
            //   598: aload           16
            //   600: athrow         
            //   601: return         
            //   602: iload_2        
            //   603: iconst_1       
            //   604: iadd           
            //   605: istore_2       
            //   606: lload           12
            //   608: lstore          10
            //   610: iload_1        
            //   611: istore_3       
            //   612: goto            149
            //   615: lload           10
            //   617: lstore          12
            //   619: iload_3        
            //   620: istore_1       
            //   621: lload           14
            //   623: ldc2_w          -9223372036854775808
            //   626: lcmp           
            //   627: ifne            602
            //   630: iload_3        
            //   631: iconst_1       
            //   632: iadd           
            //   633: istore_1       
            //   634: lload           10
            //   636: lstore          12
            //   638: goto            602
            //   641: lload           10
            //   643: lconst_0       
            //   644: lcmp           
            //   645: ifeq            409
            //   648: iload           8
            //   650: ifeq            35
            //   653: goto            409
            //   656: iload_3        
            //   657: iconst_1       
            //   658: iadd           
            //   659: istore_3       
            //   660: goto            481
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  2      16     97     104    Any
            //  17     29     97     104    Any
            //  38     44     280    601    Any
            //  47     58     280    601    Any
            //  61     71     280    601    Any
            //  82     89     90     97     Any
            //  92     94     90     97     Any
            //  99     101    97     104    Any
            //  112    124    280    601    Any
            //  127    132    280    601    Any
            //  142    147    280    601    Any
            //  158    167    280    601    Any
            //  177    186    280    601    Any
            //  200    206    280    601    Any
            //  209    221    280    601    Any
            //  227    237    280    601    Any
            //  248    255    256    263    Any
            //  258    260    256    263    Any
            //  272    277    280    601    Any
            //  288    295    594    601    Any
            //  311    317    280    601    Any
            //  320    331    280    601    Any
            //  342    352    280    601    Any
            //  363    370    371    378    Any
            //  373    375    371    378    Any
            //  400    406    280    601    Any
            //  412    414    280    601    Any
            //  417    424    587    594    Any
            //  427    432    587    594    Any
            //  434    436    587    594    Any
            //  442    449    450    457    Any
            //  452    454    450    457    Any
            //  460    471    280    601    Any
            //  474    479    280    601    Any
            //  496    503    280    601    Any
            //  513    523    536    564    Ljava/lang/Throwable;
            //  513    523    280    601    Any
            //  526    533    280    601    Any
            //  541    546    280    601    Any
            //  549    561    280    601    Any
            //  574    579    587    594    Any
            //  582    584    587    594    Any
            //  589    591    587    594    Any
            //  591    594    280    601    Any
            //  596    598    594    601    Any
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
        
        void init() {
            this.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    PublishSubscriber.this.producers.getAndSet(PublishSubscriber.TERMINATED);
                    PublishSubscriber.this.current.compareAndSet(PublishSubscriber.this, null);
                }
            }));
        }
        
        @Override
        public void onCompleted() {
            if (this.terminalEvent == null) {
                this.terminalEvent = this.nl.completed();
                this.dispatch();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.terminalEvent == null) {
                this.terminalEvent = this.nl.error(t);
                this.dispatch();
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.queue.offer(this.nl.next(t))) {
                this.onError(new MissingBackpressureException());
                return;
            }
            this.dispatch();
        }
        
        @Override
        public void onStart() {
            this.request(RxRingBuffer.SIZE);
        }
        
        void remove(final InnerProducer<T> innerProducer) {
            while (true) {
                final InnerProducer[] array = this.producers.get();
                if (array == PublishSubscriber.EMPTY || array == PublishSubscriber.TERMINATED) {
                    break;
                }
                final int n = -1;
                final int length = array.length;
                int n2 = 0;
                int n3;
                while (true) {
                    n3 = n;
                    if (n2 >= length) {
                        break;
                    }
                    if (array[n2].equals(innerProducer)) {
                        n3 = n2;
                        break;
                    }
                    ++n2;
                }
                if (n3 < 0) {
                    break;
                }
                InnerProducer[] empty;
                if (length == 1) {
                    empty = PublishSubscriber.EMPTY;
                }
                else {
                    empty = new InnerProducer[length - 1];
                    System.arraycopy(array, 0, empty, 0, n3);
                    System.arraycopy(array, n3 + 1, empty, n3, length - n3 - 1);
                }
                if (this.producers.compareAndSet(array, empty)) {
                    return;
                }
            }
        }
    }
}
