// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import dagger.MembersInjector;

public final class MembersInjectors
{
    private MembersInjectors() {
    }
    
    public static <T> MembersInjector<T> delegatingTo(final MembersInjector<? super T> membersInjector) {
        return Preconditions.checkNotNull(membersInjector);
    }
    
    public static <T> T injectMembers(final MembersInjector<T> membersInjector, final T t) {
        membersInjector.injectMembers(t);
        return t;
    }
    
    public static <T> MembersInjector<T> noOp() {
        return (MembersInjector<T>)NoOpMembersInjector.INSTANCE;
    }
    
    private enum NoOpMembersInjector implements MembersInjector<Object>
    {
        INSTANCE;
        
        @Override
        public void injectMembers(final Object o) {
            Preconditions.checkNotNull(o);
        }
    }
}
