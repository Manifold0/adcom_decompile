// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "TurnBasedMatchEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class TurnBasedMatchEntity extends zzd implements TurnBasedMatch
{
    public static final Parcelable$Creator<TurnBasedMatchEntity> CREATOR;
    @SafeParcelable$Field(getter = "getData", id = 12)
    private final byte[] data;
    @SafeParcelable$Field(getter = "getDescription", id = 20)
    private final String description;
    @SafeParcelable$Field(getter = "getVersion", id = 11)
    private final int version;
    @SafeParcelable$Field(getter = "getLastUpdatedTimestamp", id = 6)
    private final long zzfk;
    @SafeParcelable$Field(getter = "getMatchId", id = 2)
    private final String zzhg;
    @SafeParcelable$Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @SafeParcelable$Field(getter = "getCreationTimestamp", id = 4)
    private final long zzoa;
    @SafeParcelable$Field(getter = "getParticipants", id = 13)
    private final ArrayList<ParticipantEntity> zzod;
    @SafeParcelable$Field(getter = "getVariant", id = 10)
    private final int zzoe;
    @Nullable
    @SafeParcelable$Field(getter = "getAutoMatchCriteria", id = 17)
    private final Bundle zzoz;
    @SafeParcelable$Field(getter = "getCreatorId", id = 3)
    private final String zzpc;
    @SafeParcelable$Field(getter = "getLastUpdaterId", id = 5)
    private final String zzpl;
    @SafeParcelable$Field(getter = "getPendingParticipantId", id = 7)
    private final String zzpm;
    @SafeParcelable$Field(getter = "getStatus", id = 8)
    private final int zzpn;
    @SafeParcelable$Field(getter = "getRematchId", id = 14)
    private final String zzpo;
    @SafeParcelable$Field(getter = "getPreviousMatchData", id = 15)
    private final byte[] zzpp;
    @SafeParcelable$Field(getter = "getMatchNumber", id = 16)
    private final int zzpq;
    @SafeParcelable$Field(getter = "getTurnStatus", id = 18)
    private final int zzpr;
    @SafeParcelable$Field(getter = "isLocallyModified", id = 19)
    private final boolean zzps;
    @SafeParcelable$Field(getter = "getDescriptionParticipantId", id = 21)
    private final String zzpt;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    TurnBasedMatchEntity(@SafeParcelable$Param(id = 1) final GameEntity zzky, @SafeParcelable$Param(id = 2) final String zzhg, @SafeParcelable$Param(id = 3) final String zzpc, @SafeParcelable$Param(id = 4) final long zzoa, @SafeParcelable$Param(id = 5) final String zzpl, @SafeParcelable$Param(id = 6) final long zzfk, @SafeParcelable$Param(id = 7) final String zzpm, @SafeParcelable$Param(id = 8) final int zzpn, @SafeParcelable$Param(id = 10) final int zzoe, @SafeParcelable$Param(id = 11) final int version, @SafeParcelable$Param(id = 12) final byte[] data, @SafeParcelable$Param(id = 13) final ArrayList<ParticipantEntity> zzod, @SafeParcelable$Param(id = 14) final String zzpo, @SafeParcelable$Param(id = 15) final byte[] zzpp, @SafeParcelable$Param(id = 16) final int zzpq, @Nullable @SafeParcelable$Param(id = 17) final Bundle zzoz, @SafeParcelable$Param(id = 18) final int zzpr, @SafeParcelable$Param(id = 19) final boolean zzps, @SafeParcelable$Param(id = 20) final String description, @SafeParcelable$Param(id = 21) final String zzpt) {
        this.zzky = zzky;
        this.zzhg = zzhg;
        this.zzpc = zzpc;
        this.zzoa = zzoa;
        this.zzpl = zzpl;
        this.zzfk = zzfk;
        this.zzpm = zzpm;
        this.zzpn = zzpn;
        this.zzpr = zzpr;
        this.zzoe = zzoe;
        this.version = version;
        this.data = data;
        this.zzod = zzod;
        this.zzpo = zzpo;
        this.zzpp = zzpp;
        this.zzpq = zzpq;
        this.zzoz = zzoz;
        this.zzps = zzps;
        this.description = description;
        this.zzpt = zzpt;
    }
    
    public TurnBasedMatchEntity(final TurnBasedMatch turnBasedMatch) {
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
        final byte[] data = turnBasedMatch.getData();
        if (data == null) {
            this.data = null;
        }
        else {
            System.arraycopy(data, 0, this.data = new byte[data.length], 0, data.length);
        }
        final byte[] previousMatchData = turnBasedMatch.getPreviousMatchData();
        if (previousMatchData == null) {
            this.zzpp = null;
        }
        else {
            System.arraycopy(previousMatchData, 0, this.zzpp = new byte[previousMatchData.length], 0, previousMatchData.length);
        }
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        final int size = participants.size();
        this.zzod = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.zzod.add((ParticipantEntity)participants.get(i).freeze());
        }
    }
    
    static int zza(final TurnBasedMatch turnBasedMatch) {
        return Objects.hashCode(new Object[] { turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), turnBasedMatch.getCreationTimestamp(), turnBasedMatch.getLastUpdaterId(), turnBasedMatch.getLastUpdatedTimestamp(), turnBasedMatch.getPendingParticipantId(), turnBasedMatch.getStatus(), turnBasedMatch.getTurnStatus(), turnBasedMatch.getDescription(), turnBasedMatch.getVariant(), turnBasedMatch.getVersion(), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), turnBasedMatch.getMatchNumber(), com.google.android.gms.games.internal.zzc.zza(turnBasedMatch.getAutoMatchCriteria()), turnBasedMatch.getAvailableAutoMatchSlots(), turnBasedMatch.isLocallyModified() });
    }
    
    static int zza(final TurnBasedMatch turnBasedMatch, final String s) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant.getStatus();
            }
        }
        final String matchId = turnBasedMatch.getMatchId();
        throw new IllegalStateException(new StringBuilder(String.valueOf(s).length() + 29 + String.valueOf(matchId).length()).append("Participant ").append(s).append(" is not in match ").append(matchId).toString());
    }
    
    static boolean zza(final TurnBasedMatch turnBasedMatch, final Object o) {
        if (o instanceof TurnBasedMatch) {
            if (turnBasedMatch == o) {
                return true;
            }
            final TurnBasedMatch turnBasedMatch2 = (TurnBasedMatch)o;
            if (Objects.equal((Object)turnBasedMatch2.getGame(), (Object)turnBasedMatch.getGame()) && Objects.equal((Object)turnBasedMatch2.getMatchId(), (Object)turnBasedMatch.getMatchId()) && Objects.equal((Object)turnBasedMatch2.getCreatorId(), (Object)turnBasedMatch.getCreatorId()) && Objects.equal((Object)turnBasedMatch2.getCreationTimestamp(), (Object)turnBasedMatch.getCreationTimestamp()) && Objects.equal((Object)turnBasedMatch2.getLastUpdaterId(), (Object)turnBasedMatch.getLastUpdaterId()) && Objects.equal((Object)turnBasedMatch2.getLastUpdatedTimestamp(), (Object)turnBasedMatch.getLastUpdatedTimestamp()) && Objects.equal((Object)turnBasedMatch2.getPendingParticipantId(), (Object)turnBasedMatch.getPendingParticipantId()) && Objects.equal((Object)turnBasedMatch2.getStatus(), (Object)turnBasedMatch.getStatus()) && Objects.equal((Object)turnBasedMatch2.getTurnStatus(), (Object)turnBasedMatch.getTurnStatus()) && Objects.equal((Object)turnBasedMatch2.getDescription(), (Object)turnBasedMatch.getDescription()) && Objects.equal((Object)turnBasedMatch2.getVariant(), (Object)turnBasedMatch.getVariant()) && Objects.equal((Object)turnBasedMatch2.getVersion(), (Object)turnBasedMatch.getVersion()) && Objects.equal((Object)turnBasedMatch2.getParticipants(), (Object)turnBasedMatch.getParticipants()) && Objects.equal((Object)turnBasedMatch2.getRematchId(), (Object)turnBasedMatch.getRematchId()) && Objects.equal((Object)turnBasedMatch2.getMatchNumber(), (Object)turnBasedMatch.getMatchNumber()) && com.google.android.gms.games.internal.zzc.zza(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && Objects.equal((Object)turnBasedMatch2.getAvailableAutoMatchSlots(), (Object)turnBasedMatch.getAvailableAutoMatchSlots()) && Objects.equal((Object)turnBasedMatch2.isLocallyModified(), (Object)turnBasedMatch.isLocallyModified())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final TurnBasedMatch turnBasedMatch) {
        return Objects.toStringHelper((Object)turnBasedMatch).add("Game", (Object)turnBasedMatch.getGame()).add("MatchId", (Object)turnBasedMatch.getMatchId()).add("CreatorId", (Object)turnBasedMatch.getCreatorId()).add("CreationTimestamp", (Object)turnBasedMatch.getCreationTimestamp()).add("LastUpdaterId", (Object)turnBasedMatch.getLastUpdaterId()).add("LastUpdatedTimestamp", (Object)turnBasedMatch.getLastUpdatedTimestamp()).add("PendingParticipantId", (Object)turnBasedMatch.getPendingParticipantId()).add("MatchStatus", (Object)turnBasedMatch.getStatus()).add("TurnStatus", (Object)turnBasedMatch.getTurnStatus()).add("Description", (Object)turnBasedMatch.getDescription()).add("Variant", (Object)turnBasedMatch.getVariant()).add("Data", (Object)turnBasedMatch.getData()).add("Version", (Object)turnBasedMatch.getVersion()).add("Participants", (Object)turnBasedMatch.getParticipants()).add("RematchId", (Object)turnBasedMatch.getRematchId()).add("PreviousData", (Object)turnBasedMatch.getPreviousMatchData()).add("MatchNumber", (Object)turnBasedMatch.getMatchNumber()).add("AutoMatchCriteria", (Object)turnBasedMatch.getAutoMatchCriteria()).add("AvailableAutoMatchSlots", (Object)turnBasedMatch.getAvailableAutoMatchSlots()).add("LocallyModified", (Object)turnBasedMatch.isLocallyModified()).add("DescriptionParticipantId", (Object)turnBasedMatch.getDescriptionParticipantId()).toString();
    }
    
    static String zzb(final TurnBasedMatch turnBasedMatch, final String s) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            final Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
    
    static Participant zzc(final TurnBasedMatch turnBasedMatch, final String s) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant;
            }
        }
        final String matchId = turnBasedMatch.getMatchId();
        throw new IllegalStateException(new StringBuilder(String.valueOf(s).length() + 29 + String.valueOf(matchId).length()).append("Participant ").append(s).append(" is not in match ").append(matchId).toString());
    }
    
    static ArrayList<String> zzc(final TurnBasedMatch turnBasedMatch) {
        final ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        final int size = participants.size();
        final ArrayList list = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            list.add(participants.get(i).getParticipantId());
        }
        return (ArrayList<String>)list;
    }
    
    @Override
    public final boolean canRematch() {
        return this.zzpn == 2 && this.zzpo == null;
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final TurnBasedMatch freeze() {
        return this;
    }
    
    @Nullable
    @Override
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }
    
    @Override
    public final int getAvailableAutoMatchSlots() {
        if (this.zzoz == null) {
            return 0;
        }
        return this.zzoz.getInt("max_automatch_players");
    }
    
    @Override
    public final long getCreationTimestamp() {
        return this.zzoa;
    }
    
    @Override
    public final String getCreatorId() {
        return this.zzpc;
    }
    
    @Override
    public final byte[] getData() {
        return this.data;
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
    public final Participant getDescriptionParticipant() {
        final String descriptionParticipantId = this.getDescriptionParticipantId();
        if (descriptionParticipantId == null) {
            return null;
        }
        return this.getParticipant(descriptionParticipantId);
    }
    
    @Override
    public final String getDescriptionParticipantId() {
        return this.zzpt;
    }
    
    @Override
    public final Game getGame() {
        return this.zzky;
    }
    
    @Override
    public final long getLastUpdatedTimestamp() {
        return this.zzfk;
    }
    
    @Override
    public final String getLastUpdaterId() {
        return this.zzpl;
    }
    
    @Override
    public final String getMatchId() {
        return this.zzhg;
    }
    
    @Override
    public final int getMatchNumber() {
        return this.zzpq;
    }
    
    @Override
    public final Participant getParticipant(final String s) {
        return zzc(this, s);
    }
    
    @Override
    public final String getParticipantId(final String s) {
        return zzb(this, s);
    }
    
    @Override
    public final ArrayList<String> getParticipantIds() {
        return zzc(this);
    }
    
    @Override
    public final int getParticipantStatus(final String s) {
        return zza(this, s);
    }
    
    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<Participant>(this.zzod);
    }
    
    @Override
    public final String getPendingParticipantId() {
        return this.zzpm;
    }
    
    @Override
    public final byte[] getPreviousMatchData() {
        return this.zzpp;
    }
    
    @Override
    public final String getRematchId() {
        return this.zzpo;
    }
    
    @Override
    public final int getStatus() {
        return this.zzpn;
    }
    
    @Override
    public final int getTurnStatus() {
        return this.zzpr;
    }
    
    @Override
    public final int getVariant() {
        return this.zzoe;
    }
    
    @Override
    public final int getVersion() {
        return this.version;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final boolean isLocallyModified() {
        return this.zzps;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getGame(), n, false);
        SafeParcelWriter.writeString(parcel, 2, this.getMatchId(), false);
        SafeParcelWriter.writeString(parcel, 3, this.getCreatorId(), false);
        SafeParcelWriter.writeLong(parcel, 4, this.getCreationTimestamp());
        SafeParcelWriter.writeString(parcel, 5, this.getLastUpdaterId(), false);
        SafeParcelWriter.writeLong(parcel, 6, this.getLastUpdatedTimestamp());
        SafeParcelWriter.writeString(parcel, 7, this.getPendingParticipantId(), false);
        SafeParcelWriter.writeInt(parcel, 8, this.getStatus());
        SafeParcelWriter.writeInt(parcel, 10, this.getVariant());
        SafeParcelWriter.writeInt(parcel, 11, this.getVersion());
        SafeParcelWriter.writeByteArray(parcel, 12, this.getData(), false);
        SafeParcelWriter.writeTypedList(parcel, 13, (List)this.getParticipants(), false);
        SafeParcelWriter.writeString(parcel, 14, this.getRematchId(), false);
        SafeParcelWriter.writeByteArray(parcel, 15, this.getPreviousMatchData(), false);
        SafeParcelWriter.writeInt(parcel, 16, this.getMatchNumber());
        SafeParcelWriter.writeBundle(parcel, 17, this.getAutoMatchCriteria(), false);
        SafeParcelWriter.writeInt(parcel, 18, this.getTurnStatus());
        SafeParcelWriter.writeBoolean(parcel, 19, this.isLocallyModified());
        SafeParcelWriter.writeString(parcel, 20, this.getDescription(), false);
        SafeParcelWriter.writeString(parcel, 21, this.getDescriptionParticipantId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
