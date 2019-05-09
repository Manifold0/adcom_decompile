// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event.util;

public class ThrowableFailureEvent implements HasExecutionScope
{
    private Object executionContext;
    protected final boolean suppressErrorUi;
    protected final Throwable throwable;
    
    public ThrowableFailureEvent(final Throwable throwable) {
        this.throwable = throwable;
        this.suppressErrorUi = false;
    }
    
    public ThrowableFailureEvent(final Throwable throwable, final boolean suppressErrorUi) {
        this.throwable = throwable;
        this.suppressErrorUi = suppressErrorUi;
    }
    
    @Override
    public Object getExecutionScope() {
        return this.executionContext;
    }
    
    public Throwable getThrowable() {
        return this.throwable;
    }
    
    public boolean isSuppressErrorUi() {
        return this.suppressErrorUi;
    }
    
    @Override
    public void setExecutionScope(final Object executionContext) {
        this.executionContext = executionContext;
    }
}
