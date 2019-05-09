// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveContents
{
    @Deprecated
    PendingResult<Status> commit(final GoogleApiClient p0, @Nullable final MetadataChangeSet p1);
    
    @Deprecated
    PendingResult<Status> commit(final GoogleApiClient p0, @Nullable final MetadataChangeSet p1, @Nullable final ExecutionOptions p2);
    
    @Deprecated
    void discard(final GoogleApiClient p0);
    
    DriveId getDriveId();
    
    InputStream getInputStream();
    
    int getMode();
    
    OutputStream getOutputStream();
    
    ParcelFileDescriptor getParcelFileDescriptor();
    
    @Deprecated
    PendingResult<DriveApi.DriveContentsResult> reopenForWrite(final GoogleApiClient p0);
    
    Contents zzh();
    
    void zzi();
    
    boolean zzj();
}
