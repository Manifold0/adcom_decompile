// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import java.util.Set;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.drive.events.OnChangeListener;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;

public abstract class DriveResourceClient extends GoogleApi<Drive.zza>
{
    public DriveResourceClient(@NonNull final Activity activity, @Nullable final Drive.zza zza) {
        super(activity, (Api)Drive.zzu, (Api$ApiOptions)zza, GoogleApi$Settings.DEFAULT_SETTINGS);
    }
    
    public DriveResourceClient(@NonNull final Context context, @Nullable final Drive.zza zza) {
        super(context, (Api)Drive.zzu, (Api$ApiOptions)zza, GoogleApi$Settings.DEFAULT_SETTINGS);
    }
    
    public abstract Task<ListenerToken> addChangeListener(@NonNull final DriveResource p0, @NonNull final OnChangeListener p1);
    
    public abstract Task<Void> addChangeSubscription(@NonNull final DriveResource p0);
    
    public abstract Task<Boolean> cancelOpenFileCallback(@NonNull final ListenerToken p0);
    
    public abstract Task<Void> commitContents(@NonNull final DriveContents p0, @Nullable final MetadataChangeSet p1);
    
    public abstract Task<Void> commitContents(@NonNull final DriveContents p0, @Nullable final MetadataChangeSet p1, @NonNull final ExecutionOptions p2);
    
    public abstract Task<DriveContents> createContents();
    
    public abstract Task<DriveFile> createFile(@NonNull final DriveFolder p0, @NonNull final MetadataChangeSet p1, @Nullable final DriveContents p2);
    
    public abstract Task<DriveFile> createFile(@NonNull final DriveFolder p0, @NonNull final MetadataChangeSet p1, @Nullable final DriveContents p2, @NonNull final ExecutionOptions p3);
    
    public abstract Task<DriveFolder> createFolder(@NonNull final DriveFolder p0, @NonNull final MetadataChangeSet p1);
    
    public abstract Task<Void> delete(@NonNull final DriveResource p0);
    
    public abstract Task<Void> discardContents(@NonNull final DriveContents p0);
    
    public abstract Task<DriveFolder> getAppFolder();
    
    public abstract Task<Metadata> getMetadata(@NonNull final DriveResource p0);
    
    public abstract Task<DriveFolder> getRootFolder();
    
    public abstract Task<MetadataBuffer> listChildren(@NonNull final DriveFolder p0);
    
    public abstract Task<MetadataBuffer> listParents(@NonNull final DriveResource p0);
    
    public abstract Task<DriveContents> openFile(@NonNull final DriveFile p0, final int p1);
    
    public abstract Task<ListenerToken> openFile(@NonNull final DriveFile p0, final int p1, @NonNull final OpenFileCallback p2);
    
    public abstract Task<MetadataBuffer> query(@NonNull final Query p0);
    
    public abstract Task<MetadataBuffer> queryChildren(@NonNull final DriveFolder p0, @NonNull final Query p1);
    
    public abstract Task<Boolean> removeChangeListener(@NonNull final ListenerToken p0);
    
    public abstract Task<Void> removeChangeSubscription(@NonNull final DriveResource p0);
    
    public abstract Task<DriveContents> reopenContentsForWrite(@NonNull final DriveContents p0);
    
    public abstract Task<Void> setParents(@NonNull final DriveResource p0, @NonNull final Set<DriveId> p1);
    
    public abstract Task<Void> trash(@NonNull final DriveResource p0);
    
    public abstract Task<Void> untrash(@NonNull final DriveResource p0);
    
    public abstract Task<Metadata> updateMetadata(@NonNull final DriveResource p0, @NonNull final MetadataChangeSet p1);
}
