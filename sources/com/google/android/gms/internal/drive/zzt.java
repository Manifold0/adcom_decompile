package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;

@Deprecated
public final class zzt {
    private String zzay;
    private DriveId zzbb;
    private Integer zzdi;
    private final int zzdj = 0;
    private MetadataChangeSet zzdk;

    public zzt(int i) {
    }

    public final IntentSender build(GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), "Client must be connected");
        zzf();
        zzaw zzaw = (zzaw) googleApiClient.getClient(Drive.CLIENT_KEY);
        this.zzdk.zzp().zza(zzaw.getContext());
        try {
            return ((zzeo) zzaw.getService()).zza(new zzu(this.zzdk.zzp(), this.zzdi.intValue(), this.zzay, this.zzbb, Integer.valueOf(0)));
        } catch (Throwable e) {
            throw new RuntimeException("Unable to connect Drive Play Service", e);
        }
    }

    public final int getRequestId() {
        return this.zzdi.intValue();
    }

    public final void zza(DriveId driveId) {
        this.zzbb = (DriveId) Preconditions.checkNotNull(driveId);
    }

    public final void zza(MetadataChangeSet metadataChangeSet) {
        this.zzdk = (MetadataChangeSet) Preconditions.checkNotNull(metadataChangeSet);
    }

    public final MetadataChangeSet zzb() {
        return this.zzdk;
    }

    public final DriveId zzc() {
        return this.zzbb;
    }

    public final void zzc(String str) {
        this.zzay = (String) Preconditions.checkNotNull(str);
    }

    public final String zzd() {
        return this.zzay;
    }

    public final void zzd(int i) {
        this.zzdi = Integer.valueOf(i);
    }

    public final void zzf() {
        Preconditions.checkNotNull(this.zzdk, "Must provide initial metadata via setInitialMetadata.");
        this.zzdi = Integer.valueOf(this.zzdi == null ? 0 : this.zzdi.intValue());
    }
}
