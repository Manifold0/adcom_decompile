// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

final class zzds extends zzav
{
    private final /* synthetic */ List zzfz;
    private final /* synthetic */ zzdp zzgo;
    
    zzds(final zzdp zzgo, final GoogleApiClient googleApiClient, final List zzfz) {
        this.zzgo = zzgo;
        this.zzfz = zzfz;
        super(googleApiClient);
    }
}
