// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;

public final class zzb extends TurnBasedMatchConfig
{
    private final int zzoe;
    private final Bundle zzoz;
    private final String[] zzpb;
    private final int zzpk;
    
    zzb(final Builder builder) {
        this.zzoe = builder.zzoe;
        this.zzpk = builder.zzpk;
        this.zzoz = builder.zzoz;
        this.zzpb = builder.zzoy.toArray(new String[builder.zzoy.size()]);
    }
    
    @Override
    public final Bundle getAutoMatchCriteria() {
        return this.zzoz;
    }
    
    @Override
    public final String[] getInvitedPlayerIds() {
        return this.zzpb;
    }
    
    @Override
    public final int getVariant() {
        return this.zzoe;
    }
    
    @Override
    public final int zzci() {
        return this.zzpk;
    }
}
