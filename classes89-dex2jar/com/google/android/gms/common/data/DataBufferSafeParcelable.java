// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
public class DataBufferSafeParcelable<T extends SafeParcelable> extends AbstractDataBuffer<T>
{
    private static final String[] zalo;
    private final Parcelable$Creator<T> zalp;
    
    static {
        zalo = new String[] { "data" };
    }
    
    @KeepForSdk
    public DataBufferSafeParcelable(final DataHolder dataHolder, final Parcelable$Creator<T> zalp) {
        super(dataHolder);
        this.zalp = zalp;
    }
    
    @KeepForSdk
    public static <T extends SafeParcelable> void addValue(final DataHolder.Builder builder, final T t) {
        final Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        final ContentValues contentValues = new ContentValues();
        contentValues.put("data", obtain.marshall());
        builder.withRow(contentValues);
        obtain.recycle();
    }
    
    @KeepForSdk
    public static DataHolder.Builder buildDataHolder() {
        return DataHolder.builder(DataBufferSafeParcelable.zalo);
    }
    
    @KeepForSdk
    @Override
    public T get(final int n) {
        final byte[] byteArray = this.mDataHolder.getByteArray("data", n, this.mDataHolder.getWindowIndex(n));
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(byteArray, 0, byteArray.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)this.zalp.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
}
