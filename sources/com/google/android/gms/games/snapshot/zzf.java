package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;

public final class zzf implements Creator<SnapshotMetadataEntity> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GameEntity gameEntity = null;
        PlayerEntity playerEntity = null;
        String str = null;
        Uri uri = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        long j = 0;
        long j2 = 0;
        float f = 0.0f;
        String str5 = null;
        boolean z = false;
        long j3 = 0;
        String str6 = null;
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
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 10:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 11:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 12:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 13:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 14:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 15:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SnapshotMetadataEntity(gameEntity, playerEntity, str, uri, str2, str3, str4, j, j2, f, str5, z, j3, str6);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SnapshotMetadataEntity[i];
    }
}
