// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.player.zzb;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzap implements Parcelable$Creator<PlayerEntity>
{
    public PlayerEntity zzc(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String string = null;
        String string2 = null;
        Uri uri = null;
        Uri uri2 = null;
        long long1 = 0L;
        int int1 = 0;
        long long2 = 0L;
        String string3 = null;
        String string4 = null;
        String string5 = null;
        zzb zzb = null;
        PlayerLevelInfo playerLevelInfo = null;
        boolean boolean1 = false;
        boolean boolean2 = false;
        String string6 = null;
        String string7 = null;
        Uri uri3 = null;
        String string8 = null;
        Uri uri4 = null;
        String string9 = null;
        int int2 = 0;
        long long3 = 0L;
        boolean boolean3 = false;
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
                    long1 = SafeParcelReader.readLong(parcel, header);
                    continue;
                }
                case 6: {
                    int1 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 7: {
                    long2 = SafeParcelReader.readLong(parcel, header);
                    continue;
                }
                case 8: {
                    string3 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 9: {
                    string4 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 14: {
                    string5 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 15: {
                    zzb = (zzb)SafeParcelReader.createParcelable(parcel, header, (Parcelable$Creator)com.google.android.gms.games.internal.player.zzb.CREATOR);
                    continue;
                }
                case 16: {
                    playerLevelInfo = (PlayerLevelInfo)SafeParcelReader.createParcelable(parcel, header, (Parcelable$Creator)PlayerLevelInfo.CREATOR);
                    continue;
                }
                case 18: {
                    boolean1 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 19: {
                    boolean2 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 20: {
                    string6 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 21: {
                    string7 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 22: {
                    uri3 = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 23: {
                    string8 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 24: {
                    uri4 = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 25: {
                    string9 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 26: {
                    int2 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 27: {
                    long3 = SafeParcelReader.readLong(parcel, header);
                    continue;
                }
                case 28: {
                    boolean3 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PlayerEntity(string, string2, uri, uri2, long1, int1, long2, string3, string4, string5, zzb, playerLevelInfo, boolean1, boolean2, string6, string7, uri3, string8, uri4, string9, int2, long3, boolean3);
    }
}
