// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Action0;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OnSubscribeSkipTimed<T> implements OnSubscribe<T>
{
    final Scheduler scheduler;
    final Observable<T> source;
    final long time;
    final TimeUnit unit;
    
    public OnSubscribeSkipTimed(final Observable<T> source, final long time, final TimeUnit unit, final Scheduler scheduler) {
        this.source = source;
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        final SkipTimedSubscriber<Object> skipTimedSubscriber = new SkipTimedSubscriber<Object>((Subscriber<? super Object>)subscriber);
        skipTimedSubscriber.add(worker);
        subscriber.add(skipTimedSubscriber);
        worker.schedule(skipTimedSubscriber, this.time, this.unit);
        this.source.unsafeSubscribe(skipTimedSubscriber);
    }
    
    static final class SkipTimedSubscriber<T> extends Subscriber<T> implements Action0
    {
        final Subscriber<? super T> child;
        volatile boolean gate;
        
        SkipTimedSubscriber(final Subscriber<? super T> child) {
            this.child = child;
        }
        
        @Override
        public void call() {
            this.gate = true;
        }
        
        @Override
        public void onCompleted() {
            try {
                this.child.onCompleted();
            }
            finally {
                this.unsubscribe();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            try {
                this.child.onError(t);
            }
            finally {
                this.unsubscribe();
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (this.gate) {
                this.child.onNext((Object)t);
            }
        }
    }
}
