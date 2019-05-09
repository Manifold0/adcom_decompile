// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import com.google.android.gms.games.Player;
import java.util.List;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

@Deprecated
public final class zzb extends DataBufferRef implements GameRequest
{
    private final int zzmz;
    
    public zzb(final DataHolder dataHolder, final int n, final int zzmz) {
        super(dataHolder, n);
        this.zzmz = zzmz;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return GameRequestEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new GameRequestEntity(this);
    }
    
    public final long getCreationTimestamp() {
        return this.getLong("creation_timestamp");
    }
    
    public final byte[] getData() {
        return this.getByteArray("data");
    }
    
    public final long getExpirationTimestamp() {
        return this.getLong("expiration_timestamp");
    }
    
    public final Game getGame() {
        return new GameRef(this.mDataHolder, this.mDataRow);
    }
    
    public final int getRecipientStatus(final String s) {
        for (int i = this.mDataRow; i < this.mDataRow + this.zzmz; ++i) {
            final int windowIndex = this.mDataHolder.getWindowIndex(i);
            if (this.mDataHolder.getString("recipient_external_player_id", i, windowIndex).equals(s)) {
                return this.mDataHolder.getInteger("recipient_status", i, windowIndex);
            }
        }
        return -1;
    }
    
    public final List<Player> getRecipients() {
        final ArrayList<PlayerRef> list = (ArrayList<PlayerRef>)new ArrayList<Player>(this.zzmz);
        for (int i = 0; i < this.zzmz; ++i) {
            list.add(new PlayerRef(this.mDataHolder, this.mDataRow + i, "recipient_"));
        }
        return (List<Player>)list;
    }
    
    public final String getRequestId() {
        return this.getString("external_request_id");
    }
    
    public final Player getSender() {
        return new PlayerRef(this.mDataHolder, this.getDataRow(), "sender_");
    }
    
    public final int getStatus() {
        return this.getInteger("status");
    }
    
    public final int getType() {
        return this.getInteger("type");
    }
    
    public final int hashCode() {
        return GameRequestEntity.zza(this);
    }
    
    public final boolean isConsumed(final String s) {
        return this.getRecipientStatus(s) == 1;
    }
    
    public final String toString() {
        return GameRequestEntity.zzc(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((GameRequestEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
