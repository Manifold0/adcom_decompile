package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "PermissionCreator")
@Reserved({1})
public final class zzr extends AbstractSafeParcelable {
    public static final Creator<zzr> CREATOR = new zzs();
    @Field(getter = "getAccountType", id = 3)
    private int accountType;
    @Nullable
    @Field(getter = "getAccountIdentifier", id = 2)
    private String zzbe;
    @Nullable
    @Field(getter = "getAccountDisplayName", id = 4)
    private String zzbf;
    @Nullable
    @Field(getter = "getPhotoLink", id = 5)
    private String zzbg;
    @Field(getter = "getRole", id = 6)
    private int zzbh;
    @Field(getter = "isLinkRequiredForAccess", id = 7)
    private boolean zzbi;

    @Constructor
    public zzr(@Nullable @Param(id = 2) String str, @Param(id = 3) int i, @Nullable @Param(id = 4) String str2, @Nullable @Param(id = 5) String str3, @Param(id = 6) int i2, @Param(id = 7) boolean z) {
        this.zzbe = str;
        this.accountType = i;
        this.zzbf = str2;
        this.zzbg = str3;
        this.zzbh = i2;
        this.zzbi = z;
    }

    private static boolean zzb(int i) {
        switch (i) {
            case 256:
            case 257:
            case 258:
                return true;
            default:
                return false;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zzr zzr = (zzr) obj;
        return Objects.equal(this.zzbe, zzr.zzbe) && this.accountType == zzr.accountType && this.zzbh == zzr.zzbh && this.zzbi == zzr.zzbi;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzbe, Integer.valueOf(this.accountType), Integer.valueOf(this.zzbh), Boolean.valueOf(this.zzbi)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        boolean z;
        int i2 = -1;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, !zzb(this.accountType) ? null : this.zzbe, false);
        SafeParcelWriter.writeInt(parcel, 3, !zzb(this.accountType) ? -1 : this.accountType);
        SafeParcelWriter.writeString(parcel, 4, this.zzbf, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbg, false);
        switch (this.zzbh) {
            case 0:
            case 1:
            case 2:
            case 3:
                z = true;
                break;
            default:
                z = false;
                break;
        }
        if (z) {
            i2 = this.zzbh;
        }
        SafeParcelWriter.writeInt(parcel, 6, i2);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
