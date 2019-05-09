// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "NativeAdOptionsParcelCreator")
public final class zzpl extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzpl> CREATOR;
    @SafeParcelable$Field(id = 1)
    public final int versionCode;
    @SafeParcelable$Field(id = 2)
    public final boolean zzbjn;
    @SafeParcelable$Field(id = 3)
    public final int zzbjo;
    @SafeParcelable$Field(id = 4)
    public final boolean zzbjp;
    @SafeParcelable$Field(id = 5)
    public final int zzbjq;
    @Nullable
    @SafeParcelable$Field(id = 6)
    public final zzmu zzbjr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzpm();
    }
    
    @SafeParcelable$Constructor
    public zzpl(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final boolean zzbjn, @SafeParcelable$Param(id = 3) final int zzbjo, @SafeParcelable$Param(id = 4) final boolean zzbjp, @SafeParcelable$Param(id = 5) final int zzbjq, @SafeParcelable$Param(id = 6) final zzmu zzbjr) {
        this.versionCode = versionCode;
        this.zzbjn = zzbjn;
        this.zzbjo = zzbjo;
        this.zzbjp = zzbjp;
        this.zzbjq = zzbjq;
        this.zzbjr = zzbjr;
    }
    
    public zzpl(final NativeAdOptions nativeAdOptions) {
        final boolean shouldReturnUrlsForImageAssets = nativeAdOptions.shouldReturnUrlsForImageAssets();
        final int imageOrientation = nativeAdOptions.getImageOrientation();
        final boolean shouldRequestMultipleImages = nativeAdOptions.shouldRequestMultipleImages();
        final int adChoicesPlacement = nativeAdOptions.getAdChoicesPlacement();
        zzmu zzmu;
        if (nativeAdOptions.getVideoOptions() != null) {
            zzmu = new zzmu(nativeAdOptions.getVideoOptions());
        }
        else {
            zzmu = null;
        }
        this(3, shouldReturnUrlsForImageAssets, imageOrientation, shouldRequestMultipleImages, adChoicesPlacement, zzmu);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbjn);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbjo);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzbjp);
        SafeParcelWriter.writeInt(parcel, 5, this.zzbjq);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzbjr, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
