package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzh implements Creator<FilterHolder> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzz zzz = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzl zzl = null;
        zzn zzn = null;
        zzt zzt = null;
        zzp zzp = null;
        zzv zzv = null;
        zzr zzr = null;
        zzd zzd = null;
        zzb zzb = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzb = (zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.CREATOR);
                    break;
                case 2:
                    zzd = (zzd) SafeParcelReader.createParcelable(parcel, readHeader, zzd.CREATOR);
                    break;
                case 3:
                    zzr = (zzr) SafeParcelReader.createParcelable(parcel, readHeader, zzr.CREATOR);
                    break;
                case 4:
                    zzv = (zzv) SafeParcelReader.createParcelable(parcel, readHeader, zzv.CREATOR);
                    break;
                case 5:
                    zzp = (zzp) SafeParcelReader.createParcelable(parcel, readHeader, zzp.CREATOR);
                    break;
                case 6:
                    zzt = (zzt) SafeParcelReader.createParcelable(parcel, readHeader, zzt.CREATOR);
                    break;
                case 7:
                    zzn = (zzn) SafeParcelReader.createParcelable(parcel, readHeader, zzn.CREATOR);
                    break;
                case 8:
                    zzl = (zzl) SafeParcelReader.createParcelable(parcel, readHeader, zzl.CREATOR);
                    break;
                case 9:
                    zzz = (zzz) SafeParcelReader.createParcelable(parcel, readHeader, zzz.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new FilterHolder(zzb, zzd, zzr, zzv, zzp, zzt, zzn, zzl, zzz);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new FilterHolder[i];
    }
}
