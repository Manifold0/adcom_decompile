// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.ArrayList;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import java.util.HashMap;
import rx.functions.Func1;
import rx.functions.Func0;
import java.util.Collection;
import java.util.Map;
import rx.Observable;

public final class OnSubscribeToMultimap<T, K, V> implements OnSubscribe<Map<K, Collection<V>>>, Func0<Map<K, Collection<V>>>
{
    private final Func1<? super K, ? extends Collection<V>> collectionFactory;
    private final Func1<? super T, ? extends K> keySelector;
    private final Func0<? extends Map<K, Collection<V>>> mapFactory;
    private final Observable<T> source;
    private final Func1<? super T, ? extends V> valueSelector;
    
    public OnSubscribeToMultimap(final Observable<T> observable, final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2) {
        this(observable, func1, func2, null, (Func1)DefaultMultimapCollectionFactory.instance());
    }
    
    public OnSubscribeToMultimap(final Observable<T> observable, final Func1<? super T, ? extends K> func1, final Func1<? super T, ? extends V> func2, final Func0<? extends Map<K, Collection<V>>> func3) {
        this(observable, func1, func2, func3, (Func1)DefaultMultimapCollectionFactory.instance());
    }
    
    public OnSubscribeToMultimap(final Observable<T> source, final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends V> valueSelector, final Func0<? extends Map<K, Collection<V>>> mapFactory, final Func1<? super K, ? extends Collection<V>> collectionFactory) {
        this.source = source;
        this.keySelector = keySelector;
        this.valueSelector = valueSelector;
        if (mapFactory == null) {
            this.mapFactory = this;
        }
        else {
            this.mapFactory = mapFactory;
        }
        this.collectionFactory = collectionFactory;
    }
    
    @Override
    public Map<K, Collection<V>> call() {
        return new HashMap<K, Collection<V>>();
    }
    
    @Override
    public void call(final Subscriber<? super Map<K, Collection<V>>> subscriber) {
        try {
            new ToMultimapSubscriber((Subscriber<? super Map<Object, Collection<Object>>>)subscriber, (Map<Object, Collection<Object>>)this.mapFactory.call(), (Func1<? super Object, ?>)this.keySelector, (Func1<? super Object, ?>)this.valueSelector, (Func1<? super Object, ? extends Collection<Object>>)this.collectionFactory).subscribeTo((Observable)this.source);
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            subscriber.onError(t);
        }
    }
    
    private static final class DefaultMultimapCollectionFactory<K, V> implements Func1<K, Collection<V>>
    {
        private static final DefaultMultimapCollectionFactory<Object, Object> INSTANCE;
        
        static {
            INSTANCE = new DefaultMultimapCollectionFactory<Object, Object>();
        }
        
        static <K, V> DefaultMultimapCollectionFactory<K, V> instance() {
            return (DefaultMultimapCollectionFactory<K, V>)DefaultMultimapCollectionFactory.INSTANCE;
        }
        
        @Override
        public Collection<V> call(final K k) {
            return new ArrayList<V>();
        }
    }
    
    private static final class ToMultimapSubscriber<T, K, V> extends DeferredScalarSubscriberSafe<T, Map<K, Collection<V>>>
    {
        private final Func1<? super K, ? extends Collection<V>> collectionFactory;
        private final Func1<? super T, ? extends K> keySelector;
        private final Func1<? super T, ? extends V> valueSelector;
        
        ToMultimapSubscriber(final Subscriber<? super Map<K, Collection<V>>> subscriber, final Map<K, Collection<V>> value, final Func1<? super T, ? extends K> keySelector, final Func1<? super T, ? extends V> valueSelector, final Func1<? super K, ? extends Collection<V>> collectionFactory) {
            super(subscriber);
            this.value = (R)value;
            this.hasValue = true;
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
            this.collectionFactory = collectionFactory;
        }
        
        @Override
        public void onNext(final T t) {
            if (this.done) {
                return;
            }
            try {
                final K call = (K)this.keySelector.call((Object)t);
                final V call2 = (V)this.valueSelector.call((Object)t);
                Collection<Object> collection;
                if ((collection = ((Map)this.value).get(call)) == null) {
                    collection = (Collection<Object>)this.collectionFactory.call((Object)call);
                    ((Map)this.value).put(call, collection);
                }
                collection.add(call2);
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
