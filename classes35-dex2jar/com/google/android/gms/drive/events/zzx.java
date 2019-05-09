// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "TransferStateOptionsCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzx extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzx> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final List<DriveSpace> zzbw;
    
    static {
        CREATOR = (Parcelable$Creator)new zzy();
    }
    
    @SafeParcelable$Constructor
    zzx(@NonNull @SafeParcelable$Param(id = 2) final List<DriveSpace> zzbw) {
        this.zzbw = zzbw;
    }
    
    public final boolean equals(final Object o) {
        return o != null && o.getClass() == this.getClass() && (o == this || Objects.equal((Object)this.zzbw, (Object)((zzx)o).zzbw));
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzbw });
    }
    
    public final String toString() {
        return String.format(Locale.US, "TransferStateOptions[Spaces=%s]", this.zzbw);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzbw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
