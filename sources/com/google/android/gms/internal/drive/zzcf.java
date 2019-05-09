package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult;
import com.google.android.gms.drive.FileUploadPreferences;

final class zzcf implements FileUploadPreferencesResult {
    private final Status zzdw;
    private final FileUploadPreferences zzfk;

    private zzcf(zzcb zzcb, Status status, FileUploadPreferences fileUploadPreferences) {
        this.zzdw = status;
        this.zzfk = fileUploadPreferences;
    }

    public final FileUploadPreferences getFileUploadPreferences() {
        return this.zzfk;
    }

    public final Status getStatus() {
        return this.zzdw;
    }
}
