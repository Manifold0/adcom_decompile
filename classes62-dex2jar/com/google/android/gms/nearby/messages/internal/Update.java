// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzgr;
import android.support.v4.util.ArraySet;
import java.util.Arrays;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.internal.nearby.zzgs;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "UpdateCreator")
public class Update extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<Update> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 3)
    public final Message zzhk;
    @SafeParcelable$Field(id = 2)
    private final int zzje;
    @Nullable
    @SafeParcelable$Field(id = 4)
    public final zze zzjf;
    @Nullable
    @SafeParcelable$Field(id = 5)
    public final zza zzjg;
    @Nullable
    @SafeParcelable$Field(id = 6)
    public final zzgs zzjh;
    @Nullable
    @SafeParcelable$Field(id = 7)
    private final byte[] zzji;
    
    static {
        CREATOR = (Parcelable$Creator)new zzci();
    }
    
    @SafeParcelable$Constructor
    Update(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) int zzje, @SafeParcelable$Param(id = 3) final Message zzhk, @Nullable @SafeParcelable$Param(id = 4) zze zzjf, @Nullable @SafeParcelable$Param(id = 5) zza zzjg, @Nullable @SafeParcelable$Param(id = 6) zzgs zzjh, @Nullable @SafeParcelable$Param(id = 7) byte[] zzji) {
        final zzgs zzgs = null;
        this.versionCode = versionCode;
        if (zza(zzje, 2)) {
            zzji = null;
            zzjg = null;
            zzjf = null;
            zzje = 2;
            zzjh = zzgs;
        }
        this.zzje = zzje;
        this.zzhk = zzhk;
        this.zzjf = zzjf;
        this.zzjg = zzjg;
        this.zzjh = zzjh;
        this.zzji = zzji;
    }
    
    private static boolean zza(final int n, final int n2) {
        return (n & n2) != 0x0;
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Update)) {
                return false;
            }
            final Update update = (Update)o;
            if (this.zzje != update.zzje || !Objects.equal((Object)this.zzhk, (Object)update.zzhk) || !Objects.equal((Object)this.zzjf, (Object)update.zzjf) || !Objects.equal((Object)this.zzjg, (Object)update.zzjg) || !Objects.equal((Object)this.zzjh, (Object)update.zzjh) || !Arrays.equals(this.zzji, update.zzji)) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzje, this.zzhk, this.zzjf, this.zzjg, this.zzjh, this.zzji });
    }
    
    public String toString() {
        final ArraySet set = new ArraySet();
        if (this.zzg(1)) {
            ((Set<String>)set).add("FOUND");
        }
        if (this.zzg(2)) {
            ((Set<String>)set).add("LOST");
        }
        if (this.zzg(4)) {
            ((Set<String>)set).add("DISTANCE");
        }
        if (this.zzg(8)) {
            ((Set<String>)set).add("BLE_SIGNAL");
        }
        if (this.zzg(16)) {
            ((Set<String>)set).add("DEVICE");
        }
        if (this.zzg(32)) {
            ((Set<String>)set).add("BLE_RECORD");
        }
        final String value = String.valueOf(set);
        final String value2 = String.valueOf(this.zzhk);
        final String value3 = String.valueOf(this.zzjf);
        final String value4 = String.valueOf(this.zzjg);
        final String value5 = String.valueOf(this.zzjh);
        final String value6 = String.valueOf(zzgr.zzd(this.zzji));
        return new StringBuilder(String.valueOf(value).length() + 68 + String.valueOf(value2).length() + String.valueOf(value3).length() + String.valueOf(value4).length() + String.valueOf(value5).length() + String.valueOf(value6).length()).append("Update{types=").append(value).append(", message=").append(value2).append(", distance=").append(value3).append(", bleSignal=").append(value4).append(", device=").append(value5).append(", bleRecord=").append(value6).append("}").toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.zzje);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzhk, n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzjf, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzjg, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzjh, n, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzji, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final boolean zzg(final int n) {
        return zza(this.zzje, n);
    }
}
