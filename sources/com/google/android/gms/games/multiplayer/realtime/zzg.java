package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

public final class zzg implements zzh {
    private final RoomUpdateCallback zzou;
    private final RoomStatusUpdateCallback zzov;
    private final OnRealTimeMessageReceivedListener zzpf;

    public zzg(@NonNull RoomUpdateCallback roomUpdateCallback, @Nullable RoomStatusUpdateCallback roomStatusUpdateCallback, @Nullable OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
        this.zzou = roomUpdateCallback;
        this.zzov = roomStatusUpdateCallback;
        this.zzpf = onRealTimeMessageReceivedListener;
    }

    public final void onConnectedToRoom(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onConnectedToRoom(room);
        }
    }

    public final void onDisconnectedFromRoom(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onDisconnectedFromRoom(room);
        }
    }

    public final void onJoinedRoom(int i, @Nullable Room room) {
        if (this.zzou != null) {
            this.zzou.onJoinedRoom(i, room);
        }
    }

    public final void onLeftRoom(int i, @NonNull String str) {
        if (this.zzou != null) {
            this.zzou.onLeftRoom(i, str);
        }
    }

    public final void onP2PConnected(@NonNull String str) {
        if (this.zzov != null) {
            this.zzov.onP2PConnected(str);
        }
    }

    public final void onP2PDisconnected(@NonNull String str) {
        if (this.zzov != null) {
            this.zzov.onP2PDisconnected(str);
        }
    }

    public final void onPeerDeclined(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerDeclined(room, list);
        }
    }

    public final void onPeerInvitedToRoom(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerInvitedToRoom(room, list);
        }
    }

    public final void onPeerJoined(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerJoined(room, list);
        }
    }

    public final void onPeerLeft(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerLeft(room, list);
        }
    }

    public final void onPeersConnected(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeersConnected(room, list);
        }
    }

    public final void onPeersDisconnected(@Nullable Room room, @NonNull List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeersDisconnected(room, list);
        }
    }

    public final void onRealTimeMessageReceived(@NonNull RealTimeMessage realTimeMessage) {
        if (this.zzpf != null) {
            this.zzpf.onRealTimeMessageReceived(realTimeMessage);
        }
    }

    public final void onRoomAutoMatching(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onRoomAutoMatching(room);
        }
    }

    public final void onRoomConnected(int i, @Nullable Room room) {
        if (this.zzou != null) {
            this.zzou.onRoomConnected(i, room);
        }
    }

    public final void onRoomConnecting(@Nullable Room room) {
        if (this.zzov != null) {
            this.zzov.onRoomConnecting(room);
        }
    }

    public final void onRoomCreated(int i, @Nullable Room room) {
        if (this.zzou != null) {
            this.zzou.onRoomCreated(i, room);
        }
    }
}
