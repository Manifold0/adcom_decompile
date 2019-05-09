package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class RoomUpdateCallback implements RoomUpdateListener {
    public abstract void onJoinedRoom(int i, @Nullable Room room);

    public abstract void onLeftRoom(int i, @NonNull String str);

    public abstract void onRoomConnected(int i, @Nullable Room room);

    public abstract void onRoomCreated(int i, @Nullable Room room);
}
