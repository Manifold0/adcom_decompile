// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

public final class OnCompletedFailedException extends RuntimeException
{
    private static final long serialVersionUID = 8622579378868820554L;
    
    public OnCompletedFailedException(final String s, Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        super(s, t);
    }
    
    public OnCompletedFailedException(Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        super(t);
    }
}
