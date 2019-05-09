// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.MissingBackpressureException;
import rx.exceptions.Exceptions;
import rx.internal.util.RxRingBuffer;
import rx.subscriptions.CompositeSubscription;
import rx.Observer;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func9;
import rx.functions.Func8;
import rx.functions.Func7;
import rx.functions.Func6;
import rx.functions.Func5;
import rx.functions.Func4;
import rx.functions.Func3;
import rx.functions.Functions;
import rx.functions.Func2;
import rx.functions.FuncN;
import rx.Observable;

public final class OperatorZip<R> implements Operator<R, Observable<?>[]>
{
    final FuncN<? extends R> zipFunction;
    
    public OperatorZip(final Func2 func2) {
        this.zipFunction = Functions.fromFunc((Func2<? super Object, ? super Object, ? extends R>)func2);
    }
    
    public OperatorZip(final Func3 func3) {
        this.zipFunction = Functions.fromFunc((Func3<? super Object, ? super Object, ? super Object, ? extends R>)func3);
    }
    
    public OperatorZip(final Func4 func4) {
        this.zipFunction = Functions.fromFunc((Func4<? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func4);
    }
    
    public OperatorZip(final Func5 func5) {
        this.zipFunction = Functions.fromFunc((Func5<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func5);
    }
    
    public OperatorZip(final Func6 func6) {
        this.zipFunction = Functions.fromFunc((Func6<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func6);
    }
    
    public OperatorZip(final Func7 func7) {
        this.zipFunction = Functions.fromFunc((Func7<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func7);
    }
    
    public OperatorZip(final Func8 func8) {
        this.zipFunction = Functions.fromFunc((Func8<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func8);
    }
    
    public OperatorZip(final Func9 func9) {
        this.zipFunction = Functions.fromFunc((Func9<? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? super Object, ? extends R>)func9);
    }
    
    public OperatorZip(final FuncN<? extends R> zipFunction) {
        this.zipFunction = zipFunction;
    }
    
    @Override
    public Subscriber<? super Observable[]> call(final Subscriber<? super R> subscriber) {
        final Zip zip = new Zip((Subscriber<? super Object>)subscriber, this.zipFunction);
        final ZipProducer producer = new ZipProducer((Zip<Object>)zip);
        final ZipSubscriber zipSubscriber = new ZipSubscriber(subscriber, (Zip<R>)zip, (ZipProducer<R>)producer);
        subscriber.add(zipSubscriber);
        subscriber.setProducer(producer);
        return zipSubscriber;
    }
    
    static final class Zip<R> extends AtomicLong
    {
        static final int THRESHOLD;
        private static final long serialVersionUID = 5995274816189928317L;
        final Observer<? super R> child;
        private final CompositeSubscription childSubscription;
        int emitted;
        private AtomicLong requested;
        private volatile Object[] subscribers;
        private final FuncN<? extends R> zipFunction;
        
        static {
            THRESHOLD = (int)(RxRingBuffer.SIZE * 0.7);
        }
        
        public Zip(final Subscriber<? super R> child, final FuncN<? extends R> zipFunction) {
            this.childSubscription = new CompositeSubscription();
            this.child = child;
            this.zipFunction = zipFunction;
            child.add(this.childSubscription);
        }
        
        public void start(final Observable[] array, final AtomicLong requested) {
            final Object[] subscribers = new Object[array.length];
            for (int i = 0; i < array.length; ++i) {
                final InnerSubscriber innerSubscriber = new InnerSubscriber();
                subscribers[i] = innerSubscriber;
                this.childSubscription.add(innerSubscriber);
            }
            this.requested = requested;
            this.subscribers = subscribers;
            for (int j = 0; j < array.length; ++j) {
                array[j].unsafeSubscribe((Subscriber)subscribers[j]);
            }
        }
        
        void tick() {
            final Object[] subscribers = this.subscribers;
            if (subscribers == null || this.getAndIncrement() != 0L) {
                return;
            }
            final int length = subscribers.length;
            final Observer<? super R> child = this.child;
            final AtomicLong requested = this.requested;
            while (true) {
                Object items = new Object[length];
                boolean b = true;
                for (int i = 0; i < length; ++i) {
                    final RxRingBuffer items2 = ((InnerSubscriber)subscribers[i]).items;
                    final Object peek = items2.peek();
                    if (peek == null) {
                        b = false;
                    }
                    else {
                        if (items2.isCompleted(peek)) {
                            child.onCompleted();
                            this.childSubscription.unsubscribe();
                            return;
                        }
                        items[i] = items2.getValue(peek);
                    }
                }
                if (requested.get() > 0L && b) {
                    while (true) {
                        while (true) {
                            int n;
                            try {
                                child.onNext(this.zipFunction.call((Object[])items));
                                requested.decrementAndGet();
                                ++this.emitted;
                                final int length2 = subscribers.length;
                                n = 0;
                                if (n >= length2) {
                                    break;
                                }
                                items = ((InnerSubscriber)subscribers[n]).items;
                                ((RxRingBuffer)items).poll();
                                if (((RxRingBuffer)items).isCompleted(((RxRingBuffer)items).peek())) {
                                    child.onCompleted();
                                    this.childSubscription.unsubscribe();
                                    return;
                                }
                            }
                            catch (Throwable t) {
                                Exceptions.throwOrReport(t, child, items);
                                return;
                            }
                            ++n;
                            continue;
                        }
                    }
                    if (this.emitted <= Zip.THRESHOLD) {
                        continue;
                    }
                    for (int length3 = subscribers.length, j = 0; j < length3; ++j) {
                        ((InnerSubscriber)subscribers[j]).requestMore(this.emitted);
                    }
                    this.emitted = 0;
                }
                else {
                    if (this.decrementAndGet() <= 0L) {
                        return;
                    }
                    continue;
                }
            }
        }
        
        final class InnerSubscriber extends Subscriber
        {
            final RxRingBuffer items;
            
            InnerSubscriber() {
                this.items = RxRingBuffer.getSpmcInstance();
            }
            
            @Override
            public void onCompleted() {
                this.items.onCompleted();
                Zip.this.tick();
            }
            
            @Override
            public void onError(final Throwable t) {
                Zip.this.child.onError(t);
            }
            
            @Override
            public void onNext(final Object o) {
                while (true) {
                    try {
                        this.items.onNext(o);
                        Zip.this.tick();
                    }
                    catch (MissingBackpressureException ex) {
                        this.onError(ex);
                        continue;
                    }
                    break;
                }
            }
            
            @Override
            public void onStart() {
                this.request(RxRingBuffer.SIZE);
            }
            
            public void requestMore(final long n) {
                this.request(n);
            }
        }
    }
    
    static final class ZipProducer<R> extends AtomicLong implements Producer
    {
        private static final long serialVersionUID = -1216676403723546796L;
        final Zip<R> zipper;
        
        public ZipProducer(final Zip<R> zipper) {
            this.zipper = zipper;
        }
        
        @Override
        public void request(final long n) {
            BackpressureUtils.getAndAddRequest(this, n);
            this.zipper.tick();
        }
    }
    
    final class ZipSubscriber extends Subscriber<Observable[]>
    {
        final Subscriber<? super R> child;
        final ZipProducer<R> producer;
        boolean started;
        final Zip<R> zipper;
        
        public ZipSubscriber(final Subscriber<? super R> child, final Zip<R> zipper, final ZipProducer<R> producer) {
            this.child = child;
            this.zipper = zipper;
            this.producer = producer;
        }
        
        @Override
        public void onCompleted() {
            if (!this.started) {
                this.child.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final Observable[] array) {
            if (array == null || array.length == 0) {
                this.child.onCompleted();
                return;
            }
            this.started = true;
            this.zipper.start(array, this.producer);
        }
    }
}
