// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CreateFileRequestCreator")
@SafeParcelable$Reserved({ 1, 10 })
public final class zzw extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzw> CREATOR;
    @SafeParcelable$Field(id = 7)
    private final String zzal;
    @SafeParcelable$Field(id = 4)
    private final Contents zzdd;
    @SafeParcelable$Field(id = 3)
    private final MetadataBundle zzdl;
    @SafeParcelable$Field(id = 5)
    private final Integer zzdm;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzdn;
    @SafeParcelable$Field(id = 6)
    private final boolean zzdo;
    @SafeParcelable$Field(id = 8)
    private final int zzdp;
    @SafeParcelable$Field(id = 9)
    private final int zzdq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzx();
    }
    
    @VisibleForTesting
    public zzw(final DriveId driveId, final MetadataBundle metadataBundle, final int n, final int n2, final ExecutionOptions executionOptions) {
        this(driveId, metadataBundle, null, n2, executionOptions.zzl(), executionOptions.zzk(), executionOptions.zzm(), n);
    }
    
    @SafeParcelable$Constructor
    zzw(@SafeParcelable$Param(id = 2) final DriveId driveId, @SafeParcelable$Param(id = 3) final MetadataBundle metadataBundle, @SafeParcelable$Param(id = 4) final Contents zzdd, @SafeParcelable$Param(id = 5) final int n, @SafeParcelable$Param(id = 6) final boolean zzdo, @SafeParcelable$Param(id = 7) final String zzal, @SafeParcelable$Param(id = 8) final int zzdp, @SafeParcelable$Param(id = 9) final int zzdq) {
        if (zzdd != null && zzdq != 0) {
            Preconditions.checkArgument(zzdd.getRequestId() == zzdq, (Object)"inconsistent contents reference");
        }
        if (n == 0 && zzdd == null && zzdq == 0) {
            throw new IllegalArgumentException("Need a valid contents");
        }
        this.zzdn = (DriveId)Preconditions.checkNotNull((Object)driveId);
        this.zzdl = (MetadataBundle)Preconditions.checkNotNull((Object)metadataBundle);
        this.zzdd = zzdd;
        this.zzdm = n;
        this.zzal = zzal;
        this.zzdp = zzdp;
        this.zzdo = zzdo;
        this.zzdq = zzdq;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdn, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzdl, n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzdd, n, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zzdm, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzdo);
        SafeParcelWriter.writeString(parcel, 7, this.zzal, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdp);
        SafeParcelWriter.writeInt(parcel, 9, this.zzdq);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
