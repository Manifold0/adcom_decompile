package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveApi.DriveIdResult;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.query.Query;

@Deprecated
public final class zzaf implements DriveApi {
    public final PendingResult<DriveIdResult> fetchDriveId(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzai(this, googleApiClient, str));
    }

    public final DriveFolder getAppFolder(GoogleApiClient googleApiClient) {
        zzaw zzaw = (zzaw) googleApiClient.getClient(Drive.CLIENT_KEY);
        if (zzaw.zzaf()) {
            DriveId zzae = zzaw.zzae();
            return zzae != null ? new zzbs(zzae) : null;
        } else {
            throw new IllegalStateException("Client is not yet connected");
        }
    }

    public final DriveFolder getRootFolder(GoogleApiClient googleApiClient) {
        zzaw zzaw = (zzaw) googleApiClient.getClient(Drive.CLIENT_KEY);
        if (zzaw.zzaf()) {
            DriveId zzad = zzaw.zzad();
            return zzad != null ? new zzbs(zzad) : null;
        } else {
            throw new IllegalStateException("Client is not yet connected");
        }
    }

    public final CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }

    public final PendingResult<DriveContentsResult> newDriveContents(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzah(this, googleApiClient, DriveFile.MODE_WRITE_ONLY));
    }

    public final OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }

    public final PendingResult<MetadataBufferResult> query(GoogleApiClient googleApiClient, Query query) {
        if (query != null) {
            return googleApiClient.enqueue(new zzag(this, googleApiClient, query));
        }
        throw new IllegalArgumentException("Query must be provided.");
    }

    public final PendingResult<Status> requestSync(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzaj(this, googleApiClient));
    }
}
