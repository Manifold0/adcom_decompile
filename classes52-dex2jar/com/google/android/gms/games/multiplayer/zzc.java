// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzc implements Parcelable$Creator<ParticipantEntity>
{
    public ParticipantEntity zze(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String string = null;
        String string2 = null;
        Uri uri = null;
        Uri uri2 = null;
        int int1 = 0;
        String string3 = null;
        boolean boolean1 = false;
        PlayerEntity playerEntity = null;
        int int2 = 0;
        ParticipantResult participantResult = null;
        String string4 = null;
        String string5 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                default: {
                    SafeParcelReader.skipUnknownField(parcel, header);
                    continue;
                }
                case 1: {
                    string = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 2: {
                    string2 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 3: {
                    uri = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri2 = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 5: {
                    int1 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 6: {
                    string3 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 7: {
                    boolean1 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 8: {
                    playerEntity = (PlayerEntity)SafeParcelReader.createParcelable(parcel, header, (Parcelable$Creator)PlayerEntity.CREATOR);
                    continue;
                }
                case 9: {
                    int2 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 10: {
                    participantResult = (ParticipantResult)SafeParcelReader.createParcelable(parcel, header, (Parcelable$Creator)ParticipantResult.CREATOR);
                    continue;
                }
                case 11: {
                    string4 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 12: {
                    string5 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ParticipantEntity(string, string2, uri, uri2, int1, string3, boolean1, playerEntity, int2, participantResult, string4, string5);
    }
}
