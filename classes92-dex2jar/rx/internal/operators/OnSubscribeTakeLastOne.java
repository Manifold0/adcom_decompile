// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeTakeLastOne<T> implements OnSubscribe<T>
{
    final Observable<T> source;
    
    public OnSubscribeTakeLastOne(final Observable<T> source) {
        this.source = source;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        new TakeLastOneSubscriber(subscriber).subscribeTo((Observable)this.source);
    }
    
    static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscriber<T, T>
    {
        static final Object EMPTY;
        
        static {
            EMPTY = new Object();
        }
        
        public TakeLastOneSubscriber(final Subscriber<? super T> subscriber) {
            super(subscriber);
            this.value = (R)TakeLastOneSubscriber.EMPTY;
        }
        
        @Override
        public void onCompleted() {
            final R value = this.value;
            if (value == TakeLastOneSubscriber.EMPTY) {
                this.complete();
                return;
            }
            this.complete((T)value);
        }
        
        @Override
        public void onNext(final T value) {
            this.value = (R)value;
        }
    }
}
