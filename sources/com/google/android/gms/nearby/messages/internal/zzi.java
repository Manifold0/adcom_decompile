package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzi implements Creator<zzh> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ClientAppContext clientAppContext = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 3:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    clientAppContext = (ClientAppContext) SafeParcelReader.createParcelable(parcel, readHeader, ClientAppContext.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzh(i, iBinder, str, clientAppContext);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzh[i];
    }
}
