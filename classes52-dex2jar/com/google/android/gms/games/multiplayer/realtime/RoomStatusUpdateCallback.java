// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class RoomStatusUpdateCallback implements RoomStatusUpdateListener
{
    @Override
    public abstract void onConnectedToRoom(@Nullable final Room p0);
    
    @Override
    public abstract void onDisconnectedFromRoom(@Nullable final Room p0);
    
    @Override
    public abstract void onP2PConnected(@NonNull final String p0);
    
    @Override
    public abstract void onP2PDisconnected(@NonNull final String p0);
    
    @Override
    public abstract void onPeerDeclined(@Nullable final Room p0, @NonNull final List<String> p1);
    
    @Override
    public abstract void onPeerInvitedToRoom(@Nullable final Room p0, @NonNull final List<String> p1);
    
    @Override
    public abstract void onPeerJoined(@Nullable final Room p0, @NonNull final List<String> p1);
    
    @Override
    public abstract void onPeerLeft(@Nullable final Room p0, @NonNull final List<String> p1);
    
    @Override
    public abstract void onPeersConnected(@Nullable final Room p0, @NonNull final List<String> p1);
    
    @Override
    public abstract void onPeersDisconnected(@Nullable final Room p0, @NonNull final List<String> p1);
    
    @Override
    public abstract void onRoomAutoMatching(@Nullable final Room p0);
    
    @Override
    public abstract void onRoomConnecting(@Nullable final Room p0);
}
