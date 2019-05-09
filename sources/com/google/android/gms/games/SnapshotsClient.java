package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.internal.zzp;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class SnapshotsClient extends zzu {
    public static final int DISPLAY_LIMIT_NONE = -1;
    public static final String EXTRA_SNAPSHOT_METADATA = "com.google.android.gms.games.SNAPSHOT_METADATA";
    public static final String EXTRA_SNAPSHOT_NEW = "com.google.android.gms.games.SNAPSHOT_NEW";
    public static final int RESOLUTION_POLICY_HIGHEST_PROGRESS = 4;
    public static final int RESOLUTION_POLICY_LAST_KNOWN_GOOD = 2;
    public static final int RESOLUTION_POLICY_LONGEST_PLAYTIME = 1;
    public static final int RESOLUTION_POLICY_MANUAL = -1;
    public static final int RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED = 3;
    private static final zzp<OpenSnapshotResult> zzdu = new zzby();
    private static final ResultConverter<DeleteSnapshotResult, String> zzdv = new zzbz();
    private static final ResultConverter<CommitSnapshotResult, SnapshotMetadata> zzdw = new zzca();
    private static final ResultConverter<OpenSnapshotResult, OpenSnapshotResult> zzdx = new zzcb();
    private static final zzr zzdy = new zzcc();
    private static final ResultConverter<OpenSnapshotResult, DataOrConflict<Snapshot>> zzdz = new zzbt();
    private static final ResultConverter<LoadSnapshotsResult, SnapshotMetadataBuffer> zzea = new zzbu();

    public static class DataOrConflict<T> {
        private final T data;
        private final SnapshotConflict zzeg;

        DataOrConflict(@Nullable T t, @Nullable SnapshotConflict snapshotConflict) {
            this.data = t;
            this.zzeg = snapshotConflict;
        }

        @Nullable
        public SnapshotConflict getConflict() {
            if (isConflict()) {
                return this.zzeg;
            }
            throw new IllegalStateException("getConflict called when there is no conflict.");
        }

        @Nullable
        public T getData() {
            if (!isConflict()) {
                return this.data;
            }
            throw new IllegalStateException("getData called when there is a conflict.");
        }

        public boolean isConflict() {
            return this.zzeg != null;
        }
    }

    public static class SnapshotConflict {
        private final Snapshot zzeh;
        private final String zzei;
        private final Snapshot zzej;
        private final SnapshotContents zzek;

        SnapshotConflict(@NonNull Snapshot snapshot, @NonNull String str, @NonNull Snapshot snapshot2, @NonNull SnapshotContents snapshotContents) {
            this.zzeh = snapshot;
            this.zzei = str;
            this.zzej = snapshot2;
            this.zzek = snapshotContents;
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

    public static class SnapshotContentUnavailableApiException extends ApiException {
        protected final SnapshotMetadata metadata;

        SnapshotContentUnavailableApiException(@NonNull Status status, @NonNull SnapshotMetadata snapshotMetadata) {
            super(status);
            this.metadata = snapshotMetadata;
        }

        public SnapshotMetadata getSnapshotMetadata() {
            return this.metadata;
        }
    }

    SnapshotsClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    SnapshotsClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    @Nullable
    public static SnapshotMetadata getSnapshotFromBundle(@NonNull Bundle bundle) {
        return Games.Snapshots.getSnapshotFromBundle(bundle);
    }

    private static Task<DataOrConflict<Snapshot>> zzc(@NonNull PendingResult<OpenSnapshotResult> pendingResult) {
        return zzi.zza((PendingResult) pendingResult, zzdy, zzdz, zzdx, zzdu);
    }

    public Task<SnapshotMetadata> commitAndClose(@NonNull Snapshot snapshot, @NonNull SnapshotMetadataChange snapshotMetadataChange) {
        return zzi.toTask(Games.Snapshots.commitAndClose(asGoogleApiClient(), snapshot, snapshotMetadataChange), zzdw);
    }

    public Task<String> delete(@NonNull SnapshotMetadata snapshotMetadata) {
        return zzi.toTask(Games.Snapshots.delete(asGoogleApiClient(), snapshotMetadata), zzdv);
    }

    public Task<Void> discardAndClose(@NonNull Snapshot snapshot) {
        return doWrite(new zzbx(this, snapshot));
    }

    public Task<Integer> getMaxCoverImageSize() {
        return doRead(new zzbv(this));
    }

    public Task<Integer> getMaxDataSize() {
        return doRead(new zzbs(this));
    }

    public Task<Intent> getSelectSnapshotIntent(@NonNull String str, boolean z, boolean z2, int i) {
        return doRead(new zzbw(this, str, z, z2, i));
    }

    public Task<AnnotatedData<SnapshotMetadataBuffer>> load(boolean z) {
        return zzi.zzb(Games.Snapshots.load(asGoogleApiClient(), z), zzea);
    }

    public Task<DataOrConflict<Snapshot>> open(@NonNull SnapshotMetadata snapshotMetadata) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), snapshotMetadata));
    }

    public Task<DataOrConflict<Snapshot>> open(@NonNull SnapshotMetadata snapshotMetadata, int i) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), snapshotMetadata, i));
    }

    public Task<DataOrConflict<Snapshot>> open(@NonNull String str, boolean z) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), str, z));
    }

    public Task<DataOrConflict<Snapshot>> open(@NonNull String str, boolean z, int i) {
        return zzc(Games.Snapshots.open(asGoogleApiClient(), str, z, i));
    }

    public Task<DataOrConflict<Snapshot>> resolveConflict(@NonNull String str, @NonNull Snapshot snapshot) {
        return zzc(Games.Snapshots.resolveConflict(asGoogleApiClient(), str, snapshot));
    }

    public Task<DataOrConflict<Snapshot>> resolveConflict(@NonNull String str, @NonNull String str2, @NonNull SnapshotMetadataChange snapshotMetadataChange, @NonNull SnapshotContents snapshotContents) {
        return zzc(Games.Snapshots.resolveConflict(asGoogleApiClient(), str, str2, snapshotMetadataChange, snapshotContents));
    }
}
