// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.common.api.internal.TaskApiCall;

final class zzdh extends TaskApiCall<zzaw, DriveFile>
{
    private final DriveFolder zzfh;
    private final MetadataChangeSet zzga;
    private ExecutionOptions zzgb;
    private String zzgc;
    private zzk zzgd;
    private final DriveContents zzo;
    
    zzdh(@NonNull final DriveFolder zzfh, @NonNull final MetadataChangeSet zzga, @Nullable final DriveContents zzo, @NonNull final ExecutionOptions zzgb, @Nullable final String s) {
        this.zzfh = zzfh;
        this.zzga = zzga;
        this.zzo = zzo;
        this.zzgb = zzgb;
        this.zzgc = null;
        Preconditions.checkNotNull((Object)zzfh, (Object)"DriveFolder must not be null");
        Preconditions.checkNotNull((Object)zzfh.getDriveId(), (Object)"Folder's DriveId must not be null");
        Preconditions.checkNotNull((Object)zzga, (Object)"MetadataChangeSet must not be null");
        Preconditions.checkNotNull((Object)zzgb, (Object)"ExecutionOptions must not be null");
        this.zzgd = zzk.zze(zzga.getMimeType());
        if (this.zzgd != null && this.zzgd.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolderManagerClient#createFolder() instead of mime type application/vnd.google-apps.folder");
        }
        if (zzo != null) {
            if (!(zzo instanceof zzbi)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            }
            if (zzo.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            }
            if (zzo.zzj()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
        }
    }
}
