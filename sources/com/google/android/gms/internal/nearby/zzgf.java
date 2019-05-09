package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.nearby.connection.DiscoveryOptions;

public final class zzgf implements Creator<zzgc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        DiscoveryOptions discoveryOptions = null;
        String str = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    iBinder3 = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 2:
                    iBinder2 = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 5:
                    discoveryOptions = (DiscoveryOptions) SafeParcelReader.createParcelable(parcel, readHeader, DiscoveryOptions.CREATOR);
                    break;
                case 6:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzgc(iBinder3, iBinder2, str, j, discoveryOptions, iBinder);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzgc[i];
    }
}
