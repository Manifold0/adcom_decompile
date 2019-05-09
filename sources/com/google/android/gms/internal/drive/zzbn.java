package com.google.android.gms.internal.drive;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFile.DownloadProgressListener;
import com.google.android.gms.drive.DriveId;

public final class zzbn extends zzdp implements DriveFile {
    public zzbn(DriveId driveId) {
        super(driveId);
    }

    public final PendingResult<DriveContentsResult> open(GoogleApiClient googleApiClient, int i, @Nullable DownloadProgressListener downloadProgressListener) {
        if (i == DriveFile.MODE_READ_ONLY || i == DriveFile.MODE_WRITE_ONLY || i == DriveFile.MODE_READ_WRITE) {
            return googleApiClient.enqueue(new zzbo(this, googleApiClient, i, downloadProgressListener == null ? null : new zzbp(googleApiClient.registerListener(downloadProgressListener))));
        }
        throw new IllegalArgumentException("Invalid mode provided.");
    }
}
