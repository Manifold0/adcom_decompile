package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.Set;

public final class zzc {
    public static int zza(@Nullable Bundle bundle) {
        if (bundle == null) {
            return 0;
        }
        int size = bundle.size();
        if (size == 0) {
            return 0;
        }
        String[] strArr = (String[]) bundle.keySet().toArray(new String[size]);
        Arrays.sort(strArr);
        int i = 1;
        for (String str : strArr) {
            int i2 = i * 31;
            Object obj = bundle.get(str);
            if (obj == null) {
                i = i2;
            } else if (obj instanceof Bundle) {
                i = zza((Bundle) obj) + i2;
            } else if (obj instanceof byte[]) {
                i = Arrays.hashCode((byte[]) obj) + i2;
            } else if (obj instanceof char[]) {
                i = Arrays.hashCode((char[]) obj) + i2;
            } else if (obj instanceof short[]) {
                i = Arrays.hashCode((short[]) obj) + i2;
            } else if (obj instanceof float[]) {
                i = Arrays.hashCode((float[]) obj) + i2;
            } else if (obj instanceof CharSequence[]) {
                i = Arrays.hashCode((CharSequence[]) obj) + i2;
            } else if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                r10 = objArr.length;
                r6 = 0;
                r3 = 1;
                while (r6 < r10) {
                    r2 = objArr[r6];
                    r3 *= 31;
                    r2 = r2 instanceof Bundle ? zza((Bundle) r2) + r3 : r2 != null ? r2.hashCode() + r3 : r3;
                    r6++;
                    r3 = r2;
                }
                i = i2 + r3;
            } else if (obj instanceof SparseArray) {
                SparseArray sparseArray = (SparseArray) obj;
                r10 = sparseArray.size();
                r2 = 1;
                for (r6 = 0; r6 < r10; r6++) {
                    r3 = ((r2 * 31) + sparseArray.keyAt(r6)) * 31;
                    r2 = sparseArray.valueAt(r6);
                    r2 = r2 instanceof Bundle ? zza((Bundle) r2) + r3 : r2 != null ? r2.hashCode() + r3 : r3;
                }
                i = i2 + r2;
            } else {
                i = obj.hashCode() + i2;
            }
        }
        return i;
    }

    public static boolean zza(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        if (bundle == null || bundle2 == null) {
            return false;
        }
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        Set<String> keySet = bundle.keySet();
        if (!keySet.equals(bundle2.keySet())) {
            return false;
        }
        for (String str : keySet) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (obj instanceof Bundle) {
                if (!(obj2 instanceof Bundle) || !zza((Bundle) obj, (Bundle) obj2)) {
                    return false;
                }
            } else if (obj instanceof byte[]) {
                if (!(obj2 instanceof byte[]) || !Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof char[]) {
                if (!(obj2 instanceof char[]) || !Arrays.equals((char[]) obj, (char[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof short[]) {
                if (!(obj2 instanceof short[]) || !Arrays.equals((short[]) obj, (short[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof float[]) {
                if (!(obj2 instanceof float[]) || !Arrays.equals((float[]) obj, (float[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof CharSequence[]) {
                if (!(obj2 instanceof CharSequence[]) || !Arrays.equals((CharSequence[]) obj, (CharSequence[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof Object[]) {
                if (obj2 instanceof Object[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    Parcelable[] parcelableArr2 = (Parcelable[]) obj2;
                    if (parcelableArr != parcelableArr2) {
                        r8 = parcelableArr.length;
                        if (parcelableArr2.length != r8) {
                            r0 = null;
                        } else {
                            for (r6 = 0; r6 < r8; r6++) {
                                r0 = parcelableArr[r6];
                                r3 = parcelableArr2[r6];
                                if (r0 == null) {
                                    if (r3 != null) {
                                        r0 = null;
                                        break;
                                    }
                                } else if (r0 instanceof Bundle) {
                                    if (!(r3 instanceof Bundle) || !zza((Bundle) r0, (Bundle) r3)) {
                                        r0 = null;
                                        break;
                                    }
                                } else if (!r0.equals(r3)) {
                                    r0 = null;
                                    break;
                                }
                            }
                        }
                        if (r0 != null) {
                        }
                    }
                    r0 = 1;
                    if (r0 != null) {
                    }
                }
                return false;
            } else if (obj instanceof SparseArray) {
                if (obj2 instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    SparseArray sparseArray2 = (SparseArray) obj2;
                    if (sparseArray != sparseArray2) {
                        r8 = sparseArray.size();
                        if (sparseArray2.size() != r8) {
                            r0 = null;
                        } else {
                            for (r6 = 0; r6 < r8; r6++) {
                                if (sparseArray.keyAt(r6) != sparseArray2.keyAt(r6)) {
                                    r0 = null;
                                    break;
                                }
                                r0 = sparseArray.valueAt(r6);
                                r3 = sparseArray2.valueAt(r6);
                                if (r0 == null) {
                                    if (r3 != null) {
                                        r0 = null;
                                        break;
                                    }
                                } else if (r0 instanceof Bundle) {
                                    if (!(r3 instanceof Bundle) || !zza((Bundle) r0, (Bundle) r3)) {
                                        r0 = null;
                                        break;
                                    }
                                } else if (!r0.equals(r3)) {
                                    r0 = null;
                                    break;
                                }
                            }
                        }
                        if (r0 != null) {
                        }
                    }
                    r0 = 1;
                    if (r0 != null) {
                    }
                }
                return false;
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }
}
