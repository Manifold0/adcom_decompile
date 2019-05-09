// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.DriveApi;
import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import java.io.InputStream;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.zzp;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.zzn;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.drive.DriveContents;

public final class zzbi implements DriveContents
{
    private static final GmsLogger zzbx;
    private boolean closed;
    private final Contents zzeq;
    private boolean zzer;
    private boolean zzes;
    
    static {
        zzbx = new GmsLogger("DriveContentsImpl", "");
    }
    
    public zzbi(final Contents contents) {
        this.closed = false;
        this.zzer = false;
        this.zzes = false;
        this.zzeq = (Contents)Preconditions.checkNotNull((Object)contents);
    }
    
    private final PendingResult<Status> zza(final GoogleApiClient googleApiClient, @Nullable MetadataChangeSet zzav, @Nullable zzn zzn) {
        if (zzn == null) {
            zzn = (zzn)((ExecutionOptions.Builder)new zzp()).build();
        }
        if (this.zzeq.getMode() == 268435456) {
            throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
        }
        if (ExecutionOptions.zza(zzn.zzm()) && !this.zzeq.zza()) {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
        zzn.zza(googleApiClient);
        if (this.closed) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        if (this.getDriveId() == null) {
            throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
        }
        if (zzav == null) {
            zzav = MetadataChangeSet.zzav;
        }
        this.zzi();
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbk(this, googleApiClient, zzav, zzn));
    }
    
    @Override
    public final PendingResult<Status> commit(final GoogleApiClient googleApiClient, @Nullable final MetadataChangeSet set) {
        return this.zza(googleApiClient, set, null);
    }
    
    @Override
    public final PendingResult<Status> commit(final GoogleApiClient googleApiClient, @Nullable final MetadataChangeSet set, @Nullable final ExecutionOptions executionOptions) {
        zzn zza;
        if (executionOptions == null) {
            zza = null;
        }
        else {
            zza = zzn.zza(executionOptions);
        }
        return this.zza(googleApiClient, set, zza);
    }
    
    @Override
    public final void discard(final GoogleApiClient googleApiClient) {
        if (this.closed) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        this.zzi();
        ((zzbm)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbm(this, googleApiClient))).setResultCallback((ResultCallback)new zzbl(this));
    }
    
    @Override
    public final DriveId getDriveId() {
        return this.zzeq.getDriveId();
    }
    
    @Override
    public final InputStream getInputStream() {
        if (this.closed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        }
        if (this.zzeq.getMode() != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        }
        if (this.zzer) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        }
        this.zzer = true;
        return this.zzeq.getInputStream();
    }
    
    @Override
    public final int getMode() {
        return this.zzeq.getMode();
    }
    
    @Override
    public final OutputStream getOutputStream() {
        if (this.closed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        if (this.zzeq.getMode() != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        }
        if (this.zzes) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        }
        this.zzes = true;
        return this.zzeq.getOutputStream();
    }
    
    @Override
    public final ParcelFileDescriptor getParcelFileDescriptor() {
        if (this.closed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        }
        return this.zzeq.getParcelFileDescriptor();
    }
    
    @Override
    public final PendingResult<DriveApi.DriveContentsResult> reopenForWrite(final GoogleApiClient googleApiClient) {
        if (this.closed) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        if (this.zzeq.getMode() != 268435456) {
            throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
        }
        this.zzi();
        return (PendingResult<DriveApi.DriveContentsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbj(this, googleApiClient));
    }
    
    @Override
    public final Contents zzh() {
        return this.zzeq;
    }
    
    @Override
    public final void zzi() {
        IOUtils.closeQuietly(this.zzeq.getParcelFileDescriptor());
        this.closed = true;
    }
    
    @Override
    public final boolean zzj() {
        return this.closed;
    }
}
