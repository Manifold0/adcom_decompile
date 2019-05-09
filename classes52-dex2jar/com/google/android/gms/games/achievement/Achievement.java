// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.achievement;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.annotation.KeepName;
import android.support.annotation.Nullable;
import android.net.Uri;
import com.google.android.gms.games.Player;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@VisibleForTesting
public interface Achievement extends Parcelable, Freezable<Achievement>
{
    public static final int STATE_HIDDEN = 2;
    public static final int STATE_REVEALED = 1;
    public static final int STATE_UNLOCKED = 0;
    public static final int TYPE_INCREMENTAL = 1;
    public static final int TYPE_STANDARD = 0;
    
    String getAchievementId();
    
    int getCurrentSteps();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getFormattedCurrentSteps();
    
    void getFormattedCurrentSteps(final CharArrayBuffer p0);
    
    String getFormattedTotalSteps();
    
    void getFormattedTotalSteps(final CharArrayBuffer p0);
    
    long getLastUpdatedTimestamp();
    
    String getName();
    
    void getName(final CharArrayBuffer p0);
    
    Player getPlayer();
    
    @Nullable
    Uri getRevealedImageUri();
    
    @Deprecated
    @Nullable
    @KeepName
    String getRevealedImageUrl();
    
    int getState();
    
    int getTotalSteps();
    
    int getType();
    
    @Nullable
    Uri getUnlockedImageUri();
    
    @Deprecated
    @Nullable
    @KeepName
    String getUnlockedImageUrl();
    
    long getXpValue();
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface AchievementState {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface AchievementType {
    }
}
