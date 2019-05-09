// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.Subscription;
import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;
import rx.internal.producers.SingleDelayedProducer;
import rx.Subscriber;
import java.util.List;
import rx.Observable;

public final class OperatorToObservableList<T> implements Operator<List<T>, T>
{
    OperatorToObservableList() {
    }
    
    public static <T> OperatorToObservableList<T> instance() {
        return (OperatorToObservableList<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final SingleDelayedProducer producer = new SingleDelayedProducer((Subscriber<? super T>)subscriber);
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            boolean completed;
            List<T> list = new LinkedList<T>();
            
            @Override
            public void onCompleted() {
                if (this.completed) {
                    return;
                }
                this.completed = true;
                try {
                    final ArrayList value = new ArrayList((Collection<? extends E>)this.list);
                    this.list = null;
                    producer.setValue(value);
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, this);
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                if (!this.completed) {
                    this.list.add(t);
                }
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(producer);
        return subscriber2;
    }
    
    static final class Holder
    {
        static final OperatorToObservableList<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorToObservableList<Object>();
        }
    }
}
