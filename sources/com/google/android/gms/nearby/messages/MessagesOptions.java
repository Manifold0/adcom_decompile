package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public class MessagesOptions implements Optional {
    @Nullable
    public final String zzff;
    public final boolean zzfg;
    public final int zzfh;
    public final String zzfi;
    public final String zzfj;

    public static class Builder {
        private int zzfh = -1;

        public MessagesOptions build() {
            return new MessagesOptions();
        }

        public Builder setPermissions(int i) {
            this.zzfh = i;
            return this;
        }
    }

    private MessagesOptions(Builder builder) {
        this.zzff = null;
        this.zzfg = false;
        this.zzfh = builder.zzfh;
        this.zzfi = null;
        this.zzfj = null;
    }
}
