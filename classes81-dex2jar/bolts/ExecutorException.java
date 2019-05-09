// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

public class ExecutorException extends RuntimeException
{
    public ExecutorException(final Exception ex) {
        super("An exception was thrown by an Executor", ex);
    }
}
