package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.zza;
import java.util.List;

public final class zzfa implements Creator<zzez> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zza zza = null;
        List list = null;
        DataHolder dataHolder = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    dataHolder = (DataHolder) SafeParcelReader.createParcelable(parcel, readHeader, DataHolder.CREATOR);
                    break;
                case 3:
                    list = SafeParcelReader.createTypedList(parcel, readHeader, DriveId.CREATOR);
                    break;
                case 4:
                    zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    break;
                case 5:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzez(dataHolder, list, zza, z);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzez[i];
    }
}
