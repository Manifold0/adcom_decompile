// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.safeparcel;

import java.util.List;
import android.util.SparseLongArray;
import android.util.SparseIntArray;
import android.util.SparseBooleanArray;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.IBinder;
import android.util.SparseArray;
import android.os.Bundle;
import java.util.ArrayList;
import java.math.BigInteger;
import java.math.BigDecimal;
import android.os.Parcel;

public class SafeParcelReader
{
    private SafeParcelReader() {
    }
    
    public static BigDecimal createBigDecimal(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        final int int1 = parcel.readInt();
        parcel.setDataPosition(size + dataPosition);
        return new BigDecimal(new BigInteger(byteArray), int1);
    }
    
    public static BigDecimal[] createBigDecimalArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final BigDecimal[] array = new BigDecimal[int1];
        for (i = 0; i < int1; ++i) {
            array[i] = new BigDecimal(new BigInteger(parcel.createByteArray()), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + size);
        return array;
    }
    
    public static BigInteger createBigInteger(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(size + dataPosition);
        return new BigInteger(byteArray);
    }
    
    public static BigInteger[] createBigIntegerArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final BigInteger[] array = new BigInteger[int1];
        for (i = 0; i < int1; ++i) {
            array[i] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + size);
        return array;
    }
    
    public static boolean[] createBooleanArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final boolean[] booleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(size + dataPosition);
        return booleanArray;
    }
    
    public static ArrayList<Boolean> createBooleanList(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList<Boolean> list = new ArrayList<Boolean>();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readInt() != 0);
        }
        parcel.setDataPosition(dataPosition + size);
        return list;
    }
    
    public static Bundle createBundle(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final Bundle bundle = parcel.readBundle();
        parcel.setDataPosition(size + dataPosition);
        return bundle;
    }
    
    public static byte[] createByteArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final byte[] byteArray = parcel.createByteArray();
        parcel.setDataPosition(size + dataPosition);
        return byteArray;
    }
    
    public static byte[][] createByteArrayArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final byte[][] array = new byte[int1][];
        for (i = 0; i < int1; ++i) {
            array[i] = parcel.createByteArray();
        }
        parcel.setDataPosition(dataPosition + size);
        return array;
    }
    
    public static SparseArray<byte[]> createByteArraySparseArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final SparseArray sparseArray = new SparseArray(int1);
        for (i = 0; i < int1; ++i) {
            sparseArray.append(parcel.readInt(), (Object)parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<byte[]>)sparseArray;
    }
    
    public static char[] createCharArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final char[] charArray = parcel.createCharArray();
        parcel.setDataPosition(size + dataPosition);
        return charArray;
    }
    
    public static double[] createDoubleArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final double[] doubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(size + dataPosition);
        return doubleArray;
    }
    
    public static ArrayList<Double> createDoubleList(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList<Double> list = new ArrayList<Double>();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readDouble());
        }
        parcel.setDataPosition(dataPosition + size);
        return list;
    }
    
    public static SparseArray<Double> createDoubleSparseArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final SparseArray sparseArray = new SparseArray();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            sparseArray.append(parcel.readInt(), (Object)parcel.readDouble());
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<Double>)sparseArray;
    }
    
    public static float[] createFloatArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final float[] floatArray = parcel.createFloatArray();
        parcel.setDataPosition(size + dataPosition);
        return floatArray;
    }
    
    public static ArrayList<Float> createFloatList(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList<Float> list = new ArrayList<Float>();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readFloat());
        }
        parcel.setDataPosition(dataPosition + size);
        return list;
    }
    
    public static SparseArray<Float> createFloatSparseArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final SparseArray sparseArray = new SparseArray();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            sparseArray.append(parcel.readInt(), (Object)parcel.readFloat());
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<Float>)sparseArray;
    }
    
    public static IBinder[] createIBinderArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final IBinder[] binderArray = parcel.createBinderArray();
        parcel.setDataPosition(size + dataPosition);
        return binderArray;
    }
    
    public static ArrayList<IBinder> createIBinderList(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList binderArrayList = parcel.createBinderArrayList();
        parcel.setDataPosition(size + dataPosition);
        return (ArrayList<IBinder>)binderArrayList;
    }
    
    public static SparseArray<IBinder> createIBinderSparseArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final SparseArray sparseArray = new SparseArray(int1);
        for (i = 0; i < int1; ++i) {
            sparseArray.append(parcel.readInt(), (Object)parcel.readStrongBinder());
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<IBinder>)sparseArray;
    }
    
    public static int[] createIntArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int[] intArray = parcel.createIntArray();
        parcel.setDataPosition(size + dataPosition);
        return intArray;
    }
    
    public static ArrayList<Integer> createIntegerList(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList<Integer> list = new ArrayList<Integer>();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + size);
        return list;
    }
    
    public static long[] createLongArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final long[] longArray = parcel.createLongArray();
        parcel.setDataPosition(size + dataPosition);
        return longArray;
    }
    
    public static ArrayList<Long> createLongList(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList<Long> list = new ArrayList<Long>();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            list.add(parcel.readLong());
        }
        parcel.setDataPosition(dataPosition + size);
        return list;
    }
    
    public static Parcel createParcel(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, size);
        parcel.setDataPosition(size + dataPosition);
        return obtain;
    }
    
    public static Parcel[] createParcelArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final Parcel[] array = new Parcel[int1];
        int int2;
        int dataPosition2;
        Parcel obtain;
        for (i = 0; i < int1; ++i) {
            int2 = parcel.readInt();
            if (int2 != 0) {
                dataPosition2 = parcel.dataPosition();
                obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, int2);
                array[i] = obtain;
                parcel.setDataPosition(int2 + dataPosition2);
            }
            else {
                array[i] = null;
            }
        }
        parcel.setDataPosition(dataPosition + size);
        return array;
    }
    
    public static ArrayList<Parcel> createParcelList(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final ArrayList<Parcel> list = new ArrayList<Parcel>();
        int int2;
        int dataPosition2;
        Parcel obtain;
        for (i = 0; i < int1; ++i) {
            int2 = parcel.readInt();
            if (int2 != 0) {
                dataPosition2 = parcel.dataPosition();
                obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, int2);
                list.add(obtain);
                parcel.setDataPosition(int2 + dataPosition2);
            }
            else {
                list.add(null);
            }
        }
        parcel.setDataPosition(dataPosition + size);
        return list;
    }
    
    public static SparseArray<Parcel> createParcelSparseArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final SparseArray sparseArray = new SparseArray();
        int int2;
        int int3;
        int dataPosition2;
        Parcel obtain;
        for (i = 0; i < int1; ++i) {
            int2 = parcel.readInt();
            int3 = parcel.readInt();
            if (int3 != 0) {
                dataPosition2 = parcel.dataPosition();
                obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, int3);
                sparseArray.append(int2, (Object)obtain);
                parcel.setDataPosition(dataPosition2 + int3);
            }
            else {
                sparseArray.append(int2, (Object)null);
            }
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<Parcel>)sparseArray;
    }
    
    public static <T extends Parcelable> T createParcelable(final Parcel parcel, int size, final Parcelable$Creator<T> parcelable$Creator) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final Parcelable parcelable = (Parcelable)parcelable$Creator.createFromParcel(parcel);
        parcel.setDataPosition(size + dataPosition);
        return (T)parcelable;
    }
    
    public static SparseBooleanArray createSparseBooleanArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final SparseBooleanArray sparseBooleanArray = parcel.readSparseBooleanArray();
        parcel.setDataPosition(size + dataPosition);
        return sparseBooleanArray;
    }
    
    public static SparseIntArray createSparseIntArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final SparseIntArray sparseIntArray = new SparseIntArray();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            sparseIntArray.append(parcel.readInt(), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + size);
        return sparseIntArray;
    }
    
    public static SparseLongArray createSparseLongArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final SparseLongArray sparseLongArray = new SparseLongArray();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            sparseLongArray.append(parcel.readInt(), parcel.readLong());
        }
        parcel.setDataPosition(dataPosition + size);
        return sparseLongArray;
    }
    
    public static String createString(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final String string = parcel.readString();
        parcel.setDataPosition(size + dataPosition);
        return string;
    }
    
    public static String[] createStringArray(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final String[] stringArray = parcel.createStringArray();
        parcel.setDataPosition(size + dataPosition);
        return stringArray;
    }
    
    public static ArrayList<String> createStringList(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList stringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(size + dataPosition);
        return (ArrayList<String>)stringArrayList;
    }
    
    public static SparseArray<String> createStringSparseArray(final Parcel parcel, int i) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final SparseArray sparseArray = new SparseArray();
        int int1;
        for (int1 = parcel.readInt(), i = 0; i < int1; ++i) {
            sparseArray.append(parcel.readInt(), (Object)parcel.readString());
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<String>)sparseArray;
    }
    
    public static <T> T[] createTypedArray(final Parcel parcel, int size, final Parcelable$Creator<T> parcelable$Creator) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final Object[] typedArray = parcel.createTypedArray((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(size + dataPosition);
        return (T[])typedArray;
    }
    
    public static <T> ArrayList<T> createTypedList(final Parcel parcel, int size, final Parcelable$Creator<T> parcelable$Creator) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)parcelable$Creator);
        parcel.setDataPosition(size + dataPosition);
        return (ArrayList<T>)typedArrayList;
    }
    
    public static <T> SparseArray<T> createTypedSparseArray(final Parcel parcel, int i, final Parcelable$Creator<T> parcelable$Creator) {
        final int size = readSize(parcel, i);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final int int1 = parcel.readInt();
        final SparseArray sparseArray = new SparseArray();
        int int2;
        Object fromParcel;
        for (i = 0; i < int1; ++i) {
            int2 = parcel.readInt();
            if (parcel.readInt() != 0) {
                fromParcel = parcelable$Creator.createFromParcel(parcel);
            }
            else {
                fromParcel = null;
            }
            sparseArray.append(int2, fromParcel);
        }
        parcel.setDataPosition(dataPosition + size);
        return (SparseArray<T>)sparseArray;
    }
    
    public static void ensureAtEnd(final Parcel parcel, final int n) {
        if (parcel.dataPosition() != n) {
            throw new ParseException(new StringBuilder(37).append("Overread allowed size end=").append(n).toString(), parcel);
        }
    }
    
    public static int getFieldId(final int n) {
        return 0xFFFF & n;
    }
    
    public static boolean readBoolean(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt() != 0;
    }
    
    public static Boolean readBooleanObject(final Parcel parcel, final int n) {
        final int size = readSize(parcel, n);
        if (size == 0) {
            return null;
        }
        zza(parcel, n, size, 4);
        return parcel.readInt() != 0;
    }
    
    public static byte readByte(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (byte)parcel.readInt();
    }
    
    public static char readChar(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (char)parcel.readInt();
    }
    
    public static double readDouble(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readDouble();
    }
    
    public static Double readDoubleObject(final Parcel parcel, final int n) {
        final int size = readSize(parcel, n);
        if (size == 0) {
            return null;
        }
        zza(parcel, n, size, 8);
        return parcel.readDouble();
    }
    
    public static float readFloat(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readFloat();
    }
    
    public static Float readFloatObject(final Parcel parcel, final int n) {
        final int size = readSize(parcel, n);
        if (size == 0) {
            return null;
        }
        zza(parcel, n, size, 4);
        return parcel.readFloat();
    }
    
    public static int readHeader(final Parcel parcel) {
        return parcel.readInt();
    }
    
    public static IBinder readIBinder(final Parcel parcel, int size) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return null;
        }
        final IBinder strongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(size + dataPosition);
        return strongBinder;
    }
    
    public static int readInt(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt();
    }
    
    public static Integer readIntegerObject(final Parcel parcel, final int n) {
        final int size = readSize(parcel, n);
        if (size == 0) {
            return null;
        }
        zza(parcel, n, size, 4);
        return parcel.readInt();
    }
    
    public static void readList(final Parcel parcel, int size, final List list, final ClassLoader classLoader) {
        size = readSize(parcel, size);
        final int dataPosition = parcel.dataPosition();
        if (size == 0) {
            return;
        }
        parcel.readList(list, classLoader);
        parcel.setDataPosition(size + dataPosition);
    }
    
    public static long readLong(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readLong();
    }
    
    public static Long readLongObject(final Parcel parcel, final int n) {
        final int size = readSize(parcel, n);
        if (size == 0) {
            return null;
        }
        zza(parcel, n, size, 8);
        return parcel.readLong();
    }
    
    public static short readShort(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (short)parcel.readInt();
    }
    
    public static int readSize(final Parcel parcel, final int n) {
        if ((n & 0xFFFF0000) != 0xFFFF0000) {
            return n >> 16 & 0xFFFF;
        }
        return parcel.readInt();
    }
    
    public static void skipUnknownField(final Parcel parcel, final int n) {
        parcel.setDataPosition(readSize(parcel, n) + parcel.dataPosition());
    }
    
    public static int validateObjectHeader(final Parcel parcel) {
        final int header = readHeader(parcel);
        final int size = readSize(parcel, header);
        final int dataPosition = parcel.dataPosition();
        if (getFieldId(header) != 20293) {
            final String value = String.valueOf(Integer.toHexString(header));
            String concat;
            if (value.length() != 0) {
                concat = "Expected object header. Got 0x".concat(value);
            }
            else {
                concat = new String("Expected object header. Got 0x");
            }
            throw new ParseException(concat, parcel);
        }
        final int n = dataPosition + size;
        if (n < dataPosition || n > parcel.dataSize()) {
            throw new ParseException(new StringBuilder(54).append("Size read is invalid start=").append(dataPosition).append(" end=").append(n).toString(), parcel);
        }
        return n;
    }
    
    private static void zza(final Parcel parcel, int size, final int n) {
        size = readSize(parcel, size);
        if (size != n) {
            final String hexString = Integer.toHexString(size);
            throw new ParseException(new StringBuilder(String.valueOf(hexString).length() + 46).append("Expected size ").append(n).append(" got ").append(size).append(" (0x").append(hexString).append(")").toString(), parcel);
        }
    }
    
    private static void zza(final Parcel parcel, final int n, final int n2, final int n3) {
        if (n2 != n3) {
            final String hexString = Integer.toHexString(n2);
            throw new ParseException(new StringBuilder(String.valueOf(hexString).length() + 46).append("Expected size ").append(n3).append(" got ").append(n2).append(" (0x").append(hexString).append(")").toString(), parcel);
        }
    }
    
    public static class ParseException extends RuntimeException
    {
        public ParseException(final String s, final Parcel parcel) {
            super(new StringBuilder(String.valueOf(s).length() + 41).append(s).append(" Parcel: pos=").append(parcel.dataPosition()).append(" size=").append(parcel.dataSize()).toString());
        }
    }
}
