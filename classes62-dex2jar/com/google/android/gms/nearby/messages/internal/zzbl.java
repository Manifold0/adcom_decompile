// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.Message;

final class zzbl extends zzbv
{
    private final /* synthetic */ Message zzil;
    private final /* synthetic */ zzbt zzim;
    private final /* synthetic */ PublishOptions zzin;
    
    zzbl(final zzbi zzbi, final GoogleApiClient googleApiClient, final Message zzil, final zzbt zzim, final PublishOptions zzin) {
        this.zzil = zzil;
        this.zzim = zzim;
        this.zzin = zzin;
        super(googleApiClient);
    }
}
