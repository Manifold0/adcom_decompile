package com.google.android.gms.internal.nearby;

import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.common.util.VisibleForTesting;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public final class zzgr {
    private static final ParcelUuid zzgm = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    private final int zzgn;
    @Nullable
    private final List<ParcelUuid> zzgo;
    @Nullable
    private final SparseArray<byte[]> zzgp;
    @Nullable
    private final Map<ParcelUuid, byte[]> zzgq;
    private final int zzgr;
    @Nullable
    private final String zzgs;
    private final byte[] zzgt;

    private zzgr(@Nullable List<ParcelUuid> list, @Nullable SparseArray<byte[]> sparseArray, @Nullable Map<ParcelUuid, byte[]> map, int i, int i2, @Nullable String str, byte[] bArr) {
        this.zzgo = list;
        this.zzgp = sparseArray;
        this.zzgq = map;
        this.zzgs = str;
        this.zzgn = i;
        this.zzgr = i2;
        this.zzgt = bArr;
    }

    private static int zza(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(zze(zza(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] zza(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return obj;
    }

    @VisibleForTesting
    public static zzgr zzd(@Nullable byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i = 0;
        int i2 = -1;
        List arrayList = new ArrayList();
        int i3 = Integer.MIN_VALUE;
        SparseArray sparseArray = new SparseArray();
        Map hashMap = new HashMap();
        String str = null;
        while (i < bArr.length) {
            try {
                int i4 = i + 1;
                i = bArr[i] & 255;
                if (i != 0) {
                    i--;
                    int i5 = i4 + 1;
                    switch (bArr[i4] & 255) {
                        case 1:
                            i2 = bArr[i5] & 255;
                            break;
                        case 2:
                        case 3:
                            zza(bArr, i5, i, 2, arrayList);
                            break;
                        case 4:
                        case 5:
                            zza(bArr, i5, i, 4, arrayList);
                            break;
                        case 6:
                        case 7:
                            zza(bArr, i5, i, 16, arrayList);
                            break;
                        case 8:
                        case 9:
                            str = new String(zza(bArr, i5, i));
                            break;
                        case 10:
                            i3 = bArr[i5];
                            break;
                        case 22:
                            hashMap.put(zze(zza(bArr, i5, 2)), zza(bArr, i5 + 2, i - 2));
                            break;
                        case 255:
                            sparseArray.put(((bArr[i5 + 1] & 255) << 8) + (bArr[i5] & 255), zza(bArr, i5 + 2, i - 2));
                            break;
                        default:
                            break;
                    }
                    i += i5;
                } else {
                    if (arrayList.isEmpty()) {
                        arrayList = null;
                    }
                    return new zzgr(arrayList, sparseArray, hashMap, i2, i3, str, bArr);
                }
            } catch (Throwable e) {
                Throwable th = e;
                String str2 = "BleRecord";
                String str3 = "Unable to parse scan record: ";
                String valueOf = String.valueOf(Arrays.toString(bArr));
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), th);
                return null;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new zzgr(arrayList, sparseArray, hashMap, i2, i3, str, bArr);
    }

    private static ParcelUuid zze(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        } else if (length == 16) {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
        } else {
            return new ParcelUuid(new UUID(zzgm.getUuid().getMostSignificantBits() + ((length == 2 ? ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8)) : ((((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8))) + ((long) ((bArr[2] & 255) << 16))) + ((long) ((bArr[3] & 255) << 24))) << 32), zzgm.getUuid().getLeastSignificantBits()));
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgr)) {
            return false;
        }
        return Arrays.equals(this.zzgt, ((zzgr) obj).zzgt);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzgt);
    }

    public final String toString() {
        String str;
        String str2;
        int i = 0;
        int i2 = this.zzgn;
        String valueOf = String.valueOf(this.zzgo);
        SparseArray sparseArray = this.zzgp;
        StringBuilder stringBuilder = new StringBuilder();
        if (sparseArray.size() <= 0) {
            str = "{}";
        } else {
            stringBuilder.append('{');
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                if (i3 > 0) {
                    stringBuilder.append(", ");
                }
                byte[] bArr = (byte[]) sparseArray.valueAt(i3);
                stringBuilder.append(sparseArray.keyAt(i3));
                stringBuilder.append('=');
                stringBuilder.append(bArr == null ? null : Hex.bytesToStringUppercase(bArr));
            }
            stringBuilder.append('}');
            str = stringBuilder.toString();
        }
        Map map = this.zzgq;
        StringBuilder stringBuilder2 = new StringBuilder();
        if (map.keySet().size() <= 0) {
            str2 = "{}";
        } else {
            stringBuilder2.append('{');
            for (Entry entry : map.entrySet()) {
                if (i > 0) {
                    stringBuilder2.append(", ");
                }
                stringBuilder2.append(entry.getKey());
                stringBuilder2.append('=');
                bArr = (byte[]) entry.getValue();
                stringBuilder2.append(bArr == null ? null : Hex.bytesToStringUppercase(bArr));
                i++;
            }
            stringBuilder2.append('}');
            str2 = stringBuilder2.toString();
        }
        i = this.zzgr;
        String str3 = this.zzgs;
        return new StringBuilder((((String.valueOf(valueOf).length() + 139) + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()).append("BleRecord [mAdvertiseFlags=").append(i2).append(", mServiceUuids=").append(valueOf).append(", mManufacturerSpecificData=").append(str).append(", mServiceData=").append(str2).append(", mTxPowerLevel=").append(i).append(", mDeviceName=").append(str3).append(RequestParameters.RIGHT_BRACKETS).toString();
    }
}
