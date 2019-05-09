package rx.observers;

import rx.Observer;
import rx.Subscriber;

public class SerializedSubscriber<T> extends Subscriber<T> {
    /* renamed from: s */
    private final Observer<T> f8391s;

    public SerializedSubscriber(Subscriber<? super T> s) {
        this(s, true);
    }

    public SerializedSubscriber(Subscriber<? super T> s, boolean shareSubscriptions) {
        super(s, shareSubscriptions);
        this.f8391s = new SerializedObserver(s);
    }

    public void onCompleted() {
        this.f8391s.onCompleted();
    }

    public void onError(Throwable e) {
        this.f8391s.onError(e);
    }

    public void onNext(T t) {
        this.f8391s.onNext(t);
    }
}
