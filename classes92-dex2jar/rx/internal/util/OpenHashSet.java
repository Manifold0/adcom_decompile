// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.Arrays;
import rx.functions.Action1;
import rx.internal.util.unsafe.Pow2;

public final class OpenHashSet<T>
{
    private static final int INT_PHI = -1640531527;
    T[] keys;
    final float loadFactor;
    int mask;
    int maxSize;
    int size;
    
    public OpenHashSet() {
        this(16, 0.75f);
    }
    
    public OpenHashSet(final int n) {
        this(n, 0.75f);
    }
    
    public OpenHashSet(int roundToPowerOfTwo, final float loadFactor) {
        this.loadFactor = loadFactor;
        roundToPowerOfTwo = Pow2.roundToPowerOfTwo(roundToPowerOfTwo);
        this.mask = roundToPowerOfTwo - 1;
        this.maxSize = (int)(roundToPowerOfTwo * loadFactor);
        this.keys = (T[])new Object[roundToPowerOfTwo];
    }
    
    static int mix(int n) {
        n *= -1640531527;
        return n >>> 16 ^ n;
    }
    
    public boolean add(final T t) {
        final T[] keys = this.keys;
        final int mask = this.mask;
        int n = mix(t.hashCode()) & mask;
        final T t2 = keys[n];
        int n2 = n;
        Label_0067: {
            if (t2 != null) {
                if (t2.equals(t)) {
                    return false;
                }
                T t3;
                do {
                    n = (n + 1 & mask);
                    t3 = keys[n];
                    if (t3 == null) {
                        n2 = n;
                        break Label_0067;
                    }
                } while (!t3.equals(t));
                return false;
            }
        }
        keys[n2] = t;
        if (++this.size >= this.maxSize) {
            this.rehash();
        }
        return true;
    }
    
    public void clear(final Action1<? super T> action1) {
        if (this.size == 0) {
            return;
        }
        final T[] keys = this.keys;
        for (int length = keys.length, i = 0; i < length; ++i) {
            final T t = keys[i];
            if (t != null) {
                action1.call(t);
            }
        }
        Arrays.fill(keys, null);
        this.size = 0;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    void rehash() {
        final T[] keys = this.keys;
        int length = keys.length;
        final int n = length << 1;
        final int mask = n - 1;
        final Object[] keys2 = new Object[n];
        for (int i = this.size; i != 0; --i) {
            int n2 = length;
            do {
                length = --n2;
            } while (keys[length] == null);
            int n4;
            int n3 = n4 = (mix(keys[length].hashCode()) & mask);
            if (keys2[n3] != null) {
                do {
                    n4 = (n3 = (n3 + 1 & mask));
                } while (keys2[n4] != null);
            }
            keys2[n4] = keys[length];
        }
        this.mask = mask;
        this.maxSize = (int)(n * this.loadFactor);
        this.keys = (T[])keys2;
    }
    
    public boolean remove(final T t) {
        final T[] keys = this.keys;
        final int mask = this.mask;
        final int n = mix(t.hashCode()) & mask;
        final T t2 = keys[n];
        if (t2 != null) {
            int n2 = n;
            if (t2.equals(t)) {
                return this.removeEntry(n, keys, mask);
            }
            T t3;
            int n3;
            do {
                n3 = (n2 + 1 & mask);
                t3 = keys[n3];
                if (t3 == null) {
                    return false;
                }
                n2 = n3;
            } while (!t3.equals(t));
            return this.removeEntry(n3, keys, mask);
        }
        return false;
    }
    
    boolean removeEntry(int n, final T[] array, final int n2) {
        --this.size;
        int n3 = 0;
    Block_1:
        while (true) {
            n3 = n;
            n = (n3 + 1 & n2);
            T t;
            while (true) {
                t = array[n];
                if (t == null) {
                    break Block_1;
                }
                final int n4 = mix(t.hashCode()) & n2;
                if (n3 <= n) {
                    if (n3 >= n4 || n4 > n) {
                        break;
                    }
                }
                else if (n3 >= n4 && n4 > n) {
                    break;
                }
                n = (n + 1 & n2);
            }
            array[n3] = t;
        }
        array[n3] = null;
        return true;
    }
    
    public void terminate() {
        this.size = 0;
        this.keys = (T[])new Object[0];
    }
    
    public T[] values() {
        return this.keys;
    }
}
