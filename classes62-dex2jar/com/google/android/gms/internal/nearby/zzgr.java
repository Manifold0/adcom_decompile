// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.util.Iterator;
import com.google.android.gms.common.util.Hex;
import java.util.UUID;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import android.util.Log;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import android.util.SparseArray;
import android.support.annotation.Nullable;
import java.util.List;
import android.os.ParcelUuid;

public final class zzgr
{
    private static final ParcelUuid zzgm;
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
    
    static {
        zzgm = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    }
    
    private zzgr(@Nullable final List<ParcelUuid> zzgo, @Nullable final SparseArray<byte[]> zzgp, @Nullable final Map<ParcelUuid, byte[]> zzgq, final int zzgn, final int zzgr, @Nullable final String zzgs, final byte[] zzgt) {
        this.zzgo = zzgo;
        this.zzgp = zzgp;
        this.zzgq = zzgq;
        this.zzgs = zzgs;
        this.zzgn = zzgn;
        this.zzgr = zzgr;
        this.zzgt = zzgt;
    }
    
    private static int zza(final byte[] array, int n, int i, final int n2, final List<ParcelUuid> list) {
        while (i > 0) {
            list.add(zze(zza(array, n, n2)));
            i -= n2;
            n += n2;
        }
        return n;
    }
    
    private static byte[] zza(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        return array2;
    }
    
    @VisibleForTesting
    public static zzgr zzd(@Nullable final byte[] array) {
        if (array == null) {
            return null;
        }
        int n = 0;
        int n2 = -1;
        final ArrayList<ParcelUuid> list = new ArrayList<ParcelUuid>();
        int n3 = Integer.MIN_VALUE;
        final SparseArray sparseArray = new SparseArray();
        final HashMap<ParcelUuid, byte[]> hashMap = new HashMap<ParcelUuid, byte[]>();
        String s = null;
    Label_0415_Outer:
        while (true) {
            while (true) {
                int n7 = 0;
                Label_0435: {
                    Label_0423: {
                        int n6 = 0;
                        while (true) {
                            Label_0402: {
                                Label_0321: {
                                    Label_0289: {
                                        Label_0270: {
                                            Label_0255: {
                                                try {
                                                    if (n >= array.length) {
                                                        break;
                                                    }
                                                    final int n4 = n + 1;
                                                    final int n5 = array[n] & 0xFF;
                                                    if (n5 == 0) {
                                                        break;
                                                    }
                                                    n6 = n5 - 1;
                                                    n7 = n4 + 1;
                                                    switch (array[n4] & 0xFF) {
                                                        case 4:
                                                        case 5: {
                                                            break;
                                                        }
                                                        case 6:
                                                        case 7: {
                                                            break Label_0255;
                                                        }
                                                        case 8:
                                                        case 9: {
                                                            break Label_0270;
                                                        }
                                                        case 22: {
                                                            break Label_0289;
                                                        }
                                                        case 255: {
                                                            break Label_0321;
                                                        }
                                                        default: {
                                                            break Label_0415;
                                                        }
                                                        case 2:
                                                        case 3: {
                                                            zza(array, n7, n6, 2, list);
                                                            break Label_0415;
                                                        }
                                                        case 1: {
                                                            break Label_0423;
                                                        }
                                                        case 10: {
                                                            break Label_0435;
                                                        }
                                                    }
                                                }
                                                catch (Exception ex) {
                                                    final String value = String.valueOf(Arrays.toString(array));
                                                    if (value.length() != 0) {
                                                        final String concat = "Unable to parse scan record: ".concat(value);
                                                        Log.w("BleRecord", concat, (Throwable)ex);
                                                        return null;
                                                    }
                                                    break Label_0402;
                                                }
                                                zza(array, n7, n6, 4, list);
                                                break Label_0415;
                                            }
                                            zza(array, n7, n6, 16, list);
                                            break Label_0415;
                                        }
                                        s = new String(zza(array, n7, n6));
                                        break Label_0415;
                                    }
                                    hashMap.put(zze(zza(array, n7, 2)), zza(array, n7 + 2, n6 - 2));
                                    break Label_0415;
                                }
                                sparseArray.put(((array[n7 + 1] & 0xFF) << 8) + (array[n7] & 0xFF), (Object)zza(array, n7 + 2, n6 - 2));
                                break Label_0415;
                            }
                            final String concat = new String("Unable to parse scan record: ");
                            continue;
                        }
                        n = n6 + n7;
                        continue Label_0415_Outer;
                    }
                    n2 = (array[n7] & 0xFF);
                    continue;
                }
                n3 = array[n7];
                continue;
            }
        }
        ArrayList<ParcelUuid> list2 = list;
        if (list.isEmpty()) {
            list2 = null;
        }
        return new zzgr(list2, (SparseArray<byte[]>)sparseArray, hashMap, n2, n3, s, array);
    }
    
