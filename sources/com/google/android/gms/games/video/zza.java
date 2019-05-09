package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zza implements Creator<VideoCapabilities> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean[] zArr = null;
        boolean z = false;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean[] zArr2 = null;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 2:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 4:
                    zArr2 = SafeParcelReader.createBooleanArray(parcel, readHeader);
                    break;
                case 5:
                    zArr = SafeParcelReader.createBooleanArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new VideoCapabilities(z3, z2, z, zArr2, zArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new VideoCapabilities[i];
    }
}
