// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Producer;
import rx.internal.producers.SingleDelayedProducer;
import rx.Subscriber;
import java.util.concurrent.Callable;
import rx.Observable;

public final class OnSubscribeFromCallable<T> implements OnSubscribe<T>
{
    private final Callable<? extends T> resultFactory;
    
    public OnSubscribeFromCallable(final Callable<? extends T> resultFactory) {
        this.resultFactory = resultFactory;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final SingleDelayedProducer<Object> producer = new SingleDelayedProducer<Object>((Subscriber<? super Object>)subscriber);
        subscriber.setProducer(producer);
        try {
            producer.setValue(this.resultFactory.call());
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
        }
    }
}
