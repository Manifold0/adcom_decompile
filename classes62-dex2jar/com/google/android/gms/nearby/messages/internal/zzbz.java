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
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "PublishRequestCreator")
public final class zzbz extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzbz> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 5)
    private final String zzff;
    @Deprecated
    @SafeParcelable$Field(id = 9)
    private final boolean zzfg;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final String zzfj;
    @SafeParcelable$Field(id = 11)
    private final int zzhf;
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 4, type = "android.os.IBinder")
    private final zzp zzhh;
    @Deprecated
    @SafeParcelable$Field(id = 10)
    private final ClientAppContext zzhi;
    @SafeParcelable$Field(id = 2)
    private final zzaf zzis;
    @SafeParcelable$Field(id = 3)
    private final Strategy zzit;
    @Deprecated
    @SafeParcelable$Field(id = 7)
    private final boolean zziu;
    @Nullable
    @SafeParcelable$Field(getter = "getPublishCallbackAsBinder", id = 8, type = "android.os.IBinder")
    private final zzu zziv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzca();
    }
    
    @SafeParcelable$Constructor
    zzbz(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final zzaf zzis, @SafeParcelable$Param(id = 3) final Strategy zzit, @SafeParcelable$Param(id = 4) final IBinder binder, @Nullable @SafeParcelable$Param(id = 5) final String zzff, @Nullable @SafeParcelable$Param(id = 6) final String zzfj, @SafeParcelable$Param(id = 7) final boolean zziu, @Nullable @SafeParcelable$Param(id = 8) final IBinder binder2, @SafeParcelable$Param(id = 9) final boolean zzfg, @Nullable @SafeParcelable$Param(id = 10) final ClientAppContext clientAppContext, @SafeParcelable$Param(id = 11) final int zzhf) {
        final zzu zzu = null;
        this.versionCode = versionCode;
        this.zzis = zzis;
        this.zzit = zzit;
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
        this.zziu = zziu;
        zzu zziv;
        if (binder2 == null) {
            zziv = zzu;
        }
        else {
            zziv = zzu;
            if (binder2 != null) {
                final IInterface queryLocalInterface2 = binder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IPublishCallback");
                if (queryLocalInterface2 instanceof zzu) {
                    zziv = (zzu)queryLocalInterface2;
                }
                else {
                    zziv = new zzw(binder2);
                }
            }
        }
        this.zziv = zziv;
        this.zzfg = zzfg;
        this.zzhi = ClientAppContext.zza(clientAppContext, zzfj, zzff, zzfg);
        this.zzhf = zzhf;
    }
    
    @VisibleForTesting
    public zzbz(final zzaf zzaf, final Strategy strategy, final IBinder binder, @Nullable final IBinder binder2, final int n) {
        this(2, zzaf, strategy, binder, null, null, false, binder2, false, null, n);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzis, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzit, n, false);
        SafeParcelWriter.writeIBinder(parcel, 4, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 5, this.zzff, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzfj, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zziu);
        IBinder binder;
        if (this.zziv == null) {
            binder = null;
        }
        else {
            binder = this.zziv.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 8, binder, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzfg);
        SafeParcelWriter.writeParcelable(parcel, 10, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.writeInt(parcel, 11, this.zzhf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
