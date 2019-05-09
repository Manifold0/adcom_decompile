package com.google.android.gms.drive;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.drive.zzq;

public final class CreateFileActivityOptions extends zzq {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";

    public static class Builder {
        protected final CreateFileActivityBuilder builder = new CreateFileActivityBuilder();

        public CreateFileActivityOptions build() {
            this.builder.zzf();
            return new CreateFileActivityOptions(this.builder.zzb().zzp(), Integer.valueOf(this.builder.getRequestId()), this.builder.zzd(), this.builder.zzc(), this.builder.zze());
        }

        public Builder setActivityStartFolder(@NonNull DriveId driveId) {
            this.builder.setActivityStartFolder(driveId);
            return this;
        }

        public Builder setActivityTitle(@NonNull String str) {
            this.builder.setActivityTitle(str);
            return this;
        }

        public Builder setInitialDriveContents(@Nullable DriveContents driveContents) {
            this.builder.setInitialDriveContents(driveContents);
            return this;
        }

        public Builder setInitialMetadata(@NonNull MetadataChangeSet metadataChangeSet) {
            this.builder.setInitialMetadata(metadataChangeSet);
            return this;
        }
    }

    private CreateFileActivityOptions(MetadataBundle metadataBundle, Integer num, String str, DriveId driveId, int i) {
        super(metadataBundle, num, str, driveId, i);
    }
}
