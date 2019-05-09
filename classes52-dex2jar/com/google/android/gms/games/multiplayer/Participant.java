// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.Player;
import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@VisibleForTesting
public interface Participant extends Parcelable, Freezable<Participant>
{
    public static final int STATUS_DECLINED = 3;
    public static final int STATUS_FINISHED = 5;
    public static final int STATUS_INVITED = 1;
    public static final int STATUS_JOINED = 2;
    public static final int STATUS_LEFT = 4;
    public static final int STATUS_NOT_INVITED_YET = 0;
    public static final int STATUS_UNRESPONSIVE = 6;
    
    int getCapabilities();
    
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
    
    String getParticipantId();
    
    Player getPlayer();
    
    ParticipantResult getResult();
    
    int getStatus();
    
    boolean isConnectedToRoom();
    
    String zzcg();
}
