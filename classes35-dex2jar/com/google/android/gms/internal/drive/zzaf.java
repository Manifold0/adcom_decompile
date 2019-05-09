// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveApi;

@Deprecated
public final class zzaf implements DriveApi
{
    @Override
    public final PendingResult<DriveIdResult> fetchDriveId(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<DriveIdResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzai(this, googleApiClient, s));
    }
    
    @Override
    public final DriveFolder getAppFolder(final GoogleApiClient googleApiClient) {
        final zzaw zzaw = (zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY);
        if (!zzaw.zzaf()) {
            throw new IllegalStateException("Client is not yet connected");
        }
        final DriveId zzae = zzaw.zzae();
        if (zzae != null) {
            return new zzbs(zzae);
        }
        return null;
    }
    
    @Override
    public final DriveFolder getRootFolder(final GoogleApiClient googleApiClient) {
        final zzaw zzaw = (zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY);
        if (!zzaw.zzaf()) {
            throw new IllegalStateException("Client is not yet connected");
        }
        final DriveId zzad = zzaw.zzad();
        if (zzad != null) {
            return new zzbs(zzad);
        }
        return null;
    }
    
    @Override
    public final CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }
    
    @Override
    public final PendingResult<DriveContentsResult> newDriveContents(final GoogleApiClient googleApiClient) {
        return (PendingResult<DriveContentsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzah(this, googleApiClient, 536870912));
    }
    
    @Override
    public final OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }
    
    @Override
    public final PendingResult<MetadataBufferResult> query(final GoogleApiClient googleApiClient, final Query query) {
        if (query == null) {
            throw new IllegalArgumentException("Query must be provided.");
        }
        return (PendingResult<MetadataBufferResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzag(this, googleApiClient, query));
    }
    
    @Override
    public final PendingResult<Status> requestSync(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzaj(this, googleApiClient));
    }
}
