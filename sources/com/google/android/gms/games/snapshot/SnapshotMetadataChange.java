package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.data.BitmapTeleporter;

public interface SnapshotMetadataChange {
    public static final SnapshotMetadataChange EMPTY_CHANGE = new zze();

    public static final class Builder {
        private String description;
        private Long zzqp;
        private Long zzqq;
        private BitmapTeleporter zzqr;
        private Uri zzqs;

        public final SnapshotMetadataChange build() {
            return new zze(this.description, this.zzqp, this.zzqr, this.zzqs, this.zzqq);
        }

        public final Builder fromMetadata(SnapshotMetadata snapshotMetadata) {
            this.description = snapshotMetadata.getDescription();
            this.zzqp = Long.valueOf(snapshotMetadata.getPlayedTime());
            this.zzqq = Long.valueOf(snapshotMetadata.getProgressValue());
            if (this.zzqp.longValue() == -1) {
                this.zzqp = null;
            }
            this.zzqs = snapshotMetadata.getCoverImageUri();
            if (this.zzqs != null) {
                this.zzqr = null;
            }
            return this;
        }

        public final Builder setCoverImage(Bitmap bitmap) {
            this.zzqr = new BitmapTeleporter(bitmap);
            this.zzqs = null;
            return this;
        }

        public final Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public final Builder setPlayedTimeMillis(long j) {
            this.zzqp = Long.valueOf(j);
            return this;
        }

        public final Builder setProgressValue(long j) {
            this.zzqq = Long.valueOf(j);
            return this;
        }
    }

    Bitmap getCoverImage();

    String getDescription();

    Long getPlayedTimeMillis();

    Long getProgressValue();

    BitmapTeleporter zzcm();
}
