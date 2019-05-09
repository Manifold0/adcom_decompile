package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.games.multiplayer.Multiplayer;
import java.util.ArrayList;

public abstract class TurnBasedMatchConfig {

    public static final class Builder {
        int zzoe;
        ArrayList<String> zzoy;
        Bundle zzoz;
        int zzpk;

        private Builder() {
            this.zzoe = -1;
            this.zzoy = new ArrayList();
            this.zzoz = null;
            this.zzpk = 2;
        }

        public final Builder addInvitedPlayer(String str) {
            Preconditions.checkNotNull(str);
            this.zzoy.add(str);
            return this;
        }

        public final Builder addInvitedPlayers(ArrayList<String> arrayList) {
            Preconditions.checkNotNull(arrayList);
            this.zzoy.addAll(arrayList);
            return this;
        }

        public final TurnBasedMatchConfig build() {
            return new zzb(this);
        }

        public final Builder setAutoMatchCriteria(Bundle bundle) {
            this.zzoz = bundle;
            return this;
        }

        public final Builder setVariant(int i) {
            boolean z = i == -1 || i > 0;
            Preconditions.checkArgument(z, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            this.zzoe = i;
            return this;
        }
    }

    protected TurnBasedMatchConfig() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Bundle createAutoMatchCriteria(int i, int i2, long j) {
        Bundle bundle = new Bundle();
        bundle.putInt(Multiplayer.EXTRA_MIN_AUTOMATCH_PLAYERS, i);
        bundle.putInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS, i2);
        bundle.putLong(Multiplayer.EXTRA_EXCLUSIVE_BIT_MASK, j);
        return bundle;
    }

    public abstract Bundle getAutoMatchCriteria();

    public abstract String[] getInvitedPlayerIds();

    public abstract int getVariant();

    public abstract int zzci();
}
