// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;

final class zzcf implements FileUploadPreferencesResult
{
    private final Status zzdw;
    private final FileUploadPreferences zzfk;
    
    private zzcf(final zzcb zzcb, final Status zzdw, final FileUploadPreferences zzfk) {
        this.zzdw = zzdw;
        this.zzfk = zzfk;
    }
    
    @Override
    public final FileUploadPreferences getFileUploadPreferences() {
        return this.zzfk;
    }
    
    public final Status getStatus() {
        return this.zzdw;
    }
}
