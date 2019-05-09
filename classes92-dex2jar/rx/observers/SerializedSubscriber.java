// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.Observer;
import rx.Subscriber;

public class SerializedSubscriber<T> extends Subscriber<T>
{
    private final Observer<T> s;
    
    public SerializedSubscriber(final Subscriber<? super T> subscriber) {
        this(subscriber, true);
    }
    
    public SerializedSubscriber(final Subscriber<? super T> subscriber, final boolean b) {
        super(subscriber, b);
        this.s = new SerializedObserver<T>(subscriber);
    }
    
    @Override
    public void onCompleted() {
        this.s.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        this.s.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        this.s.onNext(t);
    }
}
