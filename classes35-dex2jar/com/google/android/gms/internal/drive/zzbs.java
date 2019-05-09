// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.SearchableField;
import android.support.annotation.NonNull;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.metadata.internal.zzk;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveFolder;

public final class zzbs extends zzdp implements DriveFolder
{
    public zzbs(final DriveId driveId) {
        super(driveId);
    }
    
    static int zza(@Nullable final DriveContents driveContents, @Nullable final zzk zzk) {
        if (driveContents != null) {
            final int requestId = driveContents.zzh().getRequestId();
            driveContents.zzi();
            return requestId;
        }
        if (zzk != null && zzk.zzaz()) {
            return 0;
        }
        return 1;
    }
    
    static Query zza(@Nullable final Query query, @NonNull final DriveId driveId) {
        final Query.Builder addFilter = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, driveId));
        if (query != null) {
            if (query.getFilter() != null) {
                addFilter.addFilter(query.getFilter());
            }
            addFilter.setPageToken(query.getPageToken());
            addFilter.setSortOrder(query.getSortOrder());
        }
        return addFilter.build();
    }
    
    static void zzb(final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        final zzk zze = zzk.zze(set.getMimeType());
        if (zze != null) {
            int n;
            if (!zze.zzaz() && !zze.isFolder()) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n == 0) {
                throw new IllegalArgumentException("May not create shortcut files using this method. Use DriveFolder.createShortcutFile() instead.");
            }
        }
    }
    
    @Override
    public final PendingResult<DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, @Nullable final DriveContents driveContents) {
        return this.createFile(googleApiClient, set, driveContents, null);
    }
    
    @Override
    public final PendingResult<DriveFileResult> createFile(final GoogleApiClient googleApiClient, final MetadataChangeSet set, @Nullable final DriveContents driveContents, @Nullable ExecutionOptions build) {
        if (build == null) {
            build = new ExecutionOptions.Builder().build();
        }
        if (build.zzm() != 0) {
            throw new IllegalStateException("May not set a conflict strategy for new file creation.");
        }
        if (set == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        final zzk zze = com.google.android.gms.drive.metadata.internal.zzk.zze(set.getMimeType());
        if (zze != null && zze.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolder.createFolder() instead of mime type application/vnd.google-apps.folder");
        }
        if (build == null) {
            throw new IllegalArgumentException("ExecutionOptions must be provided");
        }
        build.zza(googleApiClient);
        if (driveContents != null) {
            if (!(driveContents instanceof zzbi)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            }
            if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            }
            if (driveContents.zzj()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
        }
        zzb(set);
        final int zza = zza(driveContents, com.google.android.gms.drive.metadata.internal.zzk.zze(set.getMimeType()));
        final zzk zze2 = com.google.android.gms.drive.metadata.internal.zzk.zze(set.getMimeType());
        boolean b;
        if (zze2 != null && zze2.zzaz()) {
            b = true;
        }
        else {
            b = false;
        }
        return (PendingResult<DriveFileResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbt(this, googleApiClient, set, zza, b ? 1 : 0, build));
    }
    
    @Override
    public final PendingResult<DriveFolderResult> createFolder(final GoogleApiClient googleApiClient, final MetadataChangeSet set) {
        if (set == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        if (set.getMimeType() != null && !set.getMimeType().equals("application/vnd.google-apps.folder")) {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
        return (PendingResult<DriveFolderResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbu(this, googleApiClient, set));
    }
    
    @Override
    public final PendingResult<DriveApi.MetadataBufferResult> listChildren(final GoogleApiClient googleApiClient) {
        return this.queryChildren(googleApiClient, null);
    }
    
    @Override
    public final PendingResult<DriveApi.MetadataBufferResult> queryChildren(final GoogleApiClient googleApiClient, final Query query) {
        return new zzaf().query(googleApiClient, zza(query, this.getDriveId()));
    }
}
