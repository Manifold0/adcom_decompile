// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.query.Query;

final class zzag extends zzar
{
    private final /* synthetic */ Query zzds;
    
    zzag(final zzaf zzaf, final GoogleApiClient googleApiClient, final Query zzds) {
        this.zzds = zzds;
        super(googleApiClient);
    }
}
