// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.games.Player;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.common.data.DataBufferRef;

public final class ParticipantRef extends DataBufferRef implements Participant
{
    private final PlayerRef zzol;
    
    public ParticipantRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.zzol = new PlayerRef(dataHolder, n);
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return ParticipantEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new ParticipantEntity(this);
    }
    
    public final int getCapabilities() {
        return this.getInteger("capabilities");
    }
    
    public final String getDisplayName() {
        if (this.hasNull("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.zzol.getDisplayName();
    }
    
    public final void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        if (this.hasNull("external_player_id")) {
            this.copyToBuffer("default_display_name", charArrayBuffer);
            return;
        }
        this.zzol.getDisplayName(charArrayBuffer);
    }
    
    public final Uri getHiResImageUri() {
        if (this.hasNull("external_player_id")) {
            return this.parseUri("default_display_hi_res_image_uri");
        }
        return this.zzol.getHiResImageUri();
    }
    
    public final String getHiResImageUrl() {
        if (this.hasNull("external_player_id")) {
            return this.getString("default_display_hi_res_image_url");
        }
        return this.zzol.getHiResImageUrl();
    }
    
    public final Uri getIconImageUri() {
        if (this.hasNull("external_player_id")) {
            return this.parseUri("default_display_image_uri");
        }
        return this.zzol.getIconImageUri();
    }
    
    public final String getIconImageUrl() {
        if (this.hasNull("external_player_id")) {
            return this.getString("default_display_image_url");
        }
        return this.zzol.getIconImageUrl();
    }
    
    public final String getParticipantId() {
        return this.getString("external_participant_id");
    }
    
    public final Player getPlayer() {
        if (this.hasNull("external_player_id")) {
            return null;
        }
        return this.zzol;
    }
    
    public final ParticipantResult getResult() {
        if (this.hasNull("result_type")) {
            return null;
        }
        return new ParticipantResult(this.getParticipantId(), this.getInteger("result_type"), this.getInteger("placing"));
    }
    
    public final int getStatus() {
        return this.getInteger("player_status");
    }
    
    public final int hashCode() {
        return ParticipantEntity.zza(this);
    }
    
    public final boolean isConnectedToRoom() {
        return this.getInteger("connected") > 0;
    }
    
    public final String toString() {
        return ParticipantEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((ParticipantEntity)this.freeze()).writeToParcel(parcel, n);
    }
    
    public final String zzcg() {
        return this.getString("client_address");
    }
}
