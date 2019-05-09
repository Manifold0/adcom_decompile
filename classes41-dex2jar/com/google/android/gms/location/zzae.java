// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationSettingsConfigurationCreator")
@SafeParcelable$Reserved({ 3, 4, 1000 })
public final class zzae extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzae> CREATOR;
    @SafeParcelable$Field(defaultValue = "", getter = "getJustificationText", id = 1)
    private final String zzbd;
    @SafeParcelable$Field(defaultValue = "", getter = "getExperimentId", id = 2)
    private final String zzbe;
    @SafeParcelable$Field(defaultValue = "", getter = "getTitleText", id = 5)
    private final String zzbf;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaf();
    }
    
    @SafeParcelable$Constructor
    zzae(@SafeParcelable$Param(id = 5) final String zzbf, @SafeParcelable$Param(id = 1) final String zzbd, @SafeParcelable$Param(id = 2) final String zzbe) {
        this.zzbf = zzbf;
        this.zzbd = zzbd;
        this.zzbe = zzbe;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzbd, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzbe, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
