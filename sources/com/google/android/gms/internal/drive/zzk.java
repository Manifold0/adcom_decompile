package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zze;
import com.google.android.gms.drive.events.zzt;
import com.google.android.gms.drive.events.zzx;

public final class zzk implements Creator<zzj> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzt zzt = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zzx zzx = null;
        zze zze = null;
        DriveId driveId = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    driveId = (DriveId) SafeParcelReader.createParcelable(parcel, readHeader, DriveId.CREATOR);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    zze = (zze) SafeParcelReader.createParcelable(parcel, readHeader, zze.CREATOR);
                    break;
                case 5:
                    zzx = (zzx) SafeParcelReader.createParcelable(parcel, readHeader, zzx.CREATOR);
                    break;
                case 6:
                    zzt = (zzt) SafeParcelReader.createParcelable(parcel, readHeader, zzt.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzj(driveId, i, zze, zzx, zzt);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzj[i];
    }
}
