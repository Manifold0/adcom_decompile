// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.Parcel;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBufferRef;

public final class GameRef extends DataBufferRef implements Game
{
    public GameRef(final DataHolder dataHolder, final int n) {
        super(dataHolder, n);
    }
    
    public final boolean areSnapshotsEnabled() {
        return this.getInteger("snapshots_enabled") > 0;
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final boolean equals(final Object o) {
        return GameEntity.zza(this, o);
    }
    
    public final /* synthetic */ Object freeze() {
        return new GameEntity(this);
    }
    
    public final int getAchievementTotalCount() {
        return this.getInteger("achievement_total_count");
    }
    
    public final String getApplicationId() {
        return this.getString("external_game_id");
    }
    
    public final String getDescription() {
        return this.getString("game_description");
    }
    
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("game_description", charArrayBuffer);
    }
    
    public final String getDeveloperName() {
        return this.getString("developer_name");
    }
    
    public final void getDeveloperName(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("developer_name", charArrayBuffer);
    }
    
    public final String getDisplayName() {
        return this.getString("display_name");
    }
    
    public final void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("display_name", charArrayBuffer);
    }
    
    public final Uri getFeaturedImageUri() {
        return this.parseUri("featured_image_uri");
    }
    
    public final String getFeaturedImageUrl() {
        return this.getString("featured_image_url");
    }
    
    public final Uri getHiResImageUri() {
        return this.parseUri("game_hi_res_image_uri");
    }
    
    public final String getHiResImageUrl() {
        return this.getString("game_hi_res_image_url");
    }
    
    public final Uri getIconImageUri() {
        return this.parseUri("game_icon_image_uri");
    }
    
    public final String getIconImageUrl() {
        return this.getString("game_icon_image_url");
    }
    
    public final int getLeaderboardCount() {
        return this.getInteger("leaderboard_count");
    }
    
    public final String getPrimaryCategory() {
        return this.getString("primary_category");
    }
    
    public final String getSecondaryCategory() {
        return this.getString("secondary_category");
    }
    
    public final String getThemeColor() {
        return this.getString("theme_color");
    }
    
    public final boolean hasGamepadSupport() {
        return this.getInteger("gamepad_support") > 0;
    }
    
    public final int hashCode() {
        return GameEntity.zza(this);
    }
    
    public final boolean isMuted() {
        return this.getBoolean("muted");
    }
    
    public final boolean isRealTimeMultiplayerEnabled() {
        return this.getInteger("real_time_support") > 0;
    }
    
    public final boolean isTurnBasedMultiplayerEnabled() {
        return this.getInteger("turn_based_support") > 0;
    }
    
    public final String toString() {
        return GameEntity.zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        ((GameEntity)this.freeze()).writeToParcel(parcel, n);
    }
    
    public final boolean zza() {
        return this.getBoolean("play_enabled_game");
    }
    
    public final boolean zzb() {
        return this.getBoolean("identity_sharing_confirmed");
    }
    
    public final boolean zzc() {
        return this.getInteger("installed") > 0;
    }
    
    public final String zzd() {
        return this.getString("package_name");
    }
}
