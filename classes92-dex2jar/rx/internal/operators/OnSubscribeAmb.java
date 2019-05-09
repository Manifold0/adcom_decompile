// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Producer;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscriber;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import rx.Observable;

public final class OnSubscribeAmb<T> implements OnSubscribe<T>
{
    final Iterable<? extends Observable<? extends T>> sources;
    
    private OnSubscribeAmb(final Iterable<? extends Observable<? extends T>> sources) {
        this.sources = sources;
    }
    
    public static <T> OnSubscribe<T> amb(final Iterable<? extends Observable<? extends T>> iterable) {
        return new OnSubscribeAmb<T>(iterable);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        list.add(observable4);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        list.add(observable4);
        list.add(observable5);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        list.add(observable4);
        list.add(observable5);
        list.add(observable6);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        list.add(observable4);
        list.add(observable5);
        list.add(observable6);
        list.add(observable7);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        list.add(observable4);
        list.add(observable5);
        list.add(observable6);
        list.add(observable7);
        list.add(observable8);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    public static <T> OnSubscribe<T> amb(final Observable<? extends T> observable, final Observable<? extends T> observable2, final Observable<? extends T> observable3, final Observable<? extends T> observable4, final Observable<? extends T> observable5, final Observable<? extends T> observable6, final Observable<? extends T> observable7, final Observable<? extends T> observable8, final Observable<? extends T> observable9) {
        final ArrayList<Observable<? extends T>> list = new ArrayList<Observable<? extends T>>();
        list.add(observable);
        list.add(observable2);
        list.add(observable3);
        list.add(observable4);
        list.add(observable5);
        list.add(observable6);
        list.add(observable7);
        list.add(observable8);
        list.add(observable9);
        return (OnSubscribe<T>)amb((Iterable<? extends Observable<?>>)list);
    }
    
    static <T> void unsubscribeAmbSubscribers(final Collection<AmbSubscriber<T>> collection) {
        if (!collection.isEmpty()) {
            final Iterator<AmbSubscriber<T>> iterator = collection.iterator();
            while (iterator.hasNext()) {
                iterator.next().unsubscribe();
            }
            collection.clear();
        }
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final Selection<AmbSubscriber> selection = new Selection<AmbSubscriber>();
        subscriber.add(Subscriptions.create(new Action0() {
            @Override
            public void call() {
                final AmbSubscriber ambSubscriber = (AmbSubscriber)selection.get();
                if (ambSubscriber != null) {
                    ambSubscriber.unsubscribe();
                }
                OnSubscribeAmb.unsubscribeAmbSubscribers(selection.ambSubscribers);
            }
        }));
        for (final Observable observable : this.sources) {
            if (subscriber.isUnsubscribed()) {
                break;
            }
            final AmbSubscriber ambSubscriber = new AmbSubscriber(0L, (Subscriber<? super Object>)subscriber, (Selection<Object>)selection);
            selection.ambSubscribers.add((AmbSubscriber<AmbSubscriber>)ambSubscriber);
            final AmbSubscriber ambSubscriber2 = selection.get();
            if (ambSubscriber2 != null) {
                selection.unsubscribeOthers(ambSubscriber2);
                return;
            }
            observable.unsafeSubscribe(ambSubscriber);
        }
        if (subscriber.isUnsubscribed()) {
            unsubscribeAmbSubscribers((Collection<AmbSubscriber<T>>)selection.ambSubscribers);
        }
        subscriber.setProducer(new Producer() {
            @Override
            public void request(final long n) {
                final AmbSubscriber ambSubscriber = (AmbSubscriber)selection.get();
                if (ambSubscriber != null) {
                    ambSubscriber.requestMore(n);
                }
                else {
                    for (final AmbSubscriber<Object> ambSubscriber2 : selection.ambSubscribers) {
                        if (!ambSubscriber2.isUnsubscribed()) {
                            if (selection.get() == ambSubscriber2) {
                                ambSubscriber2.requestMore(n);
                                return;
                            }
                            ambSubscriber2.requestMore(n);
                        }
                    }
                }
            }
        });
    }
    
    static final class AmbSubscriber<T> extends Subscriber<T>
    {
        private boolean chosen;
        private final Selection<T> selection;
        private final Subscriber<? super T> subscriber;
        
        AmbSubscriber(final long n, final Subscriber<? super T> subscriber, final Selection<T> selection) {
            this.subscriber = subscriber;
            this.selection = selection;
            this.request(n);
        }
        
        private boolean isSelected() {
            if (this.chosen) {
                return true;
            }
            if (this.selection.get() == this) {
                return this.chosen = true;
            }
            if (this.selection.compareAndSet(null, (AmbSubscriber<T>)this)) {
                this.selection.unsubscribeOthers(this);
                return this.chosen = true;
            }
            this.selection.unsubscribeLosers();
            return false;
        }
        
        private void requestMore(final long n) {
            this.request(n);
        }
        
        @Override
        public void onCompleted() {
            if (this.isSelected()) {
                this.subscriber.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            if (this.isSelected()) {
                this.subscriber.onError(t);
            }
        }
        
        @Override
        public void onNext(final T t) {
            if (this.isSelected()) {
                this.subscriber.onNext((Object)t);
            }
        }
    }
    
    static final class Selection<T> extends AtomicReference<AmbSubscriber<T>>
    {
        final Collection<AmbSubscriber<T>> ambSubscribers;
        
        Selection() {
            this.ambSubscribers = new ConcurrentLinkedQueue<AmbSubscriber<T>>();
        }
        
        public void unsubscribeLosers() {
            final AmbSubscriber ambSubscriber = this.get();
            if (ambSubscriber != null) {
                this.unsubscribeOthers(ambSubscriber);
            }
        }
        
        public void unsubscribeOthers(final AmbSubscriber<T> ambSubscriber) {
            for (final AmbSubscriber<T> ambSubscriber2 : this.ambSubscribers) {
                if (ambSubscriber2 != ambSubscriber) {
                    ambSubscriber2.unsubscribe();
                }
            }
            this.ambSubscribers.clear();
        }
    }
}
