package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

public final class SubscribeOptions {
    public static final SubscribeOptions DEFAULT = new Builder().build();
    private final Strategy zzfk;
    private final MessageFilter zzfz;
    @Nullable
    private final SubscribeCallback zzga;
    public final boolean zzgb;
    public final int zzgc;

    public static class Builder {
        private Strategy zzfk = Strategy.DEFAULT;
        private MessageFilter zzfz = MessageFilter.INCLUDE_ALL_MY_TYPES;
        @Nullable
        private SubscribeCallback zzga;

        public SubscribeOptions build() {
            return new SubscribeOptions(this.zzfk, this.zzfz, this.zzga);
        }

        public Builder setCallback(SubscribeCallback subscribeCallback) {
            this.zzga = (SubscribeCallback) Preconditions.checkNotNull(subscribeCallback);
            return this;
        }

        public Builder setFilter(MessageFilter messageFilter) {
            this.zzfz = messageFilter;
            return this;
        }

        public Builder setStrategy(Strategy strategy) {
            this.zzfk = strategy;
            return this;
        }
    }

    private SubscribeOptions(Strategy strategy, MessageFilter messageFilter, @Nullable SubscribeCallback subscribeCallback, boolean z, int i) {
        this.zzfk = strategy;
        this.zzfz = messageFilter;
        this.zzga = subscribeCallback;
        this.zzgb = z;
        this.zzgc = i;
    }

    @Nullable
    public final SubscribeCallback getCallback() {
        return this.zzga;
    }

    public final MessageFilter getFilter() {
        return this.zzfz;
    }

    public final Strategy getStrategy() {
        return this.zzfk;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzfk);
        String valueOf2 = String.valueOf(this.zzfz);
        return new StringBuilder((String.valueOf(valueOf).length() + 36) + String.valueOf(valueOf2).length()).append("SubscribeOptions{strategy=").append(valueOf).append(", filter=").append(valueOf2).append('}').toString();
    }
}
