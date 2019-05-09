package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;

@RetainForClient
@Class(creator = "InvitationEntityCreator")
@Reserved({1000})
public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
    public static final Creator<InvitationEntity> CREATOR = new zza();
    @Field(getter = "getInvitationId", id = 2)
    private final String zzgr;
    @Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @Field(getter = "getCreationTimestamp", id = 3)
    private final long zzoa;
    @Field(getter = "getInvitationType", id = 4)
    private final int zzob;
    @Field(getter = "getInviter", id = 5)
    private final ParticipantEntity zzoc;
    @Field(getter = "getParticipants", id = 6)
    private final ArrayList<ParticipantEntity> zzod;
    @Field(getter = "getVariant", id = 7)
    private final int zzoe;
    @Field(getter = "getAvailableAutoMatchSlots", id = 8)
    private final int zzof;

    static final class zza extends zza {
        zza() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zzd(parcel);
        }

        public final InvitationEntity zzd(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(InvitationEntity.getUnparcelClientVersion()) || InvitationEntity.canUnparcelSafely(InvitationEntity.class.getCanonicalName())) {
                return super.zzd(parcel);
            }
            GameEntity gameEntity = (GameEntity) GameEntity.CREATOR.createFromParcel(parcel);
            String readString = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            ParticipantEntity participantEntity = (ParticipantEntity) ParticipantEntity.CREATOR.createFromParcel(parcel);
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i < readInt2; i++) {
                arrayList.add((ParticipantEntity) ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(gameEntity, readString, readLong, readInt, participantEntity, arrayList, -1, 0);
        }
    }

    @Constructor
    InvitationEntity(@Param(id = 1) GameEntity gameEntity, @Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) int i, @Param(id = 5) ParticipantEntity participantEntity, @Param(id = 6) ArrayList<ParticipantEntity> arrayList, @Param(id = 7) int i2, @Param(id = 8) int i3) {
        this.zzky = gameEntity;
        this.zzgr = str;
        this.zzoa = j;
        this.zzob = i;
        this.zzoc = participantEntity;
        this.zzod = arrayList;
        this.zzoe = i2;
        this.zzof = i3;
    }

    InvitationEntity(Invitation invitation) {
        this.zzky = new GameEntity(invitation.getGame());
        this.zzgr = invitation.getInvitationId();
        this.zzoa = invitation.getCreationTimestamp();
        this.zzob = invitation.getInvitationType();
        this.zzoe = invitation.getVariant();
        this.zzof = invitation.getAvailableAutoMatchSlots();
        String participantId = invitation.getInviter().getParticipantId();
        Participant participant = null;
        ArrayList participants = invitation.getParticipants();
        int size = participants.size();
        this.zzod = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Participant participant2 = (Participant) participants.get(i);
            if (participant2.getParticipantId().equals(participantId)) {
                participant = participant2;
            }
            this.zzod.add((ParticipantEntity) participant2.freeze());
        }
        Preconditions.checkNotNull(participant, "Must have a valid inviter!");
        this.zzoc = (ParticipantEntity) participant.freeze();
    }

    static int zza(Invitation invitation) {
        return Objects.hashCode(new Object[]{invitation.getGame(), invitation.getInvitationId(), Long.valueOf(invitation.getCreationTimestamp()), Integer.valueOf(invitation.getInvitationType()), invitation.getInviter(), invitation.getParticipants(), Integer.valueOf(invitation.getVariant()), Integer.valueOf(invitation.getAvailableAutoMatchSlots())});
    }

    static boolean zza(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return Objects.equal(invitation2.getGame(), invitation.getGame()) && Objects.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && Objects.equal(Long.valueOf(invitation2.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) && Objects.equal(Integer.valueOf(invitation2.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) && Objects.equal(invitation2.getInviter(), invitation.getInviter()) && Objects.equal(invitation2.getParticipants(), invitation.getParticipants()) && Objects.equal(Integer.valueOf(invitation2.getVariant()), Integer.valueOf(invitation.getVariant())) && Objects.equal(Integer.valueOf(invitation2.getAvailableAutoMatchSlots()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static String zzb(Invitation invitation) {
        return Objects.toStringHelper(invitation).add("Game", invitation.getGame()).add("InvitationId", invitation.getInvitationId()).add("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).add("InvitationType", Integer.valueOf(invitation.getInvitationType())).add("Inviter", invitation.getInviter()).add("Participants", invitation.getParticipants()).add("Variant", Integer.valueOf(invitation.getVariant())).add("AvailableAutoMatchSlots", Integer.valueOf(invitation.getAvailableAutoMatchSlots())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Invitation freeze() {
        return this;
    }

    public final int getAvailableAutoMatchSlots() {
        return this.zzof;
    }

    public final long getCreationTimestamp() {
        return this.zzoa;
    }

    public final Game getGame() {
        return this.zzky;
    }

    public final String getInvitationId() {
        return this.zzgr;
    }

    public final int getInvitationType() {
        return this.zzob;
    }

    public final Participant getInviter() {
        return this.zzoc;
    }

    public final ArrayList<Participant> getParticipants() {
        return new ArrayList(this.zzod);
    }

    public final int getVariant() {
        return this.zzoe;
    }

    public final int hashCode() {
        return zza((Invitation) this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final void setShouldDowngrade(boolean z) {
        super.setShouldDowngrade(z);
        this.zzky.setShouldDowngrade(z);
        this.zzoc.setShouldDowngrade(z);
        int size = this.zzod.size();
        for (int i = 0; i < size; i++) {
            ((ParticipantEntity) this.zzod.get(i)).setShouldDowngrade(z);
        }
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        if (shouldDowngrade()) {
            this.zzky.writeToParcel(parcel, i);
            parcel.writeString(this.zzgr);
            parcel.writeLong(this.zzoa);
            parcel.writeInt(this.zzob);
            this.zzoc.writeToParcel(parcel, i);
            int size = this.zzod.size();
            parcel.writeInt(size);
            for (i2 = 0; i2 < size; i2++) {
                ((ParticipantEntity) this.zzod.get(i2)).writeToParcel(parcel, i);
            }
            return;
        }
        i2 = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getInvitationId(), false);
        SafeParcelWriter.writeLong(parcel, 3, getCreationTimestamp());
        SafeParcelWriter.writeInt(parcel, 4, getInvitationType());
        SafeParcelWriter.writeParcelable(parcel, 5, getInviter(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 6, getParticipants(), false);
        SafeParcelWriter.writeInt(parcel, 7, getVariant());
        SafeParcelWriter.writeInt(parcel, 8, getAvailableAutoMatchSlots());
        SafeParcelWriter.finishObjectHeader(parcel, i2);
    }
}
