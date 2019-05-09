// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzat extends zzba
{
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ long zzbt;
    private final /* synthetic */ String zzbu;
    
    zzat(final zzam zzam, final GoogleApiClient googleApiClient, final String zzbq, final long zzbt, final String zzbu) {
        this.zzbq = zzbq;
        this.zzbt = zzbt;
        this.zzbu = zzbu;
        super(googleApiClient);
    }
}
