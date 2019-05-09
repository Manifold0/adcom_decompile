// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.stats;

import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent
{
    public static final Parcelable$Creator<WakeLockEvent> CREATOR;
    private long durationMillis;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getTimeMillis", id = 2)
    private final long zzfo;
    @Field(getter = "getEventType", id = 11)
    private int zzfp;
    @Field(getter = "getWakeLockName", id = 4)
    private final String zzfq;
    @Field(getter = "getSecondaryWakeLockName", id = 10)
    private final String zzfr;
    @Field(getter = "getCodePackage", id = 17)
    private final String zzfs;
    @Field(getter = "getWakeLockType", id = 5)
    private final int zzft;
    @Field(getter = "getCallingPackages", id = 6)
    private final List<String> zzfu;
    @Field(getter = "getEventKey", id = 12)
    private final String zzfv;
    @Field(getter = "getElapsedRealtime", id = 8)
    private final long zzfw;
    @Field(getter = "getDeviceState", id = 14)
    private int zzfx;
    @Field(getter = "getHostPackage", id = 13)
    private final String zzfy;
    @Field(getter = "getBeginPowerPercentage", id = 15)
    private final float zzfz;
    @Field(getter = "getTimeout", id = 16)
    private final long zzga;
    @Field(getter = "getAcquiredWithTimeout", id = 18)
    private final boolean zzgb;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @Constructor
    WakeLockEvent(@Param(id = 1) final int versionCode, @Param(id = 2) final long zzfo, @Param(id = 11) final int zzfp, @Param(id = 4) final String zzfq, @Param(id = 5) final int zzft, @Param(id = 6) final List<String> zzfu, @Param(id = 12) final String zzfv, @Param(id = 8) final long zzfw, @Param(id = 14) final int zzfx, @Param(id = 10) final String zzfr, @Param(id = 13) final String zzfy, @Param(id = 15) final float zzfz, @Param(id = 16) final long zzga, @Param(id = 17) final String zzfs, @Param(id = 18) final boolean zzgb) {
        this.versionCode = versionCode;
        this.zzfo = zzfo;
        this.zzfp = zzfp;
        this.zzfq = zzfq;
        this.zzfr = zzfr;
        this.zzfs = zzfs;
        this.zzft = zzft;
        this.durationMillis = -1L;
        this.zzfu = zzfu;
        this.zzfv = zzfv;
        this.zzfw = zzfw;
        this.zzfx = zzfx;
        this.zzfy = zzfy;
        this.zzfz = zzfz;
        this.zzga = zzga;
        this.zzgb = zzgb;
    }
    
    public WakeLockEvent(final long n, final int n2, final String s, final int n3, final List<String> list, final String s2, final long n4, final int n5, final String s3, final String s4, final float n6, final long n7, final String s5, final boolean b) {
        this(2, n, n2, s, n3, list, s2, n4, n5, s3, s4, n6, n7, s5, b);
    }
    
    @Override
    public final int getEventType() {
        return this.zzfp;
    }
    
    @Override
    public final long getTimeMillis() {
        return this.zzfo;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.getTimeMillis());
        SafeParcelWriter.writeString(parcel, 4, this.zzfq, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzft);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzfu, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzfw);
        SafeParcelWriter.writeString(parcel, 10, this.zzfr, false);
        SafeParcelWriter.writeInt(parcel, 11, this.getEventType());
        SafeParcelWriter.writeString(parcel, 12, this.zzfv, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzfy, false);
        SafeParcelWriter.writeInt(parcel, 14, this.zzfx);
        SafeParcelWriter.writeFloat(parcel, 15, this.zzfz);
        SafeParcelWriter.writeLong(parcel, 16, this.zzga);
        SafeParcelWriter.writeString(parcel, 17, this.zzfs, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzgb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final long zzu() {
        return this.durationMillis;
    }
    
    @Override
    public final String zzv() {
        final String zzfq = this.zzfq;
        final int zzft = this.zzft;
        String join;
        if (this.zzfu == null) {
            join = "";
        }
        else {
            join = TextUtils.join((CharSequence)",", (Iterable)this.zzfu);
        }
        final int zzfx = this.zzfx;
        String zzfr;
        if (this.zzfr == null) {
            zzfr = "";
        }
        else {
            zzfr = this.zzfr;
        }
        String zzfy;
        if (this.zzfy == null) {
            zzfy = "";
        }
        else {
            zzfy = this.zzfy;
        }
        final float zzfz = this.zzfz;
        String zzfs;
        if (this.zzfs == null) {
            zzfs = "";
        }
        else {
            zzfs = this.zzfs;
        }
        return new StringBuilder(String.valueOf(zzfq).length() + 51 + String.valueOf(join).length() + String.valueOf(zzfr).length() + String.valueOf(zzfy).length() + String.valueOf(zzfs).length()).append("\t").append(zzfq).append("\t").append(zzft).append("\t").append(join).append("\t").append(zzfx).append("\t").append(zzfr).append("\t").append(zzfy).append("\t").append(zzfz).append("\t").append(zzfs).append("\t").append(this.zzgb).toString();
    }
}
