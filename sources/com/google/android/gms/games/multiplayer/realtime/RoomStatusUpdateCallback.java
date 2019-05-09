package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

public abstract class RoomStatusUpdateCallback implements RoomStatusUpdateListener {
    public abstract void onConnectedToRoom(@Nullable Room room);

    public abstract void onDisconnectedFromRoom(@Nullable Room room);

    public abstract void onP2PConnected(@NonNull String str);

    public abstract void onP2PDisconnected(@NonNull String str);

    public abstract void onPeerDeclined(@Nullable Room room, @NonNull List<String> list);

    public abstract void onPeerInvitedToRoom(@Nullable Room room, @NonNull List<String> list);

    public abstract void onPeerJoined(@Nullable Room room, @NonNull List<String> list);

    public abstract void onPeerLeft(@Nullable Room room, @NonNull List<String> list);

    public abstract void onPeersConnected(@Nullable Room room, @NonNull List<String> list);

    public abstract void onPeersDisconnected(@Nullable Room room, @NonNull List<String> list);

    public abstract void onRoomAutoMatching(@Nullable Room room);

    public abstract void onRoomConnecting(@Nullable Room room);
}
