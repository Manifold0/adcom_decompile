// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "RetrieveDataRequestCreator")
public final class zzad extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzad> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final String accountType;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzae();
    }
    
    @SafeParcelable$Constructor
    zzad(@SafeParcelable$Param(id = 1) final int n, @SafeParcelable$Param(id = 2) final String s) {
        this.zzv = 1;
        this.accountType = (String)Preconditions.checkNotNull((Object)s);
    }
    
    public zzad(final String s) {
        this(1, s);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeString(parcel, 2, this.accountType, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
