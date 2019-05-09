// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.games.Player;
import com.google.android.gms.games.Game;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface SnapshotMetadata extends Parcelable, Freezable<SnapshotMetadata>
{
    public static final long PLAYED_TIME_UNKNOWN = -1L;
    public static final long PROGRESS_VALUE_UNKNOWN = -1L;
    
    float getCoverImageAspectRatio();
    
    Uri getCoverImageUri();
    
    @Deprecated
    @KeepName
    String getCoverImageUrl();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getDeviceName();
    
    Game getGame();
    
    long getLastModifiedTimestamp();
    
    Player getOwner();
    
    long getPlayedTime();
    
    long getProgressValue();
    
    String getSnapshotId();
    
    String getTitle();
    
    String getUniqueName();
    
    boolean hasChangePending();
}
