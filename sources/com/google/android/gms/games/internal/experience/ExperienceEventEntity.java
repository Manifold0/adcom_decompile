package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "ExperienceEventEntityCreator")
@Reserved({1000})
public final class ExperienceEventEntity extends zzd implements ExperienceEvent {
    public static final Creator<ExperienceEventEntity> CREATOR = new zza();
    @Field(getter = "getType", id = 10)
    private final int type;
    @Field(getter = "getIconImageUrl", id = 5)
    private final String zzac;
    @Field(getter = "getExperienceId", id = 1)
    private final String zzkx;
    @Field(getter = "getGame", id = 2)
    private final GameEntity zzky;
    @Field(getter = "getDisplayTitle", id = 3)
    private final String zzkz;
    @Field(getter = "getDisplayDescription", id = 4)
    private final String zzla;
    @Field(getter = "getCreatedTimestamp", id = 7)
    private final long zzlb;
    @Field(getter = "getXpEarned", id = 8)
    private final long zzlc;
    @Field(getter = "getCurrentXp", id = 9)
    private final long zzld;
    @Field(getter = "getNewLevel", id = 11)
    private final int zzle;
    @Field(getter = "getIconImageUri", id = 6)
    private final Uri zzr;

    @Constructor
    ExperienceEventEntity(@Param(id = 1) String str, @Param(id = 2) GameEntity gameEntity, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) Uri uri, @Param(id = 7) long j, @Param(id = 8) long j2, @Param(id = 9) long j3, @Param(id = 10) int i, @Param(id = 11) int i2) {
        this.zzkx = str;
        this.zzky = gameEntity;
        this.zzkz = str2;
        this.zzla = str3;
        this.zzac = str4;
        this.zzr = uri;
        this.zzlb = j;
        this.zzlc = j2;
        this.zzld = j3;
        this.type = i;
        this.zzle = i2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ExperienceEvent) {
            if (this == obj) {
                return true;
            }
            ExperienceEvent experienceEvent = (ExperienceEvent) obj;
            if (Objects.equal(experienceEvent.zzbm(), zzbm()) && Objects.equal(experienceEvent.getGame(), getGame()) && Objects.equal(experienceEvent.zzbn(), zzbn()) && Objects.equal(experienceEvent.zzbo(), zzbo()) && Objects.equal(experienceEvent.getIconImageUrl(), getIconImageUrl()) && Objects.equal(experienceEvent.getIconImageUri(), getIconImageUri()) && Objects.equal(Long.valueOf(experienceEvent.zzbp()), Long.valueOf(zzbp())) && Objects.equal(Long.valueOf(experienceEvent.zzbq()), Long.valueOf(zzbq())) && Objects.equal(Long.valueOf(experienceEvent.zzbr()), Long.valueOf(zzbr())) && Objects.equal(Integer.valueOf(experienceEvent.getType()), Integer.valueOf(getType())) && Objects.equal(Integer.valueOf(experienceEvent.zzbs()), Integer.valueOf(zzbs()))) {
                return true;
            }
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        if (this != null) {
            return this;
        }
        throw null;
    }

    public final Game getGame() {
        return this.zzky;
    }

    public final Uri getIconImageUri() {
        return this.zzr;
    }

    public final String getIconImageUrl() {
        return this.zzac;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{zzbm(), getGame(), zzbn(), zzbo(), getIconImageUrl(), getIconImageUri(), Long.valueOf(zzbp()), Long.valueOf(zzbq()), Long.valueOf(zzbr()), Integer.valueOf(getType()), Integer.valueOf(zzbs())});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("ExperienceId", zzbm()).add("Game", getGame()).add("DisplayTitle", zzbn()).add("DisplayDescription", zzbo()).add("IconImageUrl", getIconImageUrl()).add("IconImageUri", getIconImageUri()).add("CreatedTimestamp", Long.valueOf(zzbp())).add("XpEarned", Long.valueOf(zzbq())).add("CurrentXp", Long.valueOf(zzbr())).add("Type", Integer.valueOf(getType())).add("NewLevel", Integer.valueOf(zzbs())).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzkx, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzky, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzkz, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzla, false);
        SafeParcelWriter.writeString(parcel, 5, getIconImageUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzr, i, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzlb);
        SafeParcelWriter.writeLong(parcel, 8, this.zzlc);
        SafeParcelWriter.writeLong(parcel, 9, this.zzld);
        SafeParcelWriter.writeInt(parcel, 10, this.type);
        SafeParcelWriter.writeInt(parcel, 11, this.zzle);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzbm() {
        return this.zzkx;
    }

    public final String zzbn() {
        return this.zzkz;
    }

    public final String zzbo() {
        return this.zzla;
    }

    public final long zzbp() {
        return this.zzlb;
    }

    public final long zzbq() {
        return this.zzlc;
    }

    public final long zzbr() {
        return this.zzld;
    }

    public final int zzbs() {
        return this.zzle;
    }
}
