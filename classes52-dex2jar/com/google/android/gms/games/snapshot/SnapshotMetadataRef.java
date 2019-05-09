// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataBufferRef;

public final class SnapshotMetadataRef extends DataBufferRef implements SnapshotMetadata
{
    private final Game zzmy;
    private final Player zzrd;
    
    public SnapshotMetadataRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
        this.zzmy = new GameRef(dataHolder, n);
        this.zzrd = new PlayerRef(dataHolder, n);
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return SnapshotMetadataEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new SnapshotMetadataEntity(this);
    }
    
    public final float getCoverImageAspectRatio() {
        final float float1 = this.getFloat("cover_icon_image_height");
        final float float2 = this.getFloat("cover_icon_image_width");
        if (float1 == 0.0f) {
            return 0.0f;
        }
        return float2 / float1;
    }
    
    public final Uri getCoverImageUri() {
        return this.parseUri("cover_icon_image_uri");
    }
    
    public final String getCoverImageUrl() {
        return this.getString("cover_icon_image_url");
    }
    
    public final String getDescription() {
        return this.getString("description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("description", charArrayBuffer);
    }
    
    public final String getDeviceName() {
        return this.getString("device_name");
    }
    
    public final Game getGame() {
        return this.zzmy;
    }
    
    public final long getLastModifiedTimestamp() {
        return this.getLong("last_modified_timestamp");
    }
    
    public final Player getOwner() {
        return this.zzrd;
    }
    
    public final long getPlayedTime() {
        return this.getLong("duration");
    }
    
    public final long getProgressValue() {
        return this.getLong("progress_value");
    }
    
    public final String getSnapshotId() {
        return this.getString("external_snapshot_id");
    }
    
    public final String getTitle() {
        return this.getString("title");
    }
    
    public final String getUniqueName() {
        return this.getString("unique_name");
    }
    
    public final boolean hasChangePending() {
        return this.getInteger("pending_change_count") > 0;
    }
    
    public final int hashCode() {
        return SnapshotMetadataEntity.zza(this);
    }
    
    public final String toString() {
        return SnapshotMetadataEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((SnapshotMetadataEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
