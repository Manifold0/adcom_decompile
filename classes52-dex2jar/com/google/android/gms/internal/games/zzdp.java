// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdp implements InitiateMatchResult
{
    private final /* synthetic */ Status zzbc;
    
    zzdp(final zzdo zzdo, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final TurnBasedMatch getMatch() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
