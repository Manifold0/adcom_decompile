// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorTakeUntilPredicate<T> implements Operator<T, T>
{
    final Func1<? super T, Boolean> stopPredicate;
    
    public OperatorTakeUntilPredicate(final Func1<? super T, Boolean> stopPredicate) {
        this.stopPredicate = stopPredicate;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                parentSubscriber.downstreamRequest(n);
            }
        });
        return parentSubscriber;
    }
    
    final class ParentSubscriber extends Subscriber<T>
    {
        private final Subscriber<? super T> child;
        private boolean done;
        
        ParentSubscriber(final Subscriber<? super T> child) {
            this.child = child;
        }
        
        void downstreamRequest(final long n) {
            this.request(n);
        }
        
        @Override
        public void onCompleted() {
            if (!this.done) {
                this.child.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (!this.done) {
                this.child.onError(t);
            }
        }
        
        @Override
        public void onNext(final T t) {
            this.child.onNext((Object)t);
            try {
                if (OperatorTakeUntilPredicate.this.stopPredicate.call((Object)t)) {
                    this.done = true;
                    this.child.onCompleted();
                    this.unsubscribe();
                }
            }
            catch (Throwable t2) {
                this.done = true;
                Exceptions.throwOrReport(t2, this.child, t);
                this.unsubscribe();
            }
        }
    }
}
