// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import rx.Observer;
import rx.Observable;

public abstract class Subject<T, R> extends Observable<R> implements Observer<T>
{
    protected Subject(final OnSubscribe<R> onSubscribe) {
        super((OnSubscribe)onSubscribe);
    }
    
    public abstract boolean hasObservers();
    
    public final SerializedSubject<T, R> toSerialized() {
        if (this.getClass() == SerializedSubject.class) {
            return (SerializedSubject<T, R>)this;
        }
        return new SerializedSubject<T, R>(this);
    }
}
