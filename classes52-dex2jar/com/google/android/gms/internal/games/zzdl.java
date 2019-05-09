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

final class zzdl extends zzdu
{
    private final /* synthetic */ int zzkm;
    private final /* synthetic */ int[] zzkn;
    
    zzdl(final zzdb zzdb, final GoogleApiClient googleApiClient, final int zzkm, final int[] zzkn) {
        this.zzkm = zzkm;
        this.zzkn = zzkn;
        super(googleApiClient, null);
    }
}
