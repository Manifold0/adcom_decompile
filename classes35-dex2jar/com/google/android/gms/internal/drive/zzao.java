// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;

final class zzao implements DriveIdResult
{
    private final Status zzdw;
    private final DriveId zzk;
    
    public zzao(final Status zzdw, final DriveId zzk) {
        this.zzdw = zzdw;
        this.zzk = zzk;
    }
    
    @Override
    public final DriveId getDriveId() {
        return this.zzk;
    }
    
    public final Status getStatus() {
        return this.zzdw;
    }
}
