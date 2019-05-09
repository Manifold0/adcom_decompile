// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Producer;
import rx.Subscription;
import rx.Observer;
import rx.exceptions.Exceptions;
import java.util.Collections;
import java.util.ArrayList;
import rx.internal.producers.SingleDelayedProducer;
import rx.Subscriber;
import rx.functions.Func2;
import java.util.Comparator;
import java.util.List;
import rx.Observable;

public final class OperatorToObservableSortedList<T> implements Operator<List<T>, T>
{
    private static final Comparator DEFAULT_SORT_FUNCTION;
    final int initialCapacity;
    final Comparator<? super T> sortFunction;
    
    static {
        DEFAULT_SORT_FUNCTION = new DefaultComparableFunction();
    }
    
    public OperatorToObservableSortedList(final int initialCapacity) {
        this.sortFunction = (Comparator<? super T>)OperatorToObservableSortedList.DEFAULT_SORT_FUNCTION;
        this.initialCapacity = initialCapacity;
    }
    
    public OperatorToObservableSortedList(final Func2<? super T, ? super T, Integer> func2, final int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.sortFunction = new Comparator<T>() {
            @Override
            public int compare(final T t, final T t2) {
                return func2.call(t, t2);
            }
        };
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final SingleDelayedProducer producer = new SingleDelayedProducer((Subscriber<? super T>)subscriber);
        final Subscriber<T> subscriber2 = new Subscriber<T>() {
            boolean completed;
            List<T> list = new ArrayList<T>(OperatorToObservableSortedList.this.initialCapacity);
            
            @Override
            public void onCompleted() {
                if (this.completed) {
                    return;
                }
                this.completed = true;
                final List<T> list = this.list;
                this.list = null;
                try {
                    Collections.sort(list, OperatorToObservableSortedList.this.sortFunction);
                    producer.setValue(list);
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
    
    static final class DefaultComparableFunction implements Comparator<Object>
    {
        @Override
        public int compare(final Object o, final Object o2) {
            return ((Comparable)o).compareTo(o2);
        }
    }
}
