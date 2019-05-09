package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.drive.events.zzk;
import com.google.android.gms.drive.events.zzm;
import java.util.Locale;

public final class zze implements zzk {
    private final zzm zzct;
    private final long zzcu;
    private final long zzcv;

    public zze(zzh zzh) {
        this.zzct = new zzf(zzh);
        this.zzcu = zzh.zzcu;
        this.zzcv = zzh.zzcv;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zze zze = (zze) obj;
        return Objects.equal(this.zzct, zze.zzct) && this.zzcu == zze.zzcu && this.zzcv == zze.zzcv;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zzcv), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv)});
    }

    public final String toString() {
        return String.format(Locale.US, "FileTransferProgress[FileTransferState: %s, BytesTransferred: %d, TotalBytes: %d]", new Object[]{this.zzct.toString(), Long.valueOf(this.zzcu), Long.valueOf(this.zzcv)});
    }
}
