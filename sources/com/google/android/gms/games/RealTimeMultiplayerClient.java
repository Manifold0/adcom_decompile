package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class RealTimeMultiplayerClient extends zzu {

    public interface ReliableMessageSentCallback extends com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback {
        void onRealTimeMessageSent(int i, int i2, String str);
    }

    RealTimeMultiplayerClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    RealTimeMultiplayerClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Void> create(@NonNull RoomConfig roomConfig) {
        ListenerHolder registerListener = registerListener(roomConfig.zzch(), zzh.class.getSimpleName());
        return doRegisterEventListener(new zzbj(this, registerListener, registerListener, roomConfig), new zzbk(this, registerListener.getListenerKey()));
    }

    public Task<Void> declineInvitation(@NonNull String str) {
        return doWrite(new zzbe(this, str));
    }

    public Task<Void> dismissInvitation(@NonNull String str) {
        return doWrite(new zzbf(this, str));
    }

    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1) int i, @IntRange(from = 1) int i2) {
        return getSelectOpponentsIntent(i, i2, true);
    }

    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1) int i, @IntRange(from = 1) int i2, boolean z) {
        return doRead(new zzbi(this, i, i2, z));
    }

    public Task<Intent> getWaitingRoomIntent(@NonNull Room room, @IntRange(from = 0) int i) {
        return doRead(new zzba(this, room, i));
    }

    public Task<Void> join(@NonNull RoomConfig roomConfig) {
        ListenerHolder registerListener = registerListener(roomConfig.zzch(), zzh.class.getSimpleName());
        return doRegisterEventListener(new zzbl(this, registerListener, registerListener, roomConfig), new zzbm(this, registerListener.getListenerKey()));
    }

    public Task<Void> leave(@NonNull RoomConfig roomConfig, @NonNull String str) {
        ListenerHolder registerListener = registerListener(roomConfig.zzch(), zzh.class.getSimpleName());
        return doRead(new zzbg(this, str)).continueWithTask(new zzbq(this, registerListener)).continueWithTask(new zzbn(this, registerListener, str, roomConfig));
    }

    public Task<Integer> sendReliableMessage(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2, @Nullable ReliableMessageSentCallback reliableMessageSentCallback) {
        ListenerHolder listenerHolder = null;
        if (reliableMessageSentCallback != null) {
            listenerHolder = ListenerHolders.createListenerHolder(reliableMessageSentCallback, Looper.getMainLooper(), ReliableMessageSentCallback.class.getSimpleName());
        }
        return doWrite(new zzbr(this, listenerHolder, bArr, str, str2));
    }

    public Task<Void> sendUnreliableMessage(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2) {
        return doWrite(new zzbb(this, bArr, str, str2));
    }

    public Task<Void> sendUnreliableMessage(@NonNull byte[] bArr, @NonNull String str, @NonNull List<String> list) {
        return doWrite(new zzbc(this, list, bArr, str));
    }

    public Task<Void> sendUnreliableMessageToOthers(@NonNull byte[] bArr, @NonNull String str) {
        return doWrite(new zzbd(this, bArr, str));
    }
}
