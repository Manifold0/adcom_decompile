// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.IBinder;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "StartDiscoveryParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzgc extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgc> CREATOR;
    @SafeParcelable$Field(getter = "getDurationMillis", id = 4)
    private long durationMillis;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private zzdp zzel;
    @SafeParcelable$Field(getter = "getOptions", id = 5)
    private DiscoveryOptions zzem;
    @Nullable
    @SafeParcelable$Field(getter = "getDiscoveryListenerAsBinder", id = 6, type = "android.os.IBinder")
    private zzdr zzen;
    @SafeParcelable$Field(getter = "getServiceId", id = 3)
    private String zzu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgf();
    }
    
    private zzgc() {
    }
    
    @SafeParcelable$Constructor
    zzgc(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder2, @SafeParcelable$Param(id = 3) final String s, @SafeParcelable$Param(id = 4) final long n, @SafeParcelable$Param(id = 5) final DiscoveryOptions discoveryOptions, @Nullable @SafeParcelable$Param(id = 6) final IBinder binder3) {
        final zzdr zzdr = null;
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
        zzdp zzdp;
        if (binder2 == null) {
            zzdp = null;
        }
        else {
            final IInterface queryLocalInterface2 = binder2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IDiscoveryCallback");
            if (queryLocalInterface2 instanceof zzdp) {
                zzdp = (zzdp)queryLocalInterface2;
            }
            else {
                zzdp = new zzdq(binder2);
            }
        }
        zzdr zzdr2;
        if (binder3 == null) {
            zzdr2 = zzdr;
        }
        else {
            final IInterface queryLocalInterface3 = binder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
            if (queryLocalInterface3 instanceof zzdr) {
                zzdr2 = (zzdr)queryLocalInterface3;
            }
            else {
                zzdr2 = new zzdt(binder3);
            }
        }
        this(zzdz, zzdp, s, n, discoveryOptions, zzdr2);
    }
    
    private zzgc(@Nullable final zzdz zzar, @Nullable final zzdp zzel, final String zzu, final long durationMillis, final DiscoveryOptions zzem, @Nullable final zzdr zzen) {
        this.zzar = zzar;
        this.zzel = zzel;
        this.zzu = zzu;
        this.durationMillis = durationMillis;
        this.zzem = zzem;
        this.zzen = zzen;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzgc)) {
                return false;
            }
            final zzgc zzgc = (zzgc)o;
            if (!Objects.equal((Object)this.zzar, (Object)zzgc.zzar) || !Objects.equal((Object)this.zzel, (Object)zzgc.zzel) || !Objects.equal((Object)this.zzu, (Object)zzgc.zzu) || !Objects.equal((Object)this.durationMillis, (Object)zzgc.durationMillis) || !Objects.equal((Object)this.zzem, (Object)zzgc.zzem) || !Objects.equal((Object)this.zzen, (Object)zzgc.zzen)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, this.zzel, this.zzu, this.durationMillis, this.zzem, this.zzen });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final IBinder binder = null;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        IBinder binder2;
        if (this.zzar == null) {
            binder2 = null;
        }
        else {
            binder2 = this.zzar.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 1, binder2, false);
        IBinder binder3;
        if (this.zzel == null) {
            binder3 = null;
        }
        else {
            binder3 = this.zzel.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzu, false);
        SafeParcelWriter.writeLong(parcel, 4, this.durationMillis);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzem, n, false);
        IBinder binder4;
        if (this.zzen == null) {
            binder4 = binder;
        }
        else {
            binder4 = this.zzen.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, binder4, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
