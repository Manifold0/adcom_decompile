// 
// Decompiled by Procyon v0.5.34
// 

package rx.observables;

import rx.Subscriber;
import rx.Observable;

public class GroupedObservable<K, T> extends Observable<T>
{
    private final K key;
    
    protected GroupedObservable(final K key, final OnSubscribe<T> onSubscribe) {
        super((OnSubscribe)onSubscribe);
        this.key = key;
    }
    
    public static <K, T> GroupedObservable<K, T> create(final K k, final OnSubscribe<T> onSubscribe) {
        return new GroupedObservable<K, T>(k, onSubscribe);
    }
    
    public static <K, T> GroupedObservable<K, T> from(final K k, final Observable<T> observable) {
        return new GroupedObservable<K, T>(k, new OnSubscribe<T>() {
            @Override
            public void call(final Subscriber<? super T> subscriber) {
                observable.unsafeSubscribe(subscriber);
            }
        });
    }
    
    public K getKey() {
        return this.key;
    }
}
