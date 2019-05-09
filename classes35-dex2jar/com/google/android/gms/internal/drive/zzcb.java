// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DrivePreferencesApi;

@Deprecated
public final class zzcb implements DrivePreferencesApi
{
    @Override
    public final PendingResult<FileUploadPreferencesResult> getFileUploadPreferences(final GoogleApiClient googleApiClient) {
        return (PendingResult<FileUploadPreferencesResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzcc(this, googleApiClient));
    }
    
    @Override
    public final PendingResult<Status> setFileUploadPreferences(final GoogleApiClient googleApiClient, final FileUploadPreferences fileUploadPreferences) {
        if (!(fileUploadPreferences instanceof zzei)) {
            throw new IllegalArgumentException("Invalid preference value");
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcd(this, googleApiClient, (zzei)fileUploadPreferences));
    }
}
