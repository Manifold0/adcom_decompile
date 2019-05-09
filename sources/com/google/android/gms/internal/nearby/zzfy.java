package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.nearby.connection.AdvertisingOptions;

@Class(creator = "StartAdvertisingParamsCreator")
@Reserved({1000})
public final class zzfy extends AbstractSafeParcelable {
    public static final Creator<zzfy> CREATOR = new zzgb();
    @Field(getter = "getDurationMillis", id = 5)
    private long durationMillis;
    @Field(getter = "getName", id = 3)
    private String name;
    @Nullable
    @Field(getter = "getConnectionLifecycleListenerAsBinder", id = 7, type = "android.os.IBinder")
    private zzdj zzec;
    @Nullable
    @Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzec zzeh;
    @Nullable
    @Field(getter = "getCallbackAsBinder", id = 2, type = "android.os.IBinder")
    private zzdd zzei;
    @Field(getter = "getOptions", id = 6)
    private AdvertisingOptions zzej;
    @Field(getter = "getServiceId", id = 4)
    private String zzu;

    private zzfy() {
    }

    @Constructor
    zzfy(@Nullable @Param(id = 1) IBinder iBinder, @Nullable @Param(id = 2) IBinder iBinder2, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) long j, @Param(id = 6) AdvertisingOptions advertisingOptions, @Nullable @Param(id = 7) IBinder iBinder3) {
        zzec zzec;
        zzdd zzdd;
        zzdj zzdj;
        if (iBinder == null) {
            zzec = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
            zzec = queryLocalInterface instanceof zzec ? (zzec) queryLocalInterface : new zzee(iBinder);
        }
        if (iBinder2 == null) {
            zzdd = null;
        } else {
            queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
            zzdd = queryLocalInterface instanceof zzdd ? (zzdd) queryLocalInterface : new zzdf(iBinder2);
        }
        if (iBinder3 == null) {
            zzdj = null;
        } else {
            queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
            zzdj = queryLocalInterface instanceof zzdj ? (zzdj) queryLocalInterface : new zzdl(iBinder3);
        }
        this(zzec, zzdd, str, str2, j, advertisingOptions, zzdj);
    }

    private zzfy(@Nullable zzec zzec, @Nullable zzdd zzdd, String str, String str2, long j, AdvertisingOptions advertisingOptions, @Nullable zzdj zzdj) {
        this.zzeh = zzec;
        this.zzei = zzdd;
        this.name = str;
        this.zzu = str2;
        this.durationMillis = j;
        this.zzej = advertisingOptions;
        this.zzec = zzdj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfy)) {
            return false;
        }
        zzfy zzfy = (zzfy) obj;
        return Objects.equal(this.zzeh, zzfy.zzeh) && Objects.equal(this.zzei, zzfy.zzei) && Objects.equal(this.name, zzfy.name) && Objects.equal(this.zzu, zzfy.zzu) && Objects.equal(Long.valueOf(this.durationMillis), Long.valueOf(zzfy.durationMillis)) && Objects.equal(this.zzej, zzfy.zzej) && Objects.equal(this.zzec, zzfy.zzec);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzeh, this.zzei, this.name, this.zzu, Long.valueOf(this.durationMillis), this.zzej, this.zzec});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder = null;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzeh == null ? null : this.zzeh.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzei == null ? null : this.zzei.asBinder(), false);
        SafeParcelWriter.writeString(parcel, 3, this.name, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzu, false);
        SafeParcelWriter.writeLong(parcel, 5, this.durationMillis);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzej, i, false);
        if (this.zzec != null) {
            iBinder = this.zzec.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 7, iBinder, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
