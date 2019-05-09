package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "PlayerLevelInfoCreator")
@Reserved({1000})
public final class PlayerLevelInfo extends zzd {
    public static final Creator<PlayerLevelInfo> CREATOR = new zzar();
    @Field(getter = "getCurrentXpTotal", id = 1)
    private final long zzcs;
    @Field(getter = "getLastLevelUpTimestamp", id = 2)
    private final long zzct;
    @Field(getter = "getCurrentLevel", id = 3)
    private final PlayerLevel zzcu;
    @Field(getter = "getNextLevel", id = 4)
    private final PlayerLevel zzcv;

    @Constructor
    public PlayerLevelInfo(@Param(id = 1) long j, @Param(id = 2) long j2, @Param(id = 3) PlayerLevel playerLevel, @Param(id = 4) PlayerLevel playerLevel2) {
        Preconditions.checkState(j != -1);
        Preconditions.checkNotNull(playerLevel);
        Preconditions.checkNotNull(playerLevel2);
        this.zzcs = j;
        this.zzct = j2;
        this.zzcu = playerLevel;
        this.zzcv = playerLevel2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevelInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo) obj;
        return Objects.equal(Long.valueOf(this.zzcs), Long.valueOf(playerLevelInfo.zzcs)) && Objects.equal(Long.valueOf(this.zzct), Long.valueOf(playerLevelInfo.zzct)) && Objects.equal(this.zzcu, playerLevelInfo.zzcu) && Objects.equal(this.zzcv, playerLevelInfo.zzcv);
    }

    public final PlayerLevel getCurrentLevel() {
        return this.zzcu;
    }

    public final long getCurrentXpTotal() {
        return this.zzcs;
    }

    public final long getLastLevelUpTimestamp() {
        return this.zzct;
    }

    public final PlayerLevel getNextLevel() {
        return this.zzcv;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zzcs), Long.valueOf(this.zzct), this.zzcu, this.zzcv});
    }

    public final boolean isMaxLevel() {
        return this.zzcu.equals(this.zzcv);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getCurrentXpTotal());
        SafeParcelWriter.writeLong(parcel, 2, getLastLevelUpTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 3, getCurrentLevel(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getNextLevel(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
