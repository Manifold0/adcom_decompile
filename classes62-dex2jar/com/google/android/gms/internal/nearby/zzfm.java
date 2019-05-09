// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "RejectConnectionRequestParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfm extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfm> CREATOR;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 2)
    private String zzat;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfp();
    }
    
    private zzfm() {
    }
    
    @SafeParcelable$Constructor
    zzfm(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @SafeParcelable$Param(id = 2) final String s) {
        zzdz zzdz;
        if (binder == null) {
            zzdz = null;
        }
        else {
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            if (queryLocalInterface instanceof zzdz) {
                zzdz = (zzdz)queryLocalInterface;
            }
            else {
                zzdz = new zzeb(binder);
            }
        }
        this(zzdz, s);
    }
    
    private zzfm(@Nullable final zzdz zzar, final String zzat) {
        this.zzar = zzar;
        this.zzat = zzat;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzfm)) {
                return false;
            }
            final zzfm zzfm = (zzfm)o;
            if (!Objects.equal((Object)this.zzar, (Object)zzfm.zzar) || !Objects.equal((Object)this.zzat, (Object)zzfm.zzat)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, this.zzat });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        IBinder binder;
        if (this.zzar == null) {
            binder = null;
        }
        else {
            binder = this.zzar.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 1, binder, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzat, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
