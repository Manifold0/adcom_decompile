package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
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
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.games.internal.zzd;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.vungle.warren.persistence.FilePersistor.Version;
import java.util.ArrayList;

@Class(creator = "TurnBasedMatchEntityCreator")
@Reserved({1000})
public final class TurnBasedMatchEntity extends zzd implements TurnBasedMatch {
    public static final Creator<TurnBasedMatchEntity> CREATOR = new zzc();
    @Field(getter = "getData", id = 12)
    private final byte[] data;
    @Field(getter = "getDescription", id = 20)
    private final String description;
    @Field(getter = "getVersion", id = 11)
    private final int version;
    @Field(getter = "getLastUpdatedTimestamp", id = 6)
    private final long zzfk;
    @Field(getter = "getMatchId", id = 2)
    private final String zzhg;
    @Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @Field(getter = "getCreationTimestamp", id = 4)
    private final long zzoa;
    @Field(getter = "getParticipants", id = 13)
    private final ArrayList<ParticipantEntity> zzod;
    @Field(getter = "getVariant", id = 10)
    private final int zzoe;
    @Nullable
    @Field(getter = "getAutoMatchCriteria", id = 17)
    private final Bundle zzoz;
    @Field(getter = "getCreatorId", id = 3)
    private final String zzpc;
    @Field(getter = "getLastUpdaterId", id = 5)
    private final String zzpl;
    @Field(getter = "getPendingParticipantId", id = 7)
    private final String zzpm;
    @Field(getter = "getStatus", id = 8)
    private final int zzpn;
    @Field(getter = "getRematchId", id = 14)
    private final String zzpo;
    @Field(getter = "getPreviousMatchData", id = 15)
    private final byte[] zzpp;
    @Field(getter = "getMatchNumber", id = 16)
    private final int zzpq;
    @Field(getter = "getTurnStatus", id = 18)
    private final int zzpr;
    @Field(getter = "isLocallyModified", id = 19)
    private final boolean zzps;
    @Field(getter = "getDescriptionParticipantId", id = 21)
    private final String zzpt;

