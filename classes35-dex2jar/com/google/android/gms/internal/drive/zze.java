// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.util.Locale;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.drive.events.zzm;
import com.google.android.gms.drive.events.zzk;

public final class zze implements zzk
{
    private final zzm zzct;
    private final long zzcu;
    private final long zzcv;
    
    public zze(final zzh zzh) {
        this.zzct = (zzm)new zzf(zzh);
        this.zzcu = zzh.zzcu;
        this.zzcv = zzh.zzcv;
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
                final zze zze = (zze)o;
                if (Objects.equal((Object)this.zzct, (Object)zze.zzct) && this.zzcu == zze.zzcu) {
                    b2 = b;
                    if (this.zzcv == zze.zzcv) {
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
        return Objects.hashCode(new Object[] { this.zzcv, this.zzcu, this.zzcv });
    }
    
    @Override
    public final String toString() {
        return String.format(Locale.US, "FileTransferProgress[FileTransferState: %s, BytesTransferred: %d, TotalBytes: %d]", this.zzct.toString(), this.zzcu, this.zzcv);
    }
}
