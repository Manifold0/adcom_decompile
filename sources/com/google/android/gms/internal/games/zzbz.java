package com.google.android.gms.internal.games;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import java.util.List;

public final class zzbz implements RealTimeMultiplayer {
    private static ListenerHolder<RoomUpdateListener> zza(@NonNull GoogleApiClient googleApiClient, @NonNull RoomConfig roomConfig) {
        return roomConfig.getRoomUpdateCallback() != null ? googleApiClient.registerListener(roomConfig.getRoomUpdateCallback()) : googleApiClient.registerListener(roomConfig.getRoomUpdateListener());
    }

    private static <L> ListenerHolder<L> zza(GoogleApiClient googleApiClient, L l) {
        return l == null ? null : googleApiClient.registerListener(l);
    }

    @Nullable
    private static ListenerHolder<RoomStatusUpdateListener> zzb(@NonNull GoogleApiClient googleApiClient, @NonNull RoomConfig roomConfig) {
        return roomConfig.getRoomStatusUpdateCallback() != null ? zza(googleApiClient, roomConfig.getRoomStatusUpdateCallback()) : zza(googleApiClient, roomConfig.getRoomStatusUpdateListener());
    }

    @Nullable
    private static ListenerHolder<RealTimeMessageReceivedListener> zzc(@NonNull GoogleApiClient googleApiClient, @NonNull RoomConfig roomConfig) {
        return roomConfig.getOnMessageReceivedListener() != null ? googleApiClient.registerListener(roomConfig.getOnMessageReceivedListener()) : googleApiClient.registerListener(roomConfig.getMessageReceivedListener());
    }

    public final void create(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzb(zza(googleApiClient, roomConfig), zzb(googleApiClient, roomConfig), zzc(googleApiClient, roomConfig), roomConfig);
        }
    }

    public final void declineInvitation(GoogleApiClient googleApiClient, String str) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze(str, 0);
        }
    }

    public final void dismissInvitation(GoogleApiClient googleApiClient, String str) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzc(str, 0);
        }
    }

    public final Intent getSelectOpponentsIntent(GoogleApiClient googleApiClient, int i, int i2) {
        return Games.zza(googleApiClient).zzd(i, i2, true);
    }

    public final Intent getSelectOpponentsIntent(GoogleApiClient googleApiClient, int i, int i2, boolean z) {
        return Games.zza(googleApiClient).zzd(i, i2, z);
    }

    public final Intent getWaitingRoomIntent(GoogleApiClient googleApiClient, Room room, int i) {
        return Games.zza(googleApiClient).zzb(room, i);
    }

    public final void join(GoogleApiClient googleApiClient, RoomConfig roomConfig) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzd(zza(googleApiClient, roomConfig), zzb(googleApiClient, roomConfig), zzc(googleApiClient, roomConfig), roomConfig);
        }
    }

    public final void leave(GoogleApiClient googleApiClient, RoomUpdateListener roomUpdateListener, String str) {
        zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zza(googleApiClient.registerListener(roomUpdateListener), str);
        }
    }

    public final int sendReliableMessage(GoogleApiClient googleApiClient, ReliableMessageSentCallback reliableMessageSentCallback, byte[] bArr, String str, String str2) {
        return Games.zza(googleApiClient).zzb(zza(googleApiClient, (Object) reliableMessageSentCallback), bArr, str, str2);
    }

    public final int sendUnreliableMessage(GoogleApiClient googleApiClient, byte[] bArr, String str, String str2) {
        return Games.zza(googleApiClient).zza(bArr, str, new String[]{str2});
    }

    public final int sendUnreliableMessage(GoogleApiClient googleApiClient, byte[] bArr, String str, List<String> list) {
        return Games.zza(googleApiClient).zza(bArr, str, (String[]) list.toArray(new String[list.size()]));
    }

    public final int sendUnreliableMessageToOthers(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        return Games.zza(googleApiClient).zzb(bArr, str);
    }
}
