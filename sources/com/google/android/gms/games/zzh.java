package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public class zzh implements Creator<GameEntity> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return new GameEntity[i];
    }

    public GameEntity zzb(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean z = false;
        boolean z2 = false;
        String str7 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z3 = false;
        boolean z4 = false;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        String str11 = null;
        boolean z8 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 8:
                    uri2 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 9:
                    uri3 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 11:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 12:
                    str7 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 13:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 14:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 15:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 16:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 17:
                    z4 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 18:
                    str8 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 19:
                    str9 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 20:
                    str10 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 21:
                    z5 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 22:
                    z6 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 23:
                    z7 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 24:
                    str11 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 25:
                    z8 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GameEntity(str, str2, str3, str4, str5, str6, uri, uri2, uri3, z, z2, str7, i, i2, i3, z3, z4, str8, str9, str10, z5, z6, z7, str11, z8);
    }
}
