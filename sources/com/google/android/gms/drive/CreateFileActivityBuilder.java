package com.google.android.gms.drive;

import android.content.IntentSender;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.drive.zzbi;
import com.google.android.gms.internal.drive.zzt;

@Deprecated
public class CreateFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final zzt zzn = new zzt(0);
    @Nullable
    private DriveContents zzo;
    private boolean zzp;

    public IntentSender build(GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), "Client must be connected");
        zzf();
        return this.zzn.build(googleApiClient);
    }

    final int getRequestId() {
        return this.zzn.getRequestId();
    }

    public CreateFileActivityBuilder setActivityStartFolder(DriveId driveId) {
        this.zzn.zza(driveId);
        return this;
    }

    public CreateFileActivityBuilder setActivityTitle(String str) {
        this.zzn.zzc(str);
        return this;
    }

    public CreateFileActivityBuilder setInitialDriveContents(@Nullable DriveContents driveContents) {
        if (driveContents == null) {
            this.zzn.zzd(1);
        } else if (!(driveContents instanceof zzbi)) {
            throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
        } else if (driveContents.getDriveId() != null) {
            throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
        } else if (driveContents.zzj()) {
            throw new IllegalArgumentException("DriveContents are already closed.");
        } else {
            this.zzn.zzd(driveContents.zzh().zzj);
            this.zzo = driveContents;
        }
        this.zzp = true;
        return this;
    }

    public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet metadataChangeSet) {
        this.zzn.zza(metadataChangeSet);
        return this;
    }

    final MetadataChangeSet zzb() {
        return this.zzn.zzb();
    }

    final DriveId zzc() {
        return this.zzn.zzc();
    }

    final String zzd() {
        return this.zzn.zzd();
    }

    final int zze() {
        zzt zzt = this.zzn;
        return 0;
    }

    final void zzf() {
        Preconditions.checkState(this.zzp, "Must call setInitialDriveContents.");
        if (this.zzo != null) {
            this.zzo.zzi();
        }
        this.zzn.zzf();
    }
}
