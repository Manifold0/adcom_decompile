// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.SingleSubscriber;
import rx.functions.Func1;
import rx.Single;

public final class SingleOperatorOnErrorResumeNext<T> implements OnSubscribe<T>
{
    private final Single<? extends T> originalSingle;
    final Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError;
    
    private SingleOperatorOnErrorResumeNext(final Single<? extends T> originalSingle, final Func1<Throwable, ? extends Single<? extends T>> resumeFunctionInCaseOfError) {
        if (originalSingle == null) {
            throw new NullPointerException("originalSingle must not be null");
        }
        if (resumeFunctionInCaseOfError == null) {
            throw new NullPointerException("resumeFunctionInCaseOfError must not be null");
        }
        this.originalSingle = originalSingle;
        this.resumeFunctionInCaseOfError = resumeFunctionInCaseOfError;
    }
    
    public static <T> SingleOperatorOnErrorResumeNext<T> withFunction(final Single<? extends T> single, final Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new SingleOperatorOnErrorResumeNext<T>(single, func1);
    }
    
    public static <T> SingleOperatorOnErrorResumeNext<T> withOther(final Single<? extends T> single, final Single<? extends T> single2) {
        if (single2 == null) {
            throw new NullPointerException("resumeSingleInCaseOfError must not be null");
        }
        return new SingleOperatorOnErrorResumeNext<T>(single, new Func1<Throwable, Single<? extends T>>() {
            @Override
            public Single<? extends T> call(final Throwable t) {
                return single2;
            }
        });
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() {
            @Override
            public void onError(final Throwable t) {
                try {
                    ((Single)SingleOperatorOnErrorResumeNext.this.resumeFunctionInCaseOfError.call(t)).subscribe(singleSubscriber);
                }
                catch (Throwable t) {
                    Exceptions.throwOrReport(t, singleSubscriber);
                }
            }
            
            @Override
            public void onSuccess(final T t) {
                singleSubscriber.onSuccess(t);
            }
        };
        singleSubscriber.add(singleSubscriber2);
        this.originalSingle.subscribe(singleSubscriber2);
    }
}
