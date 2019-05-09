// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.query.Query;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface DriveApi
{
    @Deprecated
    PendingResult<DriveIdResult> fetchDriveId(final GoogleApiClient p0, final String p1);
    
    @Deprecated
    @Nullable
    DriveFolder getAppFolder(final GoogleApiClient p0);
    
    @Deprecated
    @Nullable
    DriveFolder getRootFolder(final GoogleApiClient p0);
    
    @Deprecated
    CreateFileActivityBuilder newCreateFileActivityBuilder();
    
    @Deprecated
    PendingResult<DriveContentsResult> newDriveContents(final GoogleApiClient p0);
    
    @Deprecated
    OpenFileActivityBuilder newOpenFileActivityBuilder();
    
    @Deprecated
    PendingResult<MetadataBufferResult> query(final GoogleApiClient p0, final Query p1);
    
    @Deprecated
    PendingResult<Status> requestSync(final GoogleApiClient p0);
    
    @Deprecated
    public interface DriveContentsResult extends Result
    {
        DriveContents getDriveContents();
    }
    
    @Deprecated
    public interface DriveIdResult extends Result
    {
        DriveId getDriveId();
    }
    
    @Deprecated
    public interface MetadataBufferResult extends Releasable, Result
    {
        MetadataBuffer getMetadataBuffer();
    }
}
