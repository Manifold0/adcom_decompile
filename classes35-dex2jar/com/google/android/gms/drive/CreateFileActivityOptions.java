// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.drive.zzq;

public final class CreateFileActivityOptions extends zzq
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    
    private CreateFileActivityOptions(final MetadataBundle metadataBundle, final Integer n, final String s, final DriveId driveId, final int n2) {
        super(metadataBundle, n, s, driveId, n2);
    }
    
    public static class Builder
    {
        protected final CreateFileActivityBuilder builder;
        
        public Builder() {
            this.builder = new CreateFileActivityBuilder();
        }
        
        public CreateFileActivityOptions build() {
            this.builder.zzf();
            return new CreateFileActivityOptions(this.builder.zzb().zzp(), this.builder.getRequestId(), this.builder.zzd(), this.builder.zzc(), this.builder.zze(), null);
        }
        
        public Builder setActivityStartFolder(@NonNull final DriveId activityStartFolder) {
            this.builder.setActivityStartFolder(activityStartFolder);
            return this;
        }
        
        public Builder setActivityTitle(@NonNull final String activityTitle) {
            this.builder.setActivityTitle(activityTitle);
            return this;
        }
        
        public Builder setInitialDriveContents(@Nullable final DriveContents initialDriveContents) {
            this.builder.setInitialDriveContents(initialDriveContents);
            return this;
        }
        
        public Builder setInitialMetadata(@NonNull final MetadataChangeSet initialMetadata) {
            this.builder.setInitialMetadata(initialMetadata);
            return this;
        }
    }
}
