// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.snapshot.SnapshotContents;
import android.content.Intent;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.internal.zzp;
import com.google.android.gms.internal.games.zzu;

public class SnapshotsClient extends zzu
{
    public static final int DISPLAY_LIMIT_NONE = -1;
    public static final String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
    public static final String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
    public static final int RESOLUTION_POLICY_HIGHEST_PROGRESS = 4;
    public static final int RESOLUTION_POLICY_LAST_KNOWN_GOOD = 2;
    public static final int RESOLUTION_POLICY_LONGEST_PLAYTIME = 1;
    public static final int RESOLUTION_POLICY_MANUAL = -1;
    public static final int RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED = 3;
    private static final zzp<Snapshots.OpenSnapshotResult> zzdu;
    private static final PendingResultUtil$ResultConverter<Snapshots.DeleteSnapshotResult, String> zzdv;
    private static final PendingResultUtil$ResultConverter<Snapshots.CommitSnapshotResult, SnapshotMetadata> zzdw;
    private static final PendingResultUtil$ResultConverter<Snapshots.OpenSnapshotResult, Snapshots.OpenSnapshotResult> zzdx;
    private static final zzr zzdy;
    private static final PendingResultUtil$ResultConverter<Snapshots.OpenSnapshotResult, DataOrConflict<Snapshot>> zzdz;
    private static final PendingResultUtil$ResultConverter<Snapshots.LoadSnapshotsResult, SnapshotMetadataBuffer> zzea;
    
    static {
        zzdu = new zzby();
        zzdv = (PendingResultUtil$ResultConverter)new zzbz();
        zzdw = (PendingResultUtil$ResultConverter)new zzca();
        zzdx = (PendingResultUtil$ResultConverter)new zzcb();
        zzdy = new zzcc();
        zzdz = (PendingResultUtil$ResultConverter)new zzbt();
        zzea = (PendingResultUtil$ResultConverter)new zzbu();
    }
    
    SnapshotsClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    SnapshotsClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    @Nullable
    public static SnapshotMetadata getSnapshotFromBundle(@NonNull final Bundle bundle) {
        return Games.Snapshots.getSnapshotFromBundle(bundle);
    }
    
    private static Task<DataOrConflict<Snapshot>> zzc(@NonNull final PendingResult<Snapshots.OpenSnapshotResult> pendingResult) {
        return zzi.zza(pendingResult, SnapshotsClient.zzdy, SnapshotsClient.zzdz, SnapshotsClient.zzdx, SnapshotsClient.zzdu);
    }
    
    public Task<SnapshotMetadata> commitAndClose(@NonNull final Snapshot snapshot, @NonNull final SnapshotMetadataChange snapshotMetadataChange) {
        return zzi.toTask(Games.Snapshots.commitAndClose(this.asGoogleApiClient(), snapshot, snapshotMetadataChange), SnapshotsClient.zzdw);
    }
    
    public Task<String> delete(@NonNull final SnapshotMetadata snapshotMetadata) {
        return zzi.toTask(Games.Snapshots.delete(this.asGoogleApiClient(), snapshotMetadata), SnapshotsClient.zzdv);
    }
    
    public Task<Void> discardAndClose(@NonNull final Snapshot snapshot) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbx(this, snapshot));
    }
    
    public Task<Integer> getMaxCoverImageSize() {
        return (Task<Integer>)this.doRead((TaskApiCall)new zzbv(this));
    }
    
    public Task<Integer> getMaxDataSize() {
        return (Task<Integer>)this.doRead((TaskApiCall)new zzbs(this));
    }
    
    public Task<Intent> getSelectSnapshotIntent(@NonNull final String s, final boolean b, final boolean b2, final int n) {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzbw(this, s, b, b2, n));
    }
    
    public Task<AnnotatedData<SnapshotMetadataBuffer>> load(final boolean b) {
        return zzi.zzb(Games.Snapshots.load(this.asGoogleApiClient(), b), SnapshotsClient.zzea);
    }
    
    public Task<DataOrConflict<Snapshot>> open(@NonNull final SnapshotMetadata snapshotMetadata) {
        return zzc(Games.Snapshots.open(this.asGoogleApiClient(), snapshotMetadata));
    }
    
    public Task<DataOrConflict<Snapshot>> open(@NonNull final SnapshotMetadata snapshotMetadata, final int n) {
        return zzc(Games.Snapshots.open(this.asGoogleApiClient(), snapshotMetadata, n));
    }
    
    public Task<DataOrConflict<Snapshot>> open(@NonNull final String s, final boolean b) {
        return zzc(Games.Snapshots.open(this.asGoogleApiClient(), s, b));
    }
    
    public Task<DataOrConflict<Snapshot>> open(@NonNull final String s, final boolean b, final int n) {
        return zzc(Games.Snapshots.open(this.asGoogleApiClient(), s, b, n));
    }
    
    public Task<DataOrConflict<Snapshot>> resolveConflict(@NonNull final String s, @NonNull final Snapshot snapshot) {
        return zzc(Games.Snapshots.resolveConflict(this.asGoogleApiClient(), s, snapshot));
    }
    
    public Task<DataOrConflict<Snapshot>> resolveConflict(@NonNull final String s, @NonNull final String s2, @NonNull final SnapshotMetadataChange snapshotMetadataChange, @NonNull final SnapshotContents snapshotContents) {
        return zzc(Games.Snapshots.resolveConflict(this.asGoogleApiClient(), s, s2, snapshotMetadataChange, snapshotContents));
    }
    
    public static class DataOrConflict<T>
    {
        private final T data;
        private final SnapshotConflict zzeg;
        
        DataOrConflict(@Nullable final T data, @Nullable final SnapshotConflict zzeg) {
            this.data = data;
            this.zzeg = zzeg;
        }
        
        @Nullable
        public SnapshotConflict getConflict() {
            if (!this.isConflict()) {
                throw new IllegalStateException("getConflict called when there is no conflict.");
            }
            return this.zzeg;
        }
        
        @Nullable
        public T getData() {
            if (this.isConflict()) {
                throw new IllegalStateException("getData called when there is a conflict.");
            }
            return this.data;
        }
        
        public boolean isConflict() {
            return this.zzeg != null;
        }
    }
    
    public static class SnapshotConflict
    {
        private final Snapshot zzeh;
        private final String zzei;
        private final Snapshot zzej;
        private final SnapshotContents zzek;
        
        SnapshotConflict(@NonNull final Snapshot zzeh, @NonNull final String zzei, @NonNull final Snapshot zzej, @NonNull final SnapshotContents zzek) {
            this.zzeh = zzeh;
            this.zzei = zzei;
            this.zzej = zzej;
            this.zzek = zzek;
        }
        
        public String getConflictId() {
            return this.zzei;
        }
        
        public Snapshot getConflictingSnapshot() {
            return this.zzej;
        }
        
        public SnapshotContents getResolutionSnapshotContents() {
            return this.zzek;
        }
        
        public Snapshot getSnapshot() {
            return this.zzeh;
        }
    }
    
    public static class SnapshotContentUnavailableApiException extends ApiException
    {
        protected final SnapshotMetadata metadata;
        
        SnapshotContentUnavailableApiException(@NonNull final Status status, @NonNull final SnapshotMetadata metadata) {
            super(status);
            this.metadata = metadata;
        }
        
        public SnapshotMetadata getSnapshotMetadata() {
            return this.metadata;
        }
    }
}
