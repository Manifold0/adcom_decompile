// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "OwnedByMeFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzz extends zza
{
    public static final Parcelable$Creator<zzz> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaa();
    }
    
    @SafeParcelable$Constructor
    public zzz() {
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
    
    public final <F> F zza(final zzj<F> zzj) {
        return zzj.zzbb();
    }
}
