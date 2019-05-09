// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AuthAccountResultCreator")
public final class zaa extends AbstractSafeParcelable implements Result
{
    public static final Parcelable$Creator<zaa> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getConnectionResultCode", id = 2)
    private int zarz;
    @SafeParcelable$Field(getter = "getRawAuthResolutionIntent", id = 3)
    private Intent zasa;
    
    static {
        CREATOR = (Parcelable$Creator)new zab();
    }
    
    public zaa() {
        this(0, null);
    }
    
    @SafeParcelable$Constructor
    zaa(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final int zarz, @SafeParcelable$Param(id = 3) final Intent zasa) {
        this.zalf = zalf;
        this.zarz = zarz;
        this.zasa = zasa;
    }
    
    private zaa(final int n, final Intent intent) {
        this(2, 0, null);
    }
    
    public final Status getStatus() {
        if (this.zarz == 0) {
            return Status.RESULT_SUCCESS;
        }
        return Status.RESULT_CANCELED;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeInt(parcel, 2, this.zarz);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zasa, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
