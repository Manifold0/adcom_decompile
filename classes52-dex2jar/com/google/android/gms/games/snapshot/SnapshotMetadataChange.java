// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import android.net.Uri;
import com.google.android.gms.common.data.BitmapTeleporter;
import android.graphics.Bitmap;

public interface SnapshotMetadataChange
{
    public static final SnapshotMetadataChange EMPTY_CHANGE = new zze();
    
    Bitmap getCoverImage();
    
    String getDescription();
    
    Long getPlayedTimeMillis();
    
    Long getProgressValue();
    
    BitmapTeleporter zzcm();
    
    public static final class Builder
    {
        private String description;
        private Long zzqp;
        private Long zzqq;
        private BitmapTeleporter zzqr;
        private Uri zzqs;
        
        public final SnapshotMetadataChange build() {
            return new zze(this.description, this.zzqp, this.zzqr, this.zzqs, this.zzqq);
        }
        
        public final Builder fromMetadata(final SnapshotMetadata snapshotMetadata) {
            this.description = snapshotMetadata.getDescription();
            this.zzqp = snapshotMetadata.getPlayedTime();
            this.zzqq = snapshotMetadata.getProgressValue();
            if (this.zzqp == -1L) {
                this.zzqp = null;
            }
            this.zzqs = snapshotMetadata.getCoverImageUri();
            if (this.zzqs != null) {
                this.zzqr = null;
            }
            return this;
        }
        
        public final Builder setCoverImage(final Bitmap bitmap) {
            this.zzqr = new BitmapTeleporter(bitmap);
            this.zzqs = null;
            return this;
        }
        
        public final Builder setDescription(final String description) {
            this.description = description;
            return this;
        }
        
        public final Builder setPlayedTimeMillis(final long n) {
            this.zzqp = n;
            return this;
        }
        
        public final Builder setProgressValue(final long n) {
            this.zzqq = n;
            return this;
        }
    }
}
