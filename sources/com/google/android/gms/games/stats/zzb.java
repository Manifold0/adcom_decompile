package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Creator<zza> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        float f = 0.0f;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundle = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    f7 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 2:
                    f6 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    f5 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 7:
                    f4 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 9:
                    f3 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 10:
                    f2 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 11:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zza(f7, f6, i3, i2, i, f5, f4, bundle, f3, f2, f);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }
}
