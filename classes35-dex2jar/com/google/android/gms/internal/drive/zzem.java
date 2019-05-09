// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.zzr;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "GetPermissionsResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzem extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzem> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final int responseCode;
    @SafeParcelable$Field(id = 2)
    private final List<zzr> zzgz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzen();
    }
    
    @SafeParcelable$Constructor
    public zzem(@SafeParcelable$Param(id = 2) final List<zzr> zzgz, @SafeParcelable$Param(id = 3) final int responseCode) {
        this.zzgz = zzgz;
        this.responseCode = responseCode;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzgz, false);
        SafeParcelWriter.writeInt(parcel, 3, this.responseCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
