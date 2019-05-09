// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.Exceptions;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.CompletableSubscriber;
import rx.Single;
import rx.functions.Func1;
import rx.Completable;

public final class CompletableFlatMapSingleToCompletable<T> implements OnSubscribe
{
    final Func1<? super T, ? extends Completable> mapper;
    final Single<T> source;
    
    public CompletableFlatMapSingleToCompletable(final Single<T> source, final Func1<? super T, ? extends Completable> mapper) {
        this.source = source;
        this.mapper = mapper;
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        final SourceSubscriber<Object> sourceSubscriber = new SourceSubscriber<Object>(completableSubscriber, (Func1<? super Object, ? extends Completable>)this.mapper);
        completableSubscriber.onSubscribe(sourceSubscriber);
        this.source.subscribe(sourceSubscriber);
    }
    
    static final class SourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber
    {
        final CompletableSubscriber actual;
        final Func1<? super T, ? extends Completable> mapper;
        
        public SourceSubscriber(final CompletableSubscriber actual, final Func1<? super T, ? extends Completable> mapper) {
            this.actual = actual;
            this.mapper = mapper;
        }
        
        @Override
        public void onCompleted() {
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.actual.onError(t);
        }
        
        @Override
        public void onSubscribe(final Subscription subscription) {
            this.add(subscription);
        }
        
        @Override
        public void onSuccess(final T t) {
            Completable completable;
            try {
                completable = (Completable)this.mapper.call((Object)t);
                if (completable == null) {
                    this.onError(new NullPointerException("The mapper returned a null Completable"));
                    return;
                }
            }
            catch (Throwable t2) {
                Exceptions.throwIfFatal(t2);
                this.onError(t2);
                return;
            }
            completable.subscribe(this);
        }
    }
}
