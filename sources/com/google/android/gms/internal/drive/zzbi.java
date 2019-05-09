package com.google.android.gms.internal.drive;

import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzn;
import com.google.android.gms.drive.zzp;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzbi implements DriveContents {
    private static final GmsLogger zzbx = new GmsLogger("DriveContentsImpl", "");
    private boolean closed = false;
    private final Contents zzeq;
    private boolean zzer = false;
    private boolean zzes = false;

    public zzbi(Contents contents) {
        this.zzeq = (Contents) Preconditions.checkNotNull(contents);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, @Nullable MetadataChangeSet metadataChangeSet, @Nullable zzn zzn) {
        ExecutionOptions executionOptions = zzn == null ? (zzn) new zzp().build() : zzn;
        if (this.zzeq.getMode() == DriveFile.MODE_READ_ONLY) {
            throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
        } else if (!ExecutionOptions.zza(executionOptions.zzm()) || this.zzeq.zza()) {
            executionOptions.zza(googleApiClient);
            if (this.closed) {
                throw new IllegalStateException("DriveContents already closed.");
            } else if (getDriveId() == null) {
                throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
            } else {
                if (metadataChangeSet == null) {
                    metadataChangeSet = MetadataChangeSet.zzav;
                }
                zzi();
                return googleApiClient.execute(new zzbk(this, googleApiClient, metadataChangeSet, executionOptions));
            }
        } else {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
    }

    public final PendingResult<Status> commit(GoogleApiClient googleApiClient, @Nullable MetadataChangeSet metadataChangeSet) {
        return zza(googleApiClient, metadataChangeSet, null);
    }

    public final PendingResult<Status> commit(GoogleApiClient googleApiClient, @Nullable MetadataChangeSet metadataChangeSet, @Nullable ExecutionOptions executionOptions) {
        return zza(googleApiClient, metadataChangeSet, executionOptions == null ? null : zzn.zza(executionOptions));
    }

    public final void discard(GoogleApiClient googleApiClient) {
        if (this.closed) {
            throw new IllegalStateException("DriveContents already closed.");
        }
        zzi();
        ((zzbm) googleApiClient.execute(new zzbm(this, googleApiClient))).setResultCallback(new zzbl(this));
    }

    public final DriveId getDriveId() {
        return this.zzeq.getDriveId();
    }

    public final InputStream getInputStream() {
        if (this.closed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        } else if (this.zzeq.getMode() != DriveFile.MODE_READ_ONLY) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        } else if (this.zzer) {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        } else {
            this.zzer = true;
            return this.zzeq.getInputStream();
        }
    }

    public final int getMode() {
        return this.zzeq.getMode();
    }

    public final OutputStream getOutputStream() {
        if (this.closed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        } else if (this.zzeq.getMode() != DriveFile.MODE_WRITE_ONLY) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        } else if (this.zzes) {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        } else {
            this.zzes = true;
            return this.zzeq.getOutputStream();
        }
    }

    public final ParcelFileDescriptor getParcelFileDescriptor() {
        if (!this.closed) {
            return this.zzeq.getParcelFileDescriptor();
        }
        throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }

    public final PendingResult<DriveContentsResult> reopenForWrite(GoogleApiClient googleApiClient) {
        if (this.closed) {
            throw new IllegalStateException("DriveContents already closed.");
        } else if (this.zzeq.getMode() != DriveFile.MODE_READ_ONLY) {
            throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
        } else {
            zzi();
            return googleApiClient.enqueue(new zzbj(this, googleApiClient));
        }
    }

    public final Contents zzh() {
        return this.zzeq;
    }

    public final void zzi() {
        IOUtils.closeQuietly(this.zzeq.getParcelFileDescriptor());
        this.closed = true;
    }

    public final boolean zzj() {
        return this.closed;
    }
}
