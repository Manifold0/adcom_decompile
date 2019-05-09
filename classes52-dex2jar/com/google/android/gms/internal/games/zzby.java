// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests;

final class zzby implements LoadQuestsResult
{
    private final /* synthetic */ Status zzbc;
    
    zzby(final zzbx zzbx, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final QuestBuffer getQuests() {
        return new QuestBuffer(DataHolder.empty(this.zzbc.getStatusCode()));
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
