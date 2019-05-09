// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbt extends zzby
{
    private final /* synthetic */ MetadataChangeSet zzfb;
    private final /* synthetic */ int zzfc;
    private final /* synthetic */ int zzfd;
    private final /* synthetic */ ExecutionOptions zzfe;
    private final /* synthetic */ zzbs zzff;
    
    zzbt(final zzbs zzff, final GoogleApiClient googleApiClient, final MetadataChangeSet zzfb, final int zzfc, final int zzfd, final ExecutionOptions zzfe) {
        this.zzff = zzff;
        this.zzfb = zzfb;
        this.zzfc = zzfc;
        this.zzfd = zzfd;
        this.zzfe = zzfe;
        super(googleApiClient);
    }
}
