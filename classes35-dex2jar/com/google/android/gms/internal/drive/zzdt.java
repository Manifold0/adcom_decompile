// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzdt extends zzea
{
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ zzdp zzgo;
    
    zzdt(final zzdp zzgo, final GoogleApiClient googleApiClient, final MetadataChangeSet zzfb) {
        this.zzgo = zzgo;
        this.zzfb = zzfb;
        super(zzgo, googleApiClient, null);
    }
}
