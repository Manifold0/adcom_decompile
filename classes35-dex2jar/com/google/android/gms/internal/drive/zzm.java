// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.util.VisibleForTesting;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CloseContentsAndUpdateMetadataRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzm extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzm> CREATOR;
    @SafeParcelable$Field(id = 6)
    private final String zzal;
    @SafeParcelable$Field(id = 5)
    private final boolean zzam;
    @SafeParcelable$Field(defaultValue = "true", id = 10)
    private final boolean zzar;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzdb;
    @SafeParcelable$Field(id = 3)
    private final MetadataBundle zzdc;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final Contents zzdd;
    @SafeParcelable$Field(id = 7)
    private final int zzde;
    @SafeParcelable$Field(id = 8)
    private final int zzdf;
    @SafeParcelable$Field(id = 9)
    private final boolean zzdg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzn();
    }
    
    @VisibleForTesting
    public zzm(final DriveId driveId, final MetadataBundle metadataBundle, final int n, final boolean b, final com.google.android.gms.drive.zzn zzn) {
        this(driveId, metadataBundle, null, zzn.zzl(), zzn.zzk(), zzn.zzm(), n, b, zzn.zzo());
    }
    
    @SafeParcelable$Constructor
    zzm(@SafeParcelable$Param(id = 2) final DriveId zzdb, @SafeParcelable$Param(id = 3) final MetadataBundle zzdc, @SafeParcelable$Param(id = 4) final Contents zzdd, @SafeParcelable$Param(id = 5) final boolean zzam, @SafeParcelable$Param(id = 6) final String zzal, @SafeParcelable$Param(id = 7) final int zzde, @SafeParcelable$Param(id = 8) final int zzdf, @SafeParcelable$Param(id = 9) final boolean zzdg, @SafeParcelable$Param(id = 10) final boolean zzar) {
        this.zzdb = zzdb;
        this.zzdc = zzdc;
        this.zzdd = zzdd;
        this.zzam = zzam;
        this.zzal = zzal;
        this.zzde = zzde;
        this.zzdf = zzdf;
        this.zzdg = zzdg;
        this.zzar = zzar;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdb, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzdc, n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzdd, n, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzam);
        SafeParcelWriter.writeString(parcel, 6, this.zzal, false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzde);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdf);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdg);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzar);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
