// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "RegisterStatusCallbackRequestCreator")
public final class zzcb extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzcb> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 5)
    private String zzff;
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private final zzp zzhh;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final ClientAppContext zzhi;
    @SafeParcelable$Field(getter = "getStatusCallbackAsBinder", id = 3, type = "android.os.IBinder")
    private final zzx zziw;
    @SafeParcelable$Field(id = 4)
    public boolean zzix;
    
    static {
        CREATOR = (Parcelable$Creator)new zzcc();
    }
    
    @SafeParcelable$Constructor
    zzcb(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final IBinder binder, @SafeParcelable$Param(id = 3) final IBinder binder2, @SafeParcelable$Param(id = 4) final boolean zzix, @Nullable @SafeParcelable$Param(id = 5) final String zzff, @Nullable @SafeParcelable$Param(id = 6) final ClientAppContext clientAppContext) {
        this.versionCode = versionCode;
        zzp zzhh;
        if (binder == null) {
            zzhh = null;
        }
        else {
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            if (queryLocalInterface instanceof zzp) {
                zzhh = (zzp)queryLocalInterface;
            }
            else {
                zzhh = new zzr(binder);
            }
        }
        this.zzhh = zzhh;
        zzx zziw;
        if (binder2 == null) {
            zziw = null;
        }
        else {
            final IInterface queryLocalInterface2 = binder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IStatusCallback");
            if (queryLocalInterface2 instanceof zzx) {
                zziw = (zzx)queryLocalInterface2;
            }
            else {
                zziw = new zzz(binder2);
            }
        }
        this.zziw = zziw;
        this.zzix = zzix;
        this.zzff = zzff;
        this.zzhi = ClientAppContext.zza(clientAppContext, null, zzff, false);
    }
    
    @VisibleForTesting
    public zzcb(final IBinder binder, final IBinder binder2) {
        this(1, binder, binder2, false, null, null);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zziw.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzix);
        SafeParcelWriter.writeString(parcel, 5, this.zzff, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
