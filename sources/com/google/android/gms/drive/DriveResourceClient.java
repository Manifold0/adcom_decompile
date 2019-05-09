package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.drive.Drive.zza;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.tasks.Task;
import java.util.Set;

public abstract class DriveResourceClient extends GoogleApi<zza> {
    public DriveResourceClient(@NonNull Activity activity, @Nullable zza zza) {
        super(activity, Drive.zzu, zza, Settings.DEFAULT_SETTINGS);
    }

    public DriveResourceClient(@NonNull Context context, @Nullable zza zza) {
        super(context, Drive.zzu, zza, Settings.DEFAULT_SETTINGS);
    }

    public abstract Task<ListenerToken> addChangeListener(@NonNull DriveResource driveResource, @NonNull OnChangeListener onChangeListener);

    public abstract Task<Void> addChangeSubscription(@NonNull DriveResource driveResource);

    public abstract Task<Boolean> cancelOpenFileCallback(@NonNull ListenerToken listenerToken);

    public abstract Task<Void> commitContents(@NonNull DriveContents driveContents, @Nullable MetadataChangeSet metadataChangeSet);

    public abstract Task<Void> commitContents(@NonNull DriveContents driveContents, @Nullable MetadataChangeSet metadataChangeSet, @NonNull ExecutionOptions executionOptions);

    public abstract Task<DriveContents> createContents();

    public abstract Task<DriveFile> createFile(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents);

    public abstract Task<DriveFile> createFile(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @NonNull ExecutionOptions executionOptions);

    public abstract Task<DriveFolder> createFolder(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet);

    public abstract Task<Void> delete(@NonNull DriveResource driveResource);

    public abstract Task<Void> discardContents(@NonNull DriveContents driveContents);

    public abstract Task<DriveFolder> getAppFolder();

    public abstract Task<Metadata> getMetadata(@NonNull DriveResource driveResource);

    public abstract Task<DriveFolder> getRootFolder();

    public abstract Task<MetadataBuffer> listChildren(@NonNull DriveFolder driveFolder);

    public abstract Task<MetadataBuffer> listParents(@NonNull DriveResource driveResource);

    public abstract Task<DriveContents> openFile(@NonNull DriveFile driveFile, int i);

    public abstract Task<ListenerToken> openFile(@NonNull DriveFile driveFile, int i, @NonNull OpenFileCallback openFileCallback);

    public abstract Task<MetadataBuffer> query(@NonNull Query query);

    public abstract Task<MetadataBuffer> queryChildren(@NonNull DriveFolder driveFolder, @NonNull Query query);

    public abstract Task<Boolean> removeChangeListener(@NonNull ListenerToken listenerToken);

    public abstract Task<Void> removeChangeSubscription(@NonNull DriveResource driveResource);

    public abstract Task<DriveContents> reopenContentsForWrite(@NonNull DriveContents driveContents);

    public abstract Task<Void> setParents(@NonNull DriveResource driveResource, @NonNull Set<DriveId> set);

    public abstract Task<Void> trash(@NonNull DriveResource driveResource);

    public abstract Task<Void> untrash(@NonNull DriveResource driveResource);

    public abstract Task<Metadata> updateMetadata(@NonNull DriveResource driveResource, @NonNull MetadataChangeSet metadataChangeSet);
}
