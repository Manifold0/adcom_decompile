// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "PlayerLevelCreator")
@SafeParcelable$Reserved({ 1000 })
public final class PlayerLevel extends zzd
{
    public static final Parcelable$Creator<PlayerLevel> CREATOR;
    @SafeParcelable$Field(getter = "getLevelNumber", id = 1)
    private final int zzcp;
    @SafeParcelable$Field(getter = "getMinXp", id = 2)
    private final long zzcq;
    @SafeParcelable$Field(getter = "getMaxXp", id = 3)
    private final long zzcr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaq();
    }
    
    @SafeParcelable$Constructor
    public PlayerLevel(@SafeParcelable$Param(id = 1) final int zzcp, @SafeParcelable$Param(id = 2) final long zzcq, @SafeParcelable$Param(id = 3) final long zzcr) {
        final boolean b = true;
        Preconditions.checkState(zzcq >= 0L, (Object)"Min XP must be positive!");
        Preconditions.checkState(zzcr > zzcq && b, (Object)"Max XP must be more than min XP!");
        this.zzcp = zzcp;
        this.zzcq = zzcq;
        this.zzcr = zzcr;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof PlayerLevel) {
            if (this == o) {
                return true;
            }
            final PlayerLevel playerLevel = (PlayerLevel)o;
            if (Objects.equal((Object)playerLevel.getLevelNumber(), (Object)this.getLevelNumber()) && Objects.equal((Object)playerLevel.getMinXp(), (Object)this.getMinXp()) && Objects.equal((Object)playerLevel.getMaxXp(), (Object)this.getMaxXp())) {
                return true;
            }
        }
        return false;
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
        return Objects.hashCode(new Object[] { this.zzcp, this.zzcq, this.zzcr });
    }
    
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("LevelNumber", (Object)this.getLevelNumber()).add("MinXp", (Object)this.getMinXp()).add("MaxXp", (Object)this.getMaxXp()).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.getLevelNumber());
        SafeParcelWriter.writeLong(parcel, 2, this.getMinXp());
        SafeParcelWriter.writeLong(parcel, 3, this.getMaxXp());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
