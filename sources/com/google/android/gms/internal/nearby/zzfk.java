package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzfk implements Creator<zzfh> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        int i = 0;
        long j2 = -1;
        String str = null;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 2:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 4:
                    parcelFileDescriptor2 = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, readHeader, ParcelFileDescriptor.CREATOR);
                    break;
                case 5:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 7:
                    parcelFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, readHeader, ParcelFileDescriptor.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzfh(j, i, bArr, parcelFileDescriptor2, str, j2, parcelFileDescriptor);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzfh[i];
    }
}
