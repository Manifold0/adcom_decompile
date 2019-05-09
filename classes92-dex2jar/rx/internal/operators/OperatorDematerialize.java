// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscriber;
import rx.Notification;
import rx.Observable;

public final class OperatorDematerialize<T> implements Operator<T, Notification<T>>
{
    OperatorDematerialize() {
    }
    
    public static OperatorDematerialize instance() {
        return Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super Notification<T>> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<Notification<T>>(subscriber) {
            boolean terminated;
            
            @Override
            public void onCompleted() {
                if (!this.terminated) {
                    this.terminated = true;
                    subscriber.onCompleted();
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                if (!this.terminated) {
                    this.terminated = true;
                    subscriber.onError(t);
                }
            }
            
            @Override
            public void onNext(final Notification<T> notification) {
                switch (notification.getKind()) {
                    default: {
                        this.onError(new IllegalArgumentException("Unsupported notification type: " + notification));
                        break;
                    }
                    case OnNext: {
                        if (!this.terminated) {
                            subscriber.onNext(notification.getValue());
                            return;
                        }
                        break;
                    }
                    case OnError: {
                        this.onError(notification.getThrowable());
                    }
                    case OnCompleted: {
                        this.onCompleted();
                    }
                }
            }
        };
    }
    
    static final class Holder
    {
        static final OperatorDematerialize<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorDematerialize<Object>();
        }
    }
}
