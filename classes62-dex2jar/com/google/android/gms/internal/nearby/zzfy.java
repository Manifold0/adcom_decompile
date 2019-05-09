// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.IBinder;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "StartAdvertisingParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfy extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfy> CREATOR;
    @SafeParcelable$Field(getter = "getDurationMillis", id = 5)
    private long durationMillis;
    @SafeParcelable$Field(getter = "getName", id = 3)
    private String name;
    @Nullable
    @SafeParcelable$Field(getter = "getConnectionLifecycleListenerAsBinder", id = 7, type = "android.os.IBinder")
    private zzdj zzec;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzec zzeh;
    @Nullable
    @SafeParcelable$Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private zzdd zzei;
    @SafeParcelable$Field(getter = "getOptions", id = 6)
    private AdvertisingOptions zzej;
    @SafeParcelable$Field(getter = "getServiceId", id = 4)
    private String zzu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgb();
    }
    
    private zzfy() {
    }
    
    @SafeParcelable$Constructor
    zzfy(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @Nullable @SafeParcelable$Param(id = 2) final IBinder binder2, @SafeParcelable$Param(id = 3) final String s, @SafeParcelable$Param(id = 4) final String s2, @SafeParcelable$Param(id = 5) final long n, @SafeParcelable$Param(id = 6) final AdvertisingOptions advertisingOptions, @Nullable @SafeParcelable$Param(id = 7) final IBinder binder3) {
        zzec zzec;
        if (binder == null) {
            zzec = null;
        }
        else {
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
            if (queryLocalInterface instanceof zzec) {
                zzec = (zzec)queryLocalInterface;
            }
            else {
                zzec = new zzee(binder);
            }
        }
        zzdd zzdd;
        if (binder2 == null) {
            zzdd = null;
        }
        else {
            final IInterface queryLocalInterface2 = binder2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
            if (queryLocalInterface2 instanceof zzdd) {
                zzdd = (zzdd)queryLocalInterface2;
            }
            else {
                zzdd = new zzdf(binder2);
            }
        }
        zzdj zzdj;
        if (binder3 == null) {
            zzdj = null;
        }
        else {
            final IInterface queryLocalInterface3 = binder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
            if (queryLocalInterface3 instanceof zzdj) {
                zzdj = (zzdj)queryLocalInterface3;
            }
            else {
                zzdj = new zzdl(binder3);
            }
        }
        this(zzec, zzdd, s, s2, n, advertisingOptions, zzdj);
    }
    
    private zzfy(@Nullable final zzec zzeh, @Nullable final zzdd zzei, final String name, final String zzu, final long durationMillis, final AdvertisingOptions zzej, @Nullable final zzdj zzec) {
        this.zzeh = zzeh;
        this.zzei = zzei;
        this.name = name;
        this.zzu = zzu;
        this.durationMillis = durationMillis;
        this.zzej = zzej;
        this.zzec = zzec;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzfy)) {
                return false;
            }
            final zzfy zzfy = (zzfy)o;
            if (!Objects.equal((Object)this.zzeh, (Object)zzfy.zzeh) || !Objects.equal((Object)this.zzei, (Object)zzfy.zzei) || !Objects.equal((Object)this.name, (Object)zzfy.name) || !Objects.equal((Object)this.zzu, (Object)zzfy.zzu) || !Objects.equal((Object)this.durationMillis, (Object)zzfy.durationMillis) || !Objects.equal((Object)this.zzej, (Object)zzfy.zzej) || !Objects.equal((Object)this.zzec, (Object)zzfy.zzec)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzeh, this.zzei, this.name, this.zzu, this.durationMillis, this.zzej, this.zzec });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final IBinder binder = null;
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        IBinder binder2;
        if (this.zzeh == null) {
            binder2 = null;
        }
        else {
            binder2 = this.zzeh.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 1, binder2, false);
        IBinder binder3;
        if (this.zzei == null) {
            binder3 = null;
        }
        else {
            binder3 = this.zzei.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 2, binder3, false);
        SafeParcelWriter.writeString(parcel, 3, this.name, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzu, false);
        SafeParcelWriter.writeLong(parcel, 5, this.durationMillis);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzej, n, false);
        IBinder binder4;
        if (this.zzec == null) {
            binder4 = binder;
        }
        else {
            binder4 = this.zzec.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 7, binder4, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
