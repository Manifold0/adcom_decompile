// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DrivePreferencesApi;

abstract class zzcg extends zzau<DrivePreferencesApi.FileUploadPreferencesResult>
{
    private final /* synthetic */ zzcb zzfi;
    
    public zzcg(final zzcb zzfi, final GoogleApiClient googleApiClient) {
        this.zzfi = zzfi;
        super(googleApiClient);
    }
}
