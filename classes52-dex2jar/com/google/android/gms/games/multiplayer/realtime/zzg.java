// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

public final class zzg implements zzh
{
    private final RoomUpdateCallback zzou;
    private final RoomStatusUpdateCallback zzov;
    private final OnRealTimeMessageReceivedListener zzpf;
    
    public zzg(@NonNull final RoomUpdateCallback zzou, @Nullable final RoomStatusUpdateCallback zzov, @Nullable final OnRealTimeMessageReceivedListener zzpf) {
        this.zzou = zzou;
        this.zzov = zzov;
        this.zzpf = zzpf;
    }
    
    @Override
    public final void onConnectedToRoom(@Nullable final Room room) {
        if (this.zzov != null) {
            this.zzov.onConnectedToRoom(room);
        }
    }
    
    @Override
    public final void onDisconnectedFromRoom(@Nullable final Room room) {
        if (this.zzov != null) {
            this.zzov.onDisconnectedFromRoom(room);
        }
    }
    
    @Override
    public final void onJoinedRoom(final int n, @Nullable final Room room) {
        if (this.zzou != null) {
            this.zzou.onJoinedRoom(n, room);
        }
    }
    
    @Override
    public final void onLeftRoom(final int n, @NonNull final String s) {
        if (this.zzou != null) {
            this.zzou.onLeftRoom(n, s);
        }
    }
    
    @Override
    public final void onP2PConnected(@NonNull final String s) {
        if (this.zzov != null) {
            this.zzov.onP2PConnected(s);
        }
    }
    
    @Override
    public final void onP2PDisconnected(@NonNull final String s) {
        if (this.zzov != null) {
            this.zzov.onP2PDisconnected(s);
        }
    }
    
    @Override
    public final void onPeerDeclined(@Nullable final Room room, @NonNull final List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerDeclined(room, list);
        }
    }
    
    @Override
    public final void onPeerInvitedToRoom(@Nullable final Room room, @NonNull final List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerInvitedToRoom(room, list);
        }
    }
    
    @Override
    public final void onPeerJoined(@Nullable final Room room, @NonNull final List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerJoined(room, list);
        }
    }
    
    @Override
    public final void onPeerLeft(@Nullable final Room room, @NonNull final List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeerLeft(room, list);
        }
    }
    
    @Override
    public final void onPeersConnected(@Nullable final Room room, @NonNull final List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeersConnected(room, list);
        }
    }
    
    @Override
    public final void onPeersDisconnected(@Nullable final Room room, @NonNull final List<String> list) {
        if (this.zzov != null) {
            this.zzov.onPeersDisconnected(room, list);
        }
    }
    
    @Override
    public final void onRealTimeMessageReceived(@NonNull final RealTimeMessage realTimeMessage) {
        if (this.zzpf != null) {
            this.zzpf.onRealTimeMessageReceived(realTimeMessage);
        }
    }
    
    @Override
    public final void onRoomAutoMatching(@Nullable final Room room) {
        if (this.zzov != null) {
            this.zzov.onRoomAutoMatching(room);
        }
    }
    
    @Override
    public final void onRoomConnected(final int n, @Nullable final Room room) {
        if (this.zzou != null) {
            this.zzou.onRoomConnected(n, room);
        }
    }
    
    @Override
    public final void onRoomConnecting(@Nullable final Room room) {
        if (this.zzov != null) {
            this.zzov.onRoomConnecting(room);
        }
    }
    
    @Override
    public final void onRoomCreated(final int n, @Nullable final Room room) {
        if (this.zzou != null) {
            this.zzou.onRoomCreated(n, room);
        }
    }
}
