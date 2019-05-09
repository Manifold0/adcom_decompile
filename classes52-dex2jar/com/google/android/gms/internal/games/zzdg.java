// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.ParticipantResult;

final class zzdg extends zzdw
{
    private final /* synthetic */ String zzew;
    private final /* synthetic */ byte[] zzkj;
    private final /* synthetic */ String zzkk;
    private final /* synthetic */ ParticipantResult[] zzkl;
    
    zzdg(final zzdb zzdb, final GoogleApiClient googleApiClient, final String zzew, final byte[] zzkj, final String zzkk, final ParticipantResult[] zzkl) {
        this.zzew = zzew;
        this.zzkj = zzkj;
        this.zzkk = zzkk;
        this.zzkl = zzkl;
        super(googleApiClient, null);
    }
}
