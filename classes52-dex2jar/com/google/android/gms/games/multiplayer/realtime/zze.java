// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zze implements Parcelable$Creator<RoomEntity>
{
    public RoomEntity zzf(final Parcel parcel) {
        int int1 = 0;
        ArrayList<ParticipantEntity> typedList = null;
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long long1 = 0L;
        Bundle bundle = null;
        int int2 = 0;
        String string = null;
        int int3 = 0;
        String string2 = null;
        String string3 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                default: {
                    SafeParcelReader.skipUnknownField(parcel, header);
                    continue;
                }
                case 1: {
                    string3 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 2: {
                    string2 = SafeParcelReader.createString(parcel, header);
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
                    string = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 6: {
                    int2 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 7: {
                    bundle = SafeParcelReader.createBundle(parcel, header);
                    continue;
                }
                case 8: {
                    typedList = (ArrayList<ParticipantEntity>)SafeParcelReader.createTypedList(parcel, header, (Parcelable$Creator)ParticipantEntity.CREATOR);
                    continue;
                }
                case 9: {
                    int1 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new RoomEntity(string3, string2, long1, int3, string, int2, bundle, typedList, int1);
    }
}
