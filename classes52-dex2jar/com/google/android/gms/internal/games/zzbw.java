// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests;

final class zzbw implements ClaimMilestoneResult
{
    private final /* synthetic */ Status zzbc;
    
    zzbw(final zzbv zzbv, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final Milestone getMilestone() {
        return null;
    }
    
    @Override
    public final Quest getQuest() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
