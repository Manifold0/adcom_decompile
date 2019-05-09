// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import com.google.android.gms.location.zzs;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.location.zzr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DeviceOrientationRequestUpdateDataCreator")
public final class zzo extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzo> CREATOR;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequestUpdateData.OPERATION_ADD", id = 1)
    private int zzcg;
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 2)
    private zzm zzch;
    @SafeParcelable$Field(defaultValueUnchecked = "null", getter = "getDeviceOrientationListenerBinder", id = 3, type = "android.os.IBinder")
    private zzr zzci;
    @SafeParcelable$Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", id = 4, type = "android.os.IBinder")
    private zzaj zzcj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzp();
    }
    
    @SafeParcelable$Constructor
    zzo(@SafeParcelable$Param(id = 1) final int zzcg, @SafeParcelable$Param(id = 2) final zzm zzch, @SafeParcelable$Param(id = 3) final IBinder binder, @SafeParcelable$Param(id = 4) final IBinder binder2) {
        final zzaj zzaj = null;
        this.zzcg = zzcg;
        this.zzch = zzch;
        zzr zza;
        if (binder == null) {
            zza = null;
        }
        else {
            zza = zzs.zza(binder);
        }
        this.zzci = zza;
        zzaj zzcj;
        if (binder2 == null) {
            zzcj = zzaj;
        }
        else {
            zzcj = zzaj;
            if (binder2 != null) {
                final IInterface queryLocalInterface = binder2.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
                if (queryLocalInterface instanceof zzaj) {
                    zzcj = (zzaj)queryLocalInterface;
                }
                else {
                    zzcj = new zzal(binder2);
                }
            }
        }
        this.zzcj = zzcj;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final IBinder binder = null;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzch, n, false);
        IBinder binder2;
        if (this.zzci == null) {
            binder2 = null;
        }
        else {
            binder2 = this.zzci.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 3, binder2, false);
        IBinder binder3;
        if (this.zzcj == null) {
            binder3 = binder;
        }
        else {
            binder3 = this.zzcj.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, binder3, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
