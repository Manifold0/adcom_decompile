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
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CancelPayloadParamsCreator")
public final class zzq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzq> CREATOR;
    @SafeParcelable$Field(getter = "getPayloadId", id = 2)
    private long zzaf;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    
    static {
        CREATOR = (Parcelable$Creator)new zzt();
    }
    
    private zzq() {
    }
    
    @SafeParcelable$Constructor
    zzq(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @SafeParcelable$Param(id = 2) final long n) {
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
        this(zzdz, n);
    }
    
    private zzq(@Nullable final zzdz zzar, final long zzaf) {
        this.zzar = zzar;
        this.zzaf = zzaf;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzq)) {
                return false;
            }
            final zzq zzq = (zzq)o;
            if (!Objects.equal((Object)this.zzar, (Object)zzq.zzar) || !Objects.equal((Object)this.zzaf, (Object)zzq.zzaf)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, this.zzaf });
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
        SafeParcelWriter.writeLong(parcel, 2, this.zzaf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
