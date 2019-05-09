// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.util.VisibleForTesting;

@Deprecated
@VisibleForTesting
public interface RealTimeMultiplayer
{
    public static final int REAL_TIME_MESSAGE_FAILED = -1;
    
    void create(final GoogleApiClient p0, final RoomConfig p1);
    
    void declineInvitation(final GoogleApiClient p0, final String p1);
    
    void dismissInvitation(final GoogleApiClient p0, final String p1);
    
    Intent getSelectOpponentsIntent(final GoogleApiClient p0, final int p1, final int p2);
    
    Intent getSelectOpponentsIntent(final GoogleApiClient p0, final int p1, final int p2, final boolean p3);
    
    Intent getWaitingRoomIntent(final GoogleApiClient p0, final Room p1, final int p2);
    
    void join(final GoogleApiClient p0, final RoomConfig p1);
    
    void leave(final GoogleApiClient p0, final RoomUpdateListener p1, final String p2);
    
    int sendReliableMessage(final GoogleApiClient p0, final ReliableMessageSentCallback p1, final byte[] p2, final String p3, final String p4);
    
    int sendUnreliableMessage(final GoogleApiClient p0, final byte[] p1, final String p2, final String p3);
    
    int sendUnreliableMessage(final GoogleApiClient p0, final byte[] p1, final String p2, final List<String> p3);
    
    int sendUnreliableMessageToOthers(final GoogleApiClient p0, final byte[] p1, final String p2);
    
    @Deprecated
    public interface ReliableMessageSentCallback
    {
        void onRealTimeMessageSent(final int p0, final int p1, final String p2);
    }
}
