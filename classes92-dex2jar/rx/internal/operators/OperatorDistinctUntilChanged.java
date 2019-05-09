// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.Subscriber;
import rx.internal.util.UtilityFunctions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.Observable;

public final class OperatorDistinctUntilChanged<T, U> implements Operator<T, T>, Func2<U, U, Boolean>
{
    final Func2<? super U, ? super U, Boolean> comparator;
    final Func1<? super T, ? extends U> keySelector;
    
    public OperatorDistinctUntilChanged(final Func1<? super T, ? extends U> keySelector) {
        this.keySelector = keySelector;
        this.comparator = this;
    }
    
    public OperatorDistinctUntilChanged(final Func2<? super U, ? super U, Boolean> comparator) {
        this.keySelector = (Func1<? super T, ? extends U>)UtilityFunctions.identity();
        this.comparator = comparator;
    }
    
    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return (OperatorDistinctUntilChanged<T, T>)Holder.INSTANCE;
    }
    
    @Override
    public Boolean call(final U u, final U u2) {
        return u == u2 || (u != null && u.equals(u2));
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) {
            boolean hasPrevious;
            U previousKey;
            final /* synthetic */ OperatorDistinctUntilChanged this$0;
            
            @Override
            public void onCompleted() {
                subscriber.onCompleted();
            }
            
            @Override
            public void onError(final Throwable t) {
                subscriber.onError(t);
            }
            
            @Override
            public void onNext(final T t) {
                Label_0095: {
                    Label_0089: {
                        U call = null;
                        U previousKey;
                        try {
                            call = (U)OperatorDistinctUntilChanged.this.keySelector.call((Object)t);
                            previousKey = this.previousKey;
                            this.previousKey = call;
                            if (!this.hasPrevious) {
                                break Label_0095;
                            }
                            final Subscriber<T> subscriber = this;
                            final OperatorDistinctUntilChanged operatorDistinctUntilChanged = subscriber.this$0;
                            final Func2<? super U, ? super U, Boolean> func2 = operatorDistinctUntilChanged.comparator;
                            final U u = previousKey;
                            final U u2 = call;
                            final Boolean b = func2.call(u, u2);
                            final Boolean b2 = b;
                            final boolean booleanValue = b2;
                            final boolean booleanValue2 = booleanValue;
                            if (!booleanValue2) {
                                final Subscriber<T> subscriber2 = this;
                                final Subscriber subscriber3 = subscriber;
                                final T t2 = t;
                                subscriber3.onNext(t2);
                                return;
                            }
                            break Label_0089;
                        }
                        catch (Throwable call) {
                            Exceptions.throwOrReport((Throwable)call, subscriber, t);
                            return;
                        }
                        try {
                            final Subscriber<T> subscriber = this;
                            final OperatorDistinctUntilChanged operatorDistinctUntilChanged = subscriber.this$0;
                            final Func2<? super U, ? super U, Boolean> func2 = operatorDistinctUntilChanged.comparator;
                            final U u = previousKey;
                            final U u2 = call;
                            final Boolean b = func2.call(u, u2);
                            final Boolean b2 = b;
                            final boolean booleanValue2;
                            final boolean booleanValue = booleanValue2 = b2;
                            if (!booleanValue2) {
                                final Subscriber<T> subscriber2 = this;
                                final Subscriber subscriber3 = subscriber;
                                final T t2 = t;
                                subscriber3.onNext(t2);
                                return;
                            }
                        }
                        catch (Throwable t3) {
                            Exceptions.throwOrReport(t3, subscriber, call);
                            return;
                        }
                    }
                    this.request(1L);
                    return;
                }
                this.hasPrevious = true;
                subscriber.onNext(t);
            }
        };
    }
    
    static final class Holder
    {
        static final OperatorDistinctUntilChanged<?, ?> INSTANCE;
        
        static {
            INSTANCE = new OperatorDistinctUntilChanged<Object, Object>(UtilityFunctions.identity());
        }
    }
}
