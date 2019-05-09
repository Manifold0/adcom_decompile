package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzgq implements Creator<zzgp> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        byte[] bArr = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        byte[] bArr2 = null;
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        ParcelUuid parcelUuid = null;
        ParcelUuid parcelUuid2 = null;
        ParcelUuid parcelUuid3 = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    parcelUuid3 = (ParcelUuid) SafeParcelReader.createParcelable(parcel, readHeader, ParcelUuid.CREATOR);
                    break;
                case 5:
                    parcelUuid2 = (ParcelUuid) SafeParcelReader.createParcelable(parcel, readHeader, ParcelUuid.CREATOR);
                    break;
                case 6:
                    parcelUuid = (ParcelUuid) SafeParcelReader.createParcelable(parcel, readHeader, ParcelUuid.CREATOR);
                    break;
                case 7:
                    bArr4 = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 8:
                    bArr3 = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 9:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 10:
                    bArr2 = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 11:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzgp(i2, parcelUuid3, parcelUuid2, parcelUuid, bArr4, bArr3, i, bArr2, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzgp[i];
    }
}
