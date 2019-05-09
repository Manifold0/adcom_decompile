// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import java.util.Collection;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.ArrayList;
import android.net.Uri;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@Deprecated
@SafeParcelable$Class(creator = "QuestEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class QuestEntity extends zzd implements Quest
{
    public static final Parcelable$Creator<QuestEntity> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 6)
    private final String description;
    @SafeParcelable$Field(getter = "getName", id = 12)
    private final String name;
    @SafeParcelable$Field(getter = "getState", id = 15)
    private final int state;
    @SafeParcelable$Field(getter = "getType", id = 16)
    private final int type;
    @SafeParcelable$Field(getter = "getLastUpdatedTimestamp", id = 8)
    private final long zzfk;
    @SafeParcelable$Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @SafeParcelable$Field(getter = "getQuestId", id = 2)
    private final String zzpx;
    @SafeParcelable$Field(getter = "getAcceptedTimestamp", id = 3)
    private final long zzpy;
    @SafeParcelable$Field(getter = "getBannerImageUri", id = 4)
    private final Uri zzpz;
    @SafeParcelable$Field(getter = "getBannerImageUrl", id = 5)
    private final String zzqa;
    @SafeParcelable$Field(getter = "getEndTimestamp", id = 7)
    private final long zzqb;
    @SafeParcelable$Field(getter = "getIconImageUri", id = 9)
    private final Uri zzqc;
    @SafeParcelable$Field(getter = "getIconImageUrl", id = 10)
    private final String zzqd;
    @SafeParcelable$Field(getter = "getNotifyTimestamp", id = 13)
    private final long zzqe;
    @SafeParcelable$Field(getter = "getStartTimestamp", id = 14)
    private final long zzqf;
    @SafeParcelable$Field(getter = "getMilestones", id = 17)
    private final ArrayList<MilestoneEntity> zzqg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    QuestEntity(@SafeParcelable$Param(id = 1) final GameEntity zzky, @SafeParcelable$Param(id = 2) final String zzpx, @SafeParcelable$Param(id = 3) final long zzpy, @SafeParcelable$Param(id = 4) final Uri zzpz, @SafeParcelable$Param(id = 5) final String zzqa, @SafeParcelable$Param(id = 6) final String description, @SafeParcelable$Param(id = 7) final long zzqb, @SafeParcelable$Param(id = 8) final long zzfk, @SafeParcelable$Param(id = 9) final Uri zzqc, @SafeParcelable$Param(id = 10) final String zzqd, @SafeParcelable$Param(id = 12) final String name, @SafeParcelable$Param(id = 13) final long zzqe, @SafeParcelable$Param(id = 14) final long zzqf, @SafeParcelable$Param(id = 15) final int state, @SafeParcelable$Param(id = 16) final int type, @SafeParcelable$Param(id = 17) final ArrayList<MilestoneEntity> zzqg) {
        this.zzky = zzky;
        this.zzpx = zzpx;
        this.zzpy = zzpy;
        this.zzpz = zzpz;
        this.zzqa = zzqa;
        this.description = description;
        this.zzqb = zzqb;
        this.zzfk = zzfk;
        this.zzqc = zzqc;
        this.zzqd = zzqd;
        this.name = name;
        this.zzqe = zzqe;
        this.zzqf = zzqf;
        this.state = state;
        this.type = type;
        this.zzqg = zzqg;
    }
    
    public QuestEntity(final Quest quest) {
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
        final List<Milestone> zzcj = quest.zzcj();
        final int size = zzcj.size();
        this.zzqg = new ArrayList<MilestoneEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.zzqg.add((MilestoneEntity)zzcj.get(i).freeze());
        }
    }
    
    static int zza(final Quest quest) {
        return Objects.hashCode(new Object[] { quest.getGame(), quest.getQuestId(), quest.getAcceptedTimestamp(), quest.getBannerImageUri(), quest.getDescription(), quest.getEndTimestamp(), quest.getIconImageUri(), quest.getLastUpdatedTimestamp(), quest.zzcj(), quest.getName(), quest.zzck(), quest.getStartTimestamp(), quest.getState() });
    }
    
    static boolean zza(final Quest quest, final Object o) {
        if (o instanceof Quest) {
            if (quest == o) {
                return true;
            }
            final Quest quest2 = (Quest)o;
            if (Objects.equal((Object)quest2.getGame(), (Object)quest.getGame()) && Objects.equal((Object)quest2.getQuestId(), (Object)quest.getQuestId()) && Objects.equal((Object)quest2.getAcceptedTimestamp(), (Object)quest.getAcceptedTimestamp()) && Objects.equal((Object)quest2.getBannerImageUri(), (Object)quest.getBannerImageUri()) && Objects.equal((Object)quest2.getDescription(), (Object)quest.getDescription()) && Objects.equal((Object)quest2.getEndTimestamp(), (Object)quest.getEndTimestamp()) && Objects.equal((Object)quest2.getIconImageUri(), (Object)quest.getIconImageUri()) && Objects.equal((Object)quest2.getLastUpdatedTimestamp(), (Object)quest.getLastUpdatedTimestamp()) && Objects.equal((Object)quest2.zzcj(), (Object)quest.zzcj()) && Objects.equal((Object)quest2.getName(), (Object)quest.getName()) && Objects.equal((Object)quest2.zzck(), (Object)quest.zzck()) && Objects.equal((Object)quest2.getStartTimestamp(), (Object)quest.getStartTimestamp()) && Objects.equal((Object)quest2.getState(), (Object)quest.getState())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final Quest quest) {
        return Objects.toStringHelper((Object)quest).add("Game", (Object)quest.getGame()).add("QuestId", (Object)quest.getQuestId()).add("AcceptedTimestamp", (Object)quest.getAcceptedTimestamp()).add("BannerImageUri", (Object)quest.getBannerImageUri()).add("BannerImageUrl", (Object)quest.getBannerImageUrl()).add("Description", (Object)quest.getDescription()).add("EndTimestamp", (Object)quest.getEndTimestamp()).add("IconImageUri", (Object)quest.getIconImageUri()).add("IconImageUrl", (Object)quest.getIconImageUrl()).add("LastUpdatedTimestamp", (Object)quest.getLastUpdatedTimestamp()).add("Milestones", (Object)quest.zzcj()).add("Name", (Object)quest.getName()).add("NotifyTimestamp", (Object)quest.zzck()).add("StartTimestamp", (Object)quest.getStartTimestamp()).add("State", (Object)quest.getState()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Quest freeze() {
        return this;
    }
    
    @Override
    public final long getAcceptedTimestamp() {
        return this.zzpy;
    }
    
    @Override
    public final Uri getBannerImageUri() {
        return this.zzpz;
    }
    
    @Override
    public final String getBannerImageUrl() {
        return this.zzqa;
    }
    
    @Override
    public final Milestone getCurrentMilestone() {
        return this.zzcj().get(0);
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
    public final long getEndTimestamp() {
        return this.zzqb;
    }
    
    @Override
    public final Game getGame() {
        return this.zzky;
    }
    
    @Override
    public final Uri getIconImageUri() {
        return this.zzqc;
    }
    
    @Override
    public final String getIconImageUrl() {
        return this.zzqd;
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
    public final String getQuestId() {
        return this.zzpx;
    }
    
    @Override
    public final long getStartTimestamp() {
        return this.zzqf;
    }
    
    @Override
    public final int getState() {
        return this.state;
    }
    
    @Override
    public final int getType() {
        return this.type;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final boolean isEndingSoon() {
        return this.zzqe <= System.currentTimeMillis() + 1800000L;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getGame(), n, false);
        SafeParcelWriter.writeString(parcel, 2, this.getQuestId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.getAcceptedTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getBannerImageUri(), n, false);
        SafeParcelWriter.writeString(parcel, 5, this.getBannerImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, this.getDescription(), false);
        SafeParcelWriter.writeLong(parcel, 7, this.getEndTimestamp());
        SafeParcelWriter.writeLong(parcel, 8, this.getLastUpdatedTimestamp());
        SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.getIconImageUri(), n, false);
        SafeParcelWriter.writeString(parcel, 10, this.getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 12, this.getName(), false);
        SafeParcelWriter.writeLong(parcel, 13, this.zzqe);
        SafeParcelWriter.writeLong(parcel, 14, this.getStartTimestamp());
        SafeParcelWriter.writeInt(parcel, 15, this.getState());
        SafeParcelWriter.writeInt(parcel, 16, this.type);
        SafeParcelWriter.writeTypedList(parcel, 17, (List)this.zzcj(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    public final List<Milestone> zzcj() {
        return new ArrayList<Milestone>(this.zzqg);
    }
    
    @Override
    public final long zzck() {
        return this.zzqe;
    }
}
