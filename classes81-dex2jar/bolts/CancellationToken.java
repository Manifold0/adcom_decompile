// 
// Decompiled by Procyon v0.5.34
// 

package bolts;

import java.util.Locale;
import java.util.concurrent.CancellationException;

public class CancellationToken
{
    private final CancellationTokenSource tokenSource;
    
    CancellationToken(final CancellationTokenSource tokenSource) {
        this.tokenSource = tokenSource;
    }
    
    public boolean isCancellationRequested() {
        return this.tokenSource.isCancellationRequested();
    }
    
    public CancellationTokenRegistration register(final Runnable runnable) {
        return this.tokenSource.register(runnable);
    }
    
    public void throwIfCancellationRequested() throws CancellationException {
        this.tokenSource.throwIfCancellationRequested();
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", this.getClass().getName(), Integer.toHexString(this.hashCode()), Boolean.toString(this.tokenSource.isCancellationRequested()));
    }
}
