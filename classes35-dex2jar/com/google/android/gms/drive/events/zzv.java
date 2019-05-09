// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.internal.drive.zzh;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "TransferStateEventCreator")
@SafeParcelable$Reserved({ 1, 2 })
public final class zzv extends AbstractSafeParcelable implements DriveEvent
{
    public static final Parcelable$Creator<zzv> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final List<zzh> zzcs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzw();
    }
    
    @SafeParcelable$Constructor
    public zzv(@SafeParcelable$Param(id = 3) final List<zzh> zzcs) {
        this.zzcs = zzcs;
    }
    
    public final boolean equals(final Object o) {
        return o != null && o.getClass() == this.getClass() && (o == this || Objects.equal((Object)this.zzcs, (Object)((zzv)o).zzcs));
    }
    
    public final int getType() {
        return 7;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzcs });
    }
    
    public final String toString() {
        return String.format("TransferStateEvent[%s]", TextUtils.join((CharSequence)"','", (Iterable)this.zzcs));
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 3, (List)this.zzcs, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
