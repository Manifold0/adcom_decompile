// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Parcel;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Participant;
import android.database.CharArrayBuffer;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzd extends DataBufferRef implements TurnBasedMatch
{
    private final Game zzmy;
    private final int zzmz;
    
    zzd(final DataHolder dataHolder, final int n, final int zzmz) {
        super(dataHolder, n);
        this.zzmy = new GameRef(dataHolder, n);
        this.zzmz = zzmz;
    }
    
    public final boolean canRematch() {
        return this.getTurnStatus() == 3 && this.getRematchId() == null && this.getParticipants().size() > 1;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return TurnBasedMatchEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new TurnBasedMatchEntity(this);
    }
    
    @Nullable
    public final Bundle getAutoMatchCriteria() {
        if (!this.getBoolean("has_automatch_criteria")) {
            return null;
        }
        return TurnBasedMatchConfig.createAutoMatchCriteria(this.getInteger("automatch_min_players"), this.getInteger("automatch_max_players"), this.getLong("automatch_bit_mask"));
    }
    
    public final int getAvailableAutoMatchSlots() {
        if (!this.getBoolean("has_automatch_criteria")) {
            return 0;
        }
        return this.getInteger("automatch_max_players");
    }
    
    public final long getCreationTimestamp() {
        return this.getLong("creation_timestamp");
    }
    
    public final String getCreatorId() {
        return this.getString("creator_external");
    }
    
    public final byte[] getData() {
        return this.getByteArray("data");
    }
    
    public final String getDescription() {
        return this.getString("description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("description", charArrayBuffer);
    }
    
    public final Participant getDescriptionParticipant() {
        final String descriptionParticipantId = this.getDescriptionParticipantId();
        if (descriptionParticipantId == null) {
            return null;
        }
        return this.getParticipant(descriptionParticipantId);
    }
    
    public final String getDescriptionParticipantId() {
        return this.getString("description_participant_id");
    }
    
    public final Game getGame() {
        return this.zzmy;
    }
    
    public final long getLastUpdatedTimestamp() {
        return this.getLong("last_updated_timestamp");
    }
    
    public final String getLastUpdaterId() {
        return this.getString("last_updater_external");
    }
    
    public final String getMatchId() {
        return this.getString("external_match_id");
    }
    
    public final int getMatchNumber() {
        return this.getInteger("match_number");
    }
    
    public final Participant getParticipant(final String s) {
        return TurnBasedMatchEntity.zzc(this, s);
    }
    
    public final String getParticipantId(final String s) {
        return TurnBasedMatchEntity.zzb(this, s);
    }
    
    public final ArrayList<String> getParticipantIds() {
        return TurnBasedMatchEntity.zzc(this);
    }
    
    public final int getParticipantStatus(final String s) {
        return TurnBasedMatchEntity.zza(this, s);
    }
    
    public final ArrayList<Participant> getParticipants() {
        final ArrayList<ParticipantRef> list = (ArrayList<ParticipantRef>)new ArrayList<Participant>(this.zzmz);
        for (int i = 0; i < this.zzmz; ++i) {
            list.add(new ParticipantRef(this.mDataHolder, this.mDataRow + i));
        }
        return (ArrayList<Participant>)list;
    }
    
    public final String getPendingParticipantId() {
        return this.getString("pending_participant_external");
    }
    
    public final byte[] getPreviousMatchData() {
        return this.getByteArray("previous_match_data");
    }
    
    public final String getRematchId() {
        return this.getString("rematch_id");
    }
    
    public final int getStatus() {
        return this.getInteger("status");
    }
    
    public final int getTurnStatus() {
        return this.getInteger("user_match_status");
    }
    
    public final int getVariant() {
        return this.getInteger("variant");
    }
    
    public final int getVersion() {
        return this.getInteger("version");
    }
    
    public final int hashCode() {
        return TurnBasedMatchEntity.zza(this);
    }
    
    public final boolean isLocallyModified() {
        return this.getBoolean("upsync_required");
    }
    
    public final String toString() {
        return TurnBasedMatchEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((TurnBasedMatchEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
