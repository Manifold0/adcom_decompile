package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;

public final class zza implements Creator<ExperienceEventEntity> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        GameEntity gameEntity = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, readHeader, GameEntity.CREATOR);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 7:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 8:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 9:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 10:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ExperienceEventEntity(str, gameEntity, str2, str3, str4, uri, j, j2, j3, i, i2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new ExperienceEventEntity[i];
    }
}
