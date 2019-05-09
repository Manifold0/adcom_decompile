package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzc implements Creator<zzb> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        MetadataBundle metadataBundle = null;
        zzx zzx = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzx = (zzx) SafeParcelReader.createParcelable(parcel, readHeader, zzx.CREATOR);
                    break;
                case 2:
                    metadataBundle = (MetadataBundle) SafeParcelReader.createParcelable(parcel, readHeader, MetadataBundle.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzb(zzx, metadataBundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzb[i];
    }
}
