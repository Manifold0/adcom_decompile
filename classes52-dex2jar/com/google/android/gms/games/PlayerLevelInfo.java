// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.Parcelable;
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

@SafeParcelable$Class(creator = "PlayerLevelInfoCreator")
@SafeParcelable$Reserved({ 1000 })
public final class PlayerLevelInfo extends zzd
{
    public static final Parcelable$Creator<PlayerLevelInfo> CREATOR;
    @SafeParcelable$Field(getter = "getCurrentXpTotal", id = 1)
    private final long zzcs;
    @SafeParcelable$Field(getter = "getLastLevelUpTimestamp", id = 2)
    private final long zzct;
    @SafeParcelable$Field(getter = "getCurrentLevel", id = 3)
    private final PlayerLevel zzcu;
    @SafeParcelable$Field(getter = "getNextLevel", id = 4)
    private final PlayerLevel zzcv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzar();
    }
    
    @SafeParcelable$Constructor
    public PlayerLevelInfo(@SafeParcelable$Param(id = 1) final long zzcs, @SafeParcelable$Param(id = 2) final long zzct, @SafeParcelable$Param(id = 3) final PlayerLevel zzcu, @SafeParcelable$Param(id = 4) final PlayerLevel zzcv) {
        Preconditions.checkState(zzcs != -1L);
        Preconditions.checkNotNull((Object)zzcu);
        Preconditions.checkNotNull((Object)zzcv);
        this.zzcs = zzcs;
        this.zzct = zzct;
        this.zzcu = zzcu;
        this.zzcv = zzcv;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof PlayerLevelInfo) {
            if (o == this) {
                return true;
            }
            final PlayerLevelInfo playerLevelInfo = (PlayerLevelInfo)o;
            if (Objects.equal((Object)this.zzcs, (Object)playerLevelInfo.zzcs) && Objects.equal((Object)this.zzct, (Object)playerLevelInfo.zzct) && Objects.equal((Object)this.zzcu, (Object)playerLevelInfo.zzcu) && Objects.equal((Object)this.zzcv, (Object)playerLevelInfo.zzcv)) {
                return true;
            }
        }
        return false;
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
        return Objects.hashCode(new Object[] { this.zzcs, this.zzct, this.zzcu, this.zzcv });
    }
    
    public final boolean isMaxLevel() {
        return this.zzcu.equals(this.zzcv);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.getCurrentXpTotal());
        SafeParcelWriter.writeLong(parcel, 2, this.getLastLevelUpTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getCurrentLevel(), n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getNextLevel(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
