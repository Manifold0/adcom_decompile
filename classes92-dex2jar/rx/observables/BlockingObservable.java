// 
// Decompiled by Procyon v0.5.34
// 

package rx.observables;

import rx.internal.operators.BlockingOperatorToFuture;
import java.util.concurrent.Future;
import rx.functions.Actions;
import rx.exceptions.OnErrorNotImplementedException;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Producer;
import rx.Subscription;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.internal.operators.NotificationLite;
import rx.Observer;
import rx.annotations.Beta;
import rx.internal.operators.BlockingOperatorNext;
import rx.internal.operators.BlockingOperatorMostRecent;
import rx.internal.operators.BlockingOperatorLatest;
import rx.internal.operators.BlockingOperatorToIterator;
import java.util.Iterator;
import rx.functions.Action1;
import rx.internal.util.UtilityFunctions;
import rx.functions.Func1;
import rx.exceptions.Exceptions;
import rx.internal.util.BlockingUtils;
import rx.Subscriber;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;

public final class BlockingObservable<T>
{
    static final Object ON_START;
    static final Object SET_PRODUCER;
    static final Object UNSUBSCRIBE;
    private final Observable<? extends T> o;
    
    static {
        ON_START = new Object();
        SET_PRODUCER = new Object();
        UNSUBSCRIBE = new Object();
    }
    
    private BlockingObservable(final Observable<? extends T> o) {
        this.o = o;
    }
    
