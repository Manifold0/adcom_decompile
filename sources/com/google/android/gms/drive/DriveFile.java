package com.google.android.gms.drive;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface DriveFile extends DriveResource {
    public static final int MODE_READ_ONLY = 268435456;
    public static final int MODE_READ_WRITE = 805306368;
    public static final int MODE_WRITE_ONLY = 536870912;

    @Deprecated
    public interface DownloadProgressListener {
        void onProgress(long j, long j2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OpenMode {
    }

    @Deprecated
    PendingResult<DriveContentsResult> open(GoogleApiClient googleApiClient, int i, @Nullable DownloadProgressListener downloadProgressListener);
}
