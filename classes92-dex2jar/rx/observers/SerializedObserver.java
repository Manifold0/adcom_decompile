// 
// Decompiled by Procyon v0.5.34
// 

package rx.observers;

import rx.exceptions.OnErrorThrowable;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;
import rx.Observer;

public class SerializedObserver<T> implements Observer<T>
{
    private final Observer<? super T> actual;
    private boolean emitting;
    private final NotificationLite<T> nl;
    private FastList queue;
    private volatile boolean terminated;
    
    public SerializedObserver(final Observer<? super T> actual) {
        this.nl = NotificationLite.instance();
        this.actual = actual;
    }
    
    @Override
    public void onCompleted() {
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
        }
        this.terminated = true;
        if (this.emitting) {
            FastList queue;
            if ((queue = this.queue) == null) {
                queue = new FastList();
                this.queue = queue;
            }
            queue.add(this.nl.completed());
            // monitorexit(this)
            return;
        }
        this.emitting = true;
        // monitorexit(this)
        this.actual.onCompleted();
    }
    
    @Override
    public void onError(final Throwable t) {
        Exceptions.throwIfFatal(t);
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
        }
        this.terminated = true;
        if (this.emitting) {
            FastList queue;
            if ((queue = this.queue) == null) {
                queue = new FastList();
                this.queue = queue;
            }
            queue.add(this.nl.error(t));
            // monitorexit(this)
            return;
        }
        this.emitting = true;
        // monitorexit(this)
        this.actual.onError(t);
    }
    
    @Override
    public void onNext(final T t) {
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
        }
        if (this.emitting) {
            FastList queue;
            if ((queue = this.queue) == null) {
                queue = new FastList();
                this.queue = queue;
            }
            queue.add(this.nl.next(t));
            // monitorexit(this)
            return;
        }
        while (true) {
            this.emitting = true;
            // monitorexit(this)
        Label_0095:
            while (true) {
                Object o;
                try {
                    this.actual.onNext((Object)t);
                    synchronized (this) {
                        o = this.queue;
                        if (o == null) {
                            this.emitting = false;
                            return;
                        }
                    }
                }
                catch (Throwable t2) {
                    this.terminated = true;
                    Exceptions.throwOrReport(t2, this.actual, t);
                    return;
                }
                this.queue = null;
                o = ((FastList)o).array;
                for (int length = ((FastList)o).length, i = 0; i < length; ++i) {
                    final Object o2 = o[i];
                    if (o2 == null) {
                        continue Label_0095;
                    }
                    try {
                        if (this.nl.accept(this.actual, o2)) {
                            this.terminated = true;
                            return;
                        }
                    }
                    catch (Throwable t3) {
                        this.terminated = true;
                        Exceptions.throwIfFatal(t3);
                        this.actual.onError(OnErrorThrowable.addValueAsLastCause(t3, t));
                        return;
                    }
                }
                continue Label_0095;
            }
        }
        // monitorexit(this)
    }
    
    static final class FastList
    {
        Object[] array;
        int size;
        
        public void add(final Object o) {
            final int size = this.size;
            final Object[] array = this.array;
            Object[] array2;
            if (array == null) {
                array2 = new Object[16];
                this.array = array2;
            }
            else {
                array2 = array;
                if (size == array.length) {
                    array2 = new Object[(size >> 2) + size];
                    System.arraycopy(array, 0, array2, 0, size);
                    this.array = array2;
                }
            }
            array2[size] = o;
            this.size = size + 1;
        }
    }
}
