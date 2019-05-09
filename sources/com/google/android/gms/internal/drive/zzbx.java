package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;

final class zzbx implements DriveFileResult {
    private final Status zzdw;
    private final DriveFile zzfg;

    public zzbx(Status status, DriveFile driveFile) {
        this.zzdw = status;
        this.zzfg = driveFile;
    }

    public final DriveFile getDriveFile() {
        return this.zzfg;
    }

    public final Status getStatus() {
        return this.zzdw;
    }
}
