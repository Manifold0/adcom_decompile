// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zza implements Parcelable$Creator<InvitationEntity>
{
    public InvitationEntity zzd(final Parcel parcel) {
        int int1 = 0;
        ArrayList<ParticipantEntity> typedList = null;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long long1 = 0L;
        int int2 = 0;
        ParticipantEntity participantEntity = null;
        int int3 = 0;
        String string = null;
        GameEntity gameEntity = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                default: {
                    SafeParcelReader.skipUnknownField(parcel, header);
                    continue;
                }
                case 1: {
                    gameEntity = (GameEntity)SafeParcelReader.createParcelable(parcel, header, (Parcelable$Creator)GameEntity.CREATOR);
                    continue;
                }
                case 2: {
                    string = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 3: {
                    long1 = SafeParcelReader.readLong(parcel, header);
                    continue;
                }
                case 4: {
                    int3 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 5: {
                    participantEntity = (ParticipantEntity)SafeParcelReader.createParcelable(parcel, header, (Parcelable$Creator)ParticipantEntity.CREATOR);
                    continue;
                }
                case 6: {
                    typedList = (ArrayList<ParticipantEntity>)SafeParcelReader.createTypedList(parcel, header, (Parcelable$Creator)ParticipantEntity.CREATOR);
                    continue;
                }
                case 7: {
                    int2 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 8: {
                    int1 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new InvitationEntity(gameEntity, string, long1, int3, participantEntity, typedList, int2, int1);
    }
}
