// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.exceptions.Exceptions;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.Semaphore;
import rx.Notification;
import rx.Subscriber;
import java.util.Iterator;
import rx.Observable;

public final class BlockingOperatorLatest
{
    private BlockingOperatorLatest() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Iterable<T> latest(final Observable<? extends T> observable) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                final LatestObserverIterator<T> latestObserverIterator = new LatestObserverIterator<T>();
                observable.materialize().subscribe(latestObserverIterator);
                return latestObserverIterator;
            }
        };
    }
    
    static final class LatestObserverIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T>
    {
        Notification<? extends T> iteratorNotification;
        final Semaphore notify;
        final AtomicReference<Notification<? extends T>> value;
        
        LatestObserverIterator() {
            this.notify = new Semaphore(0);
            this.value = new AtomicReference<Notification<? extends T>>();
        }
        
        @Override
        public boolean hasNext() {
            if (this.iteratorNotification != null && this.iteratorNotification.isOnError()) {
                throw Exceptions.propagate(this.iteratorNotification.getThrowable());
            }
            if ((this.iteratorNotification == null || !this.iteratorNotification.isOnCompleted()) && this.iteratorNotification == null) {
                try {
                    this.notify.acquire();
                    this.iteratorNotification = this.value.getAndSet(null);
                    if (this.iteratorNotification.isOnError()) {
                        throw Exceptions.propagate(this.iteratorNotification.getThrowable());
                    }
                }
                catch (InterruptedException ex) {
                    this.unsubscribe();
                    Thread.currentThread().interrupt();
                    this.iteratorNotification = Notification.createOnError(ex);
                    throw Exceptions.propagate(ex);
                }
            }
            return !this.iteratorNotification.isOnCompleted();
        }
        
        @Override
        public T next() {
            if (this.hasNext() && this.iteratorNotification.isOnNext()) {
                final T value = (T)this.iteratorNotification.getValue();
                this.iteratorNotification = null;
                return value;
            }
            throw new NoSuchElementException();
        }
        
        @Override
        public void onCompleted() {
        }
        
        @Override
        public void onError(final Throwable t) {
        }
        
        @Override
        public void onNext(final Notification<? extends T> notification) {
            int n;
            if (this.value.getAndSet(notification) == null) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                this.notify.release();
            }
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }
}
