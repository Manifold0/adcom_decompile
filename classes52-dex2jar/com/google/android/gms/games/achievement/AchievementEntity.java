// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.achievement;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "AchievementEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class AchievementEntity extends zzd implements Achievement
{
    public static final Parcelable$Creator<AchievementEntity> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 4)
    private final String description;
    @SafeParcelable$Field(getter = "getName", id = 3)
    private final String name;
    @SafeParcelable$Field(getter = "getState", id = 12)
    private final int state;
    @SafeParcelable$Field(getter = "getType", id = 2)
    private final int type;
    @SafeParcelable$Field(getter = "getAchievementId", id = 1)
    private final String zzfa;
    @SafeParcelable$Field(getter = "getUnlockedImageUri", id = 5)
    private final Uri zzfb;
    @SafeParcelable$Field(getter = "getUnlockedImageUrl", id = 6)
    private final String zzfc;
    @SafeParcelable$Field(getter = "getRevealedImageUri", id = 7)
    private final Uri zzfd;
    @SafeParcelable$Field(getter = "getRevealedImageUrl", id = 8)
    private final String zzfe;
    @SafeParcelable$Field(getter = "getTotalStepsRaw", id = 9)
    private final int zzff;
    @SafeParcelable$Field(getter = "getFormattedTotalStepsRaw", id = 10)
    private final String zzfg;
    @SafeParcelable$Field(getter = "getPlayer", id = 11)
    private final PlayerEntity zzfh;
    @SafeParcelable$Field(getter = "getCurrentStepsRaw", id = 13)
    private final int zzfi;
    @SafeParcelable$Field(getter = "getFormattedCurrentStepsRaw", id = 14)
    private final String zzfj;
    @SafeParcelable$Field(getter = "getLastUpdatedTimestamp", id = 15)
    private final long zzfk;
    @SafeParcelable$Field(getter = "getXpValue", id = 16)
    private final long zzfl;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public AchievementEntity(final Achievement achievement) {
        this.zzfa = achievement.getAchievementId();
        this.type = achievement.getType();
        this.name = achievement.getName();
        this.description = achievement.getDescription();
        this.zzfb = achievement.getUnlockedImageUri();
        this.zzfc = achievement.getUnlockedImageUrl();
        this.zzfd = achievement.getRevealedImageUri();
        this.zzfe = achievement.getRevealedImageUrl();
        this.zzfh = (PlayerEntity)achievement.getPlayer().freeze();
        this.state = achievement.getState();
        this.zzfk = achievement.getLastUpdatedTimestamp();
        this.zzfl = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.zzff = achievement.getTotalSteps();
            this.zzfg = achievement.getFormattedTotalSteps();
            this.zzfi = achievement.getCurrentSteps();
            this.zzfj = achievement.getFormattedCurrentSteps();
        }
        else {
            this.zzff = 0;
            this.zzfg = null;
            this.zzfi = 0;
            this.zzfj = null;
        }
        Asserts.checkNotNull((Object)this.zzfa);
        Asserts.checkNotNull((Object)this.description);
    }
    
    @SafeParcelable$Constructor
    AchievementEntity(@SafeParcelable$Param(id = 1) final String zzfa, @SafeParcelable$Param(id = 2) final int type, @SafeParcelable$Param(id = 3) final String name, @SafeParcelable$Param(id = 4) final String description, @SafeParcelable$Param(id = 5) final Uri zzfb, @SafeParcelable$Param(id = 6) final String zzfc, @SafeParcelable$Param(id = 7) final Uri zzfd, @SafeParcelable$Param(id = 8) final String zzfe, @SafeParcelable$Param(id = 9) final int zzff, @SafeParcelable$Param(id = 10) final String zzfg, @SafeParcelable$Param(id = 11) final PlayerEntity zzfh, @SafeParcelable$Param(id = 12) final int state, @SafeParcelable$Param(id = 13) final int zzfi, @SafeParcelable$Param(id = 14) final String zzfj, @SafeParcelable$Param(id = 15) final long zzfk, @SafeParcelable$Param(id = 16) final long zzfl) {
        this.zzfa = zzfa;
        this.type = type;
        this.name = name;
        this.description = description;
        this.zzfb = zzfb;
        this.zzfc = zzfc;
        this.zzfd = zzfd;
        this.zzfe = zzfe;
        this.zzff = zzff;
        this.zzfg = zzfg;
        this.zzfh = zzfh;
        this.state = state;
        this.zzfi = zzfi;
        this.zzfj = zzfj;
        this.zzfk = zzfk;
        this.zzfl = zzfl;
    }
    
    static String zza(final Achievement achievement) {
        final Objects$ToStringHelper add = Objects.toStringHelper((Object)achievement).add("Id", (Object)achievement.getAchievementId()).add("Type", (Object)achievement.getType()).add("Name", (Object)achievement.getName()).add("Description", (Object)achievement.getDescription()).add("Player", (Object)achievement.getPlayer()).add("State", (Object)achievement.getState());
        if (achievement.getType() == 1) {
            add.add("CurrentSteps", (Object)achievement.getCurrentSteps());
            add.add("TotalSteps", (Object)achievement.getTotalSteps());
        }
        return add.toString();
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof Achievement) {
            if (this != o) {
                final Achievement achievement = (Achievement)o;
                if (achievement.getType() != this.getType() || (this.getType() == 1 && (achievement.getCurrentSteps() != this.getCurrentSteps() || achievement.getTotalSteps() != this.getTotalSteps())) || achievement.getXpValue() != this.getXpValue() || achievement.getState() != this.getState() || achievement.getLastUpdatedTimestamp() != this.getLastUpdatedTimestamp() || !Objects.equal((Object)achievement.getAchievementId(), (Object)this.getAchievementId()) || !Objects.equal((Object)achievement.getName(), (Object)this.getName()) || !Objects.equal((Object)achievement.getDescription(), (Object)this.getDescription()) || !Objects.equal((Object)achievement.getPlayer(), (Object)this.getPlayer())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public final Achievement freeze() {
        return this;
    }
    
    @Override
    public final String getAchievementId() {
        return this.zzfa;
    }
    
    @Override
    public final int getCurrentSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.zzfi;
    }
    
    @Override
    public final String getDescription() {
        return this.description;
    }
    
    @Override
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }
    
    @Override
    public final String getFormattedCurrentSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.zzfj;
    }
    
    @Override
    public final void getFormattedCurrentSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        DataUtils.copyStringToBuffer(this.zzfj, charArrayBuffer);
    }
    
    @Override
    public final String getFormattedTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.zzfg;
    }
    
    @Override
    public final void getFormattedTotalSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        DataUtils.copyStringToBuffer(this.zzfg, charArrayBuffer);
    }
    
    @Override
    public final long getLastUpdatedTimestamp() {
        return this.zzfk;
    }
    
    @Override
    public final String getName() {
        return this.name;
    }
    
    @Override
    public final void getName(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.name, charArrayBuffer);
    }
    
    @Override
    public final Player getPlayer() {
        return this.zzfh;
    }
    
    @Override
    public final Uri getRevealedImageUri() {
        return this.zzfd;
    }
    
    @Override
    public final String getRevealedImageUrl() {
        return this.zzfe;
    }
    
    @Override
    public final int getState() {
        return this.state;
    }
    
    @Override
    public final int getTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.zzff;
    }
    
    @Override
    public final int getType() {
        return this.type;
    }
    
    @Override
    public final Uri getUnlockedImageUri() {
        return this.zzfb;
    }
    
    @Override
    public final String getUnlockedImageUrl() {
        return this.zzfc;
    }
    
    @Override
    public final long getXpValue() {
        return this.zzfl;
    }
    
    public final int hashCode() {
        int currentSteps;
        int totalSteps;
        if (this.getType() == 1) {
            currentSteps = this.getCurrentSteps();
            totalSteps = this.getTotalSteps();
        }
        else {
            totalSteps = 0;
            currentSteps = 0;
        }
        return Objects.hashCode(new Object[] { this.getAchievementId(), this.getName(), this.getType(), this.getDescription(), this.getXpValue(), this.getState(), this.getLastUpdatedTimestamp(), this.getPlayer(), currentSteps, totalSteps });
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return zza(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getAchievementId(), false);
        SafeParcelWriter.writeInt(parcel, 2, this.getType());
        SafeParcelWriter.writeString(parcel, 3, this.getName(), false);
        SafeParcelWriter.writeString(parcel, 4, this.getDescription(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.getUnlockedImageUri(), n, false);
        SafeParcelWriter.writeString(parcel, 6, this.getUnlockedImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.getRevealedImageUri(), n, false);
        SafeParcelWriter.writeString(parcel, 8, this.getRevealedImageUrl(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzff);
        SafeParcelWriter.writeString(parcel, 10, this.zzfg, false);
        SafeParcelWriter.writeParcelable(parcel, 11, (Parcelable)this.getPlayer(), n, false);
        SafeParcelWriter.writeInt(parcel, 12, this.getState());
        SafeParcelWriter.writeInt(parcel, 13, this.zzfi);
        SafeParcelWriter.writeString(parcel, 14, this.zzfj, false);
        SafeParcelWriter.writeLong(parcel, 15, this.getLastUpdatedTimestamp());
        SafeParcelWriter.writeLong(parcel, 16, this.getXpValue());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
