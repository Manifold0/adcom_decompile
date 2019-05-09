// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzy;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationRequestUpdateDataCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzbf extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzbf> CREATOR;
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 4)
    private PendingIntent zzbv;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", id = 1)
    private int zzcg;
    @SafeParcelable$Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 6, type = "android.os.IBinder")
    private zzaj zzcj;
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 2)
    private zzbd zzdl;
    @SafeParcelable$Field(defaultValueUnchecked = "null", getter = "getLocationListenerBinder", id = 3, type = "android.os.IBinder")
    private zzx zzdm;
    @SafeParcelable$Field(defaultValueUnchecked = "null", getter = "getLocationCallbackBinder", id = 5, type = "android.os.IBinder")
    private zzu zzdn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzbg();
    }
    
    @SafeParcelable$Constructor
    zzbf(@SafeParcelable$Param(id = 1) final int zzcg, @SafeParcelable$Param(id = 2) final zzbd zzdl, @SafeParcelable$Param(id = 3) final IBinder binder, @SafeParcelable$Param(id = 4) final PendingIntent zzbv, @SafeParcelable$Param(id = 5) final IBinder binder2, @SafeParcelable$Param(id = 6) final IBinder binder3) {
        final zzaj zzaj = null;
        this.zzcg = zzcg;
        this.zzdl = zzdl;
        zzx zzc;
        if (binder == null) {
            zzc = null;
        }
        else {
            zzc = zzy.zzc(binder);
        }
        this.zzdm = zzc;
        this.zzbv = zzbv;
        zzu zzb;
        if (binder2 == null) {
            zzb = null;
        }
        else {
            zzb = zzv.zzb(binder2);
        }
        this.zzdn = zzb;
        zzaj zzcj;
        if (binder3 == null) {
            zzcj = zzaj;
        }
        else {
            zzcj = zzaj;
            if (binder3 != null) {
                final IInterface queryLocalInterface = binder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
                if (queryLocalInterface instanceof zzaj) {
                    zzcj = (zzaj)queryLocalInterface;
                }
                else {
                    zzcj = new zzal(binder3);
                }
            }
        }
        this.zzcj = zzcj;
    }
    
    public static zzbf zza(final zzu zzu, @Nullable final zzaj zzaj) {
        final IBinder binder = zzu.asBinder();
        IBinder binder2;
        if (zzaj != null) {
            binder2 = zzaj.asBinder();
        }
        else {
            binder2 = null;
        }
        return new zzbf(2, null, null, null, binder, binder2);
    }
    
    public static zzbf zza(final zzx zzx, @Nullable final zzaj zzaj) {
        final IBinder binder = zzx.asBinder();
        IBinder binder2;
        if (zzaj != null) {
            binder2 = zzaj.asBinder();
        }
        else {
            binder2 = null;
        }
        return new zzbf(2, null, binder, null, null, binder2);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final IBinder binder = null;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzdl, n, false);
        IBinder binder2;
        if (this.zzdm == null) {
            binder2 = null;
        }
        else {
            binder2 = this.zzdm.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, binder2, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzbv, n, false);
        IBinder binder3;
        if (this.zzdn == null) {
            binder3 = null;
        }
        else {
            binder3 = this.zzdn.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 5, binder3, false);
        IBinder binder4;
        if (this.zzcj == null) {
            binder4 = binder;
        }
        else {
            binder4 = this.zzcj.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 6, binder4, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
