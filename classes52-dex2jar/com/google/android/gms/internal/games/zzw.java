// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.os.RemoteException;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzw extends zzz
{
    private final /* synthetic */ boolean zzjg;
    private final /* synthetic */ String[] zzjk;
    
    zzw(final zzv zzv, final GoogleApiClient googleApiClient, final boolean zzjg, final String[] zzjk) {
        this.zzjg = zzjg;
        this.zzjk = zzjk;
        super(googleApiClient, null);
    }
}
