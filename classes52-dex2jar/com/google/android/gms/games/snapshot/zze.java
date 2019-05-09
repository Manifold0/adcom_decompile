// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.graphics.Bitmap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.data.BitmapTeleporter;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "SnapshotMetadataChangeCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zze extends zzd implements SnapshotMetadataChange
{
    public static final Parcelable$Creator<zze> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 1)
    private final String description;
    @SafeParcelable$Field(getter = "getProgressValue", id = 6)
    private final Long zzqq;
    @SafeParcelable$Field(getter = "getCoverImageUri", id = 4)
    private final Uri zzqs;
    @SafeParcelable$Field(getter = "getPlayedTimeMillis", id = 2)
    private final Long zzqt;
    @SafeParcelable$Field(getter = "getCoverImageTeleporter", id = 5)
    private BitmapTeleporter zzqu;
    
    static {
        CREATOR = (Parcelable$Creator)new com.google.android.gms.games.snapshot.zzd();
    }
    
    zze() {
        this(null, null, null, null, null);
    }
    
    @SafeParcelable$Constructor
    zze(@SafeParcelable$Param(id = 1) final String description, @SafeParcelable$Param(id = 2) final Long zzqt, @SafeParcelable$Param(id = 5) final BitmapTeleporter zzqu, @SafeParcelable$Param(id = 4) final Uri zzqs, @SafeParcelable$Param(id = 6) final Long zzqq) {
        final boolean b = true;
        boolean b2 = true;
        this.description = description;
        this.zzqt = zzqt;
        this.zzqu = zzqu;
        this.zzqs = zzqs;
        this.zzqq = zzqq;
        if (this.zzqu != null) {
            if (this.zzqs != null) {
                b2 = false;
            }
            Preconditions.checkState(b2, (Object)"Cannot set both a URI and an image");
        }
        else if (this.zzqs != null) {
            Preconditions.checkState(this.zzqu == null && b, (Object)"Cannot set both a URI and an image");
        }
    }
    
    @Override
    public final Bitmap getCoverImage() {
        if (this.zzqu == null) {
            return null;
        }
        return this.zzqu.get();
    }
    
    @Override
    public final String getDescription() {
        return this.description;
    }
    
    @Override
    public final Long getPlayedTimeMillis() {
        return this.zzqt;
    }
    
    @Override
    public final Long getProgressValue() {
        return this.zzqq;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getDescription(), false);
        SafeParcelWriter.writeLongObject(parcel, 2, this.getPlayedTimeMillis(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzqs, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzqu, n, false);
        SafeParcelWriter.writeLongObject(parcel, 6, this.getProgressValue(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final BitmapTeleporter zzcm() {
        return this.zzqu;
    }
}
