// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import java.util.List;
import com.google.android.gms.games.multiplayer.realtime.Room;
import android.content.Intent;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import android.support.annotation.Nullable;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;

public final class zzbz implements RealTimeMultiplayer
{
    private static ListenerHolder<RoomUpdateListener> zza(@NonNull final GoogleApiClient googleApiClient, @NonNull final RoomConfig roomConfig) {
        if (roomConfig.getRoomUpdateCallback() != null) {
            return (ListenerHolder<RoomUpdateListener>)googleApiClient.registerListener((Object)roomConfig.getRoomUpdateCallback());
        }
        return (ListenerHolder<RoomUpdateListener>)googleApiClient.registerListener((Object)roomConfig.getRoomUpdateListener());
    }
    
    private static <L> ListenerHolder<L> zza(final GoogleApiClient googleApiClient, final L l) {
        if (l == null) {
            return null;
        }
        return (ListenerHolder<L>)googleApiClient.registerListener((Object)l);
    }
    
    @Nullable
    private static ListenerHolder<RoomStatusUpdateListener> zzb(@NonNull final GoogleApiClient googleApiClient, @NonNull final RoomConfig roomConfig) {
        if (roomConfig.getRoomStatusUpdateCallback() != null) {
            return (ListenerHolder<RoomStatusUpdateListener>)zza(googleApiClient, roomConfig.getRoomStatusUpdateCallback());
        }
        return zza(googleApiClient, roomConfig.getRoomStatusUpdateListener());
    }
    
    @Nullable
    private static ListenerHolder<RealTimeMessageReceivedListener> zzc(@NonNull final GoogleApiClient googleApiClient, @NonNull final RoomConfig roomConfig) {
        if (roomConfig.getOnMessageReceivedListener() != null) {
            return (ListenerHolder<RealTimeMessageReceivedListener>)googleApiClient.registerListener((Object)roomConfig.getOnMessageReceivedListener());
        }
        return (ListenerHolder<RealTimeMessageReceivedListener>)googleApiClient.registerListener((Object)roomConfig.getMessageReceivedListener());
    }
    
    @Override
    public final void create(final GoogleApiClient googleApiClient, final RoomConfig roomConfig) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza == null) {
            return;
        }
        zza.zzb(zza(googleApiClient, roomConfig), zzb(googleApiClient, roomConfig), zzc(googleApiClient, roomConfig), roomConfig);
    }
    
    @Override
    public final void declineInvitation(final GoogleApiClient googleApiClient, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze(s, 0);
        }
    }
    
    @Override
    public final void dismissInvitation(final GoogleApiClient googleApiClient, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzc(s, 0);
        }
    }
    
    @Override
    public final Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2) {
        return Games.zza(googleApiClient).zzd(n, n2, true);
    }
    
    @Override
    public final Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2, final boolean b) {
        return Games.zza(googleApiClient).zzd(n, n2, b);
    }
    
    @Override
    public final Intent getWaitingRoomIntent(final GoogleApiClient googleApiClient, final Room room, final int n) {
        return Games.zza(googleApiClient).zzb(room, n);
    }
    
    @Override
    public final void join(final GoogleApiClient googleApiClient, final RoomConfig roomConfig) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza == null) {
            return;
        }
        zza.zzd(zza(googleApiClient, roomConfig), zzb(googleApiClient, roomConfig), zzc(googleApiClient, roomConfig), roomConfig);
    }
    
    @Override
    public final void leave(final GoogleApiClient googleApiClient, final RoomUpdateListener roomUpdateListener, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zza((ListenerHolder<? extends RoomUpdateListener>)googleApiClient.registerListener((Object)roomUpdateListener), s);
        }
    }
    
    @Override
    public final int sendReliableMessage(final GoogleApiClient googleApiClient, final ReliableMessageSentCallback reliableMessageSentCallback, final byte[] array, final String s, final String s2) {
        return Games.zza(googleApiClient).zzb(zza(googleApiClient, reliableMessageSentCallback), array, s, s2);
    }
    
    @Override
    public final int sendUnreliableMessage(final GoogleApiClient googleApiClient, final byte[] array, final String s, final String s2) {
        return Games.zza(googleApiClient).zza(array, s, new String[] { s2 });
    }
    
    @Override
    public final int sendUnreliableMessage(final GoogleApiClient googleApiClient, final byte[] array, final String s, final List<String> list) {
        return Games.zza(googleApiClient).zza(array, s, list.toArray(new String[list.size()]));
    }
    
    @Override
    public final int sendUnreliableMessageToOthers(final GoogleApiClient googleApiClient, final byte[] array, final String s) {
        return Games.zza(googleApiClient).zzb(array, s);
    }
}
