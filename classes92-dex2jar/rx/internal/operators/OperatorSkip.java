// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.Subscriber;
import rx.Observable;

public final class OperatorSkip<T> implements Operator<T, T>
{
    final int toSkip;
    
    public OperatorSkip(final int toSkip) {
        if (toSkip < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + toSkip);
        }
        this.toSkip = toSkip;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            int skipped;
            
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                if (this.skipped >= OperatorSkip.this.toSkip) {
                    subscriber.onNext(t);
                    return;
                }
                ++this.skipped;
            }
            
            @Override
            public void setProducer(final Producer producer) {
                subscriber.setProducer(producer);
                producer.request(OperatorSkip.this.toSkip);
            }
        };
    }
}
