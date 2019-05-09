// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscription;
import rx.subscriptions.SerialSubscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func1;
import rx.Observable;

public final class OperatorDebounceWithSelector<T, U> implements Operator<T, T>
{
    final Func1<? super T, ? extends Observable<U>> selector;
    
    public OperatorDebounceWithSelector(final Func1<? super T, ? extends Observable<U>> selector) {
        this.selector = selector;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber((Subscriber<? super T>)subscriber);
        final SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        return new Subscriber<T>(subscriber) {
            final Subscriber<?> self = this;
            final OperatorDebounceWithTime.DebounceState<T> state = new OperatorDebounceWithTime.DebounceState();
            
            @Override
            public void onCompleted() {
                this.state.emitAndComplete(serializedSubscriber, this);
            }
            
            @Override
            public void onError(final Throwable t) {
                serializedSubscriber.onError(t);
                this.unsubscribe();
                this.state.clear();
            }
            
            @Override
            public void onNext(final T t) {
                try {
                    final Observable observable = (Observable)OperatorDebounceWithSelector.this.selector.call((Object)t);
                    final Subscriber<U> subscriber = new Subscriber<U>() {
                        final /* synthetic */ int val$index = OperatorDebounceWithSelector$1.this.state.next(t);
                        
                        @Override
                        public void onCompleted() {
                            Subscriber.this.state.emit(this.val$index, serializedSubscriber, Subscriber.this.self);
                            this.unsubscribe();
                        }
                        
                        @Override
                        public void onError(final Throwable t) {
                            Subscriber.this.self.onError(t);
                        }
                        
                        @Override
                        public void onNext(final U u) {
                            this.onCompleted();
                        }
                    };
                    serialSubscription.set(subscriber);
                    observable.unsafeSubscribe(subscriber);
                }
                catch (Throwable t2) {
                    Exceptions.throwOrReport(t2, this);
                }
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
    }
}
