package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "AchievementEntityCreator")
@Reserved({1000})
public final class AchievementEntity extends zzd implements Achievement {
    public static final Creator<AchievementEntity> CREATOR = new zza();
    @Field(getter = "getDescription", id = 4)
    private final String description;
    @Field(getter = "getName", id = 3)
    private final String name;
    @Field(getter = "getState", id = 12)
    private final int state;
    @Field(getter = "getType", id = 2)
    private final int type;
    @Field(getter = "getAchievementId", id = 1)
    private final String zzfa;
    @Field(getter = "getUnlockedImageUri", id = 5)
    private final Uri zzfb;
    @Field(getter = "getUnlockedImageUrl", id = 6)
    private final String zzfc;
    @Field(getter = "getRevealedImageUri", id = 7)
    private final Uri zzfd;
    @Field(getter = "getRevealedImageUrl", id = 8)
    private final String zzfe;
    @Field(getter = "getTotalStepsRaw", id = 9)
    private final int zzff;
    @Field(getter = "getFormattedTotalStepsRaw", id = 10)
    private final String zzfg;
    @Field(getter = "getPlayer", id = 11)
    private final PlayerEntity zzfh;
    @Field(getter = "getCurrentStepsRaw", id = 13)
    private final int zzfi;
    @Field(getter = "getFormattedCurrentStepsRaw", id = 14)
    private final String zzfj;
    @Field(getter = "getLastUpdatedTimestamp", id = 15)
    private final long zzfk;
    @Field(getter = "getXpValue", id = 16)
    private final long zzfl;

