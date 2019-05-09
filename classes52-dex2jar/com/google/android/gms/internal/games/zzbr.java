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

final class zzbr extends zzbx
{
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjl;
    private final /* synthetic */ int[] zzjv;
    
    zzbr(final zzbo zzbo, final GoogleApiClient googleApiClient, final int[] zzjv, final int zzjl, final boolean zzjg) {
        this.zzjv = zzjv;
        this.zzjl = zzjl;
        this.zzjg = zzjg;
        super(googleApiClient, null);
    }
}
