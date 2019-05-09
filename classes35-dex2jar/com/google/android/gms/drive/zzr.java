// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "PermissionCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzr extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzr> CREATOR;
    @SafeParcelable$Field(getter = "getAccountType", id = 3)
    private int accountType;
    @Nullable
    @SafeParcelable$Field(getter = "getAccountIdentifier", id = 2)
    private String zzbe;
    @Nullable
    @SafeParcelable$Field(getter = "getAccountDisplayName", id = 4)
    private String zzbf;
    @Nullable
    @SafeParcelable$Field(getter = "getPhotoLink", id = 5)
    private String zzbg;
    @SafeParcelable$Field(getter = "getRole", id = 6)
    private int zzbh;
    @SafeParcelable$Field(getter = "isLinkRequiredForAccess", id = 7)
    private boolean zzbi;
    
    static {
        CREATOR = (Parcelable$Creator)new zzs();
    }
    
    @SafeParcelable$Constructor
    public zzr(@Nullable @SafeParcelable$Param(id = 2) final String zzbe, @SafeParcelable$Param(id = 3) final int accountType, @Nullable @SafeParcelable$Param(id = 4) final String zzbf, @Nullable @SafeParcelable$Param(id = 5) final String zzbg, @SafeParcelable$Param(id = 6) final int zzbh, @SafeParcelable$Param(id = 7) final boolean zzbi) {
        this.zzbe = zzbe;
        this.accountType = accountType;
        this.zzbf = zzbf;
        this.zzbg = zzbg;
        this.zzbh = zzbh;
        this.zzbi = zzbi;
    }
    
    private static boolean zzb(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 256:
            case 257:
            case 258: {
                return true;
            }
        }
    }
    
    public final boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (o == null || o.getClass() != this.getClass()) {
            b2 = false;
        }
        else {
            b2 = b;
            if (o != this) {
                final zzr zzr = (zzr)o;
                if (Objects.equal((Object)this.zzbe, (Object)zzr.zzbe) && this.accountType == zzr.accountType && this.zzbh == zzr.zzbh) {
                    b2 = b;
                    if (this.zzbi == zzr.zzbi) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzbe, this.accountType, this.zzbh, this.zzbi });
    }
    
    public final void writeToParcel(final Parcel parcel, int n) {
        final int n2 = -1;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        String zzbe;
        if (!zzb(this.accountType)) {
            zzbe = null;
        }
        else {
            zzbe = this.zzbe;
        }
        SafeParcelWriter.writeString(parcel, 2, zzbe, false);
        if (!zzb(this.accountType)) {
            n = -1;
        }
        else {
            n = this.accountType;
        }
        SafeParcelWriter.writeInt(parcel, 3, n);
        SafeParcelWriter.writeString(parcel, 4, this.zzbf, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbg, false);
        switch (this.zzbh) {
            default: {
                n = 0;
                break;
            }
            case 0:
            case 1:
            case 2:
            case 3: {
                n = 1;
                break;
            }
        }
        if (n == 0) {
            n = n2;
        }
        else {
            n = this.zzbh;
        }
        SafeParcelWriter.writeInt(parcel, 6, n);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
