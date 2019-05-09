package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
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
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.zzd;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "QuestEntityCreator")
@Reserved({1000})
@Deprecated
public final class QuestEntity extends zzd implements Quest {
    public static final Creator<QuestEntity> CREATOR = new zzc();
    @Field(getter = "getDescription", id = 6)
    private final String description;
    @Field(getter = "getName", id = 12)
    private final String name;
    @Field(getter = "getState", id = 15)
    private final int state;
    @Field(getter = "getType", id = 16)
    private final int type;
    @Field(getter = "getLastUpdatedTimestamp", id = 8)
    private final long zzfk;
    @Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @Field(getter = "getQuestId", id = 2)
    private final String zzpx;
    @Field(getter = "getAcceptedTimestamp", id = 3)
    private final long zzpy;
    @Field(getter = "getBannerImageUri", id = 4)
    private final Uri zzpz;
    @Field(getter = "getBannerImageUrl", id = 5)
    private final String zzqa;
    @Field(getter = "getEndTimestamp", id = 7)
    private final long zzqb;
    @Field(getter = "getIconImageUri", id = 9)
    private final Uri zzqc;
    @Field(getter = "getIconImageUrl", id = 10)
    private final String zzqd;
    @Field(getter = "getNotifyTimestamp", id = 13)
    private final long zzqe;
    @Field(getter = "getStartTimestamp", id = 14)
    private final long zzqf;
    @Field(getter = "getMilestones", id = 17)
    private final ArrayList<MilestoneEntity> zzqg;

