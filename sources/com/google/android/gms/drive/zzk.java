package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzk implements Creator<DriveId> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        long j = 0;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        int i = -1;
        long j2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DriveId(str, j2, j, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new DriveId[i];
    }
}
