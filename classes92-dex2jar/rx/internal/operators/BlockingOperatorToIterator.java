// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.exceptions.Exceptions;
import java.util.concurrent.LinkedBlockingQueue;
import rx.internal.util.RxRingBuffer;
import java.util.concurrent.BlockingQueue;
import rx.Notification;
import rx.Subscriber;
import java.util.Iterator;
import rx.Observable;

public final class BlockingOperatorToIterator
{
    private BlockingOperatorToIterator() {
        throw new IllegalStateException("No instances!");
    }
    
    public static <T> Iterator<T> toIterator(final Observable<? extends T> observable) {
        final SubscriberIterator subscriberIterator = new SubscriberIterator<Object>();
        observable.materialize().subscribe((Subscriber<? super Notification<? extends T>>)subscriberIterator);
        return (Iterator<T>)subscriberIterator;
    }
    
    public static final class SubscriberIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T>
    {
        static final int LIMIT;
        private Notification<? extends T> buf;
        private final BlockingQueue<Notification<? extends T>> notifications;
        private int received;
        
        static {
            LIMIT = RxRingBuffer.SIZE * 3 / 4;
        }
        
        public SubscriberIterator() {
            this.notifications = new LinkedBlockingQueue<Notification<? extends T>>();
        }
        
        private Notification<? extends T> take() {
            try {
                final Notification<? extends T> notification = this.notifications.poll();
                if (notification != null) {
                    return notification;
                }
                return this.notifications.take();
            }
            catch (InterruptedException ex) {
                this.unsubscribe();
                throw Exceptions.propagate(ex);
            }
        }
        
        @Override
        public boolean hasNext() {
            boolean b = false;
            if (this.buf == null) {
                this.buf = this.take();
                ++this.received;
                if (this.received >= SubscriberIterator.LIMIT) {
                    this.request(this.received);
                    this.received = 0;
                }
            }
            if (this.buf.isOnError()) {
                throw Exceptions.propagate(this.buf.getThrowable());
            }
            if (!this.buf.isOnCompleted()) {
                b = true;
            }
            return b;
        }
        
        @Override
        public T next() {
            if (this.hasNext()) {
                final T value = (T)this.buf.getValue();
                this.buf = null;
                return value;
            }
            throw new NoSuchElementException();
        }
        
        @Override
        public void onCompleted() {
        }
        
        @Override
        public void onError(final Throwable t) {
            this.notifications.offer(Notification.createOnError(t));
        }
        
        @Override
        public void onNext(final Notification<? extends T> notification) {
            this.notifications.offer(notification);
        }
        
        @Override
        public void onStart() {
            this.request(RxRingBuffer.SIZE);
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator");
        }
    }
}
