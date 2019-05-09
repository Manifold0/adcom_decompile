// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

import rx.SingleSubscriber;
import rx.annotations.Beta;
import rx.Observer;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;

public final class Exceptions
{
    private static final int MAX_DEPTH = 25;
    
    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }
    
    public static void addCause(Throwable cause, final Throwable t) {
        final HashSet<Throwable> set = new HashSet<Throwable>();
        int n = 0;
        while (true) {
            Throwable t2 = cause;
            Label_0057: {
                if (cause.getCause() != null) {
                    if (n >= 25) {
                        break;
                    }
                    cause = cause.getCause();
                    if (!set.contains(cause.getCause())) {
                        break Label_0057;
                    }
                    t2 = cause;
                }
                try {
                    t2.initCause(t);
                    return;
                }
                catch (Throwable cause) {
                    return;
                }
            }
            set.add(cause.getCause());
            ++n;
        }
    }
    
    public static Throwable getFinalCause(Throwable cause) {
        int n = 0;
        Throwable t;
        while (true) {
            t = cause;
            if (cause.getCause() == null) {
                break;
            }
            if (n >= 25) {
                t = new RuntimeException("Stack too deep to get final cause");
                break;
            }
            cause = cause.getCause();
            ++n;
        }
        return t;
    }
    
    public static RuntimeException propagate(final Throwable t) {
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
        throw new RuntimeException(t);
    }
    
    public static void throwIfAny(final List<? extends Throwable> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.size() != 1) {
            throw new CompositeException(list);
        }
        final Throwable t = (Throwable)list.get(0);
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        if (t instanceof Error) {
            throw (Error)t;
        }
        throw new RuntimeException(t);
    }
    
    public static void throwIfFatal(final Throwable t) {
        if (t instanceof OnErrorNotImplementedException) {
            throw (OnErrorNotImplementedException)t;
        }
        if (t instanceof OnErrorFailedException) {
            throw (OnErrorFailedException)t;
        }
        if (t instanceof OnCompletedFailedException) {
            throw (OnCompletedFailedException)t;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError)t;
        }
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath)t;
        }
        if (t instanceof LinkageError) {
            throw (LinkageError)t;
        }
    }
    
    @Beta
    public static void throwOrReport(final Throwable t, final Observer<?> observer) {
        throwIfFatal(t);
        observer.onError(t);
    }
    
    @Beta
    public static void throwOrReport(final Throwable t, final Observer<?> observer, final Object o) {
        throwIfFatal(t);
        observer.onError(OnErrorThrowable.addValueAsLastCause(t, o));
    }
    
    @Beta
    public static void throwOrReport(final Throwable t, final SingleSubscriber<?> singleSubscriber) {
        throwIfFatal(t);
        singleSubscriber.onError(t);
    }
    
    @Beta
    public static void throwOrReport(final Throwable t, final SingleSubscriber<?> singleSubscriber, final Object o) {
        throwIfFatal(t);
        singleSubscriber.onError(OnErrorThrowable.addValueAsLastCause(t, o));
    }
}
