// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.Subscriber;
import rx.subscriptions.SerialSubscription;
import rx.Observer;
import rx.internal.util.LinkedArrayList;
import rx.Observable;

public final class CachedObservable<T> extends Observable<T>
{
    private final CacheState<T> state;
    
    private CachedObservable(final OnSubscribe<T> onSubscribe, final CacheState<T> state) {
        super((OnSubscribe)onSubscribe);
        this.state = state;
    }
    
    public static <T> CachedObservable<T> from(final Observable<? extends T> observable) {
        return from(observable, 16);
    }
    
    public static <T> CachedObservable<T> from(final Observable<? extends T> observable, final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("capacityHint > 0 required");
        }
        final CacheState cacheState = new CacheState(observable, n);
        return new CachedObservable<T>(new CachedSubscribe<Object>((CacheState<Object>)cacheState), (CacheState<Object>)cacheState);
    }
    
    boolean hasObservers() {
        return this.state.producers.length != 0;
    }
    
    boolean isConnected() {
        return this.state.isConnected;
    }
    
    static final class CacheState<T> extends LinkedArrayList implements Observer<T>
    {
        static final ReplayProducer<?>[] EMPTY;
        final SerialSubscription connection;
        volatile boolean isConnected;
        final NotificationLite<T> nl;
        volatile ReplayProducer<?>[] producers;
        final Observable<? extends T> source;
        boolean sourceDone;
        
        static {
            EMPTY = new ReplayProducer[0];
        }
        
        public CacheState(final Observable<? extends T> source, final int n) {
            super(n);
            this.source = source;
            this.producers = CacheState.EMPTY;
            this.nl = NotificationLite.instance();
            this.connection = new SerialSubscription();
        }
        
        public void addProducer(final ReplayProducer<T> replayProducer) {
            synchronized (this.connection) {
                final ReplayProducer<?>[] producers = this.producers;
                final int length = producers.length;
                final ReplayProducer[] producers2 = new ReplayProducer[length + 1];
                System.arraycopy(producers, 0, producers2, 0, length);
                producers2[length] = replayProducer;
                this.producers = (ReplayProducer<?>[])producers2;
            }
        }
        
        public void connect() {
            final Subscriber<T> subscriber = new Subscriber<T>() {
                @Override
                public void onCompleted() {
                    CacheState.this.onCompleted();
                }
                
                @Override
                public void onError(final Throwable t) {
                    CacheState.this.onError(t);
                }
                
                @Override
                public void onNext(final T t) {
                    CacheState.this.onNext(t);
                }
            };
            this.connection.set(subscriber);
            this.source.unsafeSubscribe(subscriber);
            this.isConnected = true;
        }
        
        void dispatch() {
            final ReplayProducer<?>[] producers = this.producers;
            for (int length = producers.length, i = 0; i < length; ++i) {
                producers[i].replay();
            }
        }
        
        @Override
        public void onCompleted() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                this.add(this.nl.completed());
                this.connection.unsubscribe();
                this.dispatch();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                this.add(this.nl.error(t));
                this.connection.unsubscribe();
                this.dispatch();
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (!this.sourceDone) {
                this.add(this.nl.next(t));
                this.dispatch();
            }
        }
        
        public void removeProducer(final ReplayProducer<T> replayProducer) {
            ReplayProducer<?>[] producers;
            int length;
            int n3;
            while (true) {
                while (true) {
                    int n2 = 0;
                    Label_0120: {
                        synchronized (this.connection) {
                            producers = this.producers;
                            length = producers.length;
                            final int n = -1;
                            n2 = 0;
                            n3 = n;
                            if (n2 < length) {
                                if (!producers[n2].equals(replayProducer)) {
                                    break Label_0120;
                                }
                                n3 = n2;
                            }
                            if (n3 < 0) {
                                return;
                            }
                            if (length == 1) {
                                this.producers = CacheState.EMPTY;
                                return;
                            }
                        }
                        break;
                    }
                    ++n2;
                    continue;
                }
            }
            final ReplayProducer[] producers2 = new ReplayProducer[length - 1];
            System.arraycopy(producers, 0, producers2, 0, n3);
            System.arraycopy(producers, n3 + 1, producers2, n3, length - n3 - 1);
            this.producers = (ReplayProducer<?>[])producers2;
        }
        // monitorexit(serialSubscription)
    }
    
    static final class CachedSubscribe<T> extends AtomicBoolean implements OnSubscribe<T>
    {
        private static final long serialVersionUID = -2817751667698696782L;
        final CacheState<T> state;
        
        public CachedSubscribe(final CacheState<T> state) {
            this.state = state;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            final ReplayProducer producer = new ReplayProducer((Subscriber<? super Object>)subscriber, (CacheState<Object>)this.state);
            this.state.addProducer((ReplayProducer<T>)producer);
            subscriber.add(producer);
            subscriber.setProducer(producer);
            if (!this.get() && this.compareAndSet(false, true)) {
                this.state.connect();
            }
        }
    }
    
    static final class ReplayProducer<T> extends AtomicLong implements Producer, Subscription
    {
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        boolean emitting;
        int index;
        boolean missed;
        final CacheState<T> state;
        
        public ReplayProducer(final Subscriber<? super T> child, final CacheState<T> state) {
            this.child = child;
            this.state = state;
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get() < 0L;
        }
        
        public long produced(final long n) {
            return this.addAndGet(-n);
        }
        
        public void replay() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //     6: ifeq            17
            //     9: aload_0        
            //    10: iconst_1       
            //    11: putfield        rx/internal/operators/CachedObservable$ReplayProducer.missed:Z
            //    14: aload_0        
            //    15: monitorexit    
            //    16: return         
            //    17: aload_0        
            //    18: iconst_1       
            //    19: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //    22: aload_0        
            //    23: monitorexit    
            //    24: iconst_0       
            //    25: istore          7
            //    27: iconst_0       
            //    28: istore          8
            //    30: iconst_0       
            //    31: istore          6
            //    33: iload           8
            //    35: istore_1       
            //    36: aload_0        
            //    37: getfield        rx/internal/operators/CachedObservable$ReplayProducer.state:Lrx/internal/operators/CachedObservable$CacheState;
            //    40: getfield        rx/internal/operators/CachedObservable$CacheState.nl:Lrx/internal/operators/NotificationLite;
            //    43: astore          16
            //    45: iload           8
            //    47: istore_1       
            //    48: aload_0        
            //    49: getfield        rx/internal/operators/CachedObservable$ReplayProducer.child:Lrx/Subscriber;
            //    52: astore          17
            //    54: iload           8
            //    56: istore_1       
            //    57: aload_0        
            //    58: invokevirtual   rx/internal/operators/CachedObservable$ReplayProducer.get:()J
            //    61: lstore          11
            //    63: lload           11
            //    65: lconst_0       
            //    66: lcmp           
            //    67: ifge            98
            //    70: iconst_1       
            //    71: ifne            723
            //    74: aload_0        
            //    75: monitorenter   
            //    76: aload_0        
            //    77: iconst_0       
            //    78: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //    81: aload_0        
            //    82: monitorexit    
            //    83: return         
            //    84: astore          14
            //    86: aload_0        
            //    87: monitorexit    
            //    88: aload           14
            //    90: athrow         
            //    91: astore          14
            //    93: aload_0        
            //    94: monitorexit    
            //    95: aload           14
            //    97: athrow         
            //    98: iload           8
            //   100: istore_1       
            //   101: aload_0        
            //   102: getfield        rx/internal/operators/CachedObservable$ReplayProducer.state:Lrx/internal/operators/CachedObservable$CacheState;
            //   105: invokevirtual   rx/internal/operators/CachedObservable$CacheState.size:()I
            //   108: istore          9
            //   110: iload           9
            //   112: ifeq            627
            //   115: iload           8
            //   117: istore_1       
            //   118: aload_0        
            //   119: getfield        rx/internal/operators/CachedObservable$ReplayProducer.currentBuffer:[Ljava/lang/Object;
            //   122: astore          15
            //   124: aload           15
            //   126: astore          14
            //   128: aload           15
            //   130: ifnonnull       154
            //   133: iload           8
            //   135: istore_1       
            //   136: aload_0        
            //   137: getfield        rx/internal/operators/CachedObservable$ReplayProducer.state:Lrx/internal/operators/CachedObservable$CacheState;
            //   140: invokevirtual   rx/internal/operators/CachedObservable$CacheState.head:()[Ljava/lang/Object;
            //   143: astore          14
            //   145: iload           8
            //   147: istore_1       
            //   148: aload_0        
            //   149: aload           14
            //   151: putfield        rx/internal/operators/CachedObservable$ReplayProducer.currentBuffer:[Ljava/lang/Object;
            //   154: iload           8
            //   156: istore_1       
            //   157: aload           14
            //   159: arraylength    
            //   160: iconst_1       
            //   161: isub           
            //   162: istore          10
            //   164: iload           8
            //   166: istore_1       
            //   167: aload_0        
            //   168: getfield        rx/internal/operators/CachedObservable$ReplayProducer.index:I
            //   171: istore          4
            //   173: iload           8
            //   175: istore_1       
            //   176: aload_0        
            //   177: getfield        rx/internal/operators/CachedObservable$ReplayProducer.currentIndexInBuffer:I
            //   180: istore_2       
            //   181: lload           11
            //   183: lconst_0       
            //   184: lcmp           
            //   185: ifne            297
            //   188: aload           14
            //   190: iload_2        
            //   191: aaload         
            //   192: astore          14
            //   194: iload           8
            //   196: istore_1       
            //   197: aload           16
            //   199: aload           14
            //   201: invokevirtual   rx/internal/operators/NotificationLite.isCompleted:(Ljava/lang/Object;)Z
            //   204: ifeq            242
            //   207: iload           8
            //   209: istore_1       
            //   210: aload           17
            //   212: invokevirtual   rx/Subscriber.onCompleted:()V
            //   215: iconst_1       
            //   216: istore_1       
            //   217: aload_0        
            //   218: invokevirtual   rx/internal/operators/CachedObservable$ReplayProducer.unsubscribe:()V
            //   221: iconst_1       
            //   222: ifne            723
            //   225: aload_0        
            //   226: monitorenter   
            //   227: aload_0        
            //   228: iconst_0       
            //   229: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   232: aload_0        
            //   233: monitorexit    
            //   234: return         
            //   235: astore          14
            //   237: aload_0        
            //   238: monitorexit    
            //   239: aload           14
            //   241: athrow         
            //   242: iload           8
            //   244: istore_1       
            //   245: aload           16
            //   247: aload           14
            //   249: invokevirtual   rx/internal/operators/NotificationLite.isError:(Ljava/lang/Object;)Z
            //   252: ifeq            627
            //   255: iload           8
            //   257: istore_1       
            //   258: aload           17
            //   260: aload           16
            //   262: aload           14
            //   264: invokevirtual   rx/internal/operators/NotificationLite.getError:(Ljava/lang/Object;)Ljava/lang/Throwable;
            //   267: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   270: iconst_1       
            //   271: istore_1       
            //   272: aload_0        
            //   273: invokevirtual   rx/internal/operators/CachedObservable$ReplayProducer.unsubscribe:()V
            //   276: iconst_1       
            //   277: ifne            723
            //   280: aload_0        
            //   281: monitorenter   
            //   282: aload_0        
            //   283: iconst_0       
            //   284: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   287: aload_0        
            //   288: monitorexit    
            //   289: return         
            //   290: astore          14
            //   292: aload_0        
            //   293: monitorexit    
            //   294: aload           14
            //   296: athrow         
            //   297: lload           11
            //   299: lconst_0       
            //   300: lcmp           
            //   301: ifle            627
            //   304: iconst_0       
            //   305: istore_3       
            //   306: aload           14
            //   308: astore          15
            //   310: iload           4
            //   312: iload           9
            //   314: if_icmpge       555
            //   317: lload           11
            //   319: lconst_0       
            //   320: lcmp           
            //   321: ifle            555
            //   324: iload           8
            //   326: istore_1       
            //   327: aload           17
            //   329: invokevirtual   rx/Subscriber.isUnsubscribed:()Z
            //   332: istore          13
            //   334: iload           13
            //   336: ifeq            360
            //   339: iconst_1       
            //   340: ifne            723
            //   343: aload_0        
            //   344: monitorenter   
            //   345: aload_0        
            //   346: iconst_0       
            //   347: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   350: aload_0        
            //   351: monitorexit    
            //   352: return         
            //   353: astore          14
            //   355: aload_0        
            //   356: monitorexit    
            //   357: aload           14
            //   359: athrow         
            //   360: aload           15
            //   362: astore          14
            //   364: iload_2        
            //   365: istore          5
            //   367: iload_2        
            //   368: iload           10
            //   370: if_icmpne       392
            //   373: iload           8
            //   375: istore_1       
            //   376: aload           15
            //   378: iload           10
            //   380: aaload         
            //   381: checkcast       [Ljava/lang/Object;
            //   384: checkcast       [Ljava/lang/Object;
            //   387: astore          14
            //   389: iconst_0       
            //   390: istore          5
            //   392: aload           14
            //   394: iload           5
            //   396: aaload         
            //   397: astore          15
            //   399: iload           6
            //   401: istore_2       
            //   402: iload           8
            //   404: istore_1       
            //   405: aload           16
            //   407: aload           17
            //   409: aload           15
            //   411: invokevirtual   rx/internal/operators/NotificationLite.accept:(Lrx/Observer;Ljava/lang/Object;)Z
            //   414: ifeq            527
            //   417: iconst_1       
            //   418: istore_1       
            //   419: iconst_1       
            //   420: istore_2       
            //   421: aload_0        
            //   422: invokevirtual   rx/internal/operators/CachedObservable$ReplayProducer.unsubscribe:()V
            //   425: iconst_1       
            //   426: ifne            723
            //   429: aload_0        
            //   430: monitorenter   
            //   431: aload_0        
            //   432: iconst_0       
            //   433: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   436: aload_0        
            //   437: monitorexit    
            //   438: return         
            //   439: astore          14
            //   441: aload_0        
            //   442: monitorexit    
            //   443: aload           14
            //   445: athrow         
            //   446: astore          14
            //   448: iload_2        
            //   449: istore_1       
            //   450: aload           14
            //   452: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
            //   455: iconst_1       
            //   456: istore_2       
            //   457: iload_2        
            //   458: istore_1       
            //   459: aload_0        
            //   460: invokevirtual   rx/internal/operators/CachedObservable$ReplayProducer.unsubscribe:()V
            //   463: iload_2        
            //   464: istore_1       
            //   465: aload           16
            //   467: aload           15
            //   469: invokevirtual   rx/internal/operators/NotificationLite.isError:(Ljava/lang/Object;)Z
            //   472: ifne            506
            //   475: iload_2        
            //   476: istore_1       
            //   477: aload           16
            //   479: aload           15
            //   481: invokevirtual   rx/internal/operators/NotificationLite.isCompleted:(Ljava/lang/Object;)Z
            //   484: ifne            506
            //   487: iload_2        
            //   488: istore_1       
            //   489: aload           17
            //   491: aload           14
            //   493: aload           16
            //   495: aload           15
            //   497: invokevirtual   rx/internal/operators/NotificationLite.getValue:(Ljava/lang/Object;)Ljava/lang/Object;
            //   500: invokestatic    rx/exceptions/OnErrorThrowable.addValueAsLastCause:(Ljava/lang/Throwable;Ljava/lang/Object;)Ljava/lang/Throwable;
            //   503: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   506: iconst_1       
            //   507: ifne            723
            //   510: aload_0        
            //   511: monitorenter   
            //   512: aload_0        
            //   513: iconst_0       
            //   514: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   517: aload_0        
            //   518: monitorexit    
            //   519: return         
            //   520: astore          14
            //   522: aload_0        
            //   523: monitorexit    
            //   524: aload           14
            //   526: athrow         
            //   527: iload           5
            //   529: iconst_1       
            //   530: iadd           
            //   531: istore_2       
            //   532: iload           4
            //   534: iconst_1       
            //   535: iadd           
            //   536: istore          4
            //   538: lload           11
            //   540: lconst_1       
            //   541: lsub           
            //   542: lstore          11
            //   544: iload_3        
            //   545: iconst_1       
            //   546: iadd           
            //   547: istore_3       
            //   548: aload           14
            //   550: astore          15
            //   552: goto            310
            //   555: iload           8
            //   557: istore_1       
            //   558: aload           17
            //   560: invokevirtual   rx/Subscriber.isUnsubscribed:()Z
            //   563: istore          13
            //   565: iload           13
            //   567: ifeq            591
            //   570: iconst_1       
            //   571: ifne            723
            //   574: aload_0        
            //   575: monitorenter   
            //   576: aload_0        
            //   577: iconst_0       
            //   578: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   581: aload_0        
            //   582: monitorexit    
            //   583: return         
            //   584: astore          14
            //   586: aload_0        
            //   587: monitorexit    
            //   588: aload           14
            //   590: athrow         
            //   591: iload           8
            //   593: istore_1       
            //   594: aload_0        
            //   595: iload           4
            //   597: putfield        rx/internal/operators/CachedObservable$ReplayProducer.index:I
            //   600: iload           8
            //   602: istore_1       
            //   603: aload_0        
            //   604: iload_2        
            //   605: putfield        rx/internal/operators/CachedObservable$ReplayProducer.currentIndexInBuffer:I
            //   608: iload           8
            //   610: istore_1       
            //   611: aload_0        
            //   612: aload           15
            //   614: putfield        rx/internal/operators/CachedObservable$ReplayProducer.currentBuffer:[Ljava/lang/Object;
            //   617: iload           8
            //   619: istore_1       
            //   620: aload_0        
            //   621: iload_3        
            //   622: i2l            
            //   623: invokevirtual   rx/internal/operators/CachedObservable$ReplayProducer.produced:(J)J
            //   626: pop2           
            //   627: iload           8
            //   629: istore_1       
            //   630: aload_0        
            //   631: monitorenter   
            //   632: iload           7
            //   634: istore_1       
            //   635: aload_0        
            //   636: getfield        rx/internal/operators/CachedObservable$ReplayProducer.missed:Z
            //   639: ifne            675
            //   642: iload           7
            //   644: istore_1       
            //   645: aload_0        
            //   646: iconst_0       
            //   647: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   650: iconst_1       
            //   651: istore_1       
            //   652: aload_0        
            //   653: monitorexit    
            //   654: iconst_1       
            //   655: ifne            723
            //   658: aload_0        
            //   659: monitorenter   
            //   660: aload_0        
            //   661: iconst_0       
            //   662: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   665: aload_0        
            //   666: monitorexit    
            //   667: return         
            //   668: astore          14
            //   670: aload_0        
            //   671: monitorexit    
            //   672: aload           14
            //   674: athrow         
            //   675: iload           7
            //   677: istore_1       
            //   678: aload_0        
            //   679: iconst_0       
            //   680: putfield        rx/internal/operators/CachedObservable$ReplayProducer.missed:Z
            //   683: iload           7
            //   685: istore_1       
            //   686: aload_0        
            //   687: monitorexit    
            //   688: goto            54
            //   691: astore          14
            //   693: aload_0        
            //   694: monitorexit    
            //   695: aload           14
            //   697: athrow         
            //   698: astore          14
            //   700: iload_1        
            //   701: ifne            713
            //   704: aload_0        
            //   705: monitorenter   
            //   706: aload_0        
            //   707: iconst_0       
            //   708: putfield        rx/internal/operators/CachedObservable$ReplayProducer.emitting:Z
            //   711: aload_0        
            //   712: monitorexit    
            //   713: aload           14
            //   715: athrow         
            //   716: astore          14
            //   718: aload_0        
            //   719: monitorexit    
            //   720: aload           14
            //   722: athrow         
            //   723: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  2      16     91     98     Any
            //  17     24     91     98     Any
            //  36     45     698    723    Any
            //  48     54     698    723    Any
            //  57     63     698    723    Any
            //  76     83     84     91     Any
            //  86     88     84     91     Any
            //  93     95     91     98     Any
            //  101    110    698    723    Any
            //  118    124    698    723    Any
            //  136    145    698    723    Any
            //  148    154    698    723    Any
            //  157    164    698    723    Any
            //  167    173    698    723    Any
            //  176    181    698    723    Any
            //  197    207    698    723    Any
            //  210    215    698    723    Any
            //  217    221    698    723    Any
            //  227    234    235    242    Any
            //  237    239    235    242    Any
            //  245    255    698    723    Any
            //  258    270    698    723    Any
            //  272    276    698    723    Any
            //  282    289    290    297    Any
            //  292    294    290    297    Any
            //  327    334    698    723    Any
            //  345    352    353    360    Any
            //  355    357    353    360    Any
            //  376    389    698    723    Any
            //  405    417    446    527    Ljava/lang/Throwable;
            //  405    417    698    723    Any
            //  421    425    446    527    Ljava/lang/Throwable;
            //  421    425    698    723    Any
            //  431    438    439    446    Any
            //  441    443    439    446    Any
            //  450    455    698    723    Any
            //  459    463    698    723    Any
            //  465    475    698    723    Any
            //  477    487    698    723    Any
            //  489    506    698    723    Any
            //  512    519    520    527    Any
            //  522    524    520    527    Any
            //  558    565    698    723    Any
            //  576    583    584    591    Any
            //  586    588    584    591    Any
            //  594    600    698    723    Any
            //  603    608    698    723    Any
            //  611    617    698    723    Any
            //  620    627    698    723    Any
            //  630    632    698    723    Any
            //  635    642    691    698    Any
            //  645    650    691    698    Any
            //  652    654    691    698    Any
            //  660    667    668    675    Any
            //  670    672    668    675    Any
            //  678    683    691    698    Any
            //  686    688    691    698    Any
            //  693    695    691    698    Any
            //  695    698    698    723    Any
            //  706    713    716    723    Any
            //  718    720    716    723    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
        public void request(final long n) {
            long value;
            long n2;
            do {
                value = this.get();
                if (value < 0L) {
                    return;
                }
                if ((n2 = value + n) >= 0L) {
                    continue;
                }
                n2 = Long.MAX_VALUE;
            } while (!this.compareAndSet(value, n2));
            this.replay();
        }
        
        @Override
        public void unsubscribe() {
            if (this.get() >= 0L && this.getAndSet(-1L) >= 0L) {
                this.state.removeProducer(this);
            }
        }
    }
}
