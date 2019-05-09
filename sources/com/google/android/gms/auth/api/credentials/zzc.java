package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzc implements Creator<Credential> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new Credential[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        List list = null;
        Uri uri = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 4:
                    list = SafeParcelReader.createTypedList(parcel, readHeader, IdToken.CREATOR);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Credential(str6, str5, uri, list, str4, str3, str2, str);
    }
}
