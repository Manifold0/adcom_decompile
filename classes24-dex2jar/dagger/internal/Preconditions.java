// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

public final class Preconditions
{
    private Preconditions() {
    }
    
    public static <T> T checkNotNull(final T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }
    
    public static <T> T checkNotNull(final T t, final String s) {
        if (t == null) {
            throw new NullPointerException(s);
        }
        return t;
    }
}
