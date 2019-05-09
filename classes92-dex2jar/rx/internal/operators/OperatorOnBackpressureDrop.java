// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;
import rx.Subscriber;
import rx.functions.Action1;
import rx.Observable;

public class OperatorOnBackpressureDrop<T> implements Operator<T, T>
{
    final Action1<? super T> onDrop;
    
    OperatorOnBackpressureDrop() {
        this(null);
    }
    
    public OperatorOnBackpressureDrop(final Action1<? super T> onDrop) {
        this.onDrop = onDrop;
    }
    
    public static <T> OperatorOnBackpressureDrop<T> instance() {
        return (OperatorOnBackpressureDrop<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final AtomicLong atomicLong = new AtomicLong();
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                BackpressureUtils.getAndAddRequest(atomicLong, n);
            }
        });
        return new Subscriber<T>(subscriber) {
            boolean done;
            
            @Override
            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    subscriber.onCompleted();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (!this.done) {
                    this.done = true;
                    subscriber.onError(t);
                    return;
                }
                RxJavaHooks.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                if (!this.done) {
                    if (atomicLong.get() > 0L) {
                        subscriber.onNext(t);
                        atomicLong.decrementAndGet();
                        return;
                    }
                    if (OperatorOnBackpressureDrop.this.onDrop != null) {
                        try {
                            OperatorOnBackpressureDrop.this.onDrop.call((Object)t);
                        }
                        catch (Throwable t2) {
                            Exceptions.throwOrReport(t2, this, t);
                        }
                    }
                }
            }
            
            @Override
            public void onStart() {
                this.request(Long.MAX_VALUE);
            }
        };
    }
    
    static final class Holder
    {
        static final OperatorOnBackpressureDrop<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorOnBackpressureDrop<Object>();
        }
    }
}
