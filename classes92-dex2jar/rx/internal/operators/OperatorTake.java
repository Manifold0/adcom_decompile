// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.Observable;

public final class OperatorTake<T> implements Operator<T, T>
{
    final int limit;
    
    public OperatorTake(final int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + limit);
        }
        this.limit = limit;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            boolean completed;
            int count;
            
            @Override
            public void onCompleted() {
                if (!this.completed) {
                    this.completed = true;
                    subscriber.onCompleted();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    subscriber.onError(t);
                }
                finally {
                    this.unsubscribe();
                }
            }
            
            @Override
            public void onNext(final T t) {
                if (this.isUnsubscribed()) {
                    return;
                }
                int n = this.count++;
                if (n >= OperatorTake.this.limit) {
                    return;
                }
                Label_0082: {
                    if (this.count != OperatorTake.this.limit) {
                        break Label_0082;
                    }
                    n = 1;
                    while (true) {
                        subscriber.onNext(t);
                        if (n == 0 || this.completed) {
                            return;
                        }
                        this.completed = true;
                        try {
                            subscriber.onCompleted();
                            return;
                            n = 0;
                            continue;
                        }
                        finally {
                            this.unsubscribe();
                        }
                        break;
                    }
                }
            }
            
            @Override
            public void setProducer(final Producer producer) {
                subscriber.setProducer(new Producer() {
                    final AtomicLong requested = new AtomicLong(0L);
                    
                    @Override
                    public void request(final long n) {
                        if (n > 0L && !Subscriber.this.completed) {
                            long value;
                            long min;
                            do {
                                value = this.requested.get();
                                min = Math.min(n, OperatorTake.this.limit - value);
                                if (min == 0L) {
                                    return;
                                }
                            } while (!this.requested.compareAndSet(value, value + min));
                            producer.request(min);
                        }
                    }
                });
            }
        };
        if (this.limit == 0) {
            subscriber.onCompleted();
            subscriber2.unsubscribe();
        }
        subscriber.add(subscriber2);
        return subscriber2;
    }
}
