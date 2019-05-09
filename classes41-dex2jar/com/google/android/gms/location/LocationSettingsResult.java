// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationSettingsResultCreator")
@SafeParcelable$Reserved({ 1000 })
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result
{
    public static final Parcelable$Creator<LocationSettingsResult> CREATOR;
    @SafeParcelable$Field(getter = "getStatus", id = 1)
    private final Status zzbl;
    @SafeParcelable$Field(getter = "getLocationSettingsStates", id = 2)
    private final LocationSettingsStates zzbm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzah();
    }
    
    public LocationSettingsResult(final Status status) {
        this(status, null);
    }
    
    @SafeParcelable$Constructor
    public LocationSettingsResult(@SafeParcelable$Param(id = 1) final Status zzbl, @SafeParcelable$Param(id = 2) final LocationSettingsStates zzbm) {
        this.zzbl = zzbl;
        this.zzbm = zzbm;
    }
    
    public final LocationSettingsStates getLocationSettingsStates() {
        return this.zzbm;
    }
    
    public final Status getStatus() {
        return this.zzbl;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getStatus(), n, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.getLocationSettingsStates(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
