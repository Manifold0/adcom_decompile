// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Participant;
import android.database.CharArrayBuffer;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class zzf extends DataBufferRef implements Room
{
    private final int zzmz;
    
    zzf(final DataHolder dataHolder, final int n, final int zzmz) {
        super(dataHolder, n);
        this.zzmz = zzmz;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return RoomEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new RoomEntity(this);
    }
    
    @Nullable
    public final Bundle getAutoMatchCriteria() {
        if (!this.getBoolean("has_automatch_criteria")) {
            return null;
        }
        return RoomConfig.createAutoMatchCriteria(this.getInteger("automatch_min_players"), this.getInteger("automatch_max_players"), this.getLong("automatch_bit_mask"));
    }
    
    public final int getAutoMatchWaitEstimateSeconds() {
        return this.getInteger("automatch_wait_estimate_sec");
    }
    
    public final long getCreationTimestamp() {
        return this.getLong("creation_timestamp");
    }
    
    public final String getCreatorId() {
        return this.getString("creator_external");
    }
    
    public final String getDescription() {
        return this.getString("description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("description", charArrayBuffer);
    }
    
    public final Participant getParticipant(final String s) {
        return RoomEntity.zzc(this, s);
    }
    
    public final String getParticipantId(final String s) {
        return RoomEntity.zzb(this, s);
    }
    
    public final ArrayList<String> getParticipantIds() {
        return RoomEntity.zzc(this);
    }
    
    public final int getParticipantStatus(final String s) {
        return RoomEntity.zza(this, s);
    }
    
    public final ArrayList<Participant> getParticipants() {
        final ArrayList<ParticipantRef> list = (ArrayList<ParticipantRef>)new ArrayList<Participant>(this.zzmz);
        for (int i = 0; i < this.zzmz; ++i) {
            list.add(new ParticipantRef(this.mDataHolder, this.mDataRow + i));
        }
        return (ArrayList<Participant>)list;
    }
    
    public final String getRoomId() {
        return this.getString("external_match_id");
    }
    
    public final int getStatus() {
        return this.getInteger("status");
    }
    
    public final int getVariant() {
        return this.getInteger("variant");
    }
    
    public final int hashCode() {
        return RoomEntity.zza(this);
    }
    
    public final String toString() {
        return RoomEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((RoomEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
