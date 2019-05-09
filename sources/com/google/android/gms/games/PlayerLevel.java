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

@Class(creator = "PlayerLevelCreator")
@Reserved({1000})
public final class PlayerLevel extends zzd {
    public static final Creator<PlayerLevel> CREATOR = new zzaq();
    @Field(getter = "getLevelNumber", id = 1)
    private final int zzcp;
    @Field(getter = "getMinXp", id = 2)
    private final long zzcq;
    @Field(getter = "getMaxXp", id = 3)
    private final long zzcr;

    @Constructor
    public PlayerLevel(@Param(id = 1) int i, @Param(id = 2) long j, @Param(id = 3) long j2) {
        boolean z = true;
        Preconditions.checkState(j >= 0, "Min XP must be positive!");
        if (j2 <= j) {
            z = false;
        }
        Preconditions.checkState(z, "Max XP must be more than min XP!");
        this.zzcp = i;
        this.zzcq = j;
        this.zzcr = j2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PlayerLevel)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayerLevel playerLevel = (PlayerLevel) obj;
        return Objects.equal(Integer.valueOf(playerLevel.getLevelNumber()), Integer.valueOf(getLevelNumber())) && Objects.equal(Long.valueOf(playerLevel.getMinXp()), Long.valueOf(getMinXp())) && Objects.equal(Long.valueOf(playerLevel.getMaxXp()), Long.valueOf(getMaxXp()));
    }

    public final int getLevelNumber() {
        return this.zzcp;
    }

    public final long getMaxXp() {
        return this.zzcr;
    }

    public final long getMinXp() {
        return this.zzcq;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzcp), Long.valueOf(this.zzcq), Long.valueOf(this.zzcr)});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("LevelNumber", Integer.valueOf(getLevelNumber())).add("MinXp", Long.valueOf(getMinXp())).add("MaxXp", Long.valueOf(getMaxXp())).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getLevelNumber());
        SafeParcelWriter.writeLong(parcel, 2, getMinXp());
        SafeParcelWriter.writeLong(parcel, 3, getMaxXp());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
