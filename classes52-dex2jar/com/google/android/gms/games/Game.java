// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Game extends Parcelable, Freezable<Game>
{
    boolean areSnapshotsEnabled();
    
    int getAchievementTotalCount();
    
    String getApplicationId();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getDeveloperName();
    
    void getDeveloperName(final CharArrayBuffer p0);
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Uri getFeaturedImageUri();
    
    @Deprecated
    @KeepName
    String getFeaturedImageUrl();
    
    Uri getHiResImageUri();
    
    @Deprecated
    @KeepName
    String getHiResImageUrl();
    
    Uri getIconImageUri();
    
    @Deprecated
    @KeepName
    String getIconImageUrl();
    
    int getLeaderboardCount();
    
    String getPrimaryCategory();
    
    String getSecondaryCategory();
    
    String getThemeColor();
    
    boolean hasGamepadSupport();
    
    boolean isMuted();
    
    boolean isRealTimeMultiplayerEnabled();
    
    boolean isTurnBasedMultiplayerEnabled();
    
    boolean zza();
    
    boolean zzb();
    
    boolean zzc();
    
    String zzd();
}
