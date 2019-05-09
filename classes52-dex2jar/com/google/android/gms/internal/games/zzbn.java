// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players;

final class zzbn implements LoadPlayersResult
{
    private final /* synthetic */ Status zzbc;
    
    zzbn(final zzbm zzbm, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final PlayerBuffer getPlayers() {
        return new PlayerBuffer(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
