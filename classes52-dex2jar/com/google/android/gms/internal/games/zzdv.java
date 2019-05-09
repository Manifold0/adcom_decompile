// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.Bundle;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzdv implements LoadMatchesResult
{
    private final /* synthetic */ Status zzbc;
    
    zzdv(final zzdu zzdu, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final LoadMatchesResponse getMatches() {
        return new LoadMatchesResponse(new Bundle());
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
