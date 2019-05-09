package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GetPermissionStatusRequestCreator")
@Deprecated
public final class zzh extends AbstractSafeParcelable {
    public static final Creator<zzh> CREATOR = new zzi();
    @VersionField(id = 1)
    private final int versionCode;
    @Nullable
    @Field(id = 3)
    @Deprecated
    private final String zzff;
    @Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private final zzp zzhh;
    @Nullable
    @Field(id = 4)
    @Deprecated
    private final ClientAppContext zzhi;

    @Constructor
    zzh(@Param(id = 1) int i, @Param(id = 2) IBinder iBinder, @Nullable @Param(id = 3) String str, @Nullable @Param(id = 4) ClientAppContext clientAppContext) {
        zzp zzp;
        this.versionCode = i;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzhh = zzp;
        this.zzff = str;
        this.zzhi = ClientAppContext.zza(clientAppContext, null, str, false);
    }

    zzh(IBinder iBinder) {
        this(1, iBinder, null, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzff, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzhi, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
