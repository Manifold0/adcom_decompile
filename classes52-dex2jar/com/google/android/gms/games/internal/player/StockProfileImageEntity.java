// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.player;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "StockProfileImageEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class StockProfileImageEntity extends zzd implements StockProfileImage
{
    public static final Parcelable$Creator<StockProfileImageEntity> CREATOR;
    @SafeParcelable$Field(getter = "getImageUrl", id = 1)
    private final String zzmr;
    @SafeParcelable$Field(getter = "getImageUri", id = 2)
    private final Uri zzms;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    @SafeParcelable$Constructor
    public StockProfileImageEntity(@SafeParcelable$Param(id = 1) final String zzmr, @SafeParcelable$Param(id = 2) final Uri zzms) {
        this.zzmr = zzmr;
        this.zzms = zzms;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof StockProfileImage) {
            if (o == this) {
                return true;
            }
            final StockProfileImage stockProfileImage = (StockProfileImage)o;
            if (Objects.equal((Object)this.zzmr, (Object)stockProfileImage.getImageUrl()) && Objects.equal((Object)this.zzms, (Object)stockProfileImage.zzbz())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public final String getImageUrl() {
        return this.zzmr;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzmr, this.zzms });
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("ImageId", (Object)this.zzmr).add("ImageUri", (Object)this.zzms).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzms, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final Uri zzbz() {
        return this.zzms;
    }
}
