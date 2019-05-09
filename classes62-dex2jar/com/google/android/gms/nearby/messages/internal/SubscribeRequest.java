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
import android.app.PendingIntent;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SubscribeRequestCreator")
public final class SubscribeRequest extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<SubscribeRequest> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 8)
    private final String zzff;
    @Deprecated
    @SafeParcelable$Field(id = 13)
    private final boolean zzfg;
    @Deprecated
    @Nullable
    @SafeParcelable$Field(id = 9)
    private final String zzfj;
    @SafeParcelable$Field(id = 15)
    private final boolean zzgb;
    @SafeParcelable$Field(id = 16)
    private final int zzgc;
    @SafeParcelable$Field(id = 17)
    private final int zzhf;
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 4, type = "android.os.IBinder")
    private final zzp zzhh;
    @Deprecated
    @SafeParcelable$Field(id = 14)
    private final ClientAppContext zzhi;
    @SafeParcelable$Field(id = 3)
    private final Strategy zzit;
    @Deprecated
    @SafeParcelable$Field(id = 11)
    private final boolean zziu;
    @Nullable
    @SafeParcelable$Field(getter = "getMessageListenerAsBinder", id = 2, type = "android.os.IBinder")
    private final zzm zziy;
    @SafeParcelable$Field(id = 5)
    private final MessageFilter zziz;
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final PendingIntent zzja;
    @Deprecated
    @SafeParcelable$Field(id = 7)
    private final int zzjb;
    @Nullable
    @SafeParcelable$Field(id = 10)
    private final byte[] zzjc;
    @Nullable
    @SafeParcelable$Field(getter = "getSubscribeCallbackAsBinder", id = 12, type = "android.os.IBinder")
    private final zzaa zzjd;
    
    static {
        CREATOR = (Parcelable$Creator)new zzcd();
    }
    
    @SafeParcelable$Constructor
    @VisibleForTesting
    public SubscribeRequest(@SafeParcelable$Param(id = 1) final int versionCode, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder, @SafeParcelable$Param(id = 3) final Strategy zzit, @SafeParcelable$Param(id = 4) final IBinder binder2, @SafeParcelable$Param(id = 5) final MessageFilter zziz, @Nullable @SafeParcelable$Param(id = 6) final PendingIntent zzja, @SafeParcelable$Param(id = 7) final int zzjb, @Nullable @SafeParcelable$Param(id = 8) final String zzff, @Nullable @SafeParcelable$Param(id = 9) final String zzfj, @Nullable @SafeParcelable$Param(id = 10) final byte[] zzjc, @SafeParcelable$Param(id = 11) final boolean zziu, @Nullable @SafeParcelable$Param(id = 12) final IBinder binder3, @SafeParcelable$Param(id = 13) final boolean zzfg, @Nullable @SafeParcelable$Param(id = 14) final ClientAppContext clientAppContext, @SafeParcelable$Param(id = 15) final boolean zzgb, @SafeParcelable$Param(id = 16) final int zzgc, @SafeParcelable$Param(id = 17) final int zzhf) {
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
        this.zzit = zzit;
        zzp zzhh;
        if (binder2 == null) {
            zzhh = null;
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
        this.zziz = zziz;
        this.zzja = zzja;
        this.zzjb = zzjb;
        this.zzff = zzff;
        this.zzfj = zzfj;
        this.zzjc = zzjc;
        this.zziu = zziu;
        zzaa zzjd;
        if (binder3 == null) {
            zzjd = null;
        }
        else if (binder3 == null) {
            zzjd = null;
        }
        else {
            final IInterface queryLocalInterface3 = binder3.queryLocalInterface("com.google.android.gms.nearby.messages.internal.ISubscribeCallback");
            if (queryLocalInterface3 instanceof zzaa) {
                zzjd = (zzaa)queryLocalInterface3;
            }
            else {
                zzjd = new zzac(binder3);
            }
        }
        this.zzjd = zzjd;
        this.zzfg = zzfg;
        this.zzhi = ClientAppContext.zza(clientAppContext, zzfj, zzff, zzfg);
        this.zzgb = zzgb;
        this.zzgc = zzgc;
        this.zzhf = zzhf;
    }
    
    public SubscribeRequest(final IBinder binder, final Strategy strategy, final IBinder binder2, final MessageFilter messageFilter, final PendingIntent pendingIntent, @Nullable final byte[] array, @Nullable final IBinder binder3, final boolean b, final int n) {
        this(binder, strategy, binder2, messageFilter, null, array, binder3, b, 0, n);
    }
    
    public SubscribeRequest(final IBinder binder, final Strategy strategy, final IBinder binder2, final MessageFilter messageFilter, final PendingIntent pendingIntent, @Nullable final byte[] array, @Nullable final IBinder binder3, final boolean b, final int n, final int n2) {
        this(3, binder, strategy, binder2, messageFilter, pendingIntent, 0, null, null, array, false, binder3, false, null, b, n, n2);
    }
    
    public final String toString() {
        final String value = String.valueOf(this.zziy);
        final String value2 = String.valueOf(this.zzit);
        final String value3 = String.valueOf(this.zzhh);
        final String value4 = String.valueOf(this.zziz);
        final String value5 = String.valueOf(this.zzja);
        String string;
        if (this.zzjc == null) {
            string = null;
        }
        else {
            string = new StringBuilder(19).append("<").append(this.zzjc.length).append(" bytes>").toString();
        }
        final String value6 = String.valueOf(this.zzjd);
        final boolean zzfg = this.zzfg;
        final String value7 = String.valueOf(this.zzhi);
        final boolean zzgb = this.zzgb;
        final String zzff = this.zzff;
        final String zzfj = this.zzfj;
        return new StringBuilder(String.valueOf(value).length() + 291 + String.valueOf(value2).length() + String.valueOf(value3).length() + String.valueOf(value4).length() + String.valueOf(value5).length() + String.valueOf(string).length() + String.valueOf(value6).length() + String.valueOf(value7).length() + String.valueOf(zzff).length() + String.valueOf(zzfj).length()).append("SubscribeRequest{messageListener=").append(value).append(", strategy=").append(value2).append(", callback=").append(value3).append(", filter=").append(value4).append(", pendingIntent=").append(value5).append(", hint=").append(string).append(", subscribeCallback=").append(value6).append(", useRealClientApiKey=").append(zzfg).append(", clientAppContext=").append(value7).append(", isDiscardPendingIntent=").append(zzgb).append(", zeroPartyPackageName=").append(zzff).append(", realClientPackageName=").append(zzfj).append(", isIgnoreNearbyPermission=").append(this.zziu).append(", callingContext=").append(this.zzhf).append("}").toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final IBinder binder = null;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        IBinder binder2;
        if (this.zziy == null) {
            binder2 = null;
        }
        else {
            binder2 = this.zziy.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder2, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzit, n, false);
        IBinder binder3;
        if (this.zzhh == null) {
            binder3 = null;
        }
        else {
            binder3 = this.zzhh.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 4, binder3, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zziz, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzja, n, false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzjb);
        SafeParcelWriter.writeString(parcel, 8, this.zzff, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzfj, false);
        SafeParcelWriter.writeByteArray(parcel, 10, this.zzjc, false);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zziu);
        IBinder binder4;
        if (this.zzjd == null) {
            binder4 = binder;
        }
        else {
            binder4 = this.zzjd.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 12, binder4, false);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzfg);
        SafeParcelWriter.writeParcelable(parcel, 14, (Parcelable)this.zzhi, n, false);
        SafeParcelWriter.writeBoolean(parcel, 15, this.zzgb);
        SafeParcelWriter.writeInt(parcel, 16, this.zzgc);
        SafeParcelWriter.writeInt(parcel, 17, this.zzhf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
