// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeRange implements OnSubscribe<Integer>
{
    private final int endIndex;
    private final int startIndex;
    
    public OnSubscribeRange(final int startIndex, final int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    
    @Override
    public void call(final Subscriber<? super Integer> subscriber) {
        subscriber.setProducer(new RangeProducer(subscriber, this.startIndex, this.endIndex));
    }
    
    static final class RangeProducer extends AtomicLong implements Producer
    {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;
        
        RangeProducer(final Subscriber<? super Integer> childSubscriber, final int n, final int endOfRange) {
            this.childSubscriber = childSubscriber;
            this.currentIndex = n;
            this.endOfRange = endOfRange;
        }
        
        void fastPath() {
            final long n = this.endOfRange;
            final Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            for (long currentIndex = this.currentIndex; currentIndex != n + 1L; ++currentIndex) {
                if (childSubscriber.isUnsubscribed()) {
                    return;
                }
                childSubscriber.onNext((int)currentIndex);
            }
            if (!childSubscriber.isUnsubscribed()) {
                childSubscriber.onCompleted();
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
            long n2 = 0L;
            final long n3 = this.endOfRange + 1L;
            long currentIndex = this.currentIndex;
            final Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            Block_4: {
                while (true) {
                    if (n2 != n && currentIndex != n3) {
                        if (childSubscriber.isUnsubscribed()) {
                            break;
                        }
                        childSubscriber.onNext((int)currentIndex);
                        ++currentIndex;
                        ++n2;
                    }
                    else {
                        if (childSubscriber.isUnsubscribed()) {
                            break;
                        }
                        if (currentIndex == n3) {
                            break Block_4;
                        }
                        if ((n = this.get()) != n2) {
                            continue;
                        }
                        this.currentIndex = currentIndex;
                        n = this.addAndGet(-n2);
                        if (n == 0L) {
                            break;
                        }
                        n2 = 0L;
                    }
                }
                return;
            }
            childSubscriber.onCompleted();
        }
    }
}
