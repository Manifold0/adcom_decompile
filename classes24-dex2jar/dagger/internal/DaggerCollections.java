// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public final class DaggerCollections
{
    private static final int MAX_POWER_OF_TWO = 1073741824;
    
    private DaggerCollections() {
    }
    
    private static int calculateInitialCapacity(final int n) {
        if (n < 3) {
            return n + 1;
        }
        if (n < 1073741824) {
            return (int)(n / 0.75f + 1.0f);
        }
        return Integer.MAX_VALUE;
    }
    
    public static boolean hasDuplicates(final List<?> list) {
        return list.size() >= 2 && list.size() != new HashSet(list).size();
    }
    
    static <T> HashSet<T> newHashSetWithExpectedSize(final int n) {
        return new HashSet<T>(calculateInitialCapacity(n));
    }
    
    static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(final int n) {
        return new LinkedHashMap<K, V>(calculateInitialCapacity(n));
    }
    
    public static <T> List<T> presizedList(final int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        return new ArrayList<T>(n);
    }
}
