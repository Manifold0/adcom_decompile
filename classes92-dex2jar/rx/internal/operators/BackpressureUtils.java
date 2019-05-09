// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;
import rx.Subscriber;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public final class BackpressureUtils
{
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;
    
    private BackpressureUtils() {
        throw new IllegalStateException("No instances!");
    }
    
    public static long addCap(long n, long n2) {
        n2 = (n += n2);
        if (n2 < 0L) {
            n = Long.MAX_VALUE;
        }
        return n;
    }
    
    public static long getAndAddRequest(final AtomicLong atomicLong, final long n) {
        long value;
        do {
            value = atomicLong.get();
        } while (!atomicLong.compareAndSet(value, addCap(value, n)));
        return value;
    }
    
    public static long multiplyCap(final long n, final long n2) {
        long n4;
        final long n3 = n4 = n * n2;
        if ((n | n2) >>> 31 != 0L) {
            n4 = n3;
            if (n2 != 0L) {
                n4 = n3;
                if (n3 / n2 != n) {
                    n4 = Long.MAX_VALUE;
                }
            }
        }
        return n4;
    }
    
    public static <T> void postCompleteDone(final AtomicLong atomicLong, final Queue<T> queue, final Subscriber<? super T> subscriber) {
        postCompleteDone(atomicLong, queue, (Subscriber<? super Object>)subscriber, (Func1<? super T, ?>)UtilityFunctions.identity());
    }
    
    public static <T, R> void postCompleteDone(final AtomicLong atomicLong, final Queue<T> queue, final Subscriber<? super R> subscriber, final Func1<? super T, ? extends R> func1) {
        long value;
        do {
            value = atomicLong.get();
            if ((value & Long.MIN_VALUE) != 0x0L) {
                return;
            }
        } while (!atomicLong.compareAndSet(value, value | Long.MIN_VALUE));
        if (value != 0L) {
            postCompleteDrain(atomicLong, (Queue<Object>)queue, (Subscriber<? super Object>)subscriber, (Func1<? super Object, ?>)func1);
        }
    }
    
    static <T, R> void postCompleteDrain(final AtomicLong atomicLong, final Queue<T> queue, final Subscriber<? super R> subscriber, final Func1<? super T, ? extends R> func1) {
        long n = atomicLong.get();
        if (n != Long.MAX_VALUE) {
            long n2 = Long.MIN_VALUE;
            Block_7: {
                while (true) {
                    if (n2 != n) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        final T poll = queue.poll();
                        if (poll == null) {
                            break;
                        }
                        subscriber.onNext((Object)func1.call(poll));
                        ++n2;
                    }
                    else {
                        if (n2 == n) {
                            if (subscriber.isUnsubscribed()) {
                                return;
                            }
                            if (queue.isEmpty()) {
                                break Block_7;
                            }
                        }
                        if ((n = atomicLong.get()) != n2) {
                            continue;
                        }
                        n = atomicLong.addAndGet(-(n2 & Long.MAX_VALUE));
                        if (n == Long.MIN_VALUE) {
                            return;
                        }
                        n2 = Long.MIN_VALUE;
                    }
                }
                subscriber.onCompleted();
                return;
            }
            subscriber.onCompleted();
            return;
        }
        while (!subscriber.isUnsubscribed()) {
            final T poll2 = queue.poll();
            if (poll2 == null) {
                subscriber.onCompleted();
                return;
            }
            subscriber.onNext((Object)func1.call(poll2));
        }
    }
    
    public static <T> boolean postCompleteRequest(final AtomicLong atomicLong, final long n, final Queue<T> queue, final Subscriber<? super T> subscriber) {
        return postCompleteRequest(atomicLong, n, queue, (Subscriber<? super Object>)subscriber, (Func1<? super T, ?>)UtilityFunctions.identity());
    }
    
    public static <T, R> boolean postCompleteRequest(final AtomicLong atomicLong, final long n, final Queue<T> queue, final Subscriber<? super R> subscriber, final Func1<? super T, ? extends R> func1) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        }
        if (n == 0L) {
            return (atomicLong.get() & Long.MIN_VALUE) == 0x0L;
        }
        long value;
        long n2;
        do {
            value = atomicLong.get();
            n2 = (value & Long.MIN_VALUE);
        } while (!atomicLong.compareAndSet(value, addCap(value & Long.MAX_VALUE, n) | n2));
        if (value == Long.MIN_VALUE) {
            postCompleteDrain(atomicLong, queue, (Subscriber<? super Object>)subscriber, (Func1<? super T, ?>)func1);
            return false;
        }
        return n2 == 0L;
    }
    
    public static long produced(final AtomicLong atomicLong, final long n) {
        long value;
        long n2;
        do {
            value = atomicLong.get();
            if (value == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            n2 = value - n;
            if (n2 < 0L) {
                throw new IllegalStateException("More produced than requested: " + n2);
            }
        } while (!atomicLong.compareAndSet(value, n2));
        return n2;
    }
    
    public static boolean validate(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        }
        return n != 0L;
    }
}
