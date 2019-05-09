package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "StockProfileImageEntityCreator")
@Reserved({1000})
public final class StockProfileImageEntity extends zzd implements StockProfileImage {
    public static final Creator<StockProfileImageEntity> CREATOR = new zzf();
    @Field(getter = "getImageUrl", id = 1)
    private final String zzmr;
    @Field(getter = "getImageUri", id = 2)
    private final Uri zzms;

    @Constructor
    public StockProfileImageEntity(@Param(id = 1) String str, @Param(id = 2) Uri uri) {
        this.zzmr = str;
        this.zzms = uri;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof StockProfileImage)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        StockProfileImage stockProfileImage = (StockProfileImage) obj;
        return Objects.equal(this.zzmr, stockProfileImage.getImageUrl()) && Objects.equal(this.zzms, stockProfileImage.zzbz());
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        if (this != null) {
            return this;
        }
        throw null;
    }

    public final String getImageUrl() {
        return this.zzmr;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzmr, this.zzms});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("ImageId", this.zzmr).add("ImageUri", this.zzms).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzms, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Uri zzbz() {
        return this.zzms;
    }
}
