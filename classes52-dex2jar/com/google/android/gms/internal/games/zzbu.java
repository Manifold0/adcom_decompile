// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests;

final class zzbu implements AcceptQuestResult
{
    private final /* synthetic */ Status zzbc;
    
    zzbu(final zzbt zzbt, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final Quest getQuest() {
        return null;
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
}
