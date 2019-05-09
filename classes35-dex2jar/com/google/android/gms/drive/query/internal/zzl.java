// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "FullTextSearchFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzl extends zza
{
    public static final Parcelable$Creator<zzl> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final String value;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
    }
    
    @SafeParcelable$Constructor
    public zzl(@SafeParcelable$Param(id = 1) final String value) {
        this.value = value;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.value, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <F> F zza(final zzj<F> zzj) {
        return zzj.zzg(this.value);
    }
}
