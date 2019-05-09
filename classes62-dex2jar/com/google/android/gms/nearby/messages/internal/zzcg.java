// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.support.annotation.VisibleForTesting;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "UnsubscribeRequestCreator")
public final class zzcg extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzcg> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final String zzff;
    @Deprecated
    @SafeParcelable$Field(id = 8)
    private final boolean zzfg;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 7)
    private final String zzfj;
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 3, type = "android.os.IBinder")
    private final zzp zzhh;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 9)
    private final ClientAppContext zzhi;
    @Nullable
    @SafeParcelable$Field(getter = "getMessageListenerAsBinder", id = 2, type = "android.os.IBinder")
    private final zzm zziy;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final PendingIntent zzja;
    @Deprecated
    @SafeParcelable$Field(id = 5)
    private final int zzjb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzch();
    }
    
    @VisibleForTesting
    @SafeParcelable$Constructor
    public zzcg(@SafeParcelable$Param(id = 1) final int versionCode, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder, @SafeParcelable$Param(id = 3) final IBinder binder2, @Nullable @SafeParcelable$Param(id = 4) final PendingIntent zzja, @SafeParcelable$Param(id = 5) final int zzjb, @Nullable @SafeParcelable$Param(id = 6) final String zzff, @Nullable @SafeParcelable$Param(id = 7) final String zzfj, @SafeParcelable$Param(id = 8) final boolean zzfg, @Nullable @SafeParcelable$Param(id = 9) final ClientAppContext clientAppContext) {
        final zzp zzp = null;
        this.versionCode = versionCode;
        zzm zziy;
        if (binder == null) {
            zziy = null;
        }
        else {
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IMessageListener");
            if (queryLocalInterface instanceof zzm) {
                zziy = (zzm)queryLocalInterface;
            }
            else {
                zziy = new zzo(binder);
            }
        }
        this.zziy = zziy;
        zzp zzhh;
        if (binder2 == null) {
            zzhh = zzp;
        }
        else {
            final IInterface queryLocalInterface2 = binder2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
            if (queryLocalInterface2 instanceof zzp) {
                zzhh = (zzp)queryLocalInterface2;
            }
            else {
                zzhh = new zzr(binder2);
            }
        }
        this.zzhh = zzhh;
        this.zzja = zzja;
        this.zzjb = zzjb;
        this.zzff = zzff;
        this.zzfj = zzfj;
        this.zzfg = zzfg;
        this.zzhi = ClientAppContext.zza(clientAppContext, zzfj, zzff, zzfg);
    }
    
    @VisibleForTesting
    public zzcg(final IBinder binder, final IBinder binder2, @Nullable final PendingIntent pendingIntent) {
        this(1, binder, binder2, pendingIntent, 0, null, null, false, null);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        IBinder binder;
        if (this.zziy == null) {
            binder = null;
        }
        else {
            binder = this.zziy.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzhh.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzja, n, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzjb);
        SafeParcelWriter.writeString(parcel, 6, this.zzff, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzfj, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzfg);
        SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
