package com.google.android.gms.drive;

import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import java.io.InputStream;
import java.io.OutputStream;

public interface DriveContents {
    @Deprecated
    PendingResult<Status> commit(GoogleApiClient googleApiClient, @Nullable MetadataChangeSet metadataChangeSet);

    @Deprecated
    PendingResult<Status> commit(GoogleApiClient googleApiClient, @Nullable MetadataChangeSet metadataChangeSet, @Nullable ExecutionOptions executionOptions);

    @Deprecated
    void discard(GoogleApiClient googleApiClient);

    DriveId getDriveId();

    InputStream getInputStream();

    int getMode();

    OutputStream getOutputStream();

    ParcelFileDescriptor getParcelFileDescriptor();

    @Deprecated
    PendingResult<DriveContentsResult> reopenForWrite(GoogleApiClient googleApiClient);

    Contents zzh();

    void zzi();

    boolean zzj();
}
