// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata;

final class zzag implements LoadGamesResult
{
    private final /* synthetic */ Status zzbc;
    
    zzag(final zzaf zzaf, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final GameBuffer getGames() {
        return new GameBuffer(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
