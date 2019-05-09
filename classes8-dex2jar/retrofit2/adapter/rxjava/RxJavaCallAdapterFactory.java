// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import retrofit2.Response;
import java.lang.reflect.ParameterizedType;
import rx.Observable;
import rx.Single;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import rx.Scheduler;
import retrofit2.CallAdapter$Factory;

public final class RxJavaCallAdapterFactory extends CallAdapter$Factory
{
    private final boolean isAsync;
    private final Scheduler scheduler;
    
    private RxJavaCallAdapterFactory(final Scheduler scheduler, final boolean isAsync) {
        this.scheduler = scheduler;
        this.isAsync = isAsync;
    }
    
    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory(null, false);
    }
    
    public static RxJavaCallAdapterFactory createAsync() {
        return new RxJavaCallAdapterFactory(null, true);
    }
    
    public static RxJavaCallAdapterFactory createWithScheduler(final Scheduler scheduler) {
        if (scheduler == null) {
            throw new NullPointerException("scheduler == null");
        }
        return new RxJavaCallAdapterFactory(scheduler, false);
    }
    
    public CallAdapter<?, ?> get(Type type, final Annotation[] array, final Retrofit retrofit) {
        final Class rawType = getRawType(type);
        final boolean b = rawType == Single.class;
        final boolean equals = "rx.Completable".equals(rawType.getCanonicalName());
        if (rawType != Observable.class && !b && !equals) {
            return null;
        }
        if (equals) {
            return (CallAdapter<?, ?>)new RxJavaCallAdapter(Void.class, this.scheduler, this.isAsync, false, true, false, true);
        }
        boolean b2 = false;
        boolean b3 = false;
        if (!(type instanceof ParameterizedType)) {
            String s;
            if (b) {
                s = "Single";
            }
            else {
                s = "Observable";
            }
            throw new IllegalStateException(s + " return type must be parameterized as " + s + "<Foo> or " + s + "<? extends Foo>");
        }
        type = getParameterUpperBound(0, (ParameterizedType)type);
        final Class rawType2 = getRawType(type);
        if (rawType2 == Response.class) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            type = getParameterUpperBound(0, (ParameterizedType)type);
        }
        else if (rawType2 == Result.class) {
            if (!(type instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            type = getParameterUpperBound(0, (ParameterizedType)type);
            b2 = true;
        }
        else {
            b3 = true;
        }
        return (CallAdapter<?, ?>)new RxJavaCallAdapter(type, this.scheduler, this.isAsync, b2, b3, b, false);
    }
}
