// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal.safeparcel;

import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;
import android.os.Bundle;
import com.google.android.gms.common.util.Base64Utils;
import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public final class SafeParcelableSerializer
{
    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromBytes(final byte[] array, final Parcelable$Creator<T> parcelable$Creator) {
        Preconditions.checkNotNull(parcelable$Creator);
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)parcelable$Creator.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromIntentExtra(final Intent intent, final String s, final Parcelable$Creator<T> parcelable$Creator) {
        final byte[] byteArrayExtra = intent.getByteArrayExtra(s);
        if (byteArrayExtra == null) {
            return null;
        }
        return deserializeFromBytes(byteArrayExtra, parcelable$Creator);
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> T deserializeFromString(final String s, final Parcelable$Creator<T> parcelable$Creator) {
        return deserializeFromBytes(Base64Utils.decodeUrlSafe(s), parcelable$Creator);
    }
    
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromBundle(final Bundle bundle, final String s, final Parcelable$Creator<T> parcelable$Creator) {
        final ArrayList list = (ArrayList)bundle.getSerializable(s);
        if (list == null) {
            return null;
        }
        final ArrayList list2 = new ArrayList<T>(list.size());
        final ArrayList<Object> list3 = (ArrayList<Object>)list;
        final int size = list3.size();
        int i = 0;
        while (i < size) {
            final byte[] value = list3.get(i);
            ++i;
            list2.add(deserializeFromBytes(value, parcelable$Creator));
        }
        return (ArrayList<T>)list2;
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> ArrayList<T> deserializeIterableFromIntentExtra(final Intent intent, final String s, final Parcelable$Creator<T> parcelable$Creator) {
        final ArrayList list = (ArrayList)intent.getSerializableExtra(s);
        if (list == null) {
            return null;
        }
        final ArrayList list2 = new ArrayList<T>(list.size());
        final ArrayList<Object> list3 = (ArrayList<Object>)list;
        final int size = list3.size();
        int i = 0;
        while (i < size) {
            final byte[] value = list3.get(i);
            ++i;
            list2.add(deserializeFromBytes(value, parcelable$Creator));
        }
        return (ArrayList<T>)list2;
    }
    
    public static <T extends SafeParcelable> void serializeIterableToBundle(final Iterable<T> iterable, final Bundle bundle, final String s) {
        final ArrayList<byte[]> list = new ArrayList<byte[]>();
        final Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(serializeToBytes(iterator.next()));
        }
        bundle.putSerializable(s, (Serializable)list);
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> void serializeIterableToIntentExtra(final Iterable<T> iterable, final Intent intent, final String s) {
        final ArrayList<byte[]> list = new ArrayList<byte[]>();
        final Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(serializeToBytes(iterator.next()));
        }
        intent.putExtra(s, (Serializable)list);
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> byte[] serializeToBytes(final T t) {
        final Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> void serializeToIntentExtra(final T t, final Intent intent, final String s) {
        intent.putExtra(s, serializeToBytes(t));
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> String serializeToString(final T t) {
        return Base64Utils.encodeUrlSafe(serializeToBytes(t));
    }
}
