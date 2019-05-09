package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;

final class zzal implements Releasable, DriveContentsResult {
    private final Status zzdw;
    private final DriveContents zzo;

    public zzal(Status status, DriveContents driveContents) {
        this.zzdw = status;
        this.zzo = driveContents;
    }

    public final DriveContents getDriveContents() {
        return this.zzo;
    }

    public final Status getStatus() {
        return this.zzdw;
    }

    public final void release() {
        if (this.zzo != null) {
            this.zzo.zzi();
        }
    }
}
