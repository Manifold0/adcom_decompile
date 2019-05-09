// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "TransferProgressDataCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzh extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzh> CREATOR;
    @SafeParcelable$Field(id = 4)
    final int status;
    @SafeParcelable$Field(id = 2)
    final int zzcr;
    @SafeParcelable$Field(id = 5)
    final long zzcu;
    @SafeParcelable$Field(id = 6)
    final long zzcv;
    @SafeParcelable$Field(id = 3)
    final DriveId zzk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    @SafeParcelable$Constructor
    public zzh(@SafeParcelable$Param(id = 2) final int zzcr, @SafeParcelable$Param(id = 3) final DriveId zzk, @SafeParcelable$Param(id = 4) final int status, @SafeParcelable$Param(id = 5) final long zzcu, @SafeParcelable$Param(id = 6) final long zzcv) {
        this.zzcr = zzcr;
        this.zzk = zzk;
        this.status = status;
        this.zzcu = zzcu;
        this.zzcv = zzcv;
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
                final zzh zzh = (zzh)o;
                if (this.zzcr == zzh.zzcr && Objects.equal((Object)this.zzk, (Object)zzh.zzk) && this.status == zzh.status && this.zzcu == zzh.zzcu) {
                    b2 = b;
                    if (this.zzcv == zzh.zzcv) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzcr, this.zzk, this.status, this.zzcu, this.zzcv });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzcr);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.writeInt(parcel, 4, this.status);
        SafeParcelWriter.writeLong(parcel, 5, this.zzcu);
        SafeParcelWriter.writeLong(parcel, 6, this.zzcv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
