package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.PlayerEntity;

public class zzc implements Creator<ParticipantEntity> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zze(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return new ParticipantEntity[i];
    }

    public ParticipantEntity zze(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        Uri uri = null;
        Uri uri2 = null;
        int i = 0;
        String str3 = null;
        boolean z = false;
        PlayerEntity playerEntity = null;
        int i2 = 0;
        ParticipantResult participantResult = null;
        String str4 = null;
        String str5 = null;
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
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 4:
                    uri2 = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 8:
                    playerEntity = (PlayerEntity) SafeParcelReader.createParcelable(parcel, readHeader, PlayerEntity.CREATOR);
                    break;
                case 9:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 10:
                    participantResult = (ParticipantResult) SafeParcelReader.createParcelable(parcel, readHeader, ParticipantResult.CREATOR);
                    break;
                case 11:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 12:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ParticipantEntity(str, str2, uri, uri2, i, str3, z, playerEntity, i2, participantResult, str4, str5);
    }
}
