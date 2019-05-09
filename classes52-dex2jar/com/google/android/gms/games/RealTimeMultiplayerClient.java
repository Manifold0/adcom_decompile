// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import java.util.List;
import com.google.android.gms.common.api.internal.ListenerHolders;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.games.multiplayer.realtime.Room;
import android.content.Intent;
import android.support.annotation.IntRange;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.games.multiplayer.realtime.zzh;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.games.zzu;

public class RealTimeMultiplayerClient extends zzu
{
    RealTimeMultiplayerClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    RealTimeMultiplayerClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<Void> create(@NonNull final RoomConfig roomConfig) {
        final ListenerHolder registerListener = this.registerListener((Object)roomConfig.zzch(), zzh.class.getSimpleName());
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzbj(this, registerListener, registerListener, roomConfig), (UnregisterListenerMethod)new zzbk(this, registerListener.getListenerKey()));
    }
    
    public Task<Void> declineInvitation(@NonNull final String s) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbe(this, s));
    }
    
    public Task<Void> dismissInvitation(@NonNull final String s) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbf(this, s));
    }
    
    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1L) final int n, @IntRange(from = 1L) final int n2) {
        return this.getSelectOpponentsIntent(n, n2, true);
    }
    
    public Task<Intent> getSelectOpponentsIntent(@IntRange(from = 1L) final int n, @IntRange(from = 1L) final int n2, final boolean b) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzbi(this, n, n2, b));
    }
    
    public Task<Intent> getWaitingRoomIntent(@NonNull final Room room, @IntRange(from = 0L) final int n) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzba(this, room, n));
    }
    
    public Task<Void> join(@NonNull final RoomConfig roomConfig) {
        final ListenerHolder registerListener = this.registerListener((Object)roomConfig.zzch(), zzh.class.getSimpleName());
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzbl(this, registerListener, registerListener, roomConfig), (UnregisterListenerMethod)new zzbm(this, registerListener.getListenerKey()));
    }
    
    public Task<Void> leave(@NonNull final RoomConfig roomConfig, @NonNull final String s) {
        final ListenerHolder registerListener = this.registerListener((Object)roomConfig.zzch(), zzh.class.getSimpleName());
        return (Task<Void>)this.doRead((TaskApiCall)new zzbg(this, s)).continueWithTask((Continuation)new zzbq(this, registerListener)).continueWithTask((Continuation)new zzbn(this, registerListener, s, roomConfig));
    }
    
    public Task<Integer> sendReliableMessage(@NonNull final byte[] array, @NonNull final String s, @NonNull final String s2, @Nullable final ReliableMessageSentCallback reliableMessageSentCallback) {
        ListenerHolder listenerHolder = null;
        if (reliableMessageSentCallback != null) {
            listenerHolder = ListenerHolders.createListenerHolder((Object)reliableMessageSentCallback, Looper.getMainLooper(), ReliableMessageSentCallback.class.getSimpleName());
        }
        return (Task<Integer>)this.doWrite((TaskApiCall)new zzbr(this, listenerHolder, array, s, s2));
    }
    
    public Task<Void> sendUnreliableMessage(@NonNull final byte[] array, @NonNull final String s, @NonNull final String s2) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbb(this, array, s, s2));
    }
    
    public Task<Void> sendUnreliableMessage(@NonNull final byte[] array, @NonNull final String s, @NonNull final List<String> list) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbc(this, list, array, s));
    }
    
    public Task<Void> sendUnreliableMessageToOthers(@NonNull final byte[] array, @NonNull final String s) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbd(this, array, s));
    }
    
    public interface ReliableMessageSentCallback extends RealTimeMultiplayer.ReliableMessageSentCallback
    {
        void onRealTimeMessageSent(final int p0, final int p1, final String p2);
    }
}
