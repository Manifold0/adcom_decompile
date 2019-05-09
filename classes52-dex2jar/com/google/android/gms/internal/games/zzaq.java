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

final class zzaq extends zzay
{
    private final /* synthetic */ String zzbq;
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ int zzjm;
    private final /* synthetic */ int zzjn;
    private final /* synthetic */ int zzjo;
    
    zzaq(final zzam zzam, final GoogleApiClient googleApiClient, final String zzbq, final int zzjm, final int zzjn, final int zzjo, final boolean zzjg) {
        this.zzbq = zzbq;
        this.zzjm = zzjm;
        this.zzjn = zzjn;
        this.zzjo = zzjo;
        this.zzjg = zzjg;
        super(googleApiClient, null);
    }
}
