// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Arrays;
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

@SafeParcelable$Class(creator = "AcceptConnectionRequestParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzm extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzm> CREATOR;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @SafeParcelable$Field(getter = "getConnectionEventListenerAsBinder", id = 2, type = "android.os.IBinder")
    private zzdg zzas;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 3)
    private String zzat;
    @Nullable
    @SafeParcelable$Field(getter = "getHandshakeData", id = 4)
    private byte[] zzau;
    @Nullable
    @SafeParcelable$Field(getter = "getPayloadListenerAsBinder", id = 5, type = "android.os.IBinder")
    private zzdw zzav;
    
    static {
        CREATOR = (Parcelable$Creator)new zzp();
    }
    
    private zzm() {
    }
    
    @SafeParcelable$Constructor
    zzm(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder2, @SafeParcelable$Param(id = 3) final String s, @Nullable @SafeParcelable$Param(id = 4) final byte[] array, @Nullable @SafeParcelable$Param(id = 5) final IBinder binder3) {
        final zzdw zzdw = null;
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
        zzdg zzdg;
        if (binder2 == null) {
            zzdg = null;
        }
        else {
            final IInterface queryLocalInterface2 = binder2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionEventListener");
            if (queryLocalInterface2 instanceof zzdg) {
                zzdg = (zzdg)queryLocalInterface2;
            }
            else {
                zzdg = new zzdi(binder2);
            }
        }
        zzdw zzdw2;
        if (binder3 == null) {
            zzdw2 = zzdw;
        }
        else {
            final IInterface queryLocalInterface3 = binder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IPayloadListener");
            if (queryLocalInterface3 instanceof zzdw) {
                zzdw2 = (zzdw)queryLocalInterface3;
            }
            else {
                zzdw2 = new zzdy(binder3);
            }
        }
        this(zzdz, zzdg, s, array, zzdw2);
    }
    
    private zzm(@Nullable final zzdz zzar, @Nullable final zzdg zzas, final String zzat, @Nullable final byte[] zzau, @Nullable final zzdw zzav) {
        this.zzar = zzar;
        this.zzas = zzas;
        this.zzat = zzat;
        this.zzau = zzau;
        this.zzav = zzav;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzm)) {
                return false;
            }
            final zzm zzm = (zzm)o;
            if (!Objects.equal((Object)this.zzar, (Object)zzm.zzar) || !Objects.equal((Object)this.zzas, (Object)zzm.zzas) || !Objects.equal((Object)this.zzat, (Object)zzm.zzat) || !Arrays.equals(this.zzau, zzm.zzau) || !Objects.equal((Object)this.zzav, (Object)zzm.zzav)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, this.zzas, this.zzat, Arrays.hashCode(this.zzau), this.zzav });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        final IBinder binder = null;
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        IBinder binder2;
        if (this.zzar == null) {
            binder2 = null;
        }
        else {
            binder2 = this.zzar.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 1, binder2, false);
        IBinder binder3;
        if (this.zzas == null) {
            binder3 = null;
        }
        else {
            binder3 = this.zzas.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder3, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzat, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzau, false);
        IBinder binder4;
        if (this.zzav == null) {
            binder4 = binder;
        }
        else {
            binder4 = this.zzav.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, binder4, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
