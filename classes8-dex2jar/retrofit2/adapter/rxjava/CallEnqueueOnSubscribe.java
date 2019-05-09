// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import rx.exceptions.Exceptions;
import retrofit2.Callback;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable$OnSubscribe;

final class CallEnqueueOnSubscribe<T> implements Observable$OnSubscribe<Response<T>>
{
    private final Call<T> originalCall;
    
    CallEnqueueOnSubscribe(final Call<T> originalCall) {
        this.originalCall = originalCall;
    }
    
    public void call(final Subscriber<? super Response<T>> subscriber) {
        final Call clone = this.originalCall.clone();
        final CallArbiter producer = new CallArbiter((retrofit2.Call<Object>)clone, (rx.Subscriber<? super retrofit2.Response<Object>>)subscriber);
        subscriber.add((Subscription)producer);
        subscriber.setProducer((Producer)producer);
        clone.enqueue((Callback)new Callback<T>() {
            public void onFailure(final Call<T> call, final Throwable t) {
                Exceptions.throwIfFatal(t);
                producer.emitError(t);
            }
            
            public void onResponse(final Call<T> call, final Response<T> response) {
                producer.emitResponse(response);
            }
        });
    }
}
