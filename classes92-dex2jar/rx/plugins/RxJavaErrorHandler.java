// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

import rx.annotations.Beta;
import rx.exceptions.Exceptions;

public abstract class RxJavaErrorHandler
{
    protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";
    
    @Deprecated
    public void handleError(final Throwable t) {
    }
    
    @Beta
    public final String handleOnNextValueRendering(final Object o) {
        try {
            return this.render(o);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        catch (Throwable t) {
            Exceptions.throwIfFatal(t);
            goto Label_0015;
        }
    }
    
    @Beta
    protected String render(final Object o) throws InterruptedException {
        return null;
    }
}
