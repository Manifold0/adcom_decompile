// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import java.util.HashMap;
import rx.functions.Func1;
import rx.functions.Func0;
import java.util.Map;
import rx.Observable;

public final class OnSubscribeToMap<T, K, V> implements OnSubscribe<Map<K, V>>, Func0<Map<K, V>>
{
    final Func1<? super T, ? extends K> keySelector;
    final Func0<? extends Map<K, V>> mapFactory;
    final Observable<T> source;
    final Func1<? super T, ? extends V> valueSelector;
    
    public OnSubscribeToMap(final Observable<T> observable, final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2) {
        this(observable, func1, func2, null);
    }
    
    public OnSubscribeToMap(final Observable<T> source, final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends V> valueSelector, final Func0<? extends Map<K, V>> mapFactory) {
        this.source = source;
        this.keySelector = keySelector;
        this.valueSelector = valueSelector;
        if (mapFactory == null) {
            this.mapFactory = this;
            return;
        }
        this.mapFactory = mapFactory;
    }
    
    @Override
    public Map<K, V> call() {
        return new HashMap<K, V>();
    }
    
    @Override
    public void call(final Subscriber<? super Map<K, V>> subscriber) {
        try {
            new ToMapSubscriber(subscriber, (Map)this.mapFactory.call(), this.keySelector, this.valueSelector).subscribeTo((Observable)this.source);
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
        }
    }
    
    static final class ToMapSubscriber<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, V>>
    {
        final Func1<? super T, ? extends K> keySelector;
        final Func1<? super T, ? extends V> valueSelector;
        
        ToMapSubscriber(final Subscriber<? super Map<K, V>> subscriber, final Map<K, V> value, final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends V> valueSelector) {
            super(subscriber);
            this.value = (R)value;
            this.hasValue = true;
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            try {
                ((Map)this.value).put(this.keySelector.call((Object)t), this.valueSelector.call((Object)t));
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.unsubscribe();
                this.onError(t2);
            }
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
    }
}
