package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;

public final class zzey implements Creator<zzex> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        PayloadTransferUpdate payloadTransferUpdate = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    payloadTransferUpdate = (PayloadTransferUpdate) SafeParcelReader.createParcelable(parcel, readHeader, PayloadTransferUpdate.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzex(str, payloadTransferUpdate);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzex[i];
    }
}
