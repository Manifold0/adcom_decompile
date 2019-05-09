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
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "RegisterStatusCallbackRequestCreator")
public final class zzcb extends AbstractSafeParcelable {
    public static final Creator<zzcb> CREATOR = new zzcc();
    @VersionField(id = 1)
    private final int versionCode;
    @Nullable
    @Field(id = 5)
    @Deprecated
    private String zzff;
    @Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private final zzp zzhh;
    @Nullable
    @Field(id = 6)
    @Deprecated
    private final ClientAppContext zzhi;
    @Field(getter = "getStatusCallbackAsBinder", id = 3, type = "android.os.IBinder")
    private final zzx zziw;
    @Field(id = 4)
    public boolean zzix;

    @Constructor
    zzcb(@Param(id = 1) int i, @Param(id = 2) IBinder iBinder, @Param(id = 3) IBinder iBinder2, @Param(id = 4) boolean z, @Nullable @Param(id = 5) String str, @Nullable @Param(id = 6) ClientAppContext clientAppContext) {
        zzp zzp;
        zzx zzx;
        this.versionCode = i;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzhh = zzp;
        if (iBinder2 == null) {
            zzx = null;
        } else {
            queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IStatusCallback");
            zzx = queryLocalInterface instanceof zzx ? (zzx) queryLocalInterface : new zzz(iBinder2);
        }
        this.zziw = zzx;
        this.zzix = z;
        this.zzff = str;
        this.zzhi = ClientAppContext.zza(clientAppContext, null, str, false);
    }

    @VisibleForTesting
    public zzcb(IBinder iBinder, IBinder iBinder2) {
        this(1, iBinder, iBinder2, false, null, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zziw.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzix);
        SafeParcelWriter.writeString(parcel, 5, this.zzff, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzhi, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
