// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;

final class zzbx implements DriveFileResult
{
    private final Status zzdw;
    private final DriveFile zzfg;
    
    public zzbx(final Status zzdw, final DriveFile zzfg) {
        this.zzdw = zzdw;
        this.zzfg = zzfg;
    }
    
    @Override
    public final DriveFile getDriveFile() {
        return this.zzfg;
    }
    
    public final Status getStatus() {
        return this.zzdw;
    }
}