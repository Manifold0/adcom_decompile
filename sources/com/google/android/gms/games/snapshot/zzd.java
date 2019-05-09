package com.google.android.gms.games.snapshot;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Creator<zze> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Long l = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Uri uri = null;
        BitmapTeleporter bitmapTeleporter = null;
        Long l2 = null;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    l2 = SafeParcelReader.readLongObject(parcel, readHeader);
                    break;
                case 4:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 5:
                    bitmapTeleporter = (BitmapTeleporter) SafeParcelReader.createParcelable(parcel, readHeader, BitmapTeleporter.CREATOR);
                    break;
                case 6:
                    l = SafeParcelReader.readLongObject(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zze(str, l2, bitmapTeleporter, uri, l);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zze[i];
    }
}
