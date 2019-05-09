// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.util.Collection;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import android.os.Bundle;

public abstract class TurnBasedMatchConfig
{
    protected TurnBasedMatchConfig() {
    }
    
    public static Builder builder() {
        return new Builder(null);
    }
    
    public static Bundle createAutoMatchCriteria(final int n, final int n2, final long n3) {
        final Bundle bundle = new Bundle();
        bundle.putInt("min_automatch_players", n);
        bundle.putInt("max_automatch_players", n2);
        bundle.putLong("exclusive_bit_mask", n3);
        return bundle;
    }
    
    public abstract Bundle getAutoMatchCriteria();
    
    public abstract String[] getInvitedPlayerIds();
    
    public abstract int getVariant();
    
    public abstract int zzci();
    
    public static final class Builder
    {
        int zzoe;
        ArrayList<String> zzoy;
        Bundle zzoz;
        int zzpk;
        
        private Builder() {
            this.zzoe = -1;
            this.zzoy = new ArrayList<String>();
            this.zzoz = null;
            this.zzpk = 2;
        }
        
        public final Builder addInvitedPlayer(final String s) {
            Preconditions.checkNotNull((Object)s);
            this.zzoy.add(s);
            return this;
        }
        
        public final Builder addInvitedPlayers(final ArrayList<String> list) {
            Preconditions.checkNotNull((Object)list);
            this.zzoy.addAll(list);
            return this;
        }
        
        public final TurnBasedMatchConfig build() {
            return new zzb(this);
        }
        
        public final Builder setAutoMatchCriteria(final Bundle zzoz) {
            this.zzoz = zzoz;
            return this;
        }
        
        public final Builder setVariant(final int zzoe) {
            Preconditions.checkArgument(zzoe == -1 || zzoe > 0, (Object)"Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
            this.zzoe = zzoe;
            return this;
        }
    }
}
