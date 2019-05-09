// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.drive.DriveId;

public final class zzf
{
    private final int status;
    private final int zzcr;
    private final DriveId zzk;
    
    public zzf(final zzh zzh) {
        this.zzk = zzh.zzk;
        this.zzcr = zzh.zzcr;
        this.status = zzh.status;
    }
    
    @Override
    public final boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (o == null || o.getClass() != this.getClass()) {
            b2 = false;
        }
        else {
            b2 = b;
            if (o != this) {
                final zzf zzf = (zzf)o;
                if (Objects.equal((Object)this.zzk, (Object)zzf.zzk) && this.zzcr == zzf.zzcr) {
                    b2 = b;
                    if (this.status == zzf.status) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzk, this.zzcr, this.status });
    }
    
    @Override
    public final String toString() {
        return String.format("FileTransferState[TransferType: %d, DriveId: %s, status: %d]", this.zzcr, this.zzk, this.status);
    }
}
