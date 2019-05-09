// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.api.Result;
import java.util.Set;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveResource
{
    @Deprecated
    PendingResult<Status> addChangeListener(final GoogleApiClient p0, final ChangeListener p1);
    
    @Deprecated
    PendingResult<Status> addChangeSubscription(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<Status> delete(final GoogleApiClient p0);
    
    DriveId getDriveId();
    
    @Deprecated
    PendingResult<MetadataResult> getMetadata(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<DriveApi.MetadataBufferResult> listParents(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<Status> removeChangeListener(final GoogleApiClient p0, final ChangeListener p1);
    
    @Deprecated
    PendingResult<Status> removeChangeSubscription(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<Status> setParents(final GoogleApiClient p0, final Set<DriveId> p1);
    
    @Deprecated
    PendingResult<Status> trash(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<Status> untrash(final GoogleApiClient p0);
    
    @Deprecated
    PendingResult<MetadataResult> updateMetadata(final GoogleApiClient p0, final MetadataChangeSet p1);
    
    @Deprecated
    public interface MetadataResult extends Result
    {
        Metadata getMetadata();
    }
}
