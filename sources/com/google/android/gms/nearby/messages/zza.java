package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.nearby.zzgs;

public final class zza implements Creator<Message> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzgs[] zzgsArr = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    zzgsArr = (zzgs[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzgs.CREATOR);
                    break;
                case 5:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 1000:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Message(i, bArr, str2, str, zzgsArr, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Message[i];
    }
}
