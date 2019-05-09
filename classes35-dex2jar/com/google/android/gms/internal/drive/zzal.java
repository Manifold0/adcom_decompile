// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.Releasable;

final class zzal implements Releasable, DriveContentsResult
{
    private final Status zzdw;
    private final DriveContents zzo;
    
    public zzal(final Status zzdw, final DriveContents zzo) {
        this.zzdw = zzdw;
        this.zzo = zzo;
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
