// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

import rx.plugins.RxJavaHooks;
import java.util.HashSet;
import rx.annotations.Experimental;

@Experimental
public final class AssemblyStackTraceException extends RuntimeException
{
    private static final long serialVersionUID = 2038859767182585852L;
    
    public AssemblyStackTraceException(final String s) {
        super(s);
    }
    
    public static AssemblyStackTraceException find(Throwable cause) {
        final HashSet<Throwable> set = new HashSet<Throwable>();
        while (!(cause instanceof AssemblyStackTraceException)) {
            if (cause == null || cause.getCause() == null) {
                return null;
            }
            if (!set.add(cause = cause.getCause())) {
                return null;
            }
        }
        return (AssemblyStackTraceException)cause;
    }
    
    public void attachTo(Throwable cause) {
        final HashSet<Throwable> set = new HashSet<Throwable>();
        while (cause.getCause() != null) {
            if (!set.add(cause = cause.getCause())) {
                RxJavaHooks.onError(this);
                return;
            }
        }
        cause.initCause(this);
    }
    
    @Override
    public Throwable fillInStackTrace() {
        // monitorenter(this)
        // monitorexit(this)
        return this;
    }
}
