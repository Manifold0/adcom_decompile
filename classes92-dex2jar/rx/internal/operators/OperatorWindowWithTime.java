// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collection;
import java.util.LinkedList;
import rx.subjects.UnicastSubject;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
import java.util.List;
import rx.observers.SerializedObserver;
import rx.Observer;
import rx.Subscription;
import rx.Subscriber;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Observable;

public final class OperatorWindowWithTime<T> implements Operator<Observable<T>, T>
{
    static final Object NEXT_SUBJECT;
    static final NotificationLite<Object> NL;
    final Scheduler scheduler;
    final int size;
    final long timeshift;
    final long timespan;
    final TimeUnit unit;
    
    static {
        NEXT_SUBJECT = new Object();
        NL = NotificationLite.instance();
    }
    
    public OperatorWindowWithTime(final long timespan, final long timeshift, final TimeUnit unit, final int size, final Scheduler scheduler) {
        this.timespan = timespan;
        this.timeshift = timeshift;
        this.unit = unit;
        this.size = size;
        this.scheduler = scheduler;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Observable<T>> subscriber) {
        final Scheduler.Worker worker = this.scheduler.createWorker();
        if (this.timespan == this.timeshift) {
            final ExactSubscriber exactSubscriber = new ExactSubscriber(subscriber, worker);
            exactSubscriber.add(worker);
            exactSubscriber.scheduleExact();
            return exactSubscriber;
        }
        final InexactSubscriber inexactSubscriber = new InexactSubscriber(subscriber, worker);
        inexactSubscriber.add(worker);
        inexactSubscriber.startNewChunk();
        inexactSubscriber.scheduleChunk();
        return inexactSubscriber;
    }
    
    static final class CountedSerializedSubject<T>
    {
        final Observer<T> consumer;
        int count;
        final Observable<T> producer;
        
        public CountedSerializedSubject(final Observer<T> observer, final Observable<T> producer) {
            this.consumer = new SerializedObserver<T>(observer);
            this.producer = producer;
        }
    }
    
    final class ExactSubscriber extends Subscriber<T>
    {
        final Subscriber<? super Observable<T>> child;
        boolean emitting;
        final Object guard;
        List<Object> queue;
        volatile State<T> state;
        final Scheduler.Worker worker;
        
        public ExactSubscriber(final Subscriber<? super Observable<T>> subscriber, final Scheduler.Worker worker) {
            this.child = new SerializedSubscriber<Object>(subscriber);
            this.worker = worker;
            this.guard = new Object();
            this.state = State.empty();
            subscriber.add(Subscriptions.create(new Action0() {
                @Override
                public void call() {
                    if (ExactSubscriber.this.state.consumer == null) {
                        ExactSubscriber.this.unsubscribe();
                    }
                }
            }));
        }
        
        void complete() {
            final Observer<T> consumer = this.state.consumer;
            this.state = this.state.clear();
            if (consumer != null) {
                consumer.onCompleted();
            }
            this.child.onCompleted();
            this.unsubscribe();
        }
        
        boolean drain(final List<Object> list) {
            if (list != null) {
                for (final T next : list) {
                    if (next == OperatorWindowWithTime.NEXT_SUBJECT) {
                        if (!this.replaceSubject()) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (OperatorWindowWithTime.NL.isError(next)) {
                            this.error(OperatorWindowWithTime.NL.getError(next));
                            return true;
                        }
                        if (OperatorWindowWithTime.NL.isCompleted(next)) {
                            this.complete();
                            return true;
                        }
                        if (!this.emitValue(next)) {
                            return false;
                        }
                        continue;
                    }
                }
            }
            return true;
        }
        
        boolean emitValue(final T t) {
            State<T> state;
            if ((state = this.state).consumer == null) {
                if (!this.replaceSubject()) {
                    return false;
                }
                state = this.state;
            }
            state.consumer.onNext(t);
            State<T> state2;
            if (state.count == OperatorWindowWithTime.this.size - 1) {
                state.consumer.onCompleted();
                state2 = state.clear();
            }
            else {
                state2 = state.next();
            }
            this.state = state2;
            return true;
        }
        
