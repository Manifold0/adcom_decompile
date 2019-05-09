package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzfx implements Creator<zzfu> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzfh zzfh = null;
        String[] strArr = null;
        IBinder iBinder = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 2:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 3:
                    zzfh = (zzfh) SafeParcelReader.createParcelable(parcel, readHeader, zzfh.CREATOR);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzfu(iBinder, strArr, zzfh, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzfu[i];
    }
}
