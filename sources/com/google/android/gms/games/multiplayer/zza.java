package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import java.util.ArrayList;

public class zza implements Creator<InvitationEntity> {
    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzd(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return new InvitationEntity[i];
    }

    public InvitationEntity zzd(Parcel parcel) {
        int i = 0;
        ArrayList arrayList = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        int i2 = 0;
        ParticipantEntity participantEntity = null;
        int i3 = 0;
        String str = null;
        GameEntity gameEntity = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, readHeader, GameEntity.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    participantEntity = (ParticipantEntity) SafeParcelReader.createParcelable(parcel, readHeader, ParticipantEntity.CREATOR);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, ParticipantEntity.CREATOR);
                    break;
                case 7:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new InvitationEntity(gameEntity, str, j, i3, participantEntity, arrayList, i2, i);
    }
}
