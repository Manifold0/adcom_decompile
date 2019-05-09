package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;

public final class zza implements Creator<GameRequestEntity> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        byte[] bArr = null;
        String str = null;
        ArrayList arrayList = null;
        int i = 0;
        long j = 0;
        long j2 = 0;
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    gameEntity = (GameEntity) SafeParcelReader.createParcelable(parcel, readHeader, GameEntity.CREATOR);
                    break;
                case 2:
                    playerEntity = (PlayerEntity) SafeParcelReader.createParcelable(parcel, readHeader, PlayerEntity.CREATOR);
                    break;
                case 3:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, PlayerEntity.CREATOR);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 9:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 10:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 11:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 12:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GameRequestEntity(gameEntity, playerEntity, bArr, str, arrayList, i, j, j2, bundle, i2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GameRequestEntity[i];
    }
}
