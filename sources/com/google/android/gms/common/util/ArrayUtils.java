package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@KeepForSdk
@VisibleForTesting
public final class ArrayUtils {
    @KeepForSdk
    public static <T> boolean contains(T[] tArr, T t) {
        int length = tArr != null ? tArr.length : 0;
        for (int i = 0; i < length; i++) {
            if (Objects.equal(tArr[i], t)) {
                length = i;
                break;
            }
        }
        length = -1;
        if (length >= 0) {
            return true;
        }
        return false;
    }

    @KeepForSdk
    public static boolean contains(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static Integer[] toWrapperArray(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int length = iArr.length;
        Integer[] numArr = new Integer[length];
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    private ArrayUtils() {
    }

    @KeepForSdk
    public static <T> void writeArray(StringBuilder stringBuilder, T[] tArr) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(tArr[i].toString());
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder, int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(Integer.toString(iArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder, long[] jArr) {
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(Long.toString(jArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder, float[] fArr) {
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(Float.toString(fArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder, double[] dArr) {
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(Double.toString(dArr[i]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder, boolean[] zArr) {
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(Boolean.toString(zArr[i]));
        }
    }

    @KeepForSdk
    public static void writeStringArray(StringBuilder stringBuilder, String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\"").append(strArr[i]).append("\"");
        }
    }

    @KeepForSdk
    public static <T> T[] concat(T[]... tArr) {
        if (tArr.length == 0) {
            return (Object[]) Array.newInstance(tArr.getClass(), 0);
        }
        int i;
        int i2 = 0;
        for (T[] length : tArr) {
            i2 += length.length;
        }
        Object copyOf = Arrays.copyOf(tArr[0], i2);
        i2 = tArr[0].length;
        for (i = 1; i < tArr.length; i++) {
            Object obj = tArr[i];
            System.arraycopy(obj, 0, copyOf, i2, obj.length);
            i2 += obj.length;
        }
        return copyOf;
    }

    @KeepForSdk
    public static byte[] concatByteArrays(byte[]... bArr) {
        if (bArr.length == 0) {
            return new byte[0];
        }
        int i;
        int i2 = 0;
        for (byte[] length : bArr) {
            i2 += length.length;
        }
        Object copyOf = Arrays.copyOf(bArr[0], i2);
        i2 = bArr[0].length;
        for (i = 1; i < bArr.length; i++) {
            Object obj = bArr[i];
            System.arraycopy(obj, 0, copyOf, i2, obj.length);
            i2 += obj.length;
        }
        return copyOf;
    }

    @KeepForSdk
    public static <T> T[] appendToArray(T[] tArr, T t) {
        if (tArr == null && t == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        }
        T[] tArr2;
        if (tArr == null) {
            tArr2 = (Object[]) Array.newInstance(t.getClass(), 1);
        } else {
            tArr2 = Arrays.copyOf(tArr, tArr.length + 1);
        }
        tArr2[tArr2.length - 1] = t;
        return tArr2;
    }

    @KeepForSdk
    public static <T> T[] removeAll(T[] tArr, T... tArr2) {
        int i = 0;
        if (tArr == null) {
            return null;
        }
        if (tArr2 == null || tArr2.length == 0) {
            return Arrays.copyOf(tArr, tArr.length);
        }
        int i2;
        Object[] objArr = (Object[]) Array.newInstance(tArr2.getClass().getComponentType(), tArr.length);
        int i3;
        int i4;
        if (tArr2.length == 1) {
            int length = tArr.length;
            i3 = 0;
            i2 = 0;
            while (i3 < length) {
                Object obj = tArr[i3];
                if (Objects.equal(tArr2[0], obj)) {
                    i4 = i2;
                } else {
                    i4 = i2 + 1;
                    objArr[i2] = obj;
                }
                i3++;
                i2 = i4;
            }
        } else {
            i3 = tArr.length;
            i2 = 0;
            while (i < i3) {
                Object obj2 = tArr[i];
                if (contains((Object[]) tArr2, obj2)) {
                    i4 = i2;
                } else {
                    i4 = i2 + 1;
                    objArr[i2] = obj2;
                }
                i++;
                i2 = i4;
            }
        }
        if (objArr == null) {
            return null;
        }
        if (i2 != objArr.length) {
            return Arrays.copyOf(objArr, i2);
        }
        return objArr;
    }

    @KeepForSdk
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList();
    }

    @KeepForSdk
    public static <T> ArrayList<T> toArrayList(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList(r1);
        for (Object add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    @KeepForSdk
    public static int[] toPrimitiveArray(Collection<Integer> collection) {
        if (collection == null || collection.size() == 0) {
            return new int[0];
        }
        int[] iArr = new int[collection.size()];
        int i = 0;
        for (Integer intValue : collection) {
            int i2 = i + 1;
            iArr[i] = intValue.intValue();
            i = i2;
        }
        return iArr;
    }
}
