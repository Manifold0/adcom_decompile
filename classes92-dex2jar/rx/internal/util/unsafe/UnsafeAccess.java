// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public final class UnsafeAccess
{
    private static final boolean DISABLED_BY_USER;
    public static final Unsafe UNSAFE;
    
    static {
        boolean disabled_BY_USER = true;
        Label_0045: {
            if (System.getProperty("rx.unsafe-disable") == null) {
                break Label_0045;
            }
            while (true) {
                DISABLED_BY_USER = disabled_BY_USER;
                Unsafe unsafe = null;
                while (true) {
                    try {
                        final Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                        declaredField.setAccessible(true);
                        unsafe = (Unsafe)declaredField.get(null);
                        UNSAFE = unsafe;
                        return;
                        disabled_BY_USER = false;
                    }
                    catch (Throwable t) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private UnsafeAccess() {
        throw new IllegalStateException("No instances!");
    }
    
    public static long addressOf(final Class<?> clazz, final String s) {
        try {
            return UnsafeAccess.UNSAFE.objectFieldOffset(clazz.getDeclaredField(s));
        }
        catch (NoSuchFieldException ex) {
            final InternalError internalError = new InternalError();
            internalError.initCause(ex);
            throw internalError;
        }
    }
    
    public static boolean compareAndSwapInt(final Object o, final long n, final int n2, final int n3) {
        return UnsafeAccess.UNSAFE.compareAndSwapInt(o, n, n2, n3);
    }
    
    public static int getAndAddInt(final Object o, final long n, final int n2) {
        int intVolatile;
        do {
            intVolatile = UnsafeAccess.UNSAFE.getIntVolatile(o, n);
        } while (!UnsafeAccess.UNSAFE.compareAndSwapInt(o, n, intVolatile, intVolatile + n2));
        return intVolatile;
    }
    
    public static int getAndIncrementInt(final Object o, final long n) {
        int intVolatile;
        do {
            intVolatile = UnsafeAccess.UNSAFE.getIntVolatile(o, n);
        } while (!UnsafeAccess.UNSAFE.compareAndSwapInt(o, n, intVolatile, intVolatile + 1));
        return intVolatile;
    }
    
    public static int getAndSetInt(final Object o, final long n, final int n2) {
        int intVolatile;
        do {
            intVolatile = UnsafeAccess.UNSAFE.getIntVolatile(o, n);
        } while (!UnsafeAccess.UNSAFE.compareAndSwapInt(o, n, intVolatile, n2));
        return intVolatile;
    }
    
    public static boolean isUnsafeAvailable() {
        return UnsafeAccess.UNSAFE != null && !UnsafeAccess.DISABLED_BY_USER;
    }
}
