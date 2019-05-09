// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.exceptions.AssemblyStackTraceException;
import rx.Subscriber;
import rx.Observable;

public final class OnSubscribeOnAssembly<T> implements OnSubscribe<T>
{
    public static volatile boolean fullStackTrace;
    final OnSubscribe<T> source;
    final String stacktrace;
    
    public OnSubscribeOnAssembly(final OnSubscribe<T> source) {
        this.source = source;
        this.stacktrace = createStacktrace();
    }
    
    static String createStacktrace() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final StringBuilder sb = new StringBuilder("Assembly trace:");
        for (int length = stackTrace.length, i = 0; i < length; ++i) {
            final StackTraceElement stackTraceElement = stackTrace[i];
            final String string = stackTraceElement.toString();
            if (OnSubscribeOnAssembly.fullStackTrace || (stackTraceElement.getLineNumber() > 1 && !string.contains("RxJavaHooks.") && !string.contains("OnSubscribeOnAssembly") && !string.contains(".junit.runner") && !string.contains(".junit4.runner") && !string.contains(".junit.internal") && !string.contains("sun.reflect") && !string.contains("java.lang.Thread.") && !string.contains("ThreadPoolExecutor") && !string.contains("org.apache.catalina.") && !string.contains("org.apache.tomcat."))) {
                sb.append("\n at ").append(string);
            }
        }
        return sb.append("\nOriginal exception:").toString();
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        this.source.call(new OnAssemblySubscriber<Object>((Subscriber<? super Object>)subscriber, this.stacktrace));
    }
    
    static final class OnAssemblySubscriber<T> extends Subscriber<T>
    {
        final Subscriber<? super T> actual;
        final String stacktrace;
        
        public OnAssemblySubscriber(final Subscriber<? super T> actual, final String stacktrace) {
            super(actual);
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
        public void onNext(final T t) {
            this.actual.onNext((Object)t);
        }
    }
}
