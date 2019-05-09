// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.Players;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzbg extends zzbm
{
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String zzjr;
    
    zzbg(final zzbe zzbe, final GoogleApiClient googleApiClient, final String zzjr, final boolean zzjg) {
        this.zzjr = zzjr;
        this.zzjg = zzjg;
        super(googleApiClient);
    }
}
