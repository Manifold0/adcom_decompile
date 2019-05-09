package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig.Builder;

public final class zzb extends TurnBasedMatchConfig {
    private final int zzoe;
    private final Bundle zzoz;
    private final String[] zzpb;
    private final int zzpk;

    zzb(Builder builder) {
        this.zzoe = builder.zzoe;
        this.zzpk = builder.zzpk;
        this.zzoz = builder.zzoz;
        this.zzpb = (String[]) builder.zzoy.toArray(new String[builder.zzoy.size()]);
    }

    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }

    public final String[] getInvitedPlayerIds() {
        return this.zzpb;
    }

    public final int getVariant() {
        return this.zzoe;
    }

    public final int zzci() {
        return this.zzpk;
    }
}
