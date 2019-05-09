// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import java.util.NoSuchElementException;
import rx.Subscriber;
import java.util.Iterator;
import rx.Observable;

public final class BlockingOperatorMostRecent
{
    private BlockingOperatorMostRecent() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Iterable<T> mostRecent(final Observable<? extends T> observable, final T t) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                final MostRecentObserver<T> mostRecentObserver = new MostRecentObserver<T>(t);
                observable.subscribe(mostRecentObserver);
                return mostRecentObserver.getIterable();
            }
        };
    }
    
    static final class MostRecentObserver<T> extends Subscriber<T>
    {
        final NotificationLite<T> nl;
        volatile Object value;
        
        MostRecentObserver(final T t) {
            this.nl = NotificationLite.instance();
            this.value = this.nl.next(t);
        }
        
        public Iterator<T> getIterable() {
            return new Iterator<T>() {
                private Object buf;
                
                @Override
                public boolean hasNext() {
                    this.buf = MostRecentObserver.this.value;
                    return !MostRecentObserver.this.nl.isCompleted(this.buf);
                }
                
                @Override
                public T next() {
                    try {
                        if (this.buf == null) {
                            this.buf = MostRecentObserver.this.value;
                        }
                        if (MostRecentObserver.this.nl.isCompleted(this.buf)) {
                            throw new NoSuchElementException();
                        }
                    }
                    finally {
                        this.buf = null;
                    }
                    if (MostRecentObserver.this.nl.isError(this.buf)) {
                        throw Exceptions.propagate(MostRecentObserver.this.nl.getError(this.buf));
                    }
                    final T value = MostRecentObserver.this.nl.getValue(this.buf);
                    this.buf = null;
                    return value;
                }
                
                @Override
                public void remove() {
                    throw new UnsupportedOperationException("Read only iterator");
                }
            };
        }
        
        @Override
        public void onCompleted() {
            this.value = this.nl.completed();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.value = this.nl.error(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.value = this.nl.next(t);
        }
    }
}
