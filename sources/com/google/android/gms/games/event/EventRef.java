package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class EventRef extends DataBufferRef implements Event {
    EventRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return EventEntity.zza(this, obj);
    }

    public final /* synthetic */ Object freeze() {
        return new EventEntity(this);
    }

    public final String getDescription() {
        return getString("description");
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("description", charArrayBuffer);
    }

    public final String getEventId() {
        return getString("external_event_id");
    }

    public final String getFormattedValue() {
        return getString("formatted_value");
    }

    public final void getFormattedValue(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("formatted_value", charArrayBuffer);
    }

    public final Uri getIconImageUri() {
        return parseUri("icon_image_uri");
    }

    public final String getIconImageUrl() {
        return getString("icon_image_url");
    }

    public final String getName() {
        return getString("name");
    }

    public final void getName(CharArrayBuffer charArrayBuffer) {
        copyToBuffer("name", charArrayBuffer);
    }

    public final Player getPlayer() {
        return new PlayerRef(this.mDataHolder, this.mDataRow);
    }

    public final long getValue() {
        return getLong("value");
    }

    public final int hashCode() {
        return EventEntity.zza(this);
    }

    public final boolean isVisible() {
        return getBoolean("visibility");
    }

    public final String toString() {
        return EventEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((EventEntity) ((Event) freeze())).writeToParcel(parcel, i);
    }
}
