// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface DrivePreferencesApi
{
    @Deprecated
    PendingResult<FileUploadPreferencesResult> getFileUploadPreferences(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<Status> setFileUploadPreferences(final GoogleApiClient p0, final FileUploadPreferences p1);
    
    @Deprecated
    public interface FileUploadPreferencesResult extends Result
    {
        FileUploadPreferences getFileUploadPreferences();
    }
}
