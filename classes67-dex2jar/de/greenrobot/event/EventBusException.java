// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event;

public class EventBusException extends RuntimeException
{
    private static final long serialVersionUID = -2912559384646531479L;
    
    public EventBusException(final String s) {
        super(s);
    }
    
    public EventBusException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public EventBusException(final Throwable t) {
        super(t);
    }
}
