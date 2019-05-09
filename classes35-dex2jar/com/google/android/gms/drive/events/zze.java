// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ChangesAvailableOptionsCreator")
@SafeParcelable$Reserved({ 1 })
public final class zze extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zze> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final int zzbu;
    @SafeParcelable$Field(id = 3)
    private final boolean zzbv;
    @SafeParcelable$Field(id = 4)
    private final List<DriveSpace> zzbw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    @SafeParcelable$Constructor
    zze(@SafeParcelable$Param(id = 2) final int zzbu, @SafeParcelable$Param(id = 3) final boolean zzbv, @NonNull @SafeParcelable$Param(id = 4) final List<DriveSpace> zzbw) {
        this.zzbu = zzbu;
        this.zzbv = zzbv;
        this.zzbw = zzbw;
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
                final zze zze = (zze)o;
                if (Objects.equal((Object)this.zzbw, (Object)zze.zzbw) && this.zzbu == zze.zzbu) {
                    b2 = b;
                    if (this.zzbv == zze.zzbv) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzbw, this.zzbu, this.zzbv });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzbu);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbv);
        SafeParcelWriter.writeTypedList(parcel, 4, (List)this.zzbw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
