// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.MetadataChangeSet;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Set;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;

public class zzdp implements DriveResource
{
    protected final DriveId zzk;
    
    public zzdp(final DriveId zzk) {
        this.zzk = zzk;
    }
    
    @Override
    public PendingResult<Status> addChangeListener(final GoogleApiClient googleApiClient, final ChangeListener changeListener) {
        return ((zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY)).zza(googleApiClient, this.zzk, changeListener);
    }
    
    @Override
    public PendingResult<Status> addChangeSubscription(final GoogleApiClient googleApiClient) {
        final zzaw zzaw = (zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY);
        final zzj zzj = new zzj(1, this.zzk);
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(zzj.zzcy, zzj.zzk));
        Preconditions.checkState(zzaw.isConnected(), (Object)"Client must be connected");
        if (!zzaw.zzea) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzaz(zzaw, googleApiClient, zzj));
    }
    
    @Override
    public PendingResult<Status> delete(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdu(this, googleApiClient));
    }
    
    @Override
    public DriveId getDriveId() {
        return this.zzk;
    }
    
    @Override
    public PendingResult<MetadataResult> getMetadata(final GoogleApiClient googleApiClient) {
        return (PendingResult<MetadataResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzdq(this, googleApiClient, false));
    }
    
    @Override
    public PendingResult<DriveApi.MetadataBufferResult> listParents(final GoogleApiClient googleApiClient) {
        return (PendingResult<DriveApi.MetadataBufferResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzdr(this, googleApiClient));
    }
    
    @Override
    public PendingResult<Status> removeChangeListener(final GoogleApiClient googleApiClient, final ChangeListener changeListener) {
        return ((zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY)).zzb(googleApiClient, this.zzk, changeListener);
    }
    
    @Override
    public PendingResult<Status> removeChangeSubscription(final GoogleApiClient googleApiClient) {
        final zzaw zzaw = (zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY);
        final DriveId zzk = this.zzk;
        Preconditions.checkArgument(com.google.android.gms.drive.events.zzj.zza(1, zzk));
        Preconditions.checkState(zzaw.isConnected(), (Object)"Client must be connected");
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzba(zzaw, googleApiClient, zzk, 1));
    }
    
    @Override
    public PendingResult<Status> setParents(final GoogleApiClient googleApiClient, final Set<DriveId> set) {
        if (set == null) {
            throw new IllegalArgumentException("ParentIds must be provided.");
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzds(this, googleApiClient, new ArrayList(set)));
    }
    
    @Override
    public PendingResult<Status> trash(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdv(this, googleApiClient));
    }
    
    @Override
    public PendingResult<Status> untrash(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdw(this, googleApiClient));
    }
    
    @Override
    public PendingResult<MetadataResult> updateMetadata(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("ChangeSet must be provided.");
        }
        return (PendingResult<MetadataResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzdt(this, googleApiClient, set));
    }
}
