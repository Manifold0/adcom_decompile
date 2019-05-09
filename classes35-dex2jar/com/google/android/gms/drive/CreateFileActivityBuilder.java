// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.internal.drive.zzbi;
import com.google.android.gms.common.internal.Preconditions;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.drive.zzt;

@Deprecated
public class CreateFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final zzt zzn;
    @Nullable
    private DriveContents zzo;
    private boolean zzp;
    
    public CreateFileActivityBuilder() {
        this.zzn = new zzt(0);
    }
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), (Object)"Client must be connected");
        this.zzf();
        return this.zzn.build(googleApiClient);
    }
    
    final int getRequestId() {
        return this.zzn.getRequestId();
    }
    
    public CreateFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.zzn.zza(driveId);
        return this;
    }
    
    public CreateFileActivityBuilder setActivityTitle(final String s) {
        this.zzn.zzc(s);
        return this;
    }
    
    public CreateFileActivityBuilder setInitialDriveContents(@Nullable final DriveContents zzo) {
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
            this.zzn.zzd(zzo.zzh().zzj);
            this.zzo = zzo;
        }
        else {
            this.zzn.zzd(1);
        }
        this.zzp = true;
        return this;
    }
    
    public CreateFileActivityBuilder setInitialMetadata(final MetadataChangeSet set) {
        this.zzn.zza(set);
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
        final zzt zzn = this.zzn;
        return 0;
    }
    
    final void zzf() {
        Preconditions.checkState(this.zzp, (Object)"Must call setInitialDriveContents.");
        if (this.zzo != null) {
            this.zzo.zzi();
        }
        this.zzn.zzf();
    }
}
