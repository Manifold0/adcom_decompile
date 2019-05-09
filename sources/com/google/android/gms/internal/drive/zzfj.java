package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzo;
import com.google.android.gms.drive.events.zzr;
import com.google.android.gms.drive.events.zzv;

@Class(creator = "OnEventResponseCreator")
@Reserved({1, 4, 8})
public final class zzfj extends AbstractSafeParcelable {
    public static final Creator<zzfj> CREATOR = new zzfk();
    @Field(id = 2)
    private final int zzcy;
    @Field(id = 3)
    private final ChangeEvent zzhl;
    @Field(id = 5)
    private final CompletionEvent zzhm;
    @Field(id = 6)
    private final zzo zzhn;
    @Field(id = 7)
    private final zzb zzho;
    @Field(id = 9)
    private final zzv zzhp;
    @Field(id = 10)
    private final zzr zzhq;

    @Constructor
    zzfj(@Param(id = 2) int i, @Param(id = 3) ChangeEvent changeEvent, @Param(id = 5) CompletionEvent completionEvent, @Param(id = 6) zzo zzo, @Param(id = 7) zzb zzb, @Param(id = 9) zzv zzv, @Param(id = 10) zzr zzr) {
        this.zzcy = i;
        this.zzhl = changeEvent;
        this.zzhm = completionEvent;
        this.zzhn = zzo;
        this.zzho = zzb;
        this.zzhp = zzv;
        this.zzhq = zzr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcy);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzhl, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzhm, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzhn, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzho, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzhp, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzhq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final DriveEvent zzak() {
        switch (this.zzcy) {
            case 1:
                return this.zzhl;
            case 2:
                return this.zzhm;
            case 3:
                return this.zzhn;
            case 4:
                return this.zzho;
            case 7:
                return this.zzhp;
            case 8:
                return this.zzhq;
            default:
                throw new IllegalStateException("Unexpected event type " + this.zzcy);
        }
    }
}
