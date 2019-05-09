// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

public class OnErrorNotImplementedException extends RuntimeException
{
    private static final long serialVersionUID = -6298857009889503852L;
    
    public OnErrorNotImplementedException(final String s, Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        super(s, t);
    }
    
    public OnErrorNotImplementedException(Throwable t) {
        String message;
        if (t != null) {
            message = t.getMessage();
        }
        else {
            message = null;
        }
        if (t == null) {
            t = new NullPointerException();
        }
        super(message, t);
    }
}
