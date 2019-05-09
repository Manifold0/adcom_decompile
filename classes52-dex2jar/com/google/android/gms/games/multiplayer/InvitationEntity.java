// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

@SafeParcelable$Class(creator = "InvitationEntityCreator")
@SafeParcelable$Reserved({ 1000 })
@RetainForClient
public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation
{
    public static final Parcelable$Creator<InvitationEntity> CREATOR;
    @SafeParcelable$Field(getter = "getInvitationId", id = 2)
    private final String zzgr;
    @SafeParcelable$Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @SafeParcelable$Field(getter = "getCreationTimestamp", id = 3)
    private final long zzoa;
    @SafeParcelable$Field(getter = "getInvitationType", id = 4)
    private final int zzob;
    @SafeParcelable$Field(getter = "getInviter", id = 5)
    private final ParticipantEntity zzoc;
    @SafeParcelable$Field(getter = "getParticipants", id = 6)
    private final ArrayList<ParticipantEntity> zzod;
    @SafeParcelable$Field(getter = "getVariant", id = 7)
    private final int zzoe;
    @SafeParcelable$Field(getter = "getAvailableAutoMatchSlots", id = 8)
    private final int zzof;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    InvitationEntity(@SafeParcelable$Param(id = 1) final GameEntity zzky, @SafeParcelable$Param(id = 2) final String zzgr, @SafeParcelable$Param(id = 3) final long zzoa, @SafeParcelable$Param(id = 4) final int zzob, @SafeParcelable$Param(id = 5) final ParticipantEntity zzoc, @SafeParcelable$Param(id = 6) final ArrayList<ParticipantEntity> zzod, @SafeParcelable$Param(id = 7) final int zzoe, @SafeParcelable$Param(id = 8) final int zzof) {
        this.zzky = zzky;
        this.zzgr = zzgr;
        this.zzoa = zzoa;
        this.zzob = zzob;
        this.zzoc = zzoc;
        this.zzod = zzod;
        this.zzoe = zzoe;
        this.zzof = zzof;
    }
    
    InvitationEntity(final Invitation invitation) {
        this.zzky = new GameEntity(invitation.getGame());
        this.zzgr = invitation.getInvitationId();
        this.zzoa = invitation.getCreationTimestamp();
        this.zzob = invitation.getInvitationType();
        this.zzoe = invitation.getVariant();
        this.zzof = invitation.getAvailableAutoMatchSlots();
        final String participantId = invitation.getInviter().getParticipantId();
        final Participant participant = null;
        final ArrayList<Participant> participants = invitation.getParticipants();
        final int size = participants.size();
        this.zzod = new ArrayList<ParticipantEntity>(size);
        int i = 0;
        Participant participant2 = participant;
        while (i < size) {
            final Participant participant3 = participants.get(i);
            if (participant3.getParticipantId().equals(participantId)) {
                participant2 = participant3;
            }
            this.zzod.add((ParticipantEntity)participant3.freeze());
            ++i;
        }
        Preconditions.checkNotNull((Object)participant2, (Object)"Must have a valid inviter!");
        this.zzoc = (ParticipantEntity)participant2.freeze();
    }
    
    static int zza(final Invitation invitation) {
        return Objects.hashCode(new Object[] { invitation.getGame(), invitation.getInvitationId(), invitation.getCreationTimestamp(), invitation.getInvitationType(), invitation.getInviter(), invitation.getParticipants(), invitation.getVariant(), invitation.getAvailableAutoMatchSlots() });
    }
    
    static boolean zza(final Invitation invitation, final Object o) {
        if (o instanceof Invitation) {
            if (invitation == o) {
                return true;
            }
            final Invitation invitation2 = (Invitation)o;
            if (Objects.equal((Object)invitation2.getGame(), (Object)invitation.getGame()) && Objects.equal((Object)invitation2.getInvitationId(), (Object)invitation.getInvitationId()) && Objects.equal((Object)invitation2.getCreationTimestamp(), (Object)invitation.getCreationTimestamp()) && Objects.equal((Object)invitation2.getInvitationType(), (Object)invitation.getInvitationType()) && Objects.equal((Object)invitation2.getInviter(), (Object)invitation.getInviter()) && Objects.equal((Object)invitation2.getParticipants(), (Object)invitation.getParticipants()) && Objects.equal((Object)invitation2.getVariant(), (Object)invitation.getVariant()) && Objects.equal((Object)invitation2.getAvailableAutoMatchSlots(), (Object)invitation.getAvailableAutoMatchSlots())) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ boolean zza(final String s) {
        return canUnparcelSafely(s);
    }
    
    static String zzb(final Invitation invitation) {
        return Objects.toStringHelper((Object)invitation).add("Game", (Object)invitation.getGame()).add("InvitationId", (Object)invitation.getInvitationId()).add("CreationTimestamp", (Object)invitation.getCreationTimestamp()).add("InvitationType", (Object)invitation.getInvitationType()).add("Inviter", (Object)invitation.getInviter()).add("Participants", (Object)invitation.getParticipants()).add("Variant", (Object)invitation.getVariant()).add("AvailableAutoMatchSlots", (Object)invitation.getAvailableAutoMatchSlots()).toString();
    }
    
    static /* synthetic */ Integer zze() {
        return getUnparcelClientVersion();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Invitation freeze() {
        return this;
    }
    
    @Override
    public final int getAvailableAutoMatchSlots() {
        return this.zzof;
    }
    
    @Override
    public final long getCreationTimestamp() {
        return this.zzoa;
    }
    
    @Override
    public final Game getGame() {
        return this.zzky;
    }
    
    @Override
    public final String getInvitationId() {
        return this.zzgr;
    }
    
    @Override
    public final int getInvitationType() {
        return this.zzob;
    }
    
    @Override
    public final Participant getInviter() {
        return this.zzoc;
    }
    
    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<Participant>(this.zzod);
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
        this.zzky.setShouldDowngrade(b);
        this.zzoc.setShouldDowngrade(b);
        for (int size = this.zzod.size(), i = 0; i < size; ++i) {
            this.zzod.get(i).setShouldDowngrade(b);
        }
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        if (!this.shouldDowngrade()) {
            final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getGame(), n, false);
            SafeParcelWriter.writeString(parcel, 2, this.getInvitationId(), false);
            SafeParcelWriter.writeLong(parcel, 3, this.getCreationTimestamp());
            SafeParcelWriter.writeInt(parcel, 4, this.getInvitationType());
            SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.getInviter(), n, false);
            SafeParcelWriter.writeTypedList(parcel, 6, (List)this.getParticipants(), false);
            SafeParcelWriter.writeInt(parcel, 7, this.getVariant());
            SafeParcelWriter.writeInt(parcel, 8, this.getAvailableAutoMatchSlots());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
        else {
            this.zzky.writeToParcel(parcel, n);
            parcel.writeString(this.zzgr);
            parcel.writeLong(this.zzoa);
            parcel.writeInt(this.zzob);
            this.zzoc.writeToParcel(parcel, n);
            final int size = this.zzod.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.zzod.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class zza extends com.google.android.gms.games.multiplayer.zza
    {
        @Override
        public final InvitationEntity zzd(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(InvitationEntity.zze()) || InvitationEntity.zza(InvitationEntity.class.getCanonicalName())) {
                return super.zzd(parcel);
            }
            final GameEntity gameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(parcel);
            final String string = parcel.readString();
            final long long1 = parcel.readLong();
            final int int1 = parcel.readInt();
            final ParticipantEntity participantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(parcel);
            final int int2 = parcel.readInt();
            final ArrayList list = new ArrayList<ParticipantEntity>(int2);
            for (int i = 0; i < int2; ++i) {
                list.add((ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(gameEntity, string, long1, int1, participantEntity, (ArrayList<ParticipantEntity>)list, -1, 0);
        }
    }
}
