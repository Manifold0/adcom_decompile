// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
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

@Deprecated
@SafeParcelable$Class(creator = "GetPermissionStatusRequestCreator")
public final class zzh extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzh> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 3)
    private final String zzff;
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private final zzp zzhh;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final ClientAppContext zzhi;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    @SafeParcelable$Constructor
    zzh(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final IBinder binder, @Nullable @SafeParcelable$Param(id = 3) final String zzff, @Nullable @SafeParcelable$Param(id = 4) final ClientAppContext clientAppContext) {
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
        this.zzff = zzff;
        this.zzhi = ClientAppContext.zza(clientAppContext, null, zzff, false);
    }
    
    zzh(final IBinder binder) {
        this(1, binder, null, null);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzff, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
