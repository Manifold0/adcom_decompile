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

@Class(creator = "UnpublishRequestCreator")
public final class zzce extends AbstractSafeParcelable {
    public static final Creator<zzce> CREATOR = new zzcf();
    @VersionField(id = 1)
    private final int versionCode;
    @Nullable
    @Field(id = 4)
    @Deprecated
    private final String zzff;
    @Field(id = 6)
    @Deprecated
    private final boolean zzfg;
    @Nullable
    @Field(id = 5)
    @Deprecated
    private final String zzfj;
    @Field(getter = "getCallbackAsBinder", id = 3, type = "android.os.IBinder")
    private final zzp zzhh;
    @Nullable
    @Field(id = 7)
    @Deprecated
    private final ClientAppContext zzhi;
    @Field(id = 2)
    private final zzaf zzis;

    @Constructor
    zzce(@Param(id = 1) int i, @Param(id = 2) zzaf zzaf, @Param(id = 3) IBinder iBinder, @Nullable @Param(id = 4) String str, @Nullable @Param(id = 5) String str2, @Param(id = 6) boolean z, @Nullable @Param(id = 7) ClientAppContext clientAppContext) {
        zzp zzp;
        this.versionCode = i;
        this.zzis = zzaf;
        if (iBinder == null) {
            zzp = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            zzp = queryLocalInterface instanceof zzp ? (zzp) queryLocalInterface : new zzr(iBinder);
        }
        this.zzhh = zzp;
        this.zzff = str;
        this.zzfj = str2;
        this.zzfg = z;
        this.zzhi = ClientAppContext.zza(clientAppContext, str2, str, z);
    }

    @VisibleForTesting
    public zzce(zzaf zzaf, IBinder iBinder) {
        this(1, zzaf, iBinder, null, null, false, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzis, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzff, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzfj, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzfg);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzhi, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
