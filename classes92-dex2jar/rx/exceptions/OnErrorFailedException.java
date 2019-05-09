// 
// Decompiled by Procyon v0.5.34
// 

package rx.exceptions;

public class OnErrorFailedException extends RuntimeException
{
    private static final long serialVersionUID = -419289748403337611L;
    
    public OnErrorFailedException(final String s, Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        super(s, t);
    }
    
    public OnErrorFailedException(Throwable t) {
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
