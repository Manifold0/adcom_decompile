package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzn implements Creator<zzm> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z2 = true;
        int i = 0;
        int i2 = 0;
        boolean z3 = false;
        Contents contents = null;
        MetadataBundle metadataBundle = null;
        DriveId driveId = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, readHeader, DriveId.CREATOR);
                    break;
                case 3:
                    metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, readHeader, MetadataBundle.CREATOR);
                    break;
                case 4:
                    contents = (Contents) SafeParcelReader.createParcelable(parcel, readHeader, Contents.CREATOR);
                    break;
                case 5:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 6:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 9:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzm(driveId, metadataBundle, contents, z3, str, i2, i, z, z2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}
