// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.Observable;

public class OperatorDoOnRequest<T> implements Operator<T, T>
{
    final Action1<Long> request;
    
    public OperatorDoOnRequest(final Action1<Long> request) {
        this.request = request;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final ParentSubscriber<Object> parentSubscriber = new ParentSubscriber<Object>((Subscriber<? super Object>)subscriber);
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                OperatorDoOnRequest.this.request.call(n);
                parentSubscriber.requestMore(n);
            }
        });
        subscriber.add(parentSubscriber);
        return parentSubscriber;
    }
    
    static final class ParentSubscriber<T> extends Subscriber<T>
    {
        private final Subscriber<? super T> child;
        
        ParentSubscriber(final Subscriber<? super T> child) {
            this.child = child;
            this.request(0L);
        }
        
        private void requestMore(final long n) {
            this.request(n);
        }
        
        @Override
        public void onCompleted() {
            this.child.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.child.onNext((Object)t);
        }
    }
}
