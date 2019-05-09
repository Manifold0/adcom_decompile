// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;

final class zzbz implements DriveFolderResult
{
    private final Status zzdw;
    private final DriveFolder zzfh;
    
    public zzbz(final Status zzdw, final DriveFolder zzfh) {
        this.zzdw = zzdw;
        this.zzfh = zzfh;
    }
    
    @Override
    public final DriveFolder getDriveFolder() {
        return this.zzfh;
    }
    
    public final Status getStatus() {
        return this.zzdw;
    }
}
