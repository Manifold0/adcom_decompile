// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveFolder extends DriveResource
{
    public static final String MIME_TYPE = "application/vnd.google-apps.folder";
    
    @Deprecated
    PendingResult<DriveFileResult> createFile(final GoogleApiClient p0, final MetadataChangeSet p1, @Nullable final DriveContents p2);
    
    @Deprecated
    PendingResult<DriveFileResult> createFile(final GoogleApiClient p0, final MetadataChangeSet p1, @Nullable final DriveContents p2, @Nullable final ExecutionOptions p3);
    
    @Deprecated
    PendingResult<DriveFolderResult> createFolder(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    @Deprecated
    PendingResult<DriveApi.MetadataBufferResult> listChildren(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<DriveApi.MetadataBufferResult> queryChildren(final GoogleApiClient p0, final Query p1);
    
    @Deprecated
    public interface DriveFileResult extends Result
    {
        DriveFile getDriveFile();
    }
    
    @Deprecated
    public interface DriveFolderResult extends Result
    {
        DriveFolder getDriveFolder();
    }
}
