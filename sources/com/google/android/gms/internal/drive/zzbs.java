package com.google.android.gms.internal.drive;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFolder.DriveFileResult;
import com.google.android.gms.drive.DriveFolder.DriveFolderResult;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.Query.Builder;
import com.google.android.gms.drive.query.SearchableField;

public final class zzbs extends zzdp implements DriveFolder {
    public zzbs(DriveId driveId) {
        super(driveId);
    }

    static int zza(@Nullable DriveContents driveContents, @Nullable zzk zzk) {
        if (driveContents == null) {
            return (zzk == null || !zzk.zzaz()) ? 1 : 0;
        } else {
            int requestId = driveContents.zzh().getRequestId();
            driveContents.zzi();
            return requestId;
        }
    }

    static Query zza(@Nullable Query query, @NonNull DriveId driveId) {
        Builder addFilter = new Builder().addFilter(Filters.in(SearchableField.PARENTS, driveId));
        if (query != null) {
            if (query.getFilter() != null) {
                addFilter.addFilter(query.getFilter());
            }
            addFilter.setPageToken(query.getPageToken());
            addFilter.setSortOrder(query.getSortOrder());
        }
        return addFilter.build();
    }

    static void zzb(MetadataChangeSet metadataChangeSet) {
        if (metadataChangeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        }
        zzk zze = zzk.zze(metadataChangeSet.getMimeType());
        if (zze != null) {
            Object obj = (zze.zzaz() || zze.isFolder()) ? null : 1;
            if (obj == null) {
                throw new IllegalArgumentException("May not create shortcut files using this method. Use DriveFolder.createShortcutFile() instead.");
            }
        }
    }

    public final PendingResult<DriveFileResult> createFile(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents) {
        return createFile(googleApiClient, metadataChangeSet, driveContents, null);
    }

    public final PendingResult<DriveFileResult> createFile(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @Nullable ExecutionOptions executionOptions) {
        ExecutionOptions build = executionOptions == null ? new ExecutionOptions.Builder().build() : executionOptions;
        if (build.zzm() != 0) {
            throw new IllegalStateException("May not set a conflict strategy for new file creation.");
        } else if (metadataChangeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        } else {
            zzk zze = zzk.zze(metadataChangeSet.getMimeType());
            if (zze != null && zze.isFolder()) {
                throw new IllegalArgumentException("May not create folders using this method. Use DriveFolder.createFolder() instead of mime type application/vnd.google-apps.folder");
            } else if (build == null) {
                throw new IllegalArgumentException("ExecutionOptions must be provided");
            } else {
                build.zza(googleApiClient);
                if (driveContents != null) {
                    if (!(driveContents instanceof zzbi)) {
                        throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
                    } else if (driveContents.getDriveId() != null) {
                        throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
                    } else if (driveContents.zzj()) {
                        throw new IllegalArgumentException("DriveContents are already closed.");
                    }
                }
                zzb(metadataChangeSet);
                int zza = zza(driveContents, zzk.zze(metadataChangeSet.getMimeType()));
                zze = zzk.zze(metadataChangeSet.getMimeType());
                int i = (zze == null || !zze.zzaz()) ? 0 : 1;
                return googleApiClient.execute(new zzbt(this, googleApiClient, metadataChangeSet, zza, i, build));
            }
        }
    }

    public final PendingResult<DriveFolderResult> createFolder(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        if (metadataChangeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        } else if (metadataChangeSet.getMimeType() == null || metadataChangeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return googleApiClient.execute(new zzbu(this, googleApiClient, metadataChangeSet));
        } else {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
    }

    public final PendingResult<MetadataBufferResult> listChildren(GoogleApiClient googleApiClient) {
        return queryChildren(googleApiClient, null);
    }

    public final PendingResult<MetadataBufferResult> queryChildren(GoogleApiClient googleApiClient, Query query) {
        return new zzaf().query(googleApiClient, zza(query, getDriveId()));
    }
}
