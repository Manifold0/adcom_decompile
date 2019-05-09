package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzv implements Creator<zzu> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Integer num = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        DriveId driveId = null;
        String str = null;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, readHeader, MetadataBundle.CREATOR);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, readHeader, DriveId.CREATOR);
                    break;
                case 6:
                    num = SafeParcelReader.readIntegerObject(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzu(metadataBundle, i, str, driveId, num);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
