// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import rx.Single;
import rx.Observable;
import retrofit2.Response;
import rx.Observable$OnSubscribe;
import retrofit2.Call;
import rx.Scheduler;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;

final class RxJavaCallAdapter<R> implements CallAdapter<R, Object>
{
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    private final Scheduler scheduler;
    
    RxJavaCallAdapter(final Type responseType, final Scheduler scheduler, final boolean isAsync, final boolean isResult, final boolean isBody, final boolean isSingle, final boolean isCompletable) {
        this.responseType = responseType;
        this.scheduler = scheduler;
        this.isAsync = isAsync;
        this.isResult = isResult;
        this.isBody = isBody;
        this.isSingle = isSingle;
        this.isCompletable = isCompletable;
    }
    
    public Object adapt(final Call<R> call) {
        Object o;
        if (this.isAsync) {
            o = new CallEnqueueOnSubscribe((retrofit2.Call<Object>)call);
        }
        else {
            o = new CallExecuteOnSubscribe((retrofit2.Call<Object>)call);
        }
        if (this.isResult) {
            o = new ResultOnSubscribe((rx.Observable$OnSubscribe<retrofit2.Response<Object>>)o);
        }
        else if (this.isBody) {
            o = new BodyOnSubscribe((rx.Observable$OnSubscribe<retrofit2.Response<Object>>)o);
        }
        Observable observable2;
        final Observable observable = observable2 = Observable.create((Observable$OnSubscribe)o);
        if (this.scheduler != null) {
            observable2 = observable.subscribeOn(this.scheduler);
        }
        Single single;
        if (this.isSingle) {
            single = observable2.toSingle();
        }
        else {
            single = (Single)observable2;
            if (this.isCompletable) {
                return CompletableHelper.toCompletable((Observable<?>)observable2);
            }
        }
        return single;
    }
    
    public Type responseType() {
        return this.responseType;
    }
    
    private static final class CompletableHelper
    {
        static Object toCompletable(final Observable<?> observable) {
            return observable.toCompletable();
        }
    }
}
