// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import java.util.Iterator;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeFromIterable<T> implements OnSubscribe<T>
{
    final Iterable<? extends T> is;
    
    public OnSubscribeFromIterable(final Iterable<? extends T> is) {
        if (is == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.is = is;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        Iterator<? extends T> iterator = null;
        Label_0040: {
            try {
                iterator = this.is.iterator();
                final boolean hasNext = iterator.hasNext();
                if (!subscriber.isUnsubscribed()) {
                    if (hasNext) {
                        break Label_0040;
                    }
                    subscriber.onCompleted();
                }
                return;
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, subscriber);
                return;
            }
        }
        subscriber.setProducer(new IterableProducer<Object>((Subscriber<? super Object>)subscriber, iterator));
    }
    
    static final class IterableProducer<T> extends AtomicLong implements Producer
    {
        private static final long serialVersionUID = -8730475647105475802L;
        private final Iterator<? extends T> it;
        private final Subscriber<? super T> o;
        
        IterableProducer(final Subscriber<? super T> o, final Iterator<? extends T> it) {
            this.o = o;
            this.it = it;
        }
        
        void fastPath() {
            final Subscriber<? super T> o = this.o;
            final Iterator<? extends T> it = this.it;
            while (!o.isUnsubscribed()) {
                try {
                    o.onNext((T)it.next());
                    if (o.isUnsubscribed()) {
                        break;
                    }
                    final Iterator<? extends T> iterator = it;
                    final boolean hasNext = iterator.hasNext();
                    final boolean hasNext2 = hasNext;
                    if (hasNext2) {
                        continue;
                    }
                    final Subscriber<? super T> subscriber = o;
                    final boolean b = subscriber.isUnsubscribed();
                    if (!b) {
                        final Subscriber<? super T> subscriber2 = o;
                        subscriber2.onCompleted();
                        return;
                    }
                    break;
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, o);
                    return;
                }
                try {
                    final Iterator<? extends T> iterator = it;
                    final boolean hasNext2;
                    final boolean hasNext = hasNext2 = iterator.hasNext();
                    if (hasNext2) {
                        continue;
                    }
                    final Subscriber<? super T> subscriber = o;
                    final boolean b = subscriber.isUnsubscribed();
                    if (!b) {
                        final Subscriber<? super T> subscriber2 = o;
                        subscriber2.onCompleted();
                        return;
                    }
                    break;
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, o);
                }
            }
        }
        
        @Override
        public void request(final long n) {
            if (this.get() != Long.MAX_VALUE) {
                if (n == Long.MAX_VALUE && this.compareAndSet(0L, Long.MAX_VALUE)) {
                    this.fastPath();
                    return;
                }
                if (n > 0L && BackpressureUtils.getAndAddRequest(this, n) == 0L) {
                    this.slowPath(n);
                }
            }
        }
        
        void slowPath(long n) {
            final Subscriber<? super T> o = this.o;
            final Iterator<? extends T> it = this.it;
            long n2 = 0L;
            while (true) {
                if (n2 != n) {
                    if (o.isUnsubscribed()) {
                        break;
                    }
                    Label_0101: {
                        try {
                            o.onNext((T)it.next());
                            if (o.isUnsubscribed()) {
                                break;
                            }
                            final Iterator<? extends T> iterator = it;
                            final boolean hasNext = iterator.hasNext();
                            final boolean hasNext2 = hasNext;
                            if (hasNext2) {
                                break Label_0101;
                            }
                            final Subscriber<? super T> subscriber = o;
                            final boolean b = subscriber.isUnsubscribed();
                            if (!b) {
                                final Subscriber<? super T> subscriber2 = o;
                                subscriber2.onCompleted();
                                return;
                            }
                            break;
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, o);
                            return;
                        }
                        try {
                            final Iterator<? extends T> iterator = it;
                            final boolean hasNext2;
                            final boolean hasNext = hasNext2 = iterator.hasNext();
                            if (!hasNext2) {
                                final Subscriber<? super T> subscriber = o;
                                final boolean b = subscriber.isUnsubscribed();
                                if (!b) {
                                    final Subscriber<? super T> subscriber2 = o;
                                    subscriber2.onCompleted();
                                    return;
                                }
                                break;
                            }
                        }
                        catch (Throwable t2) {
                            Exceptions.throwOrReport(t2, o);
                            return;
                        }
                    }
                    ++n2;
                }
                else {
                    if (n2 != (n = this.get())) {
                        continue;
                    }
                    n = BackpressureUtils.produced(this, n2);
                    if (n == 0L) {
                        break;
                    }
                    n2 = 0L;
                }
            }
        }
    }
}
