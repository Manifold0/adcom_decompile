// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

public final class UnsubscribeFailedException extends RuntimeException
{
    private static final long serialVersionUID = 4594672310593167598L;
    
    public UnsubscribeFailedException(final String s, Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        super(s, t);
    }
    
    public UnsubscribeFailedException(Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        super(t);
    }
}
