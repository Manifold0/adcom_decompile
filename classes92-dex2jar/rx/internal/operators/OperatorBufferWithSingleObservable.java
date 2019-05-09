// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.ArrayList;
import rx.observers.Subscribers;
import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func0;
import java.util.List;
import rx.Observable;

public final class OperatorBufferWithSingleObservable<T, TClosing> implements Operator<List<T>, T>
{
    final Func0<? extends Observable<? extends TClosing>> bufferClosingSelector;
    final int initialCapacity;
    
    public OperatorBufferWithSingleObservable(final Observable<? extends TClosing> observable, final int initialCapacity) {
        this.bufferClosingSelector = new Func0<Observable<? extends TClosing>>() {
            @Override
            public Observable<? extends TClosing> call() {
                return observable;
            }
        };
        this.initialCapacity = initialCapacity;
    }
    
    public OperatorBufferWithSingleObservable(final Func0<? extends Observable<? extends TClosing>> bufferClosingSelector, final int initialCapacity) {
        this.bufferClosingSelector = bufferClosingSelector;
        this.initialCapacity = initialCapacity;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        try {
            final Observable observable = (Observable)this.bufferClosingSelector.call();
            final BufferingSubscriber bufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber<Object>(subscriber));
            final Subscriber<TClosing> subscriber2 = new Subscriber<TClosing>() {
                @Override
                public void onCompleted() {
                    bufferingSubscriber.onCompleted();
                }
                
                @Override
                public void onError(final Throwable t) {
                    bufferingSubscriber.onError(t);
                }
                
                @Override
                public void onNext(final TClosing tClosing) {
                    bufferingSubscriber.emit();
                }
            };
            subscriber.add(subscriber2);
            subscriber.add(bufferingSubscriber);
            observable.unsafeSubscribe(subscriber2);
            return bufferingSubscriber;
        }
        catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
            return Subscribers.empty();
        }
    }
    
    final class BufferingSubscriber extends Subscriber<T>
    {
        final Subscriber<? super List<T>> child;
        List<T> chunk;
        boolean done;
        
        public BufferingSubscriber(final Subscriber<? super List<T>> child) {
            this.child = child;
            this.chunk = new ArrayList<T>(OperatorBufferWithSingleObservable.this.initialCapacity);
        }
        
        void emit() {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                final List<T> chunk = this.chunk;
                this.chunk = new ArrayList<T>(OperatorBufferWithSingleObservable.this.initialCapacity);
                // monitorexit(this)
                try {
                    this.child.onNext(chunk);
                    return;
                }
                catch (Throwable t2) {
                    this.unsubscribe();
                    synchronized (this) {
                        if (this.done) {
                            return;
                        }
                    }
                }
            }
            this.done = true;
            // monitorexit(this)
            final Throwable t;
            Exceptions.throwOrReport(t, this.child);
        }
        
        @Override
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    final List<T> chunk = this.chunk;
                    this.chunk = null;
                    // monitorexit(this)
                    this.child.onNext(chunk);
                    this.child.onCompleted();
                    this.unsubscribe();
                }
            }
            catch (Throwable t) {
                Exceptions.throwOrReport(t, this.child);
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunk = null;
                // monitorexit(this)
                this.child.onError(t);
                this.unsubscribe();
            }
        }
        
        @Override
        public void onNext(final T t) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunk.add(t);
            }
        }
    }
}