    public AchievementEntity(Achievement achievement) {
        this.zzfa = achievement.getAchievementId();
        this.type = achievement.getType();
        this.name = achievement.getName();
        this.description = achievement.getDescription();
        this.zzfb = achievement.getUnlockedImageUri();
        this.zzfc = achievement.getUnlockedImageUrl();
        this.zzfd = achievement.getRevealedImageUri();
        this.zzfe = achievement.getRevealedImageUrl();
        this.zzfh = (PlayerEntity) achievement.getPlayer().freeze();
        this.state = achievement.getState();
        this.zzfk = achievement.getLastUpdatedTimestamp();
        this.zzfl = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.zzff = achievement.getTotalSteps();
            this.zzfg = achievement.getFormattedTotalSteps();
            this.zzfi = achievement.getCurrentSteps();
            this.zzfj = achievement.getFormattedCurrentSteps();
        } else {
            this.zzff = 0;
            this.zzfg = null;
            this.zzfi = 0;
            this.zzfj = null;
        }
        Asserts.checkNotNull(this.zzfa);
        Asserts.checkNotNull(this.description);
    }

    @Constructor
    AchievementEntity(@Param(id = 1) String str, @Param(id = 2) int i, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) Uri uri, @Param(id = 6) String str4, @Param(id = 7) Uri uri2, @Param(id = 8) String str5, @Param(id = 9) int i2, @Param(id = 10) String str6, @Param(id = 11) PlayerEntity playerEntity, @Param(id = 12) int i3, @Param(id = 13) int i4, @Param(id = 14) String str7, @Param(id = 15) long j, @Param(id = 16) long j2) {
        this.zzfa = str;
        this.type = i;
        this.name = str2;
        this.description = str3;
        this.zzfb = uri;
        this.zzfc = str4;
        this.zzfd = uri2;
        this.zzfe = str5;
        this.zzff = i2;
        this.zzfg = str6;
        this.zzfh = playerEntity;
        this.state = i3;
        this.zzfi = i4;
        this.zzfj = str7;
        this.zzfk = j;
        this.zzfl = j2;
    }

    static String zza(Achievement achievement) {
        ToStringHelper add = Objects.toStringHelper(achievement).add("Id", achievement.getAchievementId()).add("Type", Integer.valueOf(achievement.getType())).add("Name", achievement.getName()).add("Description", achievement.getDescription()).add("Player", achievement.getPlayer()).add("State", Integer.valueOf(achievement.getState()));
        if (achievement.getType() == 1) {
            add.add("CurrentSteps", Integer.valueOf(achievement.getCurrentSteps()));
            add.add("TotalSteps", Integer.valueOf(achievement.getTotalSteps()));
        }
        return add.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Achievement) {
            if (this == obj) {
                return true;
            }
            Achievement achievement = (Achievement) obj;
            if (achievement.getType() == getType() && ((getType() != 1 || (achievement.getCurrentSteps() == getCurrentSteps() && achievement.getTotalSteps() == getTotalSteps())) && achievement.getXpValue() == getXpValue() && achievement.getState() == getState() && achievement.getLastUpdatedTimestamp() == getLastUpdatedTimestamp() && Objects.equal(achievement.getAchievementId(), getAchievementId()) && Objects.equal(achievement.getName(), getName()) && Objects.equal(achievement.getDescription(), getDescription()) && Objects.equal(achievement.getPlayer(), getPlayer()))) {
                return true;
            }
        }
        return false;
    }

    public final Achievement freeze() {
        return this;
    }

    public final String getAchievementId() {
        return this.zzfa;
    }

    public final int getCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        Asserts.checkState(z);
        return this.zzfi;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    public final String getFormattedCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        Asserts.checkState(z);
        return this.zzfj;
    }

    public final void getFormattedCurrentSteps(CharArrayBuffer charArrayBuffer) {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        Asserts.checkState(z);
        DataUtils.copyStringToBuffer(this.zzfj, charArrayBuffer);
    }

    public final String getFormattedTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        Asserts.checkState(z);
        return this.zzfg;
    }

    public final void getFormattedTotalSteps(CharArrayBuffer charArrayBuffer) {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        Asserts.checkState(z);
        DataUtils.copyStringToBuffer(this.zzfg, charArrayBuffer);
    }

    public final long getLastUpdatedTimestamp() {
        return this.zzfk;
    }

    public final String getName() {
        return this.name;
    }

    public final void getName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.name, charArrayBuffer);
    }

    public final Player getPlayer() {
        return this.zzfh;
    }

    public final Uri getRevealedImageUri() {
        return this.zzfd;
    }

    public final String getRevealedImageUrl() {
        return this.zzfe;
    }

    public final int getState() {
        return this.state;
    }

    public final int getTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        Asserts.checkState(z);
        return this.zzff;
    }

    public final int getType() {
        return this.type;
    }

    public final Uri getUnlockedImageUri() {
        return this.zzfb;
    }

    public final String getUnlockedImageUrl() {
        return this.zzfc;
    }

    public final long getXpValue() {
        return this.zzfl;
    }

    public final int hashCode() {
        int currentSteps;
        int totalSteps;
        if (getType() == 1) {
            currentSteps = getCurrentSteps();
            totalSteps = getTotalSteps();
        } else {
            totalSteps = 0;
            currentSteps = 0;
        }
        return Objects.hashCode(new Object[]{getAchievementId(), getName(), Integer.valueOf(getType()), getDescription(), Long.valueOf(getXpValue()), Integer.valueOf(getState()), Long.valueOf(getLastUpdatedTimestamp()), getPlayer(), Integer.valueOf(currentSteps), Integer.valueOf(totalSteps)});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zza(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAchievementId(), false);
        SafeParcelWriter.writeInt(parcel, 2, getType());
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getDescription(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getUnlockedImageUri(), i, false);
        SafeParcelWriter.writeString(parcel, 6, getUnlockedImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, getRevealedImageUri(), i, false);
        SafeParcelWriter.writeString(parcel, 8, getRevealedImageUrl(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzff);
        SafeParcelWriter.writeString(parcel, 10, this.zzfg, false);
        SafeParcelWriter.writeParcelable(parcel, 11, getPlayer(), i, false);
        SafeParcelWriter.writeInt(parcel, 12, getState());
        SafeParcelWriter.writeInt(parcel, 13, this.zzfi);
        SafeParcelWriter.writeString(parcel, 14, this.zzfj, false);
        SafeParcelWriter.writeLong(parcel, 15, getLastUpdatedTimestamp());
        SafeParcelWriter.writeLong(parcel, 16, getXpValue());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
