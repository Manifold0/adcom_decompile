package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.query.internal.FilterHolder;

public final class zzgh implements Creator<zzgg> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        FilterHolder filterHolder = null;
        DriveId driveId = null;
        String[] strArr = null;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 4:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, readHeader, DriveId.CREATOR);
                    break;
                case 5:
                    filterHolder = (FilterHolder) SafeParcelReader.createParcelable(parcel, readHeader, FilterHolder.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzgg(str, strArr, driveId, filterHolder);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzgg[i];
    }
}