    @Constructor
    QuestEntity(@Param(id = 1) GameEntity gameEntity, @Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) Uri uri, @Param(id = 5) String str2, @Param(id = 6) String str3, @Param(id = 7) long j2, @Param(id = 8) long j3, @Param(id = 9) Uri uri2, @Param(id = 10) String str4, @Param(id = 12) String str5, @Param(id = 13) long j4, @Param(id = 14) long j5, @Param(id = 15) int i, @Param(id = 16) int i2, @Param(id = 17) ArrayList<MilestoneEntity> arrayList) {
        this.zzky = gameEntity;
        this.zzpx = str;
        this.zzpy = j;
        this.zzpz = uri;
        this.zzqa = str2;
        this.description = str3;
        this.zzqb = j2;
        this.zzfk = j3;
        this.zzqc = uri2;
        this.zzqd = str4;
        this.name = str5;
        this.zzqe = j4;
        this.zzqf = j5;
        this.state = i;
        this.type = i2;
        this.zzqg = arrayList;
    }

    public QuestEntity(Quest quest) {
        this.zzky = new GameEntity(quest.getGame());
        this.zzpx = quest.getQuestId();
        this.zzpy = quest.getAcceptedTimestamp();
        this.description = quest.getDescription();
        this.zzpz = quest.getBannerImageUri();
        this.zzqa = quest.getBannerImageUrl();
        this.zzqb = quest.getEndTimestamp();
        this.zzqc = quest.getIconImageUri();
        this.zzqd = quest.getIconImageUrl();
        this.zzfk = quest.getLastUpdatedTimestamp();
        this.name = quest.getName();
        this.zzqe = quest.zzck();
        this.zzqf = quest.getStartTimestamp();
        this.state = quest.getState();
        this.type = quest.getType();
        List zzcj = quest.zzcj();
        int size = zzcj.size();
        this.zzqg = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.zzqg.add((MilestoneEntity) ((Milestone) zzcj.get(i)).freeze());
        }
    }

    static int zza(Quest quest) {
        return Objects.hashCode(new Object[]{quest.getGame(), quest.getQuestId(), Long.valueOf(quest.getAcceptedTimestamp()), quest.getBannerImageUri(), quest.getDescription(), Long.valueOf(quest.getEndTimestamp()), quest.getIconImageUri(), Long.valueOf(quest.getLastUpdatedTimestamp()), quest.zzcj(), quest.getName(), Long.valueOf(quest.zzck()), Long.valueOf(quest.getStartTimestamp()), Integer.valueOf(quest.getState())});
    }

    static boolean zza(Quest quest, Object obj) {
        if (!(obj instanceof Quest)) {
            return false;
        }
        if (quest == obj) {
            return true;
        }
        Quest quest2 = (Quest) obj;
        return Objects.equal(quest2.getGame(), quest.getGame()) && Objects.equal(quest2.getQuestId(), quest.getQuestId()) && Objects.equal(Long.valueOf(quest2.getAcceptedTimestamp()), Long.valueOf(quest.getAcceptedTimestamp())) && Objects.equal(quest2.getBannerImageUri(), quest.getBannerImageUri()) && Objects.equal(quest2.getDescription(), quest.getDescription()) && Objects.equal(Long.valueOf(quest2.getEndTimestamp()), Long.valueOf(quest.getEndTimestamp())) && Objects.equal(quest2.getIconImageUri(), quest.getIconImageUri()) && Objects.equal(Long.valueOf(quest2.getLastUpdatedTimestamp()), Long.valueOf(quest.getLastUpdatedTimestamp())) && Objects.equal(quest2.zzcj(), quest.zzcj()) && Objects.equal(quest2.getName(), quest.getName()) && Objects.equal(Long.valueOf(quest2.zzck()), Long.valueOf(quest.zzck())) && Objects.equal(Long.valueOf(quest2.getStartTimestamp()), Long.valueOf(quest.getStartTimestamp())) && Objects.equal(Integer.valueOf(quest2.getState()), Integer.valueOf(quest.getState()));
    }

    static String zzb(Quest quest) {
        return Objects.toStringHelper(quest).add("Game", quest.getGame()).add("QuestId", quest.getQuestId()).add("AcceptedTimestamp", Long.valueOf(quest.getAcceptedTimestamp())).add("BannerImageUri", quest.getBannerImageUri()).add("BannerImageUrl", quest.getBannerImageUrl()).add("Description", quest.getDescription()).add("EndTimestamp", Long.valueOf(quest.getEndTimestamp())).add("IconImageUri", quest.getIconImageUri()).add("IconImageUrl", quest.getIconImageUrl()).add("LastUpdatedTimestamp", Long.valueOf(quest.getLastUpdatedTimestamp())).add("Milestones", quest.zzcj()).add("Name", quest.getName()).add("NotifyTimestamp", Long.valueOf(quest.zzck())).add("StartTimestamp", Long.valueOf(quest.getStartTimestamp())).add("State", Integer.valueOf(quest.getState())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Quest freeze() {
        return this;
    }

    public final long getAcceptedTimestamp() {
        return this.zzpy;
    }

    public final Uri getBannerImageUri() {
        return this.zzpz;
    }

    public final String getBannerImageUrl() {
        return this.zzqa;
    }

    public final Milestone getCurrentMilestone() {
        return (Milestone) zzcj().get(0);
    }

    public final String getDescription() {
        return this.description;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    public final long getEndTimestamp() {
        return this.zzqb;
    }

    public final Game getGame() {
        return this.zzky;
    }

    public final Uri getIconImageUri() {
        return this.zzqc;
    }

    public final String getIconImageUrl() {
        return this.zzqd;
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

    public final String getQuestId() {
        return this.zzpx;
    }

    public final long getStartTimestamp() {
        return this.zzqf;
    }

    public final int getState() {
        return this.state;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isEndingSoon() {
        return this.zzqe <= System.currentTimeMillis() + TapjoyConstants.SESSION_ID_INACTIVITY_TIME;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getQuestId(), false);
        SafeParcelWriter.writeLong(parcel, 3, getAcceptedTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 4, getBannerImageUri(), i, false);
        SafeParcelWriter.writeString(parcel, 5, getBannerImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, getDescription(), false);
        SafeParcelWriter.writeLong(parcel, 7, getEndTimestamp());
        SafeParcelWriter.writeLong(parcel, 8, getLastUpdatedTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 9, getIconImageUri(), i, false);
        SafeParcelWriter.writeString(parcel, 10, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 12, getName(), false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzqe);
        SafeParcelWriter.writeLong(parcel, 14, getStartTimestamp());
        SafeParcelWriter.writeInt(parcel, 15, getState());
        SafeParcelWriter.writeInt(parcel, 16, this.type);
        SafeParcelWriter.writeTypedList(parcel, 17, zzcj(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<Milestone> zzcj() {
        return new ArrayList(this.zzqg);
    }

    public final long zzck() {
        return this.zzqe;
    }
}
