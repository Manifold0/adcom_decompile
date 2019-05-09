// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdn implements CancelMatchResult
{
    private final /* synthetic */ Status zzbc;
    private final /* synthetic */ zzdm zzko;
    
    zzdn(final zzdm zzko, final Status zzbc) {
        this.zzko = zzko;
        this.zzbc = zzbc;
    }
    
    @Override
    public final String getMatchId() {
        return this.zzko.zzji;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
