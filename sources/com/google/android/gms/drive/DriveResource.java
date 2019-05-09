package com.google.android.gms.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.events.ChangeListener;
import java.util.Set;

public interface DriveResource {

    @Deprecated
    public interface MetadataResult extends Result {
        Metadata getMetadata();
    }

    @Deprecated
    PendingResult<Status> addChangeListener(GoogleApiClient googleApiClient, ChangeListener changeListener);

    @Deprecated
    PendingResult<Status> addChangeSubscription(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<Status> delete(GoogleApiClient googleApiClient);

    DriveId getDriveId();

    @Deprecated
    PendingResult<MetadataResult> getMetadata(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<MetadataBufferResult> listParents(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<Status> removeChangeListener(GoogleApiClient googleApiClient, ChangeListener changeListener);

    @Deprecated
    PendingResult<Status> removeChangeSubscription(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<Status> setParents(GoogleApiClient googleApiClient, Set<DriveId> set);

    @Deprecated
    PendingResult<Status> trash(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<Status> untrash(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<MetadataResult> updateMetadata(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet);
}
