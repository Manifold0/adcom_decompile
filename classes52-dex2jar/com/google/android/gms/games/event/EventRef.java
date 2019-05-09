// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.event;

import android.os.Parcel;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.Player;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class EventRef extends DataBufferRef implements Event
{
    EventRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return EventEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new EventEntity(this);
    }
    
    public final String getDescription() {
        return this.getString("description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("description", charArrayBuffer);
    }
    
    public final String getEventId() {
        return this.getString("external_event_id");
    }
    
    public final String getFormattedValue() {
        return this.getString("formatted_value");
    }
    
    public final void getFormattedValue(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("formatted_value", charArrayBuffer);
    }
    
    public final Uri getIconImageUri() {
        return this.parseUri("icon_image_uri");
    }
    
    public final String getIconImageUrl() {
        return this.getString("icon_image_url");
    }
    
    public final String getName() {
        return this.getString("name");
    }
    
    public final void getName(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("name", charArrayBuffer);
    }
    
    public final Player getPlayer() {
        return new PlayerRef(this.mDataHolder, this.mDataRow);
    }
    
    public final long getValue() {
        return this.getLong("value");
    }
    
    public final int hashCode() {
        return EventEntity.zza(this);
    }
    
    public final boolean isVisible() {
        return this.getBoolean("visibility");
    }
    
    public final String toString() {
        return EventEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((EventEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
