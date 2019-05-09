package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult;
import com.google.android.gms.drive.FileUploadPreferences;

@Deprecated
public final class zzcb implements DrivePreferencesApi {
    public final PendingResult<FileUploadPreferencesResult> getFileUploadPreferences(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzcc(this, googleApiClient));
    }

    public final PendingResult<Status> setFileUploadPreferences(GoogleApiClient googleApiClient, FileUploadPreferences fileUploadPreferences) {
        if (fileUploadPreferences instanceof zzei) {
            return googleApiClient.execute(new zzcd(this, googleApiClient, (zzei) fileUploadPreferences));
        }
        throw new IllegalArgumentException("Invalid preference value");
    }
}
