package com.google.android.gms.drive;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.query.Query;

public interface DriveFolder extends DriveResource {
    public static final String MIME_TYPE = "application/vnd.google-apps.folder";

    @Deprecated
    public interface DriveFileResult extends Result {
        DriveFile getDriveFile();
    }

    @Deprecated
    public interface DriveFolderResult extends Result {
        DriveFolder getDriveFolder();
    }

    @Deprecated
    PendingResult<DriveFileResult> createFile(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents);

    @Deprecated
    PendingResult<DriveFileResult> createFile(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @Nullable ExecutionOptions executionOptions);

    @Deprecated
    PendingResult<DriveFolderResult> createFolder(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet);

    @Deprecated
    PendingResult<MetadataBufferResult> listChildren(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<MetadataBufferResult> queryChildren(GoogleApiClient googleApiClient, Query query);
}
