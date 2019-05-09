package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

public final class PublishOptions {
    public static final PublishOptions DEFAULT = new Builder().build();
    private final Strategy zzfk;
    @Nullable
    private final PublishCallback zzfl;

    public static class Builder {
        private Strategy zzfk = Strategy.DEFAULT;
        @Nullable
        private PublishCallback zzfl;

        public PublishOptions build() {
            return new PublishOptions(this.zzfk, this.zzfl);
        }

        public Builder setCallback(PublishCallback publishCallback) {
            this.zzfl = (PublishCallback) Preconditions.checkNotNull(publishCallback);
            return this;
        }

        public Builder setStrategy(Strategy strategy) {
            this.zzfk = (Strategy) Preconditions.checkNotNull(strategy);
            return this;
        }
    }

    private PublishOptions(Strategy strategy, @Nullable PublishCallback publishCallback) {
        this.zzfk = strategy;
        this.zzfl = publishCallback;
    }

    @Nullable
    public final PublishCallback getCallback() {
        return this.zzfl;
    }

    public final Strategy getStrategy() {
        return this.zzfk;
    }
}
