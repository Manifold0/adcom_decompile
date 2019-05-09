package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.DriveResource.MetadataResult;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.zzj;
import java.util.ArrayList;
import java.util.Set;

public class zzdp implements DriveResource {
    protected final DriveId zzk;

    public zzdp(DriveId driveId) {
        this.zzk = driveId;
    }

    public PendingResult<Status> addChangeListener(GoogleApiClient googleApiClient, ChangeListener changeListener) {
        return ((zzaw) googleApiClient.getClient(Drive.CLIENT_KEY)).zza(googleApiClient, this.zzk, changeListener);
    }

    public PendingResult<Status> addChangeSubscription(GoogleApiClient googleApiClient) {
        zzaw zzaw = (zzaw) googleApiClient.getClient(Drive.CLIENT_KEY);
        zzj zzj = new zzj(1, this.zzk);
        Preconditions.checkArgument(zzj.zza(zzj.zzcy, zzj.zzk));
        Preconditions.checkState(zzaw.isConnected(), "Client must be connected");
        if (zzaw.zzea) {
            return googleApiClient.execute(new zzaz(zzaw, googleApiClient, zzj));
        }
        throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }

    public PendingResult<Status> delete(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzdu(this, googleApiClient));
    }

    public DriveId getDriveId() {
        return this.zzk;
    }

    public PendingResult<MetadataResult> getMetadata(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzdq(this, googleApiClient, false));
    }

    public PendingResult<MetadataBufferResult> listParents(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzdr(this, googleApiClient));
    }

    public PendingResult<Status> removeChangeListener(GoogleApiClient googleApiClient, ChangeListener changeListener) {
        return ((zzaw) googleApiClient.getClient(Drive.CLIENT_KEY)).zzb(googleApiClient, this.zzk, changeListener);
    }

    public PendingResult<Status> removeChangeSubscription(GoogleApiClient googleApiClient) {
        zzaw zzaw = (zzaw) googleApiClient.getClient(Drive.CLIENT_KEY);
        DriveId driveId = this.zzk;
        Preconditions.checkArgument(zzj.zza(1, driveId));
        Preconditions.checkState(zzaw.isConnected(), "Client must be connected");
        return googleApiClient.execute(new zzba(zzaw, googleApiClient, driveId, 1));
    }

    public PendingResult<Status> setParents(GoogleApiClient googleApiClient, Set<DriveId> set) {
        if (set != null) {
            return googleApiClient.execute(new zzds(this, googleApiClient, new ArrayList(set)));
        }
        throw new IllegalArgumentException("ParentIds must be provided.");
    }

    public PendingResult<Status> trash(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzdv(this, googleApiClient));
    }

    public PendingResult<Status> untrash(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzdw(this, googleApiClient));
    }

    public PendingResult<MetadataResult> updateMetadata(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        if (metadataChangeSet != null) {
            return googleApiClient.execute(new zzdt(this, googleApiClient, metadataChangeSet));
        }
        throw new IllegalArgumentException("ChangeSet must be provided.");
    }
}
