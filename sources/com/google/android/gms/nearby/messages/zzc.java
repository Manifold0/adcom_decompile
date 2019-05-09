package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.nearby.zzgp;
import com.google.android.gms.internal.nearby.zzgu;
import com.google.android.gms.nearby.messages.internal.zzad;
import java.util.List;

public final class zzc implements Creator<MessageFilter> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        List list = null;
        int i = 0;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        List list2 = null;
        List list3 = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    list3 = SafeParcelReader.createTypedList(parcel, readHeader, zzad.CREATOR);
                    break;
                case 2:
                    list2 = SafeParcelReader.createTypedList(parcel, readHeader, zzgu.CREATOR);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 4:
                    list = SafeParcelReader.createTypedList(parcel, readHeader, zzgp.CREATOR);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 1000:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new MessageFilter(i2, list3, list2, z, list, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MessageFilter[i];
    }
}
