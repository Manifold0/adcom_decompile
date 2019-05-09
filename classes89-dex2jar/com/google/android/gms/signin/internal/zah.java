// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SignInRequestCreator")
public final class zah extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zah> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getResolveAccountRequest", id = 2)
    private final ResolveAccountRequest zasd;
    
    static {
        CREATOR = (Parcelable$Creator)new zai();
    }
    
    @SafeParcelable$Constructor
    zah(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final ResolveAccountRequest zasd) {
        this.zalf = zalf;
        this.zasd = zasd;
    }
    
    public zah(final ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zasd, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
