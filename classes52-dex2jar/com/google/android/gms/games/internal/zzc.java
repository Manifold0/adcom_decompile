// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import java.util.Iterator;
import java.util.Set;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.Arrays;
import android.support.annotation.Nullable;
import android.os.Bundle;

public final class zzc
{
    public static int zza(@Nullable final Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        final int size = bundle.size();
        if (size == 0) {
            return 0;
        }
        final String[] array = bundle.keySet().toArray(new String[size]);
        Arrays.sort(array);
        final int length = array.length;
        int i = 0;
        int n = 1;
        while (i < length) {
            final String s = array[i];
            final int n2 = n * 31;
            final Object value = bundle.get(s);
            if (value != null) {
                if (value instanceof Bundle) {
                    n = zza((Bundle)value) + n2;
                }
                else if (value instanceof byte[]) {
                    n = Arrays.hashCode((byte[])value) + n2;
                }
                else if (value instanceof char[]) {
                    n = Arrays.hashCode((char[])value) + n2;
                }
                else if (value instanceof short[]) {
                    n = Arrays.hashCode((short[])value) + n2;
                }
                else if (value instanceof float[]) {
                    n = Arrays.hashCode((float[])value) + n2;
                }
                else if (value instanceof CharSequence[]) {
                    n = Arrays.hashCode((Object[])value) + n2;
                }
                else if (value instanceof Object[]) {
                    final Object[] array2 = (Object[])value;
                    final int length2 = array2.length;
                    int j = 0;
                    int n3 = 1;
                    while (j < length2) {
                        final Object o = array2[j];
                        n3 *= 31;
                        if (o instanceof Bundle) {
                            n3 += zza((Bundle)o);
                        }
                        else if (o != null) {
                            n3 += o.hashCode();
                        }
                        ++j;
                    }
                    n = n2 + n3;
                }
                else if (value instanceof SparseArray) {
                    final SparseArray sparseArray = (SparseArray)value;
                    final int size2 = sparseArray.size();
                    int k = 0;
                    int n4 = 1;
                    while (k < size2) {
                        n4 = (n4 * 31 + sparseArray.keyAt(k)) * 31;
                        final Object value2 = sparseArray.valueAt(k);
                        if (value2 instanceof Bundle) {
                            n4 += zza((Bundle)value2);
                        }
                        else if (value2 != null) {
                            n4 += value2.hashCode();
                        }
                        ++k;
                    }
                    n = n2 + n4;
                }
                else {
                    n = value.hashCode() + n2;
                }
            }
            else {
                n = n2;
            }
            ++i;
        }
        return n;
    }
    
    public static boolean zza(@Nullable final Bundle bundle, @Nullable final Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        if (bundle == null || bundle2 == null) {
            return false;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        final Set keySet = bundle.keySet();
        if (!keySet.equals(bundle2.keySet())) {
            return false;
        }
        Object value;
        Object value2;
        Parcelable[] array;
        Parcelable[] array2;
        int length;
        Parcelable parcelable;
        Parcelable parcelable2;
        int n;
        SparseArray sparseArray;
        SparseArray sparseArray2;
        int size;
        int n2;
        Object value3;
        Object value4;
        Label_0365_Outer:Label_0523_Outer:
        for (final String s : keySet) {
            value = bundle.get(s);
            value2 = bundle2.get(s);
            if (value == null) {
                if (value2 != null) {
                    return false;
                }
                continue;
            }
            else if (value instanceof Bundle) {
                if (!(value2 instanceof Bundle) || !zza((Bundle)value, (Bundle)value2)) {
                    return false;
                }
                continue;
            }
            else if (value instanceof byte[]) {
                if (!(value2 instanceof byte[]) || !Arrays.equals((byte[])value, (byte[])value2)) {
                    return false;
                }
                continue;
            }
            else if (value instanceof char[]) {
                if (!(value2 instanceof char[]) || !Arrays.equals((char[])value, (char[])value2)) {
                    return false;
                }
                continue;
            }
            else if (value instanceof short[]) {
                if (!(value2 instanceof short[]) || !Arrays.equals((short[])value, (short[])value2)) {
                    return false;
                }
                continue;
            }
            else if (value instanceof float[]) {
                if (!(value2 instanceof float[]) || !Arrays.equals((float[])value, (float[])value2)) {
                    return false;
                }
                continue;
            }
            else if (value instanceof CharSequence[]) {
                if (!(value2 instanceof CharSequence[]) || !Arrays.equals((Object[])value, (Object[])value2)) {
                    return false;
                }
                continue;
            }
            else {
                if (value instanceof Object[]) {
                    if (value2 instanceof Object[]) {
                        array = (Parcelable[])value;
                        array2 = (Parcelable[])value2;
                    Label_0365:
                        while (true) {
                            Label_0464: {
                                if (array == array2) {
                                    break Label_0464;
                                }
                                length = array.length;
                                if (array2.length == length) {
                                    for (int i = 0; i < length; ++i) {
                                        parcelable = array[i];
                                        parcelable2 = array2[i];
                                        if (parcelable == null) {
                                            if (parcelable2 != null) {
                                                n = 0;
                                                break Label_0365;
                                            }
                                        }
                                        else if (parcelable instanceof Bundle) {
                                            if (!(parcelable2 instanceof Bundle) || !zza((Bundle)parcelable, (Bundle)parcelable2)) {
                                                n = 0;
                                                break Label_0365;
                                            }
                                        }
                                        else if (!parcelable.equals(parcelable2)) {
                                            n = 0;
                                            break Label_0365;
                                        }
                                    }
                                    break Label_0464;
                                }
                                n = 0;
                                if (n == 0) {
                                    return false;
                                }
                                continue Label_0365_Outer;
                            }
                            n = 1;
                            continue Label_0365;
                        }
                    }
                    return false;
                }
                if (value instanceof SparseArray) {
                    if (value2 instanceof SparseArray) {
                        sparseArray = (SparseArray)value;
                        sparseArray2 = (SparseArray)value2;
                    Label_0523:
                        while (true) {
                            Label_0646: {
                                if (sparseArray == sparseArray2) {
                                    break Label_0646;
                                }
                                size = sparseArray.size();
                                if (sparseArray2.size() == size) {
                                    for (int j = 0; j < size; ++j) {
                                        if (sparseArray.keyAt(j) != sparseArray2.keyAt(j)) {
                                            n2 = 0;
                                            break Label_0523;
                                        }
                                        value3 = sparseArray.valueAt(j);
                                        value4 = sparseArray2.valueAt(j);
                                        if (value3 == null) {
                                            if (value4 != null) {
                                                n2 = 0;
                                                break Label_0523;
                                            }
                                        }
                                        else if (value3 instanceof Bundle) {
                                            if (!(value4 instanceof Bundle) || !zza((Bundle)value3, (Bundle)value4)) {
                                                n2 = 0;
                                                break Label_0523;
                                            }
                                        }
                                        else if (!value3.equals(value4)) {
                                            n2 = 0;
                                            break Label_0523;
                                        }
                                    }
                                    break Label_0646;
                                }
                                n2 = 0;
                                if (n2 == 0) {
                                    return false;
                                }
                                continue Label_0523_Outer;
                            }
                            n2 = 1;
                            continue Label_0523;
                        }
                    }
                    return false;
                }
                if (!value.equals(value2)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
}
