// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.internal.ICancelToken$Stub;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

final class zzbo extends zzam
{
    private final /* synthetic */ int zzdt;
    private final /* synthetic */ DriveFile.DownloadProgressListener zzew;
    private final /* synthetic */ zzbn zzex;
    
    zzbo(final zzbn zzex, final GoogleApiClient googleApiClient, final int zzdt, final DriveFile.DownloadProgressListener zzew) {
        this.zzex = zzex;
        this.zzdt = zzdt;
        this.zzew = zzew;
        super(googleApiClient);
    }
}
