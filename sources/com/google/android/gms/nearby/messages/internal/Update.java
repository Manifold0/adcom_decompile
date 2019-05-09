package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.internal.nearby.zzgr;
import com.google.android.gms.internal.nearby.zzgs;
import com.google.android.gms.nearby.messages.Message;
import java.util.Arrays;
import java.util.Set;

@Class(creator = "UpdateCreator")
public class Update extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<Update> CREATOR = new zzci();
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 3)
    public final Message zzhk;
    @Field(id = 2)
    private final int zzje;
    @Nullable
    @Field(id = 4)
    public final zze zzjf;
    @Nullable
    @Field(id = 5)
    public final zza zzjg;
    @Nullable
    @Field(id = 6)
    public final zzgs zzjh;
    @Nullable
    @Field(id = 7)
    private final byte[] zzji;

    @Constructor
    Update(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) Message message, @Nullable @Param(id = 4) zze zze, @Nullable @Param(id = 5) zza zza, @Nullable @Param(id = 6) zzgs zzgs, @Nullable @Param(id = 7) byte[] bArr) {
        zzgs zzgs2 = null;
        this.versionCode = i;
        if (zza(i2, 2)) {
            bArr = null;
            zza = null;
            zze = null;
            i2 = 2;
        } else {
            zzgs2 = zzgs;
        }
        this.zzje = i2;
        this.zzhk = message;
        this.zzjf = zze;
        this.zzjg = zza;
        this.zzjh = zzgs2;
        this.zzji = bArr;
    }

    private static boolean zza(int i, int i2) {
        return (i & i2) != 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Update)) {
            return false;
        }
        Update update = (Update) obj;
        return this.zzje == update.zzje && Objects.equal(this.zzhk, update.zzhk) && Objects.equal(this.zzjf, update.zzjf) && Objects.equal(this.zzjg, update.zzjg) && Objects.equal(this.zzjh, update.zzjh) && Arrays.equals(this.zzji, update.zzji);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzje), this.zzhk, this.zzjf, this.zzjg, this.zzjh, this.zzji});
    }

    public String toString() {
        Set arraySet = new ArraySet();
        if (zzg(1)) {
            arraySet.add("FOUND");
        }
        if (zzg(2)) {
            arraySet.add("LOST");
        }
        if (zzg(4)) {
            arraySet.add("DISTANCE");
        }
        if (zzg(8)) {
            arraySet.add("BLE_SIGNAL");
        }
        if (zzg(16)) {
            arraySet.add("DEVICE");
        }
        if (zzg(32)) {
            arraySet.add("BLE_RECORD");
        }
        String valueOf = String.valueOf(arraySet);
        String valueOf2 = String.valueOf(this.zzhk);
        String valueOf3 = String.valueOf(this.zzjf);
        String valueOf4 = String.valueOf(this.zzjg);
        String valueOf5 = String.valueOf(this.zzjh);
        String valueOf6 = String.valueOf(zzgr.zzd(this.zzji));
        return new StringBuilder((((((String.valueOf(valueOf).length() + 68) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()).append("Update{types=").append(valueOf).append(", message=").append(valueOf2).append(", distance=").append(valueOf3).append(", bleSignal=").append(valueOf4).append(", device=").append(valueOf5).append(", bleRecord=").append(valueOf6).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.zzje);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzhk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzjf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzjg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzjh, i, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzji, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean zzg(int i) {
        return zza(this.zzje, i);
    }
}