    private static ParcelUuid zze(final byte[] array) {
        if (array == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        final int length = array.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException(new StringBuilder(38).append("uuidBytes length invalid - ").append(length).toString());
        }
        if (length == 16) {
            final ByteBuffer order = ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
        }
        long n;
        if (length == 2) {
            n = (array[0] & 0xFF) + (long)((array[1] & 0xFF) << 8);
        }
        else {
            n = (array[0] & 0xFF) + (long)((array[1] & 0xFF) << 8) + ((array[2] & 0xFF) << 16) + ((array[3] & 0xFF) << 24);
        }
        return new ParcelUuid(new UUID(zzgr.zzgm.getUuid().getMostSignificantBits() + (n << 32), zzgr.zzgm.getUuid().getLeastSignificantBits()));
    }
    
    @Override
    public final boolean equals(final Object o) {
        return o == this || (o instanceof zzgr && Arrays.equals(this.zzgt, ((zzgr)o).zzgt));
    }
    
    @Override
    public final int hashCode() {
        return Arrays.hashCode(this.zzgt);
    }
    
    @Override
    public final String toString() {
        final int n = 0;
        final int zzgn = this.zzgn;
        final String value = String.valueOf(this.zzgo);
        final SparseArray<byte[]> zzgp = this.zzgp;
        final StringBuilder sb = new StringBuilder();
        String string;
        if (zzgp.size() <= 0) {
            string = "{}";
        }
        else {
            sb.append('{');
            for (int i = 0; i < zzgp.size(); ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                final int key = zzgp.keyAt(i);
                final byte[] array = (byte[])zzgp.valueAt(i);
                sb.append(key);
                sb.append('=');
                String bytesToStringUppercase;
                if (array == null) {
                    bytesToStringUppercase = null;
                }
                else {
                    bytesToStringUppercase = Hex.bytesToStringUppercase(array);
                }
                sb.append(bytesToStringUppercase);
            }
            sb.append('}');
            string = sb.toString();
        }
        final Map<ParcelUuid, byte[]> zzgq = this.zzgq;
        final StringBuilder sb2 = new StringBuilder();
        String string2;
        if (zzgq.keySet().size() <= 0) {
            string2 = "{}";
        }
        else {
            sb2.append('{');
            final Iterator<Map.Entry<ParcelUuid, byte[]>> iterator = zzgq.entrySet().iterator();
            int n2 = n;
            while (iterator.hasNext()) {
                final Map.Entry<ParcelUuid, byte[]> entry = iterator.next();
                if (n2 > 0) {
                    sb2.append(", ");
                }
                sb2.append(entry.getKey());
                sb2.append('=');
                final byte[] array2 = entry.getValue();
                String bytesToStringUppercase2;
                if (array2 == null) {
                    bytesToStringUppercase2 = null;
                }
                else {
                    bytesToStringUppercase2 = Hex.bytesToStringUppercase(array2);
                }
                sb2.append(bytesToStringUppercase2);
                ++n2;
            }
            sb2.append('}');
            string2 = sb2.toString();
        }
        final int zzgr = this.zzgr;
        final String zzgs = this.zzgs;
        return new StringBuilder(String.valueOf(value).length() + 139 + String.valueOf(string).length() + String.valueOf(string2).length() + String.valueOf(zzgs).length()).append("BleRecord [mAdvertiseFlags=").append(zzgn).append(", mServiceUuids=").append(value).append(", mManufacturerSpecificData=").append(string).append(", mServiceData=").append(string2).append(", mTxPowerLevel=").append(zzgr).append(", mDeviceName=").append(zzgs).append("]").toString();
    }
}
