// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFile;

public final class zzbn extends zzdp implements DriveFile
{
    public zzbn(final DriveId driveId) {
        super(driveId);
    }
    
    @Override
    public final PendingResult<DriveApi.DriveContentsResult> open(final GoogleApiClient googleApiClient, final int n, @Nullable final DownloadProgressListener downloadProgressListener) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        DownloadProgressListener downloadProgressListener2;
        if (downloadProgressListener == null) {
            downloadProgressListener2 = null;
        }
        else {
            downloadProgressListener2 = new zzbp((ListenerHolder<DownloadProgressListener>)googleApiClient.registerListener((Object)downloadProgressListener));
        }
        return (PendingResult<DriveApi.DriveContentsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbo(this, googleApiClient, n, downloadProgressListener2));
    }
}
