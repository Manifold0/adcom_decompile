// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2.adapter.rxjava;

import rx.exceptions.Exceptions;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable$OnSubscribe;

final class CallExecuteOnSubscribe<T> implements Observable$OnSubscribe<Response<T>>
{
    private final Call<T> originalCall;
    
    CallExecuteOnSubscribe(final Call<T> originalCall) {
        this.originalCall = originalCall;
    }
    
    public void call(final Subscriber<? super Response<T>> subscriber) {
        final Call clone = this.originalCall.clone();
        final CallArbiter producer = new CallArbiter((retrofit2.Call<Object>)clone, (rx.Subscriber<? super retrofit2.Response<Object>>)subscriber);
        subscriber.add((Subscription)producer);
        subscriber.setProducer((Producer)producer);
        try {
            producer.emitResponse(clone.execute());
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            producer.emitError(t);
        }
    }
}
