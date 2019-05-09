package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class zzb extends DataBufferRef implements GameRequest {
    private final int zzmz;

    public zzb(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.zzmz = i2;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return GameRequestEntity.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new GameRequestEntity(this);
    }

    public final long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    public final byte[] getData() {
        return getByteArray("data");
    }

    public final long getExpirationTimestamp() {
        return getLong("expiration_timestamp");
    }

    public final Game getGame() {
        return new GameRef(this.mDataHolder, this.mDataRow);
    }

    public final int getRecipientStatus(String str) {
        for (int i = this.mDataRow; i < this.mDataRow + this.zzmz; i++) {
            int windowIndex = this.mDataHolder.getWindowIndex(i);
            if (this.mDataHolder.getString("recipient_external_player_id", i, windowIndex).equals(str)) {
                return this.mDataHolder.getInteger("recipient_status", i, windowIndex);
            }
        }
        return -1;
    }

    public final List<Player> getRecipients() {
        List arrayList = new ArrayList(this.zzmz);
        for (int i = 0; i < this.zzmz; i++) {
            arrayList.add(new PlayerRef(this.mDataHolder, this.mDataRow + i, "recipient_"));
        }
        return arrayList;
    }

    public final String getRequestId() {
        return getString("external_request_id");
    }

    public final Player getSender() {
        return new PlayerRef(this.mDataHolder, getDataRow(), "sender_");
    }

    public final int getStatus() {
        return getInteger("status");
    }

    public final int getType() {
        return getInteger("type");
    }

    public final int hashCode() {
        return GameRequestEntity.zza(this);
    }

    public final boolean isConsumed(String str) {
        return getRecipientStatus(str) == 1;
    }

    public final String toString() {
        return GameRequestEntity.zzc(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((GameRequestEntity) ((GameRequest) freeze())).writeToParcel(parcel, i);
    }
}
