package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.tapjoy.TapjoyConstants;

public final class SnapshotMetadataRef extends DataBufferRef implements SnapshotMetadata {
    private final Game zzmy;
    private final Player zzrd;

    public SnapshotMetadataRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
        this.zzmy = new GameRef(dataHolder, i);
        this.zzrd = new PlayerRef(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return SnapshotMetadataEntity.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new SnapshotMetadataEntity(this);
    }

    public final float getCoverImageAspectRatio() {
        float f = getFloat("cover_icon_image_height");
        return f == 0.0f ? 0.0f : getFloat("cover_icon_image_width") / f;
    }

    public final Uri getCoverImageUri() {
        return parseUri("cover_icon_image_uri");
    }

    public final String getCoverImageUrl() {
        return getString("cover_icon_image_url");
    }

    public final String getDescription() {
        return getString("description");
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("description", charArrayBuffer);
    }

    public final String getDeviceName() {
        return getString(TapjoyConstants.TJC_DEVICE_NAME);
    }

    public final Game getGame() {
        return this.zzmy;
    }

    public final long getLastModifiedTimestamp() {
        return getLong("last_modified_timestamp");
    }

    public final Player getOwner() {
        return this.zzrd;
    }

    public final long getPlayedTime() {
        return getLong("duration");
    }

    public final long getProgressValue() {
        return getLong("progress_value");
    }

    public final String getSnapshotId() {
        return getString("external_snapshot_id");
    }

    public final String getTitle() {
        return getString("title");
    }

    public final String getUniqueName() {
        return getString("unique_name");
    }

    public final boolean hasChangePending() {
        return getInteger("pending_change_count") > 0;
    }

    public final int hashCode() {
        return SnapshotMetadataEntity.zza(this);
    }

    public final String toString() {
        return SnapshotMetadataEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((SnapshotMetadataEntity) ((SnapshotMetadata) freeze())).writeToParcel(parcel, i);
    }
}
