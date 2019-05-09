package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.multiplayer.Multiplayer;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class RoomConfig {

    public static final class Builder {
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

        private Builder(@NonNull RoomUpdateCallback roomUpdateCallback) {
            this.zzox = null;
            this.zzoe = -1;
            this.zzoy = new ArrayList();
            this.zzou = (RoomUpdateCallback) Preconditions.checkNotNull(roomUpdateCallback, "Must provide a RoomUpdateCallback");
            this.zzor = null;
        }

        @Deprecated
        private Builder(RoomUpdateListener roomUpdateListener) {
            this.zzox = null;
            this.zzoe = -1;
            this.zzoy = new ArrayList();
            this.zzor = (RoomUpdateListener) Preconditions.checkNotNull(roomUpdateListener, "Must provide a RoomUpdateListener");
            this.zzou = null;
        }

        public final Builder addPlayersToInvite(@NonNull ArrayList<String> arrayList) {
            Preconditions.checkNotNull(arrayList);
            this.zzoy.addAll(arrayList);
            return this;
        }

        public final Builder addPlayersToInvite(@NonNull String... strArr) {
            Preconditions.checkNotNull(strArr);
            this.zzoy.addAll(Arrays.asList(strArr));
            return this;
        }

        public final RoomConfig build() {
            return new zzd(this);
        }

        public final Builder setAutoMatchCriteria(Bundle bundle) {
            this.zzoz = bundle;
            return this;
        }

        public final Builder setInvitationIdToAccept(String str) {
            Preconditions.checkNotNull(str);
            this.zzox = str;
            return this;
        }

        @Deprecated
        public final Builder setMessageReceivedListener(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            this.zzot = realTimeMessageReceivedListener;
            return this;
        }

        public final Builder setOnMessageReceivedListener(@NonNull OnRealTimeMessageReceivedListener onRealTimeMessageReceivedListener) {
            this.zzow = onRealTimeMessageReceivedListener;
            return this;
        }

        public final Builder setRoomStatusUpdateCallback(@Nullable RoomStatusUpdateCallback roomStatusUpdateCallback) {
            this.zzov = roomStatusUpdateCallback;
            return this;
        }

        @Deprecated
        public final Builder setRoomStatusUpdateListener(RoomStatusUpdateListener roomStatusUpdateListener) {
            this.zzos = roomStatusUpdateListener;
            return this;
        }

        public final Builder setVariant(int i) {
            boolean z = i == -1 || i > 0;
            Preconditions.checkArgument(z, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.zzoe = i;
            return this;
        }
    }

    protected RoomConfig() {
    }

    public static Builder builder(@NonNull RoomUpdateCallback roomUpdateCallback) {
        return new Builder(roomUpdateCallback);
    }

    @Deprecated
    public static Builder builder(RoomUpdateListener roomUpdateListener) {
        return new Builder(roomUpdateListener);
    }

    public static Bundle createAutoMatchCriteria(int i, int i2, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, i);
        bundle.putInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, i2);
        bundle.putLong(Multiplayer.EXTRA_EXCLUSIVE_BIT_MASK, j);
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
}
