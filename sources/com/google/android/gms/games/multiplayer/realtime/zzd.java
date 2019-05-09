package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig.Builder;

public final class zzd extends RoomConfig {
    private final String zzgr;
    private final int zzoe;
    @Deprecated
    private final RoomUpdateListener zzor;
    @Deprecated
    private final RoomStatusUpdateListener zzos;
    @Deprecated
    private final RealTimeMessageReceivedListener zzot;
    private final RoomUpdateCallback zzou;
    private final RoomStatusUpdateCallback zzov;
    private final OnRealTimeMessageReceivedListener zzow;
    private final Bundle zzoz;
    private final zzg zzpa;
    private final String[] zzpb;

    zzd(Builder builder) {
        this.zzor = builder.zzor;
        this.zzos = builder.zzos;
        this.zzot = builder.zzot;
        this.zzou = builder.zzou;
        this.zzov = builder.zzov;
        this.zzow = builder.zzow;
        if (this.zzov != null) {
            this.zzpa = new zzg(this.zzou, this.zzov, this.zzow);
        } else {
            this.zzpa = null;
        }
        this.zzgr = builder.zzox;
        this.zzoe = builder.zzoe;
        this.zzoz = builder.zzoz;
        this.zzpb = (String[]) builder.zzoy.toArray(new String[builder.zzoy.size()]);
        if (this.zzow == null && this.zzot == null) {
            throw new NullPointerException(String.valueOf("Must specify a message listener"));
        }
    }

    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }

    public final String getInvitationId() {
        return this.zzgr;
    }

    public final String[] getInvitedPlayerIds() {
        return this.zzpb;
    }

    @Nullable
    @Deprecated
    public final RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.zzot;
    }

    @Nullable
    public final OnRealTimeMessageReceivedListener getOnMessageReceivedListener() {
        return this.zzow;
    }

    @Nullable
    public final RoomStatusUpdateCallback getRoomStatusUpdateCallback() {
        return this.zzov;
    }

    @Nullable
    @Deprecated
    public final RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.zzos;
    }

    @Nullable
    public final RoomUpdateCallback getRoomUpdateCallback() {
        return this.zzou;
    }

    @Nullable
    @Deprecated
    public final RoomUpdateListener getRoomUpdateListener() {
        return this.zzor;
    }

    public final int getVariant() {
        return this.zzoe;
    }

    public final zzh zzch() {
        return this.zzpa;
    }
}
