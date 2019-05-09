// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import java.util.NoSuchElementException;
import rx.Subscriber;
import rx.SingleSubscriber;
import rx.Observable;
import rx.Single;

public class OnSubscribeSingle<T> implements OnSubscribe<T>
{
    private final Observable<T> observable;
    
    public OnSubscribeSingle(final Observable<T> observable) {
        this.observable = observable;
    }
    
    public static <T> OnSubscribeSingle<T> create(final Observable<T> observable) {
        return new OnSubscribeSingle<T>(observable);
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final Subscriber<T> subscriber = new Subscriber<T>() {
            private T emission;
            private boolean emittedTooMany;
            private boolean itemEmitted;
            
            @Override
            public void onCompleted() {
                if (this.emittedTooMany) {
                    return;
                }
                if (this.itemEmitted) {
                    singleSubscriber.onSuccess(this.emission);
                    return;
                }
                singleSubscriber.onError(new NoSuchElementException("Observable emitted no items"));
            }
            
            @Override
            public void onError(final Throwable t) {
                singleSubscriber.onError(t);
                this.unsubscribe();
            }
            
            @Override
            public void onNext(final T emission) {
                if (this.itemEmitted) {
                    this.emittedTooMany = true;
                    singleSubscriber.onError(new IllegalArgumentException("Observable emitted too many elements"));
                    this.unsubscribe();
                    return;
                }
                this.itemEmitted = true;
                this.emission = emission;
            }
            
            @Override
            public void onStart() {
                this.request(2L);
            }
        };
        singleSubscriber.add(subscriber);
        this.observable.unsafeSubscribe(subscriber);
    }
}
