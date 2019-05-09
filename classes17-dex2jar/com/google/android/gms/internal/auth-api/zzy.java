// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.auth.api.credentials.Credential;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SaveRequestCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzy extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzy> CREATOR;
    @SafeParcelable$Field(getter = "getCredential", id = 1)
    private final Credential zzal;
    
    static {
        CREATOR = (Parcelable$Creator)new zzz();
    }
    
    @SafeParcelable$Constructor
    public zzy(@SafeParcelable$Param(id = 1) final Credential zzal) {
        this.zzal = zzal;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzal, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
