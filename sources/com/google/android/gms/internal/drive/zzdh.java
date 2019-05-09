package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzdh extends TaskApiCall<zzaw, DriveFile> {
    private final DriveFolder zzfh;
    private final MetadataChangeSet zzga;
    private ExecutionOptions zzgb;
    private String zzgc = null;
    private zzk zzgd;
    private final DriveContents zzo;

    zzdh(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @NonNull ExecutionOptions executionOptions, @Nullable String str) {
        this.zzfh = driveFolder;
        this.zzga = metadataChangeSet;
        this.zzo = driveContents;
        this.zzgb = executionOptions;
        Preconditions.checkNotNull(driveFolder, "DriveFolder must not be null");
        Preconditions.checkNotNull(driveFolder.getDriveId(), "Folder's DriveId must not be null");
        Preconditions.checkNotNull(metadataChangeSet, "MetadataChangeSet must not be null");
        Preconditions.checkNotNull(executionOptions, "ExecutionOptions must not be null");
        this.zzgd = zzk.zze(metadataChangeSet.getMimeType());
        if (this.zzgd != null && this.zzgd.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolderManagerClient#createFolder() instead of mime type application/vnd.google-apps.folder");
        } else if (driveContents == null) {
        } else {
            if (!(driveContents instanceof zzbi)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            } else if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            } else if (driveContents.zzj()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
        }
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        this.zzgb.zza(zzaw);
        MetadataChangeSet metadataChangeSet = this.zzga;
        metadataChangeSet.zzp().zza(zzaw.getContext());
        int zza = zzbs.zza(this.zzo, this.zzgd);
        int i = (this.zzgd == null || !this.zzgd.zzaz()) ? 0 : 1;
        ((zzeo) zzaw.getService()).zza(new zzw(this.zzfh.getDriveId(), metadataChangeSet.zzp(), zza, i, this.zzgb), new zzhd(taskCompletionSource));
    }
}
