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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.Contents;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CloseContentsRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzo extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzo> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 2)
    private final Contents zzdd;
    @SafeParcelable$Field(id = 4)
    private final int zzdf;
    @SafeParcelable$Field(id = 3)
    private final Boolean zzdh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzp();
    }
    
    @VisibleForTesting
    public zzo(final int n, final boolean b) {
        this(null, false, n);
    }
    
    @SafeParcelable$Constructor
    zzo(@SafeParcelable$Param(id = 2) final Contents zzdd, @SafeParcelable$Param(id = 3) final Boolean zzdh, @SafeParcelable$Param(id = 4) final int zzdf) {
        this.zzdd = zzdd;
        this.zzdh = zzdh;
        this.zzdf = zzdf;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdd, n, false);
        SafeParcelWriter.writeBooleanObject(parcel, 3, this.zzdh, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
