// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Iterator;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Observer;
import rx.observers.Subscribers;
import rx.functions.Action1;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.functions.Func0;
import rx.Observable;
import rx.Subscription;
import rx.subjects.Subject;
import java.util.concurrent.atomic.AtomicReference;
import rx.observables.ConnectableObservable;

public final class OperatorMulticast<T, R> extends ConnectableObservable<R>
{
    final AtomicReference<Subject<? super T, ? extends R>> connectedSubject;
    final Object guard;
    Subscription guardedSubscription;
    final Observable<? extends T> source;
    final Func0<? extends Subject<? super T, ? extends R>> subjectFactory;
    Subscriber<T> subscription;
    final List<Subscriber<? super R>> waitingForConnect;
    
    private OperatorMulticast(final Object guard, final AtomicReference<Subject<? super T, ? extends R>> connectedSubject, final List<Subscriber<? super R>> waitingForConnect, final Observable<? extends T> source, final Func0<? extends Subject<? super T, ? extends R>> subjectFactory) {
        super(new OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                synchronized (guard) {
                    if (connectedSubject.get() == null) {
                        waitingForConnect.add(subscriber);
                    }
                    else {
                        connectedSubject.get().unsafeSubscribe(subscriber);
                    }
                }
            }
        });
        this.guard = guard;
        this.connectedSubject = connectedSubject;
        this.waitingForConnect = waitingForConnect;
        this.source = source;
        this.subjectFactory = subjectFactory;
    }
    
    public OperatorMulticast(final Observable<? extends T> observable, final Func0<? extends Subject<? super T, ? extends R>> func0) {
        this(new Object(), (AtomicReference)new AtomicReference(), (List)new ArrayList(), observable, func0);
    }
    
    @Override
    public void connect(final Action1<? super Subscription> action1) {
        final Subject subject;
        synchronized (this.guard) {
            if (this.subscription != null) {
                action1.call(this.guardedSubscription);
                return;
            }
            subject = (Subject)this.subjectFactory.call();
            this.subscription = Subscribers.from((Observer<? super T>)subject);
            final AtomicReference<Subscription> atomicReference = new AtomicReference<Subscription>();
            atomicReference.set(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    synchronized (OperatorMulticast.this.guard) {
                        if (OperatorMulticast.this.guardedSubscription == atomicReference.get()) {
                            final Subscriber<T> subscription = OperatorMulticast.this.subscription;
                            OperatorMulticast.this.subscription = null;
                            OperatorMulticast.this.guardedSubscription = null;
                            OperatorMulticast.this.connectedSubject.set(null);
                            // monitorexit(this.this$0.guard)
                            if (subscription != null) {
                                subscription.unsubscribe();
                            }
                        }
                    }
                }
            }));
            this.guardedSubscription = atomicReference.get();
            for (final Subscriber<? super Object> subscriber : this.waitingForConnect) {
                subject.unsafeSubscribe(new Subscriber<R>(subscriber) {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }
                    
                    @Override
                    public void onError(final Throwable t) {
                        subscriber.onError(t);
                    }
                    
                    @Override
                    public void onNext(final R r) {
                        subscriber.onNext(r);
                    }
                });
            }
        }
        this.waitingForConnect.clear();
        this.connectedSubject.set(subject);
        // monitorexit(o)
        final Action1<Subscription> action2;
        action2.call(this.guardedSubscription);
        synchronized (this.guard) {
            final Subscriber<T> subscription = this.subscription;
            // monitorexit(this.guard)
            if (subscription != null) {
                this.source.subscribe(subscription);
            }
        }
    }
}
