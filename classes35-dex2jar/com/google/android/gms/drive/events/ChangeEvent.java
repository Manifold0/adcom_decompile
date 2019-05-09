// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ChangeEventCreator")
@SafeParcelable$Reserved({ 1 })
public final class ChangeEvent extends AbstractSafeParcelable implements ResourceEvent
{
    public static final Parcelable$Creator<ChangeEvent> CREATOR;
    @SafeParcelable$Field(id = 3)
    private final int zzbs;
    @SafeParcelable$Field(id = 2)
    private final DriveId zzk;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    public ChangeEvent(@SafeParcelable$Param(id = 2) final DriveId zzk, @SafeParcelable$Param(id = 3) final int zzbs) {
        this.zzk = zzk;
        this.zzbs = zzbs;
    }
    
    public final DriveId getDriveId() {
        return this.zzk;
    }
    
    public final int getType() {
        return 1;
    }
    
    public final boolean hasBeenDeleted() {
        return (this.zzbs & 0x4) != 0x0;
    }
    
    public final boolean hasContentChanged() {
        return (this.zzbs & 0x2) != 0x0;
    }
    
    public final boolean hasMetadataChanged() {
        return (this.zzbs & 0x1) != 0x0;
    }
    
    public final String toString() {
        return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", this.zzk, this.zzbs);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzk, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbs);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
