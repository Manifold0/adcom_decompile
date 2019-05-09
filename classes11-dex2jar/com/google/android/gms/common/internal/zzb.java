// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.Feature;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Class(creator = "ConnectionInfoCreator")
public final class zzb extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzb> CREATOR;
    @Field(id = 1)
    Bundle zzda;
    @Field(id = 2)
    Feature[] zzdb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public zzb() {
    }
    
    @Constructor
    zzb(@Param(id = 1) final Bundle zzda, @Param(id = 2) final Feature[] zzdb) {
        this.zzda = zzda;
        this.zzdb = zzdb;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzda, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzdb, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
