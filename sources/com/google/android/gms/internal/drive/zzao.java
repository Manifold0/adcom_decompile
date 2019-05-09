package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.DriveIdResult;
import com.google.android.gms.drive.DriveId;

final class zzao implements DriveIdResult {
    private final Status zzdw;
    private final DriveId zzk;

    public zzao(Status status, DriveId driveId) {
        this.zzdw = status;
        this.zzk = driveId;
    }

    public final DriveId getDriveId() {
        return this.zzk;
    }

    public final Status getStatus() {
        return this.zzdw;
    }
}
