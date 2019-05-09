// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzj;
import android.os.IBinder;
import javax.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Class(creator = "GoogleCertificatesQueryCreator")
public final class zzk extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzk> CREATOR;
    @Field(getter = "getAllowTestKeys", id = 3)
    private final boolean zzaa;
    @Field(defaultValue = "false", getter = "getForbidTestKeys", id = 4)
    private final boolean zzab;
    @Field(getter = "getCallingPackage", id = 1)
    private final String zzy;
    @Nullable
    @Field(getter = "getCallingCertificateBinder", id = 2, type = "android.os.IBinder")
    private final zze zzz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzl();
    }
    
    @Constructor
    zzk(@Param(id = 1) final String zzy, @Nullable @Param(id = 2) final IBinder binder, @Param(id = 3) final boolean zzaa, @Param(id = 4) final boolean zzab) {
        this.zzy = zzy;
        this.zzz = zza(binder);
        this.zzaa = zzaa;
        this.zzab = zzab;
    }
    
    zzk(final String zzy, @Nullable final zze zzz, final boolean zzaa, final boolean zzab) {
        this.zzy = zzy;
        this.zzz = zzz;
        this.zzaa = zzaa;
        this.zzab = zzab;
    }
    
    @Nullable
    private static zze zza(@Nullable final IBinder binder) {
        if (binder == null) {
            return null;
        }
        while (true) {
        Label_0060:
            while (true) {
                IObjectWrapper zzb;
                try {
                    zzb = zzj.zzb(binder).zzb();
                    if (zzb == null) {
                        final byte[] array = null;
                        if (array != null) {
                            return new zzf(array);
                        }
                        break Label_0060;
                    }
                }
                catch (RemoteException ex) {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", (Throwable)ex);
                    return null;
                }
                final byte[] array = ObjectWrapper.unwrap(zzb);
                continue;
            }
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
            return null;
        }
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzy, false);
        IBinder binder;
        if (this.zzz == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            binder = null;
        }
        else {
            binder = this.zzz.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzaa);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzab);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
