// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzb extends DataBufferRef implements Invitation
{
    private final Game zzmy;
    private final ArrayList<Participant> zzod;
    private final ParticipantRef zzog;
    
    zzb(final DataHolder dataHolder, int i, final int n) {
        super(dataHolder, i);
        this.zzmy = new GameRef(dataHolder, i);
        this.zzod = new ArrayList<Participant>(n);
        final String string = this.getString("external_inviter_id");
        i = 0;
        Object o = null;
        while (i < n) {
            final ParticipantRef participantRef = new ParticipantRef(this.mDataHolder, this.mDataRow + i);
            if (participantRef.getParticipantId().equals(string)) {
                o = participantRef;
            }
            this.zzod.add(participantRef);
            ++i;
        }
        this.zzog = (ParticipantRef)Preconditions.checkNotNull(o, (Object)"Must have a valid inviter!");
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return InvitationEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new InvitationEntity(this);
    }
    
    public final int getAvailableAutoMatchSlots() {
        if (!this.getBoolean("has_automatch_criteria")) {
            return 0;
        }
        return this.getInteger("automatch_max_players");
    }
    
    public final long getCreationTimestamp() {
        return Math.max(this.getLong("creation_timestamp"), this.getLong("last_modified_timestamp"));
    }
    
    public final Game getGame() {
        return this.zzmy;
    }
    
    public final String getInvitationId() {
        return this.getString("external_invitation_id");
    }
    
    public final int getInvitationType() {
        return this.getInteger("type");
    }
    
    public final Participant getInviter() {
        return this.zzog;
    }
    
    public final ArrayList<Participant> getParticipants() {
        return this.zzod;
    }
    
    public final int getVariant() {
        return this.getInteger("variant");
    }
    
    public final int hashCode() {
        return InvitationEntity.zza(this);
    }
    
    public final String toString() {
        return InvitationEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((InvitationEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
