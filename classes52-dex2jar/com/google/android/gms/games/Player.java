// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.player.zza;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@VisibleForTesting
public interface Player extends Parcelable, Freezable<Player>
{
    public static final long CURRENT_XP_UNKNOWN = -1L;
    public static final long TIMESTAMP_UNKNOWN = -1L;
    
    Uri getBannerImageLandscapeUri();
    
    @Deprecated
    @KeepName
    String getBannerImageLandscapeUrl();
    
    Uri getBannerImagePortraitUri();
    
    @Deprecated
    @KeepName
    String getBannerImagePortraitUrl();
    
    String getDisplayName();
    
    void getDisplayName(final CharArrayBuffer p0);
    
    Uri getHiResImageUri();
    
    @Deprecated
    @KeepName
    String getHiResImageUrl();
    
    Uri getIconImageUri();
    
    @Deprecated
    @KeepName
    String getIconImageUrl();
    
    long getLastPlayedWithTimestamp();
    
    PlayerLevelInfo getLevelInfo();
    
    String getName();
    
    String getPlayerId();
    
    long getRetrievedTimestamp();
    
    String getTitle();
    
    void getTitle(final CharArrayBuffer p0);
    
    boolean hasHiResImage();
    
    boolean hasIconImage();
    
    boolean isMuted();
    
    String zzg();
    
    boolean zzh();
    
    @Deprecated
    int zzi();
    
    boolean zzj();
    
    zza zzk();
    
    int zzl();
    
    long zzm();
}
