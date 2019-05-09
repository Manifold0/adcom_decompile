// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnStartAdvertisingResultParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzez extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzez> CREATOR;
    @SafeParcelable$Field(getter = "getStatusCode", id = 1)
    private int statusCode;
    @SafeParcelable$Field(getter = "getLocalEndpointName", id = 2)
    private String zzcc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfa();
    }
    
    private zzez() {
    }
    
    @SafeParcelable$Constructor
    zzez(@SafeParcelable$Param(id = 1) final int statusCode, @SafeParcelable$Param(id = 2) final String zzcc) {
        this.statusCode = statusCode;
        this.zzcc = zzcc;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzez)) {
                return false;
            }
            final zzez zzez = (zzez)o;
            if (!Objects.equal((Object)this.statusCode, (Object)zzez.statusCode) || !Objects.equal((Object)this.zzcc, (Object)zzez.zzcc)) {
                return false;
            }
        }
        return true;
    }
    
    public final String getLocalEndpointName() {
        return this.zzcc;
    }
    
    public final int getStatusCode() {
        return this.statusCode;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.statusCode, this.zzcc });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.statusCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzcc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
