// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CreateContentsRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzr extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzr> CREATOR;
    @SafeParcelable$Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveFile.MODE_WRITE_ONLY", id = 2)
    private final int mode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzs();
    }
    
    @SafeParcelable$Constructor
    public zzr(@SafeParcelable$Param(id = 2) final int mode) {
        Preconditions.checkArgument(mode == 536870912 || mode == 805306368, (Object)"Cannot create a new read-only contents!");
        this.mode = mode;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.mode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
