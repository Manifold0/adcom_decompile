// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.drive.DriveId;
import java.util.Set;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.zzp;
import com.google.android.gms.drive.zzn;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.events.zzj;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.internal.ListenerHolder;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.Drive;
import android.support.annotation.NonNull;
import android.app.Activity;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.drive.DriveResourceClient;

public final class zzch extends DriveResourceClient
{
    private static final AtomicInteger zzfl;
    
    static {
        zzfl = new AtomicInteger();
    }
    
    public zzch(@NonNull final Activity activity, @Nullable final Drive.zza zza) {
        super(activity, zza);
    }
    
    public zzch(@NonNull final Context context, @Nullable final Drive.zza zza) {
        super(context, zza);
    }
    
    private static void zze(final int n) {
        if (n != 268435456 && n != 536870912 && n != 805306368) {
            throw new IllegalArgumentException("Invalid openMode provided");
        }
    }
    
    @Override
    public final Task<ListenerToken> addChangeListener(@NonNull final DriveResource driveResource, @NonNull final OnChangeListener onChangeListener) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        Preconditions.checkNotNull((Object)onChangeListener, (Object)"listener");
        final zzdi zzdi = new zzdi(this, onChangeListener, driveResource.getDriveId());
        final ListenerHolder registerListener = this.registerListener((Object)zzdi, new StringBuilder(27).append("OnChangeListener").append(zzch.zzfl.incrementAndGet()).toString());
        return (Task<ListenerToken>)this.doRegisterEventListener((RegisterListenerMethod)new zzcp(this, registerListener, driveResource, zzdi), (UnregisterListenerMethod)new zzcq(this, registerListener.getListenerKey(), driveResource, zzdi)).continueWith((Continuation)new zzci(registerListener));
    }
    
    @Override
    public final Task<Void> addChangeSubscription(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        Preconditions.checkArgument(zzj.zza(1, driveResource.getDriveId()));
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcr(this, driveResource));
    }
    
    @Override
    public final Task<Boolean> cancelOpenFileCallback(@NonNull final ListenerToken listenerToken) {
        if (!(listenerToken instanceof zzg)) {
            throw new IllegalArgumentException("Unrecognized ListenerToken");
        }
        return (Task<Boolean>)this.doUnregisterEventListener(((zzg)listenerToken).zzac());
    }
    
    @Override
    public final Task<Void> commitContents(@NonNull final DriveContents driveContents, @Nullable final MetadataChangeSet set) {
        return this.commitContents(driveContents, set, ((ExecutionOptions.Builder)new zzp()).build());
    }
    
    @Override
    public final Task<Void> commitContents(@NonNull final DriveContents driveContents, @Nullable final MetadataChangeSet set, @NonNull final ExecutionOptions executionOptions) {
        final boolean b = true;
        Preconditions.checkNotNull((Object)executionOptions, (Object)"Execution options cannot be null.");
        Preconditions.checkArgument(!driveContents.zzj(), (Object)"DriveContents is already closed");
        Preconditions.checkArgument(driveContents.getMode() != 268435456 && b, (Object)"Cannot commit contents opened in MODE_READ_ONLY.");
        Preconditions.checkNotNull((Object)driveContents.getDriveId(), (Object)"Only DriveContents obtained through DriveFile.open can be committed.");
        final zzn zza = zzn.zza(executionOptions);
        if (ExecutionOptions.zza(zza.zzm()) && !driveContents.zzh().zza()) {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
        MetadataChangeSet zzav;
        if ((zzav = set) == null) {
            zzav = MetadataChangeSet.zzav;
        }
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcy(this, zza, driveContents, zzav));
    }
    
    @Override
    public final Task<DriveContents> createContents() {
        Preconditions.checkArgument(true, (Object)"Contents can only be created in MODE_WRITE_ONLY or MODE_READ_WRITE.");
        return (Task<DriveContents>)this.doWrite((TaskApiCall)new zzcw(this, 536870912));
    }
    
    @Override
    public final Task<DriveFile> createFile(@NonNull final DriveFolder driveFolder, @NonNull final MetadataChangeSet set, @Nullable final DriveContents driveContents) {
        return this.createFile(driveFolder, set, driveContents, new ExecutionOptions.Builder().build());
    }
    
    @Override
    public final Task<DriveFile> createFile(@NonNull final DriveFolder driveFolder, @NonNull final MetadataChangeSet set, @Nullable final DriveContents driveContents, @NonNull final ExecutionOptions executionOptions) {
        zzbs.zzb(set);
        return (Task<DriveFile>)this.doWrite((TaskApiCall)new zzdh(driveFolder, set, driveContents, executionOptions, null));
    }
    
    @Override
    public final Task<DriveFolder> createFolder(@NonNull final DriveFolder driveFolder, @NonNull final MetadataChangeSet set) {
        Preconditions.checkNotNull((Object)set, (Object)"MetadataChangeSet must be provided.");
        if (set.getMimeType() != null && !set.getMimeType().equals("application/vnd.google-apps.folder")) {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
        return (Task<DriveFolder>)this.doWrite((TaskApiCall)new zzdb(this, set, driveFolder));
    }
    
    @Override
    public final Task<Void> delete(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcl(this, driveResource));
    }
    
    @Override
    public final Task<Void> discardContents(@NonNull final DriveContents driveContents) {
        Preconditions.checkArgument(!driveContents.zzj(), (Object)"DriveContents is already closed");
        driveContents.zzi();
        return (Task<Void>)this.doWrite((TaskApiCall)new zzda(this, driveContents));
    }
    
    @Override
    public final Task<DriveFolder> getAppFolder() {
        return (Task<DriveFolder>)this.doRead((TaskApiCall)new zzco(this));
    }
    
    @Override
    public final Task<Metadata> getMetadata(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource, (Object)"DriveResource must not be null");
        Preconditions.checkNotNull((Object)driveResource.getDriveId(), (Object)"Resource's DriveId must not be null");
        return (Task<Metadata>)this.doRead((TaskApiCall)new zzdc(this, driveResource, false));
    }
    
    @Override
    public final Task<DriveFolder> getRootFolder() {
        return (Task<DriveFolder>)this.doRead((TaskApiCall)new zzck(this));
    }
    
    @Override
    public final Task<MetadataBuffer> listChildren(@NonNull final DriveFolder driveFolder) {
        Preconditions.checkNotNull((Object)driveFolder, (Object)"folder cannot be null.");
        return this.query(zzbs.zza(null, driveFolder.getDriveId()));
    }
    
    @Override
    public final Task<MetadataBuffer> listParents(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        return (Task<MetadataBuffer>)this.doRead((TaskApiCall)new zzde(this, driveResource));
    }
    
    @Override
    public final Task<DriveContents> openFile(@NonNull final DriveFile driveFile, final int n) {
        zze(n);
        return (Task<DriveContents>)this.doRead((TaskApiCall)new zzct(this, driveFile, n));
    }
    
    @Override
    public final Task<ListenerToken> openFile(@NonNull final DriveFile driveFile, final int n, @NonNull final OpenFileCallback openFileCallback) {
        zze(n);
        final ListenerHolder registerListener = this.registerListener((Object)openFileCallback, new StringBuilder(27).append("OpenFileCallback").append(zzch.zzfl.incrementAndGet()).toString());
        final ListenerHolder$ListenerKey listenerKey = registerListener.getListenerKey();
        final zzg zzg = new zzg(listenerKey);
        return (Task<ListenerToken>)this.doRegisterEventListener((RegisterListenerMethod)new zzcu(this, registerListener, driveFile, n, zzg, registerListener), (UnregisterListenerMethod)new zzcv(this, listenerKey, zzg)).continueWith((Continuation)new zzcj(zzg));
    }
    
    @Override
    public final Task<MetadataBuffer> query(@NonNull final Query query) {
        Preconditions.checkNotNull((Object)query, (Object)"query cannot be null.");
        return (Task<MetadataBuffer>)this.doRead((TaskApiCall)new zzcz(this, query));
    }
    
    @Override
    public final Task<MetadataBuffer> queryChildren(@NonNull final DriveFolder driveFolder, @NonNull final Query query) {
        Preconditions.checkNotNull((Object)driveFolder, (Object)"folder cannot be null.");
        Preconditions.checkNotNull((Object)query, (Object)"query cannot be null.");
        return this.query(zzbs.zza(query, driveFolder.getDriveId()));
    }
    
    @Override
    public final Task<Boolean> removeChangeListener(@NonNull final ListenerToken listenerToken) {
        Preconditions.checkNotNull((Object)listenerToken, (Object)"Token is required to unregister listener.");
        if (listenerToken instanceof zzg) {
            return (Task<Boolean>)this.doUnregisterEventListener(((zzg)listenerToken).zzac());
        }
        throw new IllegalStateException("Could not recover key from ListenerToken");
    }
    
    @Override
    public final Task<Void> removeChangeSubscription(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        Preconditions.checkArgument(zzj.zza(1, driveResource.getDriveId()));
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcs(this, driveResource));
    }
    
    @Override
    public final Task<DriveContents> reopenContentsForWrite(@NonNull final DriveContents driveContents) {
        final boolean b = true;
        Preconditions.checkArgument(!driveContents.zzj(), (Object)"DriveContents is already closed");
        Preconditions.checkArgument(driveContents.getMode() == 268435456 && b, (Object)"This method can only be called on contents that are currently opened in MODE_READ_ONLY.");
        driveContents.zzi();
        return (Task<DriveContents>)this.doRead((TaskApiCall)new zzcx(this, driveContents));
    }
    
    @Override
    public final Task<Void> setParents(@NonNull final DriveResource driveResource, @NonNull final Set<DriveId> set) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        Preconditions.checkNotNull((Object)set);
        return (Task<Void>)this.doWrite((TaskApiCall)new zzdf(this, driveResource, new ArrayList(set)));
    }
    
    @Override
    public final Task<Void> trash(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcm(this, driveResource));
    }
    
    @Override
    public final Task<Void> untrash(@NonNull final DriveResource driveResource) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        return (Task<Void>)this.doWrite((TaskApiCall)new zzcn(this, driveResource));
    }
    
    @Override
    public final Task<Metadata> updateMetadata(@NonNull final DriveResource driveResource, @NonNull final MetadataChangeSet set) {
        Preconditions.checkNotNull((Object)driveResource.getDriveId());
        Preconditions.checkNotNull((Object)set);
        return (Task<Metadata>)this.doWrite((TaskApiCall)new zzdd(this, set, driveResource));
    }
}
