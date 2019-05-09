// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class zzh implements Parcelable$Creator<GameEntity>
{
    public GameEntity zzb(final Parcel parcel) {
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String string = null;
        String string2 = null;
        String string3 = null;
        String string4 = null;
        String string5 = null;
        String string6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean boolean1 = false;
        boolean boolean2 = false;
        String string7 = null;
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        boolean boolean3 = false;
        boolean boolean4 = false;
        String string8 = null;
        String string9 = null;
        String string10 = null;
        boolean boolean5 = false;
        boolean boolean6 = false;
        boolean boolean7 = false;
        String string11 = null;
        boolean boolean8 = false;
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
                    string3 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 4: {
                    string4 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 5: {
                    string5 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 6: {
                    string6 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 7: {
                    uri = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 8: {
                    uri2 = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 9: {
                    uri3 = (Uri)SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    continue;
                }
                case 10: {
                    boolean1 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 11: {
                    boolean2 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 12: {
                    string7 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 13: {
                    int1 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 14: {
                    int2 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 15: {
                    int3 = SafeParcelReader.readInt(parcel, header);
                    continue;
                }
                case 16: {
                    boolean3 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 17: {
                    boolean4 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 18: {
                    string8 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 19: {
                    string9 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 20: {
                    string10 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 21: {
                    boolean5 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 22: {
                    boolean6 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 23: {
                    boolean7 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
                case 24: {
                    string11 = SafeParcelReader.createString(parcel, header);
                    continue;
                }
                case 25: {
                    boolean8 = SafeParcelReader.readBoolean(parcel, header);
                    continue;
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GameEntity(string, string2, string3, string4, string5, string6, uri, uri2, uri3, boolean1, boolean2, string7, int1, int2, int3, boolean3, boolean4, string8, string9, string10, boolean5, boolean6, boolean7, string11, boolean8);
    }
}
