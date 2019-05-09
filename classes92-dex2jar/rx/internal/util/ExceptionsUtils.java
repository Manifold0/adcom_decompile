// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.Collection;
import java.util.ArrayList;
import rx.exceptions.CompositeException;
import java.util.concurrent.atomic.AtomicReference;

public enum ExceptionsUtils
{
    private static final Throwable TERMINATED;
    
    static {
        TERMINATED = new Throwable("Terminated");
    }
    
    public static boolean addThrowable(final AtomicReference<Throwable> atomicReference, final Throwable t) {
        while (true) {
            final Throwable t2 = atomicReference.get();
            if (t2 == ExceptionsUtils.TERMINATED) {
                return false;
            }
            Throwable t3;
            if (t2 == null) {
                t3 = t;
            }
            else if (t2 instanceof CompositeException) {
                final ArrayList list = new ArrayList<Throwable>(((CompositeException)t2).getExceptions());
                list.add(t);
                t3 = new CompositeException((Collection<? extends Throwable>)list);
            }
            else {
                t3 = new CompositeException(new Throwable[] { t2, t });
            }
            if (atomicReference.compareAndSet(t2, t3)) {
                return true;
            }
        }
    }
    
    public static boolean isTerminated(final Throwable t) {
        return t == ExceptionsUtils.TERMINATED;
    }
    
    public static boolean isTerminated(final AtomicReference<Throwable> atomicReference) {
        return isTerminated(atomicReference.get());
    }
    
    public static Throwable terminate(final AtomicReference<Throwable> atomicReference) {
        Throwable t;
        if ((t = atomicReference.get()) != ExceptionsUtils.TERMINATED) {
            t = atomicReference.getAndSet(ExceptionsUtils.TERMINATED);
        }
        return t;
    }
}