    private T blockForSingle(final Observable<? extends T> observable) {
        final AtomicReference<T> atomicReference = new AtomicReference<T>();
        final AtomicReference<Throwable> atomicReference2 = new AtomicReference<Throwable>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, observable.subscribe(new Subscriber<T>() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                atomicReference2.set(t);
                countDownLatch.countDown();
            }
            
            @Override
            public void onNext(final T t) {
                atomicReference.set(t);
            }
        }));
        if (atomicReference2.get() != null) {
            Exceptions.propagate(atomicReference2.get());
        }
        return atomicReference.get();
    }
    
    public static <T> BlockingObservable<T> from(final Observable<? extends T> observable) {
        return new BlockingObservable<T>(observable);
    }
    
    public T first() {
        return this.blockForSingle(this.o.first());
    }
    
    public T first(final Func1<? super T, Boolean> func1) {
        return this.blockForSingle(this.o.first(func1));
    }
    
    public T firstOrDefault(final T t) {
        return this.blockForSingle((Observable<? extends T>)this.o.map((Func1<? super T, ? extends T>)UtilityFunctions.identity()).firstOrDefault(t));
    }
    
    public T firstOrDefault(final T t, final Func1<? super T, Boolean> func1) {
        return this.blockForSingle((Observable<? extends T>)this.o.filter(func1).map((Func1<? super T, ? extends T>)UtilityFunctions.identity()).firstOrDefault(t));
    }
    
    public void forEach(final Action1<? super T> action1) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference<Throwable> atomicReference = new AtomicReference<Throwable>();
        BlockingUtils.awaitForComplete(countDownLatch, this.o.subscribe(new Subscriber<T>() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                atomicReference.set(t);
                countDownLatch.countDown();
            }
            
            @Override
            public void onNext(final T t) {
                action1.call(t);
            }
        }));
        if (atomicReference.get() != null) {
            Exceptions.propagate(atomicReference.get());
        }
    }
    
    public Iterator<T> getIterator() {
        return BlockingOperatorToIterator.toIterator(this.o);
    }
    
    public T last() {
        return this.blockForSingle(this.o.last());
    }
    
    public T last(final Func1<? super T, Boolean> func1) {
        return this.blockForSingle(this.o.last(func1));
    }
    
    public T lastOrDefault(final T t) {
        return this.blockForSingle((Observable<? extends T>)this.o.map((Func1<? super T, ? extends T>)UtilityFunctions.identity()).lastOrDefault(t));
    }
    
    public T lastOrDefault(final T t, final Func1<? super T, Boolean> func1) {
        return this.blockForSingle((Observable<? extends T>)this.o.filter(func1).map((Func1<? super T, ? extends T>)UtilityFunctions.identity()).lastOrDefault(t));
    }
    
    public Iterable<T> latest() {
        return BlockingOperatorLatest.latest(this.o);
    }
    
    public Iterable<T> mostRecent(final T t) {
        return BlockingOperatorMostRecent.mostRecent(this.o, t);
    }
    
    public Iterable<T> next() {
        return BlockingOperatorNext.next(this.o);
    }
    
    public T single() {
        return this.blockForSingle(this.o.single());
    }
    
    public T single(final Func1<? super T, Boolean> func1) {
        return this.blockForSingle(this.o.single(func1));
    }
    
    public T singleOrDefault(final T t) {
        return this.blockForSingle((Observable<? extends T>)this.o.map((Func1<? super T, ? extends T>)UtilityFunctions.identity()).singleOrDefault(t));
    }
    
    public T singleOrDefault(final T t, final Func1<? super T, Boolean> func1) {
        return this.blockForSingle((Observable<? extends T>)this.o.filter(func1).map((Func1<? super T, ? extends T>)UtilityFunctions.identity()).singleOrDefault(t));
    }
    
    @Beta
    public void subscribe() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Throwable[] array = { null };
        BlockingUtils.awaitForComplete(countDownLatch, this.o.subscribe(new Subscriber<T>() {
            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final Throwable t) {
                array[0] = t;
                countDownLatch.countDown();
            }
            
            @Override
            public void onNext(final T t) {
            }
        }));
        final Throwable t = array[0];
        if (t != null) {
            Exceptions.propagate(t);
        }
    }
    
    @Beta
    public void subscribe(final Observer<? super T> observer) {
        final NotificationLite<Object> instance = NotificationLite.instance();
        final LinkedBlockingQueue<Object> linkedBlockingQueue = new LinkedBlockingQueue<Object>();
        final Subscription subscribe = this.o.subscribe(new Subscriber<T>() {
            @Override
            public void onCompleted() {
                linkedBlockingQueue.offer(instance.completed());
            }
            
            @Override
            public void onError(final Throwable t) {
                linkedBlockingQueue.offer(instance.error(t));
            }
            
            @Override
            public void onNext(final T t) {
                linkedBlockingQueue.offer(instance.next(t));
            }
        });
        try {
            Object o;
            do {
                if ((o = linkedBlockingQueue.poll()) == null) {
                    o = linkedBlockingQueue.take();
                }
            } while (!instance.accept((Observer<? super Object>)observer, o));
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            observer.onError(ex);
        }
        finally {
            subscribe.unsubscribe();
        }
    }
    
    @Beta
    public void subscribe(final Subscriber<? super T> subscriber) {
        final NotificationLite<Object> instance = NotificationLite.instance();
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<Object>();
        final Producer[] array = { null };
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            @Override
            public void onCompleted() {
                linkedBlockingQueue.offer(instance.completed());
            }
            
            @Override
            public void onError(final Throwable t) {
                linkedBlockingQueue.offer(instance.error(t));
            }
            
            @Override
            public void onNext(final T t) {
                linkedBlockingQueue.offer(instance.next(t));
            }
            
            @Override
            public void onStart() {
                linkedBlockingQueue.offer(BlockingObservable.ON_START);
            }
            
            @Override
            public void setProducer(final Producer producer) {
                array[0] = producer;
                linkedBlockingQueue.offer(BlockingObservable.SET_PRODUCER);
            }
        };
        subscriber.add(subscriber2);
        subscriber.add(Subscriptions.create(new Action0() {
            @Override
            public void call() {
                linkedBlockingQueue.offer(BlockingObservable.UNSUBSCRIBE);
            }
        }));
        this.o.subscribe(subscriber2);
    Block_8_Outer:
        while (true) {
            InterruptedException ex = null;
            Label_0160: {
                try {
                    while (!subscriber.isUnsubscribed()) {
                        if ((ex = linkedBlockingQueue.poll()) == null) {
                            ex = linkedBlockingQueue.take();
                        }
                        if (subscriber.isUnsubscribed() || ex == BlockingObservable.UNSUBSCRIBE) {
                            break;
                        }
                        if (ex != BlockingObservable.ON_START) {
                            break Label_0160;
                        }
                        subscriber.onStart();
                    }
                    return;
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    subscriber.onError(ex);
                    return;
                    while (true) {
                        subscriber.setProducer(array[0]);
                        continue Block_8_Outer;
                        continue;
                    }
                }
                // iftrue(Label_0186:, ex != BlockingObservable.SET_PRODUCER)
                finally {
                    subscriber2.unsubscribe();
                }
            }
            Label_0186: {
                if (instance.accept(subscriber, ex)) {
                    break;
                }
            }
        }
        subscriber2.unsubscribe();
    }
    
    @Beta
    public void subscribe(final Action1<? super T> action1) {
        this.subscribe(action1, new Action1<Throwable>() {
            @Override
            public void call(final Throwable t) {
                throw new OnErrorNotImplementedException(t);
            }
        }, Actions.empty());
    }
    
    @Beta
    public void subscribe(final Action1<? super T> action1, final Action1<? super Throwable> action2) {
        this.subscribe(action1, action2, Actions.empty());
    }
    
    @Beta
    public void subscribe(final Action1<? super T> action1, final Action1<? super Throwable> action2, final Action0 action3) {
        this.subscribe(new Observer<T>() {
            @Override
            public void onCompleted() {
                action3.call();
            }
            
            @Override
            public void onError(final Throwable t) {
                action2.call(t);
            }
            
            @Override
            public void onNext(final T t) {
                action1.call(t);
            }
        });
    }
    
    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.o);
    }
    
    public Iterable<T> toIterable() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return BlockingObservable.this.getIterator();
            }
        };
    }
}
