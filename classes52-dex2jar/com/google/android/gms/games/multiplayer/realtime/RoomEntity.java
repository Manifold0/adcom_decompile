// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.games.multiplayer.Participant;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

@SafeParcelable$Class(creator = "RoomEntityCreator")
@SafeParcelable$Reserved({ 1000 })
@RetainForClient
public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room
{
    public static final Parcelable$Creator<RoomEntity> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 5)
    private final String description;
    @SafeParcelable$Field(getter = "getRoomId", id = 1)
    private final String zzgt;
    @SafeParcelable$Field(getter = "getCreationTimestamp", id = 3)
    private final long zzoa;
    @SafeParcelable$Field(getter = "getParticipants", id = 8)
    private final ArrayList<ParticipantEntity> zzod;
    @SafeParcelable$Field(getter = "getVariant", id = 6)
    private final int zzoe;
    @Nullable
    @SafeParcelable$Field(getter = "getAutoMatchCriteria", id = 7)
    private final Bundle zzoz;
    @SafeParcelable$Field(getter = "getCreatorId", id = 2)
    private final String zzpc;
    @SafeParcelable$Field(getter = "getStatus", id = 4)
    private final int zzpd;
    @SafeParcelable$Field(getter = "getAutoMatchWaitEstimateSeconds", id = 9)
    private final int zzpe;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public RoomEntity(final Room room) {
        this.zzgt = room.getRoomId();
        this.zzpc = room.getCreatorId();
        this.zzoa = room.getCreationTimestamp();
        this.zzpd = room.getStatus();
        this.description = room.getDescription();
        this.zzoe = room.getVariant();
        this.zzoz = room.getAutoMatchCriteria();
        final ArrayList<Participant> participants = room.getParticipants();
        final int size = participants.size();
        this.zzod = new ArrayList<ParticipantEntity>(size);
        for (int i = 0; i < size; ++i) {
            this.zzod.add((ParticipantEntity)participants.get(i).freeze());
        }
        this.zzpe = room.getAutoMatchWaitEstimateSeconds();
    }
    
    @SafeParcelable$Constructor
    RoomEntity(@SafeParcelable$Param(id = 1) final String zzgt, @SafeParcelable$Param(id = 2) final String zzpc, @SafeParcelable$Param(id = 3) final long zzoa, @SafeParcelable$Param(id = 4) final int zzpd, @SafeParcelable$Param(id = 5) final String description, @SafeParcelable$Param(id = 6) final int zzoe, @Nullable @SafeParcelable$Param(id = 7) final Bundle zzoz, @SafeParcelable$Param(id = 8) final ArrayList<ParticipantEntity> zzod, @SafeParcelable$Param(id = 9) final int zzpe) {
        this.zzgt = zzgt;
        this.zzpc = zzpc;
        this.zzoa = zzoa;
        this.zzpd = zzpd;
        this.description = description;
        this.zzoe = zzoe;
        this.zzoz = zzoz;
        this.zzod = zzod;
        this.zzpe = zzpe;
    }
    
    static int zza(final Room room) {
        return Objects.hashCode(new Object[] { room.getRoomId(), room.getCreatorId(), room.getCreationTimestamp(), room.getStatus(), room.getDescription(), room.getVariant(), zzc.zza(room.getAutoMatchCriteria()), room.getParticipants(), room.getAutoMatchWaitEstimateSeconds() });
    }
    
    static int zza(final Room room, final String s) {
        final ArrayList<Participant> participants = room.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant.getStatus();
            }
        }
        final String roomId = room.getRoomId();
        throw new IllegalStateException(new StringBuilder(String.valueOf(s).length() + 28 + String.valueOf(roomId).length()).append("Participant ").append(s).append(" is not in room ").append(roomId).toString());
    }
    
    static boolean zza(final Room room, final Object o) {
        if (o instanceof Room) {
            if (room == o) {
                return true;
            }
            final Room room2 = (Room)o;
            if (Objects.equal((Object)room2.getRoomId(), (Object)room.getRoomId()) && Objects.equal((Object)room2.getCreatorId(), (Object)room.getCreatorId()) && Objects.equal((Object)room2.getCreationTimestamp(), (Object)room.getCreationTimestamp()) && Objects.equal((Object)room2.getStatus(), (Object)room.getStatus()) && Objects.equal((Object)room2.getDescription(), (Object)room.getDescription()) && Objects.equal((Object)room2.getVariant(), (Object)room.getVariant()) && zzc.zza(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && Objects.equal((Object)room2.getParticipants(), (Object)room.getParticipants()) && Objects.equal((Object)room2.getAutoMatchWaitEstimateSeconds(), (Object)room.getAutoMatchWaitEstimateSeconds())) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ boolean zza(final String s) {
        return canUnparcelSafely(s);
    }
    
    static String zzb(final Room room) {
        return Objects.toStringHelper((Object)room).add("RoomId", (Object)room.getRoomId()).add("CreatorId", (Object)room.getCreatorId()).add("CreationTimestamp", (Object)room.getCreationTimestamp()).add("RoomStatus", (Object)room.getStatus()).add("Description", (Object)room.getDescription()).add("Variant", (Object)room.getVariant()).add("AutoMatchCriteria", (Object)room.getAutoMatchCriteria()).add("Participants", (Object)room.getParticipants()).add("AutoMatchWaitEstimateSeconds", (Object)room.getAutoMatchWaitEstimateSeconds()).toString();
    }
    
    static String zzb(final Room room, final String s) {
        final ArrayList<Participant> participants = room.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            final Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
    
    static Participant zzc(final Room room, final String s) {
        final ArrayList<Participant> participants = room.getParticipants();
        for (int size = participants.size(), i = 0; i < size; ++i) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(s)) {
                return participant;
            }
        }
        final String roomId = room.getRoomId();
        throw new IllegalStateException(new StringBuilder(String.valueOf(s).length() + 29 + String.valueOf(roomId).length()).append("Participant ").append(s).append(" is not in match ").append(roomId).toString());
    }
    
    static ArrayList<String> zzc(final Room room) {
        final ArrayList<Participant> participants = room.getParticipants();
        final int size = participants.size();
        final ArrayList list = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            list.add(participants.get(i).getParticipantId());
        }
        return (ArrayList<String>)list;
    }
    
    static /* synthetic */ Integer zze() {
        return getUnparcelClientVersion();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Room freeze() {
        return this;
    }
    
    @Nullable
    @Override
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }
    
    @Override
    public final int getAutoMatchWaitEstimateSeconds() {
        return this.zzpe;
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
    public final String getDescription() {
        return this.description;
    }
    
    @Override
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
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
    public final String getRoomId() {
        return this.zzgt;
    }
    
    @Override
    public final int getStatus() {
        return this.zzpd;
    }
    
    @Override
    public final int getVariant() {
        return this.zzoe;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final void setShouldDowngrade(final boolean b) {
        super.setShouldDowngrade(b);
        for (int size = this.zzod.size(), i = 0; i < size; ++i) {
            this.zzod.get(i).setShouldDowngrade(b);
        }
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        if (!this.shouldDowngrade()) {
            beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, this.getRoomId(), false);
            SafeParcelWriter.writeString(parcel, 2, this.getCreatorId(), false);
            SafeParcelWriter.writeLong(parcel, 3, this.getCreationTimestamp());
            SafeParcelWriter.writeInt(parcel, 4, this.getStatus());
            SafeParcelWriter.writeString(parcel, 5, this.getDescription(), false);
            SafeParcelWriter.writeInt(parcel, 6, this.getVariant());
            SafeParcelWriter.writeBundle(parcel, 7, this.getAutoMatchCriteria(), false);
            SafeParcelWriter.writeTypedList(parcel, 8, (List)this.getParticipants(), false);
            SafeParcelWriter.writeInt(parcel, 9, this.getAutoMatchWaitEstimateSeconds());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
        else {
            parcel.writeString(this.zzgt);
            parcel.writeString(this.zzpc);
            parcel.writeLong(this.zzoa);
            parcel.writeInt(this.zzpd);
            parcel.writeString(this.description);
            parcel.writeInt(this.zzoe);
            parcel.writeBundle(this.zzoz);
            final int size = this.zzod.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.zzod.get(i).writeToParcel(parcel, beginObjectHeader);
            }
        }
    }
    
    static final class zza extends zze
    {
        @Override
        public final RoomEntity zzf(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(RoomEntity.zze()) || RoomEntity.zza(RoomEntity.class.getCanonicalName())) {
                return super.zzf(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final long long1 = parcel.readLong();
            final int int1 = parcel.readInt();
            final String string3 = parcel.readString();
            final int int2 = parcel.readInt();
            final Bundle bundle = parcel.readBundle();
            final int int3 = parcel.readInt();
            final ArrayList list = new ArrayList<ParticipantEntity>(int3);
            for (int i = 0; i < int3; ++i) {
                list.add((ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(string, string2, long1, int1, string3, int2, bundle, (ArrayList<ParticipantEntity>)list, -1);
        }
    }
}
