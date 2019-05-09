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

@SafeParcelable$Class(creator = "UnpublishRequestCreator")
public final class zzce extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzce> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final String zzff;
    @Deprecated
    @SafeParcelable$Field(id = 6)
    private final boolean zzfg;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 5)
    private final String zzfj;
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 3, type = "android.os.IBinder")
    private final zzp zzhh;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 7)
    private final ClientAppContext zzhi;
    @SafeParcelable$Field(id = 2)
    private final zzaf zzis;
    
    static {
        CREATOR = (Parcelable$Creator)new zzcf();
    }
    
    @SafeParcelable$Constructor
    zzce(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final zzaf zzis, @SafeParcelable$Param(id = 3) final IBinder binder, @Nullable @SafeParcelable$Param(id = 4) final String zzff, @Nullable @SafeParcelable$Param(id = 5) final String zzfj, @SafeParcelable$Param(id = 6) final boolean zzfg, @Nullable @SafeParcelable$Param(id = 7) final ClientAppContext clientAppContext) {
        this.versionCode = versionCode;
        this.zzis = zzis;
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
        this.zzfj = zzfj;
        this.zzfg = zzfg;
        this.zzhi = ClientAppContext.zza(clientAppContext, zzfj, zzff, zzfg);
    }
    
    @VisibleForTesting
    public zzce(final zzaf zzaf, final IBinder binder) {
        this(1, zzaf, binder, null, null, false, null);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzis, n, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzff, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzfj, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzfg);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
