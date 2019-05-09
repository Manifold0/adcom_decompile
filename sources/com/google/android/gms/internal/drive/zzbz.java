package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFolder.DriveFolderResult;

final class zzbz implements DriveFolderResult {
    private final Status zzdw;
    private final DriveFolder zzfh;

    public zzbz(Status status, DriveFolder driveFolder) {
        this.zzdw = status;
        this.zzfh = driveFolder;
    }

    public final DriveFolder getDriveFolder() {
        return this.zzfh;
    }

    public final Status getStatus() {
        return this.zzdw;
    }
}
