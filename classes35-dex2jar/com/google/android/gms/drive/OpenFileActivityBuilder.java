// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.query.internal.zzk;
import android.os.RemoteException;
import com.google.android.gms.drive.query.internal.FilterHolder;
import com.google.android.gms.internal.drive.zzgg;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.internal.drive.zzaw;
import com.google.android.gms.internal.drive.zzeo;
import com.google.android.gms.common.internal.Preconditions;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.query.Filter;

@Deprecated
public class OpenFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private String zzay;
    private String[] zzaz;
    private Filter zzba;
    private DriveId zzbb;
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), (Object)"Client must be connected");
        this.zzf();
        Label_0066: {
            if (this.zzba != null) {
                break Label_0066;
            }
            FilterHolder filterHolder = null;
            try {
                return ((zzeo)((zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY)).getService()).zza(new zzgg(this.zzay, this.zzaz, this.zzbb, filterHolder));
                filterHolder = new FilterHolder(this.zzba);
                return ((zzeo)((zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY)).getService()).zza(new zzgg(this.zzay, this.zzaz, this.zzbb, filterHolder));
            }
            catch (RemoteException ex) {
                throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)ex);
            }
        }
    }
    
    final String getTitle() {
        return this.zzay;
    }
    
    public OpenFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.zzbb = (DriveId)Preconditions.checkNotNull((Object)driveId);
        return this;
    }
    
    public OpenFileActivityBuilder setActivityTitle(final String s) {
        this.zzay = (String)Preconditions.checkNotNull((Object)s);
        return this;
    }
    
    public OpenFileActivityBuilder setMimeType(final String[] zzaz) {
        Preconditions.checkArgument(zzaz != null, (Object)"mimeTypes may not be null");
        this.zzaz = zzaz;
        return this;
    }
    
    public OpenFileActivityBuilder setSelectionFilter(final Filter zzba) {
        final boolean b = true;
        Preconditions.checkArgument(zzba != null, (Object)"filter may not be null");
        Preconditions.checkArgument(!zzk.zza(zzba) && b, (Object)"FullTextSearchFilter cannot be used as a selection filter");
        this.zzba = zzba;
        return this;
    }
    
    final void zzf() {
        if (this.zzaz == null) {
            this.zzaz = new String[0];
        }
        if (this.zzaz.length > 0 && this.zzba != null) {
            throw new IllegalStateException("Cannot use a selection filter and set mimetypes simultaneously");
        }
    }
    
    final String[] zzr() {
        return this.zzaz;
    }
    
    final Filter zzs() {
        return this.zzba;
    }
    
    final DriveId zzt() {
        return this.zzbb;
    }
}
