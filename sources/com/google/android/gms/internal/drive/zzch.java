package com.google.android.gms.internal.drive;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.android.gms.drive.Drive.zza;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.ExecutionOptions.Builder;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.events.zzj;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.zzn;
import com.google.android.gms.drive.zzp;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzch extends DriveResourceClient {
    private static final AtomicInteger zzfl = new AtomicInteger();

    public zzch(@NonNull Activity activity, @Nullable zza zza) {
        super(activity, zza);
    }

    public zzch(@NonNull Context context, @Nullable zza zza) {
        super(context, zza);
    }

    static final /* synthetic */ ListenerToken zza(ListenerHolder listenerHolder, Task task) throws Exception {
        if (task.isSuccessful()) {
            return new zzg(listenerHolder.getListenerKey());
        }
        throw task.getException();
    }

    static final /* synthetic */ ListenerToken zza(zzg zzg, Task task) throws Exception {
        if (task.isSuccessful()) {
            return zzg;
        }
        throw task.getException();
    }

    private static void zze(int i) {
        if (i != DriveFile.MODE_READ_ONLY && i != DriveFile.MODE_WRITE_ONLY && i != DriveFile.MODE_READ_WRITE) {
            throw new IllegalArgumentException("Invalid openMode provided");
        }
    }

    public final Task<ListenerToken> addChangeListener(@NonNull DriveResource driveResource, @NonNull OnChangeListener onChangeListener) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkNotNull(onChangeListener, CastExtraArgs.LISTENER);
        zzdi zzdi = new zzdi(this, onChangeListener, driveResource.getDriveId());
        ListenerHolder registerListener = registerListener(zzdi, "OnChangeListener" + zzfl.incrementAndGet());
        return doRegisterEventListener(new zzcp(this, registerListener, driveResource, zzdi), new zzcq(this, registerListener.getListenerKey(), driveResource, zzdi)).continueWith(new zzci(registerListener));
    }

    public final Task<Void> addChangeSubscription(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkArgument(zzj.zza(1, driveResource.getDriveId()));
        return doWrite(new zzcr(this, driveResource));
    }

    public final Task<Boolean> cancelOpenFileCallback(@NonNull ListenerToken listenerToken) {
        if (listenerToken instanceof zzg) {
            return doUnregisterEventListener(((zzg) listenerToken).zzac());
        }
        throw new IllegalArgumentException("Unrecognized ListenerToken");
    }

    public final Task<Void> commitContents(@NonNull DriveContents driveContents, @Nullable MetadataChangeSet metadataChangeSet) {
        return commitContents(driveContents, metadataChangeSet, (zzn) new zzp().build());
    }

    public final Task<Void> commitContents(@NonNull DriveContents driveContents, @Nullable MetadataChangeSet metadataChangeSet, @NonNull ExecutionOptions executionOptions) {
        boolean z = true;
        Preconditions.checkNotNull(executionOptions, "Execution options cannot be null.");
        Preconditions.checkArgument(!driveContents.zzj(), "DriveContents is already closed");
        if (driveContents.getMode() == DriveFile.MODE_READ_ONLY) {
            z = false;
        }
        Preconditions.checkArgument(z, "Cannot commit contents opened in MODE_READ_ONLY.");
        Preconditions.checkNotNull(driveContents.getDriveId(), "Only DriveContents obtained through DriveFile.open can be committed.");
        ExecutionOptions zza = zzn.zza(executionOptions);
        if (!ExecutionOptions.zza(zza.zzm()) || driveContents.zzh().zza()) {
            if (metadataChangeSet == null) {
                metadataChangeSet = MetadataChangeSet.zzav;
            }
            return doWrite(new zzcy(this, zza, driveContents, metadataChangeSet));
        }
        throw new IllegalStateException("DriveContents must be valid for conflict detection.");
    }

    public final Task<DriveContents> createContents() {
        Preconditions.checkArgument(true, "Contents can only be created in MODE_WRITE_ONLY or MODE_READ_WRITE.");
        return doWrite(new zzcw(this, DriveFile.MODE_WRITE_ONLY));
    }

    public final Task<DriveFile> createFile(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents) {
        return createFile(driveFolder, metadataChangeSet, driveContents, new Builder().build());
    }

    public final Task<DriveFile> createFile(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @NonNull ExecutionOptions executionOptions) {
        zzbs.zzb(metadataChangeSet);
        return doWrite(new zzdh(driveFolder, metadataChangeSet, driveContents, executionOptions, null));
    }

    public final Task<DriveFolder> createFolder(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet) {
        Preconditions.checkNotNull(metadataChangeSet, "MetadataChangeSet must be provided.");
        if (metadataChangeSet.getMimeType() == null || metadataChangeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return doWrite(new zzdb(this, metadataChangeSet, driveFolder));
        }
        throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
    }

    public final Task<Void> delete(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doWrite(new zzcl(this, driveResource));
    }

    public final Task<Void> discardContents(@NonNull DriveContents driveContents) {
        Preconditions.checkArgument(!driveContents.zzj(), "DriveContents is already closed");
        driveContents.zzi();
        return doWrite(new zzda(this, driveContents));
    }

    public final Task<DriveFolder> getAppFolder() {
        return doRead(new zzco(this));
    }

    public final Task<Metadata> getMetadata(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource, "DriveResource must not be null");
        Preconditions.checkNotNull(driveResource.getDriveId(), "Resource's DriveId must not be null");
        return doRead(new zzdc(this, driveResource, false));
    }

    public final Task<DriveFolder> getRootFolder() {
        return doRead(new zzck(this));
    }

    public final Task<MetadataBuffer> listChildren(@NonNull DriveFolder driveFolder) {
        Preconditions.checkNotNull(driveFolder, "folder cannot be null.");
        return query(zzbs.zza(null, driveFolder.getDriveId()));
    }

    public final Task<MetadataBuffer> listParents(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doRead(new zzde(this, driveResource));
    }

    public final Task<DriveContents> openFile(@NonNull DriveFile driveFile, int i) {
        zze(i);
        return doRead(new zzct(this, driveFile, i));
    }

    public final Task<ListenerToken> openFile(@NonNull DriveFile driveFile, int i, @NonNull OpenFileCallback openFileCallback) {
        zze(i);
        ListenerHolder registerListener = registerListener(openFileCallback, "OpenFileCallback" + zzfl.incrementAndGet());
        ListenerKey listenerKey = registerListener.getListenerKey();
        zzg zzg = new zzg(listenerKey);
        return doRegisterEventListener(new zzcu(this, registerListener, driveFile, i, zzg, registerListener), new zzcv(this, listenerKey, zzg)).continueWith(new zzcj(zzg));
    }

    public final Task<MetadataBuffer> query(@NonNull Query query) {
        Preconditions.checkNotNull(query, "query cannot be null.");
        return doRead(new zzcz(this, query));
    }

    public final Task<MetadataBuffer> queryChildren(@NonNull DriveFolder driveFolder, @NonNull Query query) {
        Preconditions.checkNotNull(driveFolder, "folder cannot be null.");
        Preconditions.checkNotNull(query, "query cannot be null.");
        return query(zzbs.zza(query, driveFolder.getDriveId()));
    }

    public final Task<Boolean> removeChangeListener(@NonNull ListenerToken listenerToken) {
        Preconditions.checkNotNull(listenerToken, "Token is required to unregister listener.");
        if (listenerToken instanceof zzg) {
            return doUnregisterEventListener(((zzg) listenerToken).zzac());
        }
        throw new IllegalStateException("Could not recover key from ListenerToken");
    }

    public final Task<Void> removeChangeSubscription(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkArgument(zzj.zza(1, driveResource.getDriveId()));
        return doWrite(new zzcs(this, driveResource));
    }

    public final Task<DriveContents> reopenContentsForWrite(@NonNull DriveContents driveContents) {
        boolean z = true;
        Preconditions.checkArgument(!driveContents.zzj(), "DriveContents is already closed");
        if (driveContents.getMode() != DriveFile.MODE_READ_ONLY) {
            z = false;
        }
        Preconditions.checkArgument(z, "This method can only be called on contents that are currently opened in MODE_READ_ONLY.");
        driveContents.zzi();
        return doRead(new zzcx(this, driveContents));
    }

    public final Task<Void> setParents(@NonNull DriveResource driveResource, @NonNull Set<DriveId> set) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkNotNull(set);
        return doWrite(new zzdf(this, driveResource, new ArrayList(set)));
    }

    public final Task<Void> trash(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doWrite(new zzcm(this, driveResource));
    }

    public final Task<Void> untrash(@NonNull DriveResource driveResource) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        return doWrite(new zzcn(this, driveResource));
    }

    public final Task<Metadata> updateMetadata(@NonNull DriveResource driveResource, @NonNull MetadataChangeSet metadataChangeSet) {
        Preconditions.checkNotNull(driveResource.getDriveId());
        Preconditions.checkNotNull(metadataChangeSet);
        return doWrite(new zzdd(this, metadataChangeSet, driveResource));
    }
}
