// 
// Decompiled by Procyon v0.5.34
// 

package rx;

import rx.exceptions.MissingBackpressureException;
import rx.annotations.Beta;

@Beta
public final class BackpressureOverflow
{
    public static final Strategy ON_OVERFLOW_DEFAULT;
    public static final Strategy ON_OVERFLOW_DROP_LATEST;
    public static final Strategy ON_OVERFLOW_DROP_OLDEST;
    public static final Strategy ON_OVERFLOW_ERROR;
    
    static {
        ON_OVERFLOW_ERROR = (Strategy)Error.INSTANCE;
        ON_OVERFLOW_DEFAULT = BackpressureOverflow.ON_OVERFLOW_ERROR;
        ON_OVERFLOW_DROP_OLDEST = (Strategy)DropOldest.INSTANCE;
        ON_OVERFLOW_DROP_LATEST = (Strategy)DropLatest.INSTANCE;
    }
    
    static class DropLatest implements Strategy
    {
        static final DropLatest INSTANCE;
        
        static {
            INSTANCE = new DropLatest();
        }
        
        private DropLatest() {
        }
        
        @Override
        public boolean mayAttemptDrop() {
            return false;
        }
    }
    
    static class DropOldest implements Strategy
    {
        static final DropOldest INSTANCE;
        
        static {
            INSTANCE = new DropOldest();
        }
        
        private DropOldest() {
        }
        
        @Override
        public boolean mayAttemptDrop() {
            return true;
        }
    }
    
    static class Error implements Strategy
    {
        static final Error INSTANCE;
        
        static {
            INSTANCE = new Error();
        }
        
        private Error() {
        }
        
        @Override
        public boolean mayAttemptDrop() throws MissingBackpressureException {
            throw new MissingBackpressureException("Overflowed buffer");
        }
    }
    
    public interface Strategy
    {
        boolean mayAttemptDrop() throws MissingBackpressureException;
    }
}
