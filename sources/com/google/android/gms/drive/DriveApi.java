package com.google.android.gms.drive;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.query.Query;

@Deprecated
public interface DriveApi {

    @Deprecated
    public interface DriveContentsResult extends Result {
        DriveContents getDriveContents();
    }

    @Deprecated
    public interface DriveIdResult extends Result {
        DriveId getDriveId();
    }

    @Deprecated
    public interface MetadataBufferResult extends Releasable, Result {
        MetadataBuffer getMetadataBuffer();
    }

    @Deprecated
    PendingResult<DriveIdResult> fetchDriveId(GoogleApiClient googleApiClient, String str);

    @Nullable
    @Deprecated
    DriveFolder getAppFolder(GoogleApiClient googleApiClient);

    @Nullable
    @Deprecated
    DriveFolder getRootFolder(GoogleApiClient googleApiClient);

    @Deprecated
    CreateFileActivityBuilder newCreateFileActivityBuilder();

    @Deprecated
    PendingResult<DriveContentsResult> newDriveContents(GoogleApiClient googleApiClient);

    @Deprecated
    OpenFileActivityBuilder newOpenFileActivityBuilder();

    @Deprecated
    PendingResult<MetadataBufferResult> query(GoogleApiClient googleApiClient, Query query);

    @Deprecated
    PendingResult<Status> requestSync(GoogleApiClient googleApiClient);
}
