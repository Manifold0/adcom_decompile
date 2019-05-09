// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationSettingsStatesCreator")
@SafeParcelable$Reserved({ 1000 })
public final class LocationSettingsStates extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<LocationSettingsStates> CREATOR;
    @SafeParcelable$Field(getter = "isGpsUsable", id = 1)
    private final boolean zzbn;
    @SafeParcelable$Field(getter = "isNetworkLocationUsable", id = 2)
    private final boolean zzbo;
    @SafeParcelable$Field(getter = "isBleUsable", id = 3)
    private final boolean zzbp;
    @SafeParcelable$Field(getter = "isGpsPresent", id = 4)
    private final boolean zzbq;
    @SafeParcelable$Field(getter = "isNetworkLocationPresent", id = 5)
    private final boolean zzbr;
    @SafeParcelable$Field(getter = "isBlePresent", id = 6)
    private final boolean zzbs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzai();
    }
    
    @SafeParcelable$Constructor
    public LocationSettingsStates(@SafeParcelable$Param(id = 1) final boolean zzbn, @SafeParcelable$Param(id = 2) final boolean zzbo, @SafeParcelable$Param(id = 3) final boolean zzbp, @SafeParcelable$Param(id = 4) final boolean zzbq, @SafeParcelable$Param(id = 5) final boolean zzbr, @SafeParcelable$Param(id = 6) final boolean zzbs) {
        this.zzbn = zzbn;
        this.zzbo = zzbo;
        this.zzbp = zzbp;
        this.zzbq = zzbq;
        this.zzbr = zzbr;
        this.zzbs = zzbs;
    }
    
    public static LocationSettingsStates fromIntent(final Intent intent) {
        return (LocationSettingsStates)SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", (Parcelable$Creator)LocationSettingsStates.CREATOR);
    }
    
    public final boolean isBlePresent() {
        return this.zzbs;
    }
    
    public final boolean isBleUsable() {
        return this.zzbp;
    }
    
    public final boolean isGpsPresent() {
        return this.zzbq;
    }
    
    public final boolean isGpsUsable() {
        return this.zzbn;
    }
    
    public final boolean isLocationPresent() {
        return this.zzbq || this.zzbr;
    }
    
    public final boolean isLocationUsable() {
        return this.zzbn || this.zzbo;
    }
    
    public final boolean isNetworkLocationPresent() {
        return this.zzbr;
    }
    
    public final boolean isNetworkLocationUsable() {
        return this.zzbo;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.isGpsUsable());
        SafeParcelWriter.writeBoolean(parcel, 2, this.isNetworkLocationUsable());
        SafeParcelWriter.writeBoolean(parcel, 3, this.isBleUsable());
        SafeParcelWriter.writeBoolean(parcel, 4, this.isGpsPresent());
        SafeParcelWriter.writeBoolean(parcel, 5, this.isNetworkLocationPresent());
        SafeParcelWriter.writeBoolean(parcel, 6, this.isBlePresent());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
