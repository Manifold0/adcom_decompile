// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.quest;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.DataBufferRef;

@VisibleForTesting
public final class QuestRef extends DataBufferRef implements Quest
{
    private final Game zzmy;
    private final int zzmz;
    
    QuestRef(final DataHolder dataHolder, final int n, final int zzmz) {
        super(dataHolder, n);
        this.zzmy = new GameRef(dataHolder, n);
        this.zzmz = zzmz;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return QuestEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new QuestEntity(this);
    }
    
    public final long getAcceptedTimestamp() {
        return this.getLong("accepted_ts");
    }
    
    public final Uri getBannerImageUri() {
        return this.parseUri("quest_banner_image_uri");
    }
    
    public final String getBannerImageUrl() {
        return this.getString("quest_banner_image_url");
    }
    
    public final Milestone getCurrentMilestone() {
        return this.zzcj().get(0);
    }
    
    public final String getDescription() {
        return this.getString("quest_description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("quest_description", charArrayBuffer);
    }
    
    public final long getEndTimestamp() {
        return this.getLong("quest_end_ts");
    }
    
    public final Game getGame() {
        return this.zzmy;
    }
    
    public final Uri getIconImageUri() {
        return this.parseUri("quest_icon_image_uri");
    }
    
    public final String getIconImageUrl() {
        return this.getString("quest_icon_image_url");
    }
    
    public final long getLastUpdatedTimestamp() {
        return this.getLong("quest_last_updated_ts");
    }
    
    public final String getName() {
        return this.getString("quest_name");
    }
    
    public final void getName(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("quest_name", charArrayBuffer);
    }
    
    public final String getQuestId() {
        return this.getString("external_quest_id");
    }
    
    public final long getStartTimestamp() {
        return this.getLong("quest_start_ts");
    }
    
    public final int getState() {
        return this.getInteger("quest_state");
    }
    
    public final int getType() {
        return this.getInteger("quest_type");
    }
    
    public final int hashCode() {
        return QuestEntity.zza(this);
    }
    
    public final boolean isEndingSoon() {
        return this.getLong("notification_ts") <= System.currentTimeMillis() + 1800000L;
    }
    
    public final String toString() {
        return QuestEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((QuestEntity)this.freeze()).writeToParcel(parcel, n);
    }
    
    public final List<Milestone> zzcj() {
        final ArrayList<zzb> list = (ArrayList<zzb>)new ArrayList<Milestone>(this.zzmz);
        for (int i = 0; i < this.zzmz; ++i) {
            list.add(new zzb(this.mDataHolder, this.mDataRow + i));
        }
        return (List<Milestone>)list;
    }
    
    public final long zzck() {
        return this.getLong("notification_ts");
    }
}
