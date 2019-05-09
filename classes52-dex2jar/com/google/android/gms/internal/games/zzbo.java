// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.quest.Quests;

public final class zzbo implements Quests
{
    @Override
    public final PendingResult<AcceptQuestResult> accept(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<AcceptQuestResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbp(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<ClaimMilestoneResult> claim(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return (PendingResult<ClaimMilestoneResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbq(this, googleApiClient, s, s2));
    }
    
    @Override
    public final Intent getQuestIntent(final GoogleApiClient googleApiClient, final String s) {
        return Games.zza(googleApiClient).zzd(s);
    }
    
    @Override
    public final Intent getQuestsIntent(final GoogleApiClient googleApiClient, final int[] array) {
        return Games.zza(googleApiClient).zza(array);
    }
    
    @Override
    public final PendingResult<LoadQuestsResult> load(final GoogleApiClient googleApiClient, final int[] array, final int n, final boolean b) {
        return (PendingResult<LoadQuestsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbr(this, googleApiClient, array, n, b));
    }
    
    @Override
    public final PendingResult<LoadQuestsResult> loadByIds(final GoogleApiClient googleApiClient, final boolean b, final String... array) {
        return (PendingResult<LoadQuestsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbs(this, googleApiClient, b, array));
    }
    
    @Override
    public final void registerQuestUpdateListener(final GoogleApiClient googleApiClient, final QuestUpdateListener questUpdateListener) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze((ListenerHolder<QuestUpdateListener>)googleApiClient.registerListener((Object)questUpdateListener));
        }
    }
    
    @Override
    public final void showStateChangedPopup(final GoogleApiClient googleApiClient, final String s) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zze(s);
        }
    }
    
    @Override
    public final void unregisterQuestUpdateListener(final GoogleApiClient googleApiClient) {
        final zze zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzae();
        }
    }
}
