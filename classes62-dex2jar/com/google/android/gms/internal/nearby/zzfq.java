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

@SafeParcelable$Class(creator = "SendConnectionRequestParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfq> CREATOR;
    @Nullable
    @SafeParcelable$Field(getter = "getName", id = 4)
    private String name;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @SafeParcelable$Field(getter = "getConnectionEventListenerAsBinder", id = 2, type = "android.os.IBinder")
    private zzdg zzas;
    @SafeParcelable$Field(getter = "getRemoteEndpointId", id = 5)
    private String zzat;
    @Nullable
    @SafeParcelable$Field(getter = "getHandshakeData", id = 6)
    private byte[] zzau;
    @Nullable
    @SafeParcelable$Field(getter = "getConnectionResponseListenerAsBinder", id = 3, type = "android.os.IBinder")
    private zzdm zzeb;
    @Nullable
    @SafeParcelable$Field(getter = "getConnectionLifecycleListenerAsBinder", id = 7, type = "android.os.IBinder")
    private zzdj zzec;
    
    static {
        CREATOR = (Parcelable$Creator)new zzft();
    }
    
    private zzfq() {
    }
    
    @SafeParcelable$Constructor
    zzfq(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder2, @Nullable @SafeParcelable$Param(id = 3) final IBinder binder3, @Nullable @SafeParcelable$Param(id = 4) final String s, @SafeParcelable$Param(id = 5) final String s2, @Nullable @SafeParcelable$Param(id = 6) final byte[] array, @Nullable @SafeParcelable$Param(id = 7) final IBinder binder4) {
        final zzdj zzdj = null;
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
        zzdm zzdm;
        if (binder3 == null) {
            zzdm = null;
        }
        else {
            final IInterface queryLocalInterface3 = binder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionResponseListener");
            if (queryLocalInterface3 instanceof zzdm) {
                zzdm = (zzdm)queryLocalInterface3;
            }
            else {
                zzdm = new zzdo(binder3);
            }
        }
        zzdj zzdj2;
        if (binder4 == null) {
            zzdj2 = zzdj;
        }
        else {
            final IInterface queryLocalInterface4 = binder4.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
            if (queryLocalInterface4 instanceof zzdj) {
                zzdj2 = (zzdj)queryLocalInterface4;
            }
            else {
                zzdj2 = new zzdl(binder4);
            }
        }
        this(zzdz, zzdg, zzdm, s, s2, array, zzdj2);
    }
    
    private zzfq(@Nullable final zzdz zzar, @Nullable final zzdg zzas, @Nullable final zzdm zzeb, @Nullable final String name, final String zzat, @Nullable final byte[] zzau, @Nullable final zzdj zzec) {
        this.zzar = zzar;
        this.zzas = zzas;
        this.zzeb = zzeb;
        this.name = name;
        this.zzat = zzat;
        this.zzau = zzau;
        this.zzec = zzec;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzfq)) {
                return false;
            }
            final zzfq zzfq = (zzfq)o;
            if (!Objects.equal((Object)this.zzar, (Object)zzfq.zzar) || !Objects.equal((Object)this.zzas, (Object)zzfq.zzas) || !Objects.equal((Object)this.zzeb, (Object)zzfq.zzeb) || !Objects.equal((Object)this.name, (Object)zzfq.name) || !Objects.equal((Object)this.zzat, (Object)zzfq.zzat) || !Arrays.equals(this.zzau, zzfq.zzau) || !Objects.equal((Object)this.zzec, (Object)zzfq.zzec)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, this.zzas, this.zzeb, this.name, this.zzat, Arrays.hashCode(this.zzau), this.zzec });
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
        IBinder binder4;
        if (this.zzeb == null) {
            binder4 = null;
        }
        else {
            binder4 = this.zzeb.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, binder4, false);
        SafeParcelWriter.writeString(parcel, 4, this.name, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzat, false);
        SafeParcelWriter.writeByteArray(parcel, 6, this.zzau, false);
        IBinder binder5;
        if (this.zzec == null) {
            binder5 = binder;
        }
        else {
            binder5 = this.zzec.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 7, binder5, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
