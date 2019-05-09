// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Action1;
import rx.Subscription;
import rx.exceptions.AssemblyStackTraceException;
import rx.CompletableSubscriber;
import rx.Completable;

public final class OnSubscribeOnAssemblyCompletable<T> implements OnSubscribe
{
    public static volatile boolean fullStackTrace;
    final OnSubscribe source;
    final String stacktrace;
    
    public OnSubscribeOnAssemblyCompletable(final OnSubscribe source) {
        this.source = source;
        this.stacktrace = OnSubscribeOnAssembly.createStacktrace();
    }
    
    @Override
    public void call(final CompletableSubscriber completableSubscriber) {
        ((Action1<OnAssemblyCompletableSubscriber>)this.source).call(new OnAssemblyCompletableSubscriber(completableSubscriber, this.stacktrace));
    }
    
    static final class OnAssemblyCompletableSubscriber implements CompletableSubscriber
    {
        final CompletableSubscriber actual;
        final String stacktrace;
        
        public OnAssemblyCompletableSubscriber(final CompletableSubscriber actual, final String stacktrace) {
            this.actual = actual;
            this.stacktrace = stacktrace;
        }
        
        @Override
        public void onCompleted() {
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(t);
            this.actual.onError(t);
        }
        
        @Override
        public void onSubscribe(final Subscription subscription) {
            this.actual.onSubscribe(subscription);
        }
    }
}
