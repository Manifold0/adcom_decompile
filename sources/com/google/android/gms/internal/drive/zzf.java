package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.drive.DriveId;

public final class zzf {
    private final int status;
    private final int zzcr;
    private final DriveId zzk;

    public zzf(zzh zzh) {
        this.zzk = zzh.zzk;
        this.zzcr = zzh.zzcr;
        this.status = zzh.status;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zzf zzf = (zzf) obj;
        return Objects.equal(this.zzk, zzf.zzk) && this.zzcr == zzf.zzcr && this.status == zzf.status;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzk, Integer.valueOf(this.zzcr), Integer.valueOf(this.status)});
    }

    public final String toString() {
        return String.format("FileTransferState[TransferType: %d, DriveId: %s, status: %d]", new Object[]{Integer.valueOf(this.zzcr), this.zzk, Integer.valueOf(this.status)});
    }
}
