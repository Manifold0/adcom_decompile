// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import rx.internal.producers.ProducerArbiter;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.observers.Subscribers;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Producer;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscriber;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.UtilityFunctions;
import java.util.Map;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.Observable;

public final class OperatorGroupBy<T, K, V> implements Operator<GroupedObservable<K, V>, T>
{
    final int bufferSize;
    final boolean delayError;
    final Func1<? super T, ? extends K> keySelector;
    final Func1<Action1<K>, Map<K, Object>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;
    
    public OperatorGroupBy(final Func1<? super T, ? extends K> func1) {
        this(func1, (Func1)UtilityFunctions.identity(), RxRingBuffer.SIZE, false, null);
    }
    
    public OperatorGroupBy(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2) {
        this(func1, func2, RxRingBuffer.SIZE, false, null);
    }
    
    public OperatorGroupBy(final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends V> valueSelector, final int bufferSize, final boolean delayError, final Func1<Action1<K>, Map<K, Object>> mapFactory) {
        this.keySelector = keySelector;
        this.valueSelector = valueSelector;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
        this.mapFactory = mapFactory;
    }
    
    public OperatorGroupBy(final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2, final Func1<Action1<K>, Map<K, Object>> func3) {
        this(func1, func2, RxRingBuffer.SIZE, false, func3);
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super GroupedObservable<K, V>> subscriber) {
        try {
            final GroupBySubscriber groupBySubscriber = new GroupBySubscriber((Subscriber<? super GroupedObservable<K, V>>)subscriber, (Func1<? super T, ? extends K>)this.keySelector, (Func1<? super T, ? extends V>)this.valueSelector, this.bufferSize, this.delayError, (Func1<Action1<K>, Map<K, Object>>)this.mapFactory);
            subscriber.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    groupBySubscriber.cancel();
                }
            }));
            subscriber.setProducer(groupBySubscriber.producer);
            return (Subscriber<? super T>)groupBySubscriber;
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
            final Subscriber<? super T> empty = Subscribers.empty();
            empty.unsubscribe();
            return empty;
        }
    }
    
    public static final class GroupByProducer implements Producer
    {
        final GroupBySubscriber<?, ?, ?> parent;
        
        public GroupByProducer(final GroupBySubscriber<?, ?, ?> parent) {
            this.parent = parent;
        }
        
        @Override
        public void request(final long n) {
            this.parent.requestMore(n);
        }
    }
    
    public static final class GroupBySubscriber<T, K, V> extends Subscriber<T>
    {
        static final Object NULL_KEY;
        final Subscriber<? super GroupedObservable<K, V>> actual;
        final int bufferSize;
        final AtomicBoolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final Queue<K> evictedKeys;
        final AtomicInteger groupCount;
        final Map<Object, GroupedUnicast<K, V>> groups;
        final Func1<? super T, ? extends K> keySelector;
        final GroupByProducer producer;
        final Queue<GroupedObservable<K, V>> queue;
        final AtomicLong requested;
        final ProducerArbiter s;
        final Func1<? super T, ? extends V> valueSelector;
        final AtomicInteger wip;
        
        static {
            NULL_KEY = new Object();
        }
        
        public GroupBySubscriber(final Subscriber<? super GroupedObservable<K, V>> actual, final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends V> valueSelector, final int bufferSize, final boolean delayError, final Func1<Action1<K>, Map<K, Object>> func1) {
            this.actual = actual;
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
            this.bufferSize = bufferSize;
            this.delayError = delayError;
            this.queue = new ConcurrentLinkedQueue<GroupedObservable<K, V>>();
            (this.s = new ProducerArbiter()).request(bufferSize);
            this.producer = new GroupByProducer((GroupBySubscriber<?, ?, ?>)this);
            this.cancelled = new AtomicBoolean();
            this.requested = new AtomicLong();
            this.groupCount = new AtomicInteger(1);
            this.wip = new AtomicInteger();
            if (func1 == null) {
                this.groups = new ConcurrentHashMap<Object, GroupedUnicast<K, V>>();
                this.evictedKeys = null;
                return;
            }
            this.evictedKeys = new ConcurrentLinkedQueue<K>();
            this.groups = this.createMap(func1, new EvictionAction<K>(this.evictedKeys));
        }
        
        private Map<Object, GroupedUnicast<K, V>> createMap(final Func1<Action1<K>, Map<K, Object>> func1, final Action1<K> action1) {
            return (Map<Object, GroupedUnicast<K, V>>)func1.call(action1);
        }
        
        public void cancel() {
            if (this.cancelled.compareAndSet(false, true) && this.groupCount.decrementAndGet() == 0) {
                this.unsubscribe();
            }
        }
        
        public void cancel(K null_KEY) {
            if (null_KEY == null) {
                null_KEY = (K)GroupBySubscriber.NULL_KEY;
            }
            if (this.groups.remove(null_KEY) != null && this.groupCount.decrementAndGet() == 0) {
                this.unsubscribe();
            }
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<? super GroupedObservable<K, V>> subscriber, final Queue<?> queue) {
            if (b) {
                final Throwable error = this.error;
                if (error != null) {
                    this.errorAll(subscriber, queue, error);
                    return true;
                }
                if (b2) {
                    this.actual.onCompleted();
                    return true;
                }
            }
            return false;
        }
        
        void drain() {
            Label_0010: {
                if (this.wip.getAndIncrement() == 0) {
                    int addAndGet = 1;
                    final Queue<GroupedObservable<K, V>> queue = this.queue;
                    final Subscriber<? super GroupedObservable<K, V>> actual = this.actual;
                    while (!this.checkTerminated(this.done, queue.isEmpty(), actual, queue)) {
                        long value;
                        long n;
                        for (value = this.requested.get(), n = 0L; n != value; ++n) {
                            final boolean done = this.done;
                            final GroupedObservable<K, V> groupedObservable = queue.poll();
                            final boolean b = groupedObservable == null;
                            if (this.checkTerminated(done, b, actual, queue)) {
                                break Label_0010;
                            }
                            if (b) {
                                break;
                            }
                            actual.onNext(groupedObservable);
                        }
                        if (n != 0L) {
                            if (value != Long.MAX_VALUE) {
                                BackpressureUtils.produced(this.requested, n);
                            }
                            this.s.request(n);
                        }
                        if ((addAndGet = this.wip.addAndGet(-addAndGet)) == 0) {
                            return;
                        }
                    }
                }
            }
        }
        
        void errorAll(final Subscriber<? super GroupedObservable<K, V>> subscriber, final Queue<?> queue, final Throwable t) {
            queue.clear();
            final ArrayList<GroupedUnicast> list = (ArrayList<GroupedUnicast>)new ArrayList<Object>(this.groups.values());
            this.groups.clear();
            if (this.evictedKeys != null) {
                this.evictedKeys.clear();
            }
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().onError(t);
            }
            subscriber.onError(t);
        }
        
        @Override
        public void onCompleted() {
            if (this.done) {
                return;
            }
            final Iterator<GroupedUnicast<K, V>> iterator = this.groups.values().iterator();
            while (iterator.hasNext()) {
                ((GroupedUnicast)iterator.next()).onComplete();
            }
            this.groups.clear();
            if (this.evictedKeys != null) {
                this.evictedKeys.clear();
            }
            this.done = true;
            this.groupCount.decrementAndGet();
            this.drain();
        }
        
        @Override
        public void onError(final Throwable error) {
            if (this.done) {
                RxJavaHooks.onError(error);
                return;
            }
            this.error = error;
            this.done = true;
            this.groupCount.decrementAndGet();
            this.drain();
        }
        
        @Override
        public void onNext(final T p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.done:Z
            //     4: ifeq            8
            //     7: return         
            //     8: aload_0        
            //     9: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.queue:Ljava/util/Queue;
            //    12: astore          7
            //    14: aload_0        
            //    15: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.actual:Lrx/Subscriber;
            //    18: astore          8
            //    20: aload_0        
            //    21: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.keySelector:Lrx/functions/Func1;
            //    24: aload_1        
            //    25: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
            //    30: astore          5
            //    32: iconst_1       
            //    33: istore_2       
            //    34: aload           5
            //    36: ifnull          207
            //    39: aload           5
            //    41: astore_3       
            //    42: aload_0        
            //    43: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.groups:Ljava/util/Map;
            //    46: aload_3        
            //    47: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //    52: checkcast       Lrx/internal/operators/OperatorGroupBy$GroupedUnicast;
            //    55: astore          6
            //    57: aload           6
            //    59: astore          4
            //    61: aload           6
            //    63: ifnonnull       129
            //    66: aload_0        
            //    67: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.cancelled:Ljava/util/concurrent/atomic/AtomicBoolean;
            //    70: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
            //    73: ifne            7
            //    76: aload           5
            //    78: aload_0        
            //    79: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.bufferSize:I
            //    82: aload_0        
            //    83: aload_0        
            //    84: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.delayError:Z
            //    87: invokestatic    rx/internal/operators/OperatorGroupBy$GroupedUnicast.createWith:(Ljava/lang/Object;ILrx/internal/operators/OperatorGroupBy$GroupBySubscriber;Z)Lrx/internal/operators/OperatorGroupBy$GroupedUnicast;
            //    90: astore          4
            //    92: aload_0        
            //    93: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.groups:Ljava/util/Map;
            //    96: aload_3        
            //    97: aload           4
            //    99: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //   104: pop            
            //   105: aload_0        
            //   106: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.groupCount:Ljava/util/concurrent/atomic/AtomicInteger;
            //   109: invokevirtual   java/util/concurrent/atomic/AtomicInteger.getAndIncrement:()I
            //   112: pop            
            //   113: iconst_0       
            //   114: istore_2       
            //   115: aload           7
            //   117: aload           4
            //   119: invokeinterface java/util/Queue.offer:(Ljava/lang/Object;)Z
            //   124: pop            
            //   125: aload_0        
            //   126: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupBySubscriber.drain:()V
            //   129: aload_0        
            //   130: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.valueSelector:Lrx/functions/Func1;
            //   133: aload_1        
            //   134: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
            //   139: astore_1       
            //   140: aload           4
            //   142: aload_1        
            //   143: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupedUnicast.onNext:(Ljava/lang/Object;)V
            //   146: aload_0        
            //   147: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.evictedKeys:Ljava/util/Queue;
            //   150: ifnull          229
            //   153: aload_0        
            //   154: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.evictedKeys:Ljava/util/Queue;
            //   157: invokeinterface java/util/Queue.poll:()Ljava/lang/Object;
            //   162: astore_1       
            //   163: aload_1        
            //   164: ifnull          229
            //   167: aload_0        
            //   168: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.groups:Ljava/util/Map;
            //   171: aload_1        
            //   172: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
            //   177: checkcast       Lrx/internal/operators/OperatorGroupBy$GroupedUnicast;
            //   180: astore_1       
            //   181: aload_1        
            //   182: ifnull          153
            //   185: aload_1        
            //   186: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupedUnicast.onComplete:()V
            //   189: goto            153
            //   192: astore_1       
            //   193: aload_0        
            //   194: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupBySubscriber.unsubscribe:()V
            //   197: aload_0        
            //   198: aload           8
            //   200: aload           7
            //   202: aload_1        
            //   203: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupBySubscriber.errorAll:(Lrx/Subscriber;Ljava/util/Queue;Ljava/lang/Throwable;)V
            //   206: return         
            //   207: getstatic       rx/internal/operators/OperatorGroupBy$GroupBySubscriber.NULL_KEY:Ljava/lang/Object;
            //   210: astore_3       
            //   211: goto            42
            //   214: astore_1       
            //   215: aload_0        
            //   216: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupBySubscriber.unsubscribe:()V
            //   219: aload_0        
            //   220: aload           8
            //   222: aload           7
            //   224: aload_1        
            //   225: invokevirtual   rx/internal/operators/OperatorGroupBy$GroupBySubscriber.errorAll:(Lrx/Subscriber;Ljava/util/Queue;Ljava/lang/Throwable;)V
            //   228: return         
            //   229: iload_2        
            //   230: ifeq            7
            //   233: aload_0        
            //   234: getfield        rx/internal/operators/OperatorGroupBy$GroupBySubscriber.s:Lrx/internal/producers/ProducerArbiter;
            //   237: lconst_1       
            //   238: invokevirtual   rx/internal/producers/ProducerArbiter.request:(J)V
            //   241: return         
            //    Signature:
            //  (TT;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  20     32     192    207    Ljava/lang/Throwable;
            //  129    140    214    229    Ljava/lang/Throwable;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0129:
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
        
        public void requestMore(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            BackpressureUtils.getAndAddRequest(this.requested, n);
            this.drain();
        }
        
        @Override
        public void setProducer(final Producer producer) {
            this.s.setProducer(producer);
        }
        
        static class EvictionAction<K> implements Action1<K>
        {
            final Queue<K> evictedKeys;
            
            EvictionAction(final Queue<K> evictedKeys) {
                this.evictedKeys = evictedKeys;
            }
            
            @Override
            public void call(final K k) {
                this.evictedKeys.offer(k);
            }
        }
    }
    
    static final class GroupedUnicast<K, T> extends GroupedObservable<K, T>
    {
        final State<T, K> state;
        
        protected GroupedUnicast(final K k, final State<T, K> state) {
            super(k, (OnSubscribe<Object>)state);
            this.state = state;
        }
        
        public static <T, K> GroupedUnicast<K, T> createWith(final K k, final int n, final GroupBySubscriber<?, K, T> groupBySubscriber, final boolean b) {
            return new GroupedUnicast<K, T>(k, (State<T, K>)new State(n, (GroupBySubscriber<?, Object, Object>)groupBySubscriber, k, b));
        }
        
        public void onComplete() {
            this.state.onComplete();
        }
        
        public void onError(final Throwable t) {
            this.state.onError(t);
        }
        
        public void onNext(final T t) {
            this.state.onNext(t);
        }
    }
    
    static final class State<T, K> extends AtomicInteger implements Producer, Subscription, OnSubscribe<T>
    {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<Subscriber<? super T>> actual;
        final AtomicBoolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once;
        final GroupBySubscriber<?, K, T> parent;
        final Queue<Object> queue;
        final AtomicLong requested;
        
        public State(final int n, final GroupBySubscriber<?, K, T> parent, final K key, final boolean delayError) {
            this.queue = new ConcurrentLinkedQueue<Object>();
            this.parent = parent;
            this.key = key;
            this.delayError = delayError;
            this.cancelled = new AtomicBoolean();
            this.actual = new AtomicReference<Subscriber<? super T>>();
            this.once = new AtomicBoolean();
            this.requested = new AtomicLong();
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            if (this.once.compareAndSet(false, true)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                this.actual.lazySet(subscriber);
                this.drain();
                return;
            }
            subscriber.onError(new IllegalStateException("Only one Subscriber allowed!"));
        }
        
        boolean checkTerminated(final boolean b, final boolean b2, final Subscriber<? super T> subscriber, final boolean b3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                return true;
            }
            if (b) {
                if (b3) {
                    if (b2) {
                        final Throwable error = this.error;
                        if (error != null) {
                            subscriber.onError(error);
                            return true;
                        }
                        subscriber.onCompleted();
                        return true;
                    }
                }
                else {
                    final Throwable error2 = this.error;
                    if (error2 != null) {
                        this.queue.clear();
                        subscriber.onError(error2);
                        return true;
                    }
                    if (b2) {
                        subscriber.onCompleted();
                        return true;
                    }
                }
            }
            return false;
        }
        
        void drain() {
            Label_0007: {
                if (this.getAndIncrement() == 0) {
                    int n = 1;
                    final Queue<Object> queue = this.queue;
                    final boolean delayError = this.delayError;
                    Subscriber<? super T> subscriber = this.actual.get();
                    final NotificationLite<? super T> instance = NotificationLite.instance();
                    while (true) {
                        if (subscriber != null) {
                            if (this.checkTerminated(this.done, queue.isEmpty(), subscriber, delayError)) {
                                break;
                            }
                            long value;
                            long n2;
                            for (value = this.requested.get(), n2 = 0L; n2 != value; ++n2) {
                                final boolean done = this.done;
                                final Object poll = queue.poll();
                                final boolean b = poll == null;
                                if (this.checkTerminated(done, b, subscriber, delayError)) {
                                    break Label_0007;
                                }
                                if (b) {
                                    break;
                                }
                                subscriber.onNext(instance.getValue(poll));
                            }
                            if (n2 != 0L) {
                                if (value != Long.MAX_VALUE) {
                                    BackpressureUtils.produced(this.requested, n2);
                                }
                                this.parent.s.request(n2);
                            }
                        }
                        final int addAndGet = this.addAndGet(-n);
                        if (addAndGet == 0) {
                            break;
                        }
                        n = addAndGet;
                        if (subscriber != null) {
                            continue;
                        }
                        subscriber = this.actual.get();
                        n = addAndGet;
                    }
                }
            }
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.cancelled.get();
        }
        
        public void onComplete() {
            this.done = true;
            this.drain();
        }
        
        public void onError(final Throwable error) {
            this.error = error;
            this.done = true;
            this.drain();
        }
        
        public void onNext(final T t) {
            if (t == null) {
                this.error = new NullPointerException();
                this.done = true;
            }
            else {
                this.queue.offer(NotificationLite.instance().next(t));
            }
            this.drain();
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            }
            if (n != 0L) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                this.drain();
            }
        }
        
        @Override
        public void unsubscribe() {
            if (this.cancelled.compareAndSet(false, true) && this.getAndIncrement() == 0) {
                this.parent.cancel(this.key);
            }
        }
    }
}