    @Constructor
    TurnBasedMatchEntity(@Param(id = 1) GameEntity gameEntity, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) long j, @Param(id = 5) String str3, @Param(id = 6) long j2, @Param(id = 7) String str4, @Param(id = 8) int i, @Param(id = 10) int i2, @Param(id = 11) int i3, @Param(id = 12) byte[] bArr, @Param(id = 13) ArrayList<ParticipantEntity> arrayList, @Param(id = 14) String str5, @Param(id = 15) byte[] bArr2, @Param(id = 16) int i4, @Nullable @Param(id = 17) Bundle bundle, @Param(id = 18) int i5, @Param(id = 19) boolean z, @Param(id = 20) String str6, @Param(id = 21) String str7) {
        this.zzky = gameEntity;
        this.zzhg = str;
        this.zzpc = str2;
        this.zzoa = j;
        this.zzpl = str3;
        this.zzfk = j2;
        this.zzpm = str4;
        this.zzpn = i;
        this.zzpr = i5;
        this.zzoe = i2;
        this.version = i3;
        this.data = bArr;
        this.zzod = arrayList;
        this.zzpo = str5;
        this.zzpp = bArr2;
        this.zzpq = i4;
        this.zzoz = bundle;
        this.zzps = z;
        this.description = str6;
        this.zzpt = str7;
    }

    public TurnBasedMatchEntity(TurnBasedMatch turnBasedMatch) {
        this.zzky = new GameEntity(turnBasedMatch.getGame());
        this.zzhg = turnBasedMatch.getMatchId();
        this.zzpc = turnBasedMatch.getCreatorId();
        this.zzoa = turnBasedMatch.getCreationTimestamp();
        this.zzpl = turnBasedMatch.getLastUpdaterId();
        this.zzfk = turnBasedMatch.getLastUpdatedTimestamp();
        this.zzpm = turnBasedMatch.getPendingParticipantId();
        this.zzpn = turnBasedMatch.getStatus();
        this.zzpr = turnBasedMatch.getTurnStatus();
        this.zzoe = turnBasedMatch.getVariant();
        this.version = turnBasedMatch.getVersion();
        this.zzpo = turnBasedMatch.getRematchId();
        this.zzpq = turnBasedMatch.getMatchNumber();
        this.zzoz = turnBasedMatch.getAutoMatchCriteria();
        this.zzps = turnBasedMatch.isLocallyModified();
        this.description = turnBasedMatch.getDescription();
        this.zzpt = turnBasedMatch.getDescriptionParticipantId();
        Object data = turnBasedMatch.getData();
        if (data == null) {
            this.data = null;
        } else {
            this.data = new byte[data.length];
            System.arraycopy(data, 0, this.data, 0, data.length);
        }
        data = turnBasedMatch.getPreviousMatchData();
        if (data == null) {
            this.zzpp = null;
        } else {
            this.zzpp = new byte[data.length];
            System.arraycopy(data, 0, this.zzpp, 0, data.length);
        }
        ArrayList participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        this.zzod = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.zzod.add((ParticipantEntity) ((Participant) participants.get(i)).freeze());
        }
    }

    static int zza(TurnBasedMatch turnBasedMatch) {
        return Objects.hashCode(new Object[]{turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), Long.valueOf(turnBasedMatch.getCreationTimestamp()), turnBasedMatch.getLastUpdaterId(), Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp()), turnBasedMatch.getPendingParticipantId(), Integer.valueOf(turnBasedMatch.getStatus()), Integer.valueOf(turnBasedMatch.getTurnStatus()), turnBasedMatch.getDescription(), Integer.valueOf(turnBasedMatch.getVariant()), Integer.valueOf(turnBasedMatch.getVersion()), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), Integer.valueOf(turnBasedMatch.getMatchNumber()), Integer.valueOf(zzc.zza(turnBasedMatch.getAutoMatchCriteria())), Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots()), Boolean.valueOf(turnBasedMatch.isLocallyModified())});
    }

    static int zza(TurnBasedMatch turnBasedMatch, String str) {
        ArrayList participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = (Participant) participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant.getStatus();
            }
        }
        String matchId = turnBasedMatch.getMatchId();
        throw new IllegalStateException(new StringBuilder((String.valueOf(str).length() + 29) + String.valueOf(matchId).length()).append("Participant ").append(str).append(" is not in match ").append(matchId).toString());
    }

    static boolean zza(TurnBasedMatch turnBasedMatch, Object obj) {
        if (!(obj instanceof TurnBasedMatch)) {
            return false;
        }
        if (turnBasedMatch == obj) {
            return true;
        }
        TurnBasedMatch turnBasedMatch2 = (TurnBasedMatch) obj;
        return Objects.equal(turnBasedMatch2.getGame(), turnBasedMatch.getGame()) && Objects.equal(turnBasedMatch2.getMatchId(), turnBasedMatch.getMatchId()) && Objects.equal(turnBasedMatch2.getCreatorId(), turnBasedMatch.getCreatorId()) && Objects.equal(Long.valueOf(turnBasedMatch2.getCreationTimestamp()), Long.valueOf(turnBasedMatch.getCreationTimestamp())) && Objects.equal(turnBasedMatch2.getLastUpdaterId(), turnBasedMatch.getLastUpdaterId()) && Objects.equal(Long.valueOf(turnBasedMatch2.getLastUpdatedTimestamp()), Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp())) && Objects.equal(turnBasedMatch2.getPendingParticipantId(), turnBasedMatch.getPendingParticipantId()) && Objects.equal(Integer.valueOf(turnBasedMatch2.getStatus()), Integer.valueOf(turnBasedMatch.getStatus())) && Objects.equal(Integer.valueOf(turnBasedMatch2.getTurnStatus()), Integer.valueOf(turnBasedMatch.getTurnStatus())) && Objects.equal(turnBasedMatch2.getDescription(), turnBasedMatch.getDescription()) && Objects.equal(Integer.valueOf(turnBasedMatch2.getVariant()), Integer.valueOf(turnBasedMatch.getVariant())) && Objects.equal(Integer.valueOf(turnBasedMatch2.getVersion()), Integer.valueOf(turnBasedMatch.getVersion())) && Objects.equal(turnBasedMatch2.getParticipants(), turnBasedMatch.getParticipants()) && Objects.equal(turnBasedMatch2.getRematchId(), turnBasedMatch.getRematchId()) && Objects.equal(Integer.valueOf(turnBasedMatch2.getMatchNumber()), Integer.valueOf(turnBasedMatch.getMatchNumber())) && zzc.zza(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && Objects.equal(Integer.valueOf(turnBasedMatch2.getAvailableAutoMatchSlots()), Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots())) && Objects.equal(Boolean.valueOf(turnBasedMatch2.isLocallyModified()), Boolean.valueOf(turnBasedMatch.isLocallyModified()));
    }

    static String zzb(TurnBasedMatch turnBasedMatch) {
        return Objects.toStringHelper(turnBasedMatch).add("Game", turnBasedMatch.getGame()).add("MatchId", turnBasedMatch.getMatchId()).add("CreatorId", turnBasedMatch.getCreatorId()).add("CreationTimestamp", Long.valueOf(turnBasedMatch.getCreationTimestamp())).add("LastUpdaterId", turnBasedMatch.getLastUpdaterId()).add("LastUpdatedTimestamp", Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp())).add("PendingParticipantId", turnBasedMatch.getPendingParticipantId()).add("MatchStatus", Integer.valueOf(turnBasedMatch.getStatus())).add("TurnStatus", Integer.valueOf(turnBasedMatch.getTurnStatus())).add("Description", turnBasedMatch.getDescription()).add("Variant", Integer.valueOf(turnBasedMatch.getVariant())).add(Version.ID, turnBasedMatch.getData()).add("Version", Integer.valueOf(turnBasedMatch.getVersion())).add("Participants", turnBasedMatch.getParticipants()).add("RematchId", turnBasedMatch.getRematchId()).add("PreviousData", turnBasedMatch.getPreviousMatchData()).add("MatchNumber", Integer.valueOf(turnBasedMatch.getMatchNumber())).add("AutoMatchCriteria", turnBasedMatch.getAutoMatchCriteria()).add("AvailableAutoMatchSlots", Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots())).add("LocallyModified", Boolean.valueOf(turnBasedMatch.isLocallyModified())).add("DescriptionParticipantId", turnBasedMatch.getDescriptionParticipantId()).toString();
    }

    static String zzb(TurnBasedMatch turnBasedMatch, String str) {
        ArrayList participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = (Participant) participants.get(i);
            Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(str)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }

    static Participant zzc(TurnBasedMatch turnBasedMatch, String str) {
        ArrayList participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = (Participant) participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant;
            }
        }
        String matchId = turnBasedMatch.getMatchId();
        throw new IllegalStateException(new StringBuilder((String.valueOf(str).length() + 29) + String.valueOf(matchId).length()).append("Participant ").append(str).append(" is not in match ").append(matchId).toString());
    }

    static ArrayList<String> zzc(TurnBasedMatch turnBasedMatch) {
        ArrayList participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        ArrayList<String> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(((Participant) participants.get(i)).getParticipantId());
        }
        return arrayList;
    }

    public final boolean canRematch() {
        return this.zzpn == 2 && this.zzpo == null;
    }

    public final boolean equals(Object obj) {
        return zza((TurnBasedMatch) this, obj);
    }

    public final TurnBasedMatch freeze() {
        return this;
    }

    @Nullable
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }

    public final int getAvailableAutoMatchSlots() {
        return this.zzoz == null ? 0 : this.zzoz.getInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS);
    }

    public final long getCreationTimestamp() {
        return this.zzoa;
    }

    public final String getCreatorId() {
        return this.zzpc;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    public final Participant getDescriptionParticipant() {
        String descriptionParticipantId = getDescriptionParticipantId();
        return descriptionParticipantId == null ? null : getParticipant(descriptionParticipantId);
    }

    public final String getDescriptionParticipantId() {
        return this.zzpt;
    }

    public final Game getGame() {
        return this.zzky;
    }

    public final long getLastUpdatedTimestamp() {
        return this.zzfk;
    }

    public final String getLastUpdaterId() {
        return this.zzpl;
    }

    public final String getMatchId() {
        return this.zzhg;
    }

    public final int getMatchNumber() {
        return this.zzpq;
    }

    public final Participant getParticipant(String str) {
        return zzc(this, str);
    }

    public final String getParticipantId(String str) {
        return zzb(this, str);
    }

    public final ArrayList<String> getParticipantIds() {
        return zzc(this);
    }

    public final int getParticipantStatus(String str) {
        return zza((TurnBasedMatch) this, str);
    }

    public final ArrayList<Participant> getParticipants() {
        return new ArrayList(this.zzod);
    }

    public final String getPendingParticipantId() {
        return this.zzpm;
    }

    public final byte[] getPreviousMatchData() {
        return this.zzpp;
    }

    public final String getRematchId() {
        return this.zzpo;
    }

    public final int getStatus() {
        return this.zzpn;
    }

    public final int getTurnStatus() {
        return this.zzpr;
    }

    public final int getVariant() {
        return this.zzoe;
    }

    public final int getVersion() {
        return this.version;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isLocallyModified() {
        return this.zzps;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getMatchId(), false);
        SafeParcelWriter.writeString(parcel, 3, getCreatorId(), false);
        SafeParcelWriter.writeLong(parcel, 4, getCreationTimestamp());
        SafeParcelWriter.writeString(parcel, 5, getLastUpdaterId(), false);
        SafeParcelWriter.writeLong(parcel, 6, getLastUpdatedTimestamp());
        SafeParcelWriter.writeString(parcel, 7, getPendingParticipantId(), false);
        SafeParcelWriter.writeInt(parcel, 8, getStatus());
        SafeParcelWriter.writeInt(parcel, 10, getVariant());
        SafeParcelWriter.writeInt(parcel, 11, getVersion());
        SafeParcelWriter.writeByteArray(parcel, 12, getData(), false);
        SafeParcelWriter.writeTypedList(parcel, 13, getParticipants(), false);
        SafeParcelWriter.writeString(parcel, 14, getRematchId(), false);
        SafeParcelWriter.writeByteArray(parcel, 15, getPreviousMatchData(), false);
        SafeParcelWriter.writeInt(parcel, 16, getMatchNumber());
        SafeParcelWriter.writeBundle(parcel, 17, getAutoMatchCriteria(), false);
        SafeParcelWriter.writeInt(parcel, 18, getTurnStatus());
        SafeParcelWriter.writeBoolean(parcel, 19, isLocallyModified());
        SafeParcelWriter.writeString(parcel, 20, getDescription(), false);
        SafeParcelWriter.writeString(parcel, 21, getDescriptionParticipantId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
