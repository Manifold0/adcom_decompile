// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.Nullable;
import android.os.Bundle;

public final class zzd extends RoomConfig
{
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
    
    zzd(final Builder builder) {
        this.zzor = builder.zzor;
        this.zzos = builder.zzos;
        this.zzot = builder.zzot;
        this.zzou = builder.zzou;
        this.zzov = builder.zzov;
        this.zzow = builder.zzow;
        if (this.zzov != null) {
            this.zzpa = new zzg(this.zzou, this.zzov, this.zzow);
        }
        else {
            this.zzpa = null;
        }
        this.zzgr = builder.zzox;
        this.zzoe = builder.zzoe;
        this.zzoz = builder.zzoz;
        this.zzpb = builder.zzoy.toArray(new String[builder.zzoy.size()]);
        if (this.zzow == null && this.zzot == null) {
            throw new NullPointerException(String.valueOf("Must specify a message listener"));
        }
    }
    
    @Override
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }
    
    @Override
    public final String getInvitationId() {
        return this.zzgr;
    }
    
    @Override
    public final String[] getInvitedPlayerIds() {
        return this.zzpb;
    }
    
    @Deprecated
    @Nullable
    @Override
    public final RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.zzot;
    }
    
    @Nullable
    @Override
    public final OnRealTimeMessageReceivedListener getOnMessageReceivedListener() {
        return this.zzow;
    }
    
    @Nullable
    @Override
    public final RoomStatusUpdateCallback getRoomStatusUpdateCallback() {
        return this.zzov;
    }
    
    @Deprecated
    @Nullable
    @Override
    public final RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.zzos;
    }
    
    @Nullable
    @Override
    public final RoomUpdateCallback getRoomUpdateCallback() {
        return this.zzou;
    }
    
    @Deprecated
    @Nullable
    @Override
    public final RoomUpdateListener getRoomUpdateListener() {
        return this.zzor;
    }
    
    @Override
    public final int getVariant() {
        return this.zzoe;
    }
    
    @Override
    public final zzh zzch() {
        return this.zzpa;
    }
}
