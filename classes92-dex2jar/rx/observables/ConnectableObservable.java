// 
// Decompiled by Procyon v0.5.34
// 

package rx.observables;

import rx.internal.operators.OnSubscribeRefCount;
import rx.internal.operators.OnSubscribeAutoConnect;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.Observable;

public abstract class ConnectableObservable<T> extends Observable<T>
{
    protected ConnectableObservable(final OnSubscribe<T> onSubscribe) {
        super((OnSubscribe)onSubscribe);
    }
    
    public Observable<T> autoConnect() {
        return this.autoConnect(1);
    }
    
    public Observable<T> autoConnect(final int n) {
        return this.autoConnect(n, Actions.empty());
    }
    
    public Observable<T> autoConnect(final int n, final Action1<? super Subscription> action1) {
        if (n <= 0) {
            this.connect(action1);
            return this;
        }
        return Observable.create((OnSubscribe<T>)new OnSubscribeAutoConnect(this, n, action1));
    }
    
    public final Subscription connect() {
        final Subscription[] array = { null };
        this.connect(new Action1<Subscription>() {
            @Override
            public void call(final Subscription subscription) {
                array[0] = subscription;
            }
        });
        return array[0];
    }
    
    public abstract void connect(final Action1<? super Subscription> p0);
    
    public Observable<T> refCount() {
        return Observable.create((OnSubscribe<T>)new OnSubscribeRefCount(this));
    }
}
