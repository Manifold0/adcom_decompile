package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef extends DataBufferRef implements Participant {
    private final PlayerRef zzol;

    public ParticipantRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
        this.zzol = new PlayerRef(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return ParticipantEntity.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new ParticipantEntity(this);
    }

    public final int getCapabilities() {
        return getInteger("capabilities");
    }

    public final String getDisplayName() {
        return hasNull("external_player_id") ? getString("default_display_name") : this.zzol.getDisplayName();
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        if (hasNull("external_player_id")) {
            copyToBuffer("default_display_name", charArrayBuffer);
        } else {
            this.zzol.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getHiResImageUri() {
        return hasNull("external_player_id") ? parseUri("default_display_hi_res_image_uri") : this.zzol.getHiResImageUri();
    }

    public final String getHiResImageUrl() {
        return hasNull("external_player_id") ? getString("default_display_hi_res_image_url") : this.zzol.getHiResImageUrl();
    }

    public final Uri getIconImageUri() {
        return hasNull("external_player_id") ? parseUri("default_display_image_uri") : this.zzol.getIconImageUri();
    }

    public final String getIconImageUrl() {
        return hasNull("external_player_id") ? getString("default_display_image_url") : this.zzol.getIconImageUrl();
    }

    public final String getParticipantId() {
        return getString("external_participant_id");
    }

    public final Player getPlayer() {
        return hasNull("external_player_id") ? null : this.zzol;
    }

    public final ParticipantResult getResult() {
        if (hasNull("result_type")) {
            return null;
        }
        return new ParticipantResult(getParticipantId(), getInteger("result_type"), getInteger("placing"));
    }

    public final int getStatus() {
        return getInteger("player_status");
    }

    public final int hashCode() {
        return ParticipantEntity.zza((Participant) this);
    }

    public final boolean isConnectedToRoom() {
        return getInteger("connected") > 0;
    }

    public final String toString() {
        return ParticipantEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((ParticipantEntity) ((Participant) freeze())).writeToParcel(parcel, i);
    }

    public final String zzcg() {
        return getString("client_address");
    }
}
