// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.achievement;

import android.os.Parcel;
import android.net.Uri;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class AchievementRef extends DataBufferRef implements Achievement
{
    AchievementRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final /* synthetic */ Object freeze() {
        return new AchievementEntity(this);
    }
    
    public final String getAchievementId() {
        return this.getString("external_achievement_id");
    }
    
    public final int getCurrentSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.getInteger("current_steps");
    }
    
    public final String getDescription() {
        return this.getString("description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("description", charArrayBuffer);
    }
    
    public final String getFormattedCurrentSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.getString("formatted_current_steps");
    }
    
    public final void getFormattedCurrentSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        this.copyToBuffer("formatted_current_steps", charArrayBuffer);
    }
    
    public final String getFormattedTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.getString("formatted_total_steps");
    }
    
    public final void getFormattedTotalSteps(final CharArrayBuffer charArrayBuffer) {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        this.copyToBuffer("formatted_total_steps", charArrayBuffer);
    }
    
    public final long getLastUpdatedTimestamp() {
        return this.getLong("last_updated_timestamp");
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
    
    public final Uri getRevealedImageUri() {
        return this.parseUri("revealed_icon_image_uri");
    }
    
    public final String getRevealedImageUrl() {
        return this.getString("revealed_icon_image_url");
    }
    
    public final int getState() {
        return this.getInteger("state");
    }
    
    public final int getTotalSteps() {
        boolean b = true;
        if (this.getType() != 1) {
            b = false;
        }
        Asserts.checkState(b);
        return this.getInteger("total_steps");
    }
    
    public final int getType() {
        return this.getInteger("type");
    }
    
    public final Uri getUnlockedImageUri() {
        return this.parseUri("unlocked_icon_image_uri");
    }
    
    public final String getUnlockedImageUrl() {
        return this.getString("unlocked_icon_image_url");
    }
    
    public final long getXpValue() {
        if (!this.hasColumn("instance_xp_value") || this.hasNull("instance_xp_value")) {
            return this.getLong("definition_xp_value");
        }
        return this.getLong("instance_xp_value");
    }
    
    public final String toString() {
        return AchievementEntity.zza(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((AchievementEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