        void error(final Throwable t) {
            final Observer<T> consumer = this.state.consumer;
            this.state = this.state.clear();
            if (consumer != null) {
                consumer.onError(t);
            }
            this.child.onError(t);
            this.unsubscribe();
        }
        
        void nextWindow() {
            Object o = this.guard;
            Label_0114: {
                final boolean b;
                final boolean b2;
                int n;
                synchronized (o) {
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList<Object>();
                        }
                        this.queue.add(OperatorWindowWithTime.NEXT_SUBJECT);
                        return;
                    }
                    this.emitting = true;
                    // monitorexit(o)
                    b = false;
                    b2 = false;
                    n = (b ? 1 : 0);
                    final ExactSubscriber exactSubscriber = this;
                    final boolean replaceSubject = exactSubscriber.replaceSubject();
                    final boolean replaceSubject2 = replaceSubject;
                    if (replaceSubject2) {
                        break Label_0114;
                    }
                    final boolean b3 = false;
                    if (b3) {
                        return;
                    }
                    final ExactSubscriber exactSubscriber2 = this;
                    final Object o2 = exactSubscriber2.guard;
                    final Object o3;
                    o = (o3 = o2);
                    synchronized (o3) {
                        final ExactSubscriber exactSubscriber3 = this;
                        final boolean b4 = false;
                        exactSubscriber3.emitting = b4;
                        return;
                    }
                }
                try {
                    final ExactSubscriber exactSubscriber = this;
                    final boolean replaceSubject2;
                    final boolean replaceSubject = replaceSubject2 = exactSubscriber.replaceSubject();
                    if (!replaceSubject2) {
                        final boolean b3 = false;
                        if (!b3) {
                            final ExactSubscriber exactSubscriber2 = this;
                            final Object o2 = exactSubscriber2.guard;
                            final Object o3;
                            o = (o3 = o2);
                            // monitorenter(o3)
                            final ExactSubscriber exactSubscriber3 = this;
                            final boolean b4 = false;
                            exactSubscriber3.emitting = b4;
                        }
                    }
                    else {
                        while (true) {
                            n = (b ? 1 : 0);
                            o = this.guard;
                            n = (b ? 1 : 0);
                            // monitorenter(o)
                            n = (b2 ? 1 : 0);
                            try {
                                final List<Object> queue = this.queue;
                                if (queue == null) {
                                    n = (b2 ? 1 : 0);
                                    this.emitting = false;
                                    n = 1;
                                    // monitorexit(o)
                                    if (true) {
                                        break;
                                    }
                                    synchronized (this.guard) {
                                        this.emitting = false;
                                        return;
                                    }
                                }
                                n = (b2 ? 1 : 0);
                                this.queue = null;
                                n = (b2 ? 1 : 0);
                                // monitorexit(o)
                                n = (b ? 1 : 0);
                                if (this.drain(queue)) {
                                    continue;
                                }
                                if (false) {
                                    break;
                                }
                                synchronized (this.guard) {
                                    this.emitting = false;
                                }
                            }
                            finally {
                            }
                            // monitorexit(o)
                        }
                    }
                }
                finally {
                    Label_0270: {
                        if (n != 0) {
                            break Label_0270;
                        }
                        synchronized (this.guard) {
                            this.emitting = false;
                        }
                        // monitorexit(this.guard)
                    }
                }
            }
        }
        
        @Override
        public void onCompleted() {
            final Object guard = this.guard;
            final List<Object> queue;
            synchronized (guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList<Object>();
                    }
                    this.queue.add(OperatorWindowWithTime.NL.completed());
                    return;
                }
                queue = this.queue;
                this.queue = null;
                this.emitting = true;
                // monitorexit(guard)
                final ExactSubscriber exactSubscriber = this;
                final List<Object> list = queue;
                exactSubscriber.drain(list);
                final ExactSubscriber exactSubscriber2 = this;
                exactSubscriber2.complete();
                return;
            }
            try {
                final ExactSubscriber exactSubscriber = this;
                final List<Object> list = queue;
                exactSubscriber.drain(list);
                final ExactSubscriber exactSubscriber2 = this;
                exactSubscriber2.complete();
            }
            catch (Throwable t) {
                this.error(t);
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            synchronized (this.guard) {
                if (this.emitting) {
                    this.queue = Collections.singletonList(OperatorWindowWithTime.NL.error(t));
                    return;
                }
                this.queue = null;
                this.emitting = true;
                // monitorexit(this.guard)
                this.error(t);
            }
        }
        
        @Override
        public void onNext(T guard) {
            final Object guard2 = this.guard;
            // monitorenter(guard2)
            Label_0109: {
                boolean b;
                boolean b2;
                int n;
                try {
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList<Object>();
                        }
                        this.queue.add(guard);
                        return;
                    }
                    this.emitting = true;
                    // monitorexit(guard2)
                    b = false;
                    b2 = false;
                    n = (b ? 1 : 0);
                    final ExactSubscriber exactSubscriber = this;
                    final T t = guard;
                    final boolean emitValue = exactSubscriber.emitValue(t);
                    final boolean emitValue2 = emitValue;
                    if (emitValue2) {
                        break Label_0109;
                    }
                    final boolean b3 = false;
                    if (b3) {
                        return;
                    }
                    final ExactSubscriber exactSubscriber2 = this;
                    final Object o = exactSubscriber2.guard;
                    final Object o2;
                    guard = (T)(o2 = o);
                    synchronized (o2) {
                        final ExactSubscriber exactSubscriber3 = this;
                        final boolean b4 = false;
                        exactSubscriber3.emitting = b4;
                        return;
                    }
                }
                finally {
                    final T t2;
                    guard = t2;
                }
                // monitorexit(guard2)
                try {
                    final ExactSubscriber exactSubscriber = this;
                    final T t = guard;
                    final boolean emitValue2;
                    final boolean emitValue = emitValue2 = exactSubscriber.emitValue(t);
                    if (!emitValue2) {
                        final boolean b3 = false;
                        if (!b3) {
                            final ExactSubscriber exactSubscriber2 = this;
                            final Object o = exactSubscriber2.guard;
                            final Object o2;
                            guard = (T)(o2 = o);
                            // monitorenter(o2)
                            final ExactSubscriber exactSubscriber3 = this;
                            final boolean b4 = false;
                            exactSubscriber3.emitting = b4;
                        }
                    }
                    else {
                        while (true) {
                            n = (b ? 1 : 0);
                            guard = (T)this.guard;
                            n = (b ? 1 : 0);
                            // monitorenter(guard)
                            n = (b2 ? 1 : 0);
                            try {
                                final List<Object> queue = this.queue;
                                if (queue == null) {
                                    n = (b2 ? 1 : 0);
                                    this.emitting = false;
                                    n = 1;
                                    // monitorexit(guard)
                                    if (true) {
                                        break;
                                    }
                                    synchronized (this.guard) {
                                        this.emitting = false;
                                        return;
                                    }
                                }
                                n = (b2 ? 1 : 0);
                                this.queue = null;
                                n = (b2 ? 1 : 0);
                                // monitorexit(guard)
                                n = (b ? 1 : 0);
                                if (this.drain(queue)) {
                                    continue;
                                }
                                if (false) {
                                    break;
                                }
                                synchronized (this.guard) {
                                    this.emitting = false;
                                }
                            }
                            finally {
                            }
                            // monitorexit(guard)
                        }
                    }
                }
                finally {
                    Label_0252: {
                        if (n != 0) {
                            break Label_0252;
                        }
                        synchronized (this.guard) {
                            this.emitting = false;
                        }
                        // monitorexit(this.guard)
                    }
                }
            }
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
        
        boolean replaceSubject() {
            final Observer<T> consumer = this.state.consumer;
            if (consumer != null) {
                consumer.onCompleted();
            }
            if (this.child.isUnsubscribed()) {
                this.state = this.state.clear();
                this.unsubscribe();
                return false;
            }
            final UnicastSubject<Object> create = UnicastSubject.create();
            this.state = this.state.create((Observer<T>)create, (Observable<T>)create);
            this.child.onNext((Observable<T>)create);
            return true;
        }
        
        void scheduleExact() {
            this.worker.schedulePeriodically(new Action0() {
                @Override
                public void call() {
                    ExactSubscriber.this.nextWindow();
                }
            }, 0L, OperatorWindowWithTime.this.timespan, OperatorWindowWithTime.this.unit);
        }
    }
    
    final class InexactSubscriber extends Subscriber<T>
    {
        final Subscriber<? super Observable<T>> child;
        final List<CountedSerializedSubject<T>> chunks;
        boolean done;
        final Object guard;
        final /* synthetic */ OperatorWindowWithTime this$0;
        final Scheduler.Worker worker;
        
        public InexactSubscriber(final Subscriber<? super Observable<T>> child, final Scheduler.Worker worker) {
            super(child);
            this.child = child;
            this.worker = worker;
            this.guard = new Object();
            this.chunks = new LinkedList<CountedSerializedSubject<T>>();
        }
        
        CountedSerializedSubject<T> createCountedSerializedSubject() {
            final UnicastSubject<Object> create = UnicastSubject.create();
            return (CountedSerializedSubject<T>)new CountedSerializedSubject((Observer<Object>)create, (Observable<Object>)create);
        }
        
        @Override
        public void onCompleted() {
            Object o = this.guard;
            synchronized (o) {
                if (this.done) {
                    return;
                }
                this.done = true;
                final ArrayList<CountedSerializedSubject> list = new ArrayList<CountedSerializedSubject>((Collection<? extends CountedSerializedSubject>)this.chunks);
                this.chunks.clear();
                // monitorexit(o)
                o = list.iterator();
                while (((Iterator)o).hasNext()) {
                    ((CountedSerializedSubject)((Iterator<CountedSerializedSubject>)o).next()).consumer.onCompleted();
                }
            }
            this.child.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            Object o = this.guard;
            synchronized (o) {
                if (this.done) {
                    return;
                }
                this.done = true;
                final ArrayList<CountedSerializedSubject> list = new ArrayList<CountedSerializedSubject>((Collection<? extends CountedSerializedSubject>)this.chunks);
                this.chunks.clear();
                // monitorexit(o)
                o = list.iterator();
                while (((Iterator)o).hasNext()) {
                    ((CountedSerializedSubject)((Iterator<CountedSerializedSubject>)o).next()).consumer.onError(t);
                }
            }
            this.child.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            final ArrayList<CountedSerializedSubject> list;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                list = (ArrayList<CountedSerializedSubject>)new ArrayList<Object>(this.chunks);
                final Iterator<CountedSerializedSubject<T>> iterator = this.chunks.iterator();
                while (iterator.hasNext()) {
                    final CountedSerializedSubject countedSerializedSubject = (CountedSerializedSubject)iterator.next();
                    if (++((CountedSerializedSubject)countedSerializedSubject).count == OperatorWindowWithTime.this.size) {
                        iterator.remove();
                    }
                }
            }
            // monitorexit(o)
            for (final CountedSerializedSubject countedSerializedSubject2 : list) {
                final Throwable t2;
                ((CountedSerializedSubject)countedSerializedSubject2).consumer.onNext((T)t2);
                if (((CountedSerializedSubject)countedSerializedSubject2).count == OperatorWindowWithTime.this.size) {
                    ((CountedSerializedSubject)countedSerializedSubject2).consumer.onCompleted();
                }
            }
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
        
        void scheduleChunk() {
            this.worker.schedulePeriodically(new Action0() {
                @Override
                public void call() {
                    InexactSubscriber.this.startNewChunk();
                }
            }, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.timeshift, OperatorWindowWithTime.this.unit);
        }
        
        void startNewChunk() {
            final CountedSerializedSubject<T> countedSerializedSubject = this.createCountedSerializedSubject();
            final Object guard = this.guard;
            synchronized (guard) {
                if (this.done) {
                    return;
                }
                this.chunks.add(countedSerializedSubject);
                // monitorexit(guard)
                final InexactSubscriber inexactSubscriber = this;
                final Subscriber<? super Observable<T>> subscriber = inexactSubscriber.child;
                final CountedSerializedSubject<T> countedSerializedSubject2 = countedSerializedSubject;
                final Observable<T> observable = countedSerializedSubject2.producer;
                subscriber.onNext(observable);
                final InexactSubscriber inexactSubscriber2 = this;
                final Scheduler.Worker worker = inexactSubscriber2.worker;
                final InexactSubscriber inexactSubscriber3 = this;
                final CountedSerializedSubject<T> countedSerializedSubject3 = countedSerializedSubject;
                final Action0 action0 = new Action0() {
                    final /* synthetic */ CountedSerializedSubject val$chunk;
                    
                    @Override
                    public void call() {
                        InexactSubscriber.this.terminateChunk(this.val$chunk);
                    }
                };
                final InexactSubscriber inexactSubscriber4 = this;
                final OperatorWindowWithTime operatorWindowWithTime = inexactSubscriber4.this$0;
                final long n = operatorWindowWithTime.timespan;
                final InexactSubscriber inexactSubscriber5 = this;
                final OperatorWindowWithTime operatorWindowWithTime2 = inexactSubscriber5.this$0;
                final TimeUnit timeUnit = operatorWindowWithTime2.unit;
                worker.schedule(action0, n, timeUnit);
                return;
            }
            try {
                final InexactSubscriber inexactSubscriber = this;
                final Subscriber<? super Observable<T>> subscriber = inexactSubscriber.child;
                final CountedSerializedSubject<T> countedSerializedSubject2 = countedSerializedSubject;
                final Observable<T> observable = countedSerializedSubject2.producer;
                subscriber.onNext(observable);
                final InexactSubscriber inexactSubscriber2 = this;
                final Scheduler.Worker worker = inexactSubscriber2.worker;
                final InexactSubscriber inexactSubscriber3 = this;
                final CountedSerializedSubject<T> countedSerializedSubject3 = countedSerializedSubject;
                final Action0 action0 = new Action0() {
                    final /* synthetic */ CountedSerializedSubject val$chunk;
                    
                    @Override
                    public void call() {
                        inexactSubscriber3.terminateChunk(countedSerializedSubject3);
                    }
                };
                final InexactSubscriber inexactSubscriber4 = this;
                final OperatorWindowWithTime operatorWindowWithTime = inexactSubscriber4.this$0;
                final long n = operatorWindowWithTime.timespan;
                final InexactSubscriber inexactSubscriber5 = this;
                final OperatorWindowWithTime operatorWindowWithTime2 = inexactSubscriber5.this$0;
                final TimeUnit timeUnit = operatorWindowWithTime2.unit;
                worker.schedule(action0, n, timeUnit);
            }
            catch (Throwable t) {
                this.onError(t);
            }
        }
        
        void terminateChunk(final CountedSerializedSubject<T> countedSerializedSubject) {
            final int n = 0;
            synchronized (this.guard) {
                if (this.done) {
                    return;
                }
                final Iterator<CountedSerializedSubject<T>> iterator = this.chunks.iterator();
                while (true) {
                    do {
                        final int n2 = n;
                        if (iterator.hasNext()) {
                            continue;
                        }
                        // monitorexit(this.guard)
                        if (n2 != 0) {
                            countedSerializedSubject.consumer.onCompleted();
                        }
                        return;
                    } while (iterator.next() != countedSerializedSubject);
                    final int n2 = 1;
                    iterator.remove();
                    continue;
                }
            }
        }
    }
    
    static final class State<T>
    {
        static final State<Object> EMPTY;
        final Observer<T> consumer;
        final int count;
        final Observable<T> producer;
        
        static {
            EMPTY = new State<Object>(null, null, 0);
        }
        
        public State(final Observer<T> consumer, final Observable<T> producer, final int count) {
            this.consumer = consumer;
            this.producer = producer;
            this.count = count;
        }
        
        public static <T> State<T> empty() {
            return (State<T>)State.EMPTY;
        }
        
        public State<T> clear() {
            return empty();
        }
        
        public State<T> create(final Observer<T> observer, final Observable<T> observable) {
            return new State<T>(observer, observable, 0);
        }
        
        public State<T> next() {
            return new State<T>(this.consumer, this.producer, this.count + 1);
        }
    }
}
