// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DisconnectRequestCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzad extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzad> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new zzae();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public zzad() {
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        SafeParcelWriter.finishObjectHeader(parcel, SafeParcelWriter.beginObjectHeader(parcel));
    }
}
