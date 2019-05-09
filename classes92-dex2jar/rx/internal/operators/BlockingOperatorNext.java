// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.BlockingQueue;
import java.util.NoSuchElementException;
import rx.exceptions.Exceptions;
import rx.Notification;
import rx.Subscriber;
import java.util.Iterator;
import rx.Observable;

public final class BlockingOperatorNext
{
    private BlockingOperatorNext() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Iterable<T> next(final Observable<? extends T> observable) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new NextIterator<T>(observable, (NextObserver<T>)new NextObserver());
            }
        };
    }
    
    static final class NextIterator<T> implements Iterator<T>
    {
        private Throwable error;
        private boolean hasNext;
        private boolean isNextConsumed;
        private final Observable<? extends T> items;
        private T next;
        private final NextObserver<T> observer;
        private boolean started;
        
        NextIterator(final Observable<? extends T> items, final NextObserver<T> observer) {
            this.hasNext = true;
            this.isNextConsumed = true;
            this.items = items;
            this.observer = observer;
        }
        
        private boolean moveToNext() {
            try {
                if (!this.started) {
                    this.started = true;
                    this.observer.setWaiting(1);
                    this.items.materialize().subscribe((Subscriber<? super Notification<? extends T>>)this.observer);
                }
                final Notification<? extends T> takeNext = this.observer.takeNext();
                if (takeNext.isOnNext()) {
                    this.isNextConsumed = false;
                    this.next = (T)takeNext.getValue();
                    return true;
                }
                this.hasNext = false;
                if (takeNext.isOnCompleted()) {
                    return false;
                }
                if (takeNext.isOnError()) {
                    this.error = takeNext.getThrowable();
                    throw Exceptions.propagate(this.error);
                }
            }
            catch (InterruptedException error) {
                this.observer.unsubscribe();
                Thread.currentThread().interrupt();
                this.error = error;
                throw Exceptions.propagate(error);
            }
            throw new IllegalStateException("Should not reach here");
        }
        
        @Override
        public boolean hasNext() {
            if (this.error != null) {
                throw Exceptions.propagate(this.error);
            }
            return this.hasNext && (!this.isNextConsumed || this.moveToNext());
        }
        
        @Override
        public T next() {
            if (this.error != null) {
                throw Exceptions.propagate(this.error);
            }
            if (this.hasNext()) {
                this.isNextConsumed = true;
                return this.next;
            }
            throw new NoSuchElementException("No more elements");
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }
    
    static final class NextObserver<T> extends Subscriber<Notification<? extends T>>
    {
        private final BlockingQueue<Notification<? extends T>> buf;
        final AtomicInteger waiting;
        
        NextObserver() {
            this.buf = new ArrayBlockingQueue<Notification<? extends T>>(1);
            this.waiting = new AtomicInteger();
        }
        
        @Override
        public void onCompleted() {
        }
        
        @Override
        public void onError(final Throwable t) {
        }
        
        @Override
        public void onNext(Notification<? extends T> notification) {
            if (this.waiting.getAndSet(0) == 1 || !notification.isOnNext()) {
                while (!this.buf.offer(notification)) {
                    final Notification<? extends T> notification2 = this.buf.poll();
                    if (notification2 != null && !notification2.isOnNext()) {
                        notification = notification2;
                    }
                }
            }
        }
        
        void setWaiting(final int n) {
            this.waiting.set(n);
        }
        
        public Notification<? extends T> takeNext() throws InterruptedException {
            this.setWaiting(1);
            return this.buf.take();
        }
    }
}
