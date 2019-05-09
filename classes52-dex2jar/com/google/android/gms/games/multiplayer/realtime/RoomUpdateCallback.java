// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class RoomUpdateCallback implements RoomUpdateListener
{
    @Override
    public abstract void onJoinedRoom(final int p0, @Nullable final Room p1);
    
    @Override
    public abstract void onLeftRoom(final int p0, @NonNull final String p1);
    
    @Override
    public abstract void onRoomConnected(final int p0, @Nullable final Room p1);
    
    @Override
    public abstract void onRoomCreated(final int p0, @Nullable final Room p1);
}
