// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.safeparcel;

import android.util.SparseLongArray;
import android.util.SparseIntArray;
import android.util.SparseBooleanArray;
import android.os.Parcelable;
import android.os.IBinder;
import android.util.SparseArray;
import android.os.Bundle;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;
import android.os.Parcel;

public class SafeParcelWriter
{
    private SafeParcelWriter() {
    }
    
    public static int beginObjectHeader(final Parcel parcel) {
        return zza(parcel, 20293);
    }
    
    public static void finishObjectHeader(final Parcel parcel, final int n) {
        zzb(parcel, n);
    }
    
    public static void writeBigDecimal(final Parcel parcel, int zza, final BigDecimal bigDecimal, final boolean b) {
        if (bigDecimal == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeByteArray(bigDecimal.unscaledValue().toByteArray());
        parcel.writeInt(bigDecimal.scale());
        zzb(parcel, zza);
    }
    
    public static void writeBigDecimalArray(final Parcel parcel, int i, final BigDecimal[] array, final boolean b) {
        final int n = 0;
        if (array == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        for (i = n; i < length; ++i) {
            parcel.writeByteArray(array[i].unscaledValue().toByteArray());
            parcel.writeInt(array[i].scale());
        }
        zzb(parcel, zza);
    }
    
    public static void writeBigInteger(final Parcel parcel, int zza, final BigInteger bigInteger, final boolean b) {
        if (bigInteger == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeByteArray(bigInteger.toByteArray());
        zzb(parcel, zza);
    }
    
    public static void writeBigIntegerArray(final Parcel parcel, int i, final BigInteger[] array, final boolean b) {
        final int n = 0;
        if (array == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        for (i = n; i < length; ++i) {
            parcel.writeByteArray(array[i].toByteArray());
        }
        zzb(parcel, zza);
    }
    
    public static void writeBoolean(final Parcel parcel, int n, final boolean b) {
        zzb(parcel, n, 4);
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
    
    public static void writeBooleanArray(final Parcel parcel, int zza, final boolean[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeBooleanArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeBooleanList(final Parcel parcel, int i, final List<Boolean> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        int n;
        for (i = 0; i < size; ++i) {
            if (list.get(i)) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeInt(n);
        }
        zzb(parcel, zza);
    }
    
    public static void writeBooleanObject(final Parcel parcel, int n, final Boolean b, final boolean b2) {
        final int n2 = 0;
        if (b == null) {
            if (b2) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 4);
        n = n2;
        if (b) {
            n = 1;
        }
        parcel.writeInt(n);
    }
    
    public static void writeBundle(final Parcel parcel, int zza, final Bundle bundle, final boolean b) {
        if (bundle == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeBundle(bundle);
        zzb(parcel, zza);
    }
    
    public static void writeByte(final Parcel parcel, final int n, final byte b) {
        zzb(parcel, n, 4);
        parcel.writeInt((int)b);
    }
    
    public static void writeByteArray(final Parcel parcel, int zza, final byte[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeByteArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeByteArrayArray(final Parcel parcel, int i, final byte[][] array, final boolean b) {
        final int n = 0;
        if (array == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        for (i = n; i < length; ++i) {
            parcel.writeByteArray(array[i]);
        }
        zzb(parcel, zza);
    }
    
    public static void writeByteArraySparseArray(final Parcel parcel, int i, final SparseArray<byte[]> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcel.writeByteArray((byte[])sparseArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeChar(final Parcel parcel, final int n, final char c) {
        zzb(parcel, n, 4);
        parcel.writeInt((int)c);
    }
    
    public static void writeCharArray(final Parcel parcel, int zza, final char[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeCharArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeDouble(final Parcel parcel, final int n, final double n2) {
        zzb(parcel, n, 8);
        parcel.writeDouble(n2);
    }
    
    public static void writeDoubleArray(final Parcel parcel, int zza, final double[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeDoubleArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeDoubleList(final Parcel parcel, int i, final List<Double> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeDouble((double)list.get(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeDoubleObject(final Parcel parcel, final int n, final Double n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 8);
        parcel.writeDouble((double)n2);
    }
    
    public static void writeDoubleSparseArray(final Parcel parcel, int i, final SparseArray<Double> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcel.writeDouble((double)sparseArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeFloat(final Parcel parcel, final int n, final float n2) {
        zzb(parcel, n, 4);
        parcel.writeFloat(n2);
    }
    
    public static void writeFloatArray(final Parcel parcel, int zza, final float[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeFloatArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeFloatList(final Parcel parcel, int i, final List<Float> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeFloat((float)list.get(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeFloatObject(final Parcel parcel, final int n, final Float n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 4);
        parcel.writeFloat((float)n2);
    }
    
    public static void writeFloatSparseArray(final Parcel parcel, int i, final SparseArray<Float> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcel.writeFloat((float)sparseArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeIBinder(final Parcel parcel, int zza, final IBinder binder, final boolean b) {
        if (binder == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeStrongBinder(binder);
        zzb(parcel, zza);
    }
    
    public static void writeIBinderArray(final Parcel parcel, int zza, final IBinder[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeBinderArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeIBinderList(final Parcel parcel, int zza, final List<IBinder> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeBinderList((List)list);
        zzb(parcel, zza);
    }
    
    public static void writeIBinderSparseArray(final Parcel parcel, int i, final SparseArray<IBinder> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcel.writeStrongBinder((IBinder)sparseArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeInt(final Parcel parcel, final int n, final int n2) {
        zzb(parcel, n, 4);
        parcel.writeInt(n2);
    }
    
    public static void writeIntArray(final Parcel parcel, int zza, final int[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeIntArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeIntegerList(final Parcel parcel, int i, final List<Integer> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt((int)list.get(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeIntegerObject(final Parcel parcel, final int n, final Integer n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 4);
        parcel.writeInt((int)n2);
    }
    
    public static void writeList(final Parcel parcel, int zza, final List list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeList(list);
        zzb(parcel, zza);
    }
    
    public static void writeLong(final Parcel parcel, final int n, final long n2) {
        zzb(parcel, n, 8);
        parcel.writeLong(n2);
    }
    
    public static void writeLongArray(final Parcel parcel, int zza, final long[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeLongArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeLongList(final Parcel parcel, int i, final List<Long> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeLong((long)list.get(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeLongObject(final Parcel parcel, final int n, final Long n2, final boolean b) {
        if (n2 == null) {
            if (b) {
                zzb(parcel, n, 0);
            }
            return;
        }
        zzb(parcel, n, 8);
        parcel.writeLong((long)n2);
    }
    
    public static void writeParcel(final Parcel parcel, int zza, final Parcel parcel2, final boolean b) {
        if (parcel2 == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.appendFrom(parcel2, 0, parcel2.dataSize());
        zzb(parcel, zza);
    }
    
    public static void writeParcelArray(final Parcel parcel, int i, final Parcel[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        Parcel parcel2;
        for (i = 0; i < length; ++i) {
            parcel2 = array[i];
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            }
            else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, zza);
    }
    
    public static void writeParcelList(final Parcel parcel, int i, final List<Parcel> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        Parcel parcel2;
        for (i = 0; i < size; ++i) {
            parcel2 = list.get(i);
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            }
            else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, zza);
    }
    
    public static void writeParcelSparseArray(final Parcel parcel, int i, final SparseArray<Parcel> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        Parcel parcel2;
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcel2 = (Parcel)sparseArray.valueAt(i);
            if (parcel2 != null) {
                parcel.writeInt(parcel2.dataSize());
                parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            }
            else {
                parcel.writeInt(0);
            }
        }
        zzb(parcel, zza);
    }
    
    public static void writeParcelable(final Parcel parcel, int zza, final Parcelable parcelable, final int n, final boolean b) {
        if (parcelable == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcelable.writeToParcel(parcel, n);
        zzb(parcel, zza);
    }
    
    public static void writeShort(final Parcel parcel, final int n, final short n2) {
        zzb(parcel, n, 4);
        parcel.writeInt((int)n2);
    }
    
    public static void writeSparseBooleanArray(final Parcel parcel, int zza, final SparseBooleanArray sparseBooleanArray, final boolean b) {
        if (sparseBooleanArray == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeSparseBooleanArray(sparseBooleanArray);
        zzb(parcel, zza);
    }
    
    public static void writeSparseIntArray(final Parcel parcel, int i, final SparseIntArray sparseIntArray, final boolean b) {
        final int n = 0;
        if (sparseIntArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseIntArray.size();
        parcel.writeInt(size);
        for (i = n; i < size; ++i) {
            parcel.writeInt(sparseIntArray.keyAt(i));
            parcel.writeInt(sparseIntArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeSparseLongArray(final Parcel parcel, int i, final SparseLongArray sparseLongArray, final boolean b) {
        final int n = 0;
        if (sparseLongArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseLongArray.size();
        parcel.writeInt(size);
        for (i = n; i < size; ++i) {
            parcel.writeInt(sparseLongArray.keyAt(i));
            parcel.writeLong(sparseLongArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static void writeString(final Parcel parcel, int zza, final String s, final boolean b) {
        if (s == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeString(s);
        zzb(parcel, zza);
    }
    
    public static void writeStringArray(final Parcel parcel, int zza, final String[] array, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeStringArray(array);
        zzb(parcel, zza);
    }
    
    public static void writeStringList(final Parcel parcel, int zza, final List<String> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, zza, 0);
            }
            return;
        }
        zza = zza(parcel, zza);
        parcel.writeStringList((List)list);
        zzb(parcel, zza);
    }
    
    public static void writeStringSparseArray(final Parcel parcel, int i, final SparseArray<String> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcel.writeString((String)sparseArray.valueAt(i));
        }
        zzb(parcel, zza);
    }
    
    public static <T extends Parcelable> void writeTypedArray(final Parcel parcel, int i, final T[] array, final int n, final boolean b) {
        if (array == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int length = array.length;
        parcel.writeInt(length);
        Parcelable parcelable;
        for (i = 0; i < length; ++i) {
            parcelable = array[i];
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, n);
            }
        }
        zzb(parcel, zza);
    }
    
    public static <T extends Parcelable> void writeTypedList(final Parcel parcel, int i, final List<T> list, final boolean b) {
        if (list == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = list.size();
        parcel.writeInt(size);
        Parcelable parcelable;
        for (i = 0; i < size; ++i) {
            parcelable = list.get(i);
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, 0);
            }
        }
        zzb(parcel, zza);
    }
    
    public static <T extends Parcelable> void writeTypedSparseArray(final Parcel parcel, int i, final SparseArray<T> sparseArray, final boolean b) {
        if (sparseArray == null) {
            if (b) {
                zzb(parcel, i, 0);
            }
            return;
        }
        final int zza = zza(parcel, i);
        final int size = sparseArray.size();
        parcel.writeInt(size);
        Parcelable parcelable;
        for (i = 0; i < size; ++i) {
            parcel.writeInt(sparseArray.keyAt(i));
            parcelable = (Parcelable)sparseArray.valueAt(i);
            if (parcelable == null) {
                parcel.writeInt(0);
            }
            else {
                zza(parcel, parcelable, 0);
            }
        }
        zzb(parcel, zza);
    }
    
    private static int zza(final Parcel parcel, final int n) {
        parcel.writeInt(0xFFFF0000 | n);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }
    
    private static <T extends Parcelable> void zza(final Parcel parcel, final T t, int dataPosition) {
        final int dataPosition2 = parcel.dataPosition();
        parcel.writeInt(1);
        final int dataPosition3 = parcel.dataPosition();
        t.writeToParcel(parcel, dataPosition);
        dataPosition = parcel.dataPosition();
        parcel.setDataPosition(dataPosition2);
        parcel.writeInt(dataPosition - dataPosition3);
        parcel.setDataPosition(dataPosition);
    }
    
    private static void zzb(final Parcel parcel, final int n) {
        final int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(n - 4);
        parcel.writeInt(dataPosition - n);
        parcel.setDataPosition(dataPosition);
    }
    
    private static void zzb(final Parcel parcel, final int n, final int n2) {
        if (n2 >= 65535) {
            parcel.writeInt(0xFFFF0000 | n);
            parcel.writeInt(n2);
            return;
        }
        parcel.writeInt(n2 << 16 | n);
    }
}
