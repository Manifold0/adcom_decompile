// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeFromArray<T> implements OnSubscribe<T>
{
    final T[] array;
    
    public OnSubscribeFromArray(final T[] array) {
        this.array = array;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        subscriber.setProducer(new FromArrayProducer<Object>((Subscriber<?>)subscriber, (Object[])this.array));
    }
    
    static final class FromArrayProducer<T> extends AtomicLong implements Producer
    {
        private static final long serialVersionUID = 3534218984725836979L;
        final T[] array;
        final Subscriber<? super T> child;
        int index;
        
        public FromArrayProducer(final Subscriber<? super T> child, final T[] array) {
            this.child = child;
            this.array = array;
        }
        
        void fastPath() {
            final Subscriber<? super T> child = this.child;
            final T[] array = this.array;
            for (int length = array.length, i = 0; i < length; ++i) {
                final T t = array[i];
                if (child.isUnsubscribed()) {
                    return;
                }
                child.onNext(t);
            }
            if (!child.isUnsubscribed()) {
                child.onCompleted();
            }
        }
        
        @Override
        public void request(final long n) {
            if (n < 0L) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            if (n == Long.MAX_VALUE) {
                if (BackpressureUtils.getAndAddRequest(this, n) == 0L) {
                    this.fastPath();
                }
            }
            else if (n != 0L && BackpressureUtils.getAndAddRequest(this, n) == 0L) {
                this.slowPath(n);
            }
        }
        
        void slowPath(long addAndGet) {
            final Subscriber<? super T> child = this.child;
            final T[] array = this.array;
            final int length = array.length;
            long n = 0L;
            int index = this.index;
            Block_3: {
                while (true) {
                    if (addAndGet != 0L && index != length) {
                        if (child.isUnsubscribed()) {
                            break;
                        }
                        child.onNext(array[index]);
                        ++index;
                        if (index == length) {
                            break Block_3;
                        }
                        --addAndGet;
                        --n;
                    }
                    else {
                        if ((addAndGet = this.get() + n) != 0L) {
                            continue;
                        }
                        this.index = index;
                        addAndGet = this.addAndGet(n);
                        if (addAndGet == 0L) {
                            break;
                        }
                        n = 0L;
                    }
                }
                return;
            }
            if (!child.isUnsubscribed()) {
                child.onCompleted();
            }
        }
    }
}
