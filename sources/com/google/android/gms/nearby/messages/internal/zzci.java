package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.nearby.zzgs;
import com.google.android.gms.nearby.messages.Message;

public final class zzci implements Creator<Update> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        byte[] bArr = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzgs zzgs = null;
        zza zza = null;
        zze zze = null;
        Message message = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    message = (Message) SafeParcelReader.createParcelable(parcel, readHeader, Message.CREATOR);
                    break;
                case 4:
                    zze = (zze) SafeParcelReader.createParcelable(parcel, readHeader, zze.CREATOR);
                    break;
                case 5:
                    zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    break;
                case 6:
                    zzgs = (zzgs) SafeParcelReader.createParcelable(parcel, readHeader, zzgs.CREATOR);
                    break;
                case 7:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Update(i2, i, message, zze, zza, zzgs, bArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Update[i];
    }
}
