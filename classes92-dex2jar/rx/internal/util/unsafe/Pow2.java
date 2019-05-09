// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

public final class Pow2
{
    private Pow2() {
        throw new IllegalStateException("No instances!");
    }
    
    public static boolean isPowerOfTwo(final int n) {
        return (n - 1 & n) == 0x0;
    }
    
    public static int roundToPowerOfTwo(final int n) {
        return 1 << 32 - Integer.numberOfLeadingZeros(n - 1);
    }
}
