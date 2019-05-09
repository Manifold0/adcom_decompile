// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.AssemblyStackTraceException;
import rx.Subscription;
import rx.SingleSubscriber;
import rx.Single;

public final class OnSubscribeOnAssemblySingle<T> implements OnSubscribe<T>
{
    public static volatile boolean fullStackTrace;
    final OnSubscribe<T> source;
    final String stacktrace;
    
    public OnSubscribeOnAssemblySingle(final OnSubscribe<T> source) {
        this.source = source;
        this.stacktrace = OnSubscribeOnAssembly.createStacktrace();
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        this.source.call(new OnAssemblySingleSubscriber<Object>((SingleSubscriber<? super Object>)singleSubscriber, this.stacktrace));
    }
    
    static final class OnAssemblySingleSubscriber<T> extends SingleSubscriber<T>
    {
        final SingleSubscriber<? super T> actual;
        final String stacktrace;
        
        public OnAssemblySingleSubscriber(final SingleSubscriber<? super T> actual, final String stacktrace) {
            this.actual = actual;
            this.stacktrace = stacktrace;
            actual.add(this);
        }
        
        @Override
        public void onError(final Throwable t) {
            new AssemblyStackTraceException(this.stacktrace).attachTo(t);
            this.actual.onError(t);
        }
        
        @Override
        public void onSuccess(final T t) {
            this.actual.onSuccess((Object)t);
        }
    }
}
