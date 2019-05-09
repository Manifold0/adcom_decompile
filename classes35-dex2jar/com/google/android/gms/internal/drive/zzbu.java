// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbu extends zzca
{
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ zzbs zzff;
    
    zzbu(final zzbs zzff, final GoogleApiClient googleApiClient, final MetadataChangeSet zzfb) {
        this.zzff = zzff;
        this.zzfb = zzfb;
        super(googleApiClient);
    }
}
