// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.util.HashMap;
import android.support.v4.util.ArrayMap;
import java.util.HashSet;
import android.support.v4.util.ArraySet;
import java.util.Set;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.support.annotation.Nullable;
import java.util.Collection;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class CollectionUtils
{
    private CollectionUtils() {
    }
    
    @KeepForSdk
    public static boolean isEmpty(@Nullable final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    
    @Deprecated
    @KeepForSdk
    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }
    
    @Deprecated
    @KeepForSdk
    public static <T> List<T> listOf(final T t) {
        return Collections.singletonList(t);
    }
    
    @Deprecated
    @KeepForSdk
    public static <T> List<T> listOf(final T... array) {
        switch (array.length) {
            default: {
                return Collections.unmodifiableList((List<? extends T>)Arrays.asList((T[])array));
            }
            case 0: {
                return listOf();
            }
            case 1: {
                return listOf(array[0]);
            }
        }
    }
    
    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(final K k, final V v, final K i, final V v2, final K j, final V v3) {
        final Map<? extends K, ? extends V> zzb = zzb(3, false);
        zzb.put(k, v);
        zzb.put(i, v2);
        zzb.put(j, v3);
        return (Map<K, V>)Collections.unmodifiableMap((Map<?, ?>)zzb);
    }
    
    @KeepForSdk
    public static <K, V> Map<K, V> mapOf(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4, final K m, final V v5, final K k2, final V v6) {
        final Map<? extends K, ? extends V> zzb = zzb(6, false);
        zzb.put(k, v);
        zzb.put(i, v2);
        zzb.put(j, v3);
        zzb.put(l, v4);
        zzb.put(m, v5);
        zzb.put(k2, v6);
        return (Map<K, V>)Collections.unmodifiableMap((Map<?, ?>)zzb);
    }
    
    @KeepForSdk
    public static <K, V> Map<K, V> mapOfKeyValueArrays(final K[] array, final V[] array2) {
        int i = 0;
        if (array.length != array2.length) {
            throw new IllegalArgumentException(new StringBuilder(66).append("Key and values array lengths not equal: ").append(array.length).append(" != ").append(array2.length).toString());
        }
        switch (array.length) {
            default: {
                final Map<? extends K, ? extends V> zzb = zzb(array.length, false);
                while (i < array.length) {
                    zzb.put(array[i], array2[i]);
                    ++i;
                }
                return (Map<K, V>)Collections.unmodifiableMap((Map<?, ?>)zzb);
            }
            case 0: {
                return Collections.emptyMap();
            }
            case 1: {
                return Collections.singletonMap(array[0], array2[0]);
            }
        }
    }
    
    @KeepForSdk
    public static <T> Set<T> mutableSetOfWithSize(final int n) {
        if (n == 0) {
            return (Set<T>)new ArraySet();
        }
        return zza(n, true);
    }
    
    @Deprecated
    @KeepForSdk
    public static <T> Set<T> setOf(final T t, final T t2, final T t3) {
        final Set<? extends T> zza = zza(3, false);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        return (Set<T>)Collections.unmodifiableSet((Set<?>)zza);
    }
    
    @Deprecated
    @KeepForSdk
    public static <T> Set<T> setOf(final T... array) {
        switch (array.length) {
            default: {
                final Set<? super T> zza = zza(array.length, false);
                Collections.addAll(zza, array);
                return Collections.unmodifiableSet((Set<? extends T>)zza);
            }
            case 0: {
                return Collections.emptySet();
            }
            case 1: {
                return Collections.singleton(array[0]);
            }
            case 2: {
                final T t = array[0];
                final T t2 = array[1];
                final Set<? extends T> zza2 = zza(2, false);
                zza2.add(t);
                zza2.add(t2);
                return (Set<T>)Collections.unmodifiableSet((Set<?>)zza2);
            }
            case 3: {
                return setOf(array[0], array[1], array[2]);
            }
            case 4: {
                final T t3 = array[0];
                final T t4 = array[1];
                final T t5 = array[2];
                final T t6 = array[3];
                final Set<? extends T> zza3 = zza(4, false);
                zza3.add(t3);
                zza3.add(t4);
                zza3.add(t5);
                zza3.add(t6);
                return (Set<T>)Collections.unmodifiableSet((Set<?>)zza3);
            }
        }
    }
    
    private static <T> Set<T> zza(final int n, final boolean b) {
        float n2;
        if (b) {
            n2 = 0.75f;
        }
        else {
            n2 = 1.0f;
        }
        int n3;
        if (b) {
            n3 = 128;
        }
        else {
            n3 = 256;
        }
        if (n <= n3) {
            return (Set<T>)new ArraySet(n);
        }
        return new HashSet<T>(n, n2);
    }
    
    private static <K, V> Map<K, V> zzb(final int n, final boolean b) {
        if (n <= 256) {
            return (Map<K, V>)new ArrayMap(n);
        }
        return new HashMap<K, V>(n, 1.0f);
    }
}
