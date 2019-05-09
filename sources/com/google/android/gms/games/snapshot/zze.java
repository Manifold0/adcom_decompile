package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "SnapshotMetadataChangeCreator")
@Reserved({1000})
public final class zze extends zzd implements SnapshotMetadataChange {
    public static final Creator<zze> CREATOR = new zzd();
    @Field(getter = "getDescription", id = 1)
    private final String description;
    @Field(getter = "getProgressValue", id = 6)
    private final Long zzqq;
    @Field(getter = "getCoverImageUri", id = 4)
    private final Uri zzqs;
    @Field(getter = "getPlayedTimeMillis", id = 2)
    private final Long zzqt;
    @Field(getter = "getCoverImageTeleporter", id = 5)
    private BitmapTeleporter zzqu;

    zze() {
        this(null, null, null, null, null);
    }

    @Constructor
    zze(@Param(id = 1) String str, @Param(id = 2) Long l, @Param(id = 5) BitmapTeleporter bitmapTeleporter, @Param(id = 4) Uri uri, @Param(id = 6) Long l2) {
        boolean z = true;
        this.description = str;
        this.zzqt = l;
        this.zzqu = bitmapTeleporter;
        this.zzqs = uri;
        this.zzqq = l2;
        if (this.zzqu != null) {
            if (this.zzqs != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot set both a URI and an image");
        } else if (this.zzqs != null) {
            if (this.zzqu != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot set both a URI and an image");
        }
    }

    public final Bitmap getCoverImage() {
        return this.zzqu == null ? null : this.zzqu.get();
    }

    public final String getDescription() {
        return this.description;
    }

    public final Long getPlayedTimeMillis() {
        return this.zzqt;
    }

    public final Long getProgressValue() {
        return this.zzqq;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getDescription(), false);
        SafeParcelWriter.writeLongObject(parcel, 2, getPlayedTimeMillis(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzqs, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzqu, i, false);
        SafeParcelWriter.writeLongObject(parcel, 6, getProgressValue(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final BitmapTeleporter zzcm() {
        return this.zzqu;
    }
}
