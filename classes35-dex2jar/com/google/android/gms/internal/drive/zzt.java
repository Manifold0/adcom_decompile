// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.internal.Preconditions;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.DriveId;

@Deprecated
public final class zzt
{
    private String zzay;
    private DriveId zzbb;
    private Integer zzdi;
    private final int zzdj;
    private MetadataChangeSet zzdk;
    
    public zzt(final int n) {
        this.zzdj = 0;
    }
    
    public final IntentSender build(final GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), (Object)"Client must be connected");
        this.zzf();
        final zzaw zzaw = (zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY);
        this.zzdk.zzp().zza(zzaw.getContext());
        try {
            return ((zzeo)zzaw.getService()).zza(new zzu(this.zzdk.zzp(), this.zzdi, this.zzay, this.zzbb, 0));
        }
        catch (RemoteException ex) {
            throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)ex);
        }
    }
    
    public final int getRequestId() {
        return this.zzdi;
    }
    
    public final void zza(final DriveId driveId) {
        this.zzbb = (DriveId)Preconditions.checkNotNull((Object)driveId);
    }
    
    public final void zza(final MetadataChangeSet set) {
        this.zzdk = (MetadataChangeSet)Preconditions.checkNotNull((Object)set);
    }
    
    public final MetadataChangeSet zzb() {
        return this.zzdk;
    }
    
    public final DriveId zzc() {
        return this.zzbb;
    }
    
    public final void zzc(final String s) {
        this.zzay = (String)Preconditions.checkNotNull((Object)s);
    }
    
    public final String zzd() {
        return this.zzay;
    }
    
    public final void zzd(final int n) {
        this.zzdi = n;
    }
    
    public final void zzf() {
        Preconditions.checkNotNull((Object)this.zzdk, (Object)"Must provide initial metadata via setInitialMetadata.");
        int intValue;
        if (this.zzdi == null) {
            intValue = 0;
        }
        else {
            intValue = this.zzdi;
        }
        this.zzdi = intValue;
    }
}
