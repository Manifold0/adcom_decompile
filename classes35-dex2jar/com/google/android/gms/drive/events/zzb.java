// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ChangesAvailableEventCreator")
@SafeParcelable$Reserved({ 1, 2 })
public final class zzb extends AbstractSafeParcelable implements DriveEvent
{
    public static final Parcelable$Creator<zzb> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final zze zzbt;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    public zzb(@SafeParcelable$Param(id = 3) final zze zzbt) {
        this.zzbt = zzbt;
    }
    
    public final boolean equals(final Object o) {
        return o != null && o.getClass() == this.getClass() && (o == this || Objects.equal((Object)this.zzbt, (Object)((zzb)o).zzbt));
    }
    
    public final int getType() {
        return 4;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzbt });
    }
    
    public final String toString() {
        return String.format(Locale.US, "ChangesAvailableEvent [changesAvailableOptions=%s]", this.zzbt);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzbt, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
