// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;

public abstract class RoomConfig
{
    protected RoomConfig() {
    }
    
    public static Builder builder(@NonNull final RoomUpdateCallback roomUpdateCallback) {
        return new Builder(roomUpdateCallback, null);
    }
    
    @Deprecated
    public static Builder builder(final RoomUpdateListener roomUpdateListener) {
        return new Builder(roomUpdateListener, null);
    }
    
    public static Bundle createAutoMatchCriteria(final int n, final int n2, final long n3) {
        final Bundle bundle = new Bundle();
        bundle.putInt("min_automatch_players", n);
        bundle.putInt("max_automatch_players", n2);
        bundle.putLong("exclusive_bit_mask", n3);
        return bundle;
    }
    
    public abstract Bundle getAutoMatchCriteria();
    
    public abstract String getInvitationId();
    
    public abstract String[] getInvitedPlayerIds();
    
    @Deprecated
    public abstract RealTimeMessageReceivedListener getMessageReceivedListener();
    
    public abstract OnRealTimeMessageReceivedListener getOnMessageReceivedListener();
    
    public abstract RoomStatusUpdateCallback getRoomStatusUpdateCallback();
    
    @Deprecated
    public abstract RoomStatusUpdateListener getRoomStatusUpdateListener();
    
    public abstract RoomUpdateCallback getRoomUpdateCallback();
    
    @Deprecated
    public abstract RoomUpdateListener getRoomUpdateListener();
    
    public abstract int getVariant();
    
    public abstract zzh zzch();
    
    public static final class Builder
    {
        int zzoe;
        @Deprecated
        final RoomUpdateListener zzor;
        @Deprecated
        RoomStatusUpdateListener zzos;
        @Deprecated
        RealTimeMessageReceivedListener zzot;
        final RoomUpdateCallback zzou;
        RoomStatusUpdateCallback zzov;
        OnRealTimeMessageReceivedListener zzow;
        String zzox;
        ArrayList<String> zzoy;
        Bundle zzoz;
        
        private Builder(@NonNull final RoomUpdateCallback roomUpdateCallback) {
            this.zzox = null;
            this.zzoe = -1;
            this.zzoy = new ArrayList<String>();
            this.zzou = (RoomUpdateCallback)Preconditions.checkNotNull((Object)roomUpdateCallback, (Object)"Must provide a RoomUpdateCallback");
            this.zzor = null;
        }
        
        @Deprecated
        private Builder(final RoomUpdateListener roomUpdateListener) {
            this.zzox = null;
            this.zzoe = -1;
            this.zzoy = new ArrayList<String>();
            this.zzor = (RoomUpdateListener)Preconditions.checkNotNull((Object)roomUpdateListener, (Object)"Must provide a RoomUpdateListener");
            this.zzou = null;
        }
        
        public final Builder addPlayersToInvite(@NonNull final ArrayList<String> list) {
            Preconditions.checkNotNull((Object)list);
            this.zzoy.addAll(list);
            return this;
        }
        
        public final Builder addPlayersToInvite(@NonNull final String... array) {
            Preconditions.checkNotNull((Object)array);
            this.zzoy.addAll(Arrays.asList(array));
            return this;
        }
        
        public final RoomConfig build() {
            return new zzd(this);
        }
        
        public final Builder setAutoMatchCriteria(final Bundle zzoz) {
            this.zzoz = zzoz;
            return this;
        }
        
        public final Builder setInvitationIdToAccept(final String zzox) {
            Preconditions.checkNotNull((Object)zzox);
            this.zzox = zzox;
            return this;
        }
        
        @Deprecated
        public final Builder setMessageReceivedListener(final RealTimeMessageReceivedListener zzot) {
            this.zzot = zzot;
            return this;
        }
        
        public final Builder setOnMessageReceivedListener(@NonNull final OnRealTimeMessageReceivedListener zzow) {
            this.zzow = zzow;
            return this;
        }
        
        public final Builder setRoomStatusUpdateCallback(@Nullable final RoomStatusUpdateCallback zzov) {
            this.zzov = zzov;
            return this;
        }
        
        @Deprecated
        public final Builder setRoomStatusUpdateListener(final RoomStatusUpdateListener zzos) {
            this.zzos = zzos;
            return this;
        }
        
        public final Builder setVariant(final int zzoe) {
            Preconditions.checkArgument(zzoe == -1 || zzoe > 0, (Object)"Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.zzoe = zzoe;
            return this;
        }
    }
}
