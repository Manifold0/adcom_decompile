// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ClientDisconnectingParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzu> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzw();
    }
    
    @SafeParcelable$Constructor
    zzu() {
    }
    
    public final boolean equals(final Object o) {
        return this == o || o instanceof zzu;
    }
    
    public final int hashCode() {
        return 0;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
}
