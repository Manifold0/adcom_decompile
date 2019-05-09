package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzo;
import com.google.android.gms.drive.events.zzr;
import com.google.android.gms.drive.events.zzv;

public final class zzfk implements Creator<zzfj> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzr zzr = null;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zzv zzv = null;
        zzb zzb = null;
        zzo zzo = null;
        CompletionEvent completionEvent = null;
        ChangeEvent changeEvent = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    changeEvent = (ChangeEvent) SafeParcelReader.createParcelable(parcel, readHeader, ChangeEvent.CREATOR);
                    break;
                case 5:
                    completionEvent = (CompletionEvent) SafeParcelReader.createParcelable(parcel, readHeader, CompletionEvent.CREATOR);
                    break;
                case 6:
                    zzo = (zzo) SafeParcelReader.createParcelable(parcel, readHeader, zzo.CREATOR);
                    break;
                case 7:
                    zzb = (zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.CREATOR);
                    break;
                case 9:
                    zzv = (zzv) SafeParcelReader.createParcelable(parcel, readHeader, zzv.CREATOR);
                    break;
                case 10:
                    zzr = (zzr) SafeParcelReader.createParcelable(parcel, readHeader, zzr.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzfj(i, changeEvent, completionEvent, zzo, zzb, zzv, zzr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzfj[i];
    }
}
