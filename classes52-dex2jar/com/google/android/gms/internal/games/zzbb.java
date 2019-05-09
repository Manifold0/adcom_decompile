// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzbb implements SubmitScoreResult
{
    private final /* synthetic */ Status zzbc;
    
    zzbb(final zzba zzba, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final ScoreSubmissionData getScoreData() {
        return new ScoreSubmissionData(DataHolder.empty(14));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
