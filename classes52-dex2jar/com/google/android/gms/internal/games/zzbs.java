// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbs extends zzbx
{
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String[] zzjw;
    
    zzbs(final zzbo zzbo, final GoogleApiClient googleApiClient, final boolean zzjg, final String[] zzjw) {
        this.zzjg = zzjg;
        this.zzjw = zzjw;
        super(googleApiClient, null);
    }
}
